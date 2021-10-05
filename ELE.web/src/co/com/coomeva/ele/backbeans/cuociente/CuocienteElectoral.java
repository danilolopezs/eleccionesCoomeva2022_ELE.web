package co.com.coomeva.ele.backbeans.cuociente;

import java.util.List;

import javax.faces.context.FacesContext;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.context.effects.JavascriptContext;

import co.com.coomeva.ele.backbeans.BaseVista;
import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.delegado.DelegadoCuocienteElectoral;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoZonaElectoral;
import co.com.coomeva.ele.entidades.cuociente.EleCuocienteDelegadosZona;
import co.com.coomeva.ele.entidades.cuociente.EleCuocienteElectoral;
import co.com.coomeva.ele.entidades.cuociente.EleCuocienteRegional;
import co.com.coomeva.ele.modelo.EleZonaElectoralRegionalDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.util.acceso.UtilAcceso;

public class CuocienteElectoral extends BaseVista {

	// Elementos html
	
	private HtmlInputText txtPeriodoElectoral;
	private HtmlInputText txtTotalAsocHabiles;
	private HtmlInputText txtTotalDelegadosElegir;
	private HtmlInputText txtTotalDelegadosEspeciales;
	private HtmlInputText txtFinalTotalDelegadosElegir;
	private HtmlInputText txtCuocienteElectoral;
	private HtmlCommandButton btnCrearActualizar;
	
	private String idRegistro;
	private String periodoElectoral;
	private String totalAsocHabiles;
	private String totalDelegadosElegir;
	private String totalDelegadosEspeciales;
	private String finalTotalDelegadosElegir;
	private String cuocienteElectoral;
	private List<EleCuocienteDelegadosZona> listaDelegadosZona;
	private List<EleCuocienteRegional> listaDelegadosRegional;
	private EleCuocienteElectoral cuocienteElectoralObj;
	private boolean renderDescargarReporte = false;
	
	
	public CuocienteElectoral(){
		
		txtPeriodoElectoral = new HtmlInputText();
		txtTotalAsocHabiles = new HtmlInputText();
		txtTotalDelegadosElegir = new HtmlInputText();
		txtTotalDelegadosEspeciales = new HtmlInputText();
		txtFinalTotalDelegadosElegir = new HtmlInputText();
		txtCuocienteElectoral = new HtmlInputText();
		btnCrearActualizar = new HtmlCommandButton();
		
		periodoElectoral = UtilAcceso.getParametroFuenteS("parametros",
		"param.cod.periodo"); 
		
		//totalDelegadosElegir = "0";
		//totalDelegadosEspeciales = "0";
		//finalTotalDelegadosElegir = "0";
	}
	
	// No se esta usando este metodo, se busca automaticamente los asociados habiles, al momento de crear un cuociente
	/*public String consultarAsociadosHabiles() {
		try {
			int totalHabiles = DelegadoAsociado.getInstance().consultarTotalAsociadosHabilesAsociado(null);
			totalAsocHabiles = String.valueOf(totalHabiles);
		} catch (Exception e) {
			totalAsocHabiles = "0";
		}
		return "";
	}*/
	
	public String consultarCuociente() {
		
		
		if(periodoElectoral!= null && !periodoElectoral.isEmpty() && periodoElectoral.trim().length()==4 ){
			String periodo = periodoElectoral;
			actionLimpiar();
			periodoElectoral = periodo;
			consultarCuociente(periodoElectoral);
		} else {
			getMensaje().mostrarMensaje(UtilAcceso.getParametroFuenteS("mensajes",
			"campo.obligatorio.cuociente.periodo"));
		}
		
		
		return "";
	}
	
	// Cargar informacion cuociente, lista delegados x regional y lista delegados x zona 
	public void consultarCuociente(String periodo) {
		
		Boolean recalcular = Boolean.valueOf(UtilAcceso.getParametroFuenteS("parametros",
		"param.cuociente.recalculo")); 
		
		periodo = periodo.trim();
		
		cuocienteElectoralObj = DelegadoCuocienteElectoral.getInstance().getConsultarCuocienteElectoral(periodo);
		if( cuocienteElectoralObj != null ){
			
			idRegistro = cuocienteElectoralObj.getIdRegistro().toString();
			totalAsocHabiles = cuocienteElectoralObj.getTotalAsocHabiles().toString();
			totalDelegadosElegir = cuocienteElectoralObj.getTotalDelegadosElegir().toString();
			totalDelegadosEspeciales = cuocienteElectoralObj.getTotalDelegadosEspeciales().toString();
			finalTotalDelegadosElegir = cuocienteElectoralObj.getFinalTotalDelegadosElegir().toString();
			cuocienteElectoral = cuocienteElectoralObj.getCuocienteElectoral().toString();
			
			try {
				listaDelegadosZona = DelegadoCuocienteElectoral.getInstance().consultarDelegadosZona(periodo, "fraccion");
				listaDelegadosRegional = DelegadoCuocienteElectoral.getInstance().consultarDelegadosRegionales(periodo, "relacionDelegados");
				
				renderDescargarReporte = true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				int totalHabiles = DelegadoAsociado.getInstance().consultarTotalAsociadosHabilesAsociado(null);
				totalAsocHabiles = String.valueOf(totalHabiles);
				renderDescargarReporte = false;
			} catch (Exception e) {
				totalAsocHabiles = "0";
			}
		}
		if(recalcular){
			txtTotalDelegadosElegir.setDisabled(false);
			txtTotalDelegadosEspeciales.setDisabled(false);
			//txtFinalTotalDelegadosElegir.setDisabled(false);
			btnCrearActualizar.setDisabled(false);
		} else {
			txtTotalDelegadosElegir.setDisabled(true);
			txtTotalDelegadosEspeciales.setDisabled(true);
			btnCrearActualizar.setDisabled(true);
		}
	}
	
	public String crearCuociente() {

		try {
			validaciones();			
			finalTotalDelegadosElegir = "0";
			cuocienteElectoral = "0";
			
			// Si ya existe un cuociente electoral y se va a recalcular, se borra todo lo de el periodo y se calcula nuevamente.
			cuocienteElectoralObj = DelegadoCuocienteElectoral.getInstance().getConsultarCuocienteElectoral(periodoElectoral);
			if( cuocienteElectoralObj != null ){
				eliminarCuociente(cuocienteElectoralObj);
			}
			
			cuocienteElectoralObj = DelegadoCuocienteElectoral.getInstance()
					.calcularCuocienteElectoral(null, periodoElectoral,
							new Double(totalAsocHabiles),
							new Double(totalDelegadosElegir),
							new Double(totalDelegadosEspeciales),
							new Double(finalTotalDelegadosElegir), new Double(cuocienteElectoral));
			
			finalTotalDelegadosElegir = cuocienteElectoralObj.getFinalTotalDelegadosElegir().toString();
			cuocienteElectoral = cuocienteElectoralObj.getCuocienteElectoral().toString();
			
			// Crear la matriz de delegados por zonas.
			calcularDelegados(periodoElectoral);
			
			// Cargar informacion cuociente delegados. lista delegados regional, lista delegados zona
			consultarCuociente(periodoElectoral);
				
		}catch (NumberFormatException e) {
			e.printStackTrace();
			//String mensaje = e.getMessage();
			getMensaje().mostrarMensaje(UtilAcceso.getParametroFuenteS("mensajes", "noNumeroLong"));
		}catch (Exception e) {
			e.printStackTrace();
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes",
						"nullException");
			}
			getMensaje().mostrarMensaje(mensaje);
		}
		return "";
	}
	
	private void validaciones() throws Exception {
		if(periodoElectoral== null || periodoElectoral.trim().isEmpty() || periodoElectoral.trim().length()!=4 ){
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.periodoEelctoral"));
		}
			
		if(totalDelegadosElegir==null || totalDelegadosElegir.trim().isEmpty()){
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.cuociente.totalDelegadosElegir"));
		}
		
		if (totalDelegadosEspeciales==null || totalDelegadosEspeciales.trim().isEmpty()){
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.cuociente.totalDelegadosEspeciales"));
		}
	}
	
	private void eliminarCuociente(EleCuocienteElectoral eleCuocienteElectoral) {
		
		try {
			DelegadoCuocienteElectoral.getInstance().eliminarCuocienteElectoral(eleCuocienteElectoral);
		} catch (Exception e) {
			e.printStackTrace();
			getMensaje().mostrarMensaje(e.getMessage());
		}
		
	}

	public String action_generarReporte(){
		try {
			FacesUtils.setSessionParameter("delegadosZona", listaDelegadosZona);
			FacesUtils.setSessionParameter("delegadosRegional", listaDelegadosRegional);
			FacesUtils.setSessionParameter("cuociente", cuocienteElectoralObj);
			JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(),"ServletReporteDelegadosZona();");
		} catch (Exception e) {
			getMensaje().mostrarMensaje(e.getMessage());
		}
		return "";
	}
	
	
	/**
	 * @author <a href="mailto:julianaa_coomeva.com.co">Juliana Nobile</a> - Coomeva <br>
	 * calcula la segunda parte de la matriz de delegados x zonas electorales.
	 * @param periodoElectoral 
	 * @return void
	 */
	public void calcularDelegadosResiduo(String periodo) {

		try {
			if (periodo == null) {
				throw new Exception(UtilAcceso.getParametroFuenteS(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
						"campo.obligatorio.delegadoZona.periodoEelctoral"));
			}
			
			DelegadoCuocienteElectoral.getInstance()
					.calcularDelegadosZonaFinal(periodo);
			
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
	}
	
	/**
	 * @author <a href="mailto:julianaa_coomeva.com.co">Juliana Nobile</a> - Coomeva <br>
	 * calcula la primera parte de la matriz de delegados x zonas electorales.
	 * @param periodoElectoral 
	 * @return void
	 */
	private void calcularDelegados(String periodoElectoral) throws Exception {
		
		// Consultar las zonas
		List<EleZonaElectoralRegionalDTO> listadoZonas = null;
		
		listadoZonas = DelegadoZonaElectoral.getInstance().consultarCodigosZonasElectoralesRegionales();
		
		//System.out.println("Tamaño lista:" + listadoZonas.size());
		
		// Recorrer las zonas
	
		for (EleZonaElectoralRegionalDTO zonaElectoralRegionalDTO : listadoZonas) {
			//System.out.println("Regional: "+zonaElectoralRegionalDTO.getCodigoRegional() + " - Zona: " + zonaElectoralRegionalDTO.getCodigoZona());
			Double sumaHabilesZona = null; // El calculo se hace en el metodo calcularDelegadosZona

			// crear cuocienteDelegadosZona por cada zona
			DelegadoCuocienteElectoral.getInstance().calcularDelegadosZona(
					periodoElectoral,
					zonaElectoralRegionalDTO.getCodigoRegional().toString(),
					zonaElectoralRegionalDTO.getCodigoZona().toString(),sumaHabilesZona);
		}
		// TODO FIRST
		// Completar la matriz de delegados por zonas con los delegados del residuo y creaa matriz delegados por regionales.
		DelegadoCuocienteElectoral.getInstance().calcularDelegadosZonaFinal(periodoElectoral);

	}
	
	public String actionLimpiar(){
		idRegistro = null;
		periodoElectoral = UtilAcceso.getParametroFuenteS("parametros",
				"param.cod.periodo");
		totalAsocHabiles = null;
		totalDelegadosElegir = null;
		totalDelegadosEspeciales = null;
		finalTotalDelegadosElegir = null;
		cuocienteElectoral = null;
		
		txtTotalAsocHabiles.setDisabled(true);
		txtTotalDelegadosElegir.setDisabled(true);
		txtTotalDelegadosEspeciales.setDisabled(true);
		txtFinalTotalDelegadosElegir.setDisabled(true);
		txtCuocienteElectoral.setDisabled(true);
		
		listaDelegadosZona = null;
		listaDelegadosRegional = null;		
		
		return null;
	}


	public String getIdRegistro() {
		return idRegistro;
	}


	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
	}


	public String getPeriodoElectoral() {
		return periodoElectoral;
	}


	public void setPeriodoElectoral(String periodoElectoral) {
		this.periodoElectoral = periodoElectoral;
	}


	public String getTotalAsocHabiles() {
		return totalAsocHabiles;
	}


	public void setTotalAsocHabiles(String totalAsocHabiles) {
		this.totalAsocHabiles = totalAsocHabiles;
	}


	public String getTotalDelegadosElegir() {
		return totalDelegadosElegir;
	}


	public void setTotalDelegadosElegir(String totalDelegadosElegir) {
		this.totalDelegadosElegir = totalDelegadosElegir;
	}


	public String getTotalDelegadosEspeciales() {
		return totalDelegadosEspeciales;
	}


	public void setTotalDelegadosEspeciales(String totalDelegadosEspeciales) {
		this.totalDelegadosEspeciales = totalDelegadosEspeciales;
	}


	public String getFinalTotalDelegadosElegir() {
		return finalTotalDelegadosElegir;
	}


	public void setFinalTotalDelegadosElegir(String finalTotalDelegadosElegir) {
		this.finalTotalDelegadosElegir = finalTotalDelegadosElegir;
	}


	public String getCuocienteElectoral() {
		return cuocienteElectoral;
	}


	public void setCuocienteElectoral(String cuocienteElectoral) {
		this.cuocienteElectoral = cuocienteElectoral;
	}

	public EleCuocienteElectoral getCuocienteElectoralObj() {
		return cuocienteElectoralObj;
	}


	public void setCuocienteElectoralObj(EleCuocienteElectoral cuocienteElectoralObj) {
		this.cuocienteElectoralObj = cuocienteElectoralObj;
	}

	public HtmlInputText getTxtPeriodoElectoral() {
		return txtPeriodoElectoral;
	}

	public void setTxtPeriodoElectoral(HtmlInputText txtPeriodoElectoral) {
		this.txtPeriodoElectoral = txtPeriodoElectoral;
	}

	public HtmlInputText getTxtTotalAsocHabiles() {
		return txtTotalAsocHabiles;
	}

	public void setTxtTotalAsocHabiles(HtmlInputText txtTotalAsocHabiles) {
		this.txtTotalAsocHabiles = txtTotalAsocHabiles;
	}

	public HtmlInputText getTxtTotalDelegadosElegir() {
		return txtTotalDelegadosElegir;
	}

	public void setTxtTotalDelegadosElegir(HtmlInputText txtTotalDelegadosElegir) {
		this.txtTotalDelegadosElegir = txtTotalDelegadosElegir;
	}

	public HtmlInputText getTxtTotalDelegadosEspeciales() {
		return txtTotalDelegadosEspeciales;
	}

	public void setTxtTotalDelegadosEspeciales(
			HtmlInputText txtTotalDelegadosEspeciales) {
		this.txtTotalDelegadosEspeciales = txtTotalDelegadosEspeciales;
	}

	public HtmlInputText getTxtFinalTotalDelegadosElegir() {
		return txtFinalTotalDelegadosElegir;
	}

	public void setTxtFinalTotalDelegadosElegir(
			HtmlInputText txtFinalTotalDelegadosElegir) {
		this.txtFinalTotalDelegadosElegir = txtFinalTotalDelegadosElegir;
	}

	public HtmlInputText getTxtCuocienteElectoral() {
		return txtCuocienteElectoral;
	}

	public void setTxtCuocienteElectoral(HtmlInputText txtCuocienteElectoral) {
		this.txtCuocienteElectoral = txtCuocienteElectoral;
	}

	public HtmlCommandButton getBtnCrearActualizar() {
		return btnCrearActualizar;
	}

	public void setBtnCrearActualizar(HtmlCommandButton btnCrearActualizar) {
		this.btnCrearActualizar = btnCrearActualizar;
	}

	public List<EleCuocienteDelegadosZona> getListaDelegadosZona() {
		return listaDelegadosZona;
	}

	public void setListaDelegadosZona(
			List<EleCuocienteDelegadosZona> listaDelegadosZona) {
		this.listaDelegadosZona = listaDelegadosZona;
	}

	public List<EleCuocienteRegional> getListaDelegadosRegional() {
		return listaDelegadosRegional;
	}

	public void setListaDelegadosRegional(
			List<EleCuocienteRegional> listaDelegadosRegional) {
		this.listaDelegadosRegional = listaDelegadosRegional;
	}

	public boolean isRenderDescargarReporte() {
		return renderDescargarReporte;
	}

	public void setRenderDescargarReporte(boolean renderDescargarReporte) {
		this.renderDescargarReporte = renderDescargarReporte;
	}
	
}
