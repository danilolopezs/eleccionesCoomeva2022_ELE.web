package co.com.coomeva.ele.logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import co.com.coomeva.ele.entidades.habilidad.EleSuspendido;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.habilidad.dao.EleSuspendidoDAO;
import co.com.coomeva.ele.modelo.SuspendidoDTO;

public class LogicaSuspendido extends EleSuspendidoDAO {
	private static LogicaSuspendido instance;

	private LogicaSuspendido() {

	}

	public static LogicaSuspendido getInstance() {
		if (instance == null) {
			instance = new LogicaSuspendido();
		}
		return instance;
	}

	public void crearSuspendido(Long codigoAsociado, String motivo, Date fechaSuspencion, Date fechaRegistro,
			String usuario) {
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			EleSuspendido eleSuspendido = new EleSuspendido();
			eleSuspendido.setCodigoAsociado(codigoAsociado);
			eleSuspendido.setMotivo(motivo.trim());
			eleSuspendido.setFechaSuspencion(fechaSuspencion);
			eleSuspendido.setFechaRegistro(fechaRegistro);
			eleSuspendido.setUsuario(usuario);
			eleSuspendido.setEstado("1");

			save(eleSuspendido);
			tr.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			tr.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
	}

	public List<EleSuspendido> obtenerSuspendidos() {
		return findAll();
	}

	public void eliminarSuspendidos() {
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			limpiarTablaSuspendido();
			tr.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			tr.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
	}

	public List<SuspendidoDTO> consultarSuspendidosPag(String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception {

		List<SuspendidoDTO> list = new ArrayList<SuspendidoDTO>();
		List<Object[]> objectList = null;

		Session session = HibernateSessionFactoryElecciones2012.getSession();

		try {

			StringBuffer sql = new StringBuffer("select A.NUMERO_DOCUMENTO, S.MOTIVO, S.FECHA_SUSPENSION "
					+ "from ELECDB.ELE_SUSPENDIDO S, ELECDB.ELE_ASOCIADO A WHERE S.CODIGO_ASOCIADO = A.CODIGO_ASOCIADO");

			if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
				sql.append(" ORDER BY A." + sortColumnName + " " + (sortAscending ? "asc" : "desc"));
			} else {
				sql.append(" ORDER BY A.NUMERO_DOCUMENTO asc");
			}

			SQLQuery query = session.createSQLQuery(sql.toString());

			query.addScalar("NUMERO_DOCUMENTO", Hibernate.LONG);
			query.addScalar("MOTIVO", Hibernate.STRING);
			query.addScalar("FECHA_SUSPENSION", Hibernate.DATE);

			SuspendidoDTO suspendido = null;
			objectList = query.setFirstResult(startRow).setMaxResults(maxResults).list();
			if (objectList != null && objectList.size() > 0) {
				for (Object[] objects : objectList) {
					suspendido = new SuspendidoDTO();

					suspendido.setIdentificacion((Long) objects[0]);
					suspendido.setMotivo((String) objects[1]);
					suspendido.setFechaRegistro((Date) objects[2]);

					list.add(suspendido);
				}
			} else {
				return null;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}

		return list;

	}
}
