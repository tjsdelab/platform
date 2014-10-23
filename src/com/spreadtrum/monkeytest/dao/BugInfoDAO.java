package com.spreadtrum.monkeytest.dao;


import java.util.List;

import com.spreadtrum.monkeytest.model.*;
public interface BugInfoDAO {
	public List<BugInfo> getBugInfo(int formNameID);
	public List<?>  getBugInfoProp(String prop, int formNameID);
}
