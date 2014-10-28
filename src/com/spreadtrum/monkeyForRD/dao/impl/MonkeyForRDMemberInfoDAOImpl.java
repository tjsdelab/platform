package com.spreadtrum.monkeyForRD.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.monkeyForRD.dao.MonkeyForRDMemberInfoDAO;
import com.spreadtrum.monkeyForRD.model.MonkeyForRDMemberInfo;
import com.spreadtrum.monkeyForRD.util.HibernateUtilForMonkeyRD;

public class MonkeyForRDMemberInfoDAOImpl implements MonkeyForRDMemberInfoDAO {


	@Override
	public List<String> getGroupNameBySite(String site) {
		String hql;
		List<String> queryResults = null;
		List<String> results = new ArrayList<String>();
		hql = "select mi.groupID.groupName from MonkeyForRDMemberInfo as mi where mi.siteID.siteName='"+site +"' order by mi.id ";
		
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForMonkeyRD().openSession();
		Transaction tx = session.beginTransaction();
		try{
		    //开启事务
			queryResults = session.createQuery(hql).list();

                    //返回全部的记录集       
    //遍历结果集
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtilForMonkeyRD().closeSession(session);
		        } 
		
		if (null !=queryResults){			
			for(String str:queryResults){
				if (!results.contains(str)) {
					results.add(str);
				}				
			}			
		}		     
		return results!=null&&results.size()>0?(List<String>)results:null;		
	}

	@Override
	public List<String> getAllDeviceNameBySite(String site) {
		String hql;
	    List<String> results = new ArrayList<String>();
		hql = "select mi.deviceName from MonkeyForRDMemberInfo as mi where mi.siteID.siteName='"+site +"' order by mi.id ";
		
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForMonkeyRD().openSession();
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
		        new HibernateUtilForMonkeyRD().closeSession(session);
		        } 		
		     
		return results!=null&&results.size()>0?(List<String>)results:null;		
	}

	@Override
	public List<String> getGroupDeviceName(String site, String group) {
		String hql;
	    List<String> results = new ArrayList<String>();
		hql = "select mi.deviceName from MonkeyForRDMemberInfo as mi where mi.siteID.siteName='"+site +"' and mi.groupID.groupName='"+group +"' order by mi.id ";
		
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForMonkeyRD().openSession();
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
		        new HibernateUtilForMonkeyRD().closeSession(session);
		        } 		
		     
		return results!=null&&results.size()>0?(List<String>)results:null;		
	}

	@Override
	public List<MonkeyForRDMemberInfo> getAllPerPropByDevice(String deviceName) {
		String hql;
	    List<MonkeyForRDMemberInfo> results = new ArrayList<MonkeyForRDMemberInfo>();
		hql = "from MonkeyForRDMemberInfo as mi where mi.deviceName='"+deviceName +"' order by mi.id ";
		
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForMonkeyRD().openSession();
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
		        new HibernateUtilForMonkeyRD().closeSession(session);
		        } 		
		     
		return results!=null&&results.size()>0?(List<MonkeyForRDMemberInfo>)results:null;		
	}

	@Override
	public List<?> getPerPropByDevice(String prop, String deviceName) {
		String hql;
	    List<?> results = null;
		hql = "select mi."+prop +" from MonkeyForRDMemberInfo as mi where mi.deviceName='"+deviceName +"' order by mi.id ";
		
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForMonkeyRD().openSession();
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
		        new HibernateUtilForMonkeyRD().closeSession(session);
		        } 		
		     
		return results!=null&&results.size()>0?(List<?>)results:null;		
	}	
}
