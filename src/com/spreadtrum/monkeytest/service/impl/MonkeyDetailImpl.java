package com.spreadtrum.monkeytest.service.impl;

import java.util.ArrayList;
import java.sql.Date;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.List;

import com.spreadtrum.monkeytest.model.*;
import com.spreadtrum.monkeytest.dao.*;
import com.spreadtrum.monkeytest.dao.impl.*;
import com.spreadtrum.monkeytest.vo.*;
import com.spreadtrum.monkeytest.service.MonkeyDetail;

public class MonkeyDetailImpl implements MonkeyDetail {
	private BugInfoDAO bugInfoDao;
	private ErrorInfoDAO errInfoDAO;
	private PhoneTestInfoDAO phoneTestInfoDAO;
	private TestFormDAO testFormDAO;

	public MonkeyDetailImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String receivePACPath(String testFormName) {
		testFormDAO = new TestFormDAOImpl();
		TestForm testForm = testFormDAO.getTestFormInfo(testFormName);
		String pacPath = testForm.getPacPath();
		return pacPath;
	}

	@Override
	public OverallTimeInfo receiveOverallTimeInfo(String testFormName) {
		testFormDAO = new TestFormDAOImpl();
		TestForm testForm = testFormDAO.getTestFormInfo(testFormName);
		OverallTimeInfo result = new OverallTimeInfo();

		result.setTestVersion(testForm.getPacVersion());
		result.setAverageStopTime(testForm.getAverStopTime());
		result.setAverageMiddleTime(testForm.getMidStopTime());
		result.setFirstErrAverageTime(testForm.getAverFirstErrTime());
		result.setFirstErrMiddleTime(testForm.getMidFirstErrTime());
		result.setAverJavaCrashCount(testForm.getAverJavaCrashCount());
		result.setAverNativeCrashCount(testForm.getAverNativeCrashCount());
		result.setAverAnrCount(testForm.getAverAnrCount());
		return result;
	}

	@Override
	public List<UperTestInfo> receiveUperTestInfo(String testFormName) {
		List<UperTestInfo> result = new ArrayList<UperTestInfo>();
		testFormDAO = new TestFormDAOImpl();
		phoneTestInfoDAO = new PhoneTestInfoDAOImpl();
		TestForm testForm = testFormDAO.getTestFormInfo(testFormName);
		int formID = testForm.getId();
		// 从手机信息表中查找出所有表单ID为formID的手机信息（大概10台手机），得到一个list，做个循环，依次取出每台手机所对应的信息即可
		List<PhoneTestInfo> phones = phoneTestInfoDAO.getPhoneTestInfo(formID);
        if (null != phones){
		Iterator<PhoneTestInfo> phoneIt = phones.iterator();

		while (phoneIt.hasNext()) {
			bugInfoDao = new BugInfoDAOImpl();
			errInfoDAO = new ErrorInfoDAOImpl();

			// 根据表单ID查找出所有的bug，得到list
			List<BugInfo> bugList = bugInfoDao.getBugInfo(formID);
			// 根据表单ID查找出所有的错误信息，得到list
			List<ErrorInfo> errInfoList = errInfoDAO.getErrorInfo(formID);

			UperTestInfo uper = new UperTestInfo();
			PhoneTestInfo p = phoneIt.next();
			String dev = p.getDeviceId().getDeviceName();
			// String firstErrBugID = p.getFirstErrBugID();
			Float firstErrTime = p.getFirstErrTime();
			String firstErrorType = p.getFirstErrType().getErrtype();

			String bugID = "";
			Float firstJavaCrashTime = 0f;
			int numOfJavaCrash = 0;
			String modleOfJavaCrash = "";
			Float firstNativeCrashTime = 0f;
			int numOfNativeCrash = 0;
			String modleOfNativeCrash = "";
			Float firstANRTime = 0f;
			int numOfANR = 0;
			String modleOfANR = "";

			if (null != bugList) {
				Iterator<BugInfo> bugIt = bugList.iterator();
				while (bugIt.hasNext()) {
					BugInfo bug = bugIt.next();
					String dev_bug = bug.getDeviceID().getDeviceName();
					if ((dev_bug.equals(dev)) && (bug.getBugFlag() == 0)) {
						bugID = bugID + " " + bug.getBugID();
					}
				}
			}
			if (bugID.length() > 0) {
				bugID = bugID.substring(1);
			}

			List<ErrorInfo> errInfoSameDevList = new ArrayList<ErrorInfo>();
			if (null != errInfoList) {
				Iterator<ErrorInfo> errIt = errInfoList.iterator();
				while (errIt.hasNext()) {
					ErrorInfo err = errIt.next();
					String dev_err = err.getDeviceID().getDeviceName();
					if (dev_err.equals(dev)) {
						errInfoSameDevList.add(err);
					}
				}
			}

			if (null != errInfoSameDevList) {
				Iterator<ErrorInfo> errSameDevIt = errInfoSameDevList
						.iterator();
				while (errSameDevIt.hasNext()) {
					ErrorInfo errSameDev = errSameDevIt.next();
					if (errSameDev.getErrTypeID().getErrtype()
							.equalsIgnoreCase("javacrash")) {
						// 得到首次出错时长
						if ((firstJavaCrashTime > errSameDev.getFirstErrtime())
								|| (firstJavaCrashTime == 0f)) {
							firstJavaCrashTime = errSameDev.getFirstErrtime();
						}
						numOfJavaCrash = numOfJavaCrash
								+ errSameDev.getModuleErrCount();
						modleOfJavaCrash = modleOfJavaCrash + " "
								+ errSameDev.getErrModule();
					} else if (errSameDev.getErrTypeID().getErrtype()
							.equalsIgnoreCase("nativecrash")) {
						// 得到首次出错时长
						if ((firstNativeCrashTime > errSameDev
								.getFirstErrtime())
								|| (firstNativeCrashTime == 0f)) {
							firstNativeCrashTime = errSameDev.getFirstErrtime();
						}
						numOfNativeCrash = numOfNativeCrash
								+ errSameDev.getModuleErrCount();
						modleOfNativeCrash = modleOfNativeCrash + " "
								+ errSameDev.getErrModule();
					} else if (errSameDev.getErrTypeID().getErrtype()
							.equalsIgnoreCase("anr")) {
						// 得到首次出错时长
						if ((firstANRTime > errSameDev.getFirstErrtime())
								|| (firstANRTime == 0f)) {
							firstANRTime = errSameDev.getFirstErrtime();
						}
						numOfANR = numOfANR + errSameDev.getModuleErrCount();
						modleOfANR = modleOfANR + " "
								+ errSameDev.getErrModule();						
					}
				}
			}

			if (modleOfJavaCrash.length() > 0) {
				modleOfJavaCrash = modleOfJavaCrash.substring(1);
			}
			if (modleOfNativeCrash.length() > 0) {
				modleOfNativeCrash = modleOfNativeCrash.substring(1);
			}
			if (modleOfANR.length() > 0) {
				modleOfANR = modleOfANR.substring(1);
			}
			if (firstJavaCrashTime == 0f) {
				firstJavaCrashTime = null;
			}
			if (firstNativeCrashTime == 0f) {
				firstNativeCrashTime = null;
			}
			if (firstANRTime == 0f) {
				firstANRTime = null;
			}
			// 把单台手机的信息写入到 uper
			uper.setTestPhone(dev);
			uper.setBugID(bugID);
			uper.setFirstErrTime(firstErrTime);
			uper.setFirstErrorType(firstErrorType);
			uper.setFirstJavaCrashTime(firstJavaCrashTime);
			uper.setNumOfJavaCrash(numOfJavaCrash);
			uper.setModleOfJavaCrash(modleOfJavaCrash);
			uper.setFirstNativeCrashTime(firstNativeCrashTime);
			uper.setNumOfNativeCrash(numOfNativeCrash);
			uper.setModleOfNativeCrash(modleOfNativeCrash);
			uper.setFirstANRTime(firstANRTime);
			uper.setNumOfANR(numOfANR);
			uper.setModleOfANR(modleOfANR);

			List<String> devCampare = new ArrayList<String>();
			for (int i = 0; i < result.size(); i++) {
				devCampare.add(result.get(i).getTestPhone());
			}			
                        if (devCampare.contains(dev)) {
	                    for (int i = 0; i < result.size(); i++) {
	            	        if (result.get(i).getTestPhone() == dev) {
	     	        	    result.get(i).setFirstErrorType(result.get(i).getFirstErrorType() 
                                    + "、" + firstErrorType);
	     	           	    break;
	     	                } 
	            	    }             		
	                } else {
	            	    result.add(uper);
	                }
                    }
		    return result;
                }
            else {
            	//JOptionPane.showMessageDialog(null, "测试数据不完整");
        	    return null;
            }
	}

	@Override
	public List<LowerTestInfo> receiveLowerTestInfo(String testFormName) {
		List<LowerTestInfo> result = new ArrayList<LowerTestInfo>();
		testFormDAO = new TestFormDAOImpl();
		phoneTestInfoDAO = new PhoneTestInfoDAOImpl();
		TestForm testForm = testFormDAO.getTestFormInfo(testFormName);
		int formID = testForm.getId();
		// 从手机信息表中查找出所有表单ID为formID的手机信息（大概10台手机），得到一个list，做个循环，依次取出每台手机所对应的信息即可
		List<PhoneTestInfo> phones = phoneTestInfoDAO.getPhoneTestInfo(formID);
        if (null != phones) {
		Iterator<PhoneTestInfo> phoneIt = phones.iterator();
		while (phoneIt.hasNext()) {
			LowerTestInfo lower = new LowerTestInfo();
			PhoneTestInfo p = phoneIt.next();
			String dev = p.getDeviceId().getDeviceName();
			String underBugID = p.getUnderlyBugID();
			Float testDuringTime = p.getRunTime();
			String testFinalState = p.getFinalStatus();
			String description = p.getPhenomenonDesc();
			lower.setTestPhone(dev);
			lower.setBugID(underBugID);
			lower.setTestDuringTime(testDuringTime);
			lower.setTestFinalState(testFinalState);
			lower.setDescription(description);
			List<String> devCampare = new ArrayList<String>();
			
	                for (int i = 0; i < result.size(); i++) {	            
	                    devCampare.add(result.get(i).getTestPhone());
	                }
                        if (!devCampare.contains(dev)) {
            	            result.add(lower);
                        }
		}
		return result;
        }
        else {
        	return null;
        }
	}

	@Override
	public TreeMap<String, Float> receiveProjectAverageStopTime(String project) {
		TreeMap<String, Float> results = new TreeMap<String, Float>();
		testFormDAO = new TestFormDAOImpl();
		List<String> formNameList = testFormDAO.getTestFormByProject(project);
		
		java.util.Date lastUtilTime = new java.util.Date(testFormDAO
				.getLastTimeByProject(project).getTime());
		
		Iterator<String> it = formNameList.iterator();
		while (it.hasNext()) {
			String formName = it.next();
			TestForm form = testFormDAO.getTestFormInfo(formName);
			java.util.Date nTime = new java.util.Date(form.getTdate().getTime());
			// 最近一周的数据
			if (lastUtilTime.getTime() - nTime.getTime() <= 7 * 24 * 60 * 60
					* 1000) {
				results.put(form.getPacVersion(), form.getAverStopTime());
			}

		}
		return results;
	}

	@Override
	public TreeMap<String, Float> receiveProjectAverageStopTime_FormName(
			String formName) {
		TreeMap<String, Float> results = new TreeMap<String, Float>();
		testFormDAO = new TestFormDAOImpl();

		TestForm testForm = testFormDAO.getTestFormInfo(formName);
		String project = testForm.getHardwareInfo();
		Date d = testForm.getTdate();
		java.util.Date lastUtilTime = new java.util.Date(d.getTime());

		List<String> formNameList = testFormDAO.getTestFormByProject(project);
		Iterator<String> it = formNameList.iterator();
		while (it.hasNext()) {
			String formNameT = it.next();
			TestForm form = testFormDAO.getTestFormInfo(formNameT);
			java.util.Date nTime = new java.util.Date(form.getTdate().getTime());
			// 给定testForm之前一周的数据
			if ((lastUtilTime.getTime() - nTime.getTime() <= 7 * 24 * 60 * 60
					* 1000)
					&& (lastUtilTime.getTime() - nTime.getTime() >= 0)) {
				results.put(form.getPacVersion(), form.getAverStopTime());
			}

		}
		return results;
	}

	@Override
	public TreeMap<String, Float> receiveProjectMiddleStopTime(String project) {
		TreeMap<String, Float> results = new TreeMap<String, Float>();
		testFormDAO = new TestFormDAOImpl();
		java.util.Date lastUtilTime = new java.util.Date(testFormDAO
				.getLastTimeByProject(project).getTime());
		List<String> formNameList = testFormDAO.getTestFormByProject(project);
		Iterator<String> it = formNameList.iterator();
		while (it.hasNext()) {
			String formName = it.next();
			TestForm form = testFormDAO.getTestFormInfo(formName);
			java.util.Date nTime = new java.util.Date(form.getTdate().getTime());
			// 最近一周的数据
			if (lastUtilTime.getTime() - nTime.getTime() <= 7 * 24 * 60 * 60
					* 1000) {
				results.put(form.getPacVersion(), form.getMidStopTime());
			}
		}
		return results;
	}

	@Override
	public TreeMap<String, Float> receiveProjectMiddleStopTime_FormName(
			String formName) {
		TreeMap<String, Float> results = new TreeMap<String, Float>();
		testFormDAO = new TestFormDAOImpl();

		TestForm testForm = testFormDAO.getTestFormInfo(formName);
		String project = testForm.getHardwareInfo();
		Date d = testForm.getTdate();
		java.util.Date lastUtilTime = new java.util.Date(d.getTime());

		List<String> formNameList = testFormDAO.getTestFormByProject(project);
		Iterator<String> it = formNameList.iterator();
		while (it.hasNext()) {
			String formNameT = it.next();
			TestForm form = testFormDAO.getTestFormInfo(formNameT);
			java.util.Date nTime = new java.util.Date(form.getTdate().getTime());
			// 给定testForm之前一周的数据
			if ((lastUtilTime.getTime() - nTime.getTime() <= 7 * 24 * 60 * 60
					* 1000)
					&& (lastUtilTime.getTime() - nTime.getTime() >= 0)) {
				results.put(form.getPacVersion(), form.getMidStopTime());
			}

		}
		return results;
	}

	@Override
	public TreeMap<String, Float> receiveProjectFirstErrAverageTime(String project) {
		TreeMap<String, Float> results = new TreeMap<String, Float>();
		testFormDAO = new TestFormDAOImpl();
		java.util.Date lastUtilTime = new java.util.Date(testFormDAO
				.getLastTimeByProject(project).getTime());
		List<String> formNameList = testFormDAO.getTestFormByProject(project);
		Iterator<String> it = formNameList.iterator();
		while (it.hasNext()) {
			String formName = it.next();
			TestForm form = testFormDAO.getTestFormInfo(formName);
			java.util.Date nTime = new java.util.Date(form.getTdate().getTime());
			// 最近一周的数据
			if (lastUtilTime.getTime() - nTime.getTime() <= 7 * 24 * 60 * 60
					* 1000) {
				results.put(form.getPacVersion(), form.getAverFirstErrTime());
			}
		}
		return results;
	}

	@Override
	public TreeMap<String, Float> receiveProjectFirstErrAverageTime_FormName(
			String formName) {
		TreeMap<String, Float> results = new TreeMap<String, Float>();
		testFormDAO = new TestFormDAOImpl();

		TestForm testForm = testFormDAO.getTestFormInfo(formName);
		String project = testForm.getHardwareInfo();
		Date d = testForm.getTdate();
		java.util.Date lastUtilTime = new java.util.Date(d.getTime());

		List<String> formNameList = testFormDAO.getTestFormByProject(project);
		Iterator<String> it = formNameList.iterator();
		while (it.hasNext()) {
			String formNameT = it.next();
			TestForm form = testFormDAO.getTestFormInfo(formNameT);
			java.util.Date nTime = new java.util.Date(form.getTdate().getTime());
			// 给定testForm之前一周的数据
			if ((lastUtilTime.getTime() - nTime.getTime() <= 7 * 24 * 60 * 60
					* 1000)
					&& (lastUtilTime.getTime() - nTime.getTime() >= 0)) {
				results.put(form.getPacVersion(), form.getAverFirstErrTime());
			}

		}
		return results;
	}

	@Override
	public TreeMap<String, Float> receiveProjectFirstErrMiddleTime(String project) {
		TreeMap<String, Float> results = new TreeMap<String, Float>();
		testFormDAO = new TestFormDAOImpl();
		java.util.Date lastUtilTime = new java.util.Date(testFormDAO
				.getLastTimeByProject(project).getTime());
		List<String> formNameList = testFormDAO.getTestFormByProject(project);
		Iterator<String> it = formNameList.iterator();
		while (it.hasNext()) {
			String formName = it.next();
			TestForm form = testFormDAO.getTestFormInfo(formName);
			java.util.Date nTime = new java.util.Date(form.getTdate().getTime());
			// 最近一周的数据
			if (lastUtilTime.getTime() - nTime.getTime() <= 7 * 24 * 60 * 60
					* 1000) {
				results.put(form.getPacVersion(), form.getMidFirstErrTime());
			}
		}
		return results;
	}

	@Override
	public TreeMap<String, Float> receiveProjectFirstErrMiddleTime_FormName(
			String formName) {
		TreeMap<String, Float> results = new TreeMap<String, Float>();
		testFormDAO = new TestFormDAOImpl();

		TestForm testForm = testFormDAO.getTestFormInfo(formName);
		String project = testForm.getHardwareInfo();
		Date d = testForm.getTdate();
		java.util.Date lastUtilTime = new java.util.Date(d.getTime());

		List<String> formNameList = testFormDAO.getTestFormByProject(project);
		Iterator<String> it = formNameList.iterator();
		while (it.hasNext()) {
			String formNameT = it.next();
			TestForm form = testFormDAO.getTestFormInfo(formNameT);
			java.util.Date nTime = new java.util.Date(form.getTdate().getTime());
			// 给定testForm之前一周的数据
			if ((lastUtilTime.getTime() - nTime.getTime() <= 7 * 24 * 60 * 60
					* 1000)
					&& (lastUtilTime.getTime() - nTime.getTime() >= 0)) {
				results.put(form.getPacVersion(), form.getMidFirstErrTime());
			}

		}
		return results;
	}

	@Override
	public String findLastTestFormNameByProjectName(String project) {
		testFormDAO = new TestFormDAOImpl();
		String formName = testFormDAO.getLastTestFormByProject(project);
		if (null != formName) {
			return formName;
		}
		return null;
	}

	@Override
	public String findTestFormNameByPacVersionAndProject(String PacVersion,
			String project) {
		testFormDAO = new TestFormDAOImpl();
		List<String> formNames = testFormDAO
				.getTestFormByPacVersion(PacVersion);
		if (null != formNames) {
			Iterator<String> it = formNames.iterator();
			while (it.hasNext()) {
				String formName = it.next();
				TestForm form = testFormDAO.getTestFormInfo(formName);
				if (form.getHardwareInfo().equalsIgnoreCase(project)) {
					return form.getFormName();
				}
			}
		}
		return null;
	}

	@Override
	public String receiveProject(String formName) {

		testFormDAO = new TestFormDAOImpl();
		TestForm testForm = testFormDAO.getTestFormInfo(formName);
		String project = testForm.getHardwareInfo();
		return project;
	}
	@Override
	public List<OverallTimeInfo> receiveOverallTimeInfo_List(String testFormName) {
		testFormDAO = new TestFormDAOImpl();
		TestForm testForm = testFormDAO.getTestFormInfo(testFormName);
		String project = testForm.getHardwareInfo();
		List<OverallTimeInfo> results = new ArrayList<OverallTimeInfo>();
		
		Date d = testForm.getTdate();
		java.util.Date lastUtilTime = new java.util.Date(d.getTime());

		List<String> formNameList = testFormDAO.getTestFormByProject(project);
		Iterator<String> it = formNameList.iterator();
		while (it.hasNext()) {
			OverallTimeInfo result = new OverallTimeInfo();
			String formNameT = it.next();
			TestForm form = testFormDAO.getTestFormInfo(formNameT);
			java.util.Date nTime = new java.util.Date(form.getTdate().getTime());
			// 给定testForm之前一周的数据
			if ((lastUtilTime.getTime() - nTime.getTime() <= 7 * 24 * 60 * 60 * 1000)&& (lastUtilTime.getTime() - nTime.getTime() >= 0)) {
				result.setTestVersion(form.getPacVersion());
				result.setAverageStopTime(form.getAverStopTime());
				result.setAverageMiddleTime(form.getMidStopTime());
				result.setFirstErrAverageTime(form.getAverFirstErrTime());
				result.setFirstErrMiddleTime(form.getMidFirstErrTime());
				result.setAverJavaCrashCount(form.getAverJavaCrashCount());
				result.setAverNativeCrashCount(form.getAverNativeCrashCount());
				result.setAverAnrCount(form.getAverAnrCount());
				results.add(result);
			}
		}
		return results;
	}

	@Override
	public  String receiveLogPath(String logPath, String testFormName) {
		testFormDAO = new TestFormDAOImpl();
		List<String> projectLogPath = testFormDAO.getTestFormProp(logPath,testFormName);
		return projectLogPath.get(0);
	}
}
