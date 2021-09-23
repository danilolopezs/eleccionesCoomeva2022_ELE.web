package co.com.coomeva.ele.entidades.habilidad;
// Generated 29/10/2012 06:02:13 PM by Zathura powered by Hibernate Tools 3.2.4.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Entidad de la tabla regla
 * 
 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
 * @project ELE.accesoDatos
 * @class EleRegla
 * @date 2/11/2012
 *
 */
public class EleRegla  implements java.io.Serializable {


     private Long codigoRegla;
     private String descripcionRegla;
     private String estadoRegla;
     private String nombreReglaJava;
     private String obsViolacionRegla;
     private Set<EleProcesoRegla> eleProcesoReglas = new HashSet<EleProcesoRegla>(0);

    public EleRegla() {
    }

	
    public EleRegla(Long codigoRegla) {
        this.codigoRegla = codigoRegla;
    }
    public EleRegla(Long codigoRegla, String descripcionRegla, String estadoRegla, Set<EleProcesoRegla> eleProcesoReglas, String nombreReglaJava, String obsViolacionRegla) {
       this.codigoRegla = codigoRegla;
       this.descripcionRegla = descripcionRegla;
       this.estadoRegla = estadoRegla;
       this.eleProcesoReglas = eleProcesoReglas;
       this.nombreReglaJava = nombreReglaJava;
       this.obsViolacionRegla = obsViolacionRegla;
    }
   
    public Long getCodigoRegla() {
        return this.codigoRegla;
    }
    
    public void setCodigoRegla(Long codigoRegla) {
        this.codigoRegla = codigoRegla;
    }
    public String getDescripcionRegla() {
        return this.descripcionRegla;
    }
    
    public void setDescripcionRegla(String descripcionRegla) {
        this.descripcionRegla = descripcionRegla;
    }
    public String getEstadoRegla() {
        return this.estadoRegla;
    }
    
    public void setEstadoRegla(String estadoRegla) {
        this.estadoRegla = estadoRegla;
    }
    public Set<EleProcesoRegla> getEleProcesoReglas() {
        return this.eleProcesoReglas;
    }
    
    public void setEleProcesoReglas(Set<EleProcesoRegla> eleProcesoReglas) {
        this.eleProcesoReglas = eleProcesoReglas;
    }

	public String getNombreReglaJava() {
		return nombreReglaJava;
	}

	public void setNombreReglaJava(String nombreReglaJava) {
		this.nombreReglaJava = nombreReglaJava;
	}

	public String getObsViolacionRegla() {
		return obsViolacionRegla;
	}

	public void setObsViolacionRegla(String obsViolacionRegla) {
		this.obsViolacionRegla = obsViolacionRegla;
	}
}


