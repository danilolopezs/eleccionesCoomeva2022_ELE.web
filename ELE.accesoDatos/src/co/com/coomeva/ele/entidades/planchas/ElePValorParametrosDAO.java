package co.com.coomeva.ele.entidades.planchas;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * ElePValorParametros entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.planchas.ElePValorParametros
 * @author MyEclipse Persistence Tools
 */

public class ElePValorParametrosDAO extends BaseHibernateDAOPlanchas {
	private static final Log log = LogFactory
			.getLog(ElePValorParametrosDAO.class);
	// property constants
	public static final String NOMBRE_PARAMETRO = "nombreParametro";
	public static final String CODIGO_ESTADO = "codigoEstado";

	public void save(ElePValorParametros transientInstance) {
		log.debug("saving ElePValorParametros instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ElePValorParametros persistentInstance) {
		log.debug("deleting ElePValorParametros instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ElePValorParametros findById(
			co.com.coomeva.ele.entidades.planchas.ElePValorParametrosId id) {
		log.debug("getting ElePValorParametros instance with id: " + id);
		try {
			ElePValorParametros instance = (ElePValorParametros) getSession()
					.get(
							"co.com.coomeva.ele.entidades.planchas.ElePValorParametros",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ElePValorParametros instance) {
		log.debug("finding ElePValorParametros instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"co.com.coomeva.ele.entidades.planchas.ElePValorParametros")
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
		log.debug("finding ElePValorParametros instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ElePValorParametros as model where model."
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

	public List findByCodigoEstado(Object codigoEstado) {
		return findByProperty(CODIGO_ESTADO, codigoEstado);
	}

	public List findAll() {
		log.debug("finding all ElePValorParametros instances");
		try {
			String queryString = "from ElePValorParametros";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ElePValorParametros merge(ElePValorParametros detachedInstance) {
		log.debug("merging ElePValorParametros instance");
		try {
			ElePValorParametros result = (ElePValorParametros) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ElePValorParametros instance) {
		log.debug("attaching dirty ElePValorParametros instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ElePValorParametros instance) {
		log.debug("attaching clean ElePValorParametros instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}