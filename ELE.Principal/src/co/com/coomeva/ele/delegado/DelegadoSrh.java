package co.com.coomeva.ele.delegado;

import co.com.coomeva.ele.logica.LogicaSrh;

public class DelegadoSrh {
	private static DelegadoSrh instance;
	private static LogicaSrh logicaSrh;

	//Constructor de la clase
	private DelegadoSrh() {
	}

	//Patròn Singular
	public static DelegadoSrh getInstance() {
		if (instance == null) {
			instance = new DelegadoSrh();
			logicaSrh = LogicaSrh.getInstance();
		}
		return instance;
	}


	public boolean existEmpleado(String nroIdentificacion) {
		return logicaSrh.existEmpleado(nroIdentificacion);
	}
	
	

}
