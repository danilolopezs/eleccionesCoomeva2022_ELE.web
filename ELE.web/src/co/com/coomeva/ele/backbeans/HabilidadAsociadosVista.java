package co.com.coomeva.ele.backbeans;


import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.entidades.admhabilidad.Asoelecf;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;
import co.com.coomeva.util.acceso.UtilAcceso;

import com.icesoft.faces.component.ext.HtmlSelectOneMenu;

public class HabilidadAsociadosVista extends BaseVista{

	private String nroDocumentoAsociado;
	private String profesion;
	private String antiguedad;
	private String nombreAsociado;
	private String medio;
	private HtmlSelectOneMenu estadoSltValue;
	private SelectItem[] estadosSlt;
	private boolean visibleInhabilidad;
	private boolean visibleConcepto;
	private String conceptoCambio;
	private boolean disInputs;
	private boolean renderTable;
	private UserVo user;
	
	public HabilidadAsociadosVista() {
		init();
	}
	
	/**
	 * Metodo que recibe la información de usuario que se almacen en al sesión 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 */
	private void init() 
	{
		user = (UserVo)FacesUtils.getSessionParameter("user");
	}

	/**
	 * Metodo que carga dinamicamente los estado de los asociados en un HtmlSelectOneMenu
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return SelectItem[]
	 */
	public SelectItem[] getEstadosSlt() {
		estadosSlt = new SelectItem[UtilAcceso.getParametroFuenteI("parametros", "numeroEstadosAsociado")+1];
		estadosSlt[0] = new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"),UtilAcceso.getParametroFuenteS("parametros", "defaultLabel"));
		for (int j = 1; j <= UtilAcceso.getParametroFuenteI("parametros", "numeroEstadosAsociado") ; j++) {
			estadosSlt[j] = new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "caracterEstadoAsociado"+j),UtilAcceso.getParametroFuenteS("parametros", "estadoAsociado"+j));
		}
		return estadosSlt;
	}
	
	/**
	 * Metodo que guarda los cambios los hechos en el estado del asociado
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_guardarCambios(){
		try {
			if (estadoSltValue.getValue().toString().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"))) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noSeleccionoEstado"));
			}
			DelegadoAsociado.getInstance().cambiarEstado(estadoSltValue.getValue().toString(), nroDocumentoAsociado.trim(), conceptoCambio.trim(),user.getUserId());
			getMensaje().mostrarMensaje(UtilAcceso.getParametroFuenteS("mensajes", "mensajeExito"));
			action_limpiar();
		} catch (Exception e) {
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes", "nullException");
			}
			getMensaje().mostrarMensaje(mensaje);
		}
		visibleConcepto = false;
		
		return "";
	}
	
	/**
	 * Metodo que visualiza el PopUp concepto de plancha
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_verConcepto(){
		visibleConcepto = true;
		return "";
	}
	
	/**
	 * Metodo que cierra el PopUp concepto de plancha
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_closeConcepto(){
		visibleConcepto = false;

		return "";
	}

	/**
	 * Metodo que visualiza el PopUp de inhabilidades
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_closeInhabilidades(){
		visibleInhabilidad = false;
		return "";
	}
	
	/**
	 * Metodo que limpia los componentes de presentación  
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_limpiar(){
		nroDocumentoAsociado = null;
		disInputs = false;
		profesion = null; 
		antiguedad = null;
		nombreAsociado = null;
		conceptoCambio = null;
		medio =  null;
		estadoSltValue.setValue(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"));
		
		return "";
	}
	
	/**
	 * Metodo que por medio del ingreso del numero de documento visualiza la habilidad o inhabilidad del asociado
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param event
	 */
	public void listener_nroDocumento(ValueChangeEvent event){
		if (event.getNewValue()!= null&&!event.getNewValue().toString().trim().equals("")) {
			Asoelecf asociado = null;
			
			try {
				asociado = DelegadoAsociado.getInstance().findAsoHabFin(event.getNewValue().toString().trim());
			if (asociado != null) {
				disInputs = true;
				if (asociado.getWmedvot().intValue()==UtilAcceso.getParametroFuenteI("parametros", "idMedioUrna")) {
					medio = UtilAcceso.getParametroFuenteS("parametros", "medio1");
				}else
					medio = UtilAcceso.getParametroFuenteS("parametros", "medio2");;  
				antiguedad = (asociado.getWantig()*12)+" Meses";
				nombreAsociado = asociado.getWnomcli().trim();
				estadoSltValue.setValue(asociado.getWindhab().trim());
			}else{
				disInputs = false;
				antiguedad = null;
				nombreAsociado = null;
				estadoSltValue.setValue(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"));
				}
			} catch (Exception e) {
				String mensaje = e.getMessage();
				if (mensaje == null || mensaje.equalsIgnoreCase("")) {
					mensaje = UtilAcceso.getParametroFuenteS("mensajes", "nullException");
				}
				getMensaje().mostrarMensaje(mensaje);
			}
		}else{
			disInputs = false;
			antiguedad = null;
			nombreAsociado = null;
			estadoSltValue.setValue(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"));
		}
	}

	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getMedio() {
		return medio;
	}	
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param medio
	 */
	public void setMedio(String medio) {
		this.medio = medio;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getConceptoCambio() {
		return conceptoCambio;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param conceptoCambio
	 */
	public void setConceptoCambio(String conceptoCambio) {
		this.conceptoCambio = conceptoCambio;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return HtmlSelectOneMenu
	 */
	public HtmlSelectOneMenu getEstadoSltValue() {
		return estadoSltValue;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param estadoSltValue
	 */
	public void setEstadoSltValue(HtmlSelectOneMenu estadoSltValue) {
		this.estadoSltValue = estadoSltValue;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param estadosSlt
	 */
	public void setEstadosSlt(SelectItem[] estadosSlt) {
		this.estadosSlt = estadosSlt;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return string
	 */
	public String getNroDocumentoAsociado() {
		return nroDocumentoAsociado;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param nroDocumentoAsociado
	 */
	public void setNroDocumentoAsociado(String nroDocumentoAsociado) {
		this.nroDocumentoAsociado = nroDocumentoAsociado;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getProfesion() {
		return profesion;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param profesion
	 */
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getAntiguedad() {
		return antiguedad;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param antiguedad
	 */
	public void setAntiguedad(String antiguedad) {
		this.antiguedad = antiguedad;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getNombreAsociado() {
		return nombreAsociado;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param nombreAsociado
	 */
	public void setNombreAsociado(String nombreAsociado) {
		this.nombreAsociado = nombreAsociado;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isVisibleInhabilidad() {
		return visibleInhabilidad;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param visibleInhabilidad
	 */
	public void setVisibleInhabilidad(boolean visibleInhabilidad) {
		this.visibleInhabilidad = visibleInhabilidad;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isVisibleConcepto() {
		return visibleConcepto;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param visibleConcepto
	 */
	public void setVisibleConcepto(boolean visibleConcepto) {
		this.visibleConcepto = visibleConcepto;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isDisInputs() {
		return disInputs;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param disInputs
	 */
	public void setDisInputs(boolean disInputs) {
		this.disInputs = disInputs;
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
}