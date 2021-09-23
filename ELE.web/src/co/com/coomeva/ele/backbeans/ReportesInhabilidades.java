package co.com.coomeva.ele.backbeans;


import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import co.com.coomeva.ele.delegado.DelegadoUbicacion;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.util.acceso.UtilAcceso;

import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.context.effects.JavascriptContext;

public class ReportesInhabilidades extends BaseVista
{
	private HtmlSelectOneMenu estadoSelect;
	private SelectItem[] estados;
	private HtmlSelectOneMenu zonaSelect;
	private SelectItem[] zonas;
	private HtmlSelectOneMenu oficinaSelect;
	private SelectItem[] oficinas;
	private HtmlSelectOneMenu regionalSelect;
	private SelectItem[] regionales;


	private boolean renderTable;
	private boolean visibleConfirmar;


	public ReportesInhabilidades() 
	{
		
	}
	/**
	 * Metodo que limpia los valores de los componenetes
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String 
	 */
	public String action_limpiar(){
		estadoSelect.setValue(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"));
		zonaSelect.setValue(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"));
		oficinaSelect.setValue(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"));
		regionalSelect.setValue(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"));
		zonas = new SelectItem[1];
		zonas[0] = new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"),UtilAcceso.getParametroFuenteS("parametros", "defaultLabel"));
		oficinas = new SelectItem[1];
		oficinas[0] = new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"),UtilAcceso.getParametroFuenteS("parametros", "defaultLabel"));

		return "";
	}

	/**
	 * Metodo que inicializa el comboBox de estados
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return SelectItem[]
	 */
	public SelectItem[] getEstados() {
		estados = new SelectItem[UtilAcceso.getParametroFuenteI("parametros", "numeroEstadosAsociado")+1];
		estados[0] = new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"),UtilAcceso.getParametroFuenteS("parametros", "defaultLabel"));
		for (int j = 1; j <= UtilAcceso.getParametroFuenteI("parametros", "numeroEstadosAsociado") ; j++) {
			estados[j] = new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "caracterEstadoAsociado"+j),UtilAcceso.getParametroFuenteS("parametros", "estadoAsociado"+j));
		}
		return estados;
	}
	
	/**
	 * Metodo que genera el PDF del Asociado
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String generarPdf()
	{
		String estado = "";
		String zona = "";
		String oficina = "";
		String regional = "";
		if (estadoSelect.getValue().toString().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"))) {
			estado = "";
		}else
			estado = estadoSelect.getValue().toString().trim();

		if (zonaSelect.getValue().toString().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"))) {
			zona = "";
		}else
			zona = zonaSelect.getValue().toString().trim();

		if (regionalSelect.getValue().toString().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"))) {
			regional = "";
		}else
			regional = regionalSelect.getValue().toString().trim();

		if (oficinaSelect.getValue().toString().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"))) {
			oficina = "";
		}else
			oficina = oficinaSelect.getValue().toString().trim();


		FacesUtils.setSessionParameter("zona", zona);
		FacesUtils.setSessionParameter("oficina", oficina);
		FacesUtils.setSessionParameter("regional", regional);
		FacesUtils.setSessionParameter("estado", estado);

		JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(),"servletInvokerAsociado();");
		action_limpiar();
		visibleConfirmar = false;
		return "";
	}
	
	/**
	 * Metodo que me llena dinamicamente las reginales 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param event
	 */
	public void listener_regional(ValueChangeEvent event)
	{
		if (event.getNewValue()!= null) {
			Long regional = new Long(event.getNewValue().toString());

			try {
				List<Object[]> listaZonas = DelegadoUbicacion.getInstance().consultarZonas(regional);
				zonas = new SelectItem[listaZonas.size()+1];
				zonas[0] = new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"),UtilAcceso.getParametroFuenteS("parametros", "defaultLabel"));
				int cont = 1;
				for (Object[] objects : listaZonas) {
					zonas[cont] = new SelectItem(objects[0].toString(),objects[1].toString());
					cont++;
				}
			} catch (Exception e) {
				getMensaje().mostrarMensaje(e.getMessage());
			}
		}else{
			action_limpiar();
		}


	}
	
	/**
	 * Metodo que me llena dinamicamente las zonas 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param event
	 */
	public void listener_zona(ValueChangeEvent event)
	{
		if (event.getNewValue()!= null) {
			if (regionalSelect.getValue()!=null&&!regionalSelect.getValue().toString().trim().equalsIgnoreCase("")) {
				Long regional = new Long(regionalSelect.getValue().toString());
				String zona = event.getNewValue().toString();
				try {
					List<Object[]> listaZonas = DelegadoUbicacion.getInstance().consultarOficinas(regional,zona);
					oficinas = new SelectItem[listaZonas.size()+1];
					oficinas[0] = new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"),UtilAcceso.getParametroFuenteS("parametros", "defaultLabel"));
					int cont = 1;
					for (Object[] objects : listaZonas) {
						oficinas[cont] = new SelectItem(objects[0].toString(),objects[1].toString());
						cont++;
					}
				} catch (Exception e) {
					getMensaje().mostrarMensaje(e.getMessage());
				}
			}else{
				action_limpiar();
			}
		}else{
			action_limpiar();
		}

	}

	/**
	 * Metodo que inicializa el comboBox de zonas
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return SelectItem[]
	 */
	public SelectItem[] getZonas() {
		if (regionalSelect.getValue()==null||regionalSelect.getValue().equals(UtilAcceso.getParametroFuenteI("parametros", "defaultValue"))) {
			zonas = new SelectItem[1];
			zonas[0] = new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"),UtilAcceso.getParametroFuenteS("parametros", "defaultLabel"));
		}
		return zonas;
	}
	
	/**
	 * Metodo que inicializa el comboBox de oficinas
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return SelectItem[]
	 */
	public SelectItem[] getOficinas() {
		if (regionalSelect.getValue()==null||regionalSelect.getValue().equals(UtilAcceso.getParametroFuenteI("parametros", "defaultValue"))
				||zonaSelect.getValue()==null||zonaSelect.getValue().equals(UtilAcceso.getParametroFuenteI("parametros", "defaultValue"))) {
			oficinas = new SelectItem[1];
			oficinas[0] = new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"),UtilAcceso.getParametroFuenteS("parametros", "defaultLabel"));
		}

		return oficinas;
	}
	
	/**
	 * Metodo que inicializa el comboBox de reginales
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return SelectItem[]
	 */
	public SelectItem[] getRegionales() {
		try {
			if (regionales == null) {
				List<Object[]> listaRegionales = DelegadoUbicacion.getInstance().consultarRegionales();
				regionales = new SelectItem[listaRegionales.size()+1];
				regionales[0] = new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"),UtilAcceso.getParametroFuenteS("parametros", "defaultLabel"));
				int cont = 1;
				for (Object[] objects : listaRegionales) {
					regionales[cont] = new SelectItem(objects[0].toString(),objects[1].toString());
					cont++;
				}

			}
		} catch (Exception e) {
			getMensaje().mostrarMensaje(e.getMessage());
		}

		return regionales;
	}
	
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return HtmlSelectOneMenu
	 */
	public HtmlSelectOneMenu getZonaSelect() {
		return zonaSelect;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param zonaSelect
	 */
	public void setZonaSelect(HtmlSelectOneMenu zonaSelect) {
		this.zonaSelect = zonaSelect;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param zonas
	 */
	public void setZonas(SelectItem[] zonas) {
		this.zonas = zonas;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return HtmlSelectOneMenu
	 */
	public HtmlSelectOneMenu getOficinaSelect() {
		return oficinaSelect;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param oficinaSelect
	 */
	public void setOficinaSelect(HtmlSelectOneMenu oficinaSelect) {
		this.oficinaSelect = oficinaSelect;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param oficinas
	 */
	public void setOficinas(SelectItem[] oficinas) {
		this.oficinas = oficinas;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return HtmlSelectOneMenu
	 */
	public HtmlSelectOneMenu getRegionalSelect() {

		return regionalSelect;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param regionalSelect
	 */
	public void setRegionalSelect(HtmlSelectOneMenu regionalSelect) {
		this.regionalSelect = regionalSelect;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param regionales
	 */
	public void setRegionales(SelectItem[] regionales) {
		this.regionales = regionales;
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
	 * @param estados
	 */
	public void setEstados(SelectItem[] estados) {
		this.estados = estados;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_verConfirmacion(){
		visibleConfirmar = true;
		return "";
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga 
	 * @return String
	 */
	public String action_closeConfirmar(){
		visibleConfirmar = false;
		return "";
	}
}