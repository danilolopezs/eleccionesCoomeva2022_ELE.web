package co.com.coomeva.ele.logica;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListResourceBundle;

import org.dom4j.ElementPath;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import co.com.coomeva.ele.delegado.DelegadoCabezaPlancha;
import co.com.coomeva.ele.delegado.DelegadoExperienciaLaboral;
import co.com.coomeva.ele.delegado.DelegadoLogPlan;
import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.delegado.DelegadoPrincipal;
import co.com.coomeva.ele.delegado.DelegadoSuplente;
import co.com.coomeva.ele.delegado.DelegadoZona;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.lico.HibernateSessionFactoryLico;
import co.com.coomeva.ele.entidades.planchas.EleZonas;
import co.com.coomeva.ele.entidades.planchas.EleCabPlancha;
import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral;
import co.com.coomeva.ele.entidades.planchas.ElePlanchas;
import co.com.coomeva.ele.entidades.planchas.ElePlanchasDAO;
import co.com.coomeva.ele.entidades.planchas.ElePrincipales;
import co.com.coomeva.ele.entidades.planchas.EleSuplentes;
import co.com.coomeva.ele.entidades.planchas.HibernateSessionFactoryPlanchas;
import co.com.coomeva.ele.entidades.planchas.dao.EleEstadoPlanchaDAO;
import co.com.coomeva.ele.entidades.planchas.dao.EleFormatoPlanchaDAO;
import co.com.coomeva.ele.entidades.planchas.dao.ElePlanchaDAO;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleEstadoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFormato;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFormatoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.ElePlancha;
import co.com.coomeva.ele.logica.inscripcion.plancha.ILogicaPlancha;
import co.com.coomeva.ele.logica.inscripcion.plancha.LogicaPlancha;
import co.com.coomeva.ele.modelo.AsociadoReporteDTO;
import co.com.coomeva.ele.modelo.CabezaPlanchaDTO;
import co.com.coomeva.ele.modelo.EleCabPlanchaDTO;
import co.com.coomeva.ele.modelo.EleLogDTO;
import co.com.coomeva.ele.modelo.ElePlanchaDTO;
import co.com.coomeva.ele.modelo.ElePrincipalesDTO;
import co.com.coomeva.ele.modelo.EleSuplentesDTO;
import co.com.coomeva.ele.modelo.EleTablaEmpresaCargoDTO;
import co.com.coomeva.ele.modelo.InfoPlanchaAdmisionPdfDTO;
import co.com.coomeva.ele.modelo.InfoPlanchaConstanciaPdfDTO;
import co.com.coomeva.ele.modelo.InfoPlanchaDTO;
import co.com.coomeva.ele.modelo.ParametroPlanchaDTO;
import co.com.coomeva.ele.modelo.PlanchaPorEstadoDTO;
import co.com.coomeva.ele.modelo.SuspendidoDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.UtilAccesoDb;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;
import co.com.coomeva.ele.utilidades.GeneradorConsecutivos;
import co.com.coomeva.util.acceso.UtilAcceso;

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