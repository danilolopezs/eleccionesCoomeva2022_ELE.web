package co.com.coomeva.ele.backbeans;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
import co.com.coomeva.ele.logica.LogicaAutenticacion;
import co.com.coomeva.ele.modelo.EleAsociadoDTO;
import co.com.coomeva.ele.modelo.EleCabPlanchaDTO;
import co.com.coomeva.ele.modelo.ElePlanchaDTO;
import co.com.coomeva.ele.modelo.ElePrincipalesDTO;
import co.com.coomeva.ele.modelo.EleSuplentesDTO;
import co.com.coomeva.ele.modelo.Parametro;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.ManipulacionFechas;

public class InicioSesionAsociadoVista extends BaseVista
{
	private String documento;
	private boolean visible = false;
	private String msgEntrada;
	private String returnString;
	private String bienvenido;
	private String btnCerrar;
	private final Log log  = LogFactory.getLog(InicioSesionAsociadoVista.class);
	private String login;
	private String password;
	
	
	
	/**
	 * Metodo que obtiene el numero de documento y verifica que el asociado exista y 
	 * que si es o no cabeza de plancha
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_ingreso() 
	{
		Long idLong = 0L;
		visible = true;
		//int validador = UtilAcceso.getParametroFuenteI("parametros", "maxNoDoc");
		//int validado = documento.length();
		
		LogicaAutenticacion logicaAutenticacion = LogicaAutenticacion.getInstace();
		UserVo userVo;

		try 
		{
			//validarMaximo(validado, validador, false, UtilAcceso.getParametroFuenteS("mensajes", "excedioNoDoc"));

			//validarNumLong(documento);
			
			userVo = logicaAutenticacion.autenDirectorioOpenLDAP(login, password);			

			idLong = Long.valueOf(userVo.getId());
			documento = userVo.getId();


			EleZonas elZona = DelegadoZona.getInstance().consultarZonaPlancha(documento);

			EleAsociadoDTO asociadoDTO = DelegadoHabilidad.getInstance().validateAsociadoDTO(documento,elZona,documento);

			Date dateToday = new Date();

			Parametro parametroIni = DelegadoParametros.getInstance().getParametroFuenteP("parametros", "codFechaIniInscripcion");
			Parametro parametroFin = DelegadoParametros.getInstance().getParametroFuenteP("parametros", "codFechaFinInscripcion");

			ElePParametros elePParametrosIni = parametroIni.getParametro();
			ElePParametros elePParametrosFin = parametroFin.getParametro();

			Date dateFechaIniInscrpcion = ManipulacionFechas.stringToDate(elePParametrosIni.getValorParametro(),"dd-MM-yyyy hh:mm:ss a");
			Date dateFechaFinInscrpcion = ManipulacionFechas.stringToDate(elePParametrosFin.getValorParametro(),"dd-MM-yyyy hh:mm:ss a");

			/*
			 * se comenta mientras se prueba el ingreso
			 * if (elePParametrosIni != null && elePParametrosFin !=null) 
			{
				if (dateToday.compareTo(dateFechaIniInscrpcion)<0) {
					throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgFechaInscrpcionExpired"));
				}

				if (dateToday.compareTo(dateFechaFinInscrpcion)>0) {
					throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "msgFechaInscrpcionExpired"));
				}
			}*/

			bienvenido = UtilAcceso.getParametroFuenteS("parametros", "msbBienvenido") + ", " + asociadoDTO.getNombre().toString();

			ElePlanchas elePlanchas = DelegadoPlanchas.getInstance().consultarPlancha(documento); 
			//Verifica que el Usuario Exista
			if (asociadoDTO != null) {
				if (asociadoDTO.getNombre() != null && !asociadoDTO.getNombre().equalsIgnoreCase("")) 
				{
					EleCabPlanchaDTO eleCabPlanchaDTO = new EleCabPlanchaDTO();
					eleCabPlanchaDTO.setNroIdentificacion(""+idLong);
					eleCabPlanchaDTO.setNombreCompleto(asociadoDTO.getNombre());
					eleCabPlanchaDTO.setAntiguedad(new Long(asociadoDTO.getAntiguedad()));
					eleCabPlanchaDTO.setProfesion(asociadoDTO.getProfesion());
					eleCabPlanchaDTO.setEdad(new Long(asociadoDTO.getEdad()));
					eleCabPlanchaDTO.setEmail(asociadoDTO.getEmail());
					eleCabPlanchaDTO.setPrimerNombre(asociadoDTO.getPrimerNombre());
					eleCabPlanchaDTO.setSegundoNombre(asociadoDTO.getSegundoNombre());
					eleCabPlanchaDTO.setPrimerApellido(asociadoDTO.getPrimerApellido());
					eleCabPlanchaDTO.setSegundoApellido(asociadoDTO.getSegundoApellido());

					if (asociadoDTO.isEstadoHabilidad())
					{
						//Verifica que el usuario es cabeza
						if (elePlanchas != null)
						{
							msgEntrada = UtilAcceso.getParametroFuenteS("mensajes", "msgHabil");
							btnCerrar = UtilAcceso.getParametroFuenteS("parametros", "lblContinuar");

							List<ElePrincipalesDTO> listaPrincipales = DelegadoPrincipal.getInstance().consultarPrincipales(documento);

							List<EleSuplentesDTO> listaSuplentes = DelegadoSuplente.getInstance().consultarSuplentes(documento);

							ElePlanchaDTO elePlanchaDTO  = new ElePlanchaDTO();

							elePlanchaDTO.setListaPrincipales(listaPrincipales);
							elePlanchaDTO.setListaSuplentes(listaSuplentes);

							EleCabPlancha cabPlancha = DelegadoCabezaPlancha.getInstance().consultarCabezaPlancha(documento);


							if (!elePlanchas.getEstado().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha1")) && !elePlanchas.getEstado().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha3"))) {
								throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "planchaEnProceso"));
							}
							
							List<EleExperienciaLaboral> listExperienciaLaboral = DelegadoExperienciaLaboral.getInstance().consultaExperienciaLaborales(documento);

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
						}else
						{
							msgEntrada = UtilAcceso.getParametroFuenteS("mensajes", "msgHabil");
							btnCerrar = UtilAcceso.getParametroFuenteS("parametros", "lblContinuar");
							ElePlanchaDTO elePlanchaDTO = new ElePlanchaDTO();
							elePlanchaDTO.setEleCabPlanchaDTO(eleCabPlanchaDTO);
							elePlanchaDTO.setNroCabPlancha(documento);
							elePlanchaDTO.setEleZonas(elZona);
							FacesUtils.setSessionParameter("userPlancha", elePlanchaDTO);

							returnString = "goCrearPlancha";
						}
					}else{
						msgEntrada = UtilAcceso.getParametroFuenteS("mensajes", "msgNoHabil");
						btnCerrar = UtilAcceso.getParametroFuenteS("parametros", "lblCerrar");
						returnString = "";
					}
				}
			}
		} 
		catch (Exception e) 
		{
			visible = false;
			String mensaje = e.getMessage();
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
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_limpiar()
	{
		documento = null;
		return "";
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String action_redireccion()
	{
		visible = false;
		return returnString;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getBtnCerrar() {
		return btnCerrar;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param btnCerrar
	 */
	public void setBtnCerrar(String btnCerrar) {
		this.btnCerrar = btnCerrar;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getReturnString() {
		return returnString;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param returnString
	 */
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getBienvenido() 
	{
		return bienvenido;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param bienvenido
	 */
	public void setBienvenido(String bienvenido) {
		this.bienvenido = bienvenido;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getMsgEntrada() {
		return msgEntrada;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param msgEntrada
	 */
	public void setMsgEntrada(String msgEntrada) {
		this.msgEntrada = msgEntrada;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isVisible() {
		return visible;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return Srting
	 */
	public String getDocumento() {
		return documento;
	}
	/**
	 * Metodo de Acceso
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