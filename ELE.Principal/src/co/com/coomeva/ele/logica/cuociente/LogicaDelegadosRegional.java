package co.com.coomeva.ele.logica.cuociente;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import co.com.coomeva.ele.delegado.DelegadoFiltros;
import co.com.coomeva.ele.entidades.cuociente.EleCuocienteElectoral;
import co.com.coomeva.ele.entidades.cuociente.EleCuocienteRegional;
import co.com.coomeva.ele.entidades.cuociente.dao.EleCuocienteRegionalDAO;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.modelo.FiltrosConsultasDTO;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;
import co.com.coomeva.ele.utilidades.GeneradorConsecutivos;
import co.com.coomeva.util.acceso.UtilAcceso;

public class LogicaDelegadosRegional extends EleCuocienteRegionalDAO implements ILogicaDelegadosRegional {

	private EleCuocienteRegional eleCuocienteRegional;
	private static LogicaDelegadosRegional instance;
	private static HashMap<String, String> regionales;
	
	public LogicaDelegadosRegional (){
	}
	
	public static LogicaDelegadosRegional getInstance() {
		if (instance == null) {
			instance = new LogicaDelegadosRegional();
			cargarRegionales();
		}
		return instance;
	}
	
	public void registrarDelegadosRegional(
			HashMap<String, Integer> delegadosRegional, EleCuocienteElectoral cuocienteElectoral) {
		
		//for (int i = 0; i < delegadosRegional.size(); i++) {
		for (Entry<String, Integer> regional : delegadosRegional.entrySet()) {
			
			// TODO: Buscar regional x periodo, si existe hacer merge, si no, guardar normal
			eleCuocienteRegional = new EleCuocienteRegional();
			// eleCuocienteRegional.setIdRegistro(i+1); se debe crear automaticamente
			Integer idRegistro = new Integer(
					GeneradorConsecutivos
							.getInstance()
							.getConsecutivo(
									ConstantesNamedQueries.QUERY_SEQ_CUOCIENTE_REGIONAL,
									this.getSession()).toString());
			eleCuocienteRegional.setIdRegistro(idRegistro);
			eleCuocienteRegional.setCodRegional(regional.getKey());
			eleCuocienteRegional.setTotalDelegados(new Double(regional.getValue()));
			eleCuocienteRegional.setPeriodoElectoral(cuocienteElectoral.getPeriodoElectoral());
			eleCuocienteRegional.setRelacionDelegados((new Double(regional.getValue())*100)/cuocienteElectoral.getFinalTotalDelegadosElegir());
			//this.attachDirty(eleCuocienteRegional);
			this.save(eleCuocienteRegional);
			//System.out.println("Regional: "+regional.getKey()+" - "+eleCuocienteRegional.getRelacionDelegados());
		}
	}
	
	public List<EleCuocienteRegional> consultarDelegadosRegionales(
			String periodoElectoral, String ordenarPor) throws Exception {
		
		Criteria crit = getSession().createCriteria(
				EleCuocienteRegional.class);
		crit.add(Restrictions.eq("periodoElectoral", periodoElectoral));

		if (ordenarPor != null
				&& !ordenarPor.equals(UtilAcceso.getParametroFuenteS(
						"parametros", "selectValueDefault"))) {
			crit.addOrder(Order.desc(ordenarPor.toString()));
		}

		List<EleCuocienteRegional> cuocienteDelegadosRegionales = crit.list();
		cuocienteDelegadosRegionales = cargarDescripciones(cuocienteDelegadosRegionales);
		return cuocienteDelegadosRegionales;
	}

	private List<EleCuocienteRegional> cargarDescripciones(
			List<EleCuocienteRegional> cuocienteDelegadosRegionales) {
		if (cuocienteDelegadosRegionales != null) {
			
			
			for (int i = 0; i < cuocienteDelegadosRegionales.size(); i++) {
				cuocienteDelegadosRegionales.get(i).setDesRegional( "REGIONAL " + 
						regionales.get(cuocienteDelegadosRegionales.get(i)
								.getCodRegional()));
			}
		}
		return cuocienteDelegadosRegionales;
	}
	
	private static void cargarRegionales() {
		try {
			List<FiltrosConsultasDTO> listadoRegionales = DelegadoFiltros
					.getInstance().consultarRegionales();

			regionales = new HashMap<String, String>(0);
			for (int i = 0; i < listadoRegionales.size(); i++) {
				regionales.put(listadoRegionales.get(i).getCodigoFiltro()
						.toString(), listadoRegionales.get(i)
						.getDescripcionFiltro().toUpperCase());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
public void eliminarDelegadosRegional(String periodo){
		
		Session session=null;
		Query query=null;
		
    	try {
    		session= HibernateSessionFactoryElecciones2012.getSession();
    		query = session.createQuery("DELETE FROM EleCuocienteRegional r WHERE r.periodoElectoral = :periodo");
    		query.setParameter("periodo", periodo);
    		int result = query.executeUpdate();
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
