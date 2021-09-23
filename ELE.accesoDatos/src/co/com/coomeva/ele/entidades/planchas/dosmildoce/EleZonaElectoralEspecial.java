package co.com.coomeva.ele.entidades.planchas.dosmildoce;

import java.util.HashSet;
import java.util.Set;

/**
 * EleZonaElectoral entity. @author MyEclipse Persistence Tools
 */

public class EleZonaElectoralEspecial implements java.io.Serializable {

	// Fields

	private String codigoZonaEle;
	private String descripcionZonaEle;
	private String zonaEspecial;
	private Integer maxPrincipales;
	private String numeroZona;
	private Set eleZonas = new HashSet(0);
	private Set elePlanchas = new HashSet(0);

	// Constructors

	/** default constructor */
	public EleZonaElectoralEspecial() {
	}

	/** minimal constructor */
	public EleZonaElectoralEspecial(String codigoZonaEle) {
		this.codigoZonaEle = codigoZonaEle;
	}

	/** full constructor */
	public EleZonaElectoralEspecial(String codigoZonaEle, String descripcionZonaEle,
			String zonaEspecial, Integer maxPrincipales, Set eleZonas,
			Set elePlanchas) {
		this.codigoZonaEle = codigoZonaEle;
		this.descripcionZonaEle = descripcionZonaEle;
		this.zonaEspecial = zonaEspecial;
		this.maxPrincipales = maxPrincipales;
		this.eleZonas = eleZonas;
		this.elePlanchas = elePlanchas;
	}

	// Property accessors

	public String getCodigoZonaEle() {
		return this.codigoZonaEle;
	}

	public void setCodigoZonaEle(String codigoZonaEle) {
		this.codigoZonaEle = codigoZonaEle;
	}

	public String getDescripcionZonaEle() {
		return this.descripcionZonaEle;
	}

	public void setDescripcionZonaEle(String descripcionZonaEle) {
		this.descripcionZonaEle = descripcionZonaEle;
	}

	public String getZonaEspecial() {
		return this.zonaEspecial;
	}

	public void setZonaEspecial(String zonaEspecial) {
		this.zonaEspecial = zonaEspecial;
	}

	public Integer getMaxPrincipales() {
		return this.maxPrincipales;
	}

	public void setMaxPrincipales(Integer maxPrincipales) {
		this.maxPrincipales = maxPrincipales;
	}

	public Set getEleZonas() {
		return this.eleZonas;
	}

	public void setEleZonas(Set eleZonas) {
		this.eleZonas = eleZonas;
	}

	public Set getElePlanchas() {
		return this.elePlanchas;
	}

	public void setElePlanchas(Set elePlanchas) {
		this.elePlanchas = elePlanchas;
	}

	public String getNumeroZona() {
		return numeroZona;
	}

	public void setNumeroZona(String numeroZona) {
		this.numeroZona = numeroZona;
	}

}