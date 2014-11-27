package com.spreadtrum.EPE.service;

public class EPESummary {	
	private String version;
	private int sumDevice;
	private int passDevice;
	private int failDevice;
	private float epeValue;
	
	private int freeze = 0;
	private int black = 0;
	private int restart = 0;
	private int sysdump = 0;
	private int poweroff = 0;
	private int other = 0;
	
	private String conclusion;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}


	public float getEpeValue() {
		return epeValue;
	}

	public void setEpeValue(float epeValue) {
		this.epeValue = epeValue;
	}

	public int getFreeze() {
		return freeze;
	}

	public void setFreeze(int freeze) {
		this.freeze = freeze;
	}
	public int getBlack() {
		return black;
	}

	public void setBlack(int black) {
		this.black = black;
	}

	public int getRestart() {
		return restart;
	}

	public void setRestart(int restart) {
		this.restart = restart;
	}

	public int getSysdump() {
		return sysdump;
	}

	public void setSysdump(int sysdump) {
		this.sysdump = sysdump;
	}

	public int getPoweroff() {
		return poweroff;
	}

	public void setPoweroff(int poweroff) {
		this.poweroff = poweroff;
	}

	public int getOther() {
		return other;
	}

	public void setOther(int other) {
		this.other = other;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public int getSumDevice() {
		return sumDevice;
	}

	public void setSumDevice(int sumDevice) {
		this.sumDevice = sumDevice;
	}

	public int getPassDevice() {
		return passDevice;
	}

	public void setPassDevice(int passDevice) {
		this.passDevice = passDevice;
	}

	public int getFailDevice() {
		return failDevice;
	}

	public void setFailDevice(int failDevice) {
		this.failDevice = failDevice;
	}
}
