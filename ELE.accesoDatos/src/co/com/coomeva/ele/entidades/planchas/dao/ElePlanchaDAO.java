package co.com.coomeva.ele.entidades.planchas.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlancha;

/**
 * A data access object (DAO) providing persistence and search support for
 * ElePlancha entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see co.com.elecdb.ElePlancha
 * @author MyEclipse Persistence Tools
 */

public class ElePlanchaDAO extends BaseHibernateDAO {
	
	
	private static final Log log = LogFactory.getLog(ElePlanchaDAO.class);
	// property constants
	public static final String NUMERO_PLANCHA = "numeroPlancha";
	public static final String ESTADO_PLANCHA = "estadoPlancha";

	public void save(ElePlancha transientInstance) {
		log.debug("saving ElePlancha instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ElePlancha persistentInstance) {
		log.debug("deleting ElePlancha instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ElePlancha findById(java.lang.Long id) {
		log.debug("getting ElePlancha instance with id: " + id);
		try {
			ElePlancha instance = (ElePlancha) getSession().get(
					"co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlancha", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ElePlancha instance) {
		log.debug("finding ElePlancha instance by example");
		try {
			List results = getSession()
					.createCriteria("co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlancha")
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
		log.debug("finding ElePlancha instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ElePlancha as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNumeroPlancha(Object numeroPlancha) {
		return findByProperty(NUMERO_PLANCHA, numeroPlancha);
	}

	public List findByEstadoPlancha(Object estadoPlancha) {
		return findByProperty(ESTADO_PLANCHA, estadoPlancha);
	}

	public List findAll() {
		log.debug("finding all ElePlancha instances");
		try {
			String queryString = "from ElePlancha";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ElePlancha merge(ElePlancha detachedInstance) {
		log.debug("merging ElePlancha instance");
		try {
			ElePlancha result = (ElePlancha) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ElePlancha instance) {
		log.debug("attaching dirty ElePlancha instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ElePlancha instance) {
		log.debug("attaching clean ElePlancha instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<ElePlancha> findPlanchasPorEstado(String[] estados){
		try {
			Criteria criteria = getSession()
					.createCriteria(
							"co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlancha");
			criteria.add(Restrictions.in("estadoPlancha", estados));
			return criteria.list();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}