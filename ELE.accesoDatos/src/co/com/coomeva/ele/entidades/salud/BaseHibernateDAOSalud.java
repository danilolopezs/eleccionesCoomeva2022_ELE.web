package co.com.coomeva.ele.entidades.salud;

import org.hibernate.Session;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAOSalud implements IBaseHibernateDAOSalud {
	
	public Session getSession() {
		return HibernateSessionFactorySalud.getSession();
	}
	
}