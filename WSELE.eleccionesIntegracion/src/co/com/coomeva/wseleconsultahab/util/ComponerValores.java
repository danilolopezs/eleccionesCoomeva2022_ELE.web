package co.com.coomeva.wseleconsultahab.util;

/**
 * @version 1.0
 * Descripción : Componente de transformación de datos 
 *				 para ingreso en el AS400
 */
public class ComponerValores {
	
	private static ComponerValores instance = null;

	public ComponerValores() {

	}

	public static ComponerValores getInstance() {
		if (instance == null) {
			instance = new ComponerValores();
		}
		return instance;
	}
	
	/**
	 * Agrega ceros a la izquierda
	 * @param int tamCampo
	 * @param Long campo
	 * @return
	 */
	
	public String adiconarCeros(int tamCampo, Long campo){
		
		String campoString = String.valueOf(campo);
		int tamFaltante;
		
		tamFaltante = tamCampo - campoString.length();
		for (int i = 0; i < tamFaltante; i++) {
			campoString = "0" + campoString; 
		}
		return campoString;
		
	}
	
	/**
	 * Agrega ceros a la izquierda
	 * @param int tamCampo
	 * @param String campo
	 * @return
	 */
	public String adiconarCeros(int tamCampo, String campo){
		
		int tamFaltante;
		if(campo != null){
			tamFaltante = tamCampo - campo.length();
			for (int i = 0; i < tamFaltante; i++) {
				campo = "0" + campo; 
			}
		}
		return campo;
		
	}

	/**
	 * Agrega ceros a la izquierda
	 * @param int tamCampo
	 * @param String campo
	 * @return
	 */
	public String adiconarEspacios(int tamCampo, String campo){
		
		int tamFaltante;
		if(campo != null){
			tamFaltante = tamCampo - campo.length();
			for (int i = 0; i < tamFaltante; i++) {
				campo = campo + " "; 
			}
		}
		return campo;
		
	}

}
