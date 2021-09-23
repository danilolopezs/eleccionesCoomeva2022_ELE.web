package co.com.coomeva.ele.entidades.planchas.dao;

import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleProcesoLog;

import java.sql.Timestamp;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleProcesoLog entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.planchas.dosmildoce.EleProcesoLog
 * @author MyEclipse Persistence Tools
 */

public class EleProcesoLogDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(EleProcesoLogDAO.class);
	// property constants
	public static final String CODIGO_PROCESO_LOG = "codigoProcesoLog";
	public static final String ESTADO_PROCESO_LOG = "estadoProcesoLog";
	public static final String USUARIO_EJECUTA = "usuarioEjecuta";
	public static final String DESCRIPCION_PROCESO_LOG = "descripcionProcesoLog";

	public void save(EleProcesoLog transientInstance) {
		log.debug("saving EleProcesoLog instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleProcesoLog persistentInstance) {
		log.debug("deleting EleProcesoLog instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleProcesoLog findById(java.lang.Long id) {
		log.debug("getting EleProcesoLog instance with id: " + id);
		try {
			EleProcesoLog instance = (EleProcesoLog) getSession().get(
					"co.com.coomeva.ele.entidades.planchas.dosmildoce.EleProcesoLog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleProcesoLog instance) {
		log.debug("finding EleProcesoLog instance by example");
		try {
			List results = getSession().createCriteria(
					"co.com.coomeva.ele.entidades.planchas.dosmildoce.EleProcesoLog").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding EleProcesoLog instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EleProcesoLog as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCodigoProcesoLog(Object codigoProcesoLog) {
		return findByProperty(CODIGO_PROCESO_LOG, codigoProcesoLog);
	}

	public List findByEstadoProcesoLog(Object estadoProcesoLog) {
		return findByProperty(ESTADO_PROCESO_LOG, estadoProcesoLog);
	}

	public List findByUsuarioEjecuta(Object usuarioEjecuta) {
		return findByProperty(USUARIO_EJECUTA, usuarioEjecuta);
	}

	public List findByDescripcionProcesoLog(Object descripcionProcesoLog) {
		return findByProperty(DESCRIPCION_PROCESO_LOG, descripcionProcesoLog);
	}

	public List findAll() {
		log.debug("finding all EleProcesoLog instances");
		try {
			String queryString = "from EleProcesoLog";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleProcesoLog merge(EleProcesoLog detachedInstance) {
		log.debug("merging EleProcesoLog instance");
		try {
			EleProcesoLog result = (EleProcesoLog) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleProcesoLog instance) {
		log.debug("attaching dirty EleProcesoLog instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleProcesoLog instance) {
		log.debug("attaching clean EleProcesoLog instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}