package co.com.coomeva.ele.logica.inscripcion.plancha;

import java.util.List;

public interface ILogicaProcesoMarcacion {
	void marcarAsociadosConViolaciones();
	List<DTOAsociadoMarcado> generarReporteAsociadosMarcados();
}
