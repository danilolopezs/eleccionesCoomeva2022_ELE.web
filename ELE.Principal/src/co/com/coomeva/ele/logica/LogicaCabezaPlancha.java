package co.com.coomeva.ele.logica;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import co.com.coomeva.ele.delegado.DelegadoCabezaPlancha;
import co.com.coomeva.ele.delegado.DelegadoExperienciaLaboral;
import co.com.coomeva.ele.delegado.DelegadoLogPlan;
import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.delegado.DelegadoPrincipal;
import co.com.coomeva.ele.delegado.DelegadoSuplente;
import co.com.coomeva.ele.dto.InfoDetalleFormatoPlanchaDTO;
import co.com.coomeva.ele.dto.InformacionCabezaPlanchaDTO;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.planchas.EleCabPlancha;
import co.com.coomeva.ele.entidades.planchas.EleCabPlanchaDAO;
import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral;
import co.com.coomeva.ele.entidades.planchas.ElePlanchas;
import co.com.coomeva.ele.entidades.planchas.ElePrincipales;
import co.com.coomeva.ele.entidades.planchas.EleSuplentes;
import co.com.coomeva.ele.entidades.planchas.dao.EleDetalleFormatoPlanchaDAO;
import co.com.coomeva.ele.entidades.planchas.dao.EleFormatoDAO;
import co.com.coomeva.ele.entidades.planchas.dao.EleFotoFormatoPlanchaDAO;
import co.com.coomeva.ele.entidades.planchas.dao.ElePlanchaDAO;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleDetalleFormatoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleDetalleFormatoPlanchaId;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFormato;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFormatoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFotoFormatoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFotoFormatoPlanchaId;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlancha;
import co.com.coomeva.ele.logica.inscripcion.plancha.ILogicaFormatoPlancha;
import co.com.coomeva.ele.logica.inscripcion.plancha.LogicaFormatoPlancha;
import co.com.coomeva.ele.modelo.EleCabPlanchaDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.ManipulacionFechas;
import co.com.coomeva.util.resources.LoaderResourceElements;

public class LogicaCabezaPlancha extends EleCabPlanchaDAO {
	private static LogicaCabezaPlancha instance;
	private EleDetalleFormatoPlanchaDAO dao = new EleDetalleFormatoPlanchaDAO();
	private EleFotoFormatoPlanchaDAO daoFotoFormato = new EleFotoFormatoPlanchaDAO();
	private LoaderResourceElements loaderResourceElements = LoaderResourceElements.getInstance();
	private LogicaAsociado logicaAsociado = LogicaAsociado.getInstance();
	private ILogicaFormatoPlancha logicaFormatoPlancha = new LogicaFormatoPlancha();
	private EleFormatoDAO daoFormato = new EleFormatoDAO();
	private ElePlanchaDAO daoPlancha = new ElePlanchaDAO();

	// TODO: organizar que los formatos de planchas queden parametrizables ya
	// sea para representantes, delegados, etc.
	private Long CODIGO_FORMATO_INFO_CABEZA_PLANCHA = new Long(
			UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_ESTADO_INFO_CABEZA_PLANCHA_REPRESENTANTE));
	private Long CODIGO_FORMATO_NOREPOSICION_SINAPELACION = new Long(
			UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_ESTADO_NOREPOSICION_SINAPELACION));
	private Long CODIGO_FORMATO_REPOSICION_CONAPELACION = new Long(
			UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_ESTADO_REPOSICION_CONAPELACION));
	private Long CODIGO_FORMATO_APELACION_FAVORABLE = new Long(
			UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_ESTADO_APELACION_FAVORABLE));
	private Long CODIGO_FORMATO_APELACION_ENCONTRA = new Long(
			UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_ESTADO_APELACION_ENCONTRA));

	private Long CODIGO_FORMATO_CONCEDE_RECURSO_APELACION = new Long(
			UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_ESTADO_CONCEDE_RECURSO_APELACION));

	private Long CODIGO_FORMATO_DELEGADO_DENIEGA = new Long(UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "codigo.formato.delegado.deniegaRoposicion"));

	private Long CODIGO_FORMATO_DELEGADO_APELACION = new Long(UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "codigo.formato.delegado.reposiscionApelacion"));

	private Long CODIGO_FORMATO_DELEGADO_FAVORABLE = new Long(UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "codigo.formato.delegado.recursoFavorable"));

	private Long CODIGO_FORMATO_DELEGADO_ENCONTRA = new Long(UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "codigo.formato.delegado.recursoEnContra"));

	private Long CODIGO_FORMATO_DELEGADO_EXTEMPORANEA = new Long(UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "codigo.formato.delegado.extemporaneamente"));

	private Long CODIGO_FORMATO_DELEGADO_FAVORABLEMENTE = new Long(UtilAcceso.getParametroFuenteS(
			ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, "codigo.formato.delegado.favorable"));

	// Constructor de la clase
	private LogicaCabezaPlancha() {
	}

	/**
	 * Patrón Singular
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return instance
	 */
	public static LogicaCabezaPlancha getInstance() {
		if (instance == null) {
			instance = new LogicaCabezaPlancha();
		}
		return instance;
	}

	/**
	 * Metodo que realiza la creacion de un cabeza de plancha en la base de datos
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroIdentificacion
	 * @param primerNombre
	 * @param segundoNombre
	 * @param primerApellido
	 * @param segundoApellido
	 * @param edad
	 * @param profesion
	 * @param email
	 * @param antiguedad
	 * @param rutaImagen
	 * @param otrosEstudios
	 * @param cargosDirectivos
	 * @throws Exception
	 */
	public void crearCabezaPlancha(String nroIdentificacion, String primerNombre, String segundoNombre,
			String primerApellido, String segundoApellido, Long edad, String profesion, String email, Long antiguedad,
			String rutaImagen, String otrosEstudios, String cargosDirectivos) throws Exception {

		if (nroIdentificacion == null || nroIdentificacion.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumIdentificacionCabeza"));
		}

		if (primerNombre == null || primerNombre.trim().equals("")) {
			// throw new Exception(UtilAcceso.getParametroFuenteS("mensajes",
			// "noPrimerNombre"));
			primerNombre = " ";
		}

		if (primerApellido == null || primerApellido.trim().equals("")) {
			// throw new Exception(UtilAcceso.getParametroFuenteS("mensajes",
			// "noPrimerApellido"));
			primerApellido = " ";
		}

		if (edad == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noEdad"));
		}

		if (edad < 18) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noEdadMinima"));
		}

		if (profesion == null || profesion.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noProfesion"));
		}

		// if (email == null || email.trim().equals("")) {
		// throw new Exception(UtilAcceso.getParametroFuenteS("mensajes",
		// "noEmail"));
		// }

		if (cargosDirectivos == null || cargosDirectivos.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noCargoDirect"));
		}

		if (antiguedad == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noAntiguedad"));
		}

		EleCabPlancha eleCabPlancha = DelegadoCabezaPlancha.getInstance().consultarCabezaPlancha(nroIdentificacion);

		if (eleCabPlancha != null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "existeIdPlancha"));
		}

		ElePrincipales elePrincipales = DelegadoPrincipal.getInstance().consultarPrincipal(nroIdentificacion);

		if (elePrincipales != null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "existeIdPlancha"));
		}

		EleSuplentes eleSuplentes = DelegadoSuplente.getInstance().consultarSuplente(nroIdentificacion);

		if (eleSuplentes != null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "existeIdPlancha"));
		}

		ElePlanchas elPlanchas = DelegadoPlanchas.getInstance().consultarPlancha(nroIdentificacion);

		if (elPlanchas == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noExistePlancha"));
		}

		eleCabPlancha = new EleCabPlancha();
		eleCabPlancha.setAntiguedad(antiguedad);

		eleCabPlancha.setCargosDirectivos(cargosDirectivos);

		eleCabPlancha.setEdad(edad);
		eleCabPlancha.setEmail(email);
		eleCabPlancha.setNroIdentificacion(nroIdentificacion);

		if (otrosEstudios != null && !otrosEstudios.trim().equalsIgnoreCase("")) {
			eleCabPlancha.setOtrosEstudios(otrosEstudios);
		}

		if (segundoApellido != null && !segundoApellido.trim().equalsIgnoreCase("")) {
			eleCabPlancha.setSegundoApellido(segundoApellido);
		}

		eleCabPlancha.setPrimerApellido(primerApellido);
		eleCabPlancha.setPrimerNombre(primerNombre);

		if (segundoNombre != null && !segundoNombre.trim().equalsIgnoreCase("")) {
			eleCabPlancha.setSegundoNombre(segundoNombre);
		}

		eleCabPlancha.setElePlanchas(elPlanchas);
		eleCabPlancha.setProfesion(profesion);

		if (rutaImagen != null && !rutaImagen.trim().equalsIgnoreCase("")) {
			eleCabPlancha.setRutaImagen(rutaImagen);
		}

		save(eleCabPlancha);

	}

	/**
	 * Metodo que consulta mediante el id el cabeza de plancha en DTO
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroCabezaPlancha
	 * @return EleCabPlanchaDTO
	 * @throws Exception
	 */

	public EleCabPlanchaDTO consultarCabezaPlanchaDTO(String nroCabezaPlancha) throws Exception {
		if (nroCabezaPlancha == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumIdentificacionCabeza"));
		}

		EleCabPlancha elCabPlancha = null;
		EleCabPlanchaDTO elCabPlanchaDTO = null;

		try {
			elCabPlancha = findById(nroCabezaPlancha);
			if (elCabPlancha != null) {
				elCabPlanchaDTO = new EleCabPlanchaDTO(elCabPlancha);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			this.getSession().flush();
		}

		return elCabPlanchaDTO;
	}

	/**
	 * Metodo de Consulta por medio de la identificacion un Cabeza de Plancha
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroCabezaPlancha
	 * @return EleCabPlancha
	 * @throws Exception
	 */
	public EleCabPlancha consultarCabezaPlancha(String nroCabezaPlancha) throws Exception {
		if (nroCabezaPlancha == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumIdentificacionCabeza"));
		}

		EleCabPlancha elCabPlancha = null;

		try {
			elCabPlancha = findById(nroCabezaPlancha);

		} catch (Exception e) {
			throw e;
		} finally {
			this.getSession().flush();
		}
		return elCabPlancha;
	}

	/**
	 * Metodo que Guarda la ruta de la imagen en el cabeza de plancha
	 * 
	 * @author Manuel Galvez y Ricardo Chiribogas
	 * @param nroCabezaPlancha
	 * @param ruta
	 * @throws Exception
	 */
	public void guardarRutaImagen(String nroCabezaPlancha, String ruta) throws Exception {
		if (nroCabezaPlancha.trim().equals("") || nroCabezaPlancha == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumIdentificacionCabeza"));
		}
		if (ruta.trim().equals("") || ruta == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noRuta"));
		}
		EleCabPlancha cabPlancha = consultarCabezaPlancha(nroCabezaPlancha);

		cabPlancha.setRutaImagen(ruta);

		merge(cabPlancha);

	}

	/**
	 * Metodo que modifica un cabeza de plancha en la base de datos
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroIdentificacion
	 * @param primerNombre
	 * @param segundoNombre
	 * @param primerApellido
	 * @param segundoApellido
	 * @param edad
	 * @param profesion
	 * @param email
	 * @param antiguedad
	 * @param rutaImagen
	 * @param otrosEstudios
	 * @param cargosDirectivos
	 * @throws Exception
	 */

	public void modificarCabezaPlancha(String nroIdentificacion, String primerNombre, String segundoNombre,
			String primerApellido, String segundoApellido, Long edad, String profesion, String email, Long antiguedad,
			String rutaImagen, String otrosEstudios, String cargosDirectivos) throws Exception {

		if (nroIdentificacion == null || nroIdentificacion.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumIdentificacionCabeza"));
		}

		if (primerNombre == null || primerNombre.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noPrimerNombre"));
		}

		if (primerApellido == null || primerApellido.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noPrimerApellido"));
		}

		if (edad == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noEdad"));
		}

		if (edad < 18) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noEdadMinima"));
		}

		if (profesion == null || profesion.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noProfesion"));
		}

		if (antiguedad == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noAntiguedad"));
		}

		EleCabPlancha eleCabPlancha = DelegadoCabezaPlancha.getInstance().consultarCabezaPlancha(nroIdentificacion);

		if (eleCabPlancha == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noExistePlancha"));
		}

		ElePlanchas elPlanchas = DelegadoPlanchas.getInstance().consultarPlancha(nroIdentificacion);

		if (elPlanchas == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noExistePlancha"));
		}

		eleCabPlancha.setAntiguedad(antiguedad);

		if (cargosDirectivos != null && !cargosDirectivos.trim().equalsIgnoreCase("")) {
			eleCabPlancha.setCargosDirectivos(cargosDirectivos);
		}

		eleCabPlancha.setEdad(edad);
		eleCabPlancha.setEmail(email);
		eleCabPlancha.setNroIdentificacion(nroIdentificacion);

		if (otrosEstudios != null && !otrosEstudios.trim().equalsIgnoreCase("")) {
			eleCabPlancha.setOtrosEstudios(otrosEstudios);
		}

		if (segundoApellido != null && !segundoApellido.trim().equalsIgnoreCase("")) {
			eleCabPlancha.setSegundoApellido(segundoApellido);
		}

		eleCabPlancha.setPrimerApellido(primerApellido);
		eleCabPlancha.setPrimerNombre(primerNombre);

		if (segundoNombre != null && !segundoNombre.trim().equalsIgnoreCase("")) {
			eleCabPlancha.setSegundoNombre(segundoNombre);
		}

		eleCabPlancha.setElePlanchas(elPlanchas);
		eleCabPlancha.setProfesion(profesion);

		if (rutaImagen != null && !rutaImagen.trim().equalsIgnoreCase("")) {
			eleCabPlancha.setRutaImagen(rutaImagen);
		}

		merge(eleCabPlancha);
	}

	/**
	 * Metodo que modifica los valores ortograficos de una cabeza de plancha
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroIdentificacion
	 * @param primerNombre
	 * @param segundoNombre
	 * @param primerApellido
	 * @param segundoApellido
	 * @param edad
	 * @param profesion
	 * @param email
	 * @param antiguedad
	 * @param rutaImagen
	 * @param otrosEstudios
	 * @param cargosDirectivos
	 * @throws Exception
	 */
	public void modificarCabezaPlanchaOrtografia(String nroIdentificacion, String primerNombre, String segundoNombre,
			String primerApellido, String segundoApellido, Long edad, String profesion, String email, Long antiguedad,
			String rutaImagen, String otrosEstudios, String cargosDirectivos) throws Exception {

		if (nroIdentificacion == null || nroIdentificacion.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumIdentificacionCabeza"));
		}

		if (primerNombre == null || primerNombre.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noPrimerNombre"));
		}

		if (primerApellido == null || primerApellido.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noPrimerApellido"));
		}

		if (edad == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noEdad"));
		}

		if (edad < 18) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noEdadMinima"));
		}

		if (profesion == null || profesion.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noProfesion"));
		}

		if (antiguedad == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noAntiguedad"));
		}

		EleCabPlancha eleCabPlancha = DelegadoCabezaPlancha.getInstance().consultarCabezaPlancha(nroIdentificacion);

		if (eleCabPlancha == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noExistePlancha"));
		}

		ElePlanchas elPlanchas = DelegadoPlanchas.getInstance().consultarPlancha(nroIdentificacion);

		if (elPlanchas == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noExistePlancha"));
		}

		eleCabPlancha.setAntiguedad(antiguedad);

		if (cargosDirectivos != null && !cargosDirectivos.trim().equalsIgnoreCase("")) {
			eleCabPlancha.setCargosDirectivos(cargosDirectivos);
		}

		eleCabPlancha.setEdad(edad);
		eleCabPlancha.setEmail(email);
		eleCabPlancha.setNroIdentificacion(nroIdentificacion);

		if (otrosEstudios != null && !otrosEstudios.trim().equalsIgnoreCase("")) {
			eleCabPlancha.setOtrosEstudios(otrosEstudios);
		}

		if (segundoApellido != null && !segundoApellido.trim().equalsIgnoreCase("")) {
			eleCabPlancha.setSegundoApellido(segundoApellido);
		}

		eleCabPlancha.setPrimerApellido(primerApellido);
		eleCabPlancha.setPrimerNombre(primerNombre);

		if (segundoNombre != null && !segundoNombre.trim().equalsIgnoreCase("")) {
			eleCabPlancha.setSegundoNombre(segundoNombre);
		}

		eleCabPlancha.setElePlanchas(elPlanchas);
		eleCabPlancha.setProfesion(profesion);

		if (rutaImagen != null && !rutaImagen.trim().equalsIgnoreCase("")) {
			eleCabPlancha.setRutaImagen(rutaImagen);
		}

		merge(eleCabPlancha);
	}

	/**
	 * Modifica la plancha de un asociado por medio de un funcionario de la UGA
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroCabezaPlancha
	 * @param listExperienciaLaboral
	 * @param otrosEstudios
	 * @param listCargosDirectivos
	 * @param tipoTransaccion
	 * @param conceptoCambio
	 * @param user
	 * @param profesion
	 * @throws Exception
	 */

	public void modificarOrtografiaUGA(String nroCabezaPlancha, List<EleExperienciaLaboral> listExperienciaLaboral,
			String otrosEstudios, String cargosDirectivos, String tipoTransaccion, String conceptoCambio, String user,
			String profesion, String imagenCabezaPlancha) throws Exception {
		Transaction transaction = null;

		try {
			try {
				EleCabPlancha eleCabPlancha = DelegadoCabezaPlancha.getInstance()
						.consultarCabezaPlancha(nroCabezaPlancha);

				transaction = this.getSession().beginTransaction();
				DelegadoCabezaPlancha.getInstance().modificarCabezaPlancha(eleCabPlancha.getNroIdentificacion(),
						eleCabPlancha.getPrimerNombre(), eleCabPlancha.getSegundoNombre(),
						eleCabPlancha.getPrimerApellido(), eleCabPlancha.getSegundoApellido(), eleCabPlancha.getEdad(),
						profesion, eleCabPlancha.getEmail(), eleCabPlancha.getAntiguedad(), imagenCabezaPlancha,
						otrosEstudios, cargosDirectivos);
				DelegadoExperienciaLaboral.getInstance().eliminarExperienciasLaborales(nroCabezaPlancha);
				DelegadoLogPlan.getInstance().registrarLog(nroCabezaPlancha, tipoTransaccion, user, conceptoCambio);
				this.getSession().flush();
				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				throw e;
			}
			String temp = null;
			for (EleExperienciaLaboral eleExperienciaLaboral : listExperienciaLaboral) {
				temp = eleExperienciaLaboral.getEmpresa();
			}

			if (!temp.equalsIgnoreCase("") && temp != null) {
				Transaction transaction2 = null;
				try {
					transaction2 = this.getSession().beginTransaction();
					DelegadoExperienciaLaboral.getInstance().crearExperienciasLaboralesList(listExperienciaLaboral,
							nroCabezaPlancha);
					this.getSession().flush();
					transaction2.commit();
				} catch (Exception e) {
					if (transaction2 != null) {
						transaction2.rollback();
					}
					throw e;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.getSession().clear();
			this.getSession().close();
		}
	}

	/**
	 * Modifica la ruta de la imagen
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroCabezaPlancha
	 * @param rutaImagen
	 * @throws Exception
	 */

	public void modificarRutaImagen(String nroCabezaPlancha, String rutaImagen) throws Exception {
		Transaction transaction = null;

		try {
			EleCabPlancha eleCabPlancha = DelegadoCabezaPlancha.getInstance().consultarCabezaPlancha(nroCabezaPlancha);

			transaction = this.getSession().beginTransaction();
			DelegadoCabezaPlancha.getInstance().modificarCabezaPlancha(eleCabPlancha.getNroIdentificacion(),
					eleCabPlancha.getPrimerNombre(), eleCabPlancha.getSegundoNombre(),
					eleCabPlancha.getPrimerApellido(), eleCabPlancha.getSegundoApellido(), eleCabPlancha.getEdad(),
					eleCabPlancha.getProfesion(), eleCabPlancha.getEmail(), eleCabPlancha.getAntiguedad(), rutaImagen,
					eleCabPlancha.getOtrosEstudios(), eleCabPlancha.getCargosDirectivos());
			transaction.commit();

			this.getSession().flush();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	/****************************************************************
	 * Elecciones 2012
	 *************************************************************/

	/**
	 * Metodo que guarda la informacion de la cabeza de plancha en detalle formato
	 * plancha.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 1/12/2012
	 * @param dto
	 * @throws Exception
	 */
	public void guardarInformacionCabezaPlancha(InfoDetalleFormatoPlanchaDTO dto, String imageToBase64)
			throws Exception {

		Transaction tr = null;
		try {
			if (dto.getEmpresaActual() == null || dto.getEmpresaActual().equals("")) {
				throw new Exception(loaderResourceElements.getKeyResourceValue(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
						"campo.obligatorio.empresa.actual"));
			}

			if (dto.getCargoActual() == null || dto.getCargoActual().equals("")) {
				throw new Exception(loaderResourceElements.getKeyResourceValue(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
						"campo.obligatorio.cargo.actual"));
			}

			if (dto.getAntigLaboral() == null || dto.getAntigLaboral().equals(0L)) {
				throw new Exception(loaderResourceElements.getKeyResourceValue(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
						"campo.obligatorio.antiguedad.laboral"));
			}

//			if (dto.getUltimoCargoDirectivoCoomeva() == null
//					|| dto.getUltimoCargoDirectivoCoomeva().equals("")) {
//				throw new Exception(
//						loaderResourceElements
//								.getKeyResourceValue(
//										ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
//										"campo.obligatorio.ultimo.cargo.coomeva"));
//			}

			if (dto.getOtrosEstudios() == null || dto.getOtrosEstudios().equals("")) {
				throw new Exception(loaderResourceElements.getKeyResourceValue(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
						"campo.obligatorio.otros.estudios"));
			}

			/*
			 * if (dto.getFechaGrado() == null ) { throw new Exception(
			 * loaderResourceElements .getKeyResourceValue(
			 * ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
			 * "campo.obligatorio.fecha.estudios")); }
			 */

//			if (dto.getNumeroActa() == null ) {
//				throw new Exception(
//						loaderResourceElements
//								.getKeyResourceValue(
//										ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
//										"campo.obligatorio.anio.dirigencia"));
//			}

			if (imageToBase64 == null) {
				throw new Exception(loaderResourceElements.getKeyResourceValue(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
						"campo.obligatorio.foto.digital"));
			}

			/*
			 * if (dto.getPerfilCandidatoUno() == null ||
			 * dto.getPerfilCandidatoUno().equals("")) { throw new Exception(
			 * loaderResourceElements .getKeyResourceValue(
			 * ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
			 * "campo.obligatorio.perfil.candidato")); }
			 * 
			 * if (dto.getCuentaPersonalMail() == null ||
			 * dto.getCuentaPersonalMail().equals("")) { throw new Exception(
			 * loaderResourceElements .getKeyResourceValue(
			 * ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
			 * "campo.obligatorio.cuenta.personal")); }
			 */

			Long codigoAsociado = logicaAsociado.consultarCodigoAsociadoPorNumeroDocumento(dto.getNumeroDocumento());

			EleDetalleFormatoPlancha entity = new EleDetalleFormatoPlancha();
			EleFotoFormatoPlancha fotoFormato = null;

			EleDetalleFormatoPlanchaId id = new EleDetalleFormatoPlanchaId();
			EleFotoFormatoPlanchaId idFotoFormato = new EleFotoFormatoPlanchaId();
			id.setCodigoAsociado(codigoAsociado);
			id.setCodigoFormato(CODIGO_FORMATO_INFO_CABEZA_PLANCHA.byteValue());
			idFotoFormato.setCodigoAsociado(codigoAsociado);
			idFotoFormato.setCodigoFormato(CODIGO_FORMATO_INFO_CABEZA_PLANCHA.byteValue());

			entity.setId(id);
			entity.setCargoActual(dto.getCargoActual());
			entity.setAntigLaboral(dto.getAntigLaboral());
			entity.setFechaGradoBUC(null);
			entity.setFechaGradoMod(dto.getFechaGrado());
			entity.setEmpresaActual(dto.getEmpresaActual());
			entity.setOtrosEstudios(dto.getOtrosEstudios());
			entity.setUltimoCargoCoomeva(dto.getUltimoCargoDirectivoCoomeva());
			entity.setOtrosEstudiosDos(dto.getOtrosEstudiosDos());
			entity.setPerfilCandidatoUno(dto.getPerfilCandidatoUno());
			entity.setPerfilCandidatoDos(dto.getPerfilCandidatoDos());
			entity.setCuentaPersonalMail(dto.getCuentaPersonalMail());
			entity.setCuentaPersonalTwitter(dto.getCuentaPersonalTwitter());
			entity.setCuentaPersonalFacebook(dto.getCuentaPersonalFacebook());
			entity.setNumeroActa(dto.getNumeroActa());

			tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
			dao.save(entity);
			if (imageToBase64 != null) {

				fotoFormato = daoFotoFormato.findById(idFotoFormato);

				if (fotoFormato == null) {
					fotoFormato = new EleFotoFormatoPlancha();
					fotoFormato.setId(idFotoFormato);
					fotoFormato.setFotoAsociado(imageToBase64);
					daoFotoFormato.save(fotoFormato);
				} else {
					fotoFormato.setFotoAsociado(imageToBase64);
					daoFotoFormato.merge(fotoFormato);
				}

			}
			tr.commit();

		} catch (Exception e) {
			if (tr != null && tr.isActive()) {
				tr.rollback();
			}
			throw e;
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
			tr = null;
		}
	}

	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

	private boolean isValidDate(String input) {
		try {
			Date date = simpleDateFormat.parse(input);
			if (!simpleDateFormat.format(date).equals(input)) {
				return false;
			}

			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * Metodo que modifica la informacion de la cabeza de plancha en detalle formato
	 * plancha.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 1/12/2012
	 * @param dto
	 * @throws Exception
	 */
	public void modificarInformacionCabezaPlancha(InfoDetalleFormatoPlanchaDTO dto, String imageToBase64)
			throws Exception {
		Transaction tr = null;
		try {
			if (dto.getOtrosEstudios() == null || dto.getOtrosEstudios().equals("")) {
				throw new Exception(loaderResourceElements.getKeyResourceValue(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
						"campo.obligatorio.otros.estudios"));
			}

			if (dto.getEmpresaActual() == null || dto.getEmpresaActual().equals("")) {
				throw new Exception(loaderResourceElements.getKeyResourceValue(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
						"campo.obligatorio.empresa.actual"));
			}

			if (dto.getCargoActual() == null || dto.getCargoActual().equals("")) {
				throw new Exception(loaderResourceElements.getKeyResourceValue(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
						"campo.obligatorio.cargo.actual"));
			}

//			if (dto.getUltimoCargoDirectivoCoomeva() == null
//					|| dto.getUltimoCargoDirectivoCoomeva().equals("")) {
//				throw new Exception(
//						loaderResourceElements
//								.getKeyResourceValue(
//										ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
//										"campo.obligatorio.ultimo.cargo.coomeva"));
//			}

			/*
			 * if (dto.getFechaGrado() == null ) { throw new Exception(
			 * loaderResourceElements .getKeyResourceValue(
			 * ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
			 * "campo.obligatorio.fecha.estudios")); }
			 */

//			if (dto.getNumeroActa() == null ) {
//				throw new Exception(
//						loaderResourceElements
//								.getKeyResourceValue(
//										ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
//										"campo.obligatorio.anio.dirigencia"));
//			}

			if (imageToBase64 == null) {
				throw new Exception(loaderResourceElements.getKeyResourceValue(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
						"campo.obligatorio.foto.digital"));
			}
			/*
			 * if (dto.getPerfilCandidatoUno() == null ||
			 * dto.getPerfilCandidatoUno().equals("")) { throw new Exception(
			 * loaderResourceElements .getKeyResourceValue(
			 * ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
			 * "campo.obligatorio.perfil.candidato")); }
			 * 
			 * if (dto.getCuentaPersonalMail() == null ||
			 * dto.getCuentaPersonalMail().equals("")) { throw new Exception(
			 * loaderResourceElements .getKeyResourceValue(
			 * ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
			 * "campo.obligatorio.cuenta.personal")); }
			 */
			Long codigoAsociado = logicaAsociado.consultarCodigoAsociadoPorNumeroDocumento(dto.getNumeroDocumento());
			EleDetalleFormatoPlanchaId id = new EleDetalleFormatoPlanchaId();
			EleFotoFormatoPlanchaId idFormato = new EleFotoFormatoPlanchaId();
			id.setCodigoAsociado(codigoAsociado);
			id.setCodigoFormato(CODIGO_FORMATO_INFO_CABEZA_PLANCHA.byteValue());
			idFormato.setCodigoAsociado(codigoAsociado);
			idFormato.setCodigoFormato(CODIGO_FORMATO_INFO_CABEZA_PLANCHA.byteValue());

			EleDetalleFormatoPlancha entity = dao.findById(id);
			EleFotoFormatoPlancha fotoFormato = daoFotoFormato.findById(idFormato);

			if (entity == null) {
				throw new Exception("No existe detalle del cabeza de plancha ingresado");
			}

			entity.setId(id);

			entity.setCargoActual(dto.getCargoActual());
			entity.setEmpresaActual(dto.getEmpresaActual());
			entity.setOtrosEstudios(dto.getOtrosEstudios());
			entity.setUltimoCargoCoomeva(dto.getUltimoCargoDirectivoCoomeva());
			entity.setOtrosEstudiosDos(dto.getOtrosEstudiosDos());
			entity.setPerfilCandidatoUno(dto.getPerfilCandidatoUno());
			entity.setPerfilCandidatoDos(dto.getPerfilCandidatoDos());
			entity.setCuentaPersonalMail(dto.getCuentaPersonalMail());
			entity.setCuentaPersonalTwitter(dto.getCuentaPersonalTwitter());
			entity.setCuentaPersonalFacebook(dto.getCuentaPersonalFacebook());
			entity.setFechaGradoBUC(null);
			entity.setFechaGradoMod(dto.getFechaGrado());
			entity.setAntigLaboral(dto.getAntigLaboral());
			entity.setNumeroActa(dto.getNumeroActa());

			tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
			dao.merge(entity);
			if (imageToBase64 != null) {
				if (fotoFormato == null) {
					fotoFormato = new EleFotoFormatoPlancha();
				}
				fotoFormato.setId(idFormato);
				fotoFormato.setFotoAsociado(imageToBase64);
				daoFotoFormato.merge(fotoFormato);
			}
			tr.commit();

		} catch (Exception e) {
			if (tr != null && tr.isActive()) {
				tr.rollback();
			}
			throw e;
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
			tr = null;
		}
	}

	public void guardarInformacionResolucion(InfoDetalleFormatoPlanchaDTO dto, String codResolucion) throws Exception {
		Transaction tr = null;
		Date fechaActual = new Date();
		Long codigoAsociado = logicaAsociado.consultarCodigoAsociadoPorNumeroDocumento(dto.getNumeroDocumento());

		EleDetalleFormatoPlanchaId id = new EleDetalleFormatoPlanchaId();
		id.setCodigoAsociado(codigoAsociado);
		if ("1".equals(codResolucion)) {
			id.setCodigoFormato(CODIGO_FORMATO_NOREPOSICION_SINAPELACION.byteValue());
		} else if ("2".equals(codResolucion)) {
			id.setCodigoFormato(CODIGO_FORMATO_REPOSICION_CONAPELACION.byteValue());
		} else if ("3".equals(codResolucion)) {
			id.setCodigoFormato(CODIGO_FORMATO_APELACION_FAVORABLE.byteValue());
		} else if ("4".equals(codResolucion)) {
			id.setCodigoFormato(CODIGO_FORMATO_APELACION_ENCONTRA.byteValue());
		} else if ("5".equals(codResolucion)) {
			id.setCodigoFormato(CODIGO_FORMATO_CONCEDE_RECURSO_APELACION.byteValue());
		} else if ("6".equals(codResolucion)) {
			id.setCodigoFormato(CODIGO_FORMATO_DELEGADO_DENIEGA.byteValue());
		} else if ("7".equals(codResolucion)) {
			id.setCodigoFormato(CODIGO_FORMATO_DELEGADO_APELACION.byteValue());
		} else if ("8".equals(codResolucion)) {
			id.setCodigoFormato(CODIGO_FORMATO_DELEGADO_FAVORABLE.byteValue());
		} else if ("9".equals(codResolucion)) {
			id.setCodigoFormato(CODIGO_FORMATO_DELEGADO_ENCONTRA.byteValue());
		} else if ("10".equals(codResolucion)) {
			id.setCodigoFormato(CODIGO_FORMATO_DELEGADO_EXTEMPORANEA.byteValue());
		} else if ("11".equals(codResolucion)) {
			id.setCodigoFormato(CODIGO_FORMATO_DELEGADO_FAVORABLEMENTE.byteValue());
		}

		EleDetalleFormatoPlancha entity = dao.findById(id);

		if (entity == null) {
			entity = new EleDetalleFormatoPlancha();
		} else {
			throw new Exception("La resolución ya fué generada");
		}
		// tr = HibernateSessionFactoryElecciones2012.getSession()
		// .getTransaction().begin();
		tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			entity.setId(id);
			entity.setFechaEnvio(new Timestamp(fechaActual.getTime()));
			entity.setFechaImpresion(new Timestamp(fechaActual.getTime()));
			entity.setArgumentoResolucion1(dto.getArgumentoResolucion1());
			entity.setArgumentoResolucion2(dto.getArgumentoResolucion2());
			entity.setArgumentoResolucion3(dto.getArgumentoResolucion3());
			entity.setRazonResolucion1(dto.getRazonResolucion1());
			entity.setRazonResolucion2(dto.getRazonResolucion2());
			entity.setNumeroResolucion(dto.getNumeroResolucion());
			entity.setResolucionImpugnada(dto.getResolucionImpugnada());
			entity.setArgumentoRecurrente1(dto.getArgumentoRecurrente1());
			entity.setArgumentoRecurrente2(dto.getArgumentoRecurrente2());
			entity.setArgumentoComision1(dto.getArgumentoComision1());
			entity.setArgumentoComision2(dto.getArgumentoComision2());
			entity.setPronunciamientoTribunal1(dto.getPronunciamientoTribunal1());
			entity.setPronunciamientoTribunal2(dto.getPronunciamientoTribunal2());
			entity.setConsecuenciaTribunal1(dto.getConsecuenciaTribunal1());
			entity.setConsecuenciaTribunal2(dto.getConsecuenciaTribunal2());
			entity.setResolucionInterpuesta(dto.getResolucionInterpuesta());

			dao.save(entity);
			tr.commit();
		} catch (Exception e) {
			if (tr != null && tr.isActive()) {
				tr.rollback();
			}
			throw e;
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
	}

	/**
	 * Metodo que modifica la informacion de la cabeza de plancha en detalle formato
	 * plancha al finalizar.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 1/12/2012
	 * @param dto
	 * @throws Exception
	 */
	public void finalizarEnviarInformacionCabezaPlancha(InfoDetalleFormatoPlanchaDTO dto, String imageToBase64)
			throws Exception {
		Transaction tr = null;
		try {
			Date fechaActual = new Date();

			if (dto.getOtrosEstudios() == null || dto.getOtrosEstudios().equals("")) {
				throw new Exception(loaderResourceElements.getKeyResourceValue(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
						"campo.obligatorio.otros.estudios"));
			}

			if (dto.getEmpresaActual() == null || dto.getEmpresaActual().equals("")) {
				throw new Exception(loaderResourceElements.getKeyResourceValue(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
						"campo.obligatorio.empresa.actual"));
			}

			if (dto.getCargoActual() == null || dto.getCargoActual().equals("")) {
				throw new Exception(loaderResourceElements.getKeyResourceValue(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
						"campo.obligatorio.cargo.actual"));
			}

			if (dto.getUltimoCargoDirectivoCoomeva() == null || dto.getUltimoCargoDirectivoCoomeva().equals("")) {
				throw new Exception(loaderResourceElements.getKeyResourceValue(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
						"campo.obligatorio.ultimo.cargo.coomeva"));
			}

			if (dto.getPerfilCandidatoUno() == null || dto.getPerfilCandidatoUno().equals("")) {
				throw new Exception(loaderResourceElements.getKeyResourceValue(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
						"campo.obligatorio.perfil.candidato"));
			}

			if (dto.getCuentaPersonalMail() == null || dto.getCuentaPersonalMail().equals("")) {
				throw new Exception(loaderResourceElements.getKeyResourceValue(
						ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
						"campo.obligatorio.cuenta.personal"));
			}

			Long codigoAsociado = logicaAsociado.consultarCodigoAsociadoPorNumeroDocumento(dto.getNumeroDocumento());
			EleDetalleFormatoPlanchaId id = new EleDetalleFormatoPlanchaId();
			EleFotoFormatoPlanchaId idFormato = new EleFotoFormatoPlanchaId();
			id.setCodigoAsociado(codigoAsociado);
			id.setCodigoFormato(CODIGO_FORMATO_INFO_CABEZA_PLANCHA.byteValue());
			idFormato.setCodigoAsociado(codigoAsociado);
			idFormato.setCodigoFormato(CODIGO_FORMATO_INFO_CABEZA_PLANCHA.byteValue());

			EleDetalleFormatoPlancha entity = dao.findById(id);
			EleFotoFormatoPlancha fotoFormato = daoFotoFormato.findById(idFormato);

			if (entity == null) {
				throw new Exception("No existe detalle del cabeza de plancha ingresado");
			}

			tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();

			entity.setId(id);
			entity.setCargoActual(dto.getCargoActual());
			entity.setEmpresaActual(dto.getEmpresaActual());
			entity.setOtrosEstudios(dto.getOtrosEstudios());
			entity.setUltimoCargoCoomeva(dto.getUltimoCargoDirectivoCoomeva());
			entity.setFechaEnvio(new Timestamp(fechaActual.getTime()));
			entity.setOtrosEstudiosDos(dto.getOtrosEstudiosDos());
			entity.setPerfilCandidatoUno(dto.getPerfilCandidatoUno());
			entity.setPerfilCandidatoDos(dto.getPerfilCandidatoDos());
			entity.setCuentaPersonalMail(dto.getCuentaPersonalMail());
			entity.setCuentaPersonalTwitter(dto.getCuentaPersonalTwitter());
			entity.setCuentaPersonalFacebook(dto.getCuentaPersonalFacebook());
			entity.setEstado("1");

			dao.merge(entity);
			if (imageToBase64 != null) {
				fotoFormato.setId(idFormato);
				fotoFormato.setFotoAsociado(imageToBase64);
				daoFotoFormato.merge(fotoFormato);
			}
			tr.commit();

		} catch (Exception e) {
			tr.rollback();
			throw e;
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
	}

	/**
	 * Metodo que modifica la informacion de la cabeza de plancha en detalle formato
	 * plancha al imprimir.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 1/12/2012
	 * @param dto
	 * @throws Exception
	 */
	public void imprimirEnviarInformacionCabezaPlancha(InfoDetalleFormatoPlanchaDTO dto, Long consecutivoPlancha,
			String imageToBase64) throws Exception {
		Date fechaActual = new Date();
		Transaction tr = null;
		if (dto.getOtrosEstudios() == null || dto.getOtrosEstudios().equals("")) {
			throw new Exception(loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL, "campo.obligatorio.otros.estudios"));
		}

		if (dto.getEmpresaActual() == null || dto.getEmpresaActual().equals("")) {
			throw new Exception(loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL, "campo.obligatorio.empresa.actual"));
		}

		if (dto.getCargoActual() == null || dto.getCargoActual().equals("")) {
			throw new Exception(loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL, "campo.obligatorio.cargo.actual"));
		}

//		if (dto.getUltimoCargoDirectivoCoomeva() == null
//				|| dto.getUltimoCargoDirectivoCoomeva().equals("")) {
//			throw new Exception(
//					loaderResourceElements
//							.getKeyResourceValue(
//									ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
//									"campo.obligatorio.ultimo.cargo.coomeva"));
//		}
		/*
		 * if (dto.getPerfilCandidatoUno() == null ||
		 * dto.getPerfilCandidatoUno().equals("")) { throw new Exception(
		 * loaderResourceElements .getKeyResourceValue(
		 * ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
		 * "campo.obligatorio.perfil.candidato")); }
		 * 
		 * if (dto.getCuentaPersonalMail() == null ||
		 * dto.getCuentaPersonalMail().equals("")) { throw new Exception(
		 * loaderResourceElements .getKeyResourceValue(
		 * ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
		 * "campo.obligatorio.cuenta.personal")); }
		 */
		Long codigoAsociado = logicaAsociado.consultarCodigoAsociadoPorNumeroDocumento(dto.getNumeroDocumento());
		EleDetalleFormatoPlanchaId id = new EleDetalleFormatoPlanchaId();
		EleFotoFormatoPlanchaId idFormato = new EleFotoFormatoPlanchaId();
		id.setCodigoAsociado(codigoAsociado);
		id.setCodigoFormato(CODIGO_FORMATO_INFO_CABEZA_PLANCHA.byteValue());
		idFormato.setCodigoAsociado(codigoAsociado);
		idFormato.setCodigoFormato(CODIGO_FORMATO_INFO_CABEZA_PLANCHA.byteValue());

		EleDetalleFormatoPlancha entity = dao.findById(id);
		EleFotoFormatoPlancha fotoFormato = daoFotoFormato.findById(idFormato);

		if (entity == null) {
			throw new Exception("No existe detalle del cabeza de plancha ingresado");
		}

		EleFormatoPlancha formatoPlancha = new EleFormatoPlancha();
		formatoPlancha.setConsecutivo(logicaFormatoPlancha.obtenerConsecutivoFormatoPlancha());

		EleFormato formato = daoFormato.findById(CODIGO_FORMATO_INFO_CABEZA_PLANCHA.byteValue());
		formatoPlancha.setEleFormato(formato);

		ElePlancha plancha = daoPlancha.findById(consecutivoPlancha);
		formatoPlancha.setElePlancha(plancha);

		formatoPlancha.setFechaImpresion(new Timestamp(new Date().getTime()));
		formatoPlancha.setFechaRegistro(new Timestamp(new Date().getTime()));
		formatoPlancha.setUsuarioGenera(dto.getNumeroDocumento().toString());
		formatoPlancha.setUsuarioImpresion(dto.getNumeroDocumento().toString());

		logicaFormatoPlancha.guardarFormatoPlancha(formatoPlancha);
		tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			entity.setId(id);
			entity.setCargoActual(dto.getCargoActual());
			entity.setEmpresaActual(dto.getEmpresaActual());
			entity.setOtrosEstudios(dto.getOtrosEstudios());
			entity.setUltimoCargoCoomeva(dto.getUltimoCargoDirectivoCoomeva());
			entity.setFechaImpresion(new Timestamp(fechaActual.getTime()));
			entity.setOtrosEstudiosDos(dto.getOtrosEstudiosDos());
			entity.setPerfilCandidatoUno(dto.getPerfilCandidatoUno());
			entity.setFechaGradoMod(dto.getFechaGrado());
//			entity.setPerfilCandidatoDos(dto.getPerfilCandidatoDos());
//			entity.setCuentaPersonalMail(dto.getCuentaPersonalMail());
//			entity.setCuentaPersonalTwitter(dto.getCuentaPersonalTwitter());
//			entity.setCuentaPersonalFacebook(dto.getCuentaPersonalFacebook());

			dao.merge(entity);
			if (imageToBase64 != null) {
				fotoFormato.setId(idFormato);
				fotoFormato.setFotoAsociado(imageToBase64);
				daoFotoFormato.merge(fotoFormato);
			}
			tr.commit();

		} catch (Exception e) {
			tr.rollback();
			throw e;
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
	}

	/**
	 * Metodo que consulta la iformacion basica dela cabeza plancha.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 1/12/2012
	 * @param numeroDocumento
	 * @return
	 * @throws Exception
	 */
	public InformacionCabezaPlanchaDTO consultarInformacionCabezaPlancha(Long numeroDocumento) throws Exception {
		InformacionCabezaPlanchaDTO dto = null;

		StringBuffer nombresApellidosAso = null;
		Integer fechaIng = null, antiguedadMeses = null;

		Object[] element = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			Query query = session.getNamedQuery("consulta.informacion.cabeza.plancha");
			query.setLong("numeroDocumento", numeroDocumento);
			element = (Object[]) query.uniqueResult();

			if (element != null) {
				dto = new InformacionCabezaPlanchaDTO();
				dto.setNumeroDocumento(element[0].toString());

				String nombreCompletoAsociado = null;
				nombresApellidosAso = new StringBuffer();
				nombreCompletoAsociado = (String) element[1];
				NombreAsociado nAsociado = new NombreAsociado();
				nAsociado = setNombresApellidos(nombreCompletoAsociado);
				nombresApellidosAso.append(
						nAsociado.getNombre1() != null && !"".equals(nAsociado.getNombre1()) ? nAsociado.getNombre1()
								: "");
				nombresApellidosAso.append(nAsociado.getNombre2() != null && !"".equals(nAsociado.getNombre2())
						? " " + nAsociado.getNombre2()
						: "");
				nombresApellidosAso.append(nAsociado.getApellido1() != null && !"".equals(nAsociado.getApellido1())
						? " " + nAsociado.getApellido1()
						: "");
				nombresApellidosAso.append(nAsociado.getApellido2() != null && !"".equals(nAsociado.getApellido2())
						? " " + nAsociado.getApellido2()
						: "");

				dto.setNombreCompleto(nombresApellidosAso.toString());

				fechaIng = new Integer(element[2].toString());
				antiguedadMeses = (ManipulacionFechas.calcularEdad(ManipulacionFechas.intToDate(fechaIng.toString())))
						* 12;
				dto.setAntiguedadMeses(antiguedadMeses.toString());
				dto.setProfesion(element[3] == null ? null : element[3].toString());
				dto.setZonaElectoral(element[4] == null ? null : element[4].toString());
				dto.setCodAsociado(element[5].toString());
				dto.setNumeroPlancha(element[6] == null ? null : element[6].toString());
			}
		} catch (Exception e) {
			throw new Exception(loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
					"consulta.informacion.cabeza.plancha"));
		} finally {
			session.close();
		}

		return dto;
	}

	public NombreAsociado setNombresApellidos(String nomcl1) {

		NombreAsociado nAsociado = new NombreAsociado();

		String arrayNombresApellidos[] = nomcl1.split("1");
		String nombres[] = null;
		String nombre1 = null;
		String nombre2 = null;
		String apellidos1 = arrayNombresApellidos[0];
		String apellidos2 = null;

		if (arrayNombresApellidos != null && arrayNombresApellidos.length > 1) {
			if (arrayNombresApellidos[1] != null && arrayNombresApellidos[1].indexOf("2") != -1
					&& arrayNombresApellidos[1].trim().length() > 1) {
				arrayNombresApellidos = arrayNombresApellidos[1].split("2");
				apellidos2 = arrayNombresApellidos[0];
			}
			if (arrayNombresApellidos != null && arrayNombresApellidos.length > 1) {
				if (arrayNombresApellidos[1].indexOf("3") != -1) {
					nombres = arrayNombresApellidos[1].split("3");
					nombre1 = nombres[0];
					if (nombres[1] != null && nombres[1].indexOf("4") != -1 && nombres[1].trim().length() > 1) {
						nombres = nombres[1].split("4");
						nombre2 = nombres[0];
					}
				}
				nAsociado.setNombre1(nombre1.toString());
				if (nombre2 != null)
					nAsociado.setNombre2(nombre2.toString());
			} // fin if
		} // fin if
		nAsociado.setApellido1(apellidos1);
		if (apellidos2 != null)
			nAsociado.setApellido2(apellidos2);
		return nAsociado;

	}

	class NombreAsociado {

		private String nombre1;
		private String nombre2;
		private String apellido1;
		private String apellido2;

		public String getApellido1() {
			return apellido1;
		}

		public void setApellido1(String apellido1) {
			this.apellido1 = apellido1;
		}

		public String getApellido2() {
			return apellido2;
		}

		public void setApellido2(String apellido2) {
			this.apellido2 = apellido2;
		}

		public String getNombre1() {
			return nombre1;
		}

		public void setNombre1(String nombre1) {
			this.nombre1 = nombre1;
		}

		public String getNombre2() {
			return nombre2;
		}

		public void setNombre2(String nombre2) {
			this.nombre2 = nombre2;
		}

	}

	/**
	 * Metodo que consulta el detale del formato de placha por clave primaria
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 2/12/2012
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public EleDetalleFormatoPlancha consultarDetalleFormatoPlanchaPorId(Long codigoAsociado) throws Exception {
		EleDetalleFormatoPlancha entity = null;

		EleDetalleFormatoPlanchaId id = new EleDetalleFormatoPlanchaId();
		id.setCodigoAsociado(codigoAsociado);
		id.setCodigoFormato(CODIGO_FORMATO_INFO_CABEZA_PLANCHA.byteValue());

		try {
			entity = dao.findById(id);
		} catch (Exception e) {
			throw new Exception("Error consultando el detalle del formato de placha por id");
		}
		return entity;
	}

	public EleFotoFormatoPlancha consultarFotoCabezaPlancha(Long codigoAsociado) throws Exception {
		EleFotoFormatoPlancha fotoPlancha = null;
		EleFotoFormatoPlanchaId id = new EleFotoFormatoPlanchaId();
		id.setCodigoAsociado(codigoAsociado);
		id.setCodigoFormato(CODIGO_FORMATO_INFO_CABEZA_PLANCHA.byteValue());

		fotoPlancha = daoFotoFormato.findById(id);

		return fotoPlancha;
	}

	/**
	 * Metodo que valida cuantos registros existen del detalle formato plancha por
	 * el formato inscripcion cabeza plancha.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 11/12/2012
	 * @param numeroDocumento
	 * @return Boolean
	 * @throws Exception
	 */
	public Boolean validarRegistroDetalleFormatoPlancha(Long numeroDocumento, Long codigoFormato) throws Exception {
		Boolean existeRegistro = null;
		Long codigoAsociado = logicaAsociado.consultarCodigoAsociadoPorNumeroDocumento(numeroDocumento);
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			Query query = session.getNamedQuery("consultar.numero.registros.detalle.formato.plancha");
			query.setLong("codigoAsociado", codigoAsociado);
			query.setLong("codigoFormato", codigoFormato);

			Long cantidad = (Long) query.uniqueResult();

			if (cantidad > 0) {
				existeRegistro = true;
			} else {
				existeRegistro = false;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
		return existeRegistro;
	}

	/**
	 * Metodo que obtiene la fecha de graduación de un asociado
	 * 
	 * @author Mario Alejandro Acevedo <br>
	 * @param
	 * @return Boolean
	 * @throws Exception
	 */

	public Long obtieneFechaGradoAsociado(Long codAsociado) throws Exception {

		Long fechaLong = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			Query query = session.getNamedQuery("consultar.fechaGraduacion.asociado");
			query.setLong("codAsociado", codAsociado);

			fechaLong = (Long) query.uniqueResult();

		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
		return fechaLong;
	}
	/*
	 * public static void main(String[] args) {
	 * 
	 * try { // Date dia = DateManipultate.stringToDate(, "yyyyMMdd"); //
	 * System.out.println(dia.toString());
	 * 
	 * System.out.println(new LogicaCabezaPlancha().isValidDate("2012/12/31")); }
	 * catch (Exception e) { System.out.println(e.getMessage()); }
	 * 
	 * 
	 * 
	 * }
	 */

}