package co.com.coomeva.ele.logica;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import co.com.coomeva.ele.entidades.admhabilidad.HibernateSessionFactoryHab;
import co.com.coomeva.ele.entidades.admhabilidad.LogTransacciones;
import co.com.coomeva.ele.entidades.admhabilidad.LogTransaccionesDAO;
import co.com.coomeva.ele.util.UtilAccesoDb;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.ManipulacionFechas;

public class LogicaLogHab extends LogTransaccionesDAO{
	private static LogicaLogHab instance;

	//Constructor de la clase
	private LogicaLogHab() {
	}

	//Patròn Singular
	public static LogicaLogHab getInstance() {
		if (instance == null) {
			instance = new LogicaLogHab();
		}
		return instance;
	}
	/**
	 * Registra el Log de transacciones realizadas en la administracion de habilidades 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroIdentificacion
	 * @param antHabil
	 * @param habil
	 * @param concpTransaccion
	 * @param usuario
	 * @throws Exception
	 */
	public void registrarLog(String nroIdentificacion,String antHabil,String habil, String concpTransaccion, String usuario)throws Exception{

		if (nroIdentificacion ==null ||nroIdentificacion.trim().equalsIgnoreCase("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumIdentificacion"));
		}
		if (habil ==null ||habil.trim().equalsIgnoreCase("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noHabil"));
		}
		if (concpTransaccion ==null ||concpTransaccion.trim().equalsIgnoreCase("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noConcpTransaccion"));
		}

		if (usuario ==null ||usuario.trim().equalsIgnoreCase("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noUsuario"));
		}

		Long consLog = UtilAccesoDb.getNuevoIdSimpleL(HibernateSessionFactoryHab.getSession(), LogTransacciones.class, "consLog", null);


		LogTransacciones logTransacciones2 = new LogTransacciones(consLog,nroIdentificacion,antHabil,habil,concpTransaccion,ManipulacionFechas.getFechaActual(),usuario.toUpperCase());

		save(logTransacciones2);


	}
	/**
	 * Consulta las transacciones realizadas en la adminitracion de Habilidades
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param identificacion
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param estado
	 * @param usuario
	 * @return List<LogTransacciones>
	 */
	public List<LogTransacciones> consultarLogs(String identificacion,
			Date fechaInicial, Date fechaFinal, String estado, String usuario) {
		List<LogTransacciones> returnList = new ArrayList<LogTransacciones>();
		boolean between = false;

		Criteria criteria = getSession().createCriteria(LogTransacciones.class);
		if (identificacion != null&&!identificacion.trim().equalsIgnoreCase("")) {
			criteria.add(Restrictions.eq("nroIdentificacion", identificacion));
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

		if (estado != null&&!estado.trim().equalsIgnoreCase("")) {
			criteria.add(Restrictions.eq("habil", estado));
		}
		if (usuario != null&&!usuario.trim().equalsIgnoreCase("")) {
			criteria.add(Restrictions.eq("usuario", usuario));
		}
		criteria.addOrder(Order.asc("nroIdentificacion"));
		criteria.addOrder(Order.asc("fecha"));
		returnList = criteria.list();

		return returnList;
	}

}
