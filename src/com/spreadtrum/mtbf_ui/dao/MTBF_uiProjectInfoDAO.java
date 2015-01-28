package com.spreadtrum.mtbf_ui.dao;

import java.util.List;

public interface MTBF_uiProjectInfoDAO {
	public List<String> getValidProjectName();
	public String getmailInfoByProject(String prop, String projectName);

}
