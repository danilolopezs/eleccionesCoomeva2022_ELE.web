package co.com.coomeva.ele.entidades.planchas.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleZona;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleZona entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see co.com.elecdb.EleZona
 * @author MyEclipse Persistence Tools
 */

public class EleZonaDAO extends BaseHibernateDAO {
	
	
	private static final Log log = LogFactory.getLog(EleZonaDAO.class);
	// property constants
	public static final String DESCRIPCION_ZONA = "descripcionZona";

	public void save(EleZona transientInstance) {
		log.debug("saving EleZona instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleZona persistentInstance) {
		log.debug("deleting EleZona instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleZona findById(java.lang.String id) {
		log.debug("getting EleZona instance with id: " + id);
		try {
			EleZona instance = (EleZona) getSession().get(
					"co.com.elecdb.EleZona", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleZona instance) {
		log.debug("finding EleZona instance by example");
		try {
			List results = getSession().createCriteria("co.com.elecdb.EleZona")
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
		log.debug("finding EleZona instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from EleZona as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDescripcionZona(Object descripcionZona) {
		return findByProperty(DESCRIPCION_ZONA, descripcionZona);
	}

	public List findAll() {
		log.debug("finding all EleZona instances");
		try {
			String queryString = "from EleZona";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleZona merge(EleZona detachedInstance) {
		log.debug("merging EleZona instance");
		try {
			EleZona result = (EleZona) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleZona instance) {
		log.debug("attaching dirty EleZona instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleZona instance) {
		log.debug("attaching clean EleZona instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}