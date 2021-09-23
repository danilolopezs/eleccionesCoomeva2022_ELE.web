package co.com.coomeva.ele.modelo;

import java.util.List;

public class ResumenHabilidadDTO {
	
	private String regional;
	private List<ResumenZonaHabilidadDTO> listResumen;
	private Double totalAsociadosZona;
	private Double totalHabilesZona;
	private Double porcentajeHabiles;
	private Double totalInhabilesZona;
	private Double porcentajeInhabiles;
	private Double muestraZona;
	private Double porcentajeMuestra;
	
	public ResumenHabilidadDTO(){
		
	}

	public String getRegional() {
		return regional;
	}

	public void setRegional(String regional) {
		this.regional = regional;
	}

	public List<ResumenZonaHabilidadDTO> getListResumen() {
		return listResumen;
	}

	public void setListResumen(List<ResumenZonaHabilidadDTO> listResumen) {
		this.listResumen = listResumen;
	}

	public Double getTotalAsociadosZona() {
		return totalAsociadosZona;
	}

	public void setTotalAsociadosZona(Double totalAsociadosZona) {
		this.totalAsociadosZona = totalAsociadosZona;
	}

	public Double getTotalHabilesZona() {
		return totalHabilesZona;
	}

	public void setTotalHabilesZona(Double totalHabilesZona) {
		this.totalHabilesZona = totalHabilesZona;
	}

	public Double getPorcentajeHabiles() {
		return porcentajeHabiles;
	}

	public void setPorcentajeHabiles(Double porcentajeHabiles) {
		this.porcentajeHabiles = porcentajeHabiles;
	}

	public Double getTotalInhabilesZona() {
		return totalInhabilesZona;
	}

	public void setTotalInhabilesZona(Double totalInhabilesZona) {
		this.totalInhabilesZona = totalInhabilesZona;
	}

	public Double getPorcentajeInhabiles() {
		return porcentajeInhabiles;
	}

	public void setPorcentajeInhabiles(Double porcentajeInhabiles) {
		this.porcentajeInhabiles = porcentajeInhabiles;
	}

	public Double getMuestraZona() {
		return muestraZona;
	}

	public void setMuestraZona(Double muestraZona) {
		this.muestraZona = muestraZona;
	}

	public Double getPorcentajeMuestra() {
		return porcentajeMuestra;
	}

	public void setPorcentajeMuestra(Double porcentajeMuestra) {
		this.porcentajeMuestra = porcentajeMuestra;
	}
	
}
