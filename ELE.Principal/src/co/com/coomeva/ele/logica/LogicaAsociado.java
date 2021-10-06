package co.com.coomeva.ele.logica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import co.com.coomeva.ele.delegado.DelegadoLogHab;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoZonaElectoral;
import co.com.coomeva.ele.dto.DTOHabilidadAsociado;
import co.com.coomeva.ele.entidades.admhabilidad.Asoelecf;
import co.com.coomeva.ele.entidades.admhabilidad.AsoelecfDAO;
import co.com.coomeva.ele.entidades.admhabilidad.HibernateSessionFactoryHab;
import co.com.coomeva.ele.entidades.climae.HibernateSessionFactoryClimae;
import co.com.coomeva.ele.entidades.habilidad.EleAsociado;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.habilidad.dao.EleAsociadoDAO;
import co.com.coomeva.ele.modelo.AsociadoReporteDTO;
import co.com.coomeva.ele.modelo.EleAsociadoDatosDTO;
import co.com.coomeva.ele.modelo.FiltrosConsultasDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.resources.LoaderResourceElements;

public class LogicaAsociado extends AsoelecfDAO {

	private static LogicaAsociado instance;
	private LogicaProceso logicaProceso = LogicaProceso.getInstance();
	private LoaderResourceElements loaderResourceElements = LoaderResourceElements.getInstance();
	private EleAsociadoDAO dao = new EleAsociadoDAO();
	private List<FiltrosConsultasDTO> zonasElectorales;
	private HashMap<Long, String> zonasHash;

	private Long CODIGO_FORMATO_INSCRIPCION_PLANCHA = new Long(
			UtilAcceso.getParametroFuenteL(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					ConstantesProperties.CODIGO_FORMATO_INSCRIPCION_PLANCHA));

	/**
	 * Constructor de la Clase Privado
	 */
	private LogicaAsociado() {
		cargarZonasElectorales();
	}

	public void cargarZonasElectorales() {
		try {
			zonasElectorales = DelegadoZonaElectoral.getInstance().consultarZonasElectorales();
			if (zonasElectorales != null) {
				zonasHash = new HashMap<Long, String>();
				for (int i = 0; i < zonasElectorales.size(); i++) {
					zonasHash.put(zonasElectorales.get(i).getCodigoFiltro(),
							zonasElectorales.get(i).getDescripcionFiltro());
				}
			}
		} catch (Exception e) {
			zonasElectorales = null;
		}
	}

	/**
	 * Patrón Singular
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * 
	 */
	public static LogicaAsociado getInstance() {
		if (instance == null) {
			instance = new LogicaAsociado();
		}
		return instance;
	}

	/**
	 * Metodo de consulta usado por el WS para la habilidad financiera del Asociado
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param identificacion
	 * @return Asoelecf
	 * @throws Exception
	 */

	public Asoelecf findAsoHabFinWS(Long identificacion) throws Exception {
		if (identificacion == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumIdentificacion"));
		}
		Criteria criteria = HibernateSessionFactoryHab.getSession().createCriteria(Asoelecf.class);
		criteria.add(Restrictions.eq("wnitcli", identificacion));
		Asoelecf aso = null;
		try {
			aso = (Asoelecf) criteria.uniqueResult();

			if (aso == null) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajesWs", "noAsociado"));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.getSession().close();
		}

		return aso;
	}

	/**
	 * Metodo de consulta del asociado en la tabla ASOHAB
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param identificacion
	 * @return Asoelecf
	 * @throws Exception
	 */

	public Asoelecf findAsoHabFin(Long identificacion) throws Exception {
		if (identificacion == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumIdentificacion"));
		}
		Criteria criteria = HibernateSessionFactoryClimae.getSession().createCriteria(Asoelecf.class);
		criteria.add(Restrictions.eq("wnitcli", identificacion));
		Asoelecf aso = null;
		try {
			aso = (Asoelecf) criteria.uniqueResult();

			if (aso == null) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noAsociadoHabilidad"));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.getSession().close();
		}

		return aso;
	}

	/**
	 * Metodo de actualizacion del estado del asociado en la tabla ASOHAB
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param estado
	 * @param identificacion
	 * @param concepto
	 * @param usuario
	 * @throws Exception
	 */

	public void cambiarEstado(String estado, Long identificacion, String concepto, String usuario) throws Exception {
		if (identificacion == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumIdentificacion"));
		}
		if (estado == null || estado.trim().equalsIgnoreCase("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noEstadoAsociado"));
		}

		if (concepto == null || concepto.trim().equalsIgnoreCase("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noConcepto"));
		}

		boolean boEstado = false;
		for (int j = 1; j <= UtilAcceso.getParametroFuenteI("parametros", "numeroEstadosAsociado"); j++) {
			if (estado.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "caracterEstadoAsociado" + j))) {
				boEstado = true;
				break;
			}
		}

		if (!boEstado) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noCreadoEstadoAsociado"));
		}

		Asoelecf asociado = findAsoHabFinWS(identificacion);
		HibernateSessionFactoryHab.getSession().refresh(asociado);

		if (asociado == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noExisteAsociado"));
		}

		if (estado.trim().equalsIgnoreCase(asociado.getWindhab().trim())) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noSeRealizoCambio"));
		}
		String antHabil = asociado.getWindhab().trim();
		asociado.setWindhab(estado);
		Transaction transaction = null;
		try {
			transaction = HibernateSessionFactoryHab.getSession().beginTransaction();
			this.merge(asociado);
			DelegadoLogHab.getInstance().registrarLog(identificacion.toString(), antHabil, estado, concepto, usuario);
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		} finally {
			transaction = null;
			HibernateSessionFactoryHab.getSession().clear();
		}
	}

	/**
	 * Metodo que realiza una busqueda nativa en la base de datos con el fin de
	 * retornar una lista de Asociados dependiendo de los criterios ingresados como
	 * parametros el metodo realiza o no el filtro de la información
	 * 
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param zona
	 * @param oficina
	 * @param regional
	 * @param estado
	 * @return List<AsociadoReporteDTO>
	 * @throws Exception
	 */

	public List<AsociadoReporteDTO> findAsoHabFinNative(String zona, String oficina, String regional, String estado)
			throws Exception {

		StringBuffer sql = new StringBuffer();
		StringBuffer where = new StringBuffer(" WHERE ");
		SQLQuery sqlQuery = null;
		int criterios = 0;
		String and = " AND ";
		List returnList = new ArrayList();
		List<AsociadoReporteDTO> asociados = new ArrayList<AsociadoReporteDTO>();

		sql.append(" SELECT WNITCLI,WNOMCLI,WNOMREG,WNOMZON,WNOMAGC,WINDHAB,WMEDVOT,WINDRET ");
		sql.append(" FROM ELECLIB.ASOELECF ");

		if (zona != null && !zona.equals("")) {
			where.append("WZONA = :zona");
			criterios++;
		}

		if (criterios > 0)
			and = " AND ";
		else
			and = " ";

		if (oficina != null && !oficina.equals("")) {
			where.append(and);
			where.append("WAGCORI = :oficina");
			criterios++;
		}

		if (criterios > 0)
			and = " AND ";
		else
			and = " ";

		if (regional != null && !regional.equals("")) {
			where.append(and);
			where.append("WCODREG = :regional");
			criterios++;
		}

		if (criterios > 0)
			and = " AND ";
		else
			and = " ";

		if (estado != null && !estado.equals("")) {
			where.append(and);
			where.append("WINDHAB = :estado");
			criterios++;
		}

		if (criterios > 0) {
			sql.append(where.toString());
		}
		sql.append(" ORDER BY WNITCLI");
		sqlQuery = getSession().createSQLQuery(sql.toString());

		// CONFIGURA TIPO DATO COLUMNAS SELECT
		sqlQuery.addScalar("WNITCLI", Hibernate.LONG);
		sqlQuery.addScalar("WNOMCLI", Hibernate.STRING);
		sqlQuery.addScalar("WNOMREG", Hibernate.STRING);
		sqlQuery.addScalar("WNOMZON", Hibernate.STRING);
		sqlQuery.addScalar("WNOMAGC", Hibernate.STRING);
		sqlQuery.addScalar("WINDHAB", Hibernate.STRING);
		sqlQuery.addScalar("WMEDVOT", Hibernate.LONG);
		sqlQuery.addScalar("WINDRET", Hibernate.STRING);

		try {

			if (zona != null && !zona.equals("")) {
				sqlQuery.setString("zona", zona);
			}
			if (oficina != null && !oficina.equals("")) {
				sqlQuery.setString("oficina", oficina);
			}
			if (regional != null && !regional.equals("")) {
				sqlQuery.setString("regional", regional);
			}
			if (estado != null && !estado.equals("")) {
				sqlQuery.setString("estado", estado);
			}
			returnList = sqlQuery.list();

			for (int i = 0; i < returnList.size(); i++) {
				Object[] elements = (Object[]) returnList.get(i);
				if (elements != null && elements.length > 0) {
					Long nitcli = (Long) elements[0];
					String nomcli = (String) elements[1];
					String nomreg = (String) elements[2];
					String nomZone = (String) elements[3];
					String nomAge = (String) elements[4];
					String estadoCons = "";
					if (elements[5].toString().trim().equalsIgnoreCase(
							UtilAcceso.getParametroFuenteS("parametros", "caracterEstadoAsociado1"))) {
						estadoCons = UtilAcceso.getParametroFuenteS("parametros", "estadoAsociado1");
					} else
						estadoCons = UtilAcceso.getParametroFuenteS("parametros", "estadoAsociado2");

					String medio = "";
					if (elements[6].toString()
							.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "idMedioUrna"))) {
						medio = UtilAcceso.getParametroFuenteS("parametros", "medio1");
					} else
						medio = UtilAcceso.getParametroFuenteS("parametros", "medio2");

					String actual = "";

					if (elements[7].toString().trim().equalsIgnoreCase(
							UtilAcceso.getParametroFuenteS("parametros", "idEstadoActualFallecido"))) {
						actual = UtilAcceso.getParametroFuenteS("parametros", "estadoActualFallecido");
					} else if (elements[7].toString().trim()
							.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "idEstadoActualExcluido"))) {
						actual = UtilAcceso.getParametroFuenteS("parametros", "estadoActualExcluido");
					} else if (elements[7].toString().trim()
							.equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "idEstadoActualRetirado"))) {
						actual = UtilAcceso.getParametroFuenteS("parametros", "estadoActualRetirado");
					} else
						actual = UtilAcceso.getParametroFuenteS("parametros", "estadoActualActivo");

					AsociadoReporteDTO asociadoReporteDTO = new AsociadoReporteDTO();
					asociadoReporteDTO.setNitcli(nitcli);
					asociadoReporteDTO.setNomAgc(nomAge);
					asociadoReporteDTO.setNomZona(nomZone);
					asociadoReporteDTO.setRegional(nomreg);
					asociadoReporteDTO.setNomCli(nomcli);
					asociadoReporteDTO.setEstado(estadoCons);
					asociadoReporteDTO.setMedio(medio);
					asociadoReporteDTO.setEstadoAsociado(actual);
					asociados.add(asociadoReporteDTO);

				} // fin if
			} // fin for

		} catch (Exception e) {
			throw e;
		} finally {
			returnList = null;
		}

		return asociados;
	}

	// ###################################################### ELECCIONES 2012
	// ####################################################################

	/**
	 * Consulta habilidad de un asociado
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 8/11/2012
	 * @param numeroDocumento
	 * @return
	 * @throws Exception
	 */
	public DTOHabilidadAsociado consultarHabilidadAsociado(Long numeroDocumento) throws Exception {
		Boolean habilidad = false;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		DTOHabilidadAsociado dtoHabilidadAsociado = new DTOHabilidadAsociado();
		dtoHabilidadAsociado.setAsociadoHabil(Boolean.TRUE);

		try {
			Query query = session.getNamedQuery(ConstantesNamedQueries.QUERY_HABILIDAD_DE_ASOCIADO);
			query.setLong("numIdAsociado", numeroDocumento);

			Object resultado = query.uniqueResult();
			habilidad = (resultado == null || resultado.toString().equals("") || resultado.toString().equals("0"))
					? false
					: true;
			if (!habilidad) {
				query = session.getNamedQuery(ConstantesNamedQueries.QUERY_OBSERVACIONES_INHABILIDADES_ASOCIADO);
				query.setLong("numIdAsociado", numeroDocumento);
				List<String> observaciones = (List<String>) query.list();
				List<String> lstObservaciones = new ArrayList<String>();
				if (observaciones.size() > 0) {
					dtoHabilidadAsociado.setAsociadoHabil(Boolean.FALSE);
					// Reviso si tiene inhabilidad por temas crediticios y separo los mensajes
					for (String obs : observaciones) {
						if (obs.contains("Bancoomeva") && obs.contains("Cooperativa")) {
							String[] obsSplit = obs.split("\\.");
							if (obsSplit.length >= 2) {
								lstObservaciones.add(obsSplit[0].trim());
								lstObservaciones.add(obsSplit[1].trim());
							}
						} else {
							lstObservaciones.add(obs);
						}
					}
					dtoHabilidadAsociado.setObservacionesInhabilidades(lstObservaciones);
				} else {
					return null;
				}

			}

		} catch (Exception e) {
			throw new Exception(loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL, "consulta.habilidad.asociado"));
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
			habilidad = null;
		}

		return dtoHabilidadAsociado;
	}

	// obtiene el codigo del asociado dado el documento
	// Autor: Mario Alejandro Acevedo
	public Long obtieneCodAsociado(Long documento) {
		Long resultado = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			Query query = session.getNamedQuery("consultaCodigoAsociado");
			query.setLong("documento", documento);

			resultado = (Long) query.uniqueResult();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	/**
	 * Metodo que consulta el total de los asociados habiles filtrados por
	 * profesion, identificacion o zona.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 14/11/2012
	 * @param codProfesion
	 * @param numeroDocumento
	 * @param codZona
	 * @return
	 * @throws Exception
	 */
	public int consultarTotalAsociadosHabilesAsociado(Long codProfesion, Long numeroDocumento, Long codZona)
			throws Exception {
		int total = 0;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			StringBuffer sql = new StringBuffer("SELECT COUNT(DISTINCT A.NUMERO_DOCUMENTO)  TOTAL_ASOCIADOS ");

			sql.append("FROM ELECDB.ELE_ASOCIADO A INNER JOIN MULCLIDAT.CLIMAE C ON C.NUMINT = A.CODIGO_ASOCIADO ");
			sql.append(
					"WHERE (A.CODIGO_ASOCIADO NOT IN(SELECT I.CODIGO_ASOCIADO FROM ELECDB.ELE_INHABILIDAD I WHERE I.CODIGO_ASOCIADO = A.CODIGO_ASOCIADO) ");
			sql.append(
					"OR (A.CODIGO_ASOCIADO IN (SELECT I.CODIGO_ASOCIADO FROM ELECDB.ELE_INHABILIDAD I WHERE I.CODIGO_ASOCIADO = A.CODIGO_ASOCIADO) ");
			sql.append("AND((SELECT N4.CONSECUTIVO_NOVEDAD FROM ELECDB.ELE_NOVEDAD N4 WHERE N4.CODIGO_ASOCIADO = ");
			sql.append("A.CODIGO_ASOCIADO AND N4.ESTADO_HABILIDAD = '1')= ");
			sql.append("(SELECT MAX(N3.CONSECUTIVO_NOVEDAD) FROM ELECDB.ELE_NOVEDAD N3 WHERE N3.CODIGO_ASOCIADO = ");
			sql.append("A.CODIGO_ASOCIADO))))");

			if (numeroDocumento != null) {
				sql.append("AND A.NUMERO_DOCUMENTO = :numeroDocumento ");
			}

			if (codZona != null) {
				sql.append("AND A.COD_ZONA_ASO = :codZona ");
			}

			if (codProfesion != null) {
				sql.append("AND A.CODIGO_PROFESION = :codProfesion ");
			}

			SQLQuery query = session.createSQLQuery(sql.toString());
			query.addScalar("TOTAL_ASOCIADOS", Hibernate.INTEGER);

			if (numeroDocumento != null) {
				query.setParameter("numeroDocumento", numeroDocumento);
			}

			if (codProfesion != null) {
				query.setParameter("codProfesion", codProfesion);
			}

			if (codZona != null) {
				query.setParameter("codZona", codZona);
			}

			total = (Integer) query.uniqueResult();

		} catch (Exception e) {
			// System.out.println("Error consultando el total de asociados habiles:
			// "+e.getMessage());
			throw new Exception(loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL, "consulta.total.asociados.habiles"));
		} finally {
			session.close();
		}
		return total;
	}

	/**
	 * Metodo que consulta los asociados habiles filtrados por profesion,
	 * identificacion o zona, consulta paginada para el reporte.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 14/11/2012
	 * @param codProfesion
	 * @param numeroDocumento
	 * @param codZona
	 * @return
	 * @throws Exception
	 */
	public List<EleAsociadoDatosDTO> consultarAsociadosHabilesAsociado(Long codProfesion, Long numeroDocumento,
			Long codZona) throws Exception {
		List<EleAsociadoDatosDTO> list = new ArrayList<EleAsociadoDatosDTO>();
		int startRow = 1;
		int total = consultarTotalAsociadosHabilesAsociado(codProfesion, numeroDocumento, codZona);
		int numRegistros = 100;
		int maxResults = startRow + numRegistros;
		List<Object[]> elements = null;
		EleAsociadoDatosDTO dto = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			StringBuffer sql = new StringBuffer(
					"SELECT DISTINCT A.NUMERO_DOCUMENTO AS NUMINT, C.NOMCLI, A.DESC_CIUDAD_ASO AS CIUDAD, ");
			sql.append(
					"db2util.get_fecha_iso(C.FECCON) AS ECHA_NACIMIENTO, db2util.get_fecha_iso(C.FECING) AS FECHA_VINCULACION, ");
			sql.append(
					"db2util.get_codnom_clitab(42, C.CODSEX) AS GENERO, DB2UTIL.SIP_PROFESION(C.NUMINT,'AS') AS PROFESION, ");
			sql.append("A.DESC_ZONA_ASO AS ZONA, C.ESTRAT, ");
			sql.append("(SELECT (CASE WHEN A2.CODIGO_ASOCIADO IS NULL THEN '0' ELSE '1' END) AS HABILIDAD_ASOCIADO "
					+ "FROM ELECDB.ELE_ASOCIADO A2 WHERE A2.NUMERO_DOCUMENTO = A.NUMERO_DOCUMENTO "
					+ "AND (A2.CODIGO_ASOCIADO NOT IN (SELECT I.CODIGO_ASOCIADO FROM ELECDB.ELE_INHABILIDAD I "
					+ "WHERE I.CODIGO_ASOCIADO = A2.CODIGO_ASOCIADO) OR (A2.CODIGO_ASOCIADO IN "
					+ "(SELECT I.CODIGO_ASOCIADO FROM ELECDB.ELE_INHABILIDAD I WHERE I.CODIGO_ASOCIADO = A2.CODIGO_ASOCIADO) "
					+ "AND (SELECT N.ESTADO_HABILIDAD FROM ELECDB.ELE_NOVEDAD N WHERE N.CONSECUTIVO_NOVEDAD = "
					+ "(SELECT MAX(N2.CONSECUTIVO_NOVEDAD) FROM ELECDB.ELE_NOVEDAD N2 WHERE N2.CODIGO_ASOCIADO = A2.CODIGO_ASOCIADO)) = '1'))) AS HABILIDAD_ASOCIADO, ");
			sql.append(
					"(SELECT NOMAGC FROM SEGURIDAD . PLTAGCORI WHERE AGCORI = C.AGCVIN AND CODEMP = 67890 ) AS OFICINA, ");
			sql.append(
					"(SELECT CTAB . CODNOM FROM SEGURIDAD . PLTAGCORI PLT , MULCLIDAT . CLITAB CTAB WHERE PLT . CODEMP = 67890 "
							+ "AND CTAB . CODTAB = 907 AND PLT . AGCORI = C.AGCVIN AND PLT . CODREG = CTAB . CODINT) AS REGIONAL ");

			sql.append("FROM ELECDB.ELE_ASOCIADO A INNER JOIN MULCLIDAT.CLIMAE C ON C.NUMINT = A.CODIGO_ASOCIADO ");
			sql.append(
					"WHERE (A.CODIGO_ASOCIADO NOT IN(SELECT I.CODIGO_ASOCIADO FROM ELECDB.ELE_INHABILIDAD I WHERE I.CODIGO_ASOCIADO = A.CODIGO_ASOCIADO) ");
			sql.append(
					"OR (A.CODIGO_ASOCIADO IN (SELECT I.CODIGO_ASOCIADO FROM ELECDB.ELE_INHABILIDAD I WHERE I.CODIGO_ASOCIADO = A.CODIGO_ASOCIADO) ");
			sql.append("AND((SELECT N4.CONSECUTIVO_NOVEDAD FROM ELECDB.ELE_NOVEDAD N4 WHERE N4.CODIGO_ASOCIADO = ");
			sql.append("A.CODIGO_ASOCIADO AND N4.ESTADO_HABILIDAD = '1')= ");
			sql.append("(SELECT MAX(N3.CONSECUTIVO_NOVEDAD) FROM ELECDB.ELE_NOVEDAD N3 WHERE N3.CODIGO_ASOCIADO = ");
			sql.append("A.CODIGO_ASOCIADO))))");

			if (numeroDocumento != null) {
				sql.append("AND A.NUMERO_DOCUMENTO = :numeroDocumento ");
			}

			if (codProfesion != null) {
				sql.append("AND A.CODIGO_PROFESION = :codProfesion ");
			}

			if (codZona != null) {
				sql.append("AND A.COD_ZONA_ASO = :codZona ");
			}

			sql.append("ORDER BY NUMINT, REGIONAL, ZONA, OFICINA ASC");

			SQLQuery query = session.createSQLQuery(sql.toString());
			query.addScalar("NUMINT", Hibernate.LONG);
			query.addScalar("NOMCLI", Hibernate.STRING);
			query.addScalar("DESC_CIUDAD_ASO CIUDAD", Hibernate.STRING);
			query.addScalar("FECHA_NACIMIENTO", Hibernate.STRING);
			query.addScalar("FECHA_VINCULACION", Hibernate.STRING);
			query.addScalar("GENERO", Hibernate.STRING);
			query.addScalar("PROFESION", Hibernate.STRING);
			query.addScalar("DESC_ZONA_ASO ZONA", Hibernate.STRING);
			query.addScalar("ESTRAT", Hibernate.STRING);
			query.addScalar("HABILIDAD_ASOCIADO", Hibernate.STRING);
			query.addScalar("OFICINA", Hibernate.STRING);
			query.addScalar("REGIONAL", Hibernate.STRING);

			if (numeroDocumento != null) {
				query.setParameter("numeroDocumento", numeroDocumento);
			}

			if (codZona != null) {
				query.setParameter("codZona", codZona);
			}

			if (codProfesion != null) {
				query.setParameter("codProfesion", codProfesion);
			}

			while (startRow <= total) {
				elements = query.setMaxResults(numRegistros).setFirstResult(startRow).list();

				if (elements != null && elements.size() > 0) {
					for (Object[] object : elements) {
						dto = new EleAsociadoDatosDTO();
						dto.setAsonumint(object[0].toString());
						dto.setNombreCompleto(object[1].toString());
						dto.setCiudad(object[2].toString());
						dto.setFechaNacimiento(object[3].toString());
						dto.setFechaVinculacion(object[4].toString());
						dto.setGenero(object[5].toString());
						dto.setProfesion(object[6].toString());
						dto.setZonaElectoral(object[7].toString());
						dto.setEstratoSocial(object[8].toString());
						dto.setEstadoHabilidad(object[9].toString());
						dto.setOficina(object[10].toString());
						dto.setRegional(object[11].toString());
						list.add(dto);
					}
				}

				int c = startRow;
				c += numRegistros;
				if (c > total) {
					startRow += (total - startRow);
				} else {
					startRow += numRegistros;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Error generando reporte asociados habiles:
			// "+e.getMessage());
			throw new Exception(loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL, "consulta.asociados.habiles"));
		} finally {
			session.close();
		}
		return list;
	}

	/**
	 * Metodo que consulta el total de los asociados habiles
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 14/11/2012
	 * @param codProfesion
	 * @param numeroDocumento
	 * @param codZona
	 * @return
	 * @throws Exception
	 */
	public int consultarTotalAsociadosHabiles(Long zona) throws Exception {
		int total = 0;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			/**
			 * Se modifica la consulta para excluir los asociados que se encuentran en la
			 * tabla ELE_ASOCIADO_ESPECIAL
			 */
			StringBuffer sql = new StringBuffer("SELECT COUNT(repHabil.NUMERO_DOCUMENTO) TOTAL_ASOCIADOS ");
			sql.append("FROM ELECDB.ELE_REPORTE_HABIL repHabil WHERE repHabil.TIPO_VALIDACION = 1 "
					+ "AND NOT EXISTS (SELECT * FROM  ELECDB.ELE_ASOCIADO_ESPECIAL asoEsp where repHabil.NUMERO_DOCUMENTO = asoEsp.NUMERO_DOCUMENTO ) ");

			if (zona != null) {
				sql.append("AND ZONA_ASOCIADO LIKE '" + zonasHash.get(zona) + "'");
				// System.out.println("Zona: "+zonasHash.get(zona));
			}

			SQLQuery query = session.createSQLQuery(sql.toString());
			query.addScalar("TOTAL_ASOCIADOS", Hibernate.LONG);

			Long valor = (Long) query.uniqueResult();
			total = valor.intValue();
			// System.out.println("Total: "+total);
		} catch (Exception e) {
			System.out.println("Error consultando el total de asociados habiles: " + e.getMessage());
			throw new Exception(loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL, "consulta.total.asociados.habiles"));
		} finally {
			session.flush();
		}
		return total;
	}

	/**
	 * Metodo que consulta el total de los asociados especiales habiles
	 * 
	 * @author Juan Diego Montoya
	 * @date 05/09/2016
	 * @param codProfesion
	 * @param numeroDocumento
	 * @param codZona
	 * @return
	 * @throws Exception
	 */
	public int consultarTotalAsociadosEspecialesHabiles(Long zona) throws Exception {
		int total = 0;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			/**
			 * Se modifica la consulta para excluir los asociados que se encuentran en la
			 * tabla ELE_ASOCIADO_ESPECIAL
			 */
			StringBuffer sql = new StringBuffer("SELECT COUNT(repHabil.NUMERO_DOCUMENTO) TOTAL_ASOCIADOS ");
			sql.append("FROM ELECDB.ELE_REPORTE_HABIL repHabil WHERE repHabil.TIPO_VALIDACION = 1 "
					+ "AND EXISTS (SELECT * FROM  ELECDB.ELE_ASOCIADO_ESPECIAL asoEsp where repHabil.NUMERO_DOCUMENTO = asoEsp.NUMERO_DOCUMENTO ) ");

			if (zona != null) {
				sql.append("AND ZONA_ASOCIADO LIKE '" + zonasHash.get(zona) + "'");
				// System.out.println("Zona: "+zonasHash.get(zona));
			}

			SQLQuery query = session.createSQLQuery(sql.toString());
			query.addScalar("TOTAL_ASOCIADOS", Hibernate.LONG);

			Long valor = (Long) query.uniqueResult();
			total = valor.intValue();
			// System.out.println("Total: "+total);
		} catch (Exception e) {
			System.out.println("Error consultando el total de asociados ESPECIALES habiles: " + e.getMessage());
			throw new Exception(loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
					"consulta.total.asociados.especiales.habiles.por.zona"));
		} finally {
			session.flush();
		}
		return total;
	}

	/**
	 * Obtiene el nombre completo del asociado
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 15/11/2012
	 * @param numeroDocumento
	 * @return String
	 */
	public String consultarNombreAsociado(Long numeroDocumento) {
		List qList = null;
		Session session = null;
		String nombreCompletoAsociado = null;

		try {
			session = HibernateSessionFactoryElecciones2012.getSession();
			Query query = session.getNamedQuery(ConstantesNamedQueries.QUERY_NOMBRES_APELLIDOS_ASOCIADO);
			query.setLong("numeroDocumento", numeroDocumento);
			qList = query.list();
			for (Iterator iter = qList.iterator(); iter.hasNext();) {
				Object[] element = (Object[]) iter.next();
				nombreCompletoAsociado = element[2] != null ? element[2].toString() : "";
			}

		} catch (Exception e) {
			System.out.println("No se logro consultar el asociado: " + e.toString());
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
			qList = null;
		}

		return nombreCompletoAsociado;
	}

	/**
	 * Metodo que consulta el total de asociados inhabiles
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 14/11/2012
	 * @param codProfesion
	 * @param numeroDocumento
	 * @param codZona
	 * @return
	 * @throws Exception
	 */
	public int consultarTotalAsociadosInhabiles() throws Exception {
		int total = 0;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			StringBuffer sql = new StringBuffer("SELECT COUNT(NUMERO_DOCUMENTO)  TOTAL_ASOCIADOS ");
			sql.append("FROM ELECDB.ELE_REPORTE_HABIL WHERE TIPO_VALIDACION = 2 ");

			SQLQuery query = session.createSQLQuery(sql.toString());
			query.addScalar("TOTAL_ASOCIADOS", Hibernate.LONG);

			Long valor = (Long) query.uniqueResult();
			total = valor.intValue();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
					"consulta.total.asociados.inhabiles"));
		} finally {
			session.close();
		}
		return total;
	}

	/**
	 * Metodo que consulta los asociados inhabiles
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 14/11/2012
	 * @param codProfesion
	 * @param numeroDocumento
	 * @param codZona
	 * @return
	 * @throws Exception
	 */
	public List<EleAsociadoDatosDTO> consultarAsociadosInhabilesNativa() throws Exception {
		List<EleAsociadoDatosDTO> list = new ArrayList<EleAsociadoDatosDTO>();

		int startRow = 1;
		int total = consultarTotalAsociadosInhabiles();
		int numRegistros = ConstantesProperties.NUMERO_REGISTROS_POR_PAGINA_CONSULTAS;

		EleAsociadoDatosDTO dto = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			StringBuffer sql = new StringBuffer(
					"SELECT RH.NUMERO_DOCUMENTO, RH.NOMBRE_ASOCIADO, RH.ZONA_ASOCIADO, RH.PROFESION_ASOCIADO, ");
			sql.append("REG.CODNOM AS REGIONAL, ZON.CODNOM AS ZONA, AGC.NOMAGC AS OFICINA ");
			sql.append("FROM ELECDB.ELE_REPORTE_HABIL RH ");
			sql.append("INNER JOIN ELECDB.CLIMAE CLI ON RH.NUMERO_DOCUMENTO = CLI.NITCLI AND RH.TIPO_VALIDACION = 2 ");
			sql.append("INNER JOIN SEGURIDAD.PLTAGCORI AGC ON AGC.AGCORI = CLI.AGCVIN  AND AGC.CODEMP = 67890 ");
			sql.append(
					"LEFT JOIN (SELECT CODTAB, CODINT, CODNOM FROM MULCLIDAT.CLITAB WHERE CODTAB = 907) REG ON AGC.CODREG = REG.CODINT ");
			sql.append(
					"LEFT JOIN (SELECT CODTAB, CODINT, CODNOM FROM MULCLIDAT.CLITAB WHERE CODTAB = 908) ZON ON AGC.CODZON = ZON.CODINT ");
			sql.append("ORDER BY 3, 1, 2 ASC");

			ResultSet rs = null;
			Connection con = HibernateSessionFactoryElecciones2012.getSession().connection();
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int j = 1;
			while (startRow <= total) {

				st.setFetchSize(numRegistros);
				rs = st.executeQuery(sql.toString());

				int cont = 1;

				if (rs.absolute(startRow)) {
					do {
						dto = new EleAsociadoDatosDTO();
						dto.setNitcli(rs.getString("NUMERO_DOCUMENTO").trim());
						dto.setNombreCompleto(rs.getString("NOMBRE_ASOCIADO").trim());
						dto.setProfesion(rs.getString("PROFESION_ASOCIADO") == null ? null
								: rs.getString("PROFESION_ASOCIADO").trim());
						dto.setZonaElectoral(
								rs.getString("ZONA_ASOCIADO") == null ? null : rs.getString("ZONA_ASOCIADO").trim());
						dto.setEstadoHabilidad("Inhábil");
						dto.setRegional(rs.getString("REGIONAL") == null ? null : rs.getString("REGIONAL").trim());
						dto.setCiudad(rs.getString("ZONA") == null ? null : rs.getString("ZONA").trim());
						dto.setOficina(rs.getString("OFICINA") == null ? null : rs.getString("OFICINA").trim());
						list.add(dto);
						cont++;
					} while (rs.next() && (cont <= numRegistros));
				}

				startRow += numRegistros;

				int c = startRow;
				c += numRegistros;
				if (c > total) {
					if (startRow == total) {
						numRegistros = 1;
					} else {
						numRegistros = (total - startRow);
					}
				}
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL, "consulta.asociados.inhabiles"));
		} finally {
			session.close();
		}
		return list;
	}

	/**
	 * Metodo que consulta los asociados habiles
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 14/11/2012
	 * @param codProfesion
	 * @param numeroDocumento
	 * @param codZona
	 * @return
	 * @throws Exception
	 */
	public List<EleAsociadoDatosDTO> consultarAsociadosHabilesNativa() throws Exception {
		List<EleAsociadoDatosDTO> list = new ArrayList<EleAsociadoDatosDTO>();

		int startRow = 1;
		int total = consultarTotalAsociadosHabiles(null);
		int numRegistros = ConstantesProperties.NUMERO_REGISTROS_POR_PAGINA_CONSULTAS;

		EleAsociadoDatosDTO dto = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			StringBuffer sql = new StringBuffer(
					"SELECT RH.NUMERO_DOCUMENTO, RH.NOMBRE_ASOCIADO, RH.ZONA_ASOCIADO, RH.PROFESION_ASOCIADO, ");
			sql.append("REG.CODNOM AS REGIONAL, ZON.CODNOM AS ZONA, AGC.NOMAGC AS OFICINA ");
			sql.append("FROM ELECDB.ELE_REPORTE_HABIL RH ");
			sql.append("INNER JOIN ELECDB.CLIMAE CLI ON RH.NUMERO_DOCUMENTO = CLI.NITCLI AND RH.TIPO_VALIDACION = 1 ");
			sql.append("INNER JOIN SEGURIDAD.PLTAGCORI AGC ON AGC.AGCORI = CLI.AGCVIN  AND AGC.CODEMP = 67890 ");
			sql.append(
					"LEFT JOIN (SELECT CODTAB, CODINT, CODNOM FROM MULCLIDAT.CLITAB WHERE CODTAB = 907) REG ON AGC.CODREG = REG.CODINT ");
			sql.append(
					"LEFT JOIN (SELECT CODTAB, CODINT, CODNOM FROM MULCLIDAT.CLITAB WHERE CODTAB = 908) ZON ON AGC.CODZON = ZON.CODINT ");
			sql.append("ORDER BY 3, 1, 2 ASC");

			ResultSet rs = null;
			Connection con = HibernateSessionFactoryElecciones2012.getSession().connection();
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int j = 1;
			while (startRow <= total) {
				st.setFetchSize(numRegistros);
				rs = st.executeQuery(sql.toString());

				int cont = 1;

				if (rs.absolute(startRow)) {
					do {
						dto = new EleAsociadoDatosDTO();
						dto.setNitcli(rs.getString("NUMERO_DOCUMENTO").trim());
						dto.setNombreCompleto(rs.getString("NOMBRE_ASOCIADO").trim());
						dto.setProfesion(rs.getString("PROFESION_ASOCIADO") == null ? null
								: rs.getString("PROFESION_ASOCIADO").trim());
						dto.setZonaElectoral(
								rs.getString("ZONA_ASOCIADO") == null ? null : rs.getString("ZONA_ASOCIADO").trim());
						dto.setEstadoHabilidad("Hábil");
						dto.setRegional(rs.getString("REGIONAL") == null ? null : rs.getString("REGIONAL").trim());
						dto.setCiudad(rs.getString("ZONA") == null ? null : rs.getString("ZONA").trim());
						dto.setOficina(rs.getString("OFICINA") == null ? null : rs.getString("OFICINA").trim());
						list.add(dto);
						cont++;
					} while (rs.next() && (cont <= numRegistros));
				}

				startRow += numRegistros;

				int c = startRow;
				c += numRegistros;
				if (c > total) {
					if (startRow == total) {
						numRegistros = 1;
					} else {
						numRegistros = (total - startRow);
					}
				}
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL, "consulta.asociados.habiles"));
		} finally {
			session.close();
		}
		return list;
	}

	/**
	 * Metodo que consulta el total de asociados
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 22/11/2012
	 * @param codZona
	 * @return
	 * @throws Exception
	 */
	public Double totalAsociados() throws Exception {

		Double total = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();

		try {
			Query query = session.getNamedQuery("consulta.total.asociados");
			total = (Double) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
					"consulta.total.asociados.por.zona"));
		} finally {
			session.close();
		}

		return total;
	}

	/**
	 * Metodo que consulta el total de asociados filtrados por zona.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 22/11/2012
	 * @param codZona
	 * @return
	 * @throws Exception
	 */
	public Double totalAsociadosPorZona(Long codZona) throws Exception {

		Double total = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();

		try {
			Query query = session.getNamedQuery("consulta.total.asociados.por.zona");
			query.setLong("codZona", codZona);
			total = (Double) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
					"consulta.total.asociados.por.zona"));
		} finally {
			session.close();
		}

		return total;
	}

	/**
	 * Metodo que consulta el total de asociados habiles por zona.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 22/11/2012
	 * @param codZona
	 * @return
	 * @throws Exception
	 */
	public Double totalAsociadosHabilesPorZona(Long codZona) throws Exception {

		Double total = null;
		String fechaProceso = logicaProceso.consultaFechaUltimoProcesoEjecutado();
		Session session = HibernateSessionFactoryElecciones2012.getSession();

		try {
			StringBuffer sql = new StringBuffer("SELECT COUNT(A.NUMERO_DOCUMENTO) TOTAL_ASOCIADOS ");
			sql.append("FROM ELECDB.ELE_ASOCIADO A INNER JOIN ELECDB.ELE_ZONA Z ON Z.CODIGO_ZONA = A.COD_ZONA_ASO ");
			sql.append("WHERE Z.CODIGO_ZONA_ELE = " + codZona + " ");
			sql.append("AND NOT EXISTS (SELECT 1 FROM ELECDB.ELE_INHABILIDAD I ");
			sql.append("INNER JOIN ELECDB.ELE_PROCESO_REGLA PR ON I.CONSECUTIVO_PRO_REGLA = PR.CONSECUTIVO_PRO_REGLA ");
			sql.append("INNER JOIN ELECDB.ELE_PROCESO P ON PR.CODIGO_PROCESO = P.CODIGO_PROCESO ");
			sql.append(
					"WHERE P.FECHA_PROGRAMACION = '" + fechaProceso + "' AND A.CODIGO_ASOCIADO = I.CODIGO_ASOCIADO)");

			SQLQuery query = session.createSQLQuery(sql.toString());
			query.addScalar("TOTAL_ASOCIADOS", Hibernate.DOUBLE);

			total = (Double) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
			throw new Exception(loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
					"consulta.total.asociados.habiles.por.zona"));
		} finally {
			session.close();
		}

		return total;
	}

	/**
	 * Metodo que consulta el total de asociados inhabiles por zona.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 22/11/2012
	 * @param codZona
	 * @return
	 * @throws Exception
	 */
	public Double totalAsociadosInhabilesPorZona(Long codZona) throws Exception {

		Double total = null;
		String fechaProceso = logicaProceso.consultaFechaUltimoProcesoEjecutado();
		Session session = HibernateSessionFactoryElecciones2012.getSession();

		try {
			StringBuffer sql = new StringBuffer("SELECT COUNT(DISTINCT A.NUMERO_DOCUMENTO) TOTAL_ASOCIADOS ");
			sql.append("FROM ELECDB.ELE_ASOCIADO A ");
			sql.append("INNER JOIN ELECDB.ELE_INHABILIDAD I ON I.CODIGO_ASOCIADO = A.CODIGO_ASOCIADO ");
			sql.append("INNER JOIN ELECDB.ELE_PROCESO_REGLA PR ON I.CONSECUTIVO_PRO_REGLA = PR.CONSECUTIVO_PRO_REGLA ");
			sql.append("INNER JOIN ELECDB.ELE_PROCESO P ON PR.CODIGO_PROCESO = P.CODIGO_PROCESO ");
			sql.append("INNER JOIN ELECDB.ELE_ZONA Z ON Z.CODIGO_ZONA = A.COD_ZONA_ASO ");
			sql.append("WHERE P.FECHA_PROGRAMACION = '" + fechaProceso + "'");
			sql.append("AND Z.CODIGO_ZONA_ELE = " + codZona + "");

			SQLQuery query = session.createSQLQuery(sql.toString());
			query.addScalar("TOTAL_ASOCIADOS", Hibernate.DOUBLE);

			total = (Double) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
			throw new Exception(loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
					"consulta.total.asociados.inhabiles.por.zona"));
		} finally {
			session.close();
		}

		return total;
	}

	/**
	 * Consulta si un asociado se encuentra activo en la tabla ele_asociado
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 22/11/2012
	 * @param id
	 * @return
	 */
	public boolean estaAsociaActivo(Long id) {

		EleAsociadoDAO eleAsociadoDAO = new EleAsociadoDAO();
		try {
			List<EleAsociado> asociado = eleAsociadoDAO.findByNumeroDocumento(id);
			return asociado != null && !asociado.isEmpty() ? Boolean.TRUE : Boolean.FALSE;
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
			eleAsociadoDAO = null;
		}
	}

	/**
	 * Valida el estado del asociado para mostrar el mensaje adecuado.
	 * 
	 * @author Juan Diego Montoya
	 * @date 09/09/2016
	 * @param id
	 * @return
	 */
	public Double consultarEstadoAsociado(Long id) throws Exception {

		Double estadoAsociado = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();

		try {
			Query query = session.getNamedQuery("consulta.estado.asociado");
			query.setLong("id", id);
			estadoAsociado = (Double) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL, "consulta.estado.asociado.por.id"));
		} finally {
			session.close();
		}

		return estadoAsociado;
	}

	/**
	 * Valida que un asociado este vinculado a una zona donde habran elecciones. id
	 * = numero documento identidad True = Si pertenece a una zona donde habran
	 * elecciones.
	 */
	public boolean asociadoPerteneceZonaHayEleccion(Long id) {
		boolean resul = false;
		Long valor;
		Session session = HibernateSessionFactoryElecciones2012.getSession();

		SQLQuery query = null;
		try {

			query = (SQLQuery) session.getNamedQuery(ConstantesNamedQueries.QUERY_VALIDACION_ZONA_HAY_VOTACION);
			query.setLong("numIdAsociado", id);

			Object resultado = query.uniqueResult();

			if (resultado != null) {
				valor = (Long) resultado;
				if (valor == 1) {
					resul = true;
				}
			}

		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
			query = null;
		}
		return resul;
	}

	/**
	 * Consulta que verifica si hay un asociado registrado en el climae con la
	 * cédula o id pasado como parámetro
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 22/11/2012
	 * @param id
	 * @return
	 */
	public boolean esAsociado(Long id) {

		Session session = HibernateSessionFactoryClimae.getSession();
		SQLQuery query = null;
		try {
			query = (SQLQuery) session.getNamedQuery(ConstantesNamedQueries.QUERY_CONSULTA_SI_ES_ASOCIADO);
			query.setLong("nitcli", id);

			Object resultado = query.uniqueResult();
			return resultado != null ? Boolean.TRUE : Boolean.FALSE;
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
			query = null;
		}
	}

	/**
	 * Consulta que verifica si un asociado es persona jurídica
	 * 
	 * @author Carlos Ernesto Ortega Q. Contratista Coomeva
	 * @date 13/01/2014
	 * @param id
	 * @return
	 */
	public boolean esAsociadoPersonaJuridica(Long id) {

		Session session = HibernateSessionFactoryClimae.getSession();
		SQLQuery query = null;
		try {
			query = (SQLQuery) session
					.getNamedQuery(ConstantesNamedQueries.QUERY_CONSULTA_SI_ES_ASOCIADO_PERSONA_JURIDICA);
			query.setLong("nitcli", id);

			Object resultado = query.uniqueResult();
			return resultado != null ? Boolean.TRUE : Boolean.FALSE;
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
			query = null;
		}
	}

	public String consultarNombreAsociadoBuc(Long numintAsociado) throws Exception {

		List qList = null;
		Session session = HibernateSessionFactoryClimae.getSession();
		StringBuffer nombresApellidosAso = null;
		try {
			Query query = session.getNamedQuery(ConstantesNamedQueries.QUERY_CONSULTAR_NOMBRE_COMPLETO_ASOCIADO_BUC);
			query.setLong("nitcli", numintAsociado);
			qList = query.list();

			String nombreCompletoAsociado = null;
			nombresApellidosAso = new StringBuffer();

			for (Iterator iter = qList.iterator(); iter.hasNext();) {
				Object[] element = (Object[]) iter.next();
				nombreCompletoAsociado = (String) element[3];

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
			}

		} catch (Exception e) {
			// System.out.println("No se logro consultar el asociado: "
			// + e.toString());
			throw new Exception("No se logro consultar el asociado: " + e.getMessage(), e);
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
		}
		return nombresApellidosAso.toString();
	}

	public String[] consultarDatosAsociadoZonaBuc(Long numintAsociado) throws Exception {

		String[] Datos = new String[5];
		List qList = null;
		Session session = HibernateSessionFactoryClimae.getSession();
		StringBuffer nombresApellidosAso = null;

		try {
			Query query = session.getNamedQuery(ConstantesNamedQueries.QUERY_CONSULTAR_NOMBRE_ASOCIADO_ZONA_BUC);
			query.setLong("nitcli", numintAsociado);
			qList = query.list();

			String nombreCompletoAsociado = null;
			nombresApellidosAso = new StringBuffer();
			for (Iterator iter = qList.iterator(); iter.hasNext();) {
				Object[] element = (Object[]) iter.next();
				nombreCompletoAsociado = (String) element[3];

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

				Datos[0] = element[0].toString();// numint
				Datos[1] = element[1].toString();// nitcli
				Datos[2] = nombresApellidosAso.toString(); // nombre
				Datos[3] = (String) element[4];// descr zona
				Datos[4] = (String) element[5];// descr regional
			}

		} catch (Exception e) {
			// System.out.println("No se logro consultar el asociado: "
			// + e.toString());
			throw new Exception("No se logro consultar el asociado: " + e.getMessage(), e);
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
		}

		return Datos;
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

	public Long consultarCodigoAsociadoPorNumeroDocumento(Long numeroDocumento) throws Exception {
		Long codigoAsociado = null;
		try {
			Session session = HibernateSessionFactoryElecciones2012.getSession();
			Query query = session.getNamedQuery("consultar.codigo.asociado.por.documento");
			query.setLong("numeroDocumento", numeroDocumento);
			codigoAsociado = (Long) query.uniqueResult();
		} catch (Exception e) {
			throw new Exception(loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
					"consulta.codigo.asociado.por.numero.documento"));
		}
		return codigoAsociado;
	}

	public EleAsociado consultarAsociadoPorCodigo(Long id) throws Exception {
		EleAsociado asociado = null;
		try {
			asociado = dao.findById(id);
		} catch (Exception e) {
			throw e;
		}
		return asociado;
	}

	/**
	 * Metodo que consulta el codigo del cabeza de plancha filtrado por un asociado
	 * inscrito a esta.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 6/12/2012
	 * @param numeroDocumentoInscrito
	 * @return
	 * @throws Exception
	 */
	public Long[] consultaCabezaPlanchaPorInscrito(Long numeroDocumentoInscrito) throws Exception {
		Long[] resultado = new Long[3];
		List<Object[]> element = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			Query query = session.getNamedQuery("consultar.cabeza.plancha.por.inscrito");
			query.setLong("numeroDocumento", numeroDocumentoInscrito);
			element = query.list();
			if (element != null && element.size() > 0) {
				resultado[0] = (Long) element.get(0)[0];
				resultado[1] = (Long) element.get(0)[1];
				resultado[2] = (Long) element.get(0)[2];
			} else {
				throw new Exception("El asociado no esta inscrito a ninguna plancha.");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
		return resultado;
	}

	/**
	 * Metodo que consulta la cantidad de impresiones de formatos de una plancha
	 * filtrado por el consecutivo de la plancha.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 6/12/2012
	 * @param consecutivoPlancha
	 * @return
	 * @throws Exception
	 */
	public Long consultaNumeroImpresionesFormatoPLancha(Long consecutivoPlancha) throws Exception {
		Long resultado = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			Query query = session.getNamedQuery("consultar.numero.impresiones.formato.plancha");
			query.setLong("consecutivoPlancha", consecutivoPlancha);
			query.setLong("codigoFormato", CODIGO_FORMATO_INSCRIPCION_PLANCHA);
			resultado = (Long) query.uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
		return resultado;
	}

	public EleAsociadoDatosDTO consultarInformacionBasicaAsociado(Long numintAsociado) throws Exception {
		List qList = null;
		Session session = HibernateSessionFactoryClimae.getSession();
		try {
			Query query = session.getNamedQuery(ConstantesNamedQueries.QUERY_CONSULTAR_INFO_ASOCIADO);
			query.setLong("nitcli", numintAsociado);
			qList = query.list();

			if (qList != null && !qList.isEmpty()) {
				Object[] element = (Object[]) qList.get(0);
				EleAsociadoDatosDTO dto = new EleAsociadoDatosDTO();
				dto.setAsonumint((String) element[0]);
				dto.setNitcli((String) element[1]);
				dto.setNombreCompleto((String) element[2]);
				dto.setLugarExpedicion((String) element[4]);
				dto.setFechaExpedicion((String) element[5]);
				dto.setCiudadReside(element[6] != null ? (String) element[6] : null);
				dto.setCodZonaAdministrativaAsociado(element[7] != null ? (String) element[7] : null);
				dto.setDescripZonaAdministrativaAsociado(element[8] != null ? (String) element[8] : null);
				return dto;
			}
		} catch (Exception e) {
			System.out.println("No se logró consultar el asociado: " + e.toString());
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
		}
		return null;
	}

	public static void main(String[] args) {

		try {
			DTOHabilidadAsociado dto = LogicaAsociado.getInstance().consultarHabilidadAsociado(84036648L);
			System.out.println(dto.getAsociadoHabil());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}