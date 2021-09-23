package co.com.coomeva.ele.delegado.habilidad;

import co.com.coomeva.ele.logica.habilidad.ILogicaProcesoVerificacionHabilidad;
import co.com.coomeva.ele.logica.habilidad.LogicaProcesoVerificacionHabilidad;
import co.com.coomeva.ele.modelo.habilidad.DTOProcesoVerificacionHabilidad;

public class DelegadoProcesoVerificacionHabilidad {
	
	private static DelegadoProcesoVerificacionHabilidad instance;
	private ILogicaProcesoVerificacionHabilidad iLogicaProcesoVerificacionHabilidad; 
	
	private DelegadoProcesoVerificacionHabilidad(){
	}
	
	public static DelegadoProcesoVerificacionHabilidad getInstance(){
		if(instance == null){
			instance = new DelegadoProcesoVerificacionHabilidad();
		}
		return instance;
	}
	
	public DTOProcesoVerificacionHabilidad obtenerProcesoVerificacionActivo(){
		this.iLogicaProcesoVerificacionHabilidad = new LogicaProcesoVerificacionHabilidad();
		try {
			return iLogicaProcesoVerificacionHabilidad.obtenerProcesoVerificacionActivo();
		} finally {
			this.iLogicaProcesoVerificacionHabilidad = null;
		}
	}
	
	public void ejecutarProcesoVerificacion(DTOProcesoVerificacionHabilidad dtoProcesoVerificacionHabilidad, String nombreUsuario){
		this.iLogicaProcesoVerificacionHabilidad = new LogicaProcesoVerificacionHabilidad();
		try {
			iLogicaProcesoVerificacionHabilidad.ejecutarProcesoVerificacion(dtoProcesoVerificacionHabilidad, nombreUsuario);
		} finally {
			this.iLogicaProcesoVerificacionHabilidad = null;
		}
	}

}
