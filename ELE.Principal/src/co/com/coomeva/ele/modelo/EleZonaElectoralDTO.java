package co.com.coomeva.ele.modelo;


public class EleZonaElectoralDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String codigo;
	private String descripcion;

	public EleZonaElectoralDTO() {
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
	
	
}
