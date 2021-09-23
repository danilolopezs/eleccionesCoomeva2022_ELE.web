package co.com.coomeva.wseleconsultahab.delegadoWS;

import co.com.coomeva.wseleconsultahab.delegado.DelegadoConsultaHabilidad;
import co.com.coomeva.wseleconsultahab.modelo.RespuestaWS;

public class DelegadoConsultaHabilidadWS {

	public DelegadoConsultaHabilidadWS() {
	}
	
	public RespuestaWS obtenerHabilidad(long id) throws Exception {
		
		RespuestaWS respuestaWS = new RespuestaWS();
		try {
			respuestaWS = DelegadoConsultaHabilidad.getInstance().obtenerHabilidad(id);
			
			respuestaWS.setDescRespuesta("");
			respuestaWS.setCodRespuesta("1");
			
		} catch (Exception e) {
			respuestaWS.setDescRespuesta(e.getMessage());
			respuestaWS.setCodRespuesta("0");
		}
		
		return respuestaWS;
	}
	
}
