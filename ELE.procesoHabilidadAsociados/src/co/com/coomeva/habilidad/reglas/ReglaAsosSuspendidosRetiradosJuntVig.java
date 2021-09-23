package co.com.coomeva.habilidad.reglas;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import co.com.coomeva.ele.entidades.habilidad.EleProcesoRegla;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.habilidad.dao.EleProcesoReglaDAO;
import co.com.coomeva.habilidad.datasources.IDataSourceReglaHabAsociado;
import co.com.coomeva.habilidad.exception.AssociateAbilityException;
import co.com.coomeva.habilidad.utilidades.Constantes;

/**
 * Regla que se encarga de verificar qué asociados se encuentran registrados
 * como suspendidos o retirados sus derechos por la junta de vigilancia
 * 
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
 *         Premize SAS <br>
 * @project ELE.procesoHabilidadAsociados
 * @class ReglaAsosSuspendidosRetiradosJuntVig
 * @date 6/11/2012
 *
 */
public class ReglaAsosSuspendidosRetiradosJuntVig extends ReglaHabAsociado {

	private static Logger procesoHabAsociadosLogger = Logger.getLogger(ReglaAsosSuspendidosRetiradosJuntVig.class);

	public ReglaAsosSuspendidosRetiradosJuntVig(IDataSourceReglaHabAsociado dataSource) {
		super(dataSource);
	}

	@Override
	public void ejecutarRegla(Object... var) throws AssociateAbilityException {

		procesoHabAsociadosLogger.info("\n%%%%%%% Iniciando la ejecución de la regla que "
				+ "valida que asociados están reportados por la junta de vigilancia %%%%%%%");

		List<? extends Object> datosRegla = getDataSourceRegla().obtenerDatosRegla();

		Long i = 1L;
		Transaction tr = null;
		Session session = null;

		EleProcesoRegla eleProcesoRegla = ((EleProcesoRegla) var[0]);

		try {
			session = HibernateSessionFactoryElecciones2012.getSession();
			tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();

			for (Object asociado : datosRegla) {

				registrarInhabilidad(session, (Long) asociado, eleProcesoRegla.getConsecutivoProRegla(),
						eleProcesoRegla.getEleProceso().getCodigoProceso(),
						eleProcesoRegla.getEleRegla().getCodigoRegla(),
						eleProcesoRegla.getEleRegla().getObsViolacionRegla(), null, null);

				tr = validarCommit(i, tr, session);
				i++;
			}

			eleProcesoRegla.setFechaFinEjecucion(new Date());
			eleProcesoRegla.setEstadoProgramacion(Constantes.CODIGO_ESTADO_PROCESO_EJECUTADO);
			new EleProcesoReglaDAO().update(eleProcesoRegla);

			tr.commit();

			procesoHabAsociadosLogger.info("\n%%%%%%% Comprometidos (commit) los primeros " + i + " registros "
					+ "para la regla actual %%%%%%%");

			procesoHabAsociadosLogger.info("\n%%%%%%% Finalizó la ejecución de la regla que "
					+ "valida que asociados están reportados por la junta de vigilancia %%%%%%%");
		} catch (Exception e) {

			if (tr != null && tr.isActive()) {
				tr.rollback();
			}

			procesoHabAsociadosLogger
					.error("\n###############################################  --> No se logró ejecutar "
							+ "completamente la regla que "
							+ "valida que asociados están reportados por la junta de vigilancia. " + e.getMessage());

			throw new AssociateAbilityException("No se logró ejecutar " + "completamente la regla que "
					+ "valida que asociados están reportados por la junta de vigilancia. " + e.getMessage(), e);
		} finally {
			datosRegla = null;
			tr = null;

			if (session != null) {
				session.close();
			}

			session = null;
		}

	}

}
