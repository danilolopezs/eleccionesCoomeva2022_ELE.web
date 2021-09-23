package co.com.coomeva.ele.backbeans.planchas;

import javax.faces.context.FacesContext;

import com.icesoft.faces.context.effects.JavascriptContext;

import co.com.coomeva.ele.backbeans.BaseVista;
import co.com.coomeva.ele.delegado.habilidad.DelegadoProcesoVerificacionHabilidad;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoProcesoMarcacion;
import co.com.coomeva.ele.modelo.habilidad.DTOProcesoVerificacionHabilidad;
import co.com.coomeva.ele.util.FacesUtils;

public class EjecutarProcesoMarcacionAsos extends BaseVista{
	
	private DTOProcesoVerificacionHabilidad procesoVerificacionHabilidad;
	private boolean visibleConfirmar;

	public EjecutarProcesoMarcacionAsos() {
		procesoVerificacionHabilidad = DelegadoProcesoVerificacionHabilidad
				.getInstance().obtenerProcesoVerificacionActivo();
		FacesUtils.getSession().setAttribute("procesoVerificacionHabilidad",
				procesoVerificacionHabilidad);
	}

	public String actionEjecutarProceso() {
	    try {
	    	visibleConfirmar = false;
			DelegadoProcesoMarcacion.getInstance().marcarAsociadosConViolaciones();
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
		
	public String action_generar_reporte(){
		try {
			JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(),"servletReporteProcesoMarcacion();");
		} catch (Exception e) {
			getMensaje().mostrarMensaje(e.getMessage());
		}
		return "";
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
