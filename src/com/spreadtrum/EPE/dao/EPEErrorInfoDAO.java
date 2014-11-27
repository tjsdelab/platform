package com.spreadtrum.EPE.dao;

import java.util.List;
import com.spreadtrum.EPE.model.EPEErrorInfo;

public interface EPEErrorInfoDAO {
	public List<EPEErrorInfo> getEPEErrorInfo(String formName);
}
