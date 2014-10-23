package com.spreadtrum.model;
 
import java.io.Serializable;
 
public class IndexPage implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer index_page_id;
	private String team_members;
	//private Set<News> news = new HashSet<News>();
	private String location;
	//private Set<TestInfo> test_info = new HashSet<TestInfo>();
	
	public Integer getIndex_page_id() {
		return index_page_id;
	}
	public void setIndex_page_id(Integer index_page_id) {
		this.index_page_id = index_page_id;
	}
	
	public String getTeam_members() {
		return team_members;
	}
	public void setTeam_members(String team_members) {
		this.team_members = team_members;
	}
	/*
	public Set<News> getNews() {
		return news;
	}
	public void setNews(Set<News> news) {
		this.news = news;
	}
	*/
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	/*
	public Set<TestInfo> getTest_info() {
		return test_info;
	}
	public void setTest_info(Set<TestInfo> test_info) {
		this.test_info = test_info;
	}
	*/

    //maybe not used
    public boolean equals(Object obj){
        if(this == obj){
           return true;
        }
        if(obj !=null && obj.getClass() == IndexPage.class){
           IndexPage indPage = (IndexPage)obj;
           return this.getTeam_members().equals(indPage.getTeam_members()) && this.getLocation().equals(indPage.getLocation());
        }
        return false;
    }


	//maybe not used
    public int hashCode(){
       return team_members.hashCode()+location.hashCode()*17;
    }
	@Override
	public String toString() {
		return "IndexPage [index_page_id=" + index_page_id + ", team_members="
				+ team_members + ", location=" + location + "]";
	}
 
}
