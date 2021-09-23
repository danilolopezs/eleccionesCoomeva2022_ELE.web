package co.com.coomeva.ele.logica;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import co.com.coomeva.ele.delegado.DelegadoCabezaPlancha;
import co.com.coomeva.ele.delegado.DelegadoHabilidad;
import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.delegado.DelegadoPrincipal;
import co.com.coomeva.ele.delegado.DelegadoSuplente;
import co.com.coomeva.ele.entidades.planchas.EleCabPlancha;
import co.com.coomeva.ele.entidades.planchas.ElePlanchas;
import co.com.coomeva.ele.entidades.planchas.ElePrincipales;
import co.com.coomeva.ele.entidades.planchas.ElePrincipalesDAO;
import co.com.coomeva.ele.entidades.planchas.EleSuplentes;
import co.com.coomeva.ele.modelo.ElePrincipalesDTO;
import co.com.coomeva.ele.util.Validador;
import co.com.coomeva.util.acceso.UtilAcceso;

public class LogicaPrincipal extends ElePrincipalesDAO{
	private static LogicaPrincipal instance;

	//Constructor de la clase
	private LogicaPrincipal() {
	}

	//Patròn Singular
	public static LogicaPrincipal getInstance() {
		if (instance == null) {
			instance = new LogicaPrincipal();
		}
		return instance;
	}
	/**
	 * Crear un principal en la plancha
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroPriIdentificacion
	 * @param nroCabPlancha
	 * @param primerNombre
	 * @param segundoNombre
	 * @param primerApellido
	 * @param segundoApellido
	 * @param profesion
	 * @param email
	 * @throws Exception
	 */
	public void crearPrincipalPlancha(String nroPriIdentificacion,String nroCabPlancha, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
			String profesion, String email, Long orden)throws Exception{
		if (nroPriIdentificacion == null || nroPriIdentificacion.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumIdentificacionCabeza"));
		}

		if (primerNombre == null || primerNombre.trim().equals("")) {
			//throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noPrimerNombre"));
			primerNombre = " ";
		}

		if (primerApellido == null || primerApellido.trim().equals("")) {
			//throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noPrimerApellido"));
			primerApellido = " ";
		}


		if (profesion == null || profesion.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noProfesion"));
		}

		EleCabPlancha eleCabPlancha = DelegadoCabezaPlancha.getInstance().consultarCabezaPlancha(nroCabPlancha);

		if (eleCabPlancha!=null) {
			if (!eleCabPlancha.getNroIdentificacion().equalsIgnoreCase(nroCabPlancha)) {
				throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "existeIdPlancha"));
			}
		}else
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noExistePlancha"));

		ElePrincipales elPrincipales = DelegadoPrincipal.getInstance().consultarPrincipal(nroPriIdentificacion);

		if (elPrincipales != null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "existeIdPlancha"));
		}

		EleSuplentes eleSuplentes = DelegadoSuplente.getInstance().consultarSuplente(nroPriIdentificacion);

		if (eleSuplentes != null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "existeIdPlancha"));
		}

		ElePlanchas elPlanchas = DelegadoPlanchas.getInstance().consultarPlancha(nroCabPlancha);

		if (elPlanchas == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noExistePlancha"));
		} 
		elPrincipales = new ElePrincipales();
		elPrincipales.setNroPriIdentificacion(nroPriIdentificacion);
		elPrincipales.setElePlanchas(elPlanchas);
		elPrincipales.setEmail(email);
		elPrincipales.setPrimerNombre(primerNombre);
		elPrincipales.setPrimerApellido(primerApellido);
		elPrincipales.setProfesion(profesion);

		if (segundoApellido != null && !segundoApellido.trim().equalsIgnoreCase("")) {
			elPrincipales.setSegundoApellido(segundoApellido);
		}
		
		if (orden != null && orden>0) {
			elPrincipales.setOrden(orden);
		}

		if (segundoNombre != null && !segundoNombre.trim().equalsIgnoreCase("")) {
			elPrincipales.setSegundoNombre(segundoNombre);
		}
		save(elPrincipales);
	}
	/**
	 * Consulta un principal en la base de datos
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroPriIdentificacion
	 * @return ElePrincipales
	 * @throws Exception
	 */

	public ElePrincipales consultarPrincipal(String nroPriIdentificacion) throws Exception{
		if (nroPriIdentificacion == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumIdentificacionCabeza"));
		}
		ElePrincipales elPrincipales = null;
		try {
			
			elPrincipales = findById(nroPriIdentificacion);
			
		} catch (Exception e) {
			
			throw e;
			
		}finally{
			
			this.getSession().flush();
			
		}



		return elPrincipales;
	}
	/**
	 * Consulta una lista de principales asignada a un cabeza de plancha
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param documento
	 * @return List<ElePrincipalesDTO>
	 * @throws Exception
	 */
	public List<ElePrincipalesDTO> consultarPrincipales(String documento) throws Exception {
		if (documento == null || documento.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumIdentificacionCabeza"));
		}
		Criteria criteria = this.getSession().createCriteria(ElePrincipales.class);
		List<ElePrincipales> lista = null;
		List<ElePrincipalesDTO> returnList = new ArrayList<ElePrincipalesDTO>();
		try {
			criteria.add(Restrictions.eq("elePlanchas.nroCabPlancha", documento));
			criteria.addOrder(Order.asc("orden"));
			lista = criteria.list();
			ElePrincipalesDTO elePrincipalesDTO;
			for (ElePrincipales elePrincipales : lista) {
				elePrincipalesDTO =  new ElePrincipalesDTO(elePrincipales);
				returnList.add(elePrincipalesDTO);
			}

		} catch (Exception e) {
			throw e;
		}finally{
			this.getSession().flush();
		}



		return returnList;
	}
	/**
	 * Consulta los principales principales inscritos a un cabeza
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param documento
	 * @return List<ElePrincipales>
	 * @throws Exception
	 */

	public List<ElePrincipales> consultaPrincipales(String documento) throws Exception {
		if (documento == null || documento.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumIdentificacionCabeza"));
		}
		Criteria criteria = this.getSession().createCriteria(ElePrincipales.class);
		List<ElePrincipales> lista = null;
		try {
			criteria.add(Restrictions.eq("elePlanchas.nroCabPlancha", documento));
			criteria.addOrder(Order.asc("orden"));
			lista = criteria.list();
		} catch (Exception e) {
			throw e;
		}finally{
			this.getSession().flush();
		}


		return lista;
	}
	/**
	 * Eliminar los principales pertenecientes a un cabeza de plancha
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param documento
	 * @throws Exception
	 */

	public void eliminarPrincipales(String documento) throws Exception
	{
		List<ElePrincipales> listElePrincipales = consultaPrincipales(documento);

		for (ElePrincipales elePrincipales : listElePrincipales) 
		{
			delete(elePrincipales);
			DelegadoHabilidad.getInstance().removerInhabilidades(elePrincipales.getNroPriIdentificacion());
		}
	}
	/**
	 * Metodo que crea todos los principales de una plancha
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param listaPrincipales
	 * @throws Exception
	 */
	public void crearPrincipales(List<ElePrincipalesDTO> listaPrincipales) throws Exception
	{
		Long cont = 1l;
		for (ElePrincipalesDTO elePrincipalesDTO : listaPrincipales) 
		{
			
			DelegadoPrincipal.getInstance().crearPrincipalPlancha(elePrincipalesDTO.getNroPriIdentificacion(), elePrincipalesDTO.getElePlanchas().getNroCabPlancha(), elePrincipalesDTO.getPrimerNombre(), elePrincipalesDTO.getSegundoNombre(), elePrincipalesDTO.getPrimerApellido(), elePrincipalesDTO.getSegundoApellido(), elePrincipalesDTO.getProfesion(), elePrincipalesDTO.getEmail(),cont);
			DelegadoHabilidad.getInstance().inscribirInhabilidades(elePrincipalesDTO.getInhabilidades());
			cont++;
		}
	}
	
	
	public void modificarProfesionPrincipal(String profesion, String id) throws Exception
	{
		if (profesion.trim().equals("") || profesion == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noProfesion"));
		}
		if (id.trim().equals("") || id == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noId"));
		}
		
		Transaction transaction = null;
		
		try 
		{
			transaction = this.getSession().beginTransaction();
			
			ElePrincipales elePrinci = consultarPrincipal(id);

			elePrinci.setProfesion(profesion);
			
			merge(elePrinci);
			
			this.getSession().flush();
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction!=null) {
				transaction.rollback();
			}
			throw e;
		}finally{
			this.getSession().clear();
			this.getSession().close();
		}
	}
	
	public String findPlanchasProfesionNative(String id) throws Exception {
		StringBuffer sql = new StringBuffer();
		SQLQuery sqlQuery = null;

		sql.append(" SELECT PROFESION ");
		sql.append(" FROM ELECCION.ELE_PRINCIPALES");
		sql.append(" WHERE NRO_PRI_IDENTIFICACION =:id ");


		sqlQuery = getSession().createSQLQuery(sql.toString());

		//CONFIGURA TIPO DATO COLUMNAS SELECT
		sqlQuery.addScalar("PROFESION",Hibernate.STRING);
		Object returnOb =  new Object();
		String estado = null;

		try {
			sqlQuery.setString("id", id);
			returnOb = sqlQuery.uniqueResult();
			if (returnOb!=null) {
				estado =  returnOb.toString();
			}
		} catch (Exception e) {
			throw e;
		}
		return estado;
	}
}
