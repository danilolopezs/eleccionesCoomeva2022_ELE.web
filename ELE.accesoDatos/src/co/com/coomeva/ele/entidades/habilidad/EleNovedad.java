package co.com.coomeva.ele.entidades.habilidad;
// Generated 29/10/2012 06:02:13 PM by Zathura powered by Hibernate Tools 3.2.4.GA


import java.sql.Timestamp;
import java.util.Date;

/**
 * Entidad de la tabla Novedad
 * 
 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
 * @project ELE.accesoDatos
 * @class EleNovedad
 * @date 2/11/2012
 *
 */
public class EleNovedad  implements java.io.Serializable {


     private Long consecutivoNovedad;
     private EleAsociado eleAsociado;
     private String estadoHabilidad;
     private Timestamp fechaRegistroAud;
     private Date fechaRegistro;
     private String observacion;
     private String usuarioRegistro;

    public EleNovedad() {
    }

	
    public EleNovedad(Long consecutivoNovedad) {
        this.consecutivoNovedad = consecutivoNovedad;
    }
    public EleNovedad(Long consecutivoNovedad, EleAsociado eleAsociado, String estadoHabilidad, Timestamp fechaRegistro) {
       this.consecutivoNovedad = consecutivoNovedad;
       this.eleAsociado = eleAsociado;
       this.estadoHabilidad = estadoHabilidad;
       this.fechaRegistro = fechaRegistro;
    }
   
    public Long getConsecutivoNovedad() {
        return this.consecutivoNovedad;
    }
    
    public void setConsecutivoNovedad(Long consecutivoNovedad) {
        this.consecutivoNovedad = consecutivoNovedad;
    }
    public EleAsociado getEleAsociado() {
        return this.eleAsociado;
    }
    
    public void setEleAsociado(EleAsociado eleAsociado) {
        this.eleAsociado = eleAsociado;
    }
    public String getEstadoHabilidad() {
        return this.estadoHabilidad;
    }
    
    public void setEstadoHabilidad(String estadoHabilidad) {
        this.estadoHabilidad = estadoHabilidad;
    }

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}


	public Timestamp getFechaRegistroAud() {
		return fechaRegistroAud;
	}


	public void setFechaRegistroAud(Timestamp fechaRegistroAud) {
		this.fechaRegistroAud = fechaRegistroAud;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}
	
}


