package co.com.coomeva.ele.entidades.cuociente;

/**
 * EleCuocienteElectoral entity. @author MyEclipse Persistence Tools
 */

public class EleCuocienteElectoral implements java.io.Serializable {

	// Fields

	private Integer idRegistro;
	private String periodoElectoral;
	private Double totalAsocHabiles;
	private Double totalNovedades;
	private Double totalDelegadosElegir;
	private Double totalDelegadosEspeciales;
	private Double finalTotalDelegadosElegir;
	private Double cuocienteElectoral;

	// Constructors

	/** default constructor */
	public EleCuocienteElectoral() {
	}

	/** full constructor */
	public EleCuocienteElectoral(Integer idRegistro, String periodoElectoral,
			Double totalAsocHabiles, Double totalDelegadosElegir,
			Double totalDelegadosEspeciales, Double finalTotalDelegadosElegir,
			Double cuocienteElectoral) {
		this.idRegistro = idRegistro;
		this.periodoElectoral = periodoElectoral;
		this.totalAsocHabiles = totalAsocHabiles;
		this.totalDelegadosElegir = totalDelegadosElegir;
		this.totalDelegadosEspeciales = totalDelegadosEspeciales;
		this.finalTotalDelegadosElegir = finalTotalDelegadosElegir;
		this.cuocienteElectoral = cuocienteElectoral;
	}

	// Property accessors

	public Integer getIdRegistro() {
		return this.idRegistro;
	}

	public void setIdRegistro(Integer idRegistro) {
		this.idRegistro = idRegistro;
	}

	public String getPeriodoElectoral() {
		return this.periodoElectoral;
	}

	public void setPeriodoElectoral(String periodoElectoral) {
		this.periodoElectoral = periodoElectoral;
	}

	public Double getTotalAsocHabiles() {
		return this.totalAsocHabiles;
	}

	public void setTotalAsocHabiles(Double totalAsocHabiles) {
		this.totalAsocHabiles = totalAsocHabiles;
	}

	public Double getTotalDelegadosElegir() {
		return this.totalDelegadosElegir;
	}

	public void setTotalDelegadosElegir(Double totalDelegadosElegir) {
		this.totalDelegadosElegir = totalDelegadosElegir;
	}

	public Double getTotalDelegadosEspeciales() {
		return this.totalDelegadosEspeciales;
	}

	public void setTotalDelegadosEspeciales(Double totalDelegadosEspeciales) {
		this.totalDelegadosEspeciales = totalDelegadosEspeciales;
	}

	public Double getFinalTotalDelegadosElegir() {
		return this.finalTotalDelegadosElegir;
	}

	public void setFinalTotalDelegadosElegir(Double finalTotalDelegadosElegir) {
		this.finalTotalDelegadosElegir = finalTotalDelegadosElegir;
	}

	public Double getCuocienteElectoral() {
		return this.cuocienteElectoral;
	}

	public void setCuocienteElectoral(Double cuocienteElectoral) {
		this.cuocienteElectoral = cuocienteElectoral;
	}

	public Double getTotalNovedades() {
		return totalNovedades;
	}

	public void setTotalNovedades(Double totalNovedades) {
		this.totalNovedades = totalNovedades;
	}
	
	

}