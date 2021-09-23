package co.com.coomeva.wseleconsultahab.logica;


import java.util.ArrayList;
import java.util.Arrays;

import com.icesoft.faces.util.ArrayUtils;
import com.icesoft.net.messaging.expression.IsNull;

import co.com.coomeva.wseleconsultahab.modelo.DelegadoConsultaHabilidadWS;
import co.com.coomeva.wseleconsultahab.modelo.DelegadoConsultaHabilidadWSServiceLocator;
import co.com.coomeva.wseleconsultahab.modelo.RespuestaWS;

public class LogicaConsultaHabWS {
	private static LogicaConsultaHabWS instance;

//	Constructor de la clase
	private LogicaConsultaHabWS() {
	}

//	Patròn Singular
	public static LogicaConsultaHabWS getInstance() {
		if (instance == null) {
			instance = new LogicaConsultaHabWS();
		}
		return instance;
	}
	
	public DTORespuestaHabilidad consultaHabilidad(Long id) throws Exception{
		DelegadoConsultaHabilidadWSServiceLocator locator  = new DelegadoConsultaHabilidadWSServiceLocator();
		DelegadoConsultaHabilidadWS habilidadWS = locator.getconsultarHabilidad();
		RespuestaWS respuestaWS = habilidadWS.obtenerHabilidad(id.longValue());
		if (respuestaWS.getCodRespuesta().equalsIgnoreCase("0")) {
			throw new Exception(respuestaWS.getDescRespuesta());
		}
		
		DTORespuestaHabilidad dtoRespuesta = new DTORespuestaHabilidad();
		dtoRespuesta.setMensajeHabilidad(respuestaWS.getDescHabilidad());
		String[] observacionesInhabilidades = respuestaWS.getInhabilidades();
		ArrayList<String> observacionesInhabilidad = new ArrayList<String>();
		// Quito los comentarios duplicados en el detalle de inhabilidad
		// Adiciono condicional de null
		if (observacionesInhabilidades != null) 
		{
			for (int i = 0; i < observacionesInhabilidades.length; i++) 
			{
				if (!observacionesInhabilidad.contains(observacionesInhabilidades[i])) 
				{
					observacionesInhabilidad.add(observacionesInhabilidades[i]);
				}
			}
		}
		dtoRespuesta.setObservacionesInhabilidades(observacionesInhabilidad != null && observacionesInhabilidad.size() > 0 ? 
				observacionesInhabilidad : null);
		
		return dtoRespuesta;
	}
	
	public String consultaHabilidadAsociado(Long id) throws Exception{
		DelegadoConsultaHabilidadWSServiceLocator locator  = new DelegadoConsultaHabilidadWSServiceLocator();
		DelegadoConsultaHabilidadWS habilidadWS = locator.getconsultarHabilidad();
		RespuestaWS respuestaWS = habilidadWS.obtenerHabilidad(id.longValue());
		if (respuestaWS.getCodRespuesta().equalsIgnoreCase("0")) {
			throw new Exception(respuestaWS.getDescRespuesta());
		}
		
		return respuestaWS.getDescHabilidad();
	}
}
