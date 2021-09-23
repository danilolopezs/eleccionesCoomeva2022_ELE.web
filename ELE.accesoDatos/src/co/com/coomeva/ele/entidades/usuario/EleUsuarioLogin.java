package co.com.coomeva.ele.entidades.usuario;

import java.io.Serializable;
import java.sql.Timestamp;

public class EleUsuarioLogin implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -83178018765098378L;
	
	private Long consUsuario;
	private String userId;
	private Long numItentos;
	private Long estado;
	private Timestamp fechaIngreso;
	
	private boolean seleccionado;
	
	public Long getConsUsuario() {
		return consUsuario;
	}
	public void setConsUsuario(Long consUsuario) {
		this.consUsuario = consUsuario;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getNumItentos() {
		return numItentos;
	}
	public void setNumItentos(Long numItentos) {
		this.numItentos = numItentos;
	}
	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}
	public Timestamp getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Timestamp fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public boolean isSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
}
