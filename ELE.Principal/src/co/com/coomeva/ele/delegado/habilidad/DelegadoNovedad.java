package co.com.coomeva.ele.delegado.habilidad;

import java.util.Date;
import java.util.List;

import co.com.coomeva.ele.logica.LogicaNovedad;
import co.com.coomeva.ele.modelo.EleNovedadDTO;
import co.com.coomeva.ele.modelo.ResumenNovedadesDTO;

public class DelegadoNovedad {

	private static DelegadoNovedad instance;
	private static LogicaNovedad logicaNovedad;

	private DelegadoNovedad() {

	}

	public static DelegadoNovedad getInstance() {
		if (instance == null) {
			instance = new DelegadoNovedad();
			logicaNovedad = LogicaNovedad.getInstance();
		}
		return instance;
	}

	/**
	 * Metodo que consulta las novedades aplicadas, filtradas por el tipo de
	 * novedad, fecha del porceso o zona electoral del asociado.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a>
	 *         - Pragma S.A. <br>
	 * @date 19/11/2012
	 * @param tipoNovedad
	 * @param fechaProceso
	 * @param zonaElectoral
	 * @return
	 * @throws Exception
	 */
	public List<EleNovedadDTO> consultaNovedaesAplicadas(Long tipoNovedad,
			String fechaProceso, Long zonaElectoral) throws Exception {
		return logicaNovedad.consultaNovedadesAplicadas(tipoNovedad,
				fechaProceso, zonaElectoral);
	}

	/**
	 * Metodo que consulta la informacion para generar el resumen de novedades.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a>
	 *         - Pragma S.A. <br>
	 * @date 26/11/2012
	 * @return
	 */
	public List<ResumenNovedadesDTO> generarInformacionResumenNovedades()
			throws Exception {
		return logicaNovedad.generarInformacionResumenNovedades();
	}

	/**
	 * Metodo que registra una novedad de habilidad
	 * 
	 * @author Carlos Ernesto Ortega Q. Coomeva-Contratista
	 * @date 26/09/2015
	 * @return
	 */
	public void registrarNovedad(String estadoHabilidad, String documento,
			String observaciones, String usuarioRegistro) throws Exception {
		logicaNovedad.registrarNovedad(estadoHabilidad, documento,
				observaciones, usuarioRegistro);
	}

	/**
	 * Metodo que notifica el cambio de habilidad a una lista de correos
	 * 
	 * @author Carlos Ernesto Ortega Q. Coomeva-Contratista
	 * @date 26/09/2015
	 * @return
	 */
	public void notificarCambioHabilidad(String asunto, String mensaje,
			Long codigoParametroListaCorreos) throws Exception {
		logicaNovedad.notificarCambioHabilidad(asunto, mensaje,
				codigoParametroListaCorreos);
	}
}
