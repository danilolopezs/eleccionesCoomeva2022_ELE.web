package co.com.coomeva.ele.modelo;

public class EleUsuarioComisionDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String idUsuario;
	private String codigoZonaEle;
	private String desZonaEle;
	private Integer codAgenciaUsuario;
	private String desAgenciaUsuario;
	private String codigoZonaEleEsp;
	private String desZonaEleEsp;

	public EleUsuarioComisionDTO() {
		this.codAgenciaUsuario = 0;
		this.codigoZonaEle = "";
		this.desZonaEle = "";
		this.idUsuario = "";
		this.desAgenciaUsuario = "";
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCodigoZonaEle() {
		return codigoZonaEle;
	}

	public void setCodigoZonaEle(String codigoZonaEle) {
		this.codigoZonaEle = codigoZonaEle;
	}

	public String getDesZonaEle() {
		return desZonaEle;
	}

	public void setDesZonaEle(String desZonaEle) {
		this.desZonaEle = desZonaEle;
	}

	public Integer getCodAgenciaUsuario() {
		return codAgenciaUsuario;
	}

	public void setCodAgenciaUsuario(Integer codAgenciaUsuario) {
		this.codAgenciaUsuario = codAgenciaUsuario;
	}

	public String getDesAgenciaUsuario() {
		return desAgenciaUsuario;
	}

	public void setDesAgenciaUsuario(String desAgenciaUsuario) {
		this.desAgenciaUsuario = desAgenciaUsuario;
	}

	public String getCodigoZonaEleEsp() {
		return codigoZonaEleEsp;
	}

	public void setCodigoZonaEleEsp(String codigoZonaEleEsp) {
		this.codigoZonaEleEsp = codigoZonaEleEsp;
	}

	public String getDesZonaEleEsp() {
		return desZonaEleEsp;
	}

	public void setDesZonaEleEsp(String desZonaEleEsp) {
		this.desZonaEleEsp = desZonaEleEsp;
	}

}
