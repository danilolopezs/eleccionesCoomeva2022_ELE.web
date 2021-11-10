package co.com.coomeva.ele.util;

/**
 * 
 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
 *         Pragma S.A. <br>
 * @project ELE.Principal
 * @class ConstantesProperties
 * @date 6/11/2012
 * 
 */
public interface ConstantesProperties {
	String NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL = "parametros";
	String NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL = "co.com.coomeva.ele.logica.mensajes.mensajesLogica";
	String NOMBRE_ARCHIVO_ETIQUETAS_WEB = "co.com.coomeva.ele.etiquetas.etiquetas";
	int NUMERO_REGISTROS_POR_PAGINA_CONSULTAS = 1000;
	int NUMERO_REGISTROS_POR_PAGINA_NOVEDADES = 10;
	int NUMERO_REGISTROS_POR_HOJA_XLS = 10000;
	int NUMERO_REGISTROS_POR_PAG_PDF = 12;
	Double VALOR_PORCENTAJE_MUESTRA = 0.1;
	String FORMATO_FECHA = "yyyy-MM-dd";
	String FORMATO_FECHA_TIME_STAMP = "yyyy-MM-dd hh:mm:ss";
	String NOMBRE_ARCHIVO_MENSAJES = "mensajes";
	String ESTADO_EMPLEADO_RETIRADO = "R";
	String CODIGO_ESTADO_PLANCHA_REGISTRADA = "param.cod.estado.plancha.registrada";
	String CODIGO_ESTADO_PLANCHA_INSCRITA = "param.cod.estado.plancha.inscrita";
	String CODIGO_NUMERO_INSCRITO_CABEZA_PLANCHA = "param.cod.numero.inscrito.cabeza.plancha";
	String CODIGO_ESTADO_PLANCHA_ADMITIDA = "param.cod.estado.plancha.admitida";
	String CODIGO_ESTADO_PLANCHA_INADMITIDA = "param.cod.estado.plancha.inadmitida";
	String CODIGO_ESTADO_PLANCHA_EN_RECURSO = "param.cod.estado.plancha.em.recurso";
	String CODIGO_ESTADO_PLANCHA_MODIFICADA = "param.cod.estado.plancha.modificada";
	String CODIGO_ESTADO_INFO_CABEZA_PLANCHA = "codigo.formato.inscripcion.cabeza.plancha";
	String CODIGO_ESTADO_INFO_CABEZA_PLANCHA_REPRESENTANTE = "codigo.formato.inscripcion.cabeza.plancha.representante";
	String CODIGO_ESTADO_NOREPOSICION_SINAPELACION = "codigo.formato.noreposicion.sinapelacion";
	String CODIGO_ESTADO_REPOSICION_CONAPELACION = "codigo.formato.reposicion.conapelacion";
	String CODIGO_ESTADO_APELACION_FAVORABLE = "codigo.formato.apelacion.favorable";
	String CODIGO_ESTADO_APELACION_ENCONTRA = "codigo.formato.apelacion.encontra";
	String CODIGO_ESTADO_CONCEDE_RECURSO_APELACION = "codigo.formato.concede.recurso.apelacion";
	String CODIGO_FORMATO_INSCRIPCION_PLANCHA = "codigo.formato.inscripcion.plancha";
	String CODIGO_ESTADO_DETALLE_FORMATO_PLANCHA_FINALIZADA = "param.cod.estado.detalle.formato.plancha.finalizada";
	String CODIGO_FORMATO_ADMISION_CABEZA_PLANCHA = "codigo.formato.admision.cabeza.plancha";
	String CODIGO_ESTADO_PLANCHA_RECHAZADA = "param.cod.estado.plancha.rechazada";
	String CODIGO_FORMATO_CONSTANCIA_CABEZA_PLANCHA = "codigo.formato.constancia.cabeza.plancha";
	String CODIGO_FORMATO_CONSTANCIA_CUMPLIMIENTO_REQUISITOS = "codigo.formato.constancia.cumplimiento.requisitos";
	String MENSAJE_CONFIRMACION_PLANCHA_RECURSO = "msj.confirma.plancha.recurso";
	String CADENA_ZONA_ELECTORAL = "lblMuestraZonaElectoral";
	String CADENA_ZONA_ELECTORAL_ESPECIAL = "lblMuestraZonaELectoralEspecial";
	String CADENA_ZONA_NUM = "lblZonaElectoralNum";
	String MENSAJE_PLANCHA_DEBE_ESTAR_INADMITIDA = "msg.plancha.debe.estar.inadmitida";
	String MENSAJE_NO_ES_POSIBLE_IMPRIMIR_CONSTANCIA_ADMISION = "msg.imposible.imprimir.resolucion.admision";
	String MENSAJE_NO_ES_POSIBLE_GENERAR_RESOLUCION_RECHAZO_PLANCHA_ADMITIDA = "msg.imposible.generar.resolucion.rechazo.plancha.admitida";
	String MENSAJE_NO_ES_POSIBLE_GENERAR_RESOLUCION_RECHAZO_PLANCHA_INADMITIDA = "msg.imposible.generar.resolucion.rechazo.plancha.inadmitida";
	String NOMBRE_ARCHIVO_PROPIEDADES_PM = "propiedades_pm";
	String MENSAJE_NO_ES_POSIBLE_IMPRIMIR_NOREPOSICION_SINAPELACION = "msg.imposible.imprimir.resolucion.noreposicion.sinapelacion";

	String OPC_MENU_GENERAR_RECIBO_PLANCHA = "opc.menu.generar.recibo.plancha";
	String OPC_MENU_CONSULTAR_CABEZA_POR_PLANCHA = "opc.menu.consultar.plancha.por.cabeza";
	String OPC_MENU_MARCAR_PLANCHA_RECURSO = "opc.menu.marcar.plancha.recurso";
	String OPC_MENU_NUMERAR_PLANCHA = "opc.menu.numerar.plancha";
	String OPC_MENU_NUMERAR_PLANCHA_RECURSO = "opc.menu.numerar.plancha.recurso";
	String OPC_MENU_EXPEDIR_RESOLUCION_RECHAZO = "opc.menu.expedir.resolucion.rechazo";
	String OPC_MENU_GENERAR_ARCHIVO_PLANCHA = "opc.menu.generar.archivo.plancha";
	String OPC_MENU_CONSULTAR_PLANCHA = "opc.menu.consultar.plancha";
	String OPC_MENU_EXPEDIR_RESOLUCION_ADMISION = "opc.menu.expedir.resolucion.admision";
	String OPC_MENU_EXPEDIR_RESOLUCION_CUMPLIMIENTO_REQUISITOS = "opc.menu.expedir.resolucion.cumplimiento.requisitos";
	String OPC_MENU_EXPEDIR_RESOLUCION_INADMISION = "opc.menu.expedir.resolucion.inadmision";
	String OPC_MENU_CUOCIENTE_ELECTORAL = "opc.menu.cuociente.electoral";
	String OPC_MENU_CARGAR_ENT_ELECTORALES = "opc.menu.cargar.entidades.electorales";
	String OPC_MENU_CARGAR_SANCIONADOS = "opc.menu.cargar.sancionados";
	String OPC_MENU_EJECUTAR_PROCESO_INHABILIDAD = "opc.menu.ejecutar.proceso.inhabilidad";
	String OPC_MENU_REPORTES_HABILIDAD_ASOCIADOS = "opc.menu.reportes.habilidad.asociados";
	String OPC_MENU_CARGAR_SUSPENDIDOS = "opc.menu.cargar.suspendidos";
	String MENSAJE_IMPOSIBLE_INADMITIR_PLANCHA_NO_REGISTRADA = "msg.imposible.inadmitir.plancha.no.registrada";

	String PAR_MENSAJE_JUGADOR_HABILITADO_PARA_SEGUIR_JUGANDO = "1";
	String PAR_MENSAJE_JUGADOR_YA_JUGO_EN_LA_FASE = "2";
	String PAR_MENSAJE_JUGADOR_NO_FIGURA_COMO_ASOCIADO = "3";
	String PAR_MENSAJE_JUGADOR_NO_ACTIVO_COMO_ASOCIADO = "4";
	String PAR_MENSAJE_JUGADOR_NO_PERTENECE_A_ZONA_HABILITADA = "5";

	String PAR_MENSAJE_VOTANTE_INHAHABILITADO = "1";
	String PAR_MENSAJE_VOTANTE_NO_PERTENECE_A_ZONA_VOTACION = "2";
	String PAR_MENSAJE_VOTANTE_HABILITADO_PARA_VOTAR = "3";
	String PAR_MENSAJE_VOTANTE_YA_REGISTRA_VOTACION = "4";

	String LBL_TITLE_NO_REPOSICION_SIN_APELACION = "lblTitleNoReposicionSinApelacion";
	String LBL_TITLE_RESOLUCION_EN_CONTRA_CON_APELACION = "lblTitleResolucionEnContraConApelacion";
	String LBL_TITLE_RESOLUCION_APELACION_EN_CONTRA = "lblTitleResolucionApelacionEnContra";
	String LBL_TITLE_RESOLUCION_APELACION_FAVORABLE = "lblTituloApelacionFavorable";

	String OPC_MENU_ELECCIONES_VOTANTES_REGISTRAR = "opc.menu.elecciones.votantes.registrar";
	String OPC_MENU_REPORTE_JUEGO = "opc.menu.reporte.juego";
	String OPC_MODIFICACION_PLANCHAS = "opc.modificacion.plancha";

	String MENSAJE_PLANCHA_YA_ESTA_EN_RECURSO = "msg.plancha.ya.esta.en.recurso";
	String MENSAJE_PLANCHA_NO_PUEDE_ESTAR_EN_RECURSO = "msg.plancha.no.puede.estar.en.recurso";
	String MENSAJE_IMPOSIBLE_IMPRIMIR_RESOLUCION_RECURSOS = "msg.imposible.imprimir.resolucion.recursos";

	String LBL_TITLE_RESOLUCION_CONCEDE_RECURSO_DE_APELACION = "lblTitleResolucionConcedeRecursoDeApelacion";
	
	String LBL_CONFIRMACION_FIRMA_PRESENCIAL = "confirmacionFirmaPresencial";
	String LBL_CONFIRMACION_FIRMA_VIRTUAL = "confirmacionFirmaVirtual";
}
