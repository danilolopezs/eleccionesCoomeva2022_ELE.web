/**
 * 
 */
package co.com.coomeva.util.acceso;

import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

import co.com.coomeva.util.date.ManipulacionFechas;
import co.com.coomeva.util.resources.LoaderResourceElements;

/**
 * @author Federico Guillermo Rocha Martínez
 *
 */
public class UtilAcceso {
	private static LoaderResourceElements loaderReourceElements = LoaderResourceElements.getInstance();
	/**Función que obtiene un objeto a partir del llamado dinámico de una clase
	 * @author Federico Guillermo Rocha Martínez.
	 * @fecha 23/12/2008
	 * @param o Object
	 * @param miembre String
	 * @param listadoC Class[]
	 * @param lisatdoO Object[]
	 */
	@SuppressWarnings("unchecked")
	public static Object invokeMethodMember(Object o,String miembro,boolean isGet,Class[] listadoC,Object[] listadoO) {
		// TODO Apéndice de método generado automáticamente
		try {
			String metodo = miembro.substring(0);
			if(isGet)
				metodo = "get"+miembro.substring(0,1).toUpperCase()+miembro.substring(1);	
			Method campo = o.getClass().getMethod(metodo,listadoC);			
			Object resultado = campo.invoke(o, listadoO);			
			return resultado;
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			return null;
		}
	}	
	/**Función que obtiene un objeto a partir del llamado dinámico de una clase
	 * @author Federico Guillermo Rocha Martínez.
	 * @fecha 23/12/2008
	 * @param o Object
	 * @param miembre String
	 */
	public static Object invokeMethodMember(Object o,String miembro,boolean isGet) {
		// TODO Apéndice de método generado automáticamente
		return invokeMethodMember(o, miembro,isGet, new Class[0], new Object[0]);
	}		
	/**Función que obtiene un string según el llamado a un miembro
	 * @author Federico Guillermo Rocha Martínez.
	 * @fecha 23/12/2008
	 * @param o Object
	 * @param miembre String
	 */

	public static String invokeMethodMemberS(Object o,String miembro,boolean isGet,boolean validarFecha) {
		// TODO Apéndice de método generado automáticamente
		Object resultado = invokeMethodMember(o, miembro,isGet);
		String salidaR = "";
		if(resultado != null){
			salidaR = resultado.toString();			
			 if (validarFecha && resultado instanceof Date) {
				 Date salidaD = (Date) resultado;
				 salidaR = ManipulacionFechas.dateToStringConFormato(salidaD, true, false, false,false);
			}
		}
		return salidaR;
	}
	/**Función que obtiene un parametro a partir de los datos en el archivo de parametros de text dicho en origen.
	 * @author Federico Guillermo Rocha Martínez.
	 * @fecha 21/09/2008 
	 * @param origen
	 * @param nombre
	 * @return Integer
	 */	
	public static Integer getParametroFuenteI(String origen,String nombre) {		
		try {
			return new Integer(loaderReourceElements.getKeyResourceValue(origen, nombre));
		} catch (NumberFormatException e) {
			printStackTrace(e, true);
		} catch (Exception e) {
			printStackTrace(e, true);
		}
		return new Integer("-1");
	}
	/**Función que obtiene un parametro a partir de los datos en el archivo de parametros de text dicho en origen.
	 * @author Federico Guillermo Rocha Martínez.
	 * @fecha 21/09/2008 
	 * @param origen
	 * @param nombre
	 * @return Long
	 */	
	public static Long getParametroFuenteL(String origen,String nombre) {		
		try {
			return new Long(loaderReourceElements.getKeyResourceValue(origen, nombre));
		} catch (NumberFormatException e) {
			printStackTrace(e, true);
		} catch (Exception e) {
			printStackTrace(e, true);
		}
		return new Long("-1");
	}	
	
	/**Función que obtiene un parametro a partir de los datos en el archivo de parametros de text dicho en origen.
	 * @author Federico Guillermo Rocha Martínez.
	 * @fecha 06/01/2010 
	 * @param origen
	 * @param nombre
	 * @return Long
	 */	
	public static Float getParametroFuenteF(String origen,String nombre) {		
		try {
			return new Float(loaderReourceElements.getKeyResourceValue(origen, nombre));
		} catch (NumberFormatException e) {
			printStackTrace(e, true);
		} catch (Exception e) {
			printStackTrace(e, true);
		}
		return new Float("-1f");
	}	
	
	/**Función que obtiene un parametro a partir de los datos en el archivo de parametros de text dicho en origen.
	 * @author Federico Guillermo Rocha Martínez.
	 * @fecha 06/01/2010 
	 * @param origen
	 * @param nombre
	 * @return Long
	 */	
	public static Double getParametroFuenteD(String origen,String nombre) {		
		try {
			return new Double(loaderReourceElements.getKeyResourceValue(origen, nombre));
		} catch (NumberFormatException e) {
			printStackTrace(e, true);
		} catch (Exception e) {
			printStackTrace(e, true);
		}
		return new Double("-1f");
	}	
	
	/**Función que obtiene un parametro a partir de los datos en el archivo de parametros de text dicho en origen.
	 * @author Federico Guillermo Rocha Martínez.
	 * @fecha 21/09/2008 
	 * @param origen
	 * @param nombre
	 * @return String
	 */	
	public static String getParametroFuenteS(String origen,String nombre) {		
		try {
			return loaderReourceElements.getKeyResourceValue(origen, nombre);
		} catch (Exception e) {
			printStackTrace(e, true);
		}
		return new String();
	}	

	/**Función que obtiene un objeto a partir del llamado dinámico de una clase directamente
	 * @author Federico Guillermo Rocha Martínez.
	 * @fecha 01/10/2008
	 * @param empresa
	 * @param param
	 * @param funcion
	 * @param tipoParams
	 * @param params
	 * @return Mehtod
	 * @throws ExcepcionMmm
	 */
	@SuppressWarnings("unchecked")
	public static Object invokeGetInstance(String clase,String funcion) throws Exception {
		// TODO Apéndice de método generado automáticamente
		Class[] listado = null;
		Object[] listadoO = null;			
		Class t = null;
		Method m = null;		
		try {
			listado = new Class[0];
			listadoO = new Object[0];			
			//System.out.println("Clase utilizada: "+clase);
			t = Class.forName(clase);
			m = t.getMethod(funcion, listado);
			return m.invoke(null, listadoO);
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			//printStackTrace(e, true);
			throw new Exception("Error al mapear la clase a utilizar sin empresa  "+funcion+":"+clase+":"+e.getMessage());
		} finally {
			listado = null;
			listadoO = null;			
			t = null;
			m = null;			
		}
	}
	
	/**Función q imprime la fecha de generación del error
	 * @author Federico Guillermo Rocha Martínez
	 * @fecha 27/10/2009
	 * @param e
	 * @param withDate
	 */
	public static void printStackTrace(Exception e,boolean withDate){
		
		if(withDate){
			System.err.println("Fecha Generación:"+ManipulacionFechas.dateToString(ManipulacionFechas.getFechaActual(),false));
		}
		e.printStackTrace();		
	}
	
	/**Función q imprime los elementos dichos en un map
	 * @author Federico Guillermo Rocha Martínez
	 * @fecha 9/11/2009
	 * @param obj
	 * @param campos
	 */
	public static void print(Object obj,HashMap<String,String> campos,boolean sinPintarFinal){
		System.err.println("Fecha Generación Impresión:"+ManipulacionFechas.dateToString(ManipulacionFechas.getFechaActual(),false));
		System.out.println("---Elemento---"+obj.getClass().getName()+"----Campos: "+campos.size());
		
		Set<String> camposLlaves = campos.keySet();
		
		for( String llave : camposLlaves){		
			try {
				Object objCampo = UtilAcceso.invokeMethodMember(obj, llave, true);				
				System.out.println("--"+campos.get(llave)+" => "+objCampo.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				printStackTrace(e, true);
			}
		}		
		if(!sinPintarFinal)
			System.out.println("----------------------------------------------");
		
	}	
	
	/**
	 * @param args
	 */
	
    public static String readLine() throws Exception {
        int c;
        StringBuffer sb = new StringBuffer();
        while(( c = System.in.read() ) != '\n' ) {
            if( c == -1 ) return "";
            sb.append( (char)c );
        }
        return sb.toString().trim();
    }
    
    public static boolean existsUrl(String URLName,Integer timeout){
        try {
          //HttpURLConnection.setFollowRedirects(false);
          // note : you may also need
          //        HttpURLConnection.setInstanceFollowRedirects(false)
          URLConnection con =
             new URL(URLName).openConnection();
          if(timeout != null)
        	  con.setConnectTimeout(timeout.intValue());
          con.connect();
          //con.setReadTimeout(10000);
          //con.connect();          
         // Object object = con.getContent();
          //return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
          return true;
        }
        catch (Exception e) {
          UtilAcceso.printStackTrace(e, true);
           return false;
        }
      }    
    public static boolean existsUrlProxy(String URLName,String strInicio){
        try {
          URL u = new URL(URLName);
          HttpURLConnection con = (HttpURLConnection) u.openConnection();
          //
          // it's not the greatest idea to use a sun.misc.* class
          // Sun strongly advises not to use them since they can 
          // change or go away in a future release so beware.
          //
          //sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
          
          String encodedUserPwd =
        		  DatatypeConverter.printBase64Binary("domain\\username:password".getBytes());
          con.setRequestProperty
             ("Proxy-Authorization", "Basic " + encodedUserPwd);
          con.setRequestMethod("HEAD");
          System.out.println
             (con.getResponseCode() + " : " + con.getResponseMessage());
          return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        }
        catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }    
}
