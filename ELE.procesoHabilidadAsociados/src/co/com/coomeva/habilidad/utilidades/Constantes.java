package co.com.coomeva.habilidad.utilidades;

/**
 * Interface que contiene constantes que serán utilizadas en diversas porciones
 * del código de ELE.procesoHabilidadAsociados
 * 
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
 *         Premize SAS <br>
 * @project ELE.procesoHabilidadAsociados
 * @class Constantes
 * @date 1/11/2012
 * 
 */
public interface Constantes {
	String NOMBRE_ARCHIVO_PARAMETROS = "parametros_proceso_habilidad";
	Long CANTIDAD_REGISTROS_POR_COMMIT = 5000L;
	String ARGUMENTO_NOMBRADO_PLOBAR_TABLA_ELE_ASOCIADO = "ptea";
	Long MAXIMO_VALOR_PERMITIDO_DEUDA = 10000L;
	String CODIGO_ESTADO_PROCESO_EJECUTADO = "2";
	Long CODIGO_REGLA_VALIDACION_ANT_ASOCIADO = 1L;
	Long CODIGO_REGLA_VALIDACION_OBLIGACIONES_FINANCIERAS = 2L;
	Long CANTIDAD_REGISTROS_POR_DEFECTO_PLANCHA = 5L;
	Long CANTIDAD_MAXIMA_REGISTROS_PLANCHA = 19L;
	Long CODIGO_REGLA_VALIDACION_ANT_ASOCIADO_POST_PRIMERA_EJE = 5L;
	Long CODIGO_REGLA_VALIDACION_OBLIGACIONES_FINANCIERAS_POST_PRIMERA_EJE = 6L;
}
