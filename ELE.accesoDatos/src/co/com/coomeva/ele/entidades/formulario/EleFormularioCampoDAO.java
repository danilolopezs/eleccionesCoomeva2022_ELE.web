package co.com.coomeva.ele.entidades.formulario;

import co.com.coomeva.ele.entidades.planchas.dao.BaseHibernateDAO;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleFormularioCampo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.formulario.EleFormularioCampo
 * @author MyEclipse Persistence Tools
 */

public class EleFormularioCampoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(EleFormularioCampoDAO.class);

	// property constants

	public void save(EleFormularioCampo transientInstance) {
		log.debug("saving EleFormularioCampo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleFormularioCampo persistentInstance) {
		log.debug("deleting EleFormularioCampo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleFormularioCampo findById(java.lang.Short id) {
		log.debug("getting EleFormularioCampo instance with id: " + id);
		try {
			EleFormularioCampo instance = (EleFormularioCampo) getSession()
					.get(
							"co.com.coomeva.ele.entidades.formulario.EleFormularioCampo",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleFormularioCampo instance) {
		log.debug("finding EleFormularioCampo instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"co.com.coomeva.ele.entidades.formulario.EleFormularioCampo")
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
		log.debug("finding EleFormularioCampo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EleFormularioCampo as model where model."
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
		log.debug("finding all EleFormularioCampo instances");
		try {
			String queryString = "from EleFormularioCampo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleFormularioCampo merge(EleFormularioCampo detachedInstance) {
		log.debug("merging EleFormularioCampo instance");
		try {
			EleFormularioCampo result = (EleFormularioCampo) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleFormularioCampo instance) {
		log.debug("attaching dirty EleFormularioCampo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleFormularioCampo instance) {
		log.debug("attaching clean EleFormularioCampo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}