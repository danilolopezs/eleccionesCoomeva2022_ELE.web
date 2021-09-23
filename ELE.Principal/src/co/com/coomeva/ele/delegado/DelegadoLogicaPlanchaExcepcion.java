package co.com.coomeva.ele.delegado;

import java.util.List;

import co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlanchaExcepcion;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.logica.inscripcion.plancha.ILogicaPlanchaExcepcion;
import co.com.coomeva.ele.logica.inscripcion.plancha.LogicaPlanchaExcepcion;

public class DelegadoLogicaPlanchaExcepcion {

	private static ILogicaPlanchaExcepcion iLogicaPlanchaExcepcion;
	private static DelegadoLogicaPlanchaExcepcion instance;

	//Constructor de la clase
	private DelegadoLogicaPlanchaExcepcion() {
	}

	//Patròn Singular
	public static DelegadoLogicaPlanchaExcepcion getInstance() {
		if (instance == null) {
			instance = new DelegadoLogicaPlanchaExcepcion();
			iLogicaPlanchaExcepcion = new LogicaPlanchaExcepcion();
		}
		return instance;
	}
	
	public void registrarPlanchaExcepcion(Long consecutivoPlancha,
			Long codigoAsociado, String excepciones)
			throws EleccionesDelegadosException {
		iLogicaPlanchaExcepcion.registrarPlanchaExcepcion(consecutivoPlancha, codigoAsociado, excepciones);
	}
	
	public List<ElePlanchaExcepcion> consultarExcepciones(String identificacionAsociado, String consecutivoPlancha)throws EleccionesDelegadosException{
		return iLogicaPlanchaExcepcion.consultarExcepciones(identificacionAsociado, consecutivoPlancha);
	}
}
