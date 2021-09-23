package co.com.coomeva.ele.entidades.formulario;

import java.util.HashSet;
import java.util.Set;

/**
 * EleFormulario entity. @author MyEclipse Persistence Tools
 */

public class EleFormulario implements java.io.Serializable {

	// Fields

	private Long codFormulario;
	private String nombre;
	private String descripcion;
	private Long estado;
	private Set eleFormularioCampos = new HashSet(0);
	private Set eleRegistroFormularios = new HashSet(0);

	// Constructors

	/** default constructor */
	public EleFormulario() {
	}

	/** minimal constructor */
	public EleFormulario(Long codFormulario, String nombre) {
		this.codFormulario = codFormulario;
		this.nombre = nombre;
	}

	/** full constructor */
	public EleFormulario(Long codFormulario, String nombre, String descripcion,
			Set eleFormularioCampos, Set eleRegistroFormularios) {
		this.codFormulario = codFormulario;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.eleFormularioCampos = eleFormularioCampos;
		this.eleRegistroFormularios = eleRegistroFormularios;
	}

	// Property accessors

	public Long getCodFormulario() {
		return this.codFormulario;
	}

	public void setCodFormulario(Long codFormulario) {
		this.codFormulario = codFormulario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set getEleFormularioCampos() {
		return this.eleFormularioCampos;
	}

	public void setEleFormularioCampos(Set eleFormularioCampos) {
		this.eleFormularioCampos = eleFormularioCampos;
	}

	public Set getEleRegistroFormularios() {
		return this.eleRegistroFormularios;
	}

	public void setEleRegistroFormularios(Set eleRegistroFormularios) {
		this.eleRegistroFormularios = eleRegistroFormularios;
	}

	public Long getEstado() {
		return estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
	}

}