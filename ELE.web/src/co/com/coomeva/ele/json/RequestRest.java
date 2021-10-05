package co.com.coomeva.ele.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import co.com.coomeva.ele.util.CoomevaRuntimeException;

//import co.com.coomeva.elecciones.consultarhabilidad.excepcion.CoomevaRuntimeException;

/**
 * @author GTC CORPORATION - Danilo L\u00F3pez
 * @param <T> Clase donde se almacena la respuesta de la peticion
 */
public class RequestRest<T> {

	private final Class<T> typeTokenResponseClass;

	private URL url;
	private T tokenResponse;
	private String url_service;
	private HttpURLConnection conn;
	private RequestBodyVO body;
	private Gson convert;

	public RequestRest(final String url_service, final RequestBodyVO body, final Class<T> typeTokenClass) {
		super();
		this.body = body;
		this.url_service = url_service;
		this.typeTokenResponseClass = typeTokenClass;
		convert = new Gson();
		inicializarConexion();
	}

	private void inicializarConexion() {
		try {
			this.url = new URL(this.url_service);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(HttpMethod.POST);
			conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
			conn.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
			// write body into request
			if (this.body != null) {
				conn.setDoOutput(Boolean.TRUE);
				OutputStream os = conn.getOutputStream();
				byte[] input = convert.toJson(body).getBytes("UTF-8");
				os.write(input, 0, input.length);
			}
		} catch (IOException e) {
			System.out.println("############### error setting conection inicializarConexion()");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public T getRespuesta() throws CoomevaRuntimeException {
		System.out.println("LLega respuesta");
		try {
			System.out.println("1 respuesta");
			// validate status response token
			System.out.println("------------->"+conn.getResponseCode());
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				validarRespuesta();
			}
			InputStreamReader in;
			in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			String output = "";
			String texto = br.readLine();
			while (texto != null) {
				output += texto;
				texto = br.readLine();
			}
			in.close();
			Gson gson = new Gson();
			tokenResponse = gson.fromJson(output, typeTokenResponseClass);
			System.out.println("2 respuesta");
			return gson.fromJson(output, typeTokenResponseClass);
		} catch (IOException e) {
			throw new CoomevaRuntimeException("Error en el ingreso de datos del Servicio Web.");
		}
	}

	private void validarRespuesta() throws IOException, CoomevaRuntimeException {
		try {
			switch (conn.getResponseCode()) {
			case HttpURLConnection.HTTP_BAD_REQUEST:
				throw new CoomevaRuntimeException(
						"Error enviando petici\u00F3n al servidor. Estatus " + conn.getResponseCode());
			case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
				throw new CoomevaRuntimeException(
						"El Servidor tardo mucho en responder. Estatus " + conn.getResponseCode());
			default:
				throw new CoomevaRuntimeException(
						"Ocurrio un error interno en el Servidor. Estatus " + conn.getResponseCode());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
