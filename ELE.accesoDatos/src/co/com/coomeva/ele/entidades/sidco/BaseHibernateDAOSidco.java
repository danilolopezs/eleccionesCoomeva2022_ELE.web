package co.com.coomeva.ele.entidades.sidco;

import org.hibernate.Session;

/**
 * Data access object (DAO) for domain model
 * 
 * @author Danilo L\00F3pez Sandoval
 */
public class BaseHibernateDAOSidco implements IBaseHibernateDAOSidco {

	public Session getSession() {
		return HibernateSessionFactorySidco.getSession();
	}

}