package co.com.coomeva.ele.delegado;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.coomeva.ele.entidades.planchas.EleCabPlancha;
import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral;
import co.com.coomeva.ele.entidades.planchas.ElePlanchas;
import co.com.coomeva.ele.entidades.planchas.ElePrincipales;
import co.com.coomeva.ele.entidades.planchas.EleSuplentes;
import co.com.coomeva.ele.logica.LogicaPlanchas;
import co.com.coomeva.ele.modelo.CabezaPlanchaDTO;
import co.com.coomeva.ele.modelo.EleCabPlanchaDTO;
import co.com.coomeva.ele.modelo.EleLogDTO;
import co.com.coomeva.ele.modelo.ElePlanchaDTO;
import co.com.coomeva.ele.modelo.ElePrincipalesDTO;
import co.com.coomeva.ele.modelo.EleSuplentesDTO;
import co.com.coomeva.ele.modelo.EleTablaEmpresaCargoDTO;
import co.com.coomeva.ele.modelo.InfoPlanchaAdmisionPdfDTO;
import co.com.coomeva.ele.modelo.InfoPlanchaConstanciaPdfDTO;
import co.com.coomeva.ele.modelo.InfoPlanchaDTO;
import co.com.coomeva.ele.modelo.ParametroPlanchaDTO;
import co.com.coomeva.ele.modelo.PlanchaPorEstadoDTO;
import co.com.coomeva.ele.modelo.SuspendidoDTO;

public class DelegadoPlanchas {
	private static DelegadoPlanchas instance;
	private static LogicaPlanchas logicaPlanchas;


	private DelegadoPlanchas() {
	}


	public static DelegadoPlanchas getInstance() {
		if (instance == null) {
			instance = new DelegadoPlanchas();
			logicaPlanchas =  LogicaPlanchas.getInstance();
		}
		return instance;
	}

	public String findPlanchasEstadoNative(String nroPlancha) throws Exception {
		return logicaPlanchas.findPlanchasEstadoNative(nroPlancha);
	}


	public void crearPlancha(String nroCabPlancha, Date fechaInscripcion,
			String estado, String zona, Long nroPlancha, String descEstado) throws Exception{
		
		logicaPlanchas.crearPlancha(nroCabPlancha, fechaInscripcion,
				 estado, zona, nroPlancha,  descEstado);

	}
	
	public ElePlanchas consultarPlancha(String nroCabezaPlancha) throws Exception{
		return logicaPlanchas.consultarPlancha(nroCabezaPlancha);
	}
	
	public void inscripcionPlancha(ElePlanchaDTO elPlanchas, EleCabPlanchaDTO elCabPlancha, List<ElePrincipalesDTO> principales, List<EleSuplentesDTO> suplentes, List<EleTablaEmpresaCargoDTO> listEmpresaCargo) throws Exception{
		logicaPlanchas.inscripcionPlancha(elPlanchas, elCabPlancha, principales, suplentes, listEmpresaCargo);
	}


	public void modificarPlanchaInscrita(ElePlanchas elplancha,
			EleCabPlanchaDTO eleCabPlanchaDTO,
			List<ElePrincipalesDTO> listaPrincipales,
			List<EleSuplentesDTO> listaSuplentes, List<EleExperienciaLaboral> listExperienciaLaboral, EleLogDTO eleLogDTO) throws Exception {
		// TODO Auto-generated method stub
		logicaPlanchas.modificarPlanchaInscrita(elplancha, eleCabPlanchaDTO, listaPrincipales, listaSuplentes, listExperienciaLaboral, eleLogDTO);
	}


	public void modificarPlancha(String nroCabPlancha, Date fechaInscripcion,
			String estado, String zona, Long nroPlancha, String descEstado) throws Exception {
		logicaPlanchas.modificarPlancha(nroCabPlancha, fechaInscripcion,estado, zona, nroPlancha,descEstado);
		
	}

	public void cambiarEstadoPlancha(Long nroCabPlancha, String descEstado,
			String estado, String user, EleLogDTO eleLogDTO,boolean nroPlancha) throws Exception {
		logicaPlanchas.cambiarEstadoPlancha(nroCabPlancha, descEstado, estado, user, eleLogDTO,nroPlancha);
	}


	public List<ElePlanchas> consultarPlanchas(String zona, Date fechaInicial,
			Date fechaFinal, String estado, boolean noNumeradas) throws Exception{
		// TODO Auto-generated method stub
		return logicaPlanchas.consultarPlanchas(zona, fechaInicial,
				fechaFinal, estado,noNumeradas);
	}


	public List<ElePlanchas> consultarPlanchas() throws Exception {
		return logicaPlanchas.consultarPlanchas();
	}


	public void modificarPlanchaNumerada(String nroCabPlancha, Long nroPlancha, String user, EleLogDTO eleLogDTO, String estado)
			throws Exception {
		logicaPlanchas.modificarPlanchaNumerada(nroCabPlancha, nroPlancha, user, eleLogDTO, estado);
	}


	public boolean validarOtraPlancha(String nroIdentificacion,String nroCabPlancha)throws Exception {
		
		return logicaPlanchas.validarOtraPlancha(nroIdentificacion, nroCabPlancha);
	}


	public List<ElePlanchaDTO> consultarPlanchasReporte() throws Exception {
		return logicaPlanchas.consultarPlanchasReporte();
	}


	public String formatearCadena(String cadena) {
		return logicaPlanchas.formatearCadena(cadena);
	}
	
	public List<CabezaPlanchaDTO> obtenerIntegrantesCabezaPlanchaPrinc(Long numPlancha){
		
		return logicaPlanchas.obtenerIntegrantesCabezaPlanchaPrinc(numPlancha);
	}
	
	public List<CabezaPlanchaDTO> obtenerIntegrantesCabezaPlanchaPag(String sortColumnName, boolean sortAscending, 
			int startRow, int maxResults, Long numPlancha) throws Exception{
		return logicaPlanchas.obtenerIntegrantesCabezaPlanchaPrincPag(sortColumnName, sortAscending, startRow, maxResults, numPlancha);
	}
	
	
	public InfoPlanchaDTO obtenerInfoPlancha(Long numDoc,  String idUsuario) throws Exception{
		
		return logicaPlanchas.obtenerInfoPlancha(numDoc, idUsuario);

	} 
	
	public List<ParametroPlanchaDTO> obtenerParametrosTipo(Long tipoParametro){
		return logicaPlanchas.obtenerParametrosTipo(tipoParametro);
	}
	
	public List<PlanchaPorEstadoDTO> obtenerPlanchasPorEstado(Long estado, String idUsuario) throws Exception{
		return logicaPlanchas.obtenerPlanchasPorEstado(estado, idUsuario);
	}
	
	public List<PlanchaPorEstadoDTO> obtenerPlanchasPorEstadoPag(String sortColumnName, boolean sortAscending, 
			int startRow, int maxResults, Long estado, String idUsuario) throws Exception{
		return logicaPlanchas.obtenerPlanchasPorEstadoPag(sortColumnName, sortAscending, startRow, maxResults, estado, idUsuario);
	}
	
	public List<String> procesaNombresApellidos(String nombreSinFormato){
		return logicaPlanchas.procesaNombresApellidos(nombreSinFormato);
	}
	
	public InfoPlanchaConstanciaPdfDTO obtenerInfoPlanchaConstanciaPdf(Long numPlancha){
		return logicaPlanchas.obtenerInfoPlanchaConstanciaPdf(numPlancha);
	}
	
	public List<CabezaPlanchaDTO> obtenerIntegrantesCabezaPlanchaPrincSinPag(Long numPlancha) throws Exception{
		return logicaPlanchas.obtenerIntegrantesCabezaPlanchaPrincSinPag(numPlancha);
	}
	
	/**
	 * Metodo que verifica si la fecha actual esta entre el periodo parametrizado para marcar la plancha en estado plancha recurso.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 7/12/2012
	 * @return
	 * @throws Exception
	 */
	public void validarFechaMarcarPlanchaRecurso()throws Exception{
		logicaPlanchas.validarFechaMarcarPlanchaRecurso();
	}
	
	/**
	 * metodo que marca en recurso una plancha. 
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 7/12/2012
	 * @param consecutivoPlancha
	 * @param usuarioSesion
	 * @throws Exception
	 */
	public void actualizarEstadoPlancha(Long consecutivoPlancha, String estado, String usuarioSesion)throws Exception{
		logicaPlanchas.actualizarEstadoPlancha(consecutivoPlancha, estado, usuarioSesion);
	}
	
	/**
	 * Obtiene la respectiva información de la plancha para mostrarla en un pdf de Admisión
	 * 
	 * @author Mario Alejandro Acevedo <br>
	 * @param numPlancha
	 * @return InfoPlanchaAdmisionPdfDTO
	 */
	public InfoPlanchaAdmisionPdfDTO obtenerInfoPlanchaAdmisionPdf(Long numPlancha, Long codFormato){
		return logicaPlanchas.obtenerInfoPlanchaAdmision(numPlancha, codFormato);
	}
	
	/**
	 * Metodo que verifica si la fecha actual esta entre el periodo parametrizado para generar la radicación de Admisión.
	 * 
	 * @author Mario Alejandro Acevedo <br>
	 * @param numPlancha
	 * @return boolean
	 */
	public boolean validarFechaMarcarPlanchaAdmision()throws Exception{
		return logicaPlanchas.validarFechaMarcarPlanchaAdmision();
	}
	
	/** Verifica si existe registro del asociado para determinado formato
	 * @param codAsociado
	 * @param codFormato
	 * @return int
	 */
	public int totalDetallesFormato(Long codAsociado, Long codFormato){
		return logicaPlanchas.totalDetallesFormato(codAsociado, codFormato);
	}
	
	/**
	 * Metodo que verifica si la fecha actual esta entre el periodo parametrizado para generar la radicación de Admisión.
	 * 
	 * @author Mario Alejandro Acevedo <br>
	 * @param numPlancha
	 * @return boolean
	 */
	public boolean validarFechaPlanchaConstancia()throws Exception{
		return logicaPlanchas.validarFechaPlanchaConstancia();
	}
	
	public String obtenerCiudadAgenciaVinculacion(Long numint){
		return logicaPlanchas.obtenerCiudadAgenciaVinculacion(numint);
	}
	
	public String obtieneFechasAdmision()throws Exception{
		return logicaPlanchas.obtieneFechasAdmision();
	}
	
	public String obtieneFechasConstancia()throws Exception{
		return logicaPlanchas.obtieneFechasConstancia();
	}
}