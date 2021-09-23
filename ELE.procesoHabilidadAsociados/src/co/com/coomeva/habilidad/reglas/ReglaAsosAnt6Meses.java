package co.com.coomeva.habilidad.reglas;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import co.com.coomeva.ele.entidades.habilidad.EleProcesoRegla;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.habilidad.dao.EleProcesoReglaDAO;
import co.com.coomeva.habilidad.datasources.DataSourceAsosInhabilesAnteriorProcesoPorRegla;
import co.com.coomeva.habilidad.datasources.IDataSourceReglaHabAsociado;
import co.com.coomeva.habilidad.exception.AssociateAbilityException;
import co.com.coomeva.habilidad.utilidades.Constantes;
import co.com.coomeva.habilidad.vo.VOAsosObligacionesFinanMora;

/**
 * Regla que se encarga de verificar qué asociados poseen una antiguedad menor a
 * 6 meses, tomando como fechas de referencia la fecha de ingreso y la fecha de
 * las votaciones de las elecciones
 * 
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
 *         Premize SAS <br>
 * @project ELE.procesoHabilidadAsociados
 * @class ReglaAntAso6Meses
 * @date 1/11/2012
 *
 */

public class ReglaAsosAnt6Meses extends ReglaHabAsociado {

	private static Logger procesoHabAsociadosLogger = Logger.getLogger(ReglaAsosAnt6Meses.class);

	public ReglaAsosAnt6Meses(IDataSourceReglaHabAsociado dataSource) {
		super(dataSource);
	}

	@Override
	public void ejecutarRegla(Object... var) throws AssociateAbilityException {

		procesoHabAsociadosLogger.info("\n%%%%%%% Iniciando la ejecución de la regla que "
				+ "valida que la antiguedad del asociado sea mayor a 6 meses %%%%%%%");

		EleProcesoRegla eleProcesoregla = (EleProcesoRegla) var[0];

		Long codigoOriginalRegla = eleProcesoregla.getEleRegla().getCodigoRegla();

		eleProcesoregla.getEleRegla().setCodigoRegla(Constantes.CODIGO_REGLA_VALIDACION_ANT_ASOCIADO);

		List<? extends Object> datosRegla = getDataSourceRegla().obtenerDatosRegla(eleProcesoregla);

		if (datosRegla == null || datosRegla.isEmpty()) {
			eleProcesoregla.getEleRegla()
					.setCodigoRegla(Constantes.CODIGO_REGLA_VALIDACION_ANT_ASOCIADO_POST_PRIMERA_EJE);
			datosRegla = getDataSourceRegla().obtenerDatosRegla(eleProcesoregla);
		}

		Long i = 1L;
		Transaction tr = null;
		Session session = null;

		try {
			session = HibernateSessionFactoryElecciones2012.getSession();
			tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();

			Long asoNumint = null;
			for (Object asociado : datosRegla) {

				if (getDataSourceRegla() instanceof DataSourceAsosInhabilesAnteriorProcesoPorRegla) {
					asoNumint = ((VOAsosObligacionesFinanMora) asociado).getNumintAsociado();
				} else {
					asoNumint = (Long) asociado;
				}

				registrarInhabilidad(session, asoNumint, eleProcesoregla.getConsecutivoProRegla(),
						eleProcesoregla.getEleProceso().getCodigoProceso(),
						Constantes.CODIGO_REGLA_VALIDACION_ANT_ASOCIADO,
						eleProcesoregla.getEleRegla().getObsViolacionRegla(), null, null);

				tr = validarCommit(i, tr, session);
				i++;
			}

			EleProcesoRegla proRegla = new EleProcesoReglaDAO().findById(eleProcesoregla.getConsecutivoProRegla());
			proRegla.getEleRegla().setCodigoRegla(codigoOriginalRegla);
			proRegla.setFechaFinEjecucion(new Date());
			proRegla.setEstadoProgramacion(Constantes.CODIGO_ESTADO_PROCESO_EJECUTADO);
			new EleProcesoReglaDAO().update(proRegla);

			tr.commit();

			procesoHabAsociadosLogger.info("\n%%%%%%% Comprometidos (commit) los primeros " + i + " registros "
					+ "para la regla actual %%%%%%%");

			procesoHabAsociadosLogger.info("\n%%%%%%% Finalizó la ejecución de la regla que "
					+ "valida que la antiguedad del asociado sea mayor a 6 meses %%%%%%%");
		} catch (Exception e) {

			if (tr != null && tr.isActive()) {
				tr.rollback();
			}

			e.printStackTrace();

			procesoHabAsociadosLogger
					.error("\n###############################################  --> No se logró ejecutar"
							+ " completamente la regla que valida que la antiguedad del asociado sea mayor a 6 meses. "
							+ e.getMessage());

			throw new AssociateAbilityException("No se logró ejecutar"
					+ " completamente la regla que valida que la antiguedad del asociado sea mayor a 6 meses. "
					+ e.getMessage(), e);
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
