package co.com.coomeva.ele.logica;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import co.com.coomeva.ele.delegado.DelegadoCabezaPlancha;
import co.com.coomeva.ele.delegado.DelegadoExperienciaLaboral;
import co.com.coomeva.ele.delegado.DelegadoLogPlan;
import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.delegado.DelegadoPrincipal;
import co.com.coomeva.ele.delegado.DelegadoSuplente;
import co.com.coomeva.ele.delegado.DelegadoZona;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.planchas.EleCabPlancha;
import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral;
import co.com.coomeva.ele.entidades.planchas.ElePlanchas;
import co.com.coomeva.ele.entidades.planchas.ElePlanchasDAO;
import co.com.coomeva.ele.entidades.planchas.ElePrincipales;
import co.com.coomeva.ele.entidades.planchas.EleSuplentes;
import co.com.coomeva.ele.entidades.planchas.EleZonas;
import co.com.coomeva.ele.entidades.planchas.HibernateSessionFactoryPlanchas;
import co.com.coomeva.ele.entidades.planchas.dao.EleEstadoPlanchaDAO;
import co.com.coomeva.ele.entidades.planchas.dao.ElePlanchaDAO;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleEstadoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlanchaExcepcion;
import co.com.coomeva.ele.logica.inscripcion.plancha.ILogicaUsuarioComision;
import co.com.coomeva.ele.logica.inscripcion.plancha.LogicaPlanchaExcepcion;
import co.com.coomeva.ele.logica.inscripcion.plancha.LogicaUsuarioComision;
import co.com.coomeva.ele.modelo.CabezaPlanchaDTO;
import co.com.coomeva.ele.modelo.EleCabPlanchaDTO;
import co.com.coomeva.ele.modelo.EleLogDTO;
import co.com.coomeva.ele.modelo.ElePlanchaDTO;
import co.com.coomeva.ele.modelo.ElePrincipalesDTO;
import co.com.coomeva.ele.modelo.EleSuplentesDTO;
import co.com.coomeva.ele.modelo.EleTablaEmpresaCargoDTO;
import co.com.coomeva.ele.modelo.InfoPlanchaAdmisionPdfDTO;
import co.com.coomeva.ele.modelo.InfoPlanchaConstanciaPdfDTO;
import co.com.coomeva.ele.modelo.InfoPlanchaDTO;
import co.com.coomeva.ele.modelo.ParametroPlanchaDTO;
import co.com.coomeva.ele.modelo.PlanchaPorEstadoDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.UtilAccesoDb;
import co.com.coomeva.util.acceso.UtilAcceso;

public class LogicaPlanchas extends ElePlanchasDAO {
	private static LogicaPlanchas instance;
	private ElePlanchaDAO dao = new ElePlanchaDAO();
	private EleEstadoPlanchaDAO daoEstadoPlanchaDAO = new EleEstadoPlanchaDAO();
	private ILogicaUsuarioComision logicaUsuarioComision;

	private LogicaPlanchas() {
	}

	public static LogicaPlanchas getInstance() {
		if (instance == null) {
			instance = new LogicaPlanchas();
		}
		return instance;
	}

	/**
	 * Metodo que guarda un registro en la tabla ELEPLANCHA
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroCabPlancha
	 * @param fechaInscripcion
	 * @param estado
	 * @param zona
	 * @param nroPlancha
	 * @param descEstado
	 * @throws Exception
	 */
	public void crearPlancha(String nroCabPlancha, Date fechaInscripcion, String estado, String zona, Long nroPlancha,
			String descEstado) throws Exception {
		try {
			if (nroCabPlancha == null || nroCabPlancha.trim().equals("")) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNroCabPlancha"));
			}

			if (fechaInscripcion == null) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noFechaInscripcion"));
			}
			if (estado == null || estado.trim().equals("")) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noEstado"));
			}

			if (zona == null || zona.trim().equals("")) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noZona"));
			}
			EleCabPlancha eleCabPlancha = DelegadoCabezaPlancha.getInstance().consultarCabezaPlancha(nroCabPlancha);

			if (eleCabPlancha != null) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "existeIdPlancha"));
			}

			ElePrincipales elePrincipales = DelegadoPrincipal.getInstance().consultarPrincipal(nroCabPlancha);

			if (elePrincipales != null) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "existeIdPlancha"));
			}

			EleSuplentes eleSuplentes = DelegadoSuplente.getInstance().consultarSuplente(nroCabPlancha);

			if (eleSuplentes != null) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "existeIdPlancha"));
			}

			ElePlanchas elPlancha = DelegadoPlanchas.getInstance().consultarPlancha(nroCabPlancha);

			if (elPlancha != null) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "existePlancha"));
			}

			EleZonas elZonas = DelegadoZona.getInstance().consultarZona(zona);
			if (elZonas == null) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noZona"));

			}

			boolean boEstado = false;
			for (int j = 1; j <= UtilAcceso.getParametroFuenteI("parametros", "numeroEstadosPlancha"); j++) {
				if (estado.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha" + j))) {
					boEstado = true;
					break;
				}
			}

			if (!boEstado) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noCreadoEstado"));
			}

			ElePlanchas elePlanchas = new ElePlanchas();

			elePlanchas.setEstado(estado);
			elePlanchas.setEleZonas(elZonas);
			elePlanchas.setFechaInscripcion(fechaInscripcion);
			elePlanchas.setNroCabPlancha(nroCabPlancha);
			if (nroPlancha != null && !zona.trim().equals("")) {
				elePlanchas.setNroPlancha(nroPlancha);
			}
			if (descEstado != null && !descEstado.trim().equals("")) {
				elePlanchas.setDescEstado(descEstado);
			}

			save(elePlanchas);

		} catch (Exception e) {

			throw e;

		}

	}

	/**
	 * Metodo de general de inscripcion de plancha
	 * 
	 * @param elPlanchas
	 * @param elCabPlancha
	 * @param principales
	 * @param suplentes
	 * @param listEmpresaCargo
	 * @throws Exception
	 */

	public void inscripcionPlancha(ElePlanchaDTO elPlanchas, EleCabPlanchaDTO elCabPlancha,
			List<ElePrincipalesDTO> principales, List<EleSuplentesDTO> suplentes,
			List<EleTablaEmpresaCargoDTO> listEmpresaCargo) throws Exception {
		Transaction transaction = HibernateSessionFactoryPlanchas.getSession().beginTransaction();
		try {
			DelegadoPlanchas.getInstance().crearPlancha(elPlanchas.getNroCabPlancha(), new Date(),
					elPlanchas.getEstado(), elPlanchas.getEleZonas().getCodZona(), null, elPlanchas.getDescEstado());
			DelegadoCabezaPlancha.getInstance().crearCabezaPlancha(elCabPlancha.getNroIdentificacion(),
					elCabPlancha.getPrimerNombre(), elCabPlancha.getSegundoNombre(), elCabPlancha.getPrimerApellido(),
					elCabPlancha.getSegundoApellido(), elCabPlancha.getEdad(), elCabPlancha.getProfesion(),
					elCabPlancha.getEmail(), elCabPlancha.getAntiguedad(), elCabPlancha.getRutaImagen(),
					elCabPlancha.getOtrosEstudios(), elCabPlancha.getCargosDirectivos());

			DelegadoPrincipal.getInstance().crearPrincipales(principales);
			DelegadoSuplente.getInstance().crearSuplentes(suplentes);

			for (EleTablaEmpresaCargoDTO eleTablaEmpresaCargoDTO : listEmpresaCargo) {
				DelegadoExperienciaLaboral.getInstance().crearExperienciaLaboral(elCabPlancha.getNroIdentificacion(),
						eleTablaEmpresaCargoDTO.getCargo(), eleTablaEmpresaCargoDTO.getEmpresa());
			}

			DelegadoLogPlan.getInstance().registrarLog(elPlanchas.getNroCabPlancha(),
					UtilAcceso.getParametroFuenteS("parametros", "tipoTransaccionAso1"),
					UtilAcceso.getParametroFuenteS("parametros", "usuarioAsociado"),
					UtilAcceso.getParametroFuenteS("parametros", "nombreTransaccionAso1"));
			this.getSession().flush();
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		} finally {
			this.getSession().clear();
			this.getSession().close();
		}
	}

	/**
	 * Metodo que realiza la consulta de una plancha en la DB
	 * 
	 * @param nroCabezaPlancha
	 * @return ElePlanchas
	 * @throws Exception
	 */

	public ElePlanchas consultarPlancha(String nroCabezaPlancha) throws Exception {
		if (nroCabezaPlancha == null || nroCabezaPlancha.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNroCabPlancha"));
		}
		ElePlanchas elPlancha = null;
		try {

			elPlancha = findById(nroCabezaPlancha);
		} catch (Exception e) {
			throw e;
		}
		return elPlancha;
	}

	/**
	 * Metodo general que modifica una plancha en la DB
	 * 
	 * @param elPlanchas
	 * @param elCabPlancha
	 * @param listaPrincipales
	 * @param listaSuplentes
	 * @param listExperienciaLaboral
	 * @throws Exception
	 */
	public void modificarPlanchaInscrita(ElePlanchas elPlanchas, EleCabPlanchaDTO elCabPlancha,
			List<ElePrincipalesDTO> listaPrincipales, List<EleSuplentesDTO> listaSuplentes,
			List<EleExperienciaLaboral> listExperienciaLaboral, EleLogDTO eleLogDTO) throws Exception {
		Transaction transaction = null;
		Transaction transaction2 = null;
		try {
			try {
				transaction = this.getSession().beginTransaction();

				DelegadoPlanchas.getInstance().modificarPlancha(elPlanchas.getNroCabPlancha(), new Date(),
						elPlanchas.getEstado(), elPlanchas.getEleZonas().getCodZona(), null,
						elPlanchas.getDescEstado());
				DelegadoCabezaPlancha.getInstance().modificarCabezaPlancha(elCabPlancha.getNroIdentificacion(),
						elCabPlancha.getPrimerNombre(), elCabPlancha.getSegundoNombre(),
						elCabPlancha.getPrimerApellido(), elCabPlancha.getSegundoApellido(), elCabPlancha.getEdad(),
						elCabPlancha.getProfesion(), elCabPlancha.getEmail(), elCabPlancha.getAntiguedad(),
						elCabPlancha.getRutaImagen(), elCabPlancha.getOtrosEstudios(),
						elCabPlancha.getCargosDirectivos());
				DelegadoSuplente.getInstance().eliminarSuplentes(elCabPlancha.getNroIdentificacion());
				DelegadoPrincipal.getInstance().eliminarPrincipales(elCabPlancha.getNroIdentificacion());
				DelegadoExperienciaLaboral.getInstance()
						.eliminarExperienciasLaborales(elCabPlancha.getNroIdentificacion());
				DelegadoLogPlan.getInstance().registrarLog(elPlanchas.getNroCabPlancha(),
						eleLogDTO.getTipoTransaccion(), eleLogDTO.getNombreUsuario(), eleLogDTO.getNombreTransaccion());
				this.getSession().flush();
				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				throw e;
			}
			try {
				transaction2 = this.getSession().beginTransaction();
				DelegadoPrincipal.getInstance().crearPrincipales(listaPrincipales);
				DelegadoSuplente.getInstance().crearSuplentes(listaSuplentes);
				DelegadoExperienciaLaboral.getInstance().crearExperienciasLaboralesList(listExperienciaLaboral,
						elCabPlancha.getNroIdentificacion());
				this.getSession().flush();
				transaction2.commit();
			} catch (Exception e) {
				if (transaction2 != null) {
					transaction2.rollback();
				}
				throw e;
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
	 * Metodo que actualiza el registro de una plancha en ELEPLANCHAS
	 * 
	 * @param nroCabPlancha
	 * @param fechaInscripcion
	 * @param estado
	 * @param zona
	 * @param nroPlancha
	 * @param descEstado
	 * @throws Exception
	 */
	public void modificarPlancha(String nroCabPlancha, Date fechaInscripcion, String estado, String zona,
			Long nroPlancha, String descEstado) throws Exception {

		if (nroCabPlancha == null || nroCabPlancha.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNroCabPlancha"));
		}

		if (fechaInscripcion == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noFechaInscripcion"));
		}
		if (estado == null || estado.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noEstado"));
		}

		if (zona == null || zona.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noZona"));
		}

		EleCabPlancha eleCabPlancha = DelegadoCabezaPlancha.getInstance().consultarCabezaPlancha(nroCabPlancha);

		if (eleCabPlancha == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noExistePlancha"));
		}

		EleZonas elZonas = DelegadoZona.getInstance().consultarZona(zona);
		if (elZonas == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noZona"));

		}

		ElePlanchas elPlancha = DelegadoPlanchas.getInstance().consultarPlancha(nroCabPlancha);

		EleCabPlancha cabPlancha = DelegadoCabezaPlancha.getInstance().consultarCabezaPlancha(nroCabPlancha);

		EleCabPlanchaDTO cabPlanchaDTO = new EleCabPlanchaDTO();
		cabPlanchaDTO.setNombreCompleto(cabPlancha.getPrimerNombre() + " " + cabPlancha.getSegundoNombre() + " "
				+ cabPlancha.getPrimerApellido() + " " + cabPlancha.getSegundoApellido());

		if (elPlancha == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noExistePlancha"));
		}
		boolean boEstado = false;
		for (int j = 1; j <= UtilAcceso.getParametroFuenteI("parametros", "numeroEstadosPlancha") + 1; j++) {
			if (estado.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha" + j))) {
				boEstado = true;
				break;
			}
		}

		if (!boEstado) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noCreadoEstado"));
		}

		elPlancha.setEstado(estado);
		elPlancha.setEleZonas(elZonas);
		elPlancha.setFechaInscripcion(fechaInscripcion);
		elPlancha.setNroCabPlancha(nroCabPlancha);
		if (nroPlancha != null && !zona.trim().equals("")) {
			elPlancha.setNroPlancha(nroPlancha);
		}
		if (descEstado != null && !descEstado.trim().equals("")) {
			elPlancha.setDescEstado(descEstado);
		}
		merge(elPlancha);
	}

	/**
	 * Cambio del estado de plancha
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroPlancha
	 * @param descEstado
	 * @param estado
	 * @param user
	 * @throws Exception
	 */

	public void cambiarEstadoPlancha(Long nroPlancha, String descEstado, String estado, String user,
			EleLogDTO eleLogDTO, boolean numeroPlancha) throws Exception {
		String NumPlancha = nroPlancha.toString();
		Transaction transaction = null;

		try {
			transaction = this.getSession().beginTransaction();
			ElePlanchas elePlanchas = new ElePlanchas();
			elePlanchas = DelegadoPlanchas.getInstance().consultarPlancha(NumPlancha);
			if (!numeroPlancha) {
				DelegadoPlanchas.getInstance().modificarPlancha(elePlanchas.getNroCabPlancha(),
						elePlanchas.getFechaInscripcion(), estado, elePlanchas.getEleZonas().getCodZona(), null,
						descEstado);
			} else {
				Long next = UtilAccesoDb.getNuevoIdSimpleL(this.getSession(), ElePlanchas.class, "nroPlancha",
						"eleZonas.codZona = " + elePlanchas.getEleZonas().getCodZona());

				DelegadoPlanchas.getInstance().modificarPlancha(elePlanchas.getNroCabPlancha(),
						elePlanchas.getFechaInscripcion(), estado, elePlanchas.getEleZonas().getCodZona(), next,
						descEstado);
			}

			DelegadoLogPlan.getInstance().registrarLog(elePlanchas.getNroCabPlancha(), eleLogDTO.getTipoTransaccion(),
					eleLogDTO.getNombreUsuario(), descEstado);
			this.getSession().flush();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		} finally {
			this.getSession().clear();
			this.getSession().close();
		}
	}

	/**
	 * Consulta las planchas dependiendo de los parametros ingresados
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param zona
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param estado
	 * @return List<ElePlanchas>
	 * @throws Exception
	 */
	public List<ElePlanchas> consultarPlanchas(String zona, Date fechaInicial, Date fechaFinal, String estado,
			boolean noNumeradas) throws Exception {
		boolean between = false;
		List<ElePlanchas> returnList = new ArrayList<ElePlanchas>();

		try {
			Criteria criteria = getSession().createCriteria(ElePlanchas.class);

			if (fechaInicial != null && fechaFinal != null) {
				if (fechaInicial.equals(fechaFinal)) {
					criteria.add(Restrictions.eq("fechaInscripcion", fechaInicial));
				} else {
					criteria.add(Restrictions.between("fechaInscripcion", fechaInicial, fechaFinal));
				}
				between = true;
			}

			if (fechaInicial != null && between == false) {
				criteria.add(Restrictions.ge("fechaInscripcion", fechaInicial));
			}

			if (fechaFinal != null && between == false) {
				criteria.add(Restrictions.le("fechaInscripcion", fechaFinal));
			}

			if (estado != null && !estado.trim().equalsIgnoreCase("")) {
				criteria.add(Restrictions.eq("estado", estado));
			}
			if (zona != null && !zona.trim().equalsIgnoreCase("")) {
				criteria.add(Restrictions.eq("eleZonas.codZona", zona));
			}
			if (noNumeradas) {
				criteria.add(Restrictions.isNotNull("nroPlancha"));
			}

			// criteria.addOrder(Order.asc("nroCabPlancha"));
			criteria.addOrder(Order.asc("nroPlancha"));

			returnList = criteria.list();

		} catch (Exception e) {
			throw e;
		} finally {
			this.getSession().flush();
		}

		return returnList;
	}

	public ElePlanchas consultarPlanchasByNroPlancha(String nroPlancha, String zona) throws Exception {
		ElePlanchas elePlanchas = new ElePlanchas();
		Object object = new Object();
		try {
			Criteria criteria = getSession().createCriteria(ElePlanchas.class);
			criteria.addOrder(Order.asc("nroPlancha"));
			if (nroPlancha != null) {
				criteria.add(Restrictions.eq("nroPlancha", nroPlancha));
			}
			if (zona != null) {
				criteria.add(Restrictions.eq("eleZonas.codZona", zona));
			}

			object = criteria.uniqueResult();
			if (object != null) {
				elePlanchas = (ElePlanchas) object;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			this.getSession().flush();
		}

		return elePlanchas;
	}

	/**
	 * Consulta todas la planchas
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return List<ElePlanchas>
	 * @throws Exception
	 */

	public List<ElePlanchas> consultarPlanchas() throws Exception {
		List<ElePlanchas> listPlanchas = new ArrayList<ElePlanchas>();
		try {
			listPlanchas = findAll();
		} catch (Exception e) {
			throw e;
		} finally {
			this.getSession().flush();
		}

		return listPlanchas;
	}

	/**
	 * Modificar la enumeracion de planchas
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroCabPlancha
	 * @param nroPlancha
	 * @param user
	 * @throws Exception
	 */

	public void modificarPlanchaNumerada(String nroCabPlancha, Long nroPlancha, String user, EleLogDTO eleLogDTO,
			String estado) throws Exception {
		Transaction transaction = null;
		try {
			transaction = this.getSession().beginTransaction();
			ElePlanchas elPlanchas = DelegadoPlanchas.getInstance().consultarPlancha(nroCabPlancha);

			DelegadoPlanchas.getInstance().modificarPlancha(nroCabPlancha, elPlanchas.getFechaInscripcion(), estado,
					elPlanchas.getEleZonas().getCodZona(), nroPlancha, elPlanchas.getDescEstado());
			DelegadoLogPlan.getInstance().registrarLog(nroCabPlancha, eleLogDTO.getTipoTransaccion(),
					eleLogDTO.getNombreUsuario(), eleLogDTO.getNombreTransaccion());
			this.getSession().flush();
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		} finally {
			this.getSession().clear();
			this.getSession().close();
		}
	}

	/**
	 * Validar si el usuario se encuentra en otra plancha
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroIdentificacion
	 * @param nroCabPlancha
	 * @return boolean
	 * @throws Exception
	 */

	public boolean validarOtraPlancha(String nroIdentificacion, String nroCabPlancha) throws Exception {

		if (nroIdentificacion == null || nroIdentificacion.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNroIdentificacion"));
		}

		ElePrincipales elePrincipales = DelegadoPrincipal.getInstance().consultarPrincipal(nroIdentificacion);

		if (elePrincipales != null) {
			if (!elePrincipales.getElePlanchas().getNroCabPlancha()
					.equalsIgnoreCase(elePrincipales.getNroPriIdentificacion())) {
				if (!elePrincipales.getElePlanchas().getNroCabPlancha().equalsIgnoreCase(nroCabPlancha)) {
					return true;
				}
			}
		}

		EleSuplentes eleSuplentes = DelegadoSuplente.getInstance().consultarSuplente(nroIdentificacion);

		if (eleSuplentes != null) {
			if (!eleSuplentes.getElePlanchas().getNroCabPlancha().equalsIgnoreCase(nroCabPlancha)) {
				return true;
			}
		}

		return false;
	}

	public List<ElePlanchaDTO> consultarPlanchasReporte() throws Exception {
		List<ElePlanchaDTO> listaPlanchasDTO = new ArrayList<ElePlanchaDTO>();

		try {

			ElePlanchaDTO elePlanchaDTO;
			List<ElePlanchas> listaPlanchas = listaPlanchasReporte();

			for (ElePlanchas elePlanchas : listaPlanchas) {
				elePlanchaDTO = new ElePlanchaDTO();

				elePlanchaDTO.setEstado(elePlanchas.getEstado());
				elePlanchaDTO.setEleZonas(elePlanchas.getEleZonas());
				elePlanchaDTO.setNroCabPlancha(elePlanchas.getNroCabPlancha());
				elePlanchaDTO.setNroPlancha(elePlanchas.getNroPlancha());

				EleCabPlancha cabeza = DelegadoCabezaPlancha.getInstance()
						.consultarCabezaPlancha(elePlanchas.getNroCabPlancha());

				elePlanchaDTO.setEleCabPlanchaDTO(new EleCabPlanchaDTO(cabeza));

				elePlanchaDTO.setListExperienciaLaboral(DelegadoExperienciaLaboral.getInstance()
						.consultaExperienciaLaborales(cabeza.getNroIdentificacion()));

				List<EleSuplentesDTO> listaSuplentes = DelegadoSuplente.getInstance()
						.consultarSuplentes(elePlanchas.getNroCabPlancha());
				List<ElePrincipalesDTO> listaPrincipales = DelegadoPrincipal.getInstance()
						.consultarPrincipales(elePlanchas.getNroCabPlancha());

				elePlanchaDTO.setListaPrincipales(listaPrincipales);
				elePlanchaDTO.setListaSuplentes(listaSuplentes);

				listaPlanchasDTO.add(elePlanchaDTO);

			}

		} catch (Exception e) {
			throw e;
		}

		return listaPlanchasDTO;
	}

	private List<ElePlanchas> listaPlanchasReporte() throws Exception {
		StringBuffer sql = new StringBuffer();
		Query sqlQuery = null;

		sql.append(" SELECT planchas ");
		sql.append(" FROM ElePlanchas planchas");
		sql.append(" WHERE planchas.nroPlancha is not null ");
		sql.append(" ORDER BY planchas.eleZonas.nomZona,planchas.nroPlancha");

		sqlQuery = getSession().createQuery(sql.toString());

		// CONFIGURA TIPO DATO COLUMNAS SELECT
		List<ElePlanchas> listaPlanchas = null;

		try {
			listaPlanchas = sqlQuery.list();
		} catch (Exception e) {
			throw e;
		}
		return listaPlanchas;
	}

	public String findPlanchasEstadoNative(String nroPlancha) throws Exception {
		StringBuffer sql = new StringBuffer();
		SQLQuery sqlQuery = null;

		sql.append(" SELECT ESTADO ");
		sql.append(" FROM ELECCION.ELE_PLANCHAS");
		sql.append(" WHERE NRO_CAB_PLANCHA =:id ");

		sqlQuery = getSession().createSQLQuery(sql.toString());

		// CONFIGURA TIPO DATO COLUMNAS SELECT
		sqlQuery.addScalar("ESTADO", Hibernate.STRING);
		Object returnOb = new Object();
		String estado = null;

		try {
			sqlQuery.setString("id", nroPlancha);
			returnOb = sqlQuery.uniqueResult();
			if (returnOb != null) {
				estado = returnOb.toString();
			}
		} catch (Exception e) {
			throw e;
		}
		return estado;
	}

	public String formatearCadena(String cadena) {
		return initCap(cadena);
	}

	public List<CabezaPlanchaDTO> obtenerIntegrantesCabezaPlanchaPrinc(Long numPlancha) {

		Session session = HibernateSessionFactoryElecciones2012.getSession();
		List<CabezaPlanchaDTO> principales = new ArrayList<CabezaPlanchaDTO>();

		StringBuffer sql = new StringBuffer("select aso.NUMERO_DOCUMENTO documento, cl.NOMCL1 nombres, "
				+ "db2util.f_profesion(aso.CODIGO_ASOCIADO) profesion "
				+ "from elecdb.ELE_ASOCIADO aso, elecdb.ELE_PLANCHA pl, elecdb.ELE_PLANCHA_ASOCIADO pl_aso, "
				+ "elecdb.CLIMAE cl where pl.CONSECUTIVO_PLANCHA = pl_aso.CONSECUTIVO_PLANCHA and pl_aso.CONSECUTIVO_PLANCHA = :numPlancha and "
				+ "pl_aso.CODIGO_ASOCIADO = aso.CODIGO_ASOCIADO and pl_aso.CODIGO_ASOCIADO = cl.NUMINT");

		SQLQuery query = session.createSQLQuery(sql.toString());

		query.addScalar("documento", Hibernate.STRING);
		query.addScalar("nombres", Hibernate.STRING);
		query.addScalar("profesion", Hibernate.STRING);

		try {
			query.setLong("numPlancha", numPlancha);
			List<Object[]> datos = (List<Object[]>) query.list();

			if (datos != null && datos.size() > 0) {
				for (Object[] objects : datos) {
					List<String> nombresApellidosProcesados = new ArrayList<String>();
					nombresApellidosProcesados = procesaNombresApellidos((String) objects[1]);

					CabezaPlanchaDTO cabeza = new CabezaPlanchaDTO();
					cabeza.setIdentificacion(objects[0].toString());
					cabeza.setNombres(
							nombresApellidosProcesados.get(2).trim() + " " + nombresApellidosProcesados.get(3).trim());
					cabeza.setApellidos(
							nombresApellidosProcesados.get(0).trim() + " " + nombresApellidosProcesados.get(1).trim());
					if (objects[2] != null) {
						cabeza.setProfesion(objects[2].toString());
					} else {
						cabeza.setProfesion("");
					}

					principales.add(cabeza);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return principales;
	}

	public List<CabezaPlanchaDTO> obtenerIntegrantesCabezaPlanchaPrincPag(String sortColumnName, boolean sortAscending,
			int startRow, int maxResults, Long numPlancha) throws Exception {

		List<CabezaPlanchaDTO> list = new ArrayList<CabezaPlanchaDTO>();
		List<Object[]> objectList = null;

		Session session = HibernateSessionFactoryElecciones2012.getSession();

		try {

			StringBuffer sql = new StringBuffer("select aso.NUMERO_DOCUMENTO documento, cl.NOMCL1 nombres, "
					+ "CASE WHEN db2util.f_profesion(aso.CODIGO_ASOCIADO) IS NOT NULL "
					+ "THEN  db2util.f_profesion(aso.CODIGO_ASOCIADO) " + "ELSE " + "aso. DESC_PROFESION "
					+ "END    profesion, " + "pl_aso.tipo_inscrito tipo, pl_aso.numero_inscrito numero "
					+ "from elecdb.ELE_ASOCIADO aso, elecdb.ELE_PLANCHA pl, elecdb.ELE_PLANCHA_ASOCIADO pl_aso, "
					+ "elecdb.CLIMAE cl where pl.CONSECUTIVO_PLANCHA = pl_aso.CONSECUTIVO_PLANCHA and  pl_aso.CONSECUTIVO_PLANCHA = :numPlancha and "
					+ "pl_aso.CODIGO_ASOCIADO = aso.CODIGO_ASOCIADO and pl_aso.CODIGO_ASOCIADO = cl.NUMINT "
					+ "ORDER BY pl_aso.tipo_inscrito, pl_aso.numero_inscrito asc");

			SQLQuery query = session.createSQLQuery(sql.toString());

			query.addScalar("documento", Hibernate.STRING);
			query.addScalar("nombres", Hibernate.STRING);
			query.addScalar("profesion", Hibernate.STRING);
			query.addScalar("tipo", Hibernate.STRING);
			query.addScalar("numero", Hibernate.STRING);

			CabezaPlanchaDTO integrante = null;
			query.setLong("numPlancha", numPlancha);
			objectList = query.setFirstResult(startRow).setMaxResults(maxResults).list();
			if (objectList != null && objectList.size() > 0) {
				for (Object[] objects : objectList) {
					integrante = new CabezaPlanchaDTO();
					List<String> nombresApellidosProcesados = new ArrayList<String>();

					nombresApellidosProcesados = procesaNombresApellidos((String) objects[1]);

					integrante.setIdentificacion(objects[0].toString());
					integrante.setNombres(
							nombresApellidosProcesados.get(2).trim() + " " + nombresApellidosProcesados.get(3).trim());
					integrante.setApellidos(
							nombresApellidosProcesados.get(0).trim() + " " + nombresApellidosProcesados.get(1).trim());
					if (objects[2] != null) {
						integrante.setProfesion((String) objects[2]);
					} else {
						integrante.setProfesion("");
					}
					String tipo = "Principal";
					if (objects[3].equals("2")) {
						tipo = "Suplente";
					}
					integrante.setTipoInscrito(tipo);
					integrante.setNumeroInscrito((String) objects[4]);

					List<ElePlanchaExcepcion> excepciones = new LogicaPlanchaExcepcion()
							.consultarExcepciones(integrante.getIdentificacion(), numPlancha.toString());
					integrante.setPoseeExcepciones(excepciones != null && !excepciones.isEmpty());

					list.add(integrante);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}

		return list;

	}

	public List<CabezaPlanchaDTO> obtenerIntegrantesCabezaPlanchaPrincSinPag(Long numPlancha) throws Exception {

		List<CabezaPlanchaDTO> list = new ArrayList<CabezaPlanchaDTO>();
		List<Object[]> objectList = null;

		Session session = HibernateSessionFactoryElecciones2012.getSession();

		try {

			StringBuffer sql = new StringBuffer("select aso.NUMERO_DOCUMENTO documento, cl.NOMCL1 nombres, "
					+ "db2util.f_profesion(aso.CODIGO_ASOCIADO) profesion, pl_aso.tipo_inscrito tipo, pl_aso.numero_inscrito numero "
					+ "from elecdb.ELE_ASOCIADO aso, elecdb.ELE_PLANCHA pl, elecdb.ELE_PLANCHA_ASOCIADO pl_aso, "
					+ "elecdb.CLIMAE cl where pl.CONSECUTIVO_PLANCHA = pl_aso.CONSECUTIVO_PLANCHA and  pl_aso.CONSECUTIVO_PLANCHA = :numPlancha and "
					+ "pl_aso.CODIGO_ASOCIADO = aso.CODIGO_ASOCIADO and pl_aso.CODIGO_ASOCIADO = cl.NUMINT "
					+ "ORDER BY pl_aso.tipo_inscrito, pl_aso.numero_inscrito asc");

			SQLQuery query = session.createSQLQuery(sql.toString());

			query.addScalar("documento", Hibernate.STRING);
			query.addScalar("nombres", Hibernate.STRING);
			query.addScalar("profesion", Hibernate.STRING);
			query.addScalar("tipo", Hibernate.STRING);
			query.addScalar("numero", Hibernate.STRING);

			CabezaPlanchaDTO integrante = null;
			query.setLong("numPlancha", numPlancha);
			objectList = query.list();
			if (objectList != null && objectList.size() > 0) {
				for (Object[] objects : objectList) {
					integrante = new CabezaPlanchaDTO();
					List<String> nombresApellidosProcesados = new ArrayList<String>();

					nombresApellidosProcesados = procesaNombresApellidos((String) objects[1]);

					integrante.setIdentificacion(objects[0].toString());
					integrante.setNombres(
							nombresApellidosProcesados.get(2).trim() + " " + nombresApellidosProcesados.get(3).trim());
					integrante.setApellidos(
							nombresApellidosProcesados.get(0).trim() + " " + nombresApellidosProcesados.get(1).trim());
					if (objects[2] != null) {
						integrante.setProfesion((String) objects[2]);
					} else {
						integrante.setProfesion("");
					}
					String tipo = "Principal";
					if (objects[3].equals("2")) {
						tipo = "Suplente";
					}
					integrante.setTipoInscrito(tipo);
					integrante.setNumeroInscrito((String) objects[4]);

					list.add(integrante);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}

		return list;

	}

	public List<String> procesaNombresApellidos(String nombreSinFormato) {

		List<String> nombresApellidos = new ArrayList<String>();

		// String nombreSinFormato = "ROCHA1RIANO2SEGUNDO3JOSE4";

		int indicePrimerApellido = nombreSinFormato.indexOf("1");
		int indiceSegundoApellido = nombreSinFormato.indexOf("2");
		int indicePrimerNombre = nombreSinFormato.indexOf("3");
		int indiceSegundoNombre = nombreSinFormato.indexOf("4");

		String primerNombre = "";
		String segundoNombre = "";
		String primerApellido = "";
		String segundoApellido = "";

		primerApellido = nombreSinFormato.substring(0, indicePrimerApellido);
		segundoApellido = nombreSinFormato.substring(indicePrimerApellido + 1, indiceSegundoApellido);
		primerNombre = nombreSinFormato.substring(indiceSegundoApellido + 1, indicePrimerNombre);
		segundoNombre = nombreSinFormato.substring(indicePrimerNombre + 1, indiceSegundoNombre);

		nombresApellidos.add(primerApellido);
		nombresApellidos.add(segundoApellido);
		nombresApellidos.add(primerNombre);
		nombresApellidos.add(segundoNombre);

		return nombresApellidos;
	}

	/**
	 * Obtiene la información que se presentará sobre la grilla al consultar una
	 * plancha por numero de identificación de cabeza
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario
	 *         Alejandro Acevedo</a> - GSISIN <br>
	 * @param numDoc
	 * @return InfoPlanchaDTO
	 * 
	 */
	public InfoPlanchaDTO obtenerInfoPlancha(Long numDoc, String idUsuario) throws Exception {

		Session session = null;
		InfoPlanchaDTO info = new InfoPlanchaDTO();
		;
		logicaUsuarioComision = new LogicaUsuarioComision();

		List<Long> zonasElectoralesUsuario = logicaUsuarioComision.consultarZonaElectUsuarioComision(idUsuario);
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT pl_aso.CONSECUTIVO_PLANCHA NUMPLANCHA, ");
		sql.append(
				"zona.DESCRIPCION_ZONA_ELE ZONA, param.NOMBRE ESTADO, pl.FECHA_ENVIO FECHA, pl.CONSECUTIVO_PLANCHA, ");
		sql.append(
				"pl_aso.CODIGO_ASOCIADO CODASOCIADO, zona.CODIGO_ZONA_ELE COD_ZONA, pl.ESTADO_PLANCHA, zona.NUMERO_ZONA_ELE NUMEROZONA ");
		sql.append("FROM elecdb.ELEASOCIA aso, ");
		sql.append("elecdb.ELE_PLANCHA_ASOCIADO pl_aso, elecdb.ELE_ZONA_ELECTORAL zona, ");
		sql.append(
				"elecdb.ELE_PLANCHA pl, elecdb.ELEASOMUL cl, elecdb.ELE_PARAMETRO param, elecdb.ELE_PARAMETRO_TIPO paramT ");

		if (zonasElectoralesUsuario != null && !zonasElectoralesUsuario.isEmpty()) {
			sql.append(", elecdb.ELE_USUARIO_COMISION euc ");
		}

		sql.append("WHERE  ");
		sql.append("pl.ESTADO_PLANCHA = param.VALOR and paramT.TIP_COD = 1 and paramT.TIP_COD = param.TIP_COD and ");
		sql.append("aso.NUMERO_DOCUMENTO = :numDoc and  ");
		sql.append("aso.CODIGO_ASOCIADO = pl_aso.CODIGO_ASOCIADO and ");
		sql.append("pl_aso.NUMERO_INSCRITO = 1  and pl_aso.TIPO_INSCRITO = 1 and ");
		sql.append("pl_aso.CODIGO_ASOCIADO = cl.NUMINT and ");
		sql.append("pl_aso.CONSECUTIVO_PLANCHA = pl.CONSECUTIVO_PLANCHA and ");
		sql.append("pl.CODIGO_ZONA_ELE = zona.CODIGO_ZONA_ELE ");

		if (zonasElectoralesUsuario != null && !zonasElectoralesUsuario.isEmpty()) {
			sql.append("and euc.CODIGO_ZONA_ELE = zona.CODIGO_ZONA_ELE ");

			StringBuffer codigoZonas = new StringBuffer();
			for (Long zona : zonasElectoralesUsuario) {
				codigoZonas.append(zona + ",");
			}

			sql.append("and euc.CODIGO_ZONA_ELE in  ("
					+ codigoZonas.toString().substring(0, codigoZonas.toString().length() - 1) + ")");
		}

		session = HibernateSessionFactoryElecciones2012.getSession();
		SQLQuery query = session.createSQLQuery(sql.toString());

		query.addScalar("NUMPLANCHA", Hibernate.STRING);
		query.addScalar("ZONA", Hibernate.STRING);
		query.addScalar("ESTADO", Hibernate.STRING);
		query.addScalar("FECHA", Hibernate.DATE);
		query.addScalar("CONSECUTIVO_PLANCHA", Hibernate.LONG);
		query.addScalar("CODASOCIADO", Hibernate.LONG);
		query.addScalar("COD_ZONA", Hibernate.LONG);
		query.addScalar("ESTADO_PLANCHA", Hibernate.STRING);
		query.addScalar("NUMEROZONA", Hibernate.STRING);

		try {
			query.setLong("numDoc", numDoc);
			List<Object[]> datos = (List<Object[]>) query.list();

			for (Object[] objects : datos) {
				info.setNumPlancha(objects[0].toString());
				info.setZona(objects[1].toString());
				info.setEstado(objects[2].toString());

				if (objects[3] != null) {
					info.setFecha(objects[3].toString());
				} else {
					info.setFecha("");
				}

				info.setConsecutivoPlancha(Long.parseLong(objects[4].toString()));
				info.setCodAsociado(Long.parseLong(objects[5].toString()));

				if (objects[6] != null) {
					// info.setCodZona(objects[6].toString());
					// Debe ir el numero de zona. no el id. de la zona.
					info.setCodZona(objects[8].toString());
				} else {
					info.setCodZona("");
				}

				info.setCodigoEstadoPlancha(objects[7] != null ? objects[7].toString() : "");
			}

			if (info != null && info.getConsecutivoPlancha() != null) {
				ElePlancha elePlancha = consultarPlanchaPorId(info.getConsecutivoPlancha());

				if (elePlancha != null && elePlancha.getEleZonaElectoralEspecial() != null) {
					info.setZona(elePlancha.getEleZonaElectoralEspecial().getDescripcionZonaEle());
				}
			}

			return info;

		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Obtiene los parametros de un tipo en particular
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario
	 *         Alejandro Acevedo</a> - GSISIN <br>
	 * @param tipoParametro
	 * @return List<ParametroPlanchaDTO>
	 */
	public List<ParametroPlanchaDTO> obtenerParametrosTipo(Long tipoParametro) {

		Session session = HibernateSessionFactoryElecciones2012.getSession();
		List<ParametroPlanchaDTO> parametros = new ArrayList<ParametroPlanchaDTO>();

		StringBuffer sql = new StringBuffer("select p.VALOR VALOR, p.NOMBRE NOMBRE from elecdb.ELE_PARAMETRO p, "
				+ "elecdb.ELE_PARAMETRO_TIPO pt where p.TIP_COD = :tipoParametro and pt.TIP_COD = p.TIP_COD and "
				+ "p.ESTADO = 1");

		SQLQuery query = session.createSQLQuery(sql.toString());

		query.addScalar("VALOR", Hibernate.LONG);
		query.addScalar("NOMBRE", Hibernate.STRING);

		try {
			query.setLong("tipoParametro", tipoParametro);
			List<Object[]> datos = (List<Object[]>) query.list();

			if (datos != null && datos.size() > 0) {
				for (Object[] objects : datos) {

					ParametroPlanchaDTO parametro = new ParametroPlanchaDTO();
					parametro.setValor((Long) objects[0]);
					parametro.setNombre(objects[1].toString());

					parametros.add(parametro);
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}

		return parametros;
	}

	/**
	 * Obtiene la información de la grilla de una plancha en determinado estado
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario
	 *         Alejandro Acevedo</a> - GSISIN <br>
	 * @param estado
	 * @return List<PlanchaPorEstadoDTO>
	 */

	public List<PlanchaPorEstadoDTO> obtenerPlanchasPorEstado(Long estado, String idUsuario) throws Exception {

		Session session = null;
		List<PlanchaPorEstadoDTO> planchas = new ArrayList<PlanchaPorEstadoDTO>();

		logicaUsuarioComision = new LogicaUsuarioComision();
		List<Long> zonasElectoralesUsuario = logicaUsuarioComision.consultarZonaElectUsuarioComision(idUsuario);

		List<Long> zonasElectoralesUsuarioEspecial = logicaUsuarioComision
				.consultarZonaElectEspUsuarioComision(idUsuario);

		StringBuffer sql = new StringBuffer("SELECT DISTINCT pl.NUMERO_PLANCHA NUMPLANCHA,"
				+ "case when pl.CODIGO_ZONA_ELE_ESPECIAL IS NULL " + "THEN zona.DESCRIPCION_ZONA_ELE "
				+ "ELSE   zona_esp.DESCRIPCION_ZONA_ELE " + "END  ZONA, " + " param.NOMBRE ESTADO, "
				+ " pl.FECHA_REGISTRO FECHA, " + " aso.NUMERO_DOCUMENTO documento, " + " cl.NOMCL1 nombres "
				+ "FROM  elecdb.ELE_ASOCIADO aso " + "INNER JOIN elecdb.ELE_PLANCHA_ASOCIADO pl_aso "
				+ "ON (aso.CODIGO_ASOCIADO = pl_aso.CODIGO_ASOCIADO )  " + "INNER JOIN elecdb.ELE_PLANCHA pl "
				+ "ON (pl_aso.CONSECUTIVO_PLANCHA = pl.CONSECUTIVO_PLANCHA)  "
				+ "LEFT JOIN elecdb.ELE_ZONA_ELECTORAL zona " + "ON(pl.CODIGO_ZONA_ELE = zona.CODIGO_ZONA_ELE "
				+ " AND pl.CODIGO_ZONA_ELE_ESPECIAL IS NULL) "
				+ "LEFT JOIN elecdb.ELE_ZONA_ELECTORAL_ESPECIAL zona_esp "
				+ "ON(pl.CODIGO_ZONA_ELE = zona_esp.CODIGO_ZONA_ELE " + " AND pl.CODIGO_ZONA_ELE_ESPECIAL IS NOT NULL) "
				+ "INNER JOIN elecdb.CLIMAE cl " + "ON (pl_aso.CODIGO_ASOCIADO = cl.NUMINT ) "
				+ "INNER JOIN elecdb.ELE_PARAMETRO param " + "ON(param.VALOR = pl.ESTADO_PLANCHA "
				+ " AND param.TIP_COD = 1) ");

		if (zonasElectoralesUsuario != null && !zonasElectoralesUsuario.isEmpty()) {
			sql.append("INNER JOIN elecdb.ELE_USUARIO_COMISION euc " + "ON (euc.CODIGO_ZONA_ELE = zona.CODIGO_ZONA_ELE "
					+ "AND  euc.CODIGO_ZONA_ELE IN ( ");

			StringBuffer codigoZonas = new StringBuffer();
			for (Long zona : zonasElectoralesUsuario) {
				codigoZonas.append(zona + ",");
			}
			sql.append(codigoZonas.toString().substring(0, codigoZonas.toString().length() - 1) + ")) ");
		}

		if (zonasElectoralesUsuarioEspecial != null && !zonasElectoralesUsuarioEspecial.isEmpty()) {
			sql.append("INNER JOIN elecdb.ELE_USUARIO_COMISION eucs "
					+ "ON (eucs.CODIGO_ZONA_ELE_ESPE = zona_esp.CODIGO_ZONA_ELE "
					+ "AND  eucs.CODIGO_ZONA_ELE_ESPE IN( ");

			StringBuffer codigoZonas = new StringBuffer();
			for (Long zona : zonasElectoralesUsuarioEspecial) {
				codigoZonas.append(zona + ",");
			}
			sql.append(codigoZonas.toString().substring(0, codigoZonas.toString().length() - 1) + ")) ");
		}

		sql.append("WHERE pl.ESTADO_PLANCHA = :estado  " + "and  pl_aso.NUMERO_INSCRITO = 1 "
				+ "and pl_aso.TIPO_INSCRITO = 1  ");

		session = HibernateSessionFactoryElecciones2012.getSession();
		SQLQuery query = session.createSQLQuery(sql.toString());

		query.addScalar("NUMPLANCHA", Hibernate.LONG);
		query.addScalar("ZONA", Hibernate.STRING);
		query.addScalar("ESTADO", Hibernate.STRING);
		query.addScalar("FECHA", Hibernate.DATE);
		query.addScalar("documento", Hibernate.LONG);
		query.addScalar("nombres", Hibernate.STRING);

		try {
			query.setLong("estado", estado);
			List<Object[]> datos = (List<Object[]>) query.list();

			if (datos != null && datos.size() > 0) {
				for (Object[] objects : datos) {
					List<String> nombresApellidosProcesados = new ArrayList<String>();
					nombresApellidosProcesados = procesaNombresApellidos((String) objects[5]);

					PlanchaPorEstadoDTO plancha = new PlanchaPorEstadoDTO();

					plancha.setNumeroPlancha((Long) objects[0]);
					plancha.setZona(objects[1].toString());
					plancha.setEstado(objects[2].toString());
					plancha.setFechaInscripcion((Date) objects[3]);
					plancha.setNumCedula((Long) objects[4]);
					plancha.setAsociadoCabeza(nombresApellidosProcesados.get(2).trim() + " "
							+ nombresApellidosProcesados.get(3).trim() + " " + nombresApellidosProcesados.get(0).trim()
							+ " " + nombresApellidosProcesados.get(1).trim());

					planchas.add(plancha);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return planchas;
	}

	/**
	 * Obtiene la información paginada de la grilla de una plancha en determinado
	 * estado
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario
	 *         Alejandro Acevedo</a> - GSISIN <br>
	 * @param estado
	 * @return List<PlanchaPorEstadoDTO>
	 */
	public List<PlanchaPorEstadoDTO> obtenerPlanchasPorEstadoPag(String sortColumnName, boolean sortAscending,
			int startRow, int maxResults, Long estado, String idUsuario) throws Exception {

		Session session = null;
		List<PlanchaPorEstadoDTO> planchas = new ArrayList<PlanchaPorEstadoDTO>();
		List<Object[]> objectList = null;

		logicaUsuarioComision = new LogicaUsuarioComision();
		List<Long> zonasElectoralesUsuario = logicaUsuarioComision.consultarZonaElectUsuarioComision(idUsuario);

		List<Long> zonasElectoralesUsuarioEspecial = logicaUsuarioComision
				.consultarZonaElectEspUsuarioComision(idUsuario);

		StringBuffer sql = new StringBuffer("SELECT DISTINCT pl.NUMERO_PLANCHA NUMPLANCHA,"
				+ "case when pl.CODIGO_ZONA_ELE_ESPECIAL IS NULL " + "THEN zona.DESCRIPCION_ZONA_ELE "
				+ "ELSE   zona_esp.DESCRIPCION_ZONA_ELE " + "END  ZONA, " + " param.NOMBRE ESTADO, "
				+ " pl.FECHA_REGISTRO FECHA, " + " aso.NUMERO_DOCUMENTO documento, " + " cl.NOMCL1 nombres "
				+ "FROM  elecdb.ELE_ASOCIADO aso " + "INNER JOIN elecdb.ELE_PLANCHA_ASOCIADO pl_aso "
				+ "ON (aso.CODIGO_ASOCIADO = pl_aso.CODIGO_ASOCIADO )  " + "INNER JOIN elecdb.ELE_PLANCHA pl "
				+ "ON (pl_aso.CONSECUTIVO_PLANCHA = pl.CONSECUTIVO_PLANCHA)  "
				+ "LEFT JOIN elecdb.ELE_ZONA_ELECTORAL zona " + "ON(pl.CODIGO_ZONA_ELE = zona.CODIGO_ZONA_ELE "
				+ " AND pl.CODIGO_ZONA_ELE_ESPECIAL IS NULL) "
				+ "LEFT JOIN elecdb.ELE_ZONA_ELECTORAL_ESPECIAL zona_esp "
				+ "ON(pl.CODIGO_ZONA_ELE = zona_esp.CODIGO_ZONA_ELE " + " AND pl.CODIGO_ZONA_ELE_ESPECIAL IS NOT NULL) "
				+ "INNER JOIN elecdb.CLIMAE cl " + "ON (pl_aso.CODIGO_ASOCIADO = cl.NUMINT ) "
				+ "INNER JOIN elecdb.ELE_PARAMETRO param " + "ON(param.VALOR = pl.ESTADO_PLANCHA "
				+ " AND param.TIP_COD = 1) ");

		if (zonasElectoralesUsuario != null && !zonasElectoralesUsuario.isEmpty()) {
			sql.append("INNER JOIN elecdb.ELE_USUARIO_COMISION euc " + "ON (euc.CODIGO_ZONA_ELE = zona.CODIGO_ZONA_ELE "
					+ "AND  euc.CODIGO_ZONA_ELE IN ( ");

			StringBuffer codigoZonas = new StringBuffer();
			for (Long zona : zonasElectoralesUsuario) {
				codigoZonas.append(zona + ",");
			}
			sql.append(codigoZonas.toString().substring(0, codigoZonas.toString().length() - 1) + ")) ");
		}

		if (zonasElectoralesUsuarioEspecial != null && !zonasElectoralesUsuarioEspecial.isEmpty()) {
			sql.append("INNER JOIN elecdb.ELE_USUARIO_COMISION eucs "
					+ "ON (eucs.CODIGO_ZONA_ELE_ESPE = zona_esp.CODIGO_ZONA_ELE "
					+ "AND  eucs.CODIGO_ZONA_ELE_ESPE IN( ");

			StringBuffer codigoZonas = new StringBuffer();
			for (Long zona : zonasElectoralesUsuarioEspecial) {
				codigoZonas.append(zona + ",");
			}
			sql.append(codigoZonas.toString().substring(0, codigoZonas.toString().length() - 1) + ")) ");
		}

		sql.append("WHERE pl.ESTADO_PLANCHA = :estado  " + "and  pl_aso.NUMERO_INSCRITO = 1 "
				+ "and pl_aso.TIPO_INSCRITO = 1  ");

		session = HibernateSessionFactoryElecciones2012.getSession();
		SQLQuery query = session.createSQLQuery(sql.toString());

		query.addScalar("NUMPLANCHA", Hibernate.LONG);
		query.addScalar("ZONA", Hibernate.STRING);
		query.addScalar("ESTADO", Hibernate.STRING);
		query.addScalar("FECHA", Hibernate.DATE);
		query.addScalar("documento", Hibernate.LONG);
		query.addScalar("nombres", Hibernate.STRING);

		try {
			query.setLong("estado", estado);
			objectList = query.setFirstResult(startRow).setMaxResults(maxResults).list();

			if (objectList != null && objectList.size() > 0) {
				for (Object[] objects : objectList) {
					List<String> nombresApellidosProcesados = new ArrayList<String>();
					nombresApellidosProcesados = procesaNombresApellidos((String) objects[5]);

					PlanchaPorEstadoDTO plancha = new PlanchaPorEstadoDTO();

					plancha.setNumeroPlancha((Long) objects[0]);
					plancha.setZona(objects[1].toString());
					plancha.setEstado(objects[2].toString());
					plancha.setFechaInscripcion((Date) objects[3]);
					plancha.setNumCedula((Long) objects[4]);
					plancha.setAsociadoCabeza(nombresApellidosProcesados.get(2).trim() + " "
							+ nombresApellidosProcesados.get(3).trim() + " " + nombresApellidosProcesados.get(0).trim()
							+ " " + nombresApellidosProcesados.get(1).trim());

					planchas.add(plancha);
				}

			}
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}

		return planchas;
	}

	/**
	 * Obtiene la información de la plancha para desplegarla en el fomato CO-FT-208
	 * – Constancia de radicación y recibo de planchas
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario
	 *         Alejandro Acevedo</a> - GSISIN <br>
	 * @param numPlancha
	 * @return InfoPlanchaConstanciaPdfDTO
	 */
	public InfoPlanchaConstanciaPdfDTO obtenerInfoPlanchaConstanciaPdf(Long numPlancha) {

		Session session = HibernateSessionFactoryElecciones2012.getSession();// ceoq
		InfoPlanchaConstanciaPdfDTO info = new InfoPlanchaConstanciaPdfDTO();

		StringBuffer sql = new StringBuffer("SELECT pl_aso.CONSECUTIVO_PLANCHA NUMPLANCHA, "
				+ "pl.CODIGO_ZONA_ELE ZONA,  pl.FECHA_REGISTRO FECHA, aso.NUMERO_DOCUMENTO DOCUMENTO, "
				+ "cl.NOMCL1 NOMBRES, db2util.get_cliciudad(cl.LUGEXP) CIUDADEXP, cl.FECESC FECHAEXP, cl.NUMINT NUMINT, "
				+ "zona.DESCRIPCION_ZONA_ELE CIUZONA, zona.numero_zona_ele as NUMEROZONA FROM elecdb.ELE_ASOCIADO aso, elecdb.ELE_PLANCHA_ASOCIADO pl_aso, "
				+ "elecdb.ELE_ZONA_ELECTORAL zona, elecdb.ELE_PLANCHA pl, elecdb.CLIMAE cl, "
				+ "elecdb.ELE_PARAMETRO param, elecdb.ELE_PARAMETRO_TIPO paramT "
				+ "WHERE pl.CONSECUTIVO_PLANCHA = :numPlancha and  paramT.TIP_COD = 1 and "
				+ "paramT.TIP_COD = param.TIP_COD and param.VALOR = pl.ESTADO_PLANCHA and "
				+ " pl_aso.CONSECUTIVO_PLANCHA = pl.CONSECUTIVO_PLANCHA and "
				+ " pl.CODIGO_ZONA_ELE = zona.CODIGO_ZONA_ELE and "
				+ "aso.CODIGO_ASOCIADO = pl_aso.CODIGO_ASOCIADO and "
				+ " pl_aso.NUMERO_INSCRITO = 1 and pl_aso.TIPO_INSCRITO = 1 and pl_aso.CODIGO_ASOCIADO = cl.NUMINT");

		SQLQuery query = session.createSQLQuery(sql.toString());

		query.addScalar("NUMPLANCHA", Hibernate.LONG);
		query.addScalar("ZONA", Hibernate.STRING);
		query.addScalar("FECHA", Hibernate.TIMESTAMP);
		query.addScalar("DOCUMENTO", Hibernate.LONG);
		query.addScalar("NOMBRES", Hibernate.STRING);
		query.addScalar("CIUDADEXP", Hibernate.STRING);
		query.addScalar("FECHAEXP", Hibernate.STRING);
		query.addScalar("NUMINT", Hibernate.LONG);
		query.addScalar("CIUZONA", Hibernate.STRING);
		query.addScalar("NUMEROZONA", Hibernate.STRING);

		try {
			query.setLong("numPlancha", numPlancha);
			List<Object[]> datos = (List<Object[]>) query.list();

			for (Object[] objects : datos) {

				List<String> nombresApellidosProcesados = new ArrayList<String>();
				nombresApellidosProcesados = procesaNombresApellidos((String) objects[4]);

				info.setNumeroPlancha((Long) objects[0]);
				// info.setZona(objects[1].toString());
				info.setZona(objects[9].toString());// ES NUMERO ZONA.

				SimpleDateFormat formatterMes = new SimpleDateFormat("MM");
				SimpleDateFormat formatterDia = new SimpleDateFormat("dd");
				SimpleDateFormat formatterHora = new SimpleDateFormat("HH");
				SimpleDateFormat formatterAnno = new SimpleDateFormat("yyyy");
				SimpleDateFormat formatterMinutos = new SimpleDateFormat("mm");

				info.setAnnoInsc(formatterAnno.format(objects[2]));
				info.setMesInsc(formatterMes.format(objects[2]));
				info.setDiaInsc(formatterDia.format(objects[2]));
				info.setHoraInsc(formatterHora.format(objects[2]) + ":" + formatterMinutos.format(objects[2]));

				info.setNumCedula((Long) objects[3]);
				info.setNombres(nombresApellidosProcesados.get(2) + " " + nombresApellidosProcesados.get(3));
				info.setApellidos(nombresApellidosProcesados.get(0) + " " + nombresApellidosProcesados.get(1));
				info.setCiudadExp(objects[5].toString());

				if (objects[6] != null && !"".equals(objects[6].toString()) && objects[6].toString().length() > 7) {
					info.setAnnoExp(objects[6].toString().substring(0, 4));
					info.setMesExp(objects[6].toString().substring(4, 6));
					info.setDiaExp(objects[6].toString().substring(6, 8));
				}

				Date fechaActual = new Date();
				info.setAnnoAct(formatterAnno.format(fechaActual));
				info.setMesAct(formatterMes.format(fechaActual));
				info.setDiaAct(formatterDia.format(fechaActual));
				info.setHoraAct(formatterHora.format(fechaActual) + ":" + formatterMinutos.format(fechaActual));

				info.setNumint((Long) objects[7]);
				info.setComisionCiu(objects[8].toString());

			}

			if (info != null) {
				ElePlancha elePlancha = consultarPlanchaPorId(numPlancha);

				if (elePlancha != null && elePlancha.getEleZonaElectoralEspecial() != null) {
					info.setComisionCiu(elePlancha.getEleZonaElectoralEspecial().getDescripcionZonaEle());
				}
			}

			return info;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Metodo que verifica si la fecha actual esta entre el periodo parametrizado
	 * para marcar la plancha en estado plancha recurso.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 7/12/2012
	 * @return
	 * @throws Exception
	 */
	public void validarFechaMarcarPlanchaRecurso() throws Exception {
		Boolean esFechaMarcaRecurso = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		Timestamp fechaIni = null, fechaFin = null, fechaActual = new Timestamp(new Date().getTime());
		SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantesProperties.FORMATO_FECHA_TIME_STAMP);
		try {
			Query query = session.getNamedQuery("consulta.fechas.marcar.recurso.plancha");
			List<String> elements = (List<String>) query.list();
			if (elements != null && elements.size() > 0) {
				for (int i = 0; i < elements.size(); i++) {
					if (i == 0) {
						java.util.Date parsedDateIni = dateFormat.parse(elements.get(i).toString());
						fechaIni = new java.sql.Timestamp(parsedDateIni.getTime());
					} else if (i == 1) {
						java.util.Date parsedDateFin = dateFormat.parse(elements.get(i).toString());
						fechaFin = new java.sql.Timestamp(parsedDateFin.getTime());
					}
				}
			}

			if (fechaIni != null && fechaFin != null) {
				if (fechaActual.before(fechaIni) || fechaActual.after(fechaFin)) {
					esFechaMarcaRecurso = false;
				} else {
					esFechaMarcaRecurso = true;
				}
			}

			if (!esFechaMarcaRecurso) {
				throw new Exception(MessageFormat
						.format(UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
								"msgPeriodoMarcarPlanchaRecurso"), fechaIni, fechaFin));
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * metodo que marca en recurso una plancha.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 7/12/2012
	 * @param consecutivoPlancha
	 * @param usuarioSesion
	 * @throws Exception
	 */
	public void actualizarEstadoPlancha(Long consecutivoPlancha, String estado, String usuarioSesion) throws Exception {
		try {
			ElePlancha plancha = consultarPlanchaPorId(consecutivoPlancha);
			plancha.setEstadoPlancha(estado);
			modificarPlancha(plancha);

			EleEstadoPlancha estadoPlancha = new EleEstadoPlancha();
			estadoPlancha.setConsecutivoEstadoPlancha(consultarConsecutivoEstadoPlancha());
			estadoPlancha.setElePlancha(plancha);
			estadoPlancha.setFechaRegistro(new Timestamp(new Date().getTime()));
			estadoPlancha.setUsuarioEstado(usuarioSesion);
			estadoPlancha.setEstadoPlancha(estado);

			guardarEstadoPlancha(estadoPlancha);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Metodo que obtiene el consecutivo de ele_estado_plancha
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 7/12/2012
	 * @return
	 * @throws Exception
	 */
	public Long consultarConsecutivoEstadoPlancha() throws Exception {
		Long consecutivo = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			Query query = session.getNamedQuery("obtener.consecutivo.estado.plancha");
			consecutivo = (Long) query.uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
		return consecutivo;
	}

	/**
	 * Metodo que consulta la plancha por consecutivo.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 7/12/2012
	 * @param consecutivoPlancha
	 * @return
	 * @throws Exception
	 */
	public ElePlancha consultarPlanchaPorId(Long consecutivoPlancha) throws Exception {
		ElePlancha plancha = null;
		try {
			plancha = dao.findById(consecutivoPlancha);
		} catch (Exception e) {
			throw e;
		}
		return plancha;
	}

	/**
	 * Metodo que modifica la plancha elecciones 2012.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 7/12/2012
	 * @param plancha
	 * @throws Exception
	 */
	public void modificarPlancha(ElePlancha plancha) throws Exception {
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			dao.merge(plancha);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			throw e;
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
	}

	/**
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 7/12/2012
	 * @param plancha
	 * @throws Exception
	 */
	public void guardarEstadoPlancha(EleEstadoPlancha plancha) throws Exception {
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			daoEstadoPlanchaDAO.save(plancha);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			throw e;
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
	}

	/**
	 * Obtiene la información de la plancha para desplegarla en el fomato CO-FT-172
	 * – Expedición de resolución de admision
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario
	 *         Alejandro Acevedo</a> - GSISIN <br>
	 * @param numPlancha
	 * @return InfoPlanchaAdmisionPdfDTO
	 */
	public InfoPlanchaAdmisionPdfDTO obtenerInfoPlanchaAdmision(Long numPlancha, Long codFormato) {

		Session session = HibernateSessionFactoryElecciones2012.getSession();
		InfoPlanchaAdmisionPdfDTO info = new InfoPlanchaAdmisionPdfDTO();

		// por indicaciones del ingeniero jose luis no va a ser necesario
		// obtener la información
		// de la tabla ELE_DETALLE_FORMATO_PLANCHA
		/*
		 * StringBuffer sql = new
		 * StringBuffer(" SELECT pl_aso.CONSECUTIVO_PLANCHA NUMPLANCHA, " +
		 * "pl.CODIGO_ZONA_ELE ZONA,  pl.FECHA_REGISTRO FECHA, aso.NUMERO_DOCUMENTO DOCUMENTO, "
		 * +
		 * "cl.NOMCL1 NOMBRES, db2util.get_cliciudad(cl.LUGEXP) CIUDADEXP, cl.FECESC FECHAEXP, "
		 * +
		 * "format.NUMERO_RESOLUCION RESOLUCION, format.NUMERO_ACTA ACTA, format.FECHA_ACTA FECHAACTA, "
		 * +
		 * "format.NUMERO_COMISION_ELECT COMISION, cl.NUMINT NUMINT FROM elecdb.ELE_ASOCIADO aso, elecdb.ELE_PLANCHA_ASOCIADO pl_aso, "
		 * +
		 * "elecdb.ELE_ZONA_ELECTORAL zona, elecdb.ELE_PLANCHA pl, elecdb.CLIMAE cl, elecdb.ELE_PARAMETRO param, "
		 * +
		 * "elecdb.ELE_PARAMETRO_TIPO paramT, elecdb.ELE_DETALLE_FORMATO_PLANCHA format WHERE pl.CONSECUTIVO_PLANCHA = :numPlancha and  "
		 * +
		 * "paramT.TIP_COD = 1 and paramT.TIP_COD = param.TIP_COD and param.VALOR = pl.ESTADO_PLANCHA and "
		 * +
		 * "pl_aso.CONSECUTIVO_PLANCHA = pl.CONSECUTIVO_PLANCHA and  pl.CODIGO_ZONA_ELE = zona.CODIGO_ZONA_ELE and "
		 * +
		 * "aso.CODIGO_ASOCIADO = pl_aso.CODIGO_ASOCIADO and  pl_aso.NUMERO_INSCRITO = 1 and pl_aso.TIPO_INSCRITO = 1 and  pl_aso.CODIGO_ASOCIADO = cl.NUMINT and "
		 * +
		 * " pl_aso.CODIGO_ASOCIADO = format.CODIGO_ASOCIADO and  format.CODIGO_FORMATO = :codFormato"
		 * );
		 */

		StringBuffer sql = new StringBuffer(" SELECT pl_aso.CONSECUTIVO_PLANCHA NUMPLANCHA, "
				+ "pl.CODIGO_ZONA_ELE ZONA,  pl.FECHA_REGISTRO FECHA, aso.NUMERO_DOCUMENTO DOCUMENTO, "
				+ "cl.NOMCL1 NOMBRES, null CIUDADEXP, null FECHAEXP, "
				+ "cl.NUMINT NUMINT, zona.NUMERO_ZONA_ELE NUMEROZONA,zona.DESCRIPCION_ZONA_ELE DESZONA FROM elecdb.ELE_ASOCIADO aso, elecdb.ELE_PLANCHA_ASOCIADO pl_aso, "
				+ "elecdb.ELE_ZONA_ELECTORAL zona, elecdb.ELE_PLANCHA pl, elecdb.CLIMAE cl, elecdb.ELE_PARAMETRO param, "
				+ "elecdb.ELE_PARAMETRO_TIPO paramT WHERE pl.CONSECUTIVO_PLANCHA = :numPlancha and  "
				+ "paramT.TIP_COD = 1 and paramT.TIP_COD = param.TIP_COD and param.VALOR = pl.ESTADO_PLANCHA and "
				+ "pl_aso.CONSECUTIVO_PLANCHA = pl.CONSECUTIVO_PLANCHA and  pl.CODIGO_ZONA_ELE = zona.CODIGO_ZONA_ELE and "
				+ "aso.CODIGO_ASOCIADO = pl_aso.CODIGO_ASOCIADO and  pl_aso.NUMERO_INSCRITO = 1 and pl_aso.TIPO_INSCRITO = 1 and  "
				+ "pl_aso.CODIGO_ASOCIADO = cl.NUMINT ");

		SQLQuery query = session.createSQLQuery(sql.toString());

		query.addScalar("NUMPLANCHA", Hibernate.LONG);
		query.addScalar("ZONA", Hibernate.STRING);
		query.addScalar("FECHA", Hibernate.TIMESTAMP);
		query.addScalar("DOCUMENTO", Hibernate.LONG);
		query.addScalar("NOMBRES", Hibernate.STRING);
		/*
		 * query.addScalar("RESOLUCION", Hibernate.STRING); query.addScalar("ACTA",
		 * Hibernate.STRING); query.addScalar("FECHAACTA", Hibernate.STRING);
		 * query.addScalar("COMISION", Hibernate.STRING);
		 */
		query.addScalar("NUMINT", Hibernate.LONG);
		query.addScalar("NUMEROZONA", Hibernate.STRING);
		query.addScalar("DESZONA", Hibernate.STRING);

		try {
			query.setLong("numPlancha", numPlancha);
			// query.setLong("codFormato", codFormato);
			List<Object[]> datos = (List<Object[]>) query.list();

			for (Object[] objects : datos) {

				List<String> nombresApellidosProcesados = new ArrayList<String>();
				nombresApellidosProcesados = procesaNombresApellidos((String) objects[4]);

				info.setNumeroPlancha((Long) objects[0]);
				// info.setZona(objects[1].toString());
				/** 14012013 - cambio debe codigo numero. */
				info.setZona(objects[6].toString());
				info.setAgencia(objects[7].toString());

				SimpleDateFormat formatterMes = new SimpleDateFormat("MM");
				SimpleDateFormat formatterDia = new SimpleDateFormat("dd");
				SimpleDateFormat formatterHora = new SimpleDateFormat("HH");
				SimpleDateFormat formatterAnno = new SimpleDateFormat("yyyy");
				SimpleDateFormat formatterMinutos = new SimpleDateFormat("mm");

				info.setAnnoInsc(formatterAnno.format(objects[2]));
				info.setMesInsc(formatterMes.format(objects[2]));
				info.setDiaInsc(formatterDia.format(objects[2]));
				info.setHoraInsc(formatterHora.format(objects[2]) + ":" + formatterMinutos.format(objects[2]));

				info.setNumCedula((Long) objects[3]);
				info.setNombres(nombresApellidosProcesados.get(2) + " " + nombresApellidosProcesados.get(3));
				info.setApellidos(nombresApellidosProcesados.get(0) + " " + nombresApellidosProcesados.get(1));

				Date fechaActual = new Date();
				info.setAnnoAct(formatterAnno.format(fechaActual));
				info.setMesAct(formatterMes.format(fechaActual));
				info.setDiaAct(formatterDia.format(fechaActual));
				info.setHoraAct(formatterHora.format(fechaActual) + ":" + formatterMinutos.format(fechaActual));

				/*
				 * info.setResolucion(objects[5].toString());
				 * info.setActa(objects[6].toString());
				 * info.setFechaActa((objects[7].toString()).substring(0, 11));
				 * info.setComision(objects[8].toString());
				 */
				// info.setNumint((Long) objects[9]);
				info.setNumint((Long) objects[5]);
			}

			return info;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Metodo que verifica si la fecha actual esta entre el periodo parametrizado
	 * para generar la radicación de Admisión.
	 * 
	 * @author Mario Alejandro Acevedo <br>
	 * @return Boolean
	 * @throws Exception
	 */
	public boolean validarFechaMarcarPlanchaAdmision() throws Exception {

		Boolean esFechaAdmision = false;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		Timestamp fechaIni = null, fechaFin = null, fechaActual = new Timestamp(new Date().getTime());
		SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantesProperties.FORMATO_FECHA_TIME_STAMP);
		try {
			Query query = session.getNamedQuery("consulta.parametrizacion.fechas.admision");
			List<String> elements = (List<String>) query.list();
			if (elements != null && elements.size() > 0) {
				for (int i = 0; i < elements.size(); i++) {
					if (i == 0) {
						java.util.Date parsedDateIni = dateFormat.parse(elements.get(i).toString());
						fechaIni = new java.sql.Timestamp(parsedDateIni.getTime());
					} else if (i == 1) {
						java.util.Date parsedDateFin = dateFormat.parse(elements.get(i).toString());
						fechaFin = new java.sql.Timestamp(parsedDateFin.getTime());
					}
				}
			}

			if (fechaIni != null && fechaFin != null) {
				if (!(fechaActual.before(fechaIni)) && !(fechaActual.after(fechaFin))) {
					esFechaAdmision = true;
				}
			}

		} catch (Exception e) {
			throw e;
		}

		return esFechaAdmision;
	}

	/**
	 * Verifica si existe registro del asociado para determinado formato
	 * 
	 * @param codAsociado
	 * @param codFormato
	 * @return int
	 */
	public int totalDetallesFormato(Long codAsociado, Long codFormato) {

		int total = 0;
		Session session = HibernateSessionFactoryElecciones2012.getSession();

		try {
			StringBuffer sql = new StringBuffer(" select count(*) TOTAL_DETALLES from "
					+ "elecdb.ELE_DETALLE_FORMATO_PLANCHA format where format.CODIGO_ASOCIADO = :codAsociado and "
					+ " format.CODIGO_FORMATO = :codFormato");

			SQLQuery query = session.createSQLQuery(sql.toString());

			query.setLong("codAsociado", codAsociado);
			query.setLong("codFormato", codFormato);
			query.addScalar("TOTAL_DETALLES", Hibernate.INTEGER);
			total = (Integer) query.uniqueResult();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * Metodo que verifica si la fecha actual esta entre el periodo parametrizado
	 * para generar la constancia.
	 * 
	 * @author Mario Alejandro Acevedo <br>
	 * @return Boolean
	 * @throws Exception
	 */
	public boolean validarFechaPlanchaConstancia() throws Exception {

		Boolean esFechaConstancia = false;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		Timestamp fechaIni = null, fechaFin = null, fechaActual = new Timestamp(new Date().getTime());
		SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantesProperties.FORMATO_FECHA_TIME_STAMP);
		try {
			Query query = session.getNamedQuery("consulta.parametrizacion.fechas.constancia");
			List<String> elements = (List<String>) query.list();
			if (elements != null && elements.size() > 0) {
				for (int i = 0; i < elements.size(); i++) {
					if (i == 0) {
						java.util.Date parsedDateIni = dateFormat.parse(elements.get(i).toString());
						fechaIni = new java.sql.Timestamp(parsedDateIni.getTime());
					} else if (i == 1) {
						java.util.Date parsedDateFin = dateFormat.parse(elements.get(i).toString());
						fechaFin = new java.sql.Timestamp(parsedDateFin.getTime());
					}
				}
			}

			if (fechaIni != null && fechaFin != null) {
				if (!(fechaActual.before(fechaIni)) && !(fechaActual.after(fechaFin))) {
					esFechaConstancia = true;
				}
			}

		} catch (Exception e) {
			throw e;
		}

		return esFechaConstancia;
	}

	/**
	 * Obtiene la Ciudad de la agencia de vinculación del asociado
	 * 
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario
	 *         Alejandro Acevedo</a> - GSISIN <br>
	 * @param numint
	 * @return String
	 */
	public String obtenerCiudadAgenciaVinculacion(Long numint) {

		Session session = HibernateSessionFactoryElecciones2012.getSession();
		String info = "";

		StringBuffer sql = new StringBuffer(" SELECT NOMAGC " + "FROM SEGURIDAD.PLTAGCORI "
				+ "WHERE AGCORI = (SELECT C.AGCVIN FROM elecdb.CLIMAE C " + "	WHERE C.NUMINT = :numint)"
				+ " AND CODEMP = 67890");

		SQLQuery query = session.createSQLQuery(sql.toString());

		query.addScalar("NOMAGC", Hibernate.STRING);

		try {
			query.setLong("numint", numint);

			info = query.uniqueResult().toString();

			return info;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Metodo que obtiene las fechas para expedir la admisión
	 * 
	 * @author Mario Alejandro Acevedo <br>
	 * @return String
	 * @throws Exception
	 */
	public String obtieneFechasAdmision() throws Exception {

		Session session = HibernateSessionFactoryElecciones2012.getSession();
		String fecha1 = "";
		String fecha2 = "";
		String fechas = "";
		try {
			Query query = session.getNamedQuery("consulta.parametrizacion.fechas.admision");
			List<String> elements = (List<String>) query.list();
			if (elements != null && elements.size() > 0) {
				for (int i = 0; i < elements.size(); i++) {
					if (i == 0) {
						fecha1 = elements.get(i);
					} else if (i == 1) {
						fecha2 = elements.get(i);
					}
				}
				fechas = fecha1.substring(0, 11) + " y " + fecha2.substring(0, 11);
			}

		} catch (Exception e) {
			throw e;
		}

		return fechas;
	}

	/**
	 * Metodo que obtiene las fechas para generar constancia de recibo
	 * 
	 * @author Mario Alejandro Acevedo <br>
	 * @return String
	 * @throws Exception
	 */
	public String obtieneFechasConstancia() throws Exception {

		Session session = HibernateSessionFactoryElecciones2012.getSession();
		String fecha1 = "";
		String fecha2 = "";
		String fechas = "";
		try {
			Query query = session.getNamedQuery("consulta.parametrizacion.fechas.constancia");
			List<String> elements = (List<String>) query.list();
			if (elements != null && elements.size() > 0) {
				for (int i = 0; i < elements.size(); i++) {
					if (i == 0) {
						fecha1 = elements.get(i);
					} else if (i == 1) {
						fecha2 = elements.get(i);
					}
				}
				fechas = fecha1.substring(0, 11) + " y " + fecha2.substring(0, 11);
			}

		} catch (Exception e) {
			throw e;
		}

		return fechas;
	}

	/**
	 * Convierte el caracter inicial de cada palabra de una cadena a mayúscula
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 2/01/2013
	 * @param str
	 * @return String
	 */
	private String initCap(String str) {

		if (str == null || str.length() < 1) {
			return str;
		}

		if (str.length() == 1) {
			return str.toUpperCase();
		}

		StringBuffer retBuff = new StringBuffer("");
		String testString = str.trim();

		String input = testString.toLowerCase();
		String allWords[] = input.split(" ");

		for (int i = 0; i < allWords.length; i++) {
			if (allWords[i] == null || "".equals(allWords[i])) {
				continue;
			}

			Character ch = new Character(allWords[i].charAt(0));
			String modified = ch.toString().toUpperCase().toString() + allWords[i];
			StringBuffer strBuf = new StringBuffer(modified);
			strBuf.deleteCharAt(1);
			retBuff.append(strBuf.append(" ").toString());
		}

		return retBuff.toString();
	}

	public static void main(String[] args) {
		try {
			System.out.println("19670215".substring(0, 9));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}