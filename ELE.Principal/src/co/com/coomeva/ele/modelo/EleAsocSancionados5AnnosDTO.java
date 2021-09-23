package co.com.coomeva.ele.modelo;

import java.util.Date;

import co.com.coomeva.ele.entidades.admhabilidad.Asoelecf;
import co.com.coomeva.ele.entidades.habilidad.EleSuspendido;

public class EleAsocSancionados5AnnosDTO
{
	 private String documento;
	 private String nombres;
	 private Date fechaSuspension;
	 private String motivo;
	 
	 
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public Date getFechaSuspension() {
		return fechaSuspension;
	}
	public void setFechaSuspension(Date fechaSuspension) {
		this.fechaSuspension = fechaSuspension;
	}
	
	 
}
