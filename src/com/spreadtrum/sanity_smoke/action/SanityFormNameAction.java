package com.spreadtrum.sanity_smoke.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.spreadtrum.sanity_smoke.dao.SanityTestFormDAO;
import com.spreadtrum.sanity_smoke.dao.impl.SanityTestFormDAOImpl;
import com.spreadtrum.sanity_smoke.model.SanityTestForm;

public class SanityFormNameAction extends ActionSupport {
private static final long serialVersionUID = 1L;
private String type;
private String searchProject;
private List<SanityTestForm> testFormList ;
public String execute() {
	//通过SanityTestFormDAO调用查找相关的三个方法
	SanityTestFormDAO testForm = new SanityTestFormDAOImpl();
	if(type.equals("date")){
		 
		testFormList = testForm.searchSanityTableNameByDate(Date.valueOf(searchProject)); 
	}
	else if (type.equals("project")) {
		testFormList = testForm.searchSanityTableNameByProject(searchProject);
	}
	else if (type.equals("pacVersion")) {
		testFormList = testForm.searchSanityTableNameByVersion(searchProject);
	}
	if(testFormList == null){
		testFormList = new ArrayList<SanityTestForm>();
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
public List<SanityTestForm> getTestFormList() {
	return testFormList;
}
public void setTestFormList(List<SanityTestForm> testFormList) {
	this.testFormList = testFormList;
}


}
