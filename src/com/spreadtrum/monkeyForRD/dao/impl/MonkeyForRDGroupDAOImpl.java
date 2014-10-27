package com.spreadtrum.monkeyForRD.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.monkeyForRD.dao.MonkeyForRDGroupDAO;
import com.spreadtrum.monkeyForRD.util.HibernateUtilForMonkeyRD;

public class MonkeyForRDGroupDAOImpl implements MonkeyForRDGroupDAO {

	@Override
	public List<String> getAllValidGroupName() {
		String hql;
		List<String> results = null;
		hql = "select mfrg.groupName from MonkeyForRDGroup as mfrg where mfrg.validFlag=1 order by mfrg.id ";
		
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForMonkeyRD().openSession();
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
		        new HibernateUtilForMonkeyRD().closeSession(session);
		        } 

		     
		return results!=null&&results.size()>0?(List<String>)results:null;		
	}
	
}
