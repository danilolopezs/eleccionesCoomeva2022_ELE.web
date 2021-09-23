package co.com.coomeva.ele.entidades.planchas.dosmildoce;

import java.io.Serializable;

public class EleFotoFormatoPlanchaId implements Serializable {

	private Long codigoAsociado;
	private Byte codigoFormato;

	/** default constructor */
	public EleFotoFormatoPlanchaId() {
	}

	/** minimal constructor */
	public EleFotoFormatoPlanchaId(Long codigoAsociado) {
		this.codigoAsociado = codigoAsociado;
	}

	/** full constructor */
	public EleFotoFormatoPlanchaId(Long codigoAsociado, Byte codigoFormato) {
		this.codigoAsociado = codigoAsociado;
		this.codigoFormato = codigoFormato;
	}

	public Long getCodigoAsociado() {
		return codigoAsociado;
	}

	public void setCodigoAsociado(Long codigoAsociado) {
		this.codigoAsociado = codigoAsociado;
	}

	public Byte getCodigoFormato() {
		return codigoFormato;
	}

	public void setCodigoFormato(Byte codigoFormato) {
		this.codigoFormato = codigoFormato;
	}
}
