package com.spreadtrum.mtbf.dao.impl;

import java.sql.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.mtbf.dao.MTBFFormDAO;
import com.spreadtrum.mtbf.model.MTBFForm;
import com.spreadtrum.mtbf.util.HibernateUtilForMTBF;

public class MTBFFormDAOImpl implements MTBFFormDAO{	
	@SuppressWarnings("unchecked")
	@Override
		public MTBFForm getMTBFLastTestByProject(String project) {
		String hql;
		List<MTBFForm> results = null;
		hql = "from MTBFForm as mfm where mfm.projectID.projectName = '"+ project+ "' order by mfm.testDate desc";
		//开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
				org.hibernate.Session session = new HibernateUtilForMTBF().openSession();
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
				        new HibernateUtilForMTBF().closeSession(session);
				        }      
				return results!=null&&results.size()>0?(MTBFForm)results.get(0):null;
			}
	
	@SuppressWarnings("unchecked")
	@Override
	public MTBFForm getMTBFFormTestByTableName(String tableName) {
		String hql;
		List<MTBFForm> results = null;
		hql = "from MTBFForm as mfm where mfm.formName='"+ tableName +"' ";
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForMTBF().openSession();
		Transaction tx = session.beginTransaction();
		try{
			results = session.createQuery(hql).list();
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtilForMTBF().closeSession(session);
		        } 		     
		return results!=null&&results.size()>0?(MTBFForm)results.get(0):null;	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MTBFForm> searchByProject(String project) {
		String hql;
		List<MTBFForm> results = null;
		hql = "from MTBFForm as mfm where mfm.projectID.projectName like '%"+project+"%' order by mfm.testDate desc" ;
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForMTBF().openSession();
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
		        new HibernateUtilForMTBF().closeSession(session);
		        } 		     
		return results!=null&&results.size()>0?(List<MTBFForm>)results:null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MTBFForm> searchByVersion(String version) {
		String hql;
		List<MTBFForm> results = null;
		hql = "from MTBFForm as mfm where mfm.softwareVsn like '%"+version+"%' order by mfm.testDate desc" ;
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForMTBF().openSession();
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
		        new HibernateUtilForMTBF().closeSession(session);
		        } 		     
		return results!=null&&results.size()>0?(List<MTBFForm>)results:null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MTBFForm> searchByDate(Date sdate) {
		String hql;
		List<MTBFForm> results = null;
		hql = "from MTBFForm as mfm where mfm.testDate = '"+ sdate +"'" ;
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForMTBF().openSession();
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
		        new HibernateUtilForMTBF().closeSession(session);
		        }     
		return results!=null&&results.size()>0?(List<MTBFForm>)results:null;
	}

}
