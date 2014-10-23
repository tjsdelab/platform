package com.spreadtrum.monkeytest.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TestForm {
	private int id;
	private String formName;
	private String hardwareInfo;
	private String pacVersion;
	private Float averStopTime;
	private Float midStopTime;
	private Float averFirstErrTime;
	private Float midFirstErrTime;
	private Float averJavaCrashCount;
	private Float averNativeCrashCount;
	private Float averAnrCount;
	private String pacPath;
	private Date tdate;
	private String logPath;
	
	@Column(nullable=false,unique=true)
	public String getLogPath() {
		return logPath;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}

	//To establish the relationships between the table of VersionType
	private VersionType versionType;
	
    public TestForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
    @GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(nullable=false,unique=true)
	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getHardwareInfo() {
		return hardwareInfo;
	}

	public void setHardwareInfo(String hardwareInfo) {
		this.hardwareInfo = hardwareInfo;
	}

	public String getPacVersion() {
		return pacVersion;
	}

	public void setPacVersion(String pacVersion) {
		this.pacVersion = pacVersion;
	}

	public Float getAverStopTime() {
		return averStopTime;
	}

	public void setAverStopTime(Float averStopTime) {
		this.averStopTime = averStopTime;
	}

	public Float getMidStopTime() {
		return midStopTime;
	}

	public void setMidStopTime(Float midStopTime) {
		this.midStopTime = midStopTime;
	}

	public Float getAverFirstErrTime() {
		return averFirstErrTime;
	}

	public void setAverFirstErrTime(Float averFirstErrTime) {
		this.averFirstErrTime = averFirstErrTime;
	}

	public Float getMidFirstErrTime() {
		return midFirstErrTime;
	}

	public void setMidFirstErrTime(Float midFirstErrTime) {
		this.midFirstErrTime = midFirstErrTime;
	}

	public Float getAverJavaCrashCount() {
		return averJavaCrashCount;
	}

	public void setAverJavaCrashCount(Float averJavaCrashCount) {
		this.averJavaCrashCount = averJavaCrashCount;
	}

	public Float getAverNativeCrashCount() {
		return averNativeCrashCount;
	}

	public void setAverNativeCrashCount(Float averNativeCrashCount) {
		this.averNativeCrashCount = averNativeCrashCount;
	}

	public Float getAverAnrCount() {
		return averAnrCount;
	}

	public void setAverAnrCount(Float averAnrCount) {
		this.averAnrCount = averAnrCount;
	}

	public String getPacPath() {
		return pacPath;
	}

	public void setPacPath(String pacPath) {
		this.pacPath = pacPath;
	}
	@Column(nullable=false)
	public Date getTdate() {
		return tdate;
	}

	public void setTdate(Date tdate) {
		this.tdate = tdate;
	}
	
	@ManyToOne
	@JoinColumn(nullable=false)
	public VersionType getVersionType() {
		return versionType;
	}

	public void setVersionType(VersionType versionType) {
		this.versionType = versionType;
	}

	@Override
	public String toString() {
		return "TestForm [formName=" + formName + ", hardwareInfo="
				+ hardwareInfo + ", pacVersion=" + pacVersion + ", tdate="
				+ tdate + ", versionType=" + versionType + "]";
	}
	

}
