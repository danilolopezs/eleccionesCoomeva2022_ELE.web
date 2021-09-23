package co.com.coomeva.wseleconsultahab.vista;

public class MensajesVista {
	private boolean visible = false;
	private String mensaje = "";
	

	public MensajesVista(){
		super();
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void mostrarMensaje(String mensaje) {
		// Muestra el mensaje en el panel popup
		this.mensaje = mensaje;
		visible = true;

	}
	public void ocultarMensaje(){
		this.mensaje = "";
		visible = false;
	}


	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
