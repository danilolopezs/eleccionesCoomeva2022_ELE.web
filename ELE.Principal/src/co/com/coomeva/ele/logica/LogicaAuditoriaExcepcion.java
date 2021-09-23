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
import co.com.coomeva.ele.entidades.habilidad.EleSuspendidoAUD;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.habilidad.dao.EleSuspendidoDAO;
import co.com.coomeva.ele.entidades.planchas.dao.EleAsocOtrasEntElectDAO;
import co.com.coomeva.ele.entidades.planchas.dao.EleAuditoriaExcepcionDAO;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleAsocOtrasEntElect;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleAuditoriaExcepcion;
import co.com.coomeva.ele.modelo.EleAsocOtrasEntElectDTO;
import co.com.coomeva.ele.modelo.SuspendidoDTO;
import co.com.coomeva.ele.utilidades.GeneradorConsecutivos;

public class LogicaAuditoriaExcepcion extends EleAuditoriaExcepcionDAO {
	private static LogicaAuditoriaExcepcion instance;
	
	private LogicaAuditoriaExcepcion() {
	
	}
	
	public static LogicaAuditoriaExcepcion getInstance(){
		if (instance == null){
			instance = new LogicaAuditoriaExcepcion();
		}
		return instance;
	}
	
	/**Genera el registro en ELE_AUDITORIA_EXCEPCION
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario Alejandro Acevedo</a> - GSISIN <br>
	 * @param numeroDocumento
	 * @param usuario
	 * @param fechaSuspencion
	 * @param motivo
	 * @param tipoExcepcion
	 * @param fechaRegistro
	 */
	public void crearAuditoriaExcepcion(Long numeroDocumento, String usuario, Date fechaSuspension, String motivo, String tipoExcepcion, Date fechaRegistro){
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			Long codigoAuditoria = GeneradorConsecutivos
			.getInstance().getConsecutivo("consultarSecuenciaAuditoriaExcepcion", HibernateSessionFactoryElecciones2012.getSession());
			
			EleAuditoriaExcepcion auditoriaExcepcion = new EleAuditoriaExcepcion();
			
			auditoriaExcepcion.setConsecutivo(codigoAuditoria);
			auditoriaExcepcion.setFechaRegistro(fechaRegistro);
			auditoriaExcepcion.setFechaSuspension(fechaSuspension);
			auditoriaExcepcion.setMotivoSuspension(motivo);
			auditoriaExcepcion.setNumeroDocumento(numeroDocumento);
			auditoriaExcepcion.setTipoExcepcion(tipoExcepcion);
			auditoriaExcepcion.setUsuario(usuario);
			save(auditoriaExcepcion);
			
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
