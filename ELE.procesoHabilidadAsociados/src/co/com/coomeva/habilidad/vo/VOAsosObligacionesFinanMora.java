package co.com.coomeva.habilidad.vo;


/**
 * Value Object para la transferencia de datos entre el datasource y la regla que
 * ejecuta la validación de los asociados en mora
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
 * @project ELE.procesoHabilidadAsociados
 * @class VOAsosObligacionesFinanMora
 * @date 3/11/2012
 */
public class VOAsosObligacionesFinanMora {
	
	private Long numintAsociado;
	private Long valorMoraFinanciera;
	private Long valorMoraMultiactiva;
	private String observacionInhabilidad;
	
	public Long getNumintAsociado() {
		return numintAsociado;
	}
	
	public void setNumintAsociado(Long numintAsociado) {
		this.numintAsociado = numintAsociado;
	}
	public Long getValorMoraMultiactiva() {
		return valorMoraMultiactiva;
	}
	public void setValorMoraMultiactiva(Long valorMoraMultiactiva) {
		this.valorMoraMultiactiva = valorMoraMultiactiva;
	}
	public Long getValorMoraFinanciera() {
		return valorMoraFinanciera;
	}
	public void setValorMoraFinanciera(Long valorMoraFinanciera) {
		this.valorMoraFinanciera = valorMoraFinanciera;
	}

	public String getObservacionInhabilidad() {
		return observacionInhabilidad;
	}

	public void setObservacionInhabilidad(String observacionInhabilidad) {
		this.observacionInhabilidad = observacionInhabilidad;
	}
	
}
