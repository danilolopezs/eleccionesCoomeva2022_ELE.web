package co.com.coomeva.ele.entidades.planchas.dao;

import java.util.List;

import org.apache.log4j.spi.LoggerFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlanchaAsociadoAud;

/**
 * A data access object (DAO) providing persistence and search support for
 * ElePlanchaAsociadoAud entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.elecdb.ElePlanchaAsociadoAud
 * @author MyEclipse Persistence Tools
 */

public class ElePlanchaAsociadoAudDAO extends BaseHibernateDAO {
	
	private static final Log log = LogFactory.getLog(ElePlanchaAsociadoAudDAO.class);
	
	// property constants
	public static final String CONSECUTIVO_PLANCHA_ASO = "consecutivoPlanchaAso";
	public static final String NUMERO_INSCRITO = "numeroInscrito";
	public static final String TIPO_INSCRITO = "tipoInscrito";
	public static final String CODIGO_ASOCIADO = "codigoAsociado";
	public static final String CONSECUTIVO_PLANCHA = "consecutivoPlancha";

	public void save(ElePlanchaAsociadoAud transientInstance) {
		log.debug("saving ElePlanchaAsociadoAud instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ElePlanchaAsociadoAud persistentInstance) {
		log.debug("deleting ElePlanchaAsociadoAud instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ElePlanchaAsociadoAud findById(java.lang.Long id) {
		log.debug("getting ElePlanchaAsociadoAud instance with id: " + id);
		try {
			ElePlanchaAsociadoAud instance = (ElePlanchaAsociadoAud) getSession()
					.get("co.com.elecdb.ElePlanchaAsociadoAud", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ElePlanchaAsociadoAud instance) {
		log.debug("finding ElePlanchaAsociadoAud instance by example");
		try {
			List results = getSession()
					.createCriteria("co.com.elecdb.ElePlanchaAsociadoAud")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ElePlanchaAsociadoAud instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ElePlanchaAsociadoAud as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByConsecutivoPlanchaAso(Object consecutivoPlanchaAso) {
		return findByProperty(CONSECUTIVO_PLANCHA_ASO, consecutivoPlanchaAso);
	}

	public List findByNumeroInscrito(Object numeroInscrito) {
		return findByProperty(NUMERO_INSCRITO, numeroInscrito);
	}

	public List findByTipoInscrito(Object tipoInscrito) {
		return findByProperty(TIPO_INSCRITO, tipoInscrito);
	}

	public List findByCodigoAsociado(Object codigoAsociado) {
		return findByProperty(CODIGO_ASOCIADO, codigoAsociado);
	}

	public List findByConsecutivoPlancha(Object consecutivoPlancha) {
		return findByProperty(CONSECUTIVO_PLANCHA, consecutivoPlancha);
	}

	public List findAll() {
		log.debug("finding all ElePlanchaAsociadoAud instances");
		try {
			String queryString = "from ElePlanchaAsociadoAud";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ElePlanchaAsociadoAud merge(ElePlanchaAsociadoAud detachedInstance) {
		log.debug("merging ElePlanchaAsociadoAud instance");
		try {
			ElePlanchaAsociadoAud result = (ElePlanchaAsociadoAud) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ElePlanchaAsociadoAud instance) {
		log.debug("attaching dirty ElePlanchaAsociadoAud instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ElePlanchaAsociadoAud instance) {
		log.debug("attaching clean ElePlanchaAsociadoAud instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}