package co.com.coomeva.ele.entidades.admhabilidad;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Zonaregio1 entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.admhabilidad.Zonaregio1
 * @author MyEclipse Persistence Tools
 */

public class Zonaregio1DAO extends BaseHibernateDAOHab {
	private static final Log log = LogFactory.getLog(Zonaregio1DAO.class);

	// property constants

	public void save(Zonaregio1 transientInstance) {
		log.debug("saving Zonaregio1 instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Zonaregio1 persistentInstance) {
		log.debug("deleting Zonaregio1 instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Zonaregio1 findById(
			co.com.coomeva.ele.entidades.admhabilidad.Zonaregio1Id id) {
		log.debug("getting Zonaregio1 instance with id: " + id);
		try {
			Zonaregio1 instance = (Zonaregio1) getSession().get(
					"co.com.coomeva.ele.entidades.admhabilidad.Zonaregio1", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Zonaregio1 instance) {
		log.debug("finding Zonaregio1 instance by example");
		try {
			List results = getSession().createCriteria(
					"co.com.coomeva.ele.entidades.admhabilidad.Zonaregio1")
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
		log.debug("finding Zonaregio1 instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Zonaregio1 as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Zonaregio1 instances");
		try {
			String queryString = "from Zonaregio1";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Zonaregio1 merge(Zonaregio1 detachedInstance) {
		log.debug("merging Zonaregio1 instance");
		try {
			Zonaregio1 result = (Zonaregio1) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Zonaregio1 instance) {
		log.debug("attaching dirty Zonaregio1 instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Zonaregio1 instance) {
		log.debug("attaching clean Zonaregio1 instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}