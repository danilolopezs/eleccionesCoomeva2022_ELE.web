package co.com.coomeva.ele.logica;

import co.com.coomeva.ele.entidades.planchas.ElePParametrosDAO;
import co.com.coomeva.ele.modelo.Parametro;
import co.com.coomeva.util.acceso.UtilAcceso;



public class LogicaElePParametro extends ElePParametrosDAO{

	private static LogicaElePParametro instance = null;
	/**
	 * Patrón Singular
	 * @return instance
	 */
	public static LogicaElePParametro getInstance() {
		
		if (instance == null) {
			instance = new LogicaElePParametro();
		}
		
		return instance;
	}
	/**
	 * Obtiene de la tabla ElePParametros el valor de este.
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param origen
	 * @param nombre
	 * @return Parametro
	 */
	public Parametro getParametroFuenteP(String origen,String nombre)
	{
		Parametro param = null;
		try {			
			param = new Parametro(getInstance().findById(UtilAcceso.getParametroFuenteL(origen, nombre)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return param;
	}
	
	public Parametro getParametroFuenteP2012(String origen,String nombre)
	{
		Parametro param = null;
		try {			
			param = new Parametro(getInstance().findById2012(UtilAcceso.getParametroFuenteL(origen, nombre)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return param;
	}
	
}
