package co.com.coomeva.ele.entidades.usuario.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.usuario.EleUsuarioLogin;

public class EleUsuarioLoginDAO  implements IEleUsuarioLoginDAO{
	
	private static final Log log = LogFactory.getLog(EleUsuarioLoginDAO.class);
	
	private Session getSession() {
        return HibernateSessionFactoryElecciones2012.getSession();
    }

	@Override
	public EleUsuarioLogin findByUserId(String userId) {
		
		log.debug("finding EleUsuarioLogin instance with property: userId");
		try {
			String queryString = "from EleUsuarioLogin as model where model.userId= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, userId);
			
			List<EleUsuarioLogin> lista = queryObject.list();
			
			if(lista != null && !lista.isEmpty())
			{
				return lista.get(0);
			}
			else
			{
				return null;
			}
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	@Override
	public List<EleUsuarioLogin> consultarUsuariosBloqueados(String userId) {
		
		log.debug("finding EleUsuarioLogin instance with property: userId");
		try {
			String queryString = "from EleUsuarioLogin as model where model.estado = 2";
			if(userId != null && !userId.isEmpty())
			{
				queryString = queryString+"AND model.userId= ?";
			}
			Query queryObject = getSession().createQuery(queryString);
			
			if(userId != null && !userId.isEmpty())
			{
				queryObject.setParameter(0, userId);
			}			
			List<EleUsuarioLogin> lista = queryObject.list();
			
			if(lista != null && !lista.isEmpty())
			{
				return lista;
			}
			else
			{
				return null;
			}
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@Override
	public void save(EleUsuarioLogin entity) {
		log.debug("saving EleUsuarioLogin instance");
		try {
			getSession().save(entity);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void update(EleUsuarioLogin entity) {
		log.debug("update EleUsuarioLogin instance");
		try {
			getSession().update(entity);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	@Override
	public void merge(EleUsuarioLogin entity) {
		log.debug("merge EleUsuarioLogin instance");
		try {
			getSession().merge(entity);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

}
