package co.com.coomeva.ele.entidades.planchas.dosmildoce;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * ElePlancha entity. @author MyEclipse Persistence Tools
 */

public class ElePlancha implements java.io.Serializable {

	// Fields

	private Long consecutivoPlancha;
	private EleZonaElectoral eleZonaElectoral;
	private Timestamp fechaRegistro;
	private Integer numeroPlancha;
	private String estadoPlancha;
	private Timestamp fechaEnvio;
	private Long codigoUsuario;
	private EleZonaElectoralEspecial eleZonaElectoralEspecial;
	private Set eleEstadoPlanchas = new HashSet(0);
	private Set eleFormatoPlanchas = new HashSet(0);

	// Constructors

	/** default constructor */
	public ElePlancha() {
	}

	/** minimal constructor */
	public ElePlancha(Long consecutivoPlancha) {
		this.consecutivoPlancha = consecutivoPlancha;
	}

	/** full constructor */
	public ElePlancha(Long consecutivoPlancha,
			EleZonaElectoral eleZonaElectoral, Timestamp fechaRegistro,
			Integer numeroPlancha, String estadoPlancha, Timestamp fechaEnvio, Set eleEstadoPlanchas,
			Set eleFormatoPlanchas, Long codigoUsuario) {
		this.consecutivoPlancha = consecutivoPlancha;
		this.eleZonaElectoral = eleZonaElectoral;
		this.fechaRegistro = fechaRegistro;
		this.numeroPlancha = numeroPlancha;
		this.estadoPlancha = estadoPlancha;
		this.fechaEnvio = fechaEnvio;
		this.eleEstadoPlanchas = eleEstadoPlanchas;
		this.eleFormatoPlanchas = eleFormatoPlanchas;
		this.codigoUsuario = codigoUsuario;
	}

	// Property accessors

	public Long getConsecutivoPlancha() {
		return this.consecutivoPlancha;
	}

	public void setConsecutivoPlancha(Long consecutivoPlancha) {
		this.consecutivoPlancha = consecutivoPlancha;
	}

	public EleZonaElectoral getEleZonaElectoral() {
		return this.eleZonaElectoral;
	}

	public void setEleZonaElectoral(EleZonaElectoral eleZonaElectoral) {
		this.eleZonaElectoral = eleZonaElectoral;
	}

	public Timestamp getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Integer getNumeroPlancha() {
		return this.numeroPlancha;
	}

	public void setNumeroPlancha(Integer numeroPlancha) {
		this.numeroPlancha = numeroPlancha;
	}

	public String getEstadoPlancha() {
		return this.estadoPlancha;
	}

	public void setEstadoPlancha(String estadoPlancha) {
		this.estadoPlancha = estadoPlancha;
	}

	public Set getEleEstadoPlanchas() {
		return this.eleEstadoPlanchas;
	}

	public void setEleEstadoPlanchas(Set eleEstadoPlanchas) {
		this.eleEstadoPlanchas = eleEstadoPlanchas;
	}

	public Set getEleFormatoPlanchas() {
		return this.eleFormatoPlanchas;
	}

	public void setEleFormatoPlanchas(Set eleFormatoPlanchas) {
		this.eleFormatoPlanchas = eleFormatoPlanchas;
	}

	public Timestamp getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Timestamp fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
	
	public Long getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Long codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public EleZonaElectoralEspecial getEleZonaElectoralEspecial() {
		return eleZonaElectoralEspecial;
	}

	public void setEleZonaElectoralEspecial(
			EleZonaElectoralEspecial eleZonaElectoralEspecial) {
		this.eleZonaElectoralEspecial = eleZonaElectoralEspecial;
	}
	
	
}