package co.com.coomeva.ele.modelo;

import co.com.coomeva.ele.entidades.admhabilidad.Asoelecf;

public class AsociadoDTO extends Asoelecf
{
	private String nombreCompleto;

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public AsociadoDTO() {
		// TODO Auto-generated constructor stub
	}
	public AsociadoDTO(Asoelecf asociado) {
	}
	
}
