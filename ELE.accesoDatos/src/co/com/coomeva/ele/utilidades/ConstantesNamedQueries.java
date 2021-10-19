package co.com.coomeva.ele.utilidades;

/**
 * Interfaz para la referenciación de NamedQueries
 * 
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
 *         Premize SAS <br>
 * @project ELE.accesoDatos
 * @class ConstantesNamedQueries
 * @date 1/11/2012
 * 
 */
public interface ConstantesNamedQueries {
	String QUERY_ASOCIADOS_FECHA_INGRESO_SUPERIOR_DET_FECHA = "asociados.fecha.ingreso.superior.determinada.fecha";
	String QUERY_SEQ_ELE_INHABILIDAD = "consultar.secuencia.ele.ele.inhabilidad";
	String QUERY_POBLAR_TABLA_ELE_ASOCIADO = "consulta.poblar.tabla.ele.asociado";
	String QUERY_ASOCIADOS_MORA_OBLIGACIONES_FINANCIERAS = "consulta.asociados.mora.obligaciones.financieras";
	String QUERY_ASOCIADOS_SUSPENDIDOS_RETIRADOS_ACTIVOS = "consulta.asociados.suspendidos.retirados.activos";
	String QUERY_INHABILIDADES_PROCESO_VALOR_MORA_MENOR_IGUAL_X = "consulta.inhabilidades.proceso.valor.mora.x";
	String QUERY_ASOCIADOS_CHEQUES_DEVUELTOS = "consulta.asociados.cheques.devueltos";
	String QUERY_ASOCIADOS_CUENTAS_AHORRO_ACTIVAS_DEB_AUTO = "consulta.asociados.cuentas.ahorro.activas";
	String QUERY_ASOCIADOS_CUBIERTOS_FONDO_CALAMIDAD = "consulta.asociados.cubiertos.fondo.calamidad";
	String QUERY_INHABILIDADES_PROCESO_VALOR_MORA_NO_NULL = "consulta.inhabilidades.proceso.valor.mora.no.null";
	String QUERY_HABILIDAD_DE_ASOCIADO = "consulta.habilidad.asociado";
	String QUERY_OBSERVACIONES_INHABILIDADES_ASOCIADO = "consulta.observaciones.inhabilidades.asociado";
	String QUERY_NOMBRES_APELLIDOS_ASOCIADO = "consultar.nombre.asociado";
	String QUERY_ASOCIADOS_REESTRUCTURADOS_MULTIACTIVA = "consulta.asociados.reestructurados.multiactiva";
	String QUERY_ASOCIADOS_REESTRUCTURADOS_FINANCIERA = "consulta.asociados.reestructurados.financiera";
	String QUERY_ASOCIADOS_PAGOS_OTRAS_ENTIDADES = "consulta.asociados.pagos.otras.entidades";
	String QUERY_ASOCIADOS_INHABILES_ANTERIOR_PROCESO_POR_REGLA = "consulta.asociados.inhabiles.anterior.proceso.por.regla";
	String QUERY_REGISTRAR_INHABILIDADES_POR_CAMBIO_ESTADO_BUC = "consulta.registrar.inhabilidades.por.cambio.estado.buc";
	String QUERY_REGISTRAR_NOVEDAD_ASOCIAD0S_INHABILES = "consulta.registrar.novedad.asociados.inhabiles";
	String QUERY_REGISTRAR_NOVEDAD_ASOCIAD0S_HABILES = "consulta.registrar.novedad.asociados.habiles";
	String QUERY_CONSULTA_SI_ES_ASOCIADO = "consulta.si.es.asociado";
	String QUERY_CONSULTA_SI_ES_ASOCIADO_PERSONA_JURIDICA = "consulta.si.es.asociado.persona.juridica";
	String QUERY_CONSULTAR_NOMBRE_COMPLETO_ASOCIADO_BUC = "consultar.nombre.asociado.buc";
	String QUERY_CONSULTAR_NOMBRE_ASOCIADO_ZONA_BUC = "consultar.nombre.zona.asociado.buc";
	String QUERY_SEQ_ELE_LOG_ASOCIADO = "consultar.secuencia.ele.log.asociado";
	String QUERY_PLANCHAS_POR_ASOCIADO = "consulta.planchas.asociado";
	String QUERY_ZONA_ELECTORAL_ASOCIADO = "consulta.zona.electoral.asociado";
	String QUERY_SEQ_CUOCIENTE_ELECTORAL = "consultar.secuencia.ele.consec.cuociente.electoral";
	String QUERY_INTEGRANTES_OTRAS_ENTIDADES_ELECTORALES = "consulta.integrantes.otras.entidades.electorales";
	String QUERY_ASOCIADOS_SANCIONADOS_5_ANNOS_ATRAS = "consulta.asociados.sancionados.5.annos.atras";
	String QUERY_SEQ_CUOCIENTE_DELEGADOS_ZONA = "consultar.secuencia.ele.consec.cuociente.delegados.zona";
	String QUERY_SEQ_CUOCIENTE_REGIONAL = "consultar.secuencia.ele.consec.cuociente.delegados.regional";	
	String QUERY_SEQ_ELE_PLANCHA_ASOCIADO = "consultar.secuencia.ele.plancha.asociado";
	String QUERY_SEQ_ELE_PLANCHA = "consultar.secuencia.ele.plancha";
	String QUERY_CONSULTAR_INFO_ASOCIADO = "consultar.info.asociado";
	String QUERY_ASOCIADOS_EMPLEADOS_ACTIVOS = "consulta.asociados.empleados.activos";
	String QUERY_ASOCIADOS_EMPLEADOS_RETIRADOS = "consulta.asociados.empleados.retirados";
	String QUERY_ZONA_ELECTORAL_POR_USUARIO_COMISION = "consulta.zona.electoral.por.usuario.comision";
	String QUERY_ZONA_ELECTORAL_ESPE_POR_USUARIO_COMISION = "consulta.zona.electoral.espe.por.usuario.comision";
	String QUERY_SEQ_PLANCHA_EXCEPCION = "consultar.secuencia.ele.plancha.excepcion";
	String QUERY_NUMERO_ZONA_POR_CODIGOZONA = "consulta.zona.electoral.numero.por.codigozona";
	String QUERY_MARCACION_ASOCIADOS_ESCRUTINIOS = "consulta.marcacion.asociados.escrutinios";
	String QUERY_SEQ_ELE_PROCESO_LOG = "consultar.secuencia.ele.proceso.log";
	String QUERY_SEQ_ELE_PARTICIPANTE_JUEGO = "consultar.secuencia.ele.ele.participante.juego";
	String QUERY_CONSULTAR_BOLETAS_ASIGNADAS_JUEGO = "consultar.boletas.asignadas.juego";
	String QUERY_CONSULTAR_HABILIDAD_ASOCIADO_ZONA_JUEGO = "consultar.habilidad.asociado.zona.juego";
	String QUERY_VALIDACION_ZONA_HAY_VOTACION = "valida.zonaelectoral.asociado.Hayvotacion";
	String QUERY_CONSULTAR_VOTANTE_PRIMERA_VEZ = "consultar.votante.primera.vez";
	String QUERY_CONSULTAR_VOTANTE_USUARIO_QUE_REGISTRA = "consultar.votante.usuario.que.registra";
	String QUERY_CONSULTAR_DATOS_VOTANTE = "consultar.datos.votante";	
	String QUERY_CONSULTAR_TOTAL_JUEGOS = "consultar.total.juegos";
	String QUERY_CONSULTAR_ES_PLANCHA_RADICADA = "consultar.es.plancha.radicada";
	String QUERY_CONSULTAR_NOVEDAD_HABILIDAD_ASOCIADO = "consultar.novedad.habilidad.asociado";
	String QUERY_SEQ_ELE_NOVEDAD = "consultar.secuencia.ele.novedad";
	String QUERY_CONSULTAR_ZONAS_ELECTORALES_REGIONAL = "consulta.zonas.electorales.con.regional";
	String QUERY_CONSULTAR_REGIONALES_DE_ZONA_ELECTORAL = "consulta.regionales.zona.electoral";
}
