package co.com.coomeva.ele.backbeans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import co.com.coomeva.ele.delegado.DelegadoClimae;
import co.com.coomeva.ele.delegado.DelegadoFormatoPlanchas;
import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.delegado.formulario.DelegadoRegistroFormulario;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoFormatoPlancha;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoPlancha;
import co.com.coomeva.ele.dto.DTOInformacionPlancha;
import co.com.coomeva.ele.dto.DTOMiembroPlancha;
import co.com.coomeva.ele.dto.DTOPlanchaAsociado;
import co.com.coomeva.ele.dto.DTOZonaElectoral;
import co.com.coomeva.ele.entidades.formulario.EleRegistroCampos;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.formatos.pdf.inscripcion.plancha.FormatoPdfInscripcionPlancha;
import co.com.coomeva.ele.modelo.EleAsociadoDTO;
import co.com.coomeva.ele.modelo.ParametroPlanchaDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.ele.util.LectorParametros;
import co.com.coomeva.ele.util.WorkStrigs;
import co.com.coomeva.habilidad.utilidades.Constantes;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.ManipulacionFechas;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.context.effects.JavascriptContext;

/**
 * Managedbean para el registro de los integrantes de una plancha
 * 
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londo�o</a>
 *         Premize SAS <br>
 * @project ELE.web
 * @class RegistrarPlancha
 * @date 30/11/2012
 * @coAuthor GTC CORPORATION - Danilo L�pez Sandoval
 * @dateRefactor 27/10/2021
 */
public class RegistrarPlancha extends BaseVista {

	Logger log = Logger.getLogger(RegistrarPlancha.class);

	public static final String COD_ESTADO_PLANCHA_REGISTRADA = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
			ConstantesProperties.CODIGO_ESTADO_PLANCHA_REGISTRADA);

	public static final String COD_ESTADO_PLANCHA_MODIFICADA = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
			ConstantesProperties.CODIGO_ESTADO_PLANCHA_MODIFICADA);

	public static final String COD_ESTADO_PLANCHA_RECHAZADA = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
			ConstantesProperties.CODIGO_ESTADO_PLANCHA_RECHAZADA);

	public static final String COD_ESTADO_PLANCHA_INADMITIDA = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
			ConstantesProperties.CODIGO_ESTADO_PLANCHA_INADMITIDA);

	public static final String COD_ESTADO_PLANCHA_EN_RECURSO = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
			ConstantesProperties.CODIGO_ESTADO_PLANCHA_EN_RECURSO);

	public static final String COD_ESTADO_PLANCHA_INSCRITA = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
			ConstantesProperties.CODIGO_ESTADO_PLANCHA_INSCRITA);

	public static final String TIPO_INSCRITO_TITULAR = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "param.tipo.inscrito.asociado.titular");

	public static final String TIPO_INSCRITO_SUPLENTE = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "param.tipo.inscrito.asociado.suplente");

	private static final Long CODIGO_FORMATO_INSCRIPCION_PLANCHA = new Long(
			UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_FORMATO_INSCRIPCION_PLANCHA));

	private static final String MSJ_INFO_CEDULA = "Se�or usuario, por favor digite la siguiente informaci�n correspondiente a la C�dula de Ciudadania n�mero: ";
	
	private String numeroZonaElectoral;
	private List<DTOMiembroPlancha> miembrosPrincipales = new ArrayList<DTOMiembroPlancha>();
	private List<DTOMiembroPlancha> miembrosSuplentes = new ArrayList<DTOMiembroPlancha>();
	private List<ParametroPlanchaDTO> estadosPlanchaModificables;
	private MensajesVista mensajeVista = new MensajesVista();
	private boolean visibleConfirmar;
	private Long numeroDocumentoUserEnSesion;
	private Long consecutivoPlancha;
	public String estadoPlancha;
	private String mensajeConfirmacion = "";
	private boolean visibleConfirmarEnviar;
	private boolean visibleConfirmarImprimir;

	private boolean mostrarPopupExcepciones;
	private String mensajePopupExcepciones;

	private FormatoPdfInscripcionPlancha formatoPdfInscripcionPlancha = new FormatoPdfInscripcionPlancha();
	private String tipoEleccionesSession;
	private String tipoEleccionesRepresentantes;

	// atributo para indicar que se deben aplicar validaciones sobre elecciones de
	// representantes �nicamente
	private boolean aplicaValidaciones;
	// Verifica si el usuario de la sesi�n es de la comisi�n
	private boolean esUsuarioComision;
	private boolean esSaneamiento;

	// variable usada para la modificacion de la plancha en estado inscirta y dentro
	// de las fechas de modificaci�n
	private boolean esModificable;
	
	private boolean visibleInfoReporte211;
	private String mensajeInfoCedula;
	private String lugExpCedula;
	private String ciudadUbicacion;
	private Date fechaFirma;
	private EleAsociadoDTO asociadoDTO;

	public RegistrarPlancha() {
		log.info("Leega plancha");
		List<DTOPlanchaAsociado> planchaAsociado = null;
		try {
			this.esUsuarioComision = FacesUtils.getSessionParameter("userComision") != null;
			this.tipoEleccionesSession = (String) FacesUtils.getSessionParameter("tipoElecciones");
			this.tipoEleccionesRepresentantes = UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "param.tipo.elecciones.representantes");
			this.aplicaValidaciones = !tipoEleccionesRepresentantes.equals(tipoEleccionesSession);

			if (FacesUtils.getSessionParameter("numeroDocAsociado") != null) {
				numeroDocumentoUserEnSesion = (Long) FacesUtils.getSessionParameter("numeroDocAsociado");
				planchaAsociado = DelegadoPlancha.getInstance().asociadoPertenceOtraPlancha(numeroDocumentoUserEnSesion,
						this.consecutivoPlancha);
			}

			if (planchaAsociado != null && !planchaAsociado.isEmpty()) {
				DTOInformacionPlancha infoPlancha = DelegadoPlancha.getInstance()
						.consultarInformacionPlancha(planchaAsociado.get(0).getConsecutivoPlancha());

				consecutivoPlancha = infoPlancha.getConsecutivoPlancha();

				FacesUtils.setSessionParameter("consecutivoPlancha", consecutivoPlancha);
				estadoPlancha = infoPlancha.getEstadoPlancha();

				this.miembrosPrincipales = llenarDatatableMiembros(this.miembrosPrincipales,
						infoPlancha.getMiembrosTitulares(), TIPO_INSCRITO_TITULAR);
				this.miembrosSuplentes = llenarDatatableMiembros(this.miembrosSuplentes,
						infoPlancha.getMiembrosSuplentes(), TIPO_INSCRITO_SUPLENTE);

			} else {
				for (int i = 0; i < Constantes.CANTIDAD_REGISTROS_POR_DEFECTO_PLANCHA; i++) {
					DTOMiembroPlancha a = new DTOMiembroPlancha();
					a.setPosicionPlancha("" + (i + 1));
					a.setTipoMiembro(TIPO_INSCRITO_TITULAR);
					this.miembrosPrincipales.add(a);

					DTOMiembroPlancha b = new DTOMiembroPlancha();
					b.setPosicionPlancha("" + (i + 1));
					b.setTipoMiembro(TIPO_INSCRITO_SUPLENTE);
					this.miembrosSuplentes.add(b);
				}
			}

			if (!COD_ESTADO_PLANCHA_REGISTRADA.equals(this.estadoPlancha)
					&& !COD_ESTADO_PLANCHA_RECHAZADA.equals(this.estadoPlancha)
					&& !COD_ESTADO_PLANCHA_INSCRITA.equals(this.estadoPlancha)
					&& !COD_ESTADO_PLANCHA_INADMITIDA.equals(this.estadoPlancha)
					&& !COD_ESTADO_PLANCHA_EN_RECURSO.equals(this.estadoPlancha)) {
				this.mensajeVista.setVisible(Boolean.TRUE);
				this.mensajeVista.setMensaje(UtilAcceso
						.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES, "msgInicioRegistroPlancha"));
			}

			if (validarSaneamiento()) {
				this.esSaneamiento = true;
			} else {
				this.esSaneamiento = false;
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje("Se presento un error Inesperado");
		}
	}

	private List<DTOMiembroPlancha> llenarDatatableMiembros(List<DTOMiembroPlancha> listaMiembrosDestino,
			List<DTOMiembroPlancha> listaMiembrosOrigen, String tipoMiembro) {

		if (listaMiembrosOrigen == null || listaMiembrosOrigen.isEmpty()) {

			for (int i = 0; i < Constantes.CANTIDAD_REGISTROS_POR_DEFECTO_PLANCHA; i++) {
				DTOMiembroPlancha miembro = new DTOMiembroPlancha();
				miembro.setPosicionPlancha(String.valueOf(i + 1));
				miembro.setTipoMiembro(tipoMiembro);
				listaMiembrosDestino.add(miembro);
			}
			return listaMiembrosDestino;
		}

		Long mayorNumeroPlancha = Long
				.parseLong(listaMiembrosOrigen.get(listaMiembrosOrigen.size() - 1).getPosicionPlancha());
		DTOZonaElectoral zonaElectoral = null;
		try {
			zonaElectoral = DelegadoPlancha.getInstance()
					.consultarZonaElectoralAsociado(this.numeroDocumentoUserEnSesion.toString());
		} catch (EleccionesDelegadosException e) {
		}

		DTOMiembroPlancha[] arregloMiembros = null;
		if (mayorNumeroPlancha.intValue() == zonaElectoral.getMaxPrincipales().intValue()) {
			arregloMiembros = new DTOMiembroPlancha[mayorNumeroPlancha.intValue()];
		} else {
			arregloMiembros = new DTOMiembroPlancha[mayorNumeroPlancha.intValue() + 1];
		}

		for (int i = 0; i < arregloMiembros.length; i++) {

			if (i < listaMiembrosOrigen.size()) {
				arregloMiembros[Integer.parseInt(listaMiembrosOrigen.get(i).getPosicionPlancha())
						- 1] = listaMiembrosOrigen.get(i);
			}

			if (arregloMiembros[i] == null) {
				DTOMiembroPlancha miembro = new DTOMiembroPlancha();
				miembro.setPosicionPlancha(String.valueOf(i + 1));
				miembro.setTipoMiembro(tipoMiembro);
				arregloMiembros[i] = miembro;
			}
		}

		listaMiembrosDestino = new ArrayList<DTOMiembroPlancha>(Arrays.asList(arregloMiembros));

		return listaMiembrosDestino;
	}

	/**
	 * Se realiza la validaci\u00F3n de asociados para ingresar un miembro principal
	 * @param v
	 */
	public void adicionarMiembroPrincipal(ValueChangeEvent v) {
		HtmlInputText inputTextCedula = (HtmlInputText) v.getComponent();

		if (v.getNewValue() != null && !"".equals(v.getNewValue().toString().trim())) {

			try {
				Long numeroDocumento = Long.parseLong(v.getNewValue().toString());

				this.miembrosPrincipales = adicionarMiembro(this.miembrosPrincipales, numeroDocumento,
						Integer.parseInt(inputTextCedula.getAlt()));

				String observacion = this.miembrosPrincipales.get(Integer.parseInt(inputTextCedula.getAlt()) - 1)
						.getObservacionAdicionMiembro();

				if (observacion != null && !"".equals(observacion)) {
					this.mensajeVista.setVisible(Boolean.TRUE);
					this.mensajeVista.setMensaje(observacion);
				}
			} catch (EleccionesDelegadosException e) {
				this.mensajeVista.setVisible(Boolean.TRUE);
				this.mensajeVista.setMensaje(e.getMessage() != null && !"".equals(e.getMessage()) ? e.getMessage()
						: UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
								"msgErrorNullPointerException"));
				this.miembrosPrincipales.get(Integer.parseInt(inputTextCedula.getAlt()) - 1).setNumeroDocumento(null);
				this.miembrosPrincipales.get(Integer.parseInt(inputTextCedula.getAlt()) - 1).setApellidosNombres(null);
				this.miembrosPrincipales.get(Integer.parseInt(inputTextCedula.getAlt()) - 1).setProfesion(null);
			}
		} else {
			this.miembrosPrincipales.get(Integer.parseInt(inputTextCedula.getAlt()) - 1).setNumeroDocumento(null);
			this.miembrosPrincipales.get(Integer.parseInt(inputTextCedula.getAlt()) - 1).setApellidosNombres(null);
			this.miembrosPrincipales.get(Integer.parseInt(inputTextCedula.getAlt()) - 1).setProfesion(null);
		}
	}

	/**
	 * metodo que recibe un evento de cambio de estado en el valor del componente
	 * desde donde se invoca. adicionar o modificar el numero de documento de un
	 * suplente
	 * @param v event
	 */
	public void adicionarMiembroSuplente(ValueChangeEvent v) {
		HtmlInputText inputTextCedula = (HtmlInputText) v.getComponent();

		if (v.getNewValue() != null && !"".equals(v.getNewValue().toString().trim())) {
			try {

				Long numeroDocumento = Long.parseLong(v.getNewValue().toString());

				this.miembrosSuplentes = adicionarMiembro(this.miembrosSuplentes, numeroDocumento,
						Integer.parseInt(inputTextCedula.getAlt()));

				String observacion = this.miembrosSuplentes.get(Integer.parseInt(inputTextCedula.getAlt()) - 1)
						.getObservacionAdicionMiembro();

				if (observacion != null && !"".equals(observacion)) {
					this.mensajeVista.setVisible(Boolean.TRUE);
					this.mensajeVista.setMensaje(observacion);
				}
			} catch (EleccionesDelegadosException e) {
				this.mensajeVista.setVisible(Boolean.TRUE);
				this.mensajeVista.setMensaje(e.getMessage() != null && !"".equals(e.getMessage()) ? e.getMessage()
						: UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
								"msgErrorNullPointerException"));
				this.miembrosSuplentes.get(Integer.parseInt(inputTextCedula.getAlt()) - 1).setNumeroDocumento(null);
				this.miembrosSuplentes.get(Integer.parseInt(inputTextCedula.getAlt()) - 1).setApellidosNombres(null);
				this.miembrosSuplentes.get(Integer.parseInt(inputTextCedula.getAlt()) - 1).setProfesion(null);
			}
		} else {
			this.miembrosSuplentes.get(Integer.parseInt(inputTextCedula.getAlt()) - 1).setNumeroDocumento(null);
			this.miembrosSuplentes.get(Integer.parseInt(inputTextCedula.getAlt()) - 1).setApellidosNombres(null);
			this.miembrosSuplentes.get(Integer.parseInt(inputTextCedula.getAlt()) - 1).setProfesion(null);
		}
	}

	private List<DTOMiembroPlancha> adicionarMiembro(List<DTOMiembroPlancha> lista, Long numeroDocumento, int posicion)
			throws EleccionesDelegadosException {

		lista = DelegadoPlancha.getInstance().adicionarMiembroPlancha(lista, numeroDocumento, posicion,
				this.consecutivoPlancha, this.aplicaValidaciones);
		DTOZonaElectoral zonaElectoral = DelegadoPlancha.getInstance()
				.consultarZonaElectoralAsociado(this.numeroDocumentoUserEnSesion.toString());
		if (zonaElectoral.getMaxPrincipales() != null) {
			if (lista.size() < zonaElectoral.getMaxPrincipales().intValue()) {
				DTOMiembroPlancha miembro = new DTOMiembroPlancha();
				miembro.setPosicionPlancha(String.valueOf((lista.size() + 1)));
				lista.add(miembro);
			}
		}

		return lista;
	}

	public String actionConfirmarRegistrarPlancha() {
		this.visibleConfirmar = Boolean.TRUE;
		this.mensajeConfirmacion = "�Esta seguro que desea registrar la plancha?";
		return "";
	}

	public String registrarPlancha() {

		try {
			this.visibleConfirmar = Boolean.FALSE;

			boolean usuarioQueRegistraEsTitular = Boolean.FALSE;

			List<DTOMiembroPlancha> listaRealMiembrosTitulares = obtenerListaRealMiembros(miembrosPrincipales);
			List<DTOMiembroPlancha> listaRealMiembrosSuplentes = obtenerListaRealMiembros(miembrosSuplentes);

			if (miembrosPrincipales != null && !miembrosPrincipales.isEmpty()) {
				for (DTOMiembroPlancha dtoMiembroPlancha : miembrosPrincipales) {
					if (dtoMiembroPlancha.getNumeroDocumento() != null
							&& dtoMiembroPlancha.getNumeroDocumento().equals(numeroDocumentoUserEnSesion)
							&& dtoMiembroPlancha.getPosicionPlancha() != null
							&& dtoMiembroPlancha.getPosicionPlancha().equals("1")) {
						usuarioQueRegistraEsTitular = Boolean.TRUE;
						break;
					}
				}

				if (!usuarioQueRegistraEsTitular) {
					throw new EleccionesDelegadosException("Estimado Asociado, recuerde que el "
							+ "formulario debe ser diligenciado �nicamente por el cabeza de la plancha.");
				}
			}
			// validar si se puede modificar la plancha:
			DTOZonaElectoral zonaElectoral = DelegadoPlancha.getInstance()
					.consultarZonaElectoralAsociado(numeroDocumentoUserEnSesion.toString());
			if (zonaElectoral.getMaxPrincipales() != null) {
				int maxPrincipales = Integer.parseInt(zonaElectoral.getMaxPrincipales().toString());
				if (listaRealMiembrosTitulares.size() > maxPrincipales) {
					throw new EleccionesDelegadosException("La plancha excede el m�ximo de principales para esta zona. "
							+ "El m�ximo de principales para esta zona es de " + maxPrincipales + ".");
				}
			}

			if (this.consecutivoPlancha == null) {

				DTOInformacionPlancha dtoInfoPlancha = DelegadoPlancha.getInstance().registrarPlancha(
						this.miembrosPrincipales, this.miembrosSuplentes, this.numeroDocumentoUserEnSesion,
						this.aplicaValidaciones);

				this.estadoPlancha = dtoInfoPlancha.getEstadoPlancha();
				this.consecutivoPlancha = dtoInfoPlancha.getConsecutivoPlancha();

				if (dtoInfoPlancha.getDescripcionExcepciones() != null
						&& !"".equals(dtoInfoPlancha.getDescripcionExcepciones())) {
					this.mostrarPopupExcepciones = Boolean.TRUE;
					this.mensajePopupExcepciones = dtoInfoPlancha.getDescripcionExcepciones();
				} else {
					this.mensajeVista.setVisible(Boolean.TRUE);
				}

			} else {

				if ((listaRealMiembrosTitulares != null && listaRealMiembrosSuplentes != null)
						&& (listaRealMiembrosSuplentes.size() > listaRealMiembrosTitulares.size())) {
					throw new EleccionesDelegadosException(
							"Estimado Asociado, recuerde que el no. de suplentes debe ser igual o inferior al no. de principales inscritos.");
				}

				this.estadoPlancha = DelegadoPlancha.getInstance().modificarPlancha(this.miembrosPrincipales,
						TIPO_INSCRITO_TITULAR, this.consecutivoPlancha, esModificable);

				if (!tipoEleccionesRepresentantes.equals(tipoEleccionesSession)) {
					this.estadoPlancha = DelegadoPlancha.getInstance().modificarPlancha(this.miembrosSuplentes,
							TIPO_INSCRITO_SUPLENTE, this.consecutivoPlancha, esModificable);
				}

				this.mensajeVista.setVisible(Boolean.TRUE);
			}

			// this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje("Se guardaron los datos del formulario. Recuerde "
					+ "que puede modificarlo mientras no se expida el formato de Constancia de Radicaci�n y Recibo.");
		} catch (EleccionesDelegadosException e) {
			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje(e.getMessage());
		} catch (Exception e) {
			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje("Se presento un error Inesperado.");
			e.printStackTrace();
		}
		return "";
	}

	private List<DTOMiembroPlancha> obtenerListaRealMiembros(List<DTOMiembroPlancha> miembros) {
		List<DTOMiembroPlancha> listaReal = new ArrayList<DTOMiembroPlancha>();

		for (DTOMiembroPlancha dtoMiembroPlancha : miembros) {
			if (dtoMiembroPlancha.getApellidosNombres() != null && !dtoMiembroPlancha.getApellidosNombres().isEmpty()) {
				listaReal.add(dtoMiembroPlancha);
			}
		}
		return listaReal;
	}

	public String actionConfirmarFinalizarEnviarPlancha() {
		this.visibleConfirmarEnviar = Boolean.TRUE;
		this.mensajeConfirmacion = "�Esta seguro que desea finalizar y enviar la plancha?";
		return "";
	}

	public String actionFinalizarEnviarPlancha() {
		boolean admiteSuplentes = false;
		try {
			this.visibleConfirmarEnviar = Boolean.FALSE;
			DTOZonaElectoral zonaElectoral = DelegadoPlancha.getInstance()
					.consultarZonaElectoralAsociado(numeroDocumentoUserEnSesion.toString());
			if (zonaElectoral.getMaxPrincipales() != null) {
				int maxPrincipales = Integer.parseInt(zonaElectoral.getMaxPrincipales().toString());
				if (miembrosPrincipales.size() > maxPrincipales) {
					throw new Exception("La plancha excede el m�ximo de principales para esta zona. "
							+ "El m�ximo de principales para esta zona es de " + maxPrincipales);
				}
			}

			if (!tipoEleccionesRepresentantes.equals(tipoEleccionesSession)) {
				admiteSuplentes = true;
			}

			DTOInformacionPlancha dtoInformacionPlancha = DelegadoPlancha.getInstance().finalizarEnviarPlancha(
					this.miembrosPrincipales, this.miembrosSuplentes, this.consecutivoPlancha,
					this.numeroDocumentoUserEnSesion, this.aplicaValidaciones, admiteSuplentes);

			this.estadoPlancha = dtoInformacionPlancha.getEstadoPlancha();

			if (dtoInformacionPlancha.getDescripcionExcepciones() != null
					&& !"".equals(dtoInformacionPlancha.getDescripcionExcepciones())) {
				this.mostrarPopupExcepciones = Boolean.TRUE;
				this.mensajePopupExcepciones = dtoInformacionPlancha.getDescripcionExcepciones();
			} else {
				this.mensajeVista.setVisible(Boolean.TRUE);
			}

			this.mensajeVista.setMensaje("Estimado asociado, ud. Finaliz� el registro de la "
					+ "plancha. Por favor, imprima el formato generado, f�rmelo y entr�guelo en "
					+ "las oficinas indicadas en la direcci�n www.coomeva.com.co");
		} catch (Exception e) {
			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje("Se presento un error Inesperado");
			e.printStackTrace();
		}
		return "";
	}

	public String actionImprimirFormatoPdf() {
		try {
			this.visibleConfirmarImprimir = Boolean.FALSE;
			formatoPdfInscripcionPlancha.generarReporte(this.consecutivoPlancha);

			DelegadoFormatoPlancha.getInstance().registrarFormatoPlancha(this.numeroDocumentoUserEnSesion.toString(),
					CODIGO_FORMATO_INSCRIPCION_PLANCHA, consecutivoPlancha);

			DelegadoPlancha.getInstance().asignarEstadoRegistradoPlancha(this.consecutivoPlancha);

			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje(
					"Se�or Asociado, recuerde que este formato debe imprimirlo, firmarlo y entregarlo en las oficinas indicadas en la p�gina web www.coomeva.com.co");

		} catch (Exception e) {
			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje("Se presento un error Inesperado");
		}
		return "";
	}

	public boolean validarSaneamiento() {

		Date dateToday = new Date();

		ParametroPlanchaDTO parametroFechaInicial = LectorParametros.obtenerParametrosCodigoTipo(
				UtilAcceso.getParametroFuenteL("parametros", "campo.param.saneamiento.inicio"),
				UtilAcceso.getParametroFuenteL("parametros", "campo.param.saneamiento.tipo"));
		ParametroPlanchaDTO parametroFechaFinal = LectorParametros.obtenerParametrosCodigoTipo(
				UtilAcceso.getParametroFuenteL("parametros", "campo.param.saneamiento.fin"),
				UtilAcceso.getParametroFuenteL("parametros", "campo.param.saneamiento.tipo"));

		Date dateFechaIniSaneamiento = ManipulacionFechas.stringToDate(parametroFechaInicial.getStrValor(),
				"yyyy-MM-dd hh:mm:ss");
		Date dateFechaFinSaneamiento = ManipulacionFechas.stringToDate(parametroFechaFinal.getStrValor(),
				"yyyy-MM-dd hh:mm:ss");

		boolean cumpleFechaSaneamiento = false;
		boolean cumpleEstadosModificables = false;

		/*
		 * if (dateToday.getTime() >= dateFechaIniSaneamiento.getTime()//-> fechas de
		 * saneamiento && dateToday.getTime() <= dateFechaFinSaneamiento.getTime()) {
		 * cumpleFechaSaneamiento = true; }
		 */
		estadosPlanchaModificables = DelegadoPlanchas.getInstance().obtenerParametrosTipo(9L);
		for (ParametroPlanchaDTO paraPlancha : estadosPlanchaModificables) {
			if (this.estadoPlancha != null && paraPlancha.getValor().toString().equals(this.estadoPlancha)) {
				cumpleEstadosModificables = true;
			}
		}

		boolean esCabezaPlancha = false;
		DTOInformacionPlancha planchaAsociado;
		try {
			if (consecutivoPlancha != null) {
				planchaAsociado = DelegadoPlancha.getInstance().consultarInformacionPlancha(this.consecutivoPlancha);

				if (planchaAsociado != null) {
					if (numeroDocumentoUserEnSesion.toString()
							.equalsIgnoreCase(planchaAsociado.getNumeroIdentificacionCabeza())) {
						esCabezaPlancha = true;
					}
				}
			}

		} catch (EleccionesDelegadosException e) {
			esCabezaPlancha = false;
		}

		// Si el estado es incrita y dentro de las fechas de saneamiento
		// y la personas es cabeza de plancah se puede realizar modificacion
		if (cumpleFechaSaneamiento && cumpleEstadosModificables && esCabezaPlancha) {
			this.esModificable = true;
		} else {
			this.esModificable = false;
		}

		return cumpleFechaSaneamiento;
	}

	public String actionConfirmarImprimirFormatoPlancha() {
		this.visibleConfirmarImprimir = Boolean.TRUE;
		this.mensajeConfirmacion = "�Esta seguro que desea imprimir el formato de plancha?";
		return "";
	}
	
	public String actionCloseConfirmar() {
		this.visibleConfirmar = Boolean.FALSE;
		this.visibleConfirmarEnviar = Boolean.FALSE;
		this.visibleConfirmarImprimir = Boolean.FALSE;
		this.visibleInfoReporte211 = Boolean.FALSE;
		borrarDatosReporte211();
		return "";
	}
	
	//****************************************************************************************
	// metodos de accion para Descargar formulario de constancia de oficio de miembro suplente
	public void actionDescargarFormatoPrincipal(ActionEvent event) {
		HtmlCommandButton button = (HtmlCommandButton) event.getComponent();
		String posPrincipal = button.getAlt();
		DTOMiembroPlancha principal = this.miembrosPrincipales.get(Integer.parseInt(posPrincipal) - 1);		
		consultarInformacionReporte211(principal.getNumeroDocumento());
	}
	
	public void actionDescargarFormatoSuplente(ActionEvent event) {
		HtmlCommandButton button = (HtmlCommandButton) event.getComponent();
		String posPrincipal = button.getAlt();
		DTOMiembroPlancha principal = this.miembrosSuplentes.get(Integer.parseInt(posPrincipal) - 1);		
		consultarInformacionReporte211(principal.getNumeroDocumento());
	}

	public void consultarInformacionReporte211(Long documento) {
		try {
			asociadoDTO = DelegadoClimae.getInstance().find(documento + "");
			if (asociadoDTO != null) {
				this.visibleInfoReporte211 = Boolean.TRUE;
				this.mensajeInfoCedula = MSJ_INFO_CEDULA + documento + ".";
			}
		} catch (Exception e) {
			actionCloseConfirmar();
			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje("Se presento un error Inesperado");
		}
	}
	
	public String actionImprimirReporte211() {
		Date fechaElaboracionDoc = new Date();
		String tipoReporte = "211";
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		List<EleRegistroCampos> listaRegCampos = new ArrayList<EleRegistroCampos>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

		request.getSession().setAttribute("ciudad", this.ciudadUbicacion);
		request.getSession().setAttribute("dia",
				fechaElaboracionDoc != null ? String.valueOf(fechaElaboracionDoc.getDate()) : "");
		request.getSession().setAttribute("mes",
				fechaElaboracionDoc != null ? String.valueOf(fechaElaboracionDoc.getMonth()) : "");
		request.getSession().setAttribute("anio",
				fechaElaboracionDoc != null ? WorkStrigs.getAnio(fechaElaboracionDoc.getYear()) : "");
		request.getSession().setAttribute("nombreAsociado", asociadoDTO.getNombre());
		request.getSession().setAttribute("cedulaAsociado", asociadoDTO.getId() + "");
		request.getSession().setAttribute("ciudadCedula", this.lugExpCedula);
		request.getSession().setAttribute("ciudadFirma", this.ciudadUbicacion);
		request.getSession().setAttribute("diaFirma", this.fechaFirma != null ? String.valueOf(this.fechaFirma.getDate()) : "");
		request.getSession().setAttribute("mesFirma",
				this.fechaFirma != null ? WorkStrigs.getMes(this.fechaFirma.getMonth()) : "");
		request.getSession().setAttribute("anioFirma",
				this.fechaFirma != null ? WorkStrigs.getAnio(this.fechaFirma.getYear()) : "");

		// para guardar en base de datos los registros de los campos
		listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 1L, asociadoDTO.getNombre()));
		listaRegCampos
				.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 7L, asociadoDTO.getId() + ""));
		listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 8L, this.lugExpCedula));
		listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 21L, this.ciudadUbicacion));
		listaRegCampos
				.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 23L, sdf.format(fechaElaboracionDoc)));
		listaRegCampos.add(new EleRegistroCampos(null, Long.valueOf(tipoReporte), 63L, sdf.format(fechaFirma)));

		try {
			UserVo user = (UserVo) FacesUtils.getSessionParameter("user");
			DelegadoRegistroFormulario.getInstance().crearRegistroFormulario(Long.valueOf(tipoReporte), listaRegCampos,
					user.getUserId());
		} catch (Exception e) {
			this.mensajeVista.setVisible(Boolean.TRUE);
			this.mensajeVista.setMensaje(e.getMessage());
		}
		request.getSession().setAttribute("codigoReporte", tipoReporte);
		JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "ServletReportesJasper();");
		actionCloseConfirmar();
		return "";
	}
	
	// metodos de accion para Cargar formulario de constancia de oficio de miembro principal
	public void actionCargarFormatoPrincipal(ActionEvent event) {
		HtmlCommandButton button = (HtmlCommandButton) event.getComponent();
		String posPrincipal = button.getAlt();
		DTOMiembroPlancha principal = this.miembrosPrincipales.get(Integer.parseInt(posPrincipal) - 1);
	}

	public void actionCargarFormatoSuplente(ActionEvent event) {
		HtmlCommandButton button = (HtmlCommandButton) event.getComponent();
		String posSuplente = button.getAlt();
		DTOMiembroPlancha principal = this.miembrosSuplentes.get(Integer.parseInt(posSuplente) - 1);
	}
	
	private void borrarDatosReporte211() {
		lugExpCedula = null;
		ciudadUbicacion = null;
		fechaFirma = null;
		asociadoDTO = null;
	}
	
	public String getNumeroZonaElectoral() {
		return numeroZonaElectoral;
	}

	public void setNumeroZonaElectoral(String numeroZonaElectoral) {
		this.numeroZonaElectoral = numeroZonaElectoral;
	}

	public List<DTOMiembroPlancha> getMiembrosPrincipales() {
		return miembrosPrincipales;
	}

	public void setMiembrosPrincipales(List<DTOMiembroPlancha> miembrosPrincipales) {
		this.miembrosPrincipales = miembrosPrincipales;
	}

	public List<DTOMiembroPlancha> getMiembrosSuplentes() {
		return miembrosSuplentes;
	}

	public void setMiembrosSuplentes(List<DTOMiembroPlancha> miembrosSuplentes) {
		this.miembrosSuplentes = miembrosSuplentes;
	}

	public MensajesVista getMensajeVista() {
		return this.mensajeVista;
	}

	public void setMensajeVista(MensajesVista mensajeVista) {
		this.mensajeVista = mensajeVista;
	}

	public boolean isVisibleConfirmar() {
		return visibleConfirmar;
	}

	public void setVisibleConfirmar(boolean visibleConfirmar) {
		this.visibleConfirmar = visibleConfirmar;
	}

	public String action_verConfirmacion() {
		visibleConfirmar = true;
		return "";
	}

	public boolean isActivarBotonImprimir() {
		return this.estadoPlancha != null && COD_ESTADO_PLANCHA_REGISTRADA.equals(this.estadoPlancha);
	}

	public void setActivarBotonImprimir(boolean activarBotonImprimir) {
	}

	public boolean isActivarBotonFinalizarEnviar() {
		try {
			// Si la plancha est� radicada no se puede modificar:
			if (consecutivoPlancha != null
					&& DelegadoFormatoPlanchas.getInstance().esPlanchaRadicada(consecutivoPlancha)) {
				return Boolean.FALSE;
			}
			// Si la plancha est� registrada o inscrita pero sin estar radicada,
			// se puede modificar:
			if (this.estadoPlancha == null || COD_ESTADO_PLANCHA_REGISTRADA.equals(this.estadoPlancha)
					|| COD_ESTADO_PLANCHA_INSCRITA.equals(this.estadoPlancha)) {
				return Boolean.TRUE;
			}
			if (this.estadoPlancha != null
					&& (COD_ESTADO_PLANCHA_REGISTRADA.equals(this.estadoPlancha) || validarSaneamiento())) {
				return Boolean.TRUE;
			}
		} catch (Exception e) {
		}
		return Boolean.FALSE;
	}

	public void setActivarBotonFinalizarEnviar(boolean activarBotonFinalizarEnviar) {
	}

	public boolean isActivarBotonRegistrar() {
		try {
			// Si es usuario de la comisi�n puede hacer modificaciones en fechas
			// de saneamiento:
			if (esUsuarioComision && esSaneamiento) {
				return Boolean.TRUE;
			}

			// Si el estado es incrita y dentro de las fechas de saneamiento
			// y la personas es cabeza de plancaha se puede realizar modificacion
			if (esModificable) {
				return Boolean.TRUE;
			}

			// si la placha esta en estado modificado se puede guardar
			if (estadoPlancha != null && estadoPlancha.equalsIgnoreCase(COD_ESTADO_PLANCHA_MODIFICADA)) {
				return Boolean.TRUE;
			}

			// Si la plancha est� radicada no se puede modificar:
			if (consecutivoPlancha != null
					&& DelegadoFormatoPlanchas.getInstance().esPlanchaRadicada(consecutivoPlancha)) {
				return Boolean.FALSE;
			}
			// Si la plancha est� registrada o inscrita pero sin estar radicada,
			// se puede modificar:
			if (this.estadoPlancha == null || COD_ESTADO_PLANCHA_REGISTRADA.equals(this.estadoPlancha)
					|| COD_ESTADO_PLANCHA_INSCRITA.equals(this.estadoPlancha)) {
				return Boolean.TRUE;
			}
			if (this.estadoPlancha == null || COD_ESTADO_PLANCHA_REGISTRADA.equals(this.estadoPlancha)
					|| validarSaneamiento()) {
				return Boolean.TRUE;
			}

		} catch (Exception e) {
		}
		return Boolean.FALSE;
	}

	public void setActivarBotonRegistrar(boolean activarBotonRegistrar) {
	}

	public String getMensajeConfirmacion() {
		return mensajeConfirmacion;
	}

	public void setMensajeConfirmacion(String mensajeConfirmacion) {
		this.mensajeConfirmacion = mensajeConfirmacion;
	}

	public boolean isVisibleConfirmarEnviar() {
		return visibleConfirmarEnviar;
	}

	public void setVisibleConfirmarEnviar(boolean visibleConfirmarEnviar) {
		this.visibleConfirmarEnviar = visibleConfirmarEnviar;
	}

	public boolean isVisibleConfirmarImprimir() {
		return visibleConfirmarImprimir;
	}

	public void setVisibleConfirmarImprimir(boolean visibleConfirmarImprimir) {
		this.visibleConfirmarImprimir = visibleConfirmarImprimir;
	}

	public boolean isDesactivarIngresoCedulas() {

		// Si es usuario de la comisi�n puede hacer modificaciones en fechas de
		// saneamiento:
		if (esUsuarioComision && esSaneamiento) {
			return Boolean.FALSE;
		}

		// Si el estado es incrita y dentro de las fechas de saneamiento
		// y la personas es cabeza de plancaha se puede realizar modificacion
		if (esModificable) {
			return Boolean.FALSE;
		}

		try {
			// Si la plancha est� radicada no se puede modificar:
			if (consecutivoPlancha != null
					&& DelegadoFormatoPlanchas.getInstance().esPlanchaRadicada(consecutivoPlancha)) {
				return Boolean.FALSE;
			}
		} catch (Exception e) {
		}

		if (this.estadoPlancha != null && COD_ESTADO_PLANCHA_INSCRITA.equals(this.estadoPlancha)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public void setDesactivarIngresoCedulas(boolean desactivarIngresoCedulas) {
	}

	public String ocultarPopupExcepciones() {
		this.mostrarPopupExcepciones = Boolean.FALSE;
		this.mensajeVista.setVisible(Boolean.TRUE);
		return "";
	}

	public boolean isMostrarPopupExcepciones() {
		return mostrarPopupExcepciones;
	}

	public void setMostrarPopupExcepciones(boolean mostrarPopupExcepciones) {
		this.mostrarPopupExcepciones = mostrarPopupExcepciones;
	}

	public String getMensajePopupExcepciones() {
		return mensajePopupExcepciones;
	}

	public void setMensajePopupExcepciones(String mensajePopupExcepciones) {
		this.mensajePopupExcepciones = mensajePopupExcepciones;
	}

	public boolean isVisibleInfoReporte211() {
		return visibleInfoReporte211;
	}

	public void setVisibleInfoReporte211(boolean visibleInfoReporte211) {
		this.visibleInfoReporte211 = visibleInfoReporte211;
	}

	public String getMensajeInfoCedula() {
		return mensajeInfoCedula;
	}

	public void setMensajeInfoCedula(String mensajeInfoCedula) {
		this.mensajeInfoCedula = mensajeInfoCedula;
	}

	public String getLugExpCedula() {
		return lugExpCedula;
	}

	public void setLugExpCedula(String lugExpCedula) {
		this.lugExpCedula = lugExpCedula;
	}

	public String getCiudadUbicacion() {
		return ciudadUbicacion;
	}

	public void setCiudadUbicacion(String ciudadUbicacion) {
		this.ciudadUbicacion = ciudadUbicacion;
	}

	public Date getFechaFirma() {
		return fechaFirma;
	}

	public void setFechaFirma(Date fechaFirma) {
		this.fechaFirma = fechaFirma;
	}

}
