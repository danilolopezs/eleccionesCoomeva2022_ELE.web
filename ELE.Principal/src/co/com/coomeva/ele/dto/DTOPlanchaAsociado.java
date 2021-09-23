package co.com.coomeva.ele.dto;

import java.sql.Timestamp;

public class DTOPlanchaAsociado {
	
	private Long consecutivoPlanchaAso;
	private Long numeroInscrito;
	private String tipoInscrito;
	private Timestamp fechaInscripcion;
	private Long codigoAsociado;
	private Long consecutivoPlancha;
	
	public Long getConsecutivoPlanchaAso() {
		return consecutivoPlanchaAso;
	}
	public void setConsecutivoPlanchaAso(Long consecutivoPlanchaAso) {
		this.consecutivoPlanchaAso = consecutivoPlanchaAso;
	}
	public Long getNumeroInscrito() {
		return numeroInscrito;
	}
	public void setNumeroInscrito(Long numeroInscrito) {
		this.numeroInscrito = numeroInscrito;
	}
	public String getTipoInscrito() {
		return tipoInscrito;
	}
	public void setTipoInscrito(String tipoInscrito) {
		this.tipoInscrito = tipoInscrito;
	}
	public Timestamp getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(Timestamp fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	public Long getCodigoAsociado() {
		return codigoAsociado;
	}
	public void setCodigoAsociado(Long codigoAsociado) {
		this.codigoAsociado = codigoAsociado;
	}
	public Long getConsecutivoPlancha() {
		return consecutivoPlancha;
	}
	public void setConsecutivoPlancha(Long consecutivoPlancha) {
		this.consecutivoPlancha = consecutivoPlancha;
	}
	
	
	

}
