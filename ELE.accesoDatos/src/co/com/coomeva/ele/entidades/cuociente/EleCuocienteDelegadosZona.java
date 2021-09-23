package co.com.coomeva.ele.entidades.cuociente;

/**
 * EleCuocienteDelegadosZona entity. @author MyEclipse Persistence Tools
 */

public class EleCuocienteDelegadosZona implements java.io.Serializable {

	// Fields
	private Integer idRegistro;
	private String periodoElectoral;
	private String codRegional;
	private String codZona;

	private String descCodRegional;
	private String descCodZona;
	
	// Agrego los nuevos campos solicitados para similitud con la matriz
	// Juan Diego Montoya
	
	private Double sumaNovedades;
	private Double sumaEspHabiles;
	private Double sumaNovedadesEsp;
	private Double sumaTotalHabiles;

	private Double sumaHabiles;
	private Double delegados;
	private Double delegadosDirectos;
	private Double fraccion;
	private Double delegadosResiduo;
	private Double distribuidosRestantes;
	private Double totalDelegadosZona;

	// Constructors
	/** default constructor */
	public EleCuocienteDelegadosZona() {
	}

	/** full constructor */
	public EleCuocienteDelegadosZona(Integer idRegistro,
			String periodoElectoral, String codRegional, String codZona,
			Double sumaHabiles, Double sumaNovedades, Double sumaEspHabiles, Double sumaNovedadesEsp, Double sumaTotalHabiles, Double delegados, Double delegadosDirectos,
			Double fraccion, Double delegadosResiduo,
			Double distribuidosRestantes, Double totalDelegadosZona) {
		this.idRegistro = idRegistro;
		this.periodoElectoral = periodoElectoral;
		this.codRegional = codRegional;
		this.codZona = codZona;
		this.sumaHabiles = sumaHabiles;
		this.sumaNovedades = sumaNovedades;
		this.sumaEspHabiles = sumaEspHabiles;
		this.sumaNovedadesEsp = sumaNovedadesEsp;
		this.sumaTotalHabiles = sumaTotalHabiles;
		this.delegados = delegados;
		this.delegadosDirectos = delegadosDirectos;
		this.fraccion = fraccion;
		this.delegadosResiduo = delegadosResiduo;
		this.distribuidosRestantes = distribuidosRestantes;
		this.totalDelegadosZona = totalDelegadosZona;
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

	public String getCodZona() {
		return this.codZona;
	}

	public void setCodZona(String codZona) {
		this.codZona = codZona;
	}

	public Double getSumaHabiles() {
		return this.sumaHabiles;
	}

	public void setSumaHabiles(Double sumaHabiles) {
		this.sumaHabiles = sumaHabiles;
	}

	public Double getDelegados() {
		return this.delegados;
	}

	public void setDelegados(Double delegados) {
		this.delegados = delegados;
	}

	public Double getDelegadosDirectos() {
		return this.delegadosDirectos;
	}

	public void setDelegadosDirectos(Double delegadosDirectos) {
		this.delegadosDirectos = delegadosDirectos;
	}

	public Double getFraccion() {
		return this.fraccion;
	}

	public void setFraccion(Double fraccion) {
		this.fraccion = fraccion;
	}

	public String getDescCodRegional() {
		return descCodRegional;
	}

	public void setDescCodRegional(String descCodRegional) {
		this.descCodRegional = descCodRegional;
	}

	public String getDescCodZona() {
		return descCodZona;
	}

	public void setDescCodZona(String descCodZona) {
		this.descCodZona = descCodZona;
	}

	public Double getDelegadosResiduo() {
		return delegadosResiduo;
	}

	public void setDelegadosResiduo(Double delegadosResiduo) {
		this.delegadosResiduo = delegadosResiduo;
	}

	public Double getDistribuidosRestantes() {
		return distribuidosRestantes;
	}

	public void setDistribuidosRestantes(Double distribuidosRestantes) {
		this.distribuidosRestantes = distribuidosRestantes;
	}

	public Double getTotalDelegadosZona() {
		return totalDelegadosZona;
	}

	public void setTotalDelegadosZona(Double totalDelegadosZona) {
		this.totalDelegadosZona = totalDelegadosZona;
	}

	public Double getSumaNovedades() {
		return sumaNovedades;
	}

	public void setSumaNovedades(Double sumaNovedades) {
		this.sumaNovedades = sumaNovedades;
	}

	public Double getSumaEspHabiles() {
		return sumaEspHabiles;
	}

	public void setSumaEspHabiles(Double sumaEspHabiles) {
		this.sumaEspHabiles = sumaEspHabiles;
	}

	public Double getSumaNovedadesEsp() {
		return sumaNovedadesEsp;
	}

	public void setSumaNovedadesEsp(Double sumaNovedadesEsp) {
		this.sumaNovedadesEsp = sumaNovedadesEsp;
	}

	public Double getSumaTotalHabiles() {
		return sumaTotalHabiles;
	}

	public void setSumaTotalHabiles(Double sumaTotalHabiles) {
		this.sumaTotalHabiles = sumaTotalHabiles;
	}
	
	
	
}