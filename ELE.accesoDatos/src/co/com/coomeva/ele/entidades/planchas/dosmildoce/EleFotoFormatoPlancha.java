package co.com.coomeva.ele.entidades.planchas.dosmildoce;

import java.io.Serializable;

public class EleFotoFormatoPlancha implements Serializable {

	private EleFotoFormatoPlanchaId id;
	private String fotoAsociado;

	/** default constructor */
	public EleFotoFormatoPlancha() {
	}

	/** minimal constructor */
	public EleFotoFormatoPlancha(EleFotoFormatoPlanchaId id) {
		this.id = id;
	}

	/** full constructor */
	public EleFotoFormatoPlancha(EleFotoFormatoPlanchaId id, String fotoAsociado) {
		this.id = id;
		this.fotoAsociado = fotoAsociado;
	}

	public EleFotoFormatoPlanchaId getId() {
		return id;
	}

	public void setId(EleFotoFormatoPlanchaId id) {
		this.id = id;
	}

	public String getFotoAsociado() {
		return fotoAsociado;
	}

	public void setFotoAsociado(String fotoAsociado) {
		this.fotoAsociado = fotoAsociado;
	}
}
