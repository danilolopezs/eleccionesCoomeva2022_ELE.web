package co.com.coomeva.ele.dto;

import java.util.List;

/**
 * DTO que sirve para el transporte de información que permite determinar si
 * el asociado es hábil o no para elegir y ser elegido en las elecciones de delegados
 * @author <a href="mailto:javiero.londono@premize.com">Javier Londoño</a> - Premize SAS <br>
 * @project ELE.Principal
 * @class DTOHabilidadAsociado
 * @date 13/11/2012
 *
 */
public class DTOHabilidadAsociado {
	
	private Boolean asociadoHabil;
	private List<String> observacionesInhabilidades;
	private String nombreAsociado;
	private String documento;
	
	public Boolean getAsociadoHabil() {
		return asociadoHabil;
	}
	public void setAsociadoHabil(Boolean asociadoHabil) {
		this.asociadoHabil = asociadoHabil;
	}
	public List<String> getObservacionesInhabilidades() {
		return observacionesInhabilidades;
	}
	public void setObservacionesInhabilidades(
			List<String> observacionesInhabilidades) {
		this.observacionesInhabilidades = observacionesInhabilidades;
	}
	public String getNombreAsociado() {
		return nombreAsociado;
	}
	public void setNombreAsociado(String nombreAsociado) {
		this.nombreAsociado = nombreAsociado;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}	
}
