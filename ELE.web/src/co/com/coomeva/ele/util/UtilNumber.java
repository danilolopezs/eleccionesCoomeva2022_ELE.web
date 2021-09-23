package co.com.coomeva.ele.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


public class UtilNumber {
	
	private static UtilNumber instance;

	// Patrón Singular
	public static UtilNumber getInstance() {
		if (instance == null) {
			instance = new UtilNumber();
		}
		return instance;
	}

	public double getDecimal(int numeroDecimales, double decimal) {
		decimal = decimal * (java.lang.Math.pow(10, numeroDecimales));
		decimal = java.lang.Math.round(decimal);
		decimal = decimal / java.lang.Math.pow(10, numeroDecimales);

		return decimal;
	}
	
	public String getDecimalFormat(Double number, String formato){
		
		if(formato==null || formato.isEmpty()){
			formato = "#,###.00";
		}
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator(',');
		simbolos.setGroupingSeparator('.');
		
		DecimalFormat formatter = new DecimalFormat(formato);
		
		
		return formatter.format(number);
	}

}
