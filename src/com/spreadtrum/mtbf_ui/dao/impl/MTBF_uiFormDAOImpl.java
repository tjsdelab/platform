package com.spreadtrum.mtbf_ui.dao.impl;

import java.sql.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import com.spreadtrum.mtbf_ui.dao.MTBF_uiFormDAO;
import com.spreadtrum.mtbf_ui.model.MTBF_uiForm;
import com.spreadtrum.mtbf_ui.util.HibernateUtilForMTBF_ui;


public class MTBF_uiFormDAOImpl implements MTBF_uiFormDAO {
	@SuppressWarnings("unchecked")
	@Override
		public MTBF_uiForm getMTBF_uiLastTestByProject(String project) {
		String hql;
		List<MTBF_uiForm> results = null;
		hql = "from MTBF_uiForm as mfu where mfu.projectID.projectName = '"+ project+ "' order by mfu.testDate desc";
		//开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
				org.hibernate.Session session = new HibernateUtilForMTBF_ui().openSession();
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
				        new HibernateUtilForMTBF_ui().closeSession(session);
				        }      
				return results!=null&&results.size()>0?(MTBF_uiForm)results.get(0):null;
			}
	
	@SuppressWarnings("unchecked")
	@Override
	public MTBF_uiForm getMTBF_uiFormTestByTableName(String tableName) {
		String hql;
		List<MTBF_uiForm> results = null;
		hql = "from MTBF_uiForm as mfu where mfu.formName='"+ tableName +"' ";
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForMTBF_ui().openSession();
		Transaction tx = session.beginTransaction();
		try{
			results = session.createQuery(hql).list();
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtilForMTBF_ui().closeSession(session);
		        } 		     
		return results!=null&&results.size()>0?(MTBF_uiForm)results.get(0):null;	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MTBF_uiForm> searchByProject(String project) {
		String hql;
		List<MTBF_uiForm> results = null;
		hql = "from MTBF_uiForm as mfu where mfu.projectID.projectName like '%"+project+"%' order by mfu.testDate desc" ;
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForMTBF_ui().openSession();
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
		        new HibernateUtilForMTBF_ui().closeSession(session);
		        } 		     
		return results!=null&&results.size()>0?(List<MTBF_uiForm>)results:null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MTBF_uiForm> searchByVersion(String version) {
		String hql;
		List<MTBF_uiForm> results = null;
		hql = "from MTBF_uiForm as mfu where mfu.softwareVsn like '%"+version+"%' order by mfu.testDate desc" ;
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForMTBF_ui().openSession();
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
		        new HibernateUtilForMTBF_ui().closeSession(session);
		        } 		     
		return results!=null&&results.size()>0?(List<MTBF_uiForm>)results:null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MTBF_uiForm> searchByDate(Date sdate) {
		String hql;
		List<MTBF_uiForm> results = null;
		hql = "from MTBF_uiForm as mfu where mfu.testDate = '"+ sdate +"'" ;
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForMTBF_ui().openSession();
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
		        new HibernateUtilForMTBF_ui().closeSession(session);
		        }     
		return results!=null&&results.size()>0?(List<MTBF_uiForm>)results:null;
	}

}
