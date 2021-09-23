package co.com.coomeva.ele.entidades.formulario;

import co.com.coomeva.ele.entidades.planchas.dao.BaseHibernateDAO;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * EleRegistroCampos entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.formulario.EleRegistroCampos
 * @author MyEclipse Persistence Tools
 */

public class EleRegistroCamposDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(EleRegistroCamposDAO.class);
	// property constants
	public static final String CONS_REGISTRO_FORMULARIO = "consRegistroFormulario";
	public static final String COD_CAMPO = "codCampo";
	public static final String VALOR = "valor";

	public void save(EleRegistroCampos transientInstance) {
		log.debug("saving EleRegistroCampos instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleRegistroCampos persistentInstance) {
		log.debug("deleting EleRegistroCampos instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleRegistroCampos findById(java.lang.Long id) {
		log.debug("getting EleRegistroCampos instance with id: " + id);
		try {
			EleRegistroCampos instance = (EleRegistroCampos) getSession()
					.get(
							"co.com.coomeva.ele.entidades.formulario.EleRegistroCampos",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EleRegistroCampos instance) {
		log.debug("finding EleRegistroCampos instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"co.com.coomeva.ele.entidades.formulario.EleRegistroCampos")
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
		log.debug("finding EleRegistroCampos instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EleRegistroCampos as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByConsRegistroFormulario(Object consRegistroFormulario) {
		return findByProperty(CONS_REGISTRO_FORMULARIO, consRegistroFormulario);
	}

	public List findByCodCampo(Object codCampo) {
		return findByProperty(COD_CAMPO, codCampo);
	}

	public List findByValor(Object valor) {
		return findByProperty(VALOR, valor);
	}

	public List findAll() {
		log.debug("finding all EleRegistroCampos instances");
		try {
			String queryString = "from EleRegistroCampos";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EleRegistroCampos merge(EleRegistroCampos detachedInstance) {
		log.debug("merging EleRegistroCampos instance");
		try {
			EleRegistroCampos result = (EleRegistroCampos) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EleRegistroCampos instance) {
		log.debug("attaching dirty EleRegistroCampos instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EleRegistroCampos instance) {
		log.debug("attaching clean EleRegistroCampos instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}