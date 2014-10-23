package com.spreadtrum.monkeytest.dao.impl;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.monkeytest.dao.PhoneTestInfoDAO;
import com.spreadtrum.monkeytest.model.*;
import com.spreadtrum.util.HibernateUtil;
public class PhoneTestInfoDAOImpl implements PhoneTestInfoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<PhoneTestInfo> getPhoneTestInfo(int formNameID) {
		String hql;
		List<PhoneTestInfo> results = null;
		hql = "from PhoneTestInfo as ptf where ptf.testFormId=" +formNameID;
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
		     
		return results!=null&&results.size()>0?(List<PhoneTestInfo>)results:null;
	}

	@Override
	public List<?> PhoneTestInfoProp(String prop, int formNameID) {
		String hql;
		List<?> results = null;
		hql = "select "+ prop +" from PhoneTestInfo as ptf where ptf.testFormId=" +formNameID;
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
