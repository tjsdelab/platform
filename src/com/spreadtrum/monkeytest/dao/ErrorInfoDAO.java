package com.spreadtrum.monkeytest.dao;


import java.util.List;

import com.spreadtrum.monkeytest.model.*;
public interface ErrorInfoDAO {
	public List<ErrorInfo> getErrorInfo(int formNameID);
	public List<?> getErrorInfoProp(String prop, int formNameID);
}
