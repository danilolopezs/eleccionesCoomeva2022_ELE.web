package co.com.coomeva.ele.entidades.planchas.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFormato;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleFormato entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see co.com.elecdb.EleFormato
 * @author MyEclipse Persistence Tools
 */

public class EleFormatoDAO extends BaseHibernateDAO {
	
	private static final Log log = LogFactory.getLog(EleFormatoDAO.class);
	// property constants
	public static final String DESCRIPCION_FORMATO = "descripcionFormato";
	public static final String ESTADO_FORMATO = "estadoFormato";

	public void save(EleFormato transientInstance) {
		log.debug("saving EleFormato instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleFormato persistentInstance) {
		log.debug("deleting EleFormato instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleFormato findById(java.lang.Byte id) {
		log.debug("getting EleFormato instance with id: " + id);
		try {
			EleFormato instance = (EleFormato) getSession().get(
					"co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFormato", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleFormato instance) {
		log.debug("finding EleFormato instance by example");
		try {
			List results = getSession()
					.createCriteria("co.com.elecdb.EleFormato")
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
		log.debug("finding EleFormato instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from EleFormato as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDescripcionFormato(Object descripcionFormato) {
		return findByProperty(DESCRIPCION_FORMATO, descripcionFormato);
	}

	public List findByEstadoFormato(Object estadoFormato) {
		return findByProperty(ESTADO_FORMATO, estadoFormato);
	}

	public List findAll() {
		log.debug("finding all EleFormato instances");
		try {
			String queryString = "from EleFormato";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleFormato merge(EleFormato detachedInstance) {
		log.debug("merging EleFormato instance");
		try {
			EleFormato result = (EleFormato) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleFormato instance) {
		log.debug("attaching dirty EleFormato instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleFormato instance) {
		log.debug("attaching clean EleFormato instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}