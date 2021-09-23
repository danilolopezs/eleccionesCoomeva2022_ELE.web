package co.com.coomeva.wseleconsultahab.logica;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;

import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.dto.DTOHabilidadAsociado;
import co.com.coomeva.ele.entidades.admhabilidad.Asoelecf;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.resources.LoaderResourceElements;
import co.com.coomeva.wseleconsultahab.modelo.RespuestaWS;
import co.com.coomeva.wseleconsultahab.util.ConstantesParametrosWs;
import co.com.coomeva.wseleconsultahab.util.ConstantesWs;

public class LogicaConsultaHabilidad {
	private static LogicaConsultaHabilidad instance;
	

	//Constructor de la clase
	private LogicaConsultaHabilidad() {
	}

	//Patròn Singular
	public static LogicaConsultaHabilidad getInstance() {
		if (instance == null) {
			instance = new LogicaConsultaHabilidad();
		}
		return instance;
	}
	
	public RespuestaWS obtenerHabilidad(long id) throws Exception{
		
		StringBuffer resultado = new StringBuffer();
		RespuestaWS respuestaWS =  new RespuestaWS();

		try {
			Asoelecf asociado = DelegadoAsociado.getInstance().findAsoHabFinWS(id+"");
			String medioVotacion = "";
			
			if (asociado.getWmedvot()==2) {
				respuestaWS.setDescVotacion(UtilAcceso.getParametroFuenteS("mensajesWs", "msjUrna"));
				respuestaWS.setCodVotacion(UtilAcceso.getParametroFuenteS("parametrosWs", "codVotUrna"));
				medioVotacion = UtilAcceso.getParametroFuenteS("mensajesWs", "msjHabilidadUrna");
			}else{
				respuestaWS.setDescVotacion(UtilAcceso.getParametroFuenteS("mensajesWs", "msjAudio"));
				respuestaWS.setCodVotacion(UtilAcceso.getParametroFuenteS("parametrosWs", "codVotAudio"));
				medioVotacion = UtilAcceso.getParametroFuenteS("mensajesWs", "msjHabilidadAudio");
			}
			
			if (asociado.getWindhab().trim().equals("H"))
			{
				respuestaWS.setCodHabilidad(UtilAcceso.getParametroFuenteS("parametrosWs", "codHab"));
				resultado.append(UtilAcceso.getParametroFuenteS("mensajesWs", "msjHabil"));
				resultado.append(medioVotacion);
		     }
			else
			{
				respuestaWS.setCodHabilidad(UtilAcceso.getParametroFuenteS("parametrosWs", "codInhab"));
				resultado.append(UtilAcceso.getParametroFuenteS("mensajesWs", "msjInhabil"));
			}
			
			respuestaWS.setDescHabilidad(resultado.toString());
			
		} catch (Exception e) {
			resultado.append(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return respuestaWS;
	}
	
	public RespuestaWS obtenerHabilidadElecciones2013(long id) throws Exception {

		RespuestaWS respuestaWS = new RespuestaWS();

		try {
			String mensajeHabilidadAso = null;
			DTOHabilidadAsociado dtoHabilidad = null;
			
			if(DelegadoAsociado.getInstance().esAsociado(id)){
				
				if(DelegadoAsociado.getInstance().estaAsociaActivo(id)){
					//12/12/2013 : Se valida si pertenece a una zona electoral donde se realizaran las elecciones
					//Para mostrar un mensaje adecuado.
					if(DelegadoAsociado.getInstance().asociadoPerteneceZonaHayEleccion(id)){
					    //Esta en el listado de habiles ?
						dtoHabilidad = DelegadoAsociado.getInstance()
								.consultarHabilidadAsociado(id);
	
						mensajeHabilidadAso = dtoHabilidad.getAsociadoHabil() ? ConstantesParametrosWs.PAR_MENSAJE_ASOCIADO_HABIL_2013
								: ConstantesParametrosWs.PAR_MENSAJE_ASOCIADO_INHABIL_2013;
					}else{
						mensajeHabilidadAso = ConstantesParametrosWs.PAR_MENSAJE_PERTENECE_ZONA_NOVOTACION;
					}
				} else {
					Double estadoAsociado = DelegadoAsociado.getInstance().consultarEstadoAsociado(id);
					ArrayList<Double> estadosCooperativa = new ArrayList<Double>();
					ArrayList<Double> estadosFallecidos = new ArrayList<Double>();
					estadosCooperativa = llenarListadoEstadosCooperativa(estadosCooperativa);
					estadosFallecidos = llenarListadoEstadosFallecidos(estadosFallecidos);
					mensajeHabilidadAso = ConstantesParametrosWs.PAR_MENSAJE_ASOCIADO_NO_ACTIVO_COOPERATIVA;
					if (estadosCooperativa.contains(estadoAsociado)) {
						mensajeHabilidadAso = ConstantesParametrosWs.PAR_MENSAJE_ESTADO_COOPERATIVA;
					}
					if (estadosFallecidos.contains(estadoAsociado)) {
						mensajeHabilidadAso = ConstantesParametrosWs.PAR_MENSAJE_ESTADO_FALLECIDOS;
					}
				}
				
				String nombreCompletoAsociado = DelegadoAsociado.getInstance()
				.consultarNombreAsociado(id);
				
				respuestaWS.setDescHabilidad(MessageFormat.format(
						mensajeHabilidadAso, nombreCompletoAsociado.trim()));
				
				if(dtoHabilidad != null && dtoHabilidad
						.getObservacionesInhabilidades() != null && dtoHabilidad
						.getObservacionesInhabilidades().size() > 0){
					respuestaWS.setInhabilidades(dtoHabilidad
							.getObservacionesInhabilidades().toArray(
									new String[dtoHabilidad
									           .getObservacionesInhabilidades().size()]));
				}
			} else{
				respuestaWS.setDescHabilidad(ConstantesParametrosWs.PAR_MENSAJE_ID_NO_FIGURA_COMO_ASOCIADO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return respuestaWS;
	}
	
	// Método que me llena el listado de estados que hacen referencia a la cooperativa para mostrar su mensaje pertinente
	// Juan Diego Montoya - 09/09/2016
	private ArrayList<Double> llenarListadoEstadosCooperativa(ArrayList<Double> listadoCooperativa)
	{
		for (int i = 20; i <= 25; i++) {
			listadoCooperativa.add(new Double(i));
		}
		for (int i = 30; i <= 48; i++) {
			listadoCooperativa.add(new Double(i));
		}
		listadoCooperativa.add(new Double(99));
		return listadoCooperativa;		
	}
	
	private ArrayList<Double> llenarListadoEstadosFallecidos(ArrayList<Double> listadoFallecidos)
	{
		for (int i = 50; i <= 56; i++) {
			listadoFallecidos.add(new Double(i));
		}
		listadoFallecidos.add(new Double(26));
		return listadoFallecidos;		
	}
	
}