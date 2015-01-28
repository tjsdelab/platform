package com.spreadtrum.monkeytest.dao.impl;

import java.sql.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.monkeytest.dao.TestFormDAO;
import com.spreadtrum.monkeytest.model.TestForm;
import com.spreadtrum.util.HibernateUtil;

public class TestFormDAOImpl implements TestFormDAO{

	@SuppressWarnings("unchecked")
	@Override
	public TestForm getTestFormInfo(String formName) {
		String hql;
		List<TestForm> results = null;
		hql = "from TestForm tf where tf.formName='"+ formName +"'";

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
		     
		return results!=null&&results.size()>0?(TestForm)results.get(0):null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getTestFormProp(String prop, String formName) {
		String hql;		
		T results = null;
		hql = "select "+prop+" from TestForm as tf where tf.formName='" +formName+"'";
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
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTestFormByProject(String project) {
		String hql;
		List<String> results = null;
		hql = "select formName from TestForm tf where tf.hardwareInfo = '"+project+"'";
		System.out.println(hql);
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
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTestFormByPacVersion(String pacVersion) {
		String hql;
		List<String>  results = null;
		hql = "select formName from TestForm tf where tf.pacVersion = '"+pacVersion+"'";
		System.out.println(hql);
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
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTestFormByDate(Date mdate) {
		String hql;
		List<String>  results = null;
		//String start = mdate + " 00:00:00";
		//String end = mdate + " 23:59:59";
		hql = "select formName from TestForm tf where tf.tdate = '"+mdate+"'";
		System.out.println(hql);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<String> searchTestFormByProject(String project) {
		String hql;
		List<String> results = null;
		hql = "select formName from TestForm tf where tf.hardwareInfo like '%"+project+"%'";
		System.out.println(hql);
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
	@SuppressWarnings("unchecked")
	@Override
	public List<String> searchTestFormByPacVersion(String pacVersion) {
		String hql;
		List<String> results = null;
		hql = "select formName from TestForm tf where tf.pacVersion like '%"+pacVersion+"%'";
		System.out.println(hql);
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

	@SuppressWarnings("unchecked")
	@Override
	public String getLastTestFormByProject(String project) {
		String hql;
		List<String> results = null;		
		hql = "select formName from TestForm tf where tf.hardwareInfo = '"+project+"' order by tf.tdate desc";

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

	@SuppressWarnings("unchecked")
	@Override
	public Date getLastTimeByProject(String project) {
		String hql;
		List<Date> results = null;		
		hql = "select tdate from TestForm tf  where tf.hardwareInfo = '"+project+"' order by tdate desc";
		System.out.println(hql);
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
		     
		return results!=null&&results.size()>0?(Date)results.get(0):null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TestForm> getMonkeyTestInfoByProject(String project) {
		String hql;
		List<TestForm> results = null;
		hql = "from TestForm as tf where tf.hardwareInfo = '"+project+"' order by tf.tdate desc";

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