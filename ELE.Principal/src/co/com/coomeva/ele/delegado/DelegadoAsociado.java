package co.com.coomeva.ele.delegado;


import java.util.List;

import co.com.coomeva.ele.dto.DTOHabilidadAsociado;
import co.com.coomeva.ele.entidades.admhabilidad.Asoelecf;
import co.com.coomeva.ele.logica.LogicaAsociado;
import co.com.coomeva.ele.modelo.AsociadoReporteDTO;
import co.com.coomeva.ele.modelo.EleAsociadoDatosDTO;
import co.com.coomeva.util.acceso.UtilAcceso;

public class DelegadoAsociado {
	private static DelegadoAsociado instance;
	private static LogicaAsociado logicaAsociado;

	//Constructor de la clase
	private DelegadoAsociado() {
	}

	//Patròn Singular
	public static DelegadoAsociado getInstance() {
		if (instance == null) {
			instance = new DelegadoAsociado();
			logicaAsociado = LogicaAsociado.getInstance();
		}
		return instance;
	}
	
	
	public Asoelecf findAsoHabFinWS(String identificacion) throws Exception{
		Long idLong = 0l;
		try {
			idLong = Long.valueOf(identificacion);	
		} catch (NumberFormatException e) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noFormatoNumeroID"));
		}
		return logicaAsociado.findAsoHabFinWS(idLong);
	}
	
	public Asoelecf findAsoHabFin(String identificacion) throws Exception{
		Long idLong = 0l;
		try {
			idLong =(Long) Long.parseLong(identificacion);	
		} catch (NumberFormatException e) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noFormatoNumeroID"));
		}
		return logicaAsociado.findAsoHabFin(idLong);
	}
	
	public void cambiarEstado(String estado, String identificacion, String concepto, String usuario) throws Exception{
		Long idLong = 0l;
		try {
			idLong = Long.valueOf(identificacion);	
		} catch (NumberFormatException e) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noFormatoNumeroID"));
		}
		logicaAsociado.cambiarEstado(estado, idLong, concepto,usuario);
	}

	
	public List<AsociadoReporteDTO> findAsoHabFinNative(String zona, String oficina,
			String regional,String estado) throws Exception {
		
		return logicaAsociado.findAsoHabFinNative(zona,oficina,regional,estado);
	}
	
	// ###################################################### ELECCIONES 2012 ####################################################################
			
		/**
		 * Consulta habilidad de un asociado
		 * 
		 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
		 * @date 8/11/2012
		 * @param numeroDocumento
		 * @return
		 * @throws Exception
		 */
		public DTOHabilidadAsociado consultarHabilidadAsociado(Long numeroDocumento)throws Exception{
			return logicaAsociado.consultarHabilidadAsociado(numeroDocumento);
		}
		
		public Long obtieneCodAsociado(Long documento){
			return logicaAsociado.obtieneCodAsociado(documento);
		}
		
		/**
		 * Metodo que consulta los asociados habiles filtrados por profesion, identificacion o zona,
		 * consulta paginada para el reporte.
		 * 
		 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
		 * @date 14/11/2012
		 * @param codProfesion
		 * @param numeroDocumento
		 * @param codZona
		 * @return
		 * @throws Exception
		 */
		public List<EleAsociadoDatosDTO> consultarAsociadosHabilesAsociado(Long codProfesion, Long numeroDocumento, Long codZona) throws Exception{
			return logicaAsociado.consultarAsociadosHabilesAsociado(codProfesion, numeroDocumento, codZona);
		}
		
		/**
		 * Obtiene el nombre completo de un asociado
		 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
		 * @date 15/11/2012
		 * @param numeroDocumento
		 * @return String
		 */
		public String consultarNombreAsociado(Long numeroDocumento) throws Exception{
			return logicaAsociado.consultarNombreAsociadoBuc(numeroDocumento);
		}
		/**
		 * Consulta los datos de zona, regional y nombre
		 * @param numeroDocumento
		 * @return
		 * @throws Exception
		 */
		public String[] consultarDatosAsociadoZonaBuc(Long numeroDocumento) throws Exception{
			return logicaAsociado.consultarDatosAsociadoZonaBuc(numeroDocumento);
		}
		
		/**
		 * Metodo que consulta los asociados habiles filtrados por profesion, identificacion o zona,
		 * consulta paginada para el reporte.
		 * 
		 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
		 * @date 14/11/2012
		 * @param codProfesion
		 * @param numeroDocumento
		 * @param codZona
		 * @return
		 * @throws Exception
		 */
		public List<EleAsociadoDatosDTO> consultarAsociadosHabiles() throws Exception{
			return logicaAsociado.consultarAsociadosHabilesNativa();
		}
		
		/**
		 * Metodo que consulta los asociados inhabiles
		 * 
		 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
		 * @date 14/11/2012
		 * @param codProfesion
		 * @param numeroDocumento
		 * @param codZona
		 * @return
		 * @throws Exception
		 */
		public List<EleAsociadoDatosDTO> consultarAsociadosInhabiles() throws Exception{
			return logicaAsociado.consultarAsociadosInhabilesNativa();
		}
		
		/**
		 * Consulta un asociado activo en la tabla ele_asociado
		 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
		 * @date 22/11/2012
		 * @param id
		 * @return boolean
		 */
		public boolean estaAsociaActivo(Long id){
			return logicaAsociado.estaAsociaActivo(id);
		}

		/**
		 * Consulta el estado del asociado dada su cédula
		 * @author Juan Diego Montoya - Coomeva - 09/09/2016
		 * @param id
		 * @return Double
		 * @throws Exception 
		 */
		public Double consultarEstadoAsociado(Long id) throws Exception{
			return logicaAsociado.consultarEstadoAsociado(id);
		}
		
		/**
		 * Verifica si en el sistema hay un asociado con la cédula o id
		 * pasado como parámetro
		 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
		 * @date 22/11/2012
		 * @param id
		 * @return boolean
		 */
		public boolean esAsociado(Long id){
			return logicaAsociado.esAsociado(id);
		}

		/**
		 * Consulta que verifica si un asociado es persona jurídica
		 * @author Carlos Ernesto Ortega Q. Contratista Coomeva
		 * @date 13/01/2014
		 * @param id
		 * @return
		 */
		public boolean esAsociadoPersonaJuridica(Long id){
			return logicaAsociado.esAsociadoPersonaJuridica(id);
		}		
		
		public boolean asociadoPerteneceZonaHayEleccion(Long id){
			return logicaAsociado.asociadoPerteneceZonaHayEleccion(id);
		}

		
		/**
		 * Metodo que consulta el codigo del cabeza de plancha filtrado por un
		 * asociado inscrito a esta.
		 * 
		 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
		 * @date 6/12/2012
		 * @param numeroDocumentoInscrito
		 * @return
		 * @throws Exception
		 */
		public Long[] consultaCabezaPlanchaPorInscrito(Long numeroDocumentoInscrito)throws Exception{
			return logicaAsociado.consultaCabezaPlanchaPorInscrito(numeroDocumentoInscrito);
		}
		
		/**
		 * Metodo que consulta la cantidad de impresiones de formatos de una plancha filtrado 
		 * por el consecutivo de la plancha.
		 * 
		 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
		 * @date 6/12/2012
		 * @param consecutivoPlancha
		 * @return
		 * @throws Exception
		 */
		public Long consultaNumeroImpresionesFormatoPLancha(Long consecutivoPlancha)throws Exception{
			return logicaAsociado.consultaNumeroImpresionesFormatoPLancha(consecutivoPlancha);
		}
		
		/**
		 * Metodo encargado de consultar el total de asociados hábiles a 
		 * la fecha
		 */
		public int consultarTotalAsociadosHabilesAsociado(Long zona) throws Exception{
			return logicaAsociado.consultarTotalAsociadosHabiles(zona);
		}
		
		/**
		 * Metodo encargado de consultar el total de asociados especiales hábiles a 
		 * la fecha
		 */
		public int consultarTotalAsociadosEspecialesHabilesAsociado(Long zona) throws Exception{
			return logicaAsociado.consultarTotalAsociadosEspecialesHabiles(zona);
		}
		
		/**
		 * Metodo encargado de consultar el total de asociados hábiles a 
		 * la fecha por zona
		 */
		public Double totalAsociadosPorZona(Long codZona) throws Exception{
			return logicaAsociado.totalAsociadosPorZona(codZona);
		}
		
		public EleAsociadoDatosDTO consultarInformacionBasicaAsociado(Long numintAsociado) throws Exception{
			return logicaAsociado.consultarInformacionBasicaAsociado(numintAsociado);
		}
		
		public Long consultarCodigoAsociadoPorNumeroDocumento(Long numeroDocumento) throws Exception {
			return logicaAsociado.consultarCodigoAsociadoPorNumeroDocumento(numeroDocumento);		
		}
}