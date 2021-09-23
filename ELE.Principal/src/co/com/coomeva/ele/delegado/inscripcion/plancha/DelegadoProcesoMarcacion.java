package co.com.coomeva.ele.delegado.inscripcion.plancha;

import java.util.List;

import co.com.coomeva.ele.logica.inscripcion.plancha.DTOAsociadoMarcado;
import co.com.coomeva.ele.logica.inscripcion.plancha.ILogicaProcesoMarcacion;
import co.com.coomeva.ele.logica.inscripcion.plancha.LogicaProcesoMarcacion;

public class DelegadoProcesoMarcacion {
	
	private static DelegadoProcesoMarcacion instance;
	private ILogicaProcesoMarcacion iLogicaProcesoMarcacion;
	
	public static DelegadoProcesoMarcacion getInstance(){
		if(instance == null){
			instance = new DelegadoProcesoMarcacion();
		}
		return instance;
	}
	
	public List<DTOAsociadoMarcado> generarReporteAsociadosMarcados(){
		try{
			iLogicaProcesoMarcacion = new LogicaProcesoMarcacion();
			return iLogicaProcesoMarcacion.generarReporteAsociadosMarcados();
		}finally{
			iLogicaProcesoMarcacion = null;
		}
	}
	
	 public void marcarAsociadosConViolaciones(){
		 try{
				iLogicaProcesoMarcacion = new LogicaProcesoMarcacion();
				iLogicaProcesoMarcacion.marcarAsociadosConViolaciones();
			}finally{
				iLogicaProcesoMarcacion = null;
			}
	 }

}
