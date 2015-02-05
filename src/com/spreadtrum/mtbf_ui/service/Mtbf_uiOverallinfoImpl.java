package com.spreadtrum.mtbf_ui.service;

import java.util.ArrayList;
import java.util.List;


import com.spreadtrum.mtbf_ui.dao.MTBF_uiErrorInfoDAO;
import com.spreadtrum.mtbf_ui.dao.MTBF_uiFormDAO;
import com.spreadtrum.mtbf_ui.dao.impl.MTBF_uiErrorInfoDAOImpl;
import com.spreadtrum.mtbf_ui.dao.impl.MTBF_uiFormDAOImpl;
import com.spreadtrum.mtbf_ui.model.MTBF_uiErrorInfo;

public class Mtbf_uiOverallinfoImpl implements Mtbf_uiOverallinfo {

	@Override
	public List<Mtbf_uiSummary> receiveOverallTestinfo(String testFormName) {
		
		MTBF_uiFormDAO MTBF_uiFormDAO = new MTBF_uiFormDAOImpl();
		List<Mtbf_uiSummary> results = new ArrayList<Mtbf_uiSummary>();
		String projectName = MTBF_uiFormDAO.getMTBF_uiFormTestByTableName(testFormName).getProjectID().getProjectName();
		String SoftwareVsn = MTBF_uiFormDAO.getMTBF_uiFormTestByTableName(testFormName).getSoftwareVsn();
		String pacPath = MTBF_uiFormDAO.getMTBF_uiFormTestByTableName(testFormName).getPacPath();
		String conclusion = MTBF_uiFormDAO.getMTBF_uiFormTestByTableName(testFormName).getConclusion();

		

		Mtbf_uiSummary result = new Mtbf_uiSummary();
		result.setProjectName(projectName);
		result.setSoftwareVsn(SoftwareVsn);
		result.setPacPath(pacPath);
		result.setConclusion(conclusion);
		results.add(result);
		return results;
	}

	@Override
	public List<Mtbf_uiStatisticinfo> receiveStatisticinfo(String testFormName) {
		MTBF_uiFormDAO MTBF_uiFormDAO = new MTBF_uiFormDAOImpl();
		List<Mtbf_uiStatisticinfo> results = new ArrayList<Mtbf_uiStatisticinfo>();
		int deviceNum = MTBF_uiFormDAO.getMTBF_uiFormTestByTableName(
				testFormName).getDeviceNum();
		int runNum = MTBF_uiFormDAO.getMTBF_uiFormTestByTableName(testFormName)
				.getRunNum();
		String runTimeAll = MTBF_uiFormDAO.getMTBF_uiFormTestByTableName(
				testFormName).getRunTimeAll();
		int errorTotal = MTBF_uiFormDAO.getMTBF_uiFormTestByTableName(
				testFormName).getErrorTotal();
		float mtbfValue = MTBF_uiFormDAO.getMTBF_uiFormTestByTableName(
				testFormName).getMtbfValue();
		
		Mtbf_uiStatisticinfo result =new Mtbf_uiStatisticinfo();
		result.setDeviceNum(deviceNum);
		result.setRunNum(runNum);
		result.setRunTimeAll(runTimeAll);
		result.setErrorTotal(errorTotal);
		result.setMtbfValue(mtbfValue);
		results.add(result);
		return results;
	}
	
	@Override
	public 	List<Mtbf_uiErrorinfo> receiveMTBF_uiErrorInfo(String testFormName) {
		
		List<Mtbf_uiErrorinfo> results =new ArrayList<Mtbf_uiErrorinfo>();
		MTBF_uiErrorInfoDAO MTBF_uierrorInfo = new MTBF_uiErrorInfoDAOImpl();
		
		List<MTBF_uiErrorInfo> uierrorInfo = MTBF_uierrorInfo.getMTBF_uiErrorInfoByTableName(testFormName);
		for (int i=0;i<uierrorInfo.size();i++){
			String deviceNum1 = uierrorInfo.get(i).getDeviceNum();
			int errorEach=uierrorInfo.get(i).getErrorEach();
					
			Mtbf_uiErrorinfo result =new Mtbf_uiErrorinfo();
			result.setDeviceNum1(deviceNum1);
			result.setErrorEach(errorEach);
			results.add(result);
			
		}
		return results;
	}

}
