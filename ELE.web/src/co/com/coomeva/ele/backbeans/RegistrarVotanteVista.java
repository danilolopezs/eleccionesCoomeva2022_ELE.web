package co.com.coomeva.ele.backbeans;

import java.text.SimpleDateFormat;
import java.util.Date;

import co.com.coomeva.ele.delegado.DelegadoVotante;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoPlancha;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.modelo.EleUsuarioComisionDTO;
import co.com.coomeva.ele.modelo.EleVotanteDatosDTO;
import co.com.coomeva.ele.modelo.ParametroPlanchaDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.ele.util.LectorParametros;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.ManipulacionFechas;

/**
 * 
 * @author Carlos Ernesto Ortega Q. Contratista Coomeva 12/12/2013
 * 
 */
public class RegistrarVotanteVista extends BaseVista {

	private final static Long CODIGO_TIPO_PARAMETRO_FECHA_ELECCION = new Long(
			UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"codigo.tipo.parametro.fecha.eleccion.representantes"));

	private Long documentoVotante;
	private String fechaVotacion;
	private String usuarioRegistro;
	private String zonaUsuarioRegistro;
	private Integer agenciaUsuarioRegistro;
	private String ipRegistro;
	private EleUsuarioComisionDTO dtoUsuarioComision;
	private EleVotanteDatosDTO dtoVotanteDatos;
	private UserVo user;
	private boolean votanteValido;
	private boolean fechaValida;
	private String mensajeConfirmacion = "";
	private boolean visibleConfirmarRegistrar;
	private MensajesVista mensajeVista = new MensajesVista();

	public RegistrarVotanteVista() {
		init();
	}

	/**
	 * Método que recibe la información de la sesión e inicializa los
	 * componentes
	 * 
	 * @author Carlos Ernesto Ortega Q.
	 */
	private void init() {
		try {
			this.votanteValido = false;
			this.fechaValida = false;
			ParametroPlanchaDTO parametroFechaInicial = LectorParametros
					.obtenerParametrosCodigoTipo(19L,
							CODIGO_TIPO_PARAMETRO_FECHA_ELECCION);
			ParametroPlanchaDTO parametroFechaFinal = LectorParametros
					.obtenerParametrosCodigoTipo(20L,
							CODIGO_TIPO_PARAMETRO_FECHA_ELECCION);
			Date dateToday = new Date();
			Date dateFechaIni = ManipulacionFechas.stringToDate(
					parametroFechaInicial.getStrValor(), "yyyy-MM-dd hh:mm:ss");
			Date dateFechaFin = ManipulacionFechas.stringToDate(
					parametroFechaFinal.getStrValor(), "yyyy-MM-dd hh:mm:ss");

			SimpleDateFormat formatterFecha = new SimpleDateFormat(
					"yyyy/MM/dd HH:mm");
			SimpleDateFormat formatterFecha2 = new SimpleDateFormat(
					"yyyy/MM/dd");
			this.fechaVotacion = formatterFecha2.format(dateFechaIni);

			// Se valida el rango de fechas de las votaciones:
			if ((dateToday.getTime() <  dateFechaIni.getTime()) || (dateToday.getTime() > dateFechaFin.getTime())){
				throw new EleccionesDelegadosException(
						"Está intentando registrar votantes en una fecha no permitida, "
								+ "esta acción solo es permitida entre el "
								+ formatterFecha.format(dateFechaIni)
								+ " y el "
								+ formatterFecha.format(dateFechaFin));

			}
			this.user = (UserVo) FacesUtils.getSessionParameter("user");
			// Si no hay usuario autenticado en la sesión:
			if (this.user == null) {
				throw new EleccionesDelegadosException(UtilAcceso
						.getParametroFuenteS("mensajes",
								"msgNoExisteUserSesion"));
			}
			// se obtiene el login del usuario de la sesión.
			this.usuarioRegistro = user.getUserId();
			// se consulta el usuario de la sesión para verificar que esté
			// registrado en la tabla ele_usuario_comision:
			this.consultarUsuarioComisionDTO();
			if (dtoUsuarioComision == null) {
				throw new EleccionesDelegadosException(UtilAcceso
						.getParametroFuenteS("mensajes",
								"msgNoExisteUserComision"));
			}

			this.dtoVotanteDatos = new EleVotanteDatosDTO();
			this.fechaValida = true;
			this.ipRegistro = FacesUtils.getDireccionRemota();

			this.zonaUsuarioRegistro = dtoUsuarioComision.getCodigoZonaEle();
			this.agenciaUsuarioRegistro = dtoUsuarioComision
					.getCodAgenciaUsuario();

		} catch (Exception e) {
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes",
						"nullException");
			}
			getMensaje().mostrarMensaje(mensaje);
		}
	}

	/**
	 * Método que se encarga de registrar un votante
	 * 
	 * @author Carlos Ernesto Ortega Q.
	 * @return String
	 */
	public void actionRegistar() {
		try {
			DelegadoVotante.getInstance().registrarVotante(documentoVotante,
					usuarioRegistro, zonaUsuarioRegistro,
					agenciaUsuarioRegistro, ipRegistro);
			this.votanteValido = false;
			getMensaje().mostrarMensaje(
					UtilAcceso.getParametroFuenteS("mensajes",
							"msgRegistroExitosoVotante"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes",
						"nullException");
			}
			getMensaje().mostrarMensaje(mensaje);
		}
	}

	//Método responsable de consultar si un usuario de sesión es usuario de comisión
	public void consultarUsuarioComisionDTO() {
		try {
			dtoUsuarioComision = DelegadoVotante.getInstance()
					.consultarUsuarioComision(this.usuarioRegistro);
		} catch (Exception e) {
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes",
						"nullException");
			}
			getMensaje().mostrarMensaje(mensaje);
		}
	}

	//Método que valida si un asociado puede ejercer su derecho al voto:
	public void actionValidar() {
		this.limpiarDatosVotante();
		try {
			dtoVotanteDatos = DelegadoVotante.getInstance().validarVotante(documentoVotante,
					zonaUsuarioRegistro);

			String respuesta = dtoVotanteDatos.getRespuesta();
			if (!respuesta.isEmpty()) {
				if (respuesta
						.equals(ConstantesProperties.PAR_MENSAJE_VOTANTE_INHAHABILITADO)) {
					getMensaje().mostrarMensaje(
							"El asociado de cédula " + this.documentoVotante
									+ " no tiene habilidad para votar");
				}
				if (respuesta
						.equals(ConstantesProperties.PAR_MENSAJE_VOTANTE_NO_PERTENECE_A_ZONA_VOTACION)) {
					getMensaje()
							.mostrarMensaje(
									"El asociado de cédula "
											+ dtoVotanteDatos.getDocumento()
											+ " no pertenece a la zona "
											+ dtoUsuarioComision
													.getCodigoZonaEle()
											+ "-"
											+ dtoUsuarioComision
													.getDesZonaEle()
											+ ", el asociado registra como vinculado en la zona "
											+ dtoVotanteDatos.getCodZona()
											+ "-"
											+ dtoVotanteDatos.getDesZona());
					this.limpiarDatosVotante();
				}
				if (respuesta
						.equals(ConstantesProperties.PAR_MENSAJE_VOTANTE_YA_REGISTRA_VOTACION)) {

					getMensaje()
							.mostrarMensaje(
									"El asociado de cédula "
											+ dtoVotanteDatos.getDocumento()
											+ " registra que ya ejerció el derecho al voto en la zona "
											+ dtoVotanteDatos.getCodZona()
											+ "-"
											+ dtoVotanteDatos.getDesZona()
											+ ", oficina "
											+ dtoVotanteDatos.getAgencia()
											+ ", para este proceso electoral");
					this.limpiarDatosVotante();
				}
				if (respuesta
						.equals(ConstantesProperties.PAR_MENSAJE_VOTANTE_HABILITADO_PARA_VOTAR)) {
					this.votanteValido = true;
				}
			}
		} catch (Exception e) {
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes",
						"nullException");
			}
			getMensaje().mostrarMensaje(mensaje);
		}

	}

	public String registrarVotante() {
		this.visibleConfirmarRegistrar = Boolean.FALSE;
		this.actionRegistar();
		return "";
	}

	public String actionConfirmarRegistro() {
		try {
			this.visibleConfirmarRegistrar = Boolean.TRUE;
			this.mensajeConfirmacion = "Está seguro que desea registrar el votante actual?";
		} catch (Exception e) {
			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje(e.getMessage() != null
					&& !"".equals(e.getMessage()) ? e.getMessage() : UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
							"msgErrorNullPointerException"));
		}
		return "";
	}

	public String actionCloseConfirmar() {
		this.visibleConfirmarRegistrar = Boolean.FALSE;
		return "";
	}

	public void actionLimpiar() {
		this.documentoVotante = null;
		this.limpiarDatosVotante();
		this.votanteValido = false;
	}

	public void limpiarDatosVotante() {
		this.dtoVotanteDatos.setAgencia("");
		this.dtoVotanteDatos.setCodZona("");
		this.dtoVotanteDatos.setDesZona("");
		this.dtoVotanteDatos.setDocumento("");
		this.dtoVotanteDatos.setNombres("");
		this.dtoVotanteDatos.setRespuesta("");
	}

	public boolean isActivarBotonRegistrar() {
		if (isVotanteValido())
			return Boolean.TRUE;
		else
			return Boolean.FALSE;
	}

	public boolean isActivarBotonValidar() {
		if (isFechaValida())
			return Boolean.TRUE;
		else
			return Boolean.FALSE;
	}

	public EleUsuarioComisionDTO getDtoUsuarioComision() {
		return dtoUsuarioComision;
	}

	public void setDtoUsuarioComision(EleUsuarioComisionDTO dtoUsuarioComision) {
		this.dtoUsuarioComision = dtoUsuarioComision;
	}

	public EleVotanteDatosDTO getDtoVotanteDatos() {
		return dtoVotanteDatos;
	}

	public void setDtoVotanteDatos(EleVotanteDatosDTO dtoVotanteDatos) {
		this.dtoVotanteDatos = dtoVotanteDatos;
	}

	public String getFechaVotacion() {
		return fechaVotacion;
	}

	public void setFechaVotacion(String fechaVotacion) {
		this.fechaVotacion = fechaVotacion;
	}

	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public String getZonaUsuarioRegistro() {
		return zonaUsuarioRegistro;
	}

	public void setZonaUsuarioRegistro(String zonaUsuarioRegistro) {
		this.zonaUsuarioRegistro = zonaUsuarioRegistro;
	}

	public Integer getAgenciaUsuarioRegistro() {
		return agenciaUsuarioRegistro;
	}

	public void setAgenciaUsuarioRegistro(Integer agenciaUsuarioRegistro) {
		this.agenciaUsuarioRegistro = agenciaUsuarioRegistro;
	}

	public String getIpRegistro() {
		return ipRegistro;
	}

	public void setIpRegistro(String ipRegistro) {
		this.ipRegistro = ipRegistro;
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public boolean isVotanteValido() {
		return votanteValido;
	}

	public void setVotanteValido(boolean votanteValido) {
		this.votanteValido = votanteValido;
	}

	public boolean isFechaValida() {
		return fechaValida;
	}

	public void setFechaValida(boolean fechaValida) {
		this.fechaValida = fechaValida;
	}

	public String getMensajeConfirmacion() {
		return mensajeConfirmacion;
	}

	public void setMensajeConfirmacion(String mensajeConfirmacion) {
		this.mensajeConfirmacion = mensajeConfirmacion;
	}

	public boolean isVisibleConfirmarRegistrar() {
		return visibleConfirmarRegistrar;
	}

	public void setVisibleConfirmarRegistrar(boolean visibleConfirmarRegistrar) {
		this.visibleConfirmarRegistrar = visibleConfirmarRegistrar;
	}

	public MensajesVista getMensajeVista() {
		return mensajeVista;
	}

	public void setMensajeVista(MensajesVista mensajeVista) {
		this.mensajeVista = mensajeVista;
	}

	public Long getDocumentoVotante() {
		return documentoVotante;
	}

	public void setDocumentoVotante(Long documentoVotante) {
		this.documentoVotante = documentoVotante;
	}

}