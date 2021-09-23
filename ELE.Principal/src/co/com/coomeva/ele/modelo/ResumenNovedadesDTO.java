package co.com.coomeva.ele.modelo;

import java.util.List;

public class ResumenNovedadesDTO {
	
	private String fechaProceso;
	private String tipoNovedad;
	private List<ResumenZonasNovedadesDTO> list;
	private String totalNumeroNov;
	private String totalPorcentajeNov;
	
	public ResumenNovedadesDTO(){
		
	}

	public String getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public String getTipoNovedad() {
		return tipoNovedad;
	}

	public void setTipoNovedad(String tipoNovedad) {
		this.tipoNovedad = tipoNovedad;
	}

	public List<ResumenZonasNovedadesDTO> getList() {
		return list;
	}

	public void setList(List<ResumenZonasNovedadesDTO> list) {
		this.list = list;
	}

	public String getTotalNumeroNov() {
		return totalNumeroNov;
	}

	public void setTotalNumeroNov(String totalNumeroNov) {
		this.totalNumeroNov = totalNumeroNov;
	}

	public String getTotalPorcentajeNov() {
		return totalPorcentajeNov;
	}

	public void setTotalPorcentajeNov(String totalPorcentajeNov) {
		this.totalPorcentajeNov = totalPorcentajeNov;
	}
	
	
}
