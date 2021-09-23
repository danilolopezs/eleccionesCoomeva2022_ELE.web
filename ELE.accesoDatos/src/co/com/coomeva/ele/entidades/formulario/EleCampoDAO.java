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
 * EleCampo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.formulario.EleCampo
 * @author MyEclipse Persistence Tools
 */

public class EleCampoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(EleCampoDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String TIPO_CAMPO = "tipoCampo";
	public static final String TIPO_DATO = "tipoDato";
	public static final String OBLIGATORIO = "obligatorio";
	public static final String DESCRIPCION = "descripcion";
	public static final String ATRIBUTOS = "atributos";

	public void save(EleCampo transientInstance) {
		log.debug("saving EleCampo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleCampo persistentInstance) {
		log.debug("deleting EleCampo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleCampo findById(java.lang.Byte id) {
		log.debug("getting EleCampo instance with id: " + id);
		try {
			EleCampo instance = (EleCampo) getSession().get(
					"co.com.coomeva.ele.entidades.formulario.EleCampo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleCampo instance) {
		log.debug("finding EleCampo instance by example");
		try {
			List results = getSession().createCriteria(
					"co.com.coomeva.ele.entidades.formulario.EleCampo").add(
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
		log.debug("finding EleCampo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from EleCampo as model where model."
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

	public List findByTipoCampo(Object tipoCampo) {
		return findByProperty(TIPO_CAMPO, tipoCampo);
	}

	public List findByTipoDato(Object tipoDato) {
		return findByProperty(TIPO_DATO, tipoDato);
	}

	public List findByObligatorio(Object obligatorio) {
		return findByProperty(OBLIGATORIO, obligatorio);
	}

	public List findByDescripcion(Object descripcion) {
		return findByProperty(DESCRIPCION, descripcion);
	}

	public List findByAtributos(Object atributos) {
		return findByProperty(ATRIBUTOS, atributos);
	}

	public List findAll() {
		log.debug("finding all EleCampo instances");
		try {
			String queryString = "from EleCampo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleCampo merge(EleCampo detachedInstance) {
		log.debug("merging EleCampo instance");
		try {
			EleCampo result = (EleCampo) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleCampo instance) {
		log.debug("attaching dirty EleCampo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleCampo instance) {
		log.debug("attaching clean EleCampo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}