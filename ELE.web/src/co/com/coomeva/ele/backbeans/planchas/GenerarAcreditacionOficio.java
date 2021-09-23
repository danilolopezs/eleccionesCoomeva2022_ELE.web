package co.com.coomeva.ele.backbeans.planchas;

import java.text.MessageFormat;
import java.util.List;

import javax.faces.event.ActionEvent;

import co.com.coomeva.ele.backbeans.MensajesVista;
import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoAcreditacionOficio;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoPlancha;
import co.com.coomeva.ele.dto.DTOPlanchaAsociado;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.formatos.pdf.inscripcion.plancha.FormatoPdfInfoDeclaracionAcreditarOcupacion;
import co.com.coomeva.ele.modelo.EleAsociadoDatosDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.util.acceso.UtilAcceso;

public class GenerarAcreditacionOficio {

	private String nombresApellidosAsociado;
	private Long numeroIdentificacionAsociado;
	private String ciudadExpedicionAsociado;
	private String descripOficio;

	private MensajesVista mensajeVista = new MensajesVista();
	private MensajesVista mensajeAsociadoNoTienePlancha = new MensajesVista();
	private String mensajeConfirmacion = "";
	private boolean visibleConfirmarGenerar;
	private FormatoPdfInfoDeclaracionAcreditarOcupacion formato = new FormatoPdfInfoDeclaracionAcreditarOcupacion();
	private boolean visiblePopupIngresoCedula;
	private String cedulaAsociadoIngresado;
	
	private boolean mostrarPopupErrorIngreso;
	private String mensajePopupErrorIngreso;
	
	private Long asonumintAsociado;

	public GenerarAcreditacionOficio() {
		this.visiblePopupIngresoCedula = Boolean.TRUE;
	}

	public void actionVerificarCedulaAsociado(ActionEvent event) {
		try {
			
			if(this.cedulaAsociadoIngresado == null){
				throw new EleccionesDelegadosException("Por favor ingrese el número" +
						"de documento del asociado para el cual desea generar la Acreditación de" +
						" Oficio");
			}
			
			try {
				Long.parseLong(this.cedulaAsociadoIngresado);
			} catch (NumberFormatException e) {
				throw new EleccionesDelegadosException("Por favor" +
						" ingrese valores numéricos para el número de documento que" +
						" desea generar la acreditación de oficio");
			}

			if (DelegadoAsociado.getInstance().esAsociado(
					Long.parseLong(this.cedulaAsociadoIngresado))) {
				if (!DelegadoAsociado.getInstance().estaAsociaActivo(
						Long.parseLong(this.cedulaAsociadoIngresado))) {
					throw new EleccionesDelegadosException(MessageFormat
							.format(UtilAcceso.getParametroFuenteS("mensajes",
									"msgAsociadoNoActivoCooperativa"),
									this.cedulaAsociadoIngresado.toString()));
				}
			} else {
				throw new EleccionesDelegadosException(UtilAcceso
						.getParametroFuenteS("mensajes",
								"msgIdNoFiguraComoAsociadoCooperativa"));
			}

			this.numeroIdentificacionAsociado = (Long) FacesUtils
					.getSessionParameter("numeroDocAsociado");

			List<DTOPlanchaAsociado> planchaAsociadoIngresoCedula = DelegadoPlancha
					.getInstance().asociadoPertenceOtraPlancha(
							Long.parseLong(this.cedulaAsociadoIngresado), null);

			if (planchaAsociadoIngresoCedula != null
					&& !planchaAsociadoIngresoCedula.isEmpty()) {

				List<DTOPlanchaAsociado> planchaAsociadoLogueado = DelegadoPlancha
						.getInstance().asociadoPertenceOtraPlancha(
								this.numeroIdentificacionAsociado, null);

				if (planchaAsociadoLogueado != null
						&& !planchaAsociadoLogueado.isEmpty()) {

					if (planchaAsociadoLogueado.get(0).getConsecutivoPlancha()
							.equals(
									planchaAsociadoIngresoCedula.get(0)
											.getConsecutivoPlancha())) {

						nombresApellidosAsociado = DelegadoAsociado
								.getInstance().consultarNombreAsociado(
										Long.parseLong(this.cedulaAsociadoIngresado));

						EleAsociadoDatosDTO asociado = DelegadoAsociado
								.getInstance()
								.consultarInformacionBasicaAsociado(
										Long.parseLong(this.cedulaAsociadoIngresado));

						ciudadExpedicionAsociado = asociado
								.getLugarExpedicion();
						
						this.asonumintAsociado = Long.parseLong(asociado.getAsonumint());
						
						this.visiblePopupIngresoCedula = Boolean.FALSE;
						this.mensajeVista.setVisible(Boolean.TRUE);
						this.mensajeVista
								.setMensaje(UtilAcceso
										.getParametroFuenteS(
												ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
												"msgInicioRegistroAcreditacionOficio"));

					} else {
						throw new EleccionesDelegadosException("El asociado ingresado con el"
										+ " número de documento "
										+ this.cedulaAsociadoIngresado
										+ " no pertenece a la misma"
										+ " plancha del asociado logueado actualmente con el número de documento "
										+ this.numeroIdentificacionAsociado
										+ ","
										+ " por lo tanto no se puede generar"
										+ " la Acreditación de Oficio para el número de documento ingresado");
					}
				} else {
					throw new EleccionesDelegadosException("El asociado logueado no pertenece"
									+ " en este momento a ninguna plancha, por lo tanto no puede generar"
									+ " la acreditación de oficio para el número de documento ingresado");
				}
			} else {
				throw new EleccionesDelegadosException("El asociado ingresado no pertenece"
								+ " en este momento a ninguna plancha, por lo tanto no puede generar"
								+ " la acreditación de oficio");
			}
		} catch (Exception e) {
			this.cedulaAsociadoIngresado = null;
			this.mostrarPopupErrorIngreso = Boolean.TRUE;
			this.mensajePopupErrorIngreso = e.getMessage() != null
					&& !"".equals(e.getMessage()) ? e.getMessage() : UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
							"msgErrorNullPointerException");
		}
	}

	public String actionGenerarFormatoPdf() {
		try {
			formato.generarReporte(this.cedulaAsociadoIngresado);
		} catch (Exception e) {
			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje(e.getMessage() != null
					&& !"".equals(e.getMessage()) ? e.getMessage() : UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
							"msgErrorNullPointerException"));
		}
		return "";
	}

	public void actionLisGenerarFormatoPdf(ActionEvent event) {
		try {
			DelegadoAcreditacionOficio.getInstance().registrarInfoAcreditacionOficio(this.asonumintAsociado);
			formato.generarReporte(this.cedulaAsociadoIngresado);
		} catch (Exception e) {
			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje(e.getMessage() != null
					&& !"".equals(e.getMessage()) ? e.getMessage() : UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
							"msgErrorNullPointerException"));
		}
	}

	public String actionConfirmarGeneracionAcreditacion() {
		this.visibleConfirmarGenerar = Boolean.TRUE;
		this.mensajeConfirmacion = "Esta seguro que desea generar la acreditación de"
				+ " oficio?";
		return "";
	}

	public String actionCloseConfirmar() {
		this.visibleConfirmarGenerar = Boolean.FALSE;
		return "";
	}

	public MensajesVista getMensajeVista() {
		return mensajeVista;
	}

	public void setMensajeVista(MensajesVista mensajeVista) {
		this.mensajeVista = mensajeVista;
	}

	public String getMensajeConfirmacion() {
		return mensajeConfirmacion;
	}

	public void setMensajeConfirmacion(String mensajeConfirmacion) {
		this.mensajeConfirmacion = mensajeConfirmacion;
	}

	public boolean isVisibleConfirmarGenerar() {
		return visibleConfirmarGenerar;
	}

	public void setVisibleConfirmarGenerar(boolean visibleConfirmarGenerar) {
		this.visibleConfirmarGenerar = visibleConfirmarGenerar;
	}

	public String getNombresApellidosAsociado() {
		return nombresApellidosAsociado;
	}

	public void setNombresApellidosAsociado(String nombresApellidosAsociado) {
		this.nombresApellidosAsociado = nombresApellidosAsociado;
	}

	public Long getNumeroIdentificacionAsociado() {
		return numeroIdentificacionAsociado;
	}

	public void setNumeroIdentificacionAsociado(
			Long numeroIdentificacionAsociado) {
		this.numeroIdentificacionAsociado = numeroIdentificacionAsociado;
	}

	public String getCiudadExpedicionAsociado() {
		return ciudadExpedicionAsociado;
	}

	public void setCiudadExpedicionAsociado(String ciudadExpedicionAsociado) {
		this.ciudadExpedicionAsociado = ciudadExpedicionAsociado;
	}

	public String getDescripOficio() {
		return descripOficio;
	}

	public void setDescripOficio(String descripOficio) {
		this.descripOficio = descripOficio;
	}

	public MensajesVista getMensajeAsociadoNoTienePlancha() {
		return mensajeAsociadoNoTienePlancha;
	}

	public void setMensajeAsociadoNoTienePlancha(
			MensajesVista mensajeAsociadoNoTienePlancha) {
		this.mensajeAsociadoNoTienePlancha = mensajeAsociadoNoTienePlancha;
	}

	public boolean isVisiblePopupIngresoCedula() {
		return visiblePopupIngresoCedula;
	}

	public void setVisiblePopupIngresoCedula(boolean visiblePopupIngresoCedula) {
		this.visiblePopupIngresoCedula = visiblePopupIngresoCedula;
	}

	public String getCedulaAsociadoIngresado() {
		return cedulaAsociadoIngresado;
	}

	public void setCedulaAsociadoIngresado(String cedulaAsociadoIngresado) {
		this.cedulaAsociadoIngresado = cedulaAsociadoIngresado;
	}

	public boolean isMostrarPopupErrorIngreso() {
		return mostrarPopupErrorIngreso;
	}

	public void setMostrarPopupErrorIngreso(boolean mostrarPopupErrorIngreso) {
		this.mostrarPopupErrorIngreso = mostrarPopupErrorIngreso;
	}

	public String getMensajePopupErrorIngreso() {
		return mensajePopupErrorIngreso;
	}

	public void setMensajePopupErrorIngreso(String mensajePopupErrorIngreso) {
		this.mensajePopupErrorIngreso = mensajePopupErrorIngreso;
	}
	
	public String ocultarMensajePopupErrorIngreso(){
		this.mostrarPopupErrorIngreso = Boolean.FALSE;
		return "";
	}
	
	
}
