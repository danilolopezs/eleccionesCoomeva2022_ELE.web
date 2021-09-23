package co.com.coomeva.ele.entidades.planchas;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractEleCabPlancha entity provides the base persistence definition of the
 * EleCabPlancha entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractEleCabPlancha implements java.io.Serializable {

	// Fields

	private String nroIdentificacion;
	private ElePlanchas elePlanchas;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private Long edad;
	private String profesion;
	private String email;
	private Long antiguedad;
	private String rutaImagen;
	private String otrosEstudios;
	private String cargosDirectivos;
	private Set eleExperienciaLaborals = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractEleCabPlancha() {
	}

	/** minimal constructor */
	public AbstractEleCabPlancha(String nroIdentificacion,
			ElePlanchas elePlanchas, String primerNombre,
			String primerApellido, Long edad, String profesion, String email,
			Long antiguedad, String cargosDirectivos) {
		this.nroIdentificacion = nroIdentificacion;
		this.elePlanchas = elePlanchas;
		this.primerNombre = primerNombre;
		this.primerApellido = primerApellido;
		this.edad = edad;
		this.profesion = profesion;
		this.email = email;
		this.antiguedad = antiguedad;
		this.cargosDirectivos = cargosDirectivos;
	}

	/** full constructor */
	public AbstractEleCabPlancha(String nroIdentificacion,
			ElePlanchas elePlanchas, String primerNombre, String segundoNombre,
			String primerApellido, String segundoApellido, Long edad,
			String profesion, String email, Long antiguedad, String rutaImagen,
			String otrosEstudios, String cargosDirectivos,
			Set eleExperienciaLaborals) {
		this.nroIdentificacion = nroIdentificacion;
		this.elePlanchas = elePlanchas;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.edad = edad;
		this.profesion = profesion;
		this.email = email;
		this.antiguedad = antiguedad;
		this.rutaImagen = rutaImagen;
		this.otrosEstudios = otrosEstudios;
		this.cargosDirectivos = cargosDirectivos;
		this.eleExperienciaLaborals = eleExperienciaLaborals;
	}

	// Property accessors

	public String getNroIdentificacion() {
		return this.nroIdentificacion;
	}

	public void setNroIdentificacion(String nroIdentificacion) {
		this.nroIdentificacion = nroIdentificacion;
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

	public Long getEdad() {
		return this.edad;
	}

	public void setEdad(Long edad) {
		this.edad = edad;
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

	public Long getAntiguedad() {
		return this.antiguedad;
	}

	public void setAntiguedad(Long antiguedad) {
		this.antiguedad = antiguedad;
	}

	public String getRutaImagen() {
		return this.rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public String getOtrosEstudios() {
		return this.otrosEstudios;
	}

	public void setOtrosEstudios(String otrosEstudios) {
		this.otrosEstudios = otrosEstudios;
	}

	public String getCargosDirectivos() {
		return this.cargosDirectivos;
	}

	public void setCargosDirectivos(String cargosDirectivos) {
		this.cargosDirectivos = cargosDirectivos;
	}

	public Set getEleExperienciaLaborals() {
		return this.eleExperienciaLaborals;
	}

	public void setEleExperienciaLaborals(Set eleExperienciaLaborals) {
		this.eleExperienciaLaborals = eleExperienciaLaborals;
	}

}