package co.com.coomeva.wseleconsultahab.delegado;

import co.com.coomeva.wseleconsultahab.logica.DTORespuestaHabilidad;
import co.com.coomeva.wseleconsultahab.logica.LogicaConsultaHabWS;

public class DelegadoHabilidad {
	private static DelegadoHabilidad instance;
	private static LogicaConsultaHabWS logicaConsultaHabWS;

	//Constructor de la clase
	private DelegadoHabilidad() {
	}

	//Patròn Singular
	public static DelegadoHabilidad getInstance() {
		if (instance == null) {
			instance = new DelegadoHabilidad();
			logicaConsultaHabWS = LogicaConsultaHabWS.getInstance();
		}
		return instance;
	}

	public DTORespuestaHabilidad consultaHabilidad(String id) throws Exception {
		Long idLong = new Long(0);
		try {
			idLong = Long.valueOf(id);	
		} catch (NumberFormatException e) {
			throw new Exception("La identificacion que se envio no es numerica o traspasa el maximo permitido");
		}
		
		return logicaConsultaHabWS.consultaHabilidad(idLong);
	}
	
	public Object consultaHabilidadAsociado(String numeroIdentificacion) throws Exception {
		Long idLong = new Long(0);
		try {
			return logicaConsultaHabWS.consultaHabilidad(Long.valueOf(numeroIdentificacion));
		} catch (NumberFormatException e) {
			throw new Exception("La identificacion que se envio no es numerica o traspasa el maximo permitido");
		}
	}
	
}
