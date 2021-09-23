package co.com.coomeva.ele.entidades.planchas;

/**
 * AbstractEleInhabilidades entity provides the base persistence definition of
 * the EleInhabilidades entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractEleInhabilidades implements java.io.Serializable {

	// Fields

	private EleInhabilidadesId id;
	private String inhabilidad;

	// Constructors

	/** default constructor */
	public AbstractEleInhabilidades() {
	}

	/** full constructor */
	public AbstractEleInhabilidades(EleInhabilidadesId id, String inhabilidad) {
		this.id = id;
		this.inhabilidad = inhabilidad;
	}

	// Property accessors

	public EleInhabilidadesId getId() {
		return this.id;
	}

	public void setId(EleInhabilidadesId id) {
		this.id = id;
	}

	public String getInhabilidad() {
		return this.inhabilidad;
	}

	public void setInhabilidad(String inhabilidad) {
		this.inhabilidad = inhabilidad;
	}

}