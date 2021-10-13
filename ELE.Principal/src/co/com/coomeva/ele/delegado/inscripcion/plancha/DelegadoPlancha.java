package co.com.coomeva.ele.delegado.inscripcion.plancha;

import java.util.List;

import co.com.coomeva.ele.dto.DTOInformacionPlancha;
import co.com.coomeva.ele.dto.DTOMiembroPlancha;
import co.com.coomeva.ele.dto.DTOPlanchaAsociado;
import co.com.coomeva.ele.dto.DTOZonaElectoral;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.logica.inscripcion.plancha.ILogicaPlancha;
import co.com.coomeva.ele.logica.inscripcion.plancha.LogicaPlancha;

/**
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
 *         Premize SAS <br>
 * @project ELE.Principal
 * @class DelegadoPlancha
 * @date 1/12/2012
 */
public class DelegadoPlancha {

	private static DelegadoPlancha instance;
	private ILogicaPlancha logicaPlancha;

	private DelegadoPlancha() {
	}

	/**
	 * singleton pattern
	 * 
	 * @return
	 */
	public static DelegadoPlancha getInstance() {
		if (instance == null) {
			instance = new DelegadoPlancha();
		}
		return instance;
	}

	public List<DTOMiembroPlancha> adicionarMiembroPlancha(List<DTOMiembroPlancha> miembros, Long numeroDocumento,
			int posicionPlancha, Long consecutivoPlancha, boolean aplicaValidaciones)
			throws EleccionesDelegadosException {
		try {
			logicaPlancha = new LogicaPlancha();
			return logicaPlancha.adicionarMiembroPlancha(miembros, numeroDocumento, posicionPlancha, consecutivoPlancha,
					aplicaValidaciones);
		} finally {
			logicaPlancha = null;
		}
	}

	public DTOInformacionPlancha consultarInformacionPlancha(Long consecutivoPlancha)
			throws EleccionesDelegadosException {
		try {
			logicaPlancha = new LogicaPlancha();
			return logicaPlancha.consultarInformacionPlancha(consecutivoPlancha);
		} finally {
			logicaPlancha = null;
		}

	}

	public List<DTOPlanchaAsociado> asociadoPertenceOtraPlancha(Long numeroDocumento, Long consecutivoPlancha)
			throws EleccionesDelegadosException {
		try {
			logicaPlancha = new LogicaPlancha();
			return logicaPlancha.asociadoPertenceOtraPlancha(numeroDocumento.toString(), consecutivoPlancha);
		} finally {
			logicaPlancha = null;
		}
	}

	public DTOInformacionPlancha registrarPlancha(List<DTOMiembroPlancha> miembrosTitulares,
			List<DTOMiembroPlancha> miembrosSuplentes, Long numeroDocumento, boolean aplicaValidaciones)
			throws EleccionesDelegadosException {
		try {
			logicaPlancha = new LogicaPlancha();
			return logicaPlancha.registrarPlancha(miembrosTitulares, miembrosSuplentes, numeroDocumento,
					aplicaValidaciones);
		} finally {
			logicaPlancha = null;
		}
	}

	public String modificarPlancha(List<DTOMiembroPlancha> miembros, String tipoInscrito, Long consecutivoPlancha,
			boolean esModificable) throws EleccionesDelegadosException {
		try {
			logicaPlancha = new LogicaPlancha();
			return logicaPlancha.modificarMiembrosPlanchas(miembros, tipoInscrito, consecutivoPlancha, esModificable);
		} finally {
			logicaPlancha = null;
		}
	}

	public void asignarEstadoRegistradoPlancha(Long consecutivoPlancha) throws EleccionesDelegadosException {
		try {
			logicaPlancha = new LogicaPlancha();
			logicaPlancha.asignarEstadoRegistradoPlancha(consecutivoPlancha);
		} finally {
			logicaPlancha = null;
		}
	}

	public DTOInformacionPlancha finalizarEnviarPlancha(List<DTOMiembroPlancha> miembrosTitulares,
			List<DTOMiembroPlancha> miembrosSuplentes, Long consecutivoPlancha, Long numDocUsuarioRegistra,
			boolean aplicaValidaciones, boolean admiteSuplentes) throws EleccionesDelegadosException {
		try {
			logicaPlancha = new LogicaPlancha();
			return logicaPlancha.finalizarEnviarPlancha(miembrosTitulares, miembrosSuplentes, consecutivoPlancha,
					numDocUsuarioRegistra, aplicaValidaciones, admiteSuplentes);
		} finally {
			logicaPlancha = null;
		}
	}

	public String obtenerHtmlPlanchasAdmitidasPorRegional(Long codigoZonaElect, String idUsuario, int indicador)
			throws EleccionesDelegadosException {
		try {
			logicaPlancha = new LogicaPlancha();
			return logicaPlancha.obtenerHtmlPlanchasAdmitidasPorRegional(codigoZonaElect, idUsuario, indicador);
		} finally {
			logicaPlancha = null;
		}
	}

	public String obtenerHtmlPlanchasAdmitidasPorZonaEspec(Long codigoZonaElect, String idUsuario, int indicador)
			throws EleccionesDelegadosException {
		try {
			logicaPlancha = new LogicaPlancha();
			return logicaPlancha.obtenerHtmlPlanchasAdmitidasPorZonaEspec(codigoZonaElect, idUsuario, indicador);
		} finally {
			logicaPlancha = null;
		}
	}

	public void generarNumeracionPlanchas(Long codZonaElectoral, String idUsuario, boolean esZonaEspecial)
			throws EleccionesDelegadosException {
		try {
			logicaPlancha = new LogicaPlancha();
			logicaPlancha.generarNumeracionPlanchas(codZonaElectoral, idUsuario, esZonaEspecial);
		} finally {
			logicaPlancha = null;
		}
	}

	public void generarNumeracionPlanchasRecurso(Long codZonaElectoral, String idUsuario, boolean esZonaEspecial)
			throws EleccionesDelegadosException {
		try {
			logicaPlancha = new LogicaPlancha();
			logicaPlancha.generarNumeracionPlanchasRecurso(codZonaElectoral, idUsuario, esZonaEspecial);
		} finally {
			logicaPlancha = null;
		}
	}

	public boolean actualizarEstadoPlancha(Long consecPlancha, String estado)
			throws EleccionesDelegadosException, Exception {

		try {
			logicaPlancha = new LogicaPlancha();
			return logicaPlancha.actualizarEstadoPlancha(consecPlancha, estado);
		} finally {
			logicaPlancha = null;
		}
	}

	public boolean actualizarFechaEnvio(Long consecPlancha) throws EleccionesDelegadosException, Exception {
		try {
			logicaPlancha = new LogicaPlancha();
			return logicaPlancha.actualizarFechaEnvio(consecPlancha);
		} finally {
			logicaPlancha = null;
		}
	}

	public DTOZonaElectoral consultarZonaElectoralAsociado(String nroIdentificacion)
			throws EleccionesDelegadosException {
		try {
			logicaPlancha = new LogicaPlancha();
			return logicaPlancha.consultarZonaElectoralAsociado(nroIdentificacion);
		} finally {
			logicaPlancha = null;
		}
	}

	public boolean esColaboradorGECoomeva(String identificacionMiembro) {
		try {
			logicaPlancha = new LogicaPlancha();
			return logicaPlancha.esColaboradorGECoomeva(identificacionMiembro);
		} finally {
			logicaPlancha = null;
		}
	}

	public boolean perteneceInstitucionesElectoralesGECoomeva(String identificacionMiembro) {
		logicaPlancha = new LogicaPlancha();
		return logicaPlancha.perteneceInstitucionesElectoralesGECoomeva(identificacionMiembro);
	}
}
