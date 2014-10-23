package com.spreadtrum.monkeytest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ErrorInfo {
	private int id;
	private TestForm formID;
	private DeviceInfo deviceID;
	private ErrorType errTypeID;
	private String errModule;
	private int moduleErrCount;
	private Float firstErrtime;
	
    public ErrorInfo() {
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
	
	@ManyToOne
	@JoinColumn(nullable=false)
	public TestForm getFormID() {
		return formID;
	}
	public void setFormID(TestForm formID) {
		this.formID = formID;
	}
	@ManyToOne
	@JoinColumn(nullable=false)
	public DeviceInfo getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(DeviceInfo deviceID) {
		this.deviceID = deviceID;
	}
	@ManyToOne
	@JoinColumn(nullable=false)
	public ErrorType getErrTypeID() {
		return errTypeID;
	}
	public void setErrTypeID(ErrorType errTypeID) {
		this.errTypeID = errTypeID;
	}
	@Column(nullable=false)
	public String getErrModule() {
		return errModule;
	}
	public void setErrModule(String errModule) {
		this.errModule = errModule;
	}
	@Column(nullable=false)
	public int getModuleErrCount() {
		return moduleErrCount;
	}
	public void setModuleErrCount(int moduleErrCount) {
		this.moduleErrCount = moduleErrCount;
	}
	@Column(nullable=false)
	public Float getFirstErrtime() {
		return firstErrtime;
	}
	public void setFirstErrtime(Float firstErrtime) {
		this.firstErrtime = firstErrtime;
	}	
	
}
