package co.com.coomeva.ele.logica.inscripcion.plancha;

import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.dto.DTOHabilidadAsociado;

public class LogicaEnviarRadicacion implements ILogicaEnviarRadicacion{

	@Override
	public DTOHabilidadAsociado consultarPendientesAsociadoPorId(Long identificacion) throws Exception {
		return DelegadoAsociado.getInstance().consultarHabilidadAsociado(identificacion);
	}
}
