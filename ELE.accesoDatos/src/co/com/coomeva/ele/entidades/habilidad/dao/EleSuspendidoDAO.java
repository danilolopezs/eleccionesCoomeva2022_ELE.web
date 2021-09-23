package co.com.coomeva.ele.entidades.habilidad.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import co.com.coomeva.ele.entidades.habilidad.EleSuspendido;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.planchas.HibernateSessionFactoryPlanchas;


/**
 * A data access object (DAO) providing persistence and search support for
 * EleSuspendido entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class EleSuspendidoDAO implements IEleSuspendidoDAO {
    private static final Log log = LogFactory.getLog(EleSuspendidoDAO.class);

    // property constants
    //public static final Long  CODIGOASOCIADO = "codigoAsociado";
    public static final String CODIGOASOCIADO = "codigoAsociado";

    //public static final String  ESTADO = "estado";
    public static final String ESTADO = "estado";

    //public static final Date  FECHAREGISTRO = "fechaRegistro";
    public static final String FECHAREGISTRO = "fechaRegistro";

    //public static final String  MOTIVO = "motivo";
    public static final String MOTIVO = "motivo";

    private Session getSession() {
        return HibernateSessionFactoryElecciones2012.getSession();
    }

    /**
    *
    * @param Instance
    *            EleSuspendido Instance to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(EleSuspendido instance) {
        log.debug("saving EleSuspendido instance");

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
    *            EleSuspendido Instance to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(EleSuspendido instance) {
        log.debug("deleting EleSuspendido instance");

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
    *            EleSuspendido Instance to update
    * @throws RuntimeException
    *             when the operation fails
    */
    public void update(EleSuspendido instance) {
        log.debug("updating EleSuspendido instance");

        try {
            getSession().update(instance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public EleSuspendido findById(Long id) {
        log.debug("finding EleSuspendido instance with id: " + id);

        try {
            EleSuspendido instance = (EleSuspendido) getSession()
                                                         .get(EleSuspendido.class,
                    id);

            return instance;
        } catch (RuntimeException re) {
            log.error("finding EleSuspendido failed", re);
            throw re;
        }
    }

    public List<EleSuspendido> findByExample(EleSuspendido instance) {
        log.debug("finding EleSuspendido instance by example");

        try {
            List results = getSession().createCriteria("EleSuspendido")
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
    * Find all  EleSuspendido entities with a specific property value.
    *
    * @param value
    *            the property value to match
    * @param propertyName
    *            the property to search in the instance
    * @return List< EleSuspendido> found by query
        */
    public List<EleSuspendido> findByProperty(String propertyName, Object value) {
        log.debug("finding EleSuspendido instance with property: " +
            propertyName + ", value: " + value);

        try {
            String queryString = "from EleSuspendido as model where model." +
                propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<EleSuspendido> findByCodigoAsociado(Object codigoAsociado) {
        return findByProperty(CODIGOASOCIADO, codigoAsociado);
    }

    public List<EleSuspendido> findByEstado(Object estado) {
        return findByProperty(ESTADO, estado);
    }

    public List<EleSuspendido> findByFechaRegistro(Object fechaRegistro) {
        return findByProperty(FECHAREGISTRO, fechaRegistro);
    }

    public List<EleSuspendido> findByMotivo(Object motivo) {
        return findByProperty(MOTIVO, motivo);
    }

    /**
    * Find all EleSuspendido entities.
    *
    * @return List<EleSuspendido> all EleSuspendido instances
    */
    public List<EleSuspendido> findAll() {
        log.debug("finding all EleSuspendido instances");

        try {
            String queryString = "from EleSuspendido";
            Query queryObject = getSession().createQuery(queryString);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<EleSuspendido> findByCriteria(String whereCondition) {
        log.debug("finding EleSuspendido " + whereCondition);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from EleSuspendido model " +
                where;
            Query query = getSession().createQuery(queryString);
            List<EleSuspendido> entitiesList = query.list();

            return entitiesList;
        } catch (RuntimeException re) {
            log.error("find By Criteria in EleSuspendido failed", re);
            throw re;
        }
    }

    public List<EleSuspendido> findPageEleSuspendido(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        log.debug("finding EleSuspendido findPageEleSuspendido");

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from EleSuspendido model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from EleSuspendido model";

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    public Long findTotalNumberEleSuspendido() {
        log.debug("finding EleSuspendido count");

        try {
            String queryString = "select count(*) from EleSuspendido model";
            Query queryObject = getSession().createQuery(queryString);

            return (Long) queryObject.list().get(0);
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    public void limpiarTablaSuspendido(){
    	try {
            String queryString = "delete from EleSuspendido model";
            getSession().createQuery(queryString).executeUpdate();

        } catch (RuntimeException re) {
            throw re;
        }
    }
}
