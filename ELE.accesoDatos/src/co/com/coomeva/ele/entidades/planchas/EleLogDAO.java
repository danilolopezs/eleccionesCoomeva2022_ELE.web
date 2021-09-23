package co.com.coomeva.ele.entidades.planchas;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleLog entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.planchas.EleLog
 * @author MyEclipse Persistence Tools
 */

public class EleLogDAO extends BaseHibernateDAOPlanchas {
	private static final Log log = LogFactory.getLog(EleLogDAO.class);
	// property constants
	public static final String NRO_CABO_PLANCHA = "nroCaboPlancha";
	public static final String TIPOTRANS = "tipotrans";
	public static final String USUARIO = "usuario";
	public static final String DESCRIPCION = "descripcion";

	public void save(EleLog transientInstance) {
		log.debug("saving EleLog instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleLog persistentInstance) {
		log.debug("deleting EleLog instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleLog findById(java.lang.Long id) {
		log.debug("getting EleLog instance with id: " + id);
		try {
			EleLog instance = (EleLog) getSession().get(
					"co.com.coomeva.ele.entidades.planchas.EleLog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleLog instance) {
		log.debug("finding EleLog instance by example");
		try {
			List results = getSession().createCriteria(
					"co.com.coomeva.ele.entidades.planchas.EleLog").add(
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
		log.debug("finding EleLog instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from EleLog as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNroCaboPlancha(Object nroCaboPlancha) {
		return findByProperty(NRO_CABO_PLANCHA, nroCaboPlancha);
	}

	public List findByTipotrans(Object tipotrans) {
		return findByProperty(TIPOTRANS, tipotrans);
	}

	public List findByUsuario(Object usuario) {
		return findByProperty(USUARIO, usuario);
	}

	public List findByDescripcion(Object descripcion) {
		return findByProperty(DESCRIPCION, descripcion);
	}

	public List findAll() {
		log.debug("finding all EleLog instances");
		try {
			String queryString = "from EleLog";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleLog merge(EleLog detachedInstance) {
		log.debug("merging EleLog instance");
		try {
			EleLog result = (EleLog) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleLog instance) {
		log.debug("attaching dirty EleLog instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleLog instance) {
		log.debug("attaching clean EleLog instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}