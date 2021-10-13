package co.com.coomeva.ele.entidades.habilidad.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import co.com.coomeva.ele.entidades.habilidad.EleAsociado;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.planchas.HibernateSessionFactoryPlanchas;


/**
 * A data access object (DAO) providing persistence and search support for
 * EleAsociado entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class EleAsociadoDAO implements IEleAsociadoDAO {
    private static final Log log = LogFactory.getLog(EleAsociadoDAO.class);

    // property constants
    //public static final String  APELLIDO1 = "apellido1";
    public static final String APELLIDO1 = "apellido1";

    //public static final String  APELLIDO2 = "apellido2";
    public static final String APELLIDO2 = "apellido2";

    //public static final Long  CODCIUDADASOCIADO = "codCiudadAsociado";
    public static final String CODCIUDADASOCIADO = "codCiudadAsociado";

    //public static final Long  CODESTRATOSOCIAL = "codEstratoSocial";
    public static final String CODESTRATOSOCIAL = "codEstratoSocial";

    //public static final Long  CODZONAELECTASO = "codZonaElectAso";
    public static final String CODZONAELECTASO = "codZonaElectAso";

    //public static final Long  CODIGOASOCIADO = "codigoAsociado";
    public static final String CODIGOASOCIADO = "codigoAsociado";

    //public static final String  DESCCIUDADASOCIADO = "descCiudadAsociado";
    public static final String DESCCIUDADASOCIADO = "descCiudadAsociado";

    //public static final String  DESCESTRATOSOCIAL = "descEstratoSocial";
    public static final String DESCESTRATOSOCIAL = "descEstratoSocial";

    //public static final String  DESCZONAELECTASO = "descZonaElectAso";
    public static final String DESCZONAELECTASO = "descZonaElectAso";

    //public static final String  DIRECCIONCORRESPONDENCIA = "direccionCorrespondencia";
    public static final String DIRECCIONCORRESPONDENCIA = "direccionCorrespondencia";

    //public static final String  EMAIL = "email";
    public static final String EMAIL = "email";

    //public static final String  ESTADOASOCIADO = "estadoAsociado";
    public static final String ESTADOASOCIADO = "estadoAsociado";

    //public static final Date  FECHANACIMIENTO = "fechaNacimiento";
    public static final String FECHANACIMIENTO = "fechaNacimiento";

    //public static final Date  FECHAVINCULACION = "fechaVinculacion";
    public static final String FECHAVINCULACION = "fechaVinculacion";

    //public static final String  GENEROASOCIADO = "generoAsociado";
    public static final String GENEROASOCIADO = "generoAsociado";

    //public static final String  NOMBRE1 = "nombre1";
    public static final String NOMBRE1 = "nombre1";

    //public static final String  NOMBRE2 = "nombre2";
    public static final String NOMBRE2 = "nombre2";

    //public static final Long  NUMERODOCUMENTO = "numeroDocumento";
    public static final String NUMERODOCUMENTO = "numeroDocumento";

    //public static final String  PROFESION = "profesion";
    public static final String PROFESION = "profesion";

    //public static final String  TELEFONOCELULAR = "telefonoCelular";
    public static final String TELEFONOCELULAR = "telefonoCelular";

    //public static final String  TELEFONOFIJO = "telefonoFijo";
    public static final String TELEFONOFIJO = "telefonoFijo";

    //public static final Long  TIPODOCUMENTO = "tipoDocumento";
    public static final String TIPODOCUMENTO = "tipoDocumento";

    private Session getSession() {
        return HibernateSessionFactoryElecciones2012.getSession();
    }

    /**
    *
    * @param Instance
    *            EleAsociado Instance to persist
    * @throws RuntimeException
    *             when the operation fails
    */
    public void save(EleAsociado instance) {
        log.debug("saving EleAsociado instance");

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
    *            EleAsociado Instance to delete
    * @throws RuntimeException
    *             when the operation fails
    */
    public void delete(EleAsociado instance) {
        log.debug("deleting EleAsociado instance");

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
    *            EleAsociado Instance to update
    * @throws RuntimeException
    *             when the operation fails
    */
    public void update(EleAsociado instance) {
        log.debug("updating EleAsociado instance");

        try {
            getSession().update(instance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public EleAsociado findById(Long id) {
        log.debug("finding EleAsociado instance with id: " + id);

        try {
            EleAsociado instance = (EleAsociado) getSession()
                                                     .get(EleAsociado.class, id);

            return instance;
        } catch (RuntimeException re) {
            log.error("finding EleAsociado failed", re);
            throw re;
        }
    }

    public List<EleAsociado> findByExample(EleAsociado instance) {
        log.debug("finding EleAsociado instance by example");

        try {
            List results = getSession().createCriteria("EleAsociado")
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
    * Find all  EleAsociado entities with a specific property value.
    *
    * @param value
    *            the property value to match
    * @param propertyName
    *            the property to search in the instance
    * @return List< EleAsociado> found by query
        */
    public List<EleAsociado> findByProperty(String propertyName, Object value) {
        log.debug("finding EleAsociado instance with property: " +
            propertyName + ", value: " + value);

        try {
            String queryString = "FROM ELEASOCIA as model where model." +
                propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<EleAsociado> findByApellido1(Object apellido1) {
        return findByProperty(APELLIDO1, apellido1);
    }

    public List<EleAsociado> findByApellido2(Object apellido2) {
        return findByProperty(APELLIDO2, apellido2);
    }

    public List<EleAsociado> findByCodCiudadAsociado(Object codCiudadAsociado) {
        return findByProperty(CODCIUDADASOCIADO, codCiudadAsociado);
    }

    public List<EleAsociado> findByCodEstratoSocial(Object codEstratoSocial) {
        return findByProperty(CODESTRATOSOCIAL, codEstratoSocial);
    }

    public List<EleAsociado> findByCodZonaElectAso(Object codZonaElectAso) {
        return findByProperty(CODZONAELECTASO, codZonaElectAso);
    }

    public List<EleAsociado> findByCodigoAsociado(Object codigoAsociado) {
        return findByProperty(CODIGOASOCIADO, codigoAsociado);
    }

    public List<EleAsociado> findByDescCiudadAsociado(Object descCiudadAsociado) {
        return findByProperty(DESCCIUDADASOCIADO, descCiudadAsociado);
    }

    public List<EleAsociado> findByDescEstratoSocial(Object descEstratoSocial) {
        return findByProperty(DESCESTRATOSOCIAL, descEstratoSocial);
    }

    public List<EleAsociado> findByDescZonaElectAso(Object descZonaElectAso) {
        return findByProperty(DESCZONAELECTASO, descZonaElectAso);
    }

    public List<EleAsociado> findByDireccionCorrespondencia(
        Object direccionCorrespondencia) {
        return findByProperty(DIRECCIONCORRESPONDENCIA, direccionCorrespondencia);
    }

    public List<EleAsociado> findByEmail(Object email) {
        return findByProperty(EMAIL, email);
    }

    public List<EleAsociado> findByEstadoAsociado(Object estadoAsociado) {
        return findByProperty(ESTADOASOCIADO, estadoAsociado);
    }

    public List<EleAsociado> findByFechaNacimiento(Object fechaNacimiento) {
        return findByProperty(FECHANACIMIENTO, fechaNacimiento);
    }

    public List<EleAsociado> findByFechaVinculacion(Object fechaVinculacion) {
        return findByProperty(FECHAVINCULACION, fechaVinculacion);
    }

    public List<EleAsociado> findByGeneroAsociado(Object generoAsociado) {
        return findByProperty(GENEROASOCIADO, generoAsociado);
    }

    public List<EleAsociado> findByNombre1(Object nombre1) {
        return findByProperty(NOMBRE1, nombre1);
    }

    public List<EleAsociado> findByNombre2(Object nombre2) {
        return findByProperty(NOMBRE2, nombre2);
    }

    public List<EleAsociado> findByNumeroDocumento(Object numeroDocumento) {
        return findByProperty(NUMERODOCUMENTO, numeroDocumento);
    }

    public List<EleAsociado> findByProfesion(Object profesion) {
        return findByProperty(PROFESION, profesion);
    }

    public List<EleAsociado> findByTelefonoCelular(Object telefonoCelular) {
        return findByProperty(TELEFONOCELULAR, telefonoCelular);
    }

    public List<EleAsociado> findByTelefonoFijo(Object telefonoFijo) {
        return findByProperty(TELEFONOFIJO, telefonoFijo);
    }

    public List<EleAsociado> findByTipoDocumento(Object tipoDocumento) {
        return findByProperty(TIPODOCUMENTO, tipoDocumento);
    }

    /**
    * Find all EleAsociado entities.
    *
    * @return List<EleAsociado> all EleAsociado instances
    */
    public List<EleAsociado> findAll() {
        log.debug("finding all EleAsociado instances");

        try {
            String queryString = "from EleAsociado";
            Query queryObject = getSession().createQuery(queryString);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<EleAsociado> findByCriteria(String whereCondition) {
        log.debug("finding EleAsociado " + whereCondition);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from EleAsociado model " +
                where;
            Query query = getSession().createQuery(queryString);
            List<EleAsociado> entitiesList = query.list();

            return entitiesList;
        } catch (RuntimeException re) {
            log.error("find By Criteria in EleAsociado failed", re);
            throw re;
        }
    }

    public List<EleAsociado> findPageEleAsociado(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        log.debug("finding EleAsociado findPageEleAsociado");

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from EleAsociado model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from EleAsociado model";

                return getSession().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .list();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    public Long findTotalNumberEleAsociado() {
        log.debug("finding EleAsociado count");

        try {
            String queryString = "select count(*) from EleAsociado model";
            Query queryObject = getSession().createQuery(queryString);

            return (Long) queryObject.list().get(0);
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
