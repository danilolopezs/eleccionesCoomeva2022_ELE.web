package co.com.coomeva.ele.delegado;

import java.sql.Timestamp;

import co.com.coomeva.ele.logica.LogicaLogAsociado;

public class DelegadoLogAsociado {

	private static DelegadoLogAsociado instance;
	private static LogicaLogAsociado logicaLogAsociado;

	// Constructor de la clase
	private DelegadoLogAsociado() {
	}

	// Patròn Singular
	public static DelegadoLogAsociado getInstance() {
		if (instance == null) {
			instance = new DelegadoLogAsociado();
			logicaLogAsociado = LogicaLogAsociado.getInstance();
		}
		return instance;
	}

	@SuppressWarnings("unused")
	public void crearRegistroLogAsociado(Integer id, String tipoTrn,
			String ipTrn, Timestamp fecha, String nroIdentificacion,
			String descripcion) throws Exception {
		logicaLogAsociado.crearRegistroLogAsociado(id, tipoTrn, ipTrn, fecha,
				nroIdentificacion, descripcion);
	}
}
