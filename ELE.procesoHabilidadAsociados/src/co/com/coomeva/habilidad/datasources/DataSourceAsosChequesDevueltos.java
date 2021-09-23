package co.com.coomeva.habilidad.datasources;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;

/**
 * DataSource que retorna los asociados que han pagado sus
 * obligaciones financieras con cheques que resultaron sin fondos
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
 * @project ELE.procesoHabilidadAsociados
 * @class DataSourceAsosChequesDevueltos
 * @date 6/11/2012
 */
public class DataSourceAsosChequesDevueltos extends
		DataSourceReglaHabAsociado {

	@Override
	public List<? extends Object> obtenerDatosRegla(Object... parametros) {
		Session session=null;
		Query query=null;
		try {
			session= HibernateSessionFactoryElecciones2012.getSession();
			query = session.getNamedQuery(ConstantesNamedQueries.QUERY_ASOCIADOS_CHEQUES_DEVUELTOS);
			return query.list();
		} finally {
			session=null;
			query=null;
		}
	}
}
