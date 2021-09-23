package co.com.coomeva.ele.logica.cuociente;

import java.util.List;

import co.com.coomeva.ele.entidades.cuociente.EleCuocienteElectoral;

public interface ILogicaCuocienteElectoral {

	public List<EleCuocienteElectoral> getConsultarCuocienteElectoral() throws Exception;
	public EleCuocienteElectoral calcularCuocienteElectoral(Integer idRegistro, String periodoElectoral, Double totalAsocHabiles, Double totalDelegadosElegir, Double totalDelegadosEspeciales, Double finalTotalDelegadosElegir, Double cuocienteElectoral) throws Exception;
	public EleCuocienteElectoral getConsultarCuocienteElectoral(String periodoElectoral);
}
