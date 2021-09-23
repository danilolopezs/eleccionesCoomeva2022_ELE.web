package co.com.coomeva.ele.backbeans;


import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import co.com.coomeva.ele.delegado.DelegadoFiltros;
import co.com.coomeva.ele.modelo.FiltrosConsultasDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.util.resources.LoaderResourceElements;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.ext.HtmlSelectOneRadio;

public class ConsultaHabilidadAsociadosVista {
	
	private HtmlSelectOneMenu txtZona;
	private HtmlInputText txtCodProfesion;
	private HtmlInputText txtProfesion;
	private HtmlInputText txtNumeroDocumento;
	private HtmlSelectOneRadio txtTipoReporte;
	
	private SelectItem[] lasZonas;
	private SelectItem[] losTipoReportes;
	private List<FiltrosConsultasDTO> listProfesiones = null;
	
	private HtmlCommandButton btnConsultar;
	private HtmlCommandButton btnLimpiar;
	private HtmlCommandButton btnGenerarReporte;
	
	private HtmlCommandButton btnSeleccionarProfesion;
	private HtmlCommandButton btnConsultarProfesion;
	private HtmlCommandButton btnLimpiarProfesion;
	
	private Boolean popupVisible = false;
	
	private LoaderResourceElements loaderResourceElements = LoaderResourceElements.getInstance();
	
	
	
	public ConsultaHabilidadAsociadosVista() {
		
	}
	
	public String action_limpiar(){
		limpiar();
		return "";
	}
	
	public void limpiar(){
		txtZona.setValue(0);
		limpiarProfesion();
		txtNumeroDocumento.setValue(null);
		txtTipoReporte.setValue(0);
		btnGenerarReporte.setDisabled(true);
		
		FacesUtils.getSession().removeAttribute("reporteParam");
		FacesUtils.getSession().removeAttribute("tipoReporte");
		FacesUtils.getSession().removeAttribute("zonaAsociado");
		FacesUtils.getSession().removeAttribute("profesionAsociado");
		FacesUtils.getSession().removeAttribute("numIdAsociado");
	}
	
	public String action_limpiarProfesion(){
		limpiarProfesion();
		return "";
	}
	
	public void limpiarProfesion(){
		txtProfesion.setValue(null);
		txtProfesion.setDisabled(false);
		txtCodProfesion.setValue(null);
		txtCodProfesion.setDisabled(false);
		btnConsultarProfesion.setDisabled(false);
	}
	
	public String action_consultar(){
		btnGenerarReporte.setDisabled(false);
		return "";
	}
	
	public void listener_generarParametrosReportes(ActionEvent event){
		try {
			FacesUtils.getSession().setAttribute("reporteParam", loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
					"reporte_habiles"));
						
			if (FacesUtils.checkLong(txtTipoReporte, loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"tipoReporteHabilidadExcel")) == 0) {
				FacesUtils.getSession().setAttribute("tipoReporte", loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
						"tipoReporteHabilidadExcel").toString());
			}else{
				FacesUtils.getSession().setAttribute("tipoReporte", loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
						"tipoReporteHabilidadPdf").toString());
			}
			
			FacesUtils.getSession().setAttribute("zonaAsociado", FacesUtils.checkLong(txtZona, 
					loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblZonaElectoral")));
			FacesUtils.getSession().setAttribute("profesionAsociado", FacesUtils.checkLong(txtCodProfesion, 
					loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteProfesion")));
			FacesUtils.getSession().setAttribute("numIdAsociado", FacesUtils.checkLong(txtNumeroDocumento, 
					loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblTipoHabilidad")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String action_seleccionarProfesion(){
		try {
			listProfesiones = DelegadoFiltros.getInstance().consultarProfesiones(FacesUtils.checkLong(txtCodProfesion, 
					loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblCodigoCol")), 
					FacesUtils.checkString(txtProfesion,
							loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblNombre")));
			action_openPopup();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return"";
	}
	
	public void listener_seleccionarProfesion(ActionEvent event){
		//txtCodProfesion.setValue(FacesUtils.getRequestParameter("codProf"));
		txtCodProfesion.setValue(event.getComponent().getAttributes().get("codProf"));
		txtCodProfesion.setDisabled(true);
		//txtProfesion.setValue(FacesUtils.getRequestParameter("nomProf"));
		txtProfesion.setValue(event.getComponent().getAttributes().get("nomProf"));
		txtProfesion.setDisabled(true);
		btnConsultarProfesion.setDisabled(false);
		action_closePopup();
	}
	
	public String action_closePopup(){
    	popupVisible = false;
    	listProfesiones = null;
    	return "";
    }
   
    public String action_openPopup(){
        popupVisible = true;
        return "";
    }

	public HtmlSelectOneMenu getTxtZona() {
		return txtZona;
	}

	public void setTxtZona(HtmlSelectOneMenu txtZona) {
		this.txtZona = txtZona;
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

	public HtmlCommandButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(HtmlCommandButton btnConsultar) {
		this.btnConsultar = btnConsultar;
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

	public HtmlInputText getTxtProfesion() {
		return txtProfesion;
	}

	public void setTxtProfesion(HtmlInputText txtProfesion) {
		this.txtProfesion = txtProfesion;
	}

	public HtmlInputText getTxtNumeroDocumento() {
		return txtNumeroDocumento;
	}

	public void setTxtNumeroDocumento(HtmlInputText txtNumeroDocumento) {
		this.txtNumeroDocumento = txtNumeroDocumento;
	}

	public List<FiltrosConsultasDTO> getListProfesiones() {
		return listProfesiones;
	}

	public void setListProfesiones(List<FiltrosConsultasDTO> listProfesiones) {
		this.listProfesiones = listProfesiones;
	}

	public HtmlInputText getTxtCodProfesion() {
		return txtCodProfesion;
	}

	public void setTxtCodProfesion(HtmlInputText txtCodProfesion) {
		this.txtCodProfesion = txtCodProfesion;
	}

	public HtmlCommandButton getBtnSeleccionarProfesion() {
		return btnSeleccionarProfesion;
	}

	public void setBtnSeleccionarProfesion(HtmlCommandButton btnSeleccionarProfesion) {
		this.btnSeleccionarProfesion = btnSeleccionarProfesion;
	}

	public HtmlCommandButton getBtnConsultarProfesion() {
		return btnConsultarProfesion;
	}

	public void setBtnConsultarProfesion(HtmlCommandButton btnConsultarProfesion) {
		this.btnConsultarProfesion = btnConsultarProfesion;
	}

	public HtmlCommandButton getBtnLimpiarProfesion() {
		return btnLimpiarProfesion;
	}

	public void setBtnLimpiarProfesion(HtmlCommandButton btnLimpiarProfesion) {
		this.btnLimpiarProfesion = btnLimpiarProfesion;
	}

	public Boolean getPopupVisible() {
		return popupVisible;
	}

	public void setPopupVisible(Boolean popupVisible) {
		this.popupVisible = popupVisible;
	}
	
	
}
