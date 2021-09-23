package co.com.coomeva.habilidad.datasources;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;
import co.com.coomeva.habilidad.vo.VOAsosObligacionesFinanMora;

public class DataSourceAsosObligacionesFinan extends
		DataSourceReglaHabAsociado {

	@Override
	public List<? extends Object> obtenerDatosRegla(Object... parametros) {
		Session session=null;
		Query query=null;
		session= HibernateSessionFactoryElecciones2012.getSession();
		query = session.getNamedQuery(ConstantesNamedQueries.QUERY_ASOCIADOS_MORA_OBLIGACIONES_FINANCIERAS);
		
		List<Object[]> datosConsulta = query.list();
		List<VOAsosObligacionesFinanMora> listaAsosEnMora = null;
		
		try {
			if(datosConsulta != null && !datosConsulta.isEmpty()){
				listaAsosEnMora = new ArrayList<VOAsosObligacionesFinanMora>();
				for (Object[] registro : datosConsulta) {
					VOAsosObligacionesFinanMora registroAsoEnMora = new VOAsosObligacionesFinanMora();
					registroAsoEnMora.setNumintAsociado(registro[0] != null ? (Long)registro[0] : null);
					registroAsoEnMora.setValorMoraMultiactiva(registro[1] != null ? (Long)registro[1] : null);
					registroAsoEnMora.setValorMoraFinanciera(registro[2] != null ? (Long)registro[2] : null);
					listaAsosEnMora.add(registroAsoEnMora);
				}
			}
		} finally{
			datosConsulta = null;
			if(session!=null){
				session.close();
			}
			query=null;
			session = null;
		}
		
		return listaAsosEnMora;
	}
	
	@Override
	public List<Long> obtenerAsosCuentaAhorrosActivasDebitoAuto() {
		Session session = null;
		Query query = null;
		try {
			session = HibernateSessionFactoryElecciones2012.getSession();
			query = session
					.getNamedQuery(ConstantesNamedQueries.QUERY_ASOCIADOS_CUENTAS_AHORRO_ACTIVAS_DEB_AUTO);
			return query.list();
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
			query = null;
		}
	}
	
	@Override
	public List<Long> obtenerAsosReestrucProdsFinanciera() {
		Session session = null;
		Query query = null;
		try {
			session = HibernateSessionFactoryElecciones2012.getSession();
			query = session
					.getNamedQuery(ConstantesNamedQueries.QUERY_ASOCIADOS_REESTRUCTURADOS_FINANCIERA);
			return query.list();
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
			query = null;
		}
	}
	
	@Override
	public List<Long> obtenerAsosReestrucProdsMultiactiva() {
		Session session = null;
		Query query = null;
		try {
			session = HibernateSessionFactoryElecciones2012.getSession();
			query = session
					.getNamedQuery(ConstantesNamedQueries.QUERY_ASOCIADOS_REESTRUCTURADOS_MULTIACTIVA);
			return query.list();
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
			query = null;
		}
	}
	
	@Override
	public List<Long> obtenerAsosConPagosOtraEntidades() {
		Session session = null;
		Query query = null;
		try {
			session = HibernateSessionFactoryElecciones2012.getSession();
			query = session
					.getNamedQuery(ConstantesNamedQueries.QUERY_ASOCIADOS_PAGOS_OTRAS_ENTIDADES);
			return query.list();
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
			query = null;
		}
	}
	
	@Override
	public List<Long> obtenerAsosCubiertosPorFondoCalamidad() {
		Session session = null;
		Query query = null;
		try {
			session = HibernateSessionFactoryElecciones2012.getSession();
			query = session
					.getNamedQuery(ConstantesNamedQueries.QUERY_ASOCIADOS_CUBIERTOS_FONDO_CALAMIDAD);
			return query.list();
		} finally {
			session = null;
			query = null;
		}
	}
}
