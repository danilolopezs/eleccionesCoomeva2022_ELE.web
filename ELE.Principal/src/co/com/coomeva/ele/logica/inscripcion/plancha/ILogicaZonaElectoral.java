package co.com.coomeva.ele.logica.inscripcion.plancha;

import java.util.List;

import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleZonaElectoral;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.modelo.EleZonaElectoralRegionalDTO;
import co.com.coomeva.ele.modelo.FiltrosConsultasDTO;

public interface ILogicaZonaElectoral {
	
	List<FiltrosConsultasDTO> consultarZonasElectorales()
			throws EleccionesDelegadosException;

	List<EleZonaElectoralRegionalDTO> consultarCodigosZonasElectoralesRegionales()
			throws EleccionesDelegadosException;
	
	List<EleZonaElectoralRegionalDTO> consultarCodigosRegionalDeZonaElectoral()
			throws EleccionesDelegadosException;

	String consultarZonaElectoralByCodigo(Long codigo);
	
	String consultarZonaElectoralEspecialByCodigo(Long codigo);
}
