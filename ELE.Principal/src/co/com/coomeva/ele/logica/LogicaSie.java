package co.com.coomeva.ele.logica;

import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.sie.HibernateSessionFactorySie;
import co.com.coomeva.util.acceso.UtilAcceso;

public class LogicaSie {

	private static LogicaSie instance;

	//Constructor de la clase
	private LogicaSie() {
	}

	//Patròn Singular
	public static LogicaSie getInstance() {
		if (instance == null) {
			instance = new LogicaSie();
		}
		return instance;
	}
	/**
	 * Metodo que calcula el tiempo trascurrido entre dos fechas
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param time
	 * @return Integer
	 * @throws Exception
	 */
	public int calculateTime(String time) throws Exception
	{
		Session session=null;
		Query query=null;	
		session= HibernateSessionFactorySie.getSession();
		query=session.getNamedQuery("asociado.calculateTime1");

		query.setString(0, time);

		return Integer.parseInt(query.uniqueResult().toString());
	}
	/**
	 * Devuelve el numero de horas democracia realizado por el asociado
	 * @param nroIdentificacion
	 * @return boolean
	 * @throws Exception
	 */
	public boolean validateHorasDemocracia(String nroIdentificacion) throws Exception
	{
		Session session=null;
		Query query=null;
		session= HibernateSessionFactorySie.getSession();
		query = session.getNamedQuery("asociado.validateHorasDemocracia");
		query.setString(0, nroIdentificacion);
		
		Double horas = Double.parseDouble(query.uniqueResult().toString());
		return horas < UtilAcceso.getParametroFuenteI("parametros", "horasDemocracia");
	}
	
	
}
