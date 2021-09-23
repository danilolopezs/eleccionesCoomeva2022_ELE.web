package co.com.coomeva.ele.logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.logica.inscripcion.plancha.ILogicaPlancha;
import co.com.coomeva.ele.logica.inscripcion.plancha.LogicaPlancha;
import co.com.coomeva.ele.modelo.FiltrosConsultasDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.util.date.ManipulacionFechas;
import co.com.coomeva.util.resources.LoaderResourceElements;

public class LogicaFiltros {
	
	private static LogicaFiltros instance;
	private LoaderResourceElements loaderResourceElements = LoaderResourceElements.getInstance();
	
	private LogicaFiltros(){
	}
	
	public static LogicaFiltros getInstance() {
		if (instance == null) {
			instance = new LogicaFiltros();
		}
		return instance;
	}
	
	/**
	 * Consulta las zonas de coomeva para mostrarlas en una lista desplegable
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 8/11/2012
	 * @return
	 * @throws Exception
	 */
	public List<FiltrosConsultasDTO> consultarZonas() throws Exception{
		List<FiltrosConsultasDTO> list = new ArrayList<FiltrosConsultasDTO>();
		List<Object[]> elements = null;
		FiltrosConsultasDTO dto = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			Query query = session.getNamedQuery("conulta.zonas.coomeva");
			elements = query.list();
			if (elements != null && elements.size() > 0) {
				for (Object[] object : elements) {
					dto = new FiltrosConsultasDTO();
					dto.setCodigoFiltro((Long)object[0]);
					dto.setDescripcionFiltro(object[1].toString());					
					list.add(dto);
				}
			}
		} catch (Exception e) {
			throw new Exception(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
					"consulta.zonas"));
		} finally{
			session.close();
		}
		return list;
	}
	
	/**
	 * Consulta las ciudades para mostrarlas en una lista desplegable
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 8/11/2012
	 * @return
	 * @throws Exception
	 */
	public List<FiltrosConsultasDTO> consultarCiudades() throws Exception{
		List<FiltrosConsultasDTO> list = new ArrayList<FiltrosConsultasDTO>();
		List<Object[]> elements = null;
		FiltrosConsultasDTO dto = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			Query query = session.getNamedQuery("conulta.ciudades.coomeva");
			elements = query.list();
			if (elements != null && elements.size() > 0) {
				for (Object[] object : elements) {
					dto = new FiltrosConsultasDTO();
					dto.setCodigoFiltro((Long)object[0]);
					dto.setDescripcionFiltro(object[1].toString());					
					list.add(dto);
				}
			}
		} catch (Exception e) {
			throw new Exception(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
					"consulta.ciudades"));
		} finally{
			session.close();
		}
		return list;
	}
	
	/**
	 * Consulta las profesiones de los asociados
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 8/11/2012
	 * @return
	 * @throws Exception
	 */
	public List<FiltrosConsultasDTO> consultarProfesiones(Long codPro, String nomPro) throws Exception{
		List<FiltrosConsultasDTO> list = new ArrayList<FiltrosConsultasDTO>();
		List<Object[]> elements = null;
		FiltrosConsultasDTO dto = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			StringBuffer sql = new StringBuffer("SELECT CTAB.CODINT, CTAB.CODNOM ");
			sql.append("FROM MULCLIDAT.CLITAB CTAB ");
			sql.append("WHERE CTAB.CODTAB = 98 ");
			if (codPro != null) {
				sql.append("AND CTAB.CODINT = :codPro ");
			}
			if (nomPro != null && !nomPro.equals("")) {
				sql.append("AND CTAB.CODNOM LIKE :nomPro ");
			}			
			sql.append("ORDER BY CTAB.CODNOM");
			
			SQLQuery query = session.createSQLQuery(sql.toString());
			query.addScalar("CODINT", Hibernate.LONG);
			query.addScalar("CODNOM", Hibernate.STRING);
			
			if (codPro != null) {
				query.setParameter("codPro", codPro == null ? null : codPro.intValue());
			}
			if (nomPro != null && !nomPro.equals("")) {
				query.setString("nomPro", "%"+nomPro.toUpperCase()+"%");
			}	
			
			elements = query.list();
			if (elements != null && elements.size() > 0) {
				for (Object[] object : elements) {
					dto = new FiltrosConsultasDTO();
					dto.setCodigoFiltro((Long)object[0]);
					dto.setDescripcionFiltro(object[1].toString());					
					list.add(dto);
				}
			}
		} catch (Exception e) {
			throw new Exception(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
					"consulta.ciudades"));
		} finally{
			session.close();
		}
		return list;
	}
		
	/**
	 * Consulta las fechas de los procesos programadas
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 20/11/2012
	 * @return
	 * @throws Exception
	 */
	public List<FiltrosConsultasDTO> consultarFechasProcesosProgramados() throws Exception{
		List<FiltrosConsultasDTO> list = new ArrayList<FiltrosConsultasDTO>();
		List<Object[]> elements = null;
		FiltrosConsultasDTO dto = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			Query query = session.getNamedQuery("conulta.fechas.procesos.programados");
			elements = query.list();
			if (elements != null && elements.size() > 0) {
				for (Object[] object : elements) {
					dto = new FiltrosConsultasDTO();
					dto.setCodigoFiltro((Long)object[0]);
					dto.setDescripcionFiltro(ManipulacionFechas.dateToString((Date)object[1]));					
					list.add(dto);
				}
			}
		} catch (Exception e) {
			throw new Exception(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
					"consulta.fechas.proceso.programadas"));
		} finally{
			session.close();
		}
		return list;
	}
	public List<FiltrosConsultasDTO> consultarFechasCortesProcesosProgramados() throws Exception{
		List<FiltrosConsultasDTO> list = new ArrayList<FiltrosConsultasDTO>();
		List<Object[]> elements = null;
		FiltrosConsultasDTO dto = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {
			Query query = session.getNamedQuery("conulta.fechascorte.procesos.programados");
			elements = query.list();
			if (elements != null && elements.size() > 0) {
				for (Object[] object : elements) {
					dto = new FiltrosConsultasDTO();
					dto.setCodigoFiltro((Long)object[0]);
					dto.setDescripcionFiltro(ManipulacionFechas.dateToString((Date)object[1]));					
					list.add(dto);
				}
			}
		} catch (Exception e) {
			throw new Exception(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
					"consulta.fechascorte.proceso.programadas"));
		} finally{
			session.close();
		}
		return list;
	}
	
	/**
	 * Metodo que consulta todas las regionales administrativas.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 26/11/2012
	 * @return
	 * @throws Exception
	 */
	public List<FiltrosConsultasDTO> consultarRegionales() throws Exception{
		List<FiltrosConsultasDTO> list = new ArrayList<FiltrosConsultasDTO>();
		List<Object[]> elements = null;
		FiltrosConsultasDTO dto = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		String[] descRegional;
		try {
			Query query = session.getNamedQuery("consulta.regionales");
			elements = query.list();
			if (elements != null && elements.size() > 0) {
				for (Object[] object : elements) {
					//Se modifica para que únicamente me aparezca el nombre de la regional sin el Reg.
					//Modificado por: Juan Diego Montoya
					String regional = object[1].toString();
					descRegional = regional.split("\\.");
					//Quito los espacios extra
					regional = descRegional[1].replaceAll("\\W", "");
					dto = new FiltrosConsultasDTO();
					dto.setCodigoFiltro((Long)object[0]);
					//TODO JD: Mappear mediante properties
					if (regional.equals(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
					"lbl.nombre.regional.EjeCafetero").toString())) 
					{
						regional = "Eje Cafetero";
					}
					dto.setDescripcionFiltro(regional);					
					list.add(dto);
				}
			}
		} catch (Exception e) {
			throw new Exception(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
					"consulta.regionales"));
		} finally{
			session.close();
		}
		return list;
	}
	
	/**
	 * Metodo que consulta zonas electorales por regional administrativa.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 26/11/2012
	 * @param codRegional
	 * @return
	 * @throws Exception
	 */
	public List<FiltrosConsultasDTO> consultarZonasPorRegional(Long codRegional) throws Exception{
		List<FiltrosConsultasDTO> list = new ArrayList<FiltrosConsultasDTO>();
		List<Object[]> elements = null;
		FiltrosConsultasDTO dto = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		String cadenaZonaEle = loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, 
				ConstantesProperties.CADENA_ZONA_NUM),
				cadenaZonaEleEspecial = loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, 
						ConstantesProperties.CADENA_ZONA_NUM);
		
		try {
			Query query = session.getNamedQuery("consulta.zonas.electorales.por.regional");
			query.setLong("codRegional", codRegional);
			elements = query.list();
			if (elements != null && elements.size() > 0) {
				for (Object[] object : elements) {
					dto = new FiltrosConsultasDTO();
					dto.setCodigoFiltro((Long)object[0]);
					
					if ((Long)object[0] > 28l) {					
						dto.setDescripcionFiltro(cadenaZonaEleEspecial+object[2].toString()+" "+object[1].toString());	
					}else{
						dto.setDescripcionFiltro(cadenaZonaEle+object[2].toString()+" "+object[1].toString());	
					}
									
					list.add(dto);
				}
			}
		} catch (Exception e) {
			throw new Exception(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
					"consulta.zonas.electorales.por.regional"));
		} finally{
			session.close();
		}
		return list;
	}
	
	/**
	 * Metodo que consulta todas las zonas electorales.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 26/11/2012
	 * @return
	 * @throws Exception
	 */
	public List<FiltrosConsultasDTO> consultarZonasElectorales() throws Exception{
		List<FiltrosConsultasDTO> list = new ArrayList<FiltrosConsultasDTO>();
		List<Object[]> elements = null;
		FiltrosConsultasDTO dto = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		String cadenaZonaEle = loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, 
				ConstantesProperties.CADENA_ZONA_NUM),
				cadenaZonaEleEspecial = loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, 
						ConstantesProperties.CADENA_ZONA_NUM);
		try {
			Query query = session.getNamedQuery("consulta.zonas.electorales");
			elements = query.list();
			if (elements != null && elements.size() > 0) {
				for (Object[] object : elements) {
					dto = new FiltrosConsultasDTO();
					dto.setCodigoFiltro((Long)object[0]);
					if ((Long)object[0] > 28l) {					
						dto.setDescripcionFiltro(cadenaZonaEleEspecial+object[2].toString()+" "+object[1].toString());	
					}else{
						dto.setDescripcionFiltro(cadenaZonaEle+object[2].toString()+" "+object[1].toString());	
					}			
					list.add(dto);
				}
			}
		} catch (Exception e) {
			throw new Exception(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
					"consulta.zonas.electorales"));
		} finally{
			session.close();
		}
		return list;
	}
	
	/**
	 * Metodo que conuslta la zona electoral de un asociado.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 6/12/2012
	 * @return
	 * @throws Exception
	 */
	public FiltrosConsultasDTO consultarZonaElectoralePorAsociado(Long numeroDocumento) throws Exception{
		FiltrosConsultasDTO dto = new FiltrosConsultasDTO();
		Object[] element = null;
		Session session = null;
		String cadenaZonaEle = loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, 
				ConstantesProperties.CADENA_ZONA_ELECTORAL),
				cadenaZonaEleEspecial = loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, 
						ConstantesProperties.CADENA_ZONA_ELECTORAL_ESPECIAL);
		
		ILogicaPlancha iLogicaPlancha = new LogicaPlancha();
		try {
			Query query = null;
			
			if(iLogicaPlancha.esColaboradorGECoomeva(numeroDocumento.toString())){
				session = HibernateSessionFactoryElecciones2012.getSession();
				query = session.getNamedQuery("consulta.zona.electorale.por.asociado.especial");
			} else{
				session = HibernateSessionFactoryElecciones2012.getSession();
				query = session.getNamedQuery("consulta.zona.electorale.por.asociado");
			}
			
			query.setLong("numeroDocumento", numeroDocumento);
			element = (Object[])query.uniqueResult();
			if (element != null) {
				dto = new FiltrosConsultasDTO();
				dto.setCodigoFiltro((Long)element[0]);	
				
				if ((Long)element[0] > 28l) {					
					dto.setDescripcionFiltro(cadenaZonaEleEspecial+element[2].toString()+" "+element[1].toString());	
				}else{
					dto.setDescripcionFiltro(cadenaZonaEle+element[2].toString()+" "+element[1].toString());	
				}	
			}					
		} catch (Exception e) {
			throw new Exception("Error consultando la zona electoral de un asociado");
		} 
		return dto;
	}

	/**
	 * @author Christian Mauricio Tangarife Colorado - cmtc4227
	 * 
	 * Consulta la zona electrotal especial del empleado.
	 * 
	 * @param numeroDocumento
	 * @return
	 * @throws Exception
	 */
	public FiltrosConsultasDTO consultarZonaElectoralEspePorEmpleado(
			Long numeroDocumento) throws Exception{
		FiltrosConsultasDTO dto = new FiltrosConsultasDTO();
		Object[] element = null;
		Session session = null;
		String cadenaZonaEle = loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, 
				ConstantesProperties.CADENA_ZONA_ELECTORAL),
				cadenaZonaEleEspecial = loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL, 
						ConstantesProperties.CADENA_ZONA_ELECTORAL_ESPECIAL);
		
		ILogicaPlancha iLogicaPlancha = new LogicaPlancha();
		try {
			Query query = null;
			
			if(iLogicaPlancha.esColaboradorGECoomeva(numeroDocumento.toString())){
				session = HibernateSessionFactoryElecciones2012.getSession();
				query = session.getNamedQuery("consulta.zona.electorale.por.asociado.especial");
			} else{
				session = HibernateSessionFactoryElecciones2012.getSession();
				query = session.getNamedQuery("consulta.zona.electorale.por.asociado");
			}
			
			query.setLong("numeroDocumento", numeroDocumento);
			element = (Object[])query.uniqueResult();
			if (element != null) {
				dto = new FiltrosConsultasDTO();
				dto.setCodigoFiltro((Long)element[0]);	
				
				if ((Long)element[0] > 28l) {					
					dto.setDescripcionFiltro(cadenaZonaEleEspecial+element[2].toString()+" "+element[1].toString());	
				}else{
					dto.setDescripcionFiltro(cadenaZonaEle+element[2].toString()+" "+element[1].toString());	
				}	
			}					
		} catch (Exception e) {
			throw new Exception("Error consultando la zona electoral de un asociado");
		} 
		return dto;
	}
}
