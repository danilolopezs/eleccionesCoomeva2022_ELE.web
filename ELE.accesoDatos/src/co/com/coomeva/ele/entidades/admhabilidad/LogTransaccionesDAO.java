package co.com.coomeva.ele.entidades.admhabilidad;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * LogTransacciones entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.admhabilidad.LogTransacciones
 * @author MyEclipse Persistence Tools
 */

public class LogTransaccionesDAO extends BaseHibernateDAOHab {
	private static final Log log = LogFactory.getLog(LogTransaccionesDAO.class);
	// property constants
	public static final String NRO_IDENTIFICACION = "nroIdentificacion";
	public static final String ANTHABIL = "anthabil";
	public static final String HABIL = "habil";
	public static final String CONCP_TRANSACCION = "concpTransaccion";
	public static final String USUARIO = "usuario";

	public void save(LogTransacciones transientInstance) {
		log.debug("saving LogTransacciones instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LogTransacciones persistentInstance) {
		log.debug("deleting LogTransacciones instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LogTransacciones findById(java.lang.Long id) {
		log.debug("getting LogTransacciones instance with id: " + id);
		try {
			LogTransacciones instance = (LogTransacciones) getSession()
					.get(
							"co.com.coomeva.ele.entidades.admhabilidad.LogTransacciones",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LogTransacciones instance) {
		log.debug("finding LogTransacciones instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"co.com.coomeva.ele.entidades.admhabilidad.LogTransacciones")
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
		log.debug("finding LogTransacciones instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from LogTransacciones as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNroIdentificacion(Object nroIdentificacion) {
		return findByProperty(NRO_IDENTIFICACION, nroIdentificacion);
	}

	public List findByAnthabil(Object anthabil) {
		return findByProperty(ANTHABIL, anthabil);
	}

	public List findByHabil(Object habil) {
		return findByProperty(HABIL, habil);
	}

	public List findByConcpTransaccion(Object concpTransaccion) {
		return findByProperty(CONCP_TRANSACCION, concpTransaccion);
	}

	public List findByUsuario(Object usuario) {
		return findByProperty(USUARIO, usuario);
	}

	public List findAll() {
		log.debug("finding all LogTransacciones instances");
		try {
			String queryString = "from LogTransacciones";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LogTransacciones merge(LogTransacciones detachedInstance) {
		log.debug("merging LogTransacciones instance");
		try {
			LogTransacciones result = (LogTransacciones) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LogTransacciones instance) {
		log.debug("attaching dirty LogTransacciones instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LogTransacciones instance) {
		log.debug("attaching clean LogTransacciones instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}