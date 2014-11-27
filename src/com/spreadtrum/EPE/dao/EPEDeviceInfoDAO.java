package com.spreadtrum.EPE.dao;

import java.util.List;

import com.spreadtrum.EPE.model.EPEDeviceInfo;

public interface EPEDeviceInfoDAO {
	//根据表单号获取DeviceInfo表中所有信息
	public List<EPEDeviceInfo> getEPEDeviceInfo(String formName);
	public EPEDeviceInfo getEPEDeviceInfoByTableName(String formName);
}
