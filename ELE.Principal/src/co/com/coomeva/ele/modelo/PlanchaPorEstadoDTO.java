package co.com.coomeva.ele.modelo;

import java.util.Date;

public class PlanchaPorEstadoDTO {
	
	private Long numeroPlancha;
	private String asociadoCabeza;
	private Long numCedula;
	private String zona;
	private Date fechaInscripcion;
	private String estado;
	public Long getNumeroPlancha() {
		return numeroPlancha;
	}
	public void setNumeroPlancha(Long numeroPlancha) {
		this.numeroPlancha = numeroPlancha;
	}
	public String getAsociadoCabeza() {
		return asociadoCabeza;
	}
	public void setAsociadoCabeza(String asociadoCabeza) {
		this.asociadoCabeza = asociadoCabeza;
	}
	public Long getNumCedula() {
		return numCedula;
	}
	public void setNumCedula(Long numCedula) {
		this.numCedula = numCedula;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
}
