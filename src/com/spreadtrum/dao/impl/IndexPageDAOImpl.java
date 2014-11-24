package com.spreadtrum.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.dao.IndexPageDAO;
import com.spreadtrum.model.*;
import com.spreadtrum.util.HibernateUtil;
 
public class IndexPageDAOImpl implements IndexPageDAO {
 
	@Override
	public void saveIndexPage(IndexPage indexPage) {
		//开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtil().openSession();
		Transaction tx = session.beginTransaction();
		//开启事务
		try {
			//插入
			session.save(indexPage);
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
	
	
	//查找最新的indexpage

	@SuppressWarnings("unchecked")
	public IndexPage showLastIndexPage(){
	   List<IndexPage> list = null;

       //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象

	   org.hibernate.Session session = new HibernateUtil().openSession();
	   Transaction tx = session.beginTransaction();
     try{
		 //开启事务
           list = session.createQuery("from IndexPage inp where inp.id = (select max(inp.index_page_id) from inp)").list();
           tx.commit();
     } catch (HibernateException e) { //捕捉异常
        e.printStackTrace();
        tx.rollback();
     } finally {
    	 new HibernateUtil().closeSession(session);
     } 
    // IndexPage last = (IndexPage)list.get(0);
    // return last;
     return list!=null&&list.size()>0?(IndexPage)list.get(0):null;
  }
	
}
