package com.spreadtrum.cts.daoImpl;

import java.sql.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.cts.dao.CTSTestInfoDAO;
import com.spreadtrum.cts.model.CTSForm;
import com.spreadtrum.cts.util.CTSUtil;

public class CTSTestInfoDAOImpl implements CTSTestInfoDAO {

	@Override
	public List<CTSForm> ctsTestInfoByProject(String project) {
		String hql;
		List<CTSForm> results = null;
		hql = "from CTSForm as ctsf where ctsf.projectID.project = '"+project+"' order by ctsf.testDate desc";
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
		     
		return results!=null&&results.size()>0?results:null;
	}

	@Override
	public List<CTSForm> searchByDate(Date sdate) {
		String hql;
		List<CTSForm>  results = null;
		hql = "from CTSForm ctsf where ctsf.testDate = '"+sdate+"'";
		System.out.println(hql);
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
		     
		return results!=null&&results.size()>0?results:null;
	}

	@Override
	public List<CTSForm> searchByProject(String project) {
		String hql;
		List<CTSForm>  results = null;
		hql = "from CTSForm ctsf where ctsf.projectID.project like '%"+project+"%'";
		System.out.println(hql);
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
		     
		return results!=null&&results.size()>0?results:null;
	}

	@Override
	public List<CTSForm> searchByVersion(String version) {
		String hql;
		List<CTSForm>  results = null;
		hql = "from CTSForm ctsf where ctsf.softwareVsn like '%"+version+"%'";
		System.out.println(hql);
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
		     
		return results!=null&&results.size()>0?results:null;
	}

}
