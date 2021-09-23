package co.com.coomeva.ele.entidades.planchas.dosmildoce;

import java.sql.Timestamp;

/**
 * EleLogAsociado entity. @author MyEclipse Persistence Tools
 */

public class EleLogAsociado implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tipoTrn;
	private String ipTrn;
	private Timestamp fecha;
	private String nroIdentificacion;
	private String descripcion;

	// Constructors

	/** default constructor */
	public EleLogAsociado() {
	}

	/** minimal constructor */
	public EleLogAsociado(Integer id, String tipoTrn, String ipTrn,
			Timestamp fecha, String nroIdentificacion) {
		this.id = id;
		this.tipoTrn = tipoTrn;
		this.ipTrn = ipTrn;
		this.fecha = fecha;
		this.nroIdentificacion = nroIdentificacion;
	}

	/** full constructor */
	public EleLogAsociado(Integer id, String tipoTrn, String ipTrn,
			Timestamp fecha, String nroIdentificacion, String descripcion) {
		this.id = id;
		this.tipoTrn = tipoTrn;
		this.ipTrn = ipTrn;
		this.fecha = fecha;
		this.nroIdentificacion = nroIdentificacion;
		this.descripcion = descripcion;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoTrn() {
		return this.tipoTrn;
	}

	public void setTipoTrn(String tipoTrn) {
		this.tipoTrn = tipoTrn;
	}

	public String getIpTrn() {
		return this.ipTrn;
	}

	public void setIpTrn(String ipTrn) {
		this.ipTrn = ipTrn;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getNroIdentificacion() {
		return this.nroIdentificacion;
	}

	public void setNroIdentificacion(String nroIdentificacion) {
		this.nroIdentificacion = nroIdentificacion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}