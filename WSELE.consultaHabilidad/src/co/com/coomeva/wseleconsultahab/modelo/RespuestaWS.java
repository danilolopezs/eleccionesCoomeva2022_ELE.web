package co.com.coomeva.wseleconsultahab.modelo;

import java.io.Serializable;

public class RespuestaWS implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String codRespuesta;
	private String descRespuesta;
	private String codHabilidad;
	private String codVotacion;
	private String descHabilidad;
	private String descVotacion;
	private String[] inhabilidades;
	
	public String getDescHabilidad() {
		return descHabilidad;
	}
	public void setDescHabilidad(String descHabilidad) {
		this.descHabilidad = descHabilidad;
	}
	public String getDescVotacion() {
		return descVotacion;
	}
	public void setDescVotacion(String descVotacion) {
		this.descVotacion = descVotacion;
	}
	public String getCodHabilidad() {
		return codHabilidad;
	}
	public void setCodHabilidad(String codHabilidad) {
		this.codHabilidad = codHabilidad;
	}
	public String getCodVotacion() {
		return codVotacion;
	}
	public void setCodVotacion(String codVotacion) {
		this.codVotacion = codVotacion;
	}
	public String getCodRespuesta() {
		return codRespuesta;
	}
	public void setCodRespuesta(String codRespuesta) {
		this.codRespuesta = codRespuesta;
	}
	public String getDescRespuesta() {
		return descRespuesta;
	}
	public void setDescRespuesta(String descRespuesta) {
		this.descRespuesta = descRespuesta;
	}
	public String[] getInhabilidades() {
		return inhabilidades;
	}
	public void setInhabilidades(String[] inhabilidades) {
		this.inhabilidades = inhabilidades;
	}
}

