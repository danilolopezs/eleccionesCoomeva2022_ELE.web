package co.com.coomeva.habilidad.datasources;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;
import co.com.coomeva.habilidad.utilidades.Constantes;
import co.com.coomeva.habilidad.utilidades.ConstantesParametros;
import co.com.coomeva.util.resources.LoaderResourceElements;

/**
 * Obtiene la información necesaria para la validación de la regla que 
 * verifica que la antiguedad del asociado sea igual o superior a 6 meses
 * tomando como fechas de referencia, las fechas de ingreso y la fecha de
 * las votaciones
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
 * @project ELE.procesoHabilidadAsociados
 * @class DataSourceAntAso6Meses
 * @date 1/11/2012
 */

public class DataSourceAsosAnt6Meses extends DataSourceReglaHabAsociado {

	@Override
	public List<? extends Object> obtenerDatosRegla(Object... parametros) {
		Session session=null;
		Query query=null;
		try {
			
			String fechaReferenciaValAntAsociado = null;
			try {
				fechaReferenciaValAntAsociado = LoaderResourceElements
						.getInstance()
						.getKeyResourceValue(
								Constantes.NOMBRE_ARCHIVO_PARAMETROS,
								ConstantesParametros.COD_PARAMETRO_FECHA_REFERENCIA_ANTIGUEDAD_ASOS_6_MESES);
			} catch (Exception e) {
				throw new NullPointerException(
						"No se logró obtener el parámetro para ejecutar"
								+ " la regla de los asociados con antiguedad inferior a 6 meses");
			}
			
			session= HibernateSessionFactoryElecciones2012.getSession();
			query = session.getNamedQuery(ConstantesNamedQueries.QUERY_ASOCIADOS_FECHA_INGRESO_SUPERIOR_DET_FECHA);
			query.setParameter("fecha6MesesAntesVotacion", Long.parseLong(fechaReferenciaValAntAsociado));
			return query.list();
		} finally {
			if(session != null){
				session.close();
			}
			session = null;
			query=null;
		}
	}
}
