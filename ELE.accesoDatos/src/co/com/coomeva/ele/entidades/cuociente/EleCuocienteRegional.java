package co.com.coomeva.ele.entidades.cuociente;

/**
 * EleCuocienteRegional entity. @author MyEclipse Persistence Tools
 */

public class EleCuocienteRegional implements java.io.Serializable {

	// Fields

	private Integer idRegistro;
	private String periodoElectoral;
	private String codRegional;
	private String desRegional;
	private Double totalDelegados;
	private Double relacionDelegados;

	// Constructors

	/** default constructor */
	public EleCuocienteRegional() {
	}

	/** full constructor */
	public EleCuocienteRegional(Integer idRegistro, String periodoElectoral,
			String codRegional, Double totalDelegados, Double relacionDelegados) {
		this.idRegistro = idRegistro;
		this.periodoElectoral = periodoElectoral;
		this.codRegional = codRegional;
		this.totalDelegados = totalDelegados;
		this.relacionDelegados = relacionDelegados;
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

	public String getCodRegional() {
		return this.codRegional;
	}

	public void setCodRegional(String codRegional) {
		this.codRegional = codRegional;
	}

	public Double getTotalDelegados() {
		return this.totalDelegados;
	}

	public void setTotalDelegados(Double totalDelegados) {
		this.totalDelegados = totalDelegados;
	}

	public Double getRelacionDelegados() {
		return this.relacionDelegados;
	}

	public void setRelacionDelegados(Double relacionDelegados) {
		this.relacionDelegados = relacionDelegados;
	}

	public String getDesRegional() {
		return desRegional;
	}

	public void setDesRegional(String desRegional) {
		this.desRegional = desRegional;
	}
}