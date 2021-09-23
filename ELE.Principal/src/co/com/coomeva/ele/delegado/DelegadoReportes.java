package co.com.coomeva.ele.delegado;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;

import co.com.coomeva.ele.entidades.admhabilidad.LogTransacciones;
import co.com.coomeva.ele.entidades.planchas.EleLog;
import co.com.coomeva.ele.entidades.planchas.ElePlanchas;
import co.com.coomeva.ele.logica.LogicaReportes;
import co.com.coomeva.ele.modelo.AsociadoReporteDTO;
import co.com.coomeva.ele.modelo.EleAsociadoDatosDTO;
import co.com.coomeva.ele.modelo.EleNovedadDTO;
import co.com.coomeva.ele.modelo.ElePlanchaDTO;
import co.com.coomeva.ele.modelo.ResumenHabilidadDTO;
import co.com.coomeva.ele.modelo.ResumenNovedadesDTO;

public class DelegadoReportes {
	private static DelegadoReportes instance;
	private static LogicaReportes logicaReportes;

	//Constructor de la clase
	private DelegadoReportes() {
	}

	//Patròn Singular
	public static DelegadoReportes getInstance() {
		if (instance == null) {
			instance = new DelegadoReportes();
			logicaReportes = LogicaReportes.getInstance();
		}
		return instance;
	}
	
	public OutputStream generarAsociados(List<AsociadoReporteDTO> listaAsociados,String pathServidor) throws Exception {
		return logicaReportes.generarAsociados(listaAsociados,pathServidor);
	}
	
	public ByteArrayOutputStream generarAsociadosByte(List<AsociadoReporteDTO> listaAsociados, String pathServidor) throws Exception {
		return (ByteArrayOutputStream)logicaReportes.generarAsociados(listaAsociados,pathServidor);
	}

	public ByteArrayOutputStream generarTransaccionesByte(
			List<LogTransacciones> listaTransacciones,
			String pathServidor) {
		return (ByteArrayOutputStream)logicaReportes.generarTransacciones(listaTransacciones,pathServidor);
	}
	
	public ByteArrayOutputStream generarTransaccionesPlanchasByte(
			List<EleLog> listaTransacciones,
			String pathServidor, String nombreCompleto) {
		return (ByteArrayOutputStream)logicaReportes.generarTransaccionesPlanLog(listaTransacciones, pathServidor, nombreCompleto);
	}
	
	public OutputStream  generarTransacciones(
			List<LogTransacciones> listaTransacciones,
			String pathServidor) {
		// TODO Auto-generated method stub
		return logicaReportes.generarTransacciones(listaTransacciones,pathServidor);
	}

	public ByteArrayOutputStream generarPlanchasByte(
			List<ElePlanchas> listaPlanchas, String pathServerContextPath)throws Exception {
		return logicaReportes.generarPlanchas(listaPlanchas,pathServerContextPath);
	}

	public ByteArrayOutputStream generarCuadernillo(ElePlanchaDTO plancha,
			String pathContext) throws Exception {
		return logicaReportes.generarCuadernillo(plancha, pathContext);
	}
	
	/**
	 * Reporte asociados habiles Web
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 14/11/2012
	 * @param listaAsociados
	 * @param pathServidor
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream generarReporteAsociadosHabilesWeb(List<EleAsociadoDatosDTO> listAsociadosHabiles,String pathServidor) throws Exception {
		return logicaReportes.generarReporteAsociadosHabilesWeb(listAsociadosHabiles, pathServidor);
	}
	
	
	/**
	 * Reporte asociados habiles PDF
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 14/11/2012
	 * @param listaAsociados
	 * @param pathServidor
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream generarReporteAsociadosHabilesInhabilesPDF(List<EleAsociadoDatosDTO> listAsociadosHabiles,String pathServidor) throws Exception {
		return logicaReportes.generarReporteAsociadosHabilesInhabilesPDF(listAsociadosHabiles, pathServidor);
	}
	
	/**
	 * Metodo que genera el reporte de novedades aplicadas filtrado por
	 * novedad, zona y fecha de proceso.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 20/11/2012
	 * @param list
	 * @param pathServidor
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream generarReporteNovedadesAplicadasPDF(List<EleNovedadDTO> list, String pathServidor) throws Exception {
		return logicaReportes.generarReporteNovedadesAplicadasPDF(list, pathServidor);
	}
	
	/**
	 * Metodo que genera el resumen de habilidades en pdf.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 26/11/2012
	 * @param list
	 * @param pathServidor
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream generarReporteResumenHabilidadesPDF(List<ResumenHabilidadDTO> list, String pathServidor) throws Exception {
		return logicaReportes.generarReporteResumenHabilidadesPDF(list, pathServidor);
	}
	
	/**
	 * Metodo que genera el resumen de novedades en pdf.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 27/11/2012
	 * @param list
	 * @param pathServidor
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream generarReporteResumenNovedadesPDF(List<ResumenNovedadesDTO> list, String pathServidor) throws Exception {
		return logicaReportes.generarReporteResumenNovedadesPDF(list, pathServidor);
	}
	
}
