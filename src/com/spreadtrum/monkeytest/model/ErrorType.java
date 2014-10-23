package com.spreadtrum.monkeytest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ErrorType {
	private int id;
	private String errtype;
	
    public ErrorType() {
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
	public String getErrtype() {
		return errtype;
	}
	public void setErrtype(String errtype) {
		this.errtype = errtype;
	}
	    
}
