package co.com.coomeva.ele.formatos.pdf.inscripcion.plancha;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.delegado.DelegadoDetalleFormato;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleDetalleFormatoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleDetalleFormatoPlanchaId;
import co.com.coomeva.ele.formatos.pdf.AbstractFormatoPdf;
import co.com.coomeva.ele.logica.LogicaPlanchas;
import co.com.coomeva.ele.modelo.EleAsociadoDatosDTO;
import co.com.coomeva.ele.modelo.InfoPlanchaDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;
import co.com.coomeva.util.acceso.UtilAcceso;

/**
 * Genera el formato de inadmisión de un plancha
 * 
 * @author Juan Pablo Pazmin
 * @project ELE.web
 * @class FormatoPdfInscripcionPlancha
 * @date 12/12/2013
 */

public class FormatoPdfResolucionApelacionPlancha extends AbstractFormatoPdf {

	/**
	 * Requeridos
	 */
	private String nombrePlantillaFormatoNoReposicionSinApelacion;
	private String nombrePlantillaFormatoReposicionConApelacion;
	private String nombrePlantillaFormatoApelacionEnContra;
	private String nombrePlantillaFormatoApelacionFavorable;
	private String nombrePlantillaFormatoConcedeRecursoApelacion;
	private InfoPlanchaDTO infoPlanchaDTO = null;
	private String tipoEleccionesSession;

	public FormatoPdfResolucionApelacionPlancha() {
		super();
		try {
			this.tipoEleccionesSession = (String) FacesUtils
					.getSessionParameter("tipoElecciones");

			this.nombrePlantillaFormatoConcedeRecursoApelacion = UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
							"param.nombre.plantilla.resolucion.recurso.apelacion."
									+ this.tipoEleccionesSession);

			nombrePlantillaFormatoNoReposicionSinApelacion = UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
							"param.nombre.plantilla.noreposicion.sinapelacion."
									+ this.tipoEleccionesSession);
			nombrePlantillaFormatoReposicionConApelacion = UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
							"param.nombre.plantilla.reposicion.conapelacion."
									+ this.tipoEleccionesSession);
			nombrePlantillaFormatoApelacionEnContra = UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
							"param.nombre.plantilla.apelacion.encontra."
									+ this.tipoEleccionesSession);
			nombrePlantillaFormatoApelacionFavorable = UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
							"param.nombre.plantilla.apelacion.favorable."
									+ this.tipoEleccionesSession);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void generarReporte(Object... params) throws Exception {
		String cabezaPlancha = (String) params[0];
		String codResolucion = (String) params[8];

		if ("1".equals(codResolucion)) {
			this.setearValorMapaReporte(params);
			this.generarReportePDF(
					this.nombrePlantillaFormatoNoReposicionSinApelacion,
					"NoReposicionSinApelacion" + cabezaPlancha);
		} else if ("2".equals(codResolucion)) {
			this.setearValorMapaReporte(params);
			this.generarReportePDF(
					this.nombrePlantillaFormatoReposicionConApelacion,
					"ReposicionConApelacion" + cabezaPlancha);
		} else if ("3".equals(codResolucion)) {
			this.setearValorMapaReporteApelacion(params);
			this.generarReportePDF(
					this.nombrePlantillaFormatoApelacionFavorable,
					"ApelacionFavorable" + cabezaPlancha);
		} else if ("4".equals(codResolucion)) {
			this.setearValorMapaReporte(params);
			this.generarReportePDF(
					this.nombrePlantillaFormatoApelacionEnContra,
					"ApelacionEncontra" + cabezaPlancha);
		} else if ("5".equals(codResolucion)) {
			this.setearValorMapaReporte(params);
			this.generarReportePDF(
					this.nombrePlantillaFormatoConcedeRecursoApelacion,
					"RecursoApelacion" + cabezaPlancha);
		}
	}

	public void setearValorMapaReporte(Object... params) throws Exception {
		String cabezaPlancha = (String) params[0];
		String resolucionNro = (String) params[1];
		String resolucionImpugnada = (String) params[2];
		String argumento1 = (String) params[3];
		String argumento2 = (String) params[4];
		String argumento3 = (String) params[5];
		String razon1 = (String) params[6];
		String razon2 = (String) params[7];
		String codResolucion = (String) params[8];
		String nombreAsociado = (String) params[9];

		if (cabezaPlancha == null) {
			throw new Exception();
		}

		/*
		 * ElePlanchas elePlancha = DelegadoPlanchas.getInstance()
		 * .consultarPlancha(cabezaPlancha);
		 */

		UserVo user = (UserVo) FacesUtils.getSessionParameter("user");
		infoPlanchaDTO = LogicaPlanchas.getInstance().obtenerInfoPlancha(
				new Long(cabezaPlancha), user.getUserId());

		if (infoPlanchaDTO == null || infoPlanchaDTO.getCodAsociado() == null
				|| infoPlanchaDTO.getConsecutivoPlancha() == null) {
			throw new Exception(
					"No existe una plancha asociada al cabeza de plancha con identificación: "
							+ cabezaPlancha);
		}

		// EleAsociadoDatosDTO datosAsociadoCabezaPlancha = DelegadoAsociado
		// .getInstance().consultarInformacionBasicaAsociado(
		// Long.parseLong(cabezaPlancha));

		/**
		 * Se asignan los datos para la generación del reporte.
		 */
		Date fechaActual = new Date();
		SimpleDateFormat formatterMes = new SimpleDateFormat("MM");
		SimpleDateFormat formatterDia = new SimpleDateFormat("dd");
		SimpleDateFormat formatterAnno = new SimpleDateFormat("yyyy");

		this.getValores().put("pdfParamResolucion", resolucionNro);
		this.getValores().put("pdfParamZonaElectoral",
				infoPlanchaDTO.getCodZona());
		this.getValores()
				.put("pdfParamAnho", formatterAnno.format(fechaActual));
		this.getValores().put("pdfParamMes", formatterMes.format(fechaActual));
		this.getValores().put("pdfParamDia", formatterDia.format(fechaActual));
		// Se elimina la descripción de la Zona
		this.getValores().put("pdfParamResImpugnada", resolucionImpugnada);
		this.getValores().put("pdfParamNombreCompleto", nombreAsociado);
		this.getValores().put("pdfParamComision", infoPlanchaDTO.getZona());
		this.getValores().put("pdfParamArgumento1", argumento1);
		this.getValores().put("pdfParamArgumento2", argumento2);
		this.getValores().put("pdfParamArgumento3", argumento3);
		this.getValores().put("pdfParamRazon1", razon1);
		this.getValores().put("pdfParamRazon2", razon2);
		this.getValores().put("pdfParamComElectoral", infoPlanchaDTO.getZona());
		Calendar calendario = Calendar.getInstance();
		int hora = calendario.get(Calendar.HOUR_OF_DAY);
		int minutos = calendario.get(Calendar.MINUTE);
		Formatter fmt = new Formatter();
		String hora_cad = fmt.format("%1$02d:%2$02d ", hora, minutos)
				.toString();
		this.getValores().put("pdfParamHora", hora_cad);
		

		/**
		 * Se crea el registro en la tabal de detalle de formato
		 */
		EleDetalleFormatoPlanchaId id = new EleDetalleFormatoPlanchaId();
		EleDetalleFormatoPlancha detalleFormatoPlancha = new EleDetalleFormatoPlancha();
		id.setCodigoAsociado(new Long(cabezaPlancha));

		String codigoFormato = null;
		if ("1".equals(codResolucion)) {
			codigoFormato = UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"codigo.formato.noreposicion.sinapelacion");
		} else if ("2".equals(codResolucion)) {
			codigoFormato = UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"codigo.formato.reposicion.conapelacion");
		} else if ("4".equals(codResolucion)) {
			codigoFormato = UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"codigo.formato.apelacion.encontra");
		} else if ("5".equals(codResolucion)) {
			this.getValores().remove("pdfParamComElectoral");
			EleAsociadoDatosDTO datosAsociadoCabezaPlancha = DelegadoAsociado.getInstance().consultarInformacionBasicaAsociado(Long.parseLong(cabezaPlancha));
			this.getValores().put("pdfIdent",
					String.valueOf(datosAsociadoCabezaPlancha.getNitcli().toString()));
			codigoFormato = UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"codigo.formato.concede.recurso.apelacion");
		}

		id.setCodigoFormato(new Byte(codigoFormato));
		detalleFormatoPlancha.setId(id);
		detalleFormatoPlancha.setFechaImpresion(new Timestamp(fechaActual
				.getTime()));
		detalleFormatoPlancha.setFechaResolucion(fechaActual);
		detalleFormatoPlancha.setNumeroResolucion(Long.valueOf(resolucionNro));
		detalleFormatoPlancha.setArgumentoResolucion1(argumento1);
		detalleFormatoPlancha.setArgumentoResolucion2(argumento2);
		detalleFormatoPlancha.setArgumentoResolucion3(argumento3);
		detalleFormatoPlancha.setRazonResolucion1(razon1);
		detalleFormatoPlancha.setRazonResolucion2(razon2);
		detalleFormatoPlancha.setComisionElectoral(infoPlanchaDTO.getZona());
		detalleFormatoPlancha.setResolucionImpugnada(resolucionImpugnada);

		DelegadoDetalleFormato.getInstance().crearDetalleFormato(
				detalleFormatoPlancha);

	}

	public void setearValorMapaReporteApelacion(Object... params)
			throws Exception {
		String cabezaPlancha = (String) params[0];
		String resolucionNro = (String) params[1];
		String resolucionImpugnada = (String) params[2];
		String resolucionInterpuesta = (String) params[3];
		String argumentoRecurrente1 = (String) params[4];
		String argumentoRecurrente2 = (String) params[5];
		String argumentoComision1 = (String) params[6];
		String argumentoComision2 = (String) params[7];
		String pronunciamientoTribunal1 = (String) params[9];
		String pronunciamientoTribunal2 = (String) params[10];
		String consecuenciaTribunal1 = (String) params[11];
		String consecuenciaTribunal2 = (String) params[12];
		String nombreAsociado = (String) params[13];

		if (cabezaPlancha == null) {
			throw new Exception();
		}

		/*
		 * ElePlanchas elePlancha = DelegadoPlanchas.getInstance()
		 * .consultarPlancha(cabezaPlancha);
		 */

		UserVo user = (UserVo) FacesUtils.getSessionParameter("user");
		infoPlanchaDTO = LogicaPlanchas.getInstance().obtenerInfoPlancha(
				new Long(cabezaPlancha), user.getUserId());

		if (infoPlanchaDTO == null || infoPlanchaDTO.getCodAsociado() == null
				|| infoPlanchaDTO.getConsecutivoPlancha() == null) {
			throw new Exception(
					"No existe una plancha asociada al cabeza de plancha con identificación: "
							+ cabezaPlancha);
		}
		/**
		 * Se asignan los datos para la generación del reporte.
		 */
		Date fechaActual = new Date();
		SimpleDateFormat formatterMes = new SimpleDateFormat("MM");
		SimpleDateFormat formatterDia = new SimpleDateFormat("dd");
		SimpleDateFormat formatterAnno = new SimpleDateFormat("yyyy");

		this.getValores().put("pdfParamResolucion", resolucionNro);
		this.getValores().put("pdfParamZonaElectoral",
				infoPlanchaDTO.getCodZona());
		this.getValores()
				.put("pdfParamAnho", formatterAnno.format(fechaActual));
		this.getValores().put("pdfParamMes", formatterMes.format(fechaActual));
		this.getValores().put("pdfParamDia", formatterDia.format(fechaActual));

		this.getValores().put("pdfParamResImpugnada", resolucionImpugnada);
		this.getValores().put("pdfParamNombreCompleto", nombreAsociado);
		this.getValores().put("pdfParamComision", infoPlanchaDTO.getZona());
		this.getValores().put("pdfParamResInterpuesta", resolucionInterpuesta);
		this.getValores().put("pdfParamArgRecurrente1", argumentoRecurrente1);
		this.getValores().put("pdfParamArgRecurrente2", argumentoRecurrente2);
		this.getValores().put("pdfParamArgComision1", argumentoComision1);
		this.getValores().put("pdfParamArgComision2", argumentoComision2);
		this.getValores().put("pdfParamPronunciamiento1",
				pronunciamientoTribunal1);
		this.getValores().put("pdfParamPronunciamiento2",
				pronunciamientoTribunal2);
		this.getValores().put("pdfParamConsecuencia1", consecuenciaTribunal1);
		this.getValores().put("pdfParamConsecuencia2", consecuenciaTribunal2);
		this.getValores().put("pdfParamComElectoral", infoPlanchaDTO.getZona());
		
		Calendar calendario = Calendar.getInstance();
		int hora = calendario.get(Calendar.HOUR_OF_DAY);
		int minutos = calendario.get(Calendar.MINUTE);
		Formatter fmt = new Formatter();
		String hora_cad = fmt.format("%1$02d:%2$02d ", hora, minutos)
				.toString();
		this.getValores().put("pdfParamHora", hora_cad);


		/**
		 * Se crea el registro en la tabal de detalle de formato
		 */
		EleDetalleFormatoPlanchaId id = new EleDetalleFormatoPlanchaId();
		EleDetalleFormatoPlancha detalleFormatoPlancha = new EleDetalleFormatoPlancha();
		id.setCodigoAsociado(new Long(cabezaPlancha));

		String codigoFormato = UtilAcceso.getParametroFuenteS(
				ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
				"codigo.formato.apelacion.favorable");

		id.setCodigoFormato(new Byte(codigoFormato));
		detalleFormatoPlancha.setId(id);
		detalleFormatoPlancha.setFechaImpresion(new Timestamp(fechaActual
				.getTime()));
		detalleFormatoPlancha.setFechaResolucion(fechaActual);
		detalleFormatoPlancha.setNumeroResolucion(Long.valueOf(resolucionNro));
		detalleFormatoPlancha.setResolucionInterpuesta(resolucionInterpuesta);
		detalleFormatoPlancha.setArgumentoRecurrente1(argumentoRecurrente1);
		detalleFormatoPlancha.setArgumentoRecurrente2(argumentoRecurrente2);
		detalleFormatoPlancha.setArgumentoComision1(argumentoComision1);
		detalleFormatoPlancha.setArgumentoComision2(argumentoComision2);
		detalleFormatoPlancha
				.setPronunciamientoTribunal1(pronunciamientoTribunal1);
		detalleFormatoPlancha
				.setPronunciamientoTribunal2(pronunciamientoTribunal2);
		detalleFormatoPlancha.setConsecuenciaTribunal1(consecuenciaTribunal1);
		detalleFormatoPlancha.setConsecuenciaTribunal2(consecuenciaTribunal2);
		detalleFormatoPlancha.setComisionElectoral(infoPlanchaDTO.getZona());
		detalleFormatoPlancha.setResolucionImpugnada(resolucionImpugnada);

		DelegadoDetalleFormato.getInstance().crearDetalleFormato(
				detalleFormatoPlancha);

	}

	public String getNombrePlantillaFormatoNoReposicionSinApelacion() {
		return nombrePlantillaFormatoNoReposicionSinApelacion;
	}

	public void setNombrePlantillaFormatoNoReposicionSinApelacion(
			String nombrePlantillaFormatoNoReposicionSinApelacion) {
		this.nombrePlantillaFormatoNoReposicionSinApelacion = nombrePlantillaFormatoNoReposicionSinApelacion;
	}

	public String getNombrePlantillaFormatoReposicionConApelacion() {
		return nombrePlantillaFormatoReposicionConApelacion;
	}

	public void setNombrePlantillaFormatoReposicionConApelacion(
			String nombrePlantillaFormatoReposicionConApelacion) {
		this.nombrePlantillaFormatoReposicionConApelacion = nombrePlantillaFormatoReposicionConApelacion;
	}

	public String getNombrePlantillaFormatoApelacionEnContra() {
		return nombrePlantillaFormatoApelacionEnContra;
	}

	public void setNombrePlantillaFormatoApelacionEnContra(
			String nombrePlantillaFormatoApelacionEnContra) {
		this.nombrePlantillaFormatoApelacionEnContra = nombrePlantillaFormatoApelacionEnContra;
	}

	public String getNombrePlantillaFormatoConcedeRecursoApelacion() {
		return nombrePlantillaFormatoConcedeRecursoApelacion;
	}

	public void setNombrePlantillaFormatoConcedeRecursoApelacion(
			String nombrePlantillaFormatoConcedeRecursoApelacion) {
		this.nombrePlantillaFormatoConcedeRecursoApelacion = nombrePlantillaFormatoConcedeRecursoApelacion;
	}

}
