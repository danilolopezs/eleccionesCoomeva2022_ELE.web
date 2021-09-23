package co.com.coomeva.ele.entidades.planchas.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleAsocOtrasEntElect;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleAsocOtrasEntElect entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.elecdb.EleAsocOtrasEntElect
 * @author MyEclipse Persistence Tools
 */

public class EleAsocOtrasEntElectDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(EleAsocOtrasEntElectDAO.class);
	// property constants
	public static final String TIPO_ENT = "tipoEnt";
	public static final String USUARIO = "usuario";

	public void save(EleAsocOtrasEntElect transientInstance) {
		log.debug("saving EleAsocOtrasEntElect instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleAsocOtrasEntElect persistentInstance) {
		log.debug("deleting EleAsocOtrasEntElect instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleAsocOtrasEntElect findById(java.lang.Long id) {
		log.debug("getting EleAsocOtrasEntElect instance with id: " + id);
		try {
			EleAsocOtrasEntElect instance = (EleAsocOtrasEntElect) getSession()
					.get("co.com.elecdb.EleAsocOtrasEntElect", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleAsocOtrasEntElect instance) {
		log.debug("finding EleAsocOtrasEntElect instance by example");
		try {
			List results = getSession()
					.createCriteria("co.com.elecdb.EleAsocOtrasEntElect")
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
		log.debug("finding EleAsocOtrasEntElect instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EleAsocOtrasEntElect as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTipoEnt(Object tipoEnt) {
		return findByProperty(TIPO_ENT, tipoEnt);
	}

	public List findByUsuario(Object usuario) {
		return findByProperty(USUARIO, usuario);
	}

	public List findAll() {
		log.debug("finding all EleAsocOtrasEntElect instances");
		try {
			String queryString = "from EleAsocOtrasEntElect";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleAsocOtrasEntElect merge(EleAsocOtrasEntElect detachedInstance) {
		log.debug("merging EleAsocOtrasEntElect instance");
		try {
			EleAsocOtrasEntElect result = (EleAsocOtrasEntElect) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleAsocOtrasEntElect instance) {
		log.debug("attaching dirty EleAsocOtrasEntElect instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleAsocOtrasEntElect instance) {
		log.debug("attaching clean EleAsocOtrasEntElect instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	
	public void limpiarTablaOtrasEntElect(){
    	try {
            String queryString = "delete from EleAsocOtrasEntElect model";
            getSession().createQuery(queryString).executeUpdate();

        } catch (RuntimeException re) {
            throw re;
        }
    }

	
}