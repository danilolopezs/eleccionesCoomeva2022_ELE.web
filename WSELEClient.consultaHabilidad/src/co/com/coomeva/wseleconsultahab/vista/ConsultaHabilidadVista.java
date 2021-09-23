package co.com.coomeva.wseleconsultahab.vista;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;

import co.com.coomeva.wseleconsultahab.delegado.DelegadoHabilidad;
import co.com.coomeva.wseleconsultahab.logica.DTORespuestaHabilidad;

import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;

public class ConsultaHabilidadVista extends BaseVista {

	private String documento;
	private DTORespuestaHabilidad dtoRespuesta;
	private WebServiceContext wsCtxt;

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String action_consultar() {

		try {

			HttpServletRequest request = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
			String userCaptchaResponse = request.getParameter("jcaptcha");
			boolean captchaPassed = SimpleImageCaptchaServlet.validateResponse(request, userCaptchaResponse);

			if (!captchaPassed) {
				throw new Exception("El código ingresado no corresponde a la imagen");
			} 
			this.dtoRespuesta = DelegadoHabilidad.getInstance().consultaHabilidad(documento);
			
		} catch (Exception e) {
			getMensaje().mostrarMensaje(e.getMessage());
			this.dtoRespuesta = null;
		}

		return "";
	}

	public String action_limpiar(){
		this.documento = null;
		this.dtoRespuesta = null;
		
		return "goConsultaHabilidad";
	}

	public String action_regenerarCaptcha(){
		this.dtoRespuesta = null;
		
		return "goConsultaHabilidad";
	}

	public DTORespuestaHabilidad getDtoRespuesta() {
		return dtoRespuesta;
	}

	public void setDtoRespuesta(DTORespuestaHabilidad dtoRespuesta) {
		this.dtoRespuesta = dtoRespuesta;
	}

	public Boolean getTieneInhabilidadesAso() {
		return this.dtoRespuesta != null
				&& this.dtoRespuesta.getObservacionesInhabilidades() != null
				&& this.dtoRespuesta.getObservacionesInhabilidades().size() > 0 ? Boolean.TRUE
				: Boolean.FALSE;
	}

	public void setTieneInhabilidadesAso(Boolean tieneInhabilidadesAso) {
	}
	
}
