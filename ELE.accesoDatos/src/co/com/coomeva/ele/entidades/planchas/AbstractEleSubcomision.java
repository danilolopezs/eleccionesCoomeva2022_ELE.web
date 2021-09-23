package co.com.coomeva.ele.entidades.planchas;

/**
 * AbstractEleSubcomision entity provides the base persistence definition of the
 * EleSubcomision entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractEleSubcomision implements java.io.Serializable {

	// Fields

	private String nroIdentificacion;
	private String subcomisionzona;

	// Constructors

	/** default constructor */
	public AbstractEleSubcomision() {
	}

	/** full constructor */
	public AbstractEleSubcomision(String nroIdentificacion,
			String subcomisionzona) {
		this.nroIdentificacion = nroIdentificacion;
		this.subcomisionzona = subcomisionzona;
	}

	// Property accessors

	public String getNroIdentificacion() {
		return this.nroIdentificacion;
	}

	public void setNroIdentificacion(String nroIdentificacion) {
		this.nroIdentificacion = nroIdentificacion;
	}

	public String getSubcomisionzona() {
		return this.subcomisionzona;
	}

	public void setSubcomisionzona(String subcomisionzona) {
		this.subcomisionzona = subcomisionzona;
	}

}