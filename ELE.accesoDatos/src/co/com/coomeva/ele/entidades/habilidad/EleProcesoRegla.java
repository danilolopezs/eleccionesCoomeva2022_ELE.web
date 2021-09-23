package co.com.coomeva.ele.entidades.habilidad;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Entidad de la tabla ProcesoRegla
 * 
 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
 * @project ELE.accesoDatos
 * @class EleProcesoRegla
 * @date 2/11/2012
 *
 */
public class EleProcesoRegla  implements java.io.Serializable, Comparable {


     private Long consecutivoProRegla;
     private EleProceso eleProceso;
     private EleRegla eleRegla;
     private Date fechaInicioEjecucion;
     private Date fechaFinEjecucion;
     private String estadoProgramacion;
     private String usuarioRegistra;
     private Set<EleInhabilidad> eleInhabilidads = new HashSet<EleInhabilidad>(0);

    public EleProcesoRegla() {
    }

	
    public EleProcesoRegla(Long consecutivoProRegla) {
        this.consecutivoProRegla = consecutivoProRegla;
    }
    public EleProcesoRegla(Long consecutivoProRegla, EleProceso eleProceso, EleRegla eleRegla, Date fechaInicioEjecucion, Date fechaFinEjecucion, String estadoProgramacion, String usuarioRegistra, Set<EleInhabilidad> eleInhabilidads) {
       this.consecutivoProRegla = consecutivoProRegla;
       this.eleProceso = eleProceso;
       this.eleRegla = eleRegla;
       this.fechaInicioEjecucion = fechaInicioEjecucion;
       this.fechaFinEjecucion = fechaFinEjecucion;
       this.estadoProgramacion = estadoProgramacion;
       this.usuarioRegistra = usuarioRegistra;
       this.eleInhabilidads = eleInhabilidads;
    }
   
    public Long getConsecutivoProRegla() {
        return this.consecutivoProRegla;
    }
    
    public void setConsecutivoProRegla(Long consecutivoProRegla) {
        this.consecutivoProRegla = consecutivoProRegla;
    }
    public EleProceso getEleProceso() {
        return this.eleProceso;
    }
    
    public void setEleProceso(EleProceso eleProceso) {
        this.eleProceso = eleProceso;
    }
    public EleRegla getEleRegla() {
        return this.eleRegla;
    }
    
    public void setEleRegla(EleRegla eleRegla) {
        this.eleRegla = eleRegla;
    }
    public Date getFechaInicioEjecucion() {
        return this.fechaInicioEjecucion;
    }
    
    public void setFechaInicioEjecucion(Date fechaInicioEjecucion) {
        this.fechaInicioEjecucion = fechaInicioEjecucion;
    }
    public Date getFechaFinEjecucion() {
        return this.fechaFinEjecucion;
    }
    
    public void setFechaFinEjecucion(Date fechaFinEjecucion) {
        this.fechaFinEjecucion = fechaFinEjecucion;
    }
    public String getEstadoProgramacion() {
        return this.estadoProgramacion;
    }
    
    public void setEstadoProgramacion(String estadoProgramacion) {
        this.estadoProgramacion = estadoProgramacion;
    }
    public String getUsuarioRegistra() {
        return this.usuarioRegistra;
    }
    
    public void setUsuarioRegistra(String usuarioRegistra) {
        this.usuarioRegistra = usuarioRegistra;
    }
    public Set<EleInhabilidad> getEleInhabilidads() {
        return this.eleInhabilidads;
    }
    
    public void setEleInhabilidads(Set<EleInhabilidad> eleInhabilidads) {
        this.eleInhabilidads = eleInhabilidads;
    }
    
    public final int compareTo(Object o) {
		if(o instanceof EleProcesoRegla){
			return this.getEleRegla().getCodigoRegla()
			.compareTo(((EleProcesoRegla)o).getEleRegla().getCodigoRegla());
		}
		return 0;
	}
}


