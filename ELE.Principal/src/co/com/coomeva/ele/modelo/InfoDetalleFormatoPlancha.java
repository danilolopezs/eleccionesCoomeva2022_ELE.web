package co.com.coomeva.ele.modelo;

import org.hibernate.Hibernate;

public class InfoDetalleFormatoPlancha {

	private String resolucion;
	private String acta;
	private String fechaActa;
	private String comision;
	
	public String getResolucion() {
		return resolucion;
	}
	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}
	public String getActa() {
		return acta;
	}
	public void setActa(String acta) {
		this.acta = acta;
	}
	public String getFechaActa() {
		return fechaActa;
	}
	public void setFechaActa(String fechaActa) {
		this.fechaActa = fechaActa;
	}
	public String getComision() {
		return comision;
	}
	public void setComision(String comision) {
		this.comision = comision;
	}
	
	
	
}
