package co.com.coomeva.ele.entidades.planchas.dao;

import co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlanchaExcepcion;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * ElePlanchaExcepcion entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlanchaExcepcion
 * @author MyEclipse Persistence Tools
 */

public class ElePlanchaExcepcionDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(ElePlanchaExcepcionDAO.class);
	// property constants
	public static final String CONSECUTIVO_PLANCHA = "consecutivoPlancha";
	public static final String CODIGO_ASOCIADO = "codigoAsociado";
	public static final String DESC_EXCEPCION = "descExcepcion";

	public void save(ElePlanchaExcepcion transientInstance) {
		log.debug("saving ElePlanchaExcepcion instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ElePlanchaExcepcion persistentInstance) {
		log.debug("deleting ElePlanchaExcepcion instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ElePlanchaExcepcion findById(java.lang.Long id) {
		log.debug("getting ElePlanchaExcepcion instance with id: " + id);
		try {
			ElePlanchaExcepcion instance = (ElePlanchaExcepcion) getSession()
					.get("co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlanchaExcepcion", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ElePlanchaExcepcion instance) {
		log.debug("finding ElePlanchaExcepcion instance by example");
		try {
			List results = getSession().createCriteria(
					"co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlanchaExcepcion").add(
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
		log.debug("finding ElePlanchaExcepcion instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ElePlanchaExcepcion as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByConsecutivoPlancha(Object consecutivoPlancha) {
		return findByProperty(CONSECUTIVO_PLANCHA, consecutivoPlancha);
	}

	public List findByCodigoAsociado(Object codigoAsociado) {
		return findByProperty(CODIGO_ASOCIADO, codigoAsociado);
	}

	public List findByDescExcepcion(Object descExcepcion) {
		return findByProperty(DESC_EXCEPCION, descExcepcion);
	}

	public List findAll() {
		log.debug("finding all ElePlanchaExcepcion instances");
		try {
			String queryString = "from ElePlanchaExcepcion";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ElePlanchaExcepcion merge(ElePlanchaExcepcion detachedInstance) {
		log.debug("merging ElePlanchaExcepcion instance");
		try {
			ElePlanchaExcepcion result = (ElePlanchaExcepcion) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ElePlanchaExcepcion instance) {
		log.debug("attaching dirty ElePlanchaExcepcion instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ElePlanchaExcepcion instance) {
		log.debug("attaching clean ElePlanchaExcepcion instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}