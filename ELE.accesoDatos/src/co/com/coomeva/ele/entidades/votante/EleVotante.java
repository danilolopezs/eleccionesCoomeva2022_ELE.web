package co.com.coomeva.ele.entidades.votante;

import java.sql.Timestamp;

/**
 * EleVotante entity. @author MyEclipse Persistence Tools
 */

public class EleVotante implements java.io.Serializable {

	// Fields

	private Long documentoVotante;
	private Timestamp fechaRegistro;
	private String usuarioRegistro;
	private String zonaUsuarioRegistro;
	private Integer agenciaUsuarioRegistro;
	private String ipRegistro;

	// Constructors

	/** default constructor */
	public EleVotante() {
	}

	/** full constructor */
	public EleVotante(Long documentoVotante, Timestamp fechaRegistro,
			String usuarioRegistro, String zonaUsuarioRegistro,
			Integer agenciaUsuarioRegistro, String ipRegistro) {
		this.documentoVotante = documentoVotante;
		this.fechaRegistro = fechaRegistro;
		this.usuarioRegistro = usuarioRegistro;
		this.zonaUsuarioRegistro = zonaUsuarioRegistro;
		this.agenciaUsuarioRegistro = agenciaUsuarioRegistro;
		this.ipRegistro = ipRegistro;
	}

	// Property accessors

	public Long getDocumentoVotante() {
		return this.documentoVotante;
	}

	public void setDocumentoVotante(Long documentoVotante) {
		this.documentoVotante = documentoVotante;
	}

	public Timestamp getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getUsuarioRegistro() {
		return this.usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public String getZonaUsuarioRegistro() {
		return this.zonaUsuarioRegistro;
	}

	public void setZonaUsuarioRegistro(String zonaUsuarioRegistro) {
		this.zonaUsuarioRegistro = zonaUsuarioRegistro;
	}

	public Integer getAgenciaUsuarioRegistro() {
		return this.agenciaUsuarioRegistro;
	}

	public void setAgenciaUsuarioRegistro(Integer agenciaUsuarioRegistro) {
		this.agenciaUsuarioRegistro = agenciaUsuarioRegistro;
	}

	public String getIpRegistro() {
		return this.ipRegistro;
	}

	public void setIpRegistro(String ipRegistro) {
		this.ipRegistro = ipRegistro;
	}

}