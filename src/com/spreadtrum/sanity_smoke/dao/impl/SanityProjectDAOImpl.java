package com.spreadtrum.sanity_smoke.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.sanity_smoke.dao.SanityProjectDAO;
import com.spreadtrum.sanity_smoke.util.HibernateUtilForSS;
//import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
//import org.springframework.orm.hibernate3.HibernateCallback;

public class SanityProjectDAOImpl implements SanityProjectDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getSanityValidProjectName() {
		String hql;
		List<String> results = null;
		hql = "select projectName from SanityProject as sp where sp.validFlag=1 order by sp.id ";
		
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForSS().openSession();
		Transaction tx = session.beginTransaction();
		try{
		    //开启事务
			results = session.createQuery(hql).list();

                    //返回全部的记录集       
    //遍历结果集
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtilForSS().closeSession(session);
		        } 


		return results!=null&&results.size()>0?(List<String>)results:null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getmailInfoByProject(String prop, String projectName) {
		String hql;
		List<String> results = null;
		hql = "select "+prop+" from SanityProject as sp where sp.projectName='" +projectName+"'";
		
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForSS().openSession();
		Transaction tx = session.beginTransaction();
		try{
		    //开启事务
			results = session.createQuery(hql).list();

                    //返回全部的记录集       
    //遍历结果集
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtilForSS().closeSession(session);
		        } 

		return results!=null&&results.size()>0?(String)results.get(0):null;
	}

}
