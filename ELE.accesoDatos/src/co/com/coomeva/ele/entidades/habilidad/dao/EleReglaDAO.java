package co.com.coomeva.ele.entidades.habilidad.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import co.com.coomeva.ele.entidades.habilidad.EleRegla;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.planchas.HibernateSessionFactoryPlanchas;


/**
 * A data access object (DAO) providing persistence and search support for
 * EleRegla entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class EleReglaDAO implements IEleReglaDAO {
    private static final Log log = LogFactory.getLog(EleReglaDAO.class);

    // property constants
    //public static final Long  CODIGOREGLA = "codigoRegla";
    public static final String CODIGOREGLA = "codigoRegla";

    //public static final String  DESCRIPCIONREGLA = "descripcionRegla";
    public static final String DESCRIPCIONREGLA = "descripcionRegla";

    //public static final String  ESTADOREGLA = "estadoRegla";
    public static final String ESTADOREGLA = "estadoRegla";

    private Session getSession() {
        return HibernateSessionFactoryElecciones2012.getSession();
    }

    /**
    *
    * @param Instance
    *            EleRegla Instance to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(EleRegla instance) {
        log.debug("saving EleRegla instance");

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
    *            EleRegla Instance to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(EleRegla instance) {
        log.debug("deleting EleRegla instance");

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
    *            EleRegla Instance to update
    * @throws RuntimeException
    *             when the operation fails
    */
    public void update(EleRegla instance) {
        log.debug("updating EleRegla instance");

        try {
            getSession().update(instance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public EleRegla findById(Long id) {
        log.debug("finding EleRegla instance with id: " + id);

        try {
            EleRegla instance = (EleRegla) getSession().get(EleRegla.class, id);

            return instance;
        } catch (RuntimeException re) {
            log.error("finding EleRegla failed", re);
            throw re;
        }
    }

    public List<EleRegla> findByExample(EleRegla instance) {
        log.debug("finding EleRegla instance by example");

        try {
            List results = getSession().createCriteria("EleRegla")
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
    * Find all  EleRegla entities with a specific property value.
    *
    * @param value
    *            the property value to match
    * @param propertyName
    *            the property to search in the instance
    * @return List< EleRegla> found by query
        */
    public List<EleRegla> findByProperty(String propertyName, Object value) {
        log.debug("finding EleRegla instance with property: " + propertyName +
            ", value: " + value);

        try {
            String queryString = "from EleRegla as model where model." +
                propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<EleRegla> findByCodigoRegla(Object codigoRegla) {
        return findByProperty(CODIGOREGLA, codigoRegla);
    }

    public List<EleRegla> findByDescripcionRegla(Object descripcionRegla) {
        return findByProperty(DESCRIPCIONREGLA, descripcionRegla);
    }

    public List<EleRegla> findByEstadoRegla(Object estadoRegla) {
        return findByProperty(ESTADOREGLA, estadoRegla);
    }

    /**
    * Find all EleRegla entities.
    *
    * @return List<EleRegla> all EleRegla instances
    */
    public List<EleRegla> findAll() {
        log.debug("finding all EleRegla instances");

        try {
            String queryString = "from EleRegla";
            Query queryObject = getSession().createQuery(queryString);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<EleRegla> findByCriteria(String whereCondition) {
        log.debug("finding EleRegla " + whereCondition);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from EleRegla model " +
                where;
            Query query = getSession().createQuery(queryString);
            List<EleRegla> entitiesList = query.list();

            return entitiesList;
        } catch (RuntimeException re) {
            log.error("find By Criteria in EleRegla failed", re);
            throw re;
        }
    }

    public List<EleRegla> findPageEleRegla(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        log.debug("finding EleRegla findPageEleRegla");

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from EleRegla model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from EleRegla model";

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    public Long findTotalNumberEleRegla() {
        log.debug("finding EleRegla count");

        try {
            String queryString = "select count(*) from EleRegla model";
            Query queryObject = getSession().createQuery(queryString);

            return (Long) queryObject.list().get(0);
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
