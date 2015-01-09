package com.spreadtrum.mtbf.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.mtbf.dao.MTBFTestInfoDAO;
import com.spreadtrum.mtbf.model.MTBFTestInfo;
import com.spreadtrum.mtbf.util.HibernateUtilForMTBF;

public class MTBFTestInfoDAOImpl implements MTBFTestInfoDAO {
	@SuppressWarnings("unchecked")
	@Override
	public List<MTBFTestInfo> getMTBFTestInfoByTableName(String formName) {
		String hql;
		List <MTBFTestInfo> results = null;
		hql = "from MTBFTestInfo as tif where tif.formID.formName = '"+formName+"'";
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForMTBF().openSession();
		Transaction tx = session.beginTransaction();
		try{
		    //开启事务
		    results = session.createQuery(hql).list();
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtilForMTBF().closeSession(session);
		        } 
		     
		return results!=null&&results.size()>0?(List<MTBFTestInfo>)results:null;
	}

}
