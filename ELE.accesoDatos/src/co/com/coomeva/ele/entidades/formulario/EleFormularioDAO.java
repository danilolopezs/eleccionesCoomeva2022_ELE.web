package co.com.coomeva.ele.entidades.formulario;

import co.com.coomeva.ele.entidades.planchas.dao.BaseHibernateDAO;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleFormulario entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.formulario.EleFormulario
 * @author MyEclipse Persistence Tools
 */

public class EleFormularioDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(EleFormularioDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String DESCRIPCION = "descripcion";

	public void save(EleFormulario transientInstance) {
		log.debug("saving EleFormulario instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleFormulario persistentInstance) {
		log.debug("deleting EleFormulario instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleFormulario findById(java.lang.Byte id) {
		log.debug("getting EleFormulario instance with id: " + id);
		try {
			EleFormulario instance = (EleFormulario) getSession()
					.get(
							"co.com.coomeva.ele.entidades.formulario.EleFormulario",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleFormulario instance) {
		log.debug("finding EleFormulario instance by example");
		try {
			List results = getSession().createCriteria(
					"co.com.coomeva.ele.entidades.formulario.EleFormulario")
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
		log.debug("finding EleFormulario instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EleFormulario as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List findByDescripcion(Object descripcion) {
		return findByProperty(DESCRIPCION, descripcion);
	}

	public List findAll() {
		log.debug("finding all EleFormulario instances");
		try {
			String queryString = "from EleFormulario";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleFormulario merge(EleFormulario detachedInstance) {
		log.debug("merging EleFormulario instance");
		try {
			EleFormulario result = (EleFormulario) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleFormulario instance) {
		log.debug("attaching dirty EleFormulario instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleFormulario instance) {
		log.debug("attaching clean EleFormulario instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}