package co.com.coomeva.ele.entidades.admhabilidad;

/**
 * AbstractZonaregio1 entity provides the base persistence definition of the
 * Zonaregio1 entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractZonaregio1 implements java.io.Serializable {

	// Fields

	private Zonaregio1Id id;

	// Constructors

	/** default constructor */
	public AbstractZonaregio1() {
	}

	/** full constructor */
	public AbstractZonaregio1(Zonaregio1Id id) {
		this.id = id;
	}

	// Property accessors

	public Zonaregio1Id getId() {
		return this.id;
	}

	public void setId(Zonaregio1Id id) {
		this.id = id;
	}

}