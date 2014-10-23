package com.spreadtrum.monkeytest.dao.impl;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.monkeytest.dao.DeviceInfoDAO;
import com.spreadtrum.monkeytest.model.*;
import com.spreadtrum.util.HibernateUtil;
public class DeviceInfoDAOImpl implements DeviceInfoDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<DeviceInfo> getDeviceInfo() {
		String hql;
		List <DeviceInfo> results = null;
		hql = "from DeviceInfo";
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
		     
		return results!=null&&results.size()>0?(List<DeviceInfo>)results:null;
	}

	@SuppressWarnings("unchecked")
	@Override	
	public String getDeviceInfoProp(String prop, int deviceID) {
		String hql;
		List<String> results = null;
		hql = "select "+prop+" from DeviceInfo as DI where DI.id=" +deviceID;
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
