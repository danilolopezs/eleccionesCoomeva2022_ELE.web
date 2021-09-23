package co.com.coomeva.habilidad.datasources;

import java.util.List;

/**
 * Clase que sirve como adaptador, ya que algunos datasource de reglas
 * espec�ficas no requieren de implementar todos los m�todos de la interfaz
 * IDataSourceReglaHabAsociado
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londo�o</a> - Premize SAS <br>
 * @project ELE.procesoHabilidadAsociados
 * @class DataSourceReglaHabAsociado
 * @date 7/11/2012
 *
 */
public abstract class DataSourceReglaHabAsociado implements
		IDataSourceReglaHabAsociado {

	@Override
	public List<? extends Object> obtenerDatosRegla(Object... parametros) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Long> obtenerAsosCuentaAhorrosActivasDebitoAuto(){
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Long> obtenerAsosReestrucProdsFinanciera(){
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Long> obtenerAsosReestrucProdsMultiactiva(){
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Long> obtenerAsosCubiertosPorFondoCalamidad(){
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Long> obtenerAsosConPagosOtraEntidades(){
		// TODO Auto-generated method stub
		return null;
	}
	
}
