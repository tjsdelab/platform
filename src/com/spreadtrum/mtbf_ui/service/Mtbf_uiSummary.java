package com.spreadtrum.mtbf_ui.service;

import java.sql.Date;

import com.spreadtrum.mtbf_ui.model.MTBF_uiProjectInfo;

public class Mtbf_uiSummary {
		private String softwareVsn;
		private String projectName;
		private String pacPath;
		private String conclusion;	
		
		

		public String getConclusion(){
			return conclusion;
		}
		public void setConclusion(String conclusion){
			this.conclusion=conclusion;
		}
		public String getSoftwareVsn() {
			return softwareVsn;
		}
		public void setSoftwareVsn(String softwareVsn) {
			this.softwareVsn = softwareVsn;
		}

		public String getPacPath() {
			return pacPath;
		}
		public void setPacPath(String pacPath) {
			this.pacPath = pacPath;
		}
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
	

}
