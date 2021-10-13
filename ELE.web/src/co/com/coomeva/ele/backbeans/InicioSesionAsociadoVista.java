package co.com.coomeva.ele.backbeans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.delegado.DelegadoCabezaPlancha;
import co.com.coomeva.ele.delegado.DelegadoExperienciaLaboral;
import co.com.coomeva.ele.delegado.DelegadoHabilidad;
import co.com.coomeva.ele.delegado.DelegadoParametros;
import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.delegado.DelegadoPrincipal;
import co.com.coomeva.ele.delegado.DelegadoSuplente;
import co.com.coomeva.ele.delegado.DelegadoZona;
import co.com.coomeva.ele.entidades.planchas.EleCabPlancha;
import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral;
import co.com.coomeva.ele.entidades.planchas.ElePParametros;
import co.com.coomeva.ele.entidades.planchas.ElePlanchas;
import co.com.coomeva.ele.entidades.planchas.EleZonas;
import co.com.coomeva.ele.json.RequestBodyVO;
import co.com.coomeva.ele.json.RequestRest;
import co.com.coomeva.ele.json.RespuestaWS;
import co.com.coomeva.ele.modelo.EleAsociadoDTO;
import co.com.coomeva.ele.modelo.EleCabPlanchaDTO;
import co.com.coomeva.ele.modelo.ElePlanchaDTO;
import co.com.coomeva.ele.modelo.ElePrincipalesDTO;
import co.com.coomeva.ele.modelo.EleSuplentesDTO;
import co.com.coomeva.ele.modelo.Parametro;
import co.com.coomeva.ele.util.CoomevaRuntimeException;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.ele.util.Validador;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.ManipulacionFechas;

public class InicioSesionAsociadoVista extends BaseVista {
	
	private final Log log = LogFactory.getLog(InicioSesionAsociadoVista.class);
	
	private String documento;
	private boolean visible = false;
	private boolean valid = false;
	private String msgEntrada;
	private String returnString;
	private String bienvenido;
	private String btnCerrar;
	private java.lang.String login = "";
	private java.lang.String password = "";
	private java.lang.String token = "FF3FD5gffd5iojbet78398bndWPLIO767HYhu";
	private String url = "https://secure.coomeva.com.co/pasaporte-Autenticacion-1/rest/";

	/**
	 * Metodo que obtiene el numero de documento y verifica que el asociado exista y
	 * que si es o no cabeza de plancha
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_ingreso() {
		visible = false;
		valid = validaCampos();
		if (valid) {
			try {
				RequestBodyVO body = new RequestBodyVO(token, login, password);
				RequestRest<RespuestaWS> request = new RequestRest<RespuestaWS>(url, body, RespuestaWS.class);
				RespuestaWS respuestaWS = request.getRespuesta();
				if (respuestaWS.getStatusCode().equals("0")) {
					visible = Boolean.TRUE;					
					FacesUtils.setSessionParameter("numeroDocAsociado",
							Long.parseLong(respuestaWS.getClient().getUser()));
					validacionInformacionPlanchas(respuestaWS.getClient().getUser());
				} else {
					exceptionGenery(respuestaWS.getDescStatusCode());
					returnString = "";
				}
			} catch (CoomevaRuntimeException e) {
				exceptionGenery(e.getMessage());
			} catch (Exception e) {
				exceptionGenery(e.getMessage());
			}
		} else {
			returnString = "";
		}
		return "";
	}

	private boolean existeUsuario() throws NumberFormatException, Exception {
		return DelegadoAsociado.getInstance().consultarInformacionBasicaAsociado(Long.parseLong(login)) != null;
	}

	private void validacionInformacionPlanchas(String identificacion) {
		try {

			EleZonas elZona = DelegadoZona.getInstance().consultarZonaPlancha(identificacion);

			EleAsociadoDTO asociadoDTO = DelegadoHabilidad.getInstance().validateAsociadoDTO(identificacion, elZona,
					identificacion);

			Date dateToday = new Date();

			Parametro parametroIni = DelegadoParametros.getInstance().getParametroFuenteP("parametros",
					"codFechaIniInscripcion");
			Parametro parametroFin = DelegadoParametros.getInstance().getParametroFuenteP("parametros",
					"codFechaFinInscripcion");

			ElePParametros elePParametrosIni = parametroIni.getParametro();

			ElePParametros elePParametrosFin = parametroFin.getParametro();
			Date dateFechaIniInscrpcion = ManipulacionFechas.stringToDate(elePParametrosIni.getNombreParametro(),
					"dd-MM-yyyy hh:mm:ss");
			Date dateFechaFinInscrpcion = ManipulacionFechas.stringToDate(elePParametrosFin.getNombreParametro(),
					"dd-MM-yyyy hh:mm:ss");

			// se comenta mientras se prueba el ingreso
			if (elePParametrosIni != null && elePParametrosFin != null) {
				if (dateToday.compareTo(dateFechaIniInscrpcion) < 0) {
					throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgFechaInscrpcionExpired"));
				}

				if (dateToday.compareTo(dateFechaFinInscrpcion) > 0) {
					throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgFechaInscrpcionExpired"));
				}
			}

			bienvenido = UtilAcceso.getParametroFuenteS("parametros", "msbBienvenido") + ", "
					+ asociadoDTO.getNombre().toString();

			ElePlanchas elePlanchas = DelegadoPlanchas.getInstance().consultarPlancha(identificacion);
			// Verifica que el Usuario Exista
			if (asociadoDTO != null) {
				if (asociadoDTO.getNombre() != null && !asociadoDTO.getNombre().equalsIgnoreCase("")) {
					EleCabPlanchaDTO eleCabPlanchaDTO = new EleCabPlanchaDTO();
					eleCabPlanchaDTO.setNroIdentificacion(identificacion);
					eleCabPlanchaDTO.setNombreCompleto(asociadoDTO.getNombre());
					eleCabPlanchaDTO.setAntiguedad(new Long(asociadoDTO.getAntiguedad()));
					eleCabPlanchaDTO.setProfesion(asociadoDTO.getProfesion());
					eleCabPlanchaDTO.setEdad(new Long(asociadoDTO.getEdad()));
					eleCabPlanchaDTO.setEmail(asociadoDTO.getEmail());
					eleCabPlanchaDTO.setPrimerNombre(asociadoDTO.getPrimerNombre());
					eleCabPlanchaDTO.setSegundoNombre(asociadoDTO.getSegundoNombre());
					eleCabPlanchaDTO.setPrimerApellido(asociadoDTO.getPrimerApellido());
					eleCabPlanchaDTO.setSegundoApellido(asociadoDTO.getSegundoApellido());

					if (asociadoDTO.isEstadoHabilidad()) {
						// Verifica que el usuario es cabeza
						if (elePlanchas != null) {
							msgEntrada = UtilAcceso.getParametroFuenteS("mensajes", "msgHabil");
							btnCerrar = UtilAcceso.getParametroFuenteS("parametros", "lblContinuar");

							List<ElePrincipalesDTO> listaPrincipales = DelegadoPrincipal.getInstance()
									.consultarPrincipales(identificacion);

							List<EleSuplentesDTO> listaSuplentes = DelegadoSuplente.getInstance()
									.consultarSuplentes(identificacion);

							ElePlanchaDTO elePlanchaDTO = new ElePlanchaDTO();

							elePlanchaDTO.setListaPrincipales(listaPrincipales);
							elePlanchaDTO.setListaSuplentes(listaSuplentes);

							EleCabPlancha cabPlancha = DelegadoCabezaPlancha.getInstance()
									.consultarCabezaPlancha(identificacion);

							if (!elePlanchas.getEstado()
									.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha1"))
									&& !elePlanchas.getEstado().equalsIgnoreCase(
											UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha3"))) {
								throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "planchaEnProceso"));
							}

							List<EleExperienciaLaboral> listExperienciaLaboral = DelegadoExperienciaLaboral
									.getInstance().consultaExperienciaLaborales(identificacion);

							eleCabPlanchaDTO = new EleCabPlanchaDTO(cabPlancha);

							elePlanchaDTO.setEleCabPlanchaDTO(eleCabPlanchaDTO);
							elePlanchaDTO.setEstado(elePlanchas.getEstado());
							elePlanchaDTO.setFechaInscripcion(elePlanchas.getFechaInscripcion());
							elePlanchaDTO.setNroCabPlancha(elePlanchas.getNroCabPlancha());
							elePlanchaDTO.setEleZonas(elePlanchas.getEleZonas());
							elePlanchaDTO.setNroPlancha(elePlanchas.getNroPlancha());
							elePlanchaDTO.setDescEstado(elePlanchas.getDescEstado());
							elePlanchaDTO.setListExperienciaLaboral(listExperienciaLaboral);

							FacesUtils.setSessionParameter("userPlancha", elePlanchaDTO);

							returnString = "goResumenPlancha";
						} else {
							msgEntrada = UtilAcceso.getParametroFuenteS("mensajes", "msgHabil");
							btnCerrar = UtilAcceso.getParametroFuenteS("parametros", "lblContinuar");
							ElePlanchaDTO elePlanchaDTO = new ElePlanchaDTO();
							elePlanchaDTO.setEleCabPlanchaDTO(eleCabPlanchaDTO);
							elePlanchaDTO.setNroCabPlancha(identificacion);
							elePlanchaDTO.setEleZonas(elZona);
							FacesUtils.setSessionParameter("userPlancha", elePlanchaDTO);

//							returnString = "goCrearPlancha";
							returnString = "goInicioMenuAsociado";
						}
					} else {
						msgEntrada = UtilAcceso.getParametroFuenteS("mensajes", "msgNoHabil");
						btnCerrar = UtilAcceso.getParametroFuenteS("parametros", "lblCerrar");
						returnString = "";
					}
				}
			}
		} catch (Exception e) {
			visible = false;
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes", "nullException");
			}
			// log.error("Error", e);
			getMensaje().mostrarMensaje(mensaje);
		}
	}

	private void exceptionGenery(String mensaje) {
		if (mensaje == null || mensaje.equalsIgnoreCase("")) {
			mensaje = UtilAcceso.getParametroFuenteS("mensajes", "nullException");
		}
		getMensaje().mostrarMensaje(mensaje);
	}

	private boolean validaCampos() {
		boolean val = true;
		try {
			if (login == null || login.equals("") || login.contains(" ")) {
				val = false;
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noUsuario"));
			}
			if (!login.matches(Validador.REGEX_USUARIO)) {
				val = false;
				login = null;
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "usuarioInvalido"));
			}
			if (password == null || password.equals("")) {
				val = false;
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noPassword"));
			}
		} catch (Exception e) {
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes", "nullException");
			}
			// log.error("Error", e);
			getMensaje().mostrarMensaje(mensaje);
		}
		return val;
	}

	/*
	 * /** Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * 
	 * @
	 */
	public String action_limpiar() {
		documento = "";
//		password = "";
//		login = "";
		return "";
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_redireccion() {
		visible = false;
		return returnString;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getBtnCerrar() {
		return btnCerrar;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param btnCerrar
	 */
	public void setBtnCerrar(String btnCerrar) {
		this.btnCerrar = btnCerrar;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getReturnString() {
		return returnString;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param returnString
	 */
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getBienvenido() {
		return bienvenido;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param bienvenido
	 */
	public void setBienvenido(String bienvenido) {
		this.bienvenido = bienvenido;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getMsgEntrada() {
		return msgEntrada;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param msgEntrada
	 */
	public void setMsgEntrada(String msgEntrada) {
		this.msgEntrada = msgEntrada;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return Srting
	 */
	public String getDocumento() {
		return documento;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param documento
	 */
	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}