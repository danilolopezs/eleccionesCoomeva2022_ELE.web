package co.com.coomeva.ele.modelo;

public class EleVotanteDatosDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String documento;
	private String nombres;
	private String codZona;
	private String desZona;
	private String agencia;
	private String respuesta;

	public EleVotanteDatosDTO() {
		this.documento = "";
		this.nombres = "";
		this.codZona = "";
		this.desZona = "";
		this.agencia = "";
		this.respuesta = "";
	}
	
	public String getDocumento() {
		return documento;
	}


	public void setDocumento(String documento) {
		this.documento = documento;
	}


	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getCodZona() {
		return codZona;
	}

	public void setCodZona(String codZona) {
		this.codZona = codZona;
	}

	public String getDesZona() {
		return desZona;
	}

	public void setDesZona(String desZona) {
		this.desZona = desZona;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

}
