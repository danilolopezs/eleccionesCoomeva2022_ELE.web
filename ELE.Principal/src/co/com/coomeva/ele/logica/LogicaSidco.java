package co.com.coomeva.ele.logica;

import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;

public class LogicaSidco {

	private static LogicaSidco instance;
	
	private LogicaSidco() {
	}

	// singleton patterns
	public static LogicaSidco getInstance() {
		if (instance == null) {
			instance = new LogicaSidco();
		}
		return instance;
	}

	public Long consultarHorasDemocraciaAsociado(String nroIdentificacion) {
		Session session=null;
		int f;
		Query query=null;
		session= HibernateSessionFactoryElecciones2012.getSession();
		query = session.getNamedQuery("consultar.horas.democracia.asociado");
		query.setString(0, nroIdentificacion);
		
		Long horas = (Long)query.uniqueResult();
		return horas;
	}
}
