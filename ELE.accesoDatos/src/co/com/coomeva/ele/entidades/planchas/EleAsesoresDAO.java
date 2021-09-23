package co.com.coomeva.ele.entidades.planchas;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleAsesores entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.planchas.EleAsesores
 * @author MyEclipse Persistence Tools
 */

public class EleAsesoresDAO extends BaseHibernateDAOPlanchas {
	private static final Log log = LogFactory.getLog(EleAsesoresDAO.class);
	// property constants
	public static final String EMPRESA = "empresa";
	public static final String RETIRADO = "retirado";

	public void save(EleAsesores transientInstance) {
		log.debug("saving EleAsesores instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleAsesores persistentInstance) {
		log.debug("deleting EleAsesores instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleAsesores findById(java.lang.String id) {
		log.debug("getting EleAsesores instance with id: " + id);
		try {
			EleAsesores instance = (EleAsesores) getSession().get(
					"co.com.coomeva.ele.entidades.planchas.EleAsesores", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleAsesores instance) {
		log.debug("finding EleAsesores instance by example");
		try {
			List results = getSession().createCriteria(
					"co.com.coomeva.ele.entidades.planchas.EleAsesores").add(
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
		log.debug("finding EleAsesores instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from EleAsesores as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByEmpresa(Object empresa) {
		return findByProperty(EMPRESA, empresa);
	}

	public List findByRetirado(Object retirado) {
		return findByProperty(RETIRADO, retirado);
	}

	public List findAll() {
		log.debug("finding all EleAsesores instances");
		try {
			String queryString = "from EleAsesores";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleAsesores merge(EleAsesores detachedInstance) {
		log.debug("merging EleAsesores instance");
		try {
			EleAsesores result = (EleAsesores) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleAsesores instance) {
		log.debug("attaching dirty EleAsesores instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleAsesores instance) {
		log.debug("attaching clean EleAsesores instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}