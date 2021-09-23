package co.com.coomeva.ele.entidades.planchas;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractElePrincipales entity provides the base persistence definition of the
 * ElePrincipales entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractElePrincipales implements java.io.Serializable {

	// Fields

	private String nroPriIdentificacion;
	private ElePlanchas elePlanchas;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String profesion;
	private String email;
	private Long orden;
	private Set eleSuplenteses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractElePrincipales() {
	}

	/** minimal constructor */
	public AbstractElePrincipales(String nroPriIdentificacion,
			ElePlanchas elePlanchas, String primerNombre,
			String primerApellido, String profesion, String email) {
		this.nroPriIdentificacion = nroPriIdentificacion;
		this.elePlanchas = elePlanchas;
		this.primerNombre = primerNombre;
		this.primerApellido = primerApellido;
		this.profesion = profesion;
		this.email = email;
	}

	/** full constructor */
	public AbstractElePrincipales(String nroPriIdentificacion,
			ElePlanchas elePlanchas, String primerNombre, String segundoNombre,
			String primerApellido, String segundoApellido, String profesion,
			String email, Long orden, Set eleSuplenteses) {
		this.nroPriIdentificacion = nroPriIdentificacion;
		this.elePlanchas = elePlanchas;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.profesion = profesion;
		this.email = email;
		this.orden = orden;
		this.eleSuplenteses = eleSuplenteses;
	}

	// Property accessors

	public String getNroPriIdentificacion() {
		return this.nroPriIdentificacion;
	}

	public void setNroPriIdentificacion(String nroPriIdentificacion) {
		this.nroPriIdentificacion = nroPriIdentificacion;
	}

	public ElePlanchas getElePlanchas() {
		return this.elePlanchas;
	}

	public void setElePlanchas(ElePlanchas elePlanchas) {
		this.elePlanchas = elePlanchas;
	}

	public String getPrimerNombre() {
		return this.primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return this.segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return this.primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return this.segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getProfesion() {
		return this.profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getOrden() {
		return this.orden;
	}

	public void setOrden(Long orden) {
		this.orden = orden;
	}

	public Set getEleSuplenteses() {
		return this.eleSuplenteses;
	}

	public void setEleSuplenteses(Set eleSuplenteses) {
		this.eleSuplenteses = eleSuplenteses;
	}

}