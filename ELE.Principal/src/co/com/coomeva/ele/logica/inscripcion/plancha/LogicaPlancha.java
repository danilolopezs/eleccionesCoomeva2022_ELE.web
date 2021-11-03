package co.com.coomeva.ele.logica.inscripcion.plancha;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.delegado.DelegadoClimae;
import co.com.coomeva.ele.delegado.DelegadoHabilidad;
import co.com.coomeva.ele.delegado.DelegadoSie;
import co.com.coomeva.ele.delegado.formulario.DelegadoFormulario;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoPlancha;
import co.com.coomeva.ele.dto.DTOHabilidadAsociado;
import co.com.coomeva.ele.dto.DTOInformacionPlancha;
import co.com.coomeva.ele.dto.DTOMiembroPlancha;
import co.com.coomeva.ele.dto.DTOPlanchaAsociado;
import co.com.coomeva.ele.dto.DTOZonaElectoral;
import co.com.coomeva.ele.entidades.habilidad.EleAsociado;
import co.com.coomeva.ele.entidades.habilidad.EleInhabilidad;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.habilidad.dao.EleAsociadoDAO;
import co.com.coomeva.ele.entidades.planchas.EleInhabilidades;
import co.com.coomeva.ele.entidades.planchas.dao.ElePlanchaAsociadoAudDAO;
import co.com.coomeva.ele.entidades.planchas.dao.ElePlanchaAsociadoDAO;
import co.com.coomeva.ele.entidades.planchas.dao.ElePlanchaDAO;
import co.com.coomeva.ele.entidades.planchas.dao.EleZonaElectoralDAO;
import co.com.coomeva.ele.entidades.planchas.dao.EleZonaElectoralEspecialDAO;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleEstadoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFormato;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFormatoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlanchaAsociado;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlanchaAsociadoAud;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleZonaElectoral;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleZonaElectoralEspecial;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.logica.LogicaAsociado;
import co.com.coomeva.ele.logica.LogicaEstadoPlancha;
import co.com.coomeva.ele.logica.LogicaFiltros;
import co.com.coomeva.ele.logica.LogicaInhabilidad;
import co.com.coomeva.ele.modelo.AsociadoDTO;
import co.com.coomeva.ele.modelo.EleAsociadoDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;
import co.com.coomeva.ele.utilidades.GeneradorConsecutivos;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.DateManipultate;

/**
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
 *         Premize SAS <br>
 * @project ELE.Principal
 * @class LogicaPlancha
 * @date 1/12/2012
 */
public class LogicaPlancha implements ILogicaPlancha {

	public static final String TIPO_INSCRITO_TITULAR = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "param.tipo.inscrito.asociado.titular");

	public static final String TIPO_INSCRITO_SUPLENTE = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "param.tipo.inscrito.asociado.suplente");

	public static final String COD_ESTADO_PLANCHA_REGISTRADA = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
			ConstantesProperties.CODIGO_ESTADO_PLANCHA_REGISTRADA);

	public static final String COD_ESTADO_PLANCHA_MODIFICADA = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
			ConstantesProperties.CODIGO_ESTADO_PLANCHA_MODIFICADA);

	public static final String COD_ESTADO_PLANCHA_INSCRITA = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
			ConstantesProperties.CODIGO_ESTADO_PLANCHA_INSCRITA);

	public static final String COD_NUMERO_INSCRITO_CABEZA_PLANCHA = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
			ConstantesProperties.CODIGO_NUMERO_INSCRITO_CABEZA_PLANCHA);

	public static final String COD_ESTADO_PLANCHA_ADMITIDA = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
			ConstantesProperties.CODIGO_ESTADO_PLANCHA_ADMITIDA);

	public static final String COD_ESTADO_PLANCHA_RECURSO = UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
			ConstantesProperties.CODIGO_ESTADO_PLANCHA_EN_RECURSO);

	private LogicaAsociado logicaAsociado = LogicaAsociado.getInstance();
	private ElePlanchaAsociadoAudDAO daoAud = new ElePlanchaAsociadoAudDAO();
	private ElePlanchaAsociadoDAO dao = new ElePlanchaAsociadoDAO();
	private EleZonaElectoralDAO daoZonaElectoral = new EleZonaElectoralDAO();
	private EleZonaElectoralEspecialDAO daoZonaElectoralEspecial = new EleZonaElectoralEspecialDAO();
	private LogicaFiltros logicaFiltros = LogicaFiltros.getInstance();
	private ILogicaUsuarioComision logicaUsuarioComision;
	private ILogicaPlanchaExcepcion iLogicaPlanchaExcepcion;
	private String NO_ASIGNADO = "No Asignado";

	public List<DTOMiembroPlancha> adicionarMiembroPlancha(List<DTOMiembroPlancha> miembros, Long numeroDocumento,
			int posicionPlancha, Long consecutivoPlancha, boolean aplicaValidaciones)
			throws EleccionesDelegadosException {

		try {
			StringBuffer excepciones = new StringBuffer();

			String nroIdentificacionMiembro = numeroDocumento.toString();
			EleAsociadoDTO asociadoDTO = DelegadoClimae.getInstance().find(nroIdentificacionMiembro);

			if (!aplicaValidaciones && DelegadoAsociado.getInstance().esAsociadoPersonaJuridica(numeroDocumento)) {
				throw new EleccionesDelegadosException(MessageFormat.format(
						UtilAcceso.getParametroFuenteS("mensajes", "msgErrorNoSePermitenPlanchasAsociadoJuridico"),
						nroIdentificacionMiembro.toString()));
			}

			boolean esColaboradorGECoopmeva = DelegadoPlancha.getInstance()
					.esColaboradorGECoomeva(nroIdentificacionMiembro);
			if (!aplicaValidaciones && esColaboradorGECoopmeva) {
				throw new EleccionesDelegadosException(MessageFormat
						.format(UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
								"msgErrorNoSePermitenPlanchasEmpleados"), numeroDocumento.toString()));
			}

			// si aplicaValidaciones = true son validaciones para delegados
			//1.1.	Tiempo de retiro de Asociados empleados y promotores. 
			else if (aplicaValidaciones) {
				if (!esColaboradorGECoopmeva) {
					String msgValidacionEmpleadoFechaRetiro = validarEmpleadoFechaRetiro(
							Long.parseLong(nroIdentificacionMiembro));

					if (msgValidacionEmpleadoFechaRetiro != null && !msgValidacionEmpleadoFechaRetiro.isEmpty()) {
						excepciones.append("- " + msgValidacionEmpleadoFechaRetiro);
					}
				}
			}

				// Validacion del titulo academico
				/*
				 * if(asociadoDTO.getFechaObtencionTitulo() != null){
				 * 
				 * Date fechaMinimaObtencionTitulo =
				 * DateManipultate.stringToDate(UtilAcceso.getParametroFuenteS(
				 * ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
				 * "param.fecha.minima.obtencion.titulo"), "yyyy-MM-dd");
				 * 
				 * boolean fechaObtencionTituloMenorIgualAparametro =
				 * DateManipultate.comparaFechas( asociadoDTO.getFechaObtencionTitulo(),
				 * fechaMinimaObtencionTitulo);
				 * 
				 * if (!fechaObtencionTituloMenorIgualAparametro) {
				 * 
				 * excepciones.append("- "+(UtilAcceso .getParametroFuenteS(
				 * ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
				 * "msgErrorAsociadoConMenos3AnnosObtencionTitulo")) + "<br/>");
				 * 
				 * } } else{ excepciones.append("- "+(UtilAcceso .getParametroFuenteS(
				 * ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
				 * "msgErrorAsociadoConMenos3AnnosObtencionTitulo")) + "<br/>"); }
				 */
			

			if (validarExistenciaEnPlancha(miembros, numeroDocumento, posicionPlancha)) {
				throw new EleccionesDelegadosException(MessageFormat
						.format(UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
								"msgErrorAsociadoYaRegistradoEnPlancha"), numeroDocumento.toString()));
			}

			if (!asociadoPertenceOtraPlancha(nroIdentificacionMiembro, consecutivoPlancha).isEmpty()) {
				throw new EleccionesDelegadosException(MessageFormat
						.format(UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
								"msgErrorAsociadoPerteneceOtraPlancha"), nroIdentificacionMiembro));
			}
			// Cambio 20122013 :
			// Se valida frente a una fecha limite de vinculacion segun requerimiento
//			Date fechaMaximaVinculacion = DateManipultate.stringToDate(
//					UtilAcceso.getParametroFuenteS("parametros", "fechaMaximaVinculacionAntiguedadPlancha"),
//					"yyyyMMdd");
			// Si fecha de vinculacion es mayor o igual a la fecha maxima de vinculacion
//			if (asociadoDTO.getFechaVinculacion().compareTo(fechaMaximaVinculacion) >= 0) {
//				excepciones.append("- "
//						+ MessageFormat
//								.format(UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
//										"msgErrorAsociadoConMenos1AnnoAntiguedad"), nroIdentificacionMiembro)
//						+ "</br>");
//			}

			if (!logicaAsociado.estaAsociaActivo(Long.parseLong(nroIdentificacionMiembro))) {
				throw new EleccionesDelegadosException(UtilAcceso
						.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES, "vldAsociadoInactivo"));
			}

			Long idZona = logicaFiltros.consultarZonaElectoralePorAsociado(Long.parseLong(nroIdentificacionMiembro))
					.getCodigoFiltro();

			if (idZona == null) {
				throw new EleccionesDelegadosException(
						UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES, "vldZonaValida"));
			}

			if (perteneceInstitucionesElectoralesGECoomeva(nroIdentificacionMiembro)) {
				excepciones.append("- "
						+ MessageFormat
								.format(UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
										"msgErrorAsoPertenceInstitucionElectoral"), nroIdentificacionMiembro)
						+ "</br>");

			}

//			if (obtenerAsosSacionados5AnnosAtras().contains(Long.parseLong(nroIdentificacionMiembro))) {
//				excepciones
//						.append("- "
//								+ MessageFormat.format(
//										UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
//												"msgErrorAsoSancionados5AnnosAtras"),
//										nroIdentificacionMiembro)
//								+ "</br>");
//			}

			// SE DEBE VALIDAR CON JULIAN DE QUE DS SE TRAEN LOS DATOS
//			if (aplicaValidaciones && DelegadoSie.getInstance().validateHorasDemocracia(nroIdentificacionMiembro)) {
//				// throw new
//				// EleccionesDelegadosException(UtilAcceso.getParametroFuenteS("mensajes",
//				// "vldHorasDemocracia"));
//				excepciones.append("- " + UtilAcceso.getParametroFuenteS("mensajes", "vldHorasDemocracia") + "</br>");
//			}

//			String msgValidacionEmpleadoFechaRetiro = validarEmpleadoFechaRetiro(Long
//					.parseLong(nroIdentificacionMiembro));
//			excepciones.append(msgValidacionEmpleadoFechaRetiro != null ? "- "
//					+ msgValidacionEmpleadoFechaRetiro : "");

			miembros.get(posicionPlancha - 1).setApellidosNombres(
					getNombreAsociadoConFormato(asociadoDTO)+ " " +getApellidoAsociadoConFormato(asociadoDTO));

			if(!UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "noInscritoEmail")
					.equals(asociadoDTO.getEmail())) {
				miembros.get(posicionPlancha -1).setCorreo(asociadoDTO.getEmail());
			} else {
				String correo = logicaAsociado.consultarCorreoAsociadoPorId(Long.parseLong(nroIdentificacionMiembro));
				miembros.get(posicionPlancha -1).setCorreo(correo);
			}
			
			if (UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "noInscrito")
					.equals(asociadoDTO.getProfesion())) {
				
				List<EleAsociado> listaAsociado = new EleAsociadoDAO().findByProperty("numeroDocumento",
						Long.parseLong(nroIdentificacionMiembro));
				boolean encontroAsociado = Boolean.FALSE;
				for (EleAsociado asociado : listaAsociado) {
					if (asociado.getNumeroDocumento()== Long.parseLong(nroIdentificacionMiembro)
							&& asociado.getDescProfesion() != null) {
						miembros.get(posicionPlancha - 1).setProfesion(asociado.getDescProfesion());
						encontroAsociado = Boolean.TRUE; 
						break;
					}
				}
				if (!encontroAsociado) {
					excepciones
							.append("- " + UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
									"msgAsociadoDebeAcreditarOficio"));
					miembros.get(posicionPlancha - 1).setObservacionAdicionMiembro(
							"Por favor tener en cuenta las siguientes observaciones: </br>" + excepciones.toString());
					miembros.get(posicionPlancha - 1).setTieneProfesion(Boolean.FALSE);
				}
			} else {
				miembros.get(posicionPlancha - 1).setProfesion(asociadoDTO.getProfesion());
			}
			
			// Validar si el asociado es hábil para elegir y ser elegido
			DTOHabilidadAsociado habilidadAso = LogicaAsociado.getInstance()
					.consultarHabilidadAsociado(numeroDocumento);
			boolean esAsociadoHabil = Boolean.FALSE;
			if (habilidadAso != null) {
				esAsociadoHabil = habilidadAso.getAsociadoHabil();
				if (!esAsociadoHabil && !habilidadAso.getObservacionesInhabilidades().isEmpty()) {
					for (String inhabilidad : habilidadAso.getObservacionesInhabilidades()) {
						excepciones.append("- " + inhabilidad + "</br>");
					}
				}
			}
			
			String mensajesExcepciones = excepciones.toString();
			if (mensajesExcepciones != null && !"".equals(mensajesExcepciones)) {
				// Eliminamos la coma seguida de un espacio que siempre se
				// pone al final de las excepciones que se concatenan
				miembros.get(posicionPlancha - 1).setObservacionAdicionMiembro(
						"Por favor tener en cuenta las siguientes observaciones: </br>" + mensajesExcepciones);

			}
		} catch (Exception e) {
			throw new EleccionesDelegadosException(e.getMessage(), e);
		}
		return miembros;
	}

	/**
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 14/12/2012
	 * @param consecutivoPlancha
	 * @param nroIdentificacionMiembro
	 * @throws Exception
	 * @throws EleccionesDelegadosException
	 */
	private String validarZonaEspecialAsociadoEmpleado(Long numeroIdentificacionCabezaPlancha,
			List<DTOMiembroPlancha> miembrosTitulares) throws Exception, EleccionesDelegadosException {

		boolean eszonaEspecial = false;
		if (esColaboradorGECoomeva(numeroIdentificacionCabezaPlancha.toString())) {
			eszonaEspecial = true;
		}

		Long idZona = logicaFiltros.consultarZonaElectoralEspePorEmpleado(numeroIdentificacionCabezaPlancha)
				.getCodigoFiltro();

		EleZonaElectoral zonaElectoralDeAsociadocabezaPlancha = daoZonaElectoral.findById(idZona.toString());

		EleZonaElectoralEspecial zonaElectoralEspeDeAsociadocabezaPlancha = daoZonaElectoralEspecial
				.findById(idZona.toString());
		StringBuffer mensajeExcepcion = new StringBuffer();

		for (DTOMiembroPlancha dtoMiembroPlancha : miembrosTitulares) {

			if (eszonaEspecial) {
				if (UtilAcceso
						.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
								"paramAfirmacionSi")
						.equals(zonaElectoralEspeDeAsociadocabezaPlancha.getZonaEspecial())
						&& !esColaboradorGECoomeva(dtoMiembroPlancha.getNumeroDocumento().toString())) {

					throw new EleccionesDelegadosException("- "
							+ UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
									"msgNoEmpleadoNatural")
							+ " " + dtoMiembroPlancha.getNumeroDocumento().toString() + " "
							+ UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
									"msgNoEmpleadoNatural1")
							+ "</br>");
				}
			} else {
				if (!UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
						"paramAfirmacionSi").equals(zonaElectoralDeAsociadocabezaPlancha.getZonaEspecial())
						&& esColaboradorGECoomeva(dtoMiembroPlancha.getNumeroDocumento().toString())) {
					throw new EleccionesDelegadosException(MessageFormat.format(
							UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
									"msgErrorNoSePermitenPlanchasMixtas"),
							dtoMiembroPlancha.getNumeroDocumento().toString()));
				}
			}

		}

		return mensajeExcepcion != null && !"".equals(mensajeExcepcion.toString()) ? mensajeExcepcion.toString() : "";
	}

	/**
	 * Indica si un asociado pertenece a otra plancha registrada previamente
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 1/12/2012
	 * @param nroIdentificacion
	 * @return boolean
	 * @throws Exception
	 */
	public List<DTOPlanchaAsociado> asociadoPertenceOtraPlancha(String nroIdentificacion, Long consecutivoPlancha)
			throws EleccionesDelegadosException {

		Session session = null;
		Query query = null;
		List<DTOPlanchaAsociado> datos = null;
		try {
			session = HibernateSessionFactoryElecciones2012.getSession();
			query = session.getNamedQuery(ConstantesNamedQueries.QUERY_PLANCHAS_POR_ASOCIADO);
			query.setLong("numDocumentoAso", Long.parseLong(nroIdentificacion));
			query.setLong("consecutivoPlanchaActual", (consecutivoPlancha != null) ? consecutivoPlancha : 0L);

			List<Object[]> datosConsulta = query.list();

			datos = new ArrayList<DTOPlanchaAsociado>();
			for (Object[] object : datosConsulta) {
				DTOPlanchaAsociado planchaAsociado = new DTOPlanchaAsociado();
				planchaAsociado.setConsecutivoPlanchaAso(object[0] != null ? (Long) object[0] : null);
				planchaAsociado.setNumeroInscrito(object[1] != null ? (Long) object[1] : null);
				planchaAsociado.setTipoInscrito(object[2] != null ? object[2].toString() : null);
				planchaAsociado.setFechaInscripcion(object[3] != null ? (Timestamp) object[3] : null);
				planchaAsociado.setCodigoAsociado(object[4] != null ? (Long) object[4] : null);
				planchaAsociado.setConsecutivoPlancha(object[5] != null ? (Long) object[5] : null);
				datos.add(planchaAsociado);
			}
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
		}
		return datos;
	}

	/**
	 * Obtiene la zona electoral relacionada a un asociado
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 1/12/2012
	 * @param nroIdentificacion
	 * @return DTOZonaElectoral
	 * @throws Exception
	 */
	public DTOZonaElectoral consultarZonaElectoralAsociado(String nroIdentificacion)
			throws EleccionesDelegadosException {

		Session session = null;
		Query query = null;
		try {
			session = HibernateSessionFactoryElecciones2012.getSession();
			query = session.getNamedQuery(ConstantesNamedQueries.QUERY_ZONA_ELECTORAL_ASOCIADO);
			query.setLong("numDocumentoAso", Long.parseLong(nroIdentificacion));

			List<Object[]> datosConsulta = (List<Object[]>) query.list();

			DTOZonaElectoral zonaElectoralAsociado = null;
			for (Object[] objects : datosConsulta) {
				zonaElectoralAsociado = new DTOZonaElectoral();
				zonaElectoralAsociado.setCodZonaElectoral(objects[0] != null ? (String) objects[0] : null);
				zonaElectoralAsociado.setDescripcionZonaElectoral(objects[1] != null ? (String) objects[1] : null);
				zonaElectoralAsociado.setZonaEspecial(objects[2] != null ? (String) objects[2] : null);
				zonaElectoralAsociado.setMaxPrincipales(objects[3] != null ? (Long) objects[3] : null);
			}
			return zonaElectoralAsociado;
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
		}
	}

	/**
	 * Permite consultar la información de una plancha por medio de un consecutivo
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 2/12/2012
	 * @param consecutivoPlancha
	 * @return
	 * @throws EleccionesDelegadosException
	 */
	public DTOInformacionPlancha consultarInformacionPlancha(Long consecutivoPlancha)
			throws EleccionesDelegadosException {

		ElePlancha plancha = new ElePlanchaDAO().findById(consecutivoPlancha);

		if (plancha == null) {
			throw new EleccionesDelegadosException("No se ha encontrado plancha con consecutivo " + consecutivoPlancha);
		}
		EleZonaElectoral elezonaelectoral = new EleZonaElectoralDAO()
				.findById(plancha.getEleZonaElectoral().getCodigoZonaEle());

		EleZonaElectoralEspecial elezonaelectoralEspecial = null;

		if (plancha.getEleZonaElectoralEspecial() != null) {
			elezonaelectoralEspecial = new EleZonaElectoralEspecialDAO()
					.findById(plancha.getEleZonaElectoral().getCodigoZonaEle());
		}

		String numeroZona = "";
		if (elezonaelectoralEspecial != null) {
			numeroZona = elezonaelectoralEspecial.getNumeroZona();
		} else {
			numeroZona = consultarNumeroZonaPorCodigoZona(plancha.getEleZonaElectoral().getCodigoZonaEle());
		}

		DTOInformacionPlancha info = new DTOInformacionPlancha();
		info.setNumeroZonaElectoral(numeroZona.toString());
		info.setConsecutivoPlancha(consecutivoPlancha);
		 
		// 12/01/2012 Se cambia. la ciudad es la ciudad de la zona. no la ciudad
		// de residencia del asociado.		
		info.setCiudad((elezonaelectoralEspecial != null) ? elezonaelectoralEspecial.getDescripcionZonaEle()
				: elezonaelectoral.getDescripcionZonaEle());
		
		Timestamp fechaRegistro = plancha.getFechaRegistro();
		info.setEstadoPlancha(plancha.getEstadoPlancha());

		if (fechaRegistro != null) {
			SimpleDateFormat formatterMes = new SimpleDateFormat("MM");
			SimpleDateFormat formatterDia = new SimpleDateFormat("dd");
			SimpleDateFormat formatterHora = new SimpleDateFormat("HH");
			SimpleDateFormat formatterAnno = new SimpleDateFormat("yyyy");
			SimpleDateFormat formatterMinutos = new SimpleDateFormat("mm");

			info.setAnno(formatterAnno.format(fechaRegistro));
			info.setMes(formatterMes.format(fechaRegistro));
			info.setDia(formatterDia.format(fechaRegistro));
			info.setHora(formatterHora.format(fechaRegistro) + ":" + formatterMinutos.format(fechaRegistro));
		}
		if(plancha.getFirmaVirtual() != null) {
			info.setFirmaVirtual(plancha.getFirmaVirtual());
		}

		List<ElePlanchaAsociado> asociadosPlancha = new ElePlanchaAsociadoDAO()
				.findByConsecutivoPlancha(consecutivoPlancha);

		if (asociadosPlancha == null || asociadosPlancha.isEmpty()) {
			throw new EleccionesDelegadosException("No se ha encontrado asociados relacionados"
					+ " a la plancha con consecutivo " + consecutivoPlancha);
		}

		List<DTOMiembroPlancha> miembrosTitulares = new ArrayList<DTOMiembroPlancha>();
		List<DTOMiembroPlancha> miembrosSuplentes = new ArrayList<DTOMiembroPlancha>();

		String tipoInscritoTitular = UtilAcceso.getParametroFuenteS(
				ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "param.tipo.inscrito.asociado.titular");

		String tipoInscritoSuplente = UtilAcceso.getParametroFuenteS(
				ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "param.tipo.inscrito.asociado.suplente");

		Long numeroDocumentoAsociadoCabezaPlancha = null;
		for (ElePlanchaAsociado asociado : asociadosPlancha) {

			DTOMiembroPlancha miembroPlancha = new DTOMiembroPlancha();
			miembroPlancha.setPosicionPlancha(asociado.getNumeroInscrito().toString());
			miembroPlancha.setNumeroDocumento(asociado.getEleAsociado().getNumeroDocumento());

			EleAsociadoDTO asociadoDTO = null;
			try {
				asociadoDTO = DelegadoClimae.getInstance()
						.find(asociado.getEleAsociado().getNumeroDocumento().toString());
			} catch (Exception e) {
				throw new EleccionesDelegadosException(e);
			}

			// se fija el nombre del asociado
			miembroPlancha.setApellidosNombres(
					getNombreAsociadoConFormato(asociadoDTO)+ " " +getApellidoAsociadoConFormato(asociadoDTO));

			// se fija el correo del asociado
			if(!UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "noInscritoEmail")
					.equals(asociadoDTO.getEmail())) {
				miembroPlancha.setCorreo(asociadoDTO.getEmail());
			} else {
				String correo = logicaAsociado.consultarCorreoAsociadoPorId(asociado.getEleAsociado().getNumeroDocumento());
				miembroPlancha.setCorreo(correo);
			}
			
			// se fija la profesion del asociado
			if (UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "noInscrito")
					.equals(asociadoDTO.getProfesion())) {
				EleAsociado eleAsociado = new EleAsociadoDAO().findById(asociado.getEleAsociado().getCodigoAsociado());
				miembroPlancha.setProfesion(eleAsociado.getDescProfesion());
			} else {
				miembroPlancha.setProfesion(asociadoDTO.getProfesion());
			}

			if (tipoInscritoTitular.equals(asociado.getTipoInscrito())) {
				miembroPlancha.setTipoMiembro(tipoInscritoTitular);
				miembrosTitulares.add(miembroPlancha);

				if (numeroDocumentoAsociadoCabezaPlancha == null && "1".equals(miembroPlancha.getPosicionPlancha())) {
					numeroDocumentoAsociadoCabezaPlancha = miembroPlancha.getNumeroDocumento();
				}
			} else {
				miembroPlancha.setTipoMiembro(tipoInscritoSuplente);
				miembrosSuplentes.add(miembroPlancha);
			}
		}

		if (numeroDocumentoAsociadoCabezaPlancha != null) {
			info.setNumeroIdentificacionCabeza(numeroDocumentoAsociadoCabezaPlancha.toString());
		}

		// 12/01/2012 Se cambia. la ciudad es la ciudad de la zona. no la ciudad
		// de residencia del asociado.
		/*
		 * try { EleAsociadoDatosDTO datosAsociado = LogicaAsociado.getInstance()
		 * .consultarInformacionBasicaAsociado(numeroDocumentoAsociadoCabezaPlancha );
		 * info.setCiudad(datosAsociado.getCiudadReside() != null ?
		 * datosAsociado.getCiudadReside().toUpperCase() : ""); } catch (Exception e) {
		 * throw new EleccionesDelegadosException(
		 * "No se logró consultar la ciudad de residencia de asociado cabeza de plancha"
		 * ); }
		 */

		Collections.sort(miembrosTitulares);
		Collections.sort(miembrosSuplentes);

		info.setMiembrosTitulares(miembrosTitulares);
		info.setMiembrosSuplentes(miembrosSuplentes);

		return info;
	}
	

	/**
	 * Se construye el nombre del Asociado a partir de un DTO
	 * 
	 * @author GTC CORPORATION - Danilo Lopez Sandoval
	 * @date 25/10/2021
	 * @param asociadoDTO
	 * @return String - nombre del Asociado
	 */
	private String getNombreAsociadoConFormato(EleAsociadoDTO asociadoDTO) {
		StringBuffer nombresAsociado = new StringBuffer();
		nombresAsociado
				.append(asociadoDTO.getPrimerNombre() != null && !"".equals(asociadoDTO.getPrimerNombre())
						? " " + asociadoDTO.getPrimerNombre()
						: "");
		nombresAsociado
				.append(asociadoDTO.getSegundoNombre() != null && !"".equals(asociadoDTO.getSegundoNombre())
						? " " + asociadoDTO.getSegundoNombre()
						: "");
		return nombresAsociado.toString().trim();
	}
	
	/**
	 * Se construye el apellido del Asociado a partir de un DTO
	 * 
	 * @author GTC CORPORATION - Danilo Lopez Sandoval
	 * @date 25/10/2021
	 * @param asociadoDTO
	 * @return String - apellido del Asociado
	 */
	private String getApellidoAsociadoConFormato(EleAsociadoDTO asociadoDTO) {
		StringBuffer apellidosAsociado = new StringBuffer();
		apellidosAsociado
				.append(asociadoDTO.getPrimerApellido() != null && !"".equals(asociadoDTO.getPrimerApellido())
						? asociadoDTO.getPrimerApellido()
						: "");
		apellidosAsociado
				.append(asociadoDTO.getSegundoApellido() != null && !"".equals(asociadoDTO.getSegundoApellido())
						? " " + asociadoDTO.getSegundoApellido()
						: "");
		return apellidosAsociado.toString().trim();
	}

	/**
	 * Indica si el miembro a registrar en la lista pertenece a alguna entidad de
	 * apoyo electoral del GE Coomeva
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 2/12/2012
	 * @param identificacionMiembro
	 * @return boolean
	 */
	public boolean perteneceInstitucionesElectoralesGECoomeva(String identificacionMiembro) {
		Long identificacion = Long.parseLong(identificacionMiembro);
		List<Long> integrantesTribualElecciones = obtenerAsosPertenecenOtrasEntidadesElectorales();
		return integrantesTribualElecciones.contains(identificacion);
	}

	/**
	 * Indica si un asociado es colaborador en el grupo empresarial coomeva
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 13/12/2012
	 * @param identificacionMiembro
	 * @return
	 */
	public boolean esColaboradorGECoomeva(String identificacionMiembro) {
		Long identificacion = Long.parseLong(identificacionMiembro);
		List<Long> asociadosEmpleadosActivos = obtenerAsosEmpleadosActivos();
		return asociadosEmpleadosActivos.contains(identificacion);
	}

	/**
	 * Obtiene los asociados que son empleados activos en el grupo empresarial
	 * coomeva
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 13/12/2012
	 * @return
	 */
	public List<Long> obtenerAsosEmpleadosActivos() {
		Session session = null;
		Query query = null;
		try {
			session = HibernateSessionFactoryElecciones2012.getSession();
			query = session.getNamedQuery(ConstantesNamedQueries.QUERY_ASOCIADOS_EMPLEADOS_ACTIVOS);
			return query.list();
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
			query = null;
		}
	}

	public List<Object[]> obtenerAsosEmpleadosRetirados() {
		Session session = null;
		Query query = null;
		try {
			session = HibernateSessionFactoryElecciones2012.getSession();
			query = session.getNamedQuery(ConstantesNamedQueries.QUERY_ASOCIADOS_EMPLEADOS_RETIRADOS);
			return query.list();
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
			query = null;
		}
	}

	/**
	 * Obtiene los personas que están registradas como integrantes de entidades de
	 * caracter electoral como el tribunal de elecciones, la comisión electoral
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 2/12/2012
	 * @return List<Long>
	 */
	public List<Long> obtenerAsosPertenecenOtrasEntidadesElectorales() {
		Session session = null;
		Query query = null;
		try {
			session = HibernateSessionFactoryElecciones2012.getSession();
			query = session.getNamedQuery(ConstantesNamedQueries.QUERY_INTEGRANTES_OTRAS_ENTIDADES_ELECTORALES);
			return query.list();
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
			query = null;
		}
	}

	/**
	 * Valida si el asociado ya no se encuentra registrado en la plancha
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 2/12/2012
	 * @param miembros
	 * @param numeroDocumento
	 * @return boolean
	 */
	private boolean validarExistenciaEnPlancha(List<DTOMiembroPlancha> miembros, Long numeroDocumento, int posicion) {

		int i = 1;
		for (DTOMiembroPlancha dtoMiembroPlancha : miembros) {
			if (dtoMiembroPlancha.getNumeroDocumento() != null
					&& dtoMiembroPlancha.getNumeroDocumento().equals(numeroDocumento) && posicion != i) {
				return Boolean.TRUE;
			}
			i++;
		}
		return Boolean.FALSE;
	}

	/**
	 * Obtiene los asociados que fueron sancionados 5 años atrás
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 3/12/2012
	 * @return List<Long>
	 */
	public List<Long> obtenerAsosSacionados5AnnosAtras() {
		Session session = null;
		Query query = null;
		try {
			session = HibernateSessionFactoryElecciones2012.getSession();
			query = session.getNamedQuery(ConstantesNamedQueries.QUERY_ASOCIADOS_SANCIONADOS_5_ANNOS_ATRAS);
			return query.list();
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
			query = null;
		}
	}

	/**
	 * Metodo que modifica los miembros de plancha
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 3/12/2012
	 * @param miembros
	 * @param tipoInscrito
	 * @param numeroDocumentoCabeza
	 * @param consecutivoPlancha
	 * @throws Exception
	 */
	public String modificarMiembrosPlanchas(List<DTOMiembroPlancha> miembros, String tipoInscrito,
			Long consecutivoPlancha, boolean esModificable) throws EleccionesDelegadosException {
		ElePlanchaAsociado entidadElePlanchaAsociado = null;
		try {
			miembros = obtenerListaRealMiembros(miembros);
			Session session = HibernateSessionFactoryElecciones2012.getSession();

			validarZonaEspecialAsociadoEmpleado(miembros.get(0).getNumeroDocumento(), miembros);

			validarZonaElectoralIntegrantesPlancha(miembros.get(0).getNumeroDocumento(), miembros);

			for (DTOMiembroPlancha dtoMiembroPlancha : miembros) {
				Long codigoAsociado = logicaAsociado
						.consultarCodigoAsociadoPorNumeroDocumento(dtoMiembroPlancha.getNumeroDocumento());
				Long posicion = consultarPosicionPorPlanchaAsociado(consecutivoPlancha, codigoAsociado);
				if (posicion != null) {
					if (posicion != new Long(dtoMiembroPlancha.getPosicionPlancha())) {
						entidadElePlanchaAsociado = consultaPlanchaAsociadoPorCodigoAsociadoPlancha(consecutivoPlancha,
								codigoAsociado);
						registraPlanchaAsociadoAud(entidadElePlanchaAsociado);
						entidadElePlanchaAsociado.setFechaInscripcion(new Timestamp(new Date().getTime()));
						entidadElePlanchaAsociado.setNumeroInscrito(new Byte(dtoMiembroPlancha.getPosicionPlancha()));
						modificarMiembroPlancha(entidadElePlanchaAsociado);
					}
				} else {
					if (posicion == null) {
						posicion = dtoMiembroPlancha.getPosicionPlancha() != null
								? Long.valueOf(dtoMiembroPlancha.getPosicionPlancha())
								: null;
					}
					Boolean posicionNoOcupadaPorOtroMiembro = consultaSiEsPosicionNueva(consecutivoPlancha,
							new Long(dtoMiembroPlancha.getPosicionPlancha()), dtoMiembroPlancha.getTipoMiembro());
					if (posicionNoOcupadaPorOtroMiembro) {
						entidadElePlanchaAsociado = new ElePlanchaAsociado();
						entidadElePlanchaAsociado.setConsecutivoPlancha(consecutivoPlancha);

						session = HibernateSessionFactoryElecciones2012.getSession();
						Long consecutivoPlanchaAso = GeneradorConsecutivos.getInstance()
								.getConsecutivo(ConstantesNamedQueries.QUERY_SEQ_ELE_PLANCHA_ASOCIADO, session);
						entidadElePlanchaAsociado.setConsecutivoPlanchaAso(consecutivoPlanchaAso);
						entidadElePlanchaAsociado
								.setEleAsociado(logicaAsociado.consultarAsociadoPorCodigo(codigoAsociado));
						entidadElePlanchaAsociado.setFechaInscripcion(new Timestamp(new Date().getTime()));
						entidadElePlanchaAsociado.setNumeroInscrito(new Byte(dtoMiembroPlancha.getPosicionPlancha()));
						entidadElePlanchaAsociado.setTipoInscrito(tipoInscrito);
						guardarMiembroPlancha(entidadElePlanchaAsociado);
					} else {
						entidadElePlanchaAsociado = consultaPlanchaAsociadoPorCodigoPlanchaNumInscrito(
								consecutivoPlancha, new Byte(dtoMiembroPlancha.getPosicionPlancha()),
								dtoMiembroPlancha.getTipoMiembro());
						if (entidadElePlanchaAsociado != null
								&& entidadElePlanchaAsociado.getConsecutivoPlanchaAso() != null) {
							eliminarMiembroPlancha(entidadElePlanchaAsociado);
							registraPlanchaAsociadoAud(entidadElePlanchaAsociado);
						}

						entidadElePlanchaAsociado = new ElePlanchaAsociado();
						entidadElePlanchaAsociado.setConsecutivoPlancha(consecutivoPlancha);

						session = HibernateSessionFactoryElecciones2012.getSession();
						Long consecutivoPlanchaAso = GeneradorConsecutivos.getInstance()
								.getConsecutivo(ConstantesNamedQueries.QUERY_SEQ_ELE_PLANCHA_ASOCIADO, session);
						entidadElePlanchaAsociado.setConsecutivoPlanchaAso(consecutivoPlanchaAso);
						entidadElePlanchaAsociado
								.setEleAsociado(logicaAsociado.consultarAsociadoPorCodigo(codigoAsociado));
						entidadElePlanchaAsociado.setFechaInscripcion(new Timestamp(new Date().getTime()));
						entidadElePlanchaAsociado.setNumeroInscrito(new Byte(dtoMiembroPlancha.getPosicionPlancha()));
						entidadElePlanchaAsociado.setTipoInscrito(tipoInscrito);
						guardarMiembroPlancha(entidadElePlanchaAsociado);
					}
				}
			}

			if (miembros.size() < 1) {

				if (tipoInscrito.equalsIgnoreCase(TIPO_INSCRITO_SUPLENTE)) {
					throw new EleccionesDelegadosException(
							"Estimado Asociado, recuerde que la plancha debe inscribir al menos un (1) suplente");
				} else {
					throw new EleccionesDelegadosException(
							"Por favor registre al menos" + " un miembro titular para registrar la plancha");
				}
			}

			eliminarMiembrosPlancha(miembros, tipoInscrito, consecutivoPlancha);

			/**
			 * @author Christian Mauricio Tangarife Colorado cmtc4227 Modifica el estado de
			 *         la plancha a modificado cuendo se encuentra en estado Incrita y lo
			 *         hace el cabeza de plancha dentro del tiempo establecido.
			 */
			if (esModificable) {
				ElePlancha elePlancha = new ElePlanchaDAO().findById(consecutivoPlancha);

				if (elePlancha != null) {
					elePlancha.setEstadoPlancha(COD_ESTADO_PLANCHA_MODIFICADA);
					modificarPlancha(elePlancha);
				}
			}

			return COD_ESTADO_PLANCHA_REGISTRADA;

		} catch (EleccionesDelegadosException ede) {
			throw ede;
		} catch (Exception e) {
			throw new EleccionesDelegadosException(e);
		}
	}

	/**
	 * Eliminar los asociados que fueron retirados de la plancha y cuya posicion no
	 * fue asumida por otro asociado
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 14/12/2012
	 * @param miembros
	 * @param tipoInscrito
	 * @param consecutivoPlancha
	 * @throws EleccionesDelegadosException
	 */
	private void eliminarMiembrosPlancha(List<DTOMiembroPlancha> miembros, String tipoInscrito, Long consecutivoPlancha)
			throws EleccionesDelegadosException, Exception {
		// Pasamos a
		DTOInformacionPlancha infoPlancha = consultarInformacionPlancha(consecutivoPlancha);
		List<DTOMiembroPlancha> listaMiembrosAnterior = null;

		if (TIPO_INSCRITO_TITULAR.equals(tipoInscrito)) {
			listaMiembrosAnterior = infoPlancha.getMiembrosTitulares();
		} else {
			listaMiembrosAnterior = infoPlancha.getMiembrosSuplentes();
		}

		List<Long> documentosMiembrosActuales = new ArrayList<Long>();
		for (DTOMiembroPlancha dtoMiembro : miembros) {
			documentosMiembrosActuales.add(dtoMiembro.getNumeroDocumento());
		}

		for (DTOMiembroPlancha dtoMiembroPlancha : listaMiembrosAnterior) {
			if (!documentosMiembrosActuales.contains(dtoMiembroPlancha.getNumeroDocumento())) {
				Long codigoAsociado = logicaAsociado
						.consultarCodigoAsociadoPorNumeroDocumento(dtoMiembroPlancha.getNumeroDocumento());
				ElePlanchaAsociado entidadElePlanchaAsociado = consultaPlanchaAsociadoPorCodigoAsociadoPlancha(
						consecutivoPlancha, codigoAsociado);
				registraPlanchaAsociadoAud(entidadElePlanchaAsociado);
				// HibernateSessionFactoryElecciones2012.getSession().merge(
				// entidadElePlanchaAsociado);
				eliminarMiembroPlancha(entidadElePlanchaAsociado);
			}
		}
	}

	public DTOInformacionPlancha registrarPlancha(List<DTOMiembroPlancha> miembrosTitulares,
			List<DTOMiembroPlancha> miembrosSuplentes, Long numDocUsuarioRegistra, boolean aplicaValidaciones)
			throws EleccionesDelegadosException {

		Transaction tr = null;
		Session session = null;
		StringBuffer excepcionesRegistroPlancha = new StringBuffer();

		try {
			List<DTOMiembroPlancha> listaRealMiembrosTitulares = obtenerListaRealMiembros(miembrosTitulares);
			List<DTOMiembroPlancha> listaRealMiembrosSuplentes = obtenerListaRealMiembros(miembrosSuplentes);
			if (listaRealMiembrosTitulares.size() < 1) {
				throw new EleccionesDelegadosException(
						"Por favor registre al menos un miembro titular para registrar la plancha.");
			}

			if (aplicaValidaciones) {
				if (listaRealMiembrosSuplentes.size() < 1) {
					throw new EleccionesDelegadosException(
							"Estimado Asociado, recuerde que la plancha debe inscribir al menos un (1) suplente.");
				}
			}

			if ((listaRealMiembrosTitulares != null && listaRealMiembrosSuplentes != null)
					&& (listaRealMiembrosSuplentes.size() > listaRealMiembrosTitulares.size())) {
				throw new EleccionesDelegadosException(
						"Estimado Asociado, recuerde que el no. de suplentes debe ser igual o inferior al no. de principales inscritos.");
			}

			boolean usuarioQueRegistraEsTitular = Boolean.FALSE;
			for (DTOMiembroPlancha dtoMiembroPlancha : miembrosTitulares) {
				if (dtoMiembroPlancha.getNumeroDocumento() != null
						&& dtoMiembroPlancha.getNumeroDocumento().equals(numDocUsuarioRegistra)) {
					usuarioQueRegistraEsTitular = Boolean.TRUE;
					break;
				}
			}

			// Validar que los miembros titulares no sean empleados si aplica la
			// elección de representantes:
			for (DTOMiembroPlancha dtoMiembroPlancha : miembrosTitulares) {

				if (dtoMiembroPlancha.getNumeroDocumento() != null
						&& dtoMiembroPlancha.getNumeroDocumento().equals(numDocUsuarioRegistra)) {

					String doc = String.valueOf(dtoMiembroPlancha.getNumeroDocumento());

					if (!aplicaValidaciones && DelegadoAsociado.getInstance()
							.esAsociadoPersonaJuridica(dtoMiembroPlancha.getNumeroDocumento())) {
						throw new EleccionesDelegadosException(MessageFormat.format(
								UtilAcceso.getParametroFuenteS("mensajes",
										"msgErrorNoSePermitenPlanchasAsociadoJuridico"),
								dtoMiembroPlancha.getNumeroDocumento().toString()));
					}

					if (!aplicaValidaciones && DelegadoPlancha.getInstance().esColaboradorGECoomeva(doc)) {
						throw new EleccionesDelegadosException(MessageFormat
								.format(UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
										"msgErrorNoSePermitenPlanchasEmpleados"), doc));
					}
				}
			}

			// Validar que los miembros suplentes no sean empleados si aplica la
			// elección de representantes:
			for (DTOMiembroPlancha dtoMiembroPlancha : miembrosSuplentes) {

				if (dtoMiembroPlancha.getNumeroDocumento() != null
						&& dtoMiembroPlancha.getNumeroDocumento().equals(numDocUsuarioRegistra)) {

					String doc = String.valueOf(dtoMiembroPlancha.getNumeroDocumento());

					if (!aplicaValidaciones && DelegadoPlancha.getInstance().esColaboradorGECoomeva(doc)) {
						throw new EleccionesDelegadosException(MessageFormat
								.format(UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
										"msgErrorNoSePermitenPlanchasEmpleados"), doc));
					}
				}
			}

			if (!usuarioQueRegistraEsTitular) {
				throw new EleccionesDelegadosException("Estimado Asociado, recuerde que el "
						+ "formulario debe ser diligenciado únicamente por uno de los miembros principales de la plancha, es decir "
						+ "usted señor asociado debe ser parte de la misma como miembro principal.");

				// excepcionesRegistroPlancha.append("Estimado Asociado, recuerde que el "
				// +
				// "formulario debe ser diligenciado únicamente por uno de los miembros
				// principales de la plancha, es decir "
				// +
				// "usted señor asociado debe ser parte de la misma como miembro
				// principal.</br>");
			}

			if (listaRealMiembrosTitulares.get(0).getPosicionPlancha() == null
					|| !"1".equals(listaRealMiembrosTitulares.get(0).getPosicionPlancha())) {
				throw new EleccionesDelegadosException("Estimado Asociado, recuerde que el cabeza de plancha debe "
						+ "estar inscrito en la primera posición, favor ingresar sus datos.");
			}

			List<Long> miembrosComunes = hallarMiembroComunesListas(miembrosTitulares, miembrosSuplentes);

			if (miembrosComunes != null && !miembrosComunes.isEmpty()) {
				StringBuffer asociadosComunes = new StringBuffer();
				asociadosComunes.append("Estimado asociado, los asociados con las siguientes"
						+ " cédulas se encuentran tanto en la lista de titulares como la de suplentes: ");
				for (Long miembroComun : miembrosComunes) {
					asociadosComunes.append(miembroComun.toString() + ", ");
				}
				asociadosComunes.append("por favor corrija la información ingresada de forma que estos"
						+ " no existan en ambas listas.");
				throw new EleccionesDelegadosException(asociadosComunes.toString());
			}

			excepcionesRegistroPlancha.append(validarZonaEspecialAsociadoEmpleado(
					listaRealMiembrosTitulares.get(0).getNumeroDocumento(), listaRealMiembrosTitulares));
			excepcionesRegistroPlancha.append(validarZonaEspecialAsociadoEmpleado(
					listaRealMiembrosTitulares.get(0).getNumeroDocumento(), listaRealMiembrosSuplentes));

			Long numeroDocCabezaPlancha = listaRealMiembrosTitulares.get(0).getNumeroDocumento();
			validarZonaElectoralIntegrantesPlancha(numeroDocCabezaPlancha, listaRealMiembrosTitulares);
			validarZonaElectoralIntegrantesPlancha(numeroDocCabezaPlancha, listaRealMiembrosSuplentes);

			validarProfesionesMiembros(listaRealMiembrosTitulares);
			validarProfesionesMiembros(listaRealMiembrosSuplentes);

			session = HibernateSessionFactoryElecciones2012.getSession();

			ElePlancha plancha = new ElePlancha();

			Long consecutivoPlancha = GeneradorConsecutivos.getInstance()
					.getConsecutivo(ConstantesNamedQueries.QUERY_SEQ_ELE_PLANCHA, session);

			if (consecutivoPlancha == null) {
				throw new EleccionesDelegadosException("No se logró obtener el consecutivo" + " para las planchas");
			}

			plancha.setConsecutivoPlancha(consecutivoPlancha);
			plancha.setEstadoPlancha(COD_ESTADO_PLANCHA_REGISTRADA);
			plancha.setFechaRegistro(new Timestamp(new Date().getTime()));
			plancha.setCodigoUsuario(numDocUsuarioRegistra);

			Long idZona = logicaFiltros
					.consultarZonaElectoralePorAsociado(miembrosTitulares.get(0).getNumeroDocumento())
					.getCodigoFiltro();

//			Long idZonaEspecial =  logicaFiltros.consultarZonaElectoralEspePorEmpleado(
//					miembrosTitulares.get(0).getNumeroDocumento())
//					.getCodigoFiltro();

			// Valida que el cabeza de plancha es emplado para asignar zona electoral
			// especial.
			// aplica para eleccion delegados
			if (aplicaValidaciones
					&& DelegadoPlancha.getInstance().esColaboradorGECoomeva(numeroDocCabezaPlancha.toString())) {
				EleZonaElectoralEspecial zonaElectoralEspecial = daoZonaElectoralEspecial.findById(idZona.toString());

				plancha.setEleZonaElectoralEspecial(zonaElectoralEspecial);
			} else {
				plancha.setEleZonaElectoralEspecial(null);
			}

			EleZonaElectoral zonaElectoral = daoZonaElectoral.findById(idZona.toString());
			plancha.setEleZonaElectoral(zonaElectoral);

			session = HibernateSessionFactoryElecciones2012.getSession();
			tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
			new ElePlanchaDAO().save(plancha);

			String tipoInscritoTitular = UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "param.tipo.inscrito.asociado.titular");

			String tipoInscritoSuplente = UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "param.tipo.inscrito.asociado.suplente");

			registrarMiembrosEnPlancha(listaRealMiembrosTitulares, tipoInscritoTitular, consecutivoPlancha, session);
			registrarMiembrosEnPlancha(listaRealMiembrosSuplentes, tipoInscritoSuplente, consecutivoPlancha, session);

			registrarExcepcionesPlancha(listaRealMiembrosTitulares, consecutivoPlancha);
			registrarExcepcionesPlancha(listaRealMiembrosSuplentes, consecutivoPlancha);

			// Registramos las excepciones que ya no están como tal relacionadas
			// a un asociado
			// sino que están relacionadas a la plancha en general, por lo tanto
			// estás se registran
			// con la cédula del asociado cabeza de plancha
			listaRealMiembrosTitulares.get(0).setObservacionAdicionMiembro(excepcionesRegistroPlancha.toString());
			registrarExcepcionesPlancha(listaRealMiembrosTitulares.subList(0, 1), consecutivoPlancha);

			tr.commit();
			DTOInformacionPlancha objPlancha = new DTOInformacionPlancha();
			objPlancha.setConsecutivoPlancha(consecutivoPlancha);
			objPlancha.setEstadoPlancha(COD_ESTADO_PLANCHA_REGISTRADA);
			objPlancha.setDescripcionExcepciones(excepcionesRegistroPlancha.toString());

			return objPlancha;
		} catch (EleccionesDelegadosException e) {
			throw e;
		} catch (Exception e) {
			if (tr != null && tr.isActive()) {
				tr.rollback();
			}

			throw new EleccionesDelegadosException(
					"Se ha presentado un error" + " intentando registrar la plancha. " + e.getMessage(), e);
		} finally {
			if (session != null) {
				session.close();
			}

			session = null;
			tr = null;
		}
	}

	/**
	 * Valida que la profesión se halla ingresado para los asociados los cuales no
	 * poseen profesión registrada en el BUC
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 20/12/2012
	 * @param miembros
	 * @throws EleccionesDelegadosException
	 */
	private void validarProfesionesMiembros(List<DTOMiembroPlancha> miembros) throws EleccionesDelegadosException {
		for (DTOMiembroPlancha dtoMiembroPlancha : miembros) {
			if (!dtoMiembroPlancha.isTieneProfesion()
					&& (dtoMiembroPlancha.getProfesion() == null || "".equals(dtoMiembroPlancha.getProfesion()))) {
				throw new EleccionesDelegadosException(
						"Por favor ingrese la profesión" + " para el asociado identificado con el número de documento "
								+ dtoMiembroPlancha.getNumeroDocumento());
			}
		}
	}

	/**
	 * Valida que los integrantes de la plancha pertenezcan a la misma zona
	 * electoral del asociado que es cabeza de la plancha
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 13/12/2012
	 * @param numeroDocCabezaPlancha
	 * @param miembrosTitulares
	 */
	public void validarZonaElectoralIntegrantesPlancha(Long numeroDocCabezaPlancha, List<DTOMiembroPlancha> miembros)
			throws EleccionesDelegadosException {

		try {
			Long idZona = logicaFiltros.consultarZonaElectoralePorAsociado(numeroDocCabezaPlancha).getCodigoFiltro();
			EleZonaElectoral zonaElectAsociadoCabeza = daoZonaElectoral.findById(idZona.toString());

			for (DTOMiembroPlancha dtoMiembroPlancha : miembros) {
				Long idZonaIntegrante = logicaFiltros
						.consultarZonaElectoralePorAsociado(dtoMiembroPlancha.getNumeroDocumento()).getCodigoFiltro();
				EleZonaElectoral zonaElectAsociadoIntegrante = daoZonaElectoral.findById(idZonaIntegrante.toString());

				if (!zonaElectAsociadoCabeza.getCodigoZonaEle()
						.equals(zonaElectAsociadoIntegrante.getCodigoZonaEle())) {
					throw new EleccionesDelegadosException(UtilAcceso.getParametroFuenteS("mensajes", "noZonaCabPla")
							+ " " + dtoMiembroPlancha.getNumeroDocumento().toString() + " "
							+ UtilAcceso.getParametroFuenteS("mensajes", "noZonaCabPla2"));
				}
			}
		} catch (EleccionesDelegadosException e) {
			throw e;
		} catch (Exception e) {
			throw new EleccionesDelegadosException(
					"Se ha presentado un error al validar la zona electoral de" + " los integrantes de la plancha", e);
		}
	}

	public void registraPlanchaAsociadoAud(ElePlanchaAsociado entity) throws Exception {
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			ElePlanchaAsociadoAud aud = new ElePlanchaAsociadoAud();
			aud.setCodigoAsociado(entity.getEleAsociado().getCodigoAsociado());
			aud.setConsecutivoAud(obtenerConsecutivoElePlanchaAsociadoAud());
			aud.setConsecutivoPlancha(entity.getConsecutivoPlancha());
			aud.setFechaInscripcion(entity.getFechaInscripcion());
			aud.setFechaRegistro(new Timestamp(new Date().getTime()));
			aud.setNumeroInscrito(entity.getNumeroInscrito());
			aud.setTipoInscrito(entity.getTipoInscrito());
			daoAud.save(aud);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			throw e;
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
	}

	public void registrarMiembrosEnPlancha(List<DTOMiembroPlancha> miembros, String tipoInscrito,
			Long consecutivoPlancha, Session session) throws EleccionesDelegadosException {
		try {
			ElePlanchaAsociado planchaAsociado = null;
			for (DTOMiembroPlancha dtoMiembroPlancha : miembros) {

				if (dtoMiembroPlancha.getNumeroDocumento() != null && dtoMiembroPlancha.getApellidosNombres() != null) {
					planchaAsociado = new ElePlanchaAsociado();

					Long consecutivoPlanchaAso = GeneradorConsecutivos.getInstance()
							.getConsecutivo(ConstantesNamedQueries.QUERY_SEQ_ELE_PLANCHA_ASOCIADO, session);

					if (consecutivoPlanchaAso == null) {
						throw new EleccionesDelegadosException(
								"No se logró obtener el consecutivo" + " para la relación de asociados a planchas");
					}

					planchaAsociado.setConsecutivoPlanchaAso(consecutivoPlanchaAso);

					List<EleAsociado> asociado = new EleAsociadoDAO()
							.findByNumeroDocumento(dtoMiembroPlancha.getNumeroDocumento());

					EleAsociado entidadAsociado = asociado.get(0);

					planchaAsociado.setConsecutivoPlancha(consecutivoPlancha);
					planchaAsociado.setEleAsociado(entidadAsociado);
					planchaAsociado.setFechaInscripcion(new Timestamp(new Date().getTime()));
					planchaAsociado.setNumeroInscrito(new Byte(dtoMiembroPlancha.getPosicionPlancha()));
					planchaAsociado.setTipoInscrito(tipoInscrito);

					new ElePlanchaAsociadoDAO().save(planchaAsociado);

					if (!dtoMiembroPlancha.isTieneProfesion()) {
						entidadAsociado.setDescProfesion(dtoMiembroPlancha.getProfesion());
						new EleAsociadoDAO().update(entidadAsociado);
					}
				}
			}

		} catch (Exception e) {
			throw new EleccionesDelegadosException(e);
		}
	}

	/**
	 * Registra en la base de datos las excepciones que se generaron por cada
	 * asociado
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 11/01/2013
	 * @param miembros
	 * @param consecutivoPlancha
	 * @param session
	 * @throws EleccionesDelegadosException
	 */
	private void registrarExcepcionesPlancha(List<DTOMiembroPlancha> miembros, Long consecutivoPlancha)
			throws EleccionesDelegadosException {

		try {
			iLogicaPlanchaExcepcion = new LogicaPlanchaExcepcion();
			for (DTOMiembroPlancha dtoMiembroPlancha : miembros) {

				if (dtoMiembroPlancha.getNumeroDocumento() != null && dtoMiembroPlancha.getApellidosNombres() != null
						&& dtoMiembroPlancha.getObservacionAdicionMiembro() != null
						&& dtoMiembroPlancha.getObservacionAdicionMiembro().length() > 1) {
					Long codigoAsociado = LogicaAsociado.getInstance()
							.consultarCodigoAsociadoPorNumeroDocumento(dtoMiembroPlancha.getNumeroDocumento());

					String cadenaEliminar = "- " + UtilAcceso.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES, "msgAsociadoDebeAcreditarOficio");
					boolean excepcionProfesion = false;
					if (dtoMiembroPlancha.getObservacionAdicionMiembro().contains(cadenaEliminar)) {
						dtoMiembroPlancha.setObservacionAdicionMiembro(
								dtoMiembroPlancha.getObservacionAdicionMiembro().substring(0,
										dtoMiembroPlancha.getObservacionAdicionMiembro().indexOf(cadenaEliminar)));
						excepcionProfesion = true;
					}
					// Registrar excepción excepto si no tiene profesión:
					if (!excepcionProfesion) {
						iLogicaPlanchaExcepcion.registrarPlanchaExcepcion(consecutivoPlancha, codigoAsociado,
								dtoMiembroPlancha.getObservacionAdicionMiembro());
					}
				}
			}
		} catch (Exception e) {
			throw new EleccionesDelegadosException(e);
		} finally {
			iLogicaPlanchaExcepcion = null;
		}
	}

	/**
	 * Metodo que guarda los miembros de plancha ya hidratados.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 3/12/2012
	 * @param entity
	 * @throws Exception
	 */
	public void modificarMiembroPlancha(ElePlanchaAsociado entity) throws Exception {
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
			dao.merge(entity);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			throw e;
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
	}

	/**
	 * Metodo que guarda los miembros de plancha ya hidratados.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 3/12/2012
	 * @param entity
	 * @throws Exception
	 */
	public void guardarMiembroPlancha(ElePlanchaAsociado entity) throws Exception {
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
			dao.save(entity);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			throw e;
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
	}

	public void eliminarMiembroPlancha(ElePlanchaAsociado entity) throws Exception {
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			dao.delete(entity);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			throw e;
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
	}

	public void modificarPlancha(ElePlancha entity) throws Exception {
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
			new ElePlanchaDAO().merge(entity);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			throw e;
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
	}

	public void asignarEstadoRegistradoPlancha(Long consecutivoPlancha) throws EleccionesDelegadosException {
		try {
			ElePlancha elePlancha = new ElePlanchaDAO().findById(consecutivoPlancha);

			if (elePlancha != null) {
				elePlancha.setEstadoPlancha(COD_ESTADO_PLANCHA_REGISTRADA);
				modificarPlancha(elePlancha);
			}
		} catch (Exception e) {
			throw new EleccionesDelegadosException(e.getMessage(), e);
		}
	}

	/**
	 * Metodo que consulta los miembros de una plancha por cabeza.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 3/12/2012
	 * @param numintCabeza
	 * @return
	 * @throws Exception
	 */
	public List<ElePlanchaAsociado> consultaMiembrosPlanchaPorCabeza(Long numintCabeza) throws Exception {
		List<ElePlanchaAsociado> list = new ArrayList<ElePlanchaAsociado>();
		List<Object[]> elements = null;
		ElePlanchaAsociado entity = null;
		EleAsociado asociado = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			Query query = session.getNamedQuery("consulta.lista.plancha.por.cabeza");
			query.setLong("numIntCabezaPlancha", numintCabeza);
			elements = query.list();

			for (Object[] object : elements) {
				entity = new ElePlanchaAsociado();
				entity.setConsecutivoPlanchaAso((Long) object[0]);
				entity.setNumeroInscrito((Byte) object[1]);
				entity.setTipoInscrito(object[2].toString());
				entity.setFechaInscripcion((Timestamp) object[3]);
				asociado = logicaAsociado.consultarAsociadoPorCodigo((Long) object[4]);
				entity.setEleAsociado(asociado);
				entity.setConsecutivoPlancha((Long) object[5]);

				list.add(entity);
			}
		} catch (Exception e) {
			throw new Exception();
		} finally {
			session.close();
		}
		return list;
	}

	/**
	 * Metodo que obtiene el consucutico de la tabla ele_plancha_asociado.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 3/12/2012
	 * @return
	 * @throws Exception
	 */
	private Long obtenerConsecutivoElePlanchaAsociadoAud() throws Exception {
		Long consecutivo = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			Query query = session.getNamedQuery("consultar.secuencia.ele.plancha.asociado.aud");
			consecutivo = (Long) query.uniqueResult();
		} catch (Exception e) {
			throw e;
		} 
//		finally {
//			session.close();
//		}
		return consecutivo;
	}

	public ElePlanchaAsociado consultaPlanchaAsociadoPorCodigoAsociadoPlancha(Long consecutivoPlancha,
			Long codigoAsociado) throws Exception {
		ElePlanchaAsociado entity = new ElePlanchaAsociado();
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			Query query = session.getNamedQuery("consultar.plancha.asociado.por.asociado.plancha");
			query.setLong("consecutivoPlancha", consecutivoPlancha);
			query.setLong("codigoAsociado", codigoAsociado);
			Object[] element = (Object[]) query.uniqueResult();

			entity.setConsecutivoPlanchaAso((Long) element[0]);
			entity.setNumeroInscrito((Byte) element[1]);
			entity.setTipoInscrito(element[2].toString());
			entity.setFechaInscripcion((Timestamp) element[3]);
			entity.setEleAsociado(logicaAsociado.consultarAsociadoPorCodigo((Long) element[4]));
			entity.setConsecutivoPlancha((Long) element[5]);

		} catch (Exception e) {
			throw new Exception("Error consultando la informacón de plancha asociado por código de asociado y plancha");
		}
		return entity;
	}

	public ElePlanchaAsociado consultaPlanchaAsociadoPorCodigoPlanchaNumInscrito(Long consecutivoPlancha,
			Byte numInscrito, String tipoInscrito) throws Exception {
		ElePlanchaAsociado entity = new ElePlanchaAsociado();
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			Query query = session.getNamedQuery("consulta.plancha.asociado.por.plancha.num.insrcito");
			query.setLong("consecutivoPlancha", consecutivoPlancha);
			query.setLong("numInscrito", numInscrito);
			query.setString("tipoInscrito", tipoInscrito);
			Object[] element = (Object[]) query.uniqueResult();

			if (element != null && element.length > 0) {
				entity.setConsecutivoPlanchaAso((Long) element[0]);
				entity.setNumeroInscrito((Byte) element[1]);
				entity.setTipoInscrito(element[2].toString());
				entity.setFechaInscripcion((Timestamp) element[3]);
				entity.setEleAsociado(logicaAsociado.consultarAsociadoPorCodigo((Long) element[4]));
				entity.setConsecutivoPlancha((Long) element[5]);
			}

		} catch (Exception e) {
			throw new Exception("Error consultando la informacón de plancha asociado por código de asociado y plancha");
		}
		return entity;
	}

	/**
	 * metodo que valida si el asociado existe en la plancha y que posicion tiene en
	 * ella.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 3/12/2012
	 * @param consecutivoPlancha
	 * @param codigoAsociado
	 * @return
	 * @throws Exception
	 */
	public Long consultarPosicionPorPlanchaAsociado(Long consecutivoPlancha, Long codigoAsociado) throws Exception {
		Long posicionPlancha = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			Query query = session.getNamedQuery("consultar.posicion.por.plancha.asociado");
			query.setLong("consecutivoPlancha", consecutivoPlancha);
			query.setLong("codigoAsociado", codigoAsociado);
			// query.setString("tipoInscrito", tipoInscrito);
			posicionPlancha = (Long) query.uniqueResult();
		} catch (Exception e) {
			throw new Exception("Error consulatndo posicion de la plancha por codigo de la plancha y del asociado");
		}
		return posicionPlancha;
	}

	/**
	 * Metodo que valida si la posicion a ingresar esta ocupada o no por otro
	 * asociado.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 3/12/2012
	 * @param consecutivoPlancha
	 * @param numeroInscrito
	 * @return
	 * @throws Exception
	 */
	public Boolean consultaSiEsPosicionNueva(Long consecutivoPlancha, Long numeroInscrito, String tipoInscrito)
			throws Exception {
		Boolean esNuevo = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			Query query = session.getNamedQuery("consultar.si.es.posicion.nueva");
			query.setLong("consecutivoPlancha", consecutivoPlancha);
			query.setLong("numeroInscrito", numeroInscrito);
			query.setString("tipoInscrito", tipoInscrito);
			Long numero = (Long) query.uniqueResult();
			if (numero != null) {
				if (numero > 0) {
					esNuevo = false;
				} else {
					esNuevo = true;
				}
			} else {
				esNuevo = true;
			}
		} catch (Exception e) {
			throw new Exception("Error consultando si es nueva posicion en la plancha");
		}
		return esNuevo;
	}

	/**
	 * Valida que si el asociado fue empleado, este se halla retirado como mínimo
	 * hace 3 años
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 3/12/2012
	 * @param numeroDocumento
	 * @throws EleccionesDelegadosException
	 */
	public String validarEmpleadoFechaRetiro(Long numeroDocumento) throws EleccionesDelegadosException {

		List<Object[]> empleadosActivosGEC = obtenerAsosEmpleadosRetirados();
		Date fechaRetiro = null;

		for (Object[] objects : empleadosActivosGEC) {
			if (objects != null && numeroDocumento.equals((Long) objects[0])) {
				fechaRetiro = (Date) objects[1];
				break;
			}
		}

		if (fechaRetiro != null) {

			Date fechaMinimaDesvinculacion = DateManipultate.stringToDate(
					UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
							"param.fecha.minima.retiro.empleado"),
					"yyyy-MM-dd");
			boolean fechaRetiroMenorAretiro = DateManipultate.comparaFechas(fechaRetiro, fechaMinimaDesvinculacion);

			if (!fechaRetiroMenorAretiro) {
				// throw new EleccionesDelegadosException(MessageFormat.format(
				// UtilAcceso.getParametroFuenteS(
				// ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
				// "msgErrorEmpleadoRetiradoMenos3Annos"),
				// numeroDocumento.toString()));

				return MessageFormat.format(UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
						"msgErrorEmpleadoRetiradoMenos3Annos"), numeroDocumento.toString()) + "<br/>";

			} else {
				return "";
			}
		}

		return null;
	}

	/**
	 * Halla los miembros comunes entre la lista de suplentes y la lista de
	 * titulares o principales
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 4/12/2012
	 * @param titulares
	 * @param suplentes
	 * @return List<DTOMiembroPlancha>
	 */
	public List<Long> hallarMiembroComunesListas(List<DTOMiembroPlancha> titulares, List<DTOMiembroPlancha> suplentes) {

		List<Long> numerosDocumentosMiembrosTitulares = new ArrayList<Long>();
		List<Long> numerosDocumentosMiembrosSuplentes = new ArrayList<Long>();
		List<Long> numerosDocumentosComunes = new ArrayList<Long>();

		for (DTOMiembroPlancha dtoMiembroPlancha : titulares) {
			if (dtoMiembroPlancha.getNumeroDocumento() != null) {
				numerosDocumentosMiembrosTitulares.add(dtoMiembroPlancha.getNumeroDocumento());
			}
		}

		for (DTOMiembroPlancha dtoMiembroPlancha : suplentes) {
			if (dtoMiembroPlancha.getNumeroDocumento() != null) {
				numerosDocumentosMiembrosSuplentes.add(dtoMiembroPlancha.getNumeroDocumento());
			}
		}

		for (Long miembro : numerosDocumentosMiembrosTitulares) {
			if (numerosDocumentosMiembrosSuplentes.contains(miembro)) {
				numerosDocumentosComunes.add(miembro);
			}
		}
		return numerosDocumentosComunes;
	}

	public DTOInformacionPlancha finalizarEnviarPlancha(List<DTOMiembroPlancha> miembrosTitulares,
			List<DTOMiembroPlancha> miembrosSuplentes, Long consecutivoPlancha, Long numDocUsuarioRegistra,
			boolean aplicaValidaciones, boolean admiteSuplentes) throws EleccionesDelegadosException {

		StringBuffer excepcionesEnvioPlancha = new StringBuffer();
		boolean usuarioQueRegistraEsTitular = Boolean.FALSE;
		for (DTOMiembroPlancha dtoMiembroPlancha : miembrosTitulares) {

			if (dtoMiembroPlancha.getNumeroDocumento() != null) {
				if (!asociadoPertenceOtraPlancha(dtoMiembroPlancha.getNumeroDocumento().toString(), consecutivoPlancha)
						.isEmpty()) {
					throw new EleccionesDelegadosException(
							MessageFormat.format(
									UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
											"msgErrorAsociadoPerteneceOtraPlancha"),
									dtoMiembroPlancha.getNumeroDocumento()));
				}
			}

			if (dtoMiembroPlancha.getNumeroDocumento() != null
					&& dtoMiembroPlancha.getNumeroDocumento().equals(numDocUsuarioRegistra)) {
				usuarioQueRegistraEsTitular = Boolean.TRUE;
				break;
			}
		}

		if (!usuarioQueRegistraEsTitular) {
			throw new EleccionesDelegadosException("Estimado Asociado, recuerde que el "
					+ "formulario debe ser diligenciado únicamente por uno de los miembros principales de la plancha.");
			// excepcionesEnvioPlancha.append("Estimado Asociado, recuerde que el "
			// +
			// "formulario debe ser diligenciado únicamente por uno de los miembros
			// principales de la plancha. <br/>");
		}

		List<Long> miembrosComunes = hallarMiembroComunesListas(miembrosTitulares, miembrosSuplentes);

		if (miembrosComunes != null && !miembrosComunes.isEmpty()) {
			StringBuffer asociadosComunes = new StringBuffer();
			asociadosComunes.append("Estimado asociado, los asociados con las siguientes"
					+ " cédulas se encuentran tanto en la lista de titulares como la de suplentes: ");
			for (Long miembroComun : miembrosComunes) {
				asociadosComunes.append(miembroComun.toString() + ", ");
			}
			asociadosComunes.append(
					"por favor corrija la información ingresada de forma que estos no existan en ambas listas");
			throw new EleccionesDelegadosException(asociadosComunes.toString());
		}

		miembrosSuplentes = obtenerListaRealMiembros(miembrosSuplentes);

		if (aplicaValidaciones && miembrosSuplentes.size() < 1) {
			// throw new EleccionesDelegadosException(
			// "Estimado Asociado, recuerde que la plancha debe "
			// + "estar conformada por mínimo un miembro suplente");

			excepcionesEnvioPlancha.append("- Estimado Asociado, recuerde que la plancha debe "
					+ "estar conformada por mínimo un miembro suplente. <br/>");
		} else {
			for (DTOMiembroPlancha miembroPlancha : miembrosSuplentes) {

				if (!asociadoPertenceOtraPlancha(miembroPlancha.getNumeroDocumento().toString(), consecutivoPlancha)
						.isEmpty()) {
					throw new EleccionesDelegadosException(
							MessageFormat.format(
									UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
											"msgErrorAsociadoPerteneceOtraPlancha"),
									miembroPlancha.getNumeroDocumento()));
				}
			}
		}

		miembrosTitulares = obtenerListaRealMiembros(miembrosTitulares);
		if (miembrosSuplentes.size() > miembrosTitulares.size()) {
			// throw new EleccionesDelegadosException(
			// "Estimado Asociado, recuerde que la cantidad de suplentes no "
			// + "debe ser mayor a la cantidad de miembros principales ");

			excepcionesEnvioPlancha.append("Estimado Asociado, recuerde que la cantidad de suplentes no "
					+ "debe ser mayor a la cantidad de miembros principales. <br/>");
		}

		modificarMiembrosPlanchas(miembrosTitulares, TIPO_INSCRITO_TITULAR, consecutivoPlancha, false);
		if (admiteSuplentes) {
			modificarMiembrosPlanchas(miembrosSuplentes, TIPO_INSCRITO_SUPLENTE, consecutivoPlancha, false);
		}

		ElePlancha plancha = new ElePlanchaDAO().findById(consecutivoPlancha);

		Session session = HibernateSessionFactoryElecciones2012.getSession();

		DTOInformacionPlancha objPlancha = new DTOInformacionPlancha();
		objPlancha.setEstadoPlancha(COD_ESTADO_PLANCHA_REGISTRADA);
		objPlancha.setDescripcionExcepciones(excepcionesEnvioPlancha.toString());
		Transaction tr = session.beginTransaction();
		try {
			plancha.setFechaEnvio(new Timestamp(new Date().getTime()));
			plancha.setEstadoPlancha(COD_ESTADO_PLANCHA_REGISTRADA);
			new ElePlanchaDAO().merge(plancha);

			miembrosTitulares.get(0).setObservacionAdicionMiembro(excepcionesEnvioPlancha.toString());
			registrarExcepcionesPlancha(miembrosTitulares.subList(0, 1), consecutivoPlancha);

			tr.commit();
		} catch (Exception e) {
			if (tr != null && tr.isActive()) {
				tr.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
		}

		return objPlancha;
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

	/**
	 * Obtiene las planchas inscritas por regional
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 7/12/2012
	 * @param codigoZonaElect
	 * @return String
	 * @throws EleccionesDelegadosException
	 */
	public String obtenerHtmlPlanchasAdmitidasPorRegional(Long codigoZonaElect, String idUsuario, int indicador)
			throws EleccionesDelegadosException {

		List<DTOInformacionPlancha> planchasInscritaPorRegiona = consultarPlanchasPorZonaElect(codigoZonaElect,
				COD_ESTADO_PLANCHA_ADMITIDA, idUsuario);

		if (planchasInscritaPorRegiona == null || planchasInscritaPorRegiona.size() < 1) {
			throw new EleccionesDelegadosException("No se encontraron planchas"
					+ " admitidas para la zona electoral seleccionada o no tiene acceso a las planchas de esa zona, ya que"
					+ " usted señor usuario no pertenece a esa zona electoral");
		}

		StringBuffer htmlTablas = new StringBuffer();

		htmlTablas
				.append("<table border=\"1\" style=\"border-collapse:collapse;\" cellspacing=\"2\" cellpadding=\"2\">");
		htmlTablas.append("<tr>");
		htmlTablas.append(
				"<td colspan=\"2\" style=\"font-weight:bold;border:1px Solid gray;font-size:12px;\">ZONA ELECTORAL No. "
						+ planchasInscritaPorRegiona.get(0).getNumeroZonaElectoral() + " - "
						+ planchasInscritaPorRegiona.get(0).getDescripcionZonaElectoral() + ":  </td>");
		htmlTablas.append("</tr>");

		boolean control = false;

		for (DTOInformacionPlancha dtoInformacionPlancha : planchasInscritaPorRegiona) {

			List<EleEstadoPlancha> estadosPlancha = LogicaEstadoPlancha.getInstance()
					.findByProperty("elePlancha.consecutivoPlancha", dtoInformacionPlancha.getConsecutivoPlancha());

			if (indicador == 1) {
				// if(
				// dtoInformacionPlancha.getNumeroPlancha().equals(NO_ASIGNADO)
				// ){
				for (int i = 0; i < estadosPlancha.size(); i++) {
					if (estadosPlancha.get(i).getEstadoPlancha().equals(COD_ESTADO_PLANCHA_RECURSO)) {
						control = true;
					}
				}
				if (control) {
					cargarDatos(htmlTablas, dtoInformacionPlancha);
				}
				control = false;
				// }
			} else {
				/*
				 * if(dtoInformacionPlancha.getNumeroPlancha().equals(NO_ASIGNADO ) ){
				 * cargarDatos(htmlTablas, dtoInformacionPlancha); }
				 */
				for (int i = 0; i < estadosPlancha.size(); i++) {
					if (estadosPlancha.get(i).getEstadoPlancha().equals(COD_ESTADO_PLANCHA_RECURSO)) {
						control = true;
					}
				}
				if (!control) {
					cargarDatos(htmlTablas, dtoInformacionPlancha);
				}
				control = false;
			}
		}

		htmlTablas.append("</table>");
		return htmlTablas.toString();
	}

	public String obtenerHtmlPlanchasAdmitidasPorZonaEspec(Long codigoZonaElect, String idUsuario, int indicador)
			throws EleccionesDelegadosException {

		List<DTOInformacionPlancha> planchasInscritaPorRegiona = consultarPlanchasPorZonaElectEspe(codigoZonaElect,
				COD_ESTADO_PLANCHA_ADMITIDA, idUsuario);

		if (planchasInscritaPorRegiona == null || planchasInscritaPorRegiona.size() < 1) {
			throw new EleccionesDelegadosException("No se encontraron planchas"
					+ " admitidas para la zona electoral seleccionada o no tiene acceso a las planchas de esa zona, ya que"
					+ " usted señor usuario no pertenece a esa zona electoral");
		}

		StringBuffer htmlTablas = new StringBuffer();

		htmlTablas
				.append("<table border=\"1\" style=\"border-collapse:collapse;\" cellspacing=\"2\" cellpadding=\"2\">");
		htmlTablas.append("<tr>");
		htmlTablas.append(
				"<td colspan=\"2\" style=\"font-weight:bold;border:1px Solid gray;font-size:12px;\">ZONA ELECTORAL No. "
						+ planchasInscritaPorRegiona.get(0).getNumeroZonaElectoral() + " - "
						+ planchasInscritaPorRegiona.get(0).getDescripcionZonaElectoral() + ":  </td>");
		htmlTablas.append("</tr>");

		boolean control = false;

		for (DTOInformacionPlancha dtoInformacionPlancha : planchasInscritaPorRegiona) {

			List<EleEstadoPlancha> estadosPlancha = LogicaEstadoPlancha.getInstance()
					.findByProperty("elePlancha.consecutivoPlancha", dtoInformacionPlancha.getConsecutivoPlancha());

			if (indicador == 1) {
				// if(
				// dtoInformacionPlancha.getNumeroPlancha().equals(NO_ASIGNADO)
				// ){
				for (int i = 0; i < estadosPlancha.size(); i++) {
					if (estadosPlancha.get(i).getEstadoPlancha().equals(COD_ESTADO_PLANCHA_RECURSO)) {
						control = true;
					}
				}
				if (control) {
					cargarDatos(htmlTablas, dtoInformacionPlancha);
				}
				control = false;
				// }
			} else {
				/*
				 * if(dtoInformacionPlancha.getNumeroPlancha().equals(NO_ASIGNADO ) ){
				 * cargarDatos(htmlTablas, dtoInformacionPlancha); }
				 */
				for (int i = 0; i < estadosPlancha.size(); i++) {
					if (estadosPlancha.get(i).getEstadoPlancha().equals(COD_ESTADO_PLANCHA_RECURSO)) {
						control = true;
					}
				}
				if (!control) {
					cargarDatos(htmlTablas, dtoInformacionPlancha);
				}
				control = false;
			}
		}

		htmlTablas.append("</table>");
		return htmlTablas.toString();
	}

	public void cargarDatos(StringBuffer htmlTablas, DTOInformacionPlancha dtoInformacionPlancha) {
		htmlTablas.append("<tr>");
		htmlTablas.append("<td style=\"font-weight:bold;font-size:11px;\">Plancha No.</td>");
		htmlTablas.append(
				"<td style=\"font-weight:bold;font-size:10px;\">" + dtoInformacionPlancha.getNumeroPlancha() + "</td>");
		htmlTablas.append("</tr>");
		htmlTablas.append("<tr>");
		htmlTablas.append("<td style=\"font-weight:bold;font-size:11px;\">Nombre Cabeza</td>");
		htmlTablas.append("<td style=\"font-weight:bold;font-size:10px;\">"
				+ dtoInformacionPlancha.getNombresApellidosCabeza() + "</td>");
		htmlTablas.append("</tr>");
		htmlTablas.append("<tr>");
		htmlTablas.append("<td style=\"font-weight:bold;font-size:11px;\">Identificación Cabeza</td>");
		htmlTablas.append("<td style=\"font-weight:bold;font-size:10px;\">"
				+ dtoInformacionPlancha.getNumeroIdentificacionCabeza() + "</td>");
		htmlTablas.append("</tr>");
		htmlTablas.append("<tr>");
		htmlTablas.append("<td style=\"font-weight:bold;font-size:11px;\">Fecha Inscripción</td>");
		htmlTablas.append("<td style=\"font-weight:bold;font-size:10px;\">"
				+ dtoInformacionPlancha.getFechaHoraInscripcionPlancha() + "</td>");
		htmlTablas.append("</tr>");
		htmlTablas.append("<tr>");
		htmlTablas.append("<td colspan=\"2\"><br/></td>");
		htmlTablas.append("</tr>");
	}

	public List<DTOInformacionPlancha> obtenerPlanchasPorRegional() {
		return null;
	}

	/**
	 * Consulta las planchas que se encuentran en cierto estado y que pertenecen a
	 * determinada zona electoral
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 7/12/2012
	 * @param codigoZonaElect
	 * @param codEstadoPlancha
	 * @return
	 * @throws Exception
	 */
	public List<DTOInformacionPlancha> consultarPlanchasPorZonaElect(Long codigoZonaElect, String codEstadoPlancha,
			String idUsuario) throws EleccionesDelegadosException {
		List<DTOInformacionPlancha> list = new ArrayList<DTOInformacionPlancha>();
		List<Object[]> elements = null;
		DTOInformacionPlancha dtoInformacionPlancha = null;
		Session session = null;
		try {

			logicaUsuarioComision = new LogicaUsuarioComision();

			List<Long> zonasElectoralesUsuario = logicaUsuarioComision.consultarZonaElectUsuarioComision(idUsuario);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			StringBuffer sql = new StringBuffer();

			sql.append("SELECT DISTINCT ");
			sql.append("EP.NUMERO_PLANCHA, ");
			sql.append("EP.FECHA_ENVIO, ");
			sql.append("CL.NITCLI, ");
			sql.append("CL.NOMCLI, ");
			sql.append("EZE.CODIGO_ZONA_ELE, ");
			sql.append("EZE.DESCRIPCION_ZONA_ELE, ");
			sql.append("EP.CONSECUTIVO_PLANCHA ");
			sql.append("FROM ");
			sql.append("ELECDB.ELE_PLANCHA EP ");
			sql.append("INNER JOIN ELECDB.ELE_ZONA_ELECTORAL EZE ON ");
			sql.append("	EP.CODIGO_ZONA_ELE = EZE.CODIGO_ZONA_ELE ");
			sql.append("INNER JOIN ELECDB.ELE_PLANCHA_ASOCIADO EPA ON ");
			sql.append("	EP.CONSECUTIVO_PLANCHA = EPA.CONSECUTIVO_PLANCHA ");
			sql.append("INNER JOIN ELECDB.CLIMAE CL ON ");
			sql.append("	EPA.CODIGO_ASOCIADO = CL.NUMINT ");

			if (zonasElectoralesUsuario != null && !zonasElectoralesUsuario.isEmpty()) {
				sql.append("INNER JOIN ELECDB.ELE_USUARIO_COMISION EUC ON ");
				sql.append("	EZE.CODIGO_ZONA_ELE = EUC.CODIGO_ZONA_ELE ");
			}
			sql.append("WHERE ");
			sql.append("EP.ESTADO_PLANCHA = :estadoPlanchaInscrita AND ");
			sql.append("EPA.NUMERO_INSCRITO = :codigoNumeroInscritoCabeza AND ");
			sql.append("EZE.CODIGO_ZONA_ELE = :codigoZonaElectoral AND ");
			sql.append("EPA.TIPO_INSCRITO = :codigoTipoInscritoPrincipal ");

			if (zonasElectoralesUsuario != null && !zonasElectoralesUsuario.isEmpty()) {
				sql.append("and EUC.CODIGO_ZONA_ELE IN (:codigoZonas)");
			}

			sql.append("AND EP.CODIGO_ZONA_ELE_ESPECIAL IS NULL");

			/*
			 * if(indicador == 1){ sql.append("and EP.CONSECUTIVO_PLANCHA in "); }
			 */

			session = HibernateSessionFactoryElecciones2012.getSession();
			SQLQuery query = session.createSQLQuery(sql.toString());

			query.addScalar("NUMERO_PLANCHA", Hibernate.LONG);
			query.addScalar("FECHA_ENVIO", Hibernate.TIMESTAMP);
			query.addScalar("NITCLI", Hibernate.LONG);
			query.addScalar("NOMCLI", Hibernate.STRING);
			query.addScalar("CODIGO_ZONA_ELE", Hibernate.STRING);
			query.addScalar("DESCRIPCION_ZONA_ELE", Hibernate.STRING);
			query.addScalar("CONSECUTIVO_PLANCHA", Hibernate.LONG);

			query.setString("estadoPlanchaInscrita", codEstadoPlancha);
			query.setLong("codigoNumeroInscritoCabeza", Long.parseLong(COD_NUMERO_INSCRITO_CABEZA_PLANCHA));
			query.setLong("codigoZonaElectoral", codigoZonaElect);
			query.setLong("codigoTipoInscritoPrincipal", Long.parseLong(TIPO_INSCRITO_TITULAR));

			if (zonasElectoralesUsuario != null && !zonasElectoralesUsuario.isEmpty()) {
				query.setParameterList("codigoZonas", zonasElectoralesUsuario);
			}

			elements = query.list();

			for (Object[] object : elements) {
				dtoInformacionPlancha = new DTOInformacionPlancha();
				dtoInformacionPlancha.setNumeroPlancha(object[0] != null ? object[0].toString() : NO_ASIGNADO);
				dtoInformacionPlancha.setFechaHoraInscripcionPlancha(
						object[1] != null ? formatter.format((Timestamp) object[1]) : null);
				dtoInformacionPlancha.setNumeroIdentificacionCabeza(object[2] != null ? object[2].toString() : null);
				dtoInformacionPlancha.setNombresApellidosCabeza(object[3] != null ? object[3].toString() : null);
				dtoInformacionPlancha.setNumeroZonaElectoral(object[4] != null ? object[4].toString() : null);
				dtoInformacionPlancha.setDescripcionZonaElectoral(object[5] != null ? object[5].toString() : null);
				dtoInformacionPlancha.setConsecutivoPlancha(object[6] != null ? (Long) object[6] : null);

				list.add(dtoInformacionPlancha);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new EleccionesDelegadosException(e);
		} finally {
			session.close();
		}
		return list;
	}

	public List<DTOInformacionPlancha> consultarPlanchasPorZonaElectEspe(Long codigoZonaElect, String codEstadoPlancha,
			String idUsuario) throws EleccionesDelegadosException {
		List<DTOInformacionPlancha> list = new ArrayList<DTOInformacionPlancha>();
		List<Object[]> elements = null;
		DTOInformacionPlancha dtoInformacionPlancha = null;
		Session session = null;
		try {

			logicaUsuarioComision = new LogicaUsuarioComision();

			List<Long> zonasElectoralesUsuario = logicaUsuarioComision.consultarZonaElectEspUsuarioComision(idUsuario);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			StringBuffer sql = new StringBuffer();

			sql.append("SELECT DISTINCT ");
			sql.append("EP.NUMERO_PLANCHA, ");
			sql.append("EP.FECHA_ENVIO, ");
			sql.append("CL.NITCLI, ");
			sql.append("CL.NOMCLI, ");
			sql.append("EZE.CODIGO_ZONA_ELE, ");
			sql.append("EZE.DESCRIPCION_ZONA_ELE, ");
			sql.append("EP.CONSECUTIVO_PLANCHA ");
			sql.append("FROM ");
			sql.append("ELECDB.ELE_PLANCHA EP ");
			sql.append("INNER JOIN ELECDB.ELE_ZONA_ELECTORAL_ESPECIAL EZE ON ");
			sql.append("	EP.CODIGO_ZONA_ELE_ESPECIAL = EZE.CODIGO_ZONA_ELE ");
			sql.append("INNER JOIN ELECDB.ELE_PLANCHA_ASOCIADO EPA ON ");
			sql.append("	EP.CONSECUTIVO_PLANCHA = EPA.CONSECUTIVO_PLANCHA ");
			sql.append("INNER JOIN ELECDB.CLIMAE CL ON ");
			sql.append("	EPA.CODIGO_ASOCIADO = CL.NUMINT ");

			if (zonasElectoralesUsuario != null && !zonasElectoralesUsuario.isEmpty()) {
				sql.append("INNER JOIN ELECDB.ELE_USUARIO_COMISION EUC ON ");
				sql.append("	EZE.CODIGO_ZONA_ELE = EUC.CODIGO_ZONA_ELE_ESPE ");
			}
			sql.append("WHERE ");
			sql.append("EP.ESTADO_PLANCHA = :estadoPlanchaInscrita AND ");
			sql.append("EPA.NUMERO_INSCRITO = :codigoNumeroInscritoCabeza AND ");
			sql.append("EZE.CODIGO_ZONA_ELE = :codigoZonaElectoral AND ");
			sql.append("EPA.TIPO_INSCRITO = :codigoTipoInscritoPrincipal ");

			if (zonasElectoralesUsuario != null && !zonasElectoralesUsuario.isEmpty()) {
				sql.append("and EUC.CODIGO_ZONA_ELE_ESPE IN (:codigoZonas)");
			}

			/*
			 * if(indicador == 1){ sql.append("and EP.CONSECUTIVO_PLANCHA in "); }
			 */

			session = HibernateSessionFactoryElecciones2012.getSession();
			SQLQuery query = session.createSQLQuery(sql.toString());

			query.addScalar("NUMERO_PLANCHA", Hibernate.LONG);
			query.addScalar("FECHA_ENVIO", Hibernate.TIMESTAMP);
			query.addScalar("NITCLI", Hibernate.LONG);
			query.addScalar("NOMCLI", Hibernate.STRING);
			query.addScalar("CODIGO_ZONA_ELE", Hibernate.STRING);
			query.addScalar("DESCRIPCION_ZONA_ELE", Hibernate.STRING);
			query.addScalar("CONSECUTIVO_PLANCHA", Hibernate.LONG);

			query.setString("estadoPlanchaInscrita", codEstadoPlancha);
			query.setLong("codigoNumeroInscritoCabeza", Long.parseLong(COD_NUMERO_INSCRITO_CABEZA_PLANCHA));
			query.setLong("codigoZonaElectoral", codigoZonaElect);
			query.setLong("codigoTipoInscritoPrincipal", Long.parseLong(TIPO_INSCRITO_TITULAR));

			if (zonasElectoralesUsuario != null && !zonasElectoralesUsuario.isEmpty()) {
				query.setParameterList("codigoZonas", zonasElectoralesUsuario);
			}

			elements = query.list();

			for (Object[] object : elements) {
				dtoInformacionPlancha = new DTOInformacionPlancha();
				dtoInformacionPlancha.setNumeroPlancha(object[0] != null ? object[0].toString() : NO_ASIGNADO);
				dtoInformacionPlancha.setFechaHoraInscripcionPlancha(
						object[1] != null ? formatter.format((Timestamp) object[1]) : null);
				dtoInformacionPlancha.setNumeroIdentificacionCabeza(object[2] != null ? object[2].toString() : null);
				dtoInformacionPlancha.setNombresApellidosCabeza(object[3] != null ? object[3].toString() : null);
				dtoInformacionPlancha.setNumeroZonaElectoral(object[4] != null ? object[4].toString() : null);
				dtoInformacionPlancha.setDescripcionZonaElectoral(object[5] != null ? object[5].toString() : null);
				dtoInformacionPlancha.setConsecutivoPlancha(object[6] != null ? (Long) object[6] : null);

				list.add(dtoInformacionPlancha);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new EleccionesDelegadosException(e);
		} finally {
			session.close();
		}
		return list;
	}

	public void generarNumeracionPlanchas(Long codZonaElectoral, String idUsuario, boolean esZonaEspecial)
			throws EleccionesDelegadosException {

		Transaction tr = null;
		Session session = null;
		try {
			Random aleatorio = new Random();
			Integer azar;

			List<DTOInformacionPlancha> listaPlanchas = null;
			if (esZonaEspecial) {
				listaPlanchas = consultarPlanchasPorZonaElectEspe(codZonaElectoral, COD_ESTADO_PLANCHA_ADMITIDA,
						idUsuario);
			} else {
				listaPlanchas = consultarPlanchasPorZonaElect(codZonaElectoral, COD_ESTADO_PLANCHA_ADMITIDA, idUsuario);
			}

			List<ElePlancha> planchasPorZonaElectoral = new ArrayList<ElePlancha>();
			List<Integer> numerosAleatorios = new ArrayList<Integer>();

			for (DTOInformacionPlancha infoPlancha : listaPlanchas) {
				ElePlancha plancha = new ElePlanchaDAO().findById(infoPlancha.getConsecutivoPlancha());
				if (plancha != null && plancha.getNumeroPlancha() == null) {
					planchasPorZonaElectoral.add(plancha);
				} else if (plancha != null && plancha.getNumeroPlancha() != null) {
					numerosAleatorios.add(plancha.getNumeroPlancha());
				}
			}

			if (planchasPorZonaElectoral.isEmpty()) {
				throw new EleccionesDelegadosException(
						"No se hallaron planchas admitidas " + "pendientes por asignación de número de plancha");
			}

			Integer numeroPlanchasPorZona = planchasPorZonaElectoral.size();
			Integer totalNumerados = numerosAleatorios.size();

			for (int i = 0; i < numeroPlanchasPorZona; i++) {
				while (true) {
					// Hacemos esto porque nextInt genera valores incluido el
					// cero y excluye el valor pasado como parámetro
					azar = aleatorio.nextInt(numeroPlanchasPorZona + totalNumerados + 1);
					if (!numerosAleatorios.contains(azar) && azar != 0) {
						numerosAleatorios.add(azar);
						planchasPorZonaElectoral.get(i).setNumeroPlancha(azar);
						break;
					}
				}
			}

			session = HibernateSessionFactoryElecciones2012.getSession();
			tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
			for (ElePlancha plancha : planchasPorZonaElectoral) {
				new ElePlanchaDAO().merge(plancha);
			}
			tr.commit();

		} catch (EleccionesDelegadosException e) {
			throw e;
		} catch (Exception e) {

			if (tr != null && tr.isActive()) {
				tr.rollback();
			}

			throw new EleccionesDelegadosException(
					"Se ha presentado un error" + " al intentar generar la numeración por plancha", e);
		} finally {
			if (session != null) {
				session.close();
			}

			session = null;
			tr = null;
		}
	}

	/**
	 * Actualiza el estado de ELE_PLANCHA
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario
	 *         Alejandro Acevedo</a> - GSISIN <br>
	 * @param consecPlancha
	 * @param estado
	 * @return boolean
	 * @throws EleccionesDelegadosException , Exception
	 * 
	 */
	public boolean actualizarEstadoPlancha(Long consecPlancha, String estado)
			throws EleccionesDelegadosException, Exception {

		boolean actualizacionOK = false;
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			ElePlancha plancha = new ElePlanchaDAO().findById(consecPlancha);

			if (plancha == null) {
				throw new EleccionesDelegadosException("No se ha encontrado plancha con consecutivo " + consecPlancha);
			}

			plancha.setEstadoPlancha(estado);

			new ElePlanchaDAO().merge(plancha);

			Long codigoFormatoPlancha = GeneradorConsecutivos.getInstance().getConsecutivo(
					"obtener.consecutivo.formato.plancha", HibernateSessionFactoryElecciones2012.getSession());
			tr.commit();
			actualizacionOK = true;
		} catch (EleccionesDelegadosException e) {
			throw e;
		} catch (Exception e) {
			tr.rollback();
			throw new EleccionesDelegadosException(
					"Se ha presentado un error" + " al intentar actualizar el estado de la plancha", e);
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
		return actualizacionOK;
	}

	public String consultarNumeroZonaPorCodigoZona(String codigoZona) {
		Session session = null;
		Query query = null;
		String numeroZonaElectoral = null;
		try {
			session = HibernateSessionFactoryElecciones2012.getSession();
			query = session.getNamedQuery(ConstantesNamedQueries.QUERY_NUMERO_ZONA_POR_CODIGOZONA);
			query.setString("codigoZona", codigoZona);
			Object obj = query.uniqueResult();
			if (obj != null) {
				numeroZonaElectoral = (String) obj;
			}
			return numeroZonaElectoral;
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
			query = null;
		}
	}

	public void generarNumeracionPlanchasRecurso(Long codZonaElectoral, String idUsuario, boolean esZonaEspecial)
			throws EleccionesDelegadosException {

		List<DTOInformacionPlancha> listaPlanchas = consultarPlanchasPorZonaElect(codZonaElectoral,
				COD_ESTADO_PLANCHA_RECURSO, idUsuario);

		if (listaPlanchas != null && listaPlanchas.size() > 0) {
			throw new EleccionesDelegadosException(
					"Existe por lo menos una plancha en estado [EN RECURSO], por lo tanto no se puede generar la numeración de planchas para la Zona Electoral seleccionada");
		}

		generarNumeracionPlanchas(codZonaElectoral, idUsuario, esZonaEspecial);
	}

	public boolean actualizarFechaEnvio(Long consecPlancha) throws EleccionesDelegadosException, Exception {

		boolean actualizacionOK = false;
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			ElePlancha plancha = new ElePlanchaDAO().findById(consecPlancha);

			if (plancha == null) {
				throw new EleccionesDelegadosException("No se ha encontrado plancha con consecutivo " + consecPlancha);
			}

			plancha.setFechaEnvio(new Timestamp(new Date().getTime()));

			new ElePlanchaDAO().merge(plancha);
			tr.commit();
			actualizacionOK = true;
		} catch (Exception e) {
			tr.rollback();
			throw e;
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
		return actualizacionOK;
	}
	
	public void asignarTipoFirmaPlancha(Long consecutivoPlancha, Long tipoFirma) throws EleccionesDelegadosException {
		try {
			ElePlancha elePlancha = new ElePlanchaDAO().findById(consecutivoPlancha);

			if (elePlancha != null) {
				elePlancha.setFirmaVirtual(tipoFirma);
				modificarPlancha(elePlancha);
			}
		} catch (Exception e) {
			throw new EleccionesDelegadosException(e.getMessage(), e);
		}
	}

}
