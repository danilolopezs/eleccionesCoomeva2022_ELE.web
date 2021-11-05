package co.com.coomeva.ele.entidades.sidco;

import org.hibernate.Session;

/**
 * Data access interface for domain model
 * @author Danilo L\00F3pez Sandoval
 */
public interface IBaseHibernateDAOSidco {
	public Session getSession();
}