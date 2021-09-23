package co.com.coomeva.ele.delegado.habilidad;

import co.com.coomeva.ele.logica.LogicaProceso;

public class DelegadoProceso {
	
	private static DelegadoProceso instance;
	private static LogicaProceso logicaProceso;
	
	private DelegadoProceso(){
		
	}
	
	public static DelegadoProceso getInstance(){
		if (instance == null) {
			instance = new DelegadoProceso();
			logicaProceso = LogicaProceso.getInstance();
		}
		return instance;
	}
	
	/**
	 * Metodo que consulta la fecha del proceso por codigo
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 20/11/2012
	 * @param codigoProceso
	 * @return
	 * @throws Exception
	 */
	public String consultaFechaProcesoPorCodigo(Long codigoProceso) throws Exception{
		return logicaProceso.consultaFechaProcesoPorCodigo(codigoProceso);
	}
	public String consultaFechaCorteProcesoPorCodigo(Long codigoProceso) throws Exception{
		return logicaProceso.consultaFechaCorteProcesoPorCodigo(codigoProceso);
	}
	
	
}
