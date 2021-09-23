package co.com.coomeva.ele.delegado;

import java.util.List;

import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral;
import co.com.coomeva.ele.logica.LogicaExperienciaLaboral;

public class DelegadoExperienciaLaboral 
{
	private static DelegadoExperienciaLaboral instance;
	private static LogicaExperienciaLaboral loExperienciaLaboral;
	
	//Patrón Singular
	public static DelegadoExperienciaLaboral getInstance() 
	{
		if (instance == null) {
			instance = new DelegadoExperienciaLaboral();
			loExperienciaLaboral = LogicaExperienciaLaboral.getInstance();
		}
		return instance;
	}
	
	public void crearExperienciaLaboral(String documento, String cargo, String empresa) throws Exception{
		loExperienciaLaboral.crearExperienciaLaboral(documento, cargo, empresa);
	}

	public List<EleExperienciaLaboral> consultaExperienciaLaborales(
			String documento) throws Exception {
		return loExperienciaLaboral.consultaExperienciaLaborales(documento);
	}


	public void eliminarExperienciasLaborales(String documento)
			throws Exception {
		loExperienciaLaboral.eliminarExperienciasLaborales(documento);
	}
	
	public void crearExperienciasLaboralesList(List<EleExperienciaLaboral> listExperienciaLaboral, String documento)throws Exception
	{
		loExperienciaLaboral.crearExperienciasLaboralesList(listExperienciaLaboral, documento);
	}
}
