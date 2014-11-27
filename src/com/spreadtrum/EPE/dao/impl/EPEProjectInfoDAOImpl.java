package com.spreadtrum.EPE.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.EPE.dao.EPEProjectInfoDAO;
import com.spreadtrum.EPE.util.HibernateUtilForEPE;

public class EPEProjectInfoDAOImpl implements EPEProjectInfoDAO {
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getValidProjectName() {
		String hql;
		List<String> results = null;
		hql = "select projectName from EPEProjectInfo as pi where pi.projectFlag=1 order by pi.id ";
		
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
		return results!=null&&results.size()>0?(List<String>)results:null;
	}
}

