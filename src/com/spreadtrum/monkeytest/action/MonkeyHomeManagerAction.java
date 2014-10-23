package com.spreadtrum.monkeytest.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.spreadtrum.monkeytest.service.*;
import com.spreadtrum.monkeytest.service.impl.*;

public class MonkeyHomeManagerAction extends ActionSupport {
private static final long serialVersionUID = 1L;
private ArrayList<String> sortedProjectList = new ArrayList<String>();
private String projectSearchNameString;
private int projectNum;

	public int getProjectNum() {
	return projectNum;
}

public void setProjectNum(int projectNum) {
	this.projectNum = projectNum;
}
public String getProjectSearchNameString() {
	return projectSearchNameString;
}

public void setProjectSearchNameString(String projectSearchNameString) {
	this.projectSearchNameString = projectSearchNameString;
}

public ArrayList<String> getsortedProjectList() {
	return sortedProjectList;
}

public void setsortedProjectList(ArrayList<String> sortedProjectList) {
	this.sortedProjectList = sortedProjectList;
}

	
	public String monkey_jump(){
		Map<String,Float> averageStopTimeMap=new TreeMap<String,Float>();
		//获取monkey首页对象
		MonkeyHomeManager monkeyHomeManager = new MonkeyHomeManagerImpl();
		averageStopTimeMap = monkeyHomeManager.receiveAverageStopTime();
		List<Map.Entry<String,Float>> list = new ArrayList<Map.Entry<String,Float>>(averageStopTimeMap.entrySet());
        //获取有序工程名（一组list）和对应的停止均值时间（String类型）
        for(Map.Entry<String,Float> mapping:list){ 
            sortedProjectList.add(mapping.getKey());
       } 
		projectSearchNameString = sortedProjectList.get(projectNum - 1);//在工程列表中查找第projectNum个工程名,作为参数传递到project.action中
		return "jump"; 
	}
	public String execute(){
	
		//monkey首页折线图map对象
		Map<String,Float> averageStopTimeMap=new TreeMap<String,Float>();
		Map<String,Float> midStopTimeMap=new TreeMap<String,Float>();
		Map<String,Float> averFirstErrTimeMap=new TreeMap<String,Float>();
		Map<String,Float> midFirstErrTimeMap=new TreeMap<String,Float>();
		//获取monkey首页对象
		MonkeyHomeManager monkeyHomeManager = new MonkeyHomeManagerImpl();
		//获取具体工程名和时间
		averageStopTimeMap = monkeyHomeManager.receiveAverageStopTime();
		midStopTimeMap = monkeyHomeManager.receiveMiddleStopTime();
		averFirstErrTimeMap = monkeyHomeManager.receiveFirstErrAverageTime();
		midFirstErrTimeMap = monkeyHomeManager.receiveFirstErrMiddleTime();
		//获取工程名对象
		
	    String averageStopTimestr = "";
	    String averageStopTime = "";

        List<Map.Entry<String,Float>> list = new ArrayList<Map.Entry<String,Float>>(averageStopTimeMap.entrySet());
        //获取有序工程名（一组list）和对应的停止均值时间（String类型）
        for(Map.Entry<String,Float> mapping:list){ 
            sortedProjectList.add(mapping.getKey());
            averageStopTimestr += mapping.getValue() + ",";
       }   
        averageStopTime = averageStopTimestr.substring(0, averageStopTimestr.length() - 1);
     //根据以上得到的工程名获取另外三组对应的时间（均为String类型）
        String midStopTimeStr = "";
        String midStopTime = "";
        String averFirstErrTimeStr = "";
        String averFirstErrTime = "";
        String midFirstErrTimeStr = "";
        String midFirstErrTime = "";
        //循环遍历得到各工程对应的时间值
        for(int i = 0; i < sortedProjectList.size(); i++)
        {
        	midStopTimeStr += midStopTimeMap.get(sortedProjectList.get(i)) + ",";
        	averFirstErrTimeStr += averFirstErrTimeMap.get(sortedProjectList.get(i)) + ",";
        	midFirstErrTimeStr += midFirstErrTimeMap.get(sortedProjectList.get(i)) + ",";
        }
        midStopTime = midStopTimeStr.substring(0, midStopTimeStr.length() - 1);
        averFirstErrTime = averFirstErrTimeStr.substring(0, averFirstErrTimeStr.length() - 1);
        midFirstErrTime = midFirstErrTimeStr.substring(0, midFirstErrTimeStr.length() - 1);
        
        ServletActionContext.getRequest().setAttribute("averageStopTime",averageStopTime );
       
        ServletActionContext.getRequest().setAttribute("midStopTime",midStopTime );
        ServletActionContext.getRequest().setAttribute("averFirstErrTime",averFirstErrTime );
        ServletActionContext.getRequest().setAttribute("midFirstErrTime",midFirstErrTime );
        ServletActionContext.getRequest().setAttribute("list", sortedProjectList);
		return SUCCESS;
	}
	
}
