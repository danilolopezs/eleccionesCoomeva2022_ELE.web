package co.com.coomeva.ele.entidades.habilidad.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import co.com.coomeva.ele.entidades.habilidad.EleNovedad;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.planchas.HibernateSessionFactoryPlanchas;


/**
 * A data access object (DAO) providing persistence and search support for
 * EleNovedad entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class EleNovedadDAO implements IEleNovedadDAO {
    private static final Log log = LogFactory.getLog(EleNovedadDAO.class);

    // property constants
    //public static final Long  CONSECUTIVONOVEDAD = "consecutivoNovedad";
    public static final String CONSECUTIVONOVEDAD = "consecutivoNovedad";

    //public static final String  ESTADOHABILIDAD = "estadoHabilidad";
    public static final String ESTADOHABILIDAD = "estadoHabilidad";

    //public static final Date  FECHAREGISTRO = "fechaRegistro";
    public static final String FECHAREGISTRO = "fechaRegistro";

    private Session getSession() {
        return HibernateSessionFactoryElecciones2012.getSession();
    }

    /**
    *
    * @param Instance
    *            EleNovedad Instance to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(EleNovedad instance) {
        log.debug("saving EleNovedad instance");

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
    *            EleNovedad Instance to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(EleNovedad instance) {
        log.debug("deleting EleNovedad instance");

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
    *            EleNovedad Instance to update
    * @throws RuntimeException
    *             when the operation fails
    */
    public void update(EleNovedad instance) {
        log.debug("updating EleNovedad instance");

        try {
            getSession().update(instance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public EleNovedad findById(Long id) {
        log.debug("finding EleNovedad instance with id: " + id);

        try {
            EleNovedad instance = (EleNovedad) getSession()
                                                   .get(EleNovedad.class, id);

            return instance;
        } catch (RuntimeException re) {
            log.error("finding EleNovedad failed", re);
            throw re;
        }
    }

    public List<EleNovedad> findByExample(EleNovedad instance) {
        log.debug("finding EleNovedad instance by example");

        try {
            List results = getSession().createCriteria("EleNovedad")
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
    * Find all  EleNovedad entities with a specific property value.
    *
    * @param value
    *            the property value to match
    * @param propertyName
    *            the property to search in the instance
    * @return List< EleNovedad> found by query
        */
    public List<EleNovedad> findByProperty(String propertyName, Object value) {
        log.debug("finding EleNovedad instance with property: " + propertyName +
            ", value: " + value);

        try {
            String queryString = "from EleNovedad as model where model." +
                propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<EleNovedad> findByConsecutivoNovedad(Object consecutivoNovedad) {
        return findByProperty(CONSECUTIVONOVEDAD, consecutivoNovedad);
    }

    public List<EleNovedad> findByEstadoHabilidad(Object estadoHabilidad) {
        return findByProperty(ESTADOHABILIDAD, estadoHabilidad);
    }

    public List<EleNovedad> findByFechaRegistro(Object fechaRegistro) {
        return findByProperty(FECHAREGISTRO, fechaRegistro);
    }

    /**
    * Find all EleNovedad entities.
    *
    * @return List<EleNovedad> all EleNovedad instances
    */
    public List<EleNovedad> findAll() {
        log.debug("finding all EleNovedad instances");

        try {
            String queryString = "from EleNovedad";
            Query queryObject = getSession().createQuery(queryString);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<EleNovedad> findByCriteria(String whereCondition) {
        log.debug("finding EleNovedad " + whereCondition);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from EleNovedad model " +
                where;
            Query query = getSession().createQuery(queryString);
            List<EleNovedad> entitiesList = query.list();

            return entitiesList;
        } catch (RuntimeException re) {
            log.error("find By Criteria in EleNovedad failed", re);
            throw re;
        }
    }

    public List<EleNovedad> findPageEleNovedad(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        log.debug("finding EleNovedad findPageEleNovedad");

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from EleNovedad model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from EleNovedad model";

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    public Long findTotalNumberEleNovedad() {
        log.debug("finding EleNovedad count");

        try {
            String queryString = "select count(*) from EleNovedad model";
            Query queryObject = getSession().createQuery(queryString);

            return (Long) queryObject.list().get(0);
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
