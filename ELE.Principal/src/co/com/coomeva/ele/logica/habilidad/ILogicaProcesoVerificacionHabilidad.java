package co.com.coomeva.ele.logica.habilidad;

import co.com.coomeva.ele.modelo.habilidad.DTOProcesoVerificacionHabilidad;

public interface ILogicaProcesoVerificacionHabilidad {
	public DTOProcesoVerificacionHabilidad obtenerProcesoVerificacionActivo();
	public void ejecutarProcesoVerificacion(DTOProcesoVerificacionHabilidad dtoProcesoVerificacionHabilidad, String nombreUsuario);
}
