package co.com.coomeva.ele.modelo;

import java.util.Date;

public class EleNovedadDTO {
	
	private String numeroDocumento;
	private String nombreCompletoAsociado;
	private String novedadAplicada;
	private Date fechaAplicacionNovedad;
	private String zona;
	private Date fechaCorte;
	
	public EleNovedadDTO (){
		
	}

	public EleNovedadDTO(String numeroDocumento, String nombreCompletoAsociado,
			String novedadAplicada, Date fechaAplicacionNovedad, String zona) {
		this.numeroDocumento = numeroDocumento;
		this.nombreCompletoAsociado = nombreCompletoAsociado;
		this.novedadAplicada = novedadAplicada;
		this.fechaAplicacionNovedad = fechaAplicacionNovedad;
		this.zona = zona;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNombreCompletoAsociado() {
		return nombreCompletoAsociado;
	}

	public void setNombreCompletoAsociado(String nombreCompletoAsociado) {
		this.nombreCompletoAsociado = nombreCompletoAsociado;
	}

	public String getNovedadAplicada() {
		return novedadAplicada;
	}

	public void setNovedadAplicada(String novedadAplicada) {
		this.novedadAplicada = novedadAplicada;
	}

	public Date getFechaAplicacionNovedad() {
		return fechaAplicacionNovedad;
	}

	public void setFechaAplicacionNovedad(Date fechaAplicacionNovedad) {
		this.fechaAplicacionNovedad = fechaAplicacionNovedad;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public Date getFechaCorte() {
		return fechaCorte;
	}

	public void setFechaCorte(Date fechaCorte) {
		this.fechaCorte = fechaCorte;
	}


}
