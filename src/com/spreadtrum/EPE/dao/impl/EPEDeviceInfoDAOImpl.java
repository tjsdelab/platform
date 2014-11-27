package com.spreadtrum.EPE.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.EPE.dao.EPEDeviceInfoDAO;
import com.spreadtrum.EPE.model.EPEDeviceInfo;
import com.spreadtrum.EPE.util.HibernateUtilForEPE;


public class EPEDeviceInfoDAOImpl implements EPEDeviceInfoDAO {
	@SuppressWarnings("unchecked")
	@Override
	public List<EPEDeviceInfo> getEPEDeviceInfo(String formName) {
		String hql;
		List <EPEDeviceInfo> results = null;
		hql = "from EPEDeviceInfo as dif where dif.formID.formName = '"+formName+"'";
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForEPE().openSession();
		Transaction tx = session.beginTransaction();
		try{
		    //开启事务
		    results = session.createQuery(hql).list();
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtilForEPE().closeSession(session);
		        } 
		     
		return results!=null&&results.size()>0?(List<EPEDeviceInfo>)results:null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EPEDeviceInfo getEPEDeviceInfoByTableName(String formName) {
		String hql;
		List <EPEDeviceInfo> results = null;
		hql = "from EPEDeviceInfo as dif where dif.formID.formName = '"+formName+"'";
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForEPE().openSession();
		Transaction tx = session.beginTransaction();
		try{
		    //开启事务
		    results = session.createQuery(hql).list();
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtilForEPE().closeSession(session);
		        } 
		     
		return results!=null&&results.size()>0?(EPEDeviceInfo)results:null;
	}
}
