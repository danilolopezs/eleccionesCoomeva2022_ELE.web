package co.com.coomeva.ele.entidades.planchas.dosmildoce;

import java.sql.Timestamp;
import java.util.Date;

/**
 * EleAsocOtrasEntElect entity. @author MyEclipse Persistence Tools
 */

public class EleAsocOtrasEntElect implements java.io.Serializable {

	// Fields

	private Long codigoAsociado;
	private String tipoEnt;
	private Date fechaRegistro;
	private String usuario;

	// Constructors

	/** default constructor */
	public EleAsocOtrasEntElect() {
	}

	/** full constructor */
	public EleAsocOtrasEntElect(Long codigoAsociado, String tipoEnt,
			Date fechaRegistro, String usuario) {
		this.codigoAsociado = codigoAsociado;
		this.tipoEnt = tipoEnt;
		this.fechaRegistro = fechaRegistro;
		this.usuario = usuario;
	}

	// Property accessors



	public String getTipoEnt() {
		return this.tipoEnt;
	}

	public void setTipoEnt(String tipoEnt) {
		this.tipoEnt = tipoEnt;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

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

}