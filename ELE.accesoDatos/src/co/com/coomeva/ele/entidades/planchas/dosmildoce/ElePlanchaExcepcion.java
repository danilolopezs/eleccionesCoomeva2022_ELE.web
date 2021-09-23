package co.com.coomeva.ele.entidades.planchas.dosmildoce;

/**
 * ElePlanchaExcepcion entity. @author MyEclipse Persistence Tools
 */

public class ElePlanchaExcepcion implements java.io.Serializable {

	// Fields

	private Long consecutivo;
	private Long consecutivoPlancha;
	private Long codigoAsociado;
	private String descExcepcion;

	// Constructors

	/** default constructor */
	public ElePlanchaExcepcion() {
	}

	/** minimal constructor */
	public ElePlanchaExcepcion(Long consecutivo, Long consecutivoPlancha,
			Long codigoAsociado) {
		this.consecutivo = consecutivo;
		this.consecutivoPlancha = consecutivoPlancha;
		this.codigoAsociado = codigoAsociado;
	}

	/** full constructor */
	public ElePlanchaExcepcion(Long consecutivo, Long consecutivoPlancha,
			Long codigoAsociado, String descExcepcion) {
		this.consecutivo = consecutivo;
		this.consecutivoPlancha = consecutivoPlancha;
		this.codigoAsociado = codigoAsociado;
		this.descExcepcion = descExcepcion;
	}

	// Property accessors

	public Long getConsecutivo() {
		return this.consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public Long getConsecutivoPlancha() {
		return this.consecutivoPlancha;
	}

	public void setConsecutivoPlancha(Long consecutivoPlancha) {
		this.consecutivoPlancha = consecutivoPlancha;
	}

	public Long getCodigoAsociado() {
		return this.codigoAsociado;
	}

	public void setCodigoAsociado(Long codigoAsociado) {
		this.codigoAsociado = codigoAsociado;
	}

	public String getDescExcepcion() {
		return this.descExcepcion;
	}

	public void setDescExcepcion(String descExcepcion) {
		this.descExcepcion = descExcepcion;
	}

}