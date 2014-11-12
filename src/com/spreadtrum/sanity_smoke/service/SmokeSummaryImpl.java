package com.spreadtrum.sanity_smoke.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


import com.spreadtrum.sanity_smoke.action.SmokeResultSequence;
import com.spreadtrum.sanity_smoke.dao.SmokeTestFormDAO;
import com.spreadtrum.sanity_smoke.dao.impl.SmokeTestFormDAOImpl;
import com.spreadtrum.sanity_smoke.model.SmokeTestInfo;

public class SmokeSummaryImpl implements SmokeSummary{
	@Override
	public List<OverallTestInfo> receiveOverallTestInfo_List(String testFormName) {
		List<SmokeTestInfo> allCaseList = new ArrayList<SmokeTestInfo>();
		List<SmokeTestInfo> allCaseList_auto = new ArrayList<SmokeTestInfo>();
		List<OverallTestInfo> results = new ArrayList<OverallTestInfo>();
		String bugList="";
	  //获取SanityTestFormDAO对象
		SmokeTestFormDAO smokeFormDAO = new SmokeTestFormDAOImpl();
		//获取第一个工程名的最新Form
		//SanityTestForm  sanityForm = new SanityTestForm();
		allCaseList = smokeFormDAO.getSmokeTestInfoByTableName(testFormName);
		SmokeResultSequence seq = new SmokeResultSequence();
		if(allCaseList != null){
			for(int i = 0; i < allCaseList.size(); i++){			
				//统计pass。fail等次数
				String result = allCaseList.get(i).getResults();
				int  manualString = allCaseList.get(i).getManualFlag();
				if (!allCaseList.get(i).getBugID().equals("")){
				    bugList = bugList + allCaseList.get(i).getBugID() + " ";
				}
				if(manualString == 0){
					allCaseList_auto.add(allCaseList.get(i));
					allCaseList.remove(i);
					i = i-1;
				}
				seq.addModuleToSequence(result);

			}
		}
		//获取版次
		String version = smokeFormDAO.getSmokeFormByTableName(testFormName).getVersionForNum();
		//获取comment
		String comment = smokeFormDAO.getSmokeFormByTableName(testFormName).getComments();
		 int total = seq.getTotal();
		 int pass = seq.getPass();
		 NumberFormat format = NumberFormat.getPercentInstance();
	     format.setMinimumFractionDigits(2);
	     String pass_ratio = format.format(pass*1.0/total*1.0);


			OverallTestInfo result = new OverallTestInfo();
				result.setBlock(seq.getBlock());
				result.setComment(comment);
				result.setFail(seq.getFail());
				result.setNa(seq.getNa());
				result.setPass(seq.getPass());
				result.setPass_ratio(pass_ratio);
				result.setTotal(seq.getTotal());
				result.setVersion(version);
				results.add(result);
				return results;
			}
}
		
		
