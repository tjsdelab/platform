package com.spreadtrum.mtbf_ui.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.mtbf_ui.dao.MTBF_uiCaseInfoDAO;
import com.spreadtrum.mtbf_ui.model.MTBF_uiCaseInfo;
import com.spreadtrum.mtbf_ui.util.HibernateUtilForMTBF_ui;


public class MTBF_uiCaseInfoDAOImpl implements MTBF_uiCaseInfoDAO {
	@SuppressWarnings("unchecked")
	@Override
	public List<MTBF_uiCaseInfo> getMTBF_uiCaseInfoByTableName( ) {
		
		String sql;
		List<MTBF_uiCaseInfo> results = null;
		sql = "select * from MTBF_uiCaseInfo";		
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForMTBF_ui().openSession();
		Transaction tx = session.beginTransaction();
		try{
			results = session.createSQLQuery(sql).addEntity(MTBF_uiCaseInfo.class).list();
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtilForMTBF_ui().closeSession(session);
		        } 		     
		return results!=null&&results.size()>0?results:null;
	}


}
