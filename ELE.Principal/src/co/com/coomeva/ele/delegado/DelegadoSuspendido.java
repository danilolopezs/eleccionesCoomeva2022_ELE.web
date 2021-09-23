package co.com.coomeva.ele.delegado;

import java.util.Date;
import java.util.List;

import co.com.coomeva.ele.entidades.habilidad.EleSuspendido;
import co.com.coomeva.ele.logica.LogicaSuspendido;
import co.com.coomeva.ele.modelo.SuspendidoDTO;

public class DelegadoSuspendido {
	private static DelegadoSuspendido instance;
	private static LogicaSuspendido logicaSuspendido;


	private DelegadoSuspendido() {
	}


	public static DelegadoSuspendido getInstance() {
		if (instance == null) {
			instance = new DelegadoSuspendido();
			logicaSuspendido =  LogicaSuspendido.getInstance();
		}
		return instance;
	}

/*	public String findPlanchasEstadoNative(String nroPlancha) throws Exception {
		return logicaPlanchas.findPlanchasEstadoNative(nroPlancha);
	}

*/
	public void crearSuspendido(Long codigoAsociado, String motivo, Date fechaSuspencion, Date fechaRegistro, String usuario) throws Exception{
		
		logicaSuspendido.crearSuspendido(codigoAsociado, motivo, fechaSuspencion, fechaRegistro, usuario);

	}
	
	public List<EleSuspendido> obtenerSuspendidos(){
		return logicaSuspendido.findAll();
	}
	
	public void eliminarSuspendidos(){
		logicaSuspendido.eliminarSuspendidos();
	}
	
	public List<SuspendidoDTO> consultarSuspendidosPag(String sortColumnName, boolean sortAscending, 
			int startRow, int maxResults) throws Exception{
		return logicaSuspendido.consultarSuspendidosPag(sortColumnName, sortAscending, startRow, maxResults);
	}
	
}