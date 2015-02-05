package com.spreadtrum.backstageManager;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

public class EditUser {
	public boolean updateUser(int id,String userName,String passWord, String type) {

		
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new BackStageManagerUtil().openSession();
		Transaction tx = session.beginTransaction();
		BackStageUserBean u = (BackStageUserBean)session.get(BackStageUserBean.class, id);
		System.out.println(id + userName + passWord);
		u.setUserName(userName);
		u.setPassWord(passWord);
		u.setType(type);
		try{
		    //开启事务
			session.update(u);

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
