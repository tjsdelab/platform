package com.spreadtrum.backstageManager;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

public class DeleteUser {
	
	public boolean deleteUser(int id) {
		String hql;
		hql = "delete UserBean as ub where ub.id=?" ;
		
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new BackStageManagerUtil().openSession();
		Transaction tx = session.beginTransaction();
		try{
		    //开启事务
			session.createQuery(hql).setInteger(0, id).executeUpdate();

                    //返回全部的记录集       
    //遍历结果集
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    return false;
		    } finally {
		        new BackStageManagerUtil().closeSession(session);
		        } 
		     
		return true;		
	}
}
