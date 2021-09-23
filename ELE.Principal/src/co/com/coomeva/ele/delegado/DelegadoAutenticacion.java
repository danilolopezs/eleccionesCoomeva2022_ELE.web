package co.com.coomeva.ele.delegado;

import java.util.HashMap;

import co.com.coomeva.ele.logica.LogicaAutenticacion;
import co.com.coomeva.profile.ws.client.CaasStub.Application;
import co.com.coomeva.profile.ws.client.CaasStub.Role;
import co.com.coomeva.profile.ws.client.CaasStub.Section;
import co.com.coomeva.profile.ws.client.CaasStub.UserVo;

public class DelegadoAutenticacion {
	private static DelegadoAutenticacion instance;
	private static LogicaAutenticacion logicaAutenticacion;

//	Constructor de la clase
	private DelegadoAutenticacion() {
	}

//	Patròn Singular
	public static DelegadoAutenticacion getInstance() {
		if (instance == null) {
			instance = new DelegadoAutenticacion();
			logicaAutenticacion = LogicaAutenticacion.getInstace();
		}
		return instance;
	}
	
	public UserVo autenDirectorioActivo(String login, String password) throws Exception{
		return logicaAutenticacion.autenDirectorioActivo(login, password);
	}

	public Application[] listadoAplicaciones(UserVo usuario) {
		return logicaAutenticacion.listadoAplicaciones(usuario);
	}


	public String getRolUsuario(UserVo userVo) throws Exception{
		return logicaAutenticacion.getRolUsuario(userVo);
	}

	public  Role[] getRol(Application app){
		return logicaAutenticacion.getRol(app);
	}

	public HashMap<String, String> getAcciones(UserVo usuario)throws Exception{
		return logicaAutenticacion.getAcciones(usuario);
	}
	
	public HashMap<String, Section> getSecciones(UserVo usuario )throws Exception{
		return logicaAutenticacion.getSecciones(usuario);
	}

	
}
