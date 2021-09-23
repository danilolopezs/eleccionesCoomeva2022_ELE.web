package co.com.coomeva.ele.entidades.planchas;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleZonasFinanciero entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.planchas.EleZonasFinanciero
 * @author MyEclipse Persistence Tools
 */

public class EleZonasFinancieroDAO extends BaseHibernateDAOPlanchas {
	private static final Log log = LogFactory
			.getLog(EleZonasFinancieroDAO.class);
	// property constants
	public static final String NOM_ZONA_FIN = "nomZonaFin";

	public void save(EleZonasFinanciero transientInstance) {
		log.debug("saving EleZonasFinanciero instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleZonasFinanciero persistentInstance) {
		log.debug("deleting EleZonasFinanciero instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleZonasFinanciero findById(
			co.com.coomeva.ele.entidades.planchas.EleZonasFinancieroId id) {
		log.debug("getting EleZonasFinanciero instance with id: " + id);
		try {
			EleZonasFinanciero instance = (EleZonasFinanciero) getSession()
					.get(
							"co.com.coomeva.ele.entidades.planchas.EleZonasFinanciero",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleZonasFinanciero instance) {
		log.debug("finding EleZonasFinanciero instance by example");
		try {
			List results = getSession().createCriteria(
					"co.com.coomeva.ele.entidades.planchas.EleZonasFinanciero")
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
		log.debug("finding EleZonasFinanciero instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EleZonasFinanciero as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNomZonaFin(Object nomZonaFin) {
		return findByProperty(NOM_ZONA_FIN, nomZonaFin);
	}

	public List findAll() {
		log.debug("finding all EleZonasFinanciero instances");
		try {
			String queryString = "from EleZonasFinanciero";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleZonasFinanciero merge(EleZonasFinanciero detachedInstance) {
		log.debug("merging EleZonasFinanciero instance");
		try {
			EleZonasFinanciero result = (EleZonasFinanciero) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleZonasFinanciero instance) {
		log.debug("attaching dirty EleZonasFinanciero instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleZonasFinanciero instance) {
		log.debug("attaching clean EleZonasFinanciero instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}