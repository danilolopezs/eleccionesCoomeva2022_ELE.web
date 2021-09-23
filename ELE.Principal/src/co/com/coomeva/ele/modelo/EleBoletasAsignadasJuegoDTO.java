package co.com.coomeva.ele.modelo;

public class EleBoletasAsignadasJuegoDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long numeroDocumento;
	private String nombres;
	private String zona;
	private String regional;
	private Integer boletasAsignadasFase1;
	private Integer boletasAsignadasFase2;
	private Integer boletasAsignadasFase3;
	private Integer boletasAsignadasFase4;
	
	public Long getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres.trim();
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona.trim();
	}
	public String getRegional() {
		return regional;
	}
	public void setRegional(String regional) {
		this.regional = regional.trim();
	}
	public Integer getBoletasAsignadasFase1() {
		return boletasAsignadasFase1;
	}
	public void setBoletasAsignadasFase1(Integer boletasAsignadasFase1) {
		this.boletasAsignadasFase1 = boletasAsignadasFase1;
	}
	public Integer getBoletasAsignadasFase2() {
		return boletasAsignadasFase2;
	}
	public void setBoletasAsignadasFase2(Integer boletasAsignadasFase2) {
		this.boletasAsignadasFase2 = boletasAsignadasFase2;
	}
	public Integer getBoletasAsignadasFase3() {
		return boletasAsignadasFase3;
	}
	public void setBoletasAsignadasFase3(Integer boletasAsignadasFase3) {
		this.boletasAsignadasFase3 = boletasAsignadasFase3;
	}
	public Integer getBoletasAsignadasFase4() {
		return boletasAsignadasFase4;
	}
	public void setBoletasAsignadasFase4(Integer boletasAsignadasFase4) {
		this.boletasAsignadasFase4 = boletasAsignadasFase4;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
