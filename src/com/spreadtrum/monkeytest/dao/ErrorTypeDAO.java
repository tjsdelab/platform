package com.spreadtrum.monkeytest.dao;


import java.util.List;

import com.spreadtrum.monkeytest.model.*;
public interface ErrorTypeDAO {
	public List<ErrorType> getErrorTypeInfo();
	public String getErrorTypeProp(String prop, int errorTypeID);
}
