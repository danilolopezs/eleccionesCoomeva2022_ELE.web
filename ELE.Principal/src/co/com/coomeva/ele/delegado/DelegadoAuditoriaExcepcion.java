package co.com.coomeva.ele.delegado;

import java.util.Date;
import java.util.List;

import co.com.coomeva.ele.entidades.habilidad.EleSuspendido;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleAsocOtrasEntElect;
import co.com.coomeva.ele.logica.LogicaAuditoriaExcepcion;
import co.com.coomeva.ele.logica.LogicaOtrasEntElec;
import co.com.coomeva.ele.logica.LogicaSuspendido;
import co.com.coomeva.ele.modelo.EleAsocOtrasEntElectDTO;
import co.com.coomeva.ele.modelo.SuspendidoDTO;

public class DelegadoAuditoriaExcepcion {
	private static DelegadoAuditoriaExcepcion instance;
	private static LogicaAuditoriaExcepcion logicaAuditoriaExcepcion;

	private DelegadoAuditoriaExcepcion() {
	}

	public static DelegadoAuditoriaExcepcion getInstance() {
		if (instance == null) {
			instance = new DelegadoAuditoriaExcepcion();
			logicaAuditoriaExcepcion =  LogicaAuditoriaExcepcion.getInstance();
		}
		return instance;
	}
	
	public void crearAuditoriaExcepcion(Long numeroDocumento, String usuario, Date fechaSuspension, String motivo, String tipoExcepcion, Date fechaRegistro) throws Exception{
		logicaAuditoriaExcepcion.crearAuditoriaExcepcion(numeroDocumento, usuario, fechaSuspension, motivo, tipoExcepcion, fechaRegistro);
	}

}