package com.spreadtrum.backstageManager;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.spreadtrum.monkeytest.dao.ProjectFormDAO;
import com.spreadtrum.monkeytest.dao.TestFormDAO;
import com.spreadtrum.monkeytest.dao.impl.ProjectFormDAOImpl;
import com.spreadtrum.monkeytest.dao.impl.TestFormDAOImpl;
import com.spreadtrum.monkeytest.model.TestForm;
import com.spreadtrum.mtbf.dao.MTBFFormDAO;
import com.spreadtrum.mtbf.dao.MTBFProjectInfoDAO;
import com.spreadtrum.mtbf.dao.impl.MTBFFormDAOImpl;
import com.spreadtrum.mtbf.dao.impl.MTBFProjectInfoDAOImpl;
import com.spreadtrum.mtbf.model.MTBFForm;
import com.spreadtrum.mtbf_ui.dao.MTBF_uiFormDAO;
import com.spreadtrum.mtbf_ui.dao.MTBF_uiProjectInfoDAO;
import com.spreadtrum.mtbf_ui.dao.impl.MTBF_uiFormDAOImpl;
import com.spreadtrum.mtbf_ui.dao.impl.MTBF_uiProjectInfoDAOImpl;
import com.spreadtrum.mtbf_ui.model.MTBF_uiForm;
import com.spreadtrum.sanity_smoke.dao.SanityProjectDAO;
import com.spreadtrum.sanity_smoke.dao.SanityTestFormDAO;
import com.spreadtrum.sanity_smoke.dao.SmokeProjectDAO;
import com.spreadtrum.sanity_smoke.dao.SmokeTestFormDAO;
import com.spreadtrum.sanity_smoke.dao.impl.SanityProjectDAOImpl;
import com.spreadtrum.sanity_smoke.dao.impl.SanityTestFormDAOImpl;
import com.spreadtrum.sanity_smoke.dao.impl.SmokeProjectDAOImpl;
import com.spreadtrum.sanity_smoke.dao.impl.SmokeTestFormDAOImpl;
import com.spreadtrum.sanity_smoke.model.SanityTestForm;
import com.spreadtrum.sanity_smoke.model.SmokeTestForm;

public class BackManageAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String type;
	private String project;
	private String searchProject;
	private String tabclicked;
	private String mselected;
	private String snselected;
	private String smselected;
	private String mtselected;
	private String muselected;
	


	//monkey测试信息获取
	private List<String> projectListM = new ArrayList<String>();
	private String currentProjectM;
	private TestFormDAO monkeyInfo=new TestFormDAOImpl();
	private ProjectFormDAO monkeyProjectDAO=new ProjectFormDAOImpl();
	private TestForm monkeyLastInfo;
	private List<TestForm> monkeyMoreInfo;
	private String MmailTo;	
	private String MmailCC;
	
	//sanity测试信息获取
	private List<String> projectListSn = new ArrayList<String>();
	private String currentProjectSn;
	private SanityTestFormDAO sanityInfo=new SanityTestFormDAOImpl();
	private SanityProjectDAO sanityProjectDAO=new SanityProjectDAOImpl();
	private SanityTestForm sanityLastInfo;
	private List<SanityTestForm> sanityMoreInfo;
	private String SnmailTo;	
	private String SnmailCC;
	//smoke测试信息获取
	private List<String> projectListSm = new ArrayList<String>();
	private String currentProjectSm;
	private SmokeTestFormDAO smokeInfo=new SmokeTestFormDAOImpl();
	private SmokeProjectDAO smokeProjectDAO=new SmokeProjectDAOImpl();
	private SmokeTestForm smokeLastInfo;
	private List<SmokeTestForm> smokeMoreInfo;
	private String SmmailTo;	
	private String SmmailCC;
	//mtbf测试信息获取
	private List<String> projectListMt = new ArrayList<String>();
	private String currentProjectMt;
	private MTBFFormDAO mtbfInfo=new MTBFFormDAOImpl();
	private MTBFProjectInfoDAO mtbfProjectDAO=new MTBFProjectInfoDAOImpl();
	private MTBFForm mtbfLastInfo;
	private List<MTBFForm> mtbfMoreInfo;
	private String MtmailTo;	
	private String MtmailCC;
	//mtbf_ui测试信息获取
	private List<String> projectListMu = new ArrayList<String>();
	private String currentProjectMu;
	private MTBF_uiFormDAO mtbf_uiInfo=new MTBF_uiFormDAOImpl();
	private MTBF_uiProjectInfoDAO mtbf_uiProjectDAO=new MTBF_uiProjectInfoDAOImpl();
	private MTBF_uiForm mtbf_uiLastInfo;
	private List<MTBF_uiForm> mtbf_uiMoreInfo;
	private String MumailTo;
	private String MumailCC;	
	

	public String execute(){
		if (tabclicked==null){
			tabclicked = "tab-monkey";
		   if (null == mselected){
			getMonkeyInfoInit();
			getMonkeyInfo(mselected);
		     }else if (null != mselected){	
			    getMonkeyInfo(mselected);
		       }
		   }
		
	    if ( tabclicked.equals("tab-monkey") ){
	    	if (null == mselected){
				getMonkeyInfoInit();
				getMonkeyInfo(mselected);
			     }else if (null != mselected){	
				    getMonkeyInfo(mselected);
			       }	
		   }
	    
	    if ( tabclicked.equals("tab-sanity") ){
	    	if (null == snselected){
			    getSanityInfoInit();
			    getSanityInfo(snselected);
			   }else{
	            getSanityInfo(snselected);
			   }	
		   }
		 
	    if ( tabclicked.equals("tab-smoke") ){
	    	if (null == smselected){
	  		    getSmokeInfoInit();
	  		    getSmokeInfo(smselected);
	  		}else{
	        	getSmokeInfo(smselected);
	  		}	
		   }
	    
	    if ( tabclicked.equals("tab-mtbf_ui") ){
	    	if (null == muselected){
	        	getMtbf_uiInfoInit();
	        	getMtbf_uiInfo(muselected);
	      	}else{
	      		tabclicked ="tab-mtbf_ui";
	            getMtbf_uiInfo(muselected);
	      	}	
		   }
	    

		
		  

		return SUCCESS;
	}	
	
	public void getMonkeyInfoInit(){
		projectListM = monkeyProjectDAO.getValidProjectName();		
		mselected = projectListM.get(0);	
	}

	public void getSanityInfoInit(){
		projectListSn = sanityProjectDAO.getSanityValidProjectName();
		snselected = projectListSn.get(0);
	}
	
	public void getSmokeInfoInit(){
		projectListSm = smokeProjectDAO.getSmokeValidProjectName();
		smselected = projectListSm.get(0);
	}
	
	public void getMtbf_uiInfoInit(){
		projectListMu = mtbf_uiProjectDAO.getValidProjectName();
		muselected = projectListMu.get(0);
	}
	
	public void getMonkeyInfo(String MSelected){	
		    projectListM = monkeyProjectDAO.getValidProjectName();
			currentProjectM = MSelected;
			monkeyMoreInfo = monkeyInfo.getMonkeyTestInfoByProject(currentProjectM);
			MmailTo = monkeyProjectDAO.getmailInfoByProject("mailTo", currentProjectM);
		    MmailCC = monkeyProjectDAO.getmailInfoByProject("mailCC", currentProjectM);
		
		if(null != monkeyMoreInfo){
			monkeyLastInfo = monkeyMoreInfo.get(0);
			monkeyMoreInfo.remove(0);
		}
		if(null !=monkeyMoreInfo && monkeyMoreInfo.size()>5){
			for(int i=monkeyMoreInfo.size();i>4;i--){
				monkeyMoreInfo.remove(i-1);
			}			
		}	
	}
	
	public void getSanityInfo(String SnSelected){
		    projectListSn = sanityProjectDAO.getSanityValidProjectName();
			currentProjectSn = SnSelected;
			SnmailTo = sanityProjectDAO.getmailInfoByProject("mailTo", currentProjectSn);
		    SnmailCC = sanityProjectDAO.getmailInfoByProject("mailCC", currentProjectSn);
		    sanityLastInfo = sanityInfo.getSanityLastInfoByProject(currentProjectSn);
		}

	public void getSmokeInfo(String SmSelected){
	        projectListSm = smokeProjectDAO.getSmokeValidProjectName();
		    currentProjectSm = SmSelected;
		    SmmailTo = smokeProjectDAO.getmailInfoByProject("mailTo", currentProjectSm);
		    SmmailCC = smokeProjectDAO.getmailInfoByProject("mailCC", currentProjectSm);
		    smokeLastInfo = smokeInfo.getSmokeLastInfoByProject(currentProjectSm);
	}
	
	public void getMtbf_uiInfo(String MuSelected){
	        projectListMu = mtbf_uiProjectDAO.getValidProjectName();
		    currentProjectMu = MuSelected;
		    MumailTo = mtbf_uiProjectDAO.getmailInfoByProject("mailTo", currentProjectMu);
		    MumailCC = mtbf_uiProjectDAO.getmailInfoByProject("mailCC", currentProjectMu);
		    mtbf_uiLastInfo = mtbf_uiInfo.getMTBF_uiLastTestByProject(currentProjectMu);		
	}

	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getSearchProject() {
		return searchProject;
	}

	public void setSearchProject(String searchProject) {
		this.searchProject = searchProject;
	}
	
	public List<String> getProjectListM() {
		return projectListM;
	}

	public void setProjectListM(List<String> projectListM) {
		this.projectListM = projectListM;
	}

	public TestForm getMonkeyLastInfo() {
		return monkeyLastInfo;
	}

	public void setMonkeyLastInfo(TestForm monkeyLastInfo) {
		this.monkeyLastInfo = monkeyLastInfo;
	}

	public List<TestForm> getMonkeyMoreInfo() {
		return monkeyMoreInfo;
	}

	public void setMonkeyMoreInfo(List<TestForm> monkeyMoreInfo) {
		this.monkeyMoreInfo = monkeyMoreInfo;
	}

	public String getCurrentProjectM() {
		return currentProjectM;
	}

	public void setCurrentProjectM(String currentProjectM) {
		this.currentProjectM = currentProjectM;
	}
	
	public String getMmailTo() {
		return MmailTo;
	}
	public void setMmailTo(String mmailTo) {
		MmailTo = mmailTo;
	}
	
	public String getMmailCC() {
		return MmailCC;
	}
	public void setMmailCC(String mmailCC) {
		MmailCC = mmailCC;
	}


	public List<SanityTestForm> getSanityMoreInfo() {
		return sanityMoreInfo;
	}


	public void setSanityMoreInfo(List<SanityTestForm> sanityMoreInfo) {
		this.sanityMoreInfo = sanityMoreInfo;
	}
	
	public List<String> getProjectListSn() {
		return projectListSn;
	}


	public void setProjectListSn(List<String> projectListSn) {
		this.projectListSn = projectListSn;
	}


	public String getCurrentProjectSn() {
		return currentProjectSn;
	}


	public void setCurrentProjectSn(String currentProjectSn) {
		this.currentProjectSn = currentProjectSn;
	}


	public SanityTestForm getSanityLastInfo() {
		return sanityLastInfo;
	}


	public void setSanityLastInfo(SanityTestForm sanityLastInfo) {
		this.sanityLastInfo = sanityLastInfo;
	}


	public String getSnmailTo() {
		return SnmailTo;
	}


	public void setSnmailTo(String snmailTo) {
		SnmailTo = snmailTo;
	}


	public String getSnmailCC() {
		return SnmailCC;
	}


	public void setSnmailCC(String snmailCC) {
		SnmailCC = snmailCC;
	}
	public List<String> getProjectListSm() {
		return projectListSm;
	}

	public void setProjectListSm(List<String> projectListSm) {
		this.projectListSm = projectListSm;
	}

	public String getCurrentProjectSm() {
		return currentProjectSm;
	}

	public void setCurrentProjectSm(String currentProjectSm) {
		this.currentProjectSm = currentProjectSm;
	}

	public SmokeTestForm getSmokeLastInfo() {
		return smokeLastInfo;
	}

	public void setSmokeLastInfo(SmokeTestForm smokeLastInfo) {
		this.smokeLastInfo = smokeLastInfo;
	}

	public List<SmokeTestForm> getSmokeMoreInfo() {
		return smokeMoreInfo;
	}

	public void setSmokeMoreInfo(List<SmokeTestForm> smokeMoreInfo) {
		this.smokeMoreInfo = smokeMoreInfo;
	}

	public String getSmmailTo() {
		return SmmailTo;
	}

	public void setSmmailTo(String smmailTo) {
		SmmailTo = smmailTo;
	}

	public String getSmmailCC() {
		return SmmailCC;
	}

	public void setSmmailCC(String smmailCC) {
		SmmailCC = smmailCC;
	}
	public List<String> getProjectListMt() {
		return projectListMt;
	}

	public void setProjectListMt(List<String> projectListMt) {
		this.projectListMt = projectListMt;
	}

	public String getCurrentProjectMt() {
		return currentProjectMt;
	}

	public void setCurrentProjectMt(String currentProjectMt) {
		this.currentProjectMt = currentProjectMt;
	}

	public MTBFForm getMtbfLastInfo() {
		return mtbfLastInfo;
	}

	public void setMtbfLastInfo(MTBFForm mtbfLastInfo) {
		this.mtbfLastInfo = mtbfLastInfo;
	}

	public List<MTBFForm> getMtbfMoreInfo() {
		return mtbfMoreInfo;
	}

	public void setMtbfMoreInfo(List<MTBFForm> mtbfMoreInfo) {
		this.mtbfMoreInfo = mtbfMoreInfo;
	}

	public String getMtmailTo() {
		return MtmailTo;
	}

	public void setMtmailTo(String mtmailTo) {
		MtmailTo = mtmailTo;
	}

	public String getMtmailCC() {
		return MtmailCC;
	}

	public void setMtmailCC(String mtmailCC) {
		MtmailCC = mtmailCC;
	}
	
	public List<String> getProjectListMu() {
		return projectListMu;
	}

	public void setProjectListMu(List<String> projectListMu) {
		this.projectListMu = projectListMu;
	}

	public String getCurrentProjectMu() {
		return currentProjectMu;
	}

	public void setCurrentProjectMu(String currentProjectMu) {
		this.currentProjectMu = currentProjectMu;
	}

	public MTBF_uiForm getMtbf_uiLastInfo() {
		return mtbf_uiLastInfo;
	}

	public void setMtbf_uiLastInfo(MTBF_uiForm mtbf_uiLastInfo) {
		this.mtbf_uiLastInfo = mtbf_uiLastInfo;
	}

	public List<MTBF_uiForm> getMtbf_uiMoreInfo() {
		return mtbf_uiMoreInfo;
	}

	public void setMtbf_uiMoreInfo(List<MTBF_uiForm> mtbf_uiMoreInfo) {
		this.mtbf_uiMoreInfo = mtbf_uiMoreInfo;
	}

	public String getMumailTo() {
		return MumailTo;
	}

	public void setMumailTo(String mumailTo) {
		MumailTo = mumailTo;
	}

	public String getMumailCC() {
		return MumailCC;
	}

	public void setMumailCC(String mumailCC) {
		MumailCC = mumailCC;
	}

	public String getTabclicked() {
		return tabclicked;
	}

	public void setTabclicked(String tabclicked) {
		this.tabclicked = tabclicked;
	}
	public String getMselected() {
		return mselected;
	}

	public void setMselected(String mselected) {
		this.mselected = mselected;
	}

	public String getSnselected() {
		return snselected;
	}

	public void setSnselected(String snselected) {
		this.snselected = snselected;
	}

	public String getSmselected() {
		return smselected;
	}

	public void setSmselected(String smselected) {
		this.smselected = smselected;
	}

	public String getMtselected() {
		return mtselected;
	}

	public void setMtselected(String mtselected) {
		this.mtselected = mtselected;
	}

	public String getMuselected() {
		return muselected;
	}

	public void setMuselected(String muselected) {
		this.muselected = muselected;
	}

}