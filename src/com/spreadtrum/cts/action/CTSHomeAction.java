package com.spreadtrum.cts.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.spreadtrum.cts.dao.CTSProjectDAO;
import com.spreadtrum.cts.dao.CTSTestInfoDAO;
import com.spreadtrum.cts.daoImpl.CTSProjectDAOImpl;
import com.spreadtrum.cts.daoImpl.CTSTestInfoDAOImpl;
import com.spreadtrum.cts.model.CTSForm;


public class CTSHomeAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private CTSProjectDAO ctsProjects = new CTSProjectDAOImpl();
	private List<String> ctsValidProjects;
	private CTSForm lastTestInfo;
	private List<CTSForm> moreTestInfo;
	private CTSTestInfoDAO testinfo = new CTSTestInfoDAOImpl();
	private String currentProject;
	private String type;
	private String searchProject;
	


	public String execute() {	
		getCtsInfo();
		return SUCCESS;
	}
	
	public String search(){
		return "search";
	}
	
	public void getCtsInfo(){
		ctsValidProjects = ctsProjects.getValidProject();
		if (null == currentProject){
			if(null != ctsValidProjects){
			    currentProject = ctsValidProjects.get(0);
			}
		}
		moreTestInfo = testinfo.ctsTestInfoByProject(currentProject);
		if(null != moreTestInfo){
			lastTestInfo = moreTestInfo.get(0);
			moreTestInfo.remove(0);
		}
		if(null !=moreTestInfo && moreTestInfo.size()>5){
			for(int i=moreTestInfo.size();i>4;i--){
				moreTestInfo.remove(i-1);
			}			
		}
	}
	
	public List<String> getCtsValidProjects() {
		return ctsValidProjects;
	}


	public void setCtsValidProjects(List<String> ctsValidProjects) {
		this.ctsValidProjects = ctsValidProjects;
	}
	
	public CTSProjectDAO getCtsProjects() {
		return ctsProjects;
	}
	public void setCtsProjects(CTSProjectDAO ctsProjects) {
		this.ctsProjects = ctsProjects;
	}

	public CTSForm getLastTestInfo() {
		return lastTestInfo;
	}

	public void setLastTestInfo(CTSForm lastTestInfo) {
		this.lastTestInfo = lastTestInfo;
	}

	public CTSTestInfoDAO getTestinfo() {
		return testinfo;
	}

	public void setTestinfo(CTSTestInfoDAO testinfo) {
		this.testinfo = testinfo;
	}

	public String getCurrentProject() {
		return currentProject;
	}

	public void setCurrentProject(String currentProject) {
		this.currentProject = currentProject;
	}

	public List<CTSForm> getMoreTestInfo() {
		return moreTestInfo;
	}

	public void setMoreTestInfo(List<CTSForm> moreTestInfo) {
		this.moreTestInfo = moreTestInfo;
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
	
}