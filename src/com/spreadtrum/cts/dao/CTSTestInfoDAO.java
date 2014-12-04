package com.spreadtrum.cts.dao;

import java.sql.Date;
import java.util.List;

import com.spreadtrum.cts.model.CTSForm;

public interface CTSTestInfoDAO {
	public List<CTSForm> ctsTestInfoByProject(String project);
	public List<CTSForm> searchByDate(Date sdate);
	public List<CTSForm> searchByProject(String project);
	public List<CTSForm> searchByVersion(String version);

}
