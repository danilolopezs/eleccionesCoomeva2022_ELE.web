package co.com.coomeva.ele.entidades.planchas;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * ElePParametros entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.planchas.ElePParametros
 * @author MyEclipse Persistence Tools
 */

public class ElePParametrosDAO extends BaseHibernateDAOPlanchas {
	private static final Log log = LogFactory.getLog(ElePParametrosDAO.class);
	// property constants
	public static final String NOMBRE_PARAMETRO = "nombreParametro";
	public static final String VALOR_PARAMETRO = "valorParametro";
	public static final String CODIGO_ESTADO = "codigoEstado";
	public static final String TIPO_PARAMETRO = "tipoParametro";

	public void save(ElePParametros transientInstance) {
		log.debug("saving ElePParametros instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ElePParametros persistentInstance) {
		log.debug("deleting ElePParametros instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ElePParametros findById(java.lang.Long id) {
		//log.debug("getting ElePParametros instance with id: " + id);
		try {
			ElePParametros instance = (ElePParametros) getSession2012().get(
					"co.com.coomeva.ele.entidades.planchas.ElePParametros", id);
			return instance;
		} catch (RuntimeException re) {
			re.printStackTrace();
			log.error("get failed", re);
			throw re;
		}
	}
	
	public ElePParametros findById2012(java.lang.Long id) {
		log.debug("getting ElePParametros instance with id: " + id);
		try {
			ElePParametros instance = (ElePParametros) getSession2012().get(
					"co.com.coomeva.ele.entidades.planchas.ElePParametros", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ElePParametros instance) {
		log.debug("finding ElePParametros instance by example");
		try {
			List results = getSession().createCriteria(
					"co.com.coomeva.ele.entidades.planchas.ElePParametros")
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
		log.debug("finding ElePParametros instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ElePParametros as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNombreParametro(Object nombreParametro) {
		return findByProperty(NOMBRE_PARAMETRO, nombreParametro);
	}

	public List findByValorParametro(Object valorParametro) {
		return findByProperty(VALOR_PARAMETRO, valorParametro);
	}

	public List findByCodigoEstado(Object codigoEstado) {
		return findByProperty(CODIGO_ESTADO, codigoEstado);
	}

	public List findByTipoParametro(Object tipoParametro) {
		return findByProperty(TIPO_PARAMETRO, tipoParametro);
	}

	public List findAll() {
		log.debug("finding all ElePParametros instances");
		try {
			String queryString = "from ElePParametros";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ElePParametros merge(ElePParametros detachedInstance) {
		log.debug("merging ElePParametros instance");
		try {
			ElePParametros result = (ElePParametros) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ElePParametros instance) {
		log.debug("attaching dirty ElePParametros instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ElePParametros instance) {
		log.debug("attaching clean ElePParametros instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}