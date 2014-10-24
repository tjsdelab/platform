package com.spreadtrum.sanity_smoke.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SmokeProject {
	private int id;
	private String projectName;
	private String emailTo;
	private int validFlag;
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
	public int getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(int validFlag) {
		this.validFlag = validFlag;
	}	
}
