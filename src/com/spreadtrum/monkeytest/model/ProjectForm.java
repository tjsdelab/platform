package com.spreadtrum.monkeytest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProjectForm {
	private int id;
	private String projectName;
	private int flag;
	private String mailTo;
	private String mailCC;
	@Id
    @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getMailTo() {
		return mailTo;
	}
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	public String getMailCC() {
		return mailCC;
	}
	public void setMailCC(String mailCC) {
		this.mailCC = mailCC;
	}
	
	
}
