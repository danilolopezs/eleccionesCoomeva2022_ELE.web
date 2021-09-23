package co.com.coomeva.ele.entidades.planchas;

import java.util.Date;

/**
 * EleLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class EleLog extends AbstractEleLog implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public EleLog() {
	}

	/** full constructor */
	public EleLog(Long consecutivo, String nroCaboPlancha, String tipotrans,
			Date fecha, String usuario, String descripcion) {
		super(consecutivo, nroCaboPlancha, tipotrans, fecha, usuario,
				descripcion);
	}

}
