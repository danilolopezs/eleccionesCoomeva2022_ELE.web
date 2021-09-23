package co.com.coomeva.ele.delegado.inscripcion.plancha;

import co.com.coomeva.ele.dto.InfoDetalleFormatoPlanchaDTO;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.logica.inscripcion.plancha.ILogicaFormatoPlancha;
import co.com.coomeva.ele.logica.inscripcion.plancha.LogicaFormatoPlancha;

public class DelegadoFormatoPlancha {
	
	private static DelegadoFormatoPlancha instance;
	private static ILogicaFormatoPlancha logicaFormatoPlancha;
	
	private DelegadoFormatoPlancha(){
	}
	
	public static DelegadoFormatoPlancha getInstance(){
		if(instance == null){
			instance = new DelegadoFormatoPlancha();
		}
		return instance;
	}
	
	public InfoDetalleFormatoPlanchaDTO guardarInformacionResolucionRechazo(
			InfoDetalleFormatoPlanchaDTO dto, String userId)
			throws EleccionesDelegadosException{
		try{
			logicaFormatoPlancha = new LogicaFormatoPlancha();
			return logicaFormatoPlancha.guardarInformacionResolucionRechazo(dto, userId);
		}finally{
			logicaFormatoPlancha = null;
		}
	}
	
	public void registrarFormatoPlancha(String usuario, Long codigoFormato,
			Long consecutivoPlancha) throws Exception {
		try{
			logicaFormatoPlancha = new LogicaFormatoPlancha();
			logicaFormatoPlancha.registrarFormatoPlancha(usuario, codigoFormato, consecutivoPlancha);
		}finally{
			logicaFormatoPlancha = null;
		}
	}
}
