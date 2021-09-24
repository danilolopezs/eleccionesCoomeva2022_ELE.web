package co.com.coomeva.ele.backbeans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.delegado.habilidad.DelegadoNovedad;
import co.com.coomeva.ele.dto.DTOHabilidadAsociado;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.modelo.ParametroPlanchaDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.ele.util.LectorParametros;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.ManipulacionFechas;

import com.icesoft.faces.component.ext.HtmlInputTextarea;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;

/**
 * 
 * @author Carlos Ernesto Ortega Q. Contratista Coomeva 16/09/2015
 * 
 */
public class RegistrarNovedadesHabilidadVista extends BaseVista {

	private final static Long CODIGO_TIPO_PARAMETRO_FECHA_REGISTRO_NOVEDADES_HABILIDAD = new Long(
			UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"codigo.tipo.parametro.fecha.registro.novedades.habilidad"));

	
	private final static Long CODIGO_PARAMETRO_FECHA_REGISTRO_NOVEDADES_HABILIDAD = new Long(
			UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"codigo.parametro.fecha.registro.novedades.habilidad"));
	
	
	
	/**
	 * 
	 */
	private final static Long CODIGO_TIPO_PARAMETRO_FECHA_INICIAL_REGISTRO_NOVEDADES_HABILIDAD = new Long(
			UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"codigo.parametro.fecha.inicial.registro.novedades.habilidad"));

	
	private final static Long CODIGO_PARAMETRO_FECHA_FINAL_REGISTRO_NOVEDADES_HABILIDAD = new Long(
			UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"codigo.parametro.fecha.final.registro.novedades.habilidad"));
					
	
	
	
	private final static Long CODIGO_TIPO_PARAMETRO_LISTA__CORREOS_NOTIFICACION_CAMBIO_HABILIDAD = new Long(
			UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
							"codigo.tipo.parametro.lista.correos.notificacion.cambio.habilidad"));

	private Long documento;
	private HtmlInputTextarea observaciones;
	private String nombres;
	private String usuarioRegistro;
	private DTOHabilidadAsociado dtoHabilidadAsociado;
	private UserVo user;
	private boolean modificaHabilidad;
	private Integer oldHabilidad;
	private boolean fechaValida;
	private String mensajeConfirmacion = "";
	private boolean visibleConfirmarRegistrar;
	private MensajesVista mensajeVista = new MensajesVista();
	private HtmlSelectOneMenu selHabilidadAsociado;
	private List<SelectItem> itemsHabilidadAsociado;
	private static DelegadoAsociado delegadoAsociado;
	private static DelegadoNovedad delegadoNovedad;

	public RegistrarNovedadesHabilidadVista() {
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

			delegadoAsociado = DelegadoAsociado.getInstance();
			delegadoNovedad = DelegadoNovedad.getInstance();
			this.modificaHabilidad = false;
			this.fechaValida = false;
			this.nombres = "";
			this.itemsHabilidadAsociado = null;
			this.selHabilidadAsociado = null;
			this.oldHabilidad = null;

			this.user = (UserVo) FacesUtils.getSessionParameter("user");
			// Si no hay usuario autenticado en la sesión:
			if (this.user == null) {
				throw new EleccionesDelegadosException(UtilAcceso
						.getParametroFuenteS("mensajes",
								"msgNoExisteUserSesion"));
			}
			// se obtiene el login del usuario de la sesión.
			this.usuarioRegistro = user.getUserId();

			dtoHabilidadAsociado = new DTOHabilidadAsociado();
			ParametroPlanchaDTO parametroFechaInicial = LectorParametros
					.obtenerParametrosCodigoTipo(CODIGO_TIPO_PARAMETRO_FECHA_INICIAL_REGISTRO_NOVEDADES_HABILIDAD,
							CODIGO_TIPO_PARAMETRO_FECHA_REGISTRO_NOVEDADES_HABILIDAD);
			ParametroPlanchaDTO parametroFechaFinal = LectorParametros
					.obtenerParametrosCodigoTipo(CODIGO_PARAMETRO_FECHA_FINAL_REGISTRO_NOVEDADES_HABILIDAD,
							CODIGO_TIPO_PARAMETRO_FECHA_REGISTRO_NOVEDADES_HABILIDAD);
			Date dateToday = new Date();
			Date dateFechaIni = ManipulacionFechas.stringToDate(
					parametroFechaInicial.getStrValor(), "yyyy-MM-dd");
			Date dateFechaFin = ManipulacionFechas.stringToDate(
					parametroFechaFinal.getStrValor(), "yyyy-MM-dd");

			SimpleDateFormat formatterFecha = new SimpleDateFormat(
					"yyyy/MM/dd HH:mm");

			// Se valida el rango de fechas para subsanar inhabilidades:
			if ((dateToday.getTime() < dateFechaIni.getTime())
					|| (dateToday.getTime() > dateFechaFin.getTime())) {
				throw new EleccionesDelegadosException(
						"No se encuentra en las fechas establecidas para subsanar inhabilidades, "
								+ "esta acción solo es permitida entre el "
								+ formatterFecha.format(dateFechaIni)
								+ " y el "
								+ formatterFecha.format(dateFechaFin));

			}
			this.fechaValida = true;

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
	 * Método que se encarga de registrar novedades de habilidad
	 * 
	 * @author Carlos Ernesto Ortega Q.
	 * @return String
	 */
	public void actionRegistrar() {
		this.visibleConfirmarRegistrar = Boolean.FALSE;
		try {
			delegadoNovedad.registrarNovedad(selHabilidadAsociado.getValue()
					.toString(), String.valueOf(documento), observaciones
					.getValue().toString(), this.usuarioRegistro);
			String asunto = "Registro de novedades de habilidad";
			String habilidadAnterior = this
					.descripcionEstadosHabilidad(this.oldHabilidad);
			String habilidadNueva = this.descripcionEstadosHabilidad(Integer
					.parseInt(this.selHabilidadAsociado.getValue().toString()));
			String msg = "El asociado(a)  "
					+ this.dtoHabilidadAsociado.getNombreAsociado() + " con CC. "
					+ this.documento + " ha cambiado de estado " + habilidadAnterior.replace("á", "a") + " a "
					+ habilidadNueva.replace("á", "a");
			this.notificarCambioHabilidad(asunto, msg);
			this.actionLimpiar();			
			getMensaje().mostrarMensaje(
					UtilAcceso.getParametroFuenteS("mensajes",
							"msgRegistroExitosoNovedadHabilidad"));			
			
		} catch (Exception e) {
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes",
						"nullException");
			}
			//this.actionLimpiar();
			getMensaje().mostrarMensaje(mensaje);
		}		
	}

	// Método que consulta la habilidad de un asociado:
	public void actionConsultarHabilidadAsociado() {
		this.modificaHabilidad = false;
		this.nombres = "";
		this.observaciones.setValue("");
		this.oldHabilidad = null;
		try {

			dtoHabilidadAsociado = delegadoAsociado
					.consultarHabilidadAsociado(documento);
			if (dtoHabilidadAsociado == null) {
				throw new Exception(
						"Señor usuario el número de documento no corresponde a un asociado");
			}
			this.cargarEstadosHabilidadAsociado();
			if (dtoHabilidadAsociado.getAsociadoHabil()) {
				this.oldHabilidad = 1;
				this.seleccionarEstadoHabilidad(1);
			} else {
				this.oldHabilidad = 0;
				this.seleccionarEstadoHabilidad(0);
			}
			dtoHabilidadAsociado.setNombreAsociado(delegadoAsociado
					.consultarNombreAsociado(documento));
			dtoHabilidadAsociado.setDocumento(documento.toString());
			this.nombres = dtoHabilidadAsociado.getNombreAsociado();
		} catch (Exception e) {
			String mensaje = e.getMessage();
			if (mensaje == null || mensaje.equalsIgnoreCase("")) {
				mensaje = UtilAcceso.getParametroFuenteS("mensajes",
						"nullException");
			}
			getMensaje().mostrarMensaje(mensaje);
		}

	}

	public String registrarNovedad() {
		this.visibleConfirmarRegistrar = Boolean.FALSE;
		this.actionRegistrar();
		return "";
	}

	public String actionConfirmarRegistro() {
		try {
			this.visibleConfirmarRegistrar = Boolean.TRUE;
			this.mensajeConfirmacion = "Está seguro que desea registrar la novedad de habilidad?";
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
		this.documento = null;
		this.observaciones.setValue("");
		this.nombres = "";
		this.modificaHabilidad = false;
		this.itemsHabilidadAsociado = null;
		this.selHabilidadAsociado = null;
		this.oldHabilidad = null;
		if (dtoHabilidadAsociado != null) {
			dtoHabilidadAsociado.setDocumento("");
			dtoHabilidadAsociado.setNombreAsociado("");
		}
	}

	public boolean isActivarBotonConsultar() {
		if (isFechaValida())
			return Boolean.TRUE;
		else
			return Boolean.FALSE;
	}

	public DTOHabilidadAsociado getDtoHabilidadAsociado() {
		return dtoHabilidadAsociado;
	}

	public void setDtoHabilidadAsociado(
			DTOHabilidadAsociado dtoHabilidadAsociado) {
		this.dtoHabilidadAsociado = dtoHabilidadAsociado;
	}

	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public boolean isModificaHabilidad() {
		return modificaHabilidad;
	}

	public void setModificaHabilidad(boolean modificaHabilidad) {
		this.modificaHabilidad = modificaHabilidad;
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

	public Long getDocumento() {
		return documento;
	}

	public void setDocumento(Long documento) {
		this.documento = documento;
	}

	public HtmlInputTextarea getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(HtmlInputTextarea observaciones) {
		this.observaciones = observaciones;
	}

	public HtmlSelectOneMenu getSelHabilidadAsociado() {
		return selHabilidadAsociado;
	}

	public void setSelHabilidadAsociado(HtmlSelectOneMenu selHabilidadAsociado) {
		this.selHabilidadAsociado = selHabilidadAsociado;
	}

	public List<SelectItem> getItemsHabilidadAsociado() {
		return itemsHabilidadAsociado;
	}

	public void setItemsHabilidadAsociado(
			List<SelectItem> itemsHabilidadAsociado) {
		this.itemsHabilidadAsociado = itemsHabilidadAsociado;
	}

	public void cargarEstadosHabilidadAsociado() {
		itemsHabilidadAsociado = new ArrayList<SelectItem>();
		try {
			itemsHabilidadAsociado.add(new SelectItem("0", "No hábil"));
			itemsHabilidadAsociado.add(new SelectItem("1", "Hábil"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void seleccionarEstadoHabilidad(Integer indice) {
		this.selHabilidadAsociado.setValue(indice);
	}

	public void estadoHabilidadChangeListener(ValueChangeEvent event) {

		try {
			if (this.oldHabilidad != null) {
				if (event.getNewValue().toString().equals(
						this.oldHabilidad.toString())) {
					this.modificaHabilidad = false;
					this.observaciones.setValue("");
				} else
					this.modificaHabilidad = true;
			}
		} catch (Exception e) {
			getMensaje().mostrarMensaje(e.getMessage());
		}
	}

	public void notificarCambioHabilidad(String asunto, String mensaje)
			throws Exception {
		delegadoNovedad
				.notificarCambioHabilidad(asunto, mensaje,
						CODIGO_TIPO_PARAMETRO_LISTA__CORREOS_NOTIFICACION_CAMBIO_HABILIDAD);
	}

	public String descripcionEstadosHabilidad(Integer ind) {
		if (ind == 0)
			return "No hábil";
		if (ind == 1)
			return "Hábil";
		return "";
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

}