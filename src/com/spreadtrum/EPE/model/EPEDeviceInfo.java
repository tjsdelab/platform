package com.spreadtrum.EPE.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class EPEDeviceInfo {
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private EPETestForm formID;
	@Column(nullable=false)
	private String deviceNo;
	private float runTime;
	private String deviceFlag;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private EPEFailType typeID;
	@Column(nullable=false)
	private String firstErrorType;
	@Column(nullable=false)
	private String firstErrorTime;
	@Column(nullable=false)
	private String firstErrorModule;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public float getRunTime() {
		return runTime;
	}
	public void setRunTime(float runTime) {
		this.runTime = runTime;
	}

	public String getFirstErrorType() {
		return firstErrorType;
	}
	public void setFirstErrorType(String firstErrorType) {
		this.firstErrorType = firstErrorType;
	}
	public String getFirstErrorTime() {
		return firstErrorTime;
	}
	public void setFirstErrorTime(String firstErrorTime) {
		this.firstErrorTime = firstErrorTime;
	}
	public String getFirstErrorModule() {
		return firstErrorModule;
	}
	public void setFirstErrorModule(String firstErrorModule) {
		this.firstErrorModule = firstErrorModule;
	}
	public EPEFailType getTypeID() {
		return typeID;
	}
	public void setTypeID(EPEFailType typeID) {
		this.typeID = typeID;
	}

	public EPETestForm getFormID() {
		return formID;
	}
	public void setFormID(EPETestForm formID) {
		this.formID = formID;
	}
	public String getDeviceFlag() {
		return deviceFlag;
	}
	public void setDeviceFlag(String deviceFlag) {
		this.deviceFlag = deviceFlag;
	}


	}
