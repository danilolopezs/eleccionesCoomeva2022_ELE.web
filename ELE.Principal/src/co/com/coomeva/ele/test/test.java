package co.com.coomeva.ele.test;

import java.util.List;

import co.com.coomeva.ele.delegado.DelegadoUbicacion;


public class test {
	public static void main(String[] args) {
		try {
			List<Object[]> lista =DelegadoUbicacion.getInstance().consultarRegionales();	
			System.out.println(lista);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
