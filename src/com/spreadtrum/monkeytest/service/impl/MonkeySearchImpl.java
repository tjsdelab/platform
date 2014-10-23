package com.spreadtrum.monkeytest.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.spreadtrum.monkeytest.dao.*;
import com.spreadtrum.monkeytest.dao.impl.*;
import com.spreadtrum.monkeytest.model.TestForm;
import com.spreadtrum.monkeytest.service.MonkeySearch;

public class MonkeySearchImpl implements MonkeySearch{

	@Override
	public List<TestForm> findMatchedFormNames(String input, String type) {
		TestFormDAO testFormDao = new TestFormDAOImpl();
		List<String> formNameList = new ArrayList<String>();
		List<TestForm> result = new ArrayList<TestForm>();
		if(type.equalsIgnoreCase("date"))
		{
			Date myDate = Date.valueOf(input);
			if(null != testFormDao.getTestFormByDate(myDate)){
			formNameList = testFormDao.getTestFormByDate(myDate);
			}
		}
		else if(type.equalsIgnoreCase("project"))
		{
			if(null != testFormDao.searchTestFormByProject(input)){
			formNameList = testFormDao.searchTestFormByProject(input);
			}
		}
		else if(type.equalsIgnoreCase("pacVersion"))
		{
			if(null != testFormDao.searchTestFormByPacVersion(input)){
			formNameList = testFormDao.searchTestFormByPacVersion(input);
			}
		}
		Iterator<String> it=formNameList.iterator();
		while(it.hasNext())
		{
			String formName = it.next();
			TestForm form = testFormDao.getTestFormInfo(formName);
			result.add(form);
		}
		return result;
	}

}
