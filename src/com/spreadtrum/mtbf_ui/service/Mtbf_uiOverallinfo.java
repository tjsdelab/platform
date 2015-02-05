package com.spreadtrum.mtbf_ui.service;

import java.util.List;

import com.spreadtrum.mtbf_ui.model.MTBF_uiErrorInfo;

public interface Mtbf_uiOverallinfo {
	List<Mtbf_uiSummary> receiveOverallTestinfo(String testFormName);
	List<Mtbf_uiStatisticinfo> receiveStatisticinfo(String testFormName);
	List<Mtbf_uiErrorinfo> receiveMTBF_uiErrorInfo(String testFormName);


}
