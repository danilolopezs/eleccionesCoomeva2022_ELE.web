package co.com.coomeva.ele.entidades.admhabilidad;

import org.hibernate.Session;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAOHab implements IBaseHibernateDAOHab {
	
	public Session getSession() {
		return HibernateSessionFactoryHab.getSession();
	}
	
}