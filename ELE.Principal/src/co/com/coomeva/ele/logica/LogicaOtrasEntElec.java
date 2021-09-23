package co.com.coomeva.ele.logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import co.com.coomeva.ele.entidades.habilidad.EleSuspendido;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.habilidad.dao.EleSuspendidoDAO;
import co.com.coomeva.ele.entidades.planchas.dao.EleAsocOtrasEntElectDAO;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleAsocOtrasEntElect;
import co.com.coomeva.ele.modelo.EleAsocOtrasEntElectDTO;
import co.com.coomeva.ele.modelo.SuspendidoDTO;

public class LogicaOtrasEntElec extends EleAsocOtrasEntElectDAO {
	private static LogicaOtrasEntElec instance;
	
	private LogicaOtrasEntElec() {
	
	}
	
	public static LogicaOtrasEntElec getInstance(){
		if (instance == null){
			instance = new LogicaOtrasEntElec();
		}
		return instance;
	}
	
	/**Genera el registo en EleAsocOtrasEntElect
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario Alejandro Acevedo</a> - GSISIN <br>
	 * @param codigoAsociado
	 * @param tipoEnt
	 * @param fechaRegistro
	 * @param usuario
	 *
	 */
	public void crearOtrasEntElec(Long codigoAsociado, String tipoEnt, Date fechaRegistro, String usuario){
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			EleAsocOtrasEntElect otras = new EleAsocOtrasEntElect();
			otras.setCodigoAsociado(codigoAsociado);
			otras.setTipoEnt(tipoEnt);
			otras.setFechaRegistro(fechaRegistro);
			otras.setUsuario(usuario);
			
			save(otras);
			tr.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			tr.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
	}
	
	/**Recupera todos los registros de EleAsocOtrasEntElect
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario Alejandro Acevedo</a> - GSISIN <br>
	 * @return List<EleAsocOtrasEntElect>
	 *
	 */
	public List<EleAsocOtrasEntElect> obtenerOtrasEntElect(){
		return findAll();
	}
	
	/**Elimina todos los registros que existan en EleAsocOtrasEntElect
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario Alejandro Acevedo</a> - GSISIN <br>
	 * 
	 */
	public void eliminarOtrasEntElect(){
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		 try {
			limpiarTablaOtrasEntElect();
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
	}
	
	/**Obtiene los registros paginados que existan en EleAsocOtrasEntElect
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario Alejandro Acevedo</a> - GSISIN <br>
	 * @return List<EleAsocOtrasEntElectDTO>
	 */
	public List<EleAsocOtrasEntElectDTO> consultarOtrasEntElecPag(String sortColumnName, boolean sortAscending, 
			int startRow, int maxResults) throws Exception{
    	
    	List<EleAsocOtrasEntElectDTO> list = new ArrayList<EleAsocOtrasEntElectDTO>();
    	List<Object[]> objectList = null;
    	
    	
    	Session session = HibernateSessionFactoryElecciones2012.getSession();
    	
    	try {
    		
    		StringBuffer sql = new StringBuffer("select otras.NUMERO_DOCUMENTO DOCUMENTO, " +
    				"cli.NOMCL1 NOMBRES, param.NOMBRE ENTIDAD from mulclidat.CLIMAE cli, " +
    				"elecdb.ELE_ASOC_OTRAS_ENT_ELECT otras, elecdb.ELE_PARAMETRO param, elecdb.ELE_PARAMETRO_TIPO paramT" +
    				" where otras.NUMERO_DOCUMENTO = cli.NUMINT and " +
    				"otras.TIPO_ENT = param.VALOR and paramt.TIP_COD = 2 and param.TIP_COD = paramt.TIP_COD");
			
			
			SQLQuery query = session.createSQLQuery(sql.toString());
			
			query.addScalar("DOCUMENTO", Hibernate.STRING);
			query.addScalar("NOMBRES", Hibernate.STRING);
			query.addScalar("ENTIDAD", Hibernate.STRING);
			
			EleAsocOtrasEntElectDTO otras = null;
			objectList = query.setFirstResult(startRow).setMaxResults(maxResults).list();
			if (objectList != null && objectList.size() > 0) {
				for (Object[] objects : objectList) {
					
					List<String> nombresApellidosProcesados = new ArrayList<String>();
					nombresApellidosProcesados = LogicaPlanchas.getInstance().procesaNombresApellidos((String)objects[1]);
					
					otras = new EleAsocOtrasEntElectDTO();
					otras.setDocumento((String)objects[0]);
					otras.setNombres(nombresApellidosProcesados.get(2)+" "+nombresApellidosProcesados.get(3)+" "+nombresApellidosProcesados.get(0)+" "+nombresApellidosProcesados.get(1));
					otras.setEntidad((String)objects[2]);
					
					list.add(otras);					
				}
			}						
			
		} catch (Exception e) {
			throw e;
		}finally{
			session.close();
		}
    	
    	return list;
    	
    }
	
	//obtiene el NUMINT del asociado dado el documento
	//Autor: Mario Alejandro Acevedo
	public Long obtieneNumintAsociado(Long documento){
		Long resultado = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			Query query = session.getNamedQuery("consultaNumintAsociado");
			query.setLong("documento", documento);
			
			resultado = (Long)query.uniqueResult();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	return resultado;	
	}
}
