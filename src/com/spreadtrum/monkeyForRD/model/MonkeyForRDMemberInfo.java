package com.spreadtrum.monkeyForRD.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MonkeyForRDMemberInfo {
	
	private int id;
	private String name;
	private MonkeyForRDSite siteID;
	private int status;
	private String emailAddr;
	private String department;
	private String platform;
	private String deviceName;
	private MonkeyForRDGroup groupID;
	private String engName;
	@Id
    @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable=false)
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getEmailAddr() {
		return emailAddr;
	}	
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	@Column(nullable=false)
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	@ManyToOne
	@JoinColumn(nullable=false)
	public MonkeyForRDSite getSiteID() {
		return siteID;
	}
	public void setSiteID(MonkeyForRDSite siteID) {
		this.siteID = siteID;
	}
	
	@ManyToOne
	@JoinColumn(nullable=false)
	public MonkeyForRDGroup getGroupID() {
		return groupID;
	}
	public void setGroupID(MonkeyForRDGroup groupID) {
		this.groupID = groupID;
	}
	public String getEngName() {
		return engName;
	}
	public void setEngName(String engName) {
		this.engName = engName;
	}
	
	
}
