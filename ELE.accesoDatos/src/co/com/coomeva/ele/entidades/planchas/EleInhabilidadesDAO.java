package co.com.coomeva.ele.entidades.planchas;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleInhabilidades entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.planchas.EleInhabilidades
 * @author MyEclipse Persistence Tools
 */

public class EleInhabilidadesDAO extends BaseHibernateDAOPlanchas {
	private static final Log log = LogFactory.getLog(EleInhabilidadesDAO.class);
	// property constants
	public static final String INHABILIDAD = "inhabilidad";

	public void save(EleInhabilidades transientInstance) {
		log.debug("saving EleInhabilidades instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleInhabilidades persistentInstance) {
		log.debug("deleting EleInhabilidades instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleInhabilidades findById(
			co.com.coomeva.ele.entidades.planchas.EleInhabilidadesId id) {
		log.debug("getting EleInhabilidades instance with id: " + id);
		try {
			EleInhabilidades instance = (EleInhabilidades) getSession().get(
					"co.com.coomeva.ele.entidades.planchas.EleInhabilidades",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleInhabilidades instance) {
		log.debug("finding EleInhabilidades instance by example");
		try {
			List results = getSession().createCriteria(
					"co.com.coomeva.ele.entidades.planchas.EleInhabilidades")
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
		log.debug("finding EleInhabilidades instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EleInhabilidades as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByInhabilidad(Object inhabilidad) {
		return findByProperty(INHABILIDAD, inhabilidad);
	}

	public List findAll() {
		log.debug("finding all EleInhabilidades instances");
		try {
			String queryString = "from EleInhabilidades";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleInhabilidades merge(EleInhabilidades detachedInstance) {
		log.debug("merging EleInhabilidades instance");
		try {
			EleInhabilidades result = (EleInhabilidades) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleInhabilidades instance) {
		log.debug("attaching dirty EleInhabilidades instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleInhabilidades instance) {
		log.debug("attaching clean EleInhabilidades instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}