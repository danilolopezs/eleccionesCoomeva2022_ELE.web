package co.com.coomeva.ele.entidades.planchas.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleZonaElectoralEspecial;

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

public class EleZonaElectoralEspecialDAO extends BaseHibernateDAO {
	
	private static final Log log = LogFactory.getLog(EleZonaElectoralEspecialDAO.class);
	
	// property constants
	public static final String DESCRIPCION_ZONA_ELE = "descripcionZonaEle";
	public static final String ZONA_ESPECIAL = "zonaEspecial";
	public static final String MAX_PRINCIPALES = "maxPrincipales";

	public void save(EleZonaElectoralEspecial transientInstance) {
		log.debug("saving EleZonaElectoralEspecial instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleZonaElectoralEspecial persistentInstance) {
		log.debug("deleting EleZonaElectoralEspecial instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleZonaElectoralEspecial findById(java.lang.String id) {
		log.debug("getting EleZonaElectoralEspecial instance with id: " + id);
		try {
			EleZonaElectoralEspecial instance = (EleZonaElectoralEspecial) getSession().get(
					"co.com.coomeva.ele.entidades.planchas.dosmildoce.EleZonaElectoralEspecial", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleZonaElectoralEspecial instance) {
		log.debug("finding EleZonaElectoralEspecial instance by example");
		try {
			List results = getSession()
					.createCriteria("co.com.elecdb.EleZonaElectoralEspecial")
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
		log.debug("finding EleZonaElectoralEspecial instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EleZonaElectoralEspecial as model where model."
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
		log.debug("finding all EleZonaElectoralEspecial instances");
		try {
			String queryString = "from EleZonaElectoralEspecial";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleZonaElectoralEspecial merge(EleZonaElectoralEspecial detachedInstance) {
		log.debug("merging EleZonaElectoralEspecial instance");
		try {
			EleZonaElectoralEspecial result = (EleZonaElectoralEspecial) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleZonaElectoralEspecial instance) {
		log.debug("attaching dirty EleZonaElectoralEspecial instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleZonaElectoralEspecial instance) {
		log.debug("attaching clean EleZonaElectoralEspecial instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}