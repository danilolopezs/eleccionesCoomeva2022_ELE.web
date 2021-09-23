package co.com.coomeva.util.resources;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Oscar Javier Salazar 
 * Coomeva - Unidad de Tecnolog�a Inform�tica
 * 
 * Clase encargada del manejo de los archivos de propiedades.
 */
public class LoadBundle {

	private ResourceBundle resources;

	/**
	 * Contructor de la clase.
	 */
	public LoadBundle() {

	}

	/**
	 * Contructor de la clase con parametros.
	 */
	public LoadBundle(String archivo) {
		try {
			resources = ResourceBundle.getBundle(archivo, Locale.getDefault());
		} catch (Exception e) {
			System.out.println("Clase: " + e.getClass());
			System.out.println("No se puede cargar el archivo: " + archivo);
		}
	}

	/**
	 * Carga el archivo de propiedades.
	 * @param archivo - Debe incluir el nombre del paquete y el nombre del archivo son extencion.
	 */
	public void cargarArchivo(String archivo) {

		try {
			resources = ResourceBundle.getBundle(archivo, Locale.getDefault());
		} catch (Exception e) {
			System.out.println("Clase: " + e.getClass());
			System.out.println("No se puede cargar el archivo: " + archivo);
		}
	}

	/**
	 * Retorna la propiedad que le pertenece a la llave entregada por parametro.
	 * @return String
	 */
	public String getProperty(String llave) {

		String propiedad = null;
		try {
			propiedad = resources.getString(llave);
		} catch (Exception e) {
			System.out.println("Clase: " + e.getClass());
			System.out.println("No se puede encontrar la llave: " + llave);
		}

		return propiedad;
	}

}
