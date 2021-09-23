package co.com.coomeva.ele.formatos.pdf.inscripcion.plancha;

import org.apache.axis2.databinding.types.xsd.Date;

import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoPlancha;
import co.com.coomeva.ele.dto.DTOInformacionPlancha;
import co.com.coomeva.ele.dto.DTOMiembroPlancha;
import co.com.coomeva.ele.formatos.pdf.AbstractFormatoPdf;
import co.com.coomeva.ele.modelo.InfoPlanchaAdmisionPdfDTO;
import co.com.coomeva.ele.modelo.InfoPlanchaConstanciaPdfDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.util.acceso.UtilAcceso;

/**
 * Genera el formato de constancia de plancha
 * 
 * @author Mario Alejandro Acevedo
 * @project ELE.web
 * @class FormatoPdfInscripcionPlancha
 * @date 2/12/2012
 */

public class FormatoPdfAdmisionPlancha extends AbstractFormatoPdf {

	private String nombrePlantillaFormatoAdmisionPlancha;
	private String tipoEleccionesSession;
	private String tipoEleccionesRepresentantes;

	public FormatoPdfAdmisionPlancha() {
		super();
		try {
			this.tipoEleccionesSession = (String) FacesUtils
					.getSessionParameter("tipoElecciones");
			this.tipoEleccionesRepresentantes = UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"param.tipo.elecciones.representantes");
			nombrePlantillaFormatoAdmisionPlancha = UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
							"param.nombre.plantilla.inscripcion.plancha.admision."
									+ this.tipoEleccionesSession);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	@Override
	public void setearValorMapaReporte(Object... params) throws Exception {
		Long consecutivoPlancha = (Long) params[0];
		Long codFormato = (Long) params[1];

		InfoPlanchaAdmisionPdfDTO infoPlancha = DelegadoPlanchas.getInstance()
				.obtenerInfoPlanchaAdmisionPdf(consecutivoPlancha, codFormato);
		String ciudadAgenciaVinculacion = DelegadoPlanchas.getInstance()
				.obtenerCiudadAgenciaVinculacion(infoPlancha.getNumint());
		this.getValores().put("numZona", infoPlancha.getZona());
		this.getValores().put("annoInsc", infoPlancha.getAnnoAct());
		this.getValores().put("mesInsc", infoPlancha.getMesAct());
		this.getValores().put("diaInsc", infoPlancha.getDiaAct());
		this.getValores().put("horaInsc", infoPlancha.getHoraAct());
		// por definir
		this.getValores().put("agencia", infoPlancha.getAgencia());
		// this.getValores().put("ciuComision","");

		this.getValores().put("nomCabeza",
				infoPlancha.getNombres() + " " + infoPlancha.getApellidos());
		// this.getValores().put("apellidosCabeza",infoPlancha.getApellidos());
		if (tipoEleccionesRepresentantes.equals(tipoEleccionesSession)) {
			this.getValores().put("cedulaCabeza",
					infoPlancha.getNumCedula().toString());
		}

		// por indicación del ingeniero jose luis, esta fecha va a ser la fecha
		// de generación del pdf
		/*
		 * this.getValores().put("diaAct",infoPlancha.getDiaAct());
		 * this.getValores().put("mesAct",infoPlancha.getMesAct());
		 * this.getValores().put("annoAct",infoPlancha.getAnnoAct());
		 * 
		 * //por indicación del ingeniero jose luis, estos valores se dejan
		 * blanco en el pdf
		 * //this.getValores().put("numResol",infoPlancha.getResolucion());
		 * //this.getValores().put("numActa",infoPlancha.getActa());
		 * 
		 * 
		 * //this.getValores().put("fechaActa",infoPlancha.getFechaActa());
		 */
		this.getValores().put(
				"fechaActa",
				infoPlancha.getAnnoAct() + "-" + infoPlancha.getMesAct() + "-"
						+ infoPlancha.getDiaAct());
	}

	@Override
	public void generarReporte(Object... params) throws Exception {
		this.setearValorMapaReporte(params);
		Long consecutivoPlancha = (Long) params[0];

		this.generarReportePDF(this.nombrePlantillaFormatoAdmisionPlancha,
				"AdmisionPlancha" + consecutivoPlancha);
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
