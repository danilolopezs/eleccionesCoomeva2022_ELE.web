package co.com.coomeva.ele.entidades.planchas.dosmildoce;

import java.sql.Timestamp;

/**
 * ElePlanchaAsociadoAud entity. @author MyEclipse Persistence Tools
 */

public class ElePlanchaAsociadoAud implements java.io.Serializable {

	// Fields

	private Long consecutivoAud;
	private Long consecutivoPlanchaAso;
	private Byte numeroInscrito;
	private String tipoInscrito;
	private Timestamp fechaInscripcion;
	private Long codigoAsociado;
	private Long consecutivoPlancha;
	private Timestamp fechaRegistro;

	// Constructors

	/** default constructor */
	public ElePlanchaAsociadoAud() {
	}

	/** minimal constructor */
	public ElePlanchaAsociadoAud(Long consecutivoAud) {
		this.consecutivoAud = consecutivoAud;
	}

	/** full constructor */
	public ElePlanchaAsociadoAud(Long consecutivoAud,
			Long consecutivoPlanchaAso, Byte numeroInscrito,
			String tipoInscrito, Timestamp fechaInscripcion,
			Long codigoAsociado, Long consecutivoPlancha,
			Timestamp fechaRegistro) {
		this.consecutivoAud = consecutivoAud;
		this.consecutivoPlanchaAso = consecutivoPlanchaAso;
		this.numeroInscrito = numeroInscrito;
		this.tipoInscrito = tipoInscrito;
		this.fechaInscripcion = fechaInscripcion;
		this.codigoAsociado = codigoAsociado;
		this.consecutivoPlancha = consecutivoPlancha;
		this.fechaRegistro = fechaRegistro;
	}

	// Property accessors

	public Long getConsecutivoAud() {
		return this.consecutivoAud;
	}

	public void setConsecutivoAud(Long consecutivoAud) {
		this.consecutivoAud = consecutivoAud;
	}

	public Long getConsecutivoPlanchaAso() {
		return this.consecutivoPlanchaAso;
	}

	public void setConsecutivoPlanchaAso(Long consecutivoPlanchaAso) {
		this.consecutivoPlanchaAso = consecutivoPlanchaAso;
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

	public Long getCodigoAsociado() {
		return this.codigoAsociado;
	}

	public void setCodigoAsociado(Long codigoAsociado) {
		this.codigoAsociado = codigoAsociado;
	}

	public Long getConsecutivoPlancha() {
		return this.consecutivoPlancha;
	}

	public void setConsecutivoPlancha(Long consecutivoPlancha) {
		this.consecutivoPlancha = consecutivoPlancha;
	}

	public Timestamp getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

}