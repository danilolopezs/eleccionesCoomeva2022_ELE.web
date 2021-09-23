package co.com.coomeva.ele.entidades.planchas;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractEleZonas entity provides the base persistence definition of the
 * EleZonas entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractEleZonas implements java.io.Serializable {

	// Fields

	private String codZona;
	private Long maxPrincipales;
	private String zonEspecial;
	private String nomZona;
	private Set elePlanchases = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractEleZonas() {
	}

	/** minimal constructor */
	public AbstractEleZonas(String codZona, Long maxPrincipales,
			String zonEspecial, String nomZona) {
		this.codZona = codZona;
		this.maxPrincipales = maxPrincipales;
		this.zonEspecial = zonEspecial;
		this.nomZona = nomZona;
	}

	/** full constructor */
	public AbstractEleZonas(String codZona, Long maxPrincipales,
			String zonEspecial, String nomZona, Set elePlanchases) {
		this.codZona = codZona;
		this.maxPrincipales = maxPrincipales;
		this.zonEspecial = zonEspecial;
		this.nomZona = nomZona;
		this.elePlanchases = elePlanchases;
	}

	// Property accessors

	public String getCodZona() {
		return this.codZona;
	}

	public void setCodZona(String codZona) {
		this.codZona = codZona;
	}

	public Long getMaxPrincipales() {
		return this.maxPrincipales;
	}

	public void setMaxPrincipales(Long maxPrincipales) {
		this.maxPrincipales = maxPrincipales;
	}

	public String getZonEspecial() {
		return this.zonEspecial;
	}

	public void setZonEspecial(String zonEspecial) {
		this.zonEspecial = zonEspecial;
	}

	public String getNomZona() {
		return this.nomZona;
	}

	public void setNomZona(String nomZona) {
		this.nomZona = nomZona;
	}

	public Set getElePlanchases() {
		return this.elePlanchases;
	}

	public void setElePlanchases(Set elePlanchases) {
		this.elePlanchases = elePlanchases;
	}

}