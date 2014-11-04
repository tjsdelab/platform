package com.spreadtrum.sanity_smoke.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.spreadtrum.sanity_smoke.dao.SmokeTestFormDAO;
import com.spreadtrum.sanity_smoke.dao.impl.SmokeTestFormDAOImpl;
import com.spreadtrum.sanity_smoke.model.SmokeTestForm;

public class SmokeFormNameAction extends ActionSupport {
private static final long serialVersionUID = 1L;
private String type;
private String searchProject;
private List<SmokeTestForm> testFormList ;
public String execute() {
	//通过SmokeTestFormDAO调用查找相关的三个方法
	SmokeTestFormDAO testForm = new SmokeTestFormDAOImpl();
	if(type.equals("date")){
		try {
			testFormList = testForm.searchSmokeTableNameByDate(Date.valueOf(searchProject));
		} catch(Exception e) {
			//日期输入错误处理
			testFormList = testForm.searchSmokeTableNameByDate(Date.valueOf("1970-01-01"));
		}		
	}
	else if (type.equals("project")) {
		testFormList = testForm.searchSmokeTableNameByProject(searchProject);
	}
	else if (type.equals("pacVersion")) {
		testFormList = testForm.searchSmokeTableNameByVersion(searchProject);
	}
	if(testFormList == null){
		testFormList = new ArrayList<SmokeTestForm>();
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
public List<SmokeTestForm> getTestFormList() {
	return testFormList;
}
public void setTestFormList(List<SmokeTestForm> testFormList) {
	this.testFormList = testFormList;
}


}
