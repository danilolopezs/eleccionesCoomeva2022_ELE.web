package co.com.coomeva.ele.formatos.pdf.inscripcion.plancha;

import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoPlancha;
import co.com.coomeva.ele.dto.DTOInformacionPlancha;
import co.com.coomeva.ele.dto.DTOMiembroPlancha;
import co.com.coomeva.ele.formatos.pdf.AbstractFormatoPdf;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.util.acceso.UtilAcceso;

/**
 * Genera el formato de inscripción de plancha
 * 
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
 *         Premize SAS <br>
 * @project ELE.web
 * @class FormatoPdfInscripcionPlancha
 * @date 2/12/2012
 */

public class FormatoPdfInscripcionPlancha extends AbstractFormatoPdf {

	private String nombrePlantillaFormatoInscripcionPlancha;
	private String tipoEleccionesSession;
	private String tipoEleccionesRepresentantes;

	public FormatoPdfInscripcionPlancha() {
		super();
		try {
			this.tipoEleccionesSession = (String) FacesUtils
					.getSessionParameter("tipoElecciones");
			this.tipoEleccionesRepresentantes = UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"param.tipo.elecciones.representantes");
			nombrePlantillaFormatoInscripcionPlancha = UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
							"param.nombre.plantilla.inscripcion.plancha."
									+ this.tipoEleccionesSession);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	@Override
	public void setearValorMapaReporte(Object... params) throws Exception {
		Long consecutivoPlancha = (Long) params[0];

		DTOInformacionPlancha infoPlancha = DelegadoPlancha.getInstance()
				.consultarInformacionPlancha(consecutivoPlancha);

		this.getValores().put("numeroZonaElect",
				infoPlancha.getNumeroZonaElectoral());
		if (!tipoEleccionesRepresentantes.equals(tipoEleccionesSession)) {
			
			this.getValores().put("ciudad", infoPlancha.getCiudad());
		}
		this.getValores().put("anno", infoPlancha.getAnno());
		this.getValores().put("mes", infoPlancha.getMes());
		this.getValores().put("dia", infoPlancha.getDia());
		this.getValores().put("hora", infoPlancha.getHora());

		int i = 1;
		for (DTOMiembroPlancha miembro : infoPlancha.getMiembrosTitulares()) {
			this.getValores().put("apeNomTit" + miembro.getPosicionPlancha(),
					miembro.getApellidosNombres());
			this.getValores().put("profTit" + miembro.getPosicionPlancha(),
					miembro.getProfesion());
			this.getValores().put("cedTit" + miembro.getPosicionPlancha(),
					miembro.getNumeroDocumento().toString());
			i++;
		}

		if (!tipoEleccionesRepresentantes.equals(tipoEleccionesSession)) {
			i = 1;
			for (DTOMiembroPlancha miembro : infoPlancha.getMiembrosSuplentes()) {
				this.getValores().put(
						"apeNomSup" + miembro.getPosicionPlancha(),
						miembro.getApellidosNombres());
				this.getValores().put("profSup" + miembro.getPosicionPlancha(),
						miembro.getProfesion());
				this.getValores().put("cedSup" + miembro.getPosicionPlancha(),
						miembro.getNumeroDocumento().toString());
				i++;
			}
		}
	}

	@Override
	public void generarReporte(Object... params) throws Exception {
		this.setearValorMapaReporte(params);
		Long consecutivoPlancha = (Long) params[0];
		this.generarReportePDF(this.nombrePlantillaFormatoInscripcionPlancha,
				"InscripcionPlancha" + consecutivoPlancha);
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
