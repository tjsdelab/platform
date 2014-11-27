package com.spreadtrum.EPE.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.spreadtrum.EPE.dao.EPEDeviceInfoDAO;
import com.spreadtrum.EPE.dao.EPEErrorInfoDAO;
import com.spreadtrum.EPE.dao.EPEFailTypeDAO;
import com.spreadtrum.EPE.dao.EPEProjectInfoDAO;
import com.spreadtrum.EPE.dao.EPETestFormDAO;
import com.spreadtrum.EPE.dao.impl.EPEDeviceInfoDAOImpl;
import com.spreadtrum.EPE.dao.impl.EPEErrorInfoDAOImpl;
import com.spreadtrum.EPE.dao.impl.EPEFailTypeDAOImpl;
import com.spreadtrum.EPE.dao.impl.EPEProjectInfoDAOImpl;
import com.spreadtrum.EPE.dao.impl.EPETestFormDAOImpl;
import com.spreadtrum.EPE.model.EPEDeviceInfo;
import com.spreadtrum.EPE.model.EPEErrorInfo;
import com.spreadtrum.EPE.model.EPEFailType;
import com.spreadtrum.EPE.model.EPETestForm;
import com.spreadtrum.monkeytest.dao.impl.ProjectFormDAOImpl;


public class EPEHomeAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> projectList = new ArrayList<String>();
	private String currentProject;
	private String currentFormName = null;
	private String version;
	private String conclusion;
	private String type;
	private String searchProject;


	private float epeValue;
	private int sumDevice;
	private int passDevice;
	private int failDevice;
	private float averageRunTime;
	private float medianRunTime;
	private float averageFirstError;
	private float medianFirstError;
	private int freezeNum;
	private int blackNum;
	private int restartNum;
	private int sysdumpNum;
	private int unpowerNum;
	private int otherNum;
	
	//获取EPETestFormDAO对象
	private EPETestFormDAO EPEFormDAO = new EPETestFormDAOImpl();
	//获取工程list对象
	private EPEProjectInfoDAO EPEProjectList = new EPEProjectInfoDAOImpl();
	//获取第一个工程名的最新Form
	private EPETestForm  EPEForm = new EPETestForm();
	//获取EPEErrorInfo对象
	private EPEErrorInfoDAO EPEErrorDAO = new EPEErrorInfoDAOImpl();
	private List<EPEErrorInfo> allErrorList = new ArrayList<EPEErrorInfo>();
	private List<EPEErrorInfo> javaCrashList = new ArrayList<EPEErrorInfo>();
	private List<EPEErrorInfo> nativeCrashList = new ArrayList<EPEErrorInfo>();
	private List<EPEErrorInfo> ANRList = new ArrayList<EPEErrorInfo>();
	//获取EPEDeviceInfo对象
	private EPEDeviceInfoDAO EPEDeviceDAO = new EPEDeviceInfoDAOImpl();
	private List<EPEDeviceInfo> allDeviceList = new ArrayList<EPEDeviceInfo>();
	//获取failType对象
	private EPEFailTypeDAO EPEFailDAO = new EPEFailTypeDAOImpl();
	private List<EPEFailType> allFailList = new ArrayList<EPEFailType>();
	private List<CrashInfo> craInfoList = new ArrayList<CrashInfo>();
	
	private EPEDeviceInfo device = new EPEDeviceInfo();
	private EPEFailType fail = new EPEFailType();
	private int javaCrashTotal=0;


	private int nativeCrashTotal=0;
	private int anrTotal=0;
	
	private int javaCrashNum=0;
	private int nativeCrashNum=0;
	private int anrNum=0;
	//sortedProject是用在下拉列表中显示的工程名
	public String execute(){
		//有无有效工程，分开处理
		if(currentFormName != null){
			getValidProject();
			EPEForm = EPEFormDAO.getEPEFormTestByTableName(currentFormName);
			currentProject = EPEForm.getProjectID().getProjectName();
			getFormInfo();
			getErrorInfoByFormName();//通过表单名获取测试错误信息
		    getDeviceInfoByFormName();//通过表单名获取设备信息
		    byProjectAndVersion();
			
		    return SUCCESS;//如果当前有表单名，直接使用表单名初始化该页面即可
		}
		else if(EPEProjectList.getValidProjectName() != null){//存在有效的工程
			getValidProject();
		    currentProject = projectList.get(0);//采用下拉列表的第一个工程名作为要显示的工程	
			/**************************/
			/*通过工程名获得最新的表单名***/
			/**************************/
			getLastFormNameByProjectName();//通过工程名对当前页面的表单名进行设置
			getErrorInfoByFormName();//通过表单名获取测试错误信息
		    getDeviceInfoByFormName();//通过表单名获取测试错误信息
		    byProjectAndVersion();
		}
		return SUCCESS;
	}
	
	public String versionJump(){
		getValidProject();
		currentFormName = EPEFormDAO.getTestFormByProjectVersion(currentProject,version);
		EPEForm = EPEFormDAO.getEPEFormTestByTableName(currentFormName);
		//currentProject = EPEForm.getProjectID().getProjectName();
		getFormInfo();
		getErrorInfoByFormName();//通过表单名获取测试错误信息
	    getDeviceInfoByFormName();//通过表单名获取设备信息
	    byProjectAndVersion();
		
		return SUCCESS;
	}
	
	public String dropDownProject(){
		getValidProject();
		/*******************************/
		/*通过工程名获得最新的表单名***/
		/******************************/
		getLastFormNameByProjectName();
		getErrorInfoByFormName();
		getDeviceInfoByFormName();
		byProjectAndVersion();
		return SUCCESS;
	}
	
	public void getValidProject(){
		/***************************************************************************************************/
		/*  获取有效工程名  */
		/***************************************************************************************************/
		//下拉列表中的有效工程名
		projectList = EPEProjectList.getValidProjectName();
	}
	
	public void getLastFormNameByProjectName(){
		/***************************************************************************************************/
		/*  通过工程名获取最新的表单  */
		/***************************************************************************************************/
			if(EPEFormDAO.getEPELastTestByProject(currentProject) != null){
			EPEForm = EPEFormDAO.getEPELastTestByProject(currentProject);
			getFormInfo();

	    }
	}	
	
	public void getFormInfo(){
		//获取SanityTestForm中的Version等信息
		version = EPEForm.getVersion();
		//获取SanityTestForm中的testFormName等信息
		currentFormName = EPEForm.getFormName();
		//获取comment
		conclusion = EPEForm.getConclusion();
		//获取EPE值
		epeValue = EPEForm.getEpeValue();
		//获取测试时间
		averageRunTime = EPEForm.getAverageRunTime();
		medianRunTime = EPEForm.getMedianRunTime();
		averageFirstError = EPEForm.getAverageFirstError();
		medianFirstError = EPEForm.getMedianFirstError();
		//获取pass、fail设备数
		passDevice = EPEForm.getPassDevice();
		failDevice = EPEForm.getFailDevice();
		sumDevice = passDevice + failDevice;
		
	}

	public void getErrorInfoByFormName() {
		if(EPEErrorDAO.getEPEErrorInfo(currentFormName) !=null){

			allErrorList = EPEErrorDAO.getEPEErrorInfo(currentFormName);

			if(allErrorList != null){		
				for(int i = 0; i < allErrorList.size(); i++){		
				String errType;
				String packageName;			
				
				javaCrashNum=0;
				nativeCrashNum=0;
				anrNum=0;
				
				CrashInfo type = new CrashInfo();				
				errType = allErrorList.get(i).getErrorType();
				packageName = allErrorList.get(i).getErrorPackage();
				type.setPackageName(packageName);
				
				if (errType.equals("JavaCrash")){
					javaCrashNum = allErrorList.get(i).getFrequency();
					javaCrashTotal += javaCrashNum;			
					type.setJavaCrashFrequency(javaCrashNum);
				} else if (errType.equals("NativeCrash")){
					nativeCrashNum = allErrorList.get(i).getFrequency();
					nativeCrashTotal += nativeCrashNum;			
					type.setNativeCrashFrequency(nativeCrashNum);				
				} else	if (errType.equals("ANR")){
					anrNum = allErrorList.get(i).getFrequency();
					anrTotal += anrNum;			
					type.setANRFrequency(anrNum);
				} 

				for (int j = i + 1; j < allErrorList.size(); j++){
					if(packageName.equals(allErrorList.get(j).getErrorPackage())){
						errType = allErrorList.get(j).getErrorType();		
						if (errType.equals("JavaCrash")){
							javaCrashNum = allErrorList.get(j).getFrequency();
							javaCrashTotal += javaCrashNum;			
							type.setJavaCrashFrequency(javaCrashNum);
						} else if (errType.equals("NativeCrash")){
							nativeCrashNum = allErrorList.get(j).getFrequency();
							nativeCrashTotal += nativeCrashNum;			
							type.setNativeCrashFrequency(nativeCrashNum);				
						} else	if (errType.equals("ANR")){
							anrNum = allErrorList.get(j).getFrequency();
							anrTotal += anrNum;			
							type.setANRFrequency(anrNum);
						} 
						allErrorList.remove(j);
						j = j-1;
					}					
				}
				craInfoList.add(type);		   
			  }
			}
	 	}
	}
	
	public void getDeviceInfoByFormName() {
		if(EPEDeviceDAO.getEPEDeviceInfo(currentFormName) !=null){
			allDeviceList = EPEDeviceDAO.getEPEDeviceInfo(currentFormName);
			if( failDevice !=0){
				allFailList = EPEFailDAO.getEPEFailTypeForm(currentFormName);		
					for(int i = 0; i < allDeviceList.size(); i++){
						if (allDeviceList.get(i).getTypeID().getType().equals("FreezeScreen")){						
							freezeNum++;
							}
						else if (allDeviceList.get(i).getTypeID().getType().equals("BlackScreen")){	
							blackNum++;
						}
						else if (allDeviceList.get(i).getTypeID().getType().equals("Reboot")){	
							restartNum++;
						}
						else if (allDeviceList.get(i).getTypeID().getType().equals("Sysdump")){	
							sysdumpNum++;
						}
						else if (allDeviceList.get(i).getTypeID().getType().equals("PowerOff")){	
							unpowerNum++;
						}
						else if (!allDeviceList.get(i).getTypeID().getType().equals("Normal")){	
							otherNum++;
						}

					}
				}
			}
		}
	public String search(){
		return "search";
	}
	
	public String byProjectAndVersion(){
		TreeMap<String,Float> projectEPEValueMap=new TreeMap<String,Float>();
		projectEPEValueMap = EPEFormDAO.getEPEByProjectVersion(currentProject,version);
		//用于向页面返回排序之后的工程名，使四个图标显示顺序一致
		ArrayList<String> pacVersion = new ArrayList<String>();
	    String epeValuestr = "";
	    String epeValue = "";
        List<Map.Entry<String,Float>> list = new ArrayList<Map.Entry<String,Float>>(projectEPEValueMap.entrySet());
      //获取有序版本号（一组list）和对应的epevalue（String类型）
      for(Map.Entry<String,Float> mapping:list){ 
    	  pacVersion.add(mapping.getKey());
    	  epeValuestr += mapping.getValue() + ",";
     } 
      epeValue = epeValuestr.substring(0, epeValuestr.length() - 1);
      ServletActionContext.getRequest().setAttribute("epeValue",epeValue );
      ServletActionContext.getRequest().setAttribute("list",pacVersion);    
	  return SUCCESS;
		
	}
		
			
	public List<String> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<String> projectList) {
		this.projectList = projectList;
	}

	public String getCurrentProject() {
		return currentProject;
	}
	
	public void setCurrentProject(String currentProject) {
		this.currentProject = currentProject;
	}
	
	public String getCurrentFormName() {
		return currentFormName;
	}

	public void setCurrentFormName(String currentFormName) {
		this.currentFormName = currentFormName;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public float getEpeValue() {
		return epeValue;
	}

	public void setEpeValue(float epeValue) {
		this.epeValue = epeValue;
	}

	public float getAverageRunTime() {
		return averageRunTime;
	}

	public void setAverageRunTime(float averageRunTime) {
		this.averageRunTime = averageRunTime;
	}

	public float getMedianRunTime() {
		return medianRunTime;
	}

	public void setMedianRunTime(float medianRunTime) {
		this.medianRunTime = medianRunTime;
	}

	public float getAverageFirstError() {
		return averageFirstError;
	}

	public void setAverageFirstError(float averageFirstError) {
		this.averageFirstError = averageFirstError;
	}

	public float getMedianFirstError() {
		return medianFirstError;
	}

	public void setMedianFirstError(float medianFirstError) {
		this.medianFirstError = medianFirstError;
	}

	public int getPassDevice() {
		return passDevice;
	}

	public void setPassDevice(int passDevice) {
		this.passDevice = passDevice;
	}

	public int getFailDevice() {
		return failDevice;
	}

	public void setFailDevice(int failDevice) {
		this.failDevice = failDevice;
	}

	public int getSumDevice() {
		return sumDevice;
	}

	public void setSumDevice(int sumDevice) {
		this.sumDevice = sumDevice;
	}

	public List<EPEErrorInfo> getAllErrorList() {
		return allErrorList;
	}

	public void setAllErrorList(List<EPEErrorInfo> allErrorList) {
		this.allErrorList = allErrorList;
	}

	public List<EPEDeviceInfo> getAllDeviceList() {
		return allDeviceList;
	}

	public void setAllDeviceList(List<EPEDeviceInfo> allDeviceList) {
		this.allDeviceList = allDeviceList;
	}

	public List<EPEErrorInfo> getNativeCrashList() {
		return nativeCrashList;
	}

	public void setNativeCrashList(List<EPEErrorInfo> nativeCrashList) {
		this.nativeCrashList = nativeCrashList;
	}

	public List<EPEErrorInfo> getJavaCrashList() {
		return javaCrashList;
	}

	public void setJavaCrashList(List<EPEErrorInfo> javaCrashList) {
		this.javaCrashList = javaCrashList;
	}

	public List<EPEErrorInfo> getANRList() {
		return ANRList;
	}

	public void setANRList(List<EPEErrorInfo> aNRList) {
		ANRList = aNRList;
	}

	public List<EPEFailType> getAllFailList() {
		return allFailList;
	}

	public void setAllFailList(List<EPEFailType> allFailList) {
		this.allFailList = allFailList;
	}

	public EPEFailTypeDAO getEPEFailDAO() {
		return EPEFailDAO;
	}

	public void setEPEFailDAO(EPEFailTypeDAO ePEFailDAO) {
		EPEFailDAO = ePEFailDAO;
	}

	public int getFreezeNum() {
		return freezeNum;
	}

	public void setFreezeNum(int freezeNum) {
		this.freezeNum = freezeNum;
	}

	public int getBlackNum() {
		return blackNum;
	}

	public void setBlackNum(int blackNum) {
		this.blackNum = blackNum;
	}
	public String getSearchProject() {
		return searchProject;
	}

	public void setSearchProject(String searchProject) {
		this.searchProject = searchProject;
	}
	public int getRestartNum() {
		return restartNum;
	}

	public void setRestartNum(int restartNum) {
		this.restartNum = restartNum;
	}

	public int getSysdumpNum() {
		return sysdumpNum;
	}

	public void setSysdumpNum(int sysdumpNum) {
		this.sysdumpNum = sysdumpNum;
	}

	public int getUnpowerNum() {
		return unpowerNum;
	}

	public void setUnpowerNum(int unpowerNum) {
		this.unpowerNum = unpowerNum;
	}

	public int getOtherNum() {
		return otherNum;
	}

	public void setOtherNum(int otherNum) {
		this.otherNum = otherNum;
	}

	public EPEDeviceInfo getDevice() {
		return device;
	}

	public void setDevice(EPEDeviceInfo device) {
		this.device = device;
	}

	public EPEFailType getFail() {
		return fail;
	}

	public void setFail(EPEFailType fail) {
		this.fail = fail;
	}

	public List<CrashInfo> getCraInfoList() {
		return craInfoList;
	}

	public void setCraInfoList(List<CrashInfo> craInfoList) {
		this.craInfoList = craInfoList;
	}
	public int getJavaCrashTotal() {
		return javaCrashTotal;
	}

	public void setJavaCrashTotal(int javaCrashTotal) {
		this.javaCrashTotal = javaCrashTotal;
	}

	public int getNativeCrashTotal() {
		return nativeCrashTotal;
	}

	public void setNativeCrashTotal(int nativeCrashTotal) {
		this.nativeCrashTotal = nativeCrashTotal;
	}

	public int getAnrTotal() {
		return anrTotal;
	}

	public void setAnrTotal(int anrTotal) {
		this.anrTotal = anrTotal;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
