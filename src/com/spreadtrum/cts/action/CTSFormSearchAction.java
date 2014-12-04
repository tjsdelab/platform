package com.spreadtrum.cts.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.spreadtrum.cts.dao.CTSTestInfoDAO;
import com.spreadtrum.cts.daoImpl.CTSTestInfoDAOImpl;
import com.spreadtrum.cts.model.CTSForm;

public class CTSFormSearchAction extends ActionSupport {
private static final long serialVersionUID = 1L;
private String type;
private String searchProject;
private List<CTSForm> testFormList ;
public String execute() {
	//通过SmokeTestFormDAO调用查找相关的三个方法
	CTSTestInfoDAO testForm = new CTSTestInfoDAOImpl();
	System.out.println("type:"+type);
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
		testFormList = new ArrayList<CTSForm>();
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
public List<CTSForm> getTestFormList() {
	return testFormList;
}
public void setTestFormList(List<CTSForm> testFormList) {
	this.testFormList = testFormList;
}

}
