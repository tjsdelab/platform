package com.spreadtrum.mtbf.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.mtbf.dao.MTBFCaseInfoDAO;
import com.spreadtrum.mtbf.model.MTBFCaseInfo;
import com.spreadtrum.mtbf.util.HibernateUtilForMTBF;

public class MTBFCaseInfoDAOImpl implements MTBFCaseInfoDAO {
	@SuppressWarnings("unchecked")
	@Override
	public List<MTBFCaseInfo> getMTBFCaseInfoByTableName( ) {
		
		String sql;
		List<MTBFCaseInfo> results = null;
		sql = "select * from MTBFCaseInfo";		
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForMTBF().openSession();
		Transaction tx = session.beginTransaction();
		try{
			results = session.createSQLQuery(sql).addEntity(MTBFCaseInfo.class).list();
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtilForMTBF().closeSession(session);
		        } 		     
		return results!=null&&results.size()>0?results:null;
	}

}
