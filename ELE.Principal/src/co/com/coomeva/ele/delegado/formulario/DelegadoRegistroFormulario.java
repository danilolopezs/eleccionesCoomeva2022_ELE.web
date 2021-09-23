package co.com.coomeva.ele.delegado.formulario;

import java.util.List;

import co.com.coomeva.ele.entidades.formulario.EleCampo;
import co.com.coomeva.ele.entidades.formulario.EleFormulario;
import co.com.coomeva.ele.entidades.formulario.EleRegistroCampos;
import co.com.coomeva.ele.logica.formulario.ILogicaFormulario;
import co.com.coomeva.ele.logica.formulario.LogicaFormulario;
import co.com.coomeva.ele.logica.formulario.registro.ILogicaRegistroFormulario;
import co.com.coomeva.ele.logica.formulario.registro.LogicaRegistroFormulario;

public class DelegadoRegistroFormulario {

	private static DelegadoRegistroFormulario instance;
	private static ILogicaRegistroFormulario logicaRegistroFormulario;
	
	private DelegadoRegistroFormulario(){
	}
	
	public static DelegadoRegistroFormulario getInstance(){
		if(instance == null){
			instance = new DelegadoRegistroFormulario();
		}
		return instance;
	}
	
	
	public void crearRegistroFormulario(Long cod_formulario, List<EleRegistroCampos> listaRegCampos, String userID) throws Exception {
		try{
			logicaRegistroFormulario = new LogicaRegistroFormulario();
			logicaRegistroFormulario.crearRegistroFormulario(cod_formulario, listaRegCampos, userID);
		}finally{
			logicaRegistroFormulario = null;
		}
	}
	
}
