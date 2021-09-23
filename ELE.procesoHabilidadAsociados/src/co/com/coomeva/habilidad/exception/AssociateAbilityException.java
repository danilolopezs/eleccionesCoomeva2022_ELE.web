package co.com.coomeva.habilidad.exception;

/**
 * Clase para el manejo de expceciones personalizadas para el requerimiento
 * de habilidad de asociados
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
 * @project ELE.procesosHabilidadAsociados
 * @class AssociateAbilityException
 * @date 1/11/2012
 */
public class AssociateAbilityException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public AssociateAbilityException() {
		super();
	}

	public AssociateAbilityException(String message, Throwable cause) {
		super(message, cause);
	}

	public AssociateAbilityException(Throwable cause) {
		super(cause);
	}

	public AssociateAbilityException(String mensaje){
		super(mensaje);
	}
}
