package co.com.coomeva.ele.entidades.planchas;

/**
 * AbstractEleAsesores entity provides the base persistence definition of the
 * EleAsesores entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractEleAsesores implements java.io.Serializable {

	// Fields

	private String nroIdentificacion;
	private String empresa;
	private String retirado;

	// Constructors

	/** default constructor */
	public AbstractEleAsesores() {
	}

	/** full constructor */
	public AbstractEleAsesores(String nroIdentificacion, String empresa,
			String retirado) {
		this.nroIdentificacion = nroIdentificacion;
		this.empresa = empresa;
		this.retirado = retirado;
	}

	// Property accessors

	public String getNroIdentificacion() {
		return this.nroIdentificacion;
	}

	public void setNroIdentificacion(String nroIdentificacion) {
		this.nroIdentificacion = nroIdentificacion;
	}

	public String getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getRetirado() {
		return this.retirado;
	}

	public void setRetirado(String retirado) {
		this.retirado = retirado;
	}

}