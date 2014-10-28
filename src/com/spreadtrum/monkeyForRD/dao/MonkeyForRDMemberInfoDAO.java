package com.spreadtrum.monkeyForRD.dao;

import java.util.List;

import com.spreadtrum.monkeyForRD.model.MonkeyForRDMemberInfo;

public interface MonkeyForRDMemberInfoDAO  {
	
	//通过各site名得到各site分组
	public List<String> getGroupNameBySite(String site);
	//得到各site所有deviceName
	public List<String> getAllDeviceNameBySite(String site);
	//得到各site各组的deviceName
	public List<String> getGroupDeviceName(String site,String group);
	//通过deviceName得到每个人的所有属性
	public List<MonkeyForRDMemberInfo> getAllPerPropByDevice(String deviceName);
	//通过deviceName和属性名得到每个人的各属性
	public List<?> getPerPropByDevice(String prop,String deviceName);
}