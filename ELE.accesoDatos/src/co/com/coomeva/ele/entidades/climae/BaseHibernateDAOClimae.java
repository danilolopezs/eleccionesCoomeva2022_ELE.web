package co.com.coomeva.ele.entidades.climae;

import org.hibernate.Session;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAOClimae implements IBaseHibernateDAOClimae {
	
	public Session getSession() {
		return HibernateSessionFactoryClimae.getSession();
	}
	
}