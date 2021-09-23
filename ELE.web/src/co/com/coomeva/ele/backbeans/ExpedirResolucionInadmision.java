package co.com.coomeva.ele.backbeans;

import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoPlancha;
import co.com.coomeva.ele.dto.DTOInformacionPlancha;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.formatos.pdf.inscripcion.plancha.FormatoPdfInadmisionPlancha;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.resources.LoaderResourceElements;

public class ExpedirResolucionInadmision extends BaseVista{
	
	public static final String COD_ESTADO_PLANCHA_RECHAZADA = UtilAcceso
	.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
			ConstantesProperties.CODIGO_ESTADO_PLANCHA_RECHAZADA);

	private String cabezaPlancha;
	private String nombreAsociado;
	private String resolucionNro;
	private String actNro;
	private String cuidad;
	
	private String razon1;
	private String razon2;
	private String razon3;
	private String razon4;
	
	private boolean descargarReporte = false;
	private FormatoPdfInadmisionPlancha formatoPdfInadmisionPlancha = new FormatoPdfInadmisionPlancha();
	
	public String consultarCabezaPlancha(){
		try {
			if( cabezaPlancha == null ){
				throw new Exception("El documento del cabeza de plancha es requerido para el proceso.");
			}
			
			nombreAsociado = DelegadoAsociado.getInstance().consultarNombreAsociado(new Long(cabezaPlancha));
			descargarReporte = true;
		} catch (Exception e) {
			getMensaje().mostrarMensaje(e.getMessage());
		}
		return null;
		
	}
	
	public String generarReporte(){
		try {
			if( cabezaPlancha == null || cabezaPlancha.length() == 0){
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "campo.obligatorio.inadmision.cabezaPlancha"));
			}
			
//			if( resolucionNro == null || resolucionNro.length() == 0){
//				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "campo.obligatorio.inadmision.resolucionNro"));
//			}
//			if( actNro == null || actNro.length() == 0){
//				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "campo.obligatorio.inadmision.actNro"));
//			}
//			if( cuidad == null || cuidad.length() == 0){
//				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "campo.obligatorio.inadmision.cuidad"));
//			}
			
			if( razon1 == null  || razon1.length() == 0){
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "campo.obligatorio.inadmision.razon1"));
			}
			
			formatoPdfInadmisionPlancha.generarReporte(cabezaPlancha, resolucionNro, actNro, razon1, razon2, razon3, razon4,cuidad, nombreAsociado);
			
		} catch (Exception e) {
			getMensaje().mostrarMensaje(e.getMessage());
		}
		return null;
	}
	
	public String limpiar_formulario(){
		cabezaPlancha = null;
		descargarReporte = false;
		return null;
	}
	
	public String getCabezaPlancha() {
		return cabezaPlancha;
	}
	public void setCabezaPlancha(String cabezaPlancha) {
		this.cabezaPlancha = cabezaPlancha;
	}
	public String getResolucionNro() {
		return resolucionNro;
	}
	public void setResolucionNro(String resolucionNro) {
		this.resolucionNro = resolucionNro;
	}
	public String getActNro() {
		return actNro;
	}
	public void setActNro(String actNro) {
		this.actNro = actNro;
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

	public String getNombreAsociado() {
		return nombreAsociado;
	}

	public void setNombreAsociado(String nombreAsociado) {
		this.nombreAsociado = nombreAsociado;
	}

	public String getCuidad() {
		return cuidad;
	}

	public void setCuidad(String cuidad) {
		this.cuidad = cuidad;
	}

	public boolean isDescargarReporte() {
		return descargarReporte;
	}

	public void setDescargarReporte(boolean descargarReporte) {
		this.descargarReporte = descargarReporte;
	}

	public FormatoPdfInadmisionPlancha getFormatoPdfInadmisionPlancha() {
		return formatoPdfInadmisionPlancha;
	}

	public void setFormatoPdfInadmisionPlancha(
			FormatoPdfInadmisionPlancha formatoPdfInadmisionPlancha) {
		this.formatoPdfInadmisionPlancha = formatoPdfInadmisionPlancha;
	}
}
