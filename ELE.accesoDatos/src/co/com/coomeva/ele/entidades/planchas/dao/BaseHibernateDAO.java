package co.com.coomeva.ele.entidades.planchas.dao;

import org.hibernate.Session;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	public Session getSession() {
		return HibernateSessionFactoryElecciones2012.getSession();
	}
	
}