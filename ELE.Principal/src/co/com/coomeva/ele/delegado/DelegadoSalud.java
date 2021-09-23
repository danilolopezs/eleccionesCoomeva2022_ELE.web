package co.com.coomeva.ele.delegado;

import co.com.coomeva.ele.logica.LogicaSalud;

public class DelegadoSalud {
	private static DelegadoSalud instance;
	private static LogicaSalud logicaSalud;

	//Constructor de la clase
	private DelegadoSalud() {
	}

	//Patròn Singular
	public static DelegadoSalud getInstance() {
		if (instance == null) {
			instance = new DelegadoSalud();
			logicaSalud = LogicaSalud.getInstance();
		}
		return instance;
	}

	public boolean existAsesor(String nroIdentificacion) {
		return logicaSalud.existAsesor(nroIdentificacion);
	}
}