package co.com.coomeva.ele.delegado;


import java.util.List;

import co.com.coomeva.ele.entidades.planchas.EleZonas;
import co.com.coomeva.ele.logica.LogicaZona;
import co.com.coomeva.ele.modelo.EleZonaElectoralEspecialDTO;

public class DelegadoZona {
	private static DelegadoZona instance;
	private static LogicaZona logicaZona;
	

	//Constructor de la clase
	private DelegadoZona() {
	}

	//Patr�n Singular
	public static DelegadoZona getInstance() {
		if (instance == null) {
			instance = new DelegadoZona();
			logicaZona = LogicaZona.getInstance();
		}
		return instance;
	}
	
	public EleZonas consultarZona(String zona) throws Exception {
		
		return logicaZona.consultarZona(zona);
	}
	public List<EleZonas> consultarZonas() throws Exception {
		
		return logicaZona.consultarZonas();
	}

	public EleZonas consultarZonaPlancha(String nroCabIdentificacion)
			throws Exception {
		return logicaZona.consultarZonaPlancha(nroCabIdentificacion);
	}

}
