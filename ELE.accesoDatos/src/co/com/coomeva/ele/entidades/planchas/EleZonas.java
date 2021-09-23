package co.com.coomeva.ele.entidades.planchas;

import java.util.Set;

/**
 * EleZonas entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class EleZonas extends AbstractEleZonas implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public EleZonas() {
	}

	/** minimal constructor */
	public EleZonas(String codZona, Long maxPrincipales, String zonEspecial,
			String nomZona) {
		super(codZona, maxPrincipales, zonEspecial, nomZona);
	}

	/** full constructor */
	public EleZonas(String codZona, Long maxPrincipales, String zonEspecial,
			String nomZona, Set elePlanchases) {
		super(codZona, maxPrincipales, zonEspecial, nomZona, elePlanchases);
	}

}
