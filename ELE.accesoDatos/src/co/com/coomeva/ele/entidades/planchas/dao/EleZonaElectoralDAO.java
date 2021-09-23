package co.com.coomeva.ele.entidades.planchas.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleZonaElectoral;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleZonaElectoral entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.elecdb.EleZonaElectoral
 * @author MyEclipse Persistence Tools
 */

public class EleZonaElectoralDAO extends BaseHibernateDAO {
	
	private static final Log log = LogFactory.getLog(EleZonaElectoralDAO.class);
	
	// property constants
	public static final String DESCRIPCION_ZONA_ELE = "descripcionZonaEle";
	public static final String ZONA_ESPECIAL = "zonaEspecial";
	public static final String MAX_PRINCIPALES = "maxPrincipales";

	public void save(EleZonaElectoral transientInstance) {
		log.debug("saving EleZonaElectoral instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleZonaElectoral persistentInstance) {
		log.debug("deleting EleZonaElectoral instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleZonaElectoral findById(java.lang.String id) {
		log.debug("getting EleZonaElectoral instance with id: " + id);
		try {
			EleZonaElectoral instance = (EleZonaElectoral) getSession().get(
					"co.com.coomeva.ele.entidades.planchas.dosmildoce.EleZonaElectoral", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleZonaElectoral instance) {
		log.debug("finding EleZonaElectoral instance by example");
		try {
			List results = getSession()
					.createCriteria("co.com.elecdb.EleZonaElectoral")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding EleZonaElectoral instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EleZonaElectoral as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDescripcionZonaEle(Object descripcionZonaEle) {
		return findByProperty(DESCRIPCION_ZONA_ELE, descripcionZonaEle);
	}

	public List findByZonaEspecial(Object zonaEspecial) {
		return findByProperty(ZONA_ESPECIAL, zonaEspecial);
	}

	public List findByMaxPrincipales(Object maxPrincipales) {
		return findByProperty(MAX_PRINCIPALES, maxPrincipales);
	}

	public List findAll() {
		log.debug("finding all EleZonaElectoral instances");
		try {
			String queryString = "from EleZonaElectoral";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleZonaElectoral merge(EleZonaElectoral detachedInstance) {
		log.debug("merging EleZonaElectoral instance");
		try {
			EleZonaElectoral result = (EleZonaElectoral) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleZonaElectoral instance) {
		log.debug("attaching dirty EleZonaElectoral instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleZonaElectoral instance) {
		log.debug("attaching clean EleZonaElectoral instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}