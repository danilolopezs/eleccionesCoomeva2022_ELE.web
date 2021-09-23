package co.com.coomeva.ele.util;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import co.com.coomeva.util.resources.LoaderResourceElements;



public class PathRequest {
	private static PathRequest pathRequest;

	private PathRequest() {
	}
	
	public static PathRequest getInstance(){
		if(pathRequest == null)
			pathRequest = new PathRequest();
		return pathRequest;
	}
	
	
	/*public String getPathRequest(HttpServletRequest resquest){
		
	}*/
	
	public String getPathContext(ServletContext context,String relativePath){
		String pathFile = context.getRealPath(
		"/gui/message_template_notification.htm");
		return pathFile;
	}
	
	public String getPathServerContextPath(ServletContext context,String nombreDirectorioReferencia){
		String pathFile = context.getRealPath(
		"gui/"+nombreDirectorioReferencia+"/");
		return pathFile;
	}	
	
	public String getPathContext(String nameFile){
		URL url = this.getClass().getResource("/");
		String pathFile = url.toString();
		try {
			URI uri = url.toURI();
			pathFile = uri.getPath().substring(1, uri.getPath().length());
			pathFile = pathFile+nameFile;
		} catch (URISyntaxException e) {
			System.out.println("[Error obteniendo URI para template de notificacion] "+e.toString());
		}
		return pathFile;
	}
	
	
	public String getHostPath(HttpServletRequest request){
		String path = request.getContextPath();
		StringBuffer hostPath = new StringBuffer();
		hostPath.append(request.getScheme());
		hostPath.append("://");
		hostPath.append(request.getServerName());
		hostPath.append(":");
		hostPath.append(request.getServerPort());
		hostPath.append(path);
		hostPath.append("/gui/");
		
		return hostPath.toString();

	}
	
	public String getHostPath(HttpServletRequest request,String directorioRelativo){
		String path = request.getContextPath();
		StringBuffer hostPath = new StringBuffer();
		hostPath.append(request.getScheme());
		hostPath.append("://");
		hostPath.append(request.getServerName());
		hostPath.append(":");
		hostPath.append(request.getServerPort());
		hostPath.append(path);
		hostPath.append("/gui/");
		hostPath.append(directorioRelativo);
		
		return hostPath.toString();

	}

	public String getHostPath(){
		InetAddress ipLocal = null;
		URL urlHttp = null;
		String puerto = null;
		String contexto = null;
		LoaderResourceElements loaderReourceElements = LoaderResourceElements.getInstance();
		try {
			ipLocal = InetAddress.getLocalHost();
			try {
				puerto = loaderReourceElements.
				getKeyResourceValue("param_generales",
				"PUERTO_APLICACION_SIPAS");
				contexto = loaderReourceElements.
				getKeyResourceValue("param_generales",
				"CONTEXTO_APLICACION_SIPAS");
				contexto = "/"+contexto.trim()+"/gui/imagenes";
			} catch (Exception e1) {
				System.out.println("[Error cargando el archivo de propiedades ]"+e1.toString());
			}
			
			try {
				urlHttp = new URL("http",ipLocal.getHostAddress(),Integer.parseInt(puerto.trim()),contexto);
			} catch (MalformedURLException e) {
				System.out.println("MalformedURLException :"+e.getMessage());
			}
			
		} catch (UnknownHostException e) {
			System.out.println(e.toString());
		}catch (Exception e) {
			System.out.println("[Error armando ruta para templage] "+e.toString());
		}		
		return urlHttp.toString();

	}	
}
