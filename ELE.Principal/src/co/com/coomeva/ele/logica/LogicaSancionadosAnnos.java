package co.com.coomeva.ele.logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import co.com.coomeva.ele.entidades.habilidad.EleSuspendido;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.habilidad.dao.EleSuspendidoDAO;
import co.com.coomeva.ele.entidades.planchas.dao.EleAsocOtrasEntElectDAO;
import co.com.coomeva.ele.entidades.planchas.dao.EleAsocSancionados5AnnosDAO;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleAsocOtrasEntElect;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleAsocSancionados5Annos;
import co.com.coomeva.ele.modelo.EleAsocOtrasEntElectDTO;
import co.com.coomeva.ele.modelo.EleAsocSancionados5AnnosDTO;
import co.com.coomeva.ele.modelo.SuspendidoDTO;

public class LogicaSancionadosAnnos extends EleAsocSancionados5AnnosDAO {
	private static LogicaSancionadosAnnos instance;

	private LogicaSancionadosAnnos() {

	}

	public static LogicaSancionadosAnnos getInstance() {
		if (instance == null) {
			instance = new LogicaSancionadosAnnos();
		}
		return instance;
	}

	public void crearSancionados(Long codigoAsociado, Date fechaRegistro, String usuario, Date fechaSuspension,
			String motivo) {
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
			EleAsocSancionados5Annos sancionado = new EleAsocSancionados5Annos();
			sancionado.setCodigoAsociado(codigoAsociado);
			sancionado.setFechaRegistro(fechaRegistro);
			sancionado.setMotivo(motivo);
			sancionado.setUsuario(usuario);
			sancionado.setFechaSuspension(fechaSuspension);
			save(sancionado);
			tr.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			tr.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
	}

	public List<EleAsocSancionados5Annos> obtenerSancionados() {
		return findAll();
	}

	public void eliminarSancionados() {
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			limpiarTablaSancionado5Annos();
			tr.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			tr.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
	}

	public List<EleAsocSancionados5AnnosDTO> consultarSancionados(String sortColumnName, boolean sortAscending,
			int startRow, int maxResults) throws Exception {

		List<EleAsocSancionados5AnnosDTO> list = new ArrayList<EleAsocSancionados5AnnosDTO>();
		List<Object[]> objectList = null;

		Session session = HibernateSessionFactoryElecciones2012.getSession();

		try {

			StringBuffer sql = new StringBuffer("select sancionado.NUMERO_DOCUMENTO DOCUMENTO, "
					+ "cli.NOMCL1 NOMBRES, sancionado.FECHA_SUSPENSION FECHA, "
					+ "sancionado.MOTIVO_SUSPENSION MOTIVO from mulclidat.CLIMAE cli, "
					+ "elecdb.ELE_ASOC_SANCIONADOS_5_ANNOS sancionado where "
					+ "sancionado.NUMERO_DOCUMENTO = cli.NUMINT");

			SQLQuery query = session.createSQLQuery(sql.toString());

			query.addScalar("DOCUMENTO", Hibernate.STRING);
			query.addScalar("NOMBRES", Hibernate.STRING);
			query.addScalar("FECHA", Hibernate.DATE);
			query.addScalar("MOTIVO", Hibernate.STRING);

			EleAsocSancionados5AnnosDTO sancionado = null;
			objectList = query.setFirstResult(startRow).setMaxResults(maxResults).list();
			if (objectList != null && objectList.size() > 0) {
				for (Object[] objects : objectList) {

					List<String> nombresApellidosProcesados = new ArrayList<String>();
					nombresApellidosProcesados = LogicaPlanchas.getInstance()
							.procesaNombresApellidos((String) objects[1]);

					sancionado = new EleAsocSancionados5AnnosDTO();
					sancionado.setDocumento((String) objects[0]);
					sancionado.setNombres(nombresApellidosProcesados.get(2) + " " + nombresApellidosProcesados.get(3)
							+ " " + nombresApellidosProcesados.get(0) + " " + nombresApellidosProcesados.get(1));
					sancionado.setFechaSuspension((Date) objects[2]);
					sancionado.setMotivo((String) objects[3]);

					list.add(sancionado);
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}

		return list;

	}

}
