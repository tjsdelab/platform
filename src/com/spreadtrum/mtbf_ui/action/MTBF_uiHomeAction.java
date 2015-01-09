package com.spreadtrum.mtbf_ui.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.spreadtrum.mtbf_ui.dao.MTBF_uiCaseInfoDAO;
import com.spreadtrum.mtbf_ui.dao.MTBF_uiErrorInfoDAO;
import com.spreadtrum.mtbf_ui.dao.MTBF_uiFormDAO;
import com.spreadtrum.mtbf_ui.dao.MTBF_uiProjectInfoDAO;
import com.spreadtrum.mtbf_ui.dao.impl.MTBF_uiCaseInfoDAOImpl;
import com.spreadtrum.mtbf_ui.dao.impl.MTBF_uiErrorInfoDAOImpl;
import com.spreadtrum.mtbf_ui.dao.impl.MTBF_uiFormDAOImpl;
import com.spreadtrum.mtbf_ui.dao.impl.MTBF_uiProjectInfoDAOImpl;
import com.spreadtrum.mtbf_ui.model.MTBF_uiCaseInfo;
import com.spreadtrum.mtbf_ui.model.MTBF_uiErrorInfo;
import com.spreadtrum.mtbf_ui.model.MTBF_uiForm;


public class MTBF_uiHomeAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private List<String> projectList = new ArrayList<String>();
	//获取工程list对象
	private MTBF_uiProjectInfoDAO MTBF_uiProjectList = new MTBF_uiProjectInfoDAOImpl();
	private MTBF_uiFormDAO MTBF_uiMainDAO = new MTBF_uiFormDAOImpl();
	//获取第一个工程名的最新Form
	private MTBF_uiForm MTBF_uiMain = new MTBF_uiForm();
	//主表可直接使用属性
	private String currentProject;
	private String currentFormName = null;
	private String softwareVsn;
	private String hardwareVsn;
	private String conclusion;
	private String pacPath;
	
	private int deviceNum;
	private int runNum;
	private String runTimeAll;
	private int errorTotal;
	private float mtbfValue;
	
	private String type;
	private String searchProject;
	//ErrorInfo表
	private MTBF_uiErrorInfoDAO MTBF_uiErrorDAO = new MTBF_uiErrorInfoDAOImpl();
	private List<MTBF_uiErrorInfo> allDeviceList = new ArrayList<MTBF_uiErrorInfo>();
	//CaseInfo表
	private MTBF_uiCaseInfoDAO MTBF_uiCaseDAO = new MTBF_uiCaseInfoDAOImpl();
	private List<MTBF_uiCaseInfo> allCaseList = new ArrayList<MTBF_uiCaseInfo>();
	private String groups0;
	
	public String execute(){
		projectList = MTBF_uiProjectList.getValidProjectName();
		if(currentFormName != null){
			MTBF_uiMain = MTBF_uiMainDAO.getMTBF_uiFormTestByTableName(currentFormName);
			currentProject = MTBF_uiMain.getProjectID().getProjectName();
			getFormInfo();
			getTestInfoByFormName();//通过表单名获取测试信息			
			getAllCaseByFormName();//通过表单名获取所有case
			return SUCCESS;
		}
		else if (null != projectList){
			if (null == currentProject){
		    currentProject = projectList.get(0);//采用下拉列表的第一个工程名作为要显示的工程
			};
		    /**************************/
			/*通过工程名获得最新的表单名***/
			/**************************/
		    getLastFormNameByProjectName();//通过工程名对当前页面的表单名进行设置
			getTestInfoByFormName();//通过表单名获取测试信息
			getAllCaseByFormName();//通过表单名获取所有case
		}	
		return SUCCESS;
	}
	
	public String search(){
		return "search";
	}
	
	public void getLastFormNameByProjectName(){
		/***************************************************************************************************/
		/*  通过工程名获取最新的表单  */
		/***************************************************************************************************/
			if (MTBF_uiMainDAO.getMTBF_uiLastTestByProject(currentProject) != null){
				MTBF_uiMain = MTBF_uiMainDAO.getMTBF_uiLastTestByProject(currentProject);
		//获取最新表单
				currentFormName = MTBF_uiMain.getFormName();
				getFormInfo();
			}
		}
	public void getFormInfo(){
			//获取最新表单中直接信息
			softwareVsn = MTBF_uiMain.getSoftwareVsn();
			hardwareVsn = MTBF_uiMain.getHardwareVsn();
			conclusion = MTBF_uiMain.getConclusion();
			pacPath = MTBF_uiMain.getPacPath();
			deviceNum = MTBF_uiMain.getDeviceNum();
			errorTotal = MTBF_uiMain.getErrorTotal();
			mtbfValue = MTBF_uiMain.getMtbfValue();
			runTimeAll = MTBF_uiMain.getRunTimeAll();
			runNum = MTBF_uiMain.getRunNum();
	}
	public void getTestInfoByFormName(){
		/***************************************************************************************************/
		/*  通过表单名获取测试信息  */
		/***************************************************************************************************/
		if (MTBF_uiErrorDAO.getMTBF_uiErrorInfoByTableName(currentFormName) !=null){
			//获取最新表单中直接信息	
			allDeviceList = MTBF_uiErrorDAO.getMTBF_uiErrorInfoByTableName(currentFormName);
		}
	}
	public void getAllCaseByFormName(){
		if (null != MTBF_uiCaseDAO.getMTBF_uiCaseInfoByTableName()){
			allCaseList = MTBF_uiCaseDAO.getMTBF_uiCaseInfoByTableName();
			groups0 = allCaseList.get(0).getGroups();
		}
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

	public String getSoftwareVsn() {
		return softwareVsn;
	}

	public void setSoftwareVsn(String softwareVsn) {
		this.softwareVsn = softwareVsn;
	}

	public String getHardwareVsn() {
		return hardwareVsn;
	}

	public void setHardwareVsn(String hardwareVsn) {
		this.hardwareVsn = hardwareVsn;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public String getPacPath() {
		return pacPath;
	}

	public void setPacPath(String pacPath) {
		this.pacPath = pacPath;
	}

	public int getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(int deviceNum) {
		this.deviceNum = deviceNum;
	}

	public int getRunNum() {
		return runNum;
	}

	public void setRunNum(int runNum) {
		this.runNum = runNum;
	}

	public String getRunTimeAll() {
		return runTimeAll;
	}

	public void setRunTimeAll(String runTimeAll) {
		this.runTimeAll = runTimeAll;
	}

	public int getErrorTotal() {
		return errorTotal;
	}

	public void setErrorTotal(int errorTotal) {
		this.errorTotal = errorTotal;
	}

	public float getMtbfValue() {
		return mtbfValue;
	}

	public void setMtbfValue(float mtbfValue) {
		this.mtbfValue = mtbfValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSearchProject() {
		return searchProject;
	}

	public void setSearchProject(String searchProject) {
		this.searchProject = searchProject;
	}

	public List<MTBF_uiErrorInfo> getAllDeviceList() {
		return allDeviceList;
	}

	public void setAllDeviceList(List<MTBF_uiErrorInfo> allDeviceList) {
		this.allDeviceList = allDeviceList;
	}

	public List<MTBF_uiCaseInfo> getAllCaseList() {
		return allCaseList;
	}

	public void setAllCaseList(List<MTBF_uiCaseInfo> allCaseList) {
		this.allCaseList = allCaseList;
	}

	public String getGroups0() {
		return groups0;
	}

	public void setGroups0(String groups0) {
		this.groups0 = groups0;
	}
	
	
}
