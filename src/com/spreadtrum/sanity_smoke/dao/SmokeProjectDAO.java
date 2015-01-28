package com.spreadtrum.sanity_smoke.dao;

import java.util.List;

public interface SmokeProjectDAO  {

	public List<String> getSmokeValidProjectName();
	public String getmailInfoByProject(String prop, String projectName);
}