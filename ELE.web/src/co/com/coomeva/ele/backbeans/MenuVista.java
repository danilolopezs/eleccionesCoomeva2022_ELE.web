package co.com.coomeva.ele.backbeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import co.com.coomeva.ele.dto.DTOHabilidadAsociado;
import co.com.coomeva.ele.modelo.ParametroPlanchaDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.ele.util.LectorParametros;
import co.com.coomeva.profile.ws.client.CaasStub.Section;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.DateManipultate;
import co.com.coomeva.util.date.ManipulacionFechas;

public class MenuVista {

	public static final String CONSULTAR_CABEZA_POR_PLANCHA = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PROPIEDADES_PM,
			ConstantesProperties.OPC_MENU_CONSULTAR_CABEZA_POR_PLANCHA);

	public static final String CONSULTAR_PLANCHA = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PROPIEDADES_PM, ConstantesProperties.OPC_MENU_CONSULTAR_PLANCHA);

	public static final String CARGAR_ENT_ELECTORALES = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PROPIEDADES_PM, ConstantesProperties.OPC_MENU_CARGAR_ENT_ELECTORALES);

	public static final String CARGAR_SANCIONADOS = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PROPIEDADES_PM, ConstantesProperties.OPC_MENU_CARGAR_SANCIONADOS);

	public static final String MARCAR_PLANCHA_RECURSO = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PROPIEDADES_PM, ConstantesProperties.OPC_MENU_MARCAR_PLANCHA_RECURSO);

	public static final String NUMERAR_PLANCHA = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PROPIEDADES_PM, ConstantesProperties.OPC_MENU_NUMERAR_PLANCHA);

	public static final String CUOCIENTE_ELECTORAL = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PROPIEDADES_PM, ConstantesProperties.OPC_MENU_CUOCIENTE_ELECTORAL);

	public static final String CARGAR_SUSPENDIDOS = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PROPIEDADES_PM, ConstantesProperties.OPC_MENU_CARGAR_SUSPENDIDOS);

	public static final String EJECUTAR_PROCESO_HABILIDAD = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PROPIEDADES_PM,
			ConstantesProperties.OPC_MENU_EJECUTAR_PROCESO_INHABILIDAD);

	public static final String REPORTES_HABILIDAD_ASOCIADOS = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PROPIEDADES_PM,
			ConstantesProperties.OPC_MENU_REPORTES_HABILIDAD_ASOCIADOS);

	public static final String GENERAR_RECIBO_PLANCHA = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PROPIEDADES_PM, ConstantesProperties.OPC_MENU_GENERAR_RECIBO_PLANCHA);

	public static final String EXPEDIR_RESOLUCION_RECHAZO = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PROPIEDADES_PM,
			ConstantesProperties.OPC_MENU_EXPEDIR_RESOLUCION_RECHAZO);

	public static final String GENERAR_ARCHIVOS_RESOLUCION_RECURSOS = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PROPIEDADES_PM, ConstantesProperties.OPC_MENU_GENERAR_ARCHIVO_PLANCHA);

	public static final String EXPEDIR_RESOLUCION_ADMISION = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PROPIEDADES_PM,
			ConstantesProperties.OPC_MENU_EXPEDIR_RESOLUCION_ADMISION);
	
	public static final String EXPEDIR_RESOLUCION_CUMPLIMIENTO_REQUISITOS = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PROPIEDADES_PM,
			ConstantesProperties.OPC_MENU_EXPEDIR_RESOLUCION_CUMPLIMIENTO_REQUISITOS);

	public static final String EXPEDIR_RESOLUCION_INADMISION = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PROPIEDADES_PM,
			ConstantesProperties.OPC_MENU_EXPEDIR_RESOLUCION_INADMISION);

	public static final String NUMERAR_PLANCHA_RECURSO = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PROPIEDADES_PM, ConstantesProperties.OPC_MENU_NUMERAR_PLANCHA_RECURSO);

	public static final String ELECCIONES_REGISTRAR_VOTANTE = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PROPIEDADES_PM,
			ConstantesProperties.OPC_MENU_ELECCIONES_VOTANTES_REGISTRAR);

	public static final String ELECCIONES_REPORTE_JUEGO = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PROPIEDADES_PM, ConstantesProperties.OPC_MENU_REPORTE_JUEGO);

	public static final String MODIFICACION_PLANCHAS = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PROPIEDADES_PM, ConstantesProperties.OPC_MODIFICACION_PLANCHAS);

	private static final String[] OPCIONES_MENU_PLANCHAS = { CONSULTAR_CABEZA_POR_PLANCHA, CONSULTAR_PLANCHA,
			CARGAR_ENT_ELECTORALES, CARGAR_SANCIONADOS, MARCAR_PLANCHA_RECURSO, NUMERAR_PLANCHA, CUOCIENTE_ELECTORAL,
			NUMERAR_PLANCHA_RECURSO, ELECCIONES_REGISTRAR_VOTANTE, ELECCIONES_REPORTE_JUEGO, MODIFICACION_PLANCHAS };

	private static final String[] OPCIONES_MENU_HABILIDAD = { EJECUTAR_PROCESO_HABILIDAD, CARGAR_SUSPENDIDOS,
			REPORTES_HABILIDAD_ASOCIADOS };

	public boolean[] indicadorVisibilidadOpcionPlanchas = new boolean[OPCIONES_MENU_PLANCHAS.length];
	public boolean[] indicadorVisibilidadOpcionHabilidad = new boolean[OPCIONES_MENU_HABILIDAD.length];

	// Booleano que me dirá si debo o no visualizar el menu de "Habilidad Asociados"
	public boolean indicadorVisibilidadHabilidad;

	private boolean renderAdmHabilidad;
	private boolean renderSecReporte;
	private boolean renderReporteHabilidad;
	private boolean renderReporteLogHab;
	private boolean renderModificarPlancha;
	private boolean renderSanearPlancha;
	private boolean renderRecibirPlancha;
	private boolean renderNumerarPlancha;
	private boolean renderNumerarPlanchaEnRecurso;
	private boolean renderEvaluarPlancha;
	private boolean renderConsultarPlancha;
	private boolean renderInicioPlanchas;
	private boolean renderInicioHab;
	private boolean renderReporteLog;
	private boolean renderGenerarArchivo;
	private boolean renderIniciarProcesoVerificacionHabilidad;
	private boolean renderRegistrarVotante;
	private boolean renderReporteJuego;
	private boolean renderRegistroNovedadesHabilidad;
	private boolean renderModificacionPlanchas;

	private boolean pintarBotonGenerarReciboPlancha;
	private boolean pintarBotonExpedirResolucionRechazo;
	private boolean pintarBotonExpedirResolucionAdmision;
	private boolean pintarBotonExpedirResolucionInadmision;
	private boolean pintarBotonExpedirCumplimientoRequisitos;

	private boolean renderExpedirCumplimiento;

	// varoable utilizada para ver la opción de generar archivos de resolucion de
	// recursos
	private boolean pintarBotonGenerarArchivo;

	private Date dateToday = new Date();
	private ParametroPlanchaDTO parametroFechaInicial;
	private ParametroPlanchaDTO parametroFechaFinal;

	private Date dateFechaIniInscrpcion;
	private Date dateFechaFinInscrpcion;

	private boolean esUsuarioComision;// atributo para identificar si es un
										// usuario de la comisión electoral

	private boolean renderDesbloUser;

	public MenuVista() {

		if (FacesUtils.getSessionParameter("userComision") != null) {
			esUsuarioComision = true;
		} else {
			esUsuarioComision = false;
		}

		for (int i = 0; i < indicadorVisibilidadOpcionPlanchas.length; i++) {
			indicadorVisibilidadOpcionPlanchas[i] = Boolean.FALSE;
		}

		for (int i = 0; i < indicadorVisibilidadOpcionHabilidad.length; i++) {
			indicadorVisibilidadOpcionHabilidad[i] = Boolean.FALSE;
		}

		init();
		renderizarOpcionesMenu();

	}

	/**
	 * Metodo que recibe las secciones de usuarios y dependiendo de las secciones
	 * habilitadas renderiza el menu de las JSPX
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 */
	@SuppressWarnings("unchecked")
	private void init() {
		try {
			HashMap<String, Section> secciones = (HashMap<String, Section>) FacesUtils
					.getSessionParameter("userSeccions");
			// if (secciones.get(UtilAcceso.getParametroFuenteS("parametros",
			// "secInhabilidad"))!=null) {
			// renderAdmHabilidad = true;
			// renderSecReporte = true;
			// renderReporteHabilidad = true;
			// renderReporteLogHab = true;
			// renderInicioHab = true;
			// renderInicioPlanchas = false;
			// renderConsultarPlancha = false;
			// renderSanearPlancha = false;
			// renderModificarPlancha = false;
			// renderConsultarPlancha = false;
			// renderRecibirPlancha = false;
			// renderEvaluarPlancha = false;
			// renderReporteLog = false;
			// renderGenerarArchivo = false;
			//
			// }else

			if (secciones.get(UtilAcceso.getParametroFuenteS("parametros", "secAdminUser")) != null) {

				Section secAdminUser = secciones.get(UtilAcceso.getParametroFuenteS("parametros", "secAdminUser"));
				String[] acciones = secAdminUser.getActions();
				for (int i = 0; i < acciones.length; i++) {
					if (acciones[i].equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "accDesbloUser"))) {
						renderDesbloUser = true;
					}
				}
			}

			this.indicadorVisibilidadHabilidad = false;
			if (secciones.get(UtilAcceso.getParametroFuenteS("parametros", "secInhabilidad")) != null) {
				renderInicioHab = true;
				renderInicioPlanchas = false;

				renderRegistroNovedadesHabilidad = false;
				this.indicadorVisibilidadHabilidad = true;

				Section secInhabilidad = secciones.get(UtilAcceso.getParametroFuenteS("parametros", "secInhabilidad"));
				String[] acciones = secInhabilidad.getActions();
				for (int i = 0; i < acciones.length; i++) {
					if (acciones[i].equalsIgnoreCase(
							UtilAcceso.getParametroFuenteS("parametros", "accRegistroNovedadesHabilidad"))) {
						renderRegistroNovedadesHabilidad = true;
					}
				}
			}

			if (secciones.get(UtilAcceso.getParametroFuenteS("parametros", "secPlancha")) != null) {
				renderInicioHab = false;
				renderInicioPlanchas = true;
				renderAdmHabilidad = false;
				renderSecReporte = false;
				renderReporteHabilidad = false;
				renderReporteLogHab = false;
				renderGenerarArchivo = false;
				renderNumerarPlancha = false;
				renderNumerarPlanchaEnRecurso = false;
				renderRegistrarVotante = false;
				renderReporteJuego = false;
				renderModificacionPlanchas = false;
				renderExpedirCumplimiento = false;

				Section secPlanchas = secciones.get(UtilAcceso.getParametroFuenteS("parametros", "secPlancha"));
				String[] acciones = secPlanchas.getActions();

				for (int i = 0; i < acciones.length; i++) {
					if (acciones[i]
							.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "accConsultarPlancha"))) {
						renderConsultarPlancha = true;
					}
					if (acciones[i]
							.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "accEvaluarPlancha"))) {
						renderEvaluarPlancha = true;
					}
					if (acciones[i]
							.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "accModificarPlancha"))) {
						renderModificarPlancha = true;
					}
					if (acciones[i]
							.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "accRegistrarVotante"))) {
						renderRegistrarVotante = true;
					}
					if (acciones[i].equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "accReporteJuego"))) {
						renderReporteJuego = true;
					}
					if (acciones[i].equalsIgnoreCase(
							UtilAcceso.getParametroFuenteS("parametros", "accModificacionPlanchas"))) {
						/*
						 * Date fechaInicioModificacion = ManipulacionFechas .stringToDate( UtilAcceso
						 * .getParametroFuenteS(
						 * ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
						 * "param.fecha.inicio.modificacion.planchas"), "yyyy-MM-dd hh:mm:ss");
						 * 
						 * Date fechaFinalModificacion = ManipulacionFechas .stringToDate( UtilAcceso
						 * .getParametroFuenteS(
						 * ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
						 * "param.fecha.final.modificacion.planchas"), "yyyy-MM-dd hh:mm:ss");
						 * 
						 * if (dateToday.getTime() >= fechaInicioModificacion .getTime() &&
						 * dateToday.getTime() <= fechaFinalModificacion .getTime()) {
						 * renderModificacionPlanchas = true; } else { renderModificacionPlanchas =
						 * false; }
						 */
						// Si es usuario de la comisón electoral visualizar el menú de modificación
						// planchas:
						if (esUsuarioComision) {
							renderModificacionPlanchas = true;
						} else {
							renderModificacionPlanchas = false;
						}
					}

					if (acciones[i]
							.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "accNumerarPlancha"))) {
						/**
						 * Se debe validar si la fecha actual está dentro del rango de fechas hábiles
						 * para la ejecución del proceso de numeración de planchas
						 */
						parametroFechaInicial = LectorParametros.obtenerParametrosCodigoTipo(
								UtilAcceso.getParametroFuenteL("parametros", "campo.param.numeracion.inicio"),
								UtilAcceso.getParametroFuenteL("parametros", "campo.param.numeracion.tipo"));
						parametroFechaFinal = LectorParametros.obtenerParametrosCodigoTipo(
								UtilAcceso.getParametroFuenteL("parametros", "campo.param.numeracion.fin"),
								UtilAcceso.getParametroFuenteL("parametros", "campo.param.numeracion.tipo"));

						if (parametroFechaInicial == null || parametroFechaFinal == null) {
							renderNumerarPlancha = false;
						} else {
							dateFechaIniInscrpcion = ManipulacionFechas
									.stringToDate(parametroFechaInicial.getStrValor(), "yyyy-MM-dd hh:mm:ss");
							dateFechaFinInscrpcion = ManipulacionFechas.stringToDate(parametroFechaFinal.getStrValor(),
									"yyyy-MM-dd hh:mm:ss");

							if (dateToday.getTime() >= dateFechaIniInscrpcion.getTime()
									&& dateToday.getTime() <= dateFechaFinInscrpcion.getTime()) {
								renderNumerarPlancha = true;
							}
						}
					}

					if (acciones[i].equalsIgnoreCase(
							UtilAcceso.getParametroFuenteS("parametros", "accNumerarPlanchaRecurso"))) {
						/**
						 * Se debe validar si la fecha actual está dentro del rango de fechas hábiles
						 * para la ejecución del proceso de numeración de planchas
						 */
						parametroFechaInicial = LectorParametros.obtenerParametrosCodigoTipo(
								UtilAcceso.getParametroFuenteL("parametros", "campo.param.numeracion.inicio2"),
								UtilAcceso.getParametroFuenteL("parametros", "campo.param.numeracion.tipo"));
						parametroFechaFinal = LectorParametros.obtenerParametrosCodigoTipo(
								UtilAcceso.getParametroFuenteL("parametros", "campo.param.numeracion.fin2"),
								UtilAcceso.getParametroFuenteL("parametros", "campo.param.numeracion.tipo"));

						if (parametroFechaInicial == null || parametroFechaFinal == null) {
							renderNumerarPlanchaEnRecurso = false;
						} else {
							dateFechaIniInscrpcion = ManipulacionFechas
									.stringToDate(parametroFechaInicial.getStrValor(), "yyyy-MM-dd hh:mm:ss");
							dateFechaFinInscrpcion = ManipulacionFechas.stringToDate(parametroFechaFinal.getStrValor(),
									"yyyy-MM-dd hh:mm:ss");

							if (dateToday.getTime() >= dateFechaIniInscrpcion.getTime()
									&& dateToday.getTime() <= dateFechaFinInscrpcion.getTime()) {
								renderNumerarPlanchaEnRecurso = true;
							}
						}
					}

					if (acciones[i]
							.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "accRecibirPlancha"))) {
						renderRecibirPlancha = true;
					}
					if (acciones[i]
							.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "accSanearPlancha"))) {
						renderSanearPlancha = true;
					}
					if (acciones[i].equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "accReporteLog"))) {
						renderReporteLog = true;
					}
					if (acciones[i]
							.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "accGenerarArchivo"))) {
						renderGenerarArchivo = true;
					}
					if (acciones[i]
							.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "accRegistrarVotante"))) {
						renderRegistrarVotante = true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Renderiza las opciones del menú dependiendo de la parametrización del profile
	 * manager
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 31/12/2012
	 */
	private void renderizarOpcionesMenu() {

		try {
			HashMap<String, Section> secciones = (HashMap<String, Section>) FacesUtils
					.getSessionParameter("userSeccions");

			if (secciones.get(UtilAcceso.getParametroFuenteS("parametros", "secPlancha")) != null) {

				Section secPlanchas = secciones.get(UtilAcceso.getParametroFuenteS("parametros", "secPlancha"));

				String[] acciones = secPlanchas.getActions();

				List<String> listaAcciones = new ArrayList<String>(Arrays.asList(acciones));
				List<String> listaOpcionesMenuPlanchas = new ArrayList<String>(Arrays.asList(OPCIONES_MENU_PLANCHAS));

				int i = 0;
				for (String opcion : listaOpcionesMenuPlanchas) {
					if (listaAcciones.contains(opcion)) {
						indicadorVisibilidadOpcionPlanchas[i] = Boolean.TRUE;
					}

					/**
					 * TODO OJO QUITAR ESTO...!!!!!!!!! indicadorVisibilidadOpcionPlanchas
					 * [indicadorVisibilidadOpcionPlanchas.length-1] = Boolean.TRUE;
					 * indicadorVisibilidadOpcionPlanchas[
					 * indicadorVisibilidadOpcionPlanchas.length-3] = Boolean.TRUE;
					 */

					if (listaAcciones.contains(GENERAR_RECIBO_PLANCHA)) {
						this.pintarBotonGenerarReciboPlancha = Boolean.TRUE;
					}

					if (listaAcciones.contains(EXPEDIR_RESOLUCION_ADMISION)) {
						this.pintarBotonExpedirResolucionAdmision = Boolean.TRUE;
					}

					if (listaAcciones.contains(EXPEDIR_RESOLUCION_CUMPLIMIENTO_REQUISITOS)) {
						this.pintarBotonExpedirCumplimientoRequisitos = Boolean.TRUE;
					}
					this.pintarBotonExpedirCumplimientoRequisitos = Boolean.TRUE;
					
					if (listaAcciones.contains(EXPEDIR_RESOLUCION_INADMISION)) {
						this.pintarBotonExpedirResolucionInadmision = Boolean.TRUE;
					}

					if (listaAcciones.contains(EXPEDIR_RESOLUCION_RECHAZO)) {
						this.pintarBotonExpedirResolucionRechazo = Boolean.TRUE;
					}

					if (listaAcciones.contains(GENERAR_ARCHIVOS_RESOLUCION_RECURSOS)) {
						this.pintarBotonGenerarArchivo = Boolean.TRUE;
					}

					if (listaAcciones
							.contains(UtilAcceso.getParametroFuenteS("parametros", "accExpedirCumplimiento"))) {
						renderExpedirCumplimiento = true;
					}

					i++;
				}
			}

			if (secciones.get(UtilAcceso.getParametroFuenteS("parametros", "secInhabilidad")) != null) {

				Section secPlanchas = secciones.get(UtilAcceso.getParametroFuenteS("parametros", "secInhabilidad"));

				String[] acciones = secPlanchas.getActions();

				List<String> listaAcciones = new ArrayList<String>(Arrays.asList(acciones));
				List<String> listaOpcionesMenuHabilidad = new ArrayList<String>(Arrays.asList(OPCIONES_MENU_HABILIDAD));

				int i = 0;
				for (String opcion : listaOpcionesMenuHabilidad) {
					if (listaAcciones.contains(opcion)) {
						indicadorVisibilidadOpcionHabilidad[i] = Boolean.TRUE;
					}
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String salir() {
		FacesUtils.setSessionParameter("user", null);
		FacesUtils.setSessionParameter("zonaSubComision", null);
		FacesUtils.setSessionParameter("userComision", null);
		FacesUtils.setSessionParameter("asociado", null);
		FacesUtils.setSessionParameter("numeroDocAsociado", null);
		FacesUtils.setSessionParameter("tipoElecciones", null);
		return "goLogin";
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isRenderAdmHabilidad() {
		return renderAdmHabilidad;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param renderAdmHabilidad
	 */
	public void setRenderAdmHabilidad(boolean renderAdmHabilidad) {
		this.renderAdmHabilidad = renderAdmHabilidad;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isRenderSecReporte() {
		return renderSecReporte;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param renderSecReporte
	 */
	public void setRenderSecReporte(boolean renderSecReporte) {
		this.renderSecReporte = renderSecReporte;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isRenderReporteHabilidad() {
		return renderReporteHabilidad;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param renderReporteHabilidad
	 */
	public void setRenderReporteHabilidad(boolean renderReporteHabilidad) {
		this.renderReporteHabilidad = renderReporteHabilidad;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isRenderReporteLogHab() {
		return renderReporteLogHab;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param renderReporteLogHab
	 */
	public void setRenderReporteLogHab(boolean renderReporteLogHab) {
		this.renderReporteLogHab = renderReporteLogHab;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isRenderModificarPlancha() {
		return renderModificarPlancha;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param renderModificarPlancha
	 */
	public void setRenderModificarPlancha(boolean renderModificarPlancha) {
		this.renderModificarPlancha = renderModificarPlancha;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isRenderSanearPlancha() {
		return renderSanearPlancha;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param renderSanearPlancha
	 */
	public void setRenderSanearPlancha(boolean renderSanearPlancha) {
		this.renderSanearPlancha = renderSanearPlancha;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isRenderRecibirPlancha() {
		return renderRecibirPlancha;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param renderRecibirPlancha
	 */
	public void setRenderRecibirPlancha(boolean renderRecibirPlancha) {
		this.renderRecibirPlancha = renderRecibirPlancha;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isRenderNumerarPlancha() {
		return renderNumerarPlancha;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param renderNumerarPlancha
	 */
	public void setRenderNumerarPlancha(boolean renderNumerarPlancha) {
		this.renderNumerarPlancha = renderNumerarPlancha;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isRenderConsultarPlancha() {
		return renderConsultarPlancha;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param renderConsultarPlancha
	 */
	public void setRenderConsultarPlancha(boolean renderConsultarPlancha) {
		this.renderConsultarPlancha = renderConsultarPlancha;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isRenderEvaluarPlancha() {
		return renderEvaluarPlancha;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param renderEvaluarPlancha
	 */
	public void setRenderEvaluarPlancha(boolean renderEvaluarPlancha) {
		this.renderEvaluarPlancha = renderEvaluarPlancha;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isRenderInicioHab() {
		return renderInicioHab;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param renderInicioHab
	 */
	public void setRenderInicioHab(boolean renderInicioHab) {
		this.renderInicioHab = renderInicioHab;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isRenderInicioPlanchas() {
		return renderInicioPlanchas;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param renderInicioPlanchas
	 */
	public void setRenderInicioPlanchas(boolean renderInicioPlanchas) {
		this.renderInicioPlanchas = renderInicioPlanchas;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isRenderReporteLog() {
		return renderReporteLog;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param renderReporteLog
	 */
	public void setRenderReporteLog(boolean renderReporteLog) {
		this.renderReporteLog = renderReporteLog;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isRenderGenerarArchivo() {
		return renderGenerarArchivo;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param renderGenerarArchivo
	 */
	public void setRenderGenerarArchivo(boolean renderGenerarArchivo) {
		this.renderGenerarArchivo = renderGenerarArchivo;
	}

	public boolean isRenderIniciarProcesoVerificacionHabilidad() {
		return renderIniciarProcesoVerificacionHabilidad;
	}

	public void setRenderIniciarProcesoVerificacionHabilidad(boolean renderIniciarProcesoVerificacionHabilidad) {
		this.renderIniciarProcesoVerificacionHabilidad = renderIniciarProcesoVerificacionHabilidad;
	}

	public boolean[] getIndicadorVisibilidadOpcionPlanchas() {
		return indicadorVisibilidadOpcionPlanchas;
	}

	public void setIndicadorVisibilidadOpcionPlanchas(boolean[] indicadorVisibilidadOpcionPlanchas) {
		this.indicadorVisibilidadOpcionPlanchas = indicadorVisibilidadOpcionPlanchas;
	}

	public boolean[] getIndicadorVisibilidadOpcionHabilidad() {
		return indicadorVisibilidadOpcionHabilidad;
	}

	public boolean getIndicadorVisibilidadHabilidad() {
		return this.indicadorVisibilidadHabilidad;
	}

	public void setIndicadorVisibilidadOpcionHabilidad(boolean[] indicadorVisibilidadOpcionHabilidad) {
		this.indicadorVisibilidadOpcionHabilidad = indicadorVisibilidadOpcionHabilidad;
	}

	public boolean isPintarBotonGenerarReciboPlancha() {
		return pintarBotonGenerarReciboPlancha;
	}

	public void setPintarBotonGenerarReciboPlancha(boolean pintarBotonGenerarReciboPlancha) {
	}

	public boolean isPintarBotonExpedirResolucionRechazo() {
		return pintarBotonExpedirResolucionRechazo;
	}

	public void setPintarBotonExpedirResolucionRechazo(boolean pintarBotonExpedirResolucionRechazo) {
	}

	public boolean isPintarBotonExpedirResolucionAdmision() {
		return pintarBotonExpedirResolucionAdmision;
	}

	public void setPintarBotonExpedirResolucionAdmision(boolean pintarBotonExpedirResolucionAdmision) {
	}

	public boolean isPintarBotonExpedirResolucionInadmision() {
		return pintarBotonExpedirResolucionInadmision;
	}

	public void setPintarBotonExpedirResolucionInadmision(boolean pintarBotonExpedirResolucionInadmision) {
	}

	public String actionClickCabezaPlancha() {

		FacesUtils.getSession().removeAttribute("referido");
		return "goConsultaCabezaPlancha";
	}

	public boolean isRenderNumerarPlanchaEnRecurso() {
		return renderNumerarPlanchaEnRecurso;
	}

	public void setRenderNumerarPlanchaEnRecurso(boolean renderNumerarPlanchaEnRecurso) {
		this.renderNumerarPlanchaEnRecurso = renderNumerarPlanchaEnRecurso;
	}

	public boolean isRenderRegistrarVotante() {
		return renderRegistrarVotante;
	}

	public void setRenderRegistrarVotante(boolean renderRegistrarVotante) {
		this.renderRegistrarVotante = renderRegistrarVotante;
	}

	public boolean isRenderRegistroNovedadesHabilidad() {
		return this.renderRegistroNovedadesHabilidad;
	}

	public void setRenderRegistroNovedadesHabilidad(boolean renderRegistroNovedadesHabilidad) {
		this.renderRegistroNovedadesHabilidad = renderRegistroNovedadesHabilidad;
	}

	public boolean isRenderReporteJuego() {
		return renderReporteJuego;
	}

	public void setRenderReporteJuego(boolean renderReporteJuego) {
		this.renderReporteJuego = renderReporteJuego;
	}

	public boolean isRenderModificacionPlanchas() {
		return renderModificacionPlanchas;
	}

	public void setRenderModificacionPlanchas(boolean renderModificacionPlanchas) {
		this.renderModificacionPlanchas = renderModificacionPlanchas;
	}

	public boolean isPintarBotonGenerarArchivo() {
		return pintarBotonGenerarArchivo;
	}

	public void setPintarBotonGenerarArchivo(boolean pintarBotonGenerarArchivo) {
		this.pintarBotonGenerarArchivo = pintarBotonGenerarArchivo;
	}

	public boolean isRenderExpedirCumplimiento() {
		return renderExpedirCumplimiento;
	}

	public void setRenderExpedirCumplimiento(boolean renderExpedirCumplimiento) {
		this.renderExpedirCumplimiento = renderExpedirCumplimiento;
	}

	public boolean isRenderDesbloUser() {
		return renderDesbloUser;
	}

	public void setRenderDesbloUser(boolean renderDesbloUser) {
		this.renderDesbloUser = renderDesbloUser;
	}

	public boolean isPintarBotonExpedirCumplimientoRequisitos() {
		return pintarBotonExpedirCumplimientoRequisitos;
	}

	public void setPintarBotonExpedirCumplimientoRequisitos(boolean pintarBotonExpedirCumplimientoRequisitos) {
		this.pintarBotonExpedirCumplimientoRequisitos = pintarBotonExpedirCumplimientoRequisitos;
	}
}