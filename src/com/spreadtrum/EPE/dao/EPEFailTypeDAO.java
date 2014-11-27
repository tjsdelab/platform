package com.spreadtrum.EPE.dao;

import java.util.List;
import com.spreadtrum.EPE.model.EPEFailType;

public interface EPEFailTypeDAO {
	//根据停止原因ID获取EPEFailType表中所有信息
	public List<EPEFailType> getEPEFailTypeForm(String formName);
	public EPEFailType getEPEFailType();
	
}
