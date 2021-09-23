package co.com.coomeva.ele.logica.formulario.registro;

import java.util.List;

import co.com.coomeva.ele.entidades.formulario.EleCampo;
import co.com.coomeva.ele.entidades.formulario.EleFormulario;
import co.com.coomeva.ele.entidades.formulario.EleRegistroCampos;

public interface ILogicaRegistroFormulario {
	
	
	public void crearRegistroFormulario(Long cod_formulario, List<EleRegistroCampos> listaRegCampos, String userID) throws Exception;
	
	
}
