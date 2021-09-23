package co.com.coomeva.ele.entidades.planchas.dosmildoce;

import java.sql.Timestamp;

/**
 * EleProcesoLog entity. @author MyEclipse Persistence Tools
 */

public class EleProcesoLog implements java.io.Serializable {

	// Fields

	private Long consecutivoProLog;
	private Integer codigoProcesoLog;
	private Timestamp fechaProcesoLog;
	private String estadoProcesoLog;
	private String usuarioEjecuta;
	private String descripcionProcesoLog;

	// Constructors

	/** default constructor */
	public EleProcesoLog() {
	}

	/** minimal constructor */
	public EleProcesoLog(Long consecutivoProLog) {
		this.consecutivoProLog = consecutivoProLog;
	}

	/** full constructor */
	public EleProcesoLog(Long consecutivoProLog, Integer codigoProcesoLog,
			Timestamp fechaProcesoLog, String estadoProcesoLog,
			String usuarioEjecuta, String descripcionProcesoLog) {
		this.consecutivoProLog = consecutivoProLog;
		this.codigoProcesoLog = codigoProcesoLog;
		this.fechaProcesoLog = fechaProcesoLog;
		this.estadoProcesoLog = estadoProcesoLog;
		this.usuarioEjecuta = usuarioEjecuta;
		this.descripcionProcesoLog = descripcionProcesoLog;
	}

	// Property accessors

	public Long getConsecutivoProLog() {
		return this.consecutivoProLog;
	}

	public void setConsecutivoProLog(Long consecutivoProLog) {
		this.consecutivoProLog = consecutivoProLog;
	}

	public Integer getCodigoProcesoLog() {
		return this.codigoProcesoLog;
	}

	public void setCodigoProcesoLog(Integer codigoProcesoLog) {
		this.codigoProcesoLog = codigoProcesoLog;
	}

	public Timestamp getFechaProcesoLog() {
		return this.fechaProcesoLog;
	}

	public void setFechaProcesoLog(Timestamp fechaProcesoLog) {
		this.fechaProcesoLog = fechaProcesoLog;
	}

	public String getEstadoProcesoLog() {
		return this.estadoProcesoLog;
	}

	public void setEstadoProcesoLog(String estadoProcesoLog) {
		this.estadoProcesoLog = estadoProcesoLog;
	}

	public String getUsuarioEjecuta() {
		return this.usuarioEjecuta;
	}

	public void setUsuarioEjecuta(String usuarioEjecuta) {
		this.usuarioEjecuta = usuarioEjecuta;
	}

	public String getDescripcionProcesoLog() {
		return this.descripcionProcesoLog;
	}

	public void setDescripcionProcesoLog(String descripcionProcesoLog) {
		this.descripcionProcesoLog = descripcionProcesoLog;
	}

}