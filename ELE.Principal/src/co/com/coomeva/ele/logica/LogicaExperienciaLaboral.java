package co.com.coomeva.ele.logica;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import co.com.coomeva.ele.delegado.DelegadoCabezaPlancha;
import co.com.coomeva.ele.delegado.DelegadoExperienciaLaboral;
import co.com.coomeva.ele.entidades.planchas.EleCabPlancha;
import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral;
import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboralDAO;
import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboralId;
import co.com.coomeva.ele.util.UtilAccesoDb;
import co.com.coomeva.util.acceso.UtilAcceso;

public class LogicaExperienciaLaboral extends EleExperienciaLaboralDAO
{
	private static LogicaExperienciaLaboral instance;
	//Patròn Singular
	public static LogicaExperienciaLaboral getInstance() {
		if (instance == null) {
			instance = new LogicaExperienciaLaboral();
		}
		return instance;
	}
	/**
	 * Crear una Experiencia Laboral a un Asociados
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param documento
	 * @param cargo
	 * @param empresa
	 * @throws Exception
	 */
	public void crearExperienciaLaboral(String documento, String cargo,
			String empresa)throws Exception
	{
		if (documento == null || documento.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNoIdentificacion"));
		}

//		if (cargo == null || cargo.trim().equals("")) {
//			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noCargo"));
//		}

//		if (empresa == null || empresa.trim().equals("")) {
//			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noEmpresa"));
//		}
		
		EleCabPlancha cabezaPlancha = DelegadoCabezaPlancha.getInstance().consultarCabezaPlancha(documento);
		
		if (cabezaPlancha == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noExistePlancha"));
		}
		Long cons = 0L;
		String where =  "id.eleCabPlancha.nroIdentificacion = " + documento;
		
		cons = UtilAccesoDb.getNuevoIdSimpleL(this.getSession(), EleExperienciaLaboral.class, "id.consExperiencia", where);
		
		
		
		EleExperienciaLaboral eleExperienciaLaboral = new EleExperienciaLaboral();
		
		EleExperienciaLaboralId eleExperienciaLaboralId = new EleExperienciaLaboralId(cons,cabezaPlancha); 
		
		eleExperienciaLaboral.setCargo(cargo);
		eleExperienciaLaboral.setEmpresa(empresa);
		eleExperienciaLaboral.setId(eleExperienciaLaboralId);
		
		save(eleExperienciaLaboral);
	}
	/**
	 * Consulta todas las experiencias laborales asociadas a un numero de identificación
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param documento
	 * @return List<EleExperienciaLaboral>
	 * @throws Exception
	 */
	public List<EleExperienciaLaboral> consultaExperienciaLaborales(String documento) throws Exception 
	{
		if (documento == null || documento.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumIdentificacionCabeza"));
		}
		Criteria criteria = this.getSession().createCriteria(EleExperienciaLaboral.class);
		criteria.add(Restrictions.eq("id.eleCabPlancha.nroIdentificacion", documento));
		List<EleExperienciaLaboral> lista  = null;
		try {
			lista = criteria.list();
		} catch (Exception e) {
			throw e;
		}finally{
			this.getSession().flush();
		}
		
		
		return lista;
	}
	/**
	 * Elimina todas la experiencias laborales a un asociado
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param documento
	 * @throws Exception
	 */
	public void eliminarExperienciasLaborales(String documento) throws Exception
	{
		List<EleExperienciaLaboral> listEleExperienciaLaboral = consultaExperienciaLaborales(documento);
		
		for (EleExperienciaLaboral eleExperienciaLaboral : listEleExperienciaLaboral) 
		{
			delete(eleExperienciaLaboral);
		}
	}
	/**
	 * Crea apartir de una lista de experiencia laboral
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param listExperienciaLaboral
	 * @param documento
	 * @throws Exception
	 */
	
	public void crearExperienciasLaboralesList(List<EleExperienciaLaboral> listExperienciaLaboral, String documento) throws Exception
	{
		for (EleExperienciaLaboral eleExperienciaLaboral : listExperienciaLaboral) 
		{
			DelegadoExperienciaLaboral.getInstance().crearExperienciaLaboral(documento, eleExperienciaLaboral.getCargo(), eleExperienciaLaboral.getEmpresa());
		}
	}
}