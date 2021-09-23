package co.com.coomeva.ele.entidades.admhabilidad;

import java.util.Date;

/**
 * AbstractLogTransacciones entity provides the base persistence definition of
 * the LogTransacciones entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractLogTransacciones implements java.io.Serializable {

	// Fields

	private Long consLog;
	private String nroIdentificacion;
	private String anthabil;
	private String habil;
	private String concpTransaccion;
	private Date fecha;
	private String usuario;

	// Constructors

	/** default constructor */
	public AbstractLogTransacciones() {
	}

	/** full constructor */
	public AbstractLogTransacciones(Long consLog, String nroIdentificacion,
			String anthabil, String habil, String concpTransaccion, Date fecha,
			String usuario) {
		this.consLog = consLog;
		this.nroIdentificacion = nroIdentificacion;
		this.anthabil = anthabil;
		this.habil = habil;
		this.concpTransaccion = concpTransaccion;
		this.fecha = fecha;
		this.usuario = usuario;
	}

	// Property accessors

	public Long getConsLog() {
		return this.consLog;
	}

	public void setConsLog(Long consLog) {
		this.consLog = consLog;
	}

	public String getNroIdentificacion() {
		return this.nroIdentificacion;
	}

	public void setNroIdentificacion(String nroIdentificacion) {
		this.nroIdentificacion = nroIdentificacion;
	}

	public String getAnthabil() {
		return this.anthabil;
	}

	public void setAnthabil(String anthabil) {
		this.anthabil = anthabil;
	}

	public String getHabil() {
		return this.habil;
	}

	public void setHabil(String habil) {
		this.habil = habil;
	}

	public String getConcpTransaccion() {
		return this.concpTransaccion;
	}

	public void setConcpTransaccion(String concpTransaccion) {
		this.concpTransaccion = concpTransaccion;
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

}