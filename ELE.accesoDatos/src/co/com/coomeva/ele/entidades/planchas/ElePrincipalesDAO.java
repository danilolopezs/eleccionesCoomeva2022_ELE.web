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
 * ElePrincipales entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.planchas.ElePrincipales
 * @author MyEclipse Persistence Tools
 */

public class ElePrincipalesDAO extends BaseHibernateDAOPlanchas {
	private static final Log log = LogFactory.getLog(ElePrincipalesDAO.class);
	// property constants
	public static final String PRIMER_NOMBRE = "primerNombre";
	public static final String SEGUNDO_NOMBRE = "segundoNombre";
	public static final String PRIMER_APELLIDO = "primerApellido";
	public static final String SEGUNDO_APELLIDO = "segundoApellido";
	public static final String PROFESION = "profesion";
	public static final String EMAIL = "email";
	public static final String ORDEN = "orden";

	public void save(ElePrincipales transientInstance) {
		log.debug("saving ElePrincipales instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ElePrincipales persistentInstance) {
		log.debug("deleting ElePrincipales instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ElePrincipales findById(java.lang.String id) {
		log.debug("getting ElePrincipales instance with id: " + id);
		try {
			ElePrincipales instance = (ElePrincipales) getSession().get(
					"co.com.coomeva.ele.entidades.planchas.ElePrincipales", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ElePrincipales instance) {
		log.debug("finding ElePrincipales instance by example");
		try {
			List results = getSession().createCriteria(
					"co.com.coomeva.ele.entidades.planchas.ElePrincipales")
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
		log.debug("finding ElePrincipales instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ElePrincipales as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPrimerNombre(Object primerNombre) {
		return findByProperty(PRIMER_NOMBRE, primerNombre);
	}

	public List findBySegundoNombre(Object segundoNombre) {
		return findByProperty(SEGUNDO_NOMBRE, segundoNombre);
	}

	public List findByPrimerApellido(Object primerApellido) {
		return findByProperty(PRIMER_APELLIDO, primerApellido);
	}

	public List findBySegundoApellido(Object segundoApellido) {
		return findByProperty(SEGUNDO_APELLIDO, segundoApellido);
	}

	public List findByProfesion(Object profesion) {
		return findByProperty(PROFESION, profesion);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByOrden(Object orden) {
		return findByProperty(ORDEN, orden);
	}

	public List findAll() {
		log.debug("finding all ElePrincipales instances");
		try {
			String queryString = "from ElePrincipales";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ElePrincipales merge(ElePrincipales detachedInstance) {
		log.debug("merging ElePrincipales instance");
		try {
			ElePrincipales result = (ElePrincipales) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ElePrincipales instance) {
		log.debug("attaching dirty ElePrincipales instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ElePrincipales instance) {
		log.debug("attaching clean ElePrincipales instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}