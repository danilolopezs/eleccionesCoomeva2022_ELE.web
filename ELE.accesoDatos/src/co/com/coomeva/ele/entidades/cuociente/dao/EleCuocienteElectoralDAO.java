package co.com.coomeva.ele.entidades.cuociente.dao;

import co.com.coomeva.ele.entidades.cuociente.EleCuocienteElectoral;
import co.com.coomeva.ele.entidades.planchas.dao.BaseHibernateDAO;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 	* A data access object (DAO) providing persistence and search support for EleCuocienteElectoral entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see co.com.coomeva.ele.entidades.cuociente.EleCuocienteElectoral
  * @author MyEclipse Persistence Tools 
 */

public class EleCuocienteElectoralDAO extends BaseHibernateDAO  {
		 private static final Log log = LogFactory.getLog(EleCuocienteElectoralDAO.class);
		//property constants
	public static final String PERIODO_ELECTORAL = "periodoElectoral";
	public static final String TOTAL_ASOC_HABILES = "totalAsocHabiles";
	public static final String TOTAL_ASOC_ESP_HABILES = "totalAsocEspHabiles";
	public static final String TOTAL_DELEGADOS_ELEGIR = "totalDelegadosElegir";
	public static final String TOTAL_DELEGADOS_ESPECIALES = "totalDelegadosEspeciales";
	public static final String FINAL_TOTAL_DELEGADOS_ELEGIR = "finalTotalDelegadosElegir";
	public static final String CUOCIENTE_ELECTORAL = "cuocienteElectoral";



    
    public void save(EleCuocienteElectoral transientInstance) {
        log.debug("saving EleCuocienteElectoral instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(EleCuocienteElectoral persistentInstance) {
        log.debug("deleting EleCuocienteElectoral instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public EleCuocienteElectoral findById( java.lang.Integer id) {
        log.debug("getting EleCuocienteElectoral instance with id: " + id);
        try {
            EleCuocienteElectoral instance = (EleCuocienteElectoral) getSession()
                    .get("co.com.coomeva.ele.entidades.cuociente.EleCuocienteElectoral", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(EleCuocienteElectoral instance) {
        log.debug("finding EleCuocienteElectoral instance by example");
        try {
            List results = getSession()
                    .createCriteria("co.com.coomeva.ele.entidades.cuociente.EleCuocienteElectoral")
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
		log.debug("finding EleCuocienteElectoral instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from EleCuocienteElectoral as model where model." + propertyName + "= ?";
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
	
	public List findByTotalAsocHabiles(Object totalAsocHabiles
	) {
		return findByProperty(TOTAL_ASOC_HABILES, totalAsocHabiles
		);
	}
	
	public List findByTotalDelegadosElegir(Object totalDelegadosElegir
	) {
		return findByProperty(TOTAL_DELEGADOS_ELEGIR, totalDelegadosElegir
		);
	}
	
	public List findByTotalDelegadosEspeciales(Object totalDelegadosEspeciales
	) {
		return findByProperty(TOTAL_DELEGADOS_ESPECIALES, totalDelegadosEspeciales
		);
	}
	
	public List findByFinalTotalDelegadosElegir(Object finalTotalDelegadosElegir
	) {
		return findByProperty(FINAL_TOTAL_DELEGADOS_ELEGIR, finalTotalDelegadosElegir
		);
	}
	
	public List findByCuocienteElectoral(Object cuocienteElectoral
	) {
		return findByProperty(CUOCIENTE_ELECTORAL, cuocienteElectoral
		);
	}
	

	public List findAll() {
		log.debug("finding all EleCuocienteElectoral instances");
		try {
			String queryString = "from EleCuocienteElectoral";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public EleCuocienteElectoral merge(EleCuocienteElectoral detachedInstance) {
        log.debug("merging EleCuocienteElectoral instance");
        try {
            EleCuocienteElectoral result = (EleCuocienteElectoral) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(EleCuocienteElectoral instance) {
        log.debug("attaching dirty EleCuocienteElectoral instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(EleCuocienteElectoral instance) {
        log.debug("attaching clean EleCuocienteElectoral instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}