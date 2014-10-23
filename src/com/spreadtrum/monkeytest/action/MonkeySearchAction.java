package com.spreadtrum.monkeytest.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.spreadtrum.monkeytest.model.TestForm;
import com.spreadtrum.monkeytest.service.MonkeySearch;
import com.spreadtrum.monkeytest.service.impl.MonkeySearchImpl;

public class MonkeySearchAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private List<TestForm> testFormList;
	private List<String> formNameList=new ArrayList<String>();
	//下面两个参数是从project action中传递来的
	private String type = "";
	private String searchProject = "";
	
	public MonkeySearchAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSearchProject() {
		return searchProject;
	}

	public void setSearchProject(String searchProject) {
		this.searchProject = searchProject;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	public List<TestForm> getTestFormList() {
		return testFormList;
	}

	public void setTestFormList(List<TestForm> testFormList) {
		this.testFormList = testFormList;
	}
	
	
	public List<String> getFormNameList() {
		return formNameList;
	}

	public void setFormNameList(List<String> formNameList) {
		this.formNameList = formNameList;
	}

	public String execute(){
		MonkeySearch monkeySearch=new MonkeySearchImpl();
		//从jsp界面得到？？？----------------------
		//String input="W14_40";
		//String typepac="pacVersion";
		//-------------------------------
		//得到所有表单
		testFormList=monkeySearch.findMatchedFormNames(searchProject,type);
		
		//需要得到其他的什么数据呢？根据需要进行改变，暂且给的是表单名的list
		Iterator<TestForm> it=testFormList.iterator();
		while(it.hasNext())
		{
			TestForm form = it.next();
			String formName;
			formName=form.getFormName();
			formNameList.add(formName);
		}		

        return SUCCESS;
	}

}
