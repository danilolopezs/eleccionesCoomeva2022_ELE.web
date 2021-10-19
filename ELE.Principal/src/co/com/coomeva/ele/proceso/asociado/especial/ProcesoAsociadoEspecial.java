package co.com.coomeva.ele.proceso.asociado.especial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que se encarga de consultar entre los asociados habiles del proceso de elecciones, cuales son asociados especiales.
 * 
 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
 * @project ProcesoAsociadoEspecial
 * @class ProcesoAsociadoEspecial
 * @date 13/12/2012
 *
 */
public class ProcesoAsociadoEspecial {
	
	/** CONEXION BASE DE DATOS **/
	private static Connection con = null; 
	
	/** QUERIES **/
	private static String QUERY_HABILES = "SELECT NUMERO_DOCUMENTO " +
			"FROM ELECDB.ELE_REPORTE_HABIL WHERE TIPO_VALIDACION = 1";
	
	private static String QUERY_ACTIVOS = "SELECT emp.tipo_de_identificacion, TRIM(emp.cedula_empleado) AS cedula_empleado , " +
			"emp.primer_nombre ||' '|| coalesce(emp.segundo_nombre, '') ||' '|| emp.primer_apellido ||' '|| coalesce(emp.segundo_apellido,'') nombre_empleado, " +
			"emp.fecha_ingreso_empresa, emp.fecha_inicio_contrato, NULL AS fecha_fin_contrato " +
			"FROM CCB_DATOS_EMPLEADOS_V emp " +
			"WHERE emp.estado_del_empleado != 'R'";
	
	private static String QUERY_RETIRADOS_1 = "SELECT emp.tipo_de_identificacion, TRIM(emp.cedula_empleado) AS cedula_empleado, " +
			"emp.primer_nombre ||' '|| coalesce(emp.segundo_nombre, '') ||' '|| emp.primer_apellido ||' '|| coalesce(emp.segundo_apellido,'') nombre_empleado, " +
			"emp.fecha_ingreso_empresa, emp.fecha_inicio_contrato, emp.fecha_fin_contrato " +
			"FROM CCB_DATOS_EMPLEADOS_V emp " +
			"WHERE emp.estado_del_empleado = 'R' " +
			"AND TRUNC(emp.fecha_fin_contrato) > TO_DATE('2011-01-16', 'YYYY-MM-DD')";
	       //"AND TO_CHAR(emp.fecha_fin_contrato,'YYYY-MM-DD') > '2011-01-16'";
	private static String QUERY_RETIRADOS_2 = "SELECT empRet.tipo_doc, TRIM(empRet.cedula) AS cedula, " +
			"empRet.nombre_empleado, empRet.fecha_ini_contrato, empRet.fecha_retiro " +
			"FROM M4CCB_VW_SRH_EMPLE empRet " +
			"WHERE empRet.FECHA_RETIRO >= TO_DATE('2005-01-01', 'YYYY-MM-DD')+1 " +
			"AND empRet.ESTADO = 'RET'";
	
	private static String QUERY_PROMOTORES = "SELECT P.PRO_IDENTIFICACION, " +
			"P.PRO_EMPRESA, P.PRO_ESTADO, P.PRO_FECHA_INGRESO, " +
			"P.PRO_FECHA_RETIRO " +
			"FROM ELECDB.ELE_PROMOTOR P";
	
	private static String QUERY_CONSECUTIVO = "SELECT NEXT VALUE FOR ELECDB.ELE_ASOCIADO_ESPECIAL_SEQ SEQ FROM SYSIBM.SYSDUMMY1";
	
	/** PARAMETROS **/
	private final static Long COD_TIPO_EMPLEADO = 1L, COD_TIPO_EPS = 2L, COD_TIPO_MP = 3L, COD_TIPO_VINCULACION = 4L;	
	private final static String PARAM_EPS = "EPS", PARAM_VINCULACION= "COO", PARAM_MP = "MP";
	
	/** LISTAS DE ASOCIADOS **/
	private static List<Long> listAsociadosHabiles = null;
	private static List<AsociadoActivoRetiradoDTO> asociadosActivos = null;
	private static List<AsociadoActivoRetiradoDTO> asociadosRetirados1 = null;
	private static List<AsociadoActivoRetiradoDTO> asociadosRetirados2= null;
	private static List<AsociadoActivoRetiradoDTO> asociadosPromotores = null;
	private static List<Long> listEmergenteActivos = null;
	private static List<Long> listEmergenteRetirados1 = null;
	private static List<Long> listEmergenteRetirados2 = null;
	private static List<Long> listEmergentePromotores = null;
	
	private static AsociadoEspecialDTO asociadoEspecial = null;
	
	/**
	 * Metodo principal del proceso.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 13/12/2012
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		try {
			System.out.println("---> "+new Timestamp(new Date().getTime())+" [INICIANDO PROCESO ASOCIADO ESPECIAL...............]");
			ejecutarProceso();
			System.out.println("---> "+new Timestamp(new Date().getTime())+" [FINALIZANDO PROCESO ASOCIADO ESPECIAL...............]");
		}catch (Exception e) {
			System.out.println("Exception :"+e.getMessage());
		}
	}
	
	/**
	 * Metodo que ejecuta el proceso de asociado especial.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 13/12/2012
	 * @throws Exception
	 */
	public static void ejecutarProceso()throws Exception{
		try {		
			
			// -------------------Cargar consultas en memoria-----------------------
			listAsociadosHabiles = consultaAsociadosHabiles();
			
			asociadosActivos = consultaAsociadosAcivos();
			listEmergenteActivos = hidratarListaEmergente(asociadosActivos);
			
			asociadosRetirados1 = consultaAsociadosRetirados1();
			listEmergenteRetirados1 = hidratarListaEmergente(asociadosRetirados1);
			
			asociadosRetirados2 = consultaAsociadosRetirados2();
			listEmergenteRetirados2 = hidratarListaEmergente(asociadosRetirados2);					
						
			asociadosPromotores = consultaPromotores();
			listEmergentePromotores = hidratarListaEmergente(asociadosPromotores);			
			// ----------------------------------------------------------------------
			abrirConexionDB2();
			
			// -----------Compara en que lista se encuentra un asociado habil, si esta en alguna lo inserta en asociado especial----------
			for (Long cedula : listAsociadosHabiles) {
				asociadoEspecial = new AsociadoEspecialDTO();
				asociadoEspecial.setNumeroDocumento(cedula);
				
				if (listEmergenteActivos.contains(cedula)) {
					for (AsociadoActivoRetiradoDTO dto : asociadosActivos) {
						if (dto.getNumeroDocumento().equals(cedula)) {
							asociadoEspecial.setFechaIngreso(dto.getFechaIngreso());
							asociadoEspecial.setFechaRetiro(dto.getFechaFinContrato());
							asociadoEspecial.setTipo(COD_TIPO_EMPLEADO);
							guardarAsociadoEspecial(asociadoEspecial);
						}
					}
				}else if (listEmergenteRetirados1.contains(cedula)) {
					for (AsociadoActivoRetiradoDTO dto : asociadosRetirados1) {
						if (dto.getNumeroDocumento().equals(cedula)) {
							asociadoEspecial.setFechaIngreso(dto.getFechaIngreso());
							asociadoEspecial.setFechaRetiro(dto.getFechaFinContrato());
							asociadoEspecial.setTipo(COD_TIPO_EMPLEADO);
							guardarAsociadoEspecial(asociadoEspecial);
						}
					}
				}else if (listEmergenteRetirados2.contains(cedula)) {
					for (AsociadoActivoRetiradoDTO dto : asociadosRetirados2) {
						if (dto.getNumeroDocumento().equals(cedula)) {
							asociadoEspecial.setFechaIngreso(dto.getFechaIngreso());
							asociadoEspecial.setFechaRetiro(dto.getFechaFinContrato());
							asociadoEspecial.setTipo(COD_TIPO_EMPLEADO);
							guardarAsociadoEspecial(asociadoEspecial);
						}
					}
				}else if (listEmergentePromotores.contains(cedula)) {
					for (AsociadoActivoRetiradoDTO dto : asociadosPromotores) {
						if (dto.getNumeroDocumento().equals(cedula)) {
							asociadoEspecial.setFechaIngreso(dto.getFechaIngreso());
							asociadoEspecial.setFechaRetiro(dto.getFechaFinContrato());
							
							if (dto.getEmpresa().equals(PARAM_EPS)) {
								asociadoEspecial.setTipo(COD_TIPO_EPS);
							} else if (dto.getEmpresa().equals(PARAM_VINCULACION)) {
								asociadoEspecial.setTipo(COD_TIPO_VINCULACION);
							} else if (dto.getEmpresa().equals(PARAM_MP)) {
								asociadoEspecial.setTipo(COD_TIPO_MP);
							}							
							
							guardarAsociadoEspecial(asociadoEspecial);
						}
					}
				}
			}
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
		} finally {			
			cerrarConexion();
		}
	}	
	
	/**
	 * Metodo que registra el asociado especial en la tabla ELE_ASOCIADO_ESPECIAL.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 13/12/2012
	 * @param dto
	 * @throws Exception
	 */
	public static void guardarAsociadoEspecial(AsociadoEspecialDTO dto)throws Exception{
		PreparedStatement st = null;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ELECDB.ELE_ASOCIADO_ESPECIAL (CONSECUTIVO, NUMERO_DOCUMENTO, TIPO, FECHA_INGRESO, FECHA_RETIRO) VALUES ");
		sql.append("(?, ?, ?, ?, ?)");
				
		try {
			st = con.prepareStatement(sql.toString());
			st.setLong(1, obtenerConsecutivoAsociadoEspecial());
			st.setLong(2, dto.getNumeroDocumento());
			st.setLong(3, dto.getTipo());
			st.setTimestamp(4, dto.getFechaIngreso());
			st.setTimestamp(5, dto.getFechaRetiro());
			st.execute();
		} catch (Exception e) {			
			throw new Exception("[ERROR INSERTANDO EN ELE_ASOCIADO_ESPECIAL -----------> "+e.getMessage()+"]");
		} 
	}
	
	/**
	 * Metodo que hidrata la lista emergente q reciba, solo para insertar las cedulas y saber si un asociado habil 
	 * se encuentra registrado en una de las listas de empleados y promotores.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 13/12/2012
	 * @param asociadosPromotores
	 * @return
	 * @throws Exception
	 */
	public static List<Long> hidratarListaEmergente(List<AsociadoActivoRetiradoDTO> asociadosPromotores)throws Exception{
		List<Long> list = new ArrayList<Long>();
		for (AsociadoActivoRetiradoDTO dto : asociadosPromotores) {
			list.add(dto.getNumeroDocumento());
		}
		return list;
	}
	
	/**
	 * Metodo que consulta los asociados habiles.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 13/12/2012
	 * @return
	 * @throws Exception
	 */
	public static List<Long> consultaAsociadosHabiles()throws Exception{	
		List<Long> list = new ArrayList<Long>();
		ResultSet rs = null;
		Long cedula = null;
		abrirConexionDB2();
		try {			
			PreparedStatement stmt = con.prepareStatement(QUERY_HABILES);
			stmt.execute();
			rs = stmt.getResultSet();
			if (rs.next()) {
				do{
					cedula = rs.getLong("NUMERO_DOCUMENTO");
					list.add(cedula);
				}while(rs.next());
			}			
		} catch (Exception e) {
			throw new Exception("[ERROR CONSULTANDO ASOCIADOS HABILES -----------> "+e.getMessage()+"]");
		} finally {
			cerrarConexion();
		}
		return list;
	}		
	
	/**
	 * Metodo que consulta los empleados activos.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 13/12/2012
	 * @return
	 * @throws Exception
	 */
	public static List<AsociadoActivoRetiradoDTO> consultaAsociadosAcivos()throws Exception{	
		List<AsociadoActivoRetiradoDTO> list = new ArrayList<AsociadoActivoRetiradoDTO>();
		ResultSet rs = null;
		AsociadoActivoRetiradoDTO asociadoActivo = null;
		abrirConexionOracle();
		try {			
			PreparedStatement stmt = con.prepareStatement(QUERY_ACTIVOS);
			stmt.execute();
			rs = stmt.getResultSet();
			if (rs.next()) {
				do {
					asociadoActivo = new AsociadoActivoRetiradoDTO();
					asociadoActivo.setFechaFinContrato(rs.getTimestamp("fecha_fin_contrato"));
					asociadoActivo.setFechaIngreso(rs.getTimestamp("fecha_ingreso_empresa"));			
					asociadoActivo.setNombreCompleto(rs.getString("nombre_empleado"));
					asociadoActivo.setNumeroDocumento(rs.getLong("cedula_empleado"));
					asociadoActivo.setTipoIdentificacion(rs.getString("tipo_de_identificacion"));
					
					list.add(asociadoActivo);
				} while (rs.next());
			}			
		} catch (Exception e) {
			throw new Exception("[ERROR CONSULTANDO ASOCIADOS ACTIVOS -----------> "+e.getMessage()+"]");
		} finally {
			cerrarConexion();
		}
		return list;
	}
	
	/**
	 * Metodo que consulta los empleados retirados depues del 01/01/2010
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 13/12/2012
	 * @return
	 * @throws Exception
	 */
	public static List<AsociadoActivoRetiradoDTO> consultaAsociadosRetirados1()throws Exception{	
		List<AsociadoActivoRetiradoDTO> list = new ArrayList<AsociadoActivoRetiradoDTO>();
		ResultSet rs = null;
		AsociadoActivoRetiradoDTO asociadoRetirado = null;
		abrirConexionOracle();
		try {			
			PreparedStatement stmt = con.prepareStatement(QUERY_RETIRADOS_1);
			stmt.execute();
			rs = stmt.getResultSet();
			if (rs.next()) {
				do {
					/*
					System.out.println(rs.getString("cedula_empleado"));
					System.out.println(rs.getString("fecha_fin_contrato"));
					System.out.println(rs.getString("fecha_ingreso_empresa"));
					System.out.println(rs.getString("nombre_empleado"));
					System.out.println(rs.getString("tipo_de_identificacion"));
					System.out.println("************************************************");
					*/
					asociadoRetirado = new AsociadoActivoRetiradoDTO();
					asociadoRetirado.setFechaFinContrato(rs.getTimestamp("fecha_fin_contrato"));
					asociadoRetirado.setFechaIngreso(rs.getTimestamp("fecha_ingreso_empresa"));
					asociadoRetirado.setNombreCompleto(rs.getString("nombre_empleado"));
					asociadoRetirado.setNumeroDocumento(rs.getLong("cedula_empleado"));
					asociadoRetirado.setTipoIdentificacion(rs.getString("tipo_de_identificacion"));
					
					list.add(asociadoRetirado);
				} while (rs.next());
			}			
		} catch (Exception e) {
			throw new Exception("[ERROR CONSULTANDO ASOCIADOS RETIRADOS UNO -----------> "+e.getMessage()+"]");
		} finally {
			cerrarConexion();
		}
		return list;
	}
	
	/**
	 * Metodo que consulta los empleados retirados antes del 01/01/2010
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 13/12/2012
	 * @return
	 * @throws Exception
	 */
	public static List<AsociadoActivoRetiradoDTO> consultaAsociadosRetirados2()throws Exception{	
		List<AsociadoActivoRetiradoDTO> list = new ArrayList<AsociadoActivoRetiradoDTO>();
		ResultSet rs = null;
		AsociadoActivoRetiradoDTO asociadoRetirado = null;
		abrirConexionOracle();
		try {			
			PreparedStatement stmt = con.prepareStatement(QUERY_RETIRADOS_2);
			stmt.execute();
			rs = stmt.getResultSet();
			if (rs.next()) {
				do {
					asociadoRetirado = new AsociadoActivoRetiradoDTO();
					asociadoRetirado.setFechaFinContrato(rs.getTimestamp("fecha_retiro"));
					asociadoRetirado.setFechaIngreso(rs.getTimestamp("fecha_ini_contrato"));
					asociadoRetirado.setNombreCompleto(rs.getString("nombre_empleado"));
					asociadoRetirado.setNumeroDocumento(rs.getLong("cedula"));
					asociadoRetirado.setTipoIdentificacion(rs.getString("tipo_doc"));
					
					list.add(asociadoRetirado);
				} while (rs.next());
			}			
		} catch (Exception e) {
			throw new Exception("[ERROR CONSULTANDO ASOCIADOS RETIRADOS DOS -----------> "+e.getMessage()+"]");
		} finally {
			cerrarConexion();
		}
		return list;
	}
	
	/**
	 * Metodo que consulta los promotores.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 13/12/2012
	 * @return
	 * @throws Exception
	 */
	public static List<AsociadoActivoRetiradoDTO> consultaPromotores()throws Exception{	
		List<AsociadoActivoRetiradoDTO> list = new ArrayList<AsociadoActivoRetiradoDTO>();
		ResultSet rs = null;
		AsociadoActivoRetiradoDTO asociadoRetirado = null;
		abrirConexionDB2();
		try {			
			PreparedStatement stmt = con.prepareStatement(QUERY_PROMOTORES);
			stmt.execute();
			rs = stmt.getResultSet();
			if (rs.next()) {
				do {
					asociadoRetirado = new AsociadoActivoRetiradoDTO();
					asociadoRetirado.setFechaFinContrato(rs.getTimestamp("PRO_FECHA_RETIRO"));
					asociadoRetirado.setFechaIngreso(rs.getTimestamp("PRO_FECHA_INGRESO"));
					asociadoRetirado.setNumeroDocumento(rs.getLong("PRO_IDENTIFICACION"));
					
					asociadoRetirado.setEmpresa(rs.getString("PRO_EMPRESA"));
					
					
					list.add(asociadoRetirado);
				} while (rs.next());
			}			
		} catch (Exception e) {
			throw new Exception("[ERROR CONSULTANDO PROMOTORES -----------> "+e.getMessage()+"]");
		} finally {
			cerrarConexion();
		}
		return list;
	}
	
	/**
	 * Metodo que abre la conexion a DB2.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 13/12/2012
	 * @throws Exception
	 */
	public static void abrirConexionDB2()throws Exception{
		try {
			System.out.println("  [CREANDO CONEXIÓN CON LA BASE DE DATOS DB2....");
			Class.forName("com.ibm.as400.access.AS400JDBCDriver");
			//PRUEBAS
			//con = DriverManager.getConnection("jdbc:as400://pruebasma.intracoomeva.com.co:8471","usersipas", "c0omeva");
			//PRODUCCION
			con = DriverManager.getConnection("jdbc:as400://multiactiva400.intracoomeva.com.co:8471","USERELEC", "P4EL3CI0NS");
			con.setAutoCommit(false);
		}catch (Exception e) {
			throw new Exception("[ERROR CREANDO LA CONEXIÓN DE DB2 -----------> "+e.getMessage()+"]");
		}
	}		
	
	/**
	 * Metodo que abre la conexion a Oracle.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 13/12/2012
	 * @throws Exception
	 */
	public static void abrirConexionOracle()throws Exception{
		try {
			System.out.println("  CREANDO CONEXIÓN CON LA BASE DE DATOS ORACLE....");
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@bdmt4prod.intracoomeva.com.co:1550:MT4PROD","mt4Prod", "meta4");
			con.setAutoCommit(false);
		}catch (Exception e) {
			throw new Exception("[ERROR CREANDO LA CONEXIÓN DE ORACLE -----------> "+e.getMessage()+"]");
		}
	}
	
	/**
	 * Metodo que cierra la conexión a la base de datos.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 13/12/2012
	 * @throws Exception
	 */
	public static void cerrarConexion()throws Exception{
		try {
			con.close();
			System.out.println("...CERRANDO CONEXIÓN DE BASE DE DATOS]");
		} catch (SQLException e) {
			throw new Exception("[ERROR CERRANDO LA CONEXIÓN DE BASE DE DATOS -----------> "+e.getMessage()+"]");
		}
	}
	
	/**
	 * Metod que obtiene el consecutivo de asociado especial.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 13/12/2012
	 * @return
	 * @throws Exception
	 */
	public static Long obtenerConsecutivoAsociadoEspecial()throws Exception{
		Long consecutivo = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(QUERY_CONSECUTIVO);
			stmt.execute();
			rs = stmt.getResultSet();
			if(rs!=null && rs.next()){
				consecutivo = rs.getLong("SEQ");
			}
		} catch (Exception e) {
			throw new Exception("[ERROR CONSULTANDO EL ULTIMO CONSECUTIVO DE ELE_ASOCIADO_ESPECIAL -----------> "+e.getMessage()+"]");
		}
		return consecutivo;
	}

}

class AsociadoActivoRetiradoDTO {
	
	private String tipoIdentificacion;
	private Long numeroDocumento;
	private String nombreCompleto;
	private Timestamp fechaIngreso;
	private Timestamp fechaFinContrato;
	private String empresa;
	
	public AsociadoActivoRetiradoDTO(){
		
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public Long getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public Timestamp getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Timestamp fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Timestamp getFechaFinContrato() {
		return fechaFinContrato;
	}

	public void setFechaFinContrato(Timestamp fechaFinContrato) {
		this.fechaFinContrato = fechaFinContrato;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}		

}

class AsociadoEspecialDTO {
	private Long numeroDocumento;
	private Long tipo;
	private Timestamp fechaIngreso;
	private Timestamp fechaRetiro;
	
	public AsociadoEspecialDTO(){
		
	}
	
	public Long getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Long getTipo() {
		return tipo;
	}

	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}

	public Timestamp getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Timestamp fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Timestamp getFechaRetiro() {
		return fechaRetiro;
	}

	public void setFechaRetiro(Timestamp fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}
	
}