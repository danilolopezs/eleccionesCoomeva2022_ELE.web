package co.com.coomeva.ele.dto;

import java.util.Date;

public class InformacionCabezaPlanchaDTO {
	private String numeroDocumento;
	private String nombreCompleto;
	private String antiguedadMeses;
	private String profesion;
	private String zonaElectoral;
	private String zonaElectoralEspecial;
	private String ciudad;
	private String fechaGrado;
	private String codAsociado;
	private String numeroPlancha;
	
	public InformacionCabezaPlanchaDTO(){
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getAntiguedadMeses() {
		return antiguedadMeses;
	}

	public void setAntiguedadMeses(String antiguedadMeses) {
		this.antiguedadMeses = antiguedadMeses;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getZonaElectoral() {
		return zonaElectoral;
	}

	public void setZonaElectoral(String zonaElectoral) {
		this.zonaElectoral = zonaElectoral;
	}

	public String getZonaElectoralEspecial() {
		return zonaElectoralEspecial;
	}

	public void setZonaElectoralEspecial(String zonaElectoralEspecial) {
		this.zonaElectoralEspecial = zonaElectoralEspecial;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	

	public String getCodAsociado() {
		return codAsociado;
	}

	public void setCodAsociado(String codAsociado) {
		this.codAsociado = codAsociado;
	}

	public String getFechaGrado() {
		return fechaGrado;
	}

	public void setFechaGrado(String fechaGrado) {
		this.fechaGrado = fechaGrado;
	}

	public String getNumeroPlancha() {
		return numeroPlancha;
	}

	public void setNumeroPlancha(String numeroPlancha) {
		this.numeroPlancha = numeroPlancha;
	}

	
	
}
