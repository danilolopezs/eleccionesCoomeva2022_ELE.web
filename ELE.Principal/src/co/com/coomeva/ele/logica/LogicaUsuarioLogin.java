package co.com.coomeva.ele.logica;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Transaction;

import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.entidades.usuario.EleUsuarioLogin;
import co.com.coomeva.ele.entidades.usuario.dao.EleUsuarioLoginDAO;

public class LogicaUsuarioLogin extends EleUsuarioLoginDAO {
	public static LogicaUsuarioLogin instance = null;
	private static final Log log = LogFactory.getLog(LogicaUsuarioLogin.class);

	public static LogicaUsuarioLogin getInstance() {
		if (instance == null) {
			instance = new LogicaUsuarioLogin();
		}
		return instance;
	}

	public boolean usuarioBloqueado(String userId) {
		try {
			HibernateSessionFactoryElecciones2012.getSession().beginTransaction();

			EleUsuarioLogin usuarioLogin = findByUserId(userId);

			if (usuarioLogin != null) {
				if (usuarioLogin.getEstado().equals(2L)) {
					return true;
				}
			}
			return false;
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
	}

	public Long consultarItentosRestantes(String login) {
		EleUsuarioLogin usuarioLogin = findByUserId(login);
		Long numMaxIntento = 3L;

		if (usuarioLogin != null) {
			return (numMaxIntento - usuarioLogin.getNumItentos());
		} else {
			return (numMaxIntento - 1L);
		}

	}

	public void reiniciarItentos(String userId) throws Exception {
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			EleUsuarioLogin usuarioLogin = findByUserId(userId);

			if (usuarioLogin == null) {
				usuarioLogin = new EleUsuarioLogin();
				usuarioLogin.setUserId(userId);
				usuarioLogin.setNumItentos(0L);
				usuarioLogin.setEstado(1L);
				usuarioLogin.setFechaIngreso(new Timestamp(new Date().getTime()));

				save(usuarioLogin);
			} else {
				usuarioLogin.setNumItentos(0L);
				usuarioLogin.setEstado(1L);
				usuarioLogin.setFechaIngreso(new Timestamp(new Date().getTime()));
				update(usuarioLogin);
			}

			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			throw new Exception("Se presento un error Inesperado");
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
	}

	public void incrementarIntentos(String userId) throws Exception {
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			EleUsuarioLogin usuarioLogin = findByUserId(userId);

			if (usuarioLogin == null) {
				usuarioLogin = new EleUsuarioLogin();
				usuarioLogin.setUserId(userId);
				usuarioLogin.setNumItentos(1L);
				usuarioLogin.setEstado(1L);
				usuarioLogin.setFechaIngreso(new Timestamp(new Date().getTime()));

				save(usuarioLogin);
			} else {
				Long numIntento = usuarioLogin.getNumItentos();
				numIntento++;
				usuarioLogin.setNumItentos(numIntento);
				usuarioLogin.setFechaIngreso(new Timestamp(new Date().getTime()));

				if (numIntento >= 3) {
					bloquearUsuario(usuarioLogin);
				} else {
					update(usuarioLogin);
				}
			}
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			throw new Exception("Se presento un error Inesperado");
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
	}

	public void bloquearUsuario(EleUsuarioLogin entity) throws Exception {
		try {
			entity.setEstado(2L);
			update(entity);

		} catch (Exception e) {
			throw new Exception("Se presento un error Inesperado");
		}
	}

	public void desBloquearUsuario(EleUsuarioLogin entity) throws Exception {
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		try {
			entity.setEstado(1L);
			entity.setNumItentos(0L);
			update(entity);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			throw new Exception("Se presento un error Inesperado");
		} finally {
			HibernateSessionFactoryElecciones2012.getSession().close();
		}
	}

	public void desbloquearUsuarios(List<EleUsuarioLogin> usuariosSeleccionados) {
		for (EleUsuarioLogin eleUsuarioLogin : usuariosSeleccionados) {

			try {
				desBloquearUsuario(eleUsuarioLogin);
			} catch (Exception e) {

				log.error("Error AL desbloquear el usuario: " + eleUsuarioLogin.getUserId(), e);
			}
		}
	}
}
