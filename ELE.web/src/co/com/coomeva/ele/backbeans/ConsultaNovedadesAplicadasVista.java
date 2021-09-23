package co.com.coomeva.ele.backbeans;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import co.com.coomeva.ele.delegado.DelegadoFiltros;
import co.com.coomeva.ele.modelo.FiltrosConsultasDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.util.resources.LoaderResourceElements;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.ext.HtmlSelectOneRadio;
import com.icesoft.faces.context.effects.JavascriptContext;

public class ConsultaNovedadesAplicadasVista extends BaseVista{
	
	private HtmlSelectOneMenu txtNovedadAplicada;
	private HtmlSelectOneMenu txtZona;
	private HtmlSelectOneMenu txtFechaProceso;
	private HtmlSelectOneRadio txtTipoReporte;
	private Date fechaCorte;
	
	private SelectItem[] lasNovedades;
	private SelectItem[] lasZonas;
	private SelectItem[] lasFechasProceso;
	private SelectItem[] losTipoReportes;
	
	private HtmlCommandButton btnGenerarReporte;
	private HtmlCommandButton btnLimpiar;
	
	private LoaderResourceElements loaderResourceElements = LoaderResourceElements.getInstance();
	
	private boolean visibleConfirmar;
	
	public ConsultaNovedadesAplicadasVista(){
		
	}
	
	public String action_limpiar(){
		limpiar();
		return "";
	}
	
	public void limpiar(){
		txtNovedadAplicada.setValue(-1);
		txtZona.setValue(-1);
		txtFechaProceso.setValue(-1);
		txtTipoReporte.setValue(null);
	}
	
	public String action_generarReporte(){
		try {
			
			action_closeConfirmar();
			
			if (txtTipoReporte.getValue() == null) {
				throw new Exception(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL, "mensaje.tipo.reporte"));
			}
			
			FacesUtils.getSession().setAttribute("tipoNovedad", FacesUtils.checkLong(txtNovedadAplicada, loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
					"lblTipoNovedad")));
			
			if (FacesUtils.checkLong(txtTipoReporte, loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"tipoReporteHabilidadExcel")) == 0) {
				FacesUtils.getSession().setAttribute("tipoReporteNov", loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
						"tipoReporteHabilidadExcel").toString());
			}else{
				FacesUtils.getSession().setAttribute("tipoReporteNov", loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
						"tipoReporteHabilidadPdf").toString());
			}
			Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			Long codZona = FacesUtils.checkLong(txtZona, loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
					"lblreporteZona"));
			/*
			 * Long codProceso = FacesUtils.checkLong(formatter.format(fechaCorte), loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
					"lblProcesoEjecucion"));
			*/
			
			String fecha = formatter.format(fechaCorte);
			FacesUtils.getSession().setAttribute("codZona", codZona);
			FacesUtils.getSession().setAttribute("fechaCorte", fecha);
			
			JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(),"ServletReportesNovedades();");
			
			
		} catch (Exception e) {
			getMensaje().mostrarMensaje(e.getMessage());			
		}
		return "";
	}

	public HtmlSelectOneMenu getTxtNovedadAplicada() {
		return txtNovedadAplicada;
	}

	public void setTxtNovedadAplicada(HtmlSelectOneMenu txtNovedadAplicada) {
		this.txtNovedadAplicada = txtNovedadAplicada;
	}

	public HtmlSelectOneMenu getTxtZona() {
		return txtZona;
	}

	public void setTxtZona(HtmlSelectOneMenu txtZona) {
		this.txtZona = txtZona;
	}

	public HtmlSelectOneMenu getTxtFechaProceso() {
		return txtFechaProceso;
	}

	public void setTxtFechaProceso(HtmlSelectOneMenu txtFechaProceso) {
		this.txtFechaProceso = txtFechaProceso;
	}

	public SelectItem[] getLasNovedades() {
		lasNovedades = new SelectItem[3];		
		try {
			lasNovedades[0] = new SelectItem(-1, loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
					"lblOpcionSeleccionarCombo"));
			lasNovedades[1] = new SelectItem(0, loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
					"tipoAsociadosInhabiles"));
			lasNovedades[2] = new SelectItem(1, loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
					"tipoAsociadosHabiles"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lasNovedades;
	}

	public void setLasNovedades(SelectItem[] lasNovedades) {
		this.lasNovedades = lasNovedades;
	}

	public SelectItem[] getLasZonas() {
		List<FiltrosConsultasDTO> list = null;
		try {
			list = DelegadoFiltros.getInstance().consultarZonas();
			int i = 0;
			lasZonas = new SelectItem[list.size()+1];
			lasZonas[i] = new SelectItem(-1,loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
					"lblOpcionSeleccionarCombo"));
			i++;
			for (FiltrosConsultasDTO dto : list) {
				lasZonas[i] = new SelectItem(dto.getCodigoFiltro(), dto.getDescripcionFiltro());
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lasZonas;
	}

	public void setLasZonas(SelectItem[] lasZonas) {
		this.lasZonas = lasZonas;
	}

	public SelectItem[] getLasFechasProceso() {
		List<FiltrosConsultasDTO> list = null;
		try {
			list = DelegadoFiltros.getInstance().consultarFechasCortesProcesosProgramados();
			int i = 0;
			lasFechasProceso = new SelectItem[list.size()+1];
			lasFechasProceso[i] = new SelectItem(-1,loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
					"lblOpcionSeleccionarCombo"));
			
			i++;
			
			for (FiltrosConsultasDTO dto : list) {
				lasFechasProceso[i] = new SelectItem(dto.getCodigoFiltro(), dto.getDescripcionFiltro());
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lasFechasProceso;
	}

	public void setLasFechasProceso(SelectItem[] lasFechasProceso) {
		this.lasFechasProceso = lasFechasProceso;
	}

	public HtmlCommandButton getBtnGenerarReporte() {
		return btnGenerarReporte;
	}

	public void setBtnGenerarReporte(HtmlCommandButton btnGenerarReporte) {
		this.btnGenerarReporte = btnGenerarReporte;
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

	public Date getFechaCorte() {
		return fechaCorte;
	}

	public void setFechaCorte(Date fechaCorte) {
		this.fechaCorte = fechaCorte;
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
