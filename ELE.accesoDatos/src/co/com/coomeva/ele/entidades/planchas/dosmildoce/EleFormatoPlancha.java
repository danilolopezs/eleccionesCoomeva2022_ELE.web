package co.com.coomeva.ele.entidades.planchas.dosmildoce;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * EleFormatoPlancha entity. @author MyEclipse Persistence Tools
 */

public class EleFormatoPlancha implements java.io.Serializable {

	// Fields

	private Long consecutivo;
	private EleFormato eleFormato;
	private ElePlancha elePlancha;
	private String usuarioGenera;
	private Timestamp fechaRegistro;
	private Timestamp fechaImpresion;
	private String usuarioImpresion;

	// Constructors

	/** default constructor */
	public EleFormatoPlancha() {
	}

	/** minimal constructor */
	public EleFormatoPlancha(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	/** full constructor */
	public EleFormatoPlancha(Long consecutivo, EleFormato eleFormato,
			ElePlancha elePlancha, String usuarioGenera,
			Timestamp fechaRegistro, Timestamp fechaImpresion,
			String usuarioImpresion) {
		this.consecutivo = consecutivo;
		this.eleFormato = eleFormato;
		this.elePlancha = elePlancha;
		this.usuarioGenera = usuarioGenera;
		this.fechaRegistro = fechaRegistro;
		this.fechaImpresion = fechaImpresion;
		this.usuarioImpresion = usuarioImpresion;
	}

	// Property accessors

	public Long getConsecutivo() {
		return this.consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public EleFormato getEleFormato() {
		return this.eleFormato;
	}

	public void setEleFormato(EleFormato eleFormato) {
		this.eleFormato = eleFormato;
	}

	public ElePlancha getElePlancha() {
		return this.elePlancha;
	}

	public void setElePlancha(ElePlancha elePlancha) {
		this.elePlancha = elePlancha;
	}

	public String getUsuarioGenera() {
		return this.usuarioGenera;
	}

	public void setUsuarioGenera(String usuarioGenera) {
		this.usuarioGenera = usuarioGenera;
	}

	public Timestamp getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Timestamp getFechaImpresion() {
		return this.fechaImpresion;
	}

	public void setFechaImpresion(Timestamp fechaImpresion) {
		this.fechaImpresion = fechaImpresion;
	}

	public String getUsuarioImpresion() {
		return this.usuarioImpresion;
	}

	public void setUsuarioImpresion(String usuarioImpresion) {
		this.usuarioImpresion = usuarioImpresion;
	}

}