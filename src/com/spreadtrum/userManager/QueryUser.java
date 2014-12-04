package com.spreadtrum.userManager;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

public class QueryUser {
	
	public List<UserBean> getAllUser() {
		String hql;
		List<UserBean> results = null;
		hql = "from UserBean as ub order by ub.id ";
		
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new UserHibernateUtil().openSession();
		Transaction tx = session.beginTransaction();
		try{
		    //开启事务
			results = session.createQuery(hql).list();

                    //返回全部的记录集       
    //遍历结果集
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new UserHibernateUtil().closeSession(session);
		        } 
		     
		return results!=null&&results.size()>0?(List<UserBean>)results:null;		
	}
//	public UserBean getUserByID() {
//		String hql;
//		List<UserBean> results = null;
//	
//	}
}
