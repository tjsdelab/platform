package com.spreadtrum.monkeytest.dao.impl;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.monkeytest.dao.ErrorInfoDAO;
import com.spreadtrum.monkeytest.model.*;
import com.spreadtrum.util.HibernateUtil;
public class ErrorInfoDAOImpl implements ErrorInfoDAO {
	@SuppressWarnings("unchecked")
	@Override
	public List<ErrorInfo> getErrorInfo(int formNameID) {
		String hql;
		List<ErrorInfo> results = null;
		hql = "from ErrorInfo as ErrI where ErrI.formID=" +formNameID;
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
		     
		return results!=null&&results.size()>0?(List<ErrorInfo>)results:null;
	}

	@Override
	public List<?> getErrorInfoProp(String prop, int formNameID) {
		String hql;
		List<?> results = null;
		hql = "select "+ prop +" from ErrorInfo as ErrI where ErrI.formID=" +formNameID;
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
