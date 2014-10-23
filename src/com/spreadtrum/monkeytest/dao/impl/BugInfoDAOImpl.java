package com.spreadtrum.monkeytest.dao.impl;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.monkeytest.dao.BugInfoDAO;
import com.spreadtrum.monkeytest.model.*;
import com.spreadtrum.util.HibernateUtil;
public class BugInfoDAOImpl implements BugInfoDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<BugInfo> getBugInfo(int formNameID) {
		String hql;
		List <BugInfo> results = null;
		hql = "from BugInfo as BI where BI.formID=" +formNameID;
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtil().openSession();
		Transaction tx = session.beginTransaction();
		try{
		    //开启事务
		    results = session.createQuery(hql).list();
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtil().closeSession(session);
		        } 
		     
		return results!=null&&results.size()>0?(List<BugInfo>)results:null;
	}


	@Override
	public List<?> getBugInfoProp(String prop, int formNameID) {
		String hql;
		List<?> results = null;
		hql = "select "+ prop +" from BugInfo as BI where BI.formID=" +formNameID;
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtil().openSession();
		Transaction tx = session.beginTransaction();
		try{
		    //开启事务
		    results = session.createQuery(hql).list();
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtil().closeSession(session);
		        } 
		     
		return results!=null&&results.size()>0?results:null;
	}

}
