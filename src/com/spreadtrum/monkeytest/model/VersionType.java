package com.spreadtrum.monkeytest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class VersionType {
	
	private int id;
	private String verType;
	
	
	public VersionType() {
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
	@Column(nullable=false,unique=true)
	public String getVerType() {
		return verType;
	}
	public void setVerType(String verType) {
		this.verType = verType;
	}
    
}
