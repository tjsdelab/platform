package com.spreadtrum.mtbf_ui.service;


public class Mtbf_uiStatisticinfo {
	private int deviceNum;
	private int runNum;
	private String runTimeAll;
	private int errorTotal;
	private float mtbfValue;
	
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
	public float getMtbfValue() {
		return mtbfValue;
	}
	public void setMtbfValue(float mtbfValue) {
		this.mtbfValue = mtbfValue;
	}

}
