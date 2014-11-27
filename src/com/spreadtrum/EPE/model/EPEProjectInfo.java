package com.spreadtrum.EPE.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EPEProjectInfo {
	@Id
    @GeneratedValue
	private int id;
	@Column(nullable=false)
	private String projectName;
	private String email;
	private int projectFlag;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getProjectFlag() {
		return projectFlag;
	}

	public void setProjectFlag(int projectFlag) {
		this.projectFlag = projectFlag;
	}
}