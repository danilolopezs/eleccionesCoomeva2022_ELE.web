package co.com.coomeva.ele.entidades.formulario;

/**
 * EleFormularioCampo entity. @author MyEclipse Persistence Tools
 */

public class EleFormularioCampo implements java.io.Serializable {

	// Fields

	private Short consFormularioCampo;
	private EleFormulario eleFormulario;
	private EleCampo eleCampo;

	// Constructors

	/** default constructor */
	public EleFormularioCampo() {
	}

	/** full constructor */
	public EleFormularioCampo(Short consFormularioCampo,
			EleFormulario eleFormulario, EleCampo eleCampo) {
		this.consFormularioCampo = consFormularioCampo;
		this.eleFormulario = eleFormulario;
		this.eleCampo = eleCampo;
	}

	// Property accessors

	public Short getConsFormularioCampo() {
		return this.consFormularioCampo;
	}

	public void setConsFormularioCampo(Short consFormularioCampo) {
		this.consFormularioCampo = consFormularioCampo;
	}

	public EleFormulario getEleFormulario() {
		return this.eleFormulario;
	}

	public void setEleFormulario(EleFormulario eleFormulario) {
		this.eleFormulario = eleFormulario;
	}

	public EleCampo getEleCampo() {
		return this.eleCampo;
	}

	public void setEleCampo(EleCampo eleCampo) {
		this.eleCampo = eleCampo;
	}

}