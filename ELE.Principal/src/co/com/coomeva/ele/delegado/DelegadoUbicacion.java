package co.com.coomeva.ele.delegado;

import java.util.List;

import co.com.coomeva.ele.entidades.admhabilidad.Zonaregio1;
import co.com.coomeva.ele.logica.LogicaUbicacion;

public class DelegadoUbicacion {
	private static DelegadoUbicacion instance;
	private static LogicaUbicacion logicaUbicacion;

	//Constructor de la clase
	private DelegadoUbicacion() {
	}

	//Patròn Singular
	public static DelegadoUbicacion getInstance() {
		if (instance == null) {
			instance = new DelegadoUbicacion();
			logicaUbicacion = LogicaUbicacion.getInstance();
		}
		return instance;
	}

	public List<Object[]> consultarOficinas(Long regional, String zona)throws Exception {
		return logicaUbicacion.consultarOficinas(regional, zona);
	}

	public List<Object[]> consultarRegionales() throws Exception {
		return logicaUbicacion.consultarRegionales();
	}

	public List<Object[]> consultarZonas(Long regional) throws Exception {
		return logicaUbicacion.consultarZonas(regional);
	}
	
	
}
