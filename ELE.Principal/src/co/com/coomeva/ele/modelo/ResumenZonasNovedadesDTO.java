package co.com.coomeva.ele.modelo;

public class ResumenZonasNovedadesDTO {
	
	private String zona;
	private String numeroNovedades;
	private String porcentajeNovedades;
	private String regional;
	
	public ResumenZonasNovedadesDTO(){
		
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getNumeroNovedades() {
		return numeroNovedades;
	}

	public void setNumeroNovedades(String numeroNovedades) {
		this.numeroNovedades = numeroNovedades;
	}

	public String getPorcentajeNovedades() {
		return porcentajeNovedades;
	}

	public void setPorcentajeNovedades(String porcentajeNovedades) {
		this.porcentajeNovedades = porcentajeNovedades;
	}

	public String getRegional() {
		return regional;
	}

	public void setRegional(String regional) {
		this.regional = regional;
	}
}
