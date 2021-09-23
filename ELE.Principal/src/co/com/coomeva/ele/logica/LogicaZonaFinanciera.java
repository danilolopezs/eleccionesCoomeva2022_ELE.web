package co.com.coomeva.ele.logica;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import co.com.coomeva.ele.entidades.planchas.EleZonasFinanciero;
import co.com.coomeva.ele.entidades.planchas.EleZonasFinancieroDAO;
import co.com.coomeva.util.acceso.UtilAcceso;

public class LogicaZonaFinanciera extends EleZonasFinancieroDAO{
	private static LogicaZonaFinanciera instance;

	//Constructor de la clase
	private LogicaZonaFinanciera() {
	}

	//Patròn Singular
	public static LogicaZonaFinanciera getInstance() {
		if (instance == null) {
			instance = new LogicaZonaFinanciera();
		}
		return instance;
	}
	/**
	 * Consulta un EleZonasFinanciero mediante el codigo
	 * @param codZonaFin
	 * @return EleZonasFinanciero
	 * @throws Exception
	 */
	
	public EleZonasFinanciero consultarZonaFinanciero(String codZonaFin) throws Exception{
		
		if (codZonaFin==null||codZonaFin.equalsIgnoreCase("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noCodZonaFin"));
		}
		
		EleZonasFinanciero zonasFinanciero = new EleZonasFinanciero();
		
		Criteria criteria = getSession().createCriteria(EleZonasFinanciero.class);
		
		criteria.add(Restrictions.eq("id.codZonaFin", codZonaFin));
		try {
			zonasFinanciero = (EleZonasFinanciero)criteria.uniqueResult();
		} catch (Exception e) {
			throw e;
		}finally{
			this.getSession().flush();
		}
	
		
		
		return zonasFinanciero;
	}

}