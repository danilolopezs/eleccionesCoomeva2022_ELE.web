package co.com.coomeva.ele.entidades.planchas.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFormatoPlancha;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleFormatoPlancha entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.elecdb.EleFormatoPlancha
 * @author MyEclipse Persistence Tools
 */

public class EleFormatoPlanchaDAO extends BaseHibernateDAO {

	private static final Log log = LogFactory.getLog(EleFormatoPlanchaDAO.class);
	
	// property constants
	public static final String USUARIO_GENERA = "usuarioGenera";
	public static final String USUARIO_IMPRESION = "usuarioImpresion";

	public void save(EleFormatoPlancha transientInstance) {
		log.debug("saving EleFormatoPlancha instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleFormatoPlancha persistentInstance) {
		log.debug("deleting EleFormatoPlancha instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleFormatoPlancha findById(java.lang.Long id) {
		log.debug("getting EleFormatoPlancha instance with id: " + id);
		try {
			EleFormatoPlancha instance = (EleFormatoPlancha) getSession().get(
					"co.com.elecdb.EleFormatoPlancha", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleFormatoPlancha instance) {
		log.debug("finding EleFormatoPlancha instance by example");
		try {
			List results = getSession()
					.createCriteria("co.com.elecdb.EleFormatoPlancha")
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
		log.debug("finding EleFormatoPlancha instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EleFormatoPlancha as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUsuarioGenera(Object usuarioGenera) {
		return findByProperty(USUARIO_GENERA, usuarioGenera);
	}

	public List findByUsuarioImpresion(Object usuarioImpresion) {
		return findByProperty(USUARIO_IMPRESION, usuarioImpresion);
	}

	public List findAll() {
		log.debug("finding all EleFormatoPlancha instances");
		try {
			String queryString = "from EleFormatoPlancha";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleFormatoPlancha merge(EleFormatoPlancha detachedInstance) {
		log.debug("merging EleFormatoPlancha instance");
		try {
			EleFormatoPlancha result = (EleFormatoPlancha) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleFormatoPlancha instance) {
		log.debug("attaching dirty EleFormatoPlancha instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleFormatoPlancha instance) {
		log.debug("attaching clean EleFormatoPlancha instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}