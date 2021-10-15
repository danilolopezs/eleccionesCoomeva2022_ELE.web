package co.com.coomeva.ele.delegado;

import co.com.coomeva.ele.logica.LogicaClimae;
import co.com.coomeva.ele.modelo.EleAsociadoDTO;

public class DelegadoClimae {
	private static DelegadoClimae instance;
	private static LogicaClimae logicaClimae;

	//Constructor de la clase
	private DelegadoClimae() {
	}

	//Patròn Singular
	public static DelegadoClimae getInstance() {
		if (instance == null) {
			instance = new DelegadoClimae();
			logicaClimae = LogicaClimae.getInstance();
		}
		return instance;
	}

	public EleAsociadoDTO find(String idAsociado) throws Exception {
		return logicaClimae.find(idAsociado);
	}
	public boolean validaSancion(String idAsociado) throws Exception {
		return logicaClimae.validaSancion(idAsociado);
	}
}
