package co.com.coomeva.util.resources;

import java.util.Locale;
import java.util.ResourceBundle;

import co.com.coomeva.util.acceso.UtilAcceso;

/**
 * 
 * @author Giovanni Arzayus Mera
 * Componente que proporciona la funcionalidad de carga y 
 * procesamiento de archivos de propiedades, de acuerdo
 * al nombre del archivo y las claves correspondientes.
 */
public class LoaderResourceElements {
	private static LoaderResourceElements loaderReourceElements;

	private LoaderResourceElements() {
	}
	
	/**
	 * Retorna una única instancia de la clase LoaderReourceElements
	 * @return loaderReourceElements  co.com.coomeva.util.resources.LoaderReourceElements
	 */
	public static LoaderResourceElements getInstance() {
		if (loaderReourceElements == null)
			loaderReourceElements = new LoaderResourceElements();
		return loaderReourceElements;
	}
	
	/**
	 * Retorna el valor correspondiente a una clave contenida
	 * en un archivo de recurso de propiedades.
	 * @param fileResourceName
	 * @param key
	 * @return keyValue
	 * @throws Exception
	 */
	public String getKeyResourceValue(String fileResourceName,String key)throws Exception{
		String keyValue = null;
		try {		
			//System.out.println("Revisando el locale local"+Locale.getDefault().getDisplayLanguage());			
			Locale colombia = new Locale("CO","co");
			ResourceBundle resources = ResourceBundle.getBundle(fileResourceName,colombia);
			keyValue = resources.getString(key);
			if (keyValue != null) {
				keyValue = keyValue.trim();
			}
		} catch (Exception mre) {
			keyValue = null;
			UtilAcceso.printStackTrace(mre, true);
		}

		return keyValue;
	}
}
