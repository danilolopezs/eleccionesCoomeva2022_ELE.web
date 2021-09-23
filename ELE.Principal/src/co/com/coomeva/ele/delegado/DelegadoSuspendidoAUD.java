package co.com.coomeva.ele.delegado;

import java.util.Date;
import java.util.List;

import co.com.coomeva.ele.entidades.habilidad.EleSuspendido;
import co.com.coomeva.ele.entidades.planchas.EleCabPlancha;
import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral;
import co.com.coomeva.ele.entidades.planchas.ElePlanchas;
import co.com.coomeva.ele.entidades.planchas.ElePrincipales;
import co.com.coomeva.ele.entidades.planchas.EleSuplentes;
import co.com.coomeva.ele.logica.LogicaPlanchas;
import co.com.coomeva.ele.logica.LogicaSuspendido;
import co.com.coomeva.ele.logica.LogicaSuspendidoAUD;
import co.com.coomeva.ele.modelo.EleCabPlanchaDTO;
import co.com.coomeva.ele.modelo.EleLogDTO;
import co.com.coomeva.ele.modelo.ElePlanchaDTO;
import co.com.coomeva.ele.modelo.ElePrincipalesDTO;
import co.com.coomeva.ele.modelo.EleSuplentesDTO;
import co.com.coomeva.ele.modelo.EleTablaEmpresaCargoDTO;

public class DelegadoSuspendidoAUD {
	private static DelegadoSuspendidoAUD instance;
	private static LogicaSuspendidoAUD logicaSuspendidoAUD;


	private DelegadoSuspendidoAUD() {
	}


	public static DelegadoSuspendidoAUD getInstance() {
		if (instance == null) {
			instance = new DelegadoSuspendidoAUD();
			logicaSuspendidoAUD =  LogicaSuspendidoAUD.getInstance();
		}
		return instance;
	}


	public void crearSuspendidoAud(Long codigoAsociado, String motivo, Date fechaSuspencion, Date fechaRegistro, Date fechaRegistroAUD, String usuario) throws Exception{
		
		logicaSuspendidoAUD.crearSuspendidoAUD(codigoAsociado, motivo, fechaSuspencion, fechaRegistro, fechaRegistroAUD, usuario);

	}
	
	
}