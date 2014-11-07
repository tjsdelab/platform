package com.spreadtrum.monkeyForRD.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.spreadtrum.monkeyForRD.dao.MonkeyForRDMemberInfoDAO;
import com.spreadtrum.monkeyForRD.dao.MonkeyForRDTestInfoDAO;
import com.spreadtrum.monkeyForRD.dao.impl.MonkeyForRDMemberInfoDAOImpl;
import com.spreadtrum.monkeyForRD.dao.impl.MonkeyForRDTestInfoDAOImpl;
import com.spreadtrum.monkeyForRD.model.MonkeyForRDTestInfo;

public class MonkeyForRDHomeAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String site;
	private Date date;
	private MonkeyForRDTestInfoDAO rdTestInfoDAO = new MonkeyForRDTestInfoDAOImpl();
	private MonkeyForRDMemberInfoDAO rdMemberDAO = new MonkeyForRDMemberInfoDAOImpl();
	private List<MonkeyForRDTestInfo> rdTestInfo;
	private List<String> deviceList;
	private String testInfoDate;
	private int queryDays = 1;

	private List<String> group;
	private List<PieData> currPieData = new ArrayList<PieData>();
	private List<PieData> PieData2 = new ArrayList<PieData>();
	private List<PieData> PieData3 = new ArrayList<PieData>();
	private List<MonkeyForRDPerformance> rdPerformanceList = new ArrayList<MonkeyForRDPerformance>();

	public String execute() {

		if (null == site) {
			site = "TJ";
		}
		if (null == testInfoDate) {
			// date = rdTestInfoDAO.getLastDateBySite(site);
			date = new java.sql.Date(new java.util.Date().getTime() - 24 * 60
					* 60 * 1000);
		} else {
			date = Date.valueOf(testInfoDate);
		}
		getRDTestInfo();
		getPerformanceList();
		currPieData = getPieData(queryDays,site);
		if (site.equals("TJ")) {
			PieData2 = getPieData(queryDays,"BJ");
			PieData3 = getPieData(queryDays,"SH");
		} else if (site.equals("BJ")){
			PieData2 = getPieData(queryDays,"SH");
			PieData3 = getPieData(queryDays,"TJ");
		} else {
			PieData2 = getPieData(queryDays,"TJ");
			PieData3 = getPieData(queryDays,"BJ");			
		}
		ServletActionContext.getRequest()
		.setAttribute("currPieData", currPieData);
		ServletActionContext.getRequest()
		.setAttribute("PieData2", PieData2);
		ServletActionContext.getRequest()
		.setAttribute("PieData3", PieData3);

		return SUCCESS;
	}

	public void getRDTestInfo() {

		rdTestInfo = rdTestInfoDAO.getSiteTestInfoByDate(site, date);
		if (null != rdTestInfo) {
			for (int i = 0; i < rdTestInfo.size(); i++) {
				String deviceName = rdTestInfo.get(i).getDeviceName();
				if (null != deviceName) {
					if (null != rdMemberDAO.getPerPropByDevice("name",
							deviceName)) {
						String rdName = (String) (rdMemberDAO
								.getPerPropByDevice("name", deviceName).get(0));
						if (null != rdName) {
							rdTestInfo.get(i).setDeviceName(rdName);
						}
					}
				}
			}
		} else {
			ServletActionContext.getRequest().setAttribute("warning",
					"本次无人测试，请加油!!!");
		}
		ServletActionContext.getRequest()
				.setAttribute("rdTestInfo", rdTestInfo);

	}

	public void getPerformanceList() {
		deviceList = rdMemberDAO.getAllDeviceNameBySite(site);
		group = rdMemberDAO.getGroupNameBySite(site);
		for (int i = 0; i < deviceList.size(); i++) {
			MonkeyForRDPerformance rdPerformance = new MonkeyForRDPerformance();

			String name = (String) (rdMemberDAO.getPerPropByDevice("name",
					deviceList.get(i)).get(0));
			int doDaysWeek = rdTestInfoDAO.getDoDaysForPeriodByDevice(7,
					deviceList.get(i));
			int doDaysforAll = rdTestInfoDAO.getDoDaysForAllByDevice(deviceList
					.get(i));
			int doifYestoday = rdTestInfoDAO.getDoCountThisDayByDevice(
					deviceList.get(i), date);
			if (0 == doifYestoday) {
				rdPerformance.setDoIfYesterday("否");
			} else {
				rdPerformance.setDoIfYesterday("是");
			}
			String belongGroup = rdMemberDAO
					.getAllPerPropByDevice(deviceList.get(i)).get(0)
					.getGroupID().getGroupName();

			rdPerformance.setName(name);
			rdPerformance.setDoDaysForWeek(doDaysWeek);
			rdPerformance.setDoDaysForAll(doDaysforAll);
			rdPerformance.setBelongGroup(belongGroup);
			rdPerformanceList.add(rdPerformance);
		}
		Collections.sort(rdPerformanceList, comparator);
		ServletActionContext.getRequest().setAttribute("rdPerformanceList",
				rdPerformanceList);
		ServletActionContext.getRequest().setAttribute("group", group);
	}

	public List<PieData> getPieData(int piedays, String pieSite) {
		List<PieData> results = new ArrayList<PieData>();
		List<String> groupNames = rdMemberDAO.getGroupNameBySite(pieSite);		
		if (null != groupNames) {
			for (int i = 0; i < groupNames.size(); i++) {
				List<String> deviceNames = rdMemberDAO.getGroupDeviceName(pieSite, groupNames.get(i));				
				float personNum = 0;
				float doCountAll = 0;
				String group = groupNames.get(i);
				float performanceRatio;
				if (null != deviceNames){
					personNum = deviceNames.size() + 1;					
					for (int j = 0; j < deviceNames.size(); j++){
						int doCount = rdTestInfoDAO.getDoDaysForPeriodByDevice(piedays, deviceNames.get(j));
						doCountAll += doCount;
					}
				}
				performanceRatio = doCountAll / (personNum * piedays);
				results.add(new PieData(group,performanceRatio));
				System.out.println(group + ":"+"performanceRatio:"+performanceRatio + "doCountAll:" + doCountAll);
			}
		}
		ServletActionContext.getRequest()
		.setAttribute("queryDays", queryDays);
		return results;
	}

	Comparator<MonkeyForRDPerformance> comparator = new Comparator<MonkeyForRDPerformance>() {
		public int compare(MonkeyForRDPerformance m1, MonkeyForRDPerformance m2) {
			return new Integer(m2.getDoDaysForAll()).compareTo(new Integer(m1
					.getDoDaysForAll()));
		}
	};

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public MonkeyForRDTestInfoDAO getRdTestInfoDAO() {
		return rdTestInfoDAO;
	}

	public void setRdTestInfoDAO(MonkeyForRDTestInfoDAO rdTestInfoDAO) {
		this.rdTestInfoDAO = rdTestInfoDAO;
	}

	public List<MonkeyForRDTestInfo> getRdTestInfo() {
		return rdTestInfo;
	}

	public void setRdTestInfo(List<MonkeyForRDTestInfo> rdTestInfo) {
		this.rdTestInfo = rdTestInfo;
	}

	public List<String> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<String> deviceList) {
		this.deviceList = deviceList;
	}

	public List<MonkeyForRDPerformance> getRdPerformanceList() {
		return rdPerformanceList;
	}

	public void setRdPerformanceList(
			List<MonkeyForRDPerformance> rdPerformanceList) {
		this.rdPerformanceList = rdPerformanceList;
	}

	public String getTestInfoDate() {
		return testInfoDate;
	}

	public void setTestInfoDate(String testInfoDate) {
		this.testInfoDate = testInfoDate;
	}

	public List<String> getGroup() {
		return group;
	}

	public void setGroup(List<String> group) {
		this.group = group;
	}


	public int getQueryDays() {
		return queryDays;
	}

	public void setQueryDays(int queryDays) {
		this.queryDays = queryDays;
	}

	public List<PieData> getCurrPieData() {
		return currPieData;
	}

	public void setCurrPieData(List<PieData> currPieData) {
		this.currPieData = currPieData;
	}

	public List<PieData> getPieData2() {
		return PieData2;
	}

	public void setPieData2(List<PieData> pieData2) {
		PieData2 = pieData2;
	}

	public List<PieData> getPieData3() {
		return PieData3;
	}

	public void setPieData3(List<PieData> pieData3) {
		PieData3 = pieData3;
	}
	

}
