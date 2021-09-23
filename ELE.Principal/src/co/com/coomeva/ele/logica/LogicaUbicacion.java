package co.com.coomeva.ele.logica;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import co.com.coomeva.ele.entidades.admhabilidad.HibernateSessionFactoryHab;
import co.com.coomeva.ele.entidades.admhabilidad.Zonaregio1;
import co.com.coomeva.ele.entidades.planchas.HibernateSessionFactoryPlanchas;
import co.com.coomeva.util.acceso.UtilAcceso;

public class LogicaUbicacion{
	private static LogicaUbicacion instance;

	//	Constructor de la clase
	private LogicaUbicacion() {
	}

	//	Patròn Singular
	public static LogicaUbicacion getInstance() {
		if (instance == null) {
			instance = new LogicaUbicacion();
		}
		return instance;
	}
	/**
	 * Consulta las regionales de coomeva
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @return List<Object[]>
	 * @throws Exception
	 */
	public List<Object[]> consultarRegionales() throws Exception{
		Criteria criteria =  HibernateSessionFactoryHab.getSession().createCriteria(Zonaregio1.class);
		criteria.setProjection(Projections.distinct(Projections.projectionList().
				add(Projections.property("id.codreg")).
				add(Projections.property("id.codnom"))));
		criteria.addOrder(Order.asc("id.codreg"));

		return criteria.list();
	}
	/**
	 * Consulta todas las zonas en una regional
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param regional
	 * @return List<Object[]>
	 */
	public List<Object[]> consultarZonas(Long regional)throws Exception{
		Criteria criteria =  HibernateSessionFactoryHab.getSession().createCriteria(Zonaregio1.class);
		criteria.add(Restrictions.eq("id.codreg", regional));
		criteria.setProjection(Projections.distinct(Projections.projectionList().
				add(Projections.property("id.zona")).
				add(Projections.property("id.codnom01"))));
		criteria.addOrder(Order.asc("id.zona"));
		List<Object[]> listaObjetos = null;
		
		try {
			listaObjetos = criteria.list();
		} catch (Exception e) {
			throw e;
		}
		return listaObjetos;

	}
	/**
	 * Consulta todas las oficinas inscribiendo una regional y una zona
	 * @param regional
	 * @param zona
	 * @return List<Object[]>
	 */
	public List<Object[]> consultarOficinas(Long regional,String zona)throws Exception{
		if (regional==null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noRegional"));
		}
		if (zona==null||zona.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noZona"));
		}
		Criteria criteria =  HibernateSessionFactoryHab.getSession().createCriteria(Zonaregio1.class);
		criteria.add(Restrictions.eq("id.codreg", regional));
		criteria.add(Restrictions.eq("id.zona", zona));
		criteria.setProjection(Projections.distinct(Projections.projectionList().
				add(Projections.property("id.agcori")).
				add(Projections.property("id.nomagc"))));
		criteria.addOrder(Order.asc("id.agcori"));
		List<Object[]> lista = null;
		
		try {
			lista = criteria.list(); 			
		} catch (Exception e) {
			throw e;
		}finally{
			HibernateSessionFactoryPlanchas.getSession().flush();
		}
		return lista ;

	}


}
