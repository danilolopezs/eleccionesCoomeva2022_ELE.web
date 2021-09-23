package co.com.coomeva.ele.entidades.planchas;

/**
 * EleExperienciaLaboral entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class EleExperienciaLaboral extends AbstractEleExperienciaLaboral
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public EleExperienciaLaboral() {
	}

	/** full constructor */
	public EleExperienciaLaboral(EleExperienciaLaboralId id, String empresa,
			String cargo) {
		super(id, empresa, cargo);
	}

}
