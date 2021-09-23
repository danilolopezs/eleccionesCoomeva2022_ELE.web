package co.com.coomeva.ele.utilidades;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Descripción : Proporciona la lógica para la generación de los consecutivos
 * asociados a las inhabilidades
 * 
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
 *         Premize SAS <br>
 * @project ELE.accesoDatos
 * @class GeneradorConsecutivos
 * @date 1/11/2012
 */
public class GeneradorConsecutivos {
	private static GeneradorConsecutivos generadorConsecutivos = null;

	private GeneradorConsecutivos() {
	}

	public static GeneradorConsecutivos getInstance() {
		if (generadorConsecutivos == null) {
			generadorConsecutivos = new GeneradorConsecutivos();
		}
		return generadorConsecutivos;
	}

	/**
	 * procedimiento genérico que permite obtener el consecutivo de una tabla
	 * 
	 * @return Long: Consecutivo
	 */
	public synchronized Long getConsecutivo(String sqlName, Session session) {
		Long consecutivo = null;
		List qlist = null;
		try {
			Query query = session.getNamedQuery(sqlName);
			qlist = query.list();
			for (Iterator iter = qlist.iterator(); iter.hasNext();) {
				consecutivo = (Long) iter.next();
			}
		} catch (HibernateException e) {
			consecutivo = new Long(0);
		}
		return consecutivo;
	}

}
