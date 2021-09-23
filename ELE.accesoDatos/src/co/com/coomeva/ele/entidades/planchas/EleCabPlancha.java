package co.com.coomeva.ele.entidades.planchas;

import java.util.Set;

/**
 * EleCabPlancha entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class EleCabPlancha extends AbstractEleCabPlancha implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public EleCabPlancha() {
	}

	/** minimal constructor */
	public EleCabPlancha(String nroIdentificacion, ElePlanchas elePlanchas,
			String primerNombre, String primerApellido, Long edad,
			String profesion, String email, Long antiguedad,
			String cargosDirectivos) {
		super(nroIdentificacion, elePlanchas, primerNombre, primerApellido,
				edad, profesion, email, antiguedad, cargosDirectivos);
	}

	/** full constructor */
	public EleCabPlancha(String nroIdentificacion, ElePlanchas elePlanchas,
			String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, Long edad, String profesion, String email,
			Long antiguedad, String rutaImagen, String otrosEstudios,
			String cargosDirectivos, Set eleExperienciaLaborals) {
		super(nroIdentificacion, elePlanchas, primerNombre, segundoNombre,
				primerApellido, segundoApellido, edad, profesion, email,
				antiguedad, rutaImagen, otrosEstudios, cargosDirectivos,
				eleExperienciaLaborals);
	}

}
