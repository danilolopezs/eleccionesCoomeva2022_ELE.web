package co.com.coomeva.habilidad.procesos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import co.com.coomeva.ele.entidades.habilidad.EleProceso;
import co.com.coomeva.ele.entidades.habilidad.EleProcesoRegla;
import co.com.coomeva.ele.entidades.habilidad.EleRegla;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.habilidad.dao.EleProcesoDAO;
import co.com.coomeva.ele.entidades.habilidad.dao.EleProcesoReglaDAO;
import co.com.coomeva.ele.entidades.habilidad.dao.EleReglaDAO;
import co.com.coomeva.habilidad.exception.AssociateAbilityException;
import co.com.coomeva.habilidad.reglas.FabricaRegla;
import co.com.coomeva.habilidad.reglas.ReglaHabAsociado;
import co.com.coomeva.habilidad.utilidades.Constantes;
import co.com.coomeva.habilidad.utilidades.GeneradorPK;

public class ProcesoVerificacionHabilidadAsociados implements IProcesoVerificacionHabilidadAsociados {

	private static Logger procesoHabAsociadosLogger = Logger.getLogger(ProcesoVerificacionHabilidadAsociados.class);

	private List<ReglaHabAsociado> reglas;

	@Override
	public void ejecutarProcesoVerificacionHabilidad(String[] parametros) throws AssociateAbilityException {

		reglas = new ArrayList<ReglaHabAsociado>();
		List<EleProcesoRegla> listaReglasProceso = null;

		if (parametros != null && parametros.length > 0) {
			listaReglasProceso = prepararEjecucionNoPrograma(parametros);
		} else {
			throw new AssociateAbilityException(
					"No se ha especificado qué reglas se desean ejecutar para este proceso.  Por favor ingrese"
							+ " los códigos de las reglas que desea ejecutar");
		}

		try {
			String StrPoblarTablaEleAsociados = System
					.getProperty(Constantes.ARGUMENTO_NOMBRADO_PLOBAR_TABLA_ELE_ASOCIADO, "false");
			boolean poblarTablaEleAsociados = Boolean.parseBoolean(StrPoblarTablaEleAsociados);

			if (poblarTablaEleAsociados) {
				System.out.println("%%%%%%% Preparando proceso que inicia el llenado de la tabla ELE_ASOCIADO %%%%%%%");
				procesoHabAsociadosLogger
						.info("\n%%%%%%% Preparando proceso que inicia el llenado de la tabla ELE_ASOCIADO %%%%%%%");

				// poblarTablaeEleAsociados();

				procesoHabAsociadosLogger
						.info("\n%%%%%%% Terminó proceso que de llenado de la tabla ELE_ASOCIADO %%%%%%%");
				System.out.println("%%%%%%% Terminó proceso que de llenado de la tabla ELE_ASOCIADO %%%%%%%");
			}
		} catch (Exception e) {
			procesoHabAsociadosLogger.error(
					"\n###############################################  --> Se ha presentado un error intentando poblar la tabla ELE_ASOCIADO. "
							+ e.getMessage());

			throw new AssociateAbilityException(
					"Se ha presentado un error intentando poblar la tabla ELE_ASOCIADO. " + e.getMessage());
		}

		Collections.sort(listaReglasProceso);

		int i = 0;
		for (ReglaHabAsociado r : reglas) {
//			try {
//				r.ejecutarRegla(listaReglasProceso.get(i));
//			} catch (AssociateAbilityException e) {
//				System.out
//						.println("###############################################  -->  Ocurrio un error ejecutando la actual regla. "
//								+ e.getMessage());
//			} finally{
//				i++;
//			}
		}
	}

	public synchronized void ejecutarProcesoVerificacionHabilidad(EleProceso proceso) throws AssociateAbilityException {

		validarEstadoActualProceso(proceso.getCodigoProceso());

		List<EleProcesoRegla> listaReglasProceso = new ArrayList<EleProcesoRegla>(proceso.getEleProcesoReglas());

		Collections.sort(listaReglasProceso);

		reglas = new ArrayList<ReglaHabAsociado>();

		for (EleProcesoRegla eleProcesoRegla : listaReglasProceso) {
			ReglaHabAsociado reglaCargada = FabricaRegla.construirRegla(eleProcesoRegla.getEleRegla().getCodigoRegla());
			reglas.add(reglaCargada);
		}

		int i = 0;
		for (ReglaHabAsociado r : reglas) {
			try {
				r.ejecutarRegla(listaReglasProceso.get(i));
			} catch (AssociateAbilityException e) {
				System.out.println(
						"###############################################  -->  Ocurrio un error ejecutando la actual regla. "
								+ e.getMessage());
			} finally {
				i++;
			}
		}

		procesoHabAsociadosLogger
				.info("\n%%%%%%% Terminó todo el proceso de verificación de habilidad de asociados %%%%%%%");
	}

	private void validarEstadoActualProceso(Long codigoProceso) throws AssociateAbilityException {

		EleProceso procesoActual = new EleProcesoDAO().findById(codigoProceso);

		if (procesoActual != null) {

			if (!Constantes.CODIGO_ESTADO_PROCESO_EJECUTADO.equals(procesoActual.getEstadoProceso())) {

				Transaction tr = null;
				Session session = null;
				try {
					session = HibernateSessionFactoryElecciones2012.getSession();
					tr = session.beginTransaction();
					procesoActual.setEstadoProceso(Constantes.CODIGO_ESTADO_PROCESO_EJECUTADO);
					new EleProcesoDAO().update(procesoActual);
					tr.commit();
				} catch (Exception e) {
					if (tr != null && tr.isActive()) {
						tr.rollback();
					}

					throw new AssociateAbilityException("Se ha presentado un error al intentar actualizar "
							+ "el estado del proceso antes de su ejecución", e);
				}

			} else {
				throw new AssociateAbilityException("El proceso que intenta ejecutar ya fue ejecutado previamente,"
						+ " por favor cree un nuevo proceso con sus respectivas reglas");
			}

		} else {
			throw new AssociateAbilityException("No existe un proceso registrado con el id " + codigoProceso);
		}
	}

	/**
	 * Método que se encarga de registrar el nuevo proceso y sus reglas asociadas
	 * cuando las reglas solicitadas para ejecución son pasadas como parámetros y
	 * estás no han sido programadas previamente
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 7/11/2012
	 * @param vars
	 * @throws AssociateAbilityException
	 */
	public List<EleProcesoRegla> prepararEjecucionNoPrograma(String[] vars) throws AssociateAbilityException {

		Transaction tr = null;
		Session session = null;
		List<EleProcesoRegla> listaEleProcesoRegla = null;

		try {
			session = HibernateSessionFactoryElecciones2012.getSession();
			tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
			EleReglaDAO eleReglaDAO = new EleReglaDAO();
			EleProcesoDAO eleProcesoDAO = new EleProcesoDAO();
			EleProcesoReglaDAO eleProcesoReglaDAO = new EleProcesoReglaDAO();

			EleProceso eleProceso = new EleProceso();
			eleProceso.setCodigoProceso(GeneradorPK.generarPKTabla("ELECDB", "ELE_PROCESO", "CODIGO_PROCESO"));
			eleProceso.setEstadoProceso("1");
			eleProceso.setFechaProgramacion(new Date());

			eleProcesoDAO.save(eleProceso);

			listaEleProcesoRegla = new ArrayList<EleProcesoRegla>();

			Long contadorConsecutivoProRegla = GeneradorPK.generarPKTabla("ELECDB", "ELE_PROCESO_REGLA",
					"CONSECUTIVO_PRO_REGLA");

			for (String parametro : vars) {
				ReglaHabAsociado reglaCargada = FabricaRegla.construirRegla(Long.parseLong(parametro));
				reglas.add(reglaCargada);

				EleProcesoRegla eleProcesoRegla = new EleProcesoRegla();

				eleProcesoRegla = new EleProcesoRegla();
				eleProcesoRegla.setConsecutivoProRegla(contadorConsecutivoProRegla);
				eleProcesoRegla.setEleProceso(eleProceso);

				EleRegla regla = eleReglaDAO.findById(Long.parseLong(parametro));
				eleProcesoRegla.setEleRegla(regla);

				eleProcesoRegla.setEstadoProgramacion("1");
				eleProcesoRegla.setFechaFinEjecucion(null);
				eleProcesoRegla.setFechaInicioEjecucion(new Date());
				eleProcesoRegla.setUsuarioRegistra("Javier");

				eleProcesoReglaDAO.save(eleProcesoRegla);
				listaEleProcesoRegla.add(eleProcesoRegla);
				contadorConsecutivoProRegla++;
			}

			tr.commit();
		} catch (Exception e) {

			if (tr != null && tr.isActive()) {
				tr.rollback();
			}

			procesoHabAsociadosLogger
					.error("\n###############################################  --> Se ha presentado un error"
							+ " al intentar registrar la programación de las nuevas reglas. " + e.getMessage());

			throw new AssociateAbilityException("Se ha presentado un error"
					+ " al intentar registrar la programación de las nuevas reglas. " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}

			session = null;
			tr = null;
		}

		return listaEleProcesoRegla;
	}

	public static void main(String[] args) {

		try {
			new ProcesoVerificacionHabilidadAsociados().ejecutarProcesoVerificacionHabilidad(args);
		} catch (Exception e) {
			System.out.println("Ocurrió un error fatal.  " + e.getMessage());
		}
	}

}
