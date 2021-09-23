package co.com.coomeva.ele.delegado;

import java.util.List;

import co.com.coomeva.ele.logica.LogicaJuego;
import co.com.coomeva.ele.modelo.EleAsociadoDatosDTO;
import co.com.coomeva.ele.modelo.EleBoletasAsignadasJuegoDTO;
import co.com.coomeva.ele.modelo.EleParticipanteJuegoDTO;
import co.com.coomeva.ele.modelo.EleZonaElectoralDTO;
import co.com.coomeva.ele.modelo.PlanchaPorEstadoDTO;
import co.com.coomeva.util.acceso.UtilAcceso;

public class DelegadoJuego {
	private static DelegadoJuego instance;
	private static LogicaJuego logicaJuego;

	// Constructor de la clase
	private DelegadoJuego() {
	}

	// Patrón Singleton
	public static DelegadoJuego getInstance() {
		if (instance == null) {
			instance = new DelegadoJuego();
			logicaJuego = LogicaJuego.getInstance();
		}
		return instance;
	}

	public String verificarJuego(Long numeroDocumento, int fase)
			throws Exception {

		return logicaJuego.verificarJuego(numeroDocumento, fase);
	}

	public void registrarJuego(Long numeroDocumento, int fase, String zona,
			int boletas) throws Exception {
		logicaJuego.registrarJuego(numeroDocumento, fase, zona, boletas);
	}

	public EleBoletasAsignadasJuegoDTO obtenerBoletasAsignadas(
			Long numeroDocumento) throws Exception {
		return logicaJuego.obtenerBoletasAsignadas(numeroDocumento);
	}

	public List<EleParticipanteJuegoDTO> consultarJuegos(String zona,
			String fase) throws Exception {
		return logicaJuego.consultarJuegos(zona, fase);
	}

	public String consultarZonaAsociado(Long numeroDocumento) throws Exception {
		return logicaJuego.consultarZonaAsociado(numeroDocumento);
	}

	public List<EleZonaElectoralDTO> consultarZonasElectorales()
			throws Exception {

		return logicaJuego.consultarZonasElectorales();
	}
}