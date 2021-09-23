package co.com.coomeva.ele.backbeans;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import co.com.coomeva.ele.delegado.DelegadoParametros;
import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoPlancha;
import co.com.coomeva.ele.dto.DTOInformacionPlancha;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.modelo.CabezaPlanchaDTO;
import co.com.coomeva.ele.modelo.InfoPlanchaDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.resources.LoaderResourceElements;

import com.icesoft.faces.component.ext.HtmlInputText;

/**
 * Clase que controla las acciones sobre la pantalla Marcar plancha en recurso.
 * 
 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
 *         Pragma S.A. <br>
 * @project ELE.web
 * @class MarcarPlanchaRecursoVista
 * @date 7/12/2012
 * 
 */
public class MarcarPlanchaRecursoVista extends BaseVista {

	public static final String COD_ESTADO_PLANCHA_EN_RECURSO = UtilAcceso
			.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_ESTADO_PLANCHA_EN_RECURSO);

	public static final String COD_ESTADO_PLANCHA_RECHAZADA = UtilAcceso
			.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_ESTADO_PLANCHA_RECHAZADA);

	public static final String COD_ESTADO_PLANCHA_INADMITIDA = UtilAcceso
			.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_ESTADO_PLANCHA_INADMITIDA);

	public static final String COD_ESTADO_PLANCHA_ADMITIDA = UtilAcceso
			.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_ESTADO_PLANCHA_ADMITIDA);

	private List<CabezaPlanchaDTO> integrantes = new ArrayList<CabezaPlanchaDTO>();
	private HtmlInputText txtNumDoc;

	private Long numCedula;
	private boolean muestraInfoPlancha = false;
	private InfoPlanchaDTO infoPlancha = new InfoPlanchaDTO();
	private Long numPlancha;
	private MensajesVista mensajeVista = new MensajesVista();
	private UserVo usuarioSesion;

	private String mensajeConfirmacion = "";
	private boolean visibleConfirmar = false;
	private boolean visibleExcepcion = false;

	public MarcarPlanchaRecursoVista() {
		usuarioSesion = (UserVo) FacesUtils.getSessionParameter("user");
	}

	public void limpiarCampos() {
		numCedula = null;
		muestraInfoPlancha = false;
		numPlancha = null;
	}

	public void action_consultar() {
		try {
			DelegadoPlanchas.getInstance().validarFechaMarcarPlanchaRecurso();

			if (numCedula == null) {
				throw new Exception(
						"Debe ingresar el número de cédula del asociado cabeza de plancha");
			}

			UserVo user = (UserVo) FacesUtils.getSessionParameter("user");
			infoPlancha = DelegadoPlanchas.getInstance().obtenerInfoPlancha(
					numCedula, user.getUserId());
			if (infoPlancha.getNumPlancha() != null) {
				muestraInfoPlancha = true;
				numPlancha = new Long(infoPlancha.getNumPlancha());
			} else {
				limpiarCampos();
				throw new Exception(
						"El documento ingresado no corresponde a ningún Cabeza de Plancha");
			}

			integrantes = DelegadoPlanchas.getInstance()
					.obtenerIntegrantesCabezaPlanchaPrincSinPag(numPlancha);
		} catch (Exception e) {
			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje(e.getMessage());

			// getMensaje().mostrarMensaje(e.getMessage());
		}
	}

	public String action_marcar() {
		try {
			if (numPlancha == null) {
				throw new Exception(
						"Debe ingresar el número de cédula del asociado cabeza de plancha");
			}

			Long consecutivoPlancha = infoPlancha.getConsecutivoPlancha();

			DTOInformacionPlancha infoPlancha = DelegadoPlancha.getInstance()
					.consultarInformacionPlancha(consecutivoPlancha);

			if (COD_ESTADO_PLANCHA_EN_RECURSO.equals(infoPlancha
					.getEstadoPlancha())) {
				throw new EleccionesDelegadosException(
						LoaderResourceElements
								.getInstance()
								.getKeyResourceValue(
										ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
										ConstantesProperties.MENSAJE_PLANCHA_YA_ESTA_EN_RECURSO));
			}
			if (COD_ESTADO_PLANCHA_RECHAZADA.equals(infoPlancha
					.getEstadoPlancha())					
					|| COD_ESTADO_PLANCHA_ADMITIDA.equals(infoPlancha
							.getEstadoPlancha())) {
				DelegadoPlanchas.getInstance().actualizarEstadoPlancha(
						numPlancha, COD_ESTADO_PLANCHA_EN_RECURSO,
						usuarioSesion.getUserId());

				mensajeConfirmacion = MessageFormat
						.format(
								LoaderResourceElements
										.getInstance()
										.getKeyResourceValue(
												ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
												ConstantesProperties.MENSAJE_CONFIRMACION_PLANCHA_RECURSO),
								numPlancha);

				action_mostrar_mensaje_confirmacion();
			} else {
				throw new EleccionesDelegadosException(
						LoaderResourceElements
								.getInstance()
								.getKeyResourceValue(
										ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
										ConstantesProperties.MENSAJE_PLANCHA_NO_PUEDE_ESTAR_EN_RECURSO));
			}

		} catch (Exception e) {
			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje(e.getMessage());

			// getMensaje().mostrarMensaje(e.getMessage());
		}
		return "";
	}

	public String action_mostrar_mensaje_confirmacion() {
		visibleConfirmar = true;
		return "";
	}

	public String action_cerrar_mensaje_confirmacion() {
		visibleConfirmar = false;
		limpiarCampos();
		return "";
	}

	public Long getNumCedula() {
		return numCedula;
	}

	public void setNumCedula(Long numCedula) {
		this.numCedula = numCedula;
	}

	public InfoPlanchaDTO getInfoPlancha() {
		return infoPlancha;
	}

	public void setInfoPlancha(InfoPlanchaDTO infoPlancha) {
		this.infoPlancha = infoPlancha;
	}

	public boolean isMuestraInfoPlancha() {
		return muestraInfoPlancha;
	}

	public void setMuestraInfoPlancha(boolean muestraInfoPlancha) {
		this.muestraInfoPlancha = muestraInfoPlancha;
	}

	public Long getNumPlancha() {
		return numPlancha;
	}

	public void setNumPlancha(Long numPlancha) {
		this.numPlancha = numPlancha;
	}

	public List<CabezaPlanchaDTO> getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(List<CabezaPlanchaDTO> integrantes) {
		this.integrantes = integrantes;
	}

	public boolean isVisibleConfirmar() {
		return visibleConfirmar;
	}

	public void setVisibleConfirmar(boolean visibleConfirmar) {
		this.visibleConfirmar = visibleConfirmar;
	}

	public HtmlInputText getTxtNumDoc() {
		return txtNumDoc;
	}

	public void setTxtNumDoc(HtmlInputText txtNumDoc) {
		this.txtNumDoc = txtNumDoc;
	}

	public boolean isVisibleExcepcion() {
		return visibleExcepcion;
	}

	public void setVisibleExcepcion(boolean visibleExcepcion) {
		this.visibleExcepcion = visibleExcepcion;
	}

	public String getMensajeConfirmacion() {
		return mensajeConfirmacion;
	}

	public void setMensajeConfirmacion(String mensajeConfirmacion) {
		this.mensajeConfirmacion = mensajeConfirmacion;
	}

	public MensajesVista getMensajeVista() {
		return mensajeVista;
	}

	public void setMensajeVista(MensajesVista mensajeVista) {
		this.mensajeVista = mensajeVista;
	}

}