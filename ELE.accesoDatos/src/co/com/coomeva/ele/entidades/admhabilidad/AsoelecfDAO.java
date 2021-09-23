package co.com.coomeva.ele.entidades.admhabilidad;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Asoelecf entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see co.com.coomeva.ele.entidades.admhabilidad.Asoelecf
 * @author MyEclipse Persistence Tools
 */

public class AsoelecfDAO extends BaseHibernateDAOHab {
	private static final Log log = LogFactory.getLog(AsoelecfDAO.class);
	// property constants
	public static final String WNOMCLI = "wnomcli";
	public static final String WCODREG = "wcodreg";
	public static final String WNOMREG = "wnomreg";
	public static final String WZONA = "wzona";
	public static final String WNOMZON = "wnomzon";
	public static final String WAGCORI = "wagcori";
	public static final String WNOMAGC = "wnomagc";
	public static final String WINDHAB = "windhab";
	public static final String WINDRET = "windret";
	public static final String WDEBAUT = "wdebaut";
	public static final String WMEDVOT = "wmedvot";
	public static final String WCODCIUC = "wcodciuc";
	public static final String WNOMCIUC = "wnomciuc";
	public static final String WNOMDIRC = "wnomdirc";
	public static final String WESTASO = "westaso";
	public static final String WDESESTA = "wdesesta";
	public static final String WASOCOR = "wasocor";
	public static final String WDESCOR = "wdescor";
	public static final String WVLRVCDO = "wvlrvcdo";
	public static final String WVLRCUOM = "wvlrcuom";
	public static final String WMORAD = "wmorad";
	public static final String WMORAC = "wmorac";
	public static final String WFECCON = "wfeccon";
	public static final String WEDAD = "wedad";
	public static final String WFECING = "wfecing";
	public static final String WANTIG = "wantig";
	public static final String WCODSEX = "wcodsex";
	public static final String WSEXO = "wsexo";
	public static final String WFECPROW = "wfecprow";
	public static final String WHORPROW = "whorprow";
	public static final String WUSRPROW = "wusrprow";
	public static final String WFECPROA = "wfecproa";
	public static final String WHORPROA = "whorproa";
	public static final String WUSRPROA = "wusrproa";
	public static final String WFECPROR = "wfecpror";
	public static final String WHORPROR = "whorpror";
	public static final String WUSRPROR = "wusrpror";
	public static final String WNUMINT = "wnumint";

	public void save(Asoelecf transientInstance) {
		log.debug("saving Asoelecf instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Asoelecf persistentInstance) {
		log.debug("deleting Asoelecf instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Asoelecf findById(java.lang.Long id) {
		log.debug("getting Asoelecf instance with id: " + id);
		try {
			Asoelecf instance = (Asoelecf) getSession().get(
					"co.com.coomeva.ele.entidades.admhabilidad.Asoelecf", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Asoelecf instance) {
		log.debug("finding Asoelecf instance by example");
		try {
			List results = getSession().createCriteria(
					"co.com.coomeva.ele.entidades.admhabilidad.Asoelecf").add(
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
		log.debug("finding Asoelecf instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Asoelecf as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByWnomcli(Object wnomcli) {
		return findByProperty(WNOMCLI, wnomcli);
	}

	public List findByWcodreg(Object wcodreg) {
		return findByProperty(WCODREG, wcodreg);
	}

	public List findByWnomreg(Object wnomreg) {
		return findByProperty(WNOMREG, wnomreg);
	}

	public List findByWzona(Object wzona) {
		return findByProperty(WZONA, wzona);
	}

	public List findByWnomzon(Object wnomzon) {
		return findByProperty(WNOMZON, wnomzon);
	}

	public List findByWagcori(Object wagcori) {
		return findByProperty(WAGCORI, wagcori);
	}

	public List findByWnomagc(Object wnomagc) {
		return findByProperty(WNOMAGC, wnomagc);
	}

	public List findByWindhab(Object windhab) {
		return findByProperty(WINDHAB, windhab);
	}

	public List findByWindret(Object windret) {
		return findByProperty(WINDRET, windret);
	}

	public List findByWdebaut(Object wdebaut) {
		return findByProperty(WDEBAUT, wdebaut);
	}

	public List findByWmedvot(Object wmedvot) {
		return findByProperty(WMEDVOT, wmedvot);
	}

	public List findByWcodciuc(Object wcodciuc) {
		return findByProperty(WCODCIUC, wcodciuc);
	}

	public List findByWnomciuc(Object wnomciuc) {
		return findByProperty(WNOMCIUC, wnomciuc);
	}

	public List findByWnomdirc(Object wnomdirc) {
		return findByProperty(WNOMDIRC, wnomdirc);
	}

	public List findByWestaso(Object westaso) {
		return findByProperty(WESTASO, westaso);
	}

	public List findByWdesesta(Object wdesesta) {
		return findByProperty(WDESESTA, wdesesta);
	}

	public List findByWasocor(Object wasocor) {
		return findByProperty(WASOCOR, wasocor);
	}

	public List findByWdescor(Object wdescor) {
		return findByProperty(WDESCOR, wdescor);
	}

	public List findByWvlrvcdo(Object wvlrvcdo) {
		return findByProperty(WVLRVCDO, wvlrvcdo);
	}

	public List findByWvlrcuom(Object wvlrcuom) {
		return findByProperty(WVLRCUOM, wvlrcuom);
	}

	public List findByWmorad(Object wmorad) {
		return findByProperty(WMORAD, wmorad);
	}

	public List findByWmorac(Object wmorac) {
		return findByProperty(WMORAC, wmorac);
	}

	public List findByWfeccon(Object wfeccon) {
		return findByProperty(WFECCON, wfeccon);
	}

	public List findByWedad(Object wedad) {
		return findByProperty(WEDAD, wedad);
	}

	public List findByWfecing(Object wfecing) {
		return findByProperty(WFECING, wfecing);
	}

	public List findByWantig(Object wantig) {
		return findByProperty(WANTIG, wantig);
	}

	public List findByWcodsex(Object wcodsex) {
		return findByProperty(WCODSEX, wcodsex);
	}

	public List findByWsexo(Object wsexo) {
		return findByProperty(WSEXO, wsexo);
	}

	public List findByWfecprow(Object wfecprow) {
		return findByProperty(WFECPROW, wfecprow);
	}

	public List findByWhorprow(Object whorprow) {
		return findByProperty(WHORPROW, whorprow);
	}

	public List findByWusrprow(Object wusrprow) {
		return findByProperty(WUSRPROW, wusrprow);
	}

	public List findByWfecproa(Object wfecproa) {
		return findByProperty(WFECPROA, wfecproa);
	}

	public List findByWhorproa(Object whorproa) {
		return findByProperty(WHORPROA, whorproa);
	}

	public List findByWusrproa(Object wusrproa) {
		return findByProperty(WUSRPROA, wusrproa);
	}

	public List findByWfecpror(Object wfecpror) {
		return findByProperty(WFECPROR, wfecpror);
	}

	public List findByWhorpror(Object whorpror) {
		return findByProperty(WHORPROR, whorpror);
	}

	public List findByWusrpror(Object wusrpror) {
		return findByProperty(WUSRPROR, wusrpror);
	}

	public List findByWnumint(Object wnumint) {
		return findByProperty(WNUMINT, wnumint);
	}

	public List findAll() {
		log.debug("finding all Asoelecf instances");
		try {
			String queryString = "from Asoelecf";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Asoelecf merge(Asoelecf detachedInstance) {
		log.debug("merging Asoelecf instance");
		try {
			Asoelecf result = (Asoelecf) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Asoelecf instance) {
		log.debug("attaching dirty Asoelecf instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Asoelecf instance) {
		log.debug("attaching clean Asoelecf instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}