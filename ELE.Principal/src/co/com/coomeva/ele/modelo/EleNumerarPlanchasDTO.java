package co.com.coomeva.ele.modelo;

import java.util.Date;


public class EleNumerarPlanchasDTO 
{
	private String noCabezaPlancha;
	private String apellidos;
	private String nombres;
	private int noPrincipales;
	private int noSuplentes;
	private Date fechaInscripcion;
	private String numerarPlancha;
	private boolean actualiza;
	private int index;

	public String getNoCabezaPlancha() {
		return noCabezaPlancha;
	}
	public void setNoCabezaPlancha(String noCabezaPlancha) {
		this.noCabezaPlancha = noCabezaPlancha;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public int getNoPrincipales() {
		return noPrincipales;
	}
	public void setNoPrincipales(int noPrincipales) {
		this.noPrincipales = noPrincipales;
	}
	public int getNoSuplentes() {
		return noSuplentes;
	}
	public void setNoSuplentes(int noSuplentes) {
		this.noSuplentes = noSuplentes;
	}
	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	public String getNumerarPlancha() {
		return numerarPlancha;
	}
	public void setNumerarPlancha(String numerarPlancha) {
		this.numerarPlancha = numerarPlancha;
	}
	public boolean isActualiza() {
		return actualiza;
	}
	public void setActualiza(boolean actualiza) {
		this.actualiza = actualiza;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
