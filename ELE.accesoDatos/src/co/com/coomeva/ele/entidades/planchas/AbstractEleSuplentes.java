package co.com.coomeva.ele.entidades.planchas;

/**
 * AbstractEleSuplentes entity provides the base persistence definition of the
 * EleSuplentes entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractEleSuplentes implements java.io.Serializable {

	// Fields

	private String nroSuIdentificacion;
	private ElePlanchas elePlanchas;
	private ElePrincipales elePrincipales;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String profesion;
	private String email;
	private Long orden;

	// Constructors

	/** default constructor */
	public AbstractEleSuplentes() {
	}

	/** minimal constructor */
	public AbstractEleSuplentes(String nroSuIdentificacion,
			ElePlanchas elePlanchas, String primerNombre,
			String primerApellido, String profesion, String email) {
		this.nroSuIdentificacion = nroSuIdentificacion;
		this.elePlanchas = elePlanchas;
		this.primerNombre = primerNombre;
		this.primerApellido = primerApellido;
		this.profesion = profesion;
		this.email = email;
	}

	/** full constructor */
	public AbstractEleSuplentes(String nroSuIdentificacion,
			ElePlanchas elePlanchas, ElePrincipales elePrincipales,
			String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, String profesion, String email, Long orden) {
		this.nroSuIdentificacion = nroSuIdentificacion;
		this.elePlanchas = elePlanchas;
		this.elePrincipales = elePrincipales;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.profesion = profesion;
		this.email = email;
		this.orden = orden;
	}

	// Property accessors

	public String getNroSuIdentificacion() {
		return this.nroSuIdentificacion;
	}

	public void setNroSuIdentificacion(String nroSuIdentificacion) {
		this.nroSuIdentificacion = nroSuIdentificacion;
	}

	public ElePlanchas getElePlanchas() {
		return this.elePlanchas;
	}

	public void setElePlanchas(ElePlanchas elePlanchas) {
		this.elePlanchas = elePlanchas;
	}

	public ElePrincipales getElePrincipales() {
		return this.elePrincipales;
	}

	public void setElePrincipales(ElePrincipales elePrincipales) {
		this.elePrincipales = elePrincipales;
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

}