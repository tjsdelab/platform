package com.spreadtrum.sanity_smoke.dao.impl;

import java.sql.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.sanity_smoke.dao.SmokeTestFormDAO;
import com.spreadtrum.sanity_smoke.model.SmokeTestForm;
import com.spreadtrum.sanity_smoke.model.SmokeTestInfo;
import com.spreadtrum.sanity_smoke.util.HibernateUtilForSS;
//import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
//import org.springframework.orm.hibernate3.HibernateCallback;

public class SmokeTestFormDAOImpl implements SmokeTestFormDAO {
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getSmokeLastPropByProject(String prop, String project) {
		String hql;
		List<String> results = null;
		hql = "select stf."+prop+" from SmokeTestForm as stf where stf.projectID.projectName = '"+ project +"' order by stf.testDate desc" ;
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

		     
		return results!=null&&results.size()>0?(T)results.get(0):null;
	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SmokeTestForm getSmokeLastInfoByProject(String project) {
		String hql;
		List<SmokeTestForm> results = null;
		hql = "from SmokeTestForm as stf where stf.projectID.projectName = '"+ project +"'order by stf.testDate desc" ;
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

		     
		return results!=null&&results.size()>0?(SmokeTestForm)results.get(0):null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getSmokeTestPropByTableName(String prop, String tableName) {
		String sql;
		List<T> results = null;
		sql = "select "+ prop +" from "+ tableName +" ";
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForSS().openSession();
		Transaction tx = session.beginTransaction();
		try{
			results = session.createSQLQuery(sql).list();
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtilForSS().closeSession(session);
		        } 		     
		return results!=null&&results.size()>0?(T)results:null;	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SmokeTestInfo> getSmokeTestInfoByTableName(String tableName) {
		
		String sql;
		List<SmokeTestInfo> results = null;
		sql = "select * from "+ tableName +"";		
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForSS().openSession();
		Transaction tx = session.beginTransaction();
		try{
			results = session.createSQLQuery(sql).addEntity(SmokeTestInfo.class).list();
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtilForSS().closeSession(session);
		        } 		     
		return results!=null&&results.size()>0?results:null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SmokeTestForm> searchSmokeTableNameByProject(String project) {
		String hql;
		List<SmokeTestForm> results = null;
		hql = "from SmokeTestForm as stf where stf.projectID.projectName like '%"+project+"%' order by stf.testDate desc" ;
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

		     
		return results!=null&&results.size()>0?(List<SmokeTestForm>)results:null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SmokeTestForm> searchSmokeTableNameByVersion(String version) {
		String hql;
		List<SmokeTestForm> results = null;
		hql = "from SmokeTestForm as stf where stf.versionForNum like '%"+version+"%' order by stf.testDate desc" ;
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

		     
		return results!=null&&results.size()>0?(List<SmokeTestForm>)results:null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SmokeTestForm> searchSmokeTableNameByDate(Date sdate) {
		String hql;
		List<SmokeTestForm> results = null;
		hql = "from SmokeTestForm as stf where stf.testDate = '"+ sdate +"'" ;
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

		     
		return results!=null&&results.size()>0?(List<SmokeTestForm>)results:null;
	}

	@Override
	public <T> T getSmokeFormPropByTableName(String prop, String tableName) {
		String hql;
		List<T> results = null;
		hql = "select stf."+ prop +" from SmokeTestForm as stf where stf.testFormName='"+ tableName +"' ";
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForSS().openSession();
		Transaction tx = session.beginTransaction();
		try{
			results = session.createQuery(hql).list();
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtilForSS().closeSession(session);
		        } 		     
		return results!=null&&results.size()>0?(T)results.get(0):null;	
	}

	@Override
	public SmokeTestForm getSmokeFormByTableName(String tableName) {
		String hql;
		List<SmokeTestForm> results = null;
		hql = "from SmokeTestForm as stf where stf.testFormName='"+ tableName +"' ";
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForSS().openSession();
		Transaction tx = session.beginTransaction();
		try{
			results = session.createQuery(hql).list();
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtilForSS().closeSession(session);
		        } 		     
		return results!=null&&results.size()>0?(SmokeTestForm)results.get(0):null;	
	}
}
