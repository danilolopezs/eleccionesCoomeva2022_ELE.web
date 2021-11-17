package co.com.coomeva.ele.backbeans;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.delegado.DelegadoCabezaPlancha;
import co.com.coomeva.ele.delegado.DelegadoDetalleFormato;
import co.com.coomeva.ele.delegado.DelegadoFormatoPlanchas;
import co.com.coomeva.ele.delegado.DelegadoLogicaPlanchaExcepcion;
import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.delegado.formulario.DelegadoRegistroFormulario;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoEstadoPlancha;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoPlancha;
import co.com.coomeva.ele.dto.DTOInformacionPlancha;
import co.com.coomeva.ele.dto.DTOMiembroPlancha;
import co.com.coomeva.ele.dto.DTOPlanchaAsociado;
import co.com.coomeva.ele.dto.InfoDetalleFormatoPlanchaDTO;
import co.com.coomeva.ele.entidades.formulario.EleRegistroCampos;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleDetalleFormatoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleDetalleFormatoPlanchaId;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlanchaExcepcion;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.formatos.pdf.inscripcion.plancha.FormatoPdfAdmisionPlancha;
import co.com.coomeva.ele.formatos.pdf.inscripcion.plancha.FormatoPdfConstanciaPlancha;
import co.com.coomeva.ele.formatos.pdf.inscripcion.plancha.FormatoPdfInadmisionPlancha;
import co.com.coomeva.ele.formatos.pdf.inscripcion.plancha.FormatoPdfResolucionApelacionPlancha;
import co.com.coomeva.ele.modelo.CabezaPlanchaDTO;
import co.com.coomeva.ele.modelo.InfoPlanchaConstanciaPdfDTO;
import co.com.coomeva.ele.modelo.InfoPlanchaDTO;
import co.com.coomeva.ele.modelo.ParametroPlanchaDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.DataPage;
import co.com.coomeva.ele.util.DataSource;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.ele.util.PagedListDataModel;
import co.com.coomeva.ele.util.WorkStrigs;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.resources.LoaderResourceElements;

import com.icesoft.faces.async.render.RenderManager;
import com.icesoft.faces.async.render.Renderable;
import com.icesoft.faces.component.datapaginator.DataPaginator;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.context.DisposableBean;
import com.icesoft.faces.context.effects.JavascriptContext;
import com.icesoft.faces.webapp.xmlhttp.PersistentFacesState;
import com.icesoft.faces.webapp.xmlhttp.RenderingException;

/**
 * 
 * @author Mario Alejandro Acevedo
 * 
 */
public class ConsultaCabezaPlanchaVista extends DataSource implements
		Renderable, DisposableBean {
	public static final String COD_ESTADO_PLANCHA_INSCRITA = UtilAcceso
			.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_ESTADO_PLANCHA_INSCRITA);

	public static final String COD_ESTADO_PLANCHA_MODIFICADA = UtilAcceso
			.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_ESTADO_PLANCHA_MODIFICADA);
	
	public static final String COD_ESTADO_PLANCHA_EN_RECURSO = UtilAcceso
			.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_ESTADO_PLANCHA_EN_RECURSO);

	public static final String COD_ESTADO_PLANCHA_ADMITIDA = UtilAcceso
			.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_ESTADO_PLANCHA_ADMITIDA);

	public static final String COD_ESTADO_PLANCHA_INADMITIDA = UtilAcceso
			.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_ESTADO_PLANCHA_INADMITIDA);

	public static final String COD_ESTADO_PLANCHA_RECHAZADA = UtilAcceso
			.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_ESTADO_PLANCHA_RECHAZADA);

	public static final String COD_ESTADO_PLANCHA_REGISTRADA = UtilAcceso
			.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_ESTADO_PLANCHA_REGISTRADA);

	private List<CabezaPlanchaDTO> integrantes = new ArrayList<CabezaPlanchaDTO>();
	private HtmlInputText txtNumDoc;

	/** Variables del paginador **/
	private DataPaginator dataPaginator;
	private boolean renderDataTable = false;
	private boolean flag = true;
	private RenderManager renderManager;
	private PersistentFacesState state = PersistentFacesState.getInstance();
	protected String sortColumnName;
	protected boolean sortAscending;
	protected int pageSize = 40;

	private Long numCedula;
	private boolean muestraInfoPlancha = false;
	private InfoPlanchaDTO infoPlancha = new InfoPlanchaDTO();
	private Long numPlancha;
	private Long codResolucion;
	private boolean visibleConfirmar;
	private boolean visibleImprAdmision = false;
	private boolean visibleImprAdmisionFechas = false;
	private boolean visibleImprConstanciaFechas = false;
	private boolean visibleImprInadmision = false;
	private boolean visibleImprResolucion = false;
	private boolean visibleImprApelacionFavorable = false;

	private FormatoPdfConstanciaPlancha formato = new FormatoPdfConstanciaPlancha();
	private FormatoPdfAdmisionPlancha formatoAdmision = new FormatoPdfAdmisionPlancha();
	private InfoDetalleFormatoPlanchaDTO dtoInfo = new InfoDetalleFormatoPlanchaDTO();
	private UserVo user;
	private boolean visibleConfirmarRadicacion;
	private boolean visibleConfirmarCumplimientoRequisitos;
	private String mensajeConfirmacion = "";
	private boolean visibleNumeroResolucion = Boolean.FALSE;

	private Long CODIGO_FORMATO_ADMISION_CABEZA_PLANCHA = new Long(
			UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
							ConstantesProperties.CODIGO_FORMATO_ADMISION_CABEZA_PLANCHA));

	private Long CODIGO_FORMATO_CONSTANCIA_CABEZA_PLANCHA = new Long(
			UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
							ConstantesProperties.CODIGO_FORMATO_CONSTANCIA_CABEZA_PLANCHA));
	
	private Long CODIGO_FORMATO_CONSTANCIA_CUMPLIMIENTO_REQUISITOS = new Long(
			UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
							ConstantesProperties.CODIGO_FORMATO_CONSTANCIA_CUMPLIMIENTO_REQUISITOS));
	
	

	String fechasAdmision = "";
	String fechasConstancia = "";

	private String cabezaPlancha;
	private String nombreAsociado;
	private String resolucionNro;
	private String numeroResolucion;
	private String resolucionNroApelacion;
	private String actNro;
	private String resolucionImpugnada;
	private String resolucionImpugnadaApelacion;
	private String cuidad;

	private String razon1;
	private String razon2;
	private String razon3;
	private String razon4;

	private String argumentoResolucion1;
	private String argumentoResolucion2;
	private String argumentoResolucion3;
	private String razonResolucion1;
	private String razonResolucion2;
	
	private String titleImprResolucion;

	private String resolucionInterpuesta;
	private String argumentoRecurrente1;
	private String argumentoRecurrente2;
	private String argumentoComision1;
	private String argumentoComision2;
	private String pronunciamientoTribunal1;
	private String pronunciamientoTribunal2;
	private String consecuenciaTribunal1;
	private String consecuenciaTribunal2;

	private HtmlSelectOneMenu selEstado;
	private List<SelectItem> itemsEstados;

	private boolean descargarReporte = false;
	private FormatoPdfInadmisionPlancha formatoPdfInadmisionPlancha = new FormatoPdfInadmisionPlancha();
	private FormatoPdfResolucionApelacionPlancha formatoPdfResolucionApelacion = new FormatoPdfResolucionApelacionPlancha();

	private boolean visible = false;
	private String mensaje2 = "";

	private List<ElePlanchaExcepcion> listaExcepciones;
	private boolean mostrarExcepciones = false;
	private String mensajeExcepciones;
	
	/**
	 * Christian Marucio Tangarife
	 * Nuevos campos para la inclusion de los reportes de delegados
	 */
	private boolean visibleImprResolucionDelegados = false;
	private String desicionTribunal;
	private boolean visibleDesicionTribunalnDelegados = false;
	private String numActa;
	private String numActaTribunal;
	private boolean visibleFechaRecurso;
	private Date fechaPresentacion;
	private boolean visibleFavorable;
	private String nombreAccionante;
	private boolean visibleAccionante;
	

	public ConsultaCabezaPlanchaVista() {
		super("");
		init();
	}

	private void init() {
		user = (UserVo) FacesUtils.getSessionParameter("user");
	}

	public String generarReporteInadmision() {
		try {

			if (cabezaPlancha == null || cabezaPlancha.length() == 0) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes",
						"campo.obligatorio.inadmision.cabezaPlancha"));
			}

			// if( resolucionNro == null || resolucionNro.length() == 0){
			// throw new Exception(UtilAcceso.getParametroFuenteS("mensajes",
			// "campo.obligatorio.inadmision.resolucionNro"));
			// }
			// if( actNro == null || actNro.length() == 0){
			// throw new Exception(UtilAcceso.getParametroFuenteS("mensajes",
			// "campo.obligatorio.inadmision.actNro"));
			// }
			// if( cuidad == null || cuidad.length() == 0){
			// throw new Exception(UtilAcceso.getParametroFuenteS("mensajes",
			// "campo.obligatorio.inadmision.cuidad"));
			// }

			if (razon1 == null || razon1.length() == 0) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes",
						"campo.obligatorio.inadmision.razon1"));
			}

			visibleImprInadmision = false;
			formatoPdfInadmisionPlancha.generarReporte(cabezaPlancha,
					resolucionNro, actNro, razon1, razon2, razon3, razon4,
					cuidad, nombreAsociado);
			
			DelegadoEstadoPlancha.getInstance().crearEstadoPlancha(
					user.getUserId(), new Date(), COD_ESTADO_PLANCHA_INADMITIDA,
					new Long(infoPlancha.getNumPlancha()));
			
			HttpServletRequest request= (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			Date fechaElaboracionDoc = new Date();
			Date fechaActa = new Date();
			SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
			
			request.getSession().setAttribute("zonaElectoral", infoPlancha.getCodZona());
			request.getSession().setAttribute("dia",fechaElaboracionDoc!=null?String.valueOf(fechaElaboracionDoc.getDate()):""); 
			request.getSession().setAttribute("mes",fechaElaboracionDoc!=null?String.valueOf(fechaElaboracionDoc.getMonth()+1):""); 
			request.getSession().setAttribute("anio",fechaElaboracionDoc!=null?WorkStrigs.getAnio(fechaElaboracionDoc.getYear()):""); 
			request.getSession().setAttribute("hora", fechaElaboracionDoc);
			request.getSession().setAttribute("nombreAsociado", nombreAsociado);
			request.getSession().setAttribute("resolucion", resolucionNro);
			request.getSession().setAttribute("acta", numActa);
			request.getSession().setAttribute("fecha", dt1.format(fechaActa));
			request.getSession().setAttribute("ciudad",cuidad);
			request.getSession().setAttribute("razon1", razon1);
			request.getSession().setAttribute("razon2", razon2);
			request.getSession().setAttribute("razon3", razon3);
			request.getSession().setAttribute("razon4", razon4);
			
			request.getSession().setAttribute("cedulaCabezaPlancha", cabezaPlancha);
			request.getSession().setAttribute("nombreCabezaPlancha", nombreAsociado);
			
			request.getSession().setAttribute("codigoReporte", "209");
			JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "ServletReportesJasper();");
			
			action_find();

		} catch (Exception e) {
			// getMensaje().mostrarMensaje(e.getMessage());
			mensaje2 = e.getMessage();
			this.visible = true;
		}
		return null;
	}

	public String generarReporteResolucion() {
		try {
			if (cabezaPlancha == null || cabezaPlancha.length() == 0) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes",
						"campo.obligatorio.resolucion.cabezaPlancha"));
			}
			// if( cuidad == null || cuidad.length() == 0){
			// throw new Exception(UtilAcceso.getParametroFuenteS("mensajes",
			// "campo.obligatorio.inadmision.cuidad"));
			// }

			if ("6".equals(codResolucion.toString()) || "7".equals(codResolucion.toString())
					|| "8".equals(codResolucion.toString()) || "9".equals(codResolucion.toString())
					|| "10".equals(codResolucion.toString()) || "11".equals(codResolucion.toString())) {
				if (resolucionNro == null || resolucionNro.length() == 0) {
					throw new Exception(
							UtilAcceso.getParametroFuenteS("mensajes", "campo.obligatorio.resolucion.resolucionNro"));
				}
				if (resolucionImpugnada == null || resolucionImpugnada.length() == 0) {
					throw new Exception(
							UtilAcceso.getParametroFuenteS("mensajes", "campo.obligatorio.resolucion.impugnada"));
				}
				if (argumentoResolucion1 == null || argumentoResolucion1.length() == 0) {
					throw new Exception(UtilAcceso.getParametroFuenteS("mensajes",
							"campo.obligatorio.resolucion.argumentoResolucion"));
				}

				if ("8".equals(codResolucion.toString())) {
					if (desicionTribunal == null || desicionTribunal.length() == 0) {
						throw new Exception(UtilAcceso.getParametroFuenteS("mensajes",
								"campo.obligatorio.resolucion.desicionTribunal"));
					}

					if (numActa == null || numActa.length() == 0) {
						throw new Exception(
								UtilAcceso.getParametroFuenteS("mensajes", "campo.obligatorio.resolucion.numActa"));
					}
				}

				if ("10".equals(codResolucion.toString())) {
					if (fechaPresentacion == null) {
						throw new Exception(UtilAcceso.getParametroFuenteS("mensajes",
								"campo.obligatorio.resolucion.fechaPresentacion"));
					}
				}
				
				if ("11".equals(codResolucion.toString()) || "7".equals(codResolucion.toString())) {
					if (nombreAccionante == null) {
						throw new Exception(
								UtilAcceso.getParametroFuenteS("mensajes", "campo.obligatorio.resolucion.accionante"));
					}
				}

				visibleImprResolucion = false;
				visibleDesicionTribunalnDelegados = false;
				
				dtoInfo.setNumeroDocumento(new Long(cabezaPlancha));
				dtoInfo.setArgumentoResolucion1(argumentoResolucion1);
				dtoInfo.setArgumentoResolucion2(argumentoResolucion2);
				dtoInfo.setArgumentoResolucion3(argumentoResolucion3);
				dtoInfo.setRazonResolucion1(razonResolucion1);
				dtoInfo.setRazonResolucion2(razonResolucion2);
				dtoInfo.setNumeroResolucion(new Long(resolucionNro));
				dtoInfo.setResolucionImpugnada(resolucionImpugnada);
				
				generarReporteListener(dtoInfo, codResolucion.toString());
				
				visibleImprResolucionDelegados = false;
				visibleFechaRecurso = false;
				DelegadoCabezaPlancha.getInstance().guardarInformacionResolucion(dtoInfo,codResolucion.toString());
				limpiar_formulario_resolucion();
			}
			else if (!"3".equals(codResolucion.toString())) {

				if (resolucionNro == null || resolucionNro.length() == 0) {
					throw new Exception(UtilAcceso.getParametroFuenteS(
							"mensajes",
							"campo.obligatorio.resolucion.resolucionNro"));
				}
				if (resolucionImpugnada == null
						|| resolucionImpugnada.length() == 0) {
					throw new Exception(UtilAcceso.getParametroFuenteS(
							"mensajes",
							"campo.obligatorio.resolucion.impugnada"));
				}
				if (argumentoResolucion1 == null
						|| argumentoResolucion1.length() == 0) {
					throw new Exception(UtilAcceso.getParametroFuenteS(
							"mensajes",
							"campo.obligatorio.resolucion.argumentoResolucion"));
				}

				if (razonResolucion1 == null || razonResolucion1.length() == 0) {
					throw new Exception(UtilAcceso.getParametroFuenteS(
							"mensajes",
							"campo.obligatorio.resolucion.razonResolucion"));
				}

				visibleImprResolucion = false;
				
				formatoPdfResolucionApelacion.generarReporte(cabezaPlancha,
						resolucionNro, resolucionImpugnada,
						argumentoResolucion1, argumentoResolucion2,
						argumentoResolucion3, razonResolucion1,
						razonResolucion2, codResolucion.toString(),
						nombreAsociado);
				dtoInfo.setNumeroDocumento(new Long(cabezaPlancha));
				dtoInfo.setArgumentoResolucion1(argumentoResolucion1);
				dtoInfo.setArgumentoResolucion2(argumentoResolucion2);
				dtoInfo.setArgumentoResolucion3(argumentoResolucion3);
				dtoInfo.setRazonResolucion1(razonResolucion1);
				dtoInfo.setRazonResolucion2(razonResolucion2);
				dtoInfo.setNumeroResolucion(new Long(resolucionNro));
				dtoInfo.setResolucionImpugnada(resolucionImpugnada);
				DelegadoCabezaPlancha.getInstance()
						.guardarInformacionResolucion(dtoInfo,
								codResolucion.toString());
				limpiar_formulario_resolucion();
			} else {
				if (resolucionNroApelacion == null
						|| resolucionNroApelacion.length() == 0) {
					throw new Exception(UtilAcceso.getParametroFuenteS(
							"mensajes",
							"campo.obligatorio.resolucion.resolucionNro"));
				}
				if (resolucionImpugnadaApelacion == null
						|| resolucionImpugnadaApelacion.length() == 0) {
					throw new Exception(UtilAcceso.getParametroFuenteS(
							"mensajes",
							"campo.obligatorio.resolucion.impugnada"));
				}
				if (resolucionInterpuesta == null
						|| resolucionInterpuesta.length() == 0) {
					throw new Exception(
							UtilAcceso
									.getParametroFuenteS("mensajes",
											"campo.obligatorio.resolucion.resolucionInterpuesta"));
				}

				if (argumentoRecurrente1 == null
						|| argumentoRecurrente1.length() == 0) {
					throw new Exception(UtilAcceso.getParametroFuenteS(
							"mensajes",
							"campo.obligatorio.resolucion.argumentoRecurrente"));
				}

				if (argumentoComision1 == null
						|| argumentoComision1.length() == 0) {
					throw new Exception(UtilAcceso.getParametroFuenteS(
							"mensajes",
							"campo.obligatorio.resolucion.argumentoComision"));
				}

				if (pronunciamientoTribunal1 == null
						|| pronunciamientoTribunal1.length() == 0) {
					throw new Exception(UtilAcceso.getParametroFuenteS(
							"mensajes",
							"campo.obligatorio.resolucion.pronunciamiento"));
				}

				if (consecuenciaTribunal1 == null
						|| consecuenciaTribunal1.length() == 0) {
					throw new Exception(UtilAcceso.getParametroFuenteS(
							"mensajes",
							"campo.obligatorio.resolucion.consecuencia"));
				}

				visibleImprApelacionFavorable = false;

				formatoPdfResolucionApelacion.generarReporte(cabezaPlancha,
						resolucionNroApelacion, resolucionImpugnadaApelacion,
						resolucionInterpuesta, argumentoRecurrente1,
						argumentoRecurrente2, argumentoComision1,
						argumentoComision2, codResolucion.toString(),
						pronunciamientoTribunal1, pronunciamientoTribunal2,
						consecuenciaTribunal1, consecuenciaTribunal2,
						nombreAsociado);
				dtoInfo.setNumeroDocumento(new Long(cabezaPlancha));
				dtoInfo.setArgumentoRecurrente1(argumentoRecurrente1);
				dtoInfo.setArgumentoRecurrente2(argumentoRecurrente2);
				dtoInfo.setArgumentoComision1(argumentoComision1);
				dtoInfo.setArgumentoComision2(argumentoComision2);
				dtoInfo.setPronunciamientoTribunal1(pronunciamientoTribunal1);
				dtoInfo.setPronunciamientoTribunal2(pronunciamientoTribunal2);
				dtoInfo.setConsecuenciaTribunal1(consecuenciaTribunal1);
				dtoInfo.setConsecuenciaTribunal2(consecuenciaTribunal2);
				dtoInfo.setNumeroResolucion(new Long(resolucionNroApelacion));
				dtoInfo.setResolucionImpugnada(resolucionImpugnadaApelacion);
				dtoInfo.setResolucionInterpuesta(resolucionInterpuesta);
				DelegadoCabezaPlancha.getInstance()
						.guardarInformacionResolucion(dtoInfo,
								codResolucion.toString());

				limpiar_formulario_apelacion_favorable();
			}
		} catch (Exception e) {
			// getMensaje().mostrarMensaje(e.getMessage());
			mensaje2 = e.getMessage();
			this.visible = true;
		}
		return null;
	}

	public String limpiar_formulario() {

		razon1 = null;
		razon2 = null;
		razon3 = null;
		razon4 = null;

		return null;
	}

	public String limpiar_formulario_resolucion() {
		resolucionNro = null;
		resolucionImpugnada = null;
		argumentoResolucion1 = null;
		argumentoResolucion2 = null;
		argumentoResolucion3 = null;
		razonResolucion1 = null;
		razonResolucion2 = null;
		desicionTribunal = null;
		nombreAccionante = null;
		return null;
	}

	public String limpiar_formulario_apelacion_favorable() {
		resolucionNroApelacion = null;
		resolucionImpugnadaApelacion = null;
		resolucionInterpuesta = null;
		argumentoRecurrente1 = null;
		argumentoRecurrente2 = null;
		argumentoComision1 = null;
		argumentoComision2 = null;
		pronunciamientoTribunal1 = null;
		pronunciamientoTribunal2 = null;
		consecuenciaTribunal1 = null;
		consecuenciaTribunal2 = null;

		return null;
	}

	/**
	 * Cambia el estado de la plancha de INCRITA a ADMITIDA y despligue el popup
	 * para la generaci�n del pdf
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario
	 *         Alejandro Acevedo</a> - GSISIN <br>
	 * 
	 */

	public String actionExpedirResolucion() {
		try {
			if (DelegadoPlanchas.getInstance()
					.validarFechaMarcarPlanchaAdmision()) {

				if (!(COD_ESTADO_PLANCHA_INSCRITA.equals(infoPlancha
						.getCodigoEstadoPlancha())
						|| COD_ESTADO_PLANCHA_EN_RECURSO.equals(infoPlancha
								.getCodigoEstadoPlancha()) || COD_ESTADO_PLANCHA_INADMITIDA
						.equals(infoPlancha.getCodigoEstadoPlancha()))) {
					throw new EleccionesDelegadosException(
							LoaderResourceElements
									.getInstance()
									.getKeyResourceValue(
											ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
											ConstantesProperties.MENSAJE_NO_ES_POSIBLE_IMPRIMIR_CONSTANCIA_ADMISION));
				}

				if (DelegadoPlancha.getInstance().actualizarEstadoPlancha(
						new Long(infoPlancha.getNumPlancha()), "3")) {
					DelegadoEstadoPlancha.getInstance().crearEstadoPlancha(
							user.getUserId(), new Date(), "3",
							new Long(infoPlancha.getNumPlancha()));
				}

				action_find();
				visibleImprAdmision = true;
			} else {
				visibleImprAdmisionFechas = true;
			}
		} catch (Exception e) {
			// getMensaje().mostrarMensaje(e.getMessage());
			mensaje2 = e.getMessage();
			this.visible = true;
		}
		return "";
	}
	
	/**
	 * @author Christian Mauricio Tanagrife Colorado
	 * Imprime los reportes de jasper para los delegados
	 * Deniega Recurso de Reposicion (CO-FT-459)
		Recurso de Reposici�n y Remite Apelaci�n (CO-FT-460)
		Recurso Apelaci�n Favorable (CO-FT-461)
		Recurso Apelaci�n En Contra (CO-FT-462)
		Recurso Interpuesto Extemporaneamente (CO-FT-753)
	 * @param codResolucion 
	 * @param dtoInfo2 
	 */
	public void generarReporteListener(InfoDetalleFormatoPlanchaDTO dtoInfo2, String codResolucion)
	{
		HttpServletRequest request= (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		List<EleRegistroCampos> listaRegCampos = new ArrayList<EleRegistroCampos>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String tipoReporte = null;
		
		if("6".equals(codResolucion))
		{
			tipoReporte = "459";
		}
		else if("7".equals(codResolucion))
		{
			tipoReporte = "460";
		}
		else if("8".equals(codResolucion))
		{
			tipoReporte = "461";
		}
		else if("9".equals(codResolucion))
		{
			tipoReporte = "462";
		}
		else if("10".equals(codResolucion))
		{
			tipoReporte = "753";
		}
		else if("11".equals(codResolucion))
		{
			tipoReporte = "458";
		}
		
		String argApelacion = dtoInfo2.getArgumentoResolucion1()+ "\n"
							 +dtoInfo2.getArgumentoResolucion2()+ "\n"
							 +dtoInfo2.getArgumentoResolucion3()+ "\n";
		
		Date fechaElaboracionDoc = new Date();
		String numZonaElectroral = this.infoPlancha.getCodZona();		
		String numResolucionImpugnada = dtoInfo2.getResolucionImpugnada();
		String resolucion = String.valueOf(dtoInfo2.getNumeroResolucion());
		String nombrePresidente = "";
		String nombreSecretario = "";

		String nombreAspirante = this.nombreAsociado;
		
		request.getSession().setAttribute("cedulaCabezaPlancha", cabezaPlancha);
		request.getSession().setAttribute("nombreCabezaPlancha", nombreAsociado);
		//RESOLUCI�N QUE DENIEGA UN RECURSO DE REPOSICI�N
		//Y NO CONCEDE APELACI�N POR NO SER SOLICITADO CO-FT-458
		if(tipoReporte.equalsIgnoreCase("458"))
		{	
			request.getSession().setAttribute("zonaElectoral", numZonaElectroral);
			request.getSession().setAttribute("fecha", fechaElaboracionDoc);
			request.getSession().setAttribute("nombreAccionante",  nombreAccionante);
			request.getSession().setAttribute("resolucionImpugnada", numResolucionImpugnada);			
			request.getSession().setAttribute("resolucionNumero", resolucion);
			request.getSession().setAttribute("argumento", argApelacion);
			request.getSession().setAttribute("decision", desicionTribunal);
			request.getSession().setAttribute("nombrePresidente", nombrePresidente);
			request.getSession().setAttribute("nombreSecretario", nombreSecretario);
			
			// para guardar en base de datos los registros de los campos
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 1L, nombreAccionante));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 18L, numZonaElectroral));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 23L, sdf.format(fechaElaboracionDoc)));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 28L, numResolucionImpugnada));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 42L, nombrePresidente));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 44L, nombreSecretario));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 54L, argApelacion));

		}
		//RESOLUCI�N QUE DENIEGA UN RECURSO DE REPOSICI�N
		//Y NO CONCEDE APELACI�N POR NO SER SOLICITADO CO-FT-459
		else if(tipoReporte.equalsIgnoreCase("459"))
		{	
			request.getSession().setAttribute("zonaElectoral", numZonaElectroral);
			request.getSession().setAttribute("fecha", fechaElaboracionDoc);
			request.getSession().setAttribute("nombreAccionante",  nombreAccionante);
			request.getSession().setAttribute("resolucionImpugnada", numResolucionImpugnada);			
			request.getSession().setAttribute("resolucionNumero", resolucion);
			request.getSession().setAttribute("argumento", argApelacion);
			request.getSession().setAttribute("nombrePresidente", nombrePresidente);
			request.getSession().setAttribute("nombreSecretario", nombreSecretario);
			
			// para guardar en base de datos los registros de los campos
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 1L, nombreAspirante));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 18L, numZonaElectroral));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 23L, sdf.format(fechaElaboracionDoc)));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 28L, numResolucionImpugnada));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 42L, nombrePresidente));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 44L, nombreSecretario));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 54L, argApelacion));

		}
		
		//RESOLUCI�N QUE RESUELVE UN RECURSO DE REPOSICI�N
		//EN CONTRA Y REMITE LA APELACI�N CO-FT-460
		else if(tipoReporte.equalsIgnoreCase("460"))
		{
			request.getSession().setAttribute("zonaElectoral", numZonaElectroral);
			request.getSession().setAttribute("fecha", fechaElaboracionDoc);
			request.getSession().setAttribute("nombreAsociado", nombreAccionante);
			request.getSession().setAttribute("resolucionImpugnada", numResolucionImpugnada);			
			request.getSession().setAttribute("resolucion", resolucion);
			request.getSession().setAttribute("argumento", argApelacion);
			request.getSession().setAttribute("nombrePresidente", nombrePresidente);
			request.getSession().setAttribute("nombreSecretario", nombreSecretario);
			
			// para guardar en base de datos los registros de los campos
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 1L, nombreAspirante));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 18L, numZonaElectroral));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 23L, sdf.format(fechaElaboracionDoc)));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 28L, numResolucionImpugnada));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 42L, nombrePresidente));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 44L, nombreSecretario));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 54L, argApelacion));
			
		}
		
		//RESOLUCI�N QUE RESUELVE UN RECURSO DE
		//APELACI�N FAVORABLE CO-FT-461
		else if(tipoReporte.equalsIgnoreCase("461"))
		{
			request.getSession().setAttribute("acta", numActa);
			request.getSession().setAttribute("fecha", fechaElaboracionDoc);
			request.getSession().setAttribute("nombreAsociado",nombreAspirante);
			request.getSession().setAttribute("resolucionApelada" ,numResolucionImpugnada);
			request.getSession().setAttribute("resolucionNumero", resolucion);
			request.getSession().setAttribute("argumento", argApelacion);
			request.getSession().setAttribute("nombrePresidente", nombrePresidente);
			request.getSession().setAttribute("nombreSecretario", nombreSecretario);	
			
			// para guardar en base de datos los registros de los campos
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 1L, nombreAspirante));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 23L, sdf.format(fechaElaboracionDoc)));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 27L, numResolucionImpugnada));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 30L, resolucion));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 39L, numActaTribunal));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 40L, numActa));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 42L, nombrePresidente));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 44L, nombreSecretario));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 54L, argApelacion));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 69L, desicionTribunal));
			
		}
		
		//RESOLUCI�N QUE RESUELVE UN RECURSO DE
		//APELACI�N EN CONTRA CO-FT-462
		else if(tipoReporte.equalsIgnoreCase("462"))
		{
			request.getSession().setAttribute("acta", numActa);
			request.getSession().setAttribute("fecha", fechaElaboracionDoc);
			request.getSession().setAttribute("nombreAsociado", nombreAspirante);
			request.getSession().setAttribute("resolucionApelada", numResolucionImpugnada);
			request.getSession().setAttribute("resolucionNumero", resolucion);			
			request.getSession().setAttribute("actaTribunal", numActaTribunal);
			request.getSession().setAttribute("argumento", argApelacion);
			request.getSession().setAttribute("nombrePresidente", nombrePresidente);
			request.getSession().setAttribute("nombreSecretario", nombreSecretario);
			
			// para guardar en base de datos los registros de los campos
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 1L, nombreAspirante));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 27L, numResolucionImpugnada));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 30L, resolucion));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 39L, numActaTribunal));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 40L, numActa));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 41L, sdf.format(fechaElaboracionDoc)));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 42L, nombrePresidente));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 44L, nombreSecretario));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 54L, argApelacion));
		}
		
		//RESOLUCI�N QUE RESUELVE RECURSOS INTERPUESTOS EXTEMPORANEAMENTE CO-FT-753
		else if(tipoReporte.equalsIgnoreCase("753"))
		{
			request.getSession().setAttribute("zonaElectoral", numZonaElectroral);
			request.getSession().setAttribute("fecha", fechaElaboracionDoc);
			request.getSession().setAttribute("nombreAsociado", nombreAspirante);
			request.getSession().setAttribute("resolucion", resolucion);
			request.getSession().setAttribute("diaPresentado",fechaPresentacion!=null?String.valueOf(fechaPresentacion.getDate()):"" );
			request.getSession().setAttribute("dia",fechaElaboracionDoc!=null?String.valueOf(fechaElaboracionDoc.getDate()):""); 
			request.getSession().setAttribute("mes",fechaElaboracionDoc!=null?WorkStrigs.getMes(fechaElaboracionDoc.getMonth()):""); 
			request.getSession().setAttribute("anio",fechaElaboracionDoc!=null?WorkStrigs.getAnio(fechaElaboracionDoc.getYear()):"");		
			
			
			// para guardar en base de datos los registros de los campos
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 10L, nombreAspirante));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 18L, numZonaElectroral));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 23L, sdf.format(fechaElaboracionDoc)));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 29L, resolucion));
//			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 60L, sdf.format(fechaPresentacion)));
//			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 63L, sdf.format(fechaFirma)));
			
		}
		try {
			DelegadoRegistroFormulario.getInstance().crearRegistroFormulario(Long.valueOf(tipoReporte), listaRegCampos,user.getUserId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		request.getSession().setAttribute("codigoReporte", tipoReporte);
		JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "ServletReportesJasper();");
	}

	/**
	 * Imprime el formato CO-FT-172 � Expedici�n de resoluci�n de admision
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario
	 *         Alejandro Acevedo</a> - GSISIN <br>
	 * 
	 */
	public String actionImprimirAdmisionPdf() {
		try {

			Object objects[] = new Object[2];
			objects[0] = new Long(infoPlancha.getNumPlancha());
			objects[1] = new Long(CODIGO_FORMATO_ADMISION_CABEZA_PLANCHA);

//			formatoAdmision.generarReporte(objects);
			visibleImprAdmision = false;

			DelegadoFormatoPlanchas.getInstance().crearFormatoPlancha(
					user.getUserId(), new Timestamp(new Date().getTime()),
					CODIGO_FORMATO_ADMISION_CABEZA_PLANCHA,
					infoPlancha.getConsecutivoPlancha());
			
			HttpServletRequest request= (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			Date fechaElaboracionDoc = new Date();
			Date fechaActa = new Date();
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyy/MM/dd");
			
			request.getSession().setAttribute("zonaElectoral", infoPlancha.getCodZona());
			request.getSession().setAttribute("nombreAsociado", nombreAsociado);
			request.getSession().setAttribute("numResolucion", resolucionNro);
			request.getSession().setAttribute("numActa", numActa);
			request.getSession().setAttribute("fecha",dt1.format(fechaActa));
			request.getSession().setAttribute("ciudadZona",infoPlancha.getZona());
			request.getSession().setAttribute("dia",String.valueOf(fechaElaboracionDoc.getDate()));
			request.getSession().setAttribute("mes",String.valueOf(fechaElaboracionDoc.getMonth()+1));
			request.getSession().setAttribute("anio",fechaElaboracionDoc!=null?WorkStrigs.getAnio(fechaElaboracionDoc.getYear()):"");
			
			request.getSession().setAttribute("cedulaCabezaPlancha", cabezaPlancha);
			request.getSession().setAttribute("nombreCabezaPlancha", nombreAsociado);
			
			request.getSession().setAttribute("codigoReporte", "172");
			JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "ServletReportesJasper();");

			/*
			 * } else { visibleImprAdmision = false; throw new
			 * Exception("No hay informaci�n para generar el archivo PDF");
			 * 
			 * }
			 */

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// getMensaje().mostrarMensaje(e.getMessage());
			mensaje2 = e.getMessage();
			this.visible = true;
		}
		
		action_find();
		return "";
	}

	/**
	 * Imprime el formato CO-FT-208 � Constancia de radicaci�n y recibo de
	 * planchas
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario
	 *         Alejandro Acevedo</a> - GSISIN <br>
	 * 
	 */
	public String actionImprimirFormatoPdf() {
		try {
			this.visibleConfirmarRadicacion = false;
			if (DelegadoPlanchas.getInstance().validarFechaPlanchaConstancia()) {

				if (!COD_ESTADO_PLANCHA_REGISTRADA.equals(infoPlancha.getCodigoEstadoPlancha())) {
					throw new EleccionesDelegadosException(LoaderResourceElements.getInstance().getKeyResourceValue(
							ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
							"msg.error.imposible.generar.constancia.recibo.plancha.no.registrada"));
				}

//				formato.generarReporte(new Long(infoPlancha
//						.getConsecutivoPlancha()));
				DelegadoFormatoPlanchas.getInstance().crearFormatoPlancha(user.getUserId(),
						new Timestamp(new Date().getTime()), CODIGO_FORMATO_CONSTANCIA_CABEZA_PLANCHA,
						infoPlancha.getConsecutivoPlancha());

				// Cambiar el estado de la plancha a inscrita:
				DelegadoPlancha.getInstance().actualizarEstadoPlancha(
						new Long(infoPlancha.getNumPlancha()),
						COD_ESTADO_PLANCHA_INSCRITA);
				
				DelegadoEstadoPlancha.getInstance().crearEstadoPlancha(
						user.getUserId(), new Date(), COD_ESTADO_PLANCHA_INSCRITA,
						new Long(infoPlancha.getNumPlancha()));

				// Actualizar la fecha de env�o de la plancha:
				DelegadoPlancha.getInstance().actualizarFechaEnvio(
						new Long(infoPlancha.getNumPlancha()));
				
				InfoPlanchaConstanciaPdfDTO infoPlanchaDTO = DelegadoPlanchas.getInstance().obtenerInfoPlanchaConstanciaPdf(new Long(infoPlancha.getConsecutivoPlancha()));
				
				HttpServletRequest request= (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
				Date fechaElaboracionDoc = new Date();
				Date fechaActa = new Date();
				
				request.getSession().setAttribute("zonaElectoral", infoPlancha.getCodZona());
				request.getSession().setAttribute("fecha", fechaElaboracionDoc);
				request.getSession().setAttribute("nombreAsociado", nombreAsociado);
				request.getSession().setAttribute("numComision", infoPlanchaDTO.getZona());
				request.getSession().setAttribute("ciudad", infoPlanchaDTO.getComisionCiu());					
				request.getSession().setAttribute("cedulaAsociado", infoPlanchaDTO.getNumCedula().toString());
				request.getSession().setAttribute("ciudadCedula", infoPlanchaDTO.getCiudadExp());
				request.getSession().setAttribute("dia",String.valueOf(fechaElaboracionDoc.getDate())); 
				request.getSession().setAttribute("mes",String.valueOf(fechaElaboracionDoc.getMonth()+1)); 
				request.getSession().setAttribute("anio",fechaElaboracionDoc!=null?WorkStrigs.getAnio(fechaElaboracionDoc.getYear()):"");		
				request.getSession().setAttribute("nombreEntrega", "");
				request.getSession().setAttribute("nombreRecibe", "");
				
				request.getSession().setAttribute("codigoReporte", "208");
				JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "ServletReportesJasper();");

				action_find();
			} else {
				visibleImprConstanciaFechas = true;
			}
		} catch (Exception e) {
			// getMensaje().mostrarMensaje(e.getMessage());
			mensaje2 = e.getMessage();
			this.visible = true;
		}
		return "";
	}

	/**
	 * Imprime el formato CO-FT-176 � CERTIFICACI�N DE CUMPLIMIENTO PARA SER 
	 * ELEGIDO DELEGADO
	 * 
	 * @author GTC CORPORATION - Danilo L�pez Sandoval
	 * 
	 */
	public String actionImprimirFormatoPdfCumplimientoRequisitos() {
		List<EleRegistroCampos> listaRegCampos = new ArrayList<EleRegistroCampos>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String tipoReporte = "176";
		try {
			this.visibleConfirmarCumplimientoRequisitos = Boolean.FALSE;
			if (DelegadoPlanchas.getInstance().validarFechaPlanchaConstancia()) {

				if (!COD_ESTADO_PLANCHA_REGISTRADA.equals(infoPlancha.getCodigoEstadoPlancha())) {
					throw new EleccionesDelegadosException(LoaderResourceElements.getInstance().getKeyResourceValue(
							ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
							"msg.error.imposible.generar.cumplimiento.requisitos.plancha.no.registrada"));
				}

				DelegadoFormatoPlanchas.getInstance().crearFormatoPlancha(user.getUserId(),
						new Timestamp(new Date().getTime()), CODIGO_FORMATO_CONSTANCIA_CUMPLIMIENTO_REQUISITOS,
						infoPlancha.getConsecutivoPlancha());

				InfoPlanchaConstanciaPdfDTO infoPlanchaDTO = DelegadoPlanchas.getInstance()
						.obtenerInfoPlanchaConstanciaPdf(new Long(infoPlancha.getConsecutivoPlancha()));

				HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
						.getRequest();
				Date fechaElaboracionDoc = new Date();
				Date fechaActa = new Date();

				request.getSession().setAttribute("zonaElectoral", infoPlancha.getCodZona().toString());
				request.getSession().setAttribute("nombreAsociado", infoPlanchaDTO.getNombres() + " "
						+ (infoPlanchaDTO.getApellidos() != null ? infoPlanchaDTO.getApellidos() : ""));
				request.getSession().setAttribute("cedulaAsociado", infoPlanchaDTO.getNumCedula().toString());
				request.getSession().setAttribute("dia",
						fechaElaboracionDoc != null ? String.valueOf(fechaElaboracionDoc.getDate()) : "");
				request.getSession().setAttribute("mes",
						fechaElaboracionDoc != null ? String.valueOf(fechaElaboracionDoc.getMonth() + 1) : "");
				request.getSession().setAttribute("annio",
						fechaElaboracionDoc != null ? String.valueOf(WorkStrigs.getAnio(fechaElaboracionDoc.getYear()))
								: "");
				request.getSession().setAttribute("ciudad", infoPlanchaDTO.getComisionCiu());
				List<String> observaciones = obtenerObservacionesAsociados();
				request.getSession().setAttribute("observaciones", observaciones);
				request.getSession().setAttribute("codigoReporte", tipoReporte);

				listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 18L,
						infoPlancha.getCodZona().toString()));
				listaRegCampos
						.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 10L, infoPlanchaDTO.getNombres()
								+ " " + (infoPlanchaDTO.getApellidos() != null ? infoPlanchaDTO.getApellidos() : "")));
				listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 77L,
						infoPlanchaDTO.getNumCedula().toString()));
				listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 23L,sdf.format(fechaElaboracionDoc)));
				listaRegCampos.add(
						new EleRegistroCampos(null, Long.valueOf(tipoReporte), 21L, infoPlanchaDTO.getComisionCiu()));
				String cadenaObs = "";
				for (String obs : observaciones) {
					cadenaObs += obs;
				}
				listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 78L, cadenaObs));
				try {
					if (user != null) {
						DelegadoRegistroFormulario.getInstance().crearRegistroFormulario(Long.valueOf(tipoReporte),
								listaRegCampos, user.getUserId());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "ServletReportesJasper();");
				action_find();
			} else {
				visibleImprConstanciaFechas = true;
			}
		} catch (Exception e) {
			// getMensaje().mostrarMensaje(e.getMessage());
			mensaje2 = e.getMessage();
			this.visible = true;
		}
		return "";
	}
	
	private List<String> obtenerObservacionesAsociados(){
		List<String> listaObservaciones = new ArrayList<String>();
		try {
			DTOInformacionPlancha infoPlanchas = DelegadoPlancha.getInstance()
					.consultarInformacionPlancha(infoPlancha.getConsecutivoPlancha());			
			for (DTOMiembroPlancha titulares : infoPlanchas.getMiembrosTitulares()) {
				String obs = consultarExcepcionesPorIdAsociado(titulares.getNumeroDocumento() + "",
						infoPlancha.getConsecutivoPlancha() + "");
				if(obs != null && !obs.isEmpty()) {
					listaObservaciones.add(obs);
				}
			}
			for (DTOMiembroPlancha suplentes : infoPlanchas.getMiembrosSuplentes()) {				
				String obs = consultarExcepcionesPorIdAsociado(suplentes.getNumeroDocumento() + "",
						infoPlancha.getConsecutivoPlancha() + "");
				if(obs != null && !obs.isEmpty()) {
					listaObservaciones.add(obs);
				}
			}
			
//			for (String observacion : listaObservaciones) {
//				if(!observacion.equals(""))
//					observaciones += "\n" +observacion;
//			}
		} catch (EleccionesDelegadosException e) {
			mensaje2 = e.getMessage();
			this.visible = true;
			e.printStackTrace();
		}
		return listaObservaciones;
	}
	
	/**
	 * Cierra el popup de generaci�n de formato CO-FT-172
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario
	 *         Alejandro Acevedo</a> - GSISIN <br>
	 * @date 10/12/2012
	 * 
	 */
	public void action_cancelar() {
		visibleImprAdmision = false;
	}

	public void actionCloseAdmisionFechas() {
		visibleImprAdmisionFechas = false;
	}

	public void actionCloseConstanciaFechas() {
		visibleImprConstanciaFechas = false;
	}

	/**
	 * Setea los campos a un estado inicial
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario
	 *         Alejandro Acevedo</a> - GSISIN <br>
	 * 
	 */

	public void limpiarCampos() {
		numCedula = null;
		muestraInfoPlancha = false;
		numPlancha = null;
		onePageDataModel = null;
		fechasAdmision = "";
		fechasConstancia = "";
		listaExcepciones = null;
	}

	/**
	 * Realiza la b�squeda de planchas por n�mero de identificaci�n del cabeza
	 * de plancha
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario
	 *         Alejandro Acevedo</a> - GSISIN <br>
	 * 
	 */

	public void action_find() {

		Long cedulaTemporal = 0L;

		try {
			Long numCedulaAttribute = (Long) FacesUtils.getSession()
					.getAttribute("numCedula");
			if (numCedula == null && numCedulaAttribute == null) {
				throw new Exception("Debe ingresar el n�mero de c�dula del asociado Cabeza de Plancha");
			} else {

				if (numCedula != null) {
					cedulaTemporal = numCedula;
				} else if (numCedulaAttribute != null) {
					cedulaTemporal = numCedulaAttribute;
					numCedula = cedulaTemporal;
				}
				cabezaPlancha = cedulaTemporal.toString();

				UserVo user = (UserVo) FacesUtils.getSessionParameter("user");
				infoPlancha = DelegadoPlanchas.getInstance()
						.obtenerInfoPlancha(cedulaTemporal, user.getUserId());
				if (infoPlancha.getNumPlancha() != null) {
					// adf

					nombreAsociado = DelegadoAsociado.getInstance()
							.consultarNombreAsociado(new Long(cedulaTemporal));

					muestraInfoPlancha = true;
					numPlancha = new Long(infoPlancha.getNumPlancha());
					cuidad = infoPlancha.getZona();
					onePageDataModel = null;
					getData();
					FacesUtils.getSession().setAttribute("numeroDocCabeza",
							numCedula);
					fechasAdmision = DelegadoPlanchas.getInstance()
							.obtieneFechasAdmision();
					fechasConstancia = DelegadoPlanchas.getInstance()
							.obtieneFechasConstancia();
				} else {
					limpiarCampos();
					throw new Exception(
							"El documento ingresado no corresponde a ning�n Cabeza de Plancha o la C�dula de "
									+ "Ciudadan�a ingresada corresponde a un Cabeza de una plancha que no pertenece a la zona electoral "
									+ "que usted est� asociado se�or usuario");
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// getMensaje().mostrarMensaje(e.getMessage());
			// mensaje2 = e.getMessage();
			// this.visible = true;

			mensaje2 = e.getMessage();
			this.visible = true;
		}

	}

	public DataModel getData() {
		state = PersistentFacesState.getInstance();

		if (onePageDataModel == null) {
			onePageDataModel = new LocalDataModel(pageSize);
		}

		return onePageDataModel;
	}

	/**
	 * Genera el contenido resultante de consultar por la cabeza de la plancha
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario
	 *         Alejandro Acevedo</a> - GSISIN <br>
	 * 
	 */

	private DataPage<CabezaPlanchaDTO> getDataPage(int startRow, int pageSize) {
		// Retrieve the total number of objects from the database. This
		// number is required by the DataPage object so the paginator will know
		// the relative location of the page data.
		int totalIntegrantes = 0;

		try {
			totalIntegrantes = DelegadoPlanchas.getInstance()
					.obtenerIntegrantesCabezaPlanchaPrinc(numPlancha).size();
		} catch (Exception e) {
			// mensaje.mostrarMensaje(e.getMessage());
			e.printStackTrace();
		}

		// Calculate indices to be displayed in the ui.
		int endIndex = startRow + pageSize;

		if (endIndex > totalIntegrantes) {
			endIndex = totalIntegrantes;
		}

		try {
			integrantes = DelegadoPlanchas.getInstance()
					.obtenerIntegrantesCabezaPlanchaPag(sortColumnName,
							sortAscending, startRow, endIndex - startRow,
							numPlancha);

			// Remove this Renderable from the existing render groups.
			// leaveRenderGroups();
		} catch (Exception e) {
			// mensaje.mostrarMensaje(e.getMessage());
			e.printStackTrace();
		}

		if (integrantes != null && !integrantes.isEmpty()) {
			renderDataTable = true;
		}

		// Add this Renderable to the new render groups.
		// joinRenderGroups();
		// Reset the dirtyData flag.
		onePageDataModel.setDirtyData(false);
		// This is required when using Hibernate JPA. If the EntityManager is
		// not
		// cleared or closed objects are cached and stale objects will show up
		// in the table.
		// This way, the detached objects are reread from the database.
		// This call is not required with TopLink JPA, which uses a Query Hint
		// to clear the l2 cache in the DAO.
		// EntityManagerHelper.getEntityManager().clear();
		flag = true;

		return new DataPage<CabezaPlanchaDTO>(totalIntegrantes, startRow,
				integrantes);
	}

	/**
	 * A special type of JSF DataModel to allow a datatable and datapaginator to
	 * page through a large set of data without having to hold the entire set of
	 * data in memory at once. Any time a managed bean wants to avoid holding an
	 * entire dataset, the managed bean declares this inner class which extends
	 * PagedListDataModel and implements the fetchData method. fetchData is
	 * called as needed when the table requires data that isn't available in the
	 * current data page held by this object. This requires the managed bean
	 * (and in general the business method that the managed bean uses) to
	 * provide the data wrapped in a DataPage object that provides info on the
	 * full size of the dataset.
	 */
	private class LocalDataModel extends PagedListDataModel {
		public LocalDataModel(int pageSize) {
			super(pageSize);
		}

		public DataPage<CabezaPlanchaDTO> fetchPage(int startRow, int pageSize) {
			// call enclosing managed bean method to fetch the data
			return getDataPage(startRow, pageSize);

		}
	}

	public DataPaginator getDataPaginator() {
		return dataPaginator;
	}

	public void setDataPaginator(DataPaginator dataPaginator) {
		this.dataPaginator = dataPaginator;
	}

	public boolean isRenderDataTable() {
		return renderDataTable;
	}

	public void setRenderDataTable(boolean renderDataTable) {
		this.renderDataTable = renderDataTable;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public RenderManager getRenderManager() {
		return renderManager;
	}

	public void setRenderManager(RenderManager renderManager) {
		this.renderManager = renderManager;
	}

	public PersistentFacesState getState() {
		return state;
	}

	public void setState(PersistentFacesState state) {
		this.state = state;
	}

	public String getSortColumnName() {
		return sortColumnName;
	}

	public void setSortColumnName(String sortColumnName) {
		this.sortColumnName = sortColumnName;
	}

	public boolean isSortAscending() {
		return sortAscending;
	}

	public void setSortAscending(boolean sortAscending) {
		this.sortAscending = sortAscending;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	protected boolean isDefaultAscending(String sortColumn) {
		// TODO Auto-generated method stub
		return false;
	}

	public void renderingException(RenderingException arg0) {
		// TODO Auto-generated method stub

	}

	public void dispose() throws Exception {
		// TODO Auto-generated method stub

	}

	public Long getNumCedula() {
		return numCedula;
	}

	public void setNumCedula(Long numCedula) {
		this.numCedula = numCedula;
	}

	public InfoPlanchaDTO getInfoPlancha() {
		return infoPlancha;
	}

	public void setInfoPlancha(InfoPlanchaDTO infoPlancha) {
		this.infoPlancha = infoPlancha;
	}

	public boolean isMuestraInfoPlancha() {
		return muestraInfoPlancha;
	}

	public void setMuestraInfoPlancha(boolean muestraInfoPlancha) {
		this.muestraInfoPlancha = muestraInfoPlancha;
	}

	public Long getNumPlancha() {
		return numPlancha;
	}

	public void setNumPlancha(Long numPlancha) {
		this.numPlancha = numPlancha;
	}

	public List<CabezaPlanchaDTO> getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(List<CabezaPlanchaDTO> integrantes) {
		this.integrantes = integrantes;
	}

	public boolean isVisibleConfirmar() {
		return visibleConfirmar;
	}

	public void setVisibleConfirmar(boolean visibleConfirmar) {
		this.visibleConfirmar = visibleConfirmar;
	}

	public HtmlInputText getTxtNumDoc() {
		try {
			// si se accede a esta jspx desde consulta de planchas por estado,
			// se realiza autom�ticamente la b�squeda
			if (FacesUtils.getSession().getAttribute("referido") != null) {
				action_find();
			} else {
				limpiarCampos();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return txtNumDoc;
	}

	/**
	 * Se modifica metodo para adicionar validaci�n del tipo de eleccion
	 * Para consultar el respetivo listado de resoluciones.
	 * @author Christian Mauricio Tanagrife Colorado cmtc4227
	 */
	public void cargarEstadosPlanchas() {

		List<ParametroPlanchaDTO> estadosPlancha = null;
		itemsEstados = new ArrayList<SelectItem>();

		try {
			estadosPlancha = new ArrayList<ParametroPlanchaDTO>();
			
			String tipoEleccionesSession = (String) FacesUtils
					.getSessionParameter("tipoElecciones");
			String tipoEleccionesRepresentantes = UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"param.tipo.elecciones.representantes");
			if (tipoEleccionesRepresentantes.equals(tipoEleccionesSession)) {
				estadosPlancha = DelegadoPlanchas.getInstance()
				.obtenerParametrosTipo(11L);
			} else {
				estadosPlancha = DelegadoPlanchas.getInstance()
				.obtenerParametrosTipo(15L);
			}
			
			if (!(estadosPlancha == null || estadosPlancha.size() == 0)) {

				if (estadosPlancha.size() > 1) {
					itemsEstados.add(new SelectItem("-1", "- Seleccionar -"));

					for (ParametroPlanchaDTO param : estadosPlancha) {
						itemsEstados.add(new SelectItem(param.getValor(), param
								.getNombre()));
					}
				} else {
					ParametroPlanchaDTO param = (ParametroPlanchaDTO) estadosPlancha
							.get(0);
					itemsEstados.add(new SelectItem(param.getValor(), param
							.getNombre()));
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * Nuevo m�todo para la consulta de excepciones del asociado.
	 */
	public String consultarExcepciones() {

		try {
			/**
			 * Se obtiene el n�mero de identificaci�n del asociado.
			 */
			String identificacion = FacesUtils
					.getRequestParameter("identificacion");
			String consecutivoPlancha = FacesUtils
					.getRequestParameter("consecutivoPlancha");

			if (identificacion != null) {
				listaExcepciones = DelegadoLogicaPlanchaExcepcion.getInstance()
						.consultarExcepciones(identificacion,
								consecutivoPlancha);

				if (listaExcepciones != null) {
					mostrarExcepciones = true;
					mensajeExcepciones = "";
					for (int i = 0; i < listaExcepciones.size(); i++) {
						listaExcepciones.get(i).setDescExcepcion(listaExcepciones.get(i).getDescExcepcion()
								.replace("Por favor tener en cuenta las siguientes observaciones:", ""));
						listaExcepciones.get(i).setDescExcepcion(listaExcepciones.get(i).getDescExcepcion().replace(
								"- El asociado no registra profesi�n. Por favor actualice la misma descargando el Certificado de Profesion u Oficio para poder continuar.",
								""));
						listaExcepciones.get(i).getDescExcepcion().replace("</br>", "\n");
					}
				}
			}
			mostrarExcepciones = true;

		} catch (Exception e) {
			mensaje2 = e.getMessage();
			this.visible = true;
		}
		return null;
	}

	/**
	 * Nuevo m�todo para la consulta de excepciones del asociado.
	 */
	public String consultarExcepcionesPorIdAsociado(String identificacion,
			String consecutivoPlancha) {
		List<ElePlanchaExcepcion> listaExcepcionesAsoc = null;
		String obs = "";
		try {
			if (identificacion != null) {
				listaExcepcionesAsoc = new ArrayList<ElePlanchaExcepcion>(DelegadoLogicaPlanchaExcepcion.getInstance()
						.consultarExcepciones(identificacion, consecutivoPlancha));

				if (!listaExcepcionesAsoc.isEmpty()) {
					for (int i = 0; i < listaExcepcionesAsoc.size(); i++) {
						obs = listaExcepcionesAsoc.get(i).getDescExcepcion();
						obs = obs.replace("Por favor tener en cuenta las siguientes observaciones: </br>", "");
						obs = obs.replace(
								"- El asociado no registra profesi�n. Por favor actualice la misma descargando el Certificado de Profesion u Oficio para poder continuar.",
								"");
						obs = obs.replace("<br/>", "\n");
						obs = obs.replace(" </br>", "");
						String nombre = DelegadoAsociado.getInstance().consultarNombreAsociado(Long.valueOf(identificacion));
						if(i == 0 && obs.length() > 6 && !obs.equals("\n")) {
							if(nombre != null && !nombre.isEmpty()) {
								obs = (" Asociado(a): " + nombre.trim() + ". N�mero de identificaci�n: " +identificacion + "\n") + obs;
							} else {
								obs = (" N�mero de identificaci�n del asociado(a): " +identificacion + "\n") + obs;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			mensaje2 = e.getMessage();
			this.visible = true;
		}
		return obs;
	}
	
	public void setTxtNumDoc(HtmlInputText txtNumDoc) {
		this.txtNumDoc = txtNumDoc;
	}

	public FormatoPdfConstanciaPlancha getFormato() {
		return formato;
	}

	public void setFormato(FormatoPdfConstanciaPlancha formato) {
		this.formato = formato;
	}

	public boolean isVisibleImprAdmision() {
		return visibleImprAdmision;
	}

	public void setVisibleImprAdmision(boolean visibleImprAdmision) {
		this.visibleImprAdmision = visibleImprAdmision;
	}

	public FormatoPdfAdmisionPlancha getFormatoAdmision() {
		return formatoAdmision;
	}

	public void setFormatoAdmision(FormatoPdfAdmisionPlancha formatoAdmision) {
		this.formatoAdmision = formatoAdmision;
	}

	public String actionIrFormularioResolucionRechazo() {

		try {
			if (!(COD_ESTADO_PLANCHA_INADMITIDA.equals(infoPlancha
					.getCodigoEstadoPlancha()) || COD_ESTADO_PLANCHA_EN_RECURSO
					.equals(infoPlancha.getCodigoEstadoPlancha()))) {
				throw new EleccionesDelegadosException(
						LoaderResourceElements
								.getInstance()
								.getKeyResourceValue(
										ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
										ConstantesProperties.MENSAJE_NO_ES_POSIBLE_GENERAR_RESOLUCION_RECHAZO_PLANCHA_ADMITIDA));
			}

			return "irFormularioExpedirResolucionRechazo";
		} catch (Exception e) {
			// getMensaje().mostrarMensaje(e.getMessage());
			mensaje2 = e.getMessage();
			this.visible = true;
		}
		return "";
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public String getFechasAdmision() {
		return fechasAdmision;
	}

	public void setFechasAdmision(String fechasAdmision) {
		this.fechasAdmision = fechasAdmision;
	}

	public boolean isVisibleImprAdmisionFechas() {
		return visibleImprAdmisionFechas;
	}

	public void setVisibleImprAdmisionFechas(boolean visibleImprAdmisionFechas) {
		this.visibleImprAdmisionFechas = visibleImprAdmisionFechas;
	}

	public String getFechasConstancia() {
		return fechasConstancia;
	}

	public void setFechasConstancia(String fechasConstancia) {
		this.fechasConstancia = fechasConstancia;
	}

	public boolean isVisibleImprConstanciaFechas() {
		return visibleImprConstanciaFechas;
	}

	public void setVisibleImprConstanciaFechas(
			boolean visibleImprConstanciaFechas) {
		this.visibleImprConstanciaFechas = visibleImprConstanciaFechas;
	}

	public boolean isVisibleImprInadmision() {
		return visibleImprInadmision;
	}

	public void setVisibleImprInadmision(boolean visibleImprInadmision) {
		this.visibleImprInadmision = visibleImprInadmision;
	}

	/**
	 * Despliega el popup para ingresar las razones de inadmisi�n
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario
	 *         Alejandro Acevedo</a> - GSISIN <br>
	 * @date 27/12/2012
	 * 
	 */
	public void actionExpedirResolucionInadmision() {

		try {
			action_find();

			if (!(COD_ESTADO_PLANCHA_INSCRITA.equals(infoPlancha
					.getCodigoEstadoPlancha()))) {
				throw new EleccionesDelegadosException(
						LoaderResourceElements
								.getInstance()
								.getKeyResourceValue(
										ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
										ConstantesProperties.MENSAJE_IMPOSIBLE_INADMITIR_PLANCHA_NO_REGISTRADA));
			}

			visibleImprInadmision = true;
		} catch (Exception e) {
			// getMensaje().mostrarMensaje(e.getMessage());
			mensaje2 = e.getMessage();
			this.visible = true;
		}
	}

	public void actionResoluciones() {

		try {
			action_find();
			String codigoFormato = null;
			codResolucion = Long.valueOf(getSelEstado().getValue().toString());
			EleDetalleFormatoPlanchaId id = new EleDetalleFormatoPlanchaId();
			Long codigoAsociado = DelegadoAsociado.getInstance().consultarCodigoAsociadoPorNumeroDocumento(Long.valueOf(cabezaPlancha));
			id.setCodigoAsociado(codigoAsociado);

			if (!COD_ESTADO_PLANCHA_ADMITIDA.equals(infoPlancha
					.getCodigoEstadoPlancha())
					&& !COD_ESTADO_PLANCHA_RECHAZADA.equals(infoPlancha
							.getCodigoEstadoPlancha())) {
				throw new EleccionesDelegadosException(
						LoaderResourceElements
								.getInstance()
								.getKeyResourceValue(
										ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
										ConstantesProperties.MENSAJE_IMPOSIBLE_IMPRIMIR_RESOLUCION_RECURSOS));
			}

			//admitidas o rechazadas
			if ("1".equals(codResolucion.toString())) {
				titleImprResolucion = UtilAcceso
						.getParametroFuenteS(
								ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
								ConstantesProperties.LBL_TITLE_NO_REPOSICION_SIN_APELACION);
				codigoFormato = UtilAcceso
						.getParametroFuenteS(
								ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
								"codigo.formato.noreposicion.sinapelacion");
				id.setCodigoFormato(new Byte(codigoFormato));
				EleDetalleFormatoPlancha fPlancha = DelegadoDetalleFormato
						.getInstance().findById(id);
				if (fPlancha != null) {
					throw new Exception(
							"Ya fu� generada la resoluci�n no reposici�n sin apelaci�n. ");
				}
			} else if ("2".equals(codResolucion.toString())) {
				titleImprResolucion = UtilAcceso
						.getParametroFuenteS(
								ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
								ConstantesProperties.LBL_TITLE_RESOLUCION_EN_CONTRA_CON_APELACION);
				codigoFormato = UtilAcceso
						.getParametroFuenteS(
								ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
								"codigo.formato.reposicion.conapelacion");
				id.setCodigoFormato(new Byte(codigoFormato));
				EleDetalleFormatoPlancha fPlancha = DelegadoDetalleFormato
						.getInstance().findById(id);
				if (fPlancha != null) {
					throw new Exception(
							"Ya fu� generada la resoluci�n Reposici�n en Contra con Apelaci�n. ");
				}
			} else if ("3".equals(codResolucion.toString())) {

				titleImprResolucion = UtilAcceso
						.getParametroFuenteS(
								ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
								ConstantesProperties.LBL_TITLE_RESOLUCION_APELACION_FAVORABLE);
				codigoFormato = UtilAcceso
						.getParametroFuenteS(
								ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
								"codigo.formato.apelacion.favorable");
				id.setCodigoFormato(new Byte(codigoFormato));
				EleDetalleFormatoPlancha fPlancha = DelegadoDetalleFormato
						.getInstance().findById(id);
				if (fPlancha != null) {
					throw new Exception(
							"Ya fu� generada la resoluci�n Apelaci�n Favorable. ");
				}
			} else if ("4".equals(codResolucion.toString())) {
				titleImprResolucion = UtilAcceso
						.getParametroFuenteS(
								ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
								ConstantesProperties.LBL_TITLE_RESOLUCION_APELACION_EN_CONTRA);
				codigoFormato = UtilAcceso
						.getParametroFuenteS(
								ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
								"codigo.formato.apelacion.encontra");
				id.setCodigoFormato(new Byte(codigoFormato));
				EleDetalleFormatoPlancha fPlancha = DelegadoDetalleFormato
						.getInstance().findById(id);
				if (fPlancha != null) {
					throw new Exception(
							"Ya fu� generada la resoluci�n Apelaci�n en Contra. ");
				}
			} else if ("5".equals(codResolucion.toString())) {
				titleImprResolucion = UtilAcceso
						.getParametroFuenteS(
								ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
								ConstantesProperties.LBL_TITLE_RESOLUCION_CONCEDE_RECURSO_DE_APELACION);
				codigoFormato = UtilAcceso
						.getParametroFuenteS(
								ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
								"codigo.formato.concede.recurso.apelacion");
				id.setCodigoFormato(new Byte(codigoFormato));
				EleDetalleFormatoPlancha fPlancha = DelegadoDetalleFormato
						.getInstance().findById(id);
				if (fPlancha != null) {
					throw new Exception(
							"Ya fu� generada la resoluci�n que concede recurso de apelaci�n");
				}
			}
			
			else if ("6".equals(codResolucion.toString())) {
				titleImprResolucion = UtilAcceso
						.getParametroFuenteS(
								ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
								"lbldisionTribunalCO-FT-459");
				codigoFormato = UtilAcceso
						.getParametroFuenteS(
								ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
								"codigo.formato.delegado.deniegaRoposicion");
				id.setCodigoFormato(new Byte(codigoFormato));
				EleDetalleFormatoPlancha fPlancha = DelegadoDetalleFormato.getInstance().findById(id);
				if (fPlancha != null) {
					throw new Exception(
							"Ya fu� generada la resoluci�n que deniega un recurso de reposici�n y no concede apelaci�n por no ser solicitado. ");
				}
			} else if ("7".equals(codResolucion.toString())) {
				titleImprResolucion = UtilAcceso
						.getParametroFuenteS(
								ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
								"lbldisionTribunalCO-FT-460");
				codigoFormato = UtilAcceso
						.getParametroFuenteS(
								ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
								"codigo.formato.delegado.reposiscionApelacion");
				id.setCodigoFormato(new Byte(codigoFormato));
				EleDetalleFormatoPlancha fPlancha = DelegadoDetalleFormato
						.getInstance().findById(id);
				if (fPlancha != null) {
					throw new Exception(
							"Ya fu� generada resoluci�n que resuelve un recurso de reposici�n en contra y remite la apelaci�n. ");
				}
			} else if ("8".equals(codResolucion.toString())) {
				titleImprResolucion = UtilAcceso
						.getParametroFuenteS(
								ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
								"lbldisionTribunalCO-FT-461");
				codigoFormato = UtilAcceso
						.getParametroFuenteS(
								ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
								"codigo.formato.delegado.recursoFavorable");
				id.setCodigoFormato(new Byte(codigoFormato));
				EleDetalleFormatoPlancha fPlancha = DelegadoDetalleFormato
						.getInstance().findById(id);
				if (fPlancha != null) {
					throw new Exception(
							"Ya fu� generada la resoluci�n que resuelve un recurso de apelaci�n favorable. ");
				}
			} else if ("9".equals(codResolucion.toString())) {
				titleImprResolucion = UtilAcceso
						.getParametroFuenteS(
								ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
								"lbldisionTribunalCO-FT-462");
				codigoFormato = UtilAcceso
						.getParametroFuenteS(
								ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
								"codigo.formato.delegado.recursoEnContra");
				id.setCodigoFormato(new Byte(codigoFormato));
				EleDetalleFormatoPlancha fPlancha = DelegadoDetalleFormato
						.getInstance().findById(id);
				if (fPlancha != null) {
					throw new Exception(
							"Ya fu� generada la resoluci�n que resuelve un recurso de apelaci�n en contra. ");
				}
			} else if ("10".equals(codResolucion.toString())) {
				titleImprResolucion = UtilAcceso
						.getParametroFuenteS(
								ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
								"lbldisionTribunalCO-FT-753");
				codigoFormato = UtilAcceso
						.getParametroFuenteS(
								ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
								"codigo.formato.delegado.extemporaneamente");
				id.setCodigoFormato(new Byte(codigoFormato));
				EleDetalleFormatoPlancha fPlancha = DelegadoDetalleFormato
						.getInstance().findById(id);
				if (fPlancha != null) {
					throw new Exception(
							"Ya fu� generada la resoluci�n que resuelve recursos interpuestos extempor�neamente");
				}
			} else if("11".equals(codResolucion.toString()))
			{
				titleImprResolucion = UtilAcceso
				.getParametroFuenteS(
						ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
						"lbldisionTribunalCO-FT-458");
				codigoFormato = UtilAcceso
						.getParametroFuenteS(
								ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
								"codigo.formato.delegado.favorable");
				id.setCodigoFormato(new Byte(codigoFormato));
				EleDetalleFormatoPlancha fPlancha = DelegadoDetalleFormato
						.getInstance().findById(id);
				if (fPlancha != null) {
					throw new Exception(
							"Ya fu� generada la resoluci�n que resuelve recursos interpuestos extempor�neamente");
				}
			}
			visibleAccionante = false;
			if (codResolucion.equals(1L) || codResolucion.equals(2L)
					|| codResolucion.equals(4L) || codResolucion.equals(5L)
				) {
				visibleImprResolucion = true;
			} else if (codResolucion.equals(3L)) {
				visibleImprApelacionFavorable = true;
			} else if (codResolucion.equals(6L) || codResolucion.equals(7L) || codResolucion.equals(8L)
					|| codResolucion.equals(9L) || codResolucion.equals(10L) || codResolucion.equals(11L)) {
				visibleImprResolucionDelegados = true;
				if (codResolucion.equals(8L) || codResolucion.equals(9L)) {
					visibleDesicionTribunalnDelegados = true;
				} else if (codResolucion.equals(10L)) {
					visibleFechaRecurso = true;
				} else if (codResolucion.equals(11L)) {
					visibleFavorable = true;
				} else if (codResolucion.equals(6L)) {
					visibleFavorable = true;
				} else if (codResolucion.equals(7L)) {
					visibleAccionante = true;
				}
				
			}
			
		} catch (Exception e) {
			// getMensaje().mostrarMensaje(e.getMessage());
			mensaje2 = e.getMessage();
			this.visible = true;
		}
	}

	public String actionConfirmarRadicacion() {
		this.visibleConfirmarRadicacion = Boolean.TRUE;
		this.mensajeConfirmacion = "�Est� seguro que desea radicar la plancha e imprimir la constancia?";
		return "";
	}
	
	public String actionCloseConfirmar() {
		this.visibleConfirmarRadicacion = false;
		this.mensajeConfirmacion = "";
		return "";
	}
	
	public String actionConfirmarcumplimientoRequisitos() {
		this.visibleConfirmarCumplimientoRequisitos = Boolean.TRUE;
		this.mensajeConfirmacion = "�Est� seguro que desea imprimir el Certificado de Cumplimiento de Requisitos?";
		return "";
	}
	
	public String actionCloseConfirmarCumplimientoRequisitos() {
		this.visibleConfirmarCumplimientoRequisitos = false;
		this.mensajeConfirmacion = "";
		return "";
	}


	public void action_cancelar_inadmision() {
		limpiar_formulario();
		visibleImprInadmision = false;
	}

	public void action_cancelar_resolucion() {
		limpiar_formulario_resolucion();
		visibleImprResolucion = false;
		visibleImprResolucionDelegados = false;
		visibleDesicionTribunalnDelegados = false;
	}

	public void action_cancelar_apelacion_favorable() {
		limpiar_formulario_apelacion_favorable();
		visibleImprApelacionFavorable = false;
	}

	public void action_cerrar_mensaje() {
		mostrarExcepciones = false;
	}

	public String getCabezaPlancha() {
		return cabezaPlancha;
	}

	public void setCabezaPlancha(String cabezaPlancha) {
		this.cabezaPlancha = cabezaPlancha;
	}

	public String getNombreAsociado() {
		return nombreAsociado;
	}

	public void setNombreAsociado(String nombreAsociado) {
		this.nombreAsociado = nombreAsociado;
	}

	public String getResolucionNro() {
		return resolucionNro;
	}

	public void setResolucionNro(String resolucionNro) {
		
		if( resolucionNro != null && !resolucionNro.isEmpty())
		{
			this.resolucionNro = resolucionNro;
		}
	}

	public String getActNro() {
		return actNro;
	}

	public void setActNro(String actNro) {
		this.actNro = actNro;
	}

	public String getCuidad() {
		return cuidad;
	}

	public void setCuidad(String cuidad) {
		this.cuidad = cuidad;
	}

	public String getRazon1() {
		return razon1;
	}

	public void setRazon1(String razon1) {
		this.razon1 = razon1;
	}

	public String getRazon2() {
		return razon2;
	}

	public void setRazon2(String razon2) {
		this.razon2 = razon2;
	}

	public String getRazon3() {
		return razon3;
	}

	public void setRazon3(String razon3) {
		this.razon3 = razon3;
	}

	public String getRazon4() {
		return razon4;
	}

	public void setRazon4(String razon4) {
		this.razon4 = razon4;
	}

	public void mostrarMensaje(String mensaje) {
		// Muestra el mensaje en el panel popup
		this.mensaje2 = mensaje;
		visible = true;
	}

	public void ocultarMensaje() {
		this.mensaje2 = "";
		visible = false;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getMensaje2() {
		return mensaje2;
	}

	public void setMensaje2(String mensaje2) {
		this.mensaje2 = mensaje2;
	}

	public boolean isMostrarExcepciones() {
		return mostrarExcepciones;
	}

	public void setMostrarExcepciones(boolean mostrarExcepciones) {
		this.mostrarExcepciones = mostrarExcepciones;
	}

	public List<ElePlanchaExcepcion> getListaExcepciones() {
		return listaExcepciones;
	}

	public void setListaExcepciones(List<ElePlanchaExcepcion> listaExcepciones) {
		this.listaExcepciones = listaExcepciones;
	}

	public String getMensajeExcepciones() {
		return mensajeExcepciones;
	}

	public void setMensajeExcepciones(String mensajeExcepciones) {
		this.mensajeExcepciones = mensajeExcepciones;
	}

	public boolean isVisibleImprResolucion() {
		return visibleImprResolucion;
	}

	public void setVisibleImprResolucion(boolean visibleImprResolucion) {
		this.visibleImprResolucion = visibleImprResolucion;
	}

	public String getArgumentoResolucion1() {
		return argumentoResolucion1;
	}

	public void setArgumentoResolucion1(String argumentoResolucion1) {
		this.argumentoResolucion1 = argumentoResolucion1;
	}

	public String getArgumentoResolucion2() {
		return argumentoResolucion2;
	}

	public void setArgumentoResolucion2(String argumentoResolucion2) {
		this.argumentoResolucion2 = argumentoResolucion2;
	}

	public String getArgumentoResolucion3() {
		return argumentoResolucion3;
	}

	public void setArgumentoResolucion3(String argumentoResolucion3) {
		this.argumentoResolucion3 = argumentoResolucion3;
	}

	public String getRazonResolucion1() {
		return razonResolucion1;
	}

	public void setRazonResolucion1(String razonResolucion1) {
		this.razonResolucion1 = razonResolucion1;
	}

	public String getRazonResolucion2() {
		return razonResolucion2;
	}

	public void setRazonResolucion2(String razonResolucion2) {
		this.razonResolucion2 = razonResolucion2;
	}

	public HtmlSelectOneMenu getSelEstado() {
		cargarEstadosPlanchas();
		return selEstado;
	}

	public void setSelEstado(HtmlSelectOneMenu selEstado) {
		this.selEstado = selEstado;
	}

	public List<SelectItem> getItemsEstados() {
		return itemsEstados;
	}

	public void setItemsEstados(List<SelectItem> itemsEstados) {
		this.itemsEstados = itemsEstados;
	}

	public String getResolucionImpugnada() {
		return resolucionImpugnada;
	}

	public void setResolucionImpugnada(String resolucionImpugnada) {
		this.resolucionImpugnada = resolucionImpugnada;
	}

	public Long getCodResolucion() {
		return codResolucion;
	}

	public void setCodResolucion(Long codResolucion) {
		this.codResolucion = codResolucion;
	}

	public String getResolucionInterpuesta() {
		return resolucionInterpuesta;
	}

	public void setResolucionInterpuesta(String resolucionInterpuesta) {
		this.resolucionInterpuesta = resolucionInterpuesta;
	}

	public String getArgumentoRecurrente1() {
		return argumentoRecurrente1;
	}

	public void setArgumentoRecurrente1(String argumentoRecurrente1) {
		this.argumentoRecurrente1 = argumentoRecurrente1;
	}

	public String getArgumentoRecurrente2() {
		return argumentoRecurrente2;
	}

	public void setArgumentoRecurrente2(String argumentoRecurrente2) {
		this.argumentoRecurrente2 = argumentoRecurrente2;
	}

	public String getArgumentoComision1() {
		return argumentoComision1;
	}

	public void setArgumentoComision1(String argumentoComision1) {
		this.argumentoComision1 = argumentoComision1;
	}

	public String getArgumentoComision2() {
		return argumentoComision2;
	}

	public void setArgumentoComision2(String argumentoComision2) {
		this.argumentoComision2 = argumentoComision2;
	}

	public String getPronunciamientoTribunal1() {
		return pronunciamientoTribunal1;
	}

	public void setPronunciamientoTribunal1(String pronunciamientoTribunal1) {
		this.pronunciamientoTribunal1 = pronunciamientoTribunal1;
	}

	public String getPronunciamientoTribunal2() {
		return pronunciamientoTribunal2;
	}

	public void setPronunciamientoTribunal2(String pronunciamientoTribunal2) {
		this.pronunciamientoTribunal2 = pronunciamientoTribunal2;
	}

	public String getConsecuenciaTribunal1() {
		return consecuenciaTribunal1;
	}

	public void setConsecuenciaTribunal1(String consecuenciaTribunal1) {
		this.consecuenciaTribunal1 = consecuenciaTribunal1;
	}

	public String getConsecuenciaTribunal2() {
		return consecuenciaTribunal2;
	}

	public void setConsecuenciaTribunal2(String consecuenciaTribunal2) {
		this.consecuenciaTribunal2 = consecuenciaTribunal2;
	}

	public boolean isVisibleImprApelacionFavorable() {
		return visibleImprApelacionFavorable;
	}

	public void setVisibleImprApelacionFavorable(
			boolean visibleImprApelacionFavorable) {
		this.visibleImprApelacionFavorable = visibleImprApelacionFavorable;
	}

	public String getResolucionNroApelacion() {
		return resolucionNroApelacion;
	}

	public void setResolucionNroApelacion(String resolucionNroApelacion) {
		this.resolucionNroApelacion = resolucionNroApelacion;
	}

	public String getResolucionImpugnadaApelacion() {
		return resolucionImpugnadaApelacion;
	}

	public void setResolucionImpugnadaApelacion(
			String resolucionImpugnadaApelacion) {
		this.resolucionImpugnadaApelacion = resolucionImpugnadaApelacion;
	}

	public String getTitleImprResolucion() {
		return titleImprResolucion;
	}

	public void setTitleImprResolucion(String titleImprResolucion) {
		this.titleImprResolucion = titleImprResolucion;
	}

	public boolean isVisibleConfirmarRadicacion() {
		return visibleConfirmarRadicacion;
	}

	public void setVisibleConfirmarRadicacion(boolean visibleConfirmarRadicacion) {
		this.visibleConfirmarRadicacion = visibleConfirmarRadicacion;
	}

	public String getMensajeConfirmacion() {
		return mensajeConfirmacion;
	}

	public void setMensajeConfirmacion(String mensajeConfirmacion) {
		this.mensajeConfirmacion = mensajeConfirmacion;
	}

	public boolean isVisibleImprResolucionDelegados() {
		return visibleImprResolucionDelegados;
	}

	public void setVisibleImprResolucionDelegados(
			boolean visibleImprResolucionDelegados) {
		this.visibleImprResolucionDelegados = visibleImprResolucionDelegados;
	}

	public String getDesicionTribunal() {
		return desicionTribunal;
	}

	public void setDesicionTribunal(String desicionTribunal) {
		this.desicionTribunal = desicionTribunal;
	}

	public boolean isVisibleDesicionTribunalnDelegados() {
		return visibleDesicionTribunalnDelegados;
	}

	public void setVisibleDesicionTribunalnDelegados(
			boolean visibleDesicionTribunalnDelegados) {
		this.visibleDesicionTribunalnDelegados = visibleDesicionTribunalnDelegados;
	}

	public String getNumActa() {
		return numActa;
	}

	public void setNumActa(String numActa) {
		this.numActa = numActa;
	}

	public String getNumActaTribunal() {
		return numActaTribunal;
	}

	public void setNumActaTribunal(String numActaTribunal) {
		this.numActaTribunal = numActaTribunal;
	}

	public boolean isVisibleFechaRecurso() {
		return visibleFechaRecurso;
	}

	public void setVisibleFechaRecurso(boolean visibleFechaRecurso) {
		this.visibleFechaRecurso = visibleFechaRecurso;
	}

	public Date getFechaPresentacion() {
		return fechaPresentacion;
	}

	public void setFechaPresentacion(Date fechaPresentacion) {
		this.fechaPresentacion = fechaPresentacion;
	}

	public boolean isVisibleFavorable() {
		return visibleFavorable;
	}

	public void setVisibleFavorable(boolean visibleFavorable) {
		this.visibleFavorable = visibleFavorable;
	}

	public String getNombreAccionante() {
		return nombreAccionante;
	}

	public void setNombreAccionante(String nombreAccionante) {
		this.nombreAccionante = nombreAccionante;
	}

	public boolean isVisibleConfirmarCumplimientoRequisitos() {
		return visibleConfirmarCumplimientoRequisitos;
	}

	public void setVisibleConfirmarCumplimientoRequisitos(boolean visibleConfirmarCumplimientoRequisitos) {
		this.visibleConfirmarCumplimientoRequisitos = visibleConfirmarCumplimientoRequisitos;
	}

	public String getNumeroResolucion() {
		return numeroResolucion;
	}

	public void setNumeroResolucion(String numeroResolucion) {
		this.numeroResolucion = numeroResolucion;
	}

	public boolean isVisibleNumeroResolucion() {
		return visibleNumeroResolucion;
	}

	public void setVisibleNumeroResolucion(boolean visibleNumeroResolucion) {
		this.visibleNumeroResolucion = visibleNumeroResolucion;
	}

	public boolean isVisibleAccionante() {
		return visibleAccionante;
	}

	public void setVisibleAccionante(boolean visibleAccionante) {
		this.visibleAccionante = visibleAccionante;
	}
	
	
}