package co.com.coomeva.ele.logica;

import java.util.HashMap;
import java.util.List;

import co.com.coomeva.ele.entidades.planchas.ElePValorParametros;
import co.com.coomeva.ele.modelo.Parametro;
import co.com.coomeva.ele.util.Validador;
import co.com.coomeva.profile.client.delegate.DelegadoProfileManager;
import co.com.coomeva.profile.ws.client.CaasStub.Application;
import co.com.coomeva.profile.ws.client.CaasStub.Role;
import co.com.coomeva.profile.ws.client.CaasStub.Section;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;
import co.com.coomeva.profile.ws.client.CaasStub.ValidateUser;
import co.com.coomeva.util.acceso.UtilAcceso;

public class LogicaAutenticacion {
	public static Application application = new Application();

	// *public static Parametro urlP =
	// LogicaElePParametro.getInstance().getParametroFuenteP("propiedades_pm",
	// "url_ws_pm");
	public static String urlS = UtilAcceso.getParametroFuenteS("parametros", "COD_PARAM_URL_WS_PM");

	// public static Parametro nombreAplicacionP =
	// LogicaElePParametro.getInstance().getParametroFuenteP("propiedades_pm",
	// "nombreAplicacion");
	public static String nombreAplicacionS = UtilAcceso.getParametroFuenteS("parametros",
			"COD_PARAM_NOMBRE_APLICACION");

	// public static Parametro ldapP =
	// LogicaElePParametro.getInstance().getParametroFuenteP("propiedades_pm",
	// "fuente_ldap");
	public static String ldapS = UtilAcceso.getParametroFuenteS("parametros", "COD_PARAM_FUENTE_LDAP");

	// public static Parametro daP =
	// LogicaElePParametro.getInstance().getParametroFuenteP("propiedades_pm",
	// "fuente_da");
	public static String daS = UtilAcceso.getParametroFuenteS("parametros", "COD_PARAM_FUENTE_DA");
	
	private static ValidateUser valUser = new ValidateUser();
	private static LogicaAutenticacion instance = null;
	public static UserVo userVo = null;

	/**
	 * Retorna una unica instancia de la clase Patrón Singular
	 * 
	 * @return LogicaAutentication
	 */
	public static LogicaAutenticacion getInstace() {
		if (instance == null) {
			instance = new LogicaAutenticacion();
		}
		return instance;
	}

	/**
	 * Constructor
	 */
	public LogicaAutenticacion() {
		/*
		 * try { //caasStub = new CaasStub(urlS); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
	}

	/**
	 * Metodo que autentica al usuario en el directorio activo.
	 * 
	 * @param login
	 * @param password
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return usuario autenticado
	 * @throws Exception
	 */
	public UserVo autenDirectorioActivo(String login, String password) throws Exception {
		if (login == null || login.equals(""))
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noUsuario"));
		if (!login.matches(Validador.REGEX_USUARIO)) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "usuarioInvalido"));
		}
		if (password == null || password.equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noPassword"));
		}
		Integer tipoVal = null;
		userVo = null;
		// DelegadoServicioAutenticacion autenticacion=null;
		DelegadoProfileManager autenticacion = null;

		try {
			tipoVal = UtilAcceso.getParametroFuenteI("propiedades_pm", "tipoValProfile");

			LogicaUsuarioLogin logicaUsuarioLogin = LogicaUsuarioLogin.getInstance();
			if (logicaUsuarioLogin.usuarioBloqueado(login)) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "usuarioBloqueado"));
			}

			if (tipoVal != null) {
				// autenticacion = new DelegadoServicioAutenticacion();
				autenticacion = DelegadoProfileManager.getInstance();

				// userVo = autenticacion.validarUsuario(login, password, urlS, tipoVal,
				// nombreAplicacionS, 0);
				userVo = autenticacion.validateUser(urlS, tipoVal, login, password, nombreAplicacionS, 0);
			}
			if (userVo == null) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noUsuarioDA"));
			}
			if (userVo.getAuthorized().equals("false")) {

				logicaUsuarioLogin.incrementarIntentos(login);

				Long intentosRestantes = logicaUsuarioLogin.consultarItentosRestantes(login);

				String mensaje = UtilAcceso.getParametroFuenteS("mensajes", "noUsuarioNoClave");
				mensaje = mensaje.replaceAll("#numIntento", intentosRestantes.toString());

				throw new Exception(mensaje);
			} else {
				logicaUsuarioLogin.reiniciarItentos(login);
			}
			// if (!estaAutorizado(userVo, nombreAplicacionS)) {
			// throw new
			// Exception(UtilAcceso.getParametroFuenteS("mensajes","noUsuarioValido"));
			// }
		} catch (Exception e) {
			throw e;
		} finally {
			tipoVal = null;
			autenticacion = null;
		}
		return userVo;
		/*
		 * 
		 * 
		 * userVo = null; ValidateUserResponse resp = null; CaasStub caasStub = null;
		 * ValidateUser valUser = null; try {
		 * 
		 * caasStub = new CaasStub(urlS); valUser = new ValidateUser();
		 * SetAplicacionBuscada aplicacionBuscada = new SetAplicacionBuscada();
		 * aplicacionBuscada.setAplicacionBuscada(nombreAplicacionS);
		 * 
		 * caasStub.setAplicacionBuscada(aplicacionBuscada);
		 * 
		 * valUser.setUserName(login); valUser.setPassword(password);
		 * valUser.setDirectory(new Integer(daS));
		 * 
		 * resp= caasStub.validateUser(valUser);
		 * 
		 * if (resp != null) { userVo = resp.get_return(); if( userVo == null){ throw
		 * new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noUsuarioDA")); }
		 * if (userVo.getAuthorized().equals("false")) { throw new
		 * Exception(UtilAcceso.getParametroFuenteS("mensajes", "noUsuarioNoClave")); }
		 * if (!estaAutorizado(userVo, nombreAplicacionS)) { throw new
		 * Exception(UtilAcceso.getParametroFuenteS("mensajes", "noUsuarioValido")); } }
		 * 
		 * } catch (Exception e) { throw e; }finally{ resp = null; caasStub = null;
		 * valUser = null; } return userVo;
		 */}

	/**
	 * Método que autentica al usuario asociado.
	 * 
	 * @param login
	 * @param password
	 * @author Mario Alejandro Acevedo
	 * @return usuario autenticado
	 * @throws Exception
	 */
	public UserVo autenDirectorioOpenLDAP(String login, String password) throws Exception {
		if (login == null || login.equals(""))
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noUsuario"));
		if (password == null || password.equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noPassword"));
		}
		
		Integer tipoVal = null;
		userVo = null;
		DelegadoProfileManager autenticacion = null;
		try {
			tipoVal = UtilAcceso.getParametroFuenteI("propiedades_pm", "dirOpen");
			if (tipoVal != null) {
				autenticacion = DelegadoProfileManager.getInstance();
				userVo = new UserVo();
			}
			if (userVo == null) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noUsuarioDA"));
			}
			if (userVo.getAuthorized().equals("false")) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noUsuarioNoClave"));
			}

		} catch (Exception e) {
			throw e;
		} finally {
			tipoVal = null;
			autenticacion = null;
		}
		return userVo;
	}

	/**
	 * Metodo que consulta las apicaciones en las que un usuario esta registrado
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param usuario
	 * @return Application[]
	 */
	public Application[] listadoAplicaciones(UserVo usuario) {
		return usuario.getApplications();
	}

	/**
	 * Indica si un usuario está o no autorizado para acceder a una aplicación en
	 * particular que este registrada en el Profile Manager.
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nombreAplicacion
	 * @return boolean
	 * @throws Exception
	 */
	private boolean estaAutorizado(UserVo usuario, String nombreAplicacion) throws Exception {

		Application[] listadoApp = listadoAplicaciones(usuario);

		try {
			if (listadoApp == null) {
				return false;
			}
			for (int i = 0; i < listadoApp.length; i++) {
				if (listadoApp[i].getName().equals(nombreAplicacion)) {
					application = listadoApp[i];
					return true;
				}
			}
		} catch (Exception e) {
			throw e;
		}

		return false;
	}

	/**
	 * Determinan el rol que se le ha asignado al usuario en la aplicacion.
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param userVo
	 * @return String
	 * @throws Exception
	 */
	public String getRolUsuario(UserVo userVo) throws Exception {
		String rolNom = "";
		Parametro parametro = null;
		List<ElePValorParametros> losParametros = null;
		Application[] applications = null;
		Role[] role = null;
		try {
			parametro = LogicaElePParametro.getInstance().getParametroFuenteP("propiedades_pm", "roles");
			losParametros = LogicaElePValorParametro.getInstance().listaParametros(parametro);
			applications = userVo.getApplications();
			for (Application application : applications) {
				role = getRol(application);
				for (Role role2 : role) {
					for (ElePValorParametros rol : losParametros) {
						if (role2.getName().equalsIgnoreCase(rol.getNombreParametro())) {
							rolNom = rol.getNombreParametro();
						}
					}
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			parametro = null;
			losParametros = null;
			applications = null;
			role = null;
		}
		return rolNom;
	}

	/**
	 * Metodo que obtiene los roles del usuario
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param app
	 * @return Role[]
	 */
	public Role[] getRol(Application app) {
		Role[] rol = null;

		if (app != null)
			rol = app.getRoles();
		else
			return null;
		return rol;
	}

	/**
	 * Devuelve todas las acciones que contiene un usuario
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param usuario
	 * @return HashMap<String, String>
	 */

	public HashMap<String, String> getAcciones(UserVo usuario) throws Exception {

		Application[] listadoApp = listadoAplicaciones(usuario);
		Application aplicacion = null;
		HashMap<String, String> listadoAcc = new HashMap<String, String>();

		if (listadoApp == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noAplicationUser"));
		}

		for (int i = 0; i < listadoApp.length; i++) {
			if (listadoApp[i].getName().equals(nombreAplicacionS)) {
				aplicacion = listadoApp[i];
				break;
			}
		}

		Role[] roles = aplicacion.getRoles();
		for (Role role : roles) {
			Section[] secciones = role.getSections();
			for (Section section : secciones) {
				String[] actions = section.getActions();
				for (String string : actions) {
					if (listadoAcc.get(string) == null) {
						listadoAcc.put(string, string);
					}
				}
			}
		}

		return listadoAcc;
	}

	/**
	 * Devuelve todas las secciones de un usuario
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param usuario
	 * @return HashMap<String, Section>
	 */
	public HashMap<String, Section> getSecciones(UserVo usuario) throws Exception {
		Application[] listadoApp = listadoAplicaciones(usuario);
		Application aplicacion = null;
		HashMap<String, Section> listadoSec = new HashMap<String, Section>();

		if (listadoApp == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noAplicationUser"));
		}

		for (int i = 0; i < listadoApp.length; i++) {
			if (listadoApp[i].getName().equals(nombreAplicacionS)) {
				aplicacion = listadoApp[i];
				break;
			}
		}

		Role[] roles = aplicacion.getRoles();
		for (Role role : roles) {
			Section[] secciones = role.getSections();
			for (Section section : secciones) {
				if (listadoSec.get(section.getName()) == null) {
					listadoSec.put(section.getName(), section);
				}
			}
		}

		return listadoSec;
	}
}
