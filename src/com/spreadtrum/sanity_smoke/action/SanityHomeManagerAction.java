package com.spreadtrum.sanity_smoke.action;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.Sequence;

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
	private String currentFormName;
	private String version;
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
	private List<String> modulesList = new ArrayList<String>();
	List<SanityTestInfo> allCaseList = new ArrayList<SanityTestInfo>();
	
	//获取SanityTestFormDAO对象
	SanityTestFormDAO sanityFormDAO = new SanityTestFormDAOImpl();
	//获取第一个工程名的最新Form
	SanityTestForm  sanityForm = new SanityTestForm();
	
	
	public String execute(){
		/***************************************************************************************************/
		/*  获取有效工程名  */
		/***************************************************************************************************/
		SanityProjectDAOImpl sanityProjectList = new SanityProjectDAOImpl();
		//下拉列表中的有效工程名
		projectList = sanityProjectList.getSanityValidProjectName();
		currentProject = projectList.get(0);
		
		/**************************/
		/*通过工程名获得最新的表单名***/
		/**************************/
		getLastFormNameByProjectName();
		
		/***************************************************************************************************/
		/*  通过表单名获取所有case  */
		/***************************************************************************************************/
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


		return SUCCESS;
	}
	
	public String changeProject(){
		
		return SUCCESS;
	}
	public String search(){
		
		return SUCCESS;
	}

	public void getLastFormNameByProjectName(){
		/***************************************************************************************************/
		/*  通过工程名获取最新的表单  */
		/***************************************************************************************************/
	
		if(projectList != null){
			
			sanityForm= sanityFormDAO.getSanityLastInfoByProject(currentProject);
		}
		//获取SanityTestForm中的Version等信息
		version = sanityForm.getVersionForNum();
		//获取SanityTestForm中的testFormName等信息
		currentFormName = sanityForm.getTestFormName();
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
	
	}
