package co.com.coomeva.ele.entidades.planchas;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleExperienciaLaboral entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral
 * @author MyEclipse Persistence Tools
 */

public class EleExperienciaLaboralDAO extends BaseHibernateDAOPlanchas {
	private static final Log log = LogFactory
			.getLog(EleExperienciaLaboralDAO.class);
	// property constants
	public static final String EMPRESA = "empresa";
	public static final String CARGO = "cargo";

	public void save(EleExperienciaLaboral transientInstance) {
		log.debug("saving EleExperienciaLaboral instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleExperienciaLaboral persistentInstance) {
		log.debug("deleting EleExperienciaLaboral instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleExperienciaLaboral findById(
			co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboralId id) {
		log.debug("getting EleExperienciaLaboral instance with id: " + id);
		try {
			EleExperienciaLaboral instance = (EleExperienciaLaboral) getSession()
					.get(
							"co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleExperienciaLaboral instance) {
		log.debug("finding EleExperienciaLaboral instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral")
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
		log.debug("finding EleExperienciaLaboral instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EleExperienciaLaboral as model where model."
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

	public List findByCargo(Object cargo) {
		return findByProperty(CARGO, cargo);
	}

	public List findAll() {
		log.debug("finding all EleExperienciaLaboral instances");
		try {
			String queryString = "from EleExperienciaLaboral";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleExperienciaLaboral merge(EleExperienciaLaboral detachedInstance) {
		log.debug("merging EleExperienciaLaboral instance");
		try {
			EleExperienciaLaboral result = (EleExperienciaLaboral) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleExperienciaLaboral instance) {
		log.debug("attaching dirty EleExperienciaLaboral instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleExperienciaLaboral instance) {
		log.debug("attaching clean EleExperienciaLaboral instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}