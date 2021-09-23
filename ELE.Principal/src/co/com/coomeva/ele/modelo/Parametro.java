package co.com.coomeva.ele.modelo;

import co.com.coomeva.ele.entidades.planchas.ElePParametros;

public class Parametro {

	private ElePParametros parametro;
 
	public Parametro(){
		parametro = new ElePParametros();
	}
	
	public Parametro(ElePParametros parametro){
		this.setParametro(parametro);
	}
	
	public ElePParametros getParametro() {
		return parametro;
	}

	public void setParametro(ElePParametros parametro) {
		this.parametro = parametro;
	}

}
