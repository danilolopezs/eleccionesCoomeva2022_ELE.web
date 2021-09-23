package co.com.coomeva.ele.entidades.planchas.dosmildoce;

/**
 * EleDetalleFormatoPlanchaId entity. @author MyEclipse Persistence Tools
 */

public class EleDetalleFormatoPlanchaId implements java.io.Serializable {

	// Fields

	private Long codigoAsociado;
	private Byte codigoFormato;

	// Constructors

	/** default constructor */
	public EleDetalleFormatoPlanchaId() {
	}

	/** minimal constructor */
	public EleDetalleFormatoPlanchaId(Long codigoAsociado) {
		this.codigoAsociado = codigoAsociado;
	}

	/** full constructor */
	public EleDetalleFormatoPlanchaId(Long codigoAsociado, Byte codigoFormato) {
		this.codigoAsociado = codigoAsociado;
		this.codigoFormato = codigoFormato;
	}

	// Property accessors

	public Long getCodigoAsociado() {
		return this.codigoAsociado;
	}

	public void setCodigoAsociado(Long codigoAsociado) {
		this.codigoAsociado = codigoAsociado;
	}

	public Byte getCodigoFormato() {
		return this.codigoFormato;
	}

	public void setCodigoFormato(Byte codigoFormato) {
		this.codigoFormato = codigoFormato;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EleDetalleFormatoPlanchaId))
			return false;
		EleDetalleFormatoPlanchaId castOther = (EleDetalleFormatoPlanchaId) other;

		return ((this.getCodigoAsociado() == castOther.getCodigoAsociado()) || (this
				.getCodigoAsociado() != null
				&& castOther.getCodigoAsociado() != null && this
				.getCodigoAsociado().equals(castOther.getCodigoAsociado())))
				&& ((this.getCodigoFormato() == castOther.getCodigoFormato()) || (this
						.getCodigoFormato() != null
						&& castOther.getCodigoFormato() != null && this
						.getCodigoFormato()
						.equals(castOther.getCodigoFormato())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodigoAsociado() == null ? 0 : this.getCodigoAsociado()
						.hashCode());
		result = 37
				* result
				+ (getCodigoFormato() == null ? 0 : this.getCodigoFormato()
						.hashCode());
		return result;
	}

}