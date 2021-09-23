package co.com.coomeva.ele.backbeans;


import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.resources.LoaderResourceElements;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.ext.HtmlSelectOneRadio;
import com.icesoft.faces.context.effects.JavascriptContext;

public class GenerarReportesHabilidadesVista extends BaseVista{
	
	private HtmlSelectOneMenu txtTipoHabilidad;
	private HtmlSelectOneRadio txtTipoReporte;
	
	private SelectItem[] losTipoHabilidad;
	private SelectItem[] losTipoReportes;
	
	private HtmlCommandButton btnLimpiar;
	private HtmlCommandButton btnGenerarReporte;
	
	private LoaderResourceElements loaderResourceElements = LoaderResourceElements.getInstance();
	
	private final static Long CODIGO_TIPO_PARAMETRO_RUTA_REPORTES_HABILIDAD = new Long(
			UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
							"codigo.tipo.parametro.ruta.reportes.habilidad"));
	
	private boolean visibleConfirmar;
	
	public GenerarReportesHabilidadesVista() {
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		Query query = session.getNamedQuery("consultar.ruta.reportes.habilidad");
		query.setLong("tipCodigo", CODIGO_TIPO_PARAMETRO_RUTA_REPORTES_HABILIDAD);
		try {
			String strRutaReportes = (String) query.uniqueResult();
			FacesContext.getCurrentInstance().getExternalContext().redirect(strRutaReportes);
			
		} catch (Exception e) {
			   e.printStackTrace();
			// TODO: handle exception
		}
		
	}
	
	public String action_limpiar(){
		limpiar();
		return "";
	}
	
	public void limpiar(){
		txtTipoHabilidad.setValue(1);
		txtTipoReporte.setValue(null);
		
		FacesUtils.getSession().removeAttribute("reporteParam");
		FacesUtils.getSession().removeAttribute("tipoReporte");
		FacesUtils.getSession().removeAttribute("tipoNovedad");
	}
	
	public String action_generarReporte(){
		try {
			
			action_closeConfirmar();
			
			if (txtTipoReporte.getValue() == null) {
				throw new Exception(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL, "mensaje.tipo.reporte"));
			}
			
			FacesUtils.getSession().setAttribute("reporteParam", nombreReporteGenerar(FacesUtils.checkLong(txtTipoHabilidad, loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
					"lblTipoReporte"))));
			
			
			if (FacesUtils.checkLong(txtTipoReporte, loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"tipoReporteHabilidadExcel")) == 0) {
				FacesUtils.getSession().setAttribute("tipoReporte", loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
						"tipoReporteHabilidadExcel").toString());
			}else{
				FacesUtils.getSession().setAttribute("tipoReporte", loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
						"tipoReporteHabilidadPdf").toString());
			}			

			FacesUtils.getSession().setAttribute("tipoNovedad", FacesUtils.checkLong(txtTipoHabilidad, 
					loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblTipoHabilidad")));
			
			JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(),"servletReportesInhabilidades();");
		} catch (Exception e) {
			getMensaje().mostrarMensaje(e.getMessage());
		}
		return "";
	}
	
	public String nombreReporteGenerar(Long tipoReporte){
		String reporte = null;
		
		try {
			if (tipoReporte == 1) {
				reporte = loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
						"reporte_habiles");
			}else if (tipoReporte == 0) {
				reporte = loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
						"reporte_inhabiles");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return reporte;
	}

	public HtmlSelectOneMenu getTxtTipoHabilidad() {
		return txtTipoHabilidad;
	}


	public void setTxtTipoHabilidad(HtmlSelectOneMenu txtTipoHabilidad) {
		this.txtTipoHabilidad = txtTipoHabilidad;
	}

	public SelectItem[] getLosTipoHabilidad() {
		losTipoHabilidad = new SelectItem[2];		
		try {
			losTipoHabilidad[0] = new SelectItem(0, loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
					"tipoAsociadosInhabiles"));
			losTipoHabilidad[1] = new SelectItem(1, loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
					"tipoAsociadosHabiles"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return losTipoHabilidad;
	}

	public void setLosTipoHabilidad(SelectItem[] losTipoHabilidad) {
		this.losTipoHabilidad = losTipoHabilidad;
	}

	public HtmlCommandButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(HtmlCommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public HtmlSelectOneRadio getTxtTipoReporte() {
		return txtTipoReporte;
	}

	public void setTxtTipoReporte(HtmlSelectOneRadio txtTipoReporte) {
		this.txtTipoReporte = txtTipoReporte;
	}

	public SelectItem[] getLosTipoReportes() {
		losTipoReportes = new SelectItem[2];
		
		try {
			losTipoReportes[0] = new SelectItem(0, loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
					"lblGenerarExcel"));
			losTipoReportes[1] = new SelectItem(1, loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
					"lblGenerarPdf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return losTipoReportes;
	}

	public void setLosTipoReportes(SelectItem[] losTipoReportes) {
		this.losTipoReportes = losTipoReportes;
	}

	public HtmlCommandButton getBtnGenerarReporte() {
		return btnGenerarReporte;
	}

	public void setBtnGenerarReporte(HtmlCommandButton btnGenerarReporte) {
		this.btnGenerarReporte = btnGenerarReporte;
	}

	public LoaderResourceElements getLoaderResourceElements() {
		return loaderResourceElements;
	}

	public void setLoaderResourceElements(
			LoaderResourceElements loaderResourceElements) {
		this.loaderResourceElements = loaderResourceElements;
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

	public String action_closeConfirmar(){
		visibleConfirmar = false;
		return "";
	}

}
