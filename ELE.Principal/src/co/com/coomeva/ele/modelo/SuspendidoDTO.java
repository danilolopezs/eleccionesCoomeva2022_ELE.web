package co.com.coomeva.ele.modelo;

import co.com.coomeva.ele.entidades.habilidad.EleSuspendido;

public class SuspendidoDTO extends EleSuspendido
{
	 private Long identificacion;
     private String motivo;
     private String estado;
     
     
	public Long getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(Long identificacion) {
		this.identificacion = identificacion;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
