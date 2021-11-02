package co.com.coomeva.ele.backbeans;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import co.com.coomeva.ele.delegado.formulario.DelegadoFormulario;
import co.com.coomeva.ele.delegado.formulario.DelegadoRegistroFormulario;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoPlancha;
import co.com.coomeva.ele.dto.DTOInformacionPlancha;
import co.com.coomeva.ele.dto.DTOMiembroPlancha;
import co.com.coomeva.ele.dto.DTOPlanchaAsociado;
import co.com.coomeva.ele.dto.PreguntasFormulario176DTO;
import co.com.coomeva.ele.entidades.formulario.EleCampo;
import co.com.coomeva.ele.entidades.formulario.EleFormulario;
import co.com.coomeva.ele.entidades.formulario.EleRegistroCampos;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.ele.util.PreguntasFormulario176Util;
import co.com.coomeva.ele.util.WorkStrigs;
import co.com.coomeva.profile.ws.client.CaasStub.Section;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;
import co.com.coomeva.util.acceso.UtilAcceso;

import com.icesoft.faces.context.effects.JavascriptContext;

/**
 * @author Carlos Ernesto Ortega Q.
 * 
 * @update 28/08/2016
 * @author Christian Mauricio Tangarife Colorado cmtc4227
 * 
 * @update 26/10/2021
 * @author GTC CORPORATION - Danilo López Sandoval
 */
public class FormularioReportesVista extends BaseVista {
	private String nombreAspirante;
	private boolean verNombreAspirante;
	private String firmaCandidato;
	private boolean verFirmaCadidato;
	private File fotoCabezaPlancha;
	private boolean verFotoCabezaPlancha;
	private String profesionApirante;
	private boolean verProfesionAspirante;
	private String profesionCabezaPlancha;
	private boolean verProfesionCabezaPlancha;
	private String tipoIdentAspirante;
	private boolean verTipoIdentAspirante;
	private String numeroDocuemtoAspirante;
	private boolean verNumeroDocumentoAspirante;
	private String zonaExpedicionDocumento;
	private boolean verZonaExpedicionDocumento;
	private Date fechaExpedicionDocumento;
	private boolean verFechaExpedicionDocumento;
	private String nombreCabezaPlancha;
	private boolean verNombreCabezaPlancha;
	private String firmaCabezaPlancha;
	private boolean verFirmaCabezaPlancha;
	private String tipoIdentCabezaPlancha;
	private boolean verTipoIdentCabezaPlancha;
	private String numeroDocuemtoCabezaPlancha;
	private boolean verNumeroDocumentoCabezaPlancha;
	private String nombreIncribePlancha;
	private boolean verNombreIncribePlancha;
	private String firmaIncribePlancha;
	private boolean verFirmaIncribePlancha;
	private String tipoIdentIncribePlancha;
	private boolean verTipoIdentIncribePlancha;
	private String numeroDocuemtoIncribePlancha;
	private boolean verNumeroDocumentoIncribePlancha;
	private String numZonaElectroral;
	private boolean verNumZonaElectroral;
	private String nombreComicion;
	private boolean verNombreComicion;
	private String numComicion;
	private boolean verNumComicion;
	private String ciudad;
	private boolean verCiudad;
	private String ciudadComicion;
	private boolean verCiudadComicion;
	private Date fechaElaboracionDoc;
	private boolean verFechaElaboracionDoc;
	private String hora;
	private boolean verHora;
	private String numPlancha;
	private boolean verNumPlancha;
	private String numResolucionRechazoComision;
	private boolean verNumResolucionRechazoComision;
	private String numResolucionApelada;
	private boolean verNumResolucionApelada;
	private String numResolucionImpugnada;
	private boolean verNumResolucionImpugnada;
	private String numResolucionExtemporaneamente;
	private boolean verNumResolucionExtemporaneamente;
	private String numResolucionDecisionComision;
	private boolean verNumResolucionDecisionComision;
	private String nombreRespresenateComision;
	private boolean verNombreRespresenateComision;
	private String firmaRespresenateComision;
	private boolean verFirmaRespresenateComision;
	private String numResolucionAdmisionPlancha;
	private boolean verNumResolucionAdmisionPlancha;
	private String numResolucionInadmisionPlancha;
	private boolean verNumResolucionInadmisionPlancha;
	private String numResolucionCodigo;
	private boolean verNumResolucionCodigo;
	private Date fechaResolucionCodigo;
	private boolean verFechaResolucionCodigo;
	private String numResolucionReglamento;
	private boolean verNumResolucionReglamento;
	private Date fechaResolucionReglamento;
	private boolean verFechaResolucionReglamento;
	private String numActaTribunal;
	private boolean verNumActaTribunal;
	private String numActa;
	private boolean verNumActa;
	private Date fechaActa;
	private boolean verFechaActa;
	private String nombrePresidente;
	private boolean verNombrePresidente;
	private String firmaPresidente;
	private boolean verFirmaPresidente;
	private String nombreSecretario;
	private boolean verNombreSecretario;
	private String firmaSecretario;
	private boolean verFirmSecretario;
	private String firmaEntrega;
	private boolean verFirmEntrega;
	private String tipoDocEntrega;
	private boolean verTipoDocEntrega;
	private String numDocEntrega;
	private boolean verNumDocEntrega;
	private String nombreEntrega;
	private boolean verNombreEntrega;
	private String anioSuspension;
	private boolean verAnioSuspension;
	private String nombreRecibe;
	private boolean verNombreRecibe;
	private String firmaRecibe;
	private boolean verFirmRecibe;
	private String tipoDocRecibe;
	private boolean verTipoDocRecibe;
	private String numDocRecibe;
	private boolean verNumDocRecibe;
	private String argApelacion;
	private boolean verArgApelacion;
	private String antiguedad;
	private boolean verAntiguedad;
	private String anioExpedicionTitulo;
	private boolean verAnioExpedicionTitulo;
	private String otrosEstudios;
	private boolean verOtrosEstudios;
	private String experienciaLaboral;
	private boolean verExperienciaLaboral;
	private String ultimoCargo;
	private boolean verultimoCargo;
	private Date fechaPresentacion;
	private boolean verFechaPresentacion;
	private String razones;
	private boolean verRazones;
	private String razon1;
	private boolean verRazon1;
	private String razon2;
	private boolean verRazon2;
	private String razon3;
	private boolean verRazon3;
	private String razon4;
	private boolean verRazon4;
	private Date fechaFirma;
	private boolean verFechaFirma;
	private String desicion;
	private boolean verDesicion;
	private String nombreAccionante;
	private boolean verNombreAccionante;
	private String argReposicion;
	private boolean verArgReposicion;

	private String tipoReporte;
	private boolean isReporte176;
	private boolean esReporte211;
	
	private List<SelectItem> listaFormulario;
	private List<PreguntasFormulario176DTO> listaPreguntas;
	private List<SelectItem> listRespuesta;

	private MensajesVista mensajeVista = getMensaje();

	public FormularioReportesVista() {
		init();
	}
	private void init() {
		try {

		} catch (Exception e) {
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes", "nullException");
			}
			getMensaje().mostrarMensaje(mensaje);
		}
	}

	public String selecionarFormularioListener(ValueChangeEvent evento) {
		if (evento != null && evento.getNewValue() != null) {
			accionLimpiarCampos();
			if (!evento.getNewValue().toString().equals("-1")) {
				accionCargarCampos(evento.getNewValue().toString());
			}
		}
		return "";
	}

	public String consultarIntegrantePlanchaListener(ValueChangeEvent evento) {
		try {
			if (evento != null && evento.getNewValue() != null) {
				if (evento.getNewValue().toString().equals("")) {
					numPlancha = "";
					nombreAspirante = "";
					numeroDocuemtoAspirante = "";
					fechaElaboracionDoc = null;
				} else {
					this.numeroDocuemtoAspirante = evento.getNewValue().toString();
					this.fechaElaboracionDoc = new Date();

					if (numeroDocuemtoAspirante != null && !numeroDocuemtoAspirante.isEmpty()) {
						Long numeroDocumento = Long.valueOf(numeroDocuemtoAspirante);
						List<DTOPlanchaAsociado> planchaAsociado = DelegadoPlancha.getInstance()
								.asociadoPertenceOtraPlancha(numeroDocumento, null);

						if (planchaAsociado != null && !planchaAsociado.isEmpty()) {
							numPlancha = planchaAsociado.get(0).getConsecutivoPlancha().toString();

							DTOInformacionPlancha infoPlancha = DelegadoPlancha.getInstance()
									.consultarInformacionPlancha(planchaAsociado.get(0).getConsecutivoPlancha());
							
							boolean hayRegistro = false;
							if (infoPlancha != null) {
								if (infoPlancha.getMiembrosTitulares() != null
										&& !infoPlancha.getMiembrosTitulares().isEmpty()) {
									for (DTOMiembroPlancha dtoMiembroPlancha : infoPlancha.getMiembrosTitulares()) {

										if (dtoMiembroPlancha.getNumeroDocumento().equals(numeroDocumento)) {
											nombreAspirante = dtoMiembroPlancha.getApellidosNombres();
											hayRegistro = true;
											break;
										}
									}
								}
								if (!hayRegistro && infoPlancha.getMiembrosSuplentes() != null
										&& !infoPlancha.getMiembrosSuplentes().isEmpty()) {
									for (DTOMiembroPlancha dtoMiembroPlancha : infoPlancha.getMiembrosSuplentes()) {
										if (dtoMiembroPlancha.getNumeroDocumento().equals(numeroDocumento)) {
											nombreAspirante = dtoMiembroPlancha.getApellidosNombres();
											hayRegistro = true;
											break;
										}
									}
								}
							}
						} else {
							numPlancha = "";
							nombreAspirante = "";
							numeroDocuemtoAspirante = "";
							fechaElaboracionDoc = null;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public void generarReporteListener() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		List<EleRegistroCampos> listaRegCampos = new ArrayList<EleRegistroCampos>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

		// Resolucion de admision de planchas
		if (tipoReporte.equalsIgnoreCase("172")) {
			request.getSession().setAttribute("zonaElectoral", numZonaElectroral);
			request.getSession().setAttribute("nombreAsociado", nombreCabezaPlancha);
			request.getSession().setAttribute("numResolucion", numResolucionAdmisionPlancha);
			request.getSession().setAttribute("numActa", numActa);
			request.getSession().setAttribute("fecha", fechaActa);
			request.getSession().setAttribute("ciudadZona", ciudad);
			request.getSession().setAttribute("dia",
					fechaElaboracionDoc != null ? String.valueOf(fechaElaboracionDoc.getDate()) : "");
			request.getSession().setAttribute("mes",
					fechaElaboracionDoc != null ? String.valueOf(fechaElaboracionDoc.getMonth()) : "");
			request.getSession().setAttribute("anio",
					fechaElaboracionDoc != null ? String.valueOf(fechaElaboracionDoc.getYear()) : "");

			// para guardar en base de datos los registros de los campos
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 10L, nombreCabezaPlancha));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 18L, numZonaElectroral));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 21L, ciudad));
			listaRegCampos
					.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 23L, sdf.format(fechaElaboracionDoc)));
			listaRegCampos
					.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 33L, numResolucionAdmisionPlancha));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 40L, numActa));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 41L, sdf.format(fechaActa)));

		}
		// resolución de Rechazo de Planchas CO-FT-173
		else if (tipoReporte.equalsIgnoreCase("173")) {
			request.getSession().setAttribute("zonaElectoral", numZonaElectroral);
			request.getSession().setAttribute("nombreAsociado", nombreCabezaPlancha);
			request.getSession().setAttribute("cedulaAsociado", numeroDocuemtoCabezaPlancha);
			request.getSession().setAttribute("numResolucion", numResolucionRechazoComision);
			request.getSession().setAttribute("numActa", numActa);
			request.getSession().setAttribute("fecha", fechaActa);
			request.getSession().setAttribute("dia",
					fechaElaboracionDoc != null ? String.valueOf(fechaElaboracionDoc.getDate()) : "");
			request.getSession().setAttribute("mes",
					fechaElaboracionDoc != null ? String.valueOf(fechaElaboracionDoc.getMonth()) : "");
			request.getSession().setAttribute("anio",
					fechaElaboracionDoc != null ? String.valueOf(fechaElaboracionDoc.getYear()) : "");
			//request.getSession().setAttribute("razones", razones);

			// para guardar en base de datos los registros de los campos
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 10L, nombreCabezaPlancha));
			listaRegCampos
					.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 13L, numeroDocuemtoCabezaPlancha));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 18L, numZonaElectroral));
			listaRegCampos
					.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 23L, sdf.format(fechaElaboracionDoc)));
			listaRegCampos
					.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 26L, numResolucionRechazoComision));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 40L, numActa));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 41L, sdf.format(fechaActa)));
			//listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 61L, razones));

		}
		//TODO habilitar este reporte  
		// Informacion Personal del caneza de Plancha CO-FT-174 (eliminado de base de
		// datos)
		/*
		 * else if(tipoReporte.equalsIgnoreCase("174")) {
		 * request.getSession().setAttribute("plancha", numPlancha);
		 * request.getSession().setAttribute("zonaElectoral", numZonaElectroral);
		 * request.getSession().setAttribute("nombreAsociado", nombreCabezaPlancha);
		 * request.getSession().setAttribute("cedulaAsociado",
		 * numeroDocuemtoCabezaPlancha);
		 * request.getSession().setAttribute("fechaAntiguedad", antiguedad);
		 * request.getSession().setAttribute("profesion", profesionCabezaPlancha);
		 * request.getSession().setAttribute("fechaTitulo", anioExpedicionTitulo);
		 * request.getSession().setAttribute("estudios", otrosEstudios); String[]
		 * experiencia = experienciaLaboral!=null?experienciaLaboral.split(","):null;
		 * request.getSession().setAttribute("empresa",
		 * experiencia!=null?experiencia[0]:null);
		 * request.getSession().setAttribute("cargo",
		 * experiencia!=null?experiencia[1]:null);
		 * request.getSession().setAttribute("antiguedad",
		 * experiencia!=null?experiencia[2]:null);
		 * request.getSession().setAttribute("ultimoCargo", ultimoCargo);
		 * 
		 * // para guardar en base de datos los registros de los campos
		 * listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte),
		 * 25L, numPlancha)); listaRegCampos.add(new EleRegistroCampos(null,
		 * Long.valueOf(tipoReporte), 18L, numZonaElectroral)); listaRegCampos.add(new
		 * EleRegistroCampos(null, Long.valueOf(tipoReporte), 10L,
		 * nombreCabezaPlancha)); listaRegCampos.add(new EleRegistroCampos(null,
		 * Long.valueOf(tipoReporte), 13L, numeroDocuemtoCabezaPlancha));
		 * listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte),
		 * 55L, antiguedad)); listaRegCampos.add(new EleRegistroCampos(null,
		 * Long.valueOf(tipoReporte), 5L, profesionCabezaPlancha));
		 * listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte),
		 * 56L, anioExpedicionTitulo)); listaRegCampos.add(new EleRegistroCampos(null,
		 * Long.valueOf(tipoReporte), 57L, otrosEstudios)); listaRegCampos.add(new
		 * EleRegistroCampos(null, Long.valueOf(tipoReporte), 58L, experienciaLaboral));
		 * listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte),
		 * 59L, ultimoCargo));
		 * 
		 * }
		 */

		// CERTIFICACIÓN DE CUMPLIMIENTO PARA SER ELEGIDO DELEGADO CO-FT-176
		else if (tipoReporte.equalsIgnoreCase("176")) {
			request.getSession().setAttribute("nombreAsociado", nombreAspirante);
			request.getSession().setAttribute("numPlancha", numPlancha);
			request.getSession().setAttribute("nombreRepresen", nombreRespresenateComision);
			request.getSession().setAttribute("preguntas", listaPreguntas);

			String preguntas = "";
			for (PreguntasFormulario176DTO pregunta : listaPreguntas) {
				preguntas += pregunta.getPreguntas() + ":" + pregunta.getRespuesta() + ".";
			}

			// para guardar en base de datos los registros de los campos
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 1L, nombreAspirante));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 25L, numPlancha));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 31L, nombreRespresenateComision));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 62L, preguntas));
		}

		// CONSTANCIA DE RADICACIÓN Y RECIBO DE PLANCHAS CO-FT-208
		else if (tipoReporte.equalsIgnoreCase("208")) {
			request.getSession().setAttribute("zonaElectoral", numZonaElectroral);
			request.getSession().setAttribute("fecha", fechaElaboracionDoc);
			request.getSession().setAttribute("nombreAsociado", nombreCabezaPlancha);
			request.getSession().setAttribute("numComision", numComicion);
			request.getSession().setAttribute("ciudad", ciudadComicion);
			request.getSession().setAttribute("cedulaAsociado", numeroDocuemtoCabezaPlancha);
			request.getSession().setAttribute("ciudadCedula", zonaExpedicionDocumento);
			request.getSession().setAttribute("dia", fechaFirma != null ? String.valueOf(fechaFirma.getDate()) : "");
			request.getSession().setAttribute("mes", fechaFirma != null ? String.valueOf(fechaFirma.getMonth()) : "");
			request.getSession().setAttribute("anio", fechaFirma != null ? String.valueOf(fechaFirma.getYear()) : "");
			request.getSession().setAttribute("nombreEntrega", nombreEntrega);
			request.getSession().setAttribute("nombreRecibe", nombreRecibe);

			// para guardar en base de datos los registros de los campos
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 8L, zonaExpedicionDocumento));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 10L, nombreCabezaPlancha));
			listaRegCampos
					.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 13L, numeroDocuemtoCabezaPlancha));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 18L, numZonaElectroral));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 20L, numComicion));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 22L, ciudadComicion));
			listaRegCampos
					.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 23L, sdf.format(fechaElaboracionDoc)));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 50L, nombreRecibe));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 63L, sdf.format(fechaFirma)));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 64L, nombreEntrega));

		}

		// RESOLUCION INADMISION PLANCHA CO-FT-209
		else if (tipoReporte.equalsIgnoreCase("209")) {
			request.getSession().setAttribute("zonaElectoral", numZonaElectroral);
			request.getSession().setAttribute("dia",
					fechaElaboracionDoc != null ? String.valueOf(fechaElaboracionDoc.getDate()) : "");
			request.getSession().setAttribute("mes",
					fechaElaboracionDoc != null ? String.valueOf(fechaElaboracionDoc.getMonth()) : "");
			request.getSession().setAttribute("anio",
					fechaElaboracionDoc != null ? WorkStrigs.getAnio(fechaElaboracionDoc.getYear()) : "");
			request.getSession().setAttribute("hora", hora);
			//request.getSession().setAttribute("cedulaAsociado", numeroDocuemtoCabezaPlancha);
			request.getSession().setAttribute("nombreAsociado", nombreCabezaPlancha);
			request.getSession().setAttribute("resolucion", numResolucionCodigo);
			request.getSession().setAttribute("acta", numActa);
			request.getSession().setAttribute("fecha", fechaActa);
			request.getSession().setAttribute("ciudad", ciudad);
			request.getSession().setAttribute("razon1", razon1);
			request.getSession().setAttribute("razon2", razon2);
			request.getSession().setAttribute("razon3", razon3);
			request.getSession().setAttribute("razon4", razon4);

			// para guardar en base de datos los registros de los campos
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 10L, nombreCabezaPlancha));
			listaRegCampos
					.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 13L, numeroDocuemtoCabezaPlancha));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 18L, numZonaElectroral));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 21L, ciudad));
			listaRegCampos
					.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 23L, sdf.format(fechaElaboracionDoc)));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 24L, hora));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 35L, numResolucionCodigo));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 40L, numActa));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 41L, sdf.format(fechaActa)));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 65L, razon2));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 66L, razon3));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 67L, razon4));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 68L, razon1));

		}

		// INSCRIPCIÓN DE PLANCHAS CO-FT-210
		/*
		 * else if(tipoReporte.equalsIgnoreCase("210")) //( eliminado de la base de
		 * datos) { request.getSession().setAttribute("zonaElectoral",
		 * numZonaElectroral); //request.getSession().setAttribute("fecha");
		 * request.getSession().setAttribute("ciudad", ciudad); }
		 */

		// Declaración para acreditar ocupación y cumplimiento de requisitos CO-FT-211
		else if (tipoReporte.equalsIgnoreCase("211")) {
			if (fechaElaboracionDoc == null || fechaFirma == null) {
				this.mensajeVista.setVisible(Boolean.TRUE);
				this.mensajeVista
						.setMensaje("Señor Asociado, recuerde que la información de las fechas es obligatorio");
				return;
			}

			if (nombreAspirante == null || nombreAspirante.isEmpty()) {
				this.mensajeVista.setVisible(Boolean.TRUE);
				this.mensajeVista.setMensaje("Señor Asociado, recuerde que todos los campos son obligatorio");
				return;
			}

			if (numeroDocuemtoAspirante == null || numeroDocuemtoAspirante.isEmpty()) {
				this.mensajeVista.setVisible(Boolean.TRUE);
				this.mensajeVista.setMensaje("Señor Asociado, recuerde que todos los campos son obligatorio");
				return;
			}

			if (zonaExpedicionDocumento == null || zonaExpedicionDocumento.isEmpty()) {
				this.mensajeVista.setVisible(Boolean.TRUE);
				this.mensajeVista.setMensaje("Señor Asociado, recuerde que todos los campos son obligatorio");
				return;
			}

			if (ciudad == null || ciudad.isEmpty()) {
				this.mensajeVista.setVisible(Boolean.TRUE);
				this.mensajeVista.setMensaje("Señor Asociado, recuerde que todos los campos son obligatorio");
				return;
			}

			request.getSession().setAttribute("ciudad", ciudad);
			request.getSession().setAttribute("dia",
					fechaElaboracionDoc != null ? String.valueOf(fechaElaboracionDoc.getDate()) : "");
			request.getSession().setAttribute("mes",
					fechaElaboracionDoc != null ? String.valueOf(fechaElaboracionDoc.getMonth()) : "");
			request.getSession().setAttribute("anio",
					fechaElaboracionDoc != null ? WorkStrigs.getAnio(fechaElaboracionDoc.getYear()) : "");
			request.getSession().setAttribute("nombreAsociado", nombreAspirante);
			request.getSession().setAttribute("cedulaAsociado", numeroDocuemtoAspirante);
			request.getSession().setAttribute("ciudadCedula", zonaExpedicionDocumento);
			request.getSession().setAttribute("ciudadFirma", ciudad);
			request.getSession().setAttribute("diaFirma",
					fechaFirma != null ? String.valueOf(fechaFirma.getDate()) : "");
			request.getSession().setAttribute("mesFirma",
					fechaFirma != null ? WorkStrigs.getMes(fechaFirma.getMonth()) : "");
			request.getSession().setAttribute("anioFirma",
					fechaFirma != null ? WorkStrigs.getAnio(fechaFirma.getYear()) : "");

			// para guardar en base de datos los registros de los campos
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 1L, nombreAspirante));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 7L, numeroDocuemtoAspirante));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 8L, zonaExpedicionDocumento));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 21L, ciudad));
			listaRegCampos
					.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 23L, sdf.format(fechaElaboracionDoc)));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 63L, sdf.format(fechaFirma)));

		}

		// RESOLUCIÓN QUE RESUELVE UN RECURSO DE REPOSICIÓN
		// FAVORABLEMENTE CO-FT-458
		else if (tipoReporte.equalsIgnoreCase("458")) {

			request.getSession().setAttribute("zonaElectoral", numZonaElectroral);
			request.getSession().setAttribute("fecha", fechaElaboracionDoc);
			request.getSession().setAttribute("hora", hora);
			request.getSession().setAttribute("nombreAccionante", nombreAccionante);
			request.getSession().setAttribute("nombreComision", nombreComicion);
			request.getSession().setAttribute("resolucion_mod", numResolucionImpugnada);
			request.getSession().setAttribute("resolucion", numResolucionImpugnada);
			request.getSession().setAttribute("argumento", argReposicion);
			request.getSession().setAttribute("nombrePresidente", nombrePresidente);
			request.getSession().setAttribute("nombreSecretario", nombreSecretario);

			// para guardar en base de datos los registros de los campos
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 18L, numZonaElectroral));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 19L, nombreComicion));
			listaRegCampos
					.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 23L, sdf.format(fechaElaboracionDoc)));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 24L, hora));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 28L, numResolucionImpugnada));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 42L, nombrePresidente));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 44L, nombreSecretario));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 69L, desicion));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 70L, nombreAccionante));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 71L, argReposicion));

		}

		// RESOLUCIÓN QUE DENIEGA UN RECURSO DE REPOSICIÓN
		// Y NO CONCEDE APELACIÓN POR NO SER SOLICITADO CO-FT-459
		else if (tipoReporte.equalsIgnoreCase("459")) {
			request.getSession().setAttribute("zonaElectoral", numZonaElectroral);
			request.getSession().setAttribute("fecha", fechaElaboracionDoc);
			request.getSession().setAttribute("nombreComision", nombreComicion);
			request.getSession().setAttribute("nombreAsociado", nombreAspirante);
			request.getSession().setAttribute("resolucionImpugnada", numResolucionImpugnada);
			request.getSession().setAttribute("resolucion", numResolucionImpugnada);
			request.getSession().setAttribute("argumento", argApelacion);
			request.getSession().setAttribute("nombrePresidente", nombrePresidente);
			request.getSession().setAttribute("nombreSecretario", nombreSecretario);

			// para guardar en base de datos los registros de los campos
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 1L, nombreAspirante));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 18L, numZonaElectroral));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 19L, nombreComicion));
			listaRegCampos
					.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 23L, sdf.format(fechaElaboracionDoc)));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 28L, numResolucionImpugnada));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 42L, nombrePresidente));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 44L, nombreSecretario));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 54L, argApelacion));

		}

		// RESOLUCIÓN QUE RESUELVE UN RECURSO DE REPOSICIÓN
		// EN CONTRA Y REMITE LA APELACIÓN CO-FT-460
		else if (tipoReporte.equalsIgnoreCase("460")) {
			request.getSession().setAttribute("zonaElectoral", numZonaElectroral);
			request.getSession().setAttribute("fecha", fechaElaboracionDoc);
			request.getSession().setAttribute("nombreComision", nombreComicion);
			request.getSession().setAttribute("nombreAsociado", nombreAspirante);
			request.getSession().setAttribute("resolucionImpugnada", numResolucionImpugnada);
			request.getSession().setAttribute("resolucion", numResolucionImpugnada);
			request.getSession().setAttribute("argumento", argApelacion);
			request.getSession().setAttribute("nombrePresidente", nombrePresidente);
			request.getSession().setAttribute("nombreSecretario", nombreSecretario);

			// para guardar en base de datos los registros de los campos
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 1L, nombreAspirante));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 18L, numZonaElectroral));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 19L, nombreComicion));
			listaRegCampos
					.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 23L, sdf.format(fechaElaboracionDoc)));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 28L, numResolucionImpugnada));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 42L, nombrePresidente));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 44L, nombreSecretario));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 54L, argApelacion));

		}

		// RESOLUCIÓN QUE RESUELVE UN RECURSO DE
		// APELACIÓN FAVORABLE CO-FT-461
		else if (tipoReporte.equalsIgnoreCase("461")) {
			request.getSession().setAttribute("acta", numActa);
			request.getSession().setAttribute("fecha", fechaElaboracionDoc);
			request.getSession().setAttribute("nombreAsociado", nombreAspirante);
			request.getSession().setAttribute("resolucionApelada", numResolucionApelada);
			request.getSession().setAttribute("resolucionComision", numResolucionDecisionComision);
			request.getSession().setAttribute("actaTribunal", numActaTribunal);
			request.getSession().setAttribute("argumento", argApelacion);
			request.getSession().setAttribute("decision", desicion);
			request.getSession().setAttribute("nombrePresidente", nombrePresidente);
			request.getSession().setAttribute("nombreSecretario", nombreSecretario);

			// para guardar en base de datos los registros de los campos
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 1L, nombreAspirante));
			listaRegCampos
					.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 23L, sdf.format(fechaElaboracionDoc)));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 27L, numResolucionApelada));
			listaRegCampos
					.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 30L, numResolucionDecisionComision));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 39L, numActaTribunal));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 40L, numActa));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 42L, nombrePresidente));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 44L, nombreSecretario));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 54L, argApelacion));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 69L, desicion));

		}

		// RESOLUCIÓN QUE RESUELVE UN RECURSO DE
		// APELACIÓN EN CONTRA CO-FT-462
		else if (tipoReporte.equalsIgnoreCase("462")) {
			request.getSession().setAttribute("acta", numActa);
			request.getSession().setAttribute("fecha", fechaActa);
			request.getSession().setAttribute("nombreAsociado", nombreAspirante);
			request.getSession().setAttribute("resolucionApelada", numResolucionApelada);
			request.getSession().setAttribute("resolucionComision", numResolucionDecisionComision);
			request.getSession().setAttribute("actaTribunal", numActaTribunal);
			request.getSession().setAttribute("argumento", argApelacion);
			request.getSession().setAttribute("nombrePresidente", nombrePresidente);
			request.getSession().setAttribute("nombreSecretario", nombreSecretario);

			// para guardar en base de datos los registros de los campos
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 1L, nombreAspirante));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 27L, numResolucionApelada));
			listaRegCampos
					.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 30L, numResolucionDecisionComision));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 39L, numActaTribunal));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 40L, numActa));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 41L, sdf.format(fechaActa)));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 42L, nombrePresidente));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 44L, nombreSecretario));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 54L, argApelacion));
		}

		// RESOLUCIÓN QUE RESUELVE RECURSOS INTERPUESTOS EXTEMPORANEAMENTE CO-FT-753
		else if (tipoReporte.equalsIgnoreCase("753")) {
			request.getSession().setAttribute("zonaElectoral", numZonaElectroral);
			request.getSession().setAttribute("fecha", fechaElaboracionDoc);
			request.getSession().setAttribute("nombreAsociado", nombreCabezaPlancha);
			request.getSession().setAttribute("nombreComision", nombreComicion);
			request.getSession().setAttribute("resolucion", numResolucionExtemporaneamente);
			request.getSession().setAttribute("diaPresentado",
					fechaPresentacion != null ? String.valueOf(fechaPresentacion.getDate()) : "");
			request.getSession().setAttribute("dia", fechaFirma != null ? String.valueOf(fechaFirma.getDate()) : "");
			request.getSession().setAttribute("mes", fechaFirma != null ? String.valueOf(fechaFirma.getMonth()) : "");
			request.getSession().setAttribute("anio", fechaFirma != null ? String.valueOf(fechaFirma.getYear()) : "");

			// para guardar en base de datos los registros de los campos
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 10L, nombreCabezaPlancha));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 18L, numZonaElectroral));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 19L, nombreComicion));
			listaRegCampos
					.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 23L, sdf.format(fechaElaboracionDoc)));
			listaRegCampos
					.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 29L, numResolucionExtemporaneamente));
			listaRegCampos
					.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 60L, sdf.format(fechaPresentacion)));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 63L, sdf.format(fechaFirma)));

		}
		try {
			UserVo user = (UserVo) FacesUtils.getSessionParameter("user");
			DelegadoRegistroFormulario.getInstance().crearRegistroFormulario(Long.valueOf(tipoReporte), listaRegCampos,
					user.getUserId());
		} catch (Exception e) {
			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje(e.getMessage());

		}
		request.getSession().setAttribute("codigoReporte", tipoReporte);
		JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "ServletReportesJasper();");

		this.mensajeVista.setVisible(Boolean.TRUE);
		this.mensajeVista.setMensaje(
				"Señor Asociado, recuerde que este formato debe imprimirlo, firmarlo y entregarlo en las oficinas indicadas en la página web www.coomeva.com.co");

	}

	private void guardarRegistroFormulario(HttpServletRequest request) {

		Enumeration<String> listaAtributos = request.getAttributeNames();
		String atributo;
		while ((atributo = listaAtributos.nextElement()) != null) {
			System.out.print(atributo);
		}
	}

	public String limpiarCamposListener(ActionEvent accion) {
		accionLimpiarCampos();
		return "";
	}

	private void accionCargarCampos(String codFormulario) {
		try {
			List<EleCampo> listaCampo = DelegadoFormulario.getInstance()
					.listarCamposFormulario(Long.valueOf(codFormulario));

			if (codFormulario.equalsIgnoreCase("176")) {
				isReporte176 = true;
				verNumeroDocumentoAspirante = true;
			} else if (codFormulario.equalsIgnoreCase("211")) {
				esReporte211 = true;
				verNumeroDocumentoAspirante = true;

				fechaElaboracionDoc = new Date();
				fechaFirma = new Date();
			}
			if (listaCampo != null && !codFormulario.isEmpty()) {
				for (EleCampo eleCampo : listaCampo) {

					if (eleCampo.getNombre().trim().equalsIgnoreCase("nombreAspirante")) {
						verNombreAspirante = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("firmaCandidato")) {
						verFirmaCadidato = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("fotoCabezaPlancha")) {
						verFotoCabezaPlancha = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("profesionApirante")) {
						verProfesionAspirante = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("profesionCabezaPlancha")) {
						verProfesionCabezaPlancha = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("tipoIdentAspirante")) {
						verTipoIdentAspirante = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("numeroDocuemtoAspirante")) {
						verNumeroDocumentoAspirante = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("zonaExpedicionDocumento")) {
						verZonaExpedicionDocumento = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("fechaExpedicionDocumento")) {
						verFechaExpedicionDocumento = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("nombreCabezaPlancha")) {
						verNombreCabezaPlancha = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("firmaCabezaPlancha")) {
						verFirmaCabezaPlancha = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("tipoIdentCabezaPlancha")) {
						verTipoIdentCabezaPlancha = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("numeroDocuemtoCabezaPlancha")) {
						verNumeroDocumentoCabezaPlancha = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("nombreIncribePlancha")) {
						verNombreIncribePlancha = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("firmaIncribePlancha")) {
						verFirmaIncribePlancha = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("tipoIdentIncribePlancha")) {
						verTipoIdentIncribePlancha = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("numeroDocuemtoIncribePlancha")) {
						verNumeroDocumentoIncribePlancha = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("numZonaElectroral")) {
						verNumZonaElectroral = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("nombreComicion")) {
						verNombreComicion = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("numComicion")) {
						verNumComicion = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("ciudad")) {
						verCiudad = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("ciudadComicion")) {
						verCiudadComicion = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("fechaElaboracionDoc")) {
						verFechaElaboracionDoc = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("hora")) {
						verHora = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("numPlancha")) {
						verNumPlancha = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("numResolucionRechazoComision")) {
						verNumResolucionRechazoComision = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("numResolucionApelada")) {
						verNumResolucionApelada = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("numResolucionImpugnada")) {
						verNumResolucionImpugnada = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("numResolucionExtemporaneamente")) {
						verNumResolucionExtemporaneamente = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("numResolucionDecisionComision")) {
						verNumResolucionDecisionComision = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("nombreRespresenateComision")) {
						verNombreRespresenateComision = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("firmaRespresenateComision")) {
						verFirmaRespresenateComision = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("numResolucionAdmisionPlancha")) {
						verNumResolucionAdmisionPlancha = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("numResolucionInadmisionPlancha")) {
						verNumResolucionInadmisionPlancha = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("numResolucionCodigo")) {
						verNumResolucionCodigo = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("fechaResolucionCodigo")) {
						verFechaResolucionCodigo = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("numResolucionReglamento")) {
						verNumResolucionReglamento = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("fechaResolucionReglamento")) {
						verFechaResolucionReglamento = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("numActaTribunal")) {
						verNumActaTribunal = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("numActa")) {
						verNumActa = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("fechaActa")) {
						verFechaActa = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("nombrePresidente")) {
						verNombrePresidente = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("firmaPresidente")) {
						verFirmaPresidente = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("nombreSecretario")) {
						verNombreSecretario = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("firmaSecretario")) {
						verFirmSecretario = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("firmaEntrega")) {
						verFirmEntrega = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("tipoDocEntrega")) {
						verTipoDocEntrega = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("numDocEntrega")) {
						verNumDocEntrega = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("anioSuspension")) {
						verAnioSuspension = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("nombreRecibe")) {
						verNombreRecibe = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("firmaRecibe")) {
						verFirmRecibe = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("tipoDocRecibe")) {
						verTipoDocRecibe = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("numDocRecibe")) {
						verNumDocRecibe = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("argApelacion")) {
						verArgApelacion = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("antiguedad")) {
						verAntiguedad = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("anioExpedicionTitulo")) {
						verAnioExpedicionTitulo = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("otrosEstudios")) {
						verOtrosEstudios = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("experienciaLaboral")) {
						verExperienciaLaboral = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("ultimoCargo")) {
						verultimoCargo = true;

					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("fechaPresentacion")) {
						verFechaPresentacion = true;
					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("razones")) {
						verRazones = true;
					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("nombreEntrega")) {
						verNombreEntrega = true;
					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("razon1")) {
						verRazon1 = true;
					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("razon2")) {
						verRazon2 = true;
					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("razon3")) {
						verRazon3 = true;
					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("razon4")) {
						verRazon4 = true;
					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("fechaFirma")) {
						verFechaFirma = true;
					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("desicion")) {
						verDesicion = true;
					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("nombreAccionante")) {
						verNombreAccionante = true;
					} else if (eleCampo.getNombre().trim().equalsIgnoreCase("argReposicion")) {
						verArgReposicion = true;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void accionLimpiarCampos() {
		nombreAspirante = "";
		verNombreAspirante = false;

		firmaCandidato = "";
		verFirmaCadidato = false;

		fotoCabezaPlancha = null;
		verFotoCabezaPlancha = false;

		profesionApirante = "";
		verProfesionAspirante = false;

		profesionCabezaPlancha = "";
		verProfesionCabezaPlancha = false;

		tipoIdentAspirante = "";
		verTipoIdentAspirante = false;

		numeroDocuemtoAspirante = "";
		verNumeroDocumentoAspirante = false;

		zonaExpedicionDocumento = "";
		verZonaExpedicionDocumento = false;

		fechaExpedicionDocumento = null;
		verFechaExpedicionDocumento = false;

		nombreCabezaPlancha = "";
		verNombreCabezaPlancha = false;

		firmaCabezaPlancha = "";
		verFirmaCabezaPlancha = false;

		tipoIdentCabezaPlancha = "";
		verTipoIdentCabezaPlancha = false;

		numeroDocuemtoCabezaPlancha = "";
		verNumeroDocumentoCabezaPlancha = false;

		nombreIncribePlancha = "";
		verNombreIncribePlancha = false;

		firmaIncribePlancha = "";
		verFirmaIncribePlancha = false;

		tipoIdentIncribePlancha = "";
		verTipoIdentIncribePlancha = false;

		numeroDocuemtoIncribePlancha = "";
		verNumeroDocumentoIncribePlancha = false;

		numZonaElectroral = "";
		verNumZonaElectroral = false;

		nombreComicion = "";
		verNombreComicion = false;

		numComicion = "";
		verNumComicion = false;

		ciudad = "";
		verCiudad = false;

		ciudadComicion = "";
		verCiudadComicion = false;

		fechaElaboracionDoc = null;
		verFechaElaboracionDoc = false;

		hora = "";
		verHora = false;

		numPlancha = "";
		verNumPlancha = false;

		numResolucionRechazoComision = "";
		verNumResolucionRechazoComision = false;

		numResolucionApelada = "";
		verNumResolucionApelada = false;

		numResolucionImpugnada = "";
		verNumResolucionImpugnada = false;

		numResolucionExtemporaneamente = "";
		verNumResolucionExtemporaneamente = false;

		numResolucionDecisionComision = "";
		verNumResolucionDecisionComision = false;

		nombreRespresenateComision = "";
		verNombreRespresenateComision = false;

		firmaRespresenateComision = "";
		verFirmaRespresenateComision = false;

		numResolucionAdmisionPlancha = "";
		verNumResolucionAdmisionPlancha = false;

		numResolucionInadmisionPlancha = "";
		verNumResolucionInadmisionPlancha = false;

		numResolucionCodigo = "";
		verNumResolucionCodigo = false;

		fechaResolucionCodigo = null;
		verFechaResolucionCodigo = false;

		numResolucionReglamento = "";
		verNumResolucionReglamento = false;

		fechaResolucionReglamento = null;
		verFechaResolucionReglamento = false;

		numActaTribunal = "";
		verNumActaTribunal = false;

		numActa = "";
		verNumActa = false;

		fechaActa = null;
		verFechaActa = false;

		nombrePresidente = "";
		verNombrePresidente = false;

		firmaPresidente = "";
		verFirmaPresidente = false;

		nombreSecretario = "";
		verNombreSecretario = false;

		firmaSecretario = "";
		verFirmSecretario = false;

		firmaEntrega = "";
		verFirmEntrega = false;

		tipoDocEntrega = "";
		verTipoDocEntrega = false;

		numDocEntrega = "";
		verNumDocEntrega = false;

		anioSuspension = "";
		verAnioSuspension = false;

		nombreRecibe = "";
		verNombreRecibe = false;

		firmaRecibe = "";
		verFirmRecibe = false;

		tipoDocRecibe = "";
		verTipoDocRecibe = false;

		numDocRecibe = "";
		verNumDocRecibe = false;

		argApelacion = "";
		verArgApelacion = false;

		antiguedad = "";
		verAntiguedad = false;

		anioExpedicionTitulo = "";
		verAnioExpedicionTitulo = false;

		otrosEstudios = "";
		verOtrosEstudios = false;

		experienciaLaboral = "";
		verExperienciaLaboral = false;

		ultimoCargo = "";
		verultimoCargo = false;

		fechaPresentacion = null;
		verFechaPresentacion = false;

		razones = "";
		verRazones = false;

		nombreEntrega = "";
		verNombreEntrega = false;

		nombreAccionante = "";
		verNombreAccionante = false;

		argReposicion = "";
		verArgReposicion = false;

		razon1 = "";
		verRazon1 = false;
		razon2 = "";
		verRazon2 = false;
		razon3 = "";
		verRazon3 = false;
		razon4 = "";
		verRazon4 = false;
		fechaFirma = null;
		verFechaFirma = false;

		desicion = "";
		verDesicion = false;
		isReporte176 = false;
		esReporte211 = false;
		listaPreguntas = null;

		tipoReporte = "-1";
	}

	public String getNombreAspirante() {
		return nombreAspirante;
	}

	public void setNombreAspirante(String nombreAspirante) {
		this.nombreAspirante = nombreAspirante;
	}

	public boolean isVerNombreAspirante() {
		return verNombreAspirante;
	}

	public void setVerNombreAspirante(boolean verNombreAspirante) {
		this.verNombreAspirante = verNombreAspirante;
	}

	public String getFirmaCandidato() {
		return firmaCandidato;
	}

	public void setFirmaCandidato(String firmaCandidato) {
		this.firmaCandidato = firmaCandidato;
	}

	public boolean isVerFirmaCadidato() {
		return verFirmaCadidato;
	}

	public void setVerFirmaCadidato(boolean verFirmaCadidato) {
		this.verFirmaCadidato = verFirmaCadidato;
	}

	public File getFotoCabezaPlancha() {
		return fotoCabezaPlancha;
	}

	public void setFotoCabezaPlancha(File fotoCabezaPlancha) {
		this.fotoCabezaPlancha = fotoCabezaPlancha;
	}

	public boolean isVerFotoCabezaPlancha() {
		return verFotoCabezaPlancha;
	}

	public void setVerFotoCabezaPlancha(boolean verFotoCabezaPlancha) {
		this.verFotoCabezaPlancha = verFotoCabezaPlancha;
	}

	public String getProfesionApirante() {
		return profesionApirante;
	}

	public void setProfesionApirante(String profesionApirante) {
		this.profesionApirante = profesionApirante;
	}

	public boolean isVerProfesionAspirante() {
		return verProfesionAspirante;
	}

	public void setVerProfesionAspirante(boolean verProfesionAspirante) {
		this.verProfesionAspirante = verProfesionAspirante;
	}

	public String getProfesionCabezaPlancha() {
		return profesionCabezaPlancha;
	}

	public void setProfesionCabezaPlancha(String profesionCabezaPlancha) {
		this.profesionCabezaPlancha = profesionCabezaPlancha;
	}

	public boolean isVerProfesionCabezaPlancha() {
		return verProfesionCabezaPlancha;
	}

	public void setVerProfesionCabezaPlancha(boolean verProfesionCabezaPlancha) {
		this.verProfesionCabezaPlancha = verProfesionCabezaPlancha;
	}

	public String getTipoIdentAspirante() {
		return tipoIdentAspirante;
	}

	public void setTipoIdentAspirante(String tipoIdentAspirante) {
		this.tipoIdentAspirante = tipoIdentAspirante;
	}

	public boolean isVerTipoIdentAspirante() {
		return verTipoIdentAspirante;
	}

	public void setVerTipoIdentAspirante(boolean verTipoIdentAspirante) {
		this.verTipoIdentAspirante = verTipoIdentAspirante;
	}

	public String getNumeroDocuemtoAspirante() {
		return numeroDocuemtoAspirante;
	}

	public void setNumeroDocuemtoAspirante(String numeroDocuemtoAspirante) {
		this.numeroDocuemtoAspirante = numeroDocuemtoAspirante;
	}

	public boolean isVerNumeroDocumentoAspirante() {
		return verNumeroDocumentoAspirante;
	}

	public void setVerNumeroDocumentoAspirante(boolean verNumeroDocumentoAspirante) {
		this.verNumeroDocumentoAspirante = verNumeroDocumentoAspirante;
	}

	public String getZonaExpedicionDocumento() {
		return zonaExpedicionDocumento;
	}

	public void setZonaExpedicionDocumento(String zonaExpedicionDocumento) {
		this.zonaExpedicionDocumento = zonaExpedicionDocumento;
	}

	public boolean isVerZonaExpedicionDocumento() {
		return verZonaExpedicionDocumento;
	}

	public void setVerZonaExpedicionDocumento(boolean verZonaExpedicionDocumento) {
		this.verZonaExpedicionDocumento = verZonaExpedicionDocumento;
	}

	public Date getFechaExpedicionDocumento() {
		return fechaExpedicionDocumento;
	}

	public void setFechaExpedicionDocumento(Date fechaExpedicionDocumento) {
		this.fechaExpedicionDocumento = fechaExpedicionDocumento;
	}

	public boolean isVerFechaExpedicionDocumento() {
		return verFechaExpedicionDocumento;
	}

	public void setVerFechaExpedicionDocumento(boolean verFechaExpedicionDocumento) {
		this.verFechaExpedicionDocumento = verFechaExpedicionDocumento;
	}

	public String getNombreCabezaPlancha() {
		return nombreCabezaPlancha;
	}

	public void setNombreCabezaPlancha(String nombreCabezaPlancha) {
		this.nombreCabezaPlancha = nombreCabezaPlancha;
	}

	public boolean isVerNombreCabezaPlancha() {
		return verNombreCabezaPlancha;
	}

	public void setVerNombreCabezaPlancha(boolean verNombreCabezaPlancha) {
		this.verNombreCabezaPlancha = verNombreCabezaPlancha;
	}

	public String getFirmaCabezaPlancha() {
		return firmaCabezaPlancha;
	}

	public void setFirmaCabezaPlancha(String firmaCabezaPlancha) {
		this.firmaCabezaPlancha = firmaCabezaPlancha;
	}

	public boolean isVerFirmaCabezaPlancha() {
		return verFirmaCabezaPlancha;
	}

	public void setVerFirmaCabezaPlancha(boolean verFirmaCabezaPlancha) {
		this.verFirmaCabezaPlancha = verFirmaCabezaPlancha;
	}

	public String getTipoIdentCabezaPlancha() {
		return tipoIdentCabezaPlancha;
	}

	public void setTipoIdentCabezaPlancha(String tipoIdentCabezaPlancha) {
		this.tipoIdentCabezaPlancha = tipoIdentCabezaPlancha;
	}

	public boolean isVerTipoIdentCabezaPlancha() {
		return verTipoIdentCabezaPlancha;
	}

	public void setVerTipoIdentCabezaPlancha(boolean verTipoIdentCabezaPlancha) {
		this.verTipoIdentCabezaPlancha = verTipoIdentCabezaPlancha;
	}

	public String getNumeroDocuemtoCabezaPlancha() {
		return numeroDocuemtoCabezaPlancha;
	}

	public void setNumeroDocuemtoCabezaPlancha(String numeroDocuemtoCabezaPlancha) {
		this.numeroDocuemtoCabezaPlancha = numeroDocuemtoCabezaPlancha;
	}

	public boolean isVerNumeroDocumentoCabezaPlancha() {
		return verNumeroDocumentoCabezaPlancha;
	}

	public void setVerNumeroDocumentoCabezaPlancha(boolean verNumeroDocumentoCabezaPlancha) {
		this.verNumeroDocumentoCabezaPlancha = verNumeroDocumentoCabezaPlancha;
	}

	public String getNombreIncribePlancha() {
		return nombreIncribePlancha;
	}

	public void setNombreIncribePlancha(String nombreIncribePlancha) {
		this.nombreIncribePlancha = nombreIncribePlancha;
	}

	public boolean isVerNombreIncribePlancha() {
		return verNombreIncribePlancha;
	}

	public void setVerNombreIncribePlancha(boolean verNombreIncribePlancha) {
		this.verNombreIncribePlancha = verNombreIncribePlancha;
	}

	public String getFirmaIncribePlancha() {
		return firmaIncribePlancha;
	}

	public void setFirmaIncribePlancha(String firmaIncribePlancha) {
		this.firmaIncribePlancha = firmaIncribePlancha;
	}

	public boolean isVerFirmaIncribePlancha() {
		return verFirmaIncribePlancha;
	}

	public void setVerFirmaIncribePlancha(boolean verFirmaIncribePlancha) {
		this.verFirmaIncribePlancha = verFirmaIncribePlancha;
	}

	public String getTipoIdentIncribePlancha() {
		return tipoIdentIncribePlancha;
	}

	public void setTipoIdentIncribePlancha(String tipoIdentIncribePlancha) {
		this.tipoIdentIncribePlancha = tipoIdentIncribePlancha;
	}

	public boolean isVerTipoIdentIncribePlancha() {
		return verTipoIdentIncribePlancha;
	}

	public void setVerTipoIdentIncribePlancha(boolean verTipoIdentIncribePlancha) {
		this.verTipoIdentIncribePlancha = verTipoIdentIncribePlancha;
	}

	public String getNumeroDocuemtoIncribePlancha() {
		return numeroDocuemtoIncribePlancha;
	}

	public void setNumeroDocuemtoIncribePlancha(String numeroDocuemtoIncribePlancha) {
		this.numeroDocuemtoIncribePlancha = numeroDocuemtoIncribePlancha;
	}

	public boolean isVerNumeroDocumentoIncribePlancha() {
		return verNumeroDocumentoIncribePlancha;
	}

	public void setVerNumeroDocumentoIncribePlancha(boolean verNumeroDocumentoIncribePlancha) {
		this.verNumeroDocumentoIncribePlancha = verNumeroDocumentoIncribePlancha;
	}

	public String getNumZonaElectroral() {
		return numZonaElectroral;
	}

	public void setNumZonaElectroral(String numZonaElectroral) {
		this.numZonaElectroral = numZonaElectroral;
	}

	public boolean isVerNumZonaElectroral() {
		return verNumZonaElectroral;
	}

	public void setVerNumZonaElectroral(boolean verNumZonaElectroral) {
		this.verNumZonaElectroral = verNumZonaElectroral;
	}

	public String getNombreComicion() {
		return nombreComicion;
	}

	public void setNombreComicion(String nombreComicion) {
		this.nombreComicion = nombreComicion;
	}

	public boolean isVerNombreComicion() {
		return verNombreComicion;
	}

	public void setVerNombreComicion(boolean verNombreComicion) {
		this.verNombreComicion = verNombreComicion;
	}

	public String getNumComicion() {
		return numComicion;
	}

	public void setNumComicion(String numComicion) {
		this.numComicion = numComicion;
	}

	public boolean isVerNumComicion() {
		return verNumComicion;
	}

	public void setVerNumComicion(boolean verNumComicion) {
		this.verNumComicion = verNumComicion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public boolean isVerCiudad() {
		return verCiudad;
	}

	public void setVerCiudad(boolean verCiudad) {
		this.verCiudad = verCiudad;
	}

	public String getCiudadComicion() {
		return ciudadComicion;
	}

	public void setCiudadComicion(String ciudadComicion) {
		this.ciudadComicion = ciudadComicion;
	}

	public boolean isVerCiudadComicion() {
		return verCiudadComicion;
	}

	public void setVerCiudadComicion(boolean verCiudadComicion) {
		this.verCiudadComicion = verCiudadComicion;
	}

	public Date getFechaElaboracionDoc() {
		return fechaElaboracionDoc;
	}

	public void setFechaElaboracionDoc(Date fechaElaboracionDoc) {
		this.fechaElaboracionDoc = fechaElaboracionDoc;
	}

	public boolean isVerFechaElaboracionDoc() {
		return verFechaElaboracionDoc;
	}

	public void setVerFechaElaboracionDoc(boolean verFechaElaboracionDoc) {
		this.verFechaElaboracionDoc = verFechaElaboracionDoc;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public boolean isVerHora() {
		return verHora;
	}

	public void setVerHora(boolean verHora) {
		this.verHora = verHora;
	}

	public String getNumPlancha() {
		return numPlancha;
	}

	public void setNumPlancha(String numPlancha) {
		this.numPlancha = numPlancha;
	}

	public boolean isVerNumPlancha() {
		return verNumPlancha;
	}

	public void setVerNumPlancha(boolean verNumPlancha) {
		this.verNumPlancha = verNumPlancha;
	}

	public String getNumResolucionRechazoComision() {
		return numResolucionRechazoComision;
	}

	public void setNumResolucionRechazoComision(String numResolucionRechazoComision) {
		this.numResolucionRechazoComision = numResolucionRechazoComision;
	}

	public boolean isVerNumResolucionRechazoComision() {
		return verNumResolucionRechazoComision;
	}

	public void setVerNumResolucionRechazoComision(boolean verNumResolucionRechazoComision) {
		this.verNumResolucionRechazoComision = verNumResolucionRechazoComision;
	}

	public String getNumResolucionApelada() {
		return numResolucionApelada;
	}

	public void setNumResolucionApelada(String numResolucionApelada) {
		this.numResolucionApelada = numResolucionApelada;
	}

	public boolean isVerNumResolucionApelada() {
		return verNumResolucionApelada;
	}

	public void setVerNumResolucionApelada(boolean verNumResolucionApelada) {
		this.verNumResolucionApelada = verNumResolucionApelada;
	}

	public String getNumResolucionImpugnada() {
		return numResolucionImpugnada;
	}

	public void setNumResolucionImpugnada(String numResolucionImpugnada) {
		this.numResolucionImpugnada = numResolucionImpugnada;
	}

	public boolean isVerNumResolucionImpugnada() {
		return verNumResolucionImpugnada;
	}

	public void setVerNumResolucionImpugnada(boolean verNumResolucionImpugnada) {
		this.verNumResolucionImpugnada = verNumResolucionImpugnada;
	}

	public String getNumResolucionExtemporaneamente() {
		return numResolucionExtemporaneamente;
	}

	public void setNumResolucionExtemporaneamente(String numResolucionExtemporaneamente) {
		this.numResolucionExtemporaneamente = numResolucionExtemporaneamente;
	}

	public boolean isVerNumResolucionExtemporaneamente() {
		return verNumResolucionExtemporaneamente;
	}

	public void setVerNumResolucionExtemporaneamente(boolean verNumResolucionExtemporaneamente) {
		this.verNumResolucionExtemporaneamente = verNumResolucionExtemporaneamente;
	}

	public String getNumResolucionDecisionComision() {
		return numResolucionDecisionComision;
	}

	public void setNumResolucionDecisionComision(String numResolucionDecisionComision) {
		this.numResolucionDecisionComision = numResolucionDecisionComision;
	}

	public boolean isVerNumResolucionDecisionComision() {
		return verNumResolucionDecisionComision;
	}

	public void setVerNumResolucionDecisionComision(boolean verNumResolucionDecisionComision) {
		this.verNumResolucionDecisionComision = verNumResolucionDecisionComision;
	}

	public String getNombreRespresenateComision() {
		return nombreRespresenateComision;
	}

	public void setNombreRespresenateComision(String nombreRespresenateComision) {
		this.nombreRespresenateComision = nombreRespresenateComision;
	}

	public boolean isVerNombreRespresenateComision() {
		return verNombreRespresenateComision;
	}

	public void setVerNombreRespresenateComision(boolean verNombreRespresenateComision) {
		this.verNombreRespresenateComision = verNombreRespresenateComision;
	}

	public String getFirmaRespresenateComision() {
		return firmaRespresenateComision;
	}

	public void setFirmaRespresenateComision(String firmaRespresenateComision) {
		this.firmaRespresenateComision = firmaRespresenateComision;
	}

	public boolean isVerFirmaRespresenateComision() {
		return verFirmaRespresenateComision;
	}

	public void setVerFirmaRespresenateComision(boolean verFirmaRespresenateComision) {
		this.verFirmaRespresenateComision = verFirmaRespresenateComision;
	}

	public String getNumResolucionAdmisionPlancha() {
		return numResolucionAdmisionPlancha;
	}

	public void setNumResolucionAdmisionPlancha(String numResolucionAdmisionPlancha) {
		this.numResolucionAdmisionPlancha = numResolucionAdmisionPlancha;
	}

	public boolean isVerNumResolucionAdmisionPlancha() {
		return verNumResolucionAdmisionPlancha;
	}

	public void setVerNumResolucionAdmisionPlancha(boolean verNumResolucionAdmisionPlancha) {
		this.verNumResolucionAdmisionPlancha = verNumResolucionAdmisionPlancha;
	}

	public String getNumResolucionInadmisionPlancha() {
		return numResolucionInadmisionPlancha;
	}

	public void setNumResolucionInadmisionPlancha(String numResolucionInadmisionPlancha) {
		this.numResolucionInadmisionPlancha = numResolucionInadmisionPlancha;
	}

	public boolean isVerNumResolucionInadmisionPlancha() {
		return verNumResolucionInadmisionPlancha;
	}

	public void setVerNumResolucionInadmisionPlancha(boolean verNumResolucionInadmisionPlancha) {
		this.verNumResolucionInadmisionPlancha = verNumResolucionInadmisionPlancha;
	}

	public String getNumResolucionCodigo() {
		return numResolucionCodigo;
	}

	public void setNumResolucionCodigo(String numResolucionCodigo) {
		this.numResolucionCodigo = numResolucionCodigo;
	}

	public boolean isVerNumResolucionCodigo() {
		return verNumResolucionCodigo;
	}

	public void setVerNumResolucionCodigo(boolean verNumResolucionCodigo) {
		this.verNumResolucionCodigo = verNumResolucionCodigo;
	}

	public Date getFechaResolucionCodigo() {
		return fechaResolucionCodigo;
	}

	public void setFechaResolucionCodigo(Date fechaResolucionCodigo) {
		this.fechaResolucionCodigo = fechaResolucionCodigo;
	}

	public boolean isVerFechaResolucionCodigo() {
		return verFechaResolucionCodigo;
	}

	public void setVerFechaResolucionCodigo(boolean verFechaResolucionCodigo) {
		this.verFechaResolucionCodigo = verFechaResolucionCodigo;
	}

	public String getNumResolucionReglamento() {
		return numResolucionReglamento;
	}

	public void setNumResolucionReglamento(String numResolucionReglamento) {
		this.numResolucionReglamento = numResolucionReglamento;
	}

	public boolean isVerNumResolucionReglamento() {
		return verNumResolucionReglamento;
	}

	public void setVerNumResolucionReglamento(boolean verNumResolucionReglamento) {
		this.verNumResolucionReglamento = verNumResolucionReglamento;
	}

	public Date getFechaResolucionReglamento() {
		return fechaResolucionReglamento;
	}

	public void setFechaResolucionReglamento(Date fechaResolucionReglamento) {
		this.fechaResolucionReglamento = fechaResolucionReglamento;
	}

	public boolean isVerFechaResolucionReglamento() {
		return verFechaResolucionReglamento;
	}

	public void setVerFechaResolucionReglamento(boolean verFechaResolucionReglamento) {
		this.verFechaResolucionReglamento = verFechaResolucionReglamento;
	}

	public String getNumActaTribunal() {
		return numActaTribunal;
	}

	public void setNumActaTribunal(String numActaTribunal) {
		this.numActaTribunal = numActaTribunal;
	}

	public boolean isVerNumActaTribunal() {
		return verNumActaTribunal;
	}

	public void setVerNumActaTribunal(boolean verNumActaTribunal) {
		this.verNumActaTribunal = verNumActaTribunal;
	}

	public String getNumActa() {
		return numActa;
	}

	public void setNumActa(String numActa) {
		this.numActa = numActa;
	}

	public boolean isVerNumActa() {
		return verNumActa;
	}

	public void setVerNumActa(boolean verNumActa) {
		this.verNumActa = verNumActa;
	}

	public Date getFechaActa() {
		return fechaActa;
	}

	public void setFechaActa(Date fechaActa) {
		this.fechaActa = fechaActa;
	}

	public boolean isVerFechaActa() {
		return verFechaActa;
	}

	public void setVerFechaActa(boolean verFechaActa) {
		this.verFechaActa = verFechaActa;
	}

	public String getNombrePresidente() {
		return nombrePresidente;
	}

	public void setNombrePresidente(String nombrePresidente) {
		this.nombrePresidente = nombrePresidente;
	}

	public boolean isVerNombrePresidente() {
		return verNombrePresidente;
	}

	public void setVerNombrePresidente(boolean verNombrePresidente) {
		this.verNombrePresidente = verNombrePresidente;
	}

	public String getFirmaPresidente() {
		return firmaPresidente;
	}

	public void setFirmaPresidente(String firmaPresidente) {
		this.firmaPresidente = firmaPresidente;
	}

	public boolean isVerFirmaPresidente() {
		return verFirmaPresidente;
	}

	public void setVerFirmaPresidente(boolean verFirmaPresidente) {
		this.verFirmaPresidente = verFirmaPresidente;
	}

	public String getNombreSecretario() {
		return nombreSecretario;
	}

	public void setNombreSecretario(String nombreSecretario) {
		this.nombreSecretario = nombreSecretario;
	}

	public boolean isVerNombreSecretario() {
		return verNombreSecretario;
	}

	public void setVerNombreSecretario(boolean verNombreSecretario) {
		this.verNombreSecretario = verNombreSecretario;
	}

	public String getFirmaSecretario() {
		return firmaSecretario;
	}

	public void setFirmaSecretario(String firmaSecretario) {
		this.firmaSecretario = firmaSecretario;
	}

	public boolean isVerFirmSecretario() {
		return verFirmSecretario;
	}

	public void setVerFirmSecretario(boolean verFirmSecretario) {
		this.verFirmSecretario = verFirmSecretario;
	}

	public String getFirmaEntrega() {
		return firmaEntrega;
	}

	public void setFirmaEntrega(String firmaEntrega) {
		this.firmaEntrega = firmaEntrega;
	}

	public boolean isVerFirmEntrega() {
		return verFirmEntrega;
	}

	public void setVerFirmEntrega(boolean verFirmEntrega) {
		this.verFirmEntrega = verFirmEntrega;
	}

	public String getTipoDocEntrega() {
		return tipoDocEntrega;
	}

	public void setTipoDocEntrega(String tipoDocEntrega) {
		this.tipoDocEntrega = tipoDocEntrega;
	}

	public boolean isVerTipoDocEntrega() {
		return verTipoDocEntrega;
	}

	public void setVerTipoDocEntrega(boolean verTipoDocEntrega) {
		this.verTipoDocEntrega = verTipoDocEntrega;
	}

	public String getNumDocEntrega() {
		return numDocEntrega;
	}

	public void setNumDocEntrega(String numDocEntrega) {
		this.numDocEntrega = numDocEntrega;
	}

	public boolean isVerNumDocEntrega() {
		return verNumDocEntrega;
	}

	public void setVerNumDocEntrega(boolean verNumDocEntrega) {
		this.verNumDocEntrega = verNumDocEntrega;
	}

	public String getAnioSuspension() {
		return anioSuspension;
	}

	public void setAnioSuspension(String anioSuspension) {
		this.anioSuspension = anioSuspension;
	}

	public boolean isVerAnioSuspension() {
		return verAnioSuspension;
	}

	public void setVerAnioSuspension(boolean verAnioSuspension) {
		this.verAnioSuspension = verAnioSuspension;
	}

	public String getNombreRecibe() {
		return nombreRecibe;
	}

	public void setNombreRecibe(String nombreRecibe) {
		this.nombreRecibe = nombreRecibe;
	}

	public boolean isVerNombreRecibe() {
		return verNombreRecibe;
	}

	public void setVerNombreRecibe(boolean verNombreRecibe) {
		this.verNombreRecibe = verNombreRecibe;
	}

	public String getFirmaRecibe() {
		return firmaRecibe;
	}

	public void setFirmaRecibe(String firmaRecibe) {
		this.firmaRecibe = firmaRecibe;
	}

	public boolean isVerFirmRecibe() {
		return verFirmRecibe;
	}

	public void setVerFirmRecibe(boolean verFirmRecibe) {
		this.verFirmRecibe = verFirmRecibe;
	}

	public String getTipoDocRecibe() {
		return tipoDocRecibe;
	}

	public void setTipoDocRecibe(String tipoDocRecibe) {
		this.tipoDocRecibe = tipoDocRecibe;
	}

	public boolean isVerTipoDocRecibe() {
		return verTipoDocRecibe;
	}

	public void setVerTipoDocRecibe(boolean verTipoDocRecibe) {
		this.verTipoDocRecibe = verTipoDocRecibe;
	}

	public String getNumDocRecibe() {
		return numDocRecibe;
	}

	public void setNumDocRecibe(String numDocRecibe) {
		this.numDocRecibe = numDocRecibe;
	}

	public boolean isVerNumDocRecibe() {
		return verNumDocRecibe;
	}

	public void setVerNumDocRecibe(boolean verNumDocRecibe) {
		this.verNumDocRecibe = verNumDocRecibe;
	}

	public String getArgApelacion() {
		return argApelacion;
	}

	public void setArgApelacion(String argApelacion) {
		this.argApelacion = argApelacion;
	}

	public boolean isVerArgApelacion() {
		return verArgApelacion;
	}

	public void setVerArgApelacion(boolean verArgApelacion) {
		this.verArgApelacion = verArgApelacion;
	}

	public String getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(String antiguedad) {
		this.antiguedad = antiguedad;
	}

	public boolean isVerAntiguedad() {
		return verAntiguedad;
	}

	public void setVerAntiguedad(boolean verAntiguedad) {
		this.verAntiguedad = verAntiguedad;
	}

	public String getAnioExpedicionTitulo() {
		return anioExpedicionTitulo;
	}

	public void setAnioExpedicionTitulo(String anioExpedicionTitulo) {
		this.anioExpedicionTitulo = anioExpedicionTitulo;
	}

	public boolean isVerAnioExpedicionTitulo() {
		return verAnioExpedicionTitulo;
	}

	public void setVerAnioExpedicionTitulo(boolean verAnioExpedicionTitulo) {
		this.verAnioExpedicionTitulo = verAnioExpedicionTitulo;
	}

	public String getOtrosEstudios() {
		return otrosEstudios;
	}

	public void setOtrosEstudios(String otrosEstudios) {
		this.otrosEstudios = otrosEstudios;
	}

	public boolean isVerOtrosEstudios() {
		return verOtrosEstudios;
	}

	public void setVerOtrosEstudios(boolean verOtrosEstudios) {
		this.verOtrosEstudios = verOtrosEstudios;
	}

	public String getExperienciaLaboral() {
		return experienciaLaboral;
	}

	public void setExperienciaLaboral(String experienciaLaboral) {
		this.experienciaLaboral = experienciaLaboral;
	}

	public boolean isVerExperienciaLaboral() {
		return verExperienciaLaboral;
	}

	public void setVerExperienciaLaboral(boolean verExperienciaLaboral) {
		this.verExperienciaLaboral = verExperienciaLaboral;
	}

	public String getUltimoCargo() {
		return ultimoCargo;
	}

	public void setUltimoCargo(String ultimoCargo) {
		this.ultimoCargo = ultimoCargo;
	}

	public boolean isVerultimoCargo() {
		return verultimoCargo;
	}

	public void setVerultimoCargo(boolean verultimoCargo) {
		this.verultimoCargo = verultimoCargo;
	}

	public Date getFechaPresentacion() {
		return fechaPresentacion;
	}

	public void setFechaPresentacion(Date fechaPresentacion) {
		this.fechaPresentacion = fechaPresentacion;
	}

	public boolean isVerFechaPresentacion() {
		return verFechaPresentacion;
	}

	public void setVerFechaPresentacion(boolean verFechaPresentacion) {
		this.verFechaPresentacion = verFechaPresentacion;
	}

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public List<SelectItem> getListaFormulario() {
		try {
			List<EleFormulario> listaFormularios = null;

			if (listaFormulario == null) {
				listaFormulario = new ArrayList<SelectItem>();

				listaFormularios = DelegadoFormulario.getInstance().listarFormularios();
				if (listaFormularios != null) {
					SelectItem selectItem = new SelectItem(-1, "-seleccione-");
					listaFormulario.add(selectItem);
					for (EleFormulario eleFormulario : listaFormularios) {

						if (!eleFormulario.getCodFormulario().equals(176L)) {
							selectItem = new SelectItem(eleFormulario.getCodFormulario(),
									eleFormulario.getDescripcion());
							listaFormulario.add(selectItem);

						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listaFormulario;
	}

	public List<SelectItem> getListaFormularioComite() {
		try {
			List<EleFormulario> listaFormularios = null;

			if (listaFormulario == null) {
				listaFormulario = new ArrayList<SelectItem>();

				listaFormularios = DelegadoFormulario.getInstance().listarFormularios();
				if (listaFormularios != null) {
					SelectItem selectItem = new SelectItem(-1, "-seleccione-");
					listaFormulario.add(selectItem);
					for (EleFormulario eleFormulario : listaFormularios) {

						if (eleFormulario.getCodFormulario().equals(176L)) {
							selectItem = new SelectItem(eleFormulario.getCodFormulario(),
									eleFormulario.getDescripcion());
							listaFormulario.add(selectItem);
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listaFormulario;
	}

	public void setListaFormulario(List<SelectItem> listaFormulario) {
		this.listaFormulario = listaFormulario;
	}

	public String getRazones() {
		return razones;
	}

	public void setRazones(String razones) {
		this.razones = razones;
	}

	public boolean isVerRazones() {
		return verRazones;
	}

	public void setVerRazones(boolean verRazones) {
		this.verRazones = verRazones;
	}

	public String getNombreEntrega() {
		return nombreEntrega;
	}

	public void setNombreEntrega(String nombreEntrega) {
		this.nombreEntrega = nombreEntrega;
	}

	public boolean isVerNombreEntrega() {
		return verNombreEntrega;
	}

	public void setVerNombreEntrega(boolean verNombreEntrega) {
		this.verNombreEntrega = verNombreEntrega;
	}

	public String getRazon1() {
		return razon1;
	}

	public void setRazon1(String razon1) {
		this.razon1 = razon1;
	}

	public boolean isVerRazon1() {
		return verRazon1;
	}

	public void setVerRazon1(boolean verRazon1) {
		this.verRazon1 = verRazon1;
	}

	public String getRazon2() {
		return razon2;
	}

	public void setRazon2(String razon2) {
		this.razon2 = razon2;
	}

	public boolean isVerRazon2() {
		return verRazon2;
	}

	public void setVerRazon2(boolean verRazon2) {
		this.verRazon2 = verRazon2;
	}

	public String getRazon3() {
		return razon3;
	}

	public void setRazon3(String razon3) {
		this.razon3 = razon3;
	}

	public boolean isVerRazon3() {
		return verRazon3;
	}

	public void setVerRazon3(boolean verRazon3) {
		this.verRazon3 = verRazon3;
	}

	public String getRazon4() {
		return razon4;
	}

	public void setRazon4(String razon4) {
		this.razon4 = razon4;
	}

	public boolean isVerRazon4() {
		return verRazon4;
	}

	public void setVerRazon4(boolean verRazon4) {
		this.verRazon4 = verRazon4;
	}

	public Date getFechaFirma() {
		return fechaFirma;
	}

	public void setFechaFirma(Date fechaFirma) {
		this.fechaFirma = fechaFirma;
	}

	public boolean isVerFechaFirma() {
		return verFechaFirma;
	}

	public void setVerFechaFirma(boolean verFechaFirma) {
		this.verFechaFirma = verFechaFirma;
	}

	public String getDesicion() {
		return desicion;
	}

	public void setDesicion(String desicion) {
		this.desicion = desicion;
	}

	public boolean isVerDesicion() {
		return verDesicion;
	}

	public void setVerDesicion(boolean verDesicion) {
		this.verDesicion = verDesicion;
	}

	public boolean getIsReporte176() {
		return isReporte176;
	}

	public void setIsReporte176(boolean isReporte176) {
		this.isReporte176 = isReporte176;
	}

	public List<PreguntasFormulario176DTO> getListaPreguntas() {
		if (listaPreguntas == null) {
			listaPreguntas = PreguntasFormulario176Util.crearFormulario();
		}
		return listaPreguntas;
	}

	public void setListaPreguntas(List<PreguntasFormulario176DTO> listaPreguntas) {
		this.listaPreguntas = listaPreguntas;
	}

	public List<SelectItem> getListRespuesta() {

		if (listRespuesta == null) {
			listRespuesta = new ArrayList<SelectItem>();
			listRespuesta.add(new SelectItem(1, "SI"));
			listRespuesta.add(new SelectItem(0, "NO"));
		}
		return listRespuesta;
	}

	public void setListRespuesta(List<SelectItem> listRespuesta) {
		this.listRespuesta = listRespuesta;
	}

	public boolean getEsReporte211() {
		return esReporte211;
	}

	public String getNombreAccionante() {
		return nombreAccionante;
	}

	public void setNombreAccionante(String nombreAccionante) {
		this.nombreAccionante = nombreAccionante;
	}

	public boolean isVerNombreAccionante() {
		return verNombreAccionante;
	}

	public void setVerNombreAccionante(boolean verNombreAccionante) {
		this.verNombreAccionante = verNombreAccionante;
	}

	public String getArgReposicion() {
		return argReposicion;
	}

	public void setArgReposicion(String argReposicion) {
		this.argReposicion = argReposicion;
	}

	public boolean isVerArgReposicion() {
		return verArgReposicion;
	}

	public void setVerArgReposicion(boolean verArgReposicion) {
		this.verArgReposicion = verArgReposicion;
	}

}