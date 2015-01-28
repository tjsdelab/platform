package com.spreadtrum.login;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

public class QueryUser {
	
	public List<BackStageUserBean> getAllUser() {
		String hql;
		List<BackStageUserBean> results = null;
		hql = "from BackStageUserBean as ub order by ub.id ";
		
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new BackStageManagerUtil().openSession();
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
		        new BackStageManagerUtil().closeSession(session);
		        } 
		     
		return results!=null&&results.size()>0?(List<BackStageUserBean>)results:null;		
	}
	public String checkUser(String userName) {
		String hql;
		List<String> results = null;
		hql = "select bs.passWord from BackStageUserBean as bs where bs.userName= '"+ userName +"'";
		
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new BackStageManagerUtil().openSession();
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
		        new BackStageManagerUtil().closeSession(session);
		        } 
		     
		return results!=null&&results.size()>0?results.get(0):null;		
	}

}
