package com.spreadtrum.monkeytest.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.spreadtrum.monkeytest.dao.ProjectFormDAO;
import com.spreadtrum.monkeytest.dao.impl.ProjectFormDAOImpl;
import com.spreadtrum.monkeytest.model.TestForm;
import com.spreadtrum.monkeytest.service.MonkeyDetail;
import com.spreadtrum.monkeytest.service.MonkeySearch;
import com.spreadtrum.monkeytest.service.impl.MonkeyDetailImpl;
import com.spreadtrum.monkeytest.service.impl.MonkeySearchImpl;
import com.spreadtrum.monkeytest.vo.LowerTestInfo;
import com.spreadtrum.monkeytest.vo.OverallTimeInfo;
import com.spreadtrum.monkeytest.vo.UperTestInfo;

//通过表单号查找详情的时候，下面四张图使用工程名作为参数来设置，但是service没有提供这个接口
//现在默认使用的工程名是下拉列表第一个工程名，这里还要再改，否则折线图不能动态变化。
//建议：在该类中添加当前工程属性，在通过表单查找的时候能对工程赋值
//第二个问题：由上一个问题导致的下拉列表显示的第一个不是当前表单对应的工程问题，在方法getInfoByFormName开始中解决。
public class MonkeyDetailAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private String searchProject=""; //分三种类型搜索时候的工程名或时间或版次
	private String type = "";          //分三种类型搜索时候的类型 ，date 或 project 或 pacversion
	private String testFormName = null;   //表单号，用来向数据库查询详细页要显示的表单.还在页面中显示。1）工程的最新表单
													//2）显示工程所有表单之后，点击表单，testFormName就是搜索用的句柄
	private String projectSearchNameString = null; //下拉框选择的工程，显示该工程的最新表单
	//sortedProject是用在下拉列表中显示的工程名
	private List<String> sortedProjectList = new ArrayList<String>();
	//查询之后跳转到formname页显示时需要的数据
	private List<TestForm> testFormList;
	private ProjectFormDAO projectFormDAO;
	//private String currentProject="sp9830_uui";
	//从monkey页上点击折线图之后跳转到当前页中
	private String version = null;
	//monkey详细页上需要显示的内容
	private String pACPath;
	private String logPath;
	private OverallTimeInfo overallTimeInfo=new OverallTimeInfo();
	private List<UperTestInfo> uperTestInfoList=new ArrayList<UperTestInfo>();
	private List<LowerTestInfo> lowerTestInfoList=new ArrayList<LowerTestInfo>();
	private List<OverallTimeInfo> overallTimeInfoList=new ArrayList<OverallTimeInfo>();

	private MonkeyDetail monkeyDetail=new MonkeyDetailImpl();
	
	
	
	public  void addToDropdownList(){

		sortedProjectList.clear();
		projectFormDAO = new ProjectFormDAOImpl();
		sortedProjectList = projectFormDAO.getValidProjectName();
	}
	

	//按照date project pacVersion搜索时执行的方法
		public String search(){
			MonkeySearch monkeySearch=new MonkeySearchImpl();
			testFormList=monkeySearch.findMatchedFormNames(searchProject,type);
			return "search";
			
		}
	//点击工程下拉框之后执行的函数
		public String dropDownProject(){
					addToDropdownList();
					getInfoByProject(projectSearchNameString);
					return SUCCESS;
				}
		public void getPacVersionList(String formName){
			
		}
       //页面加载时默认执行的函数
		public String monkeyDetail(){				 
					  if(testFormName != null){				   
						  getInfoByForm();
						  addToDropdownList();
						  return SUCCESS;
					  }
					  else{
					addToDropdownList();
					String project;
				    //当最近工程下拉框中选中是，应该改变project的值，使当前页面显示该工程的信息
					if(null == projectSearchNameString){			
						project=sortedProjectList.get(0);
                                                projectSearchNameString=project;
					}
					else{
						project = projectSearchNameString;
					}
					
					getInfoByProject(project);
			      
			      
				  return SUCCESS;
					  }
				}		
		
		public String byProjectAndVersion(){
        //通过version的id获取版次
			//String version ="W14_41_3-01";
			testFormName = monkeyDetail.findTestFormNameByPacVersionAndProject(version, projectSearchNameString);
			getInfoByForm();
			//接下来设置详细页从当前表单向前的历史内容,未完带续

			addToDropdownList();
			TreeMap<String,Float> projectAverageStopTimeMap=new TreeMap<String,Float>();
			TreeMap<String,Float> projectMiddleStopTimeMap=new TreeMap<String,Float>();
			TreeMap<String,Float> projectFirstErrAverageTimeMap=new TreeMap<String,Float>();
			TreeMap<String,Float> projectFirstErrMiddleTimeMap=new TreeMap<String,Float>();
			pACPath=monkeyDetail.receivePACPath(testFormName);			
			logPath=monkeyDetail.receiveLogPath("logPath", testFormName);
			if (null == logPath) {
				logPath = "log 路径不存在";
			}
			//总体时间汇总
	        overallTimeInfo=monkeyDetail.receiveOverallTimeInfo(testFormName);
					
			//工程的“上层测试信息”
	    	uperTestInfoList=monkeyDetail.receiveUperTestInfo(testFormName);    
					
			//工程的“底层测试信息”
	     	lowerTestInfoList=monkeyDetail.receiveLowerTestInfo(testFormName);
	     	//工程一段时间的停止均值、停止中值、首错均值、首错中值
			projectAverageStopTimeMap=monkeyDetail.receiveProjectAverageStopTime_FormName(testFormName);
			projectMiddleStopTimeMap=monkeyDetail.receiveProjectMiddleStopTime_FormName(testFormName);
			projectFirstErrAverageTimeMap=monkeyDetail.receiveProjectFirstErrAverageTime_FormName(testFormName);
			projectFirstErrMiddleTimeMap=monkeyDetail.receiveProjectFirstErrMiddleTime_FormName(testFormName);
	     	
			//杨欣之前写的pacVersion，用于向页面返回排序之后的工程名，使四个图标显示顺序一致
			ArrayList<String> pacVersion = new ArrayList<String>();
		    String averageStopTimestr = "";
		    String averageStopTime = "";

	        List<Map.Entry<String,Float>> list = new ArrayList<Map.Entry<String,Float>>(projectAverageStopTimeMap.entrySet());
	      //获取有序版本号（一组list）和对应的停止均值时间（String类型）
	      for(Map.Entry<String,Float> mapping:list){ 
	    	  pacVersion.add(mapping.getKey());
	          averageStopTimestr += mapping.getValue() + ",";
	     } 
	      averageStopTime = averageStopTimestr.substring(0, averageStopTimestr.length() - 1);
	      String midStopTimeStr = "";
	      String midStopTime = "";
	      String averFirstErrTimeStr = "";
	      String averFirstErrTime = "";
	      String midFirstErrTimeStr = "";
	      String midFirstErrTime = "";
	     //获得停止时间中值、首错均值、首错中值
	      for(int i = 0; i < pacVersion.size(); i++)
	      {
	      	midStopTimeStr += projectMiddleStopTimeMap.get(pacVersion.get(i)) + ",";
	      	averFirstErrTimeStr += projectFirstErrAverageTimeMap.get(pacVersion.get(i)) + ",";
	      	midFirstErrTimeStr += projectFirstErrMiddleTimeMap.get(pacVersion.get(i)) + ",";
	      }
	      midStopTime = midStopTimeStr.substring(0, midStopTimeStr.length() - 1);
	      averFirstErrTime = averFirstErrTimeStr.substring(0, averFirstErrTimeStr.length() - 1);
	      midFirstErrTime = midFirstErrTimeStr.substring(0, midFirstErrTimeStr.length() - 1);
	      
	      ServletActionContext.getRequest().setAttribute("logPath", logPath);
		  ServletActionContext.getRequest().setAttribute("pACPath", pACPath);
		  ServletActionContext.getRequest().setAttribute("averageStopTime",averageStopTime );
	      ServletActionContext.getRequest().setAttribute("midStopTime",midStopTime );
	      ServletActionContext.getRequest().setAttribute("averFirstErrTime",averFirstErrTime );
	      ServletActionContext.getRequest().setAttribute("midFirstErrTime",midFirstErrTime );
	      ServletActionContext.getRequest().setAttribute("list",pacVersion);
			return SUCCESS;
		}
       //输入参数是表单名，然后设置详细页的内容
		public String getInfoByForm(){
			//这个判断转移到addToDropdownList方法中判断
//			sortedProjectList.clear();
//			if(currentProject != null){
//			   sortedProjectList.add(currentProject);
//			   }
			
			projectSearchNameString = monkeyDetail.receiveProject(testFormName);
			addToDropdownList();
			TreeMap<String,Float> projectAverageStopTimeMap=new TreeMap<String,Float>();
			TreeMap<String,Float> projectMiddleStopTimeMap=new TreeMap<String,Float>();
			TreeMap<String,Float> projectFirstErrAverageTimeMap=new TreeMap<String,Float>();
			TreeMap<String,Float> projectFirstErrMiddleTimeMap=new TreeMap<String,Float>();
			
			
			pACPath=monkeyDetail.receivePACPath(testFormName);
			logPath=monkeyDetail.receiveLogPath("logPath", testFormName);
			if (null == logPath) {
				logPath = "log 路径不存在";
			}
			//总体时间汇总
	        overallTimeInfo=monkeyDetail.receiveOverallTimeInfo(testFormName);
					
			//工程的“上层测试信息”
	    	uperTestInfoList=monkeyDetail.receiveUperTestInfo(testFormName); 
					
			//工程的“底层测试信息”
	     	lowerTestInfoList=monkeyDetail.receiveLowerTestInfo(testFormName);
     	
	     	//工程一段时间的停止均值、停止中值、首错均值、首错中值
			projectAverageStopTimeMap=monkeyDetail.receiveProjectAverageStopTime(projectSearchNameString);
			projectMiddleStopTimeMap=monkeyDetail.receiveProjectMiddleStopTime(projectSearchNameString);
			projectFirstErrAverageTimeMap=monkeyDetail.receiveProjectFirstErrAverageTime(projectSearchNameString);
			projectFirstErrMiddleTimeMap=monkeyDetail.receiveProjectFirstErrMiddleTime(projectSearchNameString);

			ArrayList<String> pacVersion = new ArrayList<String>();

		    String averageStopTimestr = "";
		    String averageStopTime = "";

	      List<Map.Entry<String,Float>> list = new ArrayList<Map.Entry<String,Float>>(projectAverageStopTimeMap.entrySet());
	      //获取有序版本号（一组list）和对应的停止均值时间（String类型）
	      for(Map.Entry<String,Float> mapping:list){ 
	    	  pacVersion.add(mapping.getKey());
	          averageStopTimestr += mapping.getValue() + ",";
	     } 
	      averageStopTime = averageStopTimestr.substring(0, averageStopTimestr.length() - 1);
	      String midStopTimeStr = "";
	      String midStopTime = "";
	      String averFirstErrTimeStr = "";
	      String averFirstErrTime = "";
	      String midFirstErrTimeStr = "";
	      String midFirstErrTime = "";
	     //获得停止时间中值、首错均值、首错中值
	      for(int i = 0; i < pacVersion.size(); i++)
	      {
	      	midStopTimeStr += projectMiddleStopTimeMap.get(pacVersion.get(i)) + ",";
	      	averFirstErrTimeStr += projectFirstErrAverageTimeMap.get(pacVersion.get(i)) + ",";
	      	midFirstErrTimeStr += projectFirstErrMiddleTimeMap.get(pacVersion.get(i)) + ",";
	      }
	      midStopTime = midStopTimeStr.substring(0, midStopTimeStr.length() - 1);
	      averFirstErrTime = averFirstErrTimeStr.substring(0, averFirstErrTimeStr.length() - 1);
	      midFirstErrTime = midFirstErrTimeStr.substring(0, midFirstErrTimeStr.length() - 1);
	      
	      ServletActionContext.getRequest().setAttribute("logPath", logPath);					
		  ServletActionContext.getRequest().setAttribute("pACPath", pACPath);
		  ServletActionContext.getRequest().setAttribute("averageStopTime",averageStopTime );
	      ServletActionContext.getRequest().setAttribute("midStopTime",midStopTime );
	      ServletActionContext.getRequest().setAttribute("averFirstErrTime",averFirstErrTime );
	      ServletActionContext.getRequest().setAttribute("midFirstErrTime",midFirstErrTime );
	      ServletActionContext.getRequest().setAttribute("list",pacVersion);
	      return SUCCESS;
		}
//输入参数是工程名，然后设置详细页的内容
		public void getInfoByProject(String project){
			
			MonkeyDetail monkeyDetail=new MonkeyDetailImpl();
			//得到工程最新的表单
			testFormName=monkeyDetail.findLastTestFormNameByProjectName(project);
			getInfoByForm();
		}
		
	
	public List<TestForm> getTestFormList() {
		return testFormList;
	}

	public void setTestFormList(List<TestForm> testFormList) {
		this.testFormList = testFormList;
	}

	public String getTestFormName() {
		return testFormName;
	}

	public void setTestFormName(String testFormName) {
		testFormName = testFormName.trim();
		this.testFormName = testFormName;
	}

	public List<String> getSortedProjectList() {
		return sortedProjectList;
	}

	public void setSortedProjectList(List<String> sortedProjectList) {
		this.sortedProjectList = sortedProjectList;
	}

	public String getProjectSearchNameString() {
		return projectSearchNameString;
	}

	public void setProjectSearchNameString(String projectSearchNameString) {
		projectSearchNameString = projectSearchNameString.trim();
		this.projectSearchNameString = projectSearchNameString;
	}

	public String getType() {
		
		return type;
	}

	public void setType(String type) {
		type = type.trim();
		this.type = type;
	}

	public String getpACPath() {
		return pACPath;
	}

	public void setpACPath(String pACPath) {
		pACPath = pACPath.trim();
		this.pACPath = pACPath;
	}
	
	public OverallTimeInfo getOverallTimeInfo() {
		return overallTimeInfo;
	}

	public void setOverallTimeInfo(OverallTimeInfo overallTimeInfo) {
		this.overallTimeInfo = overallTimeInfo;
	}


	public List<UperTestInfo> getUperTestInfoList() {
		return uperTestInfoList;
	}


	public void setUperTestInfoList(List<UperTestInfo> uperTestInfoList) {
		this.uperTestInfoList = uperTestInfoList;
	}

	public List<LowerTestInfo> getLowerTestInfoList() {
		return lowerTestInfoList;
	}

	public void setLowerTestInfoList(List<LowerTestInfo> lowerTestInfoList) {
		this.lowerTestInfoList = lowerTestInfoList;
	}

	
	public List<OverallTimeInfo> getOverallTimeInfoList() {
		return overallTimeInfoList;
	}

	public void setOverallTimeInfoList(List<OverallTimeInfo> overallTimeInfoList) {
		this.overallTimeInfoList = overallTimeInfoList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
	

	public String getSearchProject() {
		return searchProject;
	}

	public void setSearchProject(String searchProject) {
		searchProject = searchProject.trim();
		this.searchProject = searchProject;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public String getLogPath() {
		return logPath;
	}


	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}


	
	
		
}

