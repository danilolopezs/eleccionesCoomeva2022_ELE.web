package co.com.coomeva.ele.logica;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.planchas.dao.EleFormatoPlanchaDAO;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFormato;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFormatoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlancha;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;
import co.com.coomeva.ele.utilidades.GeneradorConsecutivos;

public class LogicaFormatoPlanchas extends EleFormatoPlanchaDAO {
	private static LogicaFormatoPlanchas instance;

	private LogicaFormatoPlanchas() {
	}

	public static LogicaFormatoPlanchas getInstance() {
		if (instance == null) {
			instance = new LogicaFormatoPlanchas();
		}
		return instance;
	}

	/**
	 * Metodo que guarda un registro en la tabla ELE_FORMATO_PLANCHA
	 * 
	 * @author Mario Alejandro Acevedo
	 * @param usuarioImprime
	 * @param fechaImprime
	 * @param codFormato
	 * @param consecPlancha
	 */
	public void crearFormatoPlancha(String usuarioImprime, Timestamp fechaImprime, Long codFormato, Long consecPlancha)
			throws Exception {
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			Long codigoFormatoPlancha = GeneradorConsecutivos.getInstance().getConsecutivo(
					"obtener.consecutivo.formato.plancha", HibernateSessionFactoryElecciones2012.getSession());

			EleFormatoPlancha eleFormatoPlancha = new EleFormatoPlancha();
			eleFormatoPlancha.setConsecutivo(codigoFormatoPlancha);
			eleFormatoPlancha.setUsuarioImpresion(usuarioImprime);
			eleFormatoPlancha.setFechaImpresion(fechaImprime);
			eleFormatoPlancha.setElePlancha(new ElePlancha(consecPlancha));
			eleFormatoPlancha.setEleFormato(new EleFormato((new Byte(codFormato.toString()))));

			save(eleFormatoPlancha);
			tr.commit();

		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}

	}

	/**
	 * Metodo que verifica si una plancha está radicada
	 * 
	 * @author Carlos Ernesto Ortega Q.
	 * @param consecutivoPlancha
	 */
	public boolean esPlanchaRadicada(Long consecutivoPlancha) throws Exception {
		List<Object[]> objectList = null;
		Session session = getSession();
		try {
			Query queryObject = session.getNamedQuery(ConstantesNamedQueries.QUERY_CONSULTAR_ES_PLANCHA_RADICADA);
			queryObject.setLong("consecutivoPlancha", consecutivoPlancha);
			objectList = queryObject.list();
			if (objectList != null && !objectList.isEmpty()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("No se logró consultar si la plancha está radicada" + e.toString());
			throw new Exception(e.getMessage());
		} finally {
			session.close();
		}

		return false;
	}

}