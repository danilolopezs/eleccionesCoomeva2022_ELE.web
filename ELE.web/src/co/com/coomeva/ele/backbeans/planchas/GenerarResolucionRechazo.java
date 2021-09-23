package co.com.coomeva.ele.backbeans.planchas;

import java.util.Date;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.icesoft.faces.context.effects.JavascriptContext;

import co.com.coomeva.ele.backbeans.MensajesVista;
import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoEstadoPlancha;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoFormatoPlancha;
import co.com.coomeva.ele.dto.InfoDetalleFormatoPlanchaDTO;
import co.com.coomeva.ele.formatos.pdf.inscripcion.plancha.FormatoPdfInfoResolucionRechazo;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.ele.util.WorkStrigs;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;
import co.com.coomeva.util.acceso.UtilAcceso;

public class GenerarResolucionRechazo {

	public static final String COD_ESTADO_PLANCHA_RECHAZADA = UtilAcceso
			.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_ESTADO_PLANCHA_RECHAZADA);

	private Long numeroResolucion;
	private Date fechaResolucion;
	private Long numeroActa;
	private Date fechaActa;
	private String razon;
	private MensajesVista mensajeVista = new MensajesVista();
	private String mensajeConfirmacion = "";
	private boolean visibleConfirmarGenerar;
	private FormatoPdfInfoResolucionRechazo formato = new FormatoPdfInfoResolucionRechazo();

	public GenerarResolucionRechazo() {

	}

	public String actionGenerarFormatoPdf() {
		try {
			if (this.razon.isEmpty()) {
				this.mensajeVista.setVisible(Boolean.TRUE);
				this.mensajeVista.setMensaje(UtilAcceso.getParametroFuenteS(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
						"campo.obligatorio.resolucion.razonResolucionRechazo"));
				return "";
			}

			Long numCedulaAttribute = (Long) FacesUtils.getSession()
					.getAttribute("numeroDocCabeza");
			InfoDetalleFormatoPlanchaDTO dto = new InfoDetalleFormatoPlanchaDTO();
			dto.setNumeroDocumento(numCedulaAttribute);
			dto.setFechaResolucion(this.fechaResolucion);
			dto.setNumeroResolucion(this.numeroResolucion);
			dto.setNumeroActa(this.numeroActa);
			dto.setFechaActa(this.fechaActa);
			dto.setRazon(this.razon);

			UserVo user = (UserVo) FacesUtils.getSessionParameter("user");

			dto = DelegadoFormatoPlancha.getInstance()
					.guardarInformacionResolucionRechazo(dto, user.getUserId());

			DelegadoPlanchas.getInstance().actualizarEstadoPlancha(
					dto.getConsecutivoPlancha(), COD_ESTADO_PLANCHA_RECHAZADA,
					user.getUserId());
			
//			DelegadoEstadoPlancha.getInstance().crearEstadoPlancha(
//					user.getUserId(), new Date(), COD_ESTADO_PLANCHA_RECHAZADA,
//					new Long(dto.getConsecutivoPlancha()));

//			formato.generarReporte(dto);
			
			generarformulario(dto);
			// FacesUtils.getSession().setAttribute("numeroDocCabeza",
			// null);//Se comenta esta línea porque al querer imprimir
			// nuevamente el formato esta variable de sesión queda nula causando
			// un error no controlado
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

	private void generarformulario(InfoDetalleFormatoPlanchaDTO dto) {
		HttpServletRequest request= (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		Date fechaElaboracionDoc = new Date();
		
		request.getSession().setAttribute("zonaElectoral", dto.getNumeroComisionElectoral());
		request.getSession().setAttribute("nombreAsociado", dto.getNombresApellidosAsociadoCabeza());
		request.getSession().setAttribute("cedulaAsociado", dto.getNumeroDocumento().toString());
		request.getSession().setAttribute("numResolucion", dto.getNumeroResolucion().toString());
		request.getSession().setAttribute("numActa", dto.getNumeroActa().toString());
		request.getSession().setAttribute("fecha", fechaElaboracionDoc);
		request.getSession().setAttribute("dia",fechaElaboracionDoc!=null?String.valueOf(fechaResolucion.getDate()):""); 
		request.getSession().setAttribute("mes",fechaElaboracionDoc!=null?WorkStrigs.getMes(fechaResolucion.getMonth()):""); 
		request.getSession().setAttribute("anio",fechaElaboracionDoc!=null?WorkStrigs.getAnio(fechaResolucion.getYear()):"");
		request.getSession().setAttribute("razones", dto.getRazon());
		request.getSession().setAttribute("mesActa",fechaElaboracionDoc!=null?WorkStrigs.getMes(dto.getFechaActa().getMonth()):""); 
		
		request.getSession().setAttribute("codigoReporte", "173");
		JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "ServletReportesJasper();");
	}

	public String actionConfirmarGeneracionResolucionRechazo() {
		this.visibleConfirmarGenerar = Boolean.TRUE;
		this.mensajeConfirmacion = "Esta seguro que desea generar la resolución de"
				+ " rechazo para la plancha actual?";
		return "";
	}

	public String actionCloseConfirmar() {
		this.visibleConfirmarGenerar = Boolean.FALSE;
		return "";
	}

	public Long getNumeroResolucion() {
		return numeroResolucion;
	}

	public void setNumeroResolucion(Long numeroResolucion) {
		this.numeroResolucion = numeroResolucion;
	}

	public Date getFechaResolucion() {
		return fechaResolucion;
	}

	public void setFechaResolucion(Date fechaResolucion) {
		this.fechaResolucion = fechaResolucion;
	}

	public Long getNumeroActa() {
		return numeroActa;
	}

	public void setNumeroActa(Long numeroActa) {
		this.numeroActa = numeroActa;
	}

	public Date getFechaActa() {
		return fechaActa;
	}

	public void setFechaActa(Date fechaActa) {
		this.fechaActa = fechaActa;
	}

	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
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

}
