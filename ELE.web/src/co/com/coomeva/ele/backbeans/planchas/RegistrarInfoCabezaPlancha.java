package co.com.coomeva.ele.backbeans.planchas;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.util.TimeZone;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.xerces.impl.dv.util.Base64;
import co.com.coomeva.ele.backbeans.BaseVista;
import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.delegado.DelegadoCabezaPlancha;
import co.com.coomeva.ele.delegado.DelegadoFormatoPlanchas;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoPlancha;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoZonaElectoral;
import co.com.coomeva.ele.dto.DTOInformacionPlancha;
import co.com.coomeva.ele.dto.DTOMiembroPlancha;
import co.com.coomeva.ele.dto.InfoDetalleFormatoPlanchaDTO;
import co.com.coomeva.ele.dto.InformacionCabezaPlanchaDTO;
import co.com.coomeva.ele.dto.InputFileDataDTO;
import co.com.coomeva.ele.entidades.habilidad.EleAsociado;
import co.com.coomeva.ele.entidades.habilidad.dao.EleAsociadoDAO;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleDetalleFormatoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFotoFormatoPlancha;
import co.com.coomeva.ele.logica.LogicaAsociado;
import co.com.coomeva.ele.modelo.EleAsociadoDatosDTO;
import co.com.coomeva.ele.modelo.ParametroPlanchaDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.ele.util.InputFileData;
import co.com.coomeva.ele.util.LectorParametros;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.ManipulacionFechas;
import co.com.coomeva.util.resources.LoaderResourceElements;

import com.icesoft.faces.component.inputfile.FileInfo;
import com.icesoft.faces.component.inputfile.InputFile;
import com.icesoft.faces.context.effects.JavascriptContext;

public class RegistrarInfoCabezaPlancha extends BaseVista {

	private InformacionCabezaPlanchaDTO dto;
	private InfoDetalleFormatoPlanchaDTO dtoInfo = new InfoDetalleFormatoPlanchaDTO();
	private LoaderResourceElements loaderResourceElements = LoaderResourceElements.getInstance();
	private InputFileDataDTO currentFile;

	private Boolean mensajeIngreso = false;
	private Boolean mensajeInformativo = false;

	private String confirmacionFechaGrado = "";

	private boolean visibleConfirmar;

	private boolean visible = false;
	private boolean disabledGuardar = false;
	private boolean disabledFinalizar = true;
	private boolean disabledImprimir = true;
	private String mensajeIngresoInfoCabezaPlancha = "";
	private String imageToBase64String;

	/** Variable de sesion id cabeza de plancha **/
	Long numintCabezaPlancha = null;
	Long numeroDocumentoCabezaPlancha = null;
	Long numeroDocumentoAsociado = null;
	Long consecutivoPlancha = null;

	/** Variables para el suplente cabeza de placha; */
	boolean esSuplente = false;
	boolean esCabezaPlancha = false;
	Long numintSuplenteCabezaPlancha = null;

	private int fileProgress;
	private List<InputFileDataDTO> fileList = null;

	boolean modificarRegistro = false;

	Long CODIGO_FORMATO_INSCRIPCION_CABEZA_PLANCHA = new Long(
			UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_FORMATO_INSCRIPCION_PLANCHA));
	String CODIGO_ESTADO_DETALLE_FORMATO_PLANCHA_FINALIZADA = new String(
			UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_ESTADO_DETALLE_FORMATO_PLANCHA_FINALIZADA));

	private boolean esUsuarioComision;// Verifica si el usuario de la sesión es
	// de la comisión
	private boolean esSaneamiento;

	private String lblPanelInfoCabezaPlancha;

	private boolean visibleConsultarSuplente;

	private String mensajeExito;
	private boolean visiblemensajeExito;

	public RegistrarInfoCabezaPlancha() {
		esUsuarioComision = FacesUtils.getSessionParameter("userComision") != null;
		action_mostrar_mensaje_informativo();
		this.esSaneamiento = validarSaneamiento();
	}

	public boolean validarSaneamiento() {
		Date dateToday = new Date();
		// Fechas saneamiento para que se pueda modificar planchas
		ParametroPlanchaDTO parametroFechaInicial = LectorParametros.obtenerParametrosCodigoTipo(
				UtilAcceso.getParametroFuenteL("parametros", "campo.param.saneamiento.inicio"),
				UtilAcceso.getParametroFuenteL("parametros", "campo.param.saneamiento.tipo"));
		ParametroPlanchaDTO parametroFechaFinal = LectorParametros.obtenerParametrosCodigoTipo(
				UtilAcceso.getParametroFuenteL("parametros", "campo.param.saneamiento.fin"),
				UtilAcceso.getParametroFuenteL("parametros", "campo.param.saneamiento.tipo"));

		Date dateFechaIniSaneamiento = ManipulacionFechas.stringToDate(parametroFechaInicial.getStrValor(),
				"yyyy-MM-dd hh:mm:ss");
		Date dateFechaFinSaneamiento = ManipulacionFechas.stringToDate(parametroFechaFinal.getStrValor(),
				"yyyy-MM-dd hh:mm:ss");

		return dateToday.getTime() >= dateFechaIniSaneamiento.getTime()
				&& dateToday.getTime() <= dateFechaFinSaneamiento.getTime();
	}

	public void validacionInicial() {
		try {
			if (FacesUtils.getSessionParameter("numeroDocAsociado") != null) {
				numeroDocumentoAsociado = (Long) FacesUtils.getSessionParameter("numeroDocAsociado");
				Long[] resultado = DelegadoAsociado.getInstance()
						.consultaCabezaPlanchaPorInscrito(numeroDocumentoAsociado);
				numintCabezaPlancha = resultado[0];
				consecutivoPlancha = resultado[1];
				numeroDocumentoCabezaPlancha = resultado[2];
				Long numeroImpresionesPorFormato = DelegadoAsociado.getInstance()
						.consultaNumeroImpresionesFormatoPLancha(consecutivoPlancha);

				Boolean existeRegistro = DelegadoCabezaPlancha.getInstance().validarRegistroDetalleFormatoPlancha(
						numeroDocumentoAsociado, CODIGO_FORMATO_INSCRIPCION_CABEZA_PLANCHA);
				if (numintCabezaPlancha != null
						&& (numeroImpresionesPorFormato != null && numeroImpresionesPorFormato > 0)
						&& !existeRegistro) {
					consultarInformacionCabezaPlancha();
				} else {
					action_mostrar_mensaje_ingreso();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			action_mostrar_mensaje_ingreso();
		}
	}

	public String consultarInformacionCabezaPlancha() throws Exception {
		try {
//			limpiarCampos();
			/**
			 * Si el numero del asociado en sesion es igual al numero del cambeza de plancha
			 * se consulta la informacion del cabeza de plancha.
			 * 
			 * En caso contrario se consulta la informacion de la persona en sesion.
			 */
			if (numeroDocumentoAsociado.equals(numeroDocumentoCabezaPlancha)) {
				dto = DelegadoCabezaPlancha.getInstance()
						.consultarInformacionCabezaPlancha(numeroDocumentoCabezaPlancha);
				esSuplente = false;
				esCabezaPlancha = true;
			} else {
				dto = DelegadoCabezaPlancha.getInstance().consultarInformacionCabezaPlancha(numeroDocumentoAsociado);

				if (dto == null) {
					action_mostrar_mensaje_ingreso();
					return "";
				}
				DTOInformacionPlancha infoPlancha = DelegadoPlancha.getInstance()
						.consultarInformacionPlancha(consecutivoPlancha);

				List<DTOMiembroPlancha> miembrosSuplentes = infoPlancha.getMiembrosSuplentes();

				if (miembrosSuplentes != null) {
					for (DTOMiembroPlancha dtoMiembroPlancha : miembrosSuplentes) {
						if (dtoMiembroPlancha.getNumeroDocumento().equals(numeroDocumentoAsociado)
								&& dtoMiembroPlancha.getPosicionPlancha().equalsIgnoreCase("1")) {
							esSuplente = true;
							esCabezaPlancha = false;
							break;
						}
					}
				}

				if (!esSuplente) {
					action_mostrar_mensaje_ingreso();
					return "";
				} else {
					EleAsociadoDatosDTO asociadoDTO = DelegadoAsociado.getInstance()
							.consultarInformacionBasicaAsociado(numeroDocumentoAsociado);

					if (asociadoDTO != null) {
						numintSuplenteCabezaPlancha = Long.valueOf(asociadoDTO.getAsonumint());
					}
				}
			}
			//consulta zona electoral 
			String nombre_zona_elec = dto.getZonaElectoralEspecial() != null
					? DelegadoZonaElectoral.getInstance()
							.consultarZonaElectoralEspecialByCodigo(Long.parseLong(dto.getZonaElectoralEspecial()))
					: DelegadoZonaElectoral.getInstance()
							.consultarZonaElectoralByCodigo(Long.parseLong(dto.getZonaElectoral()));
			
			dto.setZonaElectoral(
					dto.getZonaElectoral() + ((nombre_zona_elec != null) ? " - " + nombre_zona_elec : ""));
			// se verifica si la instancia dto tiene profesion
			if (dto.getProfesion() == null) { 
				List<EleAsociado> listaAsociado = new EleAsociadoDAO().findByProperty("numeroDocumento",
						numeroDocumentoAsociado);
				for (EleAsociado asociado : listaAsociado) {
					if (asociado.getNumeroDocumento().longValue() == numeroDocumentoAsociado.longValue()
							&& asociado.getDescProfesion() != null) {
						dto.setProfesion(asociado.getDescProfesion());
						break;
					}
				}
			}
			/*
			 * Long fechaGradoLong = DelegadoCabezaPlancha.getInstance()
			 * .obtieneFechaGradoAsociado(new Long(dto.getCodAsociado())); if
			 * (fechaGradoLong != null && fechaGradoLong != 0L) {
			 * 
			 * String anno = fechaGradoLong.toString().substring(0, 4); String mes =
			 * fechaGradoLong.toString().substring(4, 6); String dia =
			 * fechaGradoLong.toString().substring(6, 8);
			 * 
			 * // Date fechaGradoAsoBUC = //
			 * DateManipultate.stringToDate(fechaGradoLong.toString(), // "yyyyMMdd"); //
			 * dto.setFechaGrado(fechaGradoAso.toString()); dto.setFechaGrado(anno + "/" +
			 * mes + "/" + dia); }
			 */

			EleDetalleFormatoPlancha entity = null;
			EleFotoFormatoPlancha fotoPlancha = null;
			if (esSuplente) { 
				entity = DelegadoCabezaPlancha.getInstance()
						.consultarDetalleFormatoPlanchaPorId(numintSuplenteCabezaPlancha);
				fotoPlancha = DelegadoCabezaPlancha.getInstance()
						.consultarFotoCabezaPlancha(numintSuplenteCabezaPlancha);

				lblPanelInfoCabezaPlancha = "Informaci\u00F3n cabeza de plancha Suplente";
			} else {
				entity = DelegadoCabezaPlancha.getInstance().consultarDetalleFormatoPlanchaPorId(numintCabezaPlancha);
				fotoPlancha = DelegadoCabezaPlancha.getInstance().consultarFotoCabezaPlancha(numintCabezaPlancha);

				lblPanelInfoCabezaPlancha = "Informaci\u00F3n cabeza de plancha Principal";
			}

			if (entity != null) {
				dtoInfo.setCargoActual(entity.getCargoActual());
				dtoInfo.setEmpresaActual(entity.getEmpresaActual());
				if (esSuplente) {
					dtoInfo.setNumeroDocumento(numintSuplenteCabezaPlancha);
				} else {
					dtoInfo.setNumeroDocumento(numintCabezaPlancha);
				}

				dtoInfo.setOtrosEstudios(entity.getOtrosEstudios());
				dtoInfo.setUltimoCargoDirectivoCoomeva(entity.getUltimoCargoCoomeva());
				dtoInfo.setOtrosEstudiosDos(entity.getOtrosEstudiosDos());
				dtoInfo.setAntigLaboral(entity.getAntigLaboral());
				dtoInfo.setPerfilCandidatoUno(entity.getPerfilCandidatoUno());
				dtoInfo.setPerfilCandidatoDos(null);
				dtoInfo.setCuentaPersonalMail(entity.getCuentaPersonalMail());
				dtoInfo.setCuentaPersonalTwitter(entity.getCuentaPersonalTwitter());
				dtoInfo.setCuentaPersonalFacebook(entity.getCuentaPersonalFacebook());
				dtoInfo.setEstado(entity.getEstado());
				dtoInfo.setFechaGrado(entity.getFechaGradoMod());
				dtoInfo.setNumeroActa(entity.getNumeroActa());
				if (fotoPlancha != null) {
					this.imageToBase64String = fotoPlancha.getFotoAsociado();
				}
				// Si los detalles del formato no se pueden modificar:
				if (dtoInfo.getEstado() != null
						&& dtoInfo.getEstado().equals(CODIGO_ESTADO_DETALLE_FORMATO_PLANCHA_FINALIZADA)) {
					this.disabledFinalizar = true;
					this.disabledGuardar = true;
					this.disabledImprimir = false;
				} else {
					// Si los datos del formato se pueden modificar:
					this.disabledFinalizar = false;
					this.disabledGuardar = false;
					this.disabledImprimir = false;
				}
				modificarRegistro = true;

				// Si la plancha está radicada no se puede modificar:
				if (consecutivoPlancha != null
						&& DelegadoFormatoPlanchas.getInstance().esPlanchaRadicada(consecutivoPlancha)) {
					this.disabledGuardar = true;
				}

				// Si es usuario de la comisión puede hacer modificaciones en fechas
				// de saneamiento:
				if (esUsuarioComision && esSaneamiento) {
					this.disabledGuardar = false;
				}

			} else {
				modificarRegistro = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "";
	}

	public void actionConsultarSuplente(ActionEvent evento) {
		try {
			limpiarCampos();
			consultarInformacionCabezaPlancha();
			esCabezaPlancha = true;
			visibleConsultarSuplente = false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void action_mostrarConsulta() {
		visibleConsultarSuplente = true;
	}

	private void limpiarCampos() {
		dto = new InformacionCabezaPlanchaDTO();
		dtoInfo = new InfoDetalleFormatoPlanchaDTO();
		esSuplente = false;
		visibleConsultarSuplente = false;
	}

	public String action_guardar() {
		try {
			dtoInfo.setNumeroDocumento(new Long(dto.getNumeroDocumento()));

			if (numeroDocumentoAsociado.longValue() != numeroDocumentoCabezaPlancha.longValue()) {
				if (!esSuplente) {
					throw new Exception(
							"Estimado asociado, Solo el cabeza de plancha principal y el cabeza de plancha Suplente pueden registrar esta información.");
				}
			}

			if (dto.getProfesion() == null) {
				if (dtoInfo.getFechaGrado() == null) {
					throw new Exception(loaderResourceElements.getKeyResourceValue(
							ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
							"campo.obligatorio.fecha.estudios"));
				} else {
					String correo = LogicaAsociado.getInstance().consultarCorreoAsociadoPorId(numeroDocumentoAsociado);
					dto.setProfesion(correo);
				}
			}

			if (modificarRegistro) {
				DelegadoCabezaPlancha.getInstance().modificarInformacionCabezaPlancha(dtoInfo,
						this.imageToBase64String);
			} else {
				DelegadoCabezaPlancha.getInstance().guardarInformacionCabezaPlancha(dtoInfo, this.imageToBase64String);
				modificarRegistro = true;
			}
			mensajeExito = "La información del asociado cabeza de plancha se guardó satisfactoriamente ";
			this.visiblemensajeExito = true;
			this.disabledFinalizar = false;
			this.disabledImprimir = false;

			// getMensaje().mostrarMensaje("Se guardó satisfactoriamente la información del
			// asociado"
			// +
			// " cabeza de plancha");
		} catch (Exception e) {

			mensajeIngresoInfoCabezaPlancha = e.getMessage();
			this.mensajeInformativo = true;
			// getMensaje().mostrarMensaje(e.getMessage());
			/*
			 * mensaje2 = e.getMessage(); this.visible = true;
			 */
		}
		return "";
	}

	public String action_finalizar_enviar() {
		try {
			dtoInfo.setNumeroDocumento(new Long(dto.getNumeroDocumento()));
			if (numeroDocumentoAsociado.longValue() != numeroDocumentoCabezaPlancha.longValue()) {
				throw new Exception("Estimado asociado, Solo el cabeza de plancha puede registrar esta información.");
			}
			DelegadoCabezaPlancha.getInstance().finalizarEnviarInformacionCabezaPlancha(dtoInfo,
					this.imageToBase64String);
			mensajeIngresoInfoCabezaPlancha = "Finalizó el registro de la información del asociado cabeza de plancha. Por favor imprima.";
			this.mensajeInformativo = true;
			this.disabledGuardar = true;
			this.disabledFinalizar = true;
			// getMensaje().mostrarMensaje("Finalizó el registro de la información del
			// asociado cabeza de plancha. Por favor imprima.");
		} catch (Exception e) {
			// getMensaje().mostrarMensaje(e.getMessage());
			mensajeIngresoInfoCabezaPlancha = e.getMessage();
			this.mensajeInformativo = true;
		}
		return "";
	}

	public String action_imprimir_formato() {
		try {
			dtoInfo.setNumeroDocumento(new Long(dto.getNumeroDocumento()));
			DelegadoCabezaPlancha.getInstance().imprimirEnviarInformacionCabezaPlancha(dtoInfo, consecutivoPlancha,
					this.imageToBase64String);
//			Object[] params = new Object[3];
//			params[0] = this.numeroDocumentoCabezaPlancha;
//			params[1] = this.numintCabezaPlancha;
//			params[2] = this.imageToBase64String;

//			formatoPdfInfoCabezaPlancha.generarReporte(params);

			String realpath = FacesUtils.getServletContext().getRealPath("gui");
			String dirDestino = UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"param.url.ubicacion.plantillas");
			File imageDecoding = null;
			if (imageToBase64String != null) {
				byte[] bytearray = Base64.decode(imageToBase64String);
				BufferedImage imag = ImageIO.read(new ByteArrayInputStream(bytearray));
				imageDecoding = new File(realpath + dirDestino, "foto" + numeroDocumentoCabezaPlancha + ".jpg");
				int index = imageDecoding.getName().lastIndexOf('.');
				String extensionFoto = imageDecoding.getName().substring(index + 1);
				ImageIO.write(imag, extensionFoto, imageDecoding);
			}

			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();

			request.getSession().setAttribute("codigoReporte", "174");
			//request.getSession().setAttribute("plancha", dto.getNumeroPlancha());
			request.getSession().setAttribute("plancha", null);
			request.getSession().setAttribute("zonaElectoral", dto.getZonaElectoral());
			request.getSession().setAttribute("nombreAsociado", dto.getNombreCompleto());
			request.getSession().setAttribute("cedulaAsociado", String.valueOf(numeroDocumentoAsociado));
			request.getSession().setAttribute("fechaAntiguedad", dto.getAntiguedadMeses());
			request.getSession().setAttribute("profesion", dto.getProfesion());
			request.getSession().setAttribute("fechaTitulo", dtoInfo.getFechaGrado());
			request.getSession().setAttribute("estudios", dtoInfo.getOtrosEstudios());
			request.getSession().setAttribute("empresa", dtoInfo.getEmpresaActual());
			request.getSession().setAttribute("cargo", dtoInfo.getCargoActual());
			request.getSession().setAttribute("antiguedad", String.valueOf(dtoInfo.getAntigLaboral()));
			request.getSession().setAttribute("ultimoCargo", dtoInfo.getUltimoCargoDirectivoCoomeva());
			request.getSession().setAttribute("foto", String.valueOf(numeroDocumentoCabezaPlancha));
			request.getSession().setAttribute("esSuplente", new Boolean(esSuplente));

			JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "ServletReportesJasper();");

			this.visiblemensajeExito = true;
			this.mensajeExito = "Señor Asociado, recuerde que este formato debe imprimirlo, firmarlo y entregarlo en las oficinas indicadas en la página web www.coomeva.com.co";

		} catch (Exception e) {
			// getMensaje().mostrarMensaje(e.getMessage());
			mensajeIngresoInfoCabezaPlancha = e.getMessage();
			this.mensajeInformativo = true;

		}
		return "";
	}

	public void actionFileUploadProgress(EventObject event) {
		InputFile ifile = (InputFile) event.getSource();
		this.fileProgress = ifile.getFileInfo().getPercent();
	}

	public void actionUploadFile(ActionEvent event) {
		InputFile inputFile = (InputFile) event.getSource();
		FileInfo fileInfo = inputFile.getFileInfo();
		String tipoContenido = fileInfo.getContentType();
		if (!InputFileData.validarArchivoImagen(tipoContenido)) {
			fileInfo.setStatus(FileInfo.INVALID_CONTENT_TYPE);
		}
		if (fileInfo.getStatus() == FileInfo.INVALID_CONTENT_TYPE) {
			mensajeIngresoInfoCabezaPlancha = "Solo Se permiten imagenes.";
			this.mensajeInformativo = true;
		}
		if (fileInfo.getStatus() == FileInfo.SAVED) {
			this.currentFile = new InputFileDataDTO(fileInfo);
			// Antes de usar cualquier cosa limpiamos los componentes para
			// realizar los Uploads
			this.fileList = Collections.synchronizedList(new ArrayList<InputFileDataDTO>());
			this.imageProcess(currentFile);
		}
		if (fileInfo.getStatus() == FileInfo.SIZE_LIMIT_EXCEEDED) {
			mensajeIngresoInfoCabezaPlancha = "El archivo excede el tamaño máximo permitido.";
			this.mensajeInformativo = true;
		}
	}

	private void imageProcess(InputFileDataDTO inputFile) {
		try {
			File foto = inputFile.getFile();
			ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
			BufferedImage img = ImageIO.read(foto);
			int index = foto.getName().lastIndexOf('.');
			String extensionFoto = foto.getName().substring(index + 1);
			ImageIO.write(img, extensionFoto, baos);
			baos.flush();
			this.imageToBase64String = Base64.encode(baos.toByteArray());
			baos.close();
		} catch (IOException e) {
			mensajeIngresoInfoCabezaPlancha = e.getMessage();
			this.mensajeInformativo = true;
		}
	}

	public String action_mostrar_mensaje_informativo() {
		mensajeIngresoInfoCabezaPlancha = "Estimado asociado, recuerde que este formato debe ser diligenciado únicamente por el cabeza de plancha o el suplente cabeza de plancha. Se dará por terminado el proceso de diligenciamiento del formulario cuando se expida el formato de Constancia de Radicación y Recibo";
		mensajeInformativo = true;
		return "";
	}

	public void action_cerrar_mensaje_informativo() {
		mensajeInformativo = false;
		validacionInicial();
	}

	public String action_mostrar_mensaje_ingreso() {
		mensajeIngreso = true;
		return "";
	}

	public void action_cerrar_mensaje_exito() {
		mensajeExito = "";
		visiblemensajeExito = false;
	}

	public String action_cerrar_mensaje_ingreso() {
		mensajeIngreso = false;
		return "goInicioMenuAsociado";
	}

	public InformacionCabezaPlanchaDTO getDto() {
		return dto;
	}

	public void setDto(InformacionCabezaPlanchaDTO dto) {
		this.dto = dto;
	}

	public InfoDetalleFormatoPlanchaDTO getDtoInfo() {
		return dtoInfo;
	}

	public void setDtoInfo(InfoDetalleFormatoPlanchaDTO dtoInfo) {
		this.dtoInfo = dtoInfo;
	}

	public boolean isVisibleConfirmar() {
		return visibleConfirmar;
	}

	public void setVisibleConfirmar(boolean visibleConfirmar) {
		this.visibleConfirmar = visibleConfirmar;
	}

	public String action_verConfirmacion() {
		visibleConfirmar = true;
		return "";
	}

	public String action_closeConfirmar() {
		visibleConfirmar = false;
		return "";
	}

	public Boolean getMensajeIngreso() {
		return mensajeIngreso;
	}

	public void setMensajeIngreso(Boolean mensajeIngreso) {
		this.mensajeIngreso = mensajeIngreso;
	}

	public Boolean getMensajeInformativo() {
		return mensajeInformativo;
	}

	public void setMensajeInformativo(Boolean mensajeInformativo) {
		this.mensajeInformativo = mensajeInformativo;
	}

	public TimeZone getTimeZone() {
		return java.util.TimeZone.getDefault();
	}

	public String getConfirmacionFechaGrado() {
		return confirmacionFechaGrado;
	}

	public void setConfirmacionFechaGrado(String confirmacionFechaGrado) {
		this.confirmacionFechaGrado = confirmacionFechaGrado;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getMensajeIngresoInfoCabezaPlancha() {
		return mensajeIngresoInfoCabezaPlancha;
	}

	public void setMensajeIngresoInfoCabezaPlancha(String mensajeIngresoInfoCabezaPlancha) {
		this.mensajeIngresoInfoCabezaPlancha = mensajeIngresoInfoCabezaPlancha;
	}

	public int getFileProgress() {
		return fileProgress;
	}

	public void setFileProgress(int fileProgress) {
		this.fileProgress = fileProgress;
	}

	public List<InputFileDataDTO> getFileList() {
		return fileList;
	}

	public void setFileList(List<InputFileDataDTO> fileList) {
		this.fileList = fileList;
	}

	public InputFileDataDTO getCurrentFile() {
		return currentFile;
	}

	public void setCurrentFile(InputFileDataDTO currentFile) {
		this.currentFile = currentFile;
	}

	public String getImageToBase64String() {
		return imageToBase64String;
	}

	public void setImageToBase64String(String imageToBase64String) {
		this.imageToBase64String = imageToBase64String;
	}

	public boolean isDisabledGuardar() {
		return disabledGuardar;
	}

	public void setDisabledGuardar(boolean disabledGuardar) {
		this.disabledGuardar = disabledGuardar;
	}

	public boolean isDisabledFinalizar() {
		return disabledFinalizar;
	}

	public void setDisabledFinalizar(boolean disabledFinalizar) {
		this.disabledFinalizar = disabledFinalizar;
	}

	public boolean isDisabledImprimir() {
		return disabledImprimir;
	}

	public void setDisabledImprimir(boolean disabledImprimir) {
		this.disabledImprimir = disabledImprimir;
	}

	public boolean isEsSuplente() {
		return esSuplente;
	}

	public void setEsSuplente(boolean esSuplente) {
		this.esSuplente = esSuplente;
	}

	public boolean isEsCabezaPlancha() {
		return esCabezaPlancha;
	}

	public void setEsCabezaPlancha(boolean esCabezaPlancha) {
		this.esCabezaPlancha = esCabezaPlancha;
	}

	public Long getNumeroDocumentoAsociado() {
		return numeroDocumentoAsociado;
	}

	public void setNumeroDocumentoAsociado(Long numeroDocumentoAsociado) {
		this.numeroDocumentoAsociado = numeroDocumentoAsociado;
	}

	public String getLblPanelInfoCabezaPlancha() {
		return lblPanelInfoCabezaPlancha;
	}

	public void setLblPanelInfoCabezaPlancha(String lblPanelInfoCabezaPlancha) {
		this.lblPanelInfoCabezaPlancha = lblPanelInfoCabezaPlancha;
	}

	public boolean isVisibleConsultarSuplente() {
		return visibleConsultarSuplente;
	}

	public void setVisibleConsultarSuplente(boolean visibleConsultarSuplente) {
		this.visibleConsultarSuplente = visibleConsultarSuplente;
	}

	public String getMensajeExito() {
		return mensajeExito;
	}

	public void setMensajeExito(String mensajeExito) {
		this.mensajeExito = mensajeExito;
	}

	public boolean isVisiblemensajeExito() {
		return visiblemensajeExito;
	}

	public void setVisiblemensajeExito(boolean visiblemensajeExito) {
		this.visiblemensajeExito = visiblemensajeExito;
	}
}
