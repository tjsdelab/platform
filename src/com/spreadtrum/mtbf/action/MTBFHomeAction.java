package com.spreadtrum.mtbf.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.spreadtrum.mtbf.dao.MTBFCaseInfoDAO;
import com.spreadtrum.mtbf.dao.MTBFFormDAO;
import com.spreadtrum.mtbf.dao.MTBFProjectInfoDAO;
import com.spreadtrum.mtbf.dao.MTBFTestInfoDAO;
import com.spreadtrum.mtbf.dao.impl.MTBFCaseInfoDAOImpl;
import com.spreadtrum.mtbf.dao.impl.MTBFFormDAOImpl;
import com.spreadtrum.mtbf.dao.impl.MTBFProjectInfoDAOImpl;
import com.spreadtrum.mtbf.dao.impl.MTBFTestInfoDAOImpl;
import com.spreadtrum.mtbf.model.MTBFCaseInfo;
import com.spreadtrum.mtbf.model.MTBFForm;
import com.spreadtrum.mtbf.model.MTBFTestInfo;

public class MTBFHomeAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private List<String> projectList = new ArrayList<String>();
	//获取工程list对象
	private MTBFProjectInfoDAO MTBFProjectList = new MTBFProjectInfoDAOImpl();
	private MTBFFormDAO MTBFMainDAO = new MTBFFormDAOImpl();
	//获取第一个工程名的最新Form
	private MTBFForm MTBFMain = new MTBFForm();
	//主表可直接使用属性
	private String currentProject;
	private String currentFormName = null;
	private String softwareVsn;
	private String hardwareVsn;
	private String conclusion;
	private String issueNew;
	private String issueLeave;
	private String pacPath;
	private float mtbfValue;
	private String runTimeAll;
	private int runNum;
	private int errorNum;
	private int total;
	
	private String type;
	private String searchProject;
	//TestInfo表
	private MTBFTestInfoDAO MTBFTestDAO = new MTBFTestInfoDAOImpl();
	private List<MTBFTestInfo> allDeviceList = new ArrayList<MTBFTestInfo>();
	//CaseInfo表
	private MTBFCaseInfoDAO MTBFCaseDAO = new MTBFCaseInfoDAOImpl();
	private List<MTBFCaseInfo> allCaseList = new ArrayList<MTBFCaseInfo>();
	private String groups0;
	
	
	public String execute(){
		projectList = MTBFProjectList.getValidProjectName();
		if(currentFormName != null){
			MTBFMain = MTBFMainDAO.getMTBFFormTestByTableName(currentFormName);
			currentProject = MTBFMain.getProjectID().getProjectName();
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
			if (MTBFMainDAO.getMTBFLastTestByProject(currentProject) != null){
				MTBFMain = MTBFMainDAO.getMTBFLastTestByProject(currentProject);
		//获取最新表单
				currentFormName = MTBFMain.getFormName();
				getFormInfo();
			}
		}
	public void getFormInfo(){
			//获取最新表单中直接信息
			softwareVsn = MTBFMain.getSoftwareVsn();
			hardwareVsn = MTBFMain.getHardwareVsn();
			conclusion = MTBFMain.getConclusion();
			issueNew = MTBFMain.getIssueNew();
			issueLeave = MTBFMain.getIssueLeave();
			pacPath = MTBFMain.getPacPath();
			mtbfValue = MTBFMain.getMtbfValue();
			errorNum = MTBFMain.getErrorNum();
			runTimeAll = MTBFMain.getRunTimeAll();
			runNum = MTBFMain.getRunNum();
			total = MTBFMain.getTotal();
	}
	public void getTestInfoByFormName(){
		/***************************************************************************************************/
		/*  通过表单名获取测试信息  */
		/***************************************************************************************************/
		if (MTBFTestDAO.getMTBFTestInfoByTableName(currentFormName) !=null){
			//获取最新表单中直接信息	
			allDeviceList = MTBFTestDAO.getMTBFTestInfoByTableName(currentFormName);
		}
	}
	public void getAllCaseByFormName(){
		if (null != MTBFCaseDAO.getMTBFCaseInfoByTableName()){
			allCaseList = MTBFCaseDAO.getMTBFCaseInfoByTableName();			
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

public String getIssueNew() {
	return issueNew;
}

public void setIssueNew(String issueNew) {
	this.issueNew = issueNew;
}

public String getIssueLeave() {
	return issueLeave;
}

public void setIssueLeave(String issueLeave) {
	this.issueLeave = issueLeave;
}

public String getPacPath() {
	return pacPath;
}

public void setPacPath(String pacPath) {
	this.pacPath = pacPath;
}

public float getMtbfValue() {
	return mtbfValue;
}

public void setMtbfValue(float mtbfValue) {
	this.mtbfValue = mtbfValue;
}

public int getErrorNum() {
	return errorNum;
}

public void setErrorNum(int errorNum) {
	this.errorNum = errorNum;
}

public int getRunNum() {
	return runNum;
}

public void setRunNum(int runNum) {
	this.runNum = runNum;
}

public List<MTBFTestInfo> getAllDeviceList() {
	return allDeviceList;
}

public void setAllDeviceList(List<MTBFTestInfo> allDeviceList) {
	this.allDeviceList = allDeviceList;
}

public int getTotal() {
	return total;
}

public void setTotal(int total) {
	this.total = total;
}

public String getSearchProject() {
	return searchProject;
}

public void setSearchProject(String searchProject) {
	this.searchProject = searchProject;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public List<MTBFCaseInfo> getAllCaseList() {
	return allCaseList;
}

public void setAllCaseList(List<MTBFCaseInfo> allCaseList) {
	this.allCaseList = allCaseList;
}	

	public String getRunTimeAll() {
		return runTimeAll;
	}

	public void setRunTimeAll(String runTimeAll) {
		this.runTimeAll = runTimeAll;
	}

	public String getGroups0() {
		return groups0;
	}

	public void setGroups0(String groups0) {
		this.groups0 = groups0;
	}

}
