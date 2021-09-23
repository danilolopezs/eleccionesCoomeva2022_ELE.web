package co.com.coomeva.ele.entidades.salud;

import org.hibernate.Session;


/**
 * Data access interface for domain model
 * @author MyEclipse Persistence Tools
 */
public interface IBaseHibernateDAOSalud {
	public Session getSession();
}