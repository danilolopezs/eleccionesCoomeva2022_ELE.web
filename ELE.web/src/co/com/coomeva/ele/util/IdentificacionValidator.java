package co.com.coomeva.ele.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
//import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

//@FacesValidator("identificacionValidator")
public class IdentificacionValidator implements Validator {

	private final String MSG_DOCUMENTO_NULL = "Debe ingresar un n\u00FAmero de Documento, Campo requerido.";
	private final String MSG_DOCUMENTO_NEGATIVO = "No se permiten n\u00FAmeros negativos.";
	private final String MSG_DOCUMENTO_MIN_5DIGITOS = "El n\u00FAmero de documento debe tener un m\u00EDnimo de 5 digitos.";
	private final String MSG_DOCUMENTO_MAX_15DIGITOS = "El n\u00FAmero de documento debe tener un m\u00E1ximo de 15 digitos.";
	private final String MSG_DOCUMENTO_CHAR_INVALIDO = "Error de validaci\u00F3n, por favor revise el n\u00FAmero de documento ingresado.";
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null) {
			FacesMessage msg = new FacesMessage(MSG_DOCUMENTO_NULL);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		String id = value.toString().trim();
		try {	
			Long idx = Long.valueOf(id);
			validarNumero(idx);
		}catch (NumberFormatException e) {
			validarNumeroExtranjeria(id);			
		}
		// /^[1-9][0-9,$]{9,11}$/gm
	}

	private void validarNumero(Long idx) {
		if (idx < 0) {
			FacesMessage msg = new FacesMessage(MSG_DOCUMENTO_NEGATIVO);
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
			throw new ValidatorException(msg);	
		}

		if (idx < 9999) {
			FacesMessage msg = new FacesMessage(MSG_DOCUMENTO_MIN_5DIGITOS);
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
			throw new ValidatorException(msg);
		}

		if (idx > 999999999999999L) {
			FacesMessage msg = new FacesMessage(MSG_DOCUMENTO_MAX_15DIGITOS);
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
			throw new ValidatorException(msg);
		}
	}
	
	private void validarNumeroExtranjeria(String id) {
		if(!id.matches("[a-zA-Z0-9]*")){
			FacesMessage msg = new FacesMessage(MSG_DOCUMENTO_CHAR_INVALIDO);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}
