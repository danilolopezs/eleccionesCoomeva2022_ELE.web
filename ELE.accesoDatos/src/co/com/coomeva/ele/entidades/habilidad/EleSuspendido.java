package co.com.coomeva.ele.entidades.habilidad;


import java.util.Date;

/**
 * Entidad de la tabla suspendidos
 * 
 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
 * @project ELE.accesoDatos
 * @class EleSuspendido
 * @date 2/11/2012
 *
 */
public class EleSuspendido  implements java.io.Serializable {


     private Long codigoAsociado;
     private String motivo;
     private String estado;
     private Date fechaRegistro;
     private Date fechaSuspencion;
     private String usuario;

    public EleSuspendido() {
    }

	
    public EleSuspendido(Long codigoAsociado) {
        this.codigoAsociado = codigoAsociado;
    }
    
   
	public EleSuspendido(Long codigoAsociado, String motivo, String estado,
			Date fechaRegistro, Date fechaSuspencion, String usuario) {
		
		this.codigoAsociado = codigoAsociado;
		this.motivo = motivo;
		this.estado = estado;
		this.fechaRegistro = fechaRegistro;
		this.fechaSuspencion = fechaSuspencion;
		this.usuario = usuario;
	}


	public Long getCodigoAsociado() {
        return this.codigoAsociado;
    }
    
    public void setCodigoAsociado(Long codigoAsociado) {
        this.codigoAsociado = codigoAsociado;
    }
    public String getMotivo() {
        return this.motivo;
    }
    
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }
    
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }


	public Date getFechaSuspencion() {
		return fechaSuspencion;
	}


	public void setFechaSuspencion(Date fechaSuspencion) {
		this.fechaSuspencion = fechaSuspencion;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}




}


