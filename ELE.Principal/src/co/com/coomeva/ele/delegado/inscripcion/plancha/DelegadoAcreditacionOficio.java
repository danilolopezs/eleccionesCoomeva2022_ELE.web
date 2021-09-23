package co.com.coomeva.ele.delegado.inscripcion.plancha;

import co.com.coomeva.ele.exception.EleccionesDelegadosException;
import co.com.coomeva.ele.logica.inscripcion.plancha.ILogicaAcreditacionOficio;
import co.com.coomeva.ele.logica.inscripcion.plancha.LogicaAcreditacionOficio;

/**
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
 * @project ELE.Principal
 * @class DelegadoAcreditacionOficio
 * @date 4/01/2013
 */

public class DelegadoAcreditacionOficio {
	
	private ILogicaAcreditacionOficio iLogicaAcreditacionOficio;
	
	private static DelegadoAcreditacionOficio instance;
	
	private DelegadoAcreditacionOficio(){
	}
	
	public static final DelegadoAcreditacionOficio getInstance(){
		if(instance == null){
			instance = new DelegadoAcreditacionOficio();
		}
		return instance;
	}
	
	public void registrarInfoAcreditacionOficio(Long numintAsociado)
	throws EleccionesDelegadosException{
		try{
			iLogicaAcreditacionOficio = new LogicaAcreditacionOficio();
			iLogicaAcreditacionOficio.registrarInfoAcreditacionOficio(numintAsociado);
		} finally{
			iLogicaAcreditacionOficio = null;
		}
	}
}
