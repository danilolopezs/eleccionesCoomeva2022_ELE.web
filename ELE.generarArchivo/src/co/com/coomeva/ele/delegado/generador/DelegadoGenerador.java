package co.com.coomeva.ele.delegado.generador;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

import net.sf.jasperreports.engine.JasperPrint;
import co.com.coomeva.ele.entidades.cuociente.EleCuocienteDelegadosZona;
import co.com.coomeva.ele.entidades.cuociente.EleCuocienteElectoral;
import co.com.coomeva.ele.entidades.cuociente.EleCuocienteRegional;
import co.com.coomeva.ele.logica.generador.LogicaGenerador;
import co.com.coomeva.ele.modelo.EleAsociadoDatosDTO;
import co.com.coomeva.ele.modelo.EleNovedadDTO;
import co.com.coomeva.ele.modelo.PlanchaPorEstadoDTO;
import co.com.coomeva.ele.modelo.ResumenHabilidadDTO;
import co.com.coomeva.ele.modelo.ResumenNovedadesDTO;

public class DelegadoGenerador {
	private static DelegadoGenerador instance;
	private static LogicaGenerador logicaGenerador;

	//Constructor de la clase
	private DelegadoGenerador() {
	}

	//Patròn Singular
	public static DelegadoGenerador getInstance() {
		if (instance == null) {
			instance = new DelegadoGenerador();
			logicaGenerador = LogicaGenerador.getInstance();
		}
		return instance;
	}

	public ByteArrayOutputStream generarExcel() throws Exception {
		return logicaGenerador.generarExcel();
	}
	
	/**
	 * Metodo que genera el reporte de asociados habiles e inhabiles en excel
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 19/11/2012
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream generarReporteHabilesInhabilesExcel(List<EleAsociadoDatosDTO> listAsociadosHabiles, String tipoReporte) throws Exception{
		return logicaGenerador.generarReporteHabilesInhabilesExcel(listAsociadosHabiles, tipoReporte);
	}
	
	/**
	 * Metodo que genera el reporte en excel de las novedades
	 * aplicadas filtradas por novedad, zona y fecha proceso.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 20/11/2012
	 * @param list
	 * @param tipoReporte
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream generarReporteNovedadesExcel(List<EleNovedadDTO> list) throws Exception{
		return logicaGenerador.generarReporteNovedadesExcel(list);
	}
	
	/**
	 * Metodo que genera el resumen de habilidades en excel.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 26/11/2012
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream generarResumenHabilidad(List<ResumenHabilidadDTO> list) throws Exception{
		return logicaGenerador.generarResumenHabilidad(list);
	}
	
	/**
	 * Metodo que genera el resumen de novedades en excel.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 27/11/2012
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream generarResumenNovedades(List<ResumenNovedadesDTO> list) throws Exception{
		return logicaGenerador.generarResumenNovedades(list);
	}
	
	
	/**
	 * Metodo que genera la matriz de cuociente electoral en excel.
	 * 
	 * @author Juan Diego Montoya
	 * @date 02/09/2016
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream generarCoucienteElectoral(List<EleCuocienteRegional> regList, List<EleCuocienteDelegadosZona> list, EleCuocienteElectoral cuocienteElectoral) throws Exception{
		return logicaGenerador.generarCoucienteElectoral(regList, list, cuocienteElectoral);
	}
	
	public ByteArrayOutputStream generarReportePlanchasEstado(List<PlanchaPorEstadoDTO> list) throws Exception{
		return logicaGenerador.generarReportePlanchasEstado(list);
	}

	public JasperPrint reporteResolucionAdmisionPlanchas_FT_172(
			String zonaElectoral, String nombreAsociado, String numResolucion,
			String numActa, Date fecha, String ciudadZona,String dia, String mes, String anio,
			String rutaImagen, String rutaReporte) throws Exception {
		// TODO Auto-generated method stub
		return logicaGenerador.reporteResolucionAdmisionPlanchas_FT_172(zonaElectoral, nombreAsociado, numResolucion, numActa, 
				fecha, ciudadZona,dia, mes, anio, rutaImagen, rutaReporte);
	}

	public JasperPrint reporteResolucionRechazoPlanchas_FT_173(
			String zonaElectoral, String nombreAsociado, String numResolucion,
			String cedulaAsociado, String numActa, Date fecha, String dia,
			String mes, String anio, String razones, String mesActa, String rutaImagen,
			String rutaReporte) throws Exception {
		// TODO Auto-generated method stub
		return logicaGenerador.reporteResolucionRechazoPlanchas_FT_173(zonaElectoral, nombreAsociado, numResolucion, cedulaAsociado,
				numActa, fecha, dia, mes, anio, razones, mesActa, rutaImagen, rutaReporte);
	}

	public JasperPrint reporteInformacionPersonal_FT_174(
			String plancha, String zonaElectoral, String nombreAsociado,
			String cedulaAsociado, String fechaAntiguedad, String profesion,
			Date fechaTitulo, String estudios, String empresa, String cargo,
			String antiguedad, String ultimoCargo, String imagen,
			String rutaImagen, String rutaReporte, String tipoAsociado)throws Exception {
		// TODO Auto-generated method stub
		return logicaGenerador.reporteInformacionPersonal_FT_174(plancha, zonaElectoral, nombreAsociado, 
				cedulaAsociado, fechaAntiguedad, profesion, fechaTitulo, estudios, empresa, cargo, antiguedad,
				ultimoCargo, imagen, rutaImagen, rutaReporte, tipoAsociado);
	}

	public JasperPrint reporteCumplimientoDelegado_FT_176(
			String nombreAsociado, String numPlancha,String nombreRepresen, List<String> respustas,
			String rutaImagen, String rutaReporte) throws Exception {
		// TODO Auto-generated method stub
		return logicaGenerador.reporteCumplimientoDelegado_FT_176(nombreAsociado, numPlancha, nombreRepresen,respustas, rutaImagen, rutaReporte);
	}

	public JasperPrint reporteConstanciaRadicacionPlanchas_FT_208(
			String zonaElectoral, Date fecha,String numComision, String ciudad,
			String nombreAsociado, String cedulaAsociado, String ciudadCedula,
			String dia, String mes, String anio, String nombreEntrega, String nombreRecibe,
			String rutaImagen,
			String rutaReporte) throws Exception {
		// TODO Auto-generated method stub
		return logicaGenerador.reporteConstanciaRadicacionPlanchas_FT_208(zonaElectoral, fecha, numComision, ciudad, nombreAsociado, cedulaAsociado, ciudadCedula, dia, mes, anio, nombreEntrega, nombreRecibe, rutaImagen, rutaReporte);
	}

	public JasperPrint reporteResolucionInadmisionPlancha_FT_209(
			String zonaElectoral, String anio, String mes, String dia,
			Date hora, String nombreAsociado, String cedulaAsociado,
			String resolucion, String acta, Date fecha, String ciudad,
			String razon1, String razon2, String razon3, String razon4,
			String formulario, String rutaReporte) throws Exception {
		// TODO Auto-generated method stub
		return logicaGenerador.reporteResolucionInadmisionPlancha_FT_209(zonaElectoral, anio, mes, dia, hora, nombreAsociado, cedulaAsociado, resolucion, acta, fecha, ciudad, razon1, razon2, razon3, razon4, formulario, rutaReporte);
	}

	public JasperPrint reporteInscripcionPlanchas_FT_210(
			String zonaElectoral, String ciudad, Date fecha, String rutaImagen,
			String rutaReporte)throws Exception {
		// TODO Auto-generated method stub
		return logicaGenerador.reporteInscripcionPlanchas_FT_210(zonaElectoral, ciudad, fecha, rutaImagen, rutaReporte);
	}

	public JasperPrint reporteCertificadoAcreditaOcupacion_FT_211(
			String ciudad, String anio, String mes, String dia,
			String nombreAsociado, String cedulaAsociado, String ciudadCedula,
			String ciudadFirma, String diaFirma, String mesFirma,
			String anioFirma, String rutaImagen, String rutaReporte) throws Exception {
		// TODO Auto-generated method stub
		return logicaGenerador.reporteCertificadoAcreditaOcupacion_FT_211(ciudad, anio, mes, dia, nombreAsociado, cedulaAsociado, ciudadCedula, ciudadFirma, diaFirma, mesFirma, anioFirma, rutaImagen, rutaReporte);
	}

	public JasperPrint reporteResolucionDeniega_FT_459(
			String zonaElectoral, String nombreComision, String nombreAsociado,
			String resolucionImpugnada, Date fecha, String resolucion,
			String argumento, String nombrePresidente, String nombreSecretario,
			String rutaImagen, String rutaReporte) throws Exception {
		// TODO Auto-generated method stub
		return logicaGenerador.reporteResolucionDeniega_FT_459(zonaElectoral, nombreComision, nombreAsociado, resolucionImpugnada, fecha, resolucion, argumento, nombrePresidente, nombreSecretario,rutaImagen, rutaReporte);
	}

	public JasperPrint reporteResuelveReposicion_FT_460(
			String zonaElectoral, String nombreComision, String nombreAsociado,
			String resolucionImpugnada, Date fecha, String resolucion,
			String argumento, String nombrePresidente, String nombreSecretario, String rutaImagen, String rutaReporte)throws Exception {
		// TODO Auto-generated method stub
		return logicaGenerador.reporteResuelveReposicion_FT_460(zonaElectoral, nombreComision, nombreAsociado, resolucionImpugnada, fecha, resolucion, argumento, nombrePresidente, nombreSecretario,rutaImagen, rutaReporte);
	}

	public JasperPrint reporteResuelveApelacion_FT_461(String acta,
			String nombreAsociado, String resolucionApelada,
			String resolucionComision, Date fecha, String actaTribunal,
			String argumento, String decision, String nombrePresidente, String nombreSecretario,
			String rutaImagen, String rutaReporte) throws Exception {
		// TODO Auto-generated method stub
		return logicaGenerador.reporteResuelveApelacion_FT_461(acta, nombreAsociado, resolucionApelada, resolucionComision, fecha, actaTribunal, argumento, decision, nombrePresidente, nombreSecretario,rutaImagen, rutaReporte);
	}

	public JasperPrint reporteResuelveApelacionContra_FT_462(
			String acta, String nombreAsociado, String resolucionApelada,
			String resolucionComision, Date fecha, String actaTribunal,
			String argumento,String nombrePresidente, String nombreSecretario,
			String rutaImagen, String rutaReporte)throws Exception {
		// TODO Auto-generated method stub
		return logicaGenerador.reporteResuelveApelacionContra_FT_462(acta, nombreAsociado, resolucionApelada, resolucionComision, fecha, actaTribunal, argumento, nombrePresidente, nombreSecretario,rutaImagen, rutaReporte);
	}

	public JasperPrint reporteResolucionExtemporaneamente_FT_753(
			String zonaElectoral, Date fecha, String nombreAsociado,
			String nombreComision, String resolucion, String diaPresentado,
			String dia, String mes, String anio, String rutaImagen,
			String rutaReporte) throws Exception {
		// TODO Auto-generated method stub
		return logicaGenerador.reporteResolucionExtemporaneamente_FT_753(zonaElectoral, fecha, nombreAsociado, nombreComision, resolucion, diaPresentado, dia, mes, anio, rutaImagen, rutaReporte);
	}
	
	public JasperPrint reporteResolucionFavorable_FT_458(
			String zonaElectoral, String nombreComision, String nombreAsociado,
			String resolucionImpugnada, Date fecha, String resolucionMod,
			String argumento, String nombrePresidente, String nombreSecretario,
			String decision, String rutaImagen, String rutaReporte) throws Exception {
		// TODO Auto-generated method stub
		return logicaGenerador.reporteResolucionFavorable_FT_458(zonaElectoral, nombreComision, nombreAsociado, resolucionImpugnada, fecha, resolucionImpugnada, argumento, nombrePresidente, nombreSecretario, resolucionMod, decision, rutaImagen, rutaReporte);
	}
	
}
