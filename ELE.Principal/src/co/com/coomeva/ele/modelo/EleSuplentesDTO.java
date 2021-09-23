/*
 * Created on 17/12/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.coomeva.ele.modelo;


import java.util.ArrayList;
import java.util.List;

import co.com.coomeva.ele.entidades.planchas.EleInhabilidades;
import co.com.coomeva.ele.entidades.planchas.ElePrincipales;
import co.com.coomeva.ele.entidades.planchas.EleSuplentes;
import co.com.coomeva.util.acceso.UtilAcceso;


/**
 * @author dnxapr06
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EleSuplentesDTO extends EleSuplentes 
{
	private List<EleInhabilidades> Inhabilidades; 
	private int edad;
	private String imagenEstado;
	private String nombreCompleto;
	private String antiguedad;

	public String getStringInhabilidades(){
		String inhabilidadesString ="";
		if (Inhabilidades != null) {
			int maxInhabilidades = Inhabilidades.size(); 
			int cont = 0;
			for (EleInhabilidades inhabilidad : this.Inhabilidades) {
				if (maxInhabilidades==cont) {
					inhabilidadesString = inhabilidadesString + inhabilidad.getInhabilidad();
				}else
					inhabilidadesString = inhabilidadesString + inhabilidad.getInhabilidad()+",";
				cont++;
			}
		}
		if (inhabilidadesString.trim().equalsIgnoreCase("")) {
			inhabilidadesString = UtilAcceso.getParametroFuenteS("parametros", "MsgNoHayInhabilidades");
		}
		return inhabilidadesString;

	}
	public List<EleInhabilidades> getInhabilidades() {
		return Inhabilidades;
	}
	public void setInhabilidades(List<EleInhabilidades> inhabilidades) {
		Inhabilidades = inhabilidades;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getImagenEstado() {
		return imagenEstado;
	}
	public void setImagenEstado(String imagenEstado) {
		this.imagenEstado = imagenEstado;
	}
	public EleSuplentesDTO() {
		super();
		// TODO Auto-generated constructor stub
		Inhabilidades = new ArrayList();
	}
	public EleSuplentesDTO(EleSuplentes eleSuplentes) 
	{
		String primerNombre = "";
		String segundoNombre = "";
		String primerApellido = "";
		String segundoApellido = "";

		if (eleSuplentes.getPrimerNombre() != null){
			primerNombre = eleSuplentes.getPrimerNombre();
		}
		if (eleSuplentes.getSegundoNombre() != null){
			segundoNombre = eleSuplentes.getSegundoNombre();
		}
		if (eleSuplentes.getPrimerApellido() != null){
			primerApellido = eleSuplentes.getPrimerApellido();
		}
		if (eleSuplentes.getSegundoApellido() != null){
			segundoApellido = eleSuplentes.getSegundoApellido();
		}

		setElePlanchas(eleSuplentes.getElePlanchas());
		setEmail(eleSuplentes.getEmail());
		setNombreCompleto(primerNombre+" "+ segundoNombre+" "+primerApellido+" "+segundoApellido);
		setNroSuIdentificacion(eleSuplentes.getNroSuIdentificacion());
		setPrimerApellido(primerApellido);
		setPrimerNombre(primerNombre);
		setProfesion(eleSuplentes.getProfesion());
		setSegundoApellido(segundoApellido);
		setSegundoNombre(segundoNombre);
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(String antiguedad) {
		this.antiguedad = antiguedad;
	}


}
