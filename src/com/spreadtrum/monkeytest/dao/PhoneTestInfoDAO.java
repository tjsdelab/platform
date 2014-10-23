package com.spreadtrum.monkeytest.dao;


import java.util.List;

import com.spreadtrum.monkeytest.model.*;
public interface PhoneTestInfoDAO {
	public List<PhoneTestInfo> getPhoneTestInfo(int formNameID);
	public List<?> PhoneTestInfoProp(String prop, int formNameID);
		
}
