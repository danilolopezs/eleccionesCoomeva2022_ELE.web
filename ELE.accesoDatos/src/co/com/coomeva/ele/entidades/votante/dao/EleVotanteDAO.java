package co.com.coomeva.ele.entidades.votante.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.votante.EleVotante;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleVotante entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.votante.EleVotante
 * @author MyEclipse Persistence Tools
 */

public class EleVotanteDAO implements IEleVotanteDAO {
	private static final Log log = LogFactory.getLog(EleVotanteDAO.class);
	// property constants
	public static final String DOCUMENTO_VOTANTE = "documentoVotante";
	public static final String USUARIO_REGISTRO = "usuarioRegistro";
	public static final String ZONA_USUARIO_REGISTRO = "zonaUsuarioRegistro";
	public static final String AGENCIA_USUARIO_REGISTRO = "agenciaUsuarioRegistro";
	public static final String IP_REGISTRO = "ipRegistro";

	private Session getSession() {
		return HibernateSessionFactoryElecciones2012.getSession();
	}

	public void save(EleVotante transientInstance) {
		log.debug("saving EleVotante instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleVotante persistentInstance) {
		log.debug("deleting EleVotante instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleVotante findById(java.lang.Long id) {
		log.debug("getting EleVotante instance with id: " + id);
		try {
			EleVotante instance = (EleVotante) getSession().get(
					"co.com.coomeva.ele.entidades.votante.EleVotante", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleVotante instance) {
		log.debug("finding EleVotante instance by example");
		try {
			List results = getSession().createCriteria(
					"co.com.coomeva.ele.entidades.votante.EleVotante").add(
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
		log.debug("finding EleVotante instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from EleVotante as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDocumentoVotante(Object documentoVotante) {
		return findByProperty(DOCUMENTO_VOTANTE, documentoVotante);
	}

	public List findByUsuarioRegistro(Object usuarioRegistro) {
		return findByProperty(USUARIO_REGISTRO, usuarioRegistro);
	}

	public List findByZonaUsuarioRegistro(Object zonaUsuarioRegistro) {
		return findByProperty(ZONA_USUARIO_REGISTRO, zonaUsuarioRegistro);
	}

	public List findByAgenciaUsuarioRegistro(Object agenciaUsuarioRegistro) {
		return findByProperty(AGENCIA_USUARIO_REGISTRO, agenciaUsuarioRegistro);
	}

	public List findByIpRegistro(Object ipRegistro) {
		return findByProperty(IP_REGISTRO, ipRegistro);
	}

	public List findAll() {
		log.debug("finding all EleVotante instances");
		try {
			String queryString = "from EleVotante";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleVotante merge(EleVotante detachedInstance) {
		log.debug("merging EleVotante instance");
		try {
			EleVotante result = (EleVotante) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleVotante instance) {
		log.debug("attaching dirty EleVotante instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleVotante instance) {
		log.debug("attaching clean EleVotante instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}