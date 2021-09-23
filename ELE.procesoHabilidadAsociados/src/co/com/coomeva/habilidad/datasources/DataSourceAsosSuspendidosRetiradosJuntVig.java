package co.com.coomeva.habilidad.datasources;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;

/**
 * DataSource para la regla que se encarga de verificar qué
 * asociados se encuentran suspendidos por la junta de vigilancia
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
 * @project ELE.procesoHabilidadAsociados
 * @class DataSourceAsosSuspendidos
 * @date 6/11/2012
 */
public class DataSourceAsosSuspendidosRetiradosJuntVig extends DataSourceReglaHabAsociado {

	@Override
	public List<? extends Object> obtenerDatosRegla(Object... parametros) {
		Session session=null;
		Query query=null;
		session= HibernateSessionFactoryElecciones2012.getSession();
		query = session.getNamedQuery(ConstantesNamedQueries.QUERY_ASOCIADOS_SUSPENDIDOS_RETIRADOS_ACTIVOS);
		
		try {
			return query.list();
		} finally{
			if(session!=null){
				session.close();
			}
			query=null;
			session = null;
		}
	}

}
