package co.com.coomeva.ele.entidades.planchas;

/**
 * AbstractEleInhabilidadesId entity provides the base persistence definition of
 * the EleInhabilidadesId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractEleInhabilidadesId implements
		java.io.Serializable {

	// Fields

	private Long consHabilidad;
	private String nroIdentificacion;

	// Constructors

	/** default constructor */
	public AbstractEleInhabilidadesId() {
	}

	/** full constructor */
	public AbstractEleInhabilidadesId(Long consHabilidad,
			String nroIdentificacion) {
		this.consHabilidad = consHabilidad;
		this.nroIdentificacion = nroIdentificacion;
	}

	// Property accessors

	public Long getConsHabilidad() {
		return this.consHabilidad;
	}

	public void setConsHabilidad(Long consHabilidad) {
		this.consHabilidad = consHabilidad;
	}

	public String getNroIdentificacion() {
		return this.nroIdentificacion;
	}

	public void setNroIdentificacion(String nroIdentificacion) {
		this.nroIdentificacion = nroIdentificacion;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractEleInhabilidadesId))
			return false;
		AbstractEleInhabilidadesId castOther = (AbstractEleInhabilidadesId) other;

		return ((this.getConsHabilidad() == castOther.getConsHabilidad()) || (this
				.getConsHabilidad() != null
				&& castOther.getConsHabilidad() != null && this
				.getConsHabilidad().equals(castOther.getConsHabilidad())))
				&& ((this.getNroIdentificacion() == castOther
						.getNroIdentificacion()) || (this
						.getNroIdentificacion() != null
						&& castOther.getNroIdentificacion() != null && this
						.getNroIdentificacion().equals(
								castOther.getNroIdentificacion())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getConsHabilidad() == null ? 0 : this.getConsHabilidad()
						.hashCode());
		result = 37
				* result
				+ (getNroIdentificacion() == null ? 0 : this
						.getNroIdentificacion().hashCode());
		return result;
	}

}