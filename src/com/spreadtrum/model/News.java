package com.spreadtrum.model;
 
import java.io.Serializable;
import java.util.Date;
 
public class News implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer news_id;
	private String title;
	private String url;
	private Date mdate;
	//private IndexPage index_page;
	
	public Integer getNews_id() {
		return news_id;
	}
	public News() {
		news_id = 0;
		title = "未获得相应标题";
		url="#";
		mdate = new Date();
		// TODO Auto-generated constructor stub
	}
	public void setNews_id(Integer news_id) {
		this.news_id = news_id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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

	//根据title、url、mdate来判断是否相等
    public boolean equals(Object obj){
        if(this == obj){
           return true;
        }
        if(obj !=null && obj.getClass() == IndexPage.class){
           News news = (News)obj;
           return this.getTitle().equals(news.getTitle()) && this.getUrl().equals(news.getUrl())
                 && this.getMdate().toString().equals(news.getMdate().toString());
        }
		return false;
    }

    public int hashCode(){
       //return title.hashCode()+url.hashCode()*17+getMdate().toString().hashCode()*29;
    	return title.hashCode()+url.hashCode()*17;
    }
 
}
