package co.com.coomeva.ele.entidades.planchas;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractElePParametros entity provides the base persistence definition of the
 * ElePParametros entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractElePParametros implements java.io.Serializable {

	// Fields

	private Long codigoParametro;
	private String nombreParametro;
	private String valorParametro;
	private Long codigoEstado;
	private String tipoParametro;
	private Set elePValorParametroses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractElePParametros() {
	}

	/** minimal constructor */
	public AbstractElePParametros(Long codigoParametro, String nombreParametro,
			Long codigoEstado, String tipoParametro) {
		this.codigoParametro = codigoParametro;
		this.nombreParametro = nombreParametro;
		this.codigoEstado = codigoEstado;
		this.tipoParametro = tipoParametro;
	}

	/** full constructor */
	public AbstractElePParametros(Long codigoParametro, String nombreParametro,
			String valorParametro, Long codigoEstado, String tipoParametro,
			Set elePValorParametroses) {
		this.codigoParametro = codigoParametro;
		this.nombreParametro = nombreParametro;
		this.valorParametro = valorParametro;
		this.codigoEstado = codigoEstado;
		this.tipoParametro = tipoParametro;
		this.elePValorParametroses = elePValorParametroses;
	}

	// Property accessors

	public Long getCodigoParametro() {
		return this.codigoParametro;
	}

	public void setCodigoParametro(Long codigoParametro) {
		this.codigoParametro = codigoParametro;
	}

	public String getNombreParametro() {
		return this.nombreParametro;
	}

	public void setNombreParametro(String nombreParametro) {
		this.nombreParametro = nombreParametro;
	}

	public String getValorParametro() {
		return this.valorParametro;
	}

	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}

	public Long getCodigoEstado() {
		return this.codigoEstado;
	}

	public void setCodigoEstado(Long codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public String getTipoParametro() {
		return this.tipoParametro;
	}

	public void setTipoParametro(String tipoParametro) {
		this.tipoParametro = tipoParametro;
	}

	public Set getElePValorParametroses() {
		return this.elePValorParametroses;
	}

	public void setElePValorParametroses(Set elePValorParametroses) {
		this.elePValorParametroses = elePValorParametroses;
	}

}