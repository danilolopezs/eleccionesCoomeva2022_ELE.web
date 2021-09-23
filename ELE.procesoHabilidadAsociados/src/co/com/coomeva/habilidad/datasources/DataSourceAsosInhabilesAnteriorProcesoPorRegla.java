package co.com.coomeva.habilidad.datasources;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.habilidad.EleProcesoRegla;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;
import co.com.coomeva.habilidad.utilidades.Constantes;
import co.com.coomeva.habilidad.vo.VOAsosObligacionesFinanMora;

/**
 * Datasource que permite obtener los asociados inhábiles para un anterior proceso
 * y que permite filtrarlo por una regla
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
 * @project ELE.procesoHabilidadAsociados
 * @class DataSourceAsosInhabilesAnteriorProcesoPorRegla
 * @date 16/11/2012
 *
 */
public class DataSourceAsosInhabilesAnteriorProcesoPorRegla extends
		DataSourceAsosObligacionesFinan {
	
	/**
	 * Obtiene los asociados que resultaron inhabiles en la anterior ejecución
	 * al proceso actual y que fueron registrados por determinada regla
	 */
	public List<? extends Object> obtenerDatosRegla(Object... parametros) {
		Session session=null;
		Query query=null;
		List<Object[]> datosConsulta = null;
		List<VOAsosObligacionesFinanMora> listaAsosEnMora = null;
		try {
			EleProcesoRegla procesoRegla = (EleProcesoRegla)parametros[0];
			session= HibernateSessionFactoryElecciones2012.getSession();
			query = session.getNamedQuery(ConstantesNamedQueries.QUERY_ASOCIADOS_INHABILES_ANTERIOR_PROCESO_POR_REGLA);
			query.setLong("estadoProcesoEjecutado", Long.parseLong(Constantes.CODIGO_ESTADO_PROCESO_EJECUTADO));
			query.setLong("codigoProcesoActual", procesoRegla.getEleProceso().getCodigoProceso());
			query.setLong("codigoRegla", procesoRegla.getEleRegla().getCodigoRegla());
			
			datosConsulta = query.list();
			
			if(datosConsulta != null && !datosConsulta.isEmpty()){
				listaAsosEnMora = new ArrayList<VOAsosObligacionesFinanMora>();
				for (Object[] registro : datosConsulta) {
					VOAsosObligacionesFinanMora registroAsoEnMora = new VOAsosObligacionesFinanMora();
					registroAsoEnMora.setNumintAsociado(registro[0] != null ? (Long)registro[0] : null);
					registroAsoEnMora.setObservacionInhabilidad(registro[1] != null ? registro[1].toString() : null);
					registroAsoEnMora.setValorMoraMultiactiva(registro[2] != null ? (Long)registro[2] : null);
					registroAsoEnMora.setValorMoraFinanciera(registro[3] != null ? (Long)registro[3] : null);
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

}
