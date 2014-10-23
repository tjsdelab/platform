package com.spreadtrum.monkeytest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BugInfo {
	private int id;
	private TestForm formID;
	private DeviceInfo deviceID;
	private String bugID;
	private int bugFlag;
	private String bugStatus;
	private String bugMoudle;
	private String bugSummary;
	private String bugRatio;
	
    public BugInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public TestForm getFormID() {
		return formID;
	}
	public void setFormID(TestForm formID) {
		this.formID = formID;
	}
	@ManyToOne
	@JoinColumn(nullable=false)
	public DeviceInfo getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(DeviceInfo deviceID) {
		this.deviceID = deviceID;
	}
	@Column(nullable=false)
	public String getBugID() {
		return bugID;
	}
	public void setBugID(String bugID) {
		this.bugID = bugID;
	}
	public int getBugFlag() {
		return bugFlag;
	}
	public void setBugFlag(int bugFlag) {
		this.bugFlag = bugFlag;
	}
	public String getBugStatus() {
		return bugStatus;
	}
	public void setBugStatus(String bugStatus) {
		this.bugStatus = bugStatus;
	}
	public String getBugMoudle() {
		return bugMoudle;
	}
	public void setBugMoudle(String bugMoudle) {
		this.bugMoudle = bugMoudle;
	}
	public String getBugSummary() {
		return bugSummary;
	}
	public void setBugSummary(String bugSummary) {
		this.bugSummary = bugSummary;
	}
	public String getBugRatio() {
		return bugRatio;
	}
	public void setBugRatio(String bugRatio) {
		this.bugRatio = bugRatio;
	}	

}
