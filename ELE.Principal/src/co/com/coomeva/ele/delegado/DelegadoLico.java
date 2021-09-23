package co.com.coomeva.ele.delegado;

import co.com.coomeva.ele.logica.LogicaLico;

public class DelegadoLico {
	private static DelegadoLico instance;
	private static LogicaLico logicaLico;

	//Constructor de la clase
	private DelegadoLico() {
	}

	//Patròn Singular
	public static DelegadoLico getInstance() {
		if (instance == null) {
			instance = new DelegadoLico();
			logicaLico = LogicaLico.getInstance();
		}
		return instance;
	}

	public boolean existAsesorFin(String nroIdentificacion) {
		return logicaLico.existAsesorFin(nroIdentificacion);
	}


}
