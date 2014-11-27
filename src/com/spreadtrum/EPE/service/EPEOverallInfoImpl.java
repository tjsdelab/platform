package com.spreadtrum.EPE.service;

import java.util.ArrayList;
import java.util.List;

import com.spreadtrum.EPE.dao.EPETestFormDAO;
import com.spreadtrum.EPE.dao.impl.EPETestFormDAOImpl;

public class EPEOverallInfoImpl implements EPEOverallInfo {
	@Override
	public List<EPESummary>  receiveOverallInfo(String formName){
		
		EPETestFormDAO EPEFormDAO = new EPETestFormDAOImpl();
		List<EPESummary> results = new ArrayList<EPESummary>();
		
		
		String version = EPEFormDAO.getEPEFormTestByTableName(formName).getVersion();
		String conclusion = EPEFormDAO.getEPEFormTestByTableName(formName).getConclusion();
		float epeValue = EPEFormDAO.getEPEFormTestByTableName(formName).getEpeValue();
		
		int passDevice = EPEFormDAO.getEPEFormTestByTableName(formName).getPassDevice();
		int failDevice = EPEFormDAO.getEPEFormTestByTableName(formName).getFailDevice();
		int sumDevice = passDevice + failDevice;
	
		EPESummary result = new EPESummary();
		result.setVersion(version);
		result.setConclusion(conclusion);
		result.setEpeValue(epeValue);
		result.setPassDevice(passDevice);
		result.setFailDevice(failDevice);
		result.setSumDevice(sumDevice);
				
		results.add(result);
		return results;
		
	}
}
