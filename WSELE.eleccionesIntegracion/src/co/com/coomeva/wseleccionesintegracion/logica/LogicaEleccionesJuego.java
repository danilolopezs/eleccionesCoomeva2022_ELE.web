package co.com.coomeva.wseleccionesintegracion.logica;

import co.com.coomeva.ele.delegado.DelegadoJuego;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.wseleccionesintegracion.modelo.RespuestaWSI;
import co.com.coomeva.wseleconsultahab.util.ConstantesParametrosWs;

public class LogicaEleccionesJuego {
	private static LogicaEleccionesJuego instance;

	// Constructor de la clase
	private LogicaEleccionesJuego() {
	}

	// Patrón Singular
	public static LogicaEleccionesJuego getInstance() {
		if (instance == null) {
			instance = new LogicaEleccionesJuego();
		}
		return instance;
	}

	public RespuestaWSI consultarJuego(long numeroDocumento, int fase,
			String zona) throws Exception {

		RespuestaWSI respuestaWS = new RespuestaWSI();

		String respuesta = DelegadoJuego.getInstance().verificarJuego(
				numeroDocumento, fase);
		respuestaWS.setCodRespuesta(respuesta);
		int res = Integer.parseInt(respuesta);
		switch (res) {
		case 1:
			respuestaWS
					.setDescRespuesta(ConstantesParametrosWs.PAR_MENSAJE_JUGADOR_HABILITADO_PARA_SEGUIR_JUGANDO);
			break;
		case 2:
			respuestaWS
					.setDescRespuesta(ConstantesParametrosWs.PAR_MENSAJE_JUGADOR_YA_JUGO_EN_LA_FASE);
			break;
		case 3:
			respuestaWS
					.setDescRespuesta(ConstantesParametrosWs.PAR_MENSAJE_JUGADOR_NO_FIGURA_COMO_ASOCIADO);
			break;
		case 4:
			respuestaWS
					.setDescRespuesta(ConstantesParametrosWs.PAR_MENSAJE_JUGADOR_NO_ACTIVO_COMO_ASOCIADO);
			break;
		case 5:
			respuestaWS
					.setDescRespuesta(ConstantesParametrosWs.PAR_MENSAJE_JUGADOR_NO_PERTENECE_A_ZONA_HABILITADA);
			break;
		default:
			respuestaWS.setDescRespuesta("");
		}
		respuestaWS.setZona(zona);
		respuestaWS.setCedula(numeroDocumento);
		respuestaWS.setFase(fase);

		return respuestaWS;
	}

	public RespuestaWSI registrarJuego(long numeroDocumento, int fase,
			String zona, int boletas) throws Exception {
		RespuestaWSI respuestaWS = new RespuestaWSI();

		String respuesta = DelegadoJuego.getInstance().verificarJuego(
				numeroDocumento, fase);
		if (!respuesta
				.equals(ConstantesProperties.PAR_MENSAJE_JUGADOR_HABILITADO_PARA_SEGUIR_JUGANDO)) {
			throw new Exception("No se puede registrar el juego: el jugador no existe o no esta habilitado para jugar");
		}

		for (int i = 1; i <= boletas; i++) {
			try {
				DelegadoJuego.getInstance().registrarJuego(numeroDocumento,
						fase, zona, boletas);
				respuestaWS.setCodRespuesta("1");
				respuestaWS
						.setDescRespuesta(ConstantesParametrosWs.PAR_MENSAJE_JUEGO_REGISTRADO_EXITOSAMENTE);

			} catch (Exception e) {
				throw e;
			} finally {
			}
		}
		respuestaWS.setZona(zona);
		respuestaWS.setCedula(numeroDocumento);
		respuestaWS.setFase(fase);
		return respuestaWS;
	}

}