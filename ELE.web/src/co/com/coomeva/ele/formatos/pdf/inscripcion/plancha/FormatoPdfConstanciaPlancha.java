package co.com.coomeva.ele.formatos.pdf.inscripcion.plancha;

import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoPlancha;
import co.com.coomeva.ele.dto.DTOInformacionPlancha;
import co.com.coomeva.ele.dto.DTOMiembroPlancha;
import co.com.coomeva.ele.formatos.pdf.AbstractFormatoPdf;
import co.com.coomeva.ele.modelo.InfoPlanchaConstanciaPdfDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.util.acceso.UtilAcceso;

/**
 * Genera el formato de constancia de plancha
 * @author Mario Alejandro Acevedo
 * @project ELE.web
 * @class FormatoPdfInscripcionPlancha
 * @date 2/12/2012
 */

public class FormatoPdfConstanciaPlancha extends AbstractFormatoPdf {

	private String nombrePlantillaFormatoConstanciaPlancha;
	private String tipoEleccionesSession;
	
	public FormatoPdfConstanciaPlancha() {
		super();
		try {
			this.tipoEleccionesSession = (String) FacesUtils
			.getSessionParameter("tipoElecciones");
			nombrePlantillaFormatoConstanciaPlancha = UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
							"param.nombre.plantilla.inscripcion.plancha.constancia."+ this.tipoEleccionesSession);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	@Override
	public void setearValorMapaReporte(Object... params) throws Exception {
		Long consecutivoPlancha = (Long) params[0];
		
		InfoPlanchaConstanciaPdfDTO infoPlancha = DelegadoPlanchas.getInstance().obtenerInfoPlanchaConstanciaPdf(consecutivoPlancha);
		//String ciudadAgenciaVinculacion = DelegadoPlanchas.getInstance().obtenerCiudadAgenciaVinculacion(infoPlancha.getNumint());
		
		this.getValores().put("numZona",infoPlancha.getZona());
		this.getValores().put("annoInsc",infoPlancha.getAnnoAct());
		this.getValores().put("mesInsc",infoPlancha.getMesAct());
		this.getValores().put("diaInsc",infoPlancha.getDiaAct());
		this.getValores().put("horaInsc",infoPlancha.getHoraAct());
		//por definir
		this.getValores().put("numComision",infoPlancha.getZona());
		this.getValores().put("ciuComision",infoPlancha.getComisionCiu());
		
		this.getValores().put("nomCabeza",infoPlancha.getNombres()+" "+infoPlancha.getApellidos());
		//this.getValores().put("apellidosCabeza",infoPlancha.getApellidos());
		this.getValores().put("docCabeza",infoPlancha.getNumCedula().toString());
		this.getValores().put("lugExp",infoPlancha.getCiudadExp());
		this.getValores().put("diaExp",infoPlancha.getDiaExp());
		this.getValores().put("mesExp",infoPlancha.getMesExp());
		this.getValores().put("annoExp",infoPlancha.getAnnoExp());
		
	}

	@Override
	public void generarReporte(Object...params) throws Exception {
		this.setearValorMapaReporte(params);
		Long consecutivoPlancha = (Long)params[0];
		
		this.generarReportePDF(
				this.nombrePlantillaFormatoConstanciaPlancha,
				"ConstanciaPlancha"+consecutivoPlancha);
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
