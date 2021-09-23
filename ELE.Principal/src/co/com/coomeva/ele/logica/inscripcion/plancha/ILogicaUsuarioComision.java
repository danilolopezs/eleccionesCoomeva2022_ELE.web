package co.com.coomeva.ele.logica.inscripcion.plancha;

import java.util.List;

public interface ILogicaUsuarioComision {
	/**
	 * Consulta la zona electoral a la cual
	 * pertenece un usuario de la comision
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
	 * @date 26/12/2012
	 * @param idUsuario
	 * @return List<Long>
	 */
	List<Long> consultarZonaElectUsuarioComision(String idUsuario);
	List<Long> consultarZonaElectEspUsuarioComision(String idUsuario);	
}
