package co.com.coomeva.ele.logica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.internet.InternetAddress;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import co.com.coomeva.ele.entidades.habilidad.EleAsociado;
import co.com.coomeva.ele.entidades.habilidad.EleNovedad;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.habilidad.dao.EleNovedadDAO;
import co.com.coomeva.ele.modelo.EleNovedadDTO;
import co.com.coomeva.ele.modelo.FiltrosConsultasDTO;
import co.com.coomeva.ele.modelo.ResumenNovedadesDTO;
import co.com.coomeva.ele.modelo.ResumenZonasNovedadesDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.SendMail2;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;
import co.com.coomeva.ele.utilidades.GeneradorConsecutivos;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.ManipulacionFechas;
import co.com.coomeva.util.resources.LoaderResourceElements;

public class LogicaNovedad extends EleNovedadDAO {

	private static LogicaNovedad instance;
	private LogicaFiltros logicaFiltros = LogicaFiltros.getInstance();
	private LogicaProceso logicaProceso = LogicaProceso.getInstance();
	private LogicaAsociado logicaAsociado = LogicaAsociado.getInstance();
	private LoaderResourceElements loaderResourceElements = LoaderResourceElements
			.getInstance();

	private LogicaNovedad() {
	}

	public static LogicaNovedad getInstance() {
		if (instance == null) {
			instance = new LogicaNovedad();

		}
		return instance;
	}

	/**
	 * Metodo que consulta el total de novedades aplicadas, filtradas por el
	 * tipo de novedad, fecha del porceso o zona electoral del asociado.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a>
	 *         - Pragma S.A. <br>
	 * @date 19/11/2012
	 * @param tipoNovedad
	 * @param fechaProceso
	 * @param zonaElectoral
	 * @return
	 * @throws Exception
	 */
	public int consultaTotalNovedaesAplicadas(Long tipoNovedad,
			String fechaProceso, Long zonaElectoral) throws Exception {
		int total = 0;
		Session session = HibernateSessionFactoryElecciones2012.getSession();

		try {

			StringBuffer sql = new StringBuffer(
					"SELECT COUNT(A.NUMERO_DOCUMENTO) TOTAL_NOVEDADES ");
			sql
					.append("FROM ELECDB.ELE_ASOCIADO A INNER JOIN ELECDB.ELE_NOVEDAD N ON N.CODIGO_ASOCIADO = A.CODIGO_ASOCIADO ");

			if (tipoNovedad != null) {
				sql.append("WHERE N.ESTADO_HABILIDAD = " + tipoNovedad + " ");
			}
			if (fechaProceso != null) {
				sql.append("AND N.FECHA_REGISTRO <= '" + fechaProceso + "' ");
			}
			if (zonaElectoral != null) {
				sql.append("AND A.COD_ZONA_ASO = " + zonaElectoral + " ");
			}

			SQLQuery query = session.createSQLQuery(sql.toString());
			query.addScalar("TOTAL_NOVEDADES", Hibernate.LONG);

			Long valor = (Long) query.uniqueResult();
			total = valor.intValue();

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("Error consultando el total de asociados habiles: "
							+ e.getMessage());
			throw new Exception(
					loaderResourceElements
							.getKeyResourceValue(
									ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
									"consulta.total.novedades.aplicadas"));
		} finally {
			session.close();
		}

		return total;
	}

	/**
	 * Metodo que consulta las novedades aplicadas, filtradas por el tipo de
	 * novedad, fecha del porceso o zona electoral del asociado.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a>
	 *         - Pragma S.A. <br>
	 * @date 19/11/2012
	 * @param tipoNovedad
	 * @param fechaProceso
	 * @param zonaElectoral
	 * @return
	 * @throws Exception
	 */
	public List<EleNovedadDTO> consultaNovedadesAplicadas(Long tipoNovedad,
			String fechaProceso, Long zonaElectoral) throws Exception {
		List<EleNovedadDTO> list = new ArrayList<EleNovedadDTO>();

		int startRow = 1;
		int total = consultaTotalNovedaesAplicadas(tipoNovedad, fechaProceso,
				zonaElectoral);
		int numRegistros = ConstantesProperties.NUMERO_REGISTROS_POR_PAGINA_NOVEDADES;

		EleNovedadDTO dto = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();

		try {
			StringBuffer sql = new StringBuffer(
					"SELECT DISTINCT * FROM(SELECT A.NUMERO_DOCUMENTO, C.NOMCLI, N.ESTADO_HABILIDAD, N.FECHA_REGISTRO, A.DESC_ZONA_ASO, ");
			sql
					.append("SUBSTR(C.NOMCL1,(LOCATE('2',C.NOMCL1)+1),(LOCATE('3',C.NOMCL1)-LOCATE('2',C.NOMCL1))-1) AS NOMBRE1 ");
			sql
					.append("FROM ELECDB.ELE_ASOCIADO A INNER JOIN ELECDB.ELE_NOVEDAD N ON N.CODIGO_ASOCIADO = A.CODIGO_ASOCIADO ");
			sql
					.append("INNER JOIN MULCLIDAT.CLIMAE C ON A.CODIGO_ASOCIADO = C.NUMINT ");

			if (tipoNovedad != null) {
				sql.append("WHERE N.ESTADO_HABILIDAD = " + tipoNovedad + " ");
			}
			if (fechaProceso != null) {
				sql.append("AND N.FECHA_REGISTRO <= '" + fechaProceso + "' ");
			}
			if (zonaElectoral != null) {
				sql.append("AND A.COD_ZONA_ASO = " + zonaElectoral + " ");
			}

			sql.append(")AS todo ORDER BY todo.NOMCLI ASC");

			ResultSet rs = null;
			Connection con = HibernateSessionFactoryElecciones2012.getSession()
					.connection();
			Statement st = con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			// convertimos la fecha de corte para presentarla en el reporte
			// formato que viene yyyy-MM-dd

			Date fechaCorte = ManipulacionFechas.stringToDate(fechaProceso,
					ConstantesProperties.FORMATO_FECHA);
			//System.out.println("Fecha proceso " + fechaProceso);
			//System.out.println("Fecha corte " + fechaCorte.toString());
			int j = 1;
			while (startRow <= total) {
				//System.out.println("Consulta novedad número: " + j);

				st.setFetchSize(numRegistros);
				rs = st.executeQuery(sql.toString());

				int cont = 1;

				if (rs.absolute(startRow)) {
					do {
						dto = new EleNovedadDTO();
						dto.setNumeroDocumento(rs.getString("NUMERO_DOCUMENTO")
								.trim());
						dto.setNombreCompletoAsociado(rs.getString("NOMCLI")
								.trim());
						String novedad = rs.getString("ESTADO_HABILIDAD")
								.trim().equals("1") ? "Hábil" : "Inhábil";
						dto.setNovedadAplicada(novedad);
						dto.setFechaAplicacionNovedad(rs
								.getDate("FECHA_REGISTRO"));
						dto.setZona(rs.getString("DESC_ZONA_ASO").trim());
						dto.setFechaCorte(fechaCorte);
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
			System.out.println("Error generando reporte asociados habiles: "
					+ e.getMessage());
			throw new Exception(
					loaderResourceElements
							.getKeyResourceValue(
									ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
									"consulta.novedades.aplicadas"));
		} finally {
			session.close();
		}

		return list;
	}

	/**
	 * Metodo que consulta la informacion para generar el resumen de novedades.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a>
	 *         - Pragma S.A. <br>
	 * @date 26/11/2012
	 * @return
	 */
	public List<ResumenNovedadesDTO> generarInformacionResumenNovedades()
			throws Exception {
		List<ResumenNovedadesDTO> list = new ArrayList<ResumenNovedadesDTO>();
		ResumenNovedadesDTO dtoList = null;
		ResumenZonasNovedadesDTO dto = null;
		String fechaProceso = logicaProceso
				.consultaFechaUltimoProcesoEjecutado();

		Double totalNumero = 0d, totalPorcentaje = 0d, totalAsociados = logicaAsociado
				.totalAsociados();

		try {
			List<FiltrosConsultasDTO> zonas = logicaFiltros
					.consultarZonasElectorales();
			List<ResumenZonasNovedadesDTO> listInforme = new ArrayList<ResumenZonasNovedadesDTO>();

			dtoList = new ResumenNovedadesDTO();
			dtoList.setFechaProceso(fechaProceso);
			dtoList.setTipoNovedad("Habilidad");

			for (FiltrosConsultasDTO l : zonas) {
				dto = new ResumenZonasNovedadesDTO();
				Double numeroNovedades = consultarNumeroNov(fechaProceso, "1",
						l.getCodigoFiltro());
				Double totalAociados = logicaAsociado
						.totalAsociadosHabilesPorZona(l.getCodigoFiltro());
				Double porcentaje = (numeroNovedades / totalAociados) * 100;

				dto.setZona(l.getDescripcionFiltro());
				dto.setNumeroNovedades(numeroNovedades.toString());
				dto.setPorcentajeNovedades(porcentaje.toString());

				listInforme.add(dto);
				totalNumero += numeroNovedades;
			}

			dtoList.setList(listInforme);
			dtoList.setTotalNumeroNov(totalNumero.toString());
			totalPorcentaje = (totalNumero / totalAsociados) * 100;
			dtoList.setTotalPorcentajeNov(totalPorcentaje.toString());

			list.add(dtoList);

			totalNumero = 0d;
			totalPorcentaje = 0d;
			listInforme = new ArrayList<ResumenZonasNovedadesDTO>();
			dtoList = new ResumenNovedadesDTO();
			dtoList.setFechaProceso(fechaProceso);
			dtoList.setTipoNovedad("Inhabilidad");

			for (FiltrosConsultasDTO l : zonas) {
				dto = new ResumenZonasNovedadesDTO();
				Double numeroNovedades = consultarNumeroNov(fechaProceso, "0",
						l.getCodigoFiltro());
				Double totalAociados = logicaAsociado
						.totalAsociadosHabilesPorZona(l.getCodigoFiltro());
				Double porcentaje = (numeroNovedades / totalAociados) * 100;

				dto.setZona(l.getDescripcionFiltro());
				dto.setNumeroNovedades(numeroNovedades.toString());
				dto.setPorcentajeNovedades(porcentaje.toString());

				listInforme.add(dto);
				totalNumero += numeroNovedades;
			}

			dtoList.setList(listInforme);
			dtoList.setTotalNumeroNov(totalNumero.toString());
			totalPorcentaje = (totalNumero / totalAsociados) * 100;
			dtoList.setTotalPorcentajeNov(totalPorcentaje.toString());

			list.add(dtoList);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(
					loaderResourceElements
							.getKeyResourceValue(
									ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
									"consulta.informacion.resumen.novedades")
							+ " - " + e.getMessage());
		}

		return list;
	}
	
	// Me entrega el número de novedades de los asociados especiales
	public Double consultarNumeroNovAsocEsp(String fechaProceso, String tipoNovdedad,
			Long zona) throws Exception {
		Double numeroNovedades = null;

		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			StringBuffer sql = new StringBuffer(
					"SELECT COUNT(N.CODIGO_ASOCIADO) TOTAL_INHABILES ");
			sql.append("FROM ELECDB.ELE_NOVEDAD N ");
			sql
					.append("INNER JOIN ELECDB.ELE_ASOCIADO A ON N.CODIGO_ASOCIADO = A.CODIGO_ASOCIADO ");
			sql
					.append("INNER JOIN ELECDB.ELE_ZONA Z ON Z.CODIGO_ZONA = A.COD_ZONA_ASO ");
			sql
					.append("INNER JOIN ELECDB.ELE_ZONA_ELECTORAL ZE ON ZE.CODIGO_ZONA_ELE = Z.CODIGO_ZONA_ELE ");
			sql.append("WHERE Z.CODIGO_ZONA_ELE = " + zona + " ");
			sql.append("AND N.ESTADO_HABILIDAD = '" + tipoNovdedad + "' ");
			sql.append("AND N.FECHA_REGISTRO = '" + fechaProceso + "'");
			sql.append("AND EXISTS (SELECT * FROM   ELECDB.ELE_ASOCIADO_ESPECIAL asoEsp where A.NUMERO_DOCUMENTO = asoEsp.NUMERO_DOCUMENTO )");

			SQLQuery query = session.createSQLQuery(sql.toString());
			query.addScalar("TOTAL_INHABILES", Hibernate.DOUBLE);

			numeroNovedades = (Double) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(
					loaderResourceElements
							.getKeyResourceValue(
									ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
									"consulta.numero.novedades")
							+ " - " + e.getMessage());
		} finally {
			session.close();
		}
		return numeroNovedades;
	}

	public Double consultarNumeroNov(String fechaProceso, String tipoNovdedad,
			Long zona) throws Exception {
		Double numeroNovedades = null;

		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			StringBuffer sql = new StringBuffer(
					"SELECT COUNT(N.CODIGO_ASOCIADO) TOTAL_INHABILES ");
			sql.append("FROM ELECDB.ELE_NOVEDAD N ");
			sql
					.append("INNER JOIN ELECDB.ELE_ASOCIADO A ON N.CODIGO_ASOCIADO = A.CODIGO_ASOCIADO ");
			sql
					.append("INNER JOIN ELECDB.ELE_ZONA Z ON Z.CODIGO_ZONA = A.COD_ZONA_ASO ");
			sql
					.append("INNER JOIN ELECDB.ELE_ZONA_ELECTORAL ZE ON ZE.CODIGO_ZONA_ELE = Z.CODIGO_ZONA_ELE ");
			sql.append("WHERE Z.CODIGO_ZONA_ELE = " + zona + " ");
			sql.append("AND N.ESTADO_HABILIDAD = '" + tipoNovdedad + "' ");
			sql.append("AND N.FECHA_REGISTRO = '" + fechaProceso + "'");

			SQLQuery query = session.createSQLQuery(sql.toString());
			query.addScalar("TOTAL_INHABILES", Hibernate.DOUBLE);

			numeroNovedades = (Double) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(
					loaderResourceElements
							.getKeyResourceValue(
									ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
									"consulta.numero.novedades")
							+ " - " + e.getMessage());
		} finally {
			session.close();
		}
		return numeroNovedades;
	}

	public void registrarNovedad(String estadoHabilidad, String documento,
			String observaciones, String usuarioRegistro) throws Exception {

		if (observaciones.isEmpty() || observaciones == null) {
			throw new Exception("No hay observaciones");
		}
		if (observaciones.length() > 2001) {
			throw new Exception(
					"Solo se permiten 2000 caracteres para la observación");
		}

		Session session = HibernateSessionFactoryElecciones2012.getSession();
		Transaction tx = session.beginTransaction();

		try {
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp fechaRegistroAud = new java.sql.Timestamp(
					lnMilisegundos);
			java.sql.Date fechaRegistro = new java.sql.Date(lnMilisegundos);
			EleNovedad instancia = new EleNovedad();
			instancia.setConsecutivoNovedad(consecutivo());
			EleAsociado eleAsociado = new EleAsociado();
			LogicaAsociado logicaAsociado = LogicaAsociado.getInstance();
			eleAsociado.setCodigoAsociado(logicaAsociado
					.obtieneCodAsociado(Long.valueOf(documento)));
			instancia.setEleAsociado(eleAsociado);
			instancia.setEstadoHabilidad(estadoHabilidad);
			instancia.setFechaRegistroAud(fechaRegistroAud);
			instancia.setFechaRegistro(fechaRegistro);
			instancia.setObservacion(observaciones);
			instancia.setUsuarioRegistro(usuarioRegistro);
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
				ConstantesNamedQueries.QUERY_SEQ_ELE_NOVEDAD,
				HibernateSessionFactoryElecciones2012.getSession());
		return consecutivo;
	}

	public void notificarCambioHabilidad(String asunto, String mensaje,
			Long codigoParametroListaCorreos) throws Exception {
		List<Object[]> elements = null;
		String correoDestino = null;
		java.util.Date utilDate = new java.util.Date(); // fecha actual
		long lnMilisegundos = utilDate.getTime();
		java.sql.Timestamp fechaRegistroAud = new java.sql.Timestamp(
				lnMilisegundos);
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		mensaje += ". Fecha y hora de registro: "
				+ formateador.format(fechaRegistroAud);
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		SendMail2 mail = null;
		StringBuffer cuerpoCorreo = new StringBuffer();
		/*cuerpoCorreo
				.append("<html><head><meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1'></head><body><table>");
		cuerpoCorreo.append("<tr><td colspan='2'>" + asunto
				+ "</b></td></tr><tr><td colspan='2'>&nbsp;</td></tr>");
		cuerpoCorreo.append("<tr><td colspan='2'>" + mensaje
				+ "</b></td></tr><tr><td colspan='2'>&nbsp;</td></tr>");
		cuerpoCorreo.append("</table></body></html>");*/
		
		cuerpoCorreo.append(mensaje);

		//StringBuffer mensajeSolicitante = new StringBuffer();
		/*mensajeSolicitante.append(cuerpoCorreo.toString().replace("ó",
				"&oacute;").replace("é", "&eacute;").replace("í", "&iacute;")
				.replace("á", "&aacute;").replace("ú", "&uacute;").replace("ñ",
						"&ntilde;").replace("Ñ", "&Ntilde;"));*/
		//mensajeSolicitante.append(cuerpoCorreo.toString());

		try {
			Query query = session.getNamedQuery("consultar.lista.correos");
			query.setLong("tipCodigo", codigoParametroListaCorreos);
			elements = query.list();
			String correosDestino[] = new String[elements.size()];
			if (elements != null && elements.size() > 0) {
				int i = 0;
				for (Object[] object : elements) {
					correoDestino = new String();
					correoDestino = object[0].toString().trim();
					if (correoDestino != null && !correoDestino.equals("")) {
						correosDestino[i] = correoDestino;
					}
					i++;
				}

			}
			if (elements != null && elements.size() > 0) {
				mail = new SendMail2();
				mail.postMail(correosDestino, asunto, cuerpoCorreo.toString());
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			elements = null;
			mail = null;
			correoDestino = null;
			session.close();
		}
	}
}
