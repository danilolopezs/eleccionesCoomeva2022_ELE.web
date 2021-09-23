package co.com.coomeva.ele.entidades.planchas.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFotoFormatoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFotoFormatoPlanchaId;

public class EleFotoFormatoPlanchaDAO extends BaseHibernateDAO {

	private static final Log log = LogFactory
			.getLog(EleFotoFormatoPlancha.class);

	public void save(EleFotoFormatoPlancha transientInstance) {
		log.debug("saving EleFotoFormatoPlancha instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EleFotoFormatoPlancha persistentInstance) {
		log.debug("deleting EleFotoFormatoPlancha instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EleFotoFormatoPlancha merge(EleFotoFormatoPlancha detachedInstance) {
		log.debug("merging EleFotoFormatoPlancha instance");
		try {
			EleFotoFormatoPlancha result = (EleFotoFormatoPlancha) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public EleFotoFormatoPlancha findById(EleFotoFormatoPlanchaId id) {
		log.debug("getting EleFotoFormatoPlancha instance with id: " + id);
		try {
			EleFotoFormatoPlancha instance = (EleFotoFormatoPlancha) getSession()
					.get(
							"co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFotoFormatoPlancha",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
