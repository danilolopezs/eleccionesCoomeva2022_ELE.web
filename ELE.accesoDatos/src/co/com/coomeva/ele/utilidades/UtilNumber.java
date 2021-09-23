package co.com.coomeva.ele.utilidades;

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

	public double redondear(double numero, int digitos) {
		int cifras = (int) Math.pow(10, digitos);
		return Math.rint(numero * cifras) / cifras;
	}

}
