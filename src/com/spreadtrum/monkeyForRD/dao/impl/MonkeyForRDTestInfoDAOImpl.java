package com.spreadtrum.monkeyForRD.dao.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.monkeyForRD.dao.MonkeyForRDMemberInfoDAO;
import com.spreadtrum.monkeyForRD.dao.MonkeyForRDTestInfoDAO;
import com.spreadtrum.monkeyForRD.model.MonkeyForRDMemberInfo;
import com.spreadtrum.monkeyForRD.model.MonkeyForRDTestInfo;
import com.spreadtrum.monkeyForRD.util.HibernateUtilForMonkeyRD;

public class MonkeyForRDTestInfoDAOImpl implements MonkeyForRDTestInfoDAO {

	@Override
	public Date getLastDateBySite(String site) {
		String hql;
	    List<Date> results = new ArrayList<Date>();
		hql = "select ti.testDate from MonkeyForRDTestInfo as ti where ti.siteID.siteName='"+site +"' order by ti.id ";
		
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
		     
		return results!=null&&results.size()>0?results.get(0):null;		
	}

	@Override
	public List<MonkeyForRDTestInfo> getSiteTestInfoByDate(String site, Date date) {
		String hql;
	    List<MonkeyForRDTestInfo> results = new ArrayList<MonkeyForRDTestInfo>();
		hql = "from MonkeyForRDTestInfo as ti where ti.siteID.siteName='"+site +"' and ti.testDate='"+ date+"' order by ti.id ";
		
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
		     
		return results!=null&&results.size()>0?(List<MonkeyForRDTestInfo>)results:null;		
	}

	@Override
	public Integer getDoDaysForAllByDevice(String deviceName) {
		String hql;
	    Number results = 0;
		hql = "select count(*) from MonkeyForRDTestInfo as ti where ti.deviceName='"+deviceName +"' ";
		
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForMonkeyRD().openSession();
		Transaction tx = session.beginTransaction();
		try{
		    //开启事务
			results = (Number)session.createQuery(hql).list().get(0);

                    //返回全部的记录集       
    //遍历结果集
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtilForMonkeyRD().closeSession(session);
		        } 		
		     
		return results.intValue();		
	}

	@Override
	public Integer getDoCountThisDayByDevice(String deviceName, Date date) {
		String hql;
	    Number results = 0;
		hql = "select count(*) from MonkeyForRDTestInfo as ti where ti.deviceName='"+deviceName +"' and ti.testDate='"+date +"' ";
		
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForMonkeyRD().openSession();
		Transaction tx = session.beginTransaction();
		try{
		    //开启事务
			results = (Number)session.createQuery(hql).list().get(0);

                    //返回全部的记录集       
    //遍历结果集
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtilForMonkeyRD().closeSession(session);
		        } 		
		     
		return results.intValue();		
	}

	@Override
	public Integer getDoDaysForPeriodByDevice(Integer days, String deviceName) {
		Date date=new Date(new java.util.Date().getTime()); 
	    SimpleDateFormat dateFormate=new SimpleDateFormat("yyyy-MM-dd"); 
		List<Date> queryResults = null;
		long periodTime = 0l;
		periodTime = days * 24 * 60 * 60 * 1000l;
		List<Date> results = new ArrayList<Date>();
	    String sinceDate = dateFormate.format(new java.util.Date(date.getTime() - periodTime));
		String hql;
		hql = "select ti.testDate from MonkeyForRDTestInfo as ti where ti.deviceName='"+deviceName +"' and ti.testDate >'"+sinceDate +"' ";		
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
			for(Date str:queryResults){
				if (!results.contains(str)) {
					results.add(str);
				}				
			}			
		}		     
		return results!=null&&results.size()>0?results.size():0;		
	}	
}
