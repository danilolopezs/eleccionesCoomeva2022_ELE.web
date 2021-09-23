package co.com.coomeva.ele.logica.inscripcion.plancha;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.planchas.dao.EleAcreditacionOficioDAO;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleAcreditacionOficio;
import co.com.coomeva.ele.exception.EleccionesDelegadosException;

/**
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> -
 *         Premize SAS <br>
 * @project ELE.Principal
 * @class LogicaAcreditacionOficio
 * @date 4/01/2013
 */
public class LogicaAcreditacionOficio implements ILogicaAcreditacionOficio {

	@Override
	public void registrarInfoAcreditacionOficio(Long numintAsociado) throws EleccionesDelegadosException {

		Transaction tr = null;
		Session session = null;
		try {
			if (new EleAcreditacionOficioDAO().findById(numintAsociado) == null) {
				session = HibernateSessionFactoryElecciones2012.getSession();
				tr = session.beginTransaction();
				EleAcreditacionOficio acreditacion = new EleAcreditacionOficio();
				acreditacion.setCodigoAsociado(numintAsociado);
				acreditacion.setFechaImpresion(new Timestamp(new Date().getTime()));
				acreditacion.setFechaRegistro(new Timestamp(new Date().getTime()));
				new EleAcreditacionOficioDAO().save(acreditacion);
				tr.commit();
			}
		} catch (Exception e) {
			if (tr != null && tr.isActive()) {
				tr.rollback();
			}

			throw new EleccionesDelegadosException(
					"Se ha presentado un error al intentar registrar" + " la acreditación de oficio", e);
		} finally {
			if (session != null) {
				session.close();
			}
			session = null;
			tr = null;
		}
	}

}
