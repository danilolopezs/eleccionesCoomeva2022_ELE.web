package co.com.coomeva.ele.entidades.formulario;

import java.sql.Timestamp;

/**
 * EleRegistroFormulario entity. @author MyEclipse Persistence Tools
 */

public class EleRegistroFormulario implements java.io.Serializable {

	// Fields

	private Long consRegistroFormulario;
	private Long codFormulario;
	private Timestamp fechaRegistro;
	private String usuarioCreacion;

	// Constructors

	/** default constructor */
	public EleRegistroFormulario() {
	}

	/** full constructor */
	public EleRegistroFormulario(Long consRegistroFormulario,
			Long codFormulario, Timestamp fechaRegistro) {
		this.consRegistroFormulario = consRegistroFormulario;
		this.codFormulario = codFormulario;
		this.fechaRegistro = fechaRegistro;
	}

	// Property accessors

	public Long getConsRegistroFormulario() {
		return this.consRegistroFormulario;
	}

	public void setConsRegistroFormulario(Long consRegistroFormulario) {
		this.consRegistroFormulario = consRegistroFormulario;
	}

	public Long getCodFormulario() {
		return this.codFormulario;
	}

	public void setCodFormulario(Long codFormulario) {
		this.codFormulario = codFormulario;
	}

	public Timestamp getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
}