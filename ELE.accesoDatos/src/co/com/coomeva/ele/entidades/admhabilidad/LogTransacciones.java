package co.com.coomeva.ele.entidades.admhabilidad;

import java.util.Date;

/**
 * LogTransacciones entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class LogTransacciones extends AbstractLogTransacciones implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public LogTransacciones() {
	}

	/** full constructor */
	public LogTransacciones(Long consLog, String nroIdentificacion,
			String anthabil, String habil, String concpTransaccion, Date fecha,
			String usuario) {
		super(consLog, nroIdentificacion, anthabil, habil, concpTransaccion,
				fecha, usuario);
	}

}
