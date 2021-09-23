package co.com.coomeva.ele.delegado;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.coomeva.ele.entidades.planchas.EleCabPlancha;
import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral;
import co.com.coomeva.ele.entidades.planchas.ElePlanchas;
import co.com.coomeva.ele.entidades.planchas.ElePrincipales;
import co.com.coomeva.ele.entidades.planchas.EleSuplentes;
import co.com.coomeva.ele.logica.LogicaFormatoPlanchas;
import co.com.coomeva.ele.logica.LogicaPlanchas;
import co.com.coomeva.ele.logica.inscripcion.plancha.LogicaFormatoPlancha;
import co.com.coomeva.ele.modelo.CabezaPlanchaDTO;
import co.com.coomeva.ele.modelo.EleCabPlanchaDTO;
import co.com.coomeva.ele.modelo.EleLogDTO;
import co.com.coomeva.ele.modelo.ElePlanchaDTO;
import co.com.coomeva.ele.modelo.ElePrincipalesDTO;
import co.com.coomeva.ele.modelo.EleSuplentesDTO;
import co.com.coomeva.ele.modelo.EleTablaEmpresaCargoDTO;
import co.com.coomeva.ele.modelo.InfoPlanchaAdmisionPdfDTO;
import co.com.coomeva.ele.modelo.InfoPlanchaConstanciaPdfDTO;
import co.com.coomeva.ele.modelo.InfoPlanchaDTO;
import co.com.coomeva.ele.modelo.ParametroPlanchaDTO;
import co.com.coomeva.ele.modelo.PlanchaPorEstadoDTO;
import co.com.coomeva.ele.modelo.SuspendidoDTO;

public class DelegadoFormatoPlanchas {
	private static DelegadoFormatoPlanchas instance;
	private static LogicaFormatoPlanchas logicaFormatoPlanchas;

	private DelegadoFormatoPlanchas() {
	}

	public static DelegadoFormatoPlanchas getInstance() {
		if (instance == null) {
			instance = new DelegadoFormatoPlanchas();
			logicaFormatoPlanchas = LogicaFormatoPlanchas.getInstance();
		}
		return instance;
	}

	/**
	 * Metodo que guarda un registro en la tabla ELE_FORMATO_PLANCHA
	 * 
	 * @author Mario Alejandro Acevedo
	 * @param usuarioImprime
	 * @param fechaImprime
	 * @param codFormato
	 * @param consecPlancha
	 */
	public void crearFormatoPlancha(String usuarioImprime,
			Timestamp fechaImprime, Long codFormato, Long consecPlancha)
			throws Exception {
		logicaFormatoPlanchas.crearFormatoPlancha(usuarioImprime, fechaImprime,
				codFormato, consecPlancha);

	}

	public boolean esPlanchaRadicada(Long consecutivoPlancha) throws Exception {
		return logicaFormatoPlanchas.esPlanchaRadicada(consecutivoPlancha);
	}

}