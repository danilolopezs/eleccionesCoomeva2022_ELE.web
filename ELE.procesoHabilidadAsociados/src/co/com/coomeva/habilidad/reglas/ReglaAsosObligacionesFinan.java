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
import co.com.coomeva.habilidad.vo.VOAsosObligacionesFinanMora;

/**
 * Regla que se encarga de verificar qué asociados se encuentran en mora con sus
 * obligaciones financieras tanto con el banco como con la financiera
 * 
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
 *         Premize SAS <br>
 * @project ELE.procesoHabilidadAsociados
 * @class ReglaValidacionObligacionesFinanAso
 * @date 3/11/201
 *
 */
public class ReglaAsosObligacionesFinan extends ReglaHabAsociado {

	private static Logger procesoHabAsociadosLogger = Logger.getLogger(ReglaAsosObligacionesFinan.class);

	public ReglaAsosObligacionesFinan(IDataSourceReglaHabAsociado dataSource) {
		super(dataSource);
	}

	@Override
	public void ejecutarRegla(Object... var) throws AssociateAbilityException {

		procesoHabAsociadosLogger.info("\n%%%%%%% Iniciando la ejecución de la regla que "
				+ "valida que asociados están en mora con sus obligaciones tanto con la financiera"
				+ " como en multiactiva");

		EleProcesoRegla eleProcesoregla = (EleProcesoRegla) var[0];

		Long codigoOriginalRegla = eleProcesoregla.getEleRegla().getCodigoRegla();
		eleProcesoregla.getEleRegla().setCodigoRegla(Constantes.CODIGO_REGLA_VALIDACION_OBLIGACIONES_FINANCIERAS);

		List<? extends Object> datosRegla = getDataSourceRegla().obtenerDatosRegla(eleProcesoregla);

		if (datosRegla == null || datosRegla.isEmpty()) {
			eleProcesoregla.getEleRegla()
					.setCodigoRegla(Constantes.CODIGO_REGLA_VALIDACION_OBLIGACIONES_FINANCIERAS_POST_PRIMERA_EJE);
			datosRegla = getDataSourceRegla().obtenerDatosRegla(eleProcesoregla);
		}
		eleProcesoregla.setFechaInicioEjecucion(new Date());

		List<Long> asosCuentasAhorrosActDebAuto = getDataSourceRegla().obtenerAsosCuentaAhorrosActivasDebitoAuto();

		List<Long> asosConProdsReestructuradosMul = getDataSourceRegla().obtenerAsosReestrucProdsMultiactiva();

		List<Long> asosConProdsReestructuradosFina = getDataSourceRegla().obtenerAsosReestrucProdsFinanciera();

		List<Long> asosConPagosOtrasEntidades = getDataSourceRegla().obtenerAsosConPagosOtraEntidades();

		List<Long> asosCubiertosPorFondoCalamidad = getDataSourceRegla().obtenerAsosCubiertosPorFondoCalamidad();

		Long i = 1L;
		Transaction tr = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();

		try {
			tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();

			for (Object vOAsosObligacionesFinanMora : datosRegla) {

				VOAsosObligacionesFinanMora registroVo = ((VOAsosObligacionesFinanMora) vOAsosObligacionesFinanMora);

				Long valorTotalMora = (registroVo.getValorMoraMultiactiva() != null
						? registroVo.getValorMoraMultiactiva()
						: 0L)
						+ (registroVo.getValorMoraFinanciera() != null ? registroVo.getValorMoraFinanciera() : 0L);

				Long valorMoraMul = (registroVo.getValorMoraMultiactiva() != null ? registroVo.getValorMoraMultiactiva()
						: 0);
				Long valorMoraFin = (registroVo.getValorMoraFinanciera() != null ? registroVo.getValorMoraFinanciera()
						: 0);

				if ((asosConPagosOtrasEntidades.contains(registroVo.getNumintAsociado()))
						|| (asosCubiertosPorFondoCalamidad.contains(registroVo.getNumintAsociado()))) {
					continue;
				} else if (valorMoraFin + valorMoraMul > 10000) {
					String mensajeInhabilidad = "";
					if (registroVo.getValorMoraFinanciera() != null) {
						if (asosConProdsReestructuradosFina.contains(registroVo.getNumintAsociado())) {
							if (valorMoraMul < 10000) {
								continue;
							}
							valorMoraFin = new Long(0);
						} else {
							mensajeInhabilidad += "No se encuentra al día con las obligaciones derivadas por servicios prestados con Bancoomeva. ";
						}
					}
					if (registroVo.getValorMoraMultiactiva() != null) {
						if (asosConProdsReestructuradosMul.contains(registroVo.getNumintAsociado())) {
							if (valorMoraFin < 10000) {
								continue;
							}
						} else {
							mensajeInhabilidad += " No se encuentra al día con las obligaciones estatutarias, reglamentarias, crediticias y de todo tipo con la Cooperativa.";
						}
					}
					if (!mensajeInhabilidad.equals("")) {
						registrarInhabilidad(session, registroVo.getNumintAsociado(),
								eleProcesoregla.getConsecutivoProRegla(),
								eleProcesoregla.getEleProceso().getCodigoProceso(),
								Constantes.CODIGO_REGLA_VALIDACION_OBLIGACIONES_FINANCIERAS, mensajeInhabilidad,
								registroVo.getValorMoraFinanciera(), registroVo.getValorMoraMultiactiva());

						tr = validarCommit(i, tr, session);
						i++;
					}
				}
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
					+ "valida que asociados están en mora con sus obligaciones tanto con la financiera"
					+ " como en multiactiva");
		} catch (Exception e) {

			if (tr != null && tr.isActive()) {
				tr.rollback();
			}

			e.printStackTrace();

			procesoHabAsociadosLogger
					.error("\n###############################################  --> No se logró ejecutar"
							+ " completamente la regla que "
							+ "valida que asociados están en mora con sus obligaciones tanto con la financiera"
							+ " como en multiactiva. " + e.getMessage());

			throw new AssociateAbilityException("No se logró ejecutar" + " completamente la regla que "
					+ "valida que asociados están en mora con sus obligaciones tanto con la financiera"
					+ " como en multiactiva. " + e.getMessage(), e);
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
