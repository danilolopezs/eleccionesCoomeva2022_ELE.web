/**
 * 
 */
package co.com.coomeva.ele.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.util.acceso.UtilAcceso;

/**
 * @author Federico Guillermo Rocha Martínez
 *
 */
public class UtilAccesoDb extends UtilAcceso {

	
	/**
	 * Función que devuelve el siguiente código o secuencia del elemento dado.
	 * @author Federico G. Rocha Martínez
	 * @fecha 21/09/2008
	 * @param sesion
	 * @param tabla
	 * @param idQuery
	 * @return
	 */
	public static Integer getNuevoIdSimpleI(Session sesion,Class tabla,String idQuery,String where)
	{
		StringBuffer str = new StringBuffer();		
		str.append("    SELECT ");
		str.append("           MAX("+idQuery+")+1 ");
		str.append("      FROM "+tabla.getSimpleName());
		
		if(where != null){
			str.append(" WHERE "+where);
		}
		
		Query query = sesion.createQuery(str.toString());
		List lista = query.list();
		Integer maxVolume = 1;
		if(lista.size() > 0 && lista.get(0) != null)
			maxVolume = (Integer)lista.get(0);		
		return maxVolume;
	}
	/**
	 * Función que devuelve el siguiente código o secuencia del elemento dado.
	 * @author Federico G. Rocha Martínez
	 * @fecha 21/09/2008
	 * @param sesion
	 * @param tabla
	 * @param idQuery
	 * @return
	 */
	public static Long getNuevoIdSimpleL(Session sesion,Class tabla,String idQuery,String where){
		StringBuffer str = new StringBuffer();		
		str.append("    SELECT ");
		str.append("           MAX("+idQuery+")+1 ");
		str.append("      FROM "+tabla.getSimpleName());
		
		if(where != null){
			str.append(" WHERE "+where);
		}
		
		Query query = sesion.createQuery(str.toString());
		List lista = query.list();
		Long maxVolume = new Long(1);
		if(lista.size() > 0 && lista.get(0) != null)
			maxVolume = (Long)lista.get(0);		
		return maxVolume;
	}	

	/**
	 * Función que devuelve el siguiente código o secuencia del elemento dado.
	 * @author Federico G. Rocha Martínez
	 * @fecha 21/09/2008
	 * @param sesion
	 * @param tabla
	 * @param idQuery
	 * @return
	 */
	public static Integer getNuevoIdSimpleI(Session sesion,String tabla,String idQuery,String where)
	{
		StringBuffer str = new StringBuffer();		
		str.append("    SELECT ");
		str.append("           MAX("+idQuery+")+1 ");
		str.append("      FROM "+tabla+" ");
		
		if(where != null){
			str.append(" WHERE "+where);
		}
		
		Query query = sesion.createSQLQuery(str.toString());
		List lista = query.list();
		Integer maxVolume = 1;
		if(lista.size() > 0 && lista.get(0) != null)
			maxVolume = (Integer)lista.get(0);		
		return maxVolume;
	}
	/**
	 * Función que devuelve el siguiente código o secuencia del elemento dado.
	 * @author Federico G. Rocha Martínez
	 * @fecha 21/09/2008
	 * @param sesion
	 * @param tabla
	 * @param idQuery
	 * @return
	 */
	public static Long getNuevoIdSimpleL(Session sesion,String tabla,String idQuery,String where){
		StringBuffer str = new StringBuffer();		
		str.append("    SELECT ");
		str.append("           MAX("+idQuery+")+1 ");
		str.append("      FROM "+tabla+" ");
		
		if(where != null){
			str.append(" WHERE "+where);
		}
		
		Query query = sesion.createSQLQuery(str.toString());
		List lista = query.list();
		Long maxVolume = new Long(1);
		if(lista.size() > 0 && lista.get(0) != null)
			maxVolume = (Long)lista.get(0);		
		return maxVolume;
	}	
}
