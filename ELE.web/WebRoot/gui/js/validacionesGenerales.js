
/*
 * Fecha Creación: 05 Diciembre de 2006
 * Autor: Giovanni Arzayus Mera
 * UTI - COOMEVA
 */

//Coloca el foco en un elemento de la pantalla
//param:elementoFoco alamacena el nombre el
//elemento que se debe encontrar y al que se
//le debe dar el foco.
function focusElement(forma, campoId) {
	elementObject = document.getElementById(forma + ":" + campoId);
	elementObject.focus();
}


//valida que sólo se caracteres alfabeticos en un campo de texto
//especificado.
function validaCaracteresAlfabeticos() {
	if ((event.keyCode >= 65 && event.keyCode <= 90) || (event.keyCode >= 97 && event.keyCode <= 122) || event.keyCode == 8
		|| event.keyCode == 32 || event.keyCode == 222 || event.keyCode == 192
		|| event.keyCode == 9 || event.keyCode == 188 || event.keyCode == 190 || event.keyCode == 39 || event.keyCode == 37
		|| event.keyCode == 164 || event.keyCode == 165) {
		event.returnValue = true;
	} else {
		event.returnValue = false;
	}
}

//valida que sólo se caracteres alfanumericos en un campo de texto
//especificado.
function validaCaracteresAlfanumericos() {
	if ((event.keyCode >= 65 && event.keyCode <= 90) || (event.keyCode >= 97 && event.keyCode <= 122) || event.keyCode == 8) {
		event.returnValue = true;
	} else {
		if ((event.keyCode >= 48 && event.keyCode <= 57) || event.keyCode == 8 || event.keyCode == 39 || event.keyCode == 37) {
			event.returnValue = true;
		} else {
			event.returnValue = false;
		}
	}
}

//valida que sólo se digiten caracteres númericos en el campo especificado.
/**	patron = /\d/; // Solo acepta números
	patron = /\w/; // Acepta números y letras
	patron = /\D/; // No acepta números
	patron =/[A-Za-zñÑ\s]/; // igual que el ejemplo, pero acepta también las letras ñ y Ñ **/
function validaCaracteresNumericos(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla==8) return true;
    patron =/\d/;
    te = String.fromCharCode(tecla);
    return patron.test(te);
} 

//valida que sólo se digiten caracteres númericos en el campo
//especificado.
/*function validaCaracteresNumericos() {
	if (event != null) {
		if ((event.keyCode < 48 || event.keyCode > 57)) {
			if ((event.keyCode < 97 || event.keyCode > 105) && (event.keyCode != 8 && event.keyCode != 96 && event.keyCode != 35 && event.keyCode != 36 && event.keyCode != 37 && event.keyCode != 39)) {
				if (event.keyCode != 9) {
					event.returnValue = false;
				}
			}
		}
	}
}*/

//valida que sólo se digiten caracteres númericos en el campo
//especificado.
function validaCaracteresNumericosDecimales() {
	if (event != null) {
		if ((event.keyCode < 48 || event.keyCode > 57)) {
		//Acepta puntos - Separador Decimal y el signo negativo
			if (event.keyCode == 190 || event.keyCode == 110 || event.keyCode == 189 || event.keyCode == 109 ) {
				event.returnValue = true;
			} else {
				if ((event.keyCode < 97 || event.keyCode > 105) && (event.keyCode != 8 && event.keyCode != 96 && event.keyCode != 35 && event.keyCode != 36 && event.keyCode != 37 && event.keyCode != 39)) {
					if (event.keyCode != 9) {
						event.returnValue = false;
					}
				}
			}
		}
	}
}

//valida que sólo se digiten caracteres númericos en el campo
//especificado.
function validaCaracteresNumericosDecimalesNoNegativos() {
	if (event != null) {
		if ((event.keyCode < 48 || event.keyCode > 57)) {
		//Acepta puntos - Separador Decimal
			if (event.keyCode == 190 || event.keyCode == 110 ) {
				event.returnValue = true;
			} else {
				if ((event.keyCode < 97 || event.keyCode > 105) && (event.keyCode != 8 && event.keyCode != 96 && event.keyCode != 35 && event.keyCode != 36 && event.keyCode != 37 && event.keyCode != 39)) {
					if (event.keyCode != 9) {
						event.returnValue = false;
					}
				}
			}
		}
	}
}

function eliminaCaracteresInnecesarios(forma, campoId) {
	var elementObject = document.getElementById(forma + ":" + campoId);
	var texto = elementObject.value;
	texto = texto.replace(/[,]/g, ".");
	elementObject.value = texto;
}
/*function handleBrowserClose() {
	if (window.event.clientY < 0 && (window.event.clientX > (document.documentElement.clientWidth - 5) || window.event.clientX < 15)) {
		var webContextApp = obtenerElementoArreglo(location.pathname, 0, "/gui");
		urlAction = "http://" + location.host + webContextApp + "/gui/forms/ingreso/ingreso.iface?salir=s";
		location.href = urlAction;
     } 
} */

