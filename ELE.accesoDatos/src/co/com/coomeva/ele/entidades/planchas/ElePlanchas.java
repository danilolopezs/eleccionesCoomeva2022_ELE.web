package co.com.coomeva.ele.entidades.planchas;

import java.util.Date;
import java.util.Set;

/**
 * ElePlanchas entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class ElePlanchas extends AbstractElePlanchas implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ElePlanchas() {
	}

	/** minimal constructor */
	public ElePlanchas(String nroCabPlancha, EleZonas eleZonas,
			Date fechaInscripcion, String estado) {
		super(nroCabPlancha, eleZonas, fechaInscripcion, estado);
	}

	/** full constructor */
	public ElePlanchas(String nroCabPlancha, EleZonas eleZonas,
			Date fechaInscripcion, String estado, Long nroPlancha,
			String descEstado, Set eleSuplenteses, Set eleCabPlanchas,
			Set elePrincipaleses) {
		super(nroCabPlancha, eleZonas, fechaInscripcion, estado, nroPlancha,
				descEstado, eleSuplenteses, eleCabPlanchas, elePrincipaleses);
	}

}
