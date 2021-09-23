package co.com.coomeva.ele.logica;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.salud.HibernateSessionFactorySalud;

public class LogicaSalud {

	private static LogicaSalud instance;

	//Constructor de la clase
	private LogicaSalud() {
	}

	//Patròn Singular
	public static LogicaSalud getInstance() {
		if (instance == null) {
			instance = new LogicaSalud();
		}
		return instance;
	}
	/**
	 * Valida si existe el asesor en la base de datos de Salud
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroIdentificacion
	 * @return boolean
	 */
	public boolean existAsesor(String nroIdentificacion){
		Long returnValue = null;
		Session session=null;
		Query query=null;
		session= HibernateSessionFactorySalud.getSession();
		query=session.getNamedQuery("asesor.existMP");

		query.setString(0, nroIdentificacion);
		
//		List<LogicaSalud> objQuery = query.list();
		List list = query.list();
//		Object objQuery = query.uniqueResult();
		
//		if (objQuery != null) {
			
//			for (LogicaSalud logicaSalud : objQuery) {
//				System.out.println("");
//			}
			
//			String sbQuery = query.uniqueResult().toString();
//			returnValue = Long.parseLong(sbQuery);
//		}
		
		
		if (list.size() != 0) {
			
			for (int i = 0; i < list.size(); i++) {
				String sbQuery = ""+list.get(i);
				returnValue = Long.parseLong(sbQuery);
				break;
			}
			
		}
		
		boolean flag = false;
		if (returnValue != null ) {
			flag = true;
		}
		return flag;
	}
}
