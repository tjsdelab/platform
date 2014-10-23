package com.spreadtrum.monkeytest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PhoneTestInfo {
	private int id;
	
	//To establish the relationships between the table of TestForm
	private TestForm testFormId;
	
	//To establish the relationships between the table of DeviceList
	private DeviceInfo deviceId;
	private Float FirstErrTime;
	private String FirstErrBugID;
	
	//To establish the relationships between the table of ErrorType
	private ErrorType FirstErrType;
	private Float runTime;
	private String finalStatus;
	private String phenomenonDesc;
	private String underlyBugID;
	

	public PhoneTestInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getFinalStatus() {
		return finalStatus;
	}
	public String getFirstErrBugID() {
		return FirstErrBugID;
	}
	@Column(nullable=false)
	public Float getFirstErrTime() {
		return FirstErrTime;
	}
	
	@ManyToOne
	@JoinColumn(nullable=false)
	public ErrorType getFirstErrType() {
		return FirstErrType;
	}
    @Id
    @GeneratedValue
	public int getId() {
		return id;
	}
	public String getPhenomenonDesc() {
		return phenomenonDesc;
	}
	@Column(nullable=false)
	public Float getRunTime() {
		return runTime;
	}
	@ManyToOne
	@JoinColumn(nullable=false)
	public TestForm getTestFormId() {
		return testFormId;
	}
	public String getUnderlyBugID() {
		return underlyBugID;
	}
	@ManyToOne
	@JoinColumn(nullable=false)
	public DeviceInfo getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(DeviceInfo deviceId) {
		this.deviceId = deviceId;
	}
	public void setFinalStatus(String finalStatus) {
		this.finalStatus = finalStatus;
	}
	public void setFirstErrBugID(String firstErrBugID) {
		FirstErrBugID = firstErrBugID;
	}
	public void setFirstErrTime(Float firstErrTime) {
		FirstErrTime = firstErrTime;
	}
	public void setFirstErrType(ErrorType firstErrType) {
		FirstErrType = firstErrType;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPhenomenonDesc(String phenomenonDesc) {
		this.phenomenonDesc = phenomenonDesc;
	}
	public void setRunTime(Float runTime) {
		this.runTime = runTime;
	}
	public void setTestFormId(TestForm testFormId) {
		this.testFormId = testFormId;
	}
	public void setUnderlyBugID(String underlyBugID) {
		this.underlyBugID = underlyBugID;
	}

}
