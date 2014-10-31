package com.spreadtrum.sanity_smoke.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SanityTestForm {
	private int id;
	private String testFormName;
	private SanityProject projectID;
	private Date testDate;
	private String versionForHardware;
	private String versionForAPBP;
	private String pacPath;
	private String reporter;
	private String comments;
	private String versionForNum;
	private String completeFlag;
	
	public String getCompleteFlag() {
		return completeFlag;
	}

	public void setCompleteFlag(String completeFlag) {
		this.completeFlag = completeFlag;
	}

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
	public SanityProject getProjectID() {
		return projectID;
	}

	public void setProjectID(SanityProject projectID) {
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
