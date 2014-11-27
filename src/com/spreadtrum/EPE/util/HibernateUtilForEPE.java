package com.spreadtrum.EPE.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


@SuppressWarnings("deprecation")
public class HibernateUtilForEPE {
    private static final SessionFactory sessionFactory;
    
    static {
        try {
            sessionFactory = new Configuration().configure("EPE.cfg.xml").buildSessionFactory();  
            System.out.println("**********************************************************************");
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}
	public Session openSession()
	{
		Session session=sessionFactory.openSession();
		return session;
	}
	public  void closeSession(Session session)
	{
		if(session!=null)
		{
			session.close();
		}
	}
}