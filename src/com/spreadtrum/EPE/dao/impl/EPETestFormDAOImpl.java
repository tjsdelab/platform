package com.spreadtrum.EPE.dao.impl;

import java.util.List;
import java.util.TreeMap;
import java.sql.Date;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.spreadtrum.EPE.dao.EPETestFormDAO;
import com.spreadtrum.EPE.model.EPETestForm;
import com.spreadtrum.EPE.util.HibernateUtilForEPE;

public class EPETestFormDAOImpl implements EPETestFormDAO {
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getEPELastPropByProject(String prop,String project) {
		String hql;
		List<String> results=null;
		hql = "select etf."+ prop +"from EPETestForm as etf where etf.projectID.projectName = '"+ project +"' order by etf.testDate desc";
		//开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
				org.hibernate.Session session = new HibernateUtilForEPE().openSession();
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
				        new HibernateUtilForEPE().closeSession(session);
				        } 

				     
				return results!=null&&results.size()>0?(T)results.get(0):null;
			
			}
	
	@SuppressWarnings("unchecked")
	@Override
		public EPETestForm getEPELastTestByProject(String project) {
		String hql;
		List<EPETestForm> results = null;
		hql = "from EPETestForm as etf where etf.projectID.projectName = '"+ project+ "' order by etf.testDate desc";
		//开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
				org.hibernate.Session session = new HibernateUtilForEPE().openSession();
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
				        new HibernateUtilForEPE().closeSession(session);
				        } 

				     
				return results!=null&&results.size()>0?(EPETestForm)results.get(0):null;
			}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getEPEFormPropByTableName(String prop,String tableName) {
		String hql;
		List<T> results = null;
		hql = "select etf."+ prop +" from EPETestForm as etf where etf.formName='"+tableName+"'";
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForEPE().openSession();
		Transaction tx = session.beginTransaction();
		try{
			results = session.createQuery(hql).list();
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtilForEPE().closeSession(session);
		        } 		     
		return results!=null&&results.size()>0?(T)results.get(0):null;	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EPETestForm getEPEFormTestByTableName(String tableName) {
		String hql;
		List<EPETestForm> results = null;
		hql = "from EPETestForm as etf where etf.formName='"+ tableName +"' ";
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForEPE().openSession();
		Transaction tx = session.beginTransaction();
		try{
			results = session.createQuery(hql).list();
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtilForEPE().closeSession(session);
		        } 		     
		return results!=null&&results.size()>0?(EPETestForm)results.get(0):null;	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EPETestForm> searchEPETableNameByProject(String project) {
		String hql;
		List<EPETestForm> results = null;
		hql = "from EPETestForm as etf where etf.projectID.projectName like '%"+project+"%' order by etf.testDate desc" ;
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForEPE().openSession();
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
		        new HibernateUtilForEPE().closeSession(session);
		        } 

		     
		return results!=null&&results.size()>0?(List<EPETestForm>)results:null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EPETestForm> searchEPETableNameByVersion(String version) {
		String hql;
		List<EPETestForm> results = null;
		hql = "from EPETestForm as etf where etf.version like '%"+version+"%' order by etf.testDate desc" ;
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForEPE().openSession();
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
		        new HibernateUtilForEPE().closeSession(session);
		        } 

		     
		return results!=null&&results.size()>0?(List<EPETestForm>)results:null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EPETestForm> searchEPETableNameByDate(Date sdate) {
		String hql;
		List<EPETestForm> results = null;
		hql = "from EPETestForm as etf where etf.testDate = '"+ sdate +"'" ;
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForEPE().openSession();
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
		        new HibernateUtilForEPE().closeSession(session);
		        } 

		     
		return results!=null&&results.size()>0?(List<EPETestForm>)results:null;
	}

	@Override
	public TreeMap<String,Float> getEPEByProjectVersion(String project, String version) {
		String hql;
		List<EPETestForm> results = null;
		TreeMap<String,Float> resultsMap = new TreeMap<String,Float>();
		hql = "from EPETestForm as etf where etf.projectID.projectName='"+ project +"' order by etf.testDate desc ";
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForEPE().openSession();
		Transaction tx = session.beginTransaction();
		try{
			results = session.createQuery(hql).list();
		    tx.commit();
		} catch (HibernateException e) { //捕捉异常
		    e.printStackTrace();
		    tx.rollback();
		    } finally {
		        new HibernateUtilForEPE().closeSession(session);
		        } 
		if(null != results){
			for (int i=0;i<results.size();i++) {				
				if (results.get(i).getVersion().equals(version)){					
					break;					
				} else {
					results.remove(i);
					i = i -1;
				}
			}			
		}
		if(null != results){			
			if (results.size() >= 7){
			    for (int i=7;i<results.size();i++){
				    results.remove(i);
				    i = i -1;
			    }
			}
			for (int i=0;i<results.size();i++){
				resultsMap.put(results.get(i).getVersion(),results.get(i).getEpeValue());
			}
			
		}
		return resultsMap!=null?(TreeMap)resultsMap:null;
	}

	@Override
	public String getTestFormByProjectVersion(String project, String version) {
		String hql;
		List<String> results = null;
		hql = "select etf.formName from EPETestForm as etf where etf.projectID.projectName='"+ project +"' and etf.version='"+ version +"' " ;
	    //开启session,与HttpSession完全没有任何关系，相当于一个数据库连接对象
		org.hibernate.Session session = new HibernateUtilForEPE().openSession();
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
		        new HibernateUtilForEPE().closeSession(session);
		        } 
  
		return results!=null&&results.size()>0?results.get(0):null;
	}
}
	
	
