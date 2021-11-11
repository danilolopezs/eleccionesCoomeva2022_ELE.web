package co.com.coomeva.ele.logica;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;

public class LogicaLico {

	private static LogicaLico instance;

	// Constructor de la clase
	private LogicaLico() {
	}

	// Patròn Singular
	public static LogicaLico getInstance() {
		if (instance == null) {
			instance = new LogicaLico();
		}
		return instance;
	}

	/**
	 * Valida si es asesor en financiera
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroIdentificacion
	 * @return boolean
	 */
	public boolean existAsesorFin(String nroIdentificacion) {

		Session session = null;
		Query query = null;
		session = HibernateSessionFactoryElecciones2012.getSession();// GENERANDO CONFLICTO
		query = session.getNamedQuery("asesor.getAsesor");
		query.setString(0, nroIdentificacion);

		List<Object[]> returnList = new ArrayList<Object[]>();
		returnList = query.list();

		if (returnList.size() == 0) {
			return false;
		}

		String eprcdgo = "";
		Long tiempoRetiro = 0l;

		for (Object[] objects : returnList) {
			if (objects[0] != null) {
				eprcdgo = objects[0].toString();
			}
			if (objects[2] != null) {
				tiempoRetiro = Long.valueOf(objects[2].toString());
			}
			break;
		}

		if (!(eprcdgo.equals("A")) && (tiempoRetiro > 3)) {
			return false;
		}

		return true;
	}

	/**
	 * Valida si es asesor
	 * 
	 * @author
	 * @param nroIdentificacion
	 * @return boolean
	 */
	public boolean existAsesor(String nroIdentificacion) {

		Session session = null;
		Query query = null;
		session = HibernateSessionFactoryElecciones2012.getSession();
		query = session.getNamedQuery("asesor.getAsesorActivo");
		query.setString(0, nroIdentificacion);
		return !query.list().isEmpty();
	}

}
