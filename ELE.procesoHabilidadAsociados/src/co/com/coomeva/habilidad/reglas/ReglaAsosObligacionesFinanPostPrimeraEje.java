package co.com.coomeva.habilidad.reglas;

import co.com.coomeva.habilidad.datasources.IDataSourceReglaHabAsociado;

/**
 * Regla que registra inhabilidades por mora en las obligaciones financieras
 * a asociados que cumplen esta condición.  Esta regla toma como base los
 * datos de asociados que ya poseen inhabilidades registradas por el mismo
 * tipo en un anterior proceso
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
 * @project ELE.procesoHabilidadAsociados
 * @class ReglaAsosObligacionesFinanPostPrimeraEje
 * @date 16/11/2012
 *
 */
public class ReglaAsosObligacionesFinanPostPrimeraEje extends
		ReglaAsosObligacionesFinan {
	
	public ReglaAsosObligacionesFinanPostPrimeraEje(IDataSourceReglaHabAsociado dataSource){
		super(dataSource);
	}
}
