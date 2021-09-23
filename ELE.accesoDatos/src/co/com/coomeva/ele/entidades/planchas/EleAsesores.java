package co.com.coomeva.ele.entidades.planchas;

/**
 * EleAsesores entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class EleAsesores extends AbstractEleAsesores implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public EleAsesores() {
	}

	/** full constructor */
	public EleAsesores(String nroIdentificacion, String empresa, String retirado) {
		super(nroIdentificacion, empresa, retirado);
	}

}
