package co.com.coomeva.ele.entidades.cuociente.dao;

import co.com.coomeva.ele.entidades.cuociente.EleCuocienteRegional;
import co.com.coomeva.ele.entidades.planchas.dao.BaseHibernateDAO;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 	* A data access object (DAO) providing persistence and search support for EleCuocienteRegional entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see co.com.coomeva.ele.entidades.cuociente.EleCuocienteRegional
  * @author MyEclipse Persistence Tools 
 */

public class EleCuocienteRegionalDAO extends BaseHibernateDAO  {
		 private static final Log log = LogFactory.getLog(EleCuocienteRegionalDAO.class);
		//property constants
	public static final String PERIODO_ELECTORAL = "periodoElectoral";
	public static final String COD_REGIONAL = "codRegional";
	public static final String TOTAL_DELEGADOS = "totalDelegados";
	public static final String RELACION_DELEGADOS = "relacionDelegados";



    
    public void save(EleCuocienteRegional transientInstance) {
        log.debug("saving EleCuocienteRegional instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(EleCuocienteRegional persistentInstance) {
        log.debug("deleting EleCuocienteRegional instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public EleCuocienteRegional findById( java.lang.Integer id) {
        log.debug("getting EleCuocienteRegional instance with id: " + id);
        try {
            EleCuocienteRegional instance = (EleCuocienteRegional) getSession()
                    .get("co.com.coomeva.ele.entidades.cuociente.EleCuocienteRegional", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(EleCuocienteRegional instance) {
        log.debug("finding EleCuocienteRegional instance by example");
        try {
            List results = getSession()
                    .createCriteria("co.com.coomeva.ele.entidades.cuociente.EleCuocienteRegional")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding EleCuocienteRegional instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from EleCuocienteRegional as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByPeriodoElectoral(Object periodoElectoral
	) {
		return findByProperty(PERIODO_ELECTORAL, periodoElectoral
		);
	}
	
	public List findByCodRegional(Object codRegional
	) {
		return findByProperty(COD_REGIONAL, codRegional
		);
	}
	
	public List findByTotalDelegados(Object totalDelegados
	) {
		return findByProperty(TOTAL_DELEGADOS, totalDelegados
		);
	}
	
	public List findByRelacionDelegados(Object relacionDelegados
	) {
		return findByProperty(RELACION_DELEGADOS, relacionDelegados
		);
	}
	

	public List findAll() {
		log.debug("finding all EleCuocienteRegional instances");
		try {
			String queryString = "from EleCuocienteRegional";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public EleCuocienteRegional merge(EleCuocienteRegional detachedInstance) {
        log.debug("merging EleCuocienteRegional instance");
        try {
            EleCuocienteRegional result = (EleCuocienteRegional) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(EleCuocienteRegional instance) {
        log.debug("attaching dirty EleCuocienteRegional instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(EleCuocienteRegional instance) {
        log.debug("attaching clean EleCuocienteRegional instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}