package co.com.coomeva.ele.logica;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Transaction;

import co.com.coomeva.ele.entidades.habilidad.EleSuspendido;
import co.com.coomeva.ele.entidades.habilidad.EleSuspendidoAUD;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.habilidad.dao.EleSuspendidoAUDDAO;
import co.com.coomeva.ele.entidades.habilidad.dao.EleSuspendidoDAO;
import co.com.coomeva.ele.entidades.planchas.dao.EleEstadoPlanchaDAO;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleEstadoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlancha;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.utilidades.GeneradorConsecutivos;

public class LogicaEstadoPlancha extends EleEstadoPlanchaDAO {
	private static LogicaEstadoPlancha instance;
	
	private LogicaEstadoPlancha() {
	
	}
	
	public static LogicaEstadoPlancha getInstance(){
		if (instance == null){
			instance = new LogicaEstadoPlancha();
		}
		return instance;
	}
	
	
	/**Crea registro en ELE_ESTADO_PLANCHA
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario Alejandro Acevedo</a> - GSISIN <br>
	 * @param usuario
	 * @param fechaRegistro
	 * @param estado
	 * @param consecPlancha
	 * @return 
	 * @throws EleccionesDelegadosException, Exception
	 * 
	 */
	public void crearEstadoPlancha(String usuario, Date fechaRegistro, String estado, Long consecPlancha){
		Transaction tr = null;
		try {
			tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
			
			Long codigoEstadoPlancha = GeneradorConsecutivos
			.getInstance().getConsecutivo("obtener.consecutivo.estado.plancha", HibernateSessionFactoryElecciones2012.getSession());
			
			EleEstadoPlancha estadoPlancha = new EleEstadoPlancha();
			estadoPlancha.setConsecutivoEstadoPlancha(codigoEstadoPlancha);
			estadoPlancha.setElePlancha(new ElePlancha(consecPlancha));
			estadoPlancha.setFechaRegistro(fechaRegistro);
			estadoPlancha.setEstadoPlancha(estado);
			estadoPlancha.setUsuarioEstado(usuario);
			
			save(estadoPlancha);
			tr.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			tr.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
	}	
}
