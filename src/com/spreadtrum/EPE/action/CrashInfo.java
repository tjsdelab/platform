package com.spreadtrum.EPE.action;



public class CrashInfo {
	private String PackageName;
	private int javaCrashFrequency;
	
	private int nativeCrashFrequency;

	private int ANRFrequency;

	public int getJavaCrashFrequency() {
		return javaCrashFrequency;
	}
	public void setJavaCrashFrequency(int javaCrashFrequency) {
		this.javaCrashFrequency = javaCrashFrequency;
	}

	public int getNativeCrashFrequency() {
		return nativeCrashFrequency;
	}
	public void setNativeCrashFrequency(int nativeCrashFrequency) {
		this.nativeCrashFrequency = nativeCrashFrequency;
	}

	public String getPackageName() {
		return PackageName;
	}
	public void setPackageName(String packageName) {
		PackageName = packageName;
	}
	public int getANRFrequency() {
		return ANRFrequency;
	}
	public void setANRFrequency(int aNRFrequency) {
		ANRFrequency = aNRFrequency;
	}
	
}
	

