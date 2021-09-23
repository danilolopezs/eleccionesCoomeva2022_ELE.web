package co.com.coomeva.ele.entidades.planchas.dao;

import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleAcreditacionOficio;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleAcreditacionOficio entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.elecdb.EleAcreditacionOficio
 * @author MyEclipse Persistence Tools
 */

public class EleAcreditacionOficioDAO extends BaseHibernateDAO {
	
	private static final Log log = LogFactory.getLog(EleAcreditacionOficioDAO.class);
	
	// property constants
	public static final String DESCRIPCION_OFICIO = "descripcionOficio";

	public void save(EleAcreditacionOficio transientInstance) {
		log.debug("saving EleAcreditacionOficio instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleAcreditacionOficio persistentInstance) {
		log.debug("deleting EleAcreditacionOficio instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleAcreditacionOficio findById(java.lang.Long id) {
		log.debug("getting EleAcreditacionOficio instance with id: " + id);
		try {
			EleAcreditacionOficio instance = (EleAcreditacionOficio) getSession()
					.get("co.com.coomeva.ele.entidades.planchas.dosmildoce.EleAcreditacionOficio", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleAcreditacionOficio instance) {
		log.debug("finding EleAcreditacionOficio instance by example");
		try {
			List results = getSession()
					.createCriteria("co.com.coomeva.ele.entidades.planchas.dosmildoce.EleAcreditacionOficio")
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
		log.debug("finding EleAcreditacionOficio instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EleAcreditacionOficio as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDescripcionOficio(Object descripcionOficio) {
		return findByProperty(DESCRIPCION_OFICIO, descripcionOficio);
	}

	public List findAll() {
		log.debug("finding all EleAcreditacionOficio instances");
		try {
			String queryString = "from EleAcreditacionOficio";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleAcreditacionOficio merge(EleAcreditacionOficio detachedInstance) {
		log.debug("merging EleAcreditacionOficio instance");
		try {
			EleAcreditacionOficio result = (EleAcreditacionOficio) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleAcreditacionOficio instance) {
		log.debug("attaching dirty EleAcreditacionOficio instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleAcreditacionOficio instance) {
		log.debug("attaching clean EleAcreditacionOficio instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}