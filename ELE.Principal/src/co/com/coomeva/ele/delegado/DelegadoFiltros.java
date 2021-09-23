package co.com.coomeva.ele.delegado;

import java.util.List;

import co.com.coomeva.ele.logica.LogicaFiltros;
import co.com.coomeva.ele.modelo.FiltrosConsultasDTO;

public class DelegadoFiltros {
	
	private static DelegadoFiltros instance;
	private static LogicaFiltros logicaFiltros;
	
	private DelegadoFiltros(){
	}
	
	public static DelegadoFiltros getInstance(){
		if (instance == null) {
			instance = new DelegadoFiltros();
			logicaFiltros = LogicaFiltros.getInstance();
		}
		return instance;
	}
	
	/**
	 * Consulta las zonas de coomeva para mostrarlas en una lista desplegable
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 8/11/2012
	 * @return
	 * @throws Exception
	 */
	public List<FiltrosConsultasDTO> consultarZonas() throws Exception{
		return logicaFiltros.consultarZonas();
	}
	
	/**
	 * Consulta las ciudades para mostrarlas en una lista desplegable
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 8/11/2012
	 * @return
	 * @throws Exception
	 */
	public List<FiltrosConsultasDTO> consultarCiudades() throws Exception{
		return logicaFiltros.consultarCiudades();
	}
	
	/**
	 * Consulta las profesiones de los asociados
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 8/11/2012
	 * @return
	 * @throws Exception
	 */
	public List<FiltrosConsultasDTO> consultarProfesiones(Long codPro, String nomPro) throws Exception{
		return logicaFiltros.consultarProfesiones(codPro, nomPro);
	}
	
	/**
	 * Consulta las fechas de los procesos programadas
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 20/11/2012
	 * @return
	 * @throws Exception
	 */
	public List<FiltrosConsultasDTO> consultarFechasProcesosProgramados() throws Exception{
		return logicaFiltros.consultarFechasProcesosProgramados();
	}
	
	public List<FiltrosConsultasDTO> consultarFechasCortesProcesosProgramados() throws Exception{
		return logicaFiltros.consultarFechasCortesProcesosProgramados();
	}
	
	/**
	 * Consulta las fechas de los procesos programadas
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 20/11/2012
	 * @return
	 * @throws Exception
	 */
	public List<FiltrosConsultasDTO> consultarRegionales() throws Exception{
		return logicaFiltros.consultarRegionales();
	}
	
	public List<FiltrosConsultasDTO> consultarZonasPorRegional(Long codRegional) throws Exception{
		return logicaFiltros.consultarZonasPorRegional(codRegional);
	}

	public List<FiltrosConsultasDTO> consultarZonasElectorales() throws Exception {
		return logicaFiltros.consultarZonasElectorales();
	}
	
	/**
	 * Metodo que conuslta la zona electoral de un asociado.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 6/12/2012
	 * @return
	 * @throws Exception
	 */
	public FiltrosConsultasDTO consultarZonaElectoralePorAsociado() throws Exception{
		return consultarZonaElectoralePorAsociado();
	}

}
