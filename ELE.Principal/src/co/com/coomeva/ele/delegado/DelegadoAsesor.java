package co.com.coomeva.ele.delegado;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.com.coomeva.ele.entidades.planchas.HibernateSessionFactoryPlanchas;
import co.com.coomeva.ele.logica.LogicaAsesor;
import co.com.coomeva.ele.modelo.EleAsesorLicoDTO;

public class DelegadoAsesor {

	private static DelegadoAsesor instance;
	private static LogicaAsesor logicaAsesor;

	// Constructor de la clase
	private DelegadoAsesor() {
	}

	// Patròn Singular
	public static DelegadoAsesor getInstance() {
		if (instance == null) {
			instance = new DelegadoAsesor();
			logicaAsesor = LogicaAsesor.getInstance();
		}
		if (logicaAsesor == null) {
			logicaAsesor = LogicaAsesor.getInstance();
		}
		return instance;
	}

	public boolean existAsesor(String nroIdentificacion) throws Exception {
		return logicaAsesor.existAsesor(nroIdentificacion);
	}

}
