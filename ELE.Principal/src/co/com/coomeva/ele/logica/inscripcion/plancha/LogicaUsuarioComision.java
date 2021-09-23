package co.com.coomeva.ele.logica.inscripcion.plancha;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;

public class LogicaUsuarioComision implements ILogicaUsuarioComision {

	@Override
	public List<Long> consultarZonaElectUsuarioComision(String idUsuario) {
		Session session = null;
		Query query = null;
		try {
			session = HibernateSessionFactoryElecciones2012.getSession();
			query = session
					.getNamedQuery(ConstantesNamedQueries.QUERY_ZONA_ELECTORAL_POR_USUARIO_COMISION);
			query.setString("idUsuario", idUsuario);
			return query.list();
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
			query = null;
		}
	}
	
	@Override
	public List<Long> consultarZonaElectEspUsuarioComision(String idUsuario) {
		Session session = null;
		Query query = null;
		try {
			session = HibernateSessionFactoryElecciones2012.getSession();
			query = session
					.getNamedQuery(ConstantesNamedQueries.QUERY_ZONA_ELECTORAL_ESPE_POR_USUARIO_COMISION);
			query.setString("idUsuario", idUsuario);
			return query.list();
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
			query = null;
		}
	}

}
