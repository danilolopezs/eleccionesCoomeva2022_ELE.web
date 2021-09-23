package co.com.coomeva.habilidad.datasources;

import java.util.List;

public interface IDataSourceReglaHabAsociado {
	/**
	 * Obtiene los datos para el procesamiento de una regla
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
	 * @date 7/11/2012
	 * @param parametros
	 * @return List<? extends Object>
	 */
	List<? extends Object> obtenerDatosRegla(Object...parametros);
	
	/**
	 * Obtiene los asociados que poseen cuentas de ahorro activas
	 * con débito automático
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
	 * @date 7/11/2012
	 * @return List<Long>
	 */
	List<Long> obtenerAsosCuentaAhorrosActivasDebitoAuto();
	
	/**
	 * Obtiene los asociados que realizaron reestructuración
	 * en sus productos de financiera
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
	 * @date 7/11/2012
	 * @return List<Long>
	 */
	List<Long> obtenerAsosReestrucProdsFinanciera();
	
	/**
	 * Obtiene los asociados que realizaron reestructuración
	 * en sus productos de multiactiva
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
	 * @date 7/11/2012
	 * @return List<Long>
	 */
	List<Long> obtenerAsosReestrucProdsMultiactiva();
	
	/**
	 * Obtiene los asociados cubiertos por el fondo de calamidad
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
	 * @date 7/11/2012
	 * @return List<Long> 
	 */
	List<Long> obtenerAsosCubiertosPorFondoCalamidad();
	
	/**
	 * Obtiene los asociados que pagaron en otras entidades financieras
	 * antes de la fecha de la ejecución del primer proceso
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
	 * @date 16/11/2012
	 * @return List<Long> 
	 */
	List<Long> obtenerAsosConPagosOtraEntidades();
}
