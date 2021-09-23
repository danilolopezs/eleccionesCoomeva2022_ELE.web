package co.com.coomeva.ele.util.generador;

/**
 * @author Ricardo Alberto Chiriboga
 * 
 * Clase encargada del manejo de los archivos 
 * de parametrizacion.
 */
public class LoadProperties {

	
	private LoadBundle loadBundlePropiedades;
	
	private static LoadProperties instance;

	/**
	 * Constructor de la clase.
	 */
	private LoadProperties() {
		loadBundlePropiedades = new LoadBundle("co.com.coomeva.ele.propiedades.generador.propiedades");
	}

	/**
	 * Retorna una unica instancia de la clase.
	 */
	public static LoadProperties getInstance() {
		if (instance == null) {
			instance = new LoadProperties();
		}
		return instance;
	}

	/**
	 * Metodos accesores.
	 */


	public LoadBundle getLoadBundlePropiedades() {
		return loadBundlePropiedades;
	}

	
}
