package com.spreadtrum.monkeytest.dao.impl;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.monkeytest.dao.ErrorTypeDAO;
import com.spreadtrum.monkeytest.model.*;
import com.spreadtrum.util.HibernateUtil;
public class ErrorTypeDAOImpl implements ErrorTypeDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<ErrorType> getErrorTypeInfo() {
		// TODO Auto-generated method stub
		String hql;
		List<ErrorType> results = null;
		hql = "from ErrorType";
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
		     
		return results!=null&&results.size()>0?(List<ErrorType>)results:null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getErrorTypeProp(String prop, int errorTypeID) {
		String hql;
		List<String> results = null;
		hql = "select "+prop+" from ErrorType as ErrT where ErrT.id=" +errorTypeID;
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
		     
		return results!=null&&results.size()>0?(String)results.get(0):null;
	
	}
	

}
