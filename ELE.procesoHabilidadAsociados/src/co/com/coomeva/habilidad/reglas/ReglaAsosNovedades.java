package co.com.coomeva.habilidad.reglas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import co.com.coomeva.ele.entidades.habilidad.EleProcesoRegla;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.habilidad.dao.EleProcesoReglaDAO;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;
import co.com.coomeva.habilidad.datasources.IDataSourceReglaHabAsociado;
import co.com.coomeva.habilidad.exception.AssociateAbilityException;
import co.com.coomeva.habilidad.utilidades.Constantes;

public class ReglaAsosNovedades extends ReglaHabAsociado {

	private static Logger procesoHabAsociadosLogger = Logger.getLogger(ReglaAsosNovedades.class);

	public ReglaAsosNovedades(IDataSourceReglaHabAsociado dataSource) {
		super(dataSource);
	}

	@Override
	public void ejecutarRegla(Object... var) throws AssociateAbilityException {
		EleProcesoRegla eleProcesoregla = (EleProcesoRegla) var[0];
		registrarNovedadesPorHabilidad(eleProcesoregla);
		registrarNovedadesPorInhabilidad(eleProcesoregla);
	}

	private void registrarNovedadesPorInhabilidad(EleProcesoRegla eleProcesoregla) throws AssociateAbilityException {

		Session session = null;
		SQLQuery query = null;
		PreparedStatement stmt = null;

		procesoHabAsociadosLogger.info("\n%%%%%%% Iniciando la ejecución de la regla que "
				+ " se encarga del registro de novedades para asociados inhábiles %%%%%%%");

		session = HibernateSessionFactoryElecciones2012.getSession();
		Connection con = session.connection();
		query = (SQLQuery) session.getNamedQuery(ConstantesNamedQueries.QUERY_REGISTRAR_NOVEDAD_ASOCIAD0S_INHABILES);
		try {
			con.setAutoCommit(false);
			String sentencia = query.getQueryString();
			sentencia = sentencia.replace("codigoProcesoActual",
					eleProcesoregla.getEleProceso().getCodigoProceso().toString());
			sentencia = sentencia.replace("estadoProcesoEjecutado", Constantes.CODIGO_ESTADO_PROCESO_EJECUTADO);
			stmt = con.prepareStatement(sentencia);
			stmt.execute();
			con.commit();

			Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
			eleProcesoregla.setFechaFinEjecucion(new Date());
			eleProcesoregla.setEstadoProgramacion(Constantes.CODIGO_ESTADO_PROCESO_EJECUTADO);
			new EleProcesoReglaDAO().update(eleProcesoregla);
			tr.commit();

			procesoHabAsociadosLogger.info("\n%%%%%%% Finalizó la ejecución de la regla que "
					+ "se encarga del registro de novedades para asociados inhábiles %%%%%%%");
		} catch (SQLException e) {
			procesoHabAsociadosLogger.error(
					"\n###############################################  --> Se ha presentado un error intentando ejecutar la regla que se encarga"
							+ " del registro de novedades para asociados inhábiles" + e.getMessage());
			throw new AssociateAbilityException("Se ha presentado un error intentando ejecutar la regla que se encarga"
					+ " del registro de novedades para asociados inhábiles. " + e.getMessage(), e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e2) {
					throw new RuntimeException("Se ha presentado un error cerrando"
							+ " el statement en la regla de cambio de estados del asociado en " + "el buc", e2);
				}
			}
			session.close();
			session = null;
			query = null;
			stmt = null;
		}

	}

	private void registrarNovedadesPorHabilidad(EleProcesoRegla eleProcesoregla) throws AssociateAbilityException {

		Session session = null;
		SQLQuery query = null;
		PreparedStatement stmt = null;

		procesoHabAsociadosLogger.info("\n%%%%%%% Iniciando la ejecución de la regla que "
				+ " se encarga del registro de novedades para asociados hábiles %%%%%%%");

		session = HibernateSessionFactoryElecciones2012.getSession();
		Connection con = session.connection();
		query = (SQLQuery) session.getNamedQuery(ConstantesNamedQueries.QUERY_REGISTRAR_NOVEDAD_ASOCIAD0S_HABILES);
		try {
			con.setAutoCommit(false);
			String sentencia = query.getQueryString();
			sentencia = sentencia.replace("codigoProcesoActual",
					eleProcesoregla.getEleProceso().getCodigoProceso().toString());
			sentencia = sentencia.replace("estadoProcesoEjecutado", Constantes.CODIGO_ESTADO_PROCESO_EJECUTADO);
			stmt = con.prepareStatement(sentencia);
			stmt.execute();
			con.commit();

			procesoHabAsociadosLogger.info("\n%%%%%%% Finalizó la ejecución de la regla que "
					+ " se encarga del registro de novedades para asociados hábiles %%%%%%%");
		} catch (SQLException e) {
			procesoHabAsociadosLogger.error(
					"\n###############################################  --> Se ha presentado un error intentando ejecutar la regla que se encarga"
							+ " del registro de novedades para asociados hábiles. " + e.getMessage());
			throw new AssociateAbilityException("Se ha presentado un error intentando ejecutar la regla que se encarga"
					+ " del registro de novedades para asociados hábiles. " + e.getMessage(), e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e2) {
					throw new RuntimeException("Se ha presentado un error cerrando"
							+ " el statement en la regla que se encarga del registro de" + " novedades de habilidad",
							e2);
				}
			}
			session.close();
			session = null;
			query = null;
			stmt = null;
		}

	}

}
