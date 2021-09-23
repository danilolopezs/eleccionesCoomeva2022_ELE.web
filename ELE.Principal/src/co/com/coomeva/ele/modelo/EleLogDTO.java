package co.com.coomeva.ele.modelo;

public class EleLogDTO 
{
	private String tipoTransaccion;
	private String nombreTransaccion;
	private String nombreUsuario;
	
	public String getTipoTransaccion() {
		return tipoTransaccion;
	}
	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
	public String getNombreTransaccion() {
		return nombreTransaccion;
	}
	public void setNombreTransaccion(String nombreTransaccion) {
		this.nombreTransaccion = nombreTransaccion;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
}