package com.spreadtrum.sanity_smoke.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SanityTestInfo {
	private int id;
	private String caseID;
	private String module;
	private String summary;
	private String preconditions;
	private String importance;
	private String actions;
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
	@Column(nullable=false)
	public String getCaseID() {
		return caseID;
	}
	public void setCaseID(String caseID) {
		this.caseID = caseID;
	}

	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getPreconditions() {
		return preconditions;
	}
	public void setPreconditions(String preconditions) {
		this.preconditions = preconditions;
	}
	public String getImportance() {
		return importance;
	}
	public void setImportance(String importance) {
		this.importance = importance;
	}
	public String getActions() {
		return actions;
	}
	public void setActions(String actions) {
		this.actions = actions;
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
	@Column(nullable=false)
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Column(nullable=false)
	public int getManualFlag() {
		return manualFlag;
	}
	public void setManualFlag(int manualFlag) {
		this.manualFlag = manualFlag;
	}
	@Override
	public String toString() {
		return "SanityTestInfo [id=" + id + ", caseID=" + caseID + ", model="
				+ model + ", summary=" + summary + ", preconditions="
				+ preconditions + ", importance=" + importance + ", actions="
				+ actions + ", expectedResults=" + expectedResults
				+ ", results=" + results + ", bugID=" + bugID + ", comments="
				+ comments + ", manualFlag=" + manualFlag + "]";
	}

	
}
