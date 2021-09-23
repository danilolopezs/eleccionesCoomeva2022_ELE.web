package co.com.coomeva.ele.modelo;

import java.util.ArrayList;
import java.util.List;

import co.com.coomeva.ele.entidades.planchas.EleInhabilidades;

public class AsociadoValidadoDTO extends AsociadoDTO{
	
	private List<EleInhabilidades> listaInhabilidades;
	
	public AsociadoValidadoDTO() {
		listaInhabilidades = new ArrayList<EleInhabilidades>();
	}
	
	public void setListaInhabilidades(List<EleInhabilidades> listaInhabilidades) {
		this.listaInhabilidades = listaInhabilidades;
	}
	public void addInhabilidad(EleInhabilidades inhabilidades){
		this.listaInhabilidades.add(inhabilidades);
	}
	public List<EleInhabilidades> getListaInhabilidades() {
		return listaInhabilidades;
	}

}
