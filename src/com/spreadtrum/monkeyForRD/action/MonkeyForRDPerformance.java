package com.spreadtrum.monkeyForRD.action;

public class MonkeyForRDPerformance {
	private String name;
	private int doDaysForWeek;
	private int doDaysForAll;
	private String doIfYesterday;
	private String belongGroup;	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDoDaysForWeek() {
		return doDaysForWeek;
	}
	public void setDoDaysForWeek(int doDaysForWeek) {
		this.doDaysForWeek = doDaysForWeek;
	}
	public int getDoDaysForAll() {
		return doDaysForAll;
	}
	public void setDoDaysForAll(int doDaysForAll) {
		this.doDaysForAll = doDaysForAll;
	}
	public String getBelongGroup() {
		return belongGroup;
	}
	public void setBelongGroup(String belongGroup) {
		this.belongGroup = belongGroup;
	}
	public String getDoIfYesterday() {
		return doIfYesterday;
	}
	public void setDoIfYesterday(String doIfYesterday) {
		this.doIfYesterday = doIfYesterday;
	}
}
