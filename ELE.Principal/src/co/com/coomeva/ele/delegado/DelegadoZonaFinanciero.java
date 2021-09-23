package co.com.coomeva.ele.delegado;

import co.com.coomeva.ele.entidades.planchas.EleZonasFinanciero;
import co.com.coomeva.ele.logica.LogicaZonaFinanciera;

public class DelegadoZonaFinanciero {
	private static DelegadoZonaFinanciero instance;
	private static LogicaZonaFinanciera logicaZonaFinanciera;

	//Constructor de la clase
	private DelegadoZonaFinanciero() {
	}

	//Patròn Singular
	public static DelegadoZonaFinanciero getInstance() {
		if (instance == null) {
			instance = new DelegadoZonaFinanciero();
			logicaZonaFinanciera = LogicaZonaFinanciera.getInstance();
		}
		return instance;
	}

	public EleZonasFinanciero consultarZonaFinanciero(String codZonaFin)
			throws Exception {
		return logicaZonaFinanciera.consultarZonaFinanciero(codZonaFin);
	}
	
}
