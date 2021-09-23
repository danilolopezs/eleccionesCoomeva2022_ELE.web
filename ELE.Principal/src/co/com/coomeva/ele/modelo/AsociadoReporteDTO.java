package co.com.coomeva.ele.modelo;

public class AsociadoReporteDTO {

	private Long nitcli;
	private String nomAgc;
	private String nomZona;
	private String estado;
	private String regional;
	private String nomCli;
	private String medio;
	private String estadoAsociado;
	
	public String getMedio() {
		return medio;
	}
	public void setMedio(String medio) {
		this.medio = medio;
	}
	public String getNomCli() {
		return nomCli;
	}
	public void setNomCli(String nomCli) {
		this.nomCli = nomCli;
	}
	public Long getNitcli() {
		return nitcli;
	}
	public void setNitcli(Long nitcli) {
		this.nitcli = nitcli;
	}
	public String getNomAgc() {
		return nomAgc;
	}
	public void setNomAgc(String nomAgc) {
		this.nomAgc = nomAgc;
	}
	public String getNomZona() {
		return nomZona;
	}
	public void setNomZona(String nomZona) {
		this.nomZona = nomZona;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getRegional() {
		return regional;
	}
	public void setRegional(String regional) {
		this.regional = regional;
	}
	public String getEstadoAsociado() {
		return estadoAsociado;
	}
	public void setEstadoAsociado(String estadoAsociado) {
		this.estadoAsociado = estadoAsociado;
	}
	
}
