package co.com.coomeva.ele.modelo;

public class ParametroPlanchaDTO {
	
	private String strValor;
	private String nombre;	
	private Long valor;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Long getValor() {
		return valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}
	public String getStrValor() {
		return strValor;
	}
	public void setStrValor(String strValor) {
		this.strValor = strValor;
	}
}
