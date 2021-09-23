package co.com.coomeva.ele.delegado;

import co.com.coomeva.ele.logica.LogicaVotante;
import co.com.coomeva.ele.modelo.EleUsuarioComisionDTO;
import co.com.coomeva.ele.modelo.EleVotanteDatosDTO;

public class DelegadoVotante {
	private static DelegadoVotante instance;
	private static LogicaVotante logicaVotante;

	// Constructor de la clase
	private DelegadoVotante() {
	}

	// Patrón Singleton
	public static DelegadoVotante getInstance() {
		if (instance == null) {
			instance = new DelegadoVotante();
			logicaVotante = LogicaVotante.getInstance();
		}
		return instance;
	}

	public EleVotanteDatosDTO validarVotante(Long documentoVotante,
			String zonaUsuarioRegistro) throws Exception {
		return logicaVotante.validarVotante(documentoVotante,
				zonaUsuarioRegistro);
	}

	public void registrarVotante(Long documentoVotante, String usuarioRegistro,
			String zonaUsuarioRegistro, Integer agenciaUsuarioRegistro,
			String ipRegistro) throws Exception {
		logicaVotante.registrarVotante(documentoVotante, usuarioRegistro,
				zonaUsuarioRegistro, agenciaUsuarioRegistro, ipRegistro);
	}

	public EleUsuarioComisionDTO consultarUsuarioComision(String usuarioRegistro)
			throws Exception {
		return logicaVotante.consultarUsuarioComision(usuarioRegistro);
	}

}