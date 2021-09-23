package co.com.coomeva.habilidad.procesos;

import co.com.coomeva.ele.entidades.habilidad.EleProceso;
import co.com.coomeva.habilidad.exception.AssociateAbilityException;

/**
 * Interfaz que define el método que deben ejecutar en los procesos
 * de habilidad de asociados
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
 * @project ELE.procesosHabilidadAsociados
 * @class IProcesoVerificacionHabilidad
 * @date 1/11/2012
 *
 */
public interface IProcesoVerificacionHabilidadAsociados {
	void ejecutarProcesoVerificacionHabilidad(String[] parametros) throws AssociateAbilityException;
	void ejecutarProcesoVerificacionHabilidad(EleProceso proceso) throws AssociateAbilityException;
}
