package co.com.coomeva.ele.backbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import co.com.coomeva.ele.delegado.DelegadoJuego;
import co.com.coomeva.ele.modelo.EleZonaElectoralDTO;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.util.resources.LoaderResourceElements;
import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.context.effects.JavascriptContext;

public class GenerarReportesJuegosVista extends BaseVista {

	private HtmlCommandButton btnGenerarReporte;
	private HtmlSelectOneMenu selZona;
	private List<SelectItem> itemsZonas;
	private List<EleZonaElectoralDTO> zonasElectorales;
	private HtmlSelectOneMenu selFase;
	private List<SelectItem> itemsFases;

	private LoaderResourceElements loaderResourceElements = LoaderResourceElements
			.getInstance();

	private boolean visibleConfirmar;

	public GenerarReportesJuegosVista() {

	}

	public String action_limpiar() {
		limpiar();
		return "";
	}

	public void limpiar() {

		FacesUtils.getSession().removeAttribute("reporteParam");
		FacesUtils.getSession().removeAttribute(
				"eleccionesJuegoZonaElectoral");
		FacesUtils.getSession().removeAttribute("eleccionesJuegoFase");
	}

	public String action_generarReporte() {
		try {
			FacesUtils.getSession().setAttribute(
					"eleccionesJuegoZonaElectoral", selZona.getValue().toString());
			FacesUtils.getSession().setAttribute(
					"eleccionesJuegoFase", selFase.getValue().toString());
			JavascriptContext.addJavascriptCall(FacesContext
					.getCurrentInstance(), "servletReportesJuego();");
		} catch (Exception e) {
			getMensaje().mostrarMensaje(e.getMessage());
		}
		return "";
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

	public String action_verConfirmacion() {
		visibleConfirmar = true;
		return "";
	}

	public String action_closeConfirmar() {
		visibleConfirmar = false;
		return "";
	}

	public HtmlSelectOneMenu getSelZona() {
		cargarZonasElectorales();
		return selZona;
	}

	public void setSelZona(HtmlSelectOneMenu selZona) {
		this.selZona = selZona;
	}

	public List<SelectItem> getItemsZonas() {
		return itemsZonas;
	}

	public void setItemsEstados(List<SelectItem> itemsZonas) {
		this.itemsZonas = itemsZonas;
	}

	public HtmlSelectOneMenu getSelFase() {
		cargarFases();
		return selFase;
	}

	public void setSelFase(HtmlSelectOneMenu selFase) {
		this.selFase = selFase;
	}

	public List<SelectItem> getItemsFases() {
		return itemsFases;
	}

	public void setItemsFases(List<SelectItem> itemsFases) {
		this.itemsFases = itemsFases;
	}

	public void cargarZonasElectorales() {

		zonasElectorales = null;
		itemsZonas = new ArrayList<SelectItem>();

		try {
			zonasElectorales = new ArrayList<EleZonaElectoralDTO>();
			zonasElectorales = DelegadoJuego.getInstance()
					.consultarZonasElectorales();

			if (!(zonasElectorales == null || zonasElectorales.size() == 0)) {

				if (zonasElectorales.size() > 1) {
					itemsZonas.add(new SelectItem("-1", "- Todas -"));

					for (EleZonaElectoralDTO dto : zonasElectorales) {
						itemsZonas.add(new SelectItem(dto.getCodigo(), dto
								.getDescripcion()));
					}
				} else {
					EleZonaElectoralDTO dto = (EleZonaElectoralDTO) zonasElectorales
							.get(0);
					itemsZonas.add(new SelectItem(dto.getCodigo(), dto
							.getDescripcion()));
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void cargarFases() {
		itemsFases = new ArrayList<SelectItem>();
		try {
			itemsFases.add(new SelectItem("-1", "- Todas -"));
			itemsFases.add(new SelectItem("1", "1"));
			itemsFases.add(new SelectItem("2", "2"));
			itemsFases.add(new SelectItem("3", "3"));
			itemsFases.add(new SelectItem("4", "4"));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
