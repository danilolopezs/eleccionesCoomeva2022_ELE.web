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

public class ReglaAsosCambioEstadoBuc extends ReglaHabAsociado {

	private static Logger procesoHabAsociadosLogger = Logger.getLogger(ReglaAsosCambioEstadoBuc.class);

	public ReglaAsosCambioEstadoBuc(IDataSourceReglaHabAsociado dataSource) {
		super(dataSource);
	}

	@Override
	public void ejecutarRegla(Object... var) throws AssociateAbilityException {
		Session session = null;
		SQLQuery query = null;
		PreparedStatement stmt = null;

		EleProcesoRegla eleProcesoregla = (EleProcesoRegla) var[0];

		procesoHabAsociadosLogger.info("\n%%%%%%% Iniciando la ejecución de la regla que "
				+ "que registra inhabilidades por cambios de estado del asociado en el buc %%%%%%%");

		session = HibernateSessionFactoryElecciones2012.getSession();
		Connection con = session.connection();
		query = (SQLQuery) session
				.getNamedQuery(ConstantesNamedQueries.QUERY_REGISTRAR_INHABILIDADES_POR_CAMBIO_ESTADO_BUC);

		try {
			con.setAutoCommit(false);
			String sentencia = query.getQueryString();
			sentencia = sentencia.replace("consecutivoProregla", eleProcesoregla.getConsecutivoProRegla().toString());
			sentencia = sentencia.replace("mensajeInhabilidad",
					"'" + eleProcesoregla.getEleRegla().getObsViolacionRegla() + "'");
			stmt = con.prepareStatement(sentencia);
			stmt.execute();
			con.commit();

			Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();

			EleProcesoRegla proRegla = new EleProcesoReglaDAO().findById(eleProcesoregla.getConsecutivoProRegla());
			proRegla.setFechaFinEjecucion(new Date());
			proRegla.setEstadoProgramacion(Constantes.CODIGO_ESTADO_PROCESO_EJECUTADO);
			new EleProcesoReglaDAO().update(proRegla);

			tr.commit();

			procesoHabAsociadosLogger.info("\n%%%%%%% Finalizó la ejecución de la regla que "
					+ "que registra inhabilidades por cambios de estado del asociado en el buc %%%%%%%");
		} catch (Exception e) {
			procesoHabAsociadosLogger.error(
					"\n###############################################  --> Se ha presentado un error intentando ejecutar la regla que registra"
							+ " inhabilidades por cambios de estado del asociado en el buc " + e.getMessage());
			throw new AssociateAbilityException("Se ha presentado un error intentando ejecutar la regla que registra"
					+ " inhabilidades por cambios de estado del asociado en el buc" + e.getMessage(), e);
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

}
