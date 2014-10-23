package com.spreadtrum.monkeytest.dao;

import java.util.List;

import com.spreadtrum.monkeytest.model.*;
public interface DeviceInfoDAO {
	public List<DeviceInfo> getDeviceInfo();
	public String getDeviceInfoProp(String prop, int deviceID);
}
