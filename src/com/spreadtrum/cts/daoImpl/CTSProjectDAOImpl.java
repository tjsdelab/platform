package com.spreadtrum.cts.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.cts.dao.CTSProjectDAO;
import com.spreadtrum.cts.util.CTSUtil;

public class CTSProjectDAOImpl implements CTSProjectDAO {

	@Override
	public List<String> getValidProject() {
		String hql;
		List <String> results = null;
		hql = "select project from CTSProject as ctsp where ctsp.validFlag = 'Y'";
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new CTSUtil().openSession();
		Transaction tx = session.beginTransaction();
		try{
		    //开启事务
		    results = session.createQuery(hql).list();
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new CTSUtil().closeSession(session);
		        } 
		     
		return results!=null&&results.size()>0?(List<String>)results:null;
	}

}
