package co.com.coomeva.ele.entidades.habilidad.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import co.com.coomeva.ele.entidades.habilidad.EleInhabilidad;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.planchas.HibernateSessionFactoryPlanchas;


/**
 * A data access object (DAO) providing persistence and search support for
 * EleInhabilidad entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class EleInhabilidadDAO implements IEleInhabilidadDAO {
    private static final Log log = LogFactory.getLog(EleInhabilidadDAO.class);

    // property constants
    //public static final Long  CONSECUTIVOINHABILIDAD = "consecutivoInhabilidad";
    public static final String CONSECUTIVOINHABILIDAD = "consecutivoInhabilidad";

    //public static final String  OBSERVACIONES = "observaciones";
    public static final String OBSERVACIONES = "observaciones";

    private Session getSession() {
        return HibernateSessionFactoryElecciones2012.getSession();
    }

    /**
    *
    * @param Instance
    *            EleInhabilidad Instance to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(EleInhabilidad instance) {
        log.debug("saving EleInhabilidad instance");

        try {
            getSession().save(instance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    /**
    * @param Instance
    *            EleInhabilidad Instance to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(EleInhabilidad instance) {
        log.debug("deleting EleInhabilidad instance");

        try {
            getSession().delete(instance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    /**
    *
    * @param Instance
    *            EleInhabilidad Instance to update
    * @throws RuntimeException
    *             when the operation fails
    */
    public void update(EleInhabilidad instance) {
        log.debug("updating EleInhabilidad instance");

        try {
            getSession().update(instance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public EleInhabilidad findById(Long id) {
        log.debug("finding EleInhabilidad instance with id: " + id);

        try {
            EleInhabilidad instance = (EleInhabilidad) getSession()
                                                           .get(EleInhabilidad.class,
                    id);

            return instance;
        } catch (RuntimeException re) {
            log.error("finding EleInhabilidad failed", re);
            throw re;
        }
    }

    public List<EleInhabilidad> findByExample(EleInhabilidad instance) {
        log.debug("finding EleInhabilidad instance by example");

        try {
            List results = getSession().createCriteria("EleInhabilidad")
                               .add(Example.create(instance)).list();
            log.debug("find by example successful, result size: " +
                results.size());

            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    /**
    * Find all  EleInhabilidad entities with a specific property value.
    *
    * @param value
    *            the property value to match
    * @param propertyName
    *            the property to search in the instance
    * @return List< EleInhabilidad> found by query
        */
    public List<EleInhabilidad> findByProperty(String propertyName, Object value) {
        log.debug("finding EleInhabilidad instance with property: " +
            propertyName + ", value: " + value);

        try {
            String queryString = "from EleInhabilidad as model where model." +
                propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<EleInhabilidad> findByConsecutivoInhabilidad(
        Object consecutivoInhabilidad) {
        return findByProperty(CONSECUTIVOINHABILIDAD, consecutivoInhabilidad);
    }

    public List<EleInhabilidad> findByObservaciones(Object observaciones) {
        return findByProperty(OBSERVACIONES, observaciones);
    }

    /**
    * Find all EleInhabilidad entities.
    *
    * @return List<EleInhabilidad> all EleInhabilidad instances
    */
    public List<EleInhabilidad> findAll() {
        log.debug("finding all EleInhabilidad instances");

        try {
            String queryString = "from EleInhabilidad";
            Query queryObject = getSession().createQuery(queryString);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<EleInhabilidad> findByCriteria(String whereCondition) {
        log.debug("finding EleInhabilidad " + whereCondition);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from EleInhabilidad model " +
                where;
            Query query = getSession().createQuery(queryString);
            List<EleInhabilidad> entitiesList = query.list();

            return entitiesList;
        } catch (RuntimeException re) {
            log.error("find By Criteria in EleInhabilidad failed", re);
            throw re;
        }
    }

    public List<EleInhabilidad> findPageEleInhabilidad(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        log.debug("finding EleInhabilidad findPageEleInhabilidad");

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from EleInhabilidad model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from EleInhabilidad model";

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    public Long findTotalNumberEleInhabilidad() {
        log.debug("finding EleInhabilidad count");

        try {
            String queryString = "select count(*) from EleInhabilidad model";
            Query queryObject = getSession().createQuery(queryString);

            return (Long) queryObject.list().get(0);
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
