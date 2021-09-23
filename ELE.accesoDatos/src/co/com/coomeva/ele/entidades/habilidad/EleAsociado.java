package co.com.coomeva.ele.entidades.habilidad;

import java.util.HashSet;
import java.util.Set;

/**
 * Entidad de la tabla asociados
 * 
 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
 *         Pragma S.A. <br>
 * @project ELE.accesoDatos
 * @class EleAsociado
 * @date 2/11/2012
 * 
 */
public class EleAsociado implements java.io.Serializable {

	private Long codigoAsociado;
	private Long numeroDocumento;
	private String descZonaAso;
	private String descCiudadAso;
	private String codZonaAso;
	private String codCiudadAso;
	private Integer estadoAsociado;
	private Integer fechaIngreso;
	private Long codigoProfesion;
	private String descProfesion;
	private Set<EleNovedad> eleNovedads = new HashSet<EleNovedad>(0);
	private Set<EleInhabilidad> eleInhabilidads = new HashSet<EleInhabilidad>(0);

	public EleAsociado() {
	}

	public EleAsociado(Long codigoAsociado) {
		this.codigoAsociado = codigoAsociado;
	}

	public EleAsociado(Long codigoAsociado, Long numeroDocumento,
			Set<EleNovedad> eleNovedads, Set<EleInhabilidad> eleInhabilidads) {
		super();
		this.codigoAsociado = codigoAsociado;
		this.numeroDocumento = numeroDocumento;
		this.eleNovedads = eleNovedads;
		this.eleInhabilidads = eleInhabilidads;
	}

	public Long getCodigoAsociado() {
		return this.codigoAsociado;
	}

	public void setCodigoAsociado(Long codigoAsociado) {
		this.codigoAsociado = codigoAsociado;
	}

	public Long getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Set<EleNovedad> getEleNovedads() {
		return this.eleNovedads;
	}

	public void setEleNovedads(Set<EleNovedad> eleNovedads) {
		this.eleNovedads = eleNovedads;
	}

	public Set<EleInhabilidad> getEleInhabilidads() {
		return this.eleInhabilidads;
	}

	public void setEleInhabilidads(Set<EleInhabilidad> eleInhabilidads) {
		this.eleInhabilidads = eleInhabilidads;
	}

	public String getDescZonaAso() {
		return descZonaAso;
	}

	public void setDescZonaAso(String descZonaAso) {
		this.descZonaAso = descZonaAso;
	}

	public String getDescCiudadAso() {
		return descCiudadAso;
	}

	public void setDescCiudadAso(String descCiudadAso) {
		this.descCiudadAso = descCiudadAso;
	}

	public String getCodZonaAso() {
		return codZonaAso;
	}

	public void setCodZonaAso(String codZonaAso) {
		this.codZonaAso = codZonaAso;
	}

	public String getCodCiudadAso() {
		return codCiudadAso;
	}

	public void setCodCiudadAso(String codCiudadAso) {
		this.codCiudadAso = codCiudadAso;
	}

	public Integer getEstadoAsociado() {
		return estadoAsociado;
	}

	public void setEstadoAsociado(Integer estadoAsociado) {
		this.estadoAsociado = estadoAsociado;
	}

	public Integer getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Integer fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Long getCodigoProfesion() {
		return codigoProfesion;
	}

	public void setCodigoProfesion(Long codigoProfesion) {
		this.codigoProfesion = codigoProfesion;
	}

	public String getDescProfesion() {
		return descProfesion;
	}

	public void setDescProfesion(String descProfesion) {
		this.descProfesion = descProfesion;
	}
}
