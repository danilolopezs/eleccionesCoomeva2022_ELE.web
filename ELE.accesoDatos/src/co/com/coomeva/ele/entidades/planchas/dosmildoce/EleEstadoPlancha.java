package co.com.coomeva.ele.entidades.planchas.dosmildoce;

import java.sql.Timestamp;
import java.util.Date;

/**
 * EleEstadoPlancha entity. @author MyEclipse Persistence Tools
 */

public class EleEstadoPlancha implements java.io.Serializable {

	// Fields

	private Long consecutivoEstadoPlancha;
	private ElePlancha elePlancha;
	private String usuarioEstado;
	private Date fechaRegistro;
	private String estadoPlancha;

	// Constructors

	/** default constructor */
	public EleEstadoPlancha() {
	}

	/** minimal constructor */
	public EleEstadoPlancha(Long consecutivoEstadoPlancha) {
		this.consecutivoEstadoPlancha = consecutivoEstadoPlancha;
	}

	/** full constructor */
	public EleEstadoPlancha(Long consecutivoEstadoPlancha,
			ElePlancha elePlancha, String usuarioEstado,
			Date fechaRegistro, String estadoPlancha) {
		this.consecutivoEstadoPlancha = consecutivoEstadoPlancha;
		this.elePlancha = elePlancha;
		this.usuarioEstado = usuarioEstado;
		this.fechaRegistro = fechaRegistro;
		this.estadoPlancha = estadoPlancha;
	}

	// Property accessors

	public Long getConsecutivoEstadoPlancha() {
		return this.consecutivoEstadoPlancha;
	}

	public void setConsecutivoEstadoPlancha(Long consecutivoEstadoPlancha) {
		this.consecutivoEstadoPlancha = consecutivoEstadoPlancha;
	}

	public ElePlancha getElePlancha() {
		return this.elePlancha;
	}

	public void setElePlancha(ElePlancha elePlancha) {
		this.elePlancha = elePlancha;
	}

	public String getUsuarioEstado() {
		return this.usuarioEstado;
	}

	public void setUsuarioEstado(String usuarioEstado) {
		this.usuarioEstado = usuarioEstado;
	}

	

	public String getEstadoPlancha() {
		return this.estadoPlancha;
	}

	public void setEstadoPlancha(String estadoPlancha) {
		this.estadoPlancha = estadoPlancha;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

}