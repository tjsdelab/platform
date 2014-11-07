package com.spreadtrum.monkeyForRD.action;

public class PieData {
	private String groupName;
	private float performanceRatio;
	
	public PieData(String groupName,float performanceRatio){
        super();
        this.groupName = groupName;
        this.performanceRatio = performanceRatio;
	}	
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public float getPerformanceRatio() {
		return performanceRatio;
	}
	public void setPerformanceRatio(float performanceRatio) {
		this.performanceRatio = performanceRatio;
	}

}
