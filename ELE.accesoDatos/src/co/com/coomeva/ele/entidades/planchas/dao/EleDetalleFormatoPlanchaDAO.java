package co.com.coomeva.ele.entidades.planchas.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleDetalleFormatoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleDetalleFormatoPlanchaId;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleDetalleFormatoPlancha entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see co.com.elecdb.EleDetalleFormatoPlancha
 * @author MyEclipse Persistence Tools
 */

public class EleDetalleFormatoPlanchaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(EleDetalleFormatoPlanchaDAO.class);
	// property constants
	public static final String OTROS_ESTUDIOS = "otrosEstudios";
	public static final String ULTIMO_CARGO_COOMEVA = "ultimoCargoCoomeva";
	public static final String CARGO_ACTUAL = "cargoActual";
	public static final String EMPRESA_ACTUAL = "empresaActual";
	public static final String OTROS_ESTUDIOS_DOS = "otrosEstudiosDos";
	public static final String NUMERO_RESOLUCION = "numeroResolucion";
	public static final String NUMERO_ACTA = "numeroActa";
	public static final String NUMERO_COMISION_ELECT = "numeroComisionElect";
	public static final String RAZON_INADMISION1 = "razonInadmision1";
	public static final String RAZON_INADMISION2 = "razonInadmision2";
	public static final String RAZON_INADMISION3 = "razonInadmision3";
	public static final String RAZON_INADMISION4 = "razonInadmision4";
	public static final String RAZON_RECHAZO = "razonRechazo";

	public void save(EleDetalleFormatoPlancha transientInstance) {
		log.debug("saving EleDetalleFormatoPlancha instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleDetalleFormatoPlancha persistentInstance) {
		log.debug("deleting EleDetalleFormatoPlancha instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleDetalleFormatoPlancha findById(EleDetalleFormatoPlanchaId id) {
		log.debug("getting EleDetalleFormatoPlancha instance with id: " + id);
		try {
			EleDetalleFormatoPlancha instance = (EleDetalleFormatoPlancha) getSession()
					.get(
							"co.com.coomeva.ele.entidades.planchas.dosmildoce.EleDetalleFormatoPlancha",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleDetalleFormatoPlancha instance) {
		log.debug("finding EleDetalleFormatoPlancha instance by example");
		try {
			List results = getSession().createCriteria(
					"co.com.elecdb.EleDetalleFormatoPlancha").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding EleDetalleFormatoPlancha instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EleDetalleFormatoPlancha as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByOtrosEstudios(Object otrosEstudios) {
		return findByProperty(OTROS_ESTUDIOS, otrosEstudios);
	}

	public List findByUltimoCargoCoomeva(Object ultimoCargoCoomeva) {
		return findByProperty(ULTIMO_CARGO_COOMEVA, ultimoCargoCoomeva);
	}

	public List findByCargoActual(Object cargoActual) {
		return findByProperty(CARGO_ACTUAL, cargoActual);
	}

	public List findByEmpresaActual(Object empresaActual) {
		return findByProperty(EMPRESA_ACTUAL, empresaActual);
	}

	public List findByOtrosEstudiosDos(Object otrosEstudiosDos) {
		return findByProperty(OTROS_ESTUDIOS_DOS, otrosEstudiosDos);
	}

	public List findByNumeroResolucion(Object numeroResolucion) {
		return findByProperty(NUMERO_RESOLUCION, numeroResolucion);
	}

	public List findByNumeroActa(Object numeroActa) {
		return findByProperty(NUMERO_ACTA, numeroActa);
	}

	public List findByNumeroComisionElect(Object numeroComisionElect) {
		return findByProperty(NUMERO_COMISION_ELECT, numeroComisionElect);
	}

	public List findByRazonInadmision1(Object razonInadmision1) {
		return findByProperty(RAZON_INADMISION1, razonInadmision1);
	}

	public List findByRazonInadmision2(Object razonInadmision2) {
		return findByProperty(RAZON_INADMISION2, razonInadmision2);
	}

	public List findByRazonInadmision3(Object razonInadmision3) {
		return findByProperty(RAZON_INADMISION3, razonInadmision3);
	}

	public List findByRazonInadmision4(Object razonInadmision4) {
		return findByProperty(RAZON_INADMISION4, razonInadmision4);
	}

	public List findByRazonRechazo(Object razonRechazo) {
		return findByProperty(RAZON_RECHAZO, razonRechazo);
	}

	public List findAll() {
		log.debug("finding all EleDetalleFormatoPlancha instances");
		try {
			String queryString = "from EleDetalleFormatoPlancha";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleDetalleFormatoPlancha merge(
			EleDetalleFormatoPlancha detachedInstance) {
		log.debug("merging EleDetalleFormatoPlancha instance");
		try {
			EleDetalleFormatoPlancha result = (EleDetalleFormatoPlancha) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleDetalleFormatoPlancha instance) {
		log.debug("attaching dirty EleDetalleFormatoPlancha instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleDetalleFormatoPlancha instance) {
		log.debug("attaching clean EleDetalleFormatoPlancha instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}