package co.com.coomeva.ele.logica.formulario;

import java.util.List;

import co.com.coomeva.ele.entidades.formulario.EleCampo;
import co.com.coomeva.ele.entidades.formulario.EleFormulario;

public interface ILogicaFormulario {
	
	
	public List<EleFormulario>  listarFormularios() throws Exception;
	
	public List<EleFormulario>  listarFormulariosActivos() throws Exception;
	
	public List<EleCampo>  listarCamposFormulario(Long codFormulario ) throws Exception;
	
	public EleFormulario obtenerFormularioPorCodigo(Long cod_formulario) throws Exception;
	
	public EleCampo obtenerCampoPorCodigo(Long cod_campo) throws Exception;
	
}
