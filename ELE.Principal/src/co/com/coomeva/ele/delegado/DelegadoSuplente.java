package co.com.coomeva.ele.delegado;

import java.util.List;

import co.com.coomeva.ele.entidades.planchas.EleSuplentes;
import co.com.coomeva.ele.logica.LogicaSuplente;
import co.com.coomeva.ele.modelo.ElePrincipalesDTO;
import co.com.coomeva.ele.modelo.EleSuplentesDTO;

public class DelegadoSuplente {
	private static DelegadoSuplente instance;
	private static LogicaSuplente logicaSuplente;
	//Constructor de la clase
	private DelegadoSuplente() {
	}

	//Patròn Singular
	public static DelegadoSuplente getInstance() {
		if (instance == null) {
			instance = new DelegadoSuplente();
			logicaSuplente = LogicaSuplente.getInstance();
		}
		return instance;
	}
	
	
	public void crearSuplente(String nroSuIdentificacion, String nroPriIdentificacion, 
			String nroCabPlancha, String primerNombre, String segundoNombre,
			String primerApellido, String segundoApellido, String profesion, String email,Long orden) throws Exception{
		logicaSuplente.crearSuplente(nroSuIdentificacion, nroPriIdentificacion, nroCabPlancha, primerNombre, segundoNombre, primerApellido, segundoApellido, profesion, email, orden);
	}
	public EleSuplentes consultarSuplente(String nroSuIdentificacion) throws Exception{
		return logicaSuplente.consultarSuplente(nroSuIdentificacion);
	}

	public List<EleSuplentesDTO> consultarSuplentes(String documento) throws Exception {
		return logicaSuplente.consultarSuplentes(documento);
	}

	public void crearSuplentes(List<EleSuplentesDTO> listaSuplentes) throws Exception{
		logicaSuplente.crearSuplentes(listaSuplentes);
	}
		
	public void eliminarSuplentes(String documento) throws Exception {
		logicaSuplente.eliminarSuplentes(documento);
	}

	public void modificarProfesionSuplente(String profesion, String id)
			throws Exception {
		logicaSuplente.modificarProfesionSuplente(profesion, id);
	}

	public String findPlanchasProfesionNative(String id) throws Exception {
		return logicaSuplente.findPlanchasProfesionNative(id);
	}
	
	
}
