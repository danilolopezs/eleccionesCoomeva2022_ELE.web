package co.com.coomeva.ele.logica.cuociente;

import java.util.HashMap;

import co.com.coomeva.ele.entidades.cuociente.EleCuocienteElectoral;

public interface ILogicaDelegadosRegional {
	public void registrarDelegadosRegional( HashMap<String, Integer> delegadosRegional, EleCuocienteElectoral cuocienteElectoral );
}
