package co.com.coomeva.habilidad.reglas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;
import co.com.coomeva.habilidad.datasources.DataSourceAsosAnt6Meses;
import co.com.coomeva.habilidad.datasources.IDataSourceReglaHabAsociado;
import co.com.coomeva.habilidad.exception.AssociateAbilityException;

/**
 * Regla que se encarga de registrar por primera vez los datos en la tabla 
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
 * @project ELE.procesoHabilidadAsociados
 * @class ReglaAsosRegistroInfoBasAso
 * @date 29/11/2012
 *
 */
public class ReglaAsosRegistroInfoBasAso extends ReglaHabAsociado {
	
	private static Logger procesoHabAsociadosLogger = Logger.getLogger(ReglaAsosRegistroInfoBasAso.class);
	
	public ReglaAsosRegistroInfoBasAso(IDataSourceReglaHabAsociado dataSource){
		super(dataSource);
	}

	@Override
	public void ejecutarRegla(Object... var) throws AssociateAbilityException {
		
		Session session=null;
		SQLQuery query=null;
		PreparedStatement stmt = null;
		
		session = HibernateSessionFactoryElecciones2012.getSession();
		Connection con = session.connection();
		query = (SQLQuery)session.getNamedQuery(ConstantesNamedQueries.QUERY_POBLAR_TABLA_ELE_ASOCIADO);
		try {
			
			procesoHabAsociadosLogger.info("\n%%%%%%% Preparando la regla que inicia el llenado de la tabla ELE_ASOCIADO %%%%%%%");
			
			stmt = con.prepareStatement(query.getQueryString());
			//stmt.execute();
			//con.commit();
			
			procesoHabAsociadosLogger.info("\n%%%%%%% Terminó la regla que da llenado de la tabla ELE_ASOCIADO %%%%%%%");
		} catch (SQLException e) {
			procesoHabAsociadosLogger
					.error("\n###############################################  --> Se ha presentado un error intentando poblar la tabla ELE_ASOCIADO. "
							+ e.getMessage());
			throw new AssociateAbilityException(
					"Se ha presentado un error intentando poblar la tabla ELE_ASOCIADO. "
							+ e.getMessage());
		} finally{
			if(stmt != null){
				
				try {
					stmt.close();
				} catch (Exception e2) {
					throw new AssociateAbilityException("Se ha presentado un error cerrando el" +
							" PreparedStatement al intentar llenar la tabla ele_asociado", e2);
				}
			}
			session.close();
			session=null;
			query=null;
			stmt = null;
		}

	}
	
	public static void main(String[] args) {
		
		try {
			IDataSourceReglaHabAsociado ds = new DataSourceAsosAnt6Meses();
			ReglaAsosRegistroInfoBasAso a = new ReglaAsosRegistroInfoBasAso(ds);
			a.ejecutarRegla(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
