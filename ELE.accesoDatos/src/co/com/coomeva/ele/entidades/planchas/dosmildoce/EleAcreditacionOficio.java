package co.com.coomeva.ele.entidades.planchas.dosmildoce;

import java.sql.Timestamp;

/**
 * EleAcreditacionOficio entity. @author MyEclipse Persistence Tools
 */

public class EleAcreditacionOficio implements java.io.Serializable {

	// Fields

	private Long codigoAsociado;
	private Timestamp fechaRegistro;
	private String descripcionOficio;
	private Timestamp fechaImpresion;

	// Constructors

	/** default constructor */
	public EleAcreditacionOficio() {
	}

	/** minimal constructor */
	public EleAcreditacionOficio(Long codigoAsociado) {
		this.codigoAsociado = codigoAsociado;
	}

	/** full constructor */
	public EleAcreditacionOficio(Long codigoAsociado, Timestamp fechaRegistro,
			String descripcionOficio, Timestamp fechaImpresion) {
		this.codigoAsociado = codigoAsociado;
		this.fechaRegistro = fechaRegistro;
		this.descripcionOficio = descripcionOficio;
		this.fechaImpresion = fechaImpresion;
	}

	// Property accessors

	public Long getCodigoAsociado() {
		return this.codigoAsociado;
	}

	public void setCodigoAsociado(Long codigoAsociado) {
		this.codigoAsociado = codigoAsociado;
	}

	public Timestamp getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getDescripcionOficio() {
		return this.descripcionOficio;
	}

	public void setDescripcionOficio(String descripcionOficio) {
		this.descripcionOficio = descripcionOficio;
	}

	public Timestamp getFechaImpresion() {
		return this.fechaImpresion;
	}

	public void setFechaImpresion(Timestamp fechaImpresion) {
		this.fechaImpresion = fechaImpresion;
	}

}