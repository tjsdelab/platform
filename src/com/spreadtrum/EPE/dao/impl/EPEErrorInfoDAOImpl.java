package com.spreadtrum.EPE.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.EPE.dao.EPEErrorInfoDAO;
import com.spreadtrum.EPE.model.EPEErrorInfo;
import com.spreadtrum.EPE.util.HibernateUtilForEPE;


public class EPEErrorInfoDAOImpl implements EPEErrorInfoDAO {
	@SuppressWarnings("unchecked")
	@Override

	public List<EPEErrorInfo> getEPEErrorInfo(String formName){
		String hql;
		List<EPEErrorInfo> results = null;
		hql = "from EPEErrorInfo as eif where eif.formID.formName='"+formName +"'";
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
		return results!=null&&results.size()>0?(List<EPEErrorInfo>)results:null;
	}
}

