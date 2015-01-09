package com.spreadtrum.mtbf_ui.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class MTBF_uiForm {
	private int id;
	private MTBF_uiProjectInfo projectID;
	private String hardwareVsn;
	private String pacPath;
	private String formName;
	private String conclusion;
	private String softwareVsn;
	
	private float mtbfValue;
	private int deviceNum;
	private int runNum;
	private String runTimeAll;
	private int errorTotal;
	private Date testDate;
		
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
	public MTBF_uiProjectInfo getProjectID() {
		return projectID;
	}
	public void setProjectID(MTBF_uiProjectInfo projectID) {
		this.projectID = projectID;
	}
	public String getHardwareVsn() {
		return hardwareVsn;
	}
	public void setHardwareVsn(String hardwareVsn) {
		this.hardwareVsn = hardwareVsn;
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
	public int getDeviceNum() {
		return deviceNum;
	}
	public void setDeviceNum(int deviceNum) {
		this.deviceNum = deviceNum;
	}
	public int getRunNum() {
		return runNum;
	}
	public void setRunNum(int runNum) {
		this.runNum = runNum;
	}
	public String getRunTimeAll() {
		return runTimeAll;
	}
	public void setRunTimeAll(String runTimeAll) {
		this.runTimeAll = runTimeAll;
	}
	public int getErrorTotal() {
		return errorTotal;
	}
	public void setErrorTotal(int errorTotal) {
		this.errorTotal = errorTotal;
	}
	@Column(nullable=false)
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

}
