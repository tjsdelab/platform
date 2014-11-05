package com.spreadtrum.monkeyForRD.action;

import java.sql.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.spreadtrum.monkeyForRD.dao.MonkeyForRDTestInfoDAO;
import com.spreadtrum.monkeyForRD.dao.impl.MonkeyForRDTestInfoDAOImpl;
import com.spreadtrum.monkeyForRD.model.MonkeyForRDTestInfo;

public class MonkeyForRDHomeAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String site;
	private Date date;
	private MonkeyForRDTestInfoDAO rdTestInfoDAO = new MonkeyForRDTestInfoDAOImpl();
	private List<MonkeyForRDTestInfo> rdTestInfo;
	public String execute() {
		System.out.println("test+"+site);
		if (null != site){
			System.out.println("in:" + site);			
		} else {
			date = rdTestInfoDAO.getLastDateBySite("TJ");
			rdTestInfo = rdTestInfoDAO.getSiteTestInfoByDate("TJ", date);
			System.out.println("else:"+date + rdTestInfo);
		}
		
		return SUCCESS;
	}
	
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public MonkeyForRDTestInfoDAO getRdTestInfoDAO() {
		return rdTestInfoDAO;
	}

	public void setRdTestInfoDAO(MonkeyForRDTestInfoDAO rdTestInfoDAO) {
		this.rdTestInfoDAO = rdTestInfoDAO;
	}

	public List<MonkeyForRDTestInfo> getRdTestInfo() {
		return rdTestInfo;
	}

	public void setRdTestInfo(List<MonkeyForRDTestInfo> rdTestInfo) {
		this.rdTestInfo = rdTestInfo;
	}

	
}
