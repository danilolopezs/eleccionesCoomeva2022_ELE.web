package co.com.coomeva.ele.util;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.modelo.ParametroPlanchaDTO;

public class LectorParametros {
	
	/**Obtiene el registro de un parámetro y un tipo de parámetro
	 *  
	 * @author <a href="mailto:marioa_acevedo_contratista@coomeva.com.co">Mario Alejandro Acevedo</a> - GSISIN <br>
	 * @param codigoParametro
	 * @param tipoParametro
	 * @return ParametroPlanchaDTO
	 */
	public static ParametroPlanchaDTO obtenerParametrosCodigoTipo(Long codigoParametro, Long tipoParametro){
		
		Session session = HibernateSessionFactoryElecciones2012.getSession();
		ParametroPlanchaDTO parametro = new ParametroPlanchaDTO();
		
		StringBuffer sql = new StringBuffer("select p.NOMBRE NOMBRE, p.NOMBRE_CORTO NOMBRE_CORTO from elecdb.ELE_PARAMETRO p, " +
				"elecdb.ELE_PARAMETRO_TIPO pt where p.TIP_COD = :tipoParametro and  p.CODIGO = :codigoParametro and " +
				"pt.TIP_COD = p.TIP_COD and p.ESTADO = 1");

		SQLQuery query = session.createSQLQuery(sql.toString());
		
		query.addScalar("NOMBRE", Hibernate.STRING);
		query.addScalar("NOMBRE_CORTO", Hibernate.STRING);
		
		try {
			query.setLong("codigoParametro", codigoParametro);
			query.setLong("tipoParametro", tipoParametro);
			List<Object[]> datos = (List<Object[]>)query.list();
			
			if (datos != null && datos.size() > 0) {
				for (Object[] objects : datos) {
					parametro.setStrValor(objects[0].toString());
					parametro.setNombre(objects[1].toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			session.close();
			session = null;
		}
		
		return parametro;
	}

}
