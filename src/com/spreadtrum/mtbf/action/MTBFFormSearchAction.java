package com.spreadtrum.mtbf.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.spreadtrum.mtbf.dao.MTBFFormDAO;
import com.spreadtrum.mtbf.dao.impl.MTBFFormDAOImpl;
import com.spreadtrum.mtbf.model.MTBFForm;

public class MTBFFormSearchAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String type;
	private String searchProject;
	private List<MTBFForm> testFormList ;
	public String execute() {
		MTBFFormDAO testForm = new MTBFFormDAOImpl();
		if(type.equals("date")){		
			try {
				testFormList = testForm.searchByDate(Date.valueOf(searchProject));
			} catch(Exception e) {
				//日期输入错误处理
				testFormList = testForm.searchByDate(Date.valueOf("1970-01-01"));
			}
		}
		else if (type.equals("project")) {
			testFormList = testForm.searchByProject(searchProject);
		}
		else if (type.equals("pacVersion")) {
			testFormList = testForm.searchByVersion(searchProject);
		}
		if(testFormList == null){
			testFormList = new ArrayList<MTBFForm>();
		}
		return SUCCESS;	
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
	public List<MTBFForm> getTestFormList() {
		return testFormList;
	}
	public void setTestFormList(List<MTBFForm> testFormList) {
		this.testFormList = testFormList;
	}
}
