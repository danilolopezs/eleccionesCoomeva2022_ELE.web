package co.com.coomeva.ele.entidades.srh;

import org.hibernate.Session;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAOSrh implements IBaseHibernateDAOSrh {
	
	public Session getSession() {
		return HibernateSessionFactorySrh.getSession();
	}
	
}