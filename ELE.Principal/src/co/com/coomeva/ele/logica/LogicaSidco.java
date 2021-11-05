package co.com.coomeva.ele.logica;

import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.sidco.HibernateSessionFactorySidco;
import co.com.coomeva.ele.entidades.sie.HibernateSessionFactorySie;
import co.com.coomeva.util.acceso.UtilAcceso;

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
		session= HibernateSessionFactorySidco.getSession();
		query = session.getNamedQuery("consultar.horas.democracia.asociado");
		query.setString(0, nroIdentificacion);
		
		Long horas = (Long)query.uniqueResult();
		return horas;
	}
}
