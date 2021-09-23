package co.com.coomeva.ele.entidades.planchas.dosmildoce;

import java.sql.Timestamp;

import co.com.coomeva.ele.entidades.habilidad.EleAsociado;

/**
 * ElePlanchaAsociado entity. @author MyEclipse Persistence Tools
 */

public class ElePlanchaAsociado implements java.io.Serializable {

	// Fields

	private Long consecutivoPlanchaAso;
	private EleAsociado eleAsociado;
	private Byte numeroInscrito;
	private String tipoInscrito;
	private Timestamp fechaInscripcion;
	private Long consecutivoPlancha;
	private String observacionViolacion;
	private String codViolacion;

	// Constructors

	/** default constructor */
	public ElePlanchaAsociado() {
	}

	/** minimal constructor */
	public ElePlanchaAsociado(Long consecutivoPlanchaAso) {
		this.consecutivoPlanchaAso = consecutivoPlanchaAso;
	}

	/** full constructor */
	public ElePlanchaAsociado(Long consecutivoPlanchaAso,
			EleAsociado eleAsociado, Byte numeroInscrito, String tipoInscrito,
			Timestamp fechaInscripcion, Long consecutivoPlancha, String codViolacion) {
		this.consecutivoPlanchaAso = consecutivoPlanchaAso;
		this.eleAsociado = eleAsociado;
		this.numeroInscrito = numeroInscrito;
		this.tipoInscrito = tipoInscrito;
		this.fechaInscripcion = fechaInscripcion;
		this.consecutivoPlancha = consecutivoPlancha;
		this.observacionViolacion = codViolacion;
	}

	// Property accessors

	public Long getConsecutivoPlanchaAso() {
		return this.consecutivoPlanchaAso;
	}

	public void setConsecutivoPlanchaAso(Long consecutivoPlanchaAso) {
		this.consecutivoPlanchaAso = consecutivoPlanchaAso;
	}

	public EleAsociado getEleAsociado() {
		return this.eleAsociado;
	}

	public void setEleAsociado(EleAsociado eleAsociado) {
		this.eleAsociado = eleAsociado;
	}

	public Byte getNumeroInscrito() {
		return this.numeroInscrito;
	}

	public void setNumeroInscrito(Byte numeroInscrito) {
		this.numeroInscrito = numeroInscrito;
	}

	public String getTipoInscrito() {
		return this.tipoInscrito;
	}

	public void setTipoInscrito(String tipoInscrito) {
		this.tipoInscrito = tipoInscrito;
	}

	public Timestamp getFechaInscripcion() {
		return this.fechaInscripcion;
	}

	public void setFechaInscripcion(Timestamp fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public Long getConsecutivoPlancha() {
		return this.consecutivoPlancha;
	}

	public void setConsecutivoPlancha(Long consecutivoPlancha) {
		this.consecutivoPlancha = consecutivoPlancha;
	}

	public String getObservacionViolacion() {
		return observacionViolacion;
	}

	public void setObservacionViolacion(String codViolacion) {
		this.observacionViolacion = codViolacion;
	}

	public String getCodViolacion() {
		return codViolacion;
	}

	public void setCodViolacion(String codViolacion) {
		this.codViolacion = codViolacion;
	}
	
	
}