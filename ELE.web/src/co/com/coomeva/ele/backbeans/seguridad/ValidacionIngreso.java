package co.com.coomeva.ele.backbeans.seguridad;

import java.text.MessageFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import co.com.coomeva.ele.backbeans.BaseVista;
import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.delegado.DelegadoLogAsociado;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoPlancha;
import co.com.coomeva.ele.dto.DTOHabilidadAsociado;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.modelo.EleCabPlanchaDTO;
import co.com.coomeva.ele.modelo.ElePlanchaDTO;
import co.com.coomeva.ele.modelo.ParametroPlanchaDTO;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.ele.util.LectorParametros;
import co.com.coomeva.ele.util.ObtenerIP;
import co.com.coomeva.ele.util.Validador;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.ManipulacionFechas;
import co.com.coomeva.ele.util.ConstantesProperties;

public class ValidacionIngreso extends BaseVista {
	private Long documento;
	private boolean visible = false;
	private String msgEntrada;
	private String returnString;
	private String returnString2;
	private String bienvenido;
	private String btnCerrar;
	private final Log log = LogFactory.getLog(ValidacionIngreso.class);
	private String tipoEleccionesSession;
	private String tipoEleccionesRepresentantes;

	private DTOHabilidadAsociado asociado;

	public ValidacionIngreso() {
		documento = (Long) FacesUtils.getSessionParameter("numeroDocAsociado");
		action_ingreso();
		try {

			FacesUtils.setSessionParameter("tipoElecciones",
					FacesUtils.getExternalCpntext().getInitParameter("com.coomeva.elecciones.TIPO_ELECCIONES"));

			this.tipoEleccionesSession = (String) FacesUtils.getSessionParameter("tipoElecciones");
			this.tipoEleccionesRepresentantes = UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "param.tipo.elecciones.representantes");
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtils.addErrorMessage("Se presento un error Inesperado");
		}

	}

	/**
	 * Metodo que obtiene el numero de documento y verifica que el asociado exista y
	 * que si es o no cabeza de plancha
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_ingreso() {
		visible = true;

		try {

			/**
			 * Se valida que el usuario ingrese el número de identificación a validar
			 */
			if (documento == null) {
				throw new EleccionesDelegadosException(
						UtilAcceso.getParametroFuenteS("mensajes", "noCedulaValidacion"));
			}
			String doc = String.valueOf(documento);

			// Si la elección es para representantes se valida que el asociado
			// no sea persona jurídica:
			/*
			 * if (tipoEleccionesRepresentantes.equals(tipoEleccionesSession) &&
			 * DelegadoAsociado.getInstance().esAsociadoPersonaJuridica(documento)) { throw
			 * new EleccionesDelegadosException(MessageFormat.format(
			 * UtilAcceso.getParametroFuenteS("mensajes",
			 * "msgErrorNoSePermitenPlanchasAsociadoJuridico"), documento.toString()));
			 * 
			 * }
			 */

			// Si la elección es para representantes se valida que un empleado
			// no puede ser elegido:
			/*
			 * if (tipoEleccionesRepresentantes.equals(tipoEleccionesSession) &&
			 * DelegadoPlancha.getInstance().esColaboradorGECoomeva(doc)) { throw new
			 * EleccionesDelegadosException(MessageFormat.format(
			 * UtilAcceso.getParametroFuenteS("mensajes",
			 * "msgErrorNoSePermitenPlanchasEmpleados"), documento.toString()));
			 * 
			 * }
			 */

			asociado = null;

			if (DelegadoAsociado.getInstance().esAsociado(documento)) {

				if (DelegadoAsociado.getInstance().estaAsociaActivo(documento)) {
					if (DelegadoAsociado.getInstance().asociadoPerteneceZonaHayEleccion(documento)) {
						asociado = DelegadoAsociado.getInstance().consultarHabilidadAsociado(documento);
					} else {
						throw new EleccionesDelegadosException(MessageFormat.format(
								UtilAcceso.getParametroFuenteS("mensajes", "msgAsociadoNoPerteneceAzonaElectoral"),
								documento.toString()));
					}

				} else {
					throw new EleccionesDelegadosException(MessageFormat.format(
							UtilAcceso.getParametroFuenteS("mensajes", "msgAsociadoNoActivoCooperativa"),
							documento.toString()));
				}

			} else {
				throw new EleccionesDelegadosException(
						UtilAcceso.getParametroFuenteS("mensajes", "msgIdNoFiguraComoAsociadoCooperativa"));
			}

			if (asociado != null && asociado.getAsociadoHabil()) {

				/**
				 * se valida que la fecha actual sea una fecha hábil para el proceso de
				 * inscripción de planchas
				 */
				Date dateToday = new Date();

				ParametroPlanchaDTO parametroFechaInicial = LectorParametros.obtenerParametrosCodigoTipo(
						UtilAcceso.getParametroFuenteL("parametros", "campo.param.registro.inicio"),
						UtilAcceso.getParametroFuenteL("parametros", "campo.param.registro.tipo"));
				ParametroPlanchaDTO parametroFechaFinal = LectorParametros.obtenerParametrosCodigoTipo(
						UtilAcceso.getParametroFuenteL("parametros", "campo.param.registro.fin"),
						UtilAcceso.getParametroFuenteL("parametros", "campo.param.registro.tipo"));

				Date dateFechaIniInscrpcion = ManipulacionFechas.stringToDate(parametroFechaInicial.getStrValor(),
						"yyyy-MM-dd hh:mm:ss");
				Date dateFechaFinInscrpcion = ManipulacionFechas.stringToDate(parametroFechaFinal.getStrValor(),
						"yyyy-MM-dd hh:mm:ss");

				boolean habilidad = false;

				if (dateToday.getTime() >= dateFechaIniInscrpcion.getTime()
						&& dateToday.getTime() <= dateFechaFinInscrpcion.getTime()) {
					habilidad = true;
				}

				if (habilidad) {
					DelegadoLogAsociado.getInstance().crearRegistroLogAsociado(null,
							UtilAcceso.getParametroFuenteS("parametros", "tipTrn_autenticacion"),
							ObtenerIP.getInstance().getIP(), null, String.valueOf(documento), null);
					String nombreCompleto = DelegadoAsociado.getInstance().consultarNombreAsociado(documento);
					asociado.setNombreAsociado(nombreCompleto);
					FacesUtils.setSessionParameter("asociado", asociado);
					FacesUtils.setSessionParameter("numeroDocAsociado", documento);
					return "goInicioMenuAsociado";
				} else {
					throw new EleccionesDelegadosException(UtilAcceso.getParametroFuenteS("mensajes", "fechaFueraRango")
							+ ": [" + parametroFechaInicial.getStrValor() + "  -  " + parametroFechaFinal.getStrValor()
							+ "]");
				}
			} else {
				throw new EleccionesDelegadosException(UtilAcceso.getParametroFuenteS("mensajes", "noHabil2"));
			}
		} catch (EleccionesDelegadosException e) {
			visible = false;
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes", "nullException");
			}
			log.error("Error", e);
			getMensaje().mostrarMensaje(mensaje);
		} catch (Exception e) {
			visible = false;
			String mensaje = "Se presento un Error Inesperado";
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes", "nullException");
			}
			log.error("Error", e);
			getMensaje().mostrarMensaje(mensaje);
		}
		return "";
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_limpiar() {
		documento = null;
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
	public Long getDocumento() {
		return documento;
	}

	/**
	 * Metodo de Acceso
	 * 
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param documento
	 */
	public void setDocumento(Long documento) {
		this.documento = documento;
	}

	public String getReturnString2() {
		return returnString2;
	}

	public void setReturnString2(String returnString2) {
		this.returnString2 = returnString2;
	}

	public DTOHabilidadAsociado getAsociado() {
		if (asociado == null) {
			asociado = (DTOHabilidadAsociado) FacesUtils.getSessionParameter("asociado");
		}
		return asociado;
	}

	public void setAsociado(DTOHabilidadAsociado asociado) {
		this.asociado = asociado;
	}

	public Log getLog() {
		return log;
	}

}