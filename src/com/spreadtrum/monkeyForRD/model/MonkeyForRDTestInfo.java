package com.spreadtrum.monkeyForRD.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MonkeyForRDTestInfo {
	
	private int id;
	private String deviceName;
	private float stopTime;
	private float firstErrTime;
	private String firstErrType;
	private float javaErrTime;
	private int javaErrCount;
	private String javaErrModule;
	private float nativeErrTime;
	private int nativeErrCount;
	private String nativeErrModule;
	private float anrErrTime;
	private int anrErrCount;
	private String anrErrModule;
	private Date testDate;
	private String bugID;
	private String testModule;
	private MonkeyForRDSite siteID;
	
	@Id
    @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public float getStopTime() {
		return stopTime;
	}
	public void setStopTime(float stopTime) {
		this.stopTime = stopTime;
	}
	public float getFirstErrTime() {
		return firstErrTime;
	}
	public void setFirstErrTime(float firstErrTime) {
		this.firstErrTime = firstErrTime;
	}
	public String getFirstErrType() {
		return firstErrType;
	}
	public void setFirstErrType(String firstErrType) {
		this.firstErrType = firstErrType;
	}
	public float getJavaErrTime() {
		return javaErrTime;
	}
	public void setJavaErrTime(float javaErrTime) {
		this.javaErrTime = javaErrTime;
	}
	public int getJavaErrCount() {
		return javaErrCount;
	}
	public void setJavaErrCount(int javaErrCount) {
		this.javaErrCount = javaErrCount;
	}
	public String getJavaErrModule() {
		return javaErrModule;
	}
	public void setJavaErrModule(String javaErrModule) {
		this.javaErrModule = javaErrModule;
	}
	public float getNativeErrTime() {
		return nativeErrTime;
	}
	public void setNativeErrTime(float nativeErrTime) {
		this.nativeErrTime = nativeErrTime;
	}
	public int getNativeErrCount() {
		return nativeErrCount;
	}
	public void setNativeErrCount(int nativeErrCount) {
		this.nativeErrCount = nativeErrCount;
	}
	public String getNativeErrModule() {
		return nativeErrModule;
	}
	public void setNativeErrModule(String nativeErrModule) {
		this.nativeErrModule = nativeErrModule;
	}
	public float getAnrErrTime() {
		return anrErrTime;
	}
	public void setAnrErrTime(float anrErrTime) {
		this.anrErrTime = anrErrTime;
	}
	public int getAnrErrCount() {
		return anrErrCount;
	}
	public void setAnrErrCount(int anrErrCount) {
		this.anrErrCount = anrErrCount;
	}
	public String getAnrErrModule() {
		return anrErrModule;
	}
	public void setAnrErrModule(String anrErrModule) {
		this.anrErrModule = anrErrModule;
	}
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	public String getBugID() {
		return bugID;
	}
	public void setBugID(String bugID) {
		this.bugID = bugID;
	}
	public String getTestModule() {
		return testModule;
	}
	public void setTestModule(String testModule) {
		this.testModule = testModule;
	}
	@ManyToOne
	@JoinColumn(nullable=false)
	public MonkeyForRDSite getSiteID() {
		return siteID;
	}
	public void setSiteID(MonkeyForRDSite siteID) {
		this.siteID = siteID;
	}

}
