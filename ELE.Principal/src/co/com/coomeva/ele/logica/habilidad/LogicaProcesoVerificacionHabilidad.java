package co.com.coomeva.ele.logica.habilidad;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import co.com.coomeva.ele.entidades.habilidad.EleProceso;
import co.com.coomeva.ele.entidades.habilidad.EleProcesoRegla;
import co.com.coomeva.ele.entidades.habilidad.EleRegla;
import co.com.coomeva.ele.entidades.habilidad.dao.EleProcesoDAO;
import co.com.coomeva.ele.modelo.habilidad.DTOProcesoVerificacionHabilidad;
import co.com.coomeva.ele.modelo.habilidad.DTOReglaVerificacionHabilidad;
import co.com.coomeva.habilidad.procesos.IProcesoVerificacionHabilidadAsociados;
import co.com.coomeva.habilidad.procesos.ProcesoVerificacionHabilidadAsociados;

public class LogicaProcesoVerificacionHabilidad implements
		ILogicaProcesoVerificacionHabilidad {
	
	private final static String ESTADO_ACTIVO = "1";
	
	private EleProcesoDAO eleProcesoDAO;
	private IProcesoVerificacionHabilidadAsociados procesoVerificacionHabilidad;

	public DTOProcesoVerificacionHabilidad obtenerProcesoVerificacionActivo() {
		
		eleProcesoDAO = new EleProcesoDAO();
		EleProceso instance = new EleProceso();
		instance.setEstadoProceso(ESTADO_ACTIVO);
		
		try {
			instance = eleProcesoDAO.findByExample(instance).get(0);
		} catch (Exception e) {
			instance = new EleProceso();
		}
		
		DTOProcesoVerificacionHabilidad dto = new DTOProcesoVerificacionHabilidad();
		dto.setCodigoProceso(instance.getCodigoProceso());
		dto.setEstadoProceso(instance.getEstadoProceso());
		dto.setFechaProgramacion(instance.getFechaProgramacion());
		
		
		List<DTOReglaVerificacionHabilidad> listaRules = new ArrayList<DTOReglaVerificacionHabilidad>();
		for (Iterator iterator = instance.getEleProcesoReglas().iterator(); iterator.hasNext();) {
			
			EleProcesoRegla eleProcesoRegla = (EleProcesoRegla) iterator.next();

			if(ESTADO_ACTIVO.equals(eleProcesoRegla.getEstadoProgramacion())){
				DTOReglaVerificacionHabilidad rule = new DTOReglaVerificacionHabilidad();
				rule.setCodigoRegla(eleProcesoRegla.getEleRegla().getCodigoRegla());
				rule.setDescripcionRegla(eleProcesoRegla.getEleRegla().getDescripcionRegla());
				rule.setNombreReglaJava(eleProcesoRegla.getEleRegla().getNombreReglaJava());
				rule.setObservacionViolacionRegla(eleProcesoRegla.getEleRegla().getObsViolacionRegla());
				rule.setConsecutivoProRegla(eleProcesoRegla.getConsecutivoProRegla());
				rule.setFechaInicioEjecucion(eleProcesoRegla.getFechaInicioEjecucion());
				listaRules.add(rule);
			}
		}
		dto.setListaReglas(listaRules);
		return  dto;
	}
	
	public void ejecutarProcesoVerificacion(DTOProcesoVerificacionHabilidad dtoProcesoVerificacionHabilidad, String nombreUsuario){
		
		EleProceso eleProceso = new EleProceso();
		eleProceso.setCodigoProceso(dtoProcesoVerificacionHabilidad.getCodigoProceso());
		eleProceso.setEstadoProceso(ESTADO_ACTIVO);
		eleProceso.setFechaProgramacion(dtoProcesoVerificacionHabilidad.getFechaProgramacion());
		
		Set<EleProcesoRegla> conjuntoEleProcesoRegla = new HashSet<EleProcesoRegla>();
		
		for (Iterator iterator = dtoProcesoVerificacionHabilidad.getListaReglas().iterator(); iterator.hasNext();) {
			DTOReglaVerificacionHabilidad dtoReglaVerificacionHabilidad = (DTOReglaVerificacionHabilidad) iterator.next();
			
			EleProcesoRegla eleProcesoRegla = new EleProcesoRegla();
			eleProcesoRegla.setConsecutivoProRegla(dtoReglaVerificacionHabilidad.getConsecutivoProRegla());
			eleProcesoRegla.setEleProceso(eleProceso);
			eleProcesoRegla.setEstadoProgramacion(ESTADO_ACTIVO);
			eleProcesoRegla.setEleRegla(new EleRegla());
			eleProcesoRegla.getEleRegla().setCodigoRegla(dtoReglaVerificacionHabilidad.getCodigoRegla());
			eleProcesoRegla.getEleRegla().setNombreReglaJava(dtoReglaVerificacionHabilidad.getNombreReglaJava());
			eleProcesoRegla.getEleRegla().setObsViolacionRegla(dtoReglaVerificacionHabilidad.getObservacionViolacionRegla());
			eleProcesoRegla.setUsuarioRegistra(nombreUsuario);
			eleProcesoRegla.setFechaInicioEjecucion(dtoReglaVerificacionHabilidad.getFechaInicioEjecucion());
			conjuntoEleProcesoRegla.add(eleProcesoRegla);
		}
		eleProceso.setEleProcesoReglas(conjuntoEleProcesoRegla);
		
		procesoVerificacionHabilidad = new ProcesoVerificacionHabilidadAsociados();
		try {
			procesoVerificacionHabilidad.ejecutarProcesoVerificacionHabilidad(eleProceso);
		} catch (Exception e) {
			throw new RuntimeException("Se ha presentado un error ejecutando el proceso de" +
					" verificación de habilidad de asociado. " + e.getMessage(), e);
		}
	}

}
