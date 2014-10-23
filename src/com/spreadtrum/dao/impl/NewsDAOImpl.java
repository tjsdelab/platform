package com.spreadtrum.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.dao.NewsDAO;
import com.spreadtrum.model.*;
import com.spreadtrum.util.HibernateUtil;
 
public class NewsDAOImpl implements NewsDAO {
 
	@Override
	public void saveNews(News news) {
		//开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtil().openSession();
		Transaction tx = session.beginTransaction();
		//开启事务
		try {
			//插入
			session.save(news);
			tx.commit();
		} catch (Exception ex) {
			if (null != tx) {
				tx.rollback(); //失败回滚
			}
		} finally {
			//关闭session
			new HibernateUtil().closeSession(session);
		}
 
	}
	
	
	//find last 10 news
	@SuppressWarnings("unchecked")
	public List<News> showLast10News() {
	   List<News> list = null; 
	 //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
	   org.hibernate.Session session = new HibernateUtil().openSession();
	   Transaction tx = session.beginTransaction();
     try{
    	   //开启事务
           //list = session.createQuery("from News as n  where n.news_id > ((select max(n.news_id) from n) -10)").list();
    	   list = session.createQuery("from News as n ORDER BY n.news_id desc").setMaxResults(10).list();
           tx.commit();
     } catch (HibernateException e) { //捕捉异常
        e.printStackTrace();
        tx.rollback();
     } finally {
    	 new HibernateUtil().closeSession(session);
     } 
     
     return (List<News>)list;
  }


		
}
