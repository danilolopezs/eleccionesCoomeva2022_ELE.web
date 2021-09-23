package co.com.coomeva.ele.modelo;

import java.util.List;

import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral;
import co.com.coomeva.ele.entidades.planchas.ElePlanchas;

public class ElePlanchaDTO  extends ElePlanchas
{
	private List<ElePrincipalesDTO> listaPrincipales;
	
	private List<EleSuplentesDTO> listaSuplentes;
	
	
	private EleCabPlanchaDTO eleCabPlanchaDTO;
	
	
	private List<EleExperienciaLaboral> listExperienciaLaboral;
	
	public List<ElePrincipalesDTO> getListaPrincipales() {
		return listaPrincipales;
	}
	public void setListaPrincipales(List<ElePrincipalesDTO> listaPrincipales) {
		this.listaPrincipales = listaPrincipales;
	}
	public List<EleSuplentesDTO> getListaSuplentes() {
		return listaSuplentes;
	}
	public void setListaSuplentes(List<EleSuplentesDTO> listaSuplentes) {
		this.listaSuplentes = listaSuplentes;
	}
	
	public EleCabPlanchaDTO getEleCabPlanchaDTO() {
		return eleCabPlanchaDTO;
	}
	public void setEleCabPlanchaDTO(EleCabPlanchaDTO eleCabPlanchaDTO) {
		this.eleCabPlanchaDTO = eleCabPlanchaDTO;
	}
	public List<EleExperienciaLaboral> getListExperienciaLaboral() {
		return listExperienciaLaboral;
	}
	public void setListExperienciaLaboral(
			List<EleExperienciaLaboral> listExperienciaLaboral) {
		this.listExperienciaLaboral = listExperienciaLaboral;
	}
}
