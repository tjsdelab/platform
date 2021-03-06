package com.spreadtrum.sanity_smoke.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SmokeTestForm {
	private int id;
	private String testFormName;
	private SmokeProject projectID;
	private Date testDate;
	private String versionForHardware;
	private String versionForAPBP;
	private String pacPath;
	private String reporter;
	private String comments;
	private String versionForNum;	
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	@ManyToOne
	@JoinColumn(nullable=false)
	public SmokeProject getProjectID() {
		return projectID;
	}

	public void setProjectID(SmokeProject projectID) {
		this.projectID = projectID;
	}

	public String getTestFormName() {
		return testFormName;
	}

	public void setTestFormName(String testFormName) {
		this.testFormName = testFormName;
	}
	@JoinColumn(nullable=false)
	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public String getVersionForHardware() {
		return versionForHardware;
	}

	public void setVersionForHardware(String versionForHardware) {
		this.versionForHardware = versionForHardware;
	}

	public String getVersionForAPBP() {
		return versionForAPBP;
	}

	public void setVersionForAPBP(String versionForAPBP) {
		this.versionForAPBP = versionForAPBP;
	}

	public String getPacPath() {
		return pacPath;
	}

	public void setPacPath(String pacPath) {
		this.pacPath = pacPath;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getVersionForNum() {
		return versionForNum;
	}

	public void setVersionForNum(String versionForNum) {
		this.versionForNum = versionForNum;
	}



}
