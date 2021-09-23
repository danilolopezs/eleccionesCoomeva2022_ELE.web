package co.com.coomeva.wseleccionesintegracion.delegadoWS;

import co.com.coomeva.wseleccionesintegracion.delegado.DelegadoEleccionesJuego;
import co.com.coomeva.wseleccionesintegracion.modelo.RespuestaWSI;

public class DelegadoEleccionesJuegoWS {

	public RespuestaWSI consultarJuego(long numeroDocumento, int fase)
			throws Exception {

		RespuestaWSI respuestaWS = new RespuestaWSI();
		try {
			respuestaWS = DelegadoEleccionesJuego.getInstance().consultarJuego(
					numeroDocumento, fase);
		} catch (Exception e) {
			respuestaWS.setDescRespuesta(e.getMessage());
			respuestaWS.setCodRespuesta("0");
		}
		return respuestaWS;
	}

	public RespuestaWSI registrarJuego(long numeroDocumento, int fase,
			String zona, int boletas) {
		RespuestaWSI respuestaWS = new RespuestaWSI();
		try {
			respuestaWS = DelegadoEleccionesJuego.getInstance().registrarJuego(
					numeroDocumento, fase, zona, boletas);
		} catch (Exception e) {
			respuestaWS.setDescRespuesta(e.getMessage());
			respuestaWS.setCodRespuesta("0");
		}
		return respuestaWS;
	}

}
