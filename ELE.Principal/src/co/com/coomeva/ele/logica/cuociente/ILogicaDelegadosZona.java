package co.com.coomeva.ele.logica.cuociente;

import java.util.List;

import co.com.coomeva.ele.entidades.cuociente.EleCuocienteDelegadosZona;

public interface ILogicaDelegadosZona {

	public EleCuocienteDelegadosZona calcularDelegadosZona(String periodoElectoral, String codRegional, String codZona, Double sumaHabiles) throws Exception;
	public List<EleCuocienteDelegadosZona> calcularDelegadosZonaFinal(String periodoElectoral) throws Exception;
	public List<EleCuocienteDelegadosZona> consultarDelegadosZona(String periodoElectoral, String codRegional, String codZona) throws Exception;
}
