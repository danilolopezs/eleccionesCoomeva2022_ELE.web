package co.com.coomeva.ele.entidades.formulario;

import co.com.coomeva.ele.entidades.planchas.dao.BaseHibernateDAO;
import java.sql.Timestamp;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleRegistroFormulario entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.formulario.EleRegistroFormulario
 * @author MyEclipse Persistence Tools
 */

public class EleRegistroFormularioDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(EleRegistroFormularioDAO.class);
	// property constants
	public static final String COD_FORMULARIO = "codFormulario";

	public void save(EleRegistroFormulario transientInstance) {
		log.debug("saving EleRegistroFormulario instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleRegistroFormulario persistentInstance) {
		log.debug("deleting EleRegistroFormulario instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleRegistroFormulario findById(java.lang.Long id) {
		log.debug("getting EleRegistroFormulario instance with id: " + id);
		try {
			EleRegistroFormulario instance = (EleRegistroFormulario) getSession()
					.get(
							"co.com.coomeva.ele.entidades.formulario.EleRegistroFormulario",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleRegistroFormulario instance) {
		log.debug("finding EleRegistroFormulario instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"co.com.coomeva.ele.entidades.formulario.EleRegistroFormulario")
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
		log.debug("finding EleRegistroFormulario instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EleRegistroFormulario as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCodFormulario(Object codFormulario) {
		return findByProperty(COD_FORMULARIO, codFormulario);
	}

	public List findAll() {
		log.debug("finding all EleRegistroFormulario instances");
		try {
			String queryString = "from EleRegistroFormulario";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleRegistroFormulario merge(EleRegistroFormulario detachedInstance) {
		log.debug("merging EleRegistroFormulario instance");
		try {
			EleRegistroFormulario result = (EleRegistroFormulario) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleRegistroFormulario instance) {
		log.debug("attaching dirty EleRegistroFormulario instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleRegistroFormulario instance) {
		log.debug("attaching clean EleRegistroFormulario instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}