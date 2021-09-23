package co.com.coomeva.ele.util;

import java.util.ArrayList;
import java.util.List;

import co.com.coomeva.ele.dto.PreguntasFormulario176DTO;

public class PreguntasFormulario176Util 
{
	public static List<PreguntasFormulario176DTO> crearFormulario()
	{
		List<PreguntasFormulario176DTO> listaPreguntas = new ArrayList<PreguntasFormulario176DTO>();
		
		listaPreguntas.add(new PreguntasFormulario176DTO("Es Asociado h�bil", 0));
		listaPreguntas.add(new PreguntasFormulario176DTO("Cuenta con antig�edad como Asociado de tres (3) a�os", 0));
		listaPreguntas.add(new PreguntasFormulario176DTO("Acredita T�tulo Profesional u ocupaci�n m�nima de tres (3) a�os. Nota: para el caso de acreditaci�n de ocupaci�n los participantes deben anexar la Declaraci�n para acreditar ocupaci�n y cumplimiento de Requisitos (Formato CO-FT-211)",0));
		listaPreguntas.add(new PreguntasFormulario176DTO("No presenta sanciones por la Cooperativa, durante los cinco (5) a�os anteriores a la nominaci�n con suspensi�n o p�rdida de sus derechos sociales, de acuerdo a lo establecido en el estatuto.",0));
		listaPreguntas.add(new PreguntasFormulario176DTO("No presenta sanciones con multa en los �ltimos cinco (5) a�os o con suspensi�n, destituci�n o remoci�n del cargo por un Organismo de Control Estatal",0));
		listaPreguntas.add(new PreguntasFormulario176DTO("Actualmente no se encuentra incurso en las inhabilidades e incompatibilidades que establece la Ley y el Estatuto",0));
		listaPreguntas.add(new PreguntasFormulario176DTO("No se encuentra registrado en otra plancha",0));
		listaPreguntas.add(new PreguntasFormulario176DTO("La plancha se encuentra conformada en su totalidad por Asociados o en su totalidad por empleados",0));
		listaPreguntas.add(new PreguntasFormulario176DTO("No es miembro del Tribunal de Elecciones y Escrutinio, ni de la Comisi�n Electoral o de los Comit�s Auxiliares",0));
		listaPreguntas.add(new PreguntasFormulario176DTO("Hay por lo menos un suplente inscrito en la plancha",0));
		listaPreguntas.add(new PreguntasFormulario176DTO("La cantidad de suplentes inscritos en la plancha no supera la cantidad de miembros principales",0));
		listaPreguntas.add(new PreguntasFormulario176DTO("El cabeza de plancha corresponde al primer rengl�n de los miembros principales de la plancha",0));
		
		return listaPreguntas;
	}

}
