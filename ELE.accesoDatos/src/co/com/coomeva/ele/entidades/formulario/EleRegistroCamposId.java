package co.com.coomeva.ele.entidades.formulario;

/**
 * EleRegistroCamposId entity. @author MyEclipse Persistence Tools
 */

public class EleRegistroCamposId implements java.io.Serializable {

	// Fields

	private Long consRegistroCampos;
	private Long consRegistroFormulario;
	private Short codCampo;
	private String valor;

	// Constructors

	/** default constructor */
	public EleRegistroCamposId() {
	}

	/** full constructor */
	public EleRegistroCamposId(Long consRegistroCampos,
			Long consRegistroFormulario, Short codCampo, String valor) {
		this.consRegistroCampos = consRegistroCampos;
		this.consRegistroFormulario = consRegistroFormulario;
		this.codCampo = codCampo;
		this.valor = valor;
	}

	// Property accessors

	public Long getConsRegistroCampos() {
		return this.consRegistroCampos;
	}

	public void setConsRegistroCampos(Long consRegistroCampos) {
		this.consRegistroCampos = consRegistroCampos;
	}

	public Long getConsRegistroFormulario() {
		return this.consRegistroFormulario;
	}

	public void setConsRegistroFormulario(Long consRegistroFormulario) {
		this.consRegistroFormulario = consRegistroFormulario;
	}

	public Short getCodCampo() {
		return this.codCampo;
	}

	public void setCodCampo(Short codCampo) {
		this.codCampo = codCampo;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EleRegistroCamposId))
			return false;
		EleRegistroCamposId castOther = (EleRegistroCamposId) other;

		return ((this.getConsRegistroCampos() == castOther
				.getConsRegistroCampos()) || (this.getConsRegistroCampos() != null
				&& castOther.getConsRegistroCampos() != null && this
				.getConsRegistroCampos().equals(
						castOther.getConsRegistroCampos())))
				&& ((this.getConsRegistroFormulario() == castOther
						.getConsRegistroFormulario()) || (this
						.getConsRegistroFormulario() != null
						&& castOther.getConsRegistroFormulario() != null && this
						.getConsRegistroFormulario().equals(
								castOther.getConsRegistroFormulario())))
				&& ((this.getCodCampo() == castOther.getCodCampo()) || (this
						.getCodCampo() != null
						&& castOther.getCodCampo() != null && this
						.getCodCampo().equals(castOther.getCodCampo())))
				&& ((this.getValor() == castOther.getValor()) || (this
						.getValor() != null
						&& castOther.getValor() != null && this.getValor()
						.equals(castOther.getValor())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getConsRegistroCampos() == null ? 0 : this
						.getConsRegistroCampos().hashCode());
		result = 37
				* result
				+ (getConsRegistroFormulario() == null ? 0 : this
						.getConsRegistroFormulario().hashCode());
		result = 37 * result
				+ (getCodCampo() == null ? 0 : this.getCodCampo().hashCode());
		result = 37 * result
				+ (getValor() == null ? 0 : this.getValor().hashCode());
		return result;
	}

}