package com.spreadtrum.EPE.dao;

import java.sql.Date;
import java.util.List;
import java.util.TreeMap;

import com.spreadtrum.EPE.model.EPETestForm;


public interface EPETestFormDAO {
	
	//通过工程名和属性名获得last EPETestForm表中某字段
	public <T> T getEPELastPropByProject(String prop,String project);
	//通过工程名获得last EPETestForm表中最近一次的EPETestForm对象
	public EPETestForm getEPELastTestByProject(String project);
	
	//通过表单名和属性名获得EPETestForm表中某字段
	public <T> T getEPEFormPropByTableName(String prop,String tableName);
	//通过工程名和属性名获得EPETestForm表中某字段对应行
	public EPETestForm getEPEFormTestByTableName(String tableName);	
	//通过表单名获得EPETestForm表中所有信息
	//public List<EPETestForm> getEPEFormByTableName(String tableName);
	
	//通过工程搜索测试表名
	public List<EPETestForm> searchEPETableNameByProject(String project);
	//通过版次搜索测试表名
	public List<EPETestForm> searchEPETableNameByVersion(String version);
	//通过测试时间搜索测试表名
	public List<EPETestForm> searchEPETableNameByDate(Date sdate);
	//通过工程和版本获得最近7次测试结果的EPE值（小于7次则全取）
	public TreeMap <String,Float> getEPEByProjectVersion(String project,String version);
	
	//通过工程和版本获得TestForm
	public String getTestFormByProjectVersion(String project,String version);
	
		
}
