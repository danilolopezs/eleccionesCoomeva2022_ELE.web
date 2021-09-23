package co.com.coomeva.ele.entidades.habilidad.acceso;

import co.com.coomeva.ele.entidades.habilidad.dao.EleAsociadoDAO;
import co.com.coomeva.ele.entidades.habilidad.dao.EleInhabilidadDAO;
import co.com.coomeva.ele.entidades.habilidad.dao.EleNovedadDAO;
import co.com.coomeva.ele.entidades.habilidad.dao.EleProcesoDAO;
import co.com.coomeva.ele.entidades.habilidad.dao.EleProcesoReglaDAO;
import co.com.coomeva.ele.entidades.habilidad.dao.EleReglaDAO;
import co.com.coomeva.ele.entidades.habilidad.dao.EleSuspendidoDAO;
import co.com.coomeva.ele.entidades.habilidad.dao.IEleAsociadoDAO;
import co.com.coomeva.ele.entidades.habilidad.dao.IEleInhabilidadDAO;
import co.com.coomeva.ele.entidades.habilidad.dao.IEleNovedadDAO;
import co.com.coomeva.ele.entidades.habilidad.dao.IEleProcesoDAO;
import co.com.coomeva.ele.entidades.habilidad.dao.IEleProcesoReglaDAO;
import co.com.coomeva.ele.entidades.habilidad.dao.IEleReglaDAO;
import co.com.coomeva.ele.entidades.habilidad.dao.IEleSuspendidoDAO;


/**
 * Factory for Data Access Objects Strategy The DAO pattern can be made highly flexible
 * by adopting the Abstract Factory [GoF] and the Factory Method [GoF] patterns.
 * When the underlying storage is not subject to change from one implementation to another,
 * this strategy can be implemented using the Factory Method pattern to produce a number of DAOs needed by the application.
 * This class is a Factory Method pattern
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 */
public class XMLHibernateDaoFactoryElecciones2012 {
    private static XMLHibernateDaoFactoryElecciones2012 instance = null;

    private XMLHibernateDaoFactoryElecciones2012() {
    }

    /**
    *
    * @return HibernateDaoFactory
    */
    public static XMLHibernateDaoFactoryElecciones2012 getInstance() {
        if (instance == null) {
            instance = new XMLHibernateDaoFactoryElecciones2012();
        }

        return instance;
    }

    /**
    * This method instantiates the IEleAsociadoDAO class for HibernateCore
    * that is used in this applications deployment environment to access the data.
    * @return IEleAsociadoDAO implementation
    */
    public IEleAsociadoDAO getEleAsociadoDAO() {
        return new EleAsociadoDAO();
    }

    /**
    * This method instantiates the IEleInhabilidadDAO class for HibernateCore
    * that is used in this applications deployment environment to access the data.
    * @return IEleInhabilidadDAO implementation
    */
    public IEleInhabilidadDAO getEleInhabilidadDAO() {
        return new EleInhabilidadDAO();
    }

    /**
    * This method instantiates the IEleNovedadDAO class for HibernateCore
    * that is used in this applications deployment environment to access the data.
    * @return IEleNovedadDAO implementation
    */
    public IEleNovedadDAO getEleNovedadDAO() {
        return new EleNovedadDAO();
    }

    /**
    * This method instantiates the IEleProcesoDAO class for HibernateCore
    * that is used in this applications deployment environment to access the data.
    * @return IEleProcesoDAO implementation
    */
    public IEleProcesoDAO getEleProcesoDAO() {
        return new EleProcesoDAO();
    }

    /**
    * This method instantiates the IEleProcesoReglaDAO class for HibernateCore
    * that is used in this applications deployment environment to access the data.
    * @return IEleProcesoReglaDAO implementation
    */
    public IEleProcesoReglaDAO getEleProcesoReglaDAO() {
        return new EleProcesoReglaDAO();
    }

    /**
    * This method instantiates the IEleReglaDAO class for HibernateCore
    * that is used in this applications deployment environment to access the data.
    * @return IEleReglaDAO implementation
    */
    public IEleReglaDAO getEleReglaDAO() {
        return new EleReglaDAO();
    }

    /**
    * This method instantiates the IEleSuspendidoDAO class for HibernateCore
    * that is used in this applications deployment environment to access the data.
    * @return IEleSuspendidoDAO implementation
    */
    public IEleSuspendidoDAO getEleSuspendidoDAO() {
        return new EleSuspendidoDAO();
    }
}
