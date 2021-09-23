package co.com.coomeva.habilidad.utilidades;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;

/**
 * Clase que se encarga de generar la PK para algunas tablas que no tienen asociado
 * un consecutivo
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
 * @project ELE.procesoHabilidadAsociados
 * @class GeneradorPK
 * @date 7/11/2012
 *
 */
public class GeneradorPK {
	
	public static Long generarPKTabla(String esquema, String tabla, String atributo){
		Session session=null;
		SQLQuery query=null;
		try {
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT MAX (");
			consulta.append(atributo);
			consulta.append(") MAX_VALOR ");
			consulta.append("FROM ");
			consulta.append(esquema);
			consulta.append(".");
			consulta.append(tabla);
			session= HibernateSessionFactoryElecciones2012.getSession();
			query = session.createSQLQuery(consulta.toString());
			query.addScalar("MAX_VALOR", Hibernate.LONG);
			return ((Long)query.list().get(0))+ 1;
		} finally {
			session=null;
			query=null;
		}
	}
}
