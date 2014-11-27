package com.spreadtrum.EPE.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class EPETestForm {
	@Id
    @GeneratedValue
	private int id;
	
	@Column(nullable=false,unique=true)
	private String formName;
	
	@JoinColumn(nullable=false)
	private Date testDate;
	private int passDevice;
	private int failDevice;
	private float epeValue;
	private float averageRunTime;
	private float medianRunTime;
	private float averageFirstError;
	private float medianFirstError;
	private String conclusion;
	private String version;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private EPEProjectInfo projectID;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public int getPassDevice() {
		return passDevice;
	}

	public void setPassDevice(int passDevice) {
		this.passDevice = passDevice;
	}

	public int getFailDevice() {
		return failDevice;
	}

	public void setFailDevice(int failDevice) {
		this.failDevice = failDevice;
	}

	public float getEpeValue() {
		return epeValue;
	}

	public void setEpeValue(float epeValue) {
		this.epeValue = epeValue;
	}

	public float getAverageRunTime() {
		return averageRunTime;
	}

	public void setAverageRunTime(float averageRunTime) {
		this.averageRunTime = averageRunTime;
	}

	public float getMedianRunTime() {
		return medianRunTime;
	}

	public void setMedianRunTime(float medianRunTime) {
		this.medianRunTime = medianRunTime;
	}

	public float getAverageFirstError() {
		return averageFirstError;
	}

	public void setAverageFirstError(float averageFirstError) {
		this.averageFirstError = averageFirstError;
	}

	public float getMedianFirstError() {
		return medianFirstError;
	}

	public void setMedianFirstError(float medianFirstError) {
		this.medianFirstError = medianFirstError;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public EPEProjectInfo getProjectID() {
		return projectID;
	}

	public void setProjectID(EPEProjectInfo projectID) {
		this.projectID = projectID;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}



}