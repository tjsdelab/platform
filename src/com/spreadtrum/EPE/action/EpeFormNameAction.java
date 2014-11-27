package com.spreadtrum.EPE.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.spreadtrum.EPE.dao.EPETestFormDAO;
import com.spreadtrum.EPE.dao.impl.EPETestFormDAOImpl;
import com.spreadtrum.EPE.model.EPETestForm;

public class EpeFormNameAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	private String searchProject;
	private List<EPETestForm> testFormList ;
	public String execute() {
		//通过SanityTestFormDAO调用查找相关的三个方法
		EPETestFormDAO testForm = new EPETestFormDAOImpl();
		if(type.equals("date")){		
			try {
				testFormList = testForm.searchEPETableNameByDate(Date.valueOf(searchProject));
			} catch(Exception e) {
				//日期输入错误处理
				testFormList = testForm.searchEPETableNameByDate(Date.valueOf("1970-01-01"));
			}		 
		}
		else if (type.equals("project")) {
			testFormList = testForm.searchEPETableNameByProject(searchProject);
		}
		else if (type.equals("pacVersion")) {
			testFormList = testForm.searchEPETableNameByVersion(searchProject);
		}
		if(testFormList == null){
			testFormList = new ArrayList<EPETestForm>();
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
	public List<EPETestForm> getTestFormList() {
		return testFormList;
	}
	public void setTestFormList(List<EPETestForm> testFormList) {
		this.testFormList = testFormList;
	}
}
