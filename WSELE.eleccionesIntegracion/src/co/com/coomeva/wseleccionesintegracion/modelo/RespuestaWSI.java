package co.com.coomeva.wseleccionesintegracion.modelo;

import java.io.Serializable;

public class RespuestaWSI implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codRespuesta;
	private String descRespuesta;
	private Long cedula;
	private String zona;
	private Integer fase;

	public RespuestaWSI() {
		this.cedula = 0L;
		this.codRespuesta = "";
		this.descRespuesta = "";
		this.fase = 0;
		this.zona = "";
	}

	public String getCodRespuesta() {
		return codRespuesta;
	}

	public void setCodRespuesta(String codRespuesta) {
		this.codRespuesta = codRespuesta;
	}

	public String getDescRespuesta() {
		return descRespuesta;
	}

	public void setDescRespuesta(String descRespuesta) {
		this.descRespuesta = descRespuesta;
	}

	public Long getCedula() {
		return cedula;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public Integer getFase() {
		return fase;
	}

	public void setFase(Integer fase) {
		this.fase = fase;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}