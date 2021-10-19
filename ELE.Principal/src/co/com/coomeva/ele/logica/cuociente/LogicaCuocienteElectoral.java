package co.com.coomeva.ele.logica.cuociente;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Transaction;

import co.com.coomeva.ele.entidades.cuociente.EleCuocienteElectoral;
import co.com.coomeva.ele.entidades.cuociente.dao.EleCuocienteElectoralDAO;
import co.com.coomeva.ele.modelo.ResumenCuocienteDTO;
import co.com.coomeva.ele.modelo.ResumenNovedadesDTO;
import co.com.coomeva.ele.modelo.ResumenZonaHabilidadDTO;
import co.com.coomeva.ele.modelo.ResumenZonasNovedadesDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;
import co.com.coomeva.ele.utilidades.GeneradorConsecutivos;
import co.com.coomeva.ele.utilidades.UtilNumber;
import co.com.coomeva.util.acceso.UtilAcceso;

public class LogicaCuocienteElectoral extends EleCuocienteElectoralDAO implements ILogicaCuocienteElectoral{

	private static LogicaCuocienteElectoral instance;
	
	//Patrón Singular
	public static LogicaCuocienteElectoral getInstance() 
	{
		if (instance == null) {
			instance = new LogicaCuocienteElectoral();
		}
		return instance;
	}
	
	/**
	 * Metodo que consulta la informacion para generar el reporte de Cuociente Electoral.
	 * 
	 * @author Juan Diego Montoya
	 * @date 02/09/2016
	 * @return
	 */
	public List<ResumenCuocienteDTO> generarInformacionResumenCuociente() throws Exception {
		List<ResumenCuocienteDTO> list = new ArrayList<ResumenCuocienteDTO>();
		ResumenCuocienteDTO dtoList = null;
		ResumenZonasNovedadesDTO dtoNovedades = null;
		ResumenZonaHabilidadDTO dtoHabilidad = null;
		
		
		return list;
	}
	
	/**
	 * Lógica para calcular el cuociente electoral
	 */
	public EleCuocienteElectoral calcularCuocienteElectoral(Integer idRegistro, String periodoElectoral,
			Double totalAsocHabiles, Double totalDelegadosElegir, Double totalDelegadosEspeciales,
			Double finalTotalDelegadosElegir, Double cuocienteElectoral) throws Exception {
		
		EleCuocienteElectoral eleCuocienteElectoral = getConsultarCuocienteElectoral(periodoElectoral);
		boolean nuevo = eleCuocienteElectoral == null;
		
		// Si no existe el cuociente se obtiene el nuevo consecutivo.
		idRegistro = (nuevo && idRegistro == null) ? new Integer(GeneradorConsecutivos.getInstance()
				.getConsecutivo(ConstantesNamedQueries.QUERY_SEQ_CUOCIENTE_ELECTORAL, this.getSession()).toString())
				: eleCuocienteElectoral.getIdRegistro();
		
		/**
		 * Se validan los campos requeridos para el calculo 
		 */
		 
		if (periodoElectoral == null) {
			UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.cuociente.periodoElectoral");
		}
		if (totalAsocHabiles == null) {
			UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.cuociente.totalAsocHabiles");
		}
		if (totalDelegadosElegir == null) {
			UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.cuociente.totalDelegadosElegir");
		}
		if (totalDelegadosEspeciales == null) {
			UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.cuociente.totalDelegadosEspeciales");
		}
		
		/**
		 * Calculo del total de delegados a elegir
		 */
		finalTotalDelegadosElegir = totalDelegadosElegir - totalDelegadosEspeciales;
		/**
		 * Calculo del cuociente electoral
		 */
		cuocienteElectoral = (totalAsocHabiles / finalTotalDelegadosElegir);
		cuocienteElectoral = UtilNumber.getInstance().getDecimal(4, cuocienteElectoral);
		Transaction transaction = null;
		
		try 
		{
			transaction = this.getSession().beginTransaction();
			eleCuocienteElectoral = new EleCuocienteElectoral();
			eleCuocienteElectoral.setCuocienteElectoral(cuocienteElectoral);
			eleCuocienteElectoral.setFinalTotalDelegadosElegir(finalTotalDelegadosElegir);
			eleCuocienteElectoral.setIdRegistro(idRegistro);
			eleCuocienteElectoral.setPeriodoElectoral(periodoElectoral);
			eleCuocienteElectoral.setTotalAsocHabiles(totalAsocHabiles);
			eleCuocienteElectoral.setTotalDelegadosElegir(totalDelegadosElegir);
			eleCuocienteElectoral.setTotalDelegadosEspeciales(totalDelegadosEspeciales);
			
			// Se valida si es un nuevo registro o es un cuociente. 
			if( nuevo ){
				save(eleCuocienteElectoral);
			}else{
				merge(eleCuocienteElectoral);
			}
			
			this.getSession().flush();
			transaction.commit();
			return eleCuocienteElectoral;
			
		} catch (Exception e) {
			if (transaction!=null) {
				transaction.rollback();
			}
			throw e;
		}finally{
			this.getSession().clear();
			this.getSession().close();
		}
	}

	/**
	 * Lógica para calcular el cuociente electoral
	 */
	public List<EleCuocienteElectoral> getConsultarCuocienteElectoral()
			throws Exception {
		return findAll();
	}

	public EleCuocienteElectoral getConsultarCuocienteElectoral(String periodoElectoral) {
		if (periodoElectoral == null) {
			UtilAcceso.getParametroFuenteS(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.cuociente.periodoEelctoral");
		}

		List<EleCuocienteElectoral> resultado = findByPeriodoElectoral(periodoElectoral);
		if (!resultado.isEmpty()) {
			return resultado.get(0);
		}
		return null;
	}
	
	

}
