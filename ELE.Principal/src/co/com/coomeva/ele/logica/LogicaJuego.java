package co.com.coomeva.ele.logica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.juego.EleParticipanteJuego;
import co.com.coomeva.ele.entidades.juego.dao.EleParticipanteJuegoDAO;
import co.com.coomeva.ele.modelo.EleAsociadoDatosDTO;
import co.com.coomeva.ele.modelo.EleBoletasAsignadasJuegoDTO;
import co.com.coomeva.ele.modelo.EleParticipanteJuegoDTO;
import co.com.coomeva.ele.modelo.EleVotanteDatosDTO;
import co.com.coomeva.ele.modelo.EleZonaElectoralDTO;
import co.com.coomeva.ele.modelo.PlanchaPorEstadoDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;
import co.com.coomeva.ele.utilidades.GeneradorConsecutivos;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.resources.LoaderResourceElements;

public class LogicaJuego extends EleParticipanteJuegoDAO {

	private static LogicaJuego instance;
	private LoaderResourceElements loaderResourceElements = LoaderResourceElements
			.getInstance();

	/**
	 * Patrón Singleton
	 * 
	 * @author Carlos Ernesto Ortega Q. Contratista Coomeva
	 * 
	 */
	public static LogicaJuego getInstance() {
		if (instance == null) {
			instance = new LogicaJuego();
		}
		return instance;
	}

	private Session getSession() {
		return HibernateSessionFactoryElecciones2012.getSession();
	}

	/**
	 * Método que verifica el juego de un participante y de acuerdo a las reglas
	 * del negocio retorna un número como respuesta
	 * 
	 * @author Carlos Ernesto Ortega Q. Contratista Coomeva
	 * @param numeroDocumento
	 * @param fase
	 * @return String
	 * @throws Exception
	 */

	public String verificarJuego(Long numeroDocumento, int fase)
			throws Exception {
		if (numeroDocumento == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes",
					"noNumIdentificacion"));
		}
		if (!this.verificarFase(fase)) {
			throw new Exception(
					"El número de fase no es válido, solo 1,2,3 o 4");
		}

		// Se valida si es asociado:
		if (DelegadoAsociado.getInstance().esAsociado(numeroDocumento)) {

			// Se valida si el asociado está activo:
			if (DelegadoAsociado.getInstance()
					.estaAsociaActivo(numeroDocumento)) {

				// Se verifica si la zona del jugador está habilitada para
				// votar:
				if (this.verificarZonaHabilitada(numeroDocumento)) {
					Session session = getSession();
					// Consulta para verificar si el jugador ya ha jugado en
					// la
					// fase:
					try {
						String queryString = "FROM EleParticipanteJuego WHERE numeroDocumento = :doc AND faseJuego = :fase";
						Query queryObject = session.createQuery(queryString);
						queryObject.setParameter("doc", numeroDocumento);
						queryObject.setParameter("fase", fase);

						List<EleParticipanteJuego> list = queryObject.list();
						if (null != list && list.size() > 0) {
							return ConstantesProperties.PAR_MENSAJE_JUGADOR_YA_JUGO_EN_LA_FASE;
						} else {
							return ConstantesProperties.PAR_MENSAJE_JUGADOR_HABILITADO_PARA_SEGUIR_JUGANDO;
						}
					} finally {
						session.close();
					}
				} else
					return ConstantesProperties.PAR_MENSAJE_JUGADOR_NO_PERTENECE_A_ZONA_HABILITADA;
			} else {
				// Si el jugador es un asociado inactivo
				return ConstantesProperties.PAR_MENSAJE_JUGADOR_NO_ACTIVO_COMO_ASOCIADO;
			}
		} else {
			// Si el jugador no es asociado
			return ConstantesProperties.PAR_MENSAJE_JUGADOR_NO_FIGURA_COMO_ASOCIADO;
		}
	}

	public void registrarJuego(Long numeroDocumento, int fase, String zona,
			int boletas) throws Exception {

		if (numeroDocumento == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes",
					"noNumIdentificacion"));
		}
		if (!this.verificarFase(fase)) {
			throw new Exception(
					"El número de fase no es válido, solo 1,2,3 o 4");
		}
		if (zona.isEmpty() || zona == null) {
			throw new Exception("No hay código de zona");
		}

		java.util.Date utilDate = new java.util.Date(); // fecha actual

		long lnMilisegundos = utilDate.getTime();
		java.sql.Timestamp horaTrans = new java.sql.Timestamp(lnMilisegundos);// tiempo
		// actual
		EleParticipanteJuego instancia = new EleParticipanteJuego();
		instancia.setZonaJuego(zona);
		instancia.setFaseJuego(fase);
		instancia.setNumeroDocumento(numeroDocumento);
		instancia.setHoraTrans(horaTrans);
		instancia.setConsecutivo(consecutivo());
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			this.save(instancia);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	private long consecutivo() {
		long consecutivo = GeneradorConsecutivos.getInstance().getConsecutivo(
				ConstantesNamedQueries.QUERY_SEQ_ELE_PARTICIPANTE_JUEGO,
				HibernateSessionFactoryElecciones2012.getSession());
		return consecutivo;
	}

	private boolean verificarFase(int fase) {
		if (fase == 1 || fase == 2 || fase == 3 || fase == 4)
			return true;
		else
			return false;
	}

	private boolean verificarZonaHabilitada(Long numeroDocumento)
			throws Exception {
		List<Object[]> objectList = null;
		Session session = getSession();
		try {
			Query queryObject = session
					.getNamedQuery(ConstantesNamedQueries.QUERY_CONSULTAR_HABILIDAD_ASOCIADO_ZONA_JUEGO);
			queryObject.setLong("numeroDocumento", numeroDocumento);
			objectList = queryObject.list();
			// Si hay datos entonces está habilitado en la zona:
			if (objectList != null && !objectList.isEmpty()) {
				return true;
			}
		} catch (Exception e) {
			System.out
					.println("No se logró consultar la habilidad del participante para jugar"
							+ e.toString());
			throw new Exception(e.getMessage());
		} finally {
			session.close();
		}
		return false;
	}

	public EleBoletasAsignadasJuegoDTO obtenerBoletasAsignadas(
			Long numeroDocumento) throws Exception {
		EleBoletasAsignadasJuegoDTO dto = null;
		String[] datosAsociado = DelegadoAsociado.getInstance()
				.consultarDatosAsociadoZonaBuc(numeroDocumento);
		
		if(datosAsociado == null || datosAsociado.length <= 0){
			return null; 
		}
		//datos basicos
		dto = new EleBoletasAsignadasJuegoDTO();
		dto.setNombres(datosAsociado[2]);
		dto.setNumeroDocumento(Long.valueOf(datosAsociado[1]));
		dto.setZona(datosAsociado[3]);
		dto.setRegional(datosAsociado[4]);
		dto.setBoletasAsignadasFase1(0);
		dto.setBoletasAsignadasFase2(0);
		dto.setBoletasAsignadasFase3(0);
		dto.setBoletasAsignadasFase4(0);
		
		List<Object[]> objectList = null;
		
		Session session = getSession();
		try {
			Query queryObject = session
					.getNamedQuery(ConstantesNamedQueries.QUERY_CONSULTAR_BOLETAS_ASIGNADAS_JUEGO);
			queryObject.setLong("numeroDocumento", numeroDocumento);
			objectList = queryObject.list();
			int fase = 0;
			String zona;
			String regional;
			int boletas = 0;

			if (objectList != null && !objectList.isEmpty()) {
				for (int i = 0; i < objectList.size(); i++) {
					Object[] element = (Object[]) objectList.get(i);
					boletas = (Integer) element[4];

					fase = (Integer) element[1];
					if (fase == 1)
						dto.setBoletasAsignadasFase1(boletas);
					if (fase == 2)
						dto.setBoletasAsignadasFase2(boletas);
					if (fase == 3)
						dto.setBoletasAsignadasFase3(boletas);
				}
			}
		} catch (Exception e) {
			System.out.println("No se logró consultar las boletas asignadas"
					+ e.toString());
			throw new Exception(e.getMessage());
		} finally {
			session.close();
		}

		boolean existeVotacion = verificarVotacion(numeroDocumento);
		// Si ya registra votación entonces se asignan 4 boletas
		if (existeVotacion) {
			dto.setBoletasAsignadasFase4(4);
		}
		return dto;
	}

	private boolean verificarVotacion(Long documentoVotante) throws Exception {
		List<Object[]> objectList = null;
		Session session = getSession();
		EleVotanteDatosDTO dto = null;
		try {
			Query queryObject = session
					.getNamedQuery(ConstantesNamedQueries.QUERY_CONSULTAR_VOTANTE_PRIMERA_VEZ);
			queryObject.setLong("numeroDocumento", documentoVotante);
			objectList = queryObject.list();
			if (objectList != null && !objectList.isEmpty()) {
				return true;
			}

		} catch (Exception e) {
			System.out.println("No se logró verificar la votación"
					+ e.toString());
			throw new Exception(e.getMessage());
		} finally {
			session.close();
		}
		return false;
	}

	public List<EleParticipanteJuegoDTO> consultarJuegos(String zona,
			String fase) throws Exception {

		List<EleParticipanteJuegoDTO> list = new ArrayList<EleParticipanteJuegoDTO>();

		int startRow = 1;
		int total = consultarTotalJuegos().intValue();
		int numRegistros = ConstantesProperties.NUMERO_REGISTROS_POR_PAGINA_CONSULTAS;

		EleParticipanteJuegoDTO dto = null;
		Session session = getSession();
		try {
			//REGIONALES
			StringBuffer sql2 = new StringBuffer("INNER JOIN( ");
			sql2.append("SELECT DISTINCT AG.CODREG,  CONCAT(AG.CODREG,CONCAT('-',CT.CODNOM)) as des_regional, ZE.CODIGO_ZONA_ELE ");
			sql2.append("FROM SEGURIDAD.PLTAGCORI AG,  MULCLIDAT.CLITAB RG,  MULCLIDAT.CLITAB ZN, ELECDB.ELE_ZONA_ELECTORAL ZE, ELECDB.ELE_ZONA ZI,MULCLIDAT.CLITAB CT ");
			sql2.append("WHERE ZN.CODTAB = 908 AND RG.CODTAB = 907 AND ZN.CODINT = AG.CODZON AND ZI.CODIGO_ZONA = AG.CODZON AND ZE.CODIGO_ZONA_ELE = ZI.CODIGO_ZONA_ELE ");
			sql2.append("AND CT.CODTAB = 608 AND CT.CODINT <> 0 AND CT.CODINT =AG.CODREG ");
			sql2.append(")regional ON regional.CODIGO_ZONA_ELE  = ZE.CODIGO_ZONA_ELE ");

			
			StringBuffer sql = new StringBuffer(
					"SELECT J.NUMERO_DOCUMENTO, C.NOMCLI AS NOMBRE, FASE_JUEGO,ZONA_JUEGO COD_ZONA, regional.des_regional, CONCAT(ZONA_JUEGO,CONCAT('-',ZE.DESCRIPCION_ZONA_ELE)) AS ZONA_JUEGO,HORA_TRANS ");
			sql.append("FROM ELECDB.ELE_PARTICIPANTE_JUEGO J ");
			sql.append("INNER JOIN mulclidat.CLIMAE C ON C.nitcli = J.NUMERO_DOCUMENTO ");
			sql.append("INNER JOIN ELECDB.ELE_ZONA_ELECTORAL ZE ON J.ZONA_JUEGO=ZE.CODIGO_ZONA_ELE ");
			sql.append(sql2);
			//DE VOTANTES			
			sql.append("UNION ALL ");
			sql.append("SELECT V.DOCUMENTO_VOTANTE,C.NOMCLI AS NOMBRE, 4 FASE_JUEGO,ZONA_USUARIO_REGISTRO COD_ZONA,regional.des_regional, CONCAT(ZONA_USUARIO_REGISTRO,CONCAT('-',ZE.DESCRIPCION_ZONA_ELE)) AS ZONA_JUEGO,FECHA_REGISTRO ");
			sql.append("FROM ELECDB.ELE_VOTANTE V ");
			sql.append("INNER JOIN mulclidat.CLIMAE C ON C.nitcli = V.DOCUMENTO_VOTANTE ");
			sql.append("INNER JOIN ELECDB.ELE_ZONA_ELECTORAL ZE ON V.ZONA_USUARIO_REGISTRO=ZE.CODIGO_ZONA_ELE ");
			sql.append(sql2);
			//sql.append("ORDER BY J.NUMERO_DOCUMENTO,J.FASE_JUEGO,J.ZONA_JUEGO");

			ResultSet rs = null;
			Connection con = session.connection();
			Statement st = con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			int j = 1;
			while (startRow <= total) {

				st.setFetchSize(numRegistros);
				rs = st.executeQuery(sql.toString());

				int cont = 1;
				String faseC;
				String zonaC;
				if (rs.absolute(startRow)) {
					do {
						faseC = rs.getString("FASE_JUEGO").trim();
						zonaC = rs.getString("COD_ZONA").trim();

						// Si hay que filtrar por zona
						if (!zona.equals("-1") && fase.equals("-1")) {
							if (!zonaC.equals(zona.trim()))
								continue;
						}
						// Si hay que filtrar por fase
						if (!fase.equals("-1") && zona.equals("-1")) {
							if (!faseC.equals(fase.trim()))
								continue;

						}
						// Si hay que filtrar por fase y zona
						if (!fase.equals("-1") && !zona.equals("-1")) {
							if (!zonaC.equals(zona) || !faseC.equals(fase))
								continue;
						}

						dto = new EleParticipanteJuegoDTO();
						dto.setNumeroDocumento(rs.getLong("NUMERO_DOCUMENTO"));
						dto.setFaseJuego(rs.getInt("FASE_JUEGO"));
						dto.setZonaJuego(rs.getString("ZONA_JUEGO"));
						dto.setHoraTrans(rs.getTimestamp("HORA_TRANS"));
						dto.setDesRegional(rs.getString("DES_REGIONAL"));
					    dto.setNombreAsociado(rs.getString("NOMBRE"));
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
			throw new Exception(
					loaderResourceElements
							.getKeyResourceValue(
									ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
									"consulta.total.juegos"));
		} finally {
			session.close();
		}
		return list;
	}

	public Long consultarTotalJuegos() throws Exception {
		Long total = 0L;
		List<Object[]> objectList = null;
		Session session = getSession();
		try {
			Query queryObject = session
					.getNamedQuery(ConstantesNamedQueries.QUERY_CONSULTAR_TOTAL_JUEGOS);
			objectList = queryObject.list();

			for (int i = 0; i < objectList.size(); i++) {
				Object[] elements = (Object[]) objectList.get(i);
				if (elements != null && elements.length > 0) {
					total += (Long) elements[0];
				}
			}
		} catch (Exception e) {
			System.out
					.println("No se logró consultar total de juegos"
							+ e.toString());
			throw new Exception(e.getMessage());
		} finally {
			session.close();
		}
		return total;
	}

	public String consultarZonaAsociado(Long numeroDocumento) throws Exception {
		List<Object[]> objectList = null;
		Session session = getSession();
		String zona = "";
		try {
			Query queryObject = session
					.getNamedQuery(ConstantesNamedQueries.QUERY_CONSULTAR_HABILIDAD_ASOCIADO_ZONA_JUEGO);
			queryObject.setLong("numeroDocumento", numeroDocumento);
			objectList = queryObject.list();

			for (int i = 0; i < objectList.size(); i++) {
				Object[] elements = (Object[]) objectList.get(i);
				if (elements != null && elements.length > 0) {
					zona = (String) elements[0];
				}
			}
		} catch (Exception e) {
			System.out
					.println("No se logró consultar la zona del participante para jugar"
							+ e.toString());
			throw new Exception(e.getMessage());
		} finally {
			session.close();
		}
		return zona;
	}

	public List<EleZonaElectoralDTO> consultarZonasElectorales()
			throws Exception {
		List<EleZonaElectoralDTO> list = new ArrayList<EleZonaElectoralDTO>();

		int startRow = 1;
		int total = consultarTotalZonas();
		int numRegistros = ConstantesProperties.NUMERO_REGISTROS_POR_PAGINA_CONSULTAS;

		EleZonaElectoralDTO dto = null;
		Session session = getSession();
		try {
			StringBuffer sql = new StringBuffer(
					"SELECT ZE.CODIGO_ZONA_ELE codZona,ZE.DESCRIPCION_ZONA_ELE desZona FROM ELECDB.ELE_ZONA_ELECTORAL ZE  ");
			sql.append("ORDER BY ZE.DESCRIPCION_ZONA_ELE ");

			ResultSet rs = null;
			Connection con = session.connection();
			Statement st = con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			int j = 1;
			while (startRow <= total) {

				st.setFetchSize(numRegistros);
				rs = st.executeQuery(sql.toString());

				int cont = 1;

				if (rs.absolute(startRow)) {
					do {
						dto = new EleZonaElectoralDTO();
						dto.setCodigo(rs.getString("codZona"));
						dto.setDescripcion(rs.getString("desZona"));
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
			throw new Exception("Error consultando las zonas electorales");
		} finally {
			session.close();
		}
		return list;
	}
	public int consultarTotalZonas() throws Exception {
		int total = 0;
		Session session = getSession();
		try {
			StringBuffer sql = new StringBuffer(
					"SELECT COUNT(CODIGO_ZONA_ELE) TOTAL FROM ELECDB.ELE_ZONA_ELECTORAL ");

			SQLQuery query = session.createSQLQuery(sql.toString());
			query.addScalar("TOTAL", Hibernate.LONG);

			Long valor = (Long) query.uniqueResult();
			total = valor.intValue();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error consultando el total zonas electorales");
		} finally {
			session.close();
		}
		return total;
	}
}