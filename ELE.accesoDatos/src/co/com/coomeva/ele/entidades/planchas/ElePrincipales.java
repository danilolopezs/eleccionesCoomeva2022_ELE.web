package co.com.coomeva.ele.entidades.planchas;

import java.util.Set;

/**
 * ElePrincipales entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class ElePrincipales extends AbstractElePrincipales implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ElePrincipales() {
	}

	/** minimal constructor */
	public ElePrincipales(String nroPriIdentificacion, ElePlanchas elePlanchas,
			String primerNombre, String primerApellido, String profesion,
			String email) {
		super(nroPriIdentificacion, elePlanchas, primerNombre, primerApellido,
				profesion, email);
	}

	/** full constructor */
	public ElePrincipales(String nroPriIdentificacion, ElePlanchas elePlanchas,
			String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, String profesion, String email, Long orden,
			Set eleSuplenteses) {
		super(nroPriIdentificacion, elePlanchas, primerNombre, segundoNombre,
				primerApellido, segundoApellido, profesion, email, orden,
				eleSuplenteses);
	}

}
