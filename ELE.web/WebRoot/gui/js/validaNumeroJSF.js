/**
 * 
 * @author Bernardo López 
 * 
 * Permite validar que solo se ingresen números
 * 
 * @param input
 * @return
 */

function keyCheck(eventObj, obj) {
	var keyCode;

	// Check For Browser Type
	if (document.all) {
		keyCode = eventObj.keyCode;
	} else {
		keyCode = eventObj.which;
	}

	var str = obj.value;

	if (keyCode == 46) {
		if (str.indexOf(".") > 0) {
			return false;
		}
	}
	//if the key is the backspace key or delete key
	if (keyCode!=8 && keyCode!=46)   
	{ 
		if (keyCode < 48 || keyCode > 58) {
			return false;
		}
	}
	
	return true;
}