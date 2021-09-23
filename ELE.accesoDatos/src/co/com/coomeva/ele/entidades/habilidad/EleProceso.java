package co.com.coomeva.ele.entidades.habilidad;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Entidad de la tabla Proceso
 * 
 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
 * @project ELE.accesoDatos
 * @class EleProceso
 * @date 2/11/2012
 *
 */
public class EleProceso  implements java.io.Serializable {


     private Long codigoProceso;
     private Date fechaProgramacion;
     private String estadoProceso;
     private Date fechaCorte;
     
     private Set<EleProcesoRegla> eleProcesoReglas = new HashSet<EleProcesoRegla>(0);

    public EleProceso() {
    }

	
    public EleProceso(Long codigoProceso) {
        this.codigoProceso = codigoProceso;
    }
    public EleProceso(Long codigoProceso, Date fechaProgramacion, String estadoProceso, Set<EleProcesoRegla> eleProcesoReglas) {
       this.codigoProceso = codigoProceso;
       this.fechaProgramacion = fechaProgramacion;
       this.estadoProceso = estadoProceso;
       this.eleProcesoReglas = eleProcesoReglas;
    }
   
    public Long getCodigoProceso() {
        return this.codigoProceso;
    }
    
    public void setCodigoProceso(Long codigoProceso) {
        this.codigoProceso = codigoProceso;
    }
    public Date getFechaProgramacion() {
        return this.fechaProgramacion;
    }
    
    public void setFechaProgramacion(Date fechaProgramacion) {
        this.fechaProgramacion = fechaProgramacion;
    }
    public String getEstadoProceso() {
        return this.estadoProceso;
    }
    
    public void setEstadoProceso(String estadoProceso) {
        this.estadoProceso = estadoProceso;
    }
    public Set<EleProcesoRegla> getEleProcesoReglas() {
        return this.eleProcesoReglas;
    }
    
    public void setEleProcesoReglas(Set<EleProcesoRegla> eleProcesoReglas) {
        this.eleProcesoReglas = eleProcesoReglas;
    }


	public Date getFechaCorte() {
		return fechaCorte;
	}


	public void setFechaCorte(Date fechaCorte) {
		this.fechaCorte = fechaCorte;
	}




}


