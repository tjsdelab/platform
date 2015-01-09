package com.spreadtrum.mtbf_ui.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.mtbf_ui.dao.MTBF_uiErrorInfoDAO;
import com.spreadtrum.mtbf_ui.model.MTBF_uiErrorInfo;
import com.spreadtrum.mtbf_ui.util.HibernateUtilForMTBF_ui;

public class MTBF_uiErrorInfoDAOImpl implements MTBF_uiErrorInfoDAO {
	@SuppressWarnings("unchecked")
	@Override
	public List<MTBF_uiErrorInfo> getMTBF_uiErrorInfoByTableName(String formName) {
		String hql;
		List <MTBF_uiErrorInfo> results = null;
		hql = "from MTBF_uiErrorInfo as eif where eif.formID.formName = '"+formName+"'";
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForMTBF_ui().openSession();
		Transaction tx = session.beginTransaction();
		try{
		    //开启事务
		    results = session.createQuery(hql).list();
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtilForMTBF_ui().closeSession(session);
		        } 
		     
		return results!=null&&results.size()>0?(List<MTBF_uiErrorInfo>)results:null;
	}

}
