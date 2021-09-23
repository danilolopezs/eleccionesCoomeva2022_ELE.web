package co.com.coomeva.ele.entidades.juego;

import java.sql.Timestamp;

/**
 * EleParticipanteJuego entity. @author MyEclipse Persistence Tools
 */

public class EleParticipanteJuego implements java.io.Serializable {

	// Fields

	private Long consecutivo;
	private Long numeroDocumento;
	private String zonaJuego;
	private Integer faseJuego;
	private Timestamp horaTrans;

	// Constructors

	/** default constructor */
	public EleParticipanteJuego() {
	}

	/** full constructor */
	public EleParticipanteJuego(Long consecutivo, Long numeroDocumento,
			String zonaJuego, Integer faseJuego, Timestamp horaTrans) {
		this.consecutivo = consecutivo;
		this.numeroDocumento = numeroDocumento;
		this.zonaJuego = zonaJuego;
		this.faseJuego = faseJuego;
		this.horaTrans = horaTrans;
	}

	// Property accessors

	public Long getConsecutivo() {
		return this.consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public Long getNumeroDocumento() {
		return this.numeroDocumento;
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
		return this.faseJuego;
	}

	public void setFaseJuego(Integer faseJuego) {
		this.faseJuego = faseJuego;
	}

	public Timestamp getHoraTrans() {
		return this.horaTrans;
	}

	public void setHoraTrans(Timestamp horaTrans) {
		this.horaTrans = horaTrans;
	}

}