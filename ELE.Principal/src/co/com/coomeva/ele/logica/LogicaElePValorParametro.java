package co.com.coomeva.ele.logica;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import co.com.coomeva.ele.entidades.planchas.ElePValorParametros;
import co.com.coomeva.ele.entidades.planchas.ElePValorParametrosDAO;
import co.com.coomeva.ele.modelo.Parametro;


public class LogicaElePValorParametro extends ElePValorParametrosDAO{

	private static LogicaElePValorParametro instance = null;
	
	public static LogicaElePValorParametro getInstance() {
		
		if (instance == null) {
			instance = new LogicaElePValorParametro();
		}
		
		return instance;
	}
	
	/**
	 * Obtiene de la tabla ElePValorParametros el conjunto de parametros que le corespondan.
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param parametro 
	 * @return List<ElePValorParametros>
	 */
	public List<ElePValorParametros> listaParametros(Parametro parametro){
		List<ElePValorParametros> losParametros = null;
		StringBuffer buffer = null;
		Query query = null;
		try {
			buffer = new StringBuffer();
			buffer.append("SELECT p ");
			buffer.append("  FROM ElePValorParametros p ");
			buffer.append(" WHERE p.codigoValParametro > 0  ");
			if(parametro != null){
				buffer.append(" AND p.elePParametros.codigoParametro = ");
				buffer.append(parametro.getParametro().getCodigoParametro());
			}
			
			query = this.getSession().createQuery(buffer.toString());
			
			losParametros = query.list();
			
		} catch (Exception e) {
			e.printStackTrace();
			losParametros = new ArrayList<ElePValorParametros>();
		}
		finally{
			buffer = null;
			query = null;
			this.getSession().flush();
		}
		return losParametros;
	}
	
	
	
}
