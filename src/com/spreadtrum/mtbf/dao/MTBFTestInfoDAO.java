package com.spreadtrum.mtbf.dao;

import java.util.List;

import com.spreadtrum.mtbf.model.MTBFTestInfo;

public interface MTBFTestInfoDAO {
	public List<MTBFTestInfo> getMTBFTestInfoByTableName(String formName);

}
