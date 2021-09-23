package co.com.coomeva.habilidad.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcesoHabAsociadoLoggerFactory {
	/**
	 * Constructor privado para que la clase sea un singleton
	 */
	private ProcesoHabAsociadoLoggerFactory() {}
	
	
	/**
	 * Se encarga de crear un loger con base en la interfaz IZathuraLogger
	 * @param name
	 * @return
	 * @see ISipasLogger
	 */
	public static IProcesoHabAsociadoLogger getLogger(String name){
		Logger logger=LoggerFactory.getLogger(name);
		ProcesoHabAsociadoLogger sipasLogger = new ProcesoHabAsociadoLogger(logger);
		return sipasLogger;
	}
	
	/**
	 * Se encarga de crear un loger con base en la interfaz IZathuraLogger
	 * @param name
	 * @return
	 * @see ISipasLogger
	 */
	@SuppressWarnings("unchecked")
	public static IProcesoHabAsociadoLogger getLogger(Class myClass){
		Logger logger=LoggerFactory.getLogger(myClass);
		ProcesoHabAsociadoLogger sipasLogger = new ProcesoHabAsociadoLogger(logger);
		return sipasLogger;
	}
}
