package co.com.coomeva.ele.logica.formulario;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import co.com.coomeva.ele.entidades.formulario.EleCampo;
import co.com.coomeva.ele.entidades.formulario.EleCampoDAO;
import co.com.coomeva.ele.entidades.formulario.EleFormulario;
import co.com.coomeva.ele.entidades.formulario.EleFormularioDAO;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;


public class LogicaFormulario implements ILogicaFormulario {

	
	private EleFormularioDAO formularioDAO = new EleFormularioDAO();
	private EleCampoDAO campoDAO = new EleCampoDAO();
	
	private static LogicaFormulario instance;

	
	public LogicaFormulario(){
	}
	
	public static LogicaFormulario getInstance() {
		if (instance == null) {
			instance = new LogicaFormulario();
		}
		return instance;
	}
	
	public List<EleFormulario>  listarFormularios(){
		
		List<EleFormulario> formulariosList = new ArrayList<EleFormulario>();
		try {
			formulariosList = formularioDAO.findAll();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return formulariosList;
	}
	
	public List<EleFormulario>  listarFormulariosActivos(){
		
		List<EleFormulario> formulariosList = new ArrayList<EleFormulario>();
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		Criteria c1 = session.createCriteria(EleFormulario.class); 
		c1.add(Restrictions.eq("estado", 1l ));
		
		try {
			formulariosList = c1.list();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return formulariosList;
	}
	
	public List<EleCampo>  listarCamposFormulario(Long codFormulario ){
		List <EleCampo> listCampos = new ArrayList<EleCampo>();
		
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		Criteria c1 = session.createCriteria(EleCampo.class); 
		Criteria c2 = c1.createCriteria("eleFormularioCampos");
		Criteria c3 = c2.createCriteria("eleFormulario");
		c3.add(Restrictions.eq("codFormulario", codFormulario ));
		
		try {
			listCampos = c3.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listCampos;
	}

	public EleFormulario obtenerFormularioPorCodigo(Long cod_formulario){
		
		EleFormulario eleFormulario = null;
		try {
			eleFormulario = formularioDAO.findById(cod_formulario.byteValue());
		} catch (Exception e){
			e.printStackTrace();
		}
		return eleFormulario;
	}
	
	public EleCampo obtenerCampoPorCodigo(Long cod_campo){
		
		EleCampo eleCampo = null;
		try {
			eleCampo = campoDAO.findById(cod_campo.byteValue());
		} catch (Exception e){
			e.printStackTrace();
		}
		return eleCampo;
	}
	
	
}
