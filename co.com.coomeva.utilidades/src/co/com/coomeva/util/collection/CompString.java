package co.com.coomeva.util.collection;

import java.util.Comparator;

public class CompString implements Comparator {

	public CompString() {
		// TODO Auto-generated constructor stub
	}

	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		int salida = o1.toString().compareToIgnoreCase(o2.toString());
		return salida;
	}

}
