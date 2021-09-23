package co.com.coomeva.ele.backbeans.cuociente;

import java.io.IOException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import co.com.coomeva.ele.backbeans.BaseVista;
import co.com.coomeva.ele.backbeans.selectFactory.SelectFactoryView;
import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.delegado.DelegadoCuocienteElectoral;
import co.com.coomeva.ele.entidades.cuociente.EleCuocienteDelegadosZona;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.util.acceso.UtilAcceso;

import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.context.effects.JavascriptContext;

public class DelegadosZona extends BaseVista {

	private HtmlInputText periodoElectoral;
	private HtmlSelectOneMenu codRegional;
	private HtmlSelectOneMenu codZona;
	private HtmlInputText sumaHabiles;
	private HtmlSelectOneMenu ordenarPor;

	private Double totalDirectos = 0d;
	private Double totalReciduo = 0d;

	private List<SelectItem> smRegionales;
	private List<SelectItem> smZonas;
	private List<SelectItem> smOrdenarPor;

	private List<EleCuocienteDelegadosZona> listaDelegadosZona;
	private EleCuocienteDelegadosZona cuocienteDelegadosZona;
	private boolean renderDescargarReporte = false;
	
	public DelegadosZona() {
		inicializacionComponentes();
		
	}

	public void inicializacionComponentes() {
		getPeriodoElectoral().setValue(UtilAcceso.getParametroFuenteS("parametros",
		"param.cod.periodo"));
		
		smRegionales = SelectFactoryView.getInstance()
				.listSelectItem_Regionales();
		smOrdenarPor = SelectFactoryView.getInstance()
				.cargarOpcionOrdenamiento(smOrdenarPor);

		if (codRegional != null && codRegional.getValue() == null) {
			codRegional.setValue(0l);

			smZonas = SelectFactoryView.getInstance().listSelectItem_Zonas(
					new Long(codRegional.getValue().toString()));
		} else {
			smZonas = SelectFactoryView.getInstance()
					.listSelectItem_Zonas(null);
		}
	}
	
	public String cuocienteDelegadosZona() {

		try {
			/**
			 * Se validan las variables requeridas
			 */
			if (periodoElectoral == null) {
				throw new Exception(UtilAcceso.getParametroFuenteS(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
						"campo.obligatorio.delegadoZona.periodoEelctoral"));
			}

			if (codRegional == null
					|| codRegional.equals(UtilAcceso.getParametroFuenteS(
							"parametros", "selectValueDefault"))) {
				throw new Exception(UtilAcceso.getParametroFuenteS(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
						"campo.obligatorio.delegadoZona.regional"));
			}

			if (codZona == null
					|| codZona.equals(UtilAcceso.getParametroFuenteS(
							"parametros", "selectValueDefault"))) {
				throw new Exception(UtilAcceso.getParametroFuenteS(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
						"campo.obligatorio.delegadoZona.zona"));
			}

			if (sumaHabiles == null) {
				throw new Exception(UtilAcceso.getParametroFuenteS(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
						"campo.obligatorio.delegadoZona.habiles"));
			}

			cuocienteDelegadosZona = DelegadoCuocienteElectoral.getInstance()
					.calcularDelegadosZona(
							periodoElectoral.getValue().toString(),
							codRegional.getValue().toString(),
							codZona.getValue().toString(),
							new Double(sumaHabiles.getValue().toString()));
			listaDelegadosZona = DelegadoCuocienteElectoral.getInstance()
					.consultarDelegadosZona(
							periodoElectoral.getValue().toString(),
							ordenarPor.getValue().toString());
			renderDescargarReporte = true;

		} catch (NumberFormatException e) {
			String mensaje = e.getMessage();
			getMensaje().mostrarMensaje(mensaje);
		} catch (Exception e) {
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes",
						"nullException");
			}
			getMensaje().mostrarMensaje(mensaje);
		}
		return null;
	}

	public void listener_ordenadoPor(ValueChangeEvent changeEvent) {
		if (changeEvent != null && changeEvent.getNewValue() != null) {
			consultar_delegados_zona();
		}
	}

	public String consultar_delegados_zona() {

		try {
			/**
			 * Se validan las variables requeridas
			 */
			if (periodoElectoral == null || periodoElectoral.getValue() == null) {
				throw new Exception(UtilAcceso.getParametroFuenteS(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
						"campo.obligatorio.delegadoZona.periodoEelctoral"));
			}

			listaDelegadosZona = DelegadoCuocienteElectoral.getInstance()
					.consultarDelegadosZona(
							periodoElectoral.getValue().toString(),
							ordenarPor.getValue().toString());
			renderDescargarReporte = true;

		} catch (NumberFormatException e) {
			String mensaje = e.getMessage();
			getMensaje().mostrarMensaje(mensaje);
		} catch (Exception e) {
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes",
						"nullException");
			}
			getMensaje().mostrarMensaje(mensaje);
		}
		return null;
	}

	public void listener_zonas(ValueChangeEvent event) {
		if (event != null && event.getNewValue() != null) {
			smZonas = SelectFactoryView.getInstance().listSelectItem_Zonas(
					new Long(event.getNewValue().toString()));
			codRegional.setValue(event.getNewValue());
		}
	}

	/**
	 * Se consulta el número de asociados hábiles a la fecha que correspondan a la 
	 * regional y zib 
	 * @param event
	 */
	public void listener_delegados_zona(ValueChangeEvent event) {

		try {
			if (event != null && event.getNewValue() != null) {

				sumaHabiles.setValue(DelegadoAsociado.getInstance().totalAsociadosPorZona(
						new Long(event.getNewValue().toString())).intValue());
			}
		} catch (Exception e) {
			getMensaje().mostrarMensaje(e.getMessage());
		}
	}

	public String calcular_delegados() {

		try {
			/**
			 * Se validan las variables requeridas
			 */
			if (periodoElectoral == null) {
				throw new Exception(UtilAcceso.getParametroFuenteS(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
						"campo.obligatorio.delegadoZona.periodoEelctoral"));
			}
			listaDelegadosZona = DelegadoCuocienteElectoral.getInstance()
					.calcularDelegadosZonaFinal(
							periodoElectoral.getValue().toString());
			
			if( listaDelegadosZona != null && listaDelegadosZona.size() > 0){
				renderDescargarReporte = true;
			}

		} catch (NumberFormatException e) {
			String mensaje = e.getMessage();
			getMensaje().mostrarMensaje(mensaje);
		} catch (Exception e) {
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes",
						"nullException");
			}
			getMensaje().mostrarMensaje(mensaje);
		}
		return null;
	}

	public String actionLimpiar() {

		periodoElectoral = null;
		codRegional = null;
		codZona = null;
		sumaHabiles = null;
		renderDescargarReporte = false;
		
		return null;
	}
	
	public String action_generarReporte(){
		try {
			FacesUtils.setSessionParameter("delegadosZona", listaDelegadosZona);
			JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(),"ServletReporteDelegadosZona();");
		} catch (Exception e) {
			getMensaje().mostrarMensaje(e.getMessage());
		}
		return "";
	}

	public HtmlInputText getPeriodoElectoral() {
		if( periodoElectoral == null ){
			periodoElectoral = new HtmlInputText();
		}
		return periodoElectoral;
	}

	public void setPeriodoElectoral(HtmlInputText periodoElectoral) {
		this.periodoElectoral = periodoElectoral;
	}

	public HtmlSelectOneMenu getCodRegional() {
		return codRegional;
	}

	public void setCodRegional(HtmlSelectOneMenu codRegional) {
		this.codRegional = codRegional;
	}

	public HtmlSelectOneMenu getCodZona() {
		return codZona;
	}

	public void setCodZona(HtmlSelectOneMenu codZona) {
		this.codZona = codZona;
	}

	public HtmlInputText getSumaHabiles() {
		return sumaHabiles;
	}

	public void setSumaHabiles(HtmlInputText sumaHabiles) {
		this.sumaHabiles = sumaHabiles;
	}

	public List<SelectItem> getSmRegionales() {
		return smRegionales;
	}

	public void setSmRegionales(List<SelectItem> smRegionales) {
		this.smRegionales = smRegionales;
	}

	public List<SelectItem> getSmZonas() {
		return smZonas;
	}

	public void setSmZonas(List<SelectItem> smZonas) {
		this.smZonas = smZonas;
	}

	public List<EleCuocienteDelegadosZona> getListaDelegadosZona() {
		return listaDelegadosZona;
	}

	public void setListaDelegadosZona(
			List<EleCuocienteDelegadosZona> listaDelegadosZona) {
		this.listaDelegadosZona = listaDelegadosZona;
	}

	public EleCuocienteDelegadosZona getCuocienteDelegadosZona() {
		return cuocienteDelegadosZona;
	}

	public void setCuocienteDelegadosZona(
			EleCuocienteDelegadosZona cuocienteDelegadosZona) {
		this.cuocienteDelegadosZona = cuocienteDelegadosZona;
	}

	public Double getTotalDirectos() {
		return totalDirectos;
	}

	public void setTotalDirectos(Double totalDirectos) {
		this.totalDirectos = totalDirectos;
	}

	public Double getTotalReciduo() {
		return totalReciduo;
	}

	public void setTotalReciduo(Double totalReciduo) {
		this.totalReciduo = totalReciduo;
	}

	public HtmlSelectOneMenu getOrdenarPor() {
		return ordenarPor;
	}

	public void setOrdenarPor(HtmlSelectOneMenu ordenarPor) {
		this.ordenarPor = ordenarPor;
	}

	public List<SelectItem> getSmOrdenarPor() {
		return smOrdenarPor;
	}

	public void setSmOrdenarPor(List<SelectItem> smOrdenarPor) {
		this.smOrdenarPor = smOrdenarPor;
	}

	public boolean isRenderDescargarReporte() {
		return renderDescargarReporte;
	}

	public void setRenderDescargarReporte(boolean renderDescargarReporte) {
		this.renderDescargarReporte = renderDescargarReporte;
	}
	
	
}
