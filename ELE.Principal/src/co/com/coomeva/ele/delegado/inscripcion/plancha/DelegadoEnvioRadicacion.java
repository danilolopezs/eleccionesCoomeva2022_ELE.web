package co.com.coomeva.ele.delegado.inscripcion.plancha;

import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.dto.DTOHabilidadAsociado;
import co.com.coomeva.ele.logica.inscripcion.plancha.ILogicaEnviarRadicacion;
import co.com.coomeva.ele.logica.inscripcion.plancha.LogicaEnviarRadicacion;

public class DelegadoEnvioRadicacion {
	private static DelegadoEnvioRadicacion instance;
	private static LogicaEnviarRadicacion logicaRadicacion;
	
	// Constructor 
	private DelegadoEnvioRadicacion() {	}
	
	// patron singleton
	public static DelegadoEnvioRadicacion getInstance() {
		if (instance == null) {
			instance = new DelegadoEnvioRadicacion(); 
			logicaRadicacion = new LogicaEnviarRadicacion();
		}
		return instance;
	}
	
	public DTOHabilidadAsociado consultarPendientesAsociadoPorId(Long identificacion) throws Exception {
		return logicaRadicacion.consultarPendientesAsociadoPorId(identificacion);
	}
}
