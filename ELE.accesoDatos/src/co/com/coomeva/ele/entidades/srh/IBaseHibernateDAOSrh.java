package co.com.coomeva.ele.entidades.srh;

import org.hibernate.Session;


/**
 * Data access interface for domain model
 * @author MyEclipse Persistence Tools
 */
public interface IBaseHibernateDAOSrh {
	public Session getSession();
}