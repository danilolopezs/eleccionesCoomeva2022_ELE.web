package co.com.coomeva.ele.dto;

public class DTOZonaElectoral {
	
	private String codZonaElectoral;
	private String descripcionZonaElectoral;
	private String zonaEspecial;
	private Long maxPrincipales;
	
	public String getCodZonaElectoral() {
		return codZonaElectoral;
	}
	public void setCodZonaElectoral(String codZonaElectoral) {
		this.codZonaElectoral = codZonaElectoral;
	}
	public String getDescripcionZonaElectoral() {
		return descripcionZonaElectoral;
	}
	public void setDescripcionZonaElectoral(String descripcionZonaElectoral) {
		this.descripcionZonaElectoral = descripcionZonaElectoral;
	}
	public String getZonaEspecial() {
		return zonaEspecial;
	}
	public void setZonaEspecial(String zonaEspecial) {
		this.zonaEspecial = zonaEspecial;
	}
	public Long getMaxPrincipales() {
		return maxPrincipales;
	}
	public void setMaxPrincipales(Long maxPrincipales) {
		this.maxPrincipales = maxPrincipales;
	}
}
