package co.com.coomeva.ele.delegado;

import co.com.coomeva.ele.logica.LogicaSie;

public class DelegadoSie {
	private static DelegadoSie instance;
	private static LogicaSie logicaSie;

	//Constructor de la clase
	private DelegadoSie() {
	}

	//Patròn Singular
	public static DelegadoSie getInstance() {
		if (instance == null) {
			instance = new DelegadoSie();
			logicaSie = LogicaSie.getInstance();
		}
		return instance;
	}
	public int calculateTime(String time) throws Exception {
		return logicaSie.calculateTime(time);
	}

	public boolean validateHorasDemocracia(String nroIdentificacion)
			throws Exception {
		return logicaSie.validateHorasDemocracia(nroIdentificacion);
	}
	

}
