package co.com.coomeva.ele.entidades.formulario;

import java.util.HashSet;
import java.util.Set;

/**
 * EleCampo entity. @author MyEclipse Persistence Tools
 */

public class EleCampo implements java.io.Serializable {

	// Fields

	private Byte codCampo;
	private String nombre;
	private String tipoCampo;
	private String tipoDato;
	private Boolean obligatorio;
	private String descripcion;
	private String atributos;
	private Set eleFormularioCampos = new HashSet(0);
	private Set eleRegistroCamposes = new HashSet(0);

	// Constructors

	/** default constructor */
	public EleCampo() {
	}

	/** minimal constructor */
	public EleCampo(Byte codCampo, String nombre, String tipoCampo,
			String tipoDato, Boolean obligatorio) {
		this.codCampo = codCampo;
		this.nombre = nombre;
		this.tipoCampo = tipoCampo;
		this.tipoDato = tipoDato;
		this.obligatorio = obligatorio;
	}

	/** full constructor */
	public EleCampo(Byte codCampo, String nombre, String tipoCampo,
			String tipoDato, Boolean obligatorio, String descripcion,
			String atributos, Set eleFormularioCampos, Set eleRegistroCamposes) {
		this.codCampo = codCampo;
		this.nombre = nombre;
		this.tipoCampo = tipoCampo;
		this.tipoDato = tipoDato;
		this.obligatorio = obligatorio;
		this.descripcion = descripcion;
		this.atributos = atributos;
		this.eleFormularioCampos = eleFormularioCampos;
		this.eleRegistroCamposes = eleRegistroCamposes;
	}

	// Property accessors

	public Byte getCodCampo() {
		return this.codCampo;
	}

	public void setCodCampo(Byte codCampo) {
		this.codCampo = codCampo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoCampo() {
		return this.tipoCampo;
	}

	public void setTipoCampo(String tipoCampo) {
		this.tipoCampo = tipoCampo;
	}

	public String getTipoDato() {
		return this.tipoDato;
	}

	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}

	public Boolean getObligatorio() {
		return this.obligatorio;
	}

	public void setObligatorio(Boolean obligatorio) {
		this.obligatorio = obligatorio;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAtributos() {
		return this.atributos;
	}

	public void setAtributos(String atributos) {
		this.atributos = atributos;
	}

	public Set getEleFormularioCampos() {
		return this.eleFormularioCampos;
	}

	public void setEleFormularioCampos(Set eleFormularioCampos) {
		this.eleFormularioCampos = eleFormularioCampos;
	}

	public Set getEleRegistroCamposes() {
		return this.eleRegistroCamposes;
	}

	public void setEleRegistroCamposes(Set eleRegistroCamposes) {
		this.eleRegistroCamposes = eleRegistroCamposes;
	}

}