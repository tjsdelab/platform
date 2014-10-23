package com.spreadtrum.dao;

import java.util.List;
import com.spreadtrum.model.*;

public interface TestInfoDAO {
	public void saveTestInfo(TestInfo testInfo);
    public List<TestInfo> showLastTestInfo();
}

