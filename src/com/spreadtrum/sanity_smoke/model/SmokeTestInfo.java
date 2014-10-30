package com.spreadtrum.sanity_smoke.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SmokeTestInfo {
	private int id;
	private String caseID;
	private String feature;
	private String initCondition;
	private String steps;
	private String expectedResults;
	private String results;
	private String bugID;
	private String comments;
	private int manualFlag;

	@Id
    @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getInitCondition() {
		return initCondition;
	}
	public void setInitCondition(String initCondition) {
		this.initCondition = initCondition;
	}
	public String getSteps() {
		return steps;
	}
	public void setSteps(String steps) {
		this.steps = steps;
	}
	public String getExpectedResults() {
		return expectedResults;
	}
	public void setExpectedResults(String expectedResults) {
		this.expectedResults = expectedResults;
	}
	
	@Column(nullable=false)
	public String getResults() {
		return results;
	}
	public void setResults(String results) {
		this.results = results;
	}
	public String getBugID() {
		return bugID;
	}
	public void setBugID(String bugID) {
		this.bugID = bugID;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getManualFlag() {
		return manualFlag;
	}
	public void setManualFlag(int manualFlag) {
		this.manualFlag = manualFlag;
	}
	
	@Column(nullable=false)
	public String getCaseID() {
		return caseID;
	}
	public void setCaseID(String caseID) {
		this.caseID = caseID;
	}

	
}
