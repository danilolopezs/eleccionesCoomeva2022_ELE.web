package co.com.coomeva.ele.entidades.planchas;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleZonas entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.planchas.EleZonas
 * @author MyEclipse Persistence Tools
 */

public class EleZonasDAO extends BaseHibernateDAOPlanchas {
	private static final Log log = LogFactory.getLog(EleZonasDAO.class);
	// property constants
	public static final String MAX_PRINCIPALES = "maxPrincipales";
	public static final String ZON_ESPECIAL = "zonEspecial";
	public static final String NOM_ZONA = "nomZona";

	public void save(EleZonas transientInstance) {
		log.debug("saving EleZonas instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleZonas persistentInstance) {
		log.debug("deleting EleZonas instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleZonas findById(java.lang.String id) {
		log.debug("getting EleZonas instance with id: " + id);
		try {
			EleZonas instance = (EleZonas) getSession().get(
					"co.com.coomeva.ele.entidades.planchas.EleZonas", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleZonas instance) {
		log.debug("finding EleZonas instance by example");
		try {
			List results = getSession().createCriteria(
					"co.com.coomeva.ele.entidades.planchas.EleZonas").add(
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
		log.debug("finding EleZonas instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from EleZonas as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMaxPrincipales(Object maxPrincipales) {
		return findByProperty(MAX_PRINCIPALES, maxPrincipales);
	}

	public List findByZonEspecial(Object zonEspecial) {
		return findByProperty(ZON_ESPECIAL, zonEspecial);
	}

	public List findByNomZona(Object nomZona) {
		return findByProperty(NOM_ZONA, nomZona);
	}

	public List findAll() {
		log.debug("finding all EleZonas instances");
		try {
			String queryString = "from EleZonas";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleZonas merge(EleZonas detachedInstance) {
		log.debug("merging EleZonas instance");
		try {
			EleZonas result = (EleZonas) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleZonas instance) {
		log.debug("attaching dirty EleZonas instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleZonas instance) {
		log.debug("attaching clean EleZonas instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}