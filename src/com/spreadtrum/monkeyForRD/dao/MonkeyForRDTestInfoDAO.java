package com.spreadtrum.monkeyForRD.dao;

import java.sql.Date;
import java.util.List;

import com.spreadtrum.monkeyForRD.model.MonkeyForRDTestInfo;

public interface MonkeyForRDTestInfoDAO  {
	
	//通过各site名得到各site最近报告日期
	public Date getLastDateBySite(String site);
	//通过各site名和日期得到最近测试信息
	public List<MonkeyForRDTestInfo> getSiteTestInfoByDate(String site,Date date);
	//通过deviceName 得到某个人执行的所有次数
    public Integer getDoDaysForAllByDevice(String deviceName);
	//通过deviceName 和日期得到某个人某天执行次数
    public Integer getDoCountThisDayByDevice(String deviceName,Date date);
    //通过deviceName 和截至到现在的天数，得到最近这些天数的执行天数
    public Integer getDoDaysForPeriodByDevice(Integer days,String deviceName);
    
}