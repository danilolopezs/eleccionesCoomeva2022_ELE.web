package co.com.coomeva.ele.logica.inscripcion.plancha;

import java.util.List;

import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleZonaElectoral;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.modelo.EleZonaElectoralRegionalDTO;
import co.com.coomeva.ele.modelo.FiltrosConsultasDTO;

public interface ILogicaZonaElectoralEspecial {
	
	List<FiltrosConsultasDTO> consultarZonasElectoralesEspeciales()
			throws EleccionesDelegadosException;	
}
