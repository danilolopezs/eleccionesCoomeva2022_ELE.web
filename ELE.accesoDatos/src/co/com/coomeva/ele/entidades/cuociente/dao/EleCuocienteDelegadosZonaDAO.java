package co.com.coomeva.ele.entidades.cuociente.dao;

import co.com.coomeva.ele.entidades.cuociente.EleCuocienteDelegadosZona;
import co.com.coomeva.ele.entidades.planchas.dao.BaseHibernateDAO;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 	* A data access object (DAO) providing persistence and search support for EleCuocienteDelegadosZona entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see co.com.coomeva.ele.entidades.cuociente.EleCuocienteDelegadosZona
  * @author MyEclipse Persistence Tools 
 */

public class EleCuocienteDelegadosZonaDAO extends BaseHibernateDAO  {
		 private static final Log log = LogFactory.getLog(EleCuocienteDelegadosZonaDAO.class);
		//property constants
	public static final String PERIODO_ELECTORAL = "periodoElectoral";
	public static final String COD_REGIONAL = "codRegional";
	public static final String COD_ZONA = "codZona";
	public static final String SUMA_HABILES = "sumaHabiles";
	public static final String SUMA_NOVEDADES = "sumaNovedades";
	public static final String SUMA_ESP_HABILES = "sumaEspHabiles";
	public static final String SUMA_NOVEDADES_ESP = "sumaNovedadesEsp";
	public static final String SUMA_TOTAL_HABILES = "sumaTotalHabiles";
	
	
	public static final String DELEGADOS = "delegados";
	public static final String DELEGADOS_DIRECTOS = "delegadosDirectos";
	public static final String FRACCION = "fraccion";
	public static final String DELEGADOS_RECIDUO = "delegadosReciduo";
	public static final String DISTRIBUIDOS_RESTANTES = "distribuidosRestantes";
	public static final String TOTAL_DELEGADOS_ZONA = "totalDelegadosZona";



    
    public void save(EleCuocienteDelegadosZona transientInstance) {
        log.debug("saving EleCuocienteDelegadosZona instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(EleCuocienteDelegadosZona persistentInstance) {
        log.debug("deleting EleCuocienteDelegadosZona instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public EleCuocienteDelegadosZona findById( java.lang.Integer id) {
        log.debug("getting EleCuocienteDelegadosZona instance with id: " + id);
        try {
            EleCuocienteDelegadosZona instance = (EleCuocienteDelegadosZona) getSession()
                    .get("co.com.coomeva.ele.entidades.cuociente.EleCuocienteDelegadosZona", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(EleCuocienteDelegadosZona instance) {
        log.debug("finding EleCuocienteDelegadosZona instance by example");
        try {
            List results = getSession()
                    .createCriteria("co.com.coomeva.ele.entidades.cuociente.EleCuocienteDelegadosZona")
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
      log.debug("finding EleCuocienteDelegadosZona instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from EleCuocienteDelegadosZona as model where model." 
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
	
	public List findByCodZona(Object codZona
	) {
		return findByProperty(COD_ZONA, codZona
		);
	}
	
	public List findBySumaHabiles(Object sumaHabiles
	) {
		return findByProperty(SUMA_HABILES, sumaHabiles
		);
	}
	
	public List findByDelegados(Object delegados
	) {
		return findByProperty(DELEGADOS, delegados
		);
	}
	
	public List findByDelegadosDirectos(Object delegadosDirectos
	) {
		return findByProperty(DELEGADOS_DIRECTOS, delegadosDirectos
		);
	}
	
	public List findByFraccion(Object fraccion
	) {
		return findByProperty(FRACCION, fraccion
		);
	}
	
	public List findByDelegadosReciduo(Object delegadosReciduo
	) {
		return findByProperty(DELEGADOS_RECIDUO, delegadosReciduo
		);
	}
	
	public List findByDistribuidosRestantes(Object distribuidosRestantes
	) {
		return findByProperty(DISTRIBUIDOS_RESTANTES, distribuidosRestantes
		);
	}
	
	public List findByTotalDelegadosZona(Object totalDelegadosZona
	) {
		return findByProperty(TOTAL_DELEGADOS_ZONA, totalDelegadosZona
		);
	}
	

	public List findAll() {
		log.debug("finding all EleCuocienteDelegadosZona instances");
		try {
			String queryString = "from EleCuocienteDelegadosZona";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public EleCuocienteDelegadosZona merge(EleCuocienteDelegadosZona detachedInstance) {
        log.debug("merging EleCuocienteDelegadosZona instance");
        try {
            EleCuocienteDelegadosZona result = (EleCuocienteDelegadosZona) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(EleCuocienteDelegadosZona instance) {
        log.debug("attaching dirty EleCuocienteDelegadosZona instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(EleCuocienteDelegadosZona instance) {
        log.debug("attaching clean EleCuocienteDelegadosZona instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}