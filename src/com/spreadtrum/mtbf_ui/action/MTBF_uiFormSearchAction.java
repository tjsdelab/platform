package com.spreadtrum.mtbf_ui.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.spreadtrum.mtbf_ui.dao.MTBF_uiFormDAO;
import com.spreadtrum.mtbf_ui.dao.impl.MTBF_uiFormDAOImpl;
import com.spreadtrum.mtbf_ui.model.MTBF_uiForm;


public class MTBF_uiFormSearchAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String type;
	private String searchProject;
	private List<MTBF_uiForm> testFormList ;
	public String execute() {
		MTBF_uiFormDAO testForm = new MTBF_uiFormDAOImpl();
		if(type.equals("date")){		
			try {
				setTestFormList(testForm.searchByDate(Date.valueOf(searchProject)));
			} catch(Exception e) {
				//日期输入错误处理
				setTestFormList(testForm.searchByDate(Date.valueOf("1970-01-01")));
			}
		}
		else if (type.equals("project")) {
			setTestFormList(testForm.searchByProject(searchProject));
		}
		else if (type.equals("pacVersion")) {
			setTestFormList(testForm.searchByVersion(searchProject));
		}
		if(getTestFormList() == null){
			setTestFormList(new ArrayList<MTBF_uiForm>());
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
	public List<MTBF_uiForm> getTestFormList() {
		return testFormList;
	}
	public void setTestFormList(List<MTBF_uiForm> testFormList) {
		this.testFormList = testFormList;
	}

}
