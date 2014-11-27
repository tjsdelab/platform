package com.spreadtrum.EPE.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EPEErrorInfo {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	@JoinColumn(nullable=false)
	private EPETestForm formID;
	@Column(nullable=false)
	private String errorPackage;
	@Column(nullable=false)
	private int frequency;
	public EPETestForm getFormID() {
		return formID;
	}
	public void setFormID(EPETestForm formID) {
		this.formID = formID;
	}
	@Column(nullable=false)
	private String errorType;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	public String getErrorPackage() {
		return errorPackage;
	}
	public void setErrorPackage(String errorPackage) {
		this.errorPackage = errorPackage;
	}
	
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

}
