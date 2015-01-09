package com.spreadtrum.mtbf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MTBFTestInfo {
	private int id;
	private MTBFForm formID;
	private String deviceNum;
	private String simCardType;
	private String startTime;
	private String stopTime;
	private String testTimes;
	private int singleErrNum;
	private String errDscpn1;
	private String errDscpn2;
	
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
	public MTBFForm getFormID() {
		return formID;
	}
	public void setFormID(MTBFForm formID) {
		this.formID = formID;
	}
	public String getDeviceNum() {
		return deviceNum;
	}
	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}
	public String getSimCardType() {
		return simCardType;
	}
	public void setSimCardType(String simCardType) {
		this.simCardType = simCardType;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	public String getTestTimes() {
		return testTimes;
	}
	public void setTestTimes(String testTimes) {
		this.testTimes = testTimes;
	}	

	public int getSingleErrNum() {
		return singleErrNum;
	}
	public void setSingleErrNum(int singleErrNum) {
		this.singleErrNum = singleErrNum;
	}
	public String getErrDscpn1() {
		return errDscpn1;
	}
	public void setErrDscpn1(String errDscpn1) {
		this.errDscpn1 = errDscpn1;
	}
	public String getErrDscpn2() {
		return errDscpn2;
	}
	public void setErrDscpn2(String errDscpn2) {
		this.errDscpn2 = errDscpn2;
	}


}
