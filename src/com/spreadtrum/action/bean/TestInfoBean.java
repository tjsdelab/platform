package com.spreadtrum.action.bean;


import java.util.List;

public class TestInfoBean {
	
	private String title;
	private List<String> testPac;
	private String url;
	
	public TestInfoBean() {
		title = "未知标题";
		//testPac.add("qqq");
		url = "#";
		
		// TODO Auto-generated constructor stub
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getTestPac() {
		return testPac;
	}
	public void setTestPac(List<String> testPac) {
		this.testPac = testPac;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}