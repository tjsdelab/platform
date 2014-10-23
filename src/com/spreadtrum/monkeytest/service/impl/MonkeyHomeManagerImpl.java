package com.spreadtrum.monkeytest.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.spreadtrum.monkeytest.service.MonkeyHomeManager;
import com.spreadtrum.monkeytest.dao.*;
import com.spreadtrum.monkeytest.dao.impl.*;
import com.spreadtrum.monkeytest.model.*;

public class MonkeyHomeManagerImpl implements MonkeyHomeManager{
	
	private TestFormDAO testFormDAO;
	private ProjectFormDAO projectFormDAO;
	
	public MonkeyHomeManagerImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<String, Float> receiveAverageStopTime() {
		//得到今天的所有测试表单号，按照每个表单号，找到表单中所对应的版本和停止均值，封装成Map，返回即可
		Map<String,Float> results=new LinkedHashMap<String,Float>();
		testFormDAO = new TestFormDAOImpl();		
		projectFormDAO = new ProjectFormDAOImpl();
		List<String> validProject = projectFormDAO.getValidProjectName();
		List<String> testFormNameList = new ArrayList<String>();
		if (null != validProject) {
			Iterator<String> it = validProject.iterator();
			while(it.hasNext())
			{
				String project = it.next();
				String testFromName = testFormDAO.getLastTestFormByProject(project);
				if (null != testFromName) {
				testFormNameList.add(testFromName);				
				}
			}
		}
			Iterator<String> it=testFormNameList.iterator();
		    while(it.hasNext())
		    {
			    String formName = it.next();
			    TestForm form = testFormDAO.getTestFormInfo(formName);
			    results.put(form.getHardwareInfo(),form.getAverStopTime());
		    }
		    return results;
	}

	@Override
	public Map<String, Float> receiveMiddleStopTime() {
		//得到今天的所有测试表单号，按照每个表单号，找到表单中所对应的版本和停止中值，封装成Map，返回即可
		Map<String,Float> results=new LinkedHashMap<String,Float>();
		testFormDAO = new TestFormDAOImpl();
		projectFormDAO = new ProjectFormDAOImpl();
		List<String> validProject = projectFormDAO.getValidProjectName();
		List<String> testFormNameList = new ArrayList<String>();
		if (null != validProject) {
			Iterator<String> it = validProject.iterator();
			while(it.hasNext())
			{
				String project = it.next();
				String testFromName = testFormDAO.getLastTestFormByProject(project);
				if (null != testFromName) {
				testFormNameList.add(testFromName);				
				}
			}
		}
 		
		Iterator<String> it=testFormNameList.iterator();
		while(it.hasNext())
		{
			String formName = it.next();
			TestForm form = testFormDAO.getTestFormInfo(formName);
			results.put(form.getHardwareInfo(),form.getMidStopTime());
		}
		return results;
	}

	@Override
	public Map<String, Float> receiveFirstErrAverageTime() {
		//得到今天的所有测试表单号，按照每个表单号，找到表单中所对应的版本和错误均值，封装成Map，返回即可
		Map<String,Float> results=new LinkedHashMap<String,Float>();
		testFormDAO = new TestFormDAOImpl();
		projectFormDAO = new ProjectFormDAOImpl();
		List<String> validProject = projectFormDAO.getValidProjectName();
		List<String> testFormNameList = new ArrayList<String>();
		if (null != validProject) {
			Iterator<String> it = validProject.iterator();
			while(it.hasNext())
			{
				String project = it.next();
				String testFromName = testFormDAO.getLastTestFormByProject(project);
				if (null != testFromName) {
				testFormNameList.add(testFromName);				
				}
			}
		}
	
		Iterator<String> it=testFormNameList.iterator();
		while(it.hasNext())
		{
			String formName = it.next();
			TestForm form = testFormDAO.getTestFormInfo(formName);
			results.put(form.getHardwareInfo(),form.getAverFirstErrTime());
		}
		return results;
	}

	@Override
	public Map<String, Float> receiveFirstErrMiddleTime() {
		//得到今天的所有测试表单号，按照每个表单号，找到表单中所对应的版本和停止均值，封装成Map，返回即可
		Map<String,Float> results=new LinkedHashMap<String,Float>();
		testFormDAO = new TestFormDAOImpl();	
		projectFormDAO = new ProjectFormDAOImpl();
		List<String> validProject = projectFormDAO.getValidProjectName();
		List<String> testFormNameList = new ArrayList<String>();
		if (null != validProject) {
			Iterator<String> it = validProject.iterator();
			while(it.hasNext())
			{
				String project = it.next();
				String testFromName = testFormDAO.getLastTestFormByProject(project);
				if (null != testFromName) {
				testFormNameList.add(testFromName);				
				}
			}
		}
		
		Iterator<String> it=testFormNameList.iterator();
		while(it.hasNext())
		{
			String formName = it.next();
			TestForm form = testFormDAO.getTestFormInfo(formName);
			results.put(form.getHardwareInfo(),form.getMidFirstErrTime());
		}
		return results;
	}

}
