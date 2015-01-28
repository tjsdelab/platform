package com.spreadtrum.monkeyForRD.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MonkeyForRDGroup {
	@Id
    @GeneratedValue
	private int id;
	private String groupName;
	private String groupLeader;

	private int validFlag;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(nullable=false)
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@Column(nullable=false)
	public int getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(int validFlag) {
		this.validFlag = validFlag;
	}
	@Column(nullable=false)
	public String getGroupLeader() {
		return groupLeader;
	}
	public void setGroupLeader(String groupLeader) {
		this.groupLeader = groupLeader;
	}
}
