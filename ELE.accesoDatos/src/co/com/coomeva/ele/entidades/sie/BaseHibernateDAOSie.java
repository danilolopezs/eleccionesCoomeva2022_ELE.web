package co.com.coomeva.ele.entidades.sie;

import org.hibernate.Session;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAOSie implements IBaseHibernateDAOSie {
	
	public Session getSession() {
		return HibernateSessionFactorySie.getSession();
	}
	
}