package co.com.coomeva.ele.logica.inscripcion.plancha;

import java.util.List;

import co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlanchaExcepcion;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;


/**
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
 * @project ELE.Principal
 * @class ILogicaPlanchaExcepcion
 * @date 11/01/2013
 */
public interface ILogicaPlanchaExcepcion {
	
	/**
	 * Registra una excepción asociada a una plancha
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
	 * @date 11/01/2013
	 * @param consecutivoPlancha
	 * @param codigoAsociado
	 * @param excepciones
	 * @throws EleccionesDelegadosException
	 */
	void registrarPlanchaExcepcion(Long consecutivoPlancha,
			Long codigoAsociado, String excepciones) throws EleccionesDelegadosException;
	
	public List<ElePlanchaExcepcion> consultarExcepciones(String identificacionAsociado, String consecutivoPlancha) throws EleccionesDelegadosException;
}
