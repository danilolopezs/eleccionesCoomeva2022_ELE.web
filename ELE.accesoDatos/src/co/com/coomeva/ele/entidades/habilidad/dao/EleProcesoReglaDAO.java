package co.com.coomeva.ele.entidades.habilidad.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import co.com.coomeva.ele.entidades.habilidad.EleProcesoRegla;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.planchas.HibernateSessionFactoryPlanchas;


/**
 * A data access object (DAO) providing persistence and search support for
 * EleProcesoRegla entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class EleProcesoReglaDAO implements IEleProcesoReglaDAO {
    private static final Log log = LogFactory.getLog(EleProcesoReglaDAO.class);

    // property constants
    //public static final Long  CONSECUTIVOPROREGLA = "consecutivoProRegla";
    public static final String CONSECUTIVOPROREGLA = "consecutivoProRegla";

    //public static final String  ESTADOPROGRAMACION = "estadoProgramacion";
    public static final String ESTADOPROGRAMACION = "estadoProgramacion";

    //public static final Date  FECHAFINEJECUCION = "fechaFinEjecucion";
    public static final String FECHAFINEJECUCION = "fechaFinEjecucion";

    //public static final Date  FECHAINICIOEJECUCION = "fechaInicioEjecucion";
    public static final String FECHAINICIOEJECUCION = "fechaInicioEjecucion";

    //public static final String  USUARIOREGISTRA = "usuarioRegistra";
    public static final String USUARIOREGISTRA = "usuarioRegistra";

    private Session getSession() {
        return HibernateSessionFactoryElecciones2012.getSession();
    }

    /**
    *
    * @param Instance
    *            EleProcesoRegla Instance to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(EleProcesoRegla instance) {
        log.debug("saving EleProcesoRegla instance");

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
    *            EleProcesoRegla Instance to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(EleProcesoRegla instance) {
        log.debug("deleting EleProcesoRegla instance");

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
    *            EleProcesoRegla Instance to update
    * @throws RuntimeException
    *             when the operation fails
    */
    public void update(EleProcesoRegla instance) {
        log.debug("updating EleProcesoRegla instance");

        try {
            getSession().update(instance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public EleProcesoRegla findById(Long id) {
        log.debug("finding EleProcesoRegla instance with id: " + id);

        try {
            EleProcesoRegla instance = (EleProcesoRegla) getSession()
                                                             .get(EleProcesoRegla.class,
                    id);

            return instance;
        } catch (RuntimeException re) {
            log.error("finding EleProcesoRegla failed", re);
            throw re;
        }
    }

    public List<EleProcesoRegla> findByExample(EleProcesoRegla instance) {
        log.debug("finding EleProcesoRegla instance by example");

        try {
            List results = getSession().createCriteria("EleProcesoRegla")
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
    * Find all  EleProcesoRegla entities with a specific property value.
    *
    * @param value
    *            the property value to match
    * @param propertyName
    *            the property to search in the instance
    * @return List< EleProcesoRegla> found by query
        */
    public List<EleProcesoRegla> findByProperty(String propertyName,
        Object value) {
        log.debug("finding EleProcesoRegla instance with property: " +
            propertyName + ", value: " + value);

        try {
            String queryString = "from EleProcesoRegla as model where model." +
                propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<EleProcesoRegla> findByConsecutivoProRegla(
        Object consecutivoProRegla) {
        return findByProperty(CONSECUTIVOPROREGLA, consecutivoProRegla);
    }

    public List<EleProcesoRegla> findByEstadoProgramacion(
        Object estadoProgramacion) {
        return findByProperty(ESTADOPROGRAMACION, estadoProgramacion);
    }

    public List<EleProcesoRegla> findByFechaFinEjecucion(
        Object fechaFinEjecucion) {
        return findByProperty(FECHAFINEJECUCION, fechaFinEjecucion);
    }

    public List<EleProcesoRegla> findByFechaInicioEjecucion(
        Object fechaInicioEjecucion) {
        return findByProperty(FECHAINICIOEJECUCION, fechaInicioEjecucion);
    }

    public List<EleProcesoRegla> findByUsuarioRegistra(Object usuarioRegistra) {
        return findByProperty(USUARIOREGISTRA, usuarioRegistra);
    }

    /**
    * Find all EleProcesoRegla entities.
    *
    * @return List<EleProcesoRegla> all EleProcesoRegla instances
    */
    public List<EleProcesoRegla> findAll() {
        log.debug("finding all EleProcesoRegla instances");

        try {
            String queryString = "from EleProcesoRegla";
            Query queryObject = getSession().createQuery(queryString);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<EleProcesoRegla> findByCriteria(String whereCondition) {
        log.debug("finding EleProcesoRegla " + whereCondition);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from EleProcesoRegla model " +
                where;
            Query query = getSession().createQuery(queryString);
            List<EleProcesoRegla> entitiesList = query.list();

            return entitiesList;
        } catch (RuntimeException re) {
            log.error("find By Criteria in EleProcesoRegla failed", re);
            throw re;
        }
    }

    public List<EleProcesoRegla> findPageEleProcesoRegla(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) {
        log.debug("finding EleProcesoRegla findPageEleProcesoRegla");

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from EleProcesoRegla model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from EleProcesoRegla model";

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    public Long findTotalNumberEleProcesoRegla() {
        log.debug("finding EleProcesoRegla count");

        try {
            String queryString = "select count(*) from EleProcesoRegla model";
            Query queryObject = getSession().createQuery(queryString);

            return (Long) queryObject.list().get(0);
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
