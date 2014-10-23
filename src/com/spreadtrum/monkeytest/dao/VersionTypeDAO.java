package com.spreadtrum.monkeytest.dao;


import java.util.List;

import com.spreadtrum.monkeytest.model.*;
public interface VersionTypeDAO {
	public List<VersionType> getVersionTypeInfo();
	public <T> T getVersionTypeProp(String prop, int verTypeID);
}
