package co.com.coomeva.ele.entidades.planchas;

import org.hibernate.Session;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAOPlanchas implements IBaseHibernateDAOPlanchas {
	
	public Session getSession() {
		return HibernateSessionFactoryPlanchas.getSession();
	}
	
	public Session getSession2012() {
		return HibernateSessionFactoryElecciones2012.getSession();
	}
	
}