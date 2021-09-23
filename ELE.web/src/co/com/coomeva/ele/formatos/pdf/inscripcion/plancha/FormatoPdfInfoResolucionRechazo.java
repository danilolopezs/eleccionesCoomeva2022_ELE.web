package co.com.coomeva.ele.formatos.pdf.inscripcion.plancha;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

import co.com.coomeva.ele.dto.InfoDetalleFormatoPlanchaDTO;
import co.com.coomeva.ele.formatos.pdf.AbstractFormatoPdf;
import co.com.coomeva.ele.logica.inscripcion.plancha.LogicaPlancha;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.ManipulacionFechas;

public class FormatoPdfInfoResolucionRechazo extends AbstractFormatoPdf {

	private String nombrePlantillaFormatoResolucionRechazo;
	private String tipoEleccionesSession;
	private String tipoEleccionesRepresentantes;

	public FormatoPdfInfoResolucionRechazo() {
		super();
		try {
			this.tipoEleccionesSession = (String) FacesUtils
					.getSessionParameter("tipoElecciones");
			this.tipoEleccionesRepresentantes = UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"param.tipo.elecciones.representantes");
			nombrePlantillaFormatoResolucionRechazo = UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
							"param.nombre.plantilla.resolucion.rechazo."
									+ this.tipoEleccionesSession);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		System.out.println("format " + nombrePlantillaFormatoResolucionRechazo);
	}

	@Override
	public void setearValorMapaReporte(Object... params) throws Exception {

		InfoDetalleFormatoPlanchaDTO dtoInfoFormato = (InfoDetalleFormatoPlanchaDTO) params[0];
		LogicaPlancha logica = new LogicaPlancha();
		Calendar calendario = Calendar.getInstance();
		int hora = calendario.get(Calendar.HOUR_OF_DAY);
		int minutos = calendario.get(Calendar.MINUTE);
		Formatter fmt = new Formatter();
		String hora_cad = fmt.format("%1$02d:%2$02d ", hora, minutos)
				.toString();

		this.getValores().clear();
		// cambio 14012013 se realiza para que tome el numero de zona y no el id
		// del registro.
		String numeroZona = logica
				.consultarNumeroZonaPorCodigoZona(dtoInfoFormato
						.getNumeroComisionElectoral());
		this.getValores().put("pdfZonaElectoral", numeroZona.toString());
		// this.getValores().put("pdfZonaElectoral",
		// dtoInfoFormato.getNumeroComisionElectoral());
		this.getValores().put("pdfAsociado",
				dtoInfoFormato.getNombresApellidosAsociadoCabeza());
		// this.getValores().put("pdfNumeroResolucion",String.valueOf(dtoInfoFormato.getNumeroResolucion()));
		this.getValores().put("pdfAsociadoInscribe",
				dtoInfoFormato.getNombresApellidosAsociadoInscribe());
		this.getValores().put("pdfCedAsociadoInscribe",
				dtoInfoFormato.getCedulaAsociadoInscribe().toString());
		if (tipoEleccionesRepresentantes.equals(tipoEleccionesSession)) {
			this.getValores().put("pdfHoraActual", hora_cad);
		}
		// this.getValores().put("pdfNumeroActa",String.valueOf(dtoInfoFormato.getNumeroActa()));
		// this.getValores().put("pdfMesActa",ManipulacionFechas.getNombreMesTotal((dtoInfoFormato.getFechaActa())).toUpperCase());

		Date fechaActual = new Date();
		this.getValores().put("pdfAnio",
				ManipulacionFechas.getAgno(fechaActual));
		this.getValores().put("pdfMes", ManipulacionFechas.getMes(fechaActual));
		this.getValores().put("pdfDia", ManipulacionFechas.getDia(fechaActual));

		this.getValores().put("pdfAnio2",
				ManipulacionFechas.getAgno(new Date()));
		this.getValores().put("pdfFirmaMes",
				ManipulacionFechas.getNombreMesTotal(fechaActual));
		this.getValores()
				.put("pdfDia2", ManipulacionFechas.getDia(fechaActual));

		// --Setear valor en las columnas

		if (dtoInfoFormato.getRazon().length() > 264) {
			throw new Exception(
					"La razón sobrepasa el máximo de caracteres permitidos en el formato (264 incluyendo espacios)");
		}
		this.setearMultilinea(dtoInfoFormato.getRazon(), "pdfRazon", 95);

	}

	@Override
	public void generarReporte(Object... params) throws Exception {
		this.setearValorMapaReporte(params);
		InfoDetalleFormatoPlanchaDTO dtoInfoFormato = (InfoDetalleFormatoPlanchaDTO) params[0];
		Long consecutivoPlancha = dtoInfoFormato.getConsecutivoPlancha();
		this.generarReportePDF(this.nombrePlantillaFormatoResolucionRechazo,
				"FormatoResolucionRechazo" + consecutivoPlancha);
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
