package co.com.coomeva.delegado.wsempleados;

import co.com.coomeva.logica.wsempleados.ConsultaDatosBasicosRequest;
import co.com.coomeva.logica.wsempleados.ConsultaDatosBasicosResponse;
import co.com.coomeva.logica.wsempleados.ConsultaDatosEmpleadorRequest;
import co.com.coomeva.logica.wsempleados.ConsultaDatosEmpleadorResponse;
import co.com.coomeva.logica.wsempleados.DatosBasicos;
import co.com.coomeva.logica.wsempleados.DatosEmpleador;
import co.com.coomeva.logica.wsempleados.Empleado;
import co.com.coomeva.logica.wsempleados.EmpleadoDatosBasicos;
import co.com.coomeva.logica.wsempleados.EmpleadoDatosEmpleador;
import co.com.coomeva.logica.wsempleados.WSEmpleado_EmpleadoHttpServiceLocator;

public class DelegadoWsEmpleados {
	
	private static DelegadoWsEmpleados instance;
	
	private DelegadoWsEmpleados(){
	}
	
	public static DelegadoWsEmpleados getInstance(){
		if(instance == null){
			instance = new DelegadoWsEmpleados();
		}
		return instance;
	}
	
	/**
	 * Consulta los datos básicos del asociado
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
	 * @date 6/12/2012
	 * @param numeroCedulaEmpleado
	 * @return EmpleadoDatosBasicos
	 * @throws Exception
	 */
	public DatosBasicos consultarDatosBasicos(String numeroCedulaEmpleado) throws Exception{
		try {
			WSEmpleado_EmpleadoHttpServiceLocator locator = new WSEmpleado_EmpleadoHttpServiceLocator();
			Empleado emp = locator.getWSEmpleado_EmpleadoHttpPort();
			ConsultaDatosBasicosRequest consultaDatosBasicosRequest = new ConsultaDatosBasicosRequest();
			consultaDatosBasicosRequest.setAplicativoOrigen("elecciones");
			consultaDatosBasicosRequest.setCedulaEmpleado(numeroCedulaEmpleado);
			ConsultaDatosBasicosResponse consultaDatosBasicosResponse =  emp.consultaDatosBasicos(consultaDatosBasicosRequest);
			
			EmpleadoDatosBasicos datosAsociado = consultaDatosBasicosResponse.getEmpleado();
			
			if(datosAsociado != null){
				return datosAsociado.getRegistro(0);
			} 
		} catch (Exception e) {
			throw new Exception("Se he presentado un error consultando los datos básicos" +
					" del asociado. ", e);
		}
		
		return null;
	}
	
	
	/**
	 * Consulta los datos básicos del empleado referentes a su empleo
	 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
	 * @date 3/12/2012
	 * @param numeroCedulaEmpleado
	 * @return
	 */
	public DatosEmpleador consultarDatosEmpleador(String numeroCedulaEmpleado) throws Exception{
		try {
			WSEmpleado_EmpleadoHttpServiceLocator locator = new WSEmpleado_EmpleadoHttpServiceLocator();
			Empleado emp = locator.getWSEmpleado_EmpleadoHttpPort();
			ConsultaDatosEmpleadorRequest consultaDatosEmpleadorRequest = new  ConsultaDatosEmpleadorRequest();
			consultaDatosEmpleadorRequest.setAplicativoOrigen("elecciones");
			consultaDatosEmpleadorRequest.setCedulaEmpleado(numeroCedulaEmpleado);
			ConsultaDatosEmpleadorResponse consultaDatosEmpleadorResponse =  emp.consultaDatosEmpleador(consultaDatosEmpleadorRequest);
			
			EmpleadoDatosEmpleador datosEmpleador = consultaDatosEmpleadorResponse.getEmpleado();
			
			if(datosEmpleador != null){
				return datosEmpleador.getRegistro(0);
			} 
		} catch (Exception e) {
			throw new Exception("Se he presentado un error consultando los datos del empleador" +
					" del asociado. ", e);
		}
		
		return null;
		
	}
	
	public boolean existEmpleado(String numeroCedulaEmpleado) throws Exception{
		DatosBasicos datosBasEmpleado = consultarDatosBasicos(numeroCedulaEmpleado);
		
		if(datosBasEmpleado != null){
			return Boolean.TRUE;
		} else{
			return Boolean.FALSE;
		}
	}
	
	public static void main(String[] args) {
		try {
			DatosEmpleador emp = new DelegadoWsEmpleados().consultarDatosEmpleador("16739587");//16739587 -14839032
			System.out.println(emp.getFechaFinContrato());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
