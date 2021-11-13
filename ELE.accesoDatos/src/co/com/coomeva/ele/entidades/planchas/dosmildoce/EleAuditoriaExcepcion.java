package co.com.coomeva.ele.entidades.planchas.dosmildoce;

import java.util.Date;

/**
 * EleAuditoriaExcepcion entity. @author MyEclipse Persistence Tools
 */

public class EleAuditoriaExcepcion implements java.io.Serializable {

	// Fields
	private Long consecutivo;
	private Long numeroDocumento;
	private String usuario;
	private Date fechaSuspension;
	private String motivoSuspension;
	private String tipoExcepcion;
	private Date fechaRegistro;

	// Constructors

	/** default constructor */
	public EleAuditoriaExcepcion() {
	}

	/** minimal constructor */
	public EleAuditoriaExcepcion(Long numeroDocumento, String usuario,
			Date fechaRegistro) {
		this.numeroDocumento = numeroDocumento;
		this.usuario = usuario;
		this.fechaRegistro = fechaRegistro;
	}

	public EleAuditoriaExcepcion(Long consecutivo, Long numeroDocumento,
			String usuario, Date fechaSuspension, String motivoSuspension,
			String tipoExcepcion, Date fechaRegistro) {
	
		this.consecutivo = consecutivo;
		this.numeroDocumento = numeroDocumento;
		this.usuario = usuario;
		this.fechaSuspension = fechaSuspension;
		this.motivoSuspension = motivoSuspension;
		this.tipoExcepcion = tipoExcepcion;
		this.fechaRegistro = fechaRegistro;
	}

	// Property accessors

	

	public Long getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFechaSuspension() {
		return this.fechaSuspension;
	}

	public void setFechaSuspension(Date fechaSuspension) {
		this.fechaSuspension = fechaSuspension;
	}

	public String getMotivoSuspension() {
		return this.motivoSuspension;
	}

	public void setMotivoSuspension(String motivoSuspension) {
		this.motivoSuspension = motivoSuspension;
	}

	public String getTipoExcepcion() {
		return this.tipoExcepcion;
	}

	public void setTipoExcepcion(String tipoExcepcion) {
		this.tipoExcepcion = tipoExcepcion;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Long getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

}