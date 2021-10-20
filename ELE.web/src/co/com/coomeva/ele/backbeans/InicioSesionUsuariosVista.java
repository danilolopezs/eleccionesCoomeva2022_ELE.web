package co.com.coomeva.ele.backbeans;

import java.util.HashMap;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import co.com.coomeva.ele.delegado.DelegadoAutenticacion;
import co.com.coomeva.ele.delegado.DelegadoSubcomision;
import co.com.coomeva.ele.delegado.DelegadoVotante;
import co.com.coomeva.ele.delegado.DelegadoZona;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoPlancha;
import co.com.coomeva.ele.dto.DTOHabilidadAsociado;
import co.com.coomeva.ele.entidades.planchas.EleSubcomision;
import co.com.coomeva.ele.entidades.planchas.EleZonas;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.profile.ws.client.CaasStub.Application;
import co.com.coomeva.profile.ws.client.CaasStub.Role;
import co.com.coomeva.profile.ws.client.CaasStub.Section;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;
import co.com.coomeva.util.acceso.UtilAcceso;

public class InicioSesionUsuariosVista extends BaseVista {

	private Logger log = LogManager.getLogger(this.getClass().getName());
	private String login;
	private String pass;

	/**
	 * Metodo que utiliza el directorio activo para traer el usuario (si existe)
	 * y las secciones a las cuales puede acceder ese usuario
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_ingreso() {
		try {
			UserVo user = DelegadoAutenticacion.getInstance().autenDirectorioActivo(login, pass);
			HashMap<String, Section> secciones = DelegadoAutenticacion.getInstance().getSecciones(user);
			FacesUtils.setSessionParameter("user", user);
			FacesUtils.setSessionParameter("userSeccions", secciones);

			String login = user.getUserId();
			// Se identifica si el usuario es funcionario de la comisión
			// electoral:
			if (DelegadoVotante.getInstance().consultarUsuarioComision(login) != null) {
				FacesUtils.setSessionParameter("userComision", login);
			} else {
				FacesUtils.getSession().removeAttribute("userComision");
			}
			
			if (secciones.get(UtilAcceso.getParametroFuenteS("parametros", "secInhabilidad")) != null) {

				// Montamos estas variables en sesión con cualquier valor para
				// que el filtro
				// nos deje ir a través de las páginas
				FacesUtils.setSessionParameter("asociado", new DTOHabilidadAsociado());
				FacesUtils.setSessionParameter("numeroDocAsociado", Long.parseLong(String.valueOf(user.getId())));
				FacesUtils.setSessionParameter("tipoElecciones",
						FacesUtils.getExternalCpntext().getInitParameter("com.coomeva.elecciones.TIPO_ELECCIONES"));

				return "goHabilidad";
			}
			if (secciones.get(UtilAcceso.getParametroFuenteS("parametros",
					"secPlancha")) != null) {
				Application[] applications = user.getApplications();
				Role[] rol = DelegadoAutenticacion.getInstance().getRol(
						applications[0]);
				// if (rol!=null) {
				// if
				// (rol[0].getName().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros",
				// "rolSubcomision"))) {
				// EleSubcomision eleSubcomision =
				// DelegadoSubcomision.getInstance().consultarSubComision(user.getId());
				// if (eleSubcomision==null) {
				// throw new
				// Exception(UtilAcceso.getParametroFuenteS("mensajes",
				// "noDBSubcomision"));
				// }
				// EleZonas eleZonas =
				// DelegadoZona.getInstance().consultarZona(eleSubcomision.getSubcomisionzona());
				// FacesUtils.setSessionParameter("zonaSubComision", eleZonas);
				// }
				// }

				// Montamos estas variables en sesión con cualquier valor para
				// que el filtro
				// nos deje ir a través de las páginas
				FacesUtils.setSessionParameter("asociado", new DTOHabilidadAsociado());
				FacesUtils.setSessionParameter("numeroDocAsociado", Long.parseLong(String.valueOf(user.getId())));
				FacesUtils.setSessionParameter("tipoElecciones",
						FacesUtils.getExternalCpntext().getInitParameter("com.coomeva.elecciones.TIPO_ELECCIONES"));

				return "goPlanchas";
			}
			action_limpiar();
		} catch (Exception e) {
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes", "nullException");
			}
			getMensaje().mostrarMensaje(mensaje);
		}
		return "";
	}

	/**
	 * Metodo que limpia el login y el password que ingreso el usuario
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_limpiar() {
		login = null;
		pass = null;
		return "";
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param pass
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
}