package com.spreadtrum.mtbf_ui.dao;

import java.util.List;

import com.spreadtrum.mtbf_ui.model.MTBF_uiErrorInfo;


public interface MTBF_uiErrorInfoDAO {
	public List<MTBF_uiErrorInfo> getMTBF_uiErrorInfoByTableName(String formName);

}
