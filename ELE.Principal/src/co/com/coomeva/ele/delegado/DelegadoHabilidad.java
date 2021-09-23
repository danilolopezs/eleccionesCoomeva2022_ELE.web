package co.com.coomeva.ele.delegado;

import java.util.List;

import co.com.coomeva.ele.entidades.planchas.EleInhabilidades;
import co.com.coomeva.ele.entidades.planchas.EleZonas;
import co.com.coomeva.ele.logica.LogicaHabilidad;
import co.com.coomeva.ele.logica.LogicaInformeResumen;
import co.com.coomeva.ele.modelo.EleAsociadoDTO;
import co.com.coomeva.ele.modelo.ElePlanchaDTO;
import co.com.coomeva.ele.modelo.ResumenHabilidadDTO;

public class DelegadoHabilidad {
	private static DelegadoHabilidad instance;
	private static LogicaHabilidad logicaHabilidad = null;
	private static LogicaInformeResumen logicaInformeResumen = null;

//	Constructor de la clase
	private DelegadoHabilidad() {
	}

//	Patròn Singular
	public static DelegadoHabilidad getInstance() {
		if (instance == null) {
			instance = new DelegadoHabilidad();
			logicaHabilidad = LogicaHabilidad.getInstance();
			logicaInformeResumen = LogicaInformeResumen.getInstance();
		}
		return instance;
	}
	
	public ElePlanchaDTO validatePlancha(ElePlanchaDTO plancha) throws Exception{
		return logicaHabilidad.validatePlancha(plancha);
	}
	

	public EleAsociadoDTO validateAsociadoDTO(String nroIdentificacion, EleZonas eleZona,String nroCabPlancha)
			throws Exception {
		return logicaHabilidad.validateAsociadoDTO(nroIdentificacion,eleZona,nroCabPlancha);
	}

	public void inscribirInhabilidades(List<EleInhabilidades> listInhabilidades) {
		logicaHabilidad.inscribirInhabilidades(listInhabilidades);
	}

	public void removerInhabilidades(String nroIdentificacion) {
		logicaHabilidad.removerInhabilidades(nroIdentificacion);
	}

	public List<EleInhabilidades> buscarInhabilidadesById(
			String nroIdentificacion) throws Exception{
		return logicaHabilidad.buscarInhabilidadesById(nroIdentificacion);
	}

	public boolean validateEmpleado(String nroCabIdentificacion, EleZonas elZona)
			throws Exception {
		return logicaHabilidad.validateEmpleado(nroCabIdentificacion, elZona);
	}
	
	/**
	 * Metodo que consulta la informacion para el reporte del resumen de habilidades
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 26/11/2012
	 * @return
	 * @throws Exception
	 */
	public List<ResumenHabilidadDTO> consultarInformacionResumenHabilidad() throws Exception{
		return logicaInformeResumen.consultarInformacionResumenHabilidad();
	}
	
	
	/**
	 * Indica si el número de identificación ingresado
	 * corresponde a una persona que labora en el grupo empresarial
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
	 * @date 1/12/2012
	 * @param nroCabIdentificacion
	 * @return boolean
	 * @throws Exception
	 */
	public boolean esEmpleadoGECoomeva(String nroCabIdentificacion)
			throws Exception {
		return logicaHabilidad.esEmpleadoGECoomeva(nroCabIdentificacion);
	}
	
	
}