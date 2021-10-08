package co.com.coomeva.ele.backbeans;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.util.resources.LoaderResourceElements;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.ext.HtmlSelectOneRadio;
import com.icesoft.faces.context.effects.JavascriptContext;

public class GenerarReportesResumenHabilidades extends BaseVista {

	private HtmlSelectOneRadio txtTipoReporte;

	private SelectItem[] losTipoReportes;

	private HtmlCommandButton btnLimpiar;
	private HtmlCommandButton btnGenerarReporte;

	private LoaderResourceElements loaderResourceElements = LoaderResourceElements.getInstance();

	private boolean visibleConfirmar;

	public GenerarReportesResumenHabilidades() {

	}

	public String action_limpiar() {
		limpiar();
		return "";
	}

	public void limpiar() {
		txtTipoReporte.setValue(null);

		FacesUtils.getSession().removeAttribute("reporteParam");
		FacesUtils.getSession().removeAttribute("tipoReporte");
	}

	public String action_generarReporte() {
		try {

			action_closeConfirmar();

			if (txtTipoReporte.getValue() == null) {
				throw new Exception(loaderResourceElements.getKeyResourceValue(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL, "mensaje.tipo.reporte"));
			}

			if (FacesUtils.checkLong(txtTipoReporte, loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "tipoReporteHabilidadExcel")) == 0) {
				FacesUtils.getSession().setAttribute("tipoReporteRes",
						loaderResourceElements
								.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
										"tipoReporteHabilidadExcel")
								.toString());
			} else {
				FacesUtils.getSession().setAttribute("tipoReporteRes",
						loaderResourceElements
								.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
										"tipoReporteHabilidadPdf")
								.toString());
			}

			JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "ServletReportesResumenHab();");
		} catch (Exception e) {
			getMensaje().mostrarMensaje(e.getMessage());
		}
		return "";
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
			losTipoReportes[0] = new SelectItem(0, loaderResourceElements
					.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblGenerarExcel"));
			losTipoReportes[1] = new SelectItem(1, loaderResourceElements
					.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblGenerarPdf"));
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

	public void setLoaderResourceElements(LoaderResourceElements loaderResourceElements) {
		this.loaderResourceElements = loaderResourceElements;
	}

	public boolean isVisibleConfirmar() {
		return visibleConfirmar;
	}

	public void setVisibleConfirmar(boolean visibleConfirmar) {
		this.visibleConfirmar = visibleConfirmar;
	}

	public String action_verConfirmacion() {
		visibleConfirmar = true;
		return "";
	}

	public String action_closeConfirmar() {
		visibleConfirmar = false;
		return "";
	}

}
