package com.spreadtrum.monkeytest.dao;

import java.sql.Date;
import java.util.List;

import com.spreadtrum.monkeytest.model.*;
public interface TestFormDAO {
	public TestForm getTestFormInfo(String formName);
	public <T> T getTestFormProp(String prop, String formName);
	
	//硬件 其实是工程
	public List<String> getTestFormByProject(String project);
	//版次 W14.38.1
	public List<String> getTestFormByPacVersion(String pacVersion);
	
	public List<String> getTestFormByDate(Date mdate);

	public Date getLastTimeByProject(String project);
	
	public List<String> searchTestFormByProject(String project);
	//版次 W14.38.1
	public List<String> searchTestFormByPacVersion(String pacVersion);
	//通过工程名获得本工程最新测试表单名
	public String getLastTestFormByProject(String project);	
	
	//通过工程获取测试主题信息
	public List<TestForm> getMonkeyTestInfoByProject(String project);

}