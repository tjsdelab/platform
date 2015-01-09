package com.spreadtrum.mtbf.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MTBFForm {
	private int id;
	private String hardwareVsn;
	private MTBFProjectInfo projectID;
	private String pacPath;
	private String formName;
	private String conclusion;
	private String issueNew;
	private String issueLeave;
	private String softwareVsn;
	private float mtbfValue;
	private int errorNum;
	private int runNum;
	private String runTimeAll;
	private int total;
	private Date testDate;
		
	@Id
    @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getHardwareVsn() {
		return hardwareVsn;
	}
	public void setHardwareVsn(String hardwareVsn) {
		this.hardwareVsn = hardwareVsn;
	}
	
	@ManyToOne
	@JoinColumn(nullable=false)
	public MTBFProjectInfo getProjectID() {
		return projectID;
	}
	public void setProjectID(MTBFProjectInfo projectID) {
		this.projectID = projectID;
	}
	public String getPacPath() {
		return pacPath;
	}
	public void setPacPath(String pacPath) {
		this.pacPath = pacPath;
	}
	
	@Column(nullable=false,unique=true)
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getConclusion() {
		return conclusion;
	}
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	public String getIssueNew() {
		return issueNew;
	}
	public void setIssueNew(String issueNew) {
		this.issueNew = issueNew;
	}
	public String getIssueLeave() {
		return issueLeave;
	}
	public void setIssueLeave(String issueLeave) {
		this.issueLeave = issueLeave;
	}
	public String getSoftwareVsn() {
		return softwareVsn;
	}
	public void setSoftwareVsn(String softwareVsn) {
		this.softwareVsn = softwareVsn;
	}
	public float getMtbfValue() {
		return mtbfValue;
	}
	public void setMtbfValue(float mtbfValue) {
		this.mtbfValue = mtbfValue;
	}
	public int getErrorNum() {
		return errorNum;
	}
	public void setErrorNum(int errorNum) {
		this.errorNum = errorNum;
	}	
	@Column(nullable=false)	
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public int getRunNum() {
		return runNum;
	}
	public void setRunNum(int runNum) {
		this.runNum = runNum;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getRunTimeAll() {
		return runTimeAll;
	}
	public void setRunTimeAll(String runTimeAll) {
		this.runTimeAll = runTimeAll;
	}

}
