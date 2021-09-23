package co.com.coomeva.ele.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.StringTokenizer;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class ObtenerIP {
	
	private static ObtenerIP instance;

	//Constructor de la clase
	private ObtenerIP() {
	}

	//Patròn Singular
	public static ObtenerIP getInstance() {
		if (instance == null) {
			instance = new ObtenerIP();
		}
		return instance;
	}

	public String getIP(){
		String IP;
		HttpServletRequest httpServletRequest =  (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		IP=httpServletRequest.getRemoteAddr();
		return IP; 
	}
	
	private final static String getMacAddress() throws IOException {
		String os = System.getProperty("os.name");
		try {
			if (os.startsWith("Windows")) {
				return windowsParseMacAddress(windowsRunIpConfigCommand());
			} else if (os.startsWith("Linux")) {
				return linuxParseMacAddress(linuxRunIfConfigCommand());
			} else {
				throw new IOException("Sistema operativo desconocido: " + os);
			}
		} catch (ParseException ex) {
			ex.printStackTrace();
			throw new IOException(ex.getMessage());
		}
	}

	private final static String linuxParseMacAddress(String ipConfigResponse)
			throws ParseException {
		String localHost = null;
		HttpServletRequest httpServletRequest =  (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		localHost = httpServletRequest.getRemoteAddr();

		StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse, "\n");
		String lastMacAddress = null;

		while (tokenizer.hasMoreTokens()) {
			String line = tokenizer.nextToken().trim();
			boolean containsLocalHost = line.indexOf(localHost) >= 0;

			if (containsLocalHost && lastMacAddress != null) {
				return lastMacAddress;
			}

			int macAddressPosition = line.indexOf("HWaddr");
			if (macAddressPosition <= 0)
				continue;

			String macAddressCandidate = line.substring(macAddressPosition + 6)
					.trim();
			if (linuxIsMacAddress(macAddressCandidate)) {
				lastMacAddress = macAddressCandidate;
				continue;
			}
		}

		ParseException ex = new ParseException(
				"Imposible obtener la dirección MAC " + localHost + " desde ["
						+ ipConfigResponse + "]", 0);
		ex.printStackTrace();
		throw ex;
	}

	private final static boolean linuxIsMacAddress(String macAddressCandidate) {
		if (macAddressCandidate.length() != 17)
			return false;
		return true;
	}

	private final static String linuxRunIfConfigCommand() throws IOException {
		Process p = Runtime.getRuntime().exec("ifconfig");
		InputStream stdoutStream = new BufferedInputStream(p.getInputStream());

		StringBuffer buffer = new StringBuffer();
		for (;;) {
			int c = stdoutStream.read();
			if (c == -1)
				break;
			buffer.append((char) c);
		}
		String outputText = buffer.toString();

		stdoutStream.close();

		return outputText;
	}

	private final static String windowsParseMacAddress(String ipConfigResponse)
			throws ParseException {
		String localHost = null;
		HttpServletRequest httpServletRequest =  (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		localHost = httpServletRequest.getRemoteAddr();

		StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse, "\n");
		String lastMacAddress = null;

		while (tokenizer.hasMoreTokens()) {
			String line = tokenizer.nextToken().trim();

			if (line.endsWith(localHost) && lastMacAddress != null) {
				return lastMacAddress;
			}

			int macAddressPosition = line.indexOf(":");
			if (macAddressPosition <= 0)
				continue;

			String macAddressCandidate = line.substring(macAddressPosition + 1)
					.trim();
			if (windowsIsMacAddress(macAddressCandidate)) {
				lastMacAddress = macAddressCandidate;
				continue;
			}
		}

		ParseException ex = new ParseException(
				"Imposible obtener dirección MAC desde [" + ipConfigResponse
						+ "]", 0);
		ex.printStackTrace();
		throw ex;
	}

	private final static boolean windowsIsMacAddress(String macAddressCandidate) {
		if (macAddressCandidate.length() != 17)
			return false;

		return true;
	}

	private final static String windowsRunIpConfigCommand() throws IOException {
		Process p = Runtime.getRuntime().exec("ipconfig /all");
		InputStream stdoutStream = new BufferedInputStream(p.getInputStream());

		StringBuffer buffer = new StringBuffer();
		for (;;) {
			int c = stdoutStream.read();
			if (c == -1)
				break;
			buffer.append((char) c);
		}
		String outputText = buffer.toString();
		stdoutStream.close();

		return outputText;
	}
}
