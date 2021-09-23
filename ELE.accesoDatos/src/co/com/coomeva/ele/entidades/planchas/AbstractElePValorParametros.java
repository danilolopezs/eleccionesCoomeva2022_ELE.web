package co.com.coomeva.ele.entidades.planchas;

/**
 * AbstractElePValorParametros entity provides the base persistence definition
 * of the ElePValorParametros entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractElePValorParametros implements
		java.io.Serializable {

	// Fields

	private ElePValorParametrosId id;
	private String nombreParametro;
	private Long codigoEstado;

	// Constructors

	/** default constructor */
	public AbstractElePValorParametros() {
	}

	/** full constructor */
	public AbstractElePValorParametros(ElePValorParametrosId id,
			String nombreParametro, Long codigoEstado) {
		this.id = id;
		this.nombreParametro = nombreParametro;
		this.codigoEstado = codigoEstado;
	}

	// Property accessors

	public ElePValorParametrosId getId() {
		return this.id;
	}

	public void setId(ElePValorParametrosId id) {
		this.id = id;
	}

	public String getNombreParametro() {
		return this.nombreParametro;
	}

	public void setNombreParametro(String nombreParametro) {
		this.nombreParametro = nombreParametro;
	}

	public Long getCodigoEstado() {
		return this.codigoEstado;
	}

	public void setCodigoEstado(Long codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

}