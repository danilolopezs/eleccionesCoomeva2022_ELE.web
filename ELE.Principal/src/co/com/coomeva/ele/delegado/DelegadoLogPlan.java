package co.com.coomeva.ele.delegado;

import java.util.List;

import co.com.coomeva.ele.entidades.planchas.EleLog;
import co.com.coomeva.ele.logica.LogicaLogPlan;

public class DelegadoLogPlan 
{
	private static DelegadoLogPlan instance;
	private static LogicaLogPlan logicaLogPlan;

	//Constructor de la clase
	private DelegadoLogPlan() {
	}

	//Patròn Singular
	public static DelegadoLogPlan getInstance() {
		if (instance == null) {
			instance = new DelegadoLogPlan();
			logicaLogPlan = LogicaLogPlan.getInstance();
		}
		return instance;
	}

	public List<EleLog> consultarLogs(String identificacion,
			java.util.Date fechaInicial, java.util.Date fechaFinal,
			String tipoTransaccion, String usuario) {
		return logicaLogPlan.consultarLogs(identificacion, fechaInicial,
				fechaFinal, tipoTransaccion, usuario);
	}

	public void registrarLog(String nroCabezaPlancha, String tipoTransaccion, String nombreUsuario, String descripcion)
			throws Exception {
		logicaLogPlan.registrarLog(nroCabezaPlancha, tipoTransaccion,
				nombreUsuario, descripcion);
	}
	
	
}
