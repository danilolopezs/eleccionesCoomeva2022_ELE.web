package co.com.coomeva.ele.logica;

import java.util.ArrayList;
import java.util.List;

import co.com.coomeva.ele.modelo.FiltrosConsultasDTO;
import co.com.coomeva.ele.modelo.ResumenHabilidadDTO;
import co.com.coomeva.ele.modelo.ResumenZonaHabilidadDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.util.resources.LoaderResourceElements;

public class LogicaInformeResumen {
	
	private static LogicaInformeResumen instance;
	private LoaderResourceElements loaderResourceElements = LoaderResourceElements.getInstance();
	private LogicaFiltros logicaFiltros = LogicaFiltros.getInstance();
	private LogicaAsociado logicaAsociado = LogicaAsociado.getInstance();
	
	private LogicaInformeResumen(){
		
	}
	
	public static LogicaInformeResumen getInstance(){
		if (instance == null) {
			instance = new LogicaInformeResumen();
		}
		return instance;
	}
	
	/**
	 * Metodo redondea valores decimales
	 * 
	 * @author Juan Diego Montoya Ochoa
	 * @date 02/09/2016
	 * @return 
	 */
	
	public static double round (double value, int precision) {
	    int scale = (int) Math.pow(10, precision);
	    return (double) Math.round(value * scale) / scale;
	}
	
	/**
	 * Metodo que consulta la informacion para el reporte del resumen de habilidades
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 26/11/2012
	 * @return
	 * @throws Exception
	 */
	public List<ResumenHabilidadDTO> consultarInformacionResumenHabilidad() throws Exception{
		List<ResumenHabilidadDTO> informe = new ArrayList<ResumenHabilidadDTO>();
		List<ResumenZonaHabilidadDTO> listInformeZona = null;
		ResumenZonaHabilidadDTO dtoInformacion = null;
		ResumenHabilidadDTO dto = null;
		
		try {
			List<FiltrosConsultasDTO> listRegionales = logicaFiltros.consultarRegionales();
			List<FiltrosConsultasDTO> listZonas = null;
			
			for (FiltrosConsultasDTO regional : listRegionales) {	
				listZonas = logicaFiltros.consultarZonasPorRegional(regional.getCodigoFiltro());
				dto = new ResumenHabilidadDTO();
				listInformeZona = new ArrayList<ResumenZonaHabilidadDTO>();
				dto.setTotalAsociadosZona(new Double(0));
				dto.setTotalHabilesZona(new Double(0));
				dto.setTotalInhabilesZona(new Double(0));
				for (FiltrosConsultasDTO zona : listZonas) {
					dtoInformacion = new ResumenZonaHabilidadDTO();
					dtoInformacion.setZona(zona.getDescripcionFiltro());
					dtoInformacion.setTotalAsociadosZona(logicaAsociado.totalAsociadosPorZona(zona.getCodigoFiltro()));
					dtoInformacion.setTotalHabilesZona(logicaAsociado.totalAsociadosHabilesPorZona(zona.getCodigoFiltro()));
					dtoInformacion.setTotalInhabilesZona(logicaAsociado.totalAsociadosInhabilesPorZona(zona.getCodigoFiltro()));
					dtoInformacion.setPorcentajeHabiles(round(((dtoInformacion.getTotalHabilesZona()/dtoInformacion.getTotalAsociadosZona())*100), 2));
					dtoInformacion.setPorcentajeInhabiles(round(((dtoInformacion.getTotalInhabilesZona()/dtoInformacion.getTotalAsociadosZona())*100), 2));
					dtoInformacion.setMuestraZona(round((dtoInformacion.getTotalInhabilesZona()*ConstantesProperties.VALOR_PORCENTAJE_MUESTRA), 2));
					dtoInformacion.setPorcentajeMuestra(round(((dtoInformacion.getMuestraZona() / dtoInformacion.getTotalInhabilesZona())*100), 2));
					
					listInformeZona.add(dtoInformacion);
					dto.setTotalAsociadosZona(
							dto.getTotalAsociadosZona() == null ? dtoInformacion.getTotalAsociadosZona()
									: dto.getTotalAsociadosZona() + dtoInformacion.getTotalAsociadosZona());
					dto.setTotalHabilesZona(dto.getTotalHabilesZona()==null?dtoInformacion.getTotalHabilesZona():dto.getTotalHabilesZona()+dtoInformacion.getTotalHabilesZona());
					dto.setTotalInhabilesZona(dto.getTotalInhabilesZona()==null?dtoInformacion.getTotalInhabilesZona():dto.getTotalInhabilesZona()+dtoInformacion.getTotalInhabilesZona());					
				}				
				
				dto.setListResumen(listInformeZona);
				dto.setRegional(regional.getDescripcionFiltro().trim());
				if (dto.getTotalAsociadosZona() > 0) 
				{
					dto.setPorcentajeHabiles(round((dto.getTotalHabilesZona()/dto.getTotalAsociadosZona()*100), 2));
					dto.setPorcentajeInhabiles(round((dto.getTotalInhabilesZona()/dto.getTotalAsociadosZona()*100), 2));
				}
				else
				{
					dto.setPorcentajeHabiles(new Double(0));
					dto.setPorcentajeInhabiles(new Double(0));
				}
				dto.setMuestraZona(round(dto.getTotalInhabilesZona() * ConstantesProperties.VALOR_PORCENTAJE_MUESTRA, 2));
				
				if (dto.getTotalInhabilesZona() > 0) 
				{
					dto.setPorcentajeMuestra(round((dto.getMuestraZona()/dto.getTotalInhabilesZona()), 2));
				}
				else
				{
					dto.setPorcentajeMuestra(new Double(0));
				}
				
				informe.add(dto);				
				
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			
		}
		return informe;
	}
	
	

}
