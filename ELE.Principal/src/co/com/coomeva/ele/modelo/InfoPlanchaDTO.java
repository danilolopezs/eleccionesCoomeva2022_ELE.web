package co.com.coomeva.ele.modelo;

public class InfoPlanchaDTO {
	private String codZona;
	private String zona;
	private String numPlancha;
	private String estado;
	private String fecha;
	private Long consecutivoPlancha;
	private Long codAsociado;
	private String codigoEstadoPlancha;

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNumPlancha() {
		return numPlancha;
	}

	public void setNumPlancha(String numPlancha) {
		this.numPlancha = numPlancha;
	}

	public Long getConsecutivoPlancha() {
		return consecutivoPlancha;
	}

	public void setConsecutivoPlancha(Long consecutivoPlancha) {
		this.consecutivoPlancha = consecutivoPlancha;
	}

	public String getCodZona() {
		return codZona;
	}

	public void setCodZona(String codZona) {
		this.codZona = codZona;
	}

	public Long getCodAsociado() {
		return codAsociado;
	}

	public void setCodAsociado(Long codAsociado) {
		this.codAsociado = codAsociado;
	}

	public String getCodigoEstadoPlancha() {
		return codigoEstadoPlancha;
	}

	public void setCodigoEstadoPlancha(String codigoEstadoPlancha) {
		this.codigoEstadoPlancha = codigoEstadoPlancha;
	}
	
	

}
