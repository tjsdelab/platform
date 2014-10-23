package com.spreadtrum.monkeytest.vo;

public class OverallTimeInfo {
	private String testVersion;
	private float averageStopTime;
	private float averageMiddleTime;
	private float firstErrAverageTime;
	private float firstErrMiddleTime;
	private float AverAnrCount;
	private float averJavaCrashCount;
	private float averNativeCrashCount;
	public String getTestVersion() {
		return testVersion;
	}
	public void setTestVersion(String testVersion) {
		this.testVersion = testVersion;
	}
	public float getAverageStopTime() {
		return averageStopTime;
	}
	public void setAverageStopTime(float averageStopTime) {
		this.averageStopTime = averageStopTime;
	}
	public float getAverageMiddleTime() {
		return averageMiddleTime;
	}
	public void setAverageMiddleTime(float averageMiddleTime) {
		this.averageMiddleTime = averageMiddleTime;
	}
	public float getFirstErrAverageTime() {
		return firstErrAverageTime;
	}
	public void setFirstErrAverageTime(float firstErrAverageTime) {
		this.firstErrAverageTime = firstErrAverageTime;
	}
	public float getFirstErrMiddleTime() {
		return firstErrMiddleTime;
	}
	public void setFirstErrMiddleTime(float firstErrMiddleTime) {
		this.firstErrMiddleTime = firstErrMiddleTime;
	}
	public float getAverAnrCount() {
		return AverAnrCount;
	}
	public void setAverAnrCount(float averAnrCount) {
		AverAnrCount = averAnrCount;
	}
	public float getAverJavaCrashCount() {
		return averJavaCrashCount;
	}
	public void setAverJavaCrashCount(float averJavaCrashCount) {
		this.averJavaCrashCount = averJavaCrashCount;
	}
	public float getAverNativeCrashCount() {
		return averNativeCrashCount;
	}
	public void setAverNativeCrashCount(float averNativeCrashCount) {
		this.averNativeCrashCount = averNativeCrashCount;
	}
	public OverallTimeInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}