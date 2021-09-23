package co.com.coomeva.ele.backbeans;

public class MensajesVista 
{
	private boolean visible = false;
	private String mensaje = "";
	
	public MensajesVista(){
		super();
	}
	
	/**
	 * Metodo que muestra los mensajes de execption en un PopUp
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param mensaje
	 */
	public void mostrarMensaje(String mensaje) {
		// Muestra el mensaje en el panel popup
		this.mensaje = mensaje;
		visible = true;
	}
	
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return String
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 */
	public void ocultarMensaje(){
		this.mensaje = "";
		visible = false;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @return boolean
	 */
	public boolean isVisible() {
		return visible;
	}
	/**
	 * Metodo de Acceso
	 * @author Manuel Galvez, Ricardo Chiriboga
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}