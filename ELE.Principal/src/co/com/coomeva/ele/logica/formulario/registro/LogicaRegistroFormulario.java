package co.com.coomeva.ele.logica.formulario.registro;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Transaction;

import co.com.coomeva.ele.entidades.formulario.EleRegistroCampos;
import co.com.coomeva.ele.entidades.formulario.EleRegistroCamposDAO;
import co.com.coomeva.ele.entidades.formulario.EleRegistroFormulario;
import co.com.coomeva.ele.entidades.formulario.EleRegistroFormularioDAO;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;

public class LogicaRegistroFormulario implements ILogicaRegistroFormulario {

	private EleRegistroFormularioDAO registroFormularioDAO = new EleRegistroFormularioDAO();
	private EleRegistroCamposDAO registroCamposDAO = new EleRegistroCamposDAO();

	private static LogicaRegistroFormulario instance;

	public LogicaRegistroFormulario() {
	}

	public static LogicaRegistroFormulario getInstance() {
		if (instance == null) {
			instance = new LogicaRegistroFormulario();
		}
		return instance;
	}

	public void crearRegistroFormulario(Long cod_formulario, List<EleRegistroCampos> listaRegCampos, String userID) {
		Transaction tr = HibernateSessionFactoryElecciones2012.getSession().beginTransaction();
		EleRegistroFormulario regFormulario = new EleRegistroFormulario();
		try {
			Timestamp fechaRegistro = new Timestamp((new Date()).getTime());

			regFormulario.setCodFormulario(cod_formulario);
			regFormulario.setFechaRegistro(fechaRegistro);
			regFormulario.setUsuarioCreacion(userID);

			registroFormularioDAO.save(regFormulario);

			for (EleRegistroCampos regCampo : listaRegCampos) {
				regCampo.setUsuarioCreacion(userID);
				registroCamposDAO.save(regCampo);
			}

			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
