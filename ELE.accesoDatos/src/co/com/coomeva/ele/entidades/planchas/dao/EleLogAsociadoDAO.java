package co.com.coomeva.ele.entidades.planchas.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleLogAsociado;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleLogAsociado entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.elecdb.EleLogAsociado
 * @author MyEclipse Persistence Tools
 */

public class EleLogAsociadoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(EleLogAsociadoDAO.class);
	// property constants
	public static final String TIPO_TRN = "tipoTrn";
	public static final String IP_TRN = "ipTrn";
	public static final String NRO_IDENTIFICACION = "nroIdentificacion";
	public static final String DESCRIPCION = "descripcion";

	public void save(EleLogAsociado transientInstance) {
		log.debug("saving EleLogAsociado instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleLogAsociado persistentInstance) {
		log.debug("deleting EleLogAsociado instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleLogAsociado findById(java.lang.Integer id) {
		log.debug("getting EleLogAsociado instance with id: " + id);
		try {
			EleLogAsociado instance = (EleLogAsociado) getSession().get(
					"co.com.elecdb.EleLogAsociado", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleLogAsociado instance) {
		log.debug("finding EleLogAsociado instance by example");
		try {
			List results = getSession()
					.createCriteria("co.com.elecdb.EleLogAsociado")
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
		log.debug("finding EleLogAsociado instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EleLogAsociado as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTipoTrn(Object tipoTrn) {
		return findByProperty(TIPO_TRN, tipoTrn);
	}

	public List findByIpTrn(Object ipTrn) {
		return findByProperty(IP_TRN, ipTrn);
	}

	public List findByNroIdentificacion(Object nroIdentificacion) {
		return findByProperty(NRO_IDENTIFICACION, nroIdentificacion);
	}

	public List findByDescripcion(Object descripcion) {
		return findByProperty(DESCRIPCION, descripcion);
	}

	public List findAll() {
		log.debug("finding all EleLogAsociado instances");
		try {
			String queryString = "from EleLogAsociado";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleLogAsociado merge(EleLogAsociado detachedInstance) {
		log.debug("merging EleLogAsociado instance");
		try {
			EleLogAsociado result = (EleLogAsociado) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleLogAsociado instance) {
		log.debug("attaching dirty EleLogAsociado instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleLogAsociado instance) {
		log.debug("attaching clean EleLogAsociado instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}