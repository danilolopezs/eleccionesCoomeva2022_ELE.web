package co.com.coomeva.ele.delegado;

import java.util.HashMap;

import co.com.coomeva.ele.entidades.cuociente.EleCuocienteElectoral;
import co.com.coomeva.ele.logica.cuociente.LogicaDelegadosRegional;

public class DelegadoDelegadosRegional {

	private static DelegadoDelegadosRegional instance;
	private static LogicaDelegadosRegional logicaDelegadosRegional;

	// Patrón Singular
	public static DelegadoDelegadosRegional getInstance() {
		if (instance == null) {
			instance = new DelegadoDelegadosRegional();
			logicaDelegadosRegional = new LogicaDelegadosRegional();
		}
		return instance;
	}
	
	public void registrarDelegadosRegional(
			HashMap<String, Integer> delegadosRegional, EleCuocienteElectoral cuocienteElectoral) {
		logicaDelegadosRegional.registrarDelegadosRegional(delegadosRegional, cuocienteElectoral);
	}
	
}
