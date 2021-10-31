package co.com.coomeva.ele.logica.inscripcion.plancha;

import co.com.coomeva.ele.dto.DTOHabilidadAsociado;

public interface ILogicaEnviarRadicacion {
	DTOHabilidadAsociado consultarPendientesAsociadoPorId(Long identificacion) throws Exception;
}
