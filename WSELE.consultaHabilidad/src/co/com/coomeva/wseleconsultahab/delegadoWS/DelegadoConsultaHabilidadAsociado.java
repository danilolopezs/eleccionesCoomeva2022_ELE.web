package co.com.coomeva.wseleconsultahab.delegadoWS;


import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.dto.DTOHabilidadAsociado;
import co.com.coomeva.wseleconsultahab.util.ConstantesServicio;

public class DelegadoConsultaHabilidadAsociado {
	
	public int consultarHabilidadAsociado(long numeroDocumento){
		int valor = 0;
		try {
			if (DelegadoAsociado.getInstance().esAsociado(new Long(numeroDocumento))) {
				if (DelegadoAsociado.getInstance().estaAsociaActivo(new Long(numeroDocumento))) {
					DTOHabilidadAsociado dto = DelegadoAsociado.getInstance().consultarHabilidadAsociado(new Long(numeroDocumento));
					if (dto.getAsociadoHabil().booleanValue()) {
						valor = ConstantesServicio.VALOR_ASOCIADO_HABIL;
					}else{
						valor = ConstantesServicio.VALOR_ASOCIADO_INHABIL;
					}
				}else{
					valor = ConstantesServicio.VALOR_ASOCIADO_INACTIVO;
				}
			}else{
				valor = ConstantesServicio.VALOR_ASOCIADO_INEXISTENTE;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valor;
	}

}
