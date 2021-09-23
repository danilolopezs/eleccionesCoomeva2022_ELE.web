package co.com.coomeva.ele.modelo.habilidad;

import java.util.Collections;
import java.util.Date;
import java.util.List;


public class DTOProcesoVerificacionHabilidad {
	
	private Long codigoProceso;
	private Date fechaProgramacion;
	private String estadoProceso;
	private List<DTOReglaVerificacionHabilidad> listaReglas;
	
	public Long getCodigoProceso() {
		return codigoProceso;
	}
	public void setCodigoProceso(Long codigoProceso) {
		this.codigoProceso = codigoProceso;
	}
	public Date getFechaProgramacion() {
		return fechaProgramacion;
	}
	public void setFechaProgramacion(Date fechaProgramacion) {
		this.fechaProgramacion = fechaProgramacion;
	}
	public String getEstadoProceso() {
		return estadoProceso;
	}
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}
	public List<DTOReglaVerificacionHabilidad> getListaReglas() {
		return listaReglas;
	}
	public void setListaReglas(List<DTOReglaVerificacionHabilidad> listaReglas) {
		if(listaReglas != null && listaReglas.size() > 0){
			Collections.sort(listaReglas);
		}
		this.listaReglas = listaReglas;
	}
}
