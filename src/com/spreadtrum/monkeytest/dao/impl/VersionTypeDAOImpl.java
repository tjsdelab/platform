package com.spreadtrum.monkeytest.dao.impl;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.monkeytest.dao.VersionTypeDAO;
import com.spreadtrum.monkeytest.model.*;
import com.spreadtrum.util.HibernateUtil;

public class VersionTypeDAOImpl implements VersionTypeDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<VersionType> getVersionTypeInfo() {
		// TODO Auto-generated method stub
		String hql;
		List<VersionType> results = null;
		hql = "from VersionType";
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
		     
		return results!=null&&results.size()>0?(List<VersionType>)results:null;
	}


	@SuppressWarnings("unchecked")
	@Override
	public <T> T getVersionTypeProp(String prop, int verTypeID) {
		// TODO Auto-generated method stub
		String hql;
		T results = null;
		hql = "select "+prop+" from VersionType as ver where ver.id=" +verTypeID;
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtil().openSession();
		Transaction tx = session.beginTransaction();
		try{
		    //开启事务
		    results = (T)session.createQuery(hql).list();
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtil().closeSession(session);
		        } 
		     
		return results!=null?(T)results:null;
	}

}
