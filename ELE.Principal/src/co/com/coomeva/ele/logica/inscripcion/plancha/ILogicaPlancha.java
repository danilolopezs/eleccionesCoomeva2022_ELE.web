package co.com.coomeva.ele.logica.inscripcion.plancha;

import java.util.List;

import co.com.coomeva.ele.dto.DTOInformacionPlancha;
import co.com.coomeva.ele.dto.DTOMiembroPlancha;
import co.com.coomeva.ele.dto.DTOPlanchaAsociado;
import co.com.coomeva.ele.dto.DTOZonaElectoral;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;

/**
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
 *         Premize SAS <br>
 * @project ELE.Principal
 * @class ILogicaPlancha
 * @date 1/12/2012
 */

public interface ILogicaPlancha {

	/**
	 * Permite realizar la adición de un miembro a una plancha
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 1/12/2012
	 * @param miembros
	 * @param numeroDocumento
	 * @param posicionPlancha
	 * @param aplicaValidaciones
	 * @return
	 * @throws EleccionesDelegadosException
	 */
	List<DTOMiembroPlancha> adicionarMiembroPlancha(
			List<DTOMiembroPlancha> miembros, Long numeroDocumento,
			int posicionPlancha, Long consecutivoPlancha,
			boolean aplicaValidaciones) throws EleccionesDelegadosException;

	/**
	 * Permite consultar la información de una plancha por medio de un
	 * consecutivo
	 * 
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
	 *         Premize SAS <br>
	 * @date 2/12/2012
	 * @param consecutivoPlancha
	 * @return
	 * @throws EleccionesDelegadosException
	 */
	DTOInformacionPlancha consultarInformacionPlancha(Long consecutivoPlancha)
			throws EleccionesDelegadosException;

	public List<DTOPlanchaAsociado> asociadoPertenceOtraPlancha(
			String nroIdentificacion, Long consecutivoPlancha)
			throws EleccionesDelegadosException;

	public DTOInformacionPlancha registrarPlancha(
			List<DTOMiembroPlancha> miembrosTitulares,
			List<DTOMiembroPlancha> miembrosSuplentes,
			Long numDocUsuarioRegistra, boolean aplicaValidaciones)
			throws EleccionesDelegadosException;

	public String modificarMiembrosPlanchas(List<DTOMiembroPlancha> miembros,
			String tipoInscrito, Long consecutivoPlancha, boolean esModificable)
			throws EleccionesDelegadosException;

	public DTOInformacionPlancha finalizarEnviarPlancha(
			List<DTOMiembroPlancha> miembrosTitulares,
			List<DTOMiembroPlancha> miembrosSuplentes, Long consecutivoPlancha,
			Long numDocUsuarioRegistra, boolean aplicaValidaciones,
			boolean admiteSuplentes) throws EleccionesDelegadosException;

	public String obtenerHtmlPlanchasAdmitidasPorRegional(Long codigoZonaElect,
			String idUsuario, int indicador)
			throws EleccionesDelegadosException;
	
	public String obtenerHtmlPlanchasAdmitidasPorZonaEspec(Long codigoZonaElect,
			String idUsuario, int indicador)
			throws EleccionesDelegadosException;

	public void generarNumeracionPlanchas(Long codZonaElectoral,
			String idUsuario, boolean esZonaEspecial) throws EleccionesDelegadosException;

	public boolean actualizarEstadoPlancha(Long consecPlancha, String estado)
			throws EleccionesDelegadosException, Exception;

	public boolean esColaboradorGECoomeva(String identificacionMiembro);

	public void generarNumeracionPlanchasRecurso(Long codZonaElectoral,
			String idUsuario, boolean esZonaEspecial) throws EleccionesDelegadosException;

	String validarEmpleadoFechaRetiro(Long numeroDocumento)
			throws EleccionesDelegadosException;

	public DTOZonaElectoral consultarZonaElectoralAsociado(
			String nroIdentificacion) throws EleccionesDelegadosException;
	
	public boolean perteneceInstitucionesElectoralesGECoomeva(
			String identificacionMiembro);
	
	public boolean actualizarFechaEnvio(Long consecPlancha)
	throws EleccionesDelegadosException, Exception;
	
	public void asignarEstadoRegistradoPlancha(Long consecutivoPlancha)
	throws EleccionesDelegadosException;
}
