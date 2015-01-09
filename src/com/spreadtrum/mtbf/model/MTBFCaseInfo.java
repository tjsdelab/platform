package com.spreadtrum.mtbf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MTBFCaseInfo {
	private int id;
	private String serial;
	private String programName;
	private int cycleNum;
	private String preCondition;
	private String executeStep;
	private String cyclePart;
	private String checkPoint;
	private String testLevel;
	private String groups;
	
	@Id
    @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public int getCycleNum() {
		return cycleNum;
	}
	public void setCycleNum(int cycleNum) {
		this.cycleNum = cycleNum;
	}
	public String getPreCondition() {
		return preCondition;
	}
	public void setPreCondition(String preCondition) {
		this.preCondition = preCondition;
	}
	public String getExecuteStep() {
		return executeStep;
	}
	public void setExecuteStep(String executeStep) {
		this.executeStep = executeStep;
	}
	public String getCyclePart() {
		return cyclePart;
	}
	public void setCyclePart(String cyclePart) {
		this.cyclePart = cyclePart;
	}
	public String getCheckPoint() {
		return checkPoint;
	}
	public void setCheckPoint(String checkPoint) {
		this.checkPoint = checkPoint;
	}
	public String getTestLevel() {
		return testLevel;
	}
	public void setTestLevel(String testLevel) {
		this.testLevel = testLevel;
	}

	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getGroups() {
		return groups;
	}
	public void setGroups(String groups) {
		this.groups = groups;
	}
	

}
