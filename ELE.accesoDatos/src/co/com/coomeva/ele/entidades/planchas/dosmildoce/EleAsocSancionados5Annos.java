package co.com.coomeva.ele.entidades.planchas.dosmildoce;

import java.util.Date;

/**
 * EleAsocSancionados5Annos entity. @author MyEclipse Persistence Tools
 */

public class EleAsocSancionados5Annos implements java.io.Serializable {

	// Fields

	private Long codigoAsociado;
	private Date fechaRegistro;
	private String usuario;
	private Date fechaSuspension;
	private String motivo;

	// Constructors

	/** default constructor */
	public EleAsocSancionados5Annos() {
	}

	


	public EleAsocSancionados5Annos(Long codigoAsociado, Date fechaRegistro,
			String usuario, Date fechaSuspension, String motivo) {
		
		this.codigoAsociado = codigoAsociado;
		this.fechaRegistro = fechaRegistro;
		this.usuario = usuario;
		this.fechaSuspension = fechaSuspension;
		this.motivo = motivo;
	}




	// Property accessors

	


	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Long getCodigoAsociado() {
		return codigoAsociado;
	}

	public void setCodigoAsociado(Long codigoAsociado) {
		this.codigoAsociado = codigoAsociado;
	}



	public Date getFechaRegistro() {
		return fechaRegistro;
	}



	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}



	public Date getFechaSuspension() {
		return fechaSuspension;
	}



	public void setFechaSuspension(Date fechaSuspension) {
		this.fechaSuspension = fechaSuspension;
	}




	public String getMotivo() {
		return motivo;
	}




	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}




}