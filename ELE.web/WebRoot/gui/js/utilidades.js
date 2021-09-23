function refreshPage(){
	location.reload(true);
	location.reload(false);
}

//deshabilita un boton de comando
function disableButton(button) {
	if (button != null) {
		button.disabled = true;
	}
}

function keyCheck(eventObj, obj){
	var keyCode;
		
	// Check For Browser Type
	if (document.all){
		keyCode=eventObj.keyCode;
	}
	else{
		keyCode=eventObj.which;
	}


	var str=obj.value;


	if(keyCode==46){
		if (str.indexOf(".")>0){
			return false;
		}
	}


	if(keyCode<48 || keyCode >58){ 
		return false;
	}
	
	return true;
}

function noCopyMouse(e) {
    var isRight = (e.button) ? (e.button == 2) : (e.which == 3);
   
    if(isRight) {
        alert('No se permite dar click derecho en este campo');
        return false;
    }
    return true;
}

function noCopyKey(e) {
    var forbiddenKeys = new Array('c','x','v');
    var keyCode = (e.keyCode) ? e.keyCode : e.which;
    var isCtrl;
 
    if(window.event){
        isCtrl = e.ctrlKey
    }
    else
        isCtrl = (window.event) ? ((e.modifiers & event.CTRL_MASK) == event.CTRL_MASK) : false;

    if(isCtrl) {
        for(i = 0; i < forbiddenKeys.length; i++) {
        	
            if(forbiddenKeys[i] == String.fromCharCode(keyCode).toLowerCase()) {
                alert('No se permite copiar ni pegar texto en este campo!');
                return false;
            }
        }
    }
    return true;
}



