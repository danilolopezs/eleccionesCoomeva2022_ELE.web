package co.com.coomeva.ele.backbeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import co.com.coomeva.ele.delegado.DelegadoZona;
import co.com.coomeva.ele.entidades.planchas.EleZonas;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.util.acceso.UtilAcceso;

import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.context.effects.JavascriptContext;

public class ReportesPlanchas extends BaseVista{

	private Date fechaInicial;
	private Date fechaFinal;
	private HtmlSelectOneMenu zonaSelect;
	private List<SelectItem> zonas;
	private HtmlSelectOneMenu estadoSelect;
	private List<SelectItem> estados;
	private boolean renderTable;
	private boolean visibleConfirmar;


	public ReportesPlanchas() {
	}

	/**
	 * Metodo que limpia los valores de los componenetes
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String 
	 */
	public String action_limpiar(){
		fechaInicial = null;
		fechaFinal = null;

		estadoSelect.setValue(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"));
		return "";
	}

	/**
	 * Metodo que genera el PDF de planchas
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_generarPDF(){
		try {
			String estado;
			if (estadoSelect.getValue().toString().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"))) {
				estado = "";
			}else
				estado = estadoSelect.getValue().toString();
			
			

			String zona;
			if (zonaSelect.getValue().toString().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"))) {
				zona = "";
			}else
				zona = zonaSelect.getValue().toString();


			FacesUtils.setSessionParameter("zona", zona.trim());
			FacesUtils.setSessionParameter("fechaFinal", fechaFinal);
			FacesUtils.setSessionParameter("fechaInicial", fechaInicial);
			FacesUtils.setSessionParameter("estado", estado);

			if (fechaFinal!= null && fechaInicial!=null) {
				if (fechaFinal!=fechaInicial) {
					if (fechaFinal.before(fechaInicial)) {
						throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "ordenFechas"));
					}
				}
			}			


			JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(),"servletInvokerRepPlanchas();");
			visibleConfirmar = false;
			action_limpiar();
		} catch (Exception e) {
			getMensaje().mostrarMensaje(e.getMessage());
		}
		return "";
	}


	/**
	 * Metodo que retorna todos los estados disponibles
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getEstados() {
		boolean flag = false;
		estados = new ArrayList<SelectItem>();
		estados.add(new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"),UtilAcceso.getParametroFuenteS("parametros", "defaultLabel")));
		for (int j = 1; j <= UtilAcceso.getParametroFuenteI("parametros", "numeroEstadosPlancha"); j++) {
			if (UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha"+j).equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha1"))) {
				estados.add(new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha"+j),UtilAcceso.getParametroFuenteS("parametros", "displayEstado1")));
				flag = true;
			}

			if (UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha"+j).equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha3"))) {
				estados.add(new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha"+j),UtilAcceso.getParametroFuenteS("parametros", "displayEstado3")));
				flag = true;
			}

			if (UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha"+j).equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha2"))) {
				estados.add(new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha"+j),UtilAcceso.getParametroFuenteS("parametros", "displayEstado2")));
				flag = true;
			}

			if (UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha"+j).equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha7"))) {
				estados.add(new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha"+j),UtilAcceso.getParametroFuenteS("parametros", "displayEstado7")));
				flag = true;
			}

			if (UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha"+j).equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha8"))) {
				estados.add(new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha"+j),UtilAcceso.getParametroFuenteS("parametros", "displayEstado8")));
				flag = true;
			}

			if (UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha"+j).equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha9"))) {
				estados.add(new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha"+j),UtilAcceso.getParametroFuenteS("parametros", "displayEstado9")));
				flag = true;
			}

			if (!flag) {
				estados.add(new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha"+j),UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha"+j)));
			}
			flag = false;	
		}
		return estados;
	}

	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param estados
	 */
	public void setEstados(List<SelectItem> estados) {
		this.estados = estados;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return Date
	 */
	public Date getFechaInicial() {
		return fechaInicial;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param fechaInicial
	 */
	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return Date
	 */
	public Date getFechaFinal() {
		return fechaFinal;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param fechaFinal
	 */
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isRenderTable() {
		return renderTable;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param renderTable
	 */
	public void setRenderTable(boolean renderTable) {
		this.renderTable = renderTable;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isVisibleConfirmar() {
		return visibleConfirmar;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param visibleConfirmar
	 */
	public void setVisibleConfirmar(boolean visibleConfirmar) {
		this.visibleConfirmar = visibleConfirmar;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return TimeZone
	 */
	public TimeZone getTimeZone() {
		return java.util.TimeZone.getDefault();
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return HtmlSelectOneMenu
	 */
	public HtmlSelectOneMenu getEstadoSelect() {
		return estadoSelect;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga 
	 * @param estadoSelect
	 */
	public void setEstadoSelect(HtmlSelectOneMenu estadoSelect) {
		this.estadoSelect = estadoSelect;
	}

	public HtmlSelectOneMenu getZonaSelect() {
		return zonaSelect;
	}

	public void setZonaSelect(HtmlSelectOneMenu zonaSelect) {
		this.zonaSelect = zonaSelect;
	}

	public List<SelectItem> getZonas() {
		EleZonas eleZonaSub = (EleZonas)FacesUtils.getSessionParameter("zonaSubComision");
		EleZonas eleZonaSubEsp  = null;
		try {
			eleZonaSubEsp = DelegadoZona.getInstance().consultarZona(eleZonaSub.getZonEspecial());
			zonas = new ArrayList<SelectItem>();
			zonas.add(new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"),UtilAcceso.getParametroFuenteS("parametros", "defaultLabel")));
			if (eleZonaSub!=null&&eleZonaSubEsp!=null) {
				zonas.add(new SelectItem(eleZonaSub.getCodZona(),eleZonaSub.getNomZona()));
				zonas.add(new SelectItem(eleZonaSubEsp.getCodZona(),eleZonaSubEsp.getNomZona()));
			}
		} catch (Exception e) {
		} 
		
		
		return zonas;
	}

	public void setZonas(List<SelectItem> zonas) {
		this.zonas = zonas;
	}
}