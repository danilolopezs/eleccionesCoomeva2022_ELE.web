/*
 * Created on 17/12/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.coomeva.ele.modelo;

import co.com.coomeva.ele.entidades.planchas.EleCabPlancha;

/**
 * @author dnxapr06
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EleCabPlanchaDTO extends EleCabPlancha {
	private String nombreCompleto;
	
	public EleCabPlanchaDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public EleCabPlanchaDTO(EleCabPlancha elCabPlancha) 
	{
		String primerNombre = "";
		String segundoNombre = "";
		String primerApellido = "";
		String segundoApellido = "";
		
		if (elCabPlancha.getPrimerNombre() != null){
			primerNombre = elCabPlancha.getPrimerNombre();
		}
		if (elCabPlancha.getSegundoNombre() != null){
			segundoNombre = elCabPlancha.getSegundoNombre();
		}
		if (elCabPlancha.getPrimerApellido() != null){
			primerApellido = elCabPlancha.getPrimerApellido();
		}
		if (elCabPlancha.getSegundoApellido() != null){
			segundoApellido = elCabPlancha.getSegundoApellido();
		}
		
		setAntiguedad(elCabPlancha.getAntiguedad());
		setCargosDirectivos(elCabPlancha.getCargosDirectivos());
		setEdad(elCabPlancha.getEdad());
		setElePlanchas(elCabPlancha.getElePlanchas());
		setNroIdentificacion(elCabPlancha.getNroIdentificacion());
		setEmail(elCabPlancha.getEmail());
		setOtrosEstudios(elCabPlancha.getOtrosEstudios());
		setPrimerApellido(primerApellido);
		setPrimerNombre(primerNombre);
		setProfesion(elCabPlancha.getProfesion());
		setRutaImagen(elCabPlancha.getRutaImagen());
		setSegundoApellido(segundoApellido);
		setSegundoNombre(segundoNombre);
		setNombreCompleto(primerNombre+" "+segundoNombre+" "+primerApellido+" "+segundoApellido);
		
	}


	public String getNombreCompleto() {
		return nombreCompleto;
	}


	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}


	
	
}