package co.com.coomeva.ele.logica;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.util.date.ManipulacionFechas;
import co.com.coomeva.util.resources.LoaderResourceElements;

public class LogicaProceso {
	
	private static LogicaProceso instance;
	private LoaderResourceElements loaderResourceElements = LoaderResourceElements.getInstance();
	
	private LogicaProceso(){
		
	}
	
	public static LogicaProceso getInstance(){
		if (instance == null) {
			instance = new LogicaProceso();
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
		String fecha = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {						
			Query query = session.getNamedQuery("consulta.fecha.proceso.por.codigo");
			query.setLong("codProceso", codigoProceso);
			Date fecProceso = (Date)query.uniqueResult();
			fecha = ManipulacionFechas.dateToString(fecProceso, ConstantesProperties.FORMATO_FECHA);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error generando reporte novedades aplicadas: "+e.getMessage());
			throw new Exception(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
					"consulta.fecha.proceso.por.codigo"));
		}finally{
			
		}
		return fecha;
	}
	
	public String consultaFechaCorteProcesoPorCodigo(Long codigoProceso) throws Exception{
		String fecha = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {						
			Query query = session.getNamedQuery("consulta.fechaCorte.proceso.por.codigo");
			query.setLong("codProceso", codigoProceso);
			Date fecProceso = (Date)query.uniqueResult();
			fecha = ManipulacionFechas.dateToString(fecProceso, ConstantesProperties.FORMATO_FECHA);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error generando reporte novedades aplicadas: "+e.getMessage());
			throw new Exception(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
					"consulta.fechacorte.proceso.por.codigo"));
		}finally{
			
		}
		return fecha;
	}
	
	
	/**
	 * Metodo que consulta la fecha del ultimo proceso ejecutado
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 23/11/2012
	 * @return
	 * @throws Exception
	 */
	public String consultaFechaUltimoProcesoEjecutado()throws Exception{
		String fecha = null;
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		try {						
			Query query = session.getNamedQuery("consulta.fecha.ultimo.proceso.ejecutado");
			Date fecProceso = (Date)query.uniqueResult();
			fecha = ManipulacionFechas.dateToString(fecProceso, ConstantesProperties.FORMATO_FECHA);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error generando reporte novedades aplicadas: "+e.getMessage());
			throw new Exception(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES_LOGICA_PRINCIPAL,
					"consulta.fecha.ultimo.proceso.ejecutado"));
		}finally{
			
		}
		return fecha;
	}

}
