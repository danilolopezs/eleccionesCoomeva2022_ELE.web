package co.com.coomeva.ele.backbeans;

import java.util.Date;
import java.util.TimeZone;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;
import co.com.coomeva.util.acceso.UtilAcceso;

import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.context.effects.JavascriptContext;

public class ReportesPlanchasLog extends BaseVista{
	
	private String noCabezaPlancha;
	private Date fechaInicial;
	private Date fechaFinal;
	private String usuario;
	private HtmlSelectOneMenu estadoSelect;
	private SelectItem[] estados;
	private boolean renderTable;
	private boolean visibleConfirmar;
	
	
	public ReportesPlanchasLog() 
	{
		
	}
	/**
	 * Metodo que limpia los valores de los componenetes
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String 
	 */
	public String action_limpiar()
	{
		noCabezaPlancha = null;
		usuario = null;
		fechaInicial = null;
		fechaFinal = null;
		estadoSelect.setValue(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"));
		return "";
	}
	
	/**
	 * Metodo que inicializa el comboBox de estados
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return SelectItem[]
	 */
	public SelectItem[] getEstados() {
		int numero= 0;
		estados = new SelectItem[UtilAcceso.getParametroFuenteI("parametros", "numeroTipotransaccion")+1+UtilAcceso.getParametroFuenteI("parametros", "numeroTipoRevision")];
		estados[0] = new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"),UtilAcceso.getParametroFuenteS("parametros", "defaultLabel"));
		for (int j = 1; j <= UtilAcceso.getParametroFuenteI("parametros", "numeroTipotransaccion") ; j++) {
			estados[j] = new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "tipoTransaccion"+j),UtilAcceso.getParametroFuenteS("parametros", "nombreTransaccion"+j));
			numero = j;
		}
		numero = numero+1;
		for (int i = 1; i <= UtilAcceso.getParametroFuenteI("parametros", "numeroTipoRevision") ; i++) {
			estados[numero] = new SelectItem(UtilAcceso.getParametroFuenteS("parametros", "tipoRevision"+i),UtilAcceso.getParametroFuenteS("parametros", "nombreRevision"+i));
			numero++;
		}
		return estados;
	}
	/**
	 * Metodo que visualiza el PopUp de confirmación
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_verConfirmar(){
		try {
			if (fechaFinal!= null && fechaInicial!=null) {
				if (fechaFinal!=fechaInicial) {
					if (fechaFinal.before(fechaInicial)) {
						throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "ordenFechas"));
					}
				}
			}
			
			if (noCabezaPlancha.equalsIgnoreCase("")) {
				visibleConfirmar = true;
			}else
			{
				validarNumLong(noCabezaPlancha);
				visibleConfirmar = true;
			}
		} catch (Exception e) {
			getMensaje().mostrarMensaje(e.getMessage());
		}
		return "";
	}
	
	/**
	 * Metodo que genera el PDF del Log de planchas
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String generarPdf(){
		try {
			String estado;
			if (estadoSelect.getValue().toString().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "defaultValue"))) {
				estado = "";
			}else
				estado = estadoSelect.getValue().toString();
			
			FacesUtils.setSessionParameter("identificacion", noCabezaPlancha.trim());
			FacesUtils.setSessionParameter("usuario", usuario.trim());
			FacesUtils.setSessionParameter("fechaFinal", fechaFinal);
			FacesUtils.setSessionParameter("fechaInicial", fechaInicial);
			FacesUtils.setSessionParameter("estado", estado);
			UserVo userVo = (UserVo)FacesUtils.getSessionParameter("user");
			String nameUser = "";
			if (userVo != null) {
				nameUser = userVo.getName();
				FacesUtils.setSessionParameter("nameUser", nameUser);
			}else
				FacesUtils.setSessionParameter("nameUser", nameUser);
			
			JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(),"servletInvokerRepoLog();");
			visibleConfirmar = false;
			action_limpiar();
		} catch (Exception e) {
			getMensaje().mostrarMensaje(e.getMessage());
		}
		return "";
	}
	
	/**
	 * Metodo que cierra el PopUp de confirmación
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_closeConfirmar(){
		visibleConfirmar = false;
		return "";
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
	 * @param estados
	 */
	public void setEstados(SelectItem[] estados) {
		this.estados = estados;
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
	 * @return String
	 */
	public String getNoCabezaPlancha() {
		return noCabezaPlancha;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param noCabezaPlancha
	 */
	public void setNoCabezaPlancha(String noCabezaPlancha) {
		this.noCabezaPlancha = noCabezaPlancha;
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
	 * @return String
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
}