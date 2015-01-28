package com.spreadtrum.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.spreadtrum.model.*;
import com.spreadtrum.dao.*;
import com.spreadtrum.dao.impl.*;

public class IndexPageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private IndexPageDAO indexPageDAO = new IndexPageDAOImpl();
	private NewsDAO newsDAO = new NewsDAOImpl();
	private TestInfoDAO testInfoDAO = new TestInfoDAOImpl();

	//向project.action传递的formname，通过formName方法
	private String testFormName = null;
  

	public String getTestFormName() {
		return testFormName;
	}

	public void setTestFormName(String testFormName) {
		this.testFormName = testFormName;
	}

	public IndexPageDAO getIndexPageDAO() {
		return indexPageDAO;
	}

	public void setIndexPageDAO(IndexPageDAO indexPageDAO) {
		this.indexPageDAO = indexPageDAO;
	}
	
    public NewsDAO getNewsDAO() {
		return newsDAO;
	}

	public void setNewsDAO(NewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}
	
	public TestInfoDAO getTestInfoDAO() {
		return testInfoDAO;
	}

	public void setTestInfoDAO(TestInfoDAO testInfoDAO) {
		this.testInfoDAO = testInfoDAO;
	}

	public String formName(){
		
		return "formName";
	}
	public String execute(){
		//File file = new File("/home/apuser/Downloads/","mtbf_ui.jsp");
		
		//System.out.println("文件名："+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(file.lastModified()));
	IndexPage indexPage=new IndexPage();
	
	if(null != indexPageDAO.showLastIndexPage()){
		indexPage=indexPageDAO.showLastIndexPage();
	}
	String members=indexPage.getTeam_members();
	String location=indexPage.getLocation();
		
	List<News> newsList=new ArrayList<News>();
	if(null != newsDAO.showLast10News()){
	   newsList=newsDAO.showLast10News();
	}
	
	 
	ServletActionContext.getRequest().setAttribute("location", location);
	ServletActionContext.getRequest().setAttribute("members", members);	
	ServletActionContext.getRequest().setAttribute("news", newsList);
	return SUCCESS;	
	}

}
