package co.com.coomeva.ele.delegado;

import co.com.coomeva.ele.logica.LogicaSidco;

public class DelegadoSidco {

	private static DelegadoSidco instance;
	private static LogicaSidco logicaSie;

	//Constructor de la clase
	private DelegadoSidco() {
	}

	//Patròn Singular
	public static DelegadoSidco getInstance() {
		if (instance == null) {
			instance = new DelegadoSidco();
			logicaSie = LogicaSidco.getInstance();
		}
		return instance;
	}
	
	public Long consultarHorasDemocraciaAsociado(String nroIdentificacion)
			throws Exception {
		return logicaSie.consultarHorasDemocraciaAsociado(nroIdentificacion);
	}
}
