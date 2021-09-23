package co.com.coomeva.ele.entidades.planchas;

/**
 * AbstractEleZonasFinancieroId entity provides the base persistence definition
 * of the EleZonasFinancieroId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractEleZonasFinancieroId implements
		java.io.Serializable {

	// Fields

	private String codZonaFin;
	private String codZonaElec;

	// Constructors

	/** default constructor */
	public AbstractEleZonasFinancieroId() {
	}

	/** full constructor */
	public AbstractEleZonasFinancieroId(String codZonaFin, String codZonaElec) {
		this.codZonaFin = codZonaFin;
		this.codZonaElec = codZonaElec;
	}

	// Property accessors

	public String getCodZonaFin() {
		return this.codZonaFin;
	}

	public void setCodZonaFin(String codZonaFin) {
		this.codZonaFin = codZonaFin;
	}

	public String getCodZonaElec() {
		return this.codZonaElec;
	}

	public void setCodZonaElec(String codZonaElec) {
		this.codZonaElec = codZonaElec;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractEleZonasFinancieroId))
			return false;
		AbstractEleZonasFinancieroId castOther = (AbstractEleZonasFinancieroId) other;

		return ((this.getCodZonaFin() == castOther.getCodZonaFin()) || (this
				.getCodZonaFin() != null
				&& castOther.getCodZonaFin() != null && this.getCodZonaFin()
				.equals(castOther.getCodZonaFin())))
				&& ((this.getCodZonaElec() == castOther.getCodZonaElec()) || (this
						.getCodZonaElec() != null
						&& castOther.getCodZonaElec() != null && this
						.getCodZonaElec().equals(castOther.getCodZonaElec())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodZonaFin() == null ? 0 : this.getCodZonaFin()
						.hashCode());
		result = 37
				* result
				+ (getCodZonaElec() == null ? 0 : this.getCodZonaElec()
						.hashCode());
		return result;
	}

}