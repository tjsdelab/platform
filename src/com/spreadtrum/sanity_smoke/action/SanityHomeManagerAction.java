package com.spreadtrum.sanity_smoke.action;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.spreadtrum.sanity_smoke.dao.SanityTestFormDAO;
import com.spreadtrum.sanity_smoke.dao.impl.SanityProjectDAOImpl;
import com.spreadtrum.sanity_smoke.dao.impl.SanityTestFormDAOImpl;
import com.spreadtrum.sanity_smoke.model.SanityTestForm;
import com.spreadtrum.sanity_smoke.model.SanityTestInfo;


public class SanityHomeManagerAction extends ActionSupport {
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
	private String pass_ratio;
	private String passList;
	private String failList;
	private String naList;
	private String blockList;
	private String type;
	private String searchProject;
	private List<String> modulesList = new ArrayList<String>();
	private List<SanityTestInfo> allCaseList = new ArrayList<SanityTestInfo>();
	
	//获取SanityTestFormDAO对象
	private SanityTestFormDAO sanityFormDAO = new SanityTestFormDAOImpl();
	//获取第一个工程名的最新Form
	private SanityTestForm  sanityForm = new SanityTestForm();
	private SanityProjectDAOImpl sanityProjectList = new SanityProjectDAOImpl();
	
	
	public String execute(){
		//有无有效工程，分开处理
		if(currentFormName != null){
			getValidProject();
			getAllCaseByFormName();
			//获取版次
			version = sanityFormDAO.getSanityFormByTableName(currentFormName).getVersionForNum();
			System.out.println("version:" + version);
			//获取comment
			//comment = (String)sanityFormDAO.getSanityTestPropByTableName("comments", currentFormName);
			//获取pac路径：
			pac = sanityFormDAO.getSanityFormByTableName(currentFormName).getPacPath();
			System.out.println("pac:" + pac);
			//获取测试者
			tester = sanityFormDAO.getSanityFormByTableName(currentFormName).getReporter();
			System.out.println("tester:" + tester);
			
			return SUCCESS;//如果当前有表单名，直接使用表单名初始化该页面即可
		}
		else if(sanityProjectList.getSanityValidProjectName() != null){//存在有效的工程
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
		projectList = sanityProjectList.getSanityValidProjectName();
	}
	
	
	/***************************************************************************************************/
	/*  通过表单名获取所有case  */
	/***************************************************************************************************/
	public void getAllCaseByFormName(){
		if(sanityFormDAO.getSanityTestInfoByTableName(currentFormName) != null){
		allCaseList = sanityFormDAO.getSanityTestInfoByTableName(currentFormName);
		//结果类，存储各种状态的数目
		ResultSequence seq = new ResultSequence();
	if(allCaseList != null){
		for(int i = 0; i < allCaseList.size(); i++){
			//System.out.println("Case" + i + ":" + allCaseList.get(i).getId());
			
			//统计pass。fail等次数
			String result = allCaseList.get(i).getResults();
			String module = allCaseList.get(i).getModule();
			seq.addModuleToSequence(module, result);
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
	
		    modulesList = seq.getModulesList();
			passList = seq.getPassList();
			failList = seq.getFailList();
			naList = seq.getNaList();
			blockList = seq.getBlockList();

			  ServletActionContext.getRequest().setAttribute("allCaseList",allCaseList);
			  ServletActionContext.getRequest().setAttribute("modulesList",modulesList );
			  ServletActionContext.getRequest().setAttribute("passList",passList );
			  ServletActionContext.getRequest().setAttribute("failList",failList );
			  ServletActionContext.getRequest().setAttribute("naList",naList );
			  ServletActionContext.getRequest().setAttribute("blockList",blockList );
		}
		
	}
	public void getLastFormNameByProjectName(){
		/***************************************************************************************************/
		/*  通过工程名获取最新的表单  */
		/***************************************************************************************************/
	
			if(sanityFormDAO.getSanityLastInfoByProject(currentProject) != null){
			sanityForm= sanityFormDAO.getSanityLastInfoByProject(currentProject);
			
		//获取SanityTestForm中的Version等信息
		version = sanityForm.getVersionForNum();
		//获取SanityTestForm中的testFormName等信息
		currentFormName = sanityForm.getTestFormName();
		//获取comment
		comment = sanityForm.getComments();
		//获取pac路径：
		pac = sanityForm.getPacPath();
		//获取测试者
		tester = sanityForm.getReporter();
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
	public String getPassList() {
		return passList;
	}
	public void setPassList(String passList) {
		this.passList = passList;
	}
	public String getFailList() {
		return failList;
	}
	public void setFailList(String failList) {
		this.failList = failList;
	}
	public String getNaList() {
		return naList;
	}
	public void setNaList(String naList) {
		this.naList = naList;
	}
	public String getBlockList() {
		return blockList;
	}
	public void setBlockList(String blockList) {
		this.blockList = blockList;
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
	public List<String> getModulesList() {
		return modulesList;
	}
	public void setModulesList(List<String> modulesList) {
		this.modulesList = modulesList;
	}

	public List<SanityTestInfo> getAllCaseList() {
		return allCaseList;
	}

	public void setAllCaseList(List<SanityTestInfo> allCaseList) {
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