package co.com.coomeva.ele.delegado.inscripcion.plancha;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import co.com.coomeva.ele.dto.DTOInformacionPlancha;
import co.com.coomeva.ele.dto.DTOMiembroPlancha;
import co.com.coomeva.ele.dto.DTOPlanchaAsociado;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.planchas.dao.EleEstadoPlanchaDAO;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleEstadoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlancha;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.logica.LogicaEstadoPlancha;
import co.com.coomeva.ele.logica.inscripcion.plancha.ILogicaPlancha;
import co.com.coomeva.ele.logica.inscripcion.plancha.LogicaPlancha;
import co.com.coomeva.ele.utilidades.GeneradorConsecutivos;

/**
 * @author Mario Alejandro Acevedo <br>
 * @project ELE.Principal
 * @class DelegadoPlancha
 */
public class DelegadoEstadoPlancha {

	private static DelegadoEstadoPlancha instance;
	private static LogicaEstadoPlancha logicaEstadoPlancha;

	private DelegadoEstadoPlancha() {
	}

	public static DelegadoEstadoPlancha getInstance() {
		if (instance == null) {
			instance = new DelegadoEstadoPlancha();
			logicaEstadoPlancha = LogicaEstadoPlancha.getInstance();
		}
		return instance;
	}

	public void crearEstadoPlancha(String usuario, Date fechaRegistro, String estado, Long consecPlancha){
		logicaEstadoPlancha.crearEstadoPlancha(usuario, fechaRegistro, estado, consecPlancha);
	
	}
	
}
