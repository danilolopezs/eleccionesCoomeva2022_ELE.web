package co.com.coomeva.ele.entidades.planchas;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleSubcomision entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.planchas.EleSubcomision
 * @author MyEclipse Persistence Tools
 */

public class EleSubcomisionDAO extends BaseHibernateDAOPlanchas {
	private static final Log log = LogFactory.getLog(EleSubcomisionDAO.class);
	// property constants
	public static final String SUBCOMISIONZONA = "subcomisionzona";

	public void save(EleSubcomision transientInstance) {
		log.debug("saving EleSubcomision instance");
		try {
			getSession2012().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleSubcomision persistentInstance) {
		log.debug("deleting EleSubcomision instance");
		try {
			getSession2012().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleSubcomision findById(java.lang.String id) {
		log.debug("getting EleSubcomision instance with id: " + id);
		try {
			EleSubcomision instance = (EleSubcomision) getSession2012().get(
					"co.com.coomeva.ele.entidades.planchas.EleSubcomision", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleSubcomision instance) {
		log.debug("finding EleSubcomision instance by example");
		try {
			List results = getSession2012().createCriteria(
					"co.com.coomeva.ele.entidades.planchas.EleSubcomision")
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
		log.debug("finding EleSubcomision instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EleSubcomision as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession2012().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySubcomisionzona(Object subcomisionzona) {
		return findByProperty(SUBCOMISIONZONA, subcomisionzona);
	}

	public List findAll() {
		log.debug("finding all EleSubcomision instances");
		try {
			String queryString = "from EleSubcomision";
			Query queryObject = getSession2012().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleSubcomision merge(EleSubcomision detachedInstance) {
		log.debug("merging EleSubcomision instance");
		try {
			EleSubcomision result = (EleSubcomision) getSession2012().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleSubcomision instance) {
		log.debug("attaching dirty EleSubcomision instance");
		try {
			getSession2012().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleSubcomision instance) {
		log.debug("attaching clean EleSubcomision instance");
		try {
			getSession2012().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}