package com.spreadtrum.backStageManager;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

public class AddUser {
	
	public boolean addUser(BackStageUserBean user) {
		//String hql;
		//hql = "insert into UserBean insert into UserBean (passWord,type,userName) values ('"+userName +"','"+passWard +"','"+type+"'"+")" ;
		
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new BackStageManagerUtil().openSession();
		Transaction tx = session.beginTransaction();
		try{
		    //开启事务
			session.save(user);

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
