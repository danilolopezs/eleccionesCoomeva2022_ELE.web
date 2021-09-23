package co.com.coomeva.ele.entidades.planchas.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleAuditoriaExcepcion;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleAuditoriaExcepcion entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.coomeva.elecciones.EleAuditoriaExcepcion
 * @author MyEclipse Persistence Tools
 */

public class EleAuditoriaExcepcionDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(EleAuditoriaExcepcionDAO.class);
	// property constants
	public static final String USUARIO = "usuario";
	public static final String MOTIVO_SUSPENSION = "motivoSuspension";
	public static final String TIPO_EXCEPCION = "tipoExcepcion";

	public void save(EleAuditoriaExcepcion transientInstance) {
		log.debug("saving EleAuditoriaExcepcion instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleAuditoriaExcepcion persistentInstance) {
		log.debug("deleting EleAuditoriaExcepcion instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleAuditoriaExcepcion findById(java.lang.Long id) {
		log.debug("getting EleAuditoriaExcepcion instance with id: " + id);
		try {
			EleAuditoriaExcepcion instance = (EleAuditoriaExcepcion) getSession()
					.get("co.com.coomeva.elecciones.EleAuditoriaExcepcion", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleAuditoriaExcepcion instance) {
		log.debug("finding EleAuditoriaExcepcion instance by example");
		try {
			List results = getSession().createCriteria(
					"co.com.coomeva.elecciones.EleAuditoriaExcepcion").add(
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
		log.debug("finding EleAuditoriaExcepcion instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EleAuditoriaExcepcion as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUsuario(Object usuario) {
		return findByProperty(USUARIO, usuario);
	}

	public List findByMotivoSuspension(Object motivoSuspension) {
		return findByProperty(MOTIVO_SUSPENSION, motivoSuspension);
	}

	public List findByTipoExcepcion(Object tipoExcepcion) {
		return findByProperty(TIPO_EXCEPCION, tipoExcepcion);
	}

	public List findAll() {
		log.debug("finding all EleAuditoriaExcepcion instances");
		try {
			String queryString = "from EleAuditoriaExcepcion";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleAuditoriaExcepcion merge(EleAuditoriaExcepcion detachedInstance) {
		log.debug("merging EleAuditoriaExcepcion instance");
		try {
			EleAuditoriaExcepcion result = (EleAuditoriaExcepcion) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleAuditoriaExcepcion instance) {
		log.debug("attaching dirty EleAuditoriaExcepcion instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleAuditoriaExcepcion instance) {
		log.debug("attaching clean EleAuditoriaExcepcion instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}