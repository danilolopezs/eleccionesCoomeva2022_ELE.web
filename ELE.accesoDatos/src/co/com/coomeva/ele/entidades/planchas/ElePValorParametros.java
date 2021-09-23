package co.com.coomeva.ele.entidades.planchas;

/**
 * ElePValorParametros entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class ElePValorParametros extends AbstractElePValorParametros implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ElePValorParametros() {
	}

	/** full constructor */
	public ElePValorParametros(ElePValorParametrosId id,
			String nombreParametro, Long codigoEstado) {
		super(id, nombreParametro, codigoEstado);
	}

}
