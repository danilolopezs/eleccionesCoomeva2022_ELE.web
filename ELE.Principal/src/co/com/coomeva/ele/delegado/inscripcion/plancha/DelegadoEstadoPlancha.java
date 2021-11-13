package co.com.coomeva.ele.delegado.inscripcion.plancha;

import java.util.Date;

import co.com.coomeva.ele.logica.LogicaEstadoPlancha;

/**
 * @author Mario Alejandro Acevedo <br>
 * @project ELE.Principal
 * @class DelegadoPlancha
 */
public class DelegadoEstadoPlancha {

	private static DelegadoEstadoPlancha instance;
	private static LogicaEstadoPlancha logicaEstadoPlancha;

	private DelegadoEstadoPlancha() {
	}

	public static DelegadoEstadoPlancha getInstance() {
		if (instance == null) {
			instance = new DelegadoEstadoPlancha();
			logicaEstadoPlancha = LogicaEstadoPlancha.getInstance();
		}
		return instance;
	}

	public void crearEstadoPlancha(String usuario, Date fechaRegistro, String estado, Long consecPlancha){
		logicaEstadoPlancha.crearEstadoPlancha(usuario, fechaRegistro, estado, consecPlancha);
	
	}
	
}
