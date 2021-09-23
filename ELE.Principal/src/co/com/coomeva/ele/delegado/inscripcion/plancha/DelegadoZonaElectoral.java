package co.com.coomeva.ele.delegado.inscripcion.plancha;

import java.util.List;

import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleZonaElectoral;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.logica.inscripcion.plancha.ILogicaZonaElectoral;
import co.com.coomeva.ele.logica.inscripcion.plancha.ILogicaZonaElectoralEspecial;
import co.com.coomeva.ele.logica.inscripcion.plancha.LogicaZonaElectoral;
import co.com.coomeva.ele.logica.inscripcion.plancha.LogicaZonaElectoralEspecial;
import co.com.coomeva.ele.modelo.EleZonaElectoralRegionalDTO;
import co.com.coomeva.ele.modelo.FiltrosConsultasDTO;

public class DelegadoZonaElectoral {
	
	private ILogicaZonaElectoral logicaZonaElectoral;
	private ILogicaZonaElectoralEspecial logicaZonaElectoralEspecial;
	
	public static DelegadoZonaElectoral instance;
	
	private DelegadoZonaElectoral(){
	}
	
	public static DelegadoZonaElectoral getInstance(){
		if(instance == null){
			instance = new DelegadoZonaElectoral();
		}
		return instance;
	}

	public List<FiltrosConsultasDTO> consultarZonasElectorales()
		throws EleccionesDelegadosException {
		
		try {
			logicaZonaElectoral = new LogicaZonaElectoral();
			return logicaZonaElectoral.consultarZonasElectorales();
		} finally {
			logicaZonaElectoral = null;
		}
	}
	
	public List<FiltrosConsultasDTO> consultarZonasElectoralesEspecial()
	throws EleccionesDelegadosException {
	
	try {
		logicaZonaElectoralEspecial = new LogicaZonaElectoralEspecial();
		return logicaZonaElectoralEspecial.consultarZonasElectoralesEspeciales();
	} finally {
		logicaZonaElectoral = null;
	}
}
	
	public List<EleZonaElectoralRegionalDTO>  consultarCodigosZonasElectoralesRegionales()
	throws EleccionesDelegadosException {
	
	try {
		logicaZonaElectoral = new LogicaZonaElectoral();
		return logicaZonaElectoral.consultarCodigosZonasElectoralesRegionales();
	} finally {
		logicaZonaElectoral = null;
	}
}
	
}
