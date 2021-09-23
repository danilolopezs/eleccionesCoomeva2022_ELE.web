package co.com.coomeva.habilidad.reglas;

import co.com.coomeva.habilidad.datasources.IDataSourceReglaHabAsociado;


/**
 * Regla que registra inhabilidades de asociados que poseen menos de
 * 6 meses de antiguedad en la cooperativa pero tomando como base los datos
 * de asociados que ya poseen inhabilidades por el mismo tipo 
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
 * @project ELE.procesoHabilidadAsociados
 * @class ReglaAsosAnt6MesesPostPrimeraEje
 * @date 16/11/2012
 *
 */
public class ReglaAsosAnt6MesesPostPrimeraEje extends ReglaAsosAnt6Meses {
	
	public ReglaAsosAnt6MesesPostPrimeraEje(IDataSourceReglaHabAsociado dataSource){
		super(dataSource);
	}
}
