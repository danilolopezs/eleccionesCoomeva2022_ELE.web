package co.com.coomeva.ele.dto;

/**
 * @author Christian Tanagrife Colorado cmtc4227
 * @since 2016-08-29
 * 
 * Clase DTO con las preguntas y Respuestas del formulario 176
 */
public class PreguntasFormulario176DTO {
	
	public String preguntas;
	public int respuesta; // 1 = SI, 0 = NO
	
	public PreguntasFormulario176DTO(String preguntas, int respuesta) {
		this.preguntas = preguntas;
		this.respuesta = respuesta;
	}
	
	public String getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(String preguntas) {
		this.preguntas = preguntas;
	}
	public int getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(int respuesta) {
		this.respuesta = respuesta;
	}
}
