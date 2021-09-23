package co.com.coomeva.habilidad.reglas;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import co.com.coomeva.ele.entidades.habilidad.EleAsociado;
import co.com.coomeva.ele.entidades.habilidad.EleInhabilidad;
import co.com.coomeva.ele.entidades.habilidad.EleProceso;
import co.com.coomeva.ele.entidades.habilidad.EleProcesoRegla;
import co.com.coomeva.ele.entidades.habilidad.EleRegla;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.habilidad.dao.EleInhabilidadDAO;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;
import co.com.coomeva.ele.utilidades.GeneradorConsecutivos;
import co.com.coomeva.habilidad.datasources.IDataSourceReglaHabAsociado;
import co.com.coomeva.habilidad.exception.AssociateAbilityException;
import co.com.coomeva.habilidad.utilidades.Constantes;

/**
 * Clase que define el método que debe implementar una clase
 * para que sea considerada como una regla del proceso de verificación
 * de habilidad de asociados
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
 * @project ELE.procesoHabilidadAsociados
 * @class IReglaHabAsociado
 * @date 1/11/2012
 *
 */
public abstract class ReglaHabAsociado {
	
	private static Logger procesoHabAsociadosLogger = Logger.getLogger(ReglaHabAsociado.class);
	private final EleInhabilidadDAO eleInhabilidadDAO;
	private final IDataSourceReglaHabAsociado dataSourceRegla;
	
	public ReglaHabAsociado(IDataSourceReglaHabAsociado ds){
		eleInhabilidadDAO = new EleInhabilidadDAO();
		dataSourceRegla = ds;
	}
	
	/**
	 * Ejecuta la regla de validación
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
	 * @date 1/11/2012
	 * @param var
	 * @throws AssociateAbilityException
	 */
	public abstract void ejecutarRegla(Object...var) throws AssociateAbilityException;
	
	/**
	 * Método que se encarga del registro de las inhabilidades para un 
	 * asociado
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
	 * @date 6/11/2012
	 * @param session
	 * @param numintAsociado
	 * @param consecutivoProRegla
	 * @param codigoProceso
	 * @param codigoRegla
	 * @param observaciones
	 * @throws AssociateAbilityException
	 */
	protected void registrarInhabilidad(Session session, Long numintAsociado,
			Long consecutivoProRegla, Long codigoProceso, Long codigoRegla,
			String observaciones, Long valorMoraFin, Long valorMoraMul) throws AssociateAbilityException {
		
		EleInhabilidad inhabilidad = new EleInhabilidad();
		inhabilidad.setEleAsociado(new EleAsociado());
		inhabilidad.getEleAsociado().setCodigoAsociado(numintAsociado);
		inhabilidad.setEleProcesoRegla(new EleProcesoRegla());

		Long consecutivoInhabilidad = GeneradorConsecutivos
				.getInstance().getConsecutivo(
						ConstantesNamedQueries.QUERY_SEQ_ELE_INHABILIDAD, session);

		if (consecutivoInhabilidad == null) {
			throw new AssociateAbilityException(
					"No se logró obtener el consecutivo"
							+ " de la tabla de inhabilidad");
		}
		
		inhabilidad.setConsecutivoInhabilidad(consecutivoInhabilidad);
		inhabilidad.getEleProcesoRegla().setConsecutivoProRegla(consecutivoProRegla);
		inhabilidad.getEleProcesoRegla().setEleProceso(new EleProceso());
		inhabilidad.getEleProcesoRegla().getEleProceso().setCodigoProceso(codigoProceso);
		inhabilidad.getEleProcesoRegla().setEleRegla(new EleRegla());
		inhabilidad.getEleProcesoRegla().getEleRegla().setCodigoRegla(codigoRegla);
		inhabilidad.setObservaciones(observaciones);
		inhabilidad.setValorMoraFin(valorMoraFin);
		inhabilidad.setValorMoraMul(valorMoraMul);
		eleInhabilidadDAO.save(inhabilidad);
	}
	
	protected Transaction validarCommit(Long contadorRegistros, Transaction tr,
			Session session) {
		if (contadorRegistros % Constantes.CANTIDAD_REGISTROS_POR_COMMIT == 0) {
			session.flush();
			session.clear();
			tr.commit();

			procesoHabAsociadosLogger
					.info("\n%%%%%%% Comprometidos (commit) los primeros "
							+ contadorRegistros + " registros "
							+ "para la regla actual %%%%%%%");
			
			tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		}
		return tr;
	}

	public IDataSourceReglaHabAsociado getDataSourceRegla() {
		return dataSourceRegla;
	}

	public EleInhabilidadDAO getEleInhabilidadDAO() {
		return eleInhabilidadDAO;
	}
}
