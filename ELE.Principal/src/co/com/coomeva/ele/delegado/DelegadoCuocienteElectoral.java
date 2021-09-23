package co.com.coomeva.ele.delegado;

import java.util.List;

import org.hibernate.Transaction;

import co.com.coomeva.ele.entidades.cuociente.EleCuocienteDelegadosZona;
import co.com.coomeva.ele.entidades.cuociente.EleCuocienteElectoral;
import co.com.coomeva.ele.entidades.cuociente.EleCuocienteRegional;
import co.com.coomeva.ele.logica.cuociente.LogicaCuocienteElectoral;
import co.com.coomeva.ele.logica.cuociente.LogicaDelegadosRegional;
import co.com.coomeva.ele.logica.cuociente.LogicaDelegadosZona;
import co.com.coomeva.ele.modelo.ResumenCuocienteDTO;
import co.com.coomeva.ele.modelo.ResumenNovedadesDTO;

public class DelegadoCuocienteElectoral {

	private static DelegadoCuocienteElectoral instance;
	private static LogicaCuocienteElectoral logicaCuocienteElectoral;
	private static LogicaDelegadosZona logicaDelegadosZona;
	private static LogicaDelegadosRegional logicaDelegadosRegional;

	// Patrón Singular
	public static DelegadoCuocienteElectoral getInstance() {
		if (instance == null) {
			instance = new DelegadoCuocienteElectoral();
			logicaCuocienteElectoral = LogicaCuocienteElectoral.getInstance();
			logicaDelegadosZona = LogicaDelegadosZona.getInstance();
			logicaDelegadosRegional = LogicaDelegadosRegional.getInstance();
		}
		return instance;
	}

	/**
	 * Lógica para calcular el cuociente electoral
	 */
	public EleCuocienteElectoral calcularCuocienteElectoral(Integer idRegistro,
			String periodoElectoral, Double totalAsocHabiles,
			Double totalDelegadosElegir, Double totalDelegadosEspeciales,
			Double finalTotalDelegadosElegir, Double cuocienteElectoral)
			throws Exception {
		return logicaCuocienteElectoral.calcularCuocienteElectoral(idRegistro,
				periodoElectoral, totalAsocHabiles, totalDelegadosElegir,
				totalDelegadosEspeciales, finalTotalDelegadosElegir,
				cuocienteElectoral);
	}
	
	/**
	 * Lógica para eliminar el cuociente electoral
	 */
	public void eliminarCuocienteElectoral(EleCuocienteElectoral cuocienteElectoral)
			throws Exception {
		
		Transaction transaction = null;
		try {
			transaction = logicaCuocienteElectoral.getSession().beginTransaction();
			
			logicaDelegadosRegional.eliminarDelegadosRegional(cuocienteElectoral.getPeriodoElectoral());
			logicaDelegadosZona.eliminarDelegadosZona(cuocienteElectoral.getPeriodoElectoral());
			logicaCuocienteElectoral.delete(cuocienteElectoral);

			logicaCuocienteElectoral.getSession().flush();
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		} finally {
			logicaCuocienteElectoral.getSession().clear();
			logicaCuocienteElectoral.getSession().close();
		}
		
		
	}

	public List<EleCuocienteElectoral> getConsultarCuocienteElectoral()
			throws Exception {
		return logicaCuocienteElectoral.getConsultarCuocienteElectoral();
	}

	public EleCuocienteElectoral getConsultarCuocienteElectoral(
			String periodoElectoral) {
		return logicaCuocienteElectoral
				.getConsultarCuocienteElectoral(periodoElectoral);
	}

	public EleCuocienteDelegadosZona calcularDelegadosZona(
			String periodoElectoral, String codRegional, String codZona,
			Double sumaHabiles) throws Exception {
		return logicaDelegadosZona.calcularDelegadosZona(periodoElectoral,
				codRegional, codZona, sumaHabiles);
	}

	public List<EleCuocienteDelegadosZona> calcularDelegadosZonaFinal(
			String periodoElectoral) throws Exception {
		return logicaDelegadosZona.calcularDelegadosZonaFinal(periodoElectoral);

	}

	public List<EleCuocienteDelegadosZona> consultarDelegadosZona(
			String periodoElectoral, String codRegional, String codZona)
			throws Exception {
		return logicaDelegadosZona.consultarDelegadosZona(periodoElectoral,
				codRegional, codZona);
	}

	/**
	 * Consulta del cuociente electoral
	 */
	public List<EleCuocienteDelegadosZona> consultarDelegadosZona(
			String periodoElectoral ) throws Exception {
		return logicaDelegadosZona.consultarDelegadosZona(periodoElectoral,
				null);
	}

	/**
	 * Consulta del cuociente electoral
	 */
	public List<EleCuocienteDelegadosZona> consultarDelegadosZona(
			String periodoElectoral, String ordenarPor) throws Exception {
		return logicaDelegadosZona.consultarDelegadosZona(periodoElectoral,
				ordenarPor);
	}
	
	public List<EleCuocienteRegional> consultarDelegadosRegionales(
			String periodoElectoral, String ordenarPor) throws Exception {
		return logicaDelegadosRegional.consultarDelegadosRegionales(periodoElectoral,
				ordenarPor);
	}

	public static LogicaCuocienteElectoral getLogicaCuocienteElectoral() {
		return logicaCuocienteElectoral;
	}

	public static void setLogicaCuocienteElectoral(
			LogicaCuocienteElectoral logicaCuocienteElectoral) {
		DelegadoCuocienteElectoral.logicaCuocienteElectoral = logicaCuocienteElectoral;
	}

	public static LogicaDelegadosZona getLogicaDelegadosZona() {
		return logicaDelegadosZona;
	}

	public static void setLogicaDelegadosZona(
			LogicaDelegadosZona logicaDelegadosZona) {
		DelegadoCuocienteElectoral.logicaDelegadosZona = logicaDelegadosZona;
	}

	public static LogicaDelegadosRegional getLogicaDelegadosRegional() {
		return logicaDelegadosRegional;
	}

	public static void setLogicaDelegadosRegional(
			LogicaDelegadosRegional logicaDelegadosRegional) {
		DelegadoCuocienteElectoral.logicaDelegadosRegional = logicaDelegadosRegional;
	}
	
	/**
	 * Metodo que consulta la informacion para generar el reporte de cuociente electoral.
	 * 
	 * @author Juan Diego Montoya
	 * @date 02/09/2016
	 * @return
	 */
	public List<ResumenCuocienteDTO> generarInformacionResumenCuociente()
			throws Exception {
		return logicaCuocienteElectoral.generarInformacionResumenCuociente();
	}
	
	

}
