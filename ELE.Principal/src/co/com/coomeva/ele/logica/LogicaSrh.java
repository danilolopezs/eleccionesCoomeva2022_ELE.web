package co.com.coomeva.ele.logica;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.util.Validador;
import co.com.coomeva.util.acceso.UtilAcceso;

public class LogicaSrh {

	private static LogicaSrh instance;

	//Constructor de la clase
	private LogicaSrh() {
	}

	//Patròn Singular
	public static LogicaSrh getInstance() {
		if (instance == null) {
			instance = new LogicaSrh();
		}
		return instance;
	}

	/**
	 * Valida si existe el usuario en el SRH
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroIdentificacion
	 * @param zonaEspecial
	 * @return boolean
	 */
	public boolean existEmpleado(String nroIdentificacion){
		Session session=null;
		Query query=null;
		session= HibernateSessionFactoryElecciones2012.getSession();
		query = session.getNamedQuery("empleado.consulta");

		query.setString(0, nroIdentificacion);

		String tiempoRetiro = "";
		String emp_estado = "";

		List<Object[]> returnList = new ArrayList<Object[]>();
		returnList=query.list();
		if (returnList.size() == 0) {
			return false;
		}

		for (Object[] objects : returnList) {
			if (objects[0] != null) {
				tiempoRetiro = objects[0].toString();
			}
			if (objects[1] != null) {
				emp_estado = objects[1].toString();
			}

			break;
		}
		Double tiempoRetiroInt = 0d ;


		if (tiempoRetiro != null &&!tiempoRetiro.equalsIgnoreCase("")) {
			Double tiempoDouble = new Double(tiempoRetiro);
			tiempoDouble = Validador.Redondear( tiempoDouble, UtilAcceso.getParametroFuenteI("parametros", "maxDecimales"));
			
			tiempoRetiroInt = tiempoDouble;

		}

		if ((emp_estado.equals(UtilAcceso.getParametroFuenteS("parametros", "estadoRetiradoSrh"))))
		{
			if ( tiempoRetiroInt >3 )
				return false;
			else
				return true;
		}else
			return true;
	}
}