package co.com.coomeva.wseleconsultahab.vista;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.com.coomeva.wseleconsultahab.util.FacesUtils;



public class BaseVista {
	private MensajesVista mensaje;
	/**
	 * Contructor de la clase.
	 * Carga los mensajes de todos los Beans 
	 */
	public BaseVista() {
		cargarParametros();
	}


	private void cargarParametros() {

		mensaje = (MensajesVista) FacesUtils.getManagedBean("mensajesVista");
		mensaje.setMensaje(null);
	}



	/**
	 * Valida la nulidad de los datos
	 * @param object
	 * @param mensaje
	 * @throws Exception
	 */
	public void validarNull(Object object, String mensaje) throws Exception {
		if (object == null || object.toString().trim().length() == 0) {
			throw new Exception(mensaje);
		}
	}
	/**
	 * Valida el email enviado
	 * @param correo
	 * @param mensaje
	 * @throws Exception
	 */
	public void validarEmail(String correo, String mensaje) throws Exception {
		Pattern pat = null;
		Matcher mat = null;
		pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{1,9}.)+[a-zA-Z]{2,3})$");
		mat = pat.matcher(correo);
		if (!mat.find()) {
			throw new Exception(mensaje);
		}
	}
	/**
	 * Valida el email enviado
	 * @param correo
	 * @param mensaje
	 * @throws Exception
	 */
	public void validarAlphaNum(String cadena, String mensaje) throws Exception {

		for (int i = 0; i < cadena.length(); i++) {
			if (!(cadena.charAt(i) >= 97 && cadena.charAt(i) <= 122
					|| cadena.charAt(i) >= 65 && cadena.charAt(i) <= 90 || cadena
					.charAt(i) >= 48
					&& cadena.charAt(i) <= 57 && cadena.charAt(i) != 'ñ')) {
				throw new Exception(mensaje);
			}
		}
	}
	/**
	 * Valida la igualdad entre dos cadenas
	 * @param cadena1
	 * @param cadena2
	 * @param ignoreCase
	 * @param mensaje
	 * @throws Exception
	 */
	public void validarIgualdad(String cadena1, String cadena2,
			boolean ignoreCase, String mensaje) throws Exception {
		if (ignoreCase) {
			if (!cadena1.equalsIgnoreCase(cadena2)) {
				throw new Exception(mensaje);
			}
		} else {
			if (!cadena1.equals(cadena2)) {
				throw new Exception(mensaje);
			}
		}
	}
	/**
	 * Valida la Nulidad y el valor default para un ComboBox
	 * @param object
	 * @param mensaje
	 * @throws Exception
	 */
	
	public void validarCmb(Object object, String mensaje) throws Exception {
		if (object == null || object.toString().trim().length() == 0||object.toString().equalsIgnoreCase("")) {
			throw new Exception(mensaje);
		}
	}

	/**
	 * Valida si el valor enviado sobrepasa el valor maximo
	 * @param validado
	 * @param validador
	 * @param igualdad
	 * @param mensaje
	 * @throws Exception
	 */
	public void validarMaximo(Integer validado, Integer validador,
			boolean igualdad, String mensaje) throws Exception {
		if (igualdad == true) {
			if (validado.intValue() >= validador.intValue()) {
				throw new Exception(mensaje);
			}
		} else {
			if (validado.intValue() > validador.intValue()) {
				throw new Exception(mensaje);
			}
		}
	}
	/**
	 * Valida si el valor enviado es menor que el valor minimo
	 * @param validado
	 * @param validador
	 * @param igualdad
	 * @param mensaje
	 * @throws Exception
	 */
	public void validarMinimo(Integer validado, Integer validador,
			boolean igualdad, String mensaje) throws Exception {
		if (igualdad == true) {
			if (validado.intValue()<= validador.intValue()) {
				throw new Exception(mensaje);
			}
		} else {
			if (validado.intValue() < validador.intValue()) {
				throw new Exception(mensaje);
			}
		}
	}
	/**
	 * Valida que la ip que halla sido registrada correctamente sino lanza una exception con el mensaje enviado por parametro
	 * @param ip
	 * @param mensaje
	 * @throws Exception
	 */
	public void validarIP(String ip, String mensaje) throws Exception{
		String[] splitIP = ip.split("\\.");
		if (splitIP.length!= 4) {
			throw new Exception(mensaje);
		}
		for (int i = 0; i < splitIP.length; i++) {
			if ( ( splitIP[i].length() > 3 ) || ( splitIP[i].length() < 1 ) ) {
				throw new Exception(mensaje);
			}
			int nroInt = 0;
			try {
				nroInt = Integer.parseInt( splitIP[i] );
			}
			catch ( NumberFormatException s ) {
				throw new Exception(mensaje);
			}
			if ( ( nroInt < 0 ) || ( nroInt > 255 ) ) {
				throw new Exception(mensaje);
			}
		}
		if (splitIP[0].equalsIgnoreCase("127")&&splitIP[1].equalsIgnoreCase("0")&&splitIP[2].equalsIgnoreCase("0")&&splitIP[3].equalsIgnoreCase("1")) {
			throw new Exception(mensaje);
		}
		if (splitIP[0].equalsIgnoreCase("255")&&splitIP[1].equalsIgnoreCase("255")&&splitIP[2].equalsIgnoreCase("255")&&splitIP[3].equalsIgnoreCase("255")) {
			throw new Exception(mensaje);
		}
	}
	/**
	 * Valida si el telefono ingresado tiene la longitud maxima permitida
	 * @param telefono
	 * @param mensaje
	 * @throws Exception
	 */
	public void validarTelefonoFijo(Object telefono, String mensaje) throws Exception{
		String tel = telefono.toString();


		if (tel.length()>Integer.parseInt("")) {
			throw new Exception(mensaje);
		}
	}
	/**
	 * valida si el telefono celular tiene la logitud maxima permitida
	 * @param telefono
	 * @param mensaje
	 * @throws Exception
	 */
	public void validarTelefonoCelular(Object telefono, String mensaje) throws Exception{
		String tel = telefono.toString();

		if (tel.length()>Integer.parseInt("")) {
			throw new Exception(mensaje);
		}
	}


	public void validarNumLong(Object numero) throws Exception{
		String num = numero.toString();
		try {
			Long.parseLong( num );
		}
		catch ( NumberFormatException s ){
			throw new Exception("");
		}

	}
	
	public void validarNumDouble(Object numero) throws Exception{
		String num = numero.toString();
		try {
			Double.parseDouble( num );
		}
		catch ( NumberFormatException s ){
			throw new Exception("");
		}

	}
		

	public void validarNoCeroNegativos(Long campo, String mensaje)throws Exception{

		if (campo.longValue()<=0) {
			throw new Exception(mensaje);
		}
	}

	/**
	 * Metodo de Acceso.
	 */
	public MensajesVista getMensaje() {
		return mensaje;
	}

	public void setMensaje(MensajesVista mensaje) {
		this.mensaje = mensaje;
	}
}
