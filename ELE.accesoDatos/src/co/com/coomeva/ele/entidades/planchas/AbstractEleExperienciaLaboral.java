package co.com.coomeva.ele.entidades.planchas;

/**
 * AbstractEleExperienciaLaboral entity provides the base persistence definition
 * of the EleExperienciaLaboral entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractEleExperienciaLaboral implements
		java.io.Serializable {

	// Fields

	private EleExperienciaLaboralId id;
	private String empresa;
	private String cargo;

	// Constructors

	/** default constructor */
	public AbstractEleExperienciaLaboral() {
	}

	/** full constructor */
	public AbstractEleExperienciaLaboral(EleExperienciaLaboralId id,
			String empresa, String cargo) {
		this.id = id;
		this.empresa = empresa;
		this.cargo = cargo;
	}

	// Property accessors

	public EleExperienciaLaboralId getId() {
		return this.id;
	}

	public void setId(EleExperienciaLaboralId id) {
		this.id = id;
	}

	public String getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}