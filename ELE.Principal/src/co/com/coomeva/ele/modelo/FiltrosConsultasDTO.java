package co.com.coomeva.ele.modelo;

public class FiltrosConsultasDTO {
	
	private Long codigoFiltro;
	private String descripcionFiltro;
	
	public FiltrosConsultasDTO(){
	}

	public FiltrosConsultasDTO(Long codigoFiltro, String descripcionFiltro) {
		super();
		this.codigoFiltro = codigoFiltro;
		this.descripcionFiltro = descripcionFiltro;
	}

	public Long getCodigoFiltro() {
		return codigoFiltro;
	}

	public void setCodigoFiltro(Long codigoFiltro) {
		this.codigoFiltro = codigoFiltro;
	}

	public String getDescripcionFiltro() {
		return descripcionFiltro;
	}

	public void setDescripcionFiltro(String descripcionFiltro) {
		this.descripcionFiltro = descripcionFiltro;
	}
	
	

}
