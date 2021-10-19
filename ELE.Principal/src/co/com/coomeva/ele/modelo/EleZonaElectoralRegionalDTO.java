package co.com.coomeva.ele.modelo;

import java.io.Serializable;

public class EleZonaElectoralRegionalDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long codigoZona;
	private Long codigoRegional;

	public EleZonaElectoralRegionalDTO() {
		this.codigoZona = 0l;
		this.codigoRegional = 0l;
	}

	public Long getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(Long codigoZona) {
		this.codigoZona = codigoZona;
	}

	public Long getCodigoRegional() {
		return codigoRegional;
	}

	public void setCodigoRegional(Long codigoRegional) {
		this.codigoRegional = codigoRegional;
	}
}
