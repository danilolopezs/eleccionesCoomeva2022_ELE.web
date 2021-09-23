package co.com.coomeva.ele.entidades.planchas;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractElePlanchas entity provides the base persistence definition of the
 * ElePlanchas entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractElePlanchas implements java.io.Serializable {

	// Fields

	private String nroCabPlancha;
	private EleZonas eleZonas;
	private Date fechaInscripcion;
	private String estado;
	private Long nroPlancha;
	private String descEstado;
	private Set eleSuplenteses = new HashSet(0);
	private Set eleCabPlanchas = new HashSet(0);
	private Set elePrincipaleses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractElePlanchas() {
	}

	/** minimal constructor */
	public AbstractElePlanchas(String nroCabPlancha, EleZonas eleZonas,
			Date fechaInscripcion, String estado) {
		this.nroCabPlancha = nroCabPlancha;
		this.eleZonas = eleZonas;
		this.fechaInscripcion = fechaInscripcion;
		this.estado = estado;
	}

	/** full constructor */
	public AbstractElePlanchas(String nroCabPlancha, EleZonas eleZonas,
			Date fechaInscripcion, String estado, Long nroPlancha,
			String descEstado, Set eleSuplenteses, Set eleCabPlanchas,
			Set elePrincipaleses) {
		this.nroCabPlancha = nroCabPlancha;
		this.eleZonas = eleZonas;
		this.fechaInscripcion = fechaInscripcion;
		this.estado = estado;
		this.nroPlancha = nroPlancha;
		this.descEstado = descEstado;
		this.eleSuplenteses = eleSuplenteses;
		this.eleCabPlanchas = eleCabPlanchas;
		this.elePrincipaleses = elePrincipaleses;
	}

	// Property accessors

	public String getNroCabPlancha() {
		return this.nroCabPlancha;
	}

	public void setNroCabPlancha(String nroCabPlancha) {
		this.nroCabPlancha = nroCabPlancha;
	}

	public EleZonas getEleZonas() {
		return this.eleZonas;
	}

	public void setEleZonas(EleZonas eleZonas) {
		this.eleZonas = eleZonas;
	}

	public Date getFechaInscripcion() {
		return this.fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getNroPlancha() {
		return this.nroPlancha;
	}

	public void setNroPlancha(Long nroPlancha) {
		this.nroPlancha = nroPlancha;
	}

	public String getDescEstado() {
		return this.descEstado;
	}

	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}

	public Set getEleSuplenteses() {
		return this.eleSuplenteses;
	}

	public void setEleSuplenteses(Set eleSuplenteses) {
		this.eleSuplenteses = eleSuplenteses;
	}

	public Set getEleCabPlanchas() {
		return this.eleCabPlanchas;
	}

	public void setEleCabPlanchas(Set eleCabPlanchas) {
		this.eleCabPlanchas = eleCabPlanchas;
	}

	public Set getElePrincipaleses() {
		return this.elePrincipaleses;
	}

	public void setElePrincipaleses(Set elePrincipaleses) {
		this.elePrincipaleses = elePrincipaleses;
	}

}