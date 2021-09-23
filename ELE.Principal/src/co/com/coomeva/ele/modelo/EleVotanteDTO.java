package co.com.coomeva.ele.modelo;

import java.sql.Timestamp;

public class EleVotanteDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long documentoVotante;
	private Timestamp fechaRegistro;
	private String usuarioRegistro;
	private String zonaUsuarioRegistro;
	private Integer agenciaUsuarioRegistro;
	private String ipRegistro;
	
	public EleVotanteDTO() {
		this.documentoVotante = 0L;
		this.fechaRegistro = null;
		this.usuarioRegistro = "";
		this.zonaUsuarioRegistro = "";
		this.ipRegistro = "";
		this.agenciaUsuarioRegistro = 0;
	}

	public Long getDocumentoVotante() {
		return documentoVotante;
	}

	public void setDocumentoVotante(Long documentoVotante) {
		this.documentoVotante = documentoVotante;
	}

	public Timestamp getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public String getZonaUsuarioRegistro() {
		return zonaUsuarioRegistro;
	}

	public void setZonaUsuarioRegistro(String zonaUsuarioRegistro) {
		this.zonaUsuarioRegistro = zonaUsuarioRegistro;
	}

	public Integer getAgenciaUsuarioRegistro() {
		return agenciaUsuarioRegistro;
	}

	public void setAgenciaUsuarioRegistro(Integer agenciaUsuarioRegistro) {
		this.agenciaUsuarioRegistro = agenciaUsuarioRegistro;
	}

	public String getIpRegistro() {
		return ipRegistro;
	}

	public void setIpRegistro(String ipRegistro) {
		this.ipRegistro = ipRegistro;
	}

}
