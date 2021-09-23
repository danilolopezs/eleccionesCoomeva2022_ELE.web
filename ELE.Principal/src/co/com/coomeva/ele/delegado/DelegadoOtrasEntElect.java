package co.com.coomeva.ele.delegado;

import java.util.Date;
import java.util.List;

import co.com.coomeva.ele.entidades.habilidad.EleSuspendido;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleAsocOtrasEntElect;
import co.com.coomeva.ele.logica.LogicaOtrasEntElec;
import co.com.coomeva.ele.logica.LogicaSuspendido;
import co.com.coomeva.ele.modelo.EleAsocOtrasEntElectDTO;
import co.com.coomeva.ele.modelo.SuspendidoDTO;

public class DelegadoOtrasEntElect {
	private static DelegadoOtrasEntElect instance;
	private static LogicaOtrasEntElec logicaOtrasEntElect;


	private DelegadoOtrasEntElect() {
	}


	public static DelegadoOtrasEntElect getInstance() {
		if (instance == null) {
			instance = new DelegadoOtrasEntElect();
			logicaOtrasEntElect =  LogicaOtrasEntElec.getInstance();
		}
		return instance;
	}

/*	public String findPlanchasEstadoNative(String nroPlancha) throws Exception {
		return logicaPlanchas.findPlanchasEstadoNative(nroPlancha);
	}

*/
	public void crearOtrasEntidades(Long codigoAsociado, String tipoEnt, Date fechaRegistro, String usuario) throws Exception{
		
		logicaOtrasEntElect.crearOtrasEntElec(codigoAsociado, tipoEnt, fechaRegistro, usuario);

	}
	
	public List<EleAsocOtrasEntElect> obtenerOtrasEntidades(){
		return logicaOtrasEntElect.findAll();
	}
	
	public void eliminarOtrasEntidades(){
		logicaOtrasEntElect.eliminarOtrasEntElect();
	}
	
	public List<EleAsocOtrasEntElectDTO> consultarOtrasEntidadesPag(String sortColumnName, boolean sortAscending, 
			int startRow, int maxResults) throws Exception{
		return logicaOtrasEntElect.consultarOtrasEntElecPag(sortColumnName, sortAscending, startRow, maxResults);
	}
	
	public Long obtieneNumintAsociado(Long documento){
		return logicaOtrasEntElect.obtieneNumintAsociado(documento);
	}
	
}