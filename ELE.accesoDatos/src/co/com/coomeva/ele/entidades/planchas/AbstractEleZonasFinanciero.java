package co.com.coomeva.ele.entidades.planchas;

/**
 * AbstractEleZonasFinanciero entity provides the base persistence definition of
 * the EleZonasFinanciero entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractEleZonasFinanciero implements
		java.io.Serializable {

	// Fields

	private EleZonasFinancieroId id;
	private String nomZonaFin;

	// Constructors

	/** default constructor */
	public AbstractEleZonasFinanciero() {
	}

	/** full constructor */
	public AbstractEleZonasFinanciero(EleZonasFinancieroId id, String nomZonaFin) {
		this.id = id;
		this.nomZonaFin = nomZonaFin;
	}

	// Property accessors

	public EleZonasFinancieroId getId() {
		return this.id;
	}

	public void setId(EleZonasFinancieroId id) {
		this.id = id;
	}

	public String getNomZonaFin() {
		return this.nomZonaFin;
	}

	public void setNomZonaFin(String nomZonaFin) {
		this.nomZonaFin = nomZonaFin;
	}

}