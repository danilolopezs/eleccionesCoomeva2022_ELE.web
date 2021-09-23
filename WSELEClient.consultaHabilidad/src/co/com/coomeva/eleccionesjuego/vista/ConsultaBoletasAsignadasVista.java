package co.com.coomeva.eleccionesjuego.vista;

import co.com.coomeva.ele.delegado.DelegadoJuego;
import co.com.coomeva.ele.modelo.EleBoletasAsignadasJuegoDTO;
import co.com.coomeva.wseleconsultahab.vista.BaseVista;

public class ConsultaBoletasAsignadasVista extends BaseVista {

	private Long documento;
	private EleBoletasAsignadasJuegoDTO dtoRespuesta;

	public Long getDocumento() {
		return documento;
	}

	public void setDocumento(Long documento) {
		this.documento = documento;
	}

	public String action_consultar() {
		dtoRespuesta = null;
		try {
			this.dtoRespuesta = DelegadoJuego.getInstance()
					.obtenerBoletasAsignadas(documento);
		} catch (Exception e) {
			getMensaje().mostrarMensaje(e.getMessage());
		}
		if(documento==null){
			getMensaje().mostrarMensaje("Señor usuario no se ha ingresado un número de cédula");
			return "";
		}
		if (dtoRespuesta == null ||(dtoRespuesta.getBoletasAsignadasFase1() <= 0 && dtoRespuesta.getBoletasAsignadasFase2() <= 0 && dtoRespuesta.getBoletasAsignadasFase3() <= 0 && dtoRespuesta.getBoletasAsignadasFase4() <= 0 ))
			getMensaje().mostrarMensaje("No se encontraron boletas asignadas al número de cédula: "+documento);
		return "";
	}

	public EleBoletasAsignadasJuegoDTO getDtoRespuesta() {
		return dtoRespuesta;
	}

	public void setDtoRespuesta(EleBoletasAsignadasJuegoDTO dtoRespuesta) {
		this.dtoRespuesta = dtoRespuesta;
	}

}
