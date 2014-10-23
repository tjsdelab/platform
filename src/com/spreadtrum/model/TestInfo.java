package com.spreadtrum.model;
 
import java.io.Serializable;
import java.util.Date;
 
public class TestInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer test_info_id;
	private String text_string;
	private String pac; 
	private String url;
	private Date mdate;
	//private IndexPage index_page;
	
	public Integer getTest_info_id() {
		return test_info_id;
	}
	public TestInfo() {
		test_info_id = 0;
		text_string = "未知数据";
		pac = "未知数据";
		url = "#";
		mdate = new Date();
		// TODO Auto-generated constructor stub
	}
	public void setTest_info_id(Integer test_info_id) {
		this.test_info_id = test_info_id;
	}
	
	public String getText_string() {
		return text_string;
	}
	public void setText_string(String text_string) {
		this.text_string = text_string;
	}
	
	public String getPac() {
		return pac;
	}
	public void setPac(String pac) {
		this.pac = pac;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Date getMdate() {
		return mdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
	/*
	public IndexPage getIndex_page() {
		return index_page;
	}
	public void setIndex_page(IndexPage index_page) {
		this.index_page = index_page;
	}
	*/

    //���title��url��mdate���ж��Ƿ����
    public boolean equals(Object obj){
        if(this == obj){
           return true;
        }
        if(obj !=null && obj.getClass() == IndexPage.class){
           TestInfo testInfo = (TestInfo)obj;
           return this.getText_string().equals(testInfo.getText_string()) && this.getUrl().equals(testInfo.getUrl())
                 && this.getPac().equals(testInfo.getPac()) && this.getMdate().toString().equals(testInfo.getMdate().toString());
        }
		return false;
    }

    public int hashCode(){
       return text_string.hashCode()+url.hashCode()*17+pac.hashCode()*19+getMdate().toString().hashCode()*29;
    }
}
