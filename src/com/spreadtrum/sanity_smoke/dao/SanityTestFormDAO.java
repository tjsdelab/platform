package com.spreadtrum.sanity_smoke.dao;

import java.sql.Date;
import java.util.List;

import com.spreadtrum.sanity_smoke.model.SanityTestForm;
import com.spreadtrum.sanity_smoke.model.SanityTestInfo;


public interface SanityTestFormDAO  {

	//通过工程名和属性名获得SanityTestForm表中某字段
	public <T> T getSanityLastPropByProject(String prop,String project);
	//通过工程名获得SanityTestForm表中最近一次的SanityTestForm对象
	public SanityTestForm getSanityLastInfoByProject(String project);
	
	//通过测试表单名和属性名获得SanityTestInfo表中某个字段list
	public <T> T getSanityTestPropByTableName(String prop,String tableName); 
	//通过测试表单名获得SanityTestInfo表中所有信息
	public List<SanityTestInfo> getSanityTestInfoByTableName(String tableName);
	
	//通过工程搜索测试表名
	public List<SanityTestForm> searchSanityTableNameByProject(String project);
	//通过版次搜索测试表名
	public List<SanityTestForm> searchSanityTableNameByVersion(String version);
	//通过测试时间搜索测试表名
	public List<SanityTestForm> searchSanityTableNameByDate(Date sdate);
	
}