package co.com.coomeva.wseleccionesintegracion.delegado;

import co.com.coomeva.wseleccionesintegracion.logica.LogicaEleccionesJuego;
import co.com.coomeva.wseleccionesintegracion.modelo.RespuestaWSI;

public class DelegadoEleccionesJuego {
	private static DelegadoEleccionesJuego instance;
	private static LogicaEleccionesJuego logicaEleccionesJuego;

	// Constructor de la clase
	public DelegadoEleccionesJuego() {
	}

	// Patr�n Singular
	public static DelegadoEleccionesJuego getInstance() {
		if (instance == null) {
			instance = new DelegadoEleccionesJuego();
			logicaEleccionesJuego = LogicaEleccionesJuego.getInstance();
		}
		return instance;
	}

	public RespuestaWSI consultarJuego(long numeroDocumento, int fase,
			String zona) throws Exception {
		return logicaEleccionesJuego
				.consultarJuego(numeroDocumento, fase, zona);
	}

	public RespuestaWSI registrarJuego(long numeroDocumento, int fase,
			String zona, int boletas) throws Exception {
		return logicaEleccionesJuego.registrarJuego(numeroDocumento, fase,
				zona, boletas);
	}

}
