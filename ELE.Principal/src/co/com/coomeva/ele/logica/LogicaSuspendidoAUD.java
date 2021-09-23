package co.com.coomeva.ele.logica;

import java.util.Date;
import java.util.List;

import org.hibernate.Transaction;

import co.com.coomeva.ele.entidades.habilidad.EleSuspendido;
import co.com.coomeva.ele.entidades.habilidad.EleSuspendidoAUD;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.habilidad.dao.EleSuspendidoAUDDAO;
import co.com.coomeva.ele.entidades.habilidad.dao.EleSuspendidoDAO;
import co.com.coomeva.ele.utilidades.GeneradorConsecutivos;

public class LogicaSuspendidoAUD extends EleSuspendidoAUDDAO {
	private static LogicaSuspendidoAUD instance;

	private LogicaSuspendidoAUD() {

	}

	public static LogicaSuspendidoAUD getInstance() {
		if (instance == null) {
			instance = new LogicaSuspendidoAUD();
		}
		return instance;
	}

	public void crearSuspendidoAUD(Long codigoAsociado, String motivo, Date fechaSuspencion, Date fechaRegistro,
			Date fechaRegistroAUD, String usuario) {
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			Long codigoSuspencion = GeneradorConsecutivos.getInstance().getConsecutivo(
					"consultar.secuencia.ele.ele.suspendido.aud", HibernateSessionFactoryElecciones2012.getSession());

			EleSuspendidoAUD eleSuspendido = new EleSuspendidoAUD();
			eleSuspendido.setCodigoSuspencion(codigoSuspencion);
			eleSuspendido.setCodigoAsociado(codigoAsociado);
			eleSuspendido.setMotivo(motivo.trim());
			eleSuspendido.setFechaSuspencion(fechaSuspencion);
			eleSuspendido.setFechaRegistro(fechaRegistro);
			eleSuspendido.setFechaRegistroAUD(fechaRegistroAUD);
			eleSuspendido.setUsuario(usuario);

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

}
