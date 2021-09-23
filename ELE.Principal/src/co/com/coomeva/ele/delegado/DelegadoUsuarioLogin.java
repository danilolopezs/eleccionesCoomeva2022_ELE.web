package co.com.coomeva.ele.delegado;

import java.util.List;

import co.com.coomeva.ele.entidades.usuario.EleUsuarioLogin;
import co.com.coomeva.ele.logica.LogicaUsuarioLogin;

public class DelegadoUsuarioLogin 
{
	private static DelegadoUsuarioLogin instance = null;
	private static LogicaUsuarioLogin logicaUsuarioLogin;
	
	public static DelegadoUsuarioLogin getInstance() {
		if(instance == null)
		{
			instance = new DelegadoUsuarioLogin();
			logicaUsuarioLogin = LogicaUsuarioLogin.getInstance();
		}
		return instance;
	}
	
	public List<EleUsuarioLogin> consultarUsuariosBloqueados(String userID)throws Exception
	{
		return logicaUsuarioLogin.consultarUsuariosBloqueados(userID);
	}

	public void desbloquearUsuarios(List<EleUsuarioLogin> usuariosSeleccionados) {
		
		logicaUsuarioLogin.desbloquearUsuarios(usuariosSeleccionados);
	}

}
