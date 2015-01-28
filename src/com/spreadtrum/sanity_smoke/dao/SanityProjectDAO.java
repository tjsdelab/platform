package com.spreadtrum.sanity_smoke.dao;

import java.util.List;

public interface SanityProjectDAO  {

	public List<String> getSanityValidProjectName();
	public String getmailInfoByProject(String prop, String projectName);
}