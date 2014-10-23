package com.spreadtrum.sanity_smoke.dao;

import java.sql.Date;
import java.util.List;

import com.spreadtrum.sanity_smoke.model.SmokeTestForm;
import com.spreadtrum.sanity_smoke.model.SmokeTestInfo;


public interface SmokeTestFormDAO  {

	//通过工程名和属性名获得SmokeTestForm表中某字段
	public <T> T getSmokeLastPropByProject(String prop,String project);
	//通过工程名获得SmokeTestForm表中最近一次的SmokeTestForm对象
	public SmokeTestForm getSmokeLastInfoByProject(String project);
	
	//通过测试表单名和属性名获得SmokeTestInfo表中某个字段list
	public <T> T getSmokeTestPropByTableName(String prop,String tableName); 
	//通过测试表单名获得SmokeTestInfo表中所有信息
	public List<SmokeTestInfo> getSmokeTestInfoByTableName(String tableName);
	
	//通过工程搜索测试表名
	public List<String> searchSmokeTableNameByProject(String project);
	//通过版次搜索测试表名
	public List<String> searchSmokeTableNameByVersion(String version);
	//通过测试时间搜索测试表名
	public List<Date> searchSmokeTableNameByDate(Date sdate);
	
}