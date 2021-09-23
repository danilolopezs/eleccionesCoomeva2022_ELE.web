/**
 * 
 */
package co.com.coomeva.util.acceso;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

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
	 * 
	 * @Modify:
	 * Adición de manejo de secuencias.
	 * @fecha 15/10/2010
	 * @author Federico G. Rocha Martínez
	 */
	public static Integer getNuevoIdSimpleI(Session sesion,Class tabla,String idQuery,String where){
		return getNuevoIdSimpleI(sesion, tabla, idQuery, where, false, "");
	}
	public static Integer getNuevoIdSimpleI(Session sesion,Class tabla,String idQuery,String where,boolean esSequencia,String funcion){
		StringBuffer str = null;
		Query query = null;
		List lista = null;
		Integer maxVolume = new Integer(1);
		try {
			str = new StringBuffer();		
			str.append("    SELECT ");
			if(esSequencia){
				str.append("           "+idQuery+"."+funcion);
			}else{
				str.append("           MAX("+idQuery+")+1 ");
			}
			str.append("      FROM "+tabla.getSimpleName());
			
			if(where != null){
				str.append(" WHERE "+where);
			}
			
			query = sesion.createQuery(str.toString());
			//query.setReadOnly(true);
			lista = query.setReadOnly(true).list();			
			if(lista.size() > 0 && lista.get(0) != null)
				maxVolume = (Integer)lista.get(0);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			UtilAcceso.printStackTrace(e,true);
		} finally {
			str = null;
			query = null;
			lista = null;
			//sesion.clear();
			//sesion.close();
		}			
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
	 * 
	 * @Modify:
	 * Adición de manejo de secuencias.
	 * @fecha 15/10/2010
	 * @author Federico G. Rocha Martínez
	 */
	public static Long getNuevoIdSimpleL(Session sesion,Class tabla,String idQuery,String where){
		return getNuevoIdSimpleL(sesion, tabla, idQuery, where, false, "");
	}
	public static Long getNuevoIdSimpleL(Session sesion,Class tabla,String idQuery,String where,boolean esSequencia,String funcion){
		StringBuffer str = null;
		Query query = null;
		List lista = null;
		Long maxVolume = new Long(1);
		try {
			str = new StringBuffer();		
			str.append("    SELECT ");
			if(esSequencia){
				str.append("           "+idQuery+"."+funcion);
			}else{
				str.append("           MAX("+idQuery+")+1 ");
			}
			str.append("      FROM "+tabla.getSimpleName());
			
			if(where != null){
				str.append(" WHERE "+where);
			}
			
			query = sesion.createQuery(str.toString());
			//query.setReadOnly(true);
			lista = query.setReadOnly(true).list();		
			if(lista.size() > 0 && lista.get(0) != null)
				maxVolume = (Long)lista.get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			UtilAcceso.printStackTrace(e,true);
		} finally {
			str = null;
			query = null;
			lista = null;
			//sesion.clear();
			//sesion.close();
		}
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
	 * 
	 * @Modify:
	 * Adición de manejo de secuencias.
	 * @fecha 15/10/2010
	 * @author Federico G. Rocha Martínez
	 */
	public static Integer getNuevoIdSimpleI(Session sesion,String tabla,String idQuery,String where){
		return getNuevoIdSimpleI(sesion, tabla, idQuery, where, false, "");
	}
	public static Integer getNuevoIdSimpleI(Session sesion,String tabla,String idQuery,String where,boolean esSequencia,String funcion){
		StringBuffer str = null;
		Query query = null;
		List lista = null;
		Integer maxVolume = new Integer(1);
		try {
			str = new StringBuffer();	
			str.append("    SELECT ");
			if(esSequencia){
				str.append("           "+idQuery+"."+funcion);
			}else{
				str.append("           MAX("+idQuery+")+1 ");
			}
			str.append("      FROM "+tabla+" ");
			
			if(where != null){
				str.append(" WHERE "+where);
			}
			
			query = sesion.createSQLQuery(str.toString());
			//query.setReadOnly(true);
			lista = query.setReadOnly(true).list();
			if(lista.size() > 0 && lista.get(0) != null)
				maxVolume = (Integer)lista.get(0);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			UtilAcceso.printStackTrace(e,true);
		} finally {
			str = null;
			query = null;
			lista = null;
			//sesion.clear();
			//sesion.close();
		}			
		return maxVolume;
	}
	/**
	 * Función que devuelve el siguiente código o secuencia del elemento dado.
	 * @author Federico G. Rocha Martínez
	 * @fecha 21/09/2008	 * 
	 * @param sesion
	 * @param tabla
	 * @param idQuery
	 * @return
	 * 
	 * @Modify:
	 * Adición de manejo de secuencias.
	 * @fecha 15/10/2010
	 * @author Federico G. Rocha Martínez 
	 */
	public static Long getNuevoIdSimpleL(Session sesion,String tabla,String idQuery,String where){
		return getNuevoIdSimpleL(sesion, tabla, idQuery, where, false, "");
	}
	public static Long getNuevoIdSimpleL(Session sesion,String tabla,String idQuery,String where,boolean esSequencia,String funcion){
		StringBuffer str = null;
		Query query = null;
		List lista = null;
		Long maxVolume = new Long(1);
		try {
			str = new StringBuffer();			
			str.append("    SELECT ");
			if(esSequencia){
				str.append("           "+idQuery+"."+funcion);
			}else{
				str.append("           MAX("+idQuery+")+1 ");
			}
			str.append("      FROM "+tabla+" ");
			
			if(where != null){
				str.append(" WHERE "+where);
			}
			
			query = sesion.createSQLQuery(str.toString());
			//query.setReadOnly(true);
			lista = query.setReadOnly(true).list();			
			if(lista.size() > 0 && lista.get(0) != null)
				maxVolume = (Long)lista.get(0);		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			UtilAcceso.printStackTrace(e,true);
		} finally {
			str = null;
			query = null;
			lista = null;
			//sesion.clear();
			//sesion.close();
		}		
		return maxVolume;
	}	
	
	@SuppressWarnings("unchecked")
	public static List queryList(Session session, Query query, SQLQuery sqlquery){
		try{
			if(query != null){
				return query.list();				
			}
			
			if(sqlquery != null){
				return sqlquery.list();
			}
			
		} catch (Exception e){
			UtilAcceso.printStackTrace(e, true);
		}
		return new ArrayList();
	}
	
	public static boolean validarSession(Session session,String nombreBd,boolean mostrarError){
		Query query=null;
		List qList = null;
		try {
	  	    query = session.getNamedQuery("probar.conexion");
	  	    //query.setReadOnly(true);
			qList = query.setReadOnly(true).list();			
			return true;

		} catch (Exception e) {
			if(mostrarError){
				System.out.println("Fallo la prueba de la bd: "+nombreBd+":"+e.getClass().getName());
				UtilAcceso.printStackTrace(e, true);
			}
		}
		finally {
			query=null;
			qList = null;
			if(session != null){
				//session.clear();
				//session.close();
				session = null;
			}
		}		
		return false;
	}
	
	public static void main(String[] args){
		
	}
}
