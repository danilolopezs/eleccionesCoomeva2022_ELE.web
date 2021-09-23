package co.com.coomeva.ele.entidades.planchas;

/**
 * AbstractEleExperienciaLaboralId entity provides the base persistence
 * definition of the EleExperienciaLaboralId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractEleExperienciaLaboralId implements
		java.io.Serializable {

	// Fields

	private Long consExperiencia;
	private EleCabPlancha eleCabPlancha;

	// Constructors

	/** default constructor */
	public AbstractEleExperienciaLaboralId() {
	}

	/** full constructor */
	public AbstractEleExperienciaLaboralId(Long consExperiencia,
			EleCabPlancha eleCabPlancha) {
		this.consExperiencia = consExperiencia;
		this.eleCabPlancha = eleCabPlancha;
	}

	// Property accessors

	public Long getConsExperiencia() {
		return this.consExperiencia;
	}

	public void setConsExperiencia(Long consExperiencia) {
		this.consExperiencia = consExperiencia;
	}

	public EleCabPlancha getEleCabPlancha() {
		return this.eleCabPlancha;
	}

	public void setEleCabPlancha(EleCabPlancha eleCabPlancha) {
		this.eleCabPlancha = eleCabPlancha;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractEleExperienciaLaboralId))
			return false;
		AbstractEleExperienciaLaboralId castOther = (AbstractEleExperienciaLaboralId) other;

		return ((this.getConsExperiencia() == castOther.getConsExperiencia()) || (this
				.getConsExperiencia() != null
				&& castOther.getConsExperiencia() != null && this
				.getConsExperiencia().equals(castOther.getConsExperiencia())))
				&& ((this.getEleCabPlancha() == castOther.getEleCabPlancha()) || (this
						.getEleCabPlancha() != null
						&& castOther.getEleCabPlancha() != null && this
						.getEleCabPlancha()
						.equals(castOther.getEleCabPlancha())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getConsExperiencia() == null ? 0 : this.getConsExperiencia()
						.hashCode());
		result = 37
				* result
				+ (getEleCabPlancha() == null ? 0 : this.getEleCabPlancha()
						.hashCode());
		return result;
	}

}