package co.com.coomeva.ele.modelo;

import java.sql.Timestamp;

public class EleParticipanteJuegoDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long consecutivo;
	private Long numeroDocumento;
	private String zonaJuego;
	private Integer faseJuego;
	private Timestamp horaTrans;
	private String desRegional;
	private String nombreAsociado;

	public EleParticipanteJuegoDTO() {
		this.zonaJuego = "";
		this.consecutivo = 0L;
		this.faseJuego = 0;
		this.horaTrans = null;
		this.numeroDocumento = 0L;
		this.desRegional = "";
		this.nombreAsociado = "";
	}

	public Long getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public Long getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getZonaJuego() {
		return zonaJuego;
	}

	public void setZonaJuego(String zonaJuego) {
		this.zonaJuego = zonaJuego;
	}

	public Integer getFaseJuego() {
		return faseJuego;
	}

	public void setFaseJuego(Integer faseJuego) {
		this.faseJuego = faseJuego;
	}

	public Timestamp getHoraTrans() {
		return horaTrans;
	}

	public void setHoraTrans(Timestamp horaTrans) {
		this.horaTrans = horaTrans;
	}

	public String getDesRegional() {
		return desRegional;
	}

	public void setDesRegional(String desRegional) {
		this.desRegional = desRegional;
	}

	public String getNombreAsociado() {
		return nombreAsociado;
	}

	public void setNombreAsociado(String nombreAsociado) {
		this.nombreAsociado = nombreAsociado;
	}

}
