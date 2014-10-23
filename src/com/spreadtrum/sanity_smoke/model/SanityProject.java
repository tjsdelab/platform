package com.spreadtrum.sanity_smoke.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SanityProject {
	private int id;
	private String projectName;
	private String emailTo;
	private int vaildFlag;
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
	public String getEmailTo() {
		return emailTo;
	}
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}
	public int getVaildFlag() {
		return vaildFlag;
	}
	public void setVaildFlag(int vaildFlag) {
		this.vaildFlag = vaildFlag;
	}
	
	
}
