package com.spreadtrum.monkeytest.service;

import java.util.List;
import java.util.TreeMap;

import com.spreadtrum.monkeytest.vo.*;

public interface MonkeyDetail {
	//得到测试版本路径
	String receivePACPath(String testFormName);	
	//得到测试log路径	
	String receiveLogPath(String logPath,String testFormName);
	//得到工程的“总体时间汇总”
	OverallTimeInfo receiveOverallTimeInfo(String testFormName);
	//得到工程的“上层测试信息”
	List<UperTestInfo> receiveUperTestInfo(String testFormName);
	//得到工程的“底层测试信息”
	List<LowerTestInfo> receiveLowerTestInfo(String testFormName);
	
	//得到某个工程最近一段时间的停止均值
	TreeMap<String,Float> receiveProjectAverageStopTime(String project);
	//得到某个工程最近一段时间的停止中值
	TreeMap<String,Float> receiveProjectMiddleStopTime(String project);
	//得到某个工程最近一段时间的首错均值
	TreeMap<String,Float> receiveProjectFirstErrAverageTime(String project);
	//得到某个工程最近一段时间的首错中值
	TreeMap<String,Float> receiveProjectFirstErrMiddleTime(String project);
	
	//给定project，得到最近的该project的表单号testFormName
	String findLastTestFormNameByProjectName(String project);	
	//给定pacVersion，project，得到表单号testFormName
	String findTestFormNameByPacVersionAndProject(String PacVersion,String project);
	

	//给定表单号，得到该表单号之前一周的，该工程的“总体时间汇总”
	List<OverallTimeInfo> receiveOverallTimeInfo_List(String testFormName);
	//给定formName，得到该表单号之前一周的停止均值
	TreeMap<String,Float> receiveProjectAverageStopTime_FormName(String formName);
	//给定formName，得到该表单号之前一周的停止中值
	TreeMap<String,Float> receiveProjectMiddleStopTime_FormName(String formName);
	//给定formName，得到该表单号之前一周的首错均值
	TreeMap<String,Float> receiveProjectFirstErrAverageTime_FormName(String formName);
	//给定formName，得到该表单号之前一周的首错中值
	TreeMap<String,Float> receiveProjectFirstErrMiddleTime_FormName(String formName);
	
	//给定formName，得到该表单号对应的project
	String receiveProject(String formName);
	
}
