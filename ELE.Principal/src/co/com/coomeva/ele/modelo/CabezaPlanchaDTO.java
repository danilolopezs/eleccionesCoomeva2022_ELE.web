package co.com.coomeva.ele.modelo;

public class CabezaPlanchaDTO 
{
	 private String identificacion;
     private String nombres;
     private String apellidos;
     private String profesion;
     private String tipoInscrito;
     private String numeroInscrito;
     private boolean poseeExcepciones;
     
     private String excepcion;
     
     
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getProfesion() {
		return profesion;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	public String getTipoInscrito() {
		return tipoInscrito;
	}
	public void setTipoInscrito(String tipoInscrito) {
		this.tipoInscrito = tipoInscrito;
	}
	public String getNumeroInscrito() {
		return numeroInscrito;
	}
	public void setNumeroInscrito(String numeroInscrito) {
		this.numeroInscrito = numeroInscrito;
	}
	public String getExcepcion() {
		return excepcion;
	}
	public void setExcepcion(String excepcion) {
		this.excepcion = excepcion;
	}
	public boolean isPoseeExcepciones() {
		return poseeExcepciones;
	}
	public void setPoseeExcepciones(boolean poseeExcepciones) {
		this.poseeExcepciones = poseeExcepciones;
	}
}
