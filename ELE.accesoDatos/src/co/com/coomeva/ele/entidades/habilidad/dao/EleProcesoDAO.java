package co.com.coomeva.ele.entidades.habilidad.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import co.com.coomeva.ele.entidades.habilidad.EleProceso;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;


/**
 * A data access object (DAO) providing persistence and search support for
 * EleProceso entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class EleProcesoDAO implements IEleProcesoDAO {
    private static final Log log = LogFactory.getLog(EleProcesoDAO.class);

    // property constants
    //public static final Long  CODIGOPROCESO = "codigoProceso";
    public static final String CODIGOPROCESO = "codigoProceso";

    //public static final String  ESTADOPROCESO = "estadoProceso";
    public static final String ESTADOPROCESO = "estadoProceso";

    //public static final Date  FECHAPROGRAMACION = "fechaProgramacion";
    public static final String FECHAPROGRAMACION = "fechaProgramacion";

    private Session getSession() {
        return HibernateSessionFactoryElecciones2012.getSession();
    }

    /**
    *
    * @param Instance
    *            EleProceso Instance to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(EleProceso instance) {
        log.debug("saving EleProceso instance");

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
    *            EleProceso Instance to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(EleProceso instance) {
        log.debug("deleting EleProceso instance");

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
    *            EleProceso Instance to update
    * @throws RuntimeException
    *             when the operation fails
    */
    public void update(EleProceso instance) {
        log.debug("updating EleProceso instance");

        try {
            getSession().update(instance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public EleProceso findById(Long id) {
        log.debug("finding EleProceso instance with id: " + id);

        try {
            EleProceso instance = (EleProceso) getSession()
                                                   .get(EleProceso.class, id);

            return instance;
        } catch (RuntimeException re) {
            log.error("finding EleProceso failed", re);
            throw re;
        }
    }

    public List<EleProceso> findByExample(EleProceso instance) {
        log.debug("finding EleProceso instance by example");

        try {
            List results = getSession().createCriteria("co.com.coomeva.ele.entidades.habilidad.EleProceso")
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
    * Find all  EleProceso entities with a specific property value.
    *
    * @param value
    *            the property value to match
    * @param propertyName
    *            the property to search in the instance
    * @return List< EleProceso> found by query
        */
    public List<EleProceso> findByProperty(String propertyName, Object value) {
        log.debug("finding EleProceso instance with property: " + propertyName +
            ", value: " + value);

        try {
            String queryString = "from EleProceso as model where model." +
                propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<EleProceso> findByCodigoProceso(Object codigoProceso) {
        return findByProperty(CODIGOPROCESO, codigoProceso);
    }

    public List<EleProceso> findByEstadoProceso(Object estadoProceso) {
        return findByProperty(ESTADOPROCESO, estadoProceso);
    }

    public List<EleProceso> findByFechaProgramacion(Object fechaProgramacion) {
        return findByProperty(FECHAPROGRAMACION, fechaProgramacion);
    }

    /**
    * Find all EleProceso entities.
    *
    * @return List<EleProceso> all EleProceso instances
    */
    public List<EleProceso> findAll() {
        log.debug("finding all EleProceso instances");

        try {
            String queryString = "from EleProceso";
            Query queryObject = getSession().createQuery(queryString);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<EleProceso> findByCriteria(String whereCondition) {
        log.debug("finding EleProceso " + whereCondition);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from EleProceso model " +
                where;
            Query query = getSession().createQuery(queryString);
            List<EleProceso> entitiesList = query.list();

            return entitiesList;
        } catch (RuntimeException re) {
            log.error("find By Criteria in EleProceso failed", re);
            throw re;
        }
    }

    public List<EleProceso> findPageEleProceso(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        log.debug("finding EleProceso findPageEleProceso");

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from EleProceso model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from EleProceso model";

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    public Long findTotalNumberEleProceso() {
        log.debug("finding EleProceso count");

        try {
            String queryString = "select count(*) from EleProceso model";
            Query queryObject = getSession().createQuery(queryString);

            return (Long) queryObject.list().get(0);
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
