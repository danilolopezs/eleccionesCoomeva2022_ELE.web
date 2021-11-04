package co.com.coomeva.ele.formatos.pdf.inscripcion.plancha;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.icesoft.faces.context.effects.JavascriptContext;

import co.com.coomeva.ele.delegado.formulario.DelegadoRegistroFormulario;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoPlancha;
import co.com.coomeva.ele.dto.DTOInformacionPlancha;
import co.com.coomeva.ele.dto.DTOMiembroPlancha;
import co.com.coomeva.ele.entidades.formulario.EleRegistroCampos;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;
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

public class FormatoPdfInscripcionPlancha {

	private static final String NOMBRE_PLANTILLA_210 = "plantilla_CO-FT-210";
	private static final String TIPO_REPORTE = "210";

	private String tipoEleccionesSession;
	private String tipoEleccionesRepresentantes;

	private String urlUbicacionPlantillas;
	private String urlDestino;

	private HashMap<String, Object> parametros;
	private Long cedulaCabezaPlancha;
	private String nombreCabezaPlancha;
	private List<EleRegistroCampos> listaRegCampos;

	public FormatoPdfInscripcionPlancha() {
		super();
		try {
			this.parametros = new HashMap<String, Object>();
			listaRegCampos = new ArrayList<EleRegistroCampos>();

			this.urlUbicacionPlantillas = UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "param.url.ubicacion.plantillas");
			this.urlDestino = UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"param.url.destino.reportes");

			this.tipoEleccionesSession = (String) FacesUtils.getSessionParameter("tipoElecciones");
			this.tipoEleccionesRepresentantes = UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "param.tipo.elecciones.representantes");
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void generarReporte(Object... params) throws Exception {
		setParametrosListaCampos((Long) params[0]);
		guardarRegistroFormulario();
		generarReportePDF();
	}

	private void guardarRegistroFormulario() throws NumberFormatException, Exception {
		UserVo user = (UserVo) FacesUtils.getSessionParameter("user");
		DelegadoRegistroFormulario.getInstance().crearRegistroFormulario(Long.valueOf(TIPO_REPORTE), listaRegCampos,
				user.getUserId());
	}

	public void setParametrosListaCampos(Long consecutivoPlancha) throws Exception {
		DTOInformacionPlancha infoPlancha = DelegadoPlancha.getInstance()
				.consultarInformacionPlancha(consecutivoPlancha);

		parametros.put("zona", infoPlancha.getNumeroZonaElectoral());
		if (!tipoEleccionesRepresentantes.equals(tipoEleccionesSession)) {
			parametros.put("ciudad", infoPlancha.getCiudad());
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(TIPO_REPORTE), 21L, infoPlancha.getCiudad()));
		}
		parametros.put("anno", infoPlancha.getAnno());
		parametros.put("mes", infoPlancha.getMes());
		parametros.put("dia", infoPlancha.getDia());
		parametros.put("hora", infoPlancha.getHora());
		parametros.put("numeroPlancha", infoPlancha.getNumeroPlancha());

		listaRegCampos.add(
				new EleRegistroCampos(null, Long.valueOf(TIPO_REPORTE), 18L, infoPlancha.getNumeroZonaElectoral()));
		listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(TIPO_REPORTE), 73L, infoPlancha.getAnno()));
		listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(TIPO_REPORTE), 74L, infoPlancha.getMes()));
		listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(TIPO_REPORTE), 75L, infoPlancha.getDia()));
		listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(TIPO_REPORTE), 24L, infoPlancha.getHora()));
		listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(TIPO_REPORTE), 25L,
				infoPlancha.getNumeroPlancha() == null ? "" : infoPlancha.getNumeroPlancha()));

		List<AsociadoVO> listaSuplentes = new ArrayList<AsociadoVO>();
		List<AsociadoVO> listaPrincipales = new ArrayList<AsociadoVO>();
		for (DTOMiembroPlancha miembro : infoPlancha.getMiembrosTitulares()) {
			//se fijan valores de cabeza plancha para nombre de reporte
			if(miembro.getPosicionPlancha().equals("1")) {
				nombreCabezaPlancha = miembro.getApellidosNombres(); 
				cedulaCabezaPlancha = miembro.getNumeroDocumento();
			}
			listaPrincipales.add(new AsociadoVO(miembro.getPosicionPlancha(), miembro.getNumeroDocumento().toString(),
					miembro.getApellidosNombres(), miembro.getProfesion(), miembro.getCorreo(), null));
			listaRegCampos
					.add(new EleRegistroCampos(null, Long.valueOf(TIPO_REPORTE), 1L, miembro.getApellidosNombres()));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(TIPO_REPORTE), 5L,
					miembro.getProfesion() == null ? "" : miembro.getProfesion()));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(TIPO_REPORTE), 13L,
					miembro.getNumeroDocumento().toString()));
			listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(TIPO_REPORTE), 72L,
					miembro.getCorreo() == null ? "" : miembro.getCorreo()));
		}
		if (!tipoEleccionesRepresentantes.equals(tipoEleccionesSession)) {
			for (DTOMiembroPlancha miembro : infoPlancha.getMiembrosSuplentes()) {
				listaSuplentes
						.add(new AsociadoVO(miembro.getPosicionPlancha(), miembro.getNumeroDocumento().toString(),
								miembro.getApellidosNombres(), miembro.getProfesion(), miembro.getCorreo(), null));
				listaRegCampos.add(
						new EleRegistroCampos(null, Long.valueOf(TIPO_REPORTE), 1L, miembro.getApellidosNombres()));
				listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(TIPO_REPORTE), 4L,
						miembro.getProfesion() == null ? miembro.getProfesion() : ""));
				listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(TIPO_REPORTE), 7L,
						miembro.getNumeroDocumento().toString()));
				listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(TIPO_REPORTE), 72L,
						miembro.getNumeroDocumento().toString()));
			}
		}
		parametros.put("miembrosPrincipales", listaPrincipales);
		parametros.put("miembrosSuplentes", listaSuplentes);
	}

	private void generarReportePDF() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		request.getSession().setAttribute("codigoReporte", TIPO_REPORTE);
		request.getSession().setAttribute("cedulaCabezaPlancha", cedulaCabezaPlancha);
		request.getSession().setAttribute("nombreCabezaPlancha", nombreCabezaPlancha);
		request.getSession().setAttribute("parametrosFormulario210", parametros);
		JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "ServletReportesJasper();");
	}

}
