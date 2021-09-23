package co.com.coomeva.wseleconsultahab.delegado;

import co.com.coomeva.wseleconsultahab.logica.LogicaConsultaHabilidad;
import co.com.coomeva.wseleconsultahab.modelo.RespuestaWS;

public class DelegadoConsultaHabilidad {
	private static DelegadoConsultaHabilidad instance;
	private static LogicaConsultaHabilidad logicaConsultaHabilidad;

//	Constructor de la clase
	private DelegadoConsultaHabilidad() {
	}

//	Patròn Singular
	public static DelegadoConsultaHabilidad getInstance() {
		if (instance == null) {
			instance = new DelegadoConsultaHabilidad();
			logicaConsultaHabilidad =  LogicaConsultaHabilidad.getInstance();
		}
		return instance;
	}

	public RespuestaWS obtenerHabilidad(long id) throws Exception {
		return logicaConsultaHabilidad.obtenerHabilidadElecciones2013(id);
	}


	
	
}
