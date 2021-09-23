package co.com.coomeva.ele.entidades.planchas;

import java.util.Date;

/**
 * AbstractEleLog entity provides the base persistence definition of the EleLog
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractEleLog implements java.io.Serializable {

	// Fields

	private Long consecutivo;
	private String nroCaboPlancha;
	private String tipotrans;
	private Date fecha;
	private String usuario;
	private String descripcion;

	// Constructors

	/** default constructor */
	public AbstractEleLog() {
	}

	/** full constructor */
	public AbstractEleLog(Long consecutivo, String nroCaboPlancha,
			String tipotrans, Date fecha, String usuario, String descripcion) {
		this.consecutivo = consecutivo;
		this.nroCaboPlancha = nroCaboPlancha;
		this.tipotrans = tipotrans;
		this.fecha = fecha;
		this.usuario = usuario;
		this.descripcion = descripcion;
	}

	// Property accessors

	public Long getConsecutivo() {
		return this.consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public String getNroCaboPlancha() {
		return this.nroCaboPlancha;
	}

	public void setNroCaboPlancha(String nroCaboPlancha) {
		this.nroCaboPlancha = nroCaboPlancha;
	}

	public String getTipotrans() {
		return this.tipotrans;
	}

	public void setTipotrans(String tipotrans) {
		this.tipotrans = tipotrans;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}