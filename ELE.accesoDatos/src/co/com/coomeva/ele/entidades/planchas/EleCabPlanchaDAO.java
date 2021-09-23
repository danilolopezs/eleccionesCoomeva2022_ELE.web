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
 * EleCabPlancha entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.planchas.EleCabPlancha
 * @author MyEclipse Persistence Tools
 */

public class EleCabPlanchaDAO extends BaseHibernateDAOPlanchas {
	private static final Log log = LogFactory.getLog(EleCabPlanchaDAO.class);
	// property constants
	public static final String PRIMER_NOMBRE = "primerNombre";
	public static final String SEGUNDO_NOMBRE = "segundoNombre";
	public static final String PRIMER_APELLIDO = "primerApellido";
	public static final String SEGUNDO_APELLIDO = "segundoApellido";
	public static final String EDAD = "edad";
	public static final String PROFESION = "profesion";
	public static final String EMAIL = "email";
	public static final String ANTIGUEDAD = "antiguedad";
	public static final String RUTA_IMAGEN = "rutaImagen";
	public static final String OTROS_ESTUDIOS = "otrosEstudios";
	public static final String CARGOS_DIRECTIVOS = "cargosDirectivos";

	public void save(EleCabPlancha transientInstance) {
		log.debug("saving EleCabPlancha instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleCabPlancha persistentInstance) {
		log.debug("deleting EleCabPlancha instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleCabPlancha findById(java.lang.String id) {
		log.debug("getting EleCabPlancha instance with id: " + id);
		try {
			EleCabPlancha instance = (EleCabPlancha) getSession().get(
					"co.com.coomeva.ele.entidades.planchas.EleCabPlancha", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleCabPlancha instance) {
		log.debug("finding EleCabPlancha instance by example");
		try {
			List results = getSession().createCriteria(
					"co.com.coomeva.ele.entidades.planchas.EleCabPlancha").add(
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
		log.debug("finding EleCabPlancha instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EleCabPlancha as model where model."
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

	public List findByEdad(Object edad) {
		return findByProperty(EDAD, edad);
	}

	public List findByProfesion(Object profesion) {
		return findByProperty(PROFESION, profesion);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByAntiguedad(Object antiguedad) {
		return findByProperty(ANTIGUEDAD, antiguedad);
	}

	public List findByRutaImagen(Object rutaImagen) {
		return findByProperty(RUTA_IMAGEN, rutaImagen);
	}

	public List findByOtrosEstudios(Object otrosEstudios) {
		return findByProperty(OTROS_ESTUDIOS, otrosEstudios);
	}

	public List findByCargosDirectivos(Object cargosDirectivos) {
		return findByProperty(CARGOS_DIRECTIVOS, cargosDirectivos);
	}

	public List findAll() {
		log.debug("finding all EleCabPlancha instances");
		try {
			String queryString = "from EleCabPlancha";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleCabPlancha merge(EleCabPlancha detachedInstance) {
		log.debug("merging EleCabPlancha instance");
		try {
			EleCabPlancha result = (EleCabPlancha) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleCabPlancha instance) {
		log.debug("attaching dirty EleCabPlancha instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleCabPlancha instance) {
		log.debug("attaching clean EleCabPlancha instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}