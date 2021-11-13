package co.com.coomeva.ele.logica;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import co.com.coomeva.ele.entidades.planchas.EleLog;
import co.com.coomeva.ele.entidades.planchas.EleLogDAO;
import co.com.coomeva.ele.entidades.planchas.HibernateSessionFactoryPlanchas;
import co.com.coomeva.ele.util.UtilAccesoDb;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.ManipulacionFechas;

public class LogicaLogPlan extends EleLogDAO
{
	private static LogicaLogPlan instance;
	
	public LogicaLogPlan() 
	{
		
	}
	
	//Patròn Singular
	public static LogicaLogPlan getInstance() {
		if (instance == null) {
			instance = new LogicaLogPlan();
		}
		return instance;
	}
	/**
	 * Registra el Log de transacciones realizadas en administracion de planchas
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroCabezaPlancha
	 * @param tipoTransaccion
	 * @param nombreUsuario
	 * @param descripcion
	 * @throws Exception
	 */
	public void registrarLog(String nroCabezaPlancha,String tipoTransaccion, String nombreUsuario, String descripcion)throws Exception{
		
		if (nroCabezaPlancha ==null || nroCabezaPlancha.trim().equalsIgnoreCase("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumIdentificacion"));
		}
		if (tipoTransaccion ==null || tipoTransaccion.trim().equalsIgnoreCase("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noTipoTransaccion"));
		}
		if (nombreUsuario ==null || nombreUsuario.trim().equalsIgnoreCase("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noUsuario"));
		}
		
		if (descripcion ==null || descripcion.trim().equalsIgnoreCase("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noUsuario"));
		}
		
		Long consLog = UtilAccesoDb.getNuevoIdSimpleL(HibernateSessionFactoryPlanchas.getSession(), EleLog.class, "consecutivo", null);
		
		EleLog eleLog = new EleLog(consLog,nroCabezaPlancha, tipoTransaccion, ManipulacionFechas.getFechaActual(), nombreUsuario, descripcion);
		
		save(eleLog);
	}

	public List<EleLog> consultarLogs(String identificacion,
			java.util.Date fechaInicial, java.util.Date fechaFinal, String tipoTransaccion, String usuario) {
		List<EleLog> returnList = new ArrayList<EleLog>();
		boolean between = false;

		Criteria criteria = getSession().createCriteria(EleLog.class);
		if (identificacion != null&&!identificacion.trim().equalsIgnoreCase("")) {
			criteria.add(Restrictions.eq("nroCaboPlancha", identificacion));
		}
		
		
		if (fechaInicial != null&& fechaFinal != null) {
			if (fechaInicial.equals(fechaFinal)) {
				criteria.add(Restrictions.eq("fecha",  fechaInicial));
			}else{
				criteria.add(Restrictions.between("fecha", fechaInicial,fechaFinal ));
			}
			between = true;
		}
		
		if (fechaInicial != null&& between == false) {
			criteria.add(Restrictions.ge("fecha", fechaInicial));
		}
		
		if (fechaFinal != null&& between == false) {
			criteria.add(Restrictions.le("fecha",  fechaFinal));
		}
		
		if (tipoTransaccion != null&&!tipoTransaccion.trim().equalsIgnoreCase("")) {
			criteria.add(Restrictions.eq("tipotrans", tipoTransaccion));
		}
		if (usuario != null&&!usuario.trim().equalsIgnoreCase("")) {
			criteria.add(Restrictions.eq("usuario", usuario));
		}
		
		criteria.addOrder(Order.asc("nroCaboPlancha"));
		criteria.addOrder(Order.asc("fecha"));
		returnList = criteria.list();
		
		return returnList;
	}
}
