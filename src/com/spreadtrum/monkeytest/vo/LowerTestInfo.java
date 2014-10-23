package com.spreadtrum.monkeytest.vo;

public class LowerTestInfo {
	
	private String testPhone;
	private String bugID;
	private Float testDuringTime;
	private String testFinalState;
	private String description;
	private String preliminaryAnalysis;
	
	public String getTestPhone() {
		return testPhone;
	}
	public void setTestPhone(String testPhone) {
		this.testPhone = testPhone;
	}
	public String getBugID() {
		return bugID;
	}
	public void setBugID(String bugID) {
		this.bugID = bugID;
	}
	public Float getTestDuringTime() {
		return testDuringTime;
	}
	public void setTestDuringTime(Float testDuringTime) {
		this.testDuringTime = testDuringTime;
	}
	public String getTestFinalState() {
		return testFinalState;
	}
	public void setTestFinalState(String testFinalState) {
		this.testFinalState = testFinalState;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPreliminaryAnalysis() {
		return preliminaryAnalysis;
	}
	public void setPreliminaryAnalysis(String preliminaryAnalysis) {
		this.preliminaryAnalysis = preliminaryAnalysis;
	}
	public LowerTestInfo() {
		super();
	}
	
	
}
