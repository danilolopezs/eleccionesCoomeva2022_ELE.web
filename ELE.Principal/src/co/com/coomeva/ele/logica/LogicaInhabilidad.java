package co.com.coomeva.ele.logica;

public class LogicaInhabilidad {

	private static LogicaInhabilidad instance;
	
	private LogicaInhabilidad() {
	}

	public static LogicaInhabilidad getInstance() {
		if (instance == null) {
			instance = new LogicaInhabilidad();
		}
		return instance;
	}
	
	
}
