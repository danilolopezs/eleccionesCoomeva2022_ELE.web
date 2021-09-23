package co.com.coomeva.ele.entidades.juego.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.juego.EleParticipanteJuego;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleParticipanteJuego entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.juego.EleParticipanteJuego
 * @author MyEclipse Persistence Tools
 */

public class EleParticipanteJuegoDAO implements IEleParticipanteJuegoDAO {
	
	private static final Log log = LogFactory.getLog(EleParticipanteJuegoDAO.class);
	// property constants
	public static final String NUMERO_DOCUMENTO = "numeroDocumento";
	public static final String COD_ZONA_ASO = "codZonaAso";
	public static final String FASE_JUEGO = "faseJuego";
	
	private Session getSession() {
        return HibernateSessionFactoryElecciones2012.getSession();
    }

	public void save(EleParticipanteJuego transientInstance) {
		log.debug("saving EleParticipanteJuego instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleParticipanteJuego persistentInstance) {
		log.debug("deleting EleParticipanteJuego instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleParticipanteJuego findById(java.lang.Long id) {
		log.debug("getting EleParticipanteJuego instance with id: " + id);
		try {
			EleParticipanteJuego instance = (EleParticipanteJuego) getSession()
					.get("co.com.coomeva.ele.juego.EleParticipanteJuego", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleParticipanteJuego instance) {
		log.debug("finding EleParticipanteJuego instance by example");
		try {
			List results = getSession().createCriteria(
					"co.com.coomeva.ele.juego.EleParticipanteJuego").add(
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
		log.debug("finding EleParticipanteJuego instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EleParticipanteJuego as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNumeroDocumento(Object numeroDocumento) {
		return findByProperty(NUMERO_DOCUMENTO, numeroDocumento);
	}

	public List findByCodZonaAso(Object codZonaAso) {
		return findByProperty(COD_ZONA_ASO, codZonaAso);
	}

	public List findByFaseJuego(Object faseJuego) {
		return findByProperty(FASE_JUEGO, faseJuego);
	}

	public List findAll() {
		log.debug("finding all EleParticipanteJuego instances");
		try {
			String queryString = "from EleParticipanteJuego";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleParticipanteJuego merge(EleParticipanteJuego detachedInstance) {
		log.debug("merging EleParticipanteJuego instance");
		try {
			EleParticipanteJuego result = (EleParticipanteJuego) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleParticipanteJuego instance) {
		log.debug("attaching dirty EleParticipanteJuego instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleParticipanteJuego instance) {
		log.debug("attaching clean EleParticipanteJuego instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}