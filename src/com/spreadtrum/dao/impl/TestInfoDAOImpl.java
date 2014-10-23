package com.spreadtrum.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.dao.*;
import com.spreadtrum.model.*;
import com.spreadtrum.util.HibernateUtil;
 
public class TestInfoDAOImpl implements TestInfoDAO {
	@Override
	public void saveTestInfo(TestInfo testInfo) {
		//开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtil().openSession();
		Transaction tx = session.beginTransaction();
		//开启事务
		try {
			//插入
			session.save(testInfo);
			tx.commit();
		} catch (Exception ex) {
			if (null != tx) {
				tx.rollback(); //失败回滚
			}
		} finally {
			//关闭session
			new HibernateUtil().closeSession(session);
		}
	}
	
	
	//查找最新的testInfo
	@SuppressWarnings("unchecked")
	public List<TestInfo> showLastTestInfo() {
		List<TestInfo> list = null; 
       //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
	   org.hibernate.Session session = new HibernateUtil().openSession();
	   Transaction tx = session.beginTransaction();
     try{
    	   //开启事务
           //Query query = session.createQuery("from TestInfo as t  where t.test_info_id = (select max(t.test_info_id) from t)").list();
           //list = query.list();
    	   list=session.createQuery("from TestInfo as t  where t.test_info_id > ((select max(t.test_info_id) from t)-5)").list();
           tx.commit();
     } catch (HibernateException e) { //捕捉异常
        e.printStackTrace();
        tx.rollback();
     } finally {
    	 new HibernateUtil().closeSession(session);
     } 
     //List<TestInfo> last = (TestInfo)list.get(0);
     return (List<TestInfo>)list;
  }
	
}

