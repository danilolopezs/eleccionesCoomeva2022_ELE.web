package co.com.coomeva.ele.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.coomeva.ele.entidades.planchas.EleInhabilidades;
import co.com.coomeva.ele.entidades.planchas.EleZonas;



public class EleAsociadoDTO  {
	private String id;
	private String nombre;
	private int edad;
	private String profesion;
	private String email;
	private int antiguedad;
	private String Oficina;
	private boolean estadoHabilidad;
	private EleZonas zonaPlancha;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private Long antiguedadDesdeObtencionTitulo;
	private Date fechaObtencionTitulo;
	private Date fechaVinculacion;

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public EleZonas getZonaPlancha() {
		return zonaPlancha;
	}

	public void setZonaPlancha(EleZonas zonaPlancha) {
		this.zonaPlancha = zonaPlancha;
	}
	private List<EleInhabilidades> listaInhabilidades;
	
	public boolean isEstadoHabilidad() {
		return estadoHabilidad;
	}

	public void setEstadoHabilidad(boolean estadoHabilidad) {
		this.estadoHabilidad = estadoHabilidad;
	}


	public EleAsociadoDTO() {
		listaInhabilidades = new ArrayList<EleInhabilidades>();
	}

	public void setListaInhabilidades(List<EleInhabilidades> listaInhabilidades) {
		this.listaInhabilidades = listaInhabilidades;
	}
	public void addInhabilidad(EleInhabilidades inhabilidades){
		this.listaInhabilidades.add(inhabilidades);
	}
	public List<EleInhabilidades> getListaInhabilidades() {
		return listaInhabilidades;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getProfesion() {
		return profesion;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAntiguedad() {
		return antiguedad;
	}
	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}
	public String getOficina() {
		return Oficina;
	}
	public void setOficina(String oficina) {
		Oficina = oficina;
	}

	public Long getAntiguedadDesdeObtencionTitulo() {
		return antiguedadDesdeObtencionTitulo;
	}

	public void setAntiguedadDesdeObtencionTitulo(
			Long antiguedadDesdeObtencionTitulo) {
		this.antiguedadDesdeObtencionTitulo = antiguedadDesdeObtencionTitulo;
	}

	public Date getFechaObtencionTitulo() {
		return fechaObtencionTitulo;
	}

	public void setFechaObtencionTitulo(Date fechaObtencionTitulo) {
		this.fechaObtencionTitulo = fechaObtencionTitulo;
	}

	public Date getFechaVinculacion() {
		return fechaVinculacion;
	}

	public void setFechaVinculacion(Date fechaVinculacion) {
		this.fechaVinculacion = fechaVinculacion;
	}
	
	
}
