package co.com.coomeva.ele.entidades.planchas;

import java.util.Set;

/**
 * ElePParametros entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class ElePParametros extends AbstractElePParametros implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ElePParametros() {
	}

	/** minimal constructor */
	public ElePParametros(Long codigoParametro, String nombreParametro,
			Long codigoEstado, String tipoParametro) {
		super(codigoParametro, nombreParametro, codigoEstado, tipoParametro);
	}

	/** full constructor */
	public ElePParametros(Long codigoParametro, String nombreParametro,
			String valorParametro, Long codigoEstado, String tipoParametro,
			Set elePValorParametroses) {
		super(codigoParametro, nombreParametro, valorParametro, codigoEstado,
				tipoParametro, elePValorParametroses);
	}

}
