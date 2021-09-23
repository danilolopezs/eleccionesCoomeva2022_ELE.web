package co.com.coomeva.ele.modelo;

import java.util.List;

import co.com.coomeva.ele.entidades.cuociente.EleCuocienteDelegadosZona;

public class ResumenCuocienteDTO {
	private String regional;
	private List<ResumenZonaHabilidadDTO> listResumenHabilidad;
	private Double totalHabilesZona;
	private List<ResumenZonasNovedadesDTO> listNovedades;
	private List<EleCuocienteDelegadosZona> list;
	private String totalNumeroNov;
	private Double totalNumeroHabiles; // A la fecha
	
	private Double totalSumaHabiles;
	private Double fraccionDelegados;
	private Double totalDelegados;
	
	
	public ResumenCuocienteDTO(){
		
	}


	public String getRegional() {
		return regional;
	}


	public void setRegional(String regional) {
		this.regional = regional;
	}


	public List<ResumenZonaHabilidadDTO> getListResumenHabilidad() {
		return listResumenHabilidad;
	}


	public void setListResumenHabilidad(List<ResumenZonaHabilidadDTO> listResumenHabilidad) {
		this.listResumenHabilidad = listResumenHabilidad;
	}


	public Double getTotalHabilesZona() {
		return totalHabilesZona;
	}


	public void setTotalHabilesZona(Double totalHabilesZona) {
		this.totalHabilesZona = totalHabilesZona;
	}


	public List<ResumenZonasNovedadesDTO> getListNovedades() {
		return listNovedades;
	}


	public void setListNovedades(List<ResumenZonasNovedadesDTO> listNovedades) {
		this.listNovedades = listNovedades;
	}

	

	public List<EleCuocienteDelegadosZona> getList() {
		return list;
	}


	public void setList(List<EleCuocienteDelegadosZona> list) {
		this.list = list;
	}


	public String getTotalNumeroNov() {
		return totalNumeroNov;
	}


	public void setTotalNumeroNov(String totalNumeroNov) {
		this.totalNumeroNov = totalNumeroNov;
	}


	public Double getTotalNumeroHabiles() {
		return totalNumeroHabiles;
	}


	public void setTotalNumeroHabiles(Double totalNumeroHabiles) {
		this.totalNumeroHabiles = totalNumeroHabiles;
	}


	public Double getTotalSumaHabiles() {
		return totalSumaHabiles;
	}


	public void setTotalSumaHabiles(Double totalSumaHabiles) {
		this.totalSumaHabiles = totalSumaHabiles;
	}


	public Double getFraccionDelegados() {
		return fraccionDelegados;
	}


	public void setFraccionDelegados(Double fraccionDelegados) {
		this.fraccionDelegados = fraccionDelegados;
	}


	public Double getTotalDelegados() {
		return totalDelegados;
	}


	public void setTotalDelegados(Double totalDelegados) {
		this.totalDelegados = totalDelegados;
	}

	
	
}
