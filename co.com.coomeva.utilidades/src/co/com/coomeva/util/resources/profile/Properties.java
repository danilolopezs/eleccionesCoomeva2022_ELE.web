package co.com.coomeva.util.resources.profile;

import co.com.coomeva.util.resources.LoadBundle;

/**
 * @author Oscar Javier Salazar
 * 
 * Clase encargada del manejo de los archivos 
 * de parametrizacion.
 */
public class Properties {

	private LoadBundle loadBundleServicioAutenticacion;
	private LoadBundle loadBundleMensajes;
	private LoadBundle loadBundleParametros;
	
	private static Properties instance;

	/**
	 * Constructor de la clase.
	 */
	private Properties() {
		loadBundleServicioAutenticacion = new LoadBundle("co.com.coomeva.util.resources.profile.seguridad");
	}

	/**
	 * Retorna una unica instancia de la clase.
	 */
	public static Properties getInstance() {
		if (instance == null) {
			instance = new Properties();
		}
		return instance;
	}

	/**
	 * Metodos accesores.
	 */
	public LoadBundle getLoadBundleServicioAutenticacion() {
		return loadBundleServicioAutenticacion;
	}

	public LoadBundle getLoadBundleMensajes() {
		return loadBundleMensajes;
	}

	public LoadBundle getLoadBundleParametros() {
		return loadBundleParametros;
	}
	
}
