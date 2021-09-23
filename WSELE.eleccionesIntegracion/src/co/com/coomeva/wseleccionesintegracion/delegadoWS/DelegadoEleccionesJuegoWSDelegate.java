package co.com.coomeva.wseleccionesintegracion.delegadoWS;

import co.com.coomeva.wseleccionesintegracion.delegado.DelegadoEleccionesJuego;
import co.com.coomeva.wseleccionesintegracion.modelo.RespuestaWSI;

@javax.jws.WebService(targetNamespace = "http://delegadoWS.wseleccionesintegracion.coomeva.com.co/", serviceName = "DelegadoEleccionesJuegoWSService", portName = "DelegadoEleccionesJuegoWSPort", wsdlLocation = "WEB-INF/wsdl/DelegadoEleccionesJuegoWSService.wsdl")
public class DelegadoEleccionesJuegoWSDelegate {

	co.com.coomeva.wseleccionesintegracion.delegadoWS.DelegadoEleccionesJuegoWS delegadoEleccionesJuegoWS = new co.com.coomeva.wseleccionesintegracion.delegadoWS.DelegadoEleccionesJuegoWS();

	public RespuestaWSI consultarJuego(long numeroDocumento, int fase,
			String zona) throws Exception {
		return delegadoEleccionesJuegoWS.consultarJuego(numeroDocumento, fase,
				zona);
	}

	public RespuestaWSI registrarJuego(long numeroDocumento, int fase,
			String zona, int boletas) {
		return delegadoEleccionesJuegoWS.registrarJuego(numeroDocumento, fase,
				zona, boletas);
	}

}