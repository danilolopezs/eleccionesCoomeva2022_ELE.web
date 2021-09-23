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
import co.com.coomeva.ele.entidades.planchas.EleSuplentes;
import co.com.coomeva.ele.entidades.planchas.EleSuplentesDAO;
import co.com.coomeva.ele.entidades.planchas.HibernateSessionFactoryPlanchas;
import co.com.coomeva.ele.modelo.ElePrincipalesDTO;
import co.com.coomeva.ele.modelo.EleSuplentesDTO;
import co.com.coomeva.ele.util.Validador;
import co.com.coomeva.util.acceso.UtilAcceso;

public class LogicaSuplente extends EleSuplentesDAO{
	private static LogicaSuplente instance;

	//Constructor de la clase
	private LogicaSuplente() {
	}

	//Patròn Singular
	public static LogicaSuplente getInstance() {
		if (instance == null) {
			instance = new LogicaSuplente();
		}
		return instance;
	}
	/**
	 * Crear el suplente en la base de datos
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroSuIdentificacion
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

	public void crearSuplente(String nroSuIdentificacion, String nroPriIdentificacion, 
			String nroCabPlancha, String primerNombre, String segundoNombre,
			String primerApellido, String segundoApellido, String profesion, String email, Long orden) throws Exception{

		if (nroSuIdentificacion == null || nroSuIdentificacion.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumIdentificacion"));
		}
		if (nroPriIdentificacion == null || nroPriIdentificacion.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumIdentificacion"));
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


		if (eleCabPlancha == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noExistePlancha"));
		}

		ElePrincipales elPrincipales = DelegadoPrincipal.getInstance().consultarPrincipal(nroPriIdentificacion);

		if (elPrincipales == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noExistePlancha"));
		}

		EleSuplentes elSuplentes = new EleSuplentes();
		elSuplentes = DelegadoSuplente.getInstance().consultarSuplente(nroSuIdentificacion);

		if (elSuplentes != null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "existeIdPlancha"));
		}

		ElePlanchas elPlanchas = DelegadoPlanchas.getInstance().consultarPlancha(nroCabPlancha);

		if (elPlanchas == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noExistePlancha"));
		} 

		elSuplentes = new EleSuplentes();
		elSuplentes.setNroSuIdentificacion(nroSuIdentificacion);
		elSuplentes.setElePrincipales(elPrincipales);
		elSuplentes.setElePlanchas(elPlanchas);
		elSuplentes.setEmail(email);
		elSuplentes.setPrimerNombre(primerNombre);
		elSuplentes.setPrimerApellido(primerApellido);
		elSuplentes.setProfesion(profesion);

		if (segundoApellido != null && !segundoApellido.trim().equalsIgnoreCase("")) {
			elSuplentes.setSegundoApellido(segundoApellido);
		}
		if (orden != null && orden>0) {
			elSuplentes.setOrden(orden);
		}

		if (segundoNombre != null && !segundoNombre.trim().equalsIgnoreCase("")) {
			elSuplentes.setSegundoNombre(segundoNombre);
		}


		save(elSuplentes);



	}
	/**
	 * Consulta un Suplente en la Base de Datos
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param nroSuIdentificacion
	 * @return EleSuplentes
	 * @throws Exception
	 */

	public EleSuplentes consultarSuplente(String nroSuIdentificacion) throws Exception{
		if (nroSuIdentificacion == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumIdentificacion"));

		}
		EleSuplentes elSuplentes = 	null;
		try {
			elSuplentes = 	findById(nroSuIdentificacion);
		} catch (Exception e) {
			throw e;
		}finally{
			this.getSession().flush();
		}



		return elSuplentes;

	}
	/**
	 * Consulta los suplentes asociados a un cabeza de plancha en DTO
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param documento
	 * @return List<EleSuplentesDTO>
	 * @throws Exception
	 */

	public List<EleSuplentesDTO> consultarSuplentes(String documento) throws Exception 
	{
		if (documento == null || documento.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumIdentificacion"));
		}

		Criteria criteria = HibernateSessionFactoryPlanchas.getSession().createCriteria(EleSuplentes.class);

		criteria.add(Restrictions.eq("elePlanchas.nroCabPlancha", documento));
		criteria.addOrder(Order.asc("orden"));
		List<EleSuplentes> lista  = null;
		List<EleSuplentesDTO> returnList = new ArrayList<EleSuplentesDTO>();
		try {
			lista = criteria.list();

			EleSuplentesDTO eleSuplentesDTO;

			for (EleSuplentes eleSuplentes : lista) {
				eleSuplentesDTO =  new EleSuplentesDTO(eleSuplentes);
				returnList.add(eleSuplentesDTO);
			}

		} catch (Exception e) {
			throw e;
		}finally{
			this.getSession().flush();
		}

		return returnList;
	}
	/**
	 * Consulta los suplentes asociados a un cabeza de plancha
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param documento
	 * @return
	 * @throws Exception
	 */

	public List<EleSuplentes> consultaSuplentes(String documento) throws Exception 
	{
		if (documento == null || documento.trim().equals("")) {
			throw new Exception(UtilAcceso.getParametroFuenteS("mensajes", "noNumIdentificacion"));
		}

		Criteria criteria = HibernateSessionFactoryPlanchas.getSession().createCriteria(EleSuplentes.class);
		criteria.add(Restrictions.eq("elePlanchas.nroCabPlancha", documento));
		criteria.addOrder(Order.asc("orden"));
		List<EleSuplentes> lista = null;
		try {
			lista = criteria.list();
		} catch (Exception e) {
			throw e;
		}finally{
			this.getSession().flush();
		}





		return lista;
	}
	/**
	 * Elimina los suplentes asociados a un cabeza de plancha
	 * @author Manuel Galvez y Ricardo Chiribogas
	 * @param documento
	 * @throws Exception
	 */
	public void eliminarSuplentes(String documento) throws Exception
	{
		List<EleSuplentes> listEleSuplentes = consultaSuplentes(documento);

		for (EleSuplentes eleSuplentes : listEleSuplentes) 
		{
			delete(eleSuplentes);
			DelegadoHabilidad.getInstance().removerInhabilidades(eleSuplentes.getNroSuIdentificacion());
		}
	}
	/**
	 * Crea los suplentes asociados a un cabeza de plancha
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param listaSuplentes
	 * @throws Exception
	 */

	public void crearSuplentes(List<EleSuplentesDTO> listaSuplentes) throws Exception
	{
		Long cont = 1l;
		for (EleSuplentesDTO eleSuplentesDTO : listaSuplentes) 
		{
			DelegadoSuplente.getInstance().crearSuplente(eleSuplentesDTO.getNroSuIdentificacion(),eleSuplentesDTO.getElePrincipales().getNroPriIdentificacion(), eleSuplentesDTO.getElePlanchas().getNroCabPlancha(), eleSuplentesDTO.getPrimerNombre(), eleSuplentesDTO.getSegundoNombre(), eleSuplentesDTO.getPrimerApellido(), eleSuplentesDTO.getSegundoApellido(), eleSuplentesDTO.getProfesion(), eleSuplentesDTO.getEmail(),cont);
			DelegadoHabilidad.getInstance().inscribirInhabilidades(eleSuplentesDTO.getInhabilidades());
			cont++;
		}
	}
	
	public void modificarProfesionSuplente(String profesion, String id) throws Exception
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
			
			EleSuplentes eleSuplen = consultarSuplente(id);

			eleSuplen.setProfesion(profesion);
			
			merge(eleSuplen);
			
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
		sql.append(" FROM ELECCION.ELE_SUPLENTES");
		sql.append(" WHERE NRO_SU_IDENTIFICACION =:id ");


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
