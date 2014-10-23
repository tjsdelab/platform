package com.spreadtrum.sanity_smoke.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.Sequence;

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
	private int pass = 0;
	private int fail = 0;
	private int na = 0;
	private int block = 0;
	private String passList;
	private String failList;
	private String naList;
	private String blockList;
	
	
	public String execute(){
		SanityProjectDAOImpl sanityProjectList = new SanityProjectDAOImpl();
		//下拉列表中的有效工程名
		projectList = sanityProjectList.getSanityValidProjectName();
		
		//获取SanityTestFormDAO对象
		SanityTestFormDAO sanityFormDAO = new SanityTestFormDAOImpl();
		//获取第一个工程名的最新Form
		SanityTestForm  sanityForm = new SanityTestForm();
		if(projectList != null){
			currentProject = projectList.get(0);
			sanityForm= sanityFormDAO.getSanityLastInfoByProject(currentProject);
		}
		//获取SanityTestForm中的Version等信息
		version = sanityForm.getVersionForNum();
		//获取SanityTestForm中的testFormName等信息
		currentFormName = sanityForm.getTestFormName();
		//通过表单名获取所有case
		List<SanityTestInfo> allCaseList = sanityFormDAO.getSanityTestInfoByTableName(currentFormName);
		
	
		
		
		
		
		System.out.println("下拉列表中的数据：" + projectList);
		System.out.println("version：" + version);
		System.out.println("当前工程：" + currentProject);
		System.out.println("当前表单:" + currentFormName);
	if(allCaseList != null){
		for(int i = 0; i < allCaseList.size(); i++){
			System.out.println("Case" + i + ":" + allCaseList.get(i));
			//统计pass。fail等次数
			String result = allCaseList.get(i).getResults();
			if(result.equals("pass")){
				pass++;
			}
			else if (result.equals("fail")) {
				fail++;
			}
			else if (result.equals("block")) {
				block++;
			}
			else if (result.equals("na")) {
				na++;
			}
		}
	}
		System.out.println("pass：" + pass);
		System.out.println("fail：" + fail);
		System.out.println("na：" + na);
		System.out.println("block：" + block);

		
		//Map<String, ArrayList<int>> Sequence = new Map<String, ArrayList<int>>();
		
		
		
		
		
		System.out.println();
		return SUCCESS;
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
	
	}
