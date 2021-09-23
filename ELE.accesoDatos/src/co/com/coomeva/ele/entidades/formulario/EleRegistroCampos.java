package co.com.coomeva.ele.entidades.formulario;

/**
 * EleRegistroCampos entity. @author MyEclipse Persistence Tools
 */

public class EleRegistroCampos implements java.io.Serializable {

	// Fields

	private Long consRegistroCampos;
	private Long consRegistroFormulario;
	private Long codCampo;
	private String valor;
	private String usuarioCreacion;

	// Constructors

	/** default constructor */
	public EleRegistroCampos() {
	}

	/** full constructor */
	public EleRegistroCampos(Long consRegistroCampos,
			Long consRegistroFormulario, Long codCampo, String valor) {
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

	public Long getCodCampo() {
		return this.codCampo;
	}

	public void setCodCampo(Long codCampo) {
		this.codCampo = codCampo;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

}