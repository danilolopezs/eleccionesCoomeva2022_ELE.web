package co.com.coomeva.ele.entidades.lico;

import org.hibernate.Session;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAOLico implements IBaseHibernateDAOLico {
	
	public Session getSession() {
		return HibernateSessionFactoryLico.getSession();
	}
	
}