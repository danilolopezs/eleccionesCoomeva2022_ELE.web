package co.com.coomeva.ele.formatos.pdf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;

import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.cooomeva.uti.util.pdf.UtilPDF;

import com.icesoft.faces.context.effects.JavascriptContext;

/**
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
 * @project ELE.web
 * @class AbstractFormatoPdf
 * @date 2/12/2012
 *
 */
public abstract class AbstractFormatoPdf {

	private String urlUbicacionPlantillas;
	private String urlDestino;

	private HashMap<String, String> valores;

	public AbstractFormatoPdf() {
		this.valores = new HashMap<String, String>();

		try {

			this.urlUbicacionPlantillas = UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"param.url.ubicacion.plantillas");

			this.urlDestino = UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"param.url.destino.reportes");

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public abstract void setearValorMapaReporte(Object...params) throws Exception;

	public abstract void generarReporte(Object...params) throws Exception;

	public void generarReportePDF(String nombrePlantilla, String nombreArchivo) {

		try {
			String realpath = FacesUtils.getServletContext().getRealPath("gui");
			new UtilPDF().generarPDF(realpath + this.urlUbicacionPlantillas,
					nombrePlantilla, nombreArchivo + ".pdf", valores);

			String url = FacesUtils.getUrlAplicacion() + this.urlDestino
					+ nombreArchivo + ".pdf";
			JavascriptContext.addJavascriptCall(FacesContext
					.getCurrentInstance(), "window.open('" + url + "');");
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public HashMap<String, String> getValores() {
		return valores;
	}

	public void setValores(HashMap<String, String> valores) {
		this.valores = valores;
	}

	public String getUrlUbicacionPlantillas() {
		return urlUbicacionPlantillas;
	}

	public void setUrlUbicacionPlantillas(String urlUbicacionPlantillas) {
		this.urlUbicacionPlantillas = urlUbicacionPlantillas;
	}

	public String getUrlDestino() {
		return urlDestino;
	}

	public void setUrlDestino(String urlDestino) {
		this.urlDestino = urlDestino;
	}
	
	public void setearMultilinea(String msg,String sufijo,int tamnioLinea) throws Exception{		
		if(msg != null &&  msg.length() > 0){
			String[] cadenas = msg.split("\\s+");
			List<StringBuffer> listCadenas = new ArrayList<StringBuffer>();		
			int tamanioLinea = 0;		
			int posCurrent = 0;
			listCadenas.add(new StringBuffer());			
			for(String cadena : cadenas){
				tamanioLinea+=cadena.length()+1;
				
				if( cadena.length() > tamnioLinea ){
					throw new Exception("Existe una palabra en la razón ingresada que sobrepasa el espacio de los reglones del formato.");
				}
				if(tamanioLinea < tamnioLinea ){ 
					listCadenas.get(posCurrent).append(cadena);
					listCadenas.get(posCurrent).append(" ");			 
				}else{//--crea una nueva linea
					listCadenas.add(new StringBuffer());				
					listCadenas.get(++posCurrent).append(cadena);
					listCadenas.get(posCurrent).append(" ");
					tamanioLinea = cadena.length()+1;
				}			
			}			
			for(int i = 0; i < listCadenas.size() && i < 3; i++ ){
				valores.put(sufijo+(i+1),listCadenas.get(i).toString());
			}
		}
	}

}
