package com.spreadtrum.sanity_smoke.action;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.spreadtrum.sanity_smoke.dao.SmokeTestFormDAO;
import com.spreadtrum.sanity_smoke.dao.impl.SmokeProjectDAOImpl;
import com.spreadtrum.sanity_smoke.dao.impl.SmokeTestFormDAOImpl;
import com.spreadtrum.sanity_smoke.model.SmokeTestForm;
import com.spreadtrum.sanity_smoke.model.SmokeTestInfo;


public class SmokeHomeManagerAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> projectList = new ArrayList<String>();
	private String currentProject;
	private String currentFormName = null;
	private String comment;
	private String version;
	private String pac;
	private String tester;
	private int total = 0;
	private int pass = 0;
	private int fail = 0;
	private int na = 0;
	private int block = 0;
	private String bugList="";
	private String pass_ratio;
	private String type;
	private String searchProject;
	private List<SmokeTestInfo> allCaseList = new ArrayList<SmokeTestInfo>();
	private List<SmokeTestInfo> allCaseList_auto = new ArrayList<SmokeTestInfo>();
	//获取SanityTestFormDAO对象
	private SmokeTestFormDAO smokeFormDAO = new SmokeTestFormDAOImpl();
	//获取第一个工程名的最新Form
	private SmokeTestForm  smokeForm = new SmokeTestForm();
	private SmokeProjectDAOImpl smokeProjectList = new SmokeProjectDAOImpl();
	
	
	public String execute(){
		//有无有效工程，分开处理
		if(currentFormName != null){
			getValidProject();
			getAllCaseByFormName();
			//获取版次
			version = smokeFormDAO.getSmokeFormByTableName(currentFormName).getVersionForNum();
			//获取comment
			comment = smokeFormDAO.getSmokeFormByTableName(currentFormName).getComments();
			//获取pac路径：
			pac = smokeFormDAO.getSmokeFormByTableName(currentFormName).getPacPath();
			//获取测试者
			tester = smokeFormDAO.getSmokeFormByTableName(currentFormName).getReporter();
			
			return SUCCESS;//如果当前有表单名，直接使用表单名初始化该页面即可
		}
		else if(smokeProjectList.getSmokeValidProjectName() != null){//存在有效的工程
			getValidProject();
		currentProject = projectList.get(0);//采用下拉列表的第一个工程名作为要显示的工程
		
		/**************************/
		/*通过工程名获得最新的表单名***/
		/**************************/
		getLastFormNameByProjectName();//通过工程名对当前页面的表单名进行设置
		getAllCaseByFormName();//通过该函数及表单名完成对页面的初始化
		
		}
		return SUCCESS;
	}
	
	public String dropDownProject(){
		getValidProject();
		/*******************************/
		/*通过工程名获得最新的表单名***/
		/******************************/
		getLastFormNameByProjectName();
		getAllCaseByFormName();
		return SUCCESS;
	}
	public String search(){
		return "search";
	}
	public void getValidProject(){
		/***************************************************************************************************/
		/*  获取有效工程名  */
		/***************************************************************************************************/
		
		//下拉列表中的有效工程名
		projectList = smokeProjectList.getSmokeValidProjectName();
	}
	
	/***************************************************************************************************/
	/*  通过表单名获取所有case  */
	/***************************************************************************************************/
	public void getAllCaseByFormName(){
		if(smokeFormDAO.getSmokeTestInfoByTableName(currentFormName) != null){
		allCaseList = smokeFormDAO.getSmokeTestInfoByTableName(currentFormName);
		//结果类，存储各种状态的数目
		SmokeResultSequence seq = new SmokeResultSequence();
	if(allCaseList != null){
		for(int i = 0; i < allCaseList.size(); i++){			
			//统计pass。fail等次数
			String result = allCaseList.get(i).getResults();
			int  manualString = allCaseList.get(i).getManualFlag();
			if (null != allCaseList.get(i).getBugID()){
			    bugList = bugList + allCaseList.get(i).getBugID() + " ";
			}
			if(manualString == 1){
				allCaseList_auto.add(allCaseList.get(i));
				allCaseList.remove(i);
				i = i-1;
			}
			seq.addModuleToSequence(result);

		}
	}
			 total = seq.getTotal();
			 pass = seq.getPass();
			 fail = seq.getFail();
			 na = seq.getNa();
			 block = seq.getBlock();
			 NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例 
		     format.setMinimumFractionDigits(2);// 设置小数位 
		     pass_ratio = format.format(pass*1.0/total*1.0);

			 ServletActionContext.getRequest().setAttribute("allCaseList",allCaseList);
			 ServletActionContext.getRequest().setAttribute("allCaseList_auto",allCaseList_auto);
			 ServletActionContext.getRequest().setAttribute("bugList",bugList);
		}
		
	}
	public void getLastFormNameByProjectName(){
		/***************************************************************************************************/
		/*  通过工程名获取最新的表单  */
		/***************************************************************************************************/
	
			if(smokeFormDAO.getSmokeLastInfoByProject(currentProject) != null){
			smokeForm= smokeFormDAO.getSmokeLastInfoByProject(currentProject);
			
		//获取SmokeTestForm中的Version等信息
		version = smokeForm.getVersionForNum();
		//获取SmokeTestForm中的testFormName等信息
		currentFormName = smokeForm.getTestFormName();
		//获取comment
		comment = smokeForm.getComments();
		//获取pac路径：
		pac = smokeForm.getPacPath();
		//获取测试者
		tester = smokeForm.getReporter();
			}
	}
	
	
	public String getCurrentProject() {
		return currentProject;
	}
	public void setCurrentProject(String currentProject) {
		this.currentProject = currentProject;
	}
	public List<String> getProjectList() {
		return projectList;
	}
	public void setProjectList(List<String> projectList) {
		this.projectList = projectList;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getCurrentFormName() {
		return currentFormName;
	}
	public void setCurrentFormName(String currentFormName) {
		this.currentFormName = currentFormName;
	}
	public int getPass() {
		return pass;
	}
	public void setPass(int pass) {
		this.pass = pass;
	}
	public String getBugList() {
		return bugList;
	}

	public void setBugList(String bugList) {
		this.bugList = bugList;
	}
	public int getFail() {
		return fail;
	}
	public void setFail(int fail) {
		this.fail = fail;
	}
	public int getNa() {
		return na;
	}
	public void setNa(int na) {
		this.na = na;
	}
	public int getBlock() {
		return block;
	}
	public void setBlock(int block) {
		this.block = block;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getPass_ratio() {
		return pass_ratio;
	}
	public void setPass_ratio(String pass_ratio) {
		this.pass_ratio = pass_ratio;
	}
	public List<SmokeTestInfo> getAllCaseList() {
		return allCaseList;
	}

	public void setAllCaseList(List<SmokeTestInfo> allCaseList) {
		this.allCaseList = allCaseList;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public String getPac() {
		return pac;
	}

	public void setPac(String pac) {
		this.pac = pac;
	}

	public String getTester() {
		return tester;
	}

	public void setTester(String tester) {
		this.tester = tester;
	}
	
	}