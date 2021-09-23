package co.com.coomeva.ele.entidades.admhabilidad;

/**
 * AbstractZonaregio1Id entity provides the base persistence definition of the
 * Zonaregio1Id entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractZonaregio1Id implements java.io.Serializable {

	// Fields

	private Long agcori;
	private String nomagc;
	private Long codreg;
	private String codnom;
	private String zona;
	private String codnom01;

	// Constructors

	/** default constructor */
	public AbstractZonaregio1Id() {
	}

	/** full constructor */
	public AbstractZonaregio1Id(Long agcori, String nomagc, Long codreg,
			String codnom, String zona, String codnom01) {
		this.agcori = agcori;
		this.nomagc = nomagc;
		this.codreg = codreg;
		this.codnom = codnom;
		this.zona = zona;
		this.codnom01 = codnom01;
	}

	// Property accessors

	public Long getAgcori() {
		return this.agcori;
	}

	public void setAgcori(Long agcori) {
		this.agcori = agcori;
	}

	public String getNomagc() {
		return this.nomagc;
	}

	public void setNomagc(String nomagc) {
		this.nomagc = nomagc;
	}

	public Long getCodreg() {
		return this.codreg;
	}

	public void setCodreg(Long codreg) {
		this.codreg = codreg;
	}

	public String getCodnom() {
		return this.codnom;
	}

	public void setCodnom(String codnom) {
		this.codnom = codnom;
	}

	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getCodnom01() {
		return this.codnom01;
	}

	public void setCodnom01(String codnom01) {
		this.codnom01 = codnom01;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractZonaregio1Id))
			return false;
		AbstractZonaregio1Id castOther = (AbstractZonaregio1Id) other;

		return ((this.getAgcori() == castOther.getAgcori()) || (this
				.getAgcori() != null
				&& castOther.getAgcori() != null && this.getAgcori().equals(
				castOther.getAgcori())))
				&& ((this.getNomagc() == castOther.getNomagc()) || (this
						.getNomagc() != null
						&& castOther.getNomagc() != null && this.getNomagc()
						.equals(castOther.getNomagc())))
				&& ((this.getCodreg() == castOther.getCodreg()) || (this
						.getCodreg() != null
						&& castOther.getCodreg() != null && this.getCodreg()
						.equals(castOther.getCodreg())))
				&& ((this.getCodnom() == castOther.getCodnom()) || (this
						.getCodnom() != null
						&& castOther.getCodnom() != null && this.getCodnom()
						.equals(castOther.getCodnom())))
				&& ((this.getZona() == castOther.getZona()) || (this.getZona() != null
						&& castOther.getZona() != null && this.getZona()
						.equals(castOther.getZona())))
				&& ((this.getCodnom01() == castOther.getCodnom01()) || (this
						.getCodnom01() != null
						&& castOther.getCodnom01() != null && this
						.getCodnom01().equals(castOther.getCodnom01())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAgcori() == null ? 0 : this.getAgcori().hashCode());
		result = 37 * result
				+ (getNomagc() == null ? 0 : this.getNomagc().hashCode());
		result = 37 * result
				+ (getCodreg() == null ? 0 : this.getCodreg().hashCode());
		result = 37 * result
				+ (getCodnom() == null ? 0 : this.getCodnom().hashCode());
		result = 37 * result
				+ (getZona() == null ? 0 : this.getZona().hashCode());
		result = 37 * result
				+ (getCodnom01() == null ? 0 : this.getCodnom01().hashCode());
		return result;
	}

}