package co.com.coomeva.ele.backbeans.habilidad.asociados;

import co.com.coomeva.ele.backbeans.BaseVista;
import co.com.coomeva.ele.delegado.habilidad.DelegadoProcesoVerificacionHabilidad;
import co.com.coomeva.ele.modelo.habilidad.DTOProcesoVerificacionHabilidad;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;

/**
 * Backendbean para la presentación de la información del proceso de
 * verificación de habilidad de asociados que está pendiente por ejecución junto
 * con sus reglas de validación
 * 
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
 *         Premize SAS <br>
 * @project ELE.web
 * @class EjecutarProcesoVerificacion
 * @date 9/11/2012
 * 
 */
public class EjecutarProcesoVerificacion extends BaseVista{

	private DTOProcesoVerificacionHabilidad procesoVerificacionHabilidad;
	private boolean visibleConfirmar;

	public EjecutarProcesoVerificacion() {
		/*Tiempo de sesio  de 3 horas*/
		FacesUtils.getSession().setMaxInactiveInterval(30*60*60);

		procesoVerificacionHabilidad = DelegadoProcesoVerificacionHabilidad
				.getInstance().obtenerProcesoVerificacionActivo();
		FacesUtils.getSession().setAttribute("procesoVerificacionHabilidad",
				procesoVerificacionHabilidad);
	}

	public String actionEjecutarProceso() {
	    try {
	    	
	    	visibleConfirmar = false;

	    	UserVo user = (UserVo) FacesUtils.getSession().getAttribute("user");
			String nombreUsuario = user.getName() != null
					&& user.getName().length() > 15 ? user.getName().substring(
					0, 14) : user.getName() != null ? user.getName() : null;
					
			procesoVerificacionHabilidad = DelegadoProcesoVerificacionHabilidad
			.getInstance().obtenerProcesoVerificacionActivo();
	FacesUtils.getSession().setAttribute("procesoVerificacionHabilidad",
			procesoVerificacionHabilidad);
			
			DelegadoProcesoVerificacionHabilidad.getInstance()
					.ejecutarProcesoVerificacion(
							this.procesoVerificacionHabilidad, nombreUsuario);
			
	    }catch (Exception e) {  
	       e.printStackTrace();  
	    }  
		return "";
	}

	public DTOProcesoVerificacionHabilidad getProcesoVerificacionHabilidad() {
		return procesoVerificacionHabilidad;
	}

	public void setProcesoVerificacionHabilidad(
			DTOProcesoVerificacionHabilidad procesoVerificacionHabilidad) {
		this.procesoVerificacionHabilidad = procesoVerificacionHabilidad;
	}
	
	public Boolean getTieneReglasPorEjecutar() {
		return this.procesoVerificacionHabilidad != null
		&& this.procesoVerificacionHabilidad.getListaReglas() != null
		&& this.procesoVerificacionHabilidad.getListaReglas().size() > 0 ? Boolean.TRUE
		: Boolean.FALSE; 
	}

	public void setTieneReglasPorEjecutar(Boolean tieneReglasPorEjecutar) {
	}
	
	public String actionCloseConfirmar(){
		visibleConfirmar = false;
		return "";
	}

	public boolean isVisibleConfirmar() {
		return visibleConfirmar;
	}

	public void setVisibleConfirmar(boolean visibleConfirmar) {
		this.visibleConfirmar = visibleConfirmar;
	}
	
	public String action_verConfirmacion(){
		visibleConfirmar = true;
		return "";
	}

}
