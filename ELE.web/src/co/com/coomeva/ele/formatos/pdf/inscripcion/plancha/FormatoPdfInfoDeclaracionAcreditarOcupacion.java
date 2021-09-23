package co.com.coomeva.ele.formatos.pdf.inscripcion.plancha;

import java.util.Date;

import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.formatos.pdf.AbstractFormatoPdf;
import co.com.coomeva.ele.modelo.EleAsociadoDatosDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.ManipulacionFechas;

public class FormatoPdfInfoDeclaracionAcreditarOcupacion extends AbstractFormatoPdf {

	
	private String nombrePlantillaFormatoDeclaracionAcreditarOcupacion;
	
	public FormatoPdfInfoDeclaracionAcreditarOcupacion() {
		super();
		try {
			nombrePlantillaFormatoDeclaracionAcreditarOcupacion = UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,"param.nombre.plantilla.acreditacion.oficio");
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
	@Override
	public void setearValorMapaReporte(Object... params) throws Exception {
		Long numeroDocAsociado = Long.parseLong(params[0].toString());
		EleAsociadoDatosDTO asociado = DelegadoAsociado.getInstance().consultarInformacionBasicaAsociado(numeroDocAsociado);
		
		Date current = new Date();
		this.getValores().put("pdfCiudad",asociado.getDescripZonaAdministrativaAsociado());
		this.getValores().put("pdfAnio",ManipulacionFechas.getAgno(current));
		this.getValores().put("pdfMes",ManipulacionFechas.getMes(current));
		this.getValores().put("pdfDia",ManipulacionFechas.getDia(current));
		this.getValores().put("pdfAsociado",asociado.getNombreCompleto());
		this.getValores().put("pdfCedulaAsociado",asociado.getNitcli());
		this.getValores().put("pdfCiudadCedAsociado",asociado.getLugarExpedicion());
		this.getValores().put("pdfFirmaAnno",ManipulacionFechas.getAgno(current));
		this.getValores().put("pdfFirmaMes",ManipulacionFechas.getNombreMesTotal(current).toUpperCase());
        this.getValores().put("pdfFirmaDia",ManipulacionFechas.getDia(current));        
	}

	@Override
	public void generarReporte(Object... params) throws Exception {
		this.setearValorMapaReporte(params);
		String numeroDocAsociado = params[0].toString();
		this.generarReportePDF(this.nombrePlantillaFormatoDeclaracionAcreditarOcupacion,"AcreditacionOficio"+numeroDocAsociado);
	}
	
	public String generarReport() {
		try {
			generarReporte();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}
}
