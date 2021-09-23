package co.com.coomeva.ele.entidades.planchas.dosmildoce;

import java.util.HashSet;
import java.util.Set;

/**
 * EleFormato entity. @author MyEclipse Persistence Tools
 */

public class EleFormato implements java.io.Serializable {

	// Fields

	private Byte codigoFormato;
	private String descripcionFormato;
	private String estadoFormato;
	private Set eleFormatoPlanchas = new HashSet(0);

	// Constructors

	/** default constructor */
	public EleFormato() {
	}

	/** minimal constructor */
	public EleFormato(Byte codigoFormato) {
		this.codigoFormato = codigoFormato;
	}

	/** full constructor */
	public EleFormato(Byte codigoFormato, String descripcionFormato,
			String estadoFormato, Set eleFormatoPlanchas) {
		this.codigoFormato = codigoFormato;
		this.descripcionFormato = descripcionFormato;
		this.estadoFormato = estadoFormato;
		this.eleFormatoPlanchas = eleFormatoPlanchas;
	}

	// Property accessors

	public Byte getCodigoFormato() {
		return this.codigoFormato;
	}

	public void setCodigoFormato(Byte codigoFormato) {
		this.codigoFormato = codigoFormato;
	}

	public String getDescripcionFormato() {
		return this.descripcionFormato;
	}

	public void setDescripcionFormato(String descripcionFormato) {
		this.descripcionFormato = descripcionFormato;
	}

	public String getEstadoFormato() {
		return this.estadoFormato;
	}

	public void setEstadoFormato(String estadoFormato) {
		this.estadoFormato = estadoFormato;
	}

	public Set getEleFormatoPlanchas() {
		return this.eleFormatoPlanchas;
	}

	public void setEleFormatoPlanchas(Set eleFormatoPlanchas) {
		this.eleFormatoPlanchas = eleFormatoPlanchas;
	}

}