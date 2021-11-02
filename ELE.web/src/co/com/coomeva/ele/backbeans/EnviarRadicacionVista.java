package co.com.coomeva.ele.backbeans;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.hibernate.Transaction;

import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.delegado.DelegadoCabezaPlancha;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoEnvioRadicacion;
import co.com.coomeva.ele.dto.DTOHabilidadAsociado;
import co.com.coomeva.ele.dto.DTOInformacionPlancha;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.planchas.ElePlanchas;
import co.com.coomeva.ele.entidades.planchas.dao.EleDetalleFormatoPlanchaDAO;
import co.com.coomeva.ele.entidades.planchas.dao.ElePlanchaDAO;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleDetalleFormatoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleDetalleFormatoPlanchaId;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFotoFormatoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFotoFormatoPlanchaId;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlancha;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.logica.LogicaAsociado;
import co.com.coomeva.ele.logica.LogicaPlanchas;
import co.com.coomeva.ele.logica.inscripcion.plancha.LogicaPlancha;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.util.acceso.UtilAcceso;

public class EnviarRadicacionVista extends BaseVista {
	private LogicaAsociado logicaAsociado = LogicaAsociado.getInstance();

	private static final String VIRTUAL = "virtual";
	private static final String PRESENCIAL = "presencial";

	private String mensajeIngresoInfoEnviarRadicacion = "";
	private EleDetalleFormatoPlanchaDAO dao = new EleDetalleFormatoPlanchaDAO();
	private ElePlanchaDAO daoPlanchas = new ElePlanchaDAO();
	LogicaPlancha logicaPlancha = new LogicaPlancha();

	private Long CODIGO_FORMATO_INFO_CABEZA_PLANCHA = new Long(
			UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_ESTADO_INFO_CABEZA_PLANCHA_REPRESENTANTE));
	private Long CODIGO_FORMATO_INSCRIPCION_CABEZA_PLANCHA = new Long(
			UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_FORMATO_INSCRIPCION_PLANCHA));
	private String CODIGO_ESTADO_DETALLE_FORMATO_PLANCHA_FINALIZADA = new String(
			UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_ESTADO_DETALLE_FORMATO_PLANCHA_FINALIZADA));

	private Boolean opcionVirtual;
	private Boolean opcionPresencial;
	private Boolean seleccionFirma ;
	private Boolean existeEnvioRadicacion;
	private String opcionFirma;
	private SelectItem[] listaOpcionFirma;
	private Boolean existenPendientes;
	private DTOHabilidadAsociado pendientes;

	/** Variable de sesion id cabeza de plancha **/
	Long numintCabezaPlancha = null;
	Long numeroDocumentoCabezaPlancha = null;
	Long numeroDocumentoAsociado = null;
	Long consecutivoPlancha = null;

	private Boolean mensajeIngreso = false;
	private Boolean mensajeInformativo = false;
	private boolean visibleConfirmar;

	public EnviarRadicacionVista() {
		init();
	}

	private void init() {
		opcionVirtual = Boolean.FALSE;
		opcionPresencial = Boolean.FALSE;
		listaOpcionFirma = new SelectItem[2];
		listaOpcionFirma[0] = new SelectItem(VIRTUAL, "Firma Virtual");
		listaOpcionFirma[1] = new SelectItem(PRESENCIAL, "Firma Presencial");
		existenPendientes = Boolean.FALSE;
		seleccionFirma = Boolean.FALSE;
		numeroDocumentoAsociado = (Long) FacesUtils.getSessionParameter("numeroDocAsociado");
		validacionInicial();
	}

	private void validacionInicial() {
		consultarPendientes();
		validacionIngresoPlanchas();
	}

	public void validacionIngresoPlanchas() {
		try {
			if (FacesUtils.getSessionParameter("numeroDocAsociado") != null) {
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
					validacionIngresoInformacionCabezas();
					cargarTipoFirmaPlancha();
					mensajeIngresoInfoEnviarRadicacion = "Estimado asociado, recuerda que para Finalizar el registro de Inscripción  de Plancha, debes escoger si deseas enviar tu plancha a Radicación de Firma de forma Virtual o forma Presencial";
					mensajeInformativo = Boolean.TRUE;
					// consultarInformacionCabezaPlancha();
				} else {
					mensajeIngresoInfoEnviarRadicacion = new String(
							UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
									"mensajeIngresoInfoCabezaPlancha"));
					action_mostrar_mensaje_ingreso();
				}
			}
		} catch (Exception e) {
			mensajeIngresoInfoEnviarRadicacion = new String(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "mensajeIngresoInfoCabezaPlancha"));
			action_mostrar_mensaje_ingreso();
		}
	}
	
	public void cargarTipoFirmaPlancha() {
		existeEnvioRadicacion = Boolean.FALSE;
		if(consecutivoPlancha != null) {
			try {
				DTOInformacionPlancha plancha = logicaPlancha.consultarInformacionPlancha(consecutivoPlancha);
				if(plancha != null && plancha.getFirmaVirtual() != null) {
					opcionFirma = (plancha.getFirmaVirtual().longValue() == 1L) ? VIRTUAL : PRESENCIAL;
					existeEnvioRadicacion = Boolean.TRUE;
				}
			} catch (EleccionesDelegadosException e) {
				mensajeIngresoInfoEnviarRadicacion = "Ocurrio un error inesperado guardando el Registro de Plancha";
				action_mostrar_mensaje_ingreso();
			}
		}
	}

	public void validacionIngresoInformacionCabezas() {
		Long codigoAsociado;
		try {
			codigoAsociado = logicaAsociado.consultarCodigoAsociadoPorNumeroDocumento(numeroDocumentoAsociado);

			EleDetalleFormatoPlanchaId id = new EleDetalleFormatoPlanchaId();

			id.setCodigoAsociado(codigoAsociado);
			id.setCodigoFormato(CODIGO_FORMATO_INFO_CABEZA_PLANCHA.byteValue());

			EleDetalleFormatoPlancha entity = dao.findById(id);
			if (entity == null) {
				throw new Exception(
						"Estimado Asociado, para continuar con el proceso de Radicación de Firma, primero debe registrar la información general del Cabeza de Plancha.");
			}

		} catch (Exception e) {
			mensajeIngresoInfoEnviarRadicacion = e.getMessage();
			action_mostrar_mensaje_ingreso();
		}
	}

	public String action_mostrar_mensaje_ingreso() {
		mensajeIngreso = true;
		return "";
	}

	public String action_cerrar_mensaje_ingreso() {
		mensajeIngreso = false;
		return "goInicioMenuAsociado";
	}

	public void action_cerrar_mensaje_informativo() {
		mensajeInformativo = false;
	}

	public void consultarPendientes() {
		try {
			pendientes = DelegadoEnvioRadicacion.getInstance().consultarPendientesAsociadoPorId(10276122L);
			if (pendientes != null) {
				existenPendientes = Boolean.TRUE;
			}
		} catch (Exception e) {
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes", "nullException");
			}
			getMensaje().mostrarMensaje(mensaje);
		}
	}
	
	public void action_enviarRadicacion() {
		if(consecutivoPlancha != null && numeroDocumentoCabezaPlancha != null) {
			try {
				if (seleccionFirma) {
					logicaPlancha.asignarTipoFirmaPlancha(consecutivoPlancha, opcionFirma.equals(VIRTUAL) ? 1L : 2L);
					mensajeIngresoInfoEnviarRadicacion = "Estimado Asociado, el proceso de Inscripción de Plancha se completó satisfactoriamente.";
					action_mostrar_mensaje_ingreso();
				}

			} catch (Exception e) {
				mensajeIngresoInfoEnviarRadicacion = "Ocurrio un error inesperado guardando el Registro de Plancha";
				action_mostrar_mensaje_ingreso();
			}
		}
	}

	public void action_cambioDeFirma(ValueChangeEvent e) {
		seleccionFirma = Boolean.TRUE;
		opcionFirma = String.valueOf(e.getNewValue());
		System.out.println("cambio de opcion firma " + opcionFirma);
	}

	// GETTERS AND SETTERS
	public Boolean getOpcionVirtual() {
		return opcionVirtual;
	}

	public void setOpcionVirtual(Boolean opcionVirtual) {
		this.opcionVirtual = opcionVirtual;
	}

	public Boolean getOpcionPresencial() {
		return opcionPresencial;
	}

	public void setOpcionPresencial(Boolean opcionPresencial) {
		this.opcionPresencial = opcionPresencial;
	}

	public String getOpcionFirma() {

		return opcionFirma;
	}

	public void setOpcionFirma(String opcionFirma) {
		this.opcionFirma = opcionFirma;
	}

	public SelectItem[] getListaOpcionFirma() {
		return listaOpcionFirma;
	}

	public void setListaOpcionFirma(SelectItem[] listaOpcionFirma) {
		this.listaOpcionFirma = listaOpcionFirma;
	}

	public Boolean getExistenPendientes() {
		return existenPendientes;
	}

	public void setExistenPendientes(Boolean existenPendientes) {
		this.existenPendientes = existenPendientes;
	}

	public DTOHabilidadAsociado getPendientes() {
		return pendientes;
	}

	public void setPendientes(DTOHabilidadAsociado pendientes) {
		this.pendientes = pendientes;
	}

	public String getMensajeIngresoInfoEnviarRadicacion() {
		return mensajeIngresoInfoEnviarRadicacion;
	}

	public void setMensajeIngresoInfoEnviarRadicacion(String mensajeIngresoInfoEnviarRadicacion) {
		this.mensajeIngresoInfoEnviarRadicacion = mensajeIngresoInfoEnviarRadicacion;
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

	public boolean isVisibleConfirmar() {
		return visibleConfirmar;
	}

	public void setVisibleConfirmar(boolean visibleConfirmar) {
		this.visibleConfirmar = visibleConfirmar;
	}

	public Boolean getExisteEnvioRadicacion() {
		return existeEnvioRadicacion;
	}

	public void setExisteEnvioRadicacion(Boolean existeEnvioRadicacion) {
		this.existeEnvioRadicacion = existeEnvioRadicacion;
	}
}
