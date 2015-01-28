package com.spreadtrum.mtbf.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.mtbf.dao.MTBFProjectInfoDAO;
import com.spreadtrum.mtbf.util.HibernateUtilForMTBF;

public class MTBFProjectInfoDAOImpl implements MTBFProjectInfoDAO{
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getValidProjectName() {
		String hql;
		List<String> results = null;
		hql = "select projectName from MTBFProjectInfo as pi where pi.projectFlag='yes' order by pi.id ";
		
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
		return results!=null&&results.size()>0?(List<String>)results:null;
	}

}