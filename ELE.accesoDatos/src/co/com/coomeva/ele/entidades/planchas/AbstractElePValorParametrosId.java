package co.com.coomeva.ele.entidades.planchas;

/**
 * AbstractElePValorParametrosId entity provides the base persistence definition
 * of the ElePValorParametrosId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractElePValorParametrosId implements
		java.io.Serializable {

	// Fields

	private Long codigoValParametro;
	private ElePParametros elePParametros;

	// Constructors

	/** default constructor */
	public AbstractElePValorParametrosId() {
	}

	/** full constructor */
	public AbstractElePValorParametrosId(Long codigoValParametro,
			ElePParametros elePParametros) {
		this.codigoValParametro = codigoValParametro;
		this.elePParametros = elePParametros;
	}

	// Property accessors

	public Long getCodigoValParametro() {
		return this.codigoValParametro;
	}

	public void setCodigoValParametro(Long codigoValParametro) {
		this.codigoValParametro = codigoValParametro;
	}

	public ElePParametros getElePParametros() {
		return this.elePParametros;
	}

	public void setElePParametros(ElePParametros elePParametros) {
		this.elePParametros = elePParametros;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractElePValorParametrosId))
			return false;
		AbstractElePValorParametrosId castOther = (AbstractElePValorParametrosId) other;

		return ((this.getCodigoValParametro() == castOther
				.getCodigoValParametro()) || (this.getCodigoValParametro() != null
				&& castOther.getCodigoValParametro() != null && this
				.getCodigoValParametro().equals(
						castOther.getCodigoValParametro())))
				&& ((this.getElePParametros() == castOther.getElePParametros()) || (this
						.getElePParametros() != null
						&& castOther.getElePParametros() != null && this
						.getElePParametros().equals(
								castOther.getElePParametros())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodigoValParametro() == null ? 0 : this
						.getCodigoValParametro().hashCode());
		result = 37
				* result
				+ (getElePParametros() == null ? 0 : this.getElePParametros()
						.hashCode());
		return result;
	}

}