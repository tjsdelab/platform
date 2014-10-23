package com.spreadtrum.monkeytest.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.monkeytest.dao.ProjectFormDAO;
import com.spreadtrum.util.HibernateUtil;

public class ProjectFormDAOImpl implements ProjectFormDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getValidProjectName() {
		String hql;
		List<String> results = null;
		hql = "select projectName from ProjectForm as pf where pf.flag=1 order by pf.id ";
		
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

		     
		return results!=null&&results.size()>0?(List<String>)results:null;
	}

}
