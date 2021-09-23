package co.com.coomeva.ele.delegado;

import java.util.List;

import co.com.coomeva.ele.entidades.planchas.ElePrincipales;
import co.com.coomeva.ele.logica.LogicaPrincipal;
import co.com.coomeva.ele.modelo.ElePrincipalesDTO;

public class DelegadoPrincipal {
	private static DelegadoPrincipal instance;
	private static LogicaPrincipal logicaPrincipal;

	//Constructor de la clase
	private DelegadoPrincipal() {
	}

	//Patròn Singular
	public static DelegadoPrincipal getInstance() {
		if (instance == null) {
			instance = new DelegadoPrincipal();
			logicaPrincipal = LogicaPrincipal.getInstance();
		}
		return instance;
	}
	
	public void crearPrincipalPlancha(String nroIdentificacion,String nroCabPlancha, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
			   String profesion, String email, Long orden)throws Exception{
		logicaPrincipal.crearPrincipalPlancha(nroIdentificacion, nroCabPlancha, primerNombre, segundoNombre, primerApellido, segundoApellido, profesion, email, orden);
	}
	
	public ElePrincipales consultarPrincipal(String nroPriIdentificacion) throws Exception{
		return logicaPrincipal.consultarPrincipal(nroPriIdentificacion);
	}

	public List<ElePrincipalesDTO> consultarPrincipales(String documento) throws Exception {

		return logicaPrincipal.consultarPrincipales(documento) ;
	}

	public void crearPrincipales(List<ElePrincipalesDTO> listaPrincipales) throws Exception{
		logicaPrincipal.crearPrincipales(listaPrincipales);
	}
	
	public void eliminarPrincipales(String documento) throws Exception{
		logicaPrincipal.eliminarPrincipales(documento);
	}

	public void modificarProfesionPrincipal(String profesion, String id)
			throws Exception {
		logicaPrincipal.modificarProfesionPrincipal(profesion, id);
	}

	public String findPlanchasProfesionNative(String id) throws Exception {
		return logicaPrincipal.findPlanchasProfesionNative(id);
	}
	
	
}
