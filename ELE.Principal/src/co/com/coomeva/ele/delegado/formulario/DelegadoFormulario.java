package co.com.coomeva.ele.delegado.formulario;

import java.util.List;

import co.com.coomeva.ele.entidades.formulario.EleCampo;
import co.com.coomeva.ele.entidades.formulario.EleFormulario;
import co.com.coomeva.ele.logica.formulario.ILogicaFormulario;
import co.com.coomeva.ele.logica.formulario.LogicaFormulario;

public class DelegadoFormulario {

	private static DelegadoFormulario instance;
	private static ILogicaFormulario logicaFormulario;
	
	private DelegadoFormulario(){
	}
	
	public static DelegadoFormulario getInstance(){
		if(instance == null){
			instance = new DelegadoFormulario();
		}
		return instance;
	}
	
	
	public List<EleFormulario>  listarFormularios() throws Exception {
		try{
			logicaFormulario = new LogicaFormulario();
			return logicaFormulario.listarFormulariosActivos();
		}finally{
			logicaFormulario = null;
		}
	}
	
	public List<EleCampo>  listarCamposFormulario(Long codFormulario ) throws Exception {
		try{
			logicaFormulario = new LogicaFormulario();
			return logicaFormulario.listarCamposFormulario(codFormulario);
		}finally{
			logicaFormulario = null;
		}
	}
	
}
