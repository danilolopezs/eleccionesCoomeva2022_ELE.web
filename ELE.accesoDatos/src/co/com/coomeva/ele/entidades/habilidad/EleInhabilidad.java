package co.com.coomeva.ele.entidades.habilidad;

/**
 * Entidad de la tabla Inhabilidad
 * 
 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
 *         Pragma S.A. <br>
 * @project ELE.accesoDatos
 * @class EleInhabilidad
 * @date 2/11/2012
 *
 */
public class EleInhabilidad implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long consecutivoInhabilidad;
	private EleProcesoRegla eleProcesoRegla;
	private EleAsociado eleAsociado;
	private String observaciones;
	private Long valorMoraFin;
	private Long valorMoraMul;

	public EleInhabilidad() {
	}

	public EleInhabilidad(Long consecutivoInhabilidad) {
		this.consecutivoInhabilidad = consecutivoInhabilidad;
	}

	public EleInhabilidad(Long consecutivoInhabilidad, EleProcesoRegla eleProcesoRegla, EleAsociado eleAsociado,
			String observaciones, Long valorMoraFin, Long valorMoraMul) {
		this.consecutivoInhabilidad = consecutivoInhabilidad;
		this.eleProcesoRegla = eleProcesoRegla;
		this.eleAsociado = eleAsociado;
		this.observaciones = observaciones;
		this.valorMoraFin = valorMoraFin;
		this.valorMoraMul = valorMoraMul;
	}

	public Long getConsecutivoInhabilidad() {
		return this.consecutivoInhabilidad;
	}

	public void setConsecutivoInhabilidad(Long consecutivoInhabilidad) {
		this.consecutivoInhabilidad = consecutivoInhabilidad;
	}

	public EleProcesoRegla getEleProcesoRegla() {
		return this.eleProcesoRegla;
	}

	public void setEleProcesoRegla(EleProcesoRegla eleProcesoRegla) {
		this.eleProcesoRegla = eleProcesoRegla;
	}

	public EleAsociado getEleAsociado() {
		return this.eleAsociado;
	}

	public void setEleAsociado(EleAsociado eleAsociado) {
		this.eleAsociado = eleAsociado;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Long getValorMoraFin() {
		return valorMoraFin;
	}

	public void setValorMoraFin(Long valorMoraFin) {
		this.valorMoraFin = valorMoraFin;
	}

	public Long getValorMoraMul() {
		return valorMoraMul;
	}

	public void setValorMoraMul(Long valorMoraMul) {
		this.valorMoraMul = valorMoraMul;
	}
}
