package co.com.coomeva.ele.delegado;

import java.util.Date;
import java.util.List;

import co.com.coomeva.ele.entidades.habilidad.EleSuspendido;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleAsocOtrasEntElect;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleAsocSancionados5Annos;
import co.com.coomeva.ele.logica.LogicaOtrasEntElec;
import co.com.coomeva.ele.logica.LogicaSancionadosAnnos;
import co.com.coomeva.ele.logica.LogicaSuspendido;
import co.com.coomeva.ele.modelo.EleAsocOtrasEntElectDTO;
import co.com.coomeva.ele.modelo.EleAsocSancionados5AnnosDTO;
import co.com.coomeva.ele.modelo.SuspendidoDTO;

public class DelegadoEleAsocSancionado {
	private static DelegadoEleAsocSancionado instance;
	private static LogicaSancionadosAnnos logicaSancionadosAnnos;


	private DelegadoEleAsocSancionado() {
	}


	public static DelegadoEleAsocSancionado getInstance() {
		if (instance == null) {
			instance = new DelegadoEleAsocSancionado();
			logicaSancionadosAnnos = LogicaSancionadosAnnos.getInstance();
		}
		return instance;
	}

	public void crearSancionados(Long codigoAsociado, Date fechaRegistro, String usuario, Date fechaSuspension, String motivo) throws Exception{

		logicaSancionadosAnnos.crearSancionados(codigoAsociado, fechaRegistro, usuario, fechaSuspension, motivo);
	}
	
	public List<EleAsocSancionados5Annos> obtenerSancionados(){
		return logicaSancionadosAnnos.findAll();
	}
	
	public void eliminarSancionados(){
		logicaSancionadosAnnos.eliminarSancionados();
	}
	
	public List<EleAsocSancionados5AnnosDTO> consultarSancionados(String sortColumnName, boolean sortAscending, 
			int startRow, int maxResults) throws Exception{
		return logicaSancionadosAnnos.consultarSancionados(sortColumnName, sortAscending, startRow, maxResults);
	}
	
}