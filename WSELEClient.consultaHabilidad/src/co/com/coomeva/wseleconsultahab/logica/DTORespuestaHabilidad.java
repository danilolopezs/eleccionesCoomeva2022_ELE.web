package co.com.coomeva.wseleconsultahab.logica;

import java.util.List;

public class DTORespuestaHabilidad {
	
	String mensajeHabilidad;
	List<String> observacionesInhabilidades;
	
	public String getMensajeHabilidad() {
		return mensajeHabilidad;
	}
	public void setMensajeHabilidad(String mensajeHabilidad) {
		this.mensajeHabilidad = mensajeHabilidad;
	}
	public List<String> getObservacionesInhabilidades() {
		return observacionesInhabilidades;
	}
	public void setObservacionesInhabilidades(
			List<String> observacionesInhabilidades) {
		this.observacionesInhabilidades = observacionesInhabilidades;
	}
}
