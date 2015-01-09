package com.spreadtrum.mtbf_ui.dao;

import java.sql.Date;
import java.util.List;
import com.spreadtrum.mtbf_ui.model.MTBF_uiForm;

public interface MTBF_uiFormDAO {
	public MTBF_uiForm getMTBF_uiLastTestByProject(String project);
	//通过表单名和属性名获得表中某字段
	//public <T> T getMTBFFormPropByTableName(String prop,String tableName);
	//通过表单名获得最近一次对象
	public MTBF_uiForm getMTBF_uiFormTestByTableName(String tableName);	
	//通过表单名获得EPETestForm表中所有信息
	//public List<EPETestForm> getEPEFormByTableName(String tableName);
	
	//通过工程搜索测试表名
	public List<MTBF_uiForm> searchByProject(String project);
	//通过版次搜索测试表名
	public List<MTBF_uiForm> searchByVersion(String version);
	//通过测试时间搜索测试表名
	public List<MTBF_uiForm> searchByDate(Date sdate);
		
}

