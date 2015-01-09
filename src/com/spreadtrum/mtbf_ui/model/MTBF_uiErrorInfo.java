package com.spreadtrum.mtbf_ui.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MTBF_uiErrorInfo {
	private int id;
	private MTBF_uiForm formID;
	private String deviceNum;
	private int errorEach;
	
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
	public MTBF_uiForm getFormID() {
		return formID;
	}
	public void setFormID(MTBF_uiForm formID) {
		this.formID = formID;
	}
	public String getDeviceNum() {
		return deviceNum;
	}
	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}
	public int getErrorEach() {
		return errorEach;
	}
	public void setErrorEach(int errorEach) {
		this.errorEach = errorEach;
	}
	
}
