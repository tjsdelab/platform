package com.spreadtrum.mtbf.dao;

import java.sql.Date;
import java.util.List;

import com.spreadtrum.mtbf.model.MTBFForm;

public interface MTBFFormDAO {	
	//通过工程名和属性名获得last表中某字段
	//public <T> T getMTBFLastPropByProject(String prop,String project);
	//通过工程名获得最近一次对象
	public MTBFForm getMTBFLastTestByProject(String project);
	
	//通过表单名和属性名获得表中某字段
	//public <T> T getMTBFFormPropByTableName(String prop,String tableName);
	//通过表单名获得最近一次对象
	public MTBFForm getMTBFFormTestByTableName(String tableName);	
	//通过表单名获得EPETestForm表中所有信息
	//public List<EPETestForm> getEPEFormByTableName(String tableName);
	
	//通过工程搜索测试表名
	public List<MTBFForm> searchByProject(String project);
	//通过版次搜索测试表名
	public List<MTBFForm> searchByVersion(String version);
	//通过测试时间搜索测试表名
	public List<MTBFForm> searchByDate(Date sdate);
		
}
