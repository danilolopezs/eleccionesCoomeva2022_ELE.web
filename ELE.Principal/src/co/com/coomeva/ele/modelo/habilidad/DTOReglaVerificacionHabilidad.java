package co.com.coomeva.ele.modelo.habilidad;

import java.util.Date;


public class DTOReglaVerificacionHabilidad implements Comparable{
	
	private Long consecutivoProRegla;
	private Long codigoRegla;
	private String descripcionRegla;
	private String nombreReglaJava;
	private String observacionViolacionRegla;
	private Date fechaInicioEjecucion;
	
	public Long getCodigoRegla() {
		return codigoRegla;
	}
	public void setCodigoRegla(Long codigoRegla) {
		this.codigoRegla = codigoRegla;
	}
	public String getDescripcionRegla() {
		return descripcionRegla;
	}
	public void setDescripcionRegla(String descripcionRegla) {
		this.descripcionRegla = descripcionRegla;
	}
	public String getNombreReglaJava() {
		return nombreReglaJava;
	}
	public void setNombreReglaJava(String nombreReglaJava) {
		this.nombreReglaJava = nombreReglaJava;
	}
	public String getObservacionViolacionRegla() {
		return observacionViolacionRegla;
	}
	public void setObservacionViolacionRegla(String observacionViolacionRegla) {
		this.observacionViolacionRegla = observacionViolacionRegla;
	}
	public Long getConsecutivoProRegla() {
		return consecutivoProRegla;
	}
	public void setConsecutivoProRegla(Long consecutivoProRegla) {
		this.consecutivoProRegla = consecutivoProRegla;
	}
	
	public Date getFechaInicioEjecucion() {
		return fechaInicioEjecucion;
	}
	public void setFechaInicioEjecucion(Date fechaInicioEjecucion) {
		this.fechaInicioEjecucion = fechaInicioEjecucion;
	}
	public final int compareTo(Object o) {
			if(o instanceof DTOReglaVerificacionHabilidad){
				return this.getCodigoRegla()
				.compareTo(((DTOReglaVerificacionHabilidad)o).getCodigoRegla());
			}
			return 0;
		}
}
