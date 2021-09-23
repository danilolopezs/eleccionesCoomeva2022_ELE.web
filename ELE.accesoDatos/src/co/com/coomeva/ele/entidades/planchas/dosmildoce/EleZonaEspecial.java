package co.com.coomeva.ele.entidades.planchas.dosmildoce;

/**
 * EleZona entity. @author MyEclipse Persistence Tools
 */

public class EleZonaEspecial implements java.io.Serializable {

	// Fields

	private String codigoZona;
	private EleZonaElectoral eleZonaElectoral;
	private String descripcionZona;

	// Constructors

	/** default constructor */
	public EleZonaEspecial() {
	}

	/** minimal constructor */
	public EleZonaEspecial(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/** full constructor */
	public EleZonaEspecial(String codigoZona, EleZonaElectoral eleZonaElectoral,
			String descripcionZona) {
		this.codigoZona = codigoZona;
		this.eleZonaElectoral = eleZonaElectoral;
		this.descripcionZona = descripcionZona;
	}

	// Property accessors

	public String getCodigoZona() {
		return this.codigoZona;
	}

	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	public EleZonaElectoral getEleZonaElectoral() {
		return this.eleZonaElectoral;
	}

	public void setEleZonaElectoral(EleZonaElectoral eleZonaElectoral) {
		this.eleZonaElectoral = eleZonaElectoral;
	}

	public String getDescripcionZona() {
		return this.descripcionZona;
	}

	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}

}