package com.spreadtrum.sanity_smoke.service;

public class OverallTestInfo {
	private String version;
	private int total = 0;
	private int pass = 0;
	private int fail = 0;
	private int na = 0;
	private int block = 0;
	private String pass_ratio;
	private String comment;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPass() {
		return pass;
	}
	public void setPass(int pass) {
		this.pass = pass;
	}
	public int getFail() {
		return fail;
	}
	public void setFail(int fail) {
		this.fail = fail;
	}
	public int getNa() {
		return na;
	}
	public void setNa(int na) {
		this.na = na;
	}
	public int getBlock() {
		return block;
	}
	public void setBlock(int block) {
		this.block = block;
	}
	public String getPass_ratio() {
		return pass_ratio;
	}
	public void setPass_ratio(String pass_ratio) {
		this.pass_ratio = pass_ratio;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}	
}
