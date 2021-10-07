package co.com.coomeva.ele.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {

	public final static String REGEX_USUARIO= "[a-zA-Z0-9]*";
	
	/**
	 * Valida el email enviado
	 * @param correo
	 * @param mensaje
	 * @throws Exception
	 */
	public static void validarEmail(String correo, String mensaje) throws Exception {
		Pattern pat = null;
		Matcher mat = null;
		pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{1,9}.)+[a-zA-Z]{2,3})$");
		mat = pat.matcher(correo);
		if (!mat.find()) {
			throw new Exception(mensaje);
		}
	}

	public static double Redondear(double nD, int nDec) 
	{ 
		return Math.round(nD*Math.pow(10,nDec))/Math.pow(10,nDec); 
	}

}
