package co.com.coomeva.ele.exception;


/**
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
 * @project ELE.Principal
 * @class registrarPlanchaException
 * @date 1/12/2012
 */
public class EleccionesDelegadosException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public EleccionesDelegadosException() {
		super();
	}

	public EleccionesDelegadosException(String message, Throwable cause) {
		super(message, cause);
	}

	public EleccionesDelegadosException(Throwable cause) {
		super(cause);
	}

	public EleccionesDelegadosException(String mensaje){
		super(mensaje);
	}
}
