package co.com.coomeva.ele.delegado;

import co.com.coomeva.ele.entidades.planchas.EleSubcomision;
import co.com.coomeva.ele.logica.LogicaSubComision;

public class DelegadoSubcomision {
	private static DelegadoSubcomision instance;
	private static LogicaSubComision logicaSubComision;

	//Constructor de la clase
	private DelegadoSubcomision() {
	}

	//Patròn Singular
	public static DelegadoSubcomision getInstance() {
		if (instance == null) {
			instance = new DelegadoSubcomision();
			logicaSubComision = LogicaSubComision.getInstance();
		}
		return instance;
	}

	public boolean existSubcomision(String nroIdentificacion) throws Exception {
		return logicaSubComision.existSubcomision(nroIdentificacion);
	}

	public EleSubcomision consultarSubComision(String id) throws Exception{
		return logicaSubComision.consultarSubComision(id);
	}
	
	
	
}
