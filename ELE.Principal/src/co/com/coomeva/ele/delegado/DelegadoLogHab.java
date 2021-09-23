package co.com.coomeva.ele.delegado;

import java.util.Date;
import java.util.List;

import co.com.coomeva.ele.entidades.admhabilidad.LogTransacciones;
import co.com.coomeva.ele.logica.LogicaLogHab;

public class DelegadoLogHab {
	private static DelegadoLogHab instance;
	private static LogicaLogHab logicaLogHab;

	//Constructor de la clase
	private DelegadoLogHab() {
	}

	//Patròn Singular
	public static DelegadoLogHab getInstance() {
		if (instance == null) {
			instance = new DelegadoLogHab();
			logicaLogHab = LogicaLogHab.getInstance();
		}
		return instance;
	}
	
	public void registrarLog(String nroIdentificacion,String antHabil,String habil, String concpTransaccion, String usuario)throws Exception{
		logicaLogHab.registrarLog(nroIdentificacion,antHabil, habil, concpTransaccion, usuario);
		
	}

	public List<LogTransacciones> consultarLogs(String identificacion,
			Date fechaInicial, Date fechaFinal, String estado, String usuario) {
		
		return logicaLogHab.consultarLogs(identificacion,fechaInicial, fechaFinal, estado, usuario) ;
	}
}
