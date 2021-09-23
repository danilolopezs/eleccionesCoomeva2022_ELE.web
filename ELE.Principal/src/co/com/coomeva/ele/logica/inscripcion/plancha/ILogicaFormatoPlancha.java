package co.com.coomeva.ele.logica.inscripcion.plancha;

import co.com.coomeva.ele.dto.InfoDetalleFormatoPlanchaDTO;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFormatoPlancha;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;

public interface ILogicaFormatoPlancha {
	
	public InfoDetalleFormatoPlanchaDTO guardarInformacionResolucionRechazo(
			InfoDetalleFormatoPlanchaDTO dto, String userId)
			throws EleccionesDelegadosException;
	
	public void guardarFormatoPlancha(EleFormatoPlancha formatoPlancha)throws Exception;
	
	public Long obtenerConsecutivoFormatoPlancha()throws Exception;
	
	public void registrarFormatoPlancha(String usuario, Long codigoFormato,
			Long consecutivoPlancha) throws Exception;

}
