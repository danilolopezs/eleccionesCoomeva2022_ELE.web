package co.com.coomeva.ele.entidades.planchas;

/**
 * EleSuplentes entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class EleSuplentes extends AbstractEleSuplentes implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public EleSuplentes() {
	}

	/** minimal constructor */
	public EleSuplentes(String nroSuIdentificacion, ElePlanchas elePlanchas,
			String primerNombre, String primerApellido, String profesion,
			String email) {
		super(nroSuIdentificacion, elePlanchas, primerNombre, primerApellido,
				profesion, email);
	}

	/** full constructor */
	public EleSuplentes(String nroSuIdentificacion, ElePlanchas elePlanchas,
			ElePrincipales elePrincipales, String primerNombre,
			String segundoNombre, String primerApellido,
			String segundoApellido, String profesion, String email, Long orden) {
		super(nroSuIdentificacion, elePlanchas, elePrincipales, primerNombre,
				segundoNombre, primerApellido, segundoApellido, profesion,
				email, orden);
	}

}
