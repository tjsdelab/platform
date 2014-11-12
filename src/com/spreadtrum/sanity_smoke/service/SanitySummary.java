package com.spreadtrum.sanity_smoke.service;

import java.util.List;


public interface SanitySummary {
	List<OverallTestInfo> receiveOverallTestInfo_List(String testFormName);
	
}
