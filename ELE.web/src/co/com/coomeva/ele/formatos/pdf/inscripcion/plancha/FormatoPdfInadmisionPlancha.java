package co.com.coomeva.ele.formatos.pdf.inscripcion.plancha;

import java.sql.Timestamp;
import java.util.Date;

import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.delegado.DelegadoDetalleFormato;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoPlancha;
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
import co.com.coomeva.util.date.ManipulacionFechas;

/**
 * Genera el formato de inadmisión de un plancha
 * 
 * @author Oscar Javier Salazar
 * @project ELE.web
 * @class FormatoPdfInscripcionPlancha
 * @date 08/12/2012
 */

public class FormatoPdfInadmisionPlancha extends AbstractFormatoPdf {

	/**
	 * Requeridos
	 */
	private String nombrePlantillaFormatoAdmisionPlancha;
	private InfoPlanchaDTO infoPlanchaDTO = null;
	private String tipoEleccionesSession;

	public FormatoPdfInadmisionPlancha() {
		super();
		try {
			this.tipoEleccionesSession = (String) FacesUtils
			.getSessionParameter("tipoElecciones");
			nombrePlantillaFormatoAdmisionPlancha = UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
							"param.nombre.plantilla.inadmision.plancha."+ this.tipoEleccionesSession);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void generarReporte(Object... params) throws Exception {
		this.setearValorMapaReporte(params);
		String cabezaPlancha = (String) params[0];
		/**
		 * Se debe cambiar el estado de la plancha a 
		 * Inadmitida
		 */
		//DelegadoPlanchas.getInstance().cambiarEstadoPlancha(nroCabPlancha, descEstado, estado, user, eleLogDTO, nroPlancha);
		DelegadoPlancha.getInstance().actualizarEstadoPlancha(infoPlanchaDTO.getConsecutivoPlancha(), UtilAcceso.getParametroFuenteS("parametros", "param.cod.estado.plancha.inadmitida"));
//		this.generarReportePDF(this.nombrePlantillaFormatoAdmisionPlancha,
//				"AdmisionPlancha" + cabezaPlancha);
	}

	public void setearValorMapaReporte(Object... params) throws Exception {
		String cabezaPlancha = (String) params[0];
//		String resolucionNro = (String) params[1];
//		String actNro = (String) params[2];
		String razon1 = (String) params[3];
		String razon2 = (String) params[4];
		String razon3 = (String) params[5];
		String razon4 = (String) params[6];
//		String ciudad = (String) params[7];
		String nombreAsociado = (String) params[8];

		if (cabezaPlancha == null) {
			throw new Exception();
		}

		/*ElePlanchas elePlancha = DelegadoPlanchas.getInstance()
				.consultarPlancha(cabezaPlancha);*/
		
		UserVo user  = (UserVo)FacesUtils.getSessionParameter("user");
		infoPlanchaDTO = LogicaPlanchas.getInstance().obtenerInfoPlancha(new Long(cabezaPlancha), user.getUserId());

		if (infoPlanchaDTO == null || infoPlanchaDTO.getCodAsociado() == null || infoPlanchaDTO.getConsecutivoPlancha() == null) {
			throw new Exception(
					"No existe una plancha asociada al cabeza de plancha con identificación: "
							+ cabezaPlancha);
		}
		
		EleAsociadoDatosDTO datosAsociadoCabezaPlancha = DelegadoAsociado.getInstance().consultarInformacionBasicaAsociado(Long.parseLong(cabezaPlancha));
		
		/**
		 * Se asignan los datos para la generación del reporte.
		 */
		Date fechaActual = new Date();
		
		this.getValores().put("cedulaCabezaPlancha", cabezaPlancha);
		this.getValores().put("nombreCabezaPlancha", nombreAsociado);
		//this.getValores().put("zonaElectoral",infoPlanchaDTO.getCodZona()+" - "+ infoPlanchaDTO.getZona());
		//Se elimina la descripción de la Zona
		this.getValores().put("zonaElectoral",infoPlanchaDTO.getCodZona());
		this.getValores().put("fecha",ManipulacionFechas.dateToStringConFormato(fechaActual," yyyy   MM   dd"));
		this.getValores().put("hora",ManipulacionFechas.dateToStringConFormato(fechaActual,"HH:mm:ss"));
		this.getValores().put("resolucionNro", "");
		this.getValores().put("actaNro", "");
		this.getValores().put("ciudad", datosAsociadoCabezaPlancha.getDescripZonaAdministrativaAsociado());
		this.getValores().put("fechaInscripcion",infoPlanchaDTO.getFecha());
		this.getValores().put("razon1", razon1);
		this.getValores().put("razon2", razon2);
		this.getValores().put("razon3", razon3);
		this.getValores().put("razon4", razon4);
		
		
		/**
		 * Se crea el registro en la tabal de detalle de formato
		 */
		EleDetalleFormatoPlanchaId id = new EleDetalleFormatoPlanchaId();
		EleDetalleFormatoPlancha detalleFormatoPlancha = new EleDetalleFormatoPlancha();
		id.setCodigoAsociado(new Long(cabezaPlancha));
		
		String codigoFormato = UtilAcceso
		.getParametroFuenteS(
				ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
				"param.codigo.formato");
		
		id.setCodigoFormato(new Byte(codigoFormato));
		detalleFormatoPlancha.setId(id);
		
		detalleFormatoPlancha.setFechaImpresion(new Timestamp(fechaActual.getTime()));
		detalleFormatoPlancha.setRazonInadmision1(razon1);
		detalleFormatoPlancha.setRazonInadmision2(razon2);
		detalleFormatoPlancha.setRazonInadmision3(razon3);
		detalleFormatoPlancha.setRazonInadmision4(razon4);
		
		DelegadoDetalleFormato.getInstance().crearDetalleFormato(detalleFormatoPlancha);
		
	}

	public String getNombrePlantillaFormatoAdmisionPlancha() {
		return nombrePlantillaFormatoAdmisionPlancha;
	}

	public void setNombrePlantillaFormatoAdmisionPlancha(
			String nombrePlantillaFormatoAdmisionPlancha) {
		this.nombrePlantillaFormatoAdmisionPlancha = nombrePlantillaFormatoAdmisionPlancha;
	}

}
