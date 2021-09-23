package co.com.coomeva.ele.entidades.planchas.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleAsocSancionados5Annos;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleAsocSancionados5Annos entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see co.com.elecdb.EleAsocSancionados5Annos
 * @author MyEclipse Persistence Tools
 */

public class EleAsocSancionados5AnnosDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(EleAsocSancionados5AnnosDAO.class);
	// property constants
	public static final String USUARIO = "usuario";

	public void save(EleAsocSancionados5Annos transientInstance) {
		log.debug("saving EleAsocSancionados5Annos instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleAsocSancionados5Annos persistentInstance) {
		log.debug("deleting EleAsocSancionados5Annos instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleAsocSancionados5Annos findById(java.lang.Long id) {
		log.debug("getting EleAsocSancionados5Annos instance with id: " + id);
		try {
			EleAsocSancionados5Annos instance = (EleAsocSancionados5Annos) getSession()
					.get("co.com.elecdb.EleAsocSancionados5Annos", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleAsocSancionados5Annos instance) {
		log.debug("finding EleAsocSancionados5Annos instance by example");
		try {
			List results = getSession()
					.createCriteria("co.com.elecdb.EleAsocSancionados5Annos")
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
		log.debug("finding EleAsocSancionados5Annos instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EleAsocSancionados5Annos as model where model."
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

	public List findAll() {
		log.debug("finding all EleAsocSancionados5Annos instances");
		try {
			String queryString = "from EleAsocSancionados5Annos";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleAsocSancionados5Annos merge(
			EleAsocSancionados5Annos detachedInstance) {
		log.debug("merging EleAsocSancionados5Annos instance");
		try {
			EleAsocSancionados5Annos result = (EleAsocSancionados5Annos) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleAsocSancionados5Annos instance) {
		log.debug("attaching dirty EleAsocSancionados5Annos instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleAsocSancionados5Annos instance) {
		log.debug("attaching clean EleAsocSancionados5Annos instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void limpiarTablaSancionado5Annos(){
    	try {
            String queryString = "delete from EleAsocSancionados5Annos model";
            getSession().createQuery(queryString).executeUpdate();

        } catch (RuntimeException re) {
            throw re;
        }
    }

}