package co.com.coomeva.ele.entidades.planchas;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * ElePlanchas entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.planchas.ElePlanchas
 * @author MyEclipse Persistence Tools
 */

public class ElePlanchasDAO extends BaseHibernateDAOPlanchas {
	private static final Log log = LogFactory.getLog(ElePlanchasDAO.class);
	// property constants
	public static final String ESTADO = "estado";
	public static final String NRO_PLANCHA = "nroPlancha";
	public static final String DESC_ESTADO = "descEstado";

	public void save(ElePlanchas transientInstance) {
		log.debug("saving ElePlanchas instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ElePlanchas persistentInstance) {
		log.debug("deleting ElePlanchas instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ElePlanchas findById(java.lang.String id) {
		log.debug("getting ElePlanchas instance with id: " + id);
		try {
			ElePlanchas instance = (ElePlanchas) getSession().get(
					"co.com.coomeva.ele.entidades.planchas.ElePlanchas", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ElePlanchas instance) {
		log.debug("finding ElePlanchas instance by example");
		try {
			List results = getSession().createCriteria(
					"co.com.coomeva.ele.entidades.planchas.ElePlanchas").add(
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
		log.debug("finding ElePlanchas instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ElePlanchas as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByEstado(Object estado) {
		return findByProperty(ESTADO, estado);
	}

	public List findByNroPlancha(Object nroPlancha) {
		return findByProperty(NRO_PLANCHA, nroPlancha);
	}

	public List findByDescEstado(Object descEstado) {
		return findByProperty(DESC_ESTADO, descEstado);
	}

	public List findAll() {
		log.debug("finding all ElePlanchas instances");
		try {
			String queryString = "from ElePlanchas";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ElePlanchas merge(ElePlanchas detachedInstance) {
		log.debug("merging ElePlanchas instance");
		try {
			ElePlanchas result = (ElePlanchas) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ElePlanchas instance) {
		log.debug("attaching dirty ElePlanchas instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ElePlanchas instance) {
		log.debug("attaching clean ElePlanchas instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}