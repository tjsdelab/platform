package com.spreadtrum.cts.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CTSForm {
	private int id;
	private String hardwareVsn;
	private CTSProject projectID;
	private String pacPath;
	private String formName;
	private String softwareVsn;
	private String logPath;
	private String reportUrl;
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
	public CTSProject getProjectID() {
		return projectID;
	}
	public void setProjectID(CTSProject projectID) {
		this.projectID = projectID;
	}
	public String getPacPath() {
		return pacPath;
	}
	public void setPacPath(String vsnPath) {
		pacPath = vsnPath;
	}
	public String getFormName() {
		return formName;
	}
	@Column(nullable=false)
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getSoftwareVsn() {
		return softwareVsn;
	}
	public void setSoftwareVsn(String softwareVsn) {
		this.softwareVsn = softwareVsn;
	}
	public String getLogPath() {
		return logPath;
	}
	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}
	@Column(nullable=false)
	public String getReportUrl() {
		return reportUrl;
	}
	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}
	@Column(nullable=false)
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	
}
