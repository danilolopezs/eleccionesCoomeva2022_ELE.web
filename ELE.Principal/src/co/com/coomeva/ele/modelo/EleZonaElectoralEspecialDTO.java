package co.com.coomeva.ele.modelo;

public class EleZonaElectoralEspecialDTO {

	private String codigo;
	private String descripcion;
	private String numeroZona;

	public EleZonaElectoralEspecialDTO() {
		this.codigo = "";
		this.descripcion = "";
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNumeroZona() {
		return numeroZona;
	}

	public void setNumeroZona(String numeroZona) {
		this.numeroZona = numeroZona;
	}
}
