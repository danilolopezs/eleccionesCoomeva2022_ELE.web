package co.com.coomeva.ele.entidades.sie;

import org.hibernate.Session;


/**
 * Data access interface for domain model
 * @author MyEclipse Persistence Tools
 */
public interface IBaseHibernateDAOSie {
	public Session getSession();
}