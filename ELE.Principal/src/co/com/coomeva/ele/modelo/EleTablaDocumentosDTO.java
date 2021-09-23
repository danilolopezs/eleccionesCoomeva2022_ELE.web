package co.com.coomeva.ele.modelo;


public class EleTablaDocumentosDTO {
	private String cedulaPrincipal;
	private boolean cabezaPlancha;
	private String cedulaSuplente;
	
	public String getCedulaPrincipal() {
		return cedulaPrincipal;
	}
	public void setCedulaPrincipal(String cedulaPrincipal) {
		this.cedulaPrincipal = cedulaPrincipal;
	}
	public String getCedulaSuplente() {
		return cedulaSuplente;
	}
	public void setCedulaSuplente(String cedulaSuplente) {
		this.cedulaSuplente = cedulaSuplente;
	}
	public boolean isCabezaPlancha() {
		return cabezaPlancha;
	}
	public void setCabezaPlancha(boolean cabezaPlancha) {
		this.cabezaPlancha = cabezaPlancha;
	}
	
}
