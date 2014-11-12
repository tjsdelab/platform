package com.spreadtrum.sanity_smoke.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.spreadtrum.sanity_smoke.action.ResultSequence;
import com.spreadtrum.sanity_smoke.dao.SanityTestFormDAO;
import com.spreadtrum.sanity_smoke.dao.impl.SanityTestFormDAOImpl;
import com.spreadtrum.sanity_smoke.model.SanityTestInfo;

public class SanitySummaryImpl implements SanitySummary{
	@Override
	public List<OverallTestInfo> receiveOverallTestInfo_List(String testFormName) {
		List<SanityTestInfo> allCaseList = new ArrayList<SanityTestInfo>();
		List<SanityTestInfo> allCaseList_auto = new ArrayList<SanityTestInfo>();
		List<OverallTestInfo> results = new ArrayList<OverallTestInfo>();
		String bugList="";
	  //获取SanityTestFormDAO对象
		SanityTestFormDAO sanityFormDAO = new SanityTestFormDAOImpl();
		//获取第一个工程名的最新Form
		//SanityTestForm  sanityForm = new SanityTestForm();
		allCaseList = sanityFormDAO.getSanityTestInfoByTableName(testFormName);
		ResultSequence seq = new ResultSequence();
		if(allCaseList != null){		
			for(int i = 0; i < allCaseList.size(); i++){
				//统计pass。fail等次数
				String result = allCaseList.get(i).getResults();
				String module = allCaseList.get(i).getModule();
				if (!allCaseList.get(i).getBugID().equals("")){
				    bugList = bugList + allCaseList.get(i).getBugID() + " ";
				}
				int  manualString = allCaseList.get(i).getManualFlag();
				if(manualString == 0){
					allCaseList_auto.add(allCaseList.get(i));
					allCaseList.remove(i);
					i = i-1;
				}
				seq.addModuleToSequence(module, result);
			}
		}
		//获取版次
		String version = sanityFormDAO.getSanityFormByTableName(testFormName).getVersionForNum();
		//获取comment
		String comment = sanityFormDAO.getSanityFormByTableName(testFormName).getComments();
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
		
		
